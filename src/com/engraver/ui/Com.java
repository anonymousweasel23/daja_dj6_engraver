package com.engraver.ui;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 * Not sure why this exists
 */
@Deprecated
public class Com {
   SerialPort com = null;
   OutputStream out = null;
   boolean fan_hui_ma = false;
   public byte[] fan_hui_shu = new byte[]{0, 0, 0, 0};
   public JProgressBar jdt;
   int fh_shu = 0;
   public int jie_shou_lei_xing = 0;
   public int jie_shou_ji_shu = 0;
   public List jie_shou_huan_cun = new ArrayList();
   String osName = "";

   public static void shu_chu(String ss) {
      ApplicationFrame.ce_shi = ApplicationFrame.ce_shi + ss + "\n";
   }

   public Com(SerialPort c) {
      this.jie_shou_huan_cun.add((byte)1);
      synchronized(this) {
         this.com = c;
         this.out = this.com.getOutputStream();
         Properties props = System.getProperties();
         this.osName = props.getProperty("os.name");
         this.com.addDataListener(new SerialPortDataListener() {
            public int getListeningEvents() {
               return 1;
            }

            public void serialEvent(SerialPortEvent event) {
               synchronized(this) {
                  try {
                     ApplicationFrame.chaoshi = 0;
                     SerialPort comPort = event.getSerialPort();
                     byte[] fh = new byte[comPort.bytesAvailable()];
                     int numRead = comPort.readBytes(fh, (long)fh.length);
                     Com.shu_chu("Read " + numRead + " bytes.");

                     int i;
                     for(i = 0; i < numRead; ++i) {
                        Com.shu_chu(String.valueOf(fh[i]));
                     }

                     switch (Com.this.jie_shou_lei_xing) {
                        case 0:
                           Com.this.fan_hui_ma = true;
                           Com.this.fan_hui_shu = fh;
                        case 1:
                        default:
                           break;
                        case 2:
                           Com.this.jie_shou_ji_shu += fh.length;

                           for(i = 0; i < fh.length; ++i) {
                              Com.this.jie_shou_huan_cun.add(fh[i]);
                           }

                           if (Com.this.jie_shou_ji_shu >= 3) {
                              Com.this.fan_hui_shu = new byte[3];

                              for(i = 0; i < 3; ++i) {
                                 Com.this.fan_hui_shu[i] = (Byte)Com.this.jie_shou_huan_cun.get(i);
                              }

                              Com.this.fan_hui_ma = true;
                           }
                           break;
                        case 3:
                           for(i = 0; i < fh.length; ++i) {
                              Com.this.jie_shou_huan_cun.add(fh[i]);
                           }

                           if (Com.this.jie_shou_huan_cun.size() > 3 && (Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 4) == -1 && (Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 3) == -1 && (Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 2) == 0) {
                              Com.this.jdt.setValue((Byte)Com.this.jie_shou_huan_cun.get(Com.this.jie_shou_huan_cun.size() - 1));
                              Com.this.jdt.setVisible(true);
                              ApplicationFrame.kai_shi2 = true;
                              Com.this.jie_shou_huan_cun.clear();
                           }

                           System.out.println(fh.length);
                     }
                  } catch (Exception var8) {
                     Exception e1 = var8;
                     Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, e1);
                  }

               }
            }
         });
      }
   }

   public void guan_bi() {
      if (!this.com.equals((Object)null)) {
         this.com.closePort();
      }

   }

   public boolean du_banben() {
      final Object suo_ = new Object();
      this.fh_shu = 3;
      this.jie_shou_ji_shu = 0;
      this.jie_shou_lei_xing = 2;
      this.jie_shou_huan_cun.clear();
      synchronized(this) {
         ;
      }

      this.fan_hui_ma = false;
      this.com.writeBytes(new byte[]{-1, 0, 4, 0}, 4L);
      Runnable runnable2 = new Runnable() {
         public void run() {
            synchronized(suo_) {
               for(int i = 200; i > 0; --i) {
                  if (Com.this.fan_hui_ma) {
                     if (Com.this.fan_hui_shu.length != 3) {
                        Com.this.fan_hui_ma = false;
                     }
                     break;
                  }

                  try {
                     Thread.sleep(10L);
                  } catch (InterruptedException var5) {
                     InterruptedException ex = var5;
                     Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               }

            }
         }
      };
      Thread thread2 = new Thread(runnable2);
      thread2.start();

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var7) {
         InterruptedException ex = var7;
         Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      synchronized(suo_) {
         return this.fan_hui_ma;
      }
   }

   public boolean fa_song2(byte[] shu, final int chao_shi) {
      final Object suo_ = new Object();
      this.fh_shu = 1;
      this.jie_shou_ji_shu = 0;
      this.jie_shou_lei_xing = 0;
      this.jie_shou_huan_cun.clear();
      synchronized(this) {
         ;
      }

      this.fan_hui_ma = false;
      int fb = 0;
      if (shu.length == 1904) {
         byte[] mb = new byte[1904];
         int fh = 0;
         int zong = 0;

         do {
            System.arraycopy(shu, zong, mb, 0, shu.length - zong);

            try {
               Thread.sleep(10L);
            } catch (InterruptedException var12) {
               InterruptedException ex = var12;
               Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            if (!this.com.isOpen()) {
               this.com.openPort();
            }

            fh = this.com.writeBytes(mb, (long)(shu.length - zong));
            zong += fh;
            shu_chu("fh:" + String.valueOf(fh) + "zong:" + zong);
         } while(zong < shu.length);
      } else {
         shu_chu(String.valueOf(this.com.writeBytes(shu, (long)shu.length)));
      }

      Runnable runnable2 = new Runnable() {
         public void run() {
            synchronized(suo_) {
               for(int i = chao_shi * 100; i > 0; --i) {
                  if (Com.this.fan_hui_ma) {
                     if (Com.this.fan_hui_shu[0] == 9) {
                        break;
                     }

                     if (Com.this.fan_hui_shu[0] == 8) {
                        Com.this.fan_hui_ma = false;
                        break;
                     }
                  }

                  try {
                     Thread.sleep(10L);
                  } catch (InterruptedException var5) {
                     InterruptedException ex = var5;
                     Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               }

            }
         }
      };
      Thread thread2 = new Thread(runnable2);
      thread2.start();

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var11) {
         InterruptedException ex = var11;
         Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      synchronized(suo_) {
         return this.fan_hui_ma;
      }
   }

   public boolean sendBytes(byte[] shu, final int chao_shi) {
      final Object suo_ = new Object();
      this.fh_shu = 1;
      this.jie_shou_ji_shu = 0;
      this.jie_shou_lei_xing = 0;
      this.jie_shou_huan_cun.clear();
      synchronized(this) {
         ;
      }

      this.fan_hui_ma = false;
      int fb = 0;
      if (shu.length == 1904) {
         try {
            if (this.osName.contains("Win")) {
               this.out.write(shu);
            } else {
               for(int i = 0; i < 1000; i += 1000) {
                  this.out.write(shu, i, 1000);
                  Thread.sleep(100L);
               }

               this.out.write(shu, 1000, 904);
            }
         } catch (IOException var12) {
            IOException ex = var12;
            Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (InterruptedException var13) {
            InterruptedException ex = var13;
            Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         this.com.writeBytes(shu, (long)shu.length);
      }

      Runnable runnable2 = new Runnable() {
         public void run() {
            synchronized(suo_) {
               for(int i = chao_shi * 100; i > 0; --i) {
                  if (Com.this.fan_hui_ma) {
                     if (Com.this.fan_hui_shu[0] == 9) {
                        break;
                     }

                     if (Com.this.fan_hui_shu[0] == 8) {
                        Com.this.fan_hui_ma = false;
                        break;
                     }
                  }

                  try {
                     Thread.sleep(10L);
                  } catch (InterruptedException var5) {
                     InterruptedException ex = var5;
                     Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               }

            }
         }
      };
      Thread thread2 = new Thread(runnable2);
      thread2.start();

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var10) {
         InterruptedException ex = var10;
         Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      synchronized(suo_) {
         return this.fan_hui_ma;
      }
   }

   public boolean fa_song_mo_ni(byte[] shu, final int chao_shi) {
      final Object suo_ = new Object();
      this.fh_shu = 1;
      this.jie_shou_ji_shu = 0;
      this.jie_shou_lei_xing = 0;
      this.jie_shou_huan_cun.clear();
      synchronized(this) {
         ;
      }

      this.fan_hui_ma = false;
      int fb = 0;
      byte[] mb = new byte[shu.length];
      int fh = 0;
      int zong = 0;

      try {
         if (this.osName.contains("Win")) {
            this.out.write(shu);
         } else {
            int j = shu.length / 1000;
            if (j > 0) {
               for(int i = 0; i < j; ++i) {
                  this.out.write(shu, i * 1000, 1000);
                  Thread.sleep(100L);
               }

               this.out.write(shu, j * 1000, shu.length - j * 1000);
            } else {
               this.out.write(shu, 0, shu.length);
            }
         }
      } catch (IOException var15) {
         Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, var15);
      } catch (InterruptedException var16) {
         InterruptedException ex = var16;
         Logger.getLogger(Com.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      Runnable runnable2 = new Runnable() {
         public void run() {
            synchronized(suo_) {
               for(int i = chao_shi * 100; i > 0; --i) {
                  if (Com.this.fan_hui_ma) {
                     if (Com.this.fan_hui_shu[0] == 9) {
                        break;
                     }

                     if (Com.this.fan_hui_shu[0] == 8) {
                        Com.this.fan_hui_ma = false;
                        break;
                     }
                  }

                  try {
                     Thread.sleep(10L);
                  } catch (InterruptedException var5) {
                     InterruptedException ex = var5;
                     Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               }

            }
         }
      };
      Thread thread2 = new Thread(runnable2);
      thread2.start();

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var13) {
         InterruptedException ex = var13;
         Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      synchronized(suo_) {
         return this.fan_hui_ma;
      }
   }

   public boolean fa_song_mo_ni2(byte[] shu, final int chao_shi) {
      final Object suo_ = new Object();
      this.fh_shu = 1;
      this.jie_shou_ji_shu = 0;
      this.jie_shou_lei_xing = 0;
      this.jie_shou_huan_cun.clear();
      synchronized(this) {
         ;
      }

      this.fan_hui_ma = false;
      int fb = 0;
      byte[] mb = new byte[shu.length];
      int fh = 0;
      int zong = 0;

      do {
         System.arraycopy(shu, zong, mb, 0, shu.length - zong);

         try {
            Thread.sleep(10L);
         } catch (InterruptedException var14) {
            InterruptedException ex = var14;
            Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         if (!this.com.isOpen()) {
            this.com.openPort();
         }

         fh = this.com.writeBytes(mb, (long)(shu.length - zong));
         zong += fh;
         shu_chu("fh:" + String.valueOf(fh) + "zong:" + zong);
      } while(zong < shu.length);

      Runnable runnable2 = new Runnable() {
         public void run() {
            synchronized(suo_) {
               for(int i = chao_shi * 100; i > 0; --i) {
                  if (Com.this.fan_hui_ma) {
                     if (Com.this.fan_hui_shu[0] == 9) {
                        break;
                     }

                     if (Com.this.fan_hui_shu[0] == 8) {
                        Com.this.fan_hui_ma = false;
                        break;
                     }
                  }

                  try {
                     Thread.sleep(10L);
                  } catch (InterruptedException var5) {
                     InterruptedException ex = var5;
                     Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
                  }
               }

            }
         }
      };
      Thread thread2 = new Thread(runnable2);
      thread2.start();

      try {
         Thread.sleep(100L);
      } catch (InterruptedException var13) {
         InterruptedException ex = var13;
         Logger.getLogger(ApplicationFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

      synchronized(suo_) {
         return this.fan_hui_ma;
      }
   }

   public boolean fa_song_fe(byte[] shu, int chao_shi) {
      this.fh_shu = 4;
      this.jie_shou_ji_shu = 0;
      this.jie_shou_lei_xing = 3;
      this.jie_shou_huan_cun.clear();
      this.fan_hui_ma = false;
      this.com.writeBytes(shu, (long)shu.length);
      return true;
   }

   public boolean fa_song_she_zhi(byte[] shu, int chao_shi) {
      this.fh_shu = 4;
      this.jie_shou_ji_shu = 0;
      this.jie_shou_lei_xing = 0;
      this.jie_shou_huan_cun.clear();
      this.fan_hui_ma = false;
      this.com.writeBytes(shu, (long)shu.length);
      return true;
   }
}
