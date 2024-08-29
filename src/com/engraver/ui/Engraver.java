package com.engraver.ui;

/**
 * Laser Engraver Model
 */
public class Engraver {

	//public int bo_te_lv = 115200;

	public static SerialCommsHandler serialPort = new SerialCommsHandler();
	public static DKJ state = new DKJ();
	public static int gong_lv_wt = 100;
	public static int shen_du_wt = 10;
	public static int diao_ke_ci_shu = 1;
	public static int jin_du;
	public static boolean ting_zhi = false;
	public static boolean dk_shang_ci = false;

	public static int qu_zhuang_tai() {
		return state.stateTwo & 7;
	}

	public static Boolean qu_diao_ke() {
		return (state.stateOne & 16) > 0;
	}

	public static Boolean qu_zan_ting() {
		return (state.stateOne & 2) > 0;
	}

	public static Boolean qu_yu_lan() {
		return (state.stateOne & 64) > 0;
	}

	public static Boolean is_IAP() {
		return state.stateOne == 1;
	}

	public static int qu_jing_du() {
		return state.stateTwo >> 3 & 3;
	}

	public static Boolean qu_gun_zhou() {
		return (state.stateOne & 128) > 0;
	}

	public static Boolean qu_1064() {
		return (state.stateTwo & 64) > 0;
	}

	/**
	 * Appends a (short) checksum to last two members of 
	 * a byte array
	 * @param sz
	 * @return
	 */
	public static byte[] packFrame(byte[] sz) {
		int jiao = 0;

		for(int i = 0; i < sz.length - 2; ++i) {
			if (sz[i] < 0) {
				jiao = jiao + sz[i] + 256;
			} else {
				jiao += sz[i];
			}
		}

		sz[sz.length - 2] = (byte)(jiao >> 8);
		sz[sz.length - 1] = (byte)jiao;
		return sz;
	}

	/**
	 * POJO represents the laser engraver state
	 */
	public static class DKJ {
		public int hardwareId;
		public int softwareVersion;
		public int width;
		public int height;
		public double x;
		public double y;
		public int completed;
		public int laserSpeedFactor; //100 - laser speed
		public int laserPowerLevel; 
		public int standby;
		public int serialNumber;
		//Bitmask
		public int stateOne;
		//Bitmask
		public int stateTwo;
		public int memory; //Memory state? Seems to be always one
		public int model;
		public double scaleFactor; //Change from system scale to h/w scale?

		public int qu_zhuang_tai() {
			return this.stateTwo & 7;
		}

		@Override
		public String toString() {
			return "DKJ [hardwareId=" + hardwareId + ", softwareVersion=" + softwareVersion + ", width=" + width
					+ ", height=" + height + ", x=" + x + ", y=" + y + ", completed=" + completed + ", laserSpeedFactor="
					+ laserSpeedFactor + ", laserPowerLevel=" + laserPowerLevel + ", standby=" + standby + ", serialNumber="
					+ serialNumber + ", stateOne=" + stateOne + ", stateTwo=" + stateTwo + ", memory=" + memory + ", model="
					+ model + ", scaleFactor=" + scaleFactor + "]";
		}


	}
}
