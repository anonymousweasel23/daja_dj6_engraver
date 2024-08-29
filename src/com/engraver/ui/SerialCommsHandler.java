package com.engraver.ui;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fazecast.jSerialComm.SerialPort;

/**
 * The class seems to handle comms to/from the device converting the 
 * payload data to frame-specific data for the device
 * 
 * e.g.
 * Sending to device: 
 * first byte is -85 (tx preamble)
 * second byte seems to be message id
 * third byte seems to be msb of message length
 * fourth byte seems to be (lsb) message length
 * last two bytes are a 16bit checksum 
 * <p>
 * Recieving from device
 * First byte is 186 (rx preamble)
 * Second byte is 1 or 2 (this seems to relate to 2x models with different scale factor)
 * third byte seems to be msb of message length
 * fourth byte seems to be (lsb) message length
 * Only 28 / 29 bytes received in each frame - the larger model has a 2-byte scale factor
 */
public class SerialCommsHandler {

	private SerialPort com;
	private Boolean portOpen = false;
	//public Boolean kai_shi_du_qu = true;
	private boolean messageReceived = false;

	private int[] rxBuffer = new int[28];
	private int rxIndex = 0;
	private int rxLength = 0;

	private Object txLock = new Object();

	private String osName = "";
	private OutputStream out = null;

	private final Lock lock = new ReentrantLock();
	private final Condition awaiting = lock.newCondition();

	private Thread rxReadThread;
	private boolean shouldRun = true;
	private ReentrantLock commLock =  new ReentrantLock();
	private Condition awaitingCommPort = commLock.newCondition();

	public SerialCommsHandler() {
		rxReadThread = new Thread(new Runnable() {
			@Override
			public void run() {

				while (shouldRun) {
					try {
						commLock.lock();
						while (!portOpen) {
							System.out.println("waiting...");
							awaitingCommPort.await();
						}

					} catch (InterruptedException e) {
						return;
					} finally {
						commLock.unlock();
					}
					//Read message from engraver
					System.out.println("reading...");
					receiveByteArrayFrame();
				}
			}
		});
		rxReadThread.setName("Serial Port Reader Thread");
		rxReadThread.setDaemon(true);
		rxReadThread.start();
	}

	/**
	 * This method blocks
	 * 
	 * @param comPort
	 * @return
	 */
	public boolean testPort(String comPort) {

		String oldPort = null;
		boolean ret = false;

		try {

			if (isOpen()) {
				oldPort = portName();
				closePort();
			}

			openPort(comPort);
			commandQuery();
			ret = waitForMessageReceived(3000l);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			closePort();
		}

		if (oldPort != null)
			openPort(oldPort);

		return ret;
	}
	
	public boolean isOpen() {
		
		try {
			commLock.lock();
			return portOpen;
		} finally {
			commLock.unlock();
		}
	}

	public String portName() {
		try {
			commLock.lock();
			return com.getSystemPortName();
		} finally {
			commLock.unlock();
		}
	}

	public boolean openPort(final String comPort) {

		try {
			commLock.lock();
			if (portOpen)
				return false;
			com = SerialPort.getCommPort(comPort);
			com.setBaudRate(921600);
			com.openPort();
			out = com.getOutputStream();
			portOpen = true;

			//Wake up the reader thread
			awaitingCommPort.signalAll();

		} finally {
			commLock.unlock();
		}
		return true;
	}

	public void closePort() {
		try {
			commLock.lock();
			rxIndex = 0;
			rxLength = 0;
			portOpen = false;
			if (com != null) 
				com.closePort();
		} finally {
			commLock.unlock();
		}
		setMessageReceivedFlag(false);
	}

	private void setMessageReceivedFlag(boolean tf) {
		try {
			lock.lock();
			messageReceived = tf;
			notifyThreads();
		} finally {
			lock.unlock();
		}
	}

	private void notifyThreads() {
		try {
			lock.lock();
			awaiting.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public boolean waitForMessageReceived(long timeout) throws InterruptedException {
		try {
			lock.lock();
			awaiting.await(timeout,TimeUnit.MILLISECONDS);
			return messageReceived; 
		} finally {
			lock.unlock();
		}
	}

	public boolean messageReceived() {
		try {
			lock.lock();
			return messageReceived;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * We really should not need this...
	 * @param name
	 */
	public void setOsName(String name) {
		this.osName = name;
	}

	/**
	 * Read data bytewise from the serial port.
	 * <p>
	 * There seem to be 2x fram lengths available which are selected
	 * via the second byte value. The code runs until the length
	 * of the frame has been filled then sets
	 */
	void receiveByteArrayFrame() {
		while(this.portOpen) {
			if (this.com.isOpen()) {
				int shu = this.com.bytesAvailable();
				if (shu > 0) {
					byte[] shu_m = new byte[1];
					this.com.readBytes(shu_m, 1L);
					int m = shu_m[0];
					if (m < 0) {
						m += 256;
					}

					if (this.rxIndex == 0) {
						if (m == 186) {
							++this.rxIndex;
						} else {
							System.out.println("第一个字节不是0xba!" + m + "\r\n");
						}
					} else if (this.rxIndex == 1) {
						if (m == 1) {
							this.rxBuffer = new int[28];
							this.rxBuffer[0] = 186;
							this.rxBuffer[1] = 1;
							++this.rxIndex;
						} else if (m == 2) {
							this.rxBuffer = new int[29];
							this.rxBuffer[0] = 186;
							this.rxBuffer[1] = 2;
							++this.rxIndex;
						}
					} else if (this.rxIndex == 2) {
						this.rxLength = m << 8;
						this.rxBuffer[2] = m;
						++this.rxIndex;
					} else if (this.rxIndex == 3) {
						this.rxLength |= m;
						this.rxBuffer[3] = m;
						++this.rxIndex;
					} else if (this.rxIndex > 3) {
						this.rxBuffer[this.rxIndex++] = m;
						if (this.rxIndex == this.rxBuffer.length) {
							if (rxIndex != rxLength)
								System.out.println(String.format("Problem, expected %d bytes, received %d", rxLength, rxIndex));
							onMessageReceived();
							rxIndex = 0;
						}
					}
				}
			}
		}

	}

	public void ming_ling_gun_zhou() {
		byte[] m = new byte[]{-85, 2, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_guan_gun_zhou() {
		byte[] m = new byte[]{-85, 3, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_1064() {
		byte[] m = new byte[]{-85, 22, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_guan_1064() {
		byte[] m = new byte[]{-85, 23, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_zan_ting() {
		byte[] m = new byte[]{-85, 8, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_ji_xu() {
		byte[] m = new byte[]{-85, 9, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	/**
	 * This seems to be a generic system poll with no actions
	 */
	public void commandQuery() {
		byte[] m = new byte[]{-85, 1, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_chu_shi_hua() {
		byte[] m = new byte[]{-85, 13, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_qing_flash() {
		byte[] m = new byte[]{-85, 103, 0, 8, 0, 115, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void sendStopCommand() {
		byte[] m = new byte[]{-85, 7, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_IAP() {
		byte[] m = new byte[]{-85, 10, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_she_zhi(int rg, int jd) {
		byte[] m = new byte[]{-85, 12, 0, 8, (byte)rg, (byte)jd, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_fu_wei() {
		byte[] m = new byte[]{-85, 105, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_yu_lan(int zx, int zy, int k, int g) {
		byte[] m = new byte[]{-85, 4, 0, 14, (byte)(zx >> 8), (byte)zx, (byte)(zy >> 8), (byte)zy, (byte)(k >> 8), (byte)k, (byte)(g >> 8), (byte)g, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void sendStopPreviewCommand() {
		byte[] m = new byte[]{-85, 6, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_gl_sd(int gl, int sd) {
		byte[] m = new byte[]{-85, 11, 0, 8, (byte)gl, (byte)sd, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_qing_flash(int shan_qu, int ban_ben, int z_wt, int s_wt, int kuan_wt, int gao_wt, int weizhi_wt, int gonglv_wt, int shendu_wt, int z_sl, int s_sl, int kuan_sl, int gao_sl, int weizhi_sl, int gonglv_sl, int shendu_sl, int dianshu_sl, int ci_shu) {
		while(Engraver.qu_zhuang_tai() != 0) {
			if (Engraver.ting_zhi) {
				return;
			}

			this.ming_ling_chu_shi_hua();

			try {
				Thread.sleep(200L);
			} catch (InterruptedException var21) {
				InterruptedException ex = var21;
				Logger.getLogger(SerialCommsHandler.class.getName()).log(Level.SEVERE, (String)null, ex);
			}
		}

		byte[] m = new byte[]{-85, 100, 0, 40, (byte)(shan_qu >> 8), (byte)shan_qu, (byte)ban_ben, (byte)(z_wt >> 8), (byte)z_wt, (byte)(s_wt >> 8), (byte)s_wt, (byte)(kuan_wt >> 8), (byte)kuan_wt, (byte)(gao_wt >> 8), (byte)gao_wt, (byte)(weizhi_wt >> 8), (byte)weizhi_wt, (byte)gonglv_wt, (byte)shendu_wt, (byte)(z_sl >> 8), (byte)z_sl, (byte)(s_sl >> 8), (byte)s_sl, (byte)(kuan_sl >> 8), (byte)kuan_sl, (byte)(gao_sl >> 8), (byte)gao_sl, (byte)(weizhi_sl >> 24), (byte)(weizhi_sl >> 16), (byte)(weizhi_sl >> 8), (byte)weizhi_sl, (byte)gonglv_sl, (byte)shendu_sl, (byte)(dianshu_sl >> 24), (byte)(dianshu_sl >> 16), (byte)(dianshu_sl >> 8), (byte)dianshu_sl, (byte)ci_shu, 0, 0};
		m = Engraver.packFrame(m);

		InterruptedException ex;
		while(Engraver.qu_zhuang_tai() == 0) {
			if (Engraver.ting_zhi) {
				return;
			}

			this.transmitFrame(m);

			try {
				Thread.sleep(100L);
			} catch (InterruptedException var23) {
				ex = var23;
				Logger.getLogger(SerialCommsHandler.class.getName()).log(Level.SEVERE, (String)null, ex);
			}
		}

		while(Engraver.qu_zhuang_tai() == 1) {
			if (Engraver.ting_zhi) {
				return;
			}

			try {
				Thread.sleep(100L);
			} catch (InterruptedException var22) {
				ex = var22;
				Logger.getLogger(SerialCommsHandler.class.getName()).log(Level.SEVERE, (String)null, ex);
			}
		}

	}

	public void ming_ling_fa_shu_ju(byte[] m) {
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	public void ming_ling_diao_ke() {
		byte[] m = new byte[]{-85, 16, 0, 6, 0, 0};
		m = Engraver.packFrame(m);
		this.transmitFrame(m);
	}

	void transmitFrame(byte[] m) {
		//This mutex should be safe enough
		synchronized(txLock) {
			if (this.com.isOpen()) {
				try {
					if (m.length > 1000) {
						if (this.osName.contains("Win")) {
							this.out.write(m);
						} else {
							for(int i = 0; i < m.length / 1000 * 1000; i += 1000) {
								this.out.write(m, i, 1000);
								Thread.sleep(80L);
								this.out.flush();
							}

							this.out.write(m, m.length / 1000 * 1000, m.length % 1000);
							this.out.flush();
						}
					} else {
						this.com.writeBytes(m, (long)m.length);
					}
				} catch (IOException var5) {
					IOException ex = var5;
					Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
				} catch (InterruptedException var6) {
					InterruptedException ex = var6;
					Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
				}
			}

		}
	}

	private void onMessageReceived() {

			int checksum = this.rxBuffer[this.rxBuffer.length - 2] << 8 | this.rxBuffer[this.rxBuffer.length - 1];
			int newChecksum = 0;

			for(int i = 0; i < this.rxBuffer.length - 2; ++i) {
				newChecksum += this.rxBuffer[i];
			}

			//The frame unpacked here
			if (checksum == newChecksum) {
				setMessageReceivedFlag(true);

				//First 4 bytes are probably preamble, msgid and msg len

				//Device id
				Engraver.state.hardwareId = this.rxBuffer[4];

				//Two bytes - firmware version
				Engraver.state.softwareVersion = this.rxBuffer[5] << 8 | this.rxBuffer[6];

				//Two bytes (max width?)
				Engraver.state.width = this.rxBuffer[7] << 8 | this.rxBuffer[8];

				//Two bytes (max height)
				Engraver.state.height = this.rxBuffer[9] << 8 | this.rxBuffer[10];

				//Two bytes (current horizontal position?)
				Engraver.state.x = (double)(this.rxBuffer[11] << 8 | this.rxBuffer[12]) * Engraver.state.scaleFactor;
				//Two bytes (current vertical position?)
				Engraver.state.y = (double)(this.rxBuffer[13] << 8 | this.rxBuffer[14]) * Engraver.state.scaleFactor;

				//Percentage of job completed
				Engraver.state.completed = this.rxBuffer[15];

				//100% - speed %
				Engraver.state.laserSpeedFactor = this.rxBuffer[16];

				//Power level in %
				Engraver.state.laserPowerLevel = this.rxBuffer[17];

				Engraver.state.standby = this.rxBuffer[18];

				//Really?
				Engraver.state.serialNumber = this.rxBuffer[19] << 8 | this.rxBuffer[20];

				//Bitmask / register values?
				Engraver.state.stateOne = this.rxBuffer[21];
				Engraver.state.stateTwo = this.rxBuffer[22];

				//
				Engraver.state.memory = this.rxBuffer[23];
				Engraver.state.model = this.rxBuffer[25];

				if (this.rxBuffer[1] == 1) {
					Engraver.state.scaleFactor = 10.0 / (double)this.rxBuffer[24];
				} else if (this.rxBuffer[1] == 2) {
					Engraver.state.scaleFactor = 10.0 / (double)(this.rxBuffer[26] << 8 | this.rxBuffer[24]);
				}

				System.out.println("Received message: " + Engraver.state);

			} else {
				System.out.println(String.format("Invalid frame checksum %d received, %d expected!",newChecksum,checksum));
				setMessageReceivedFlag(false);
			}
	}
}
