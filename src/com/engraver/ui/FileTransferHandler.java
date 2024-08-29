package com.engraver.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.TransferHandler;

class FileTransferHandler extends TransferHandler {
   public static GraphicsPanel hb = null;
   public static JSlider jSlider1 = null;
   public static JSlider jSlider2 = null;

   public boolean importData(JComponent comp, Transferable t) {
      try {
         List list = (List)t.getTransferData(DataFlavor.javaFileListFlavor);
         Iterator var4 = list.iterator();

         while(true) {
            while(true) {
               while(var4.hasNext()) {
                  File f = (File)var4.next();
                  System.out.println(f.getAbsolutePath());
                  String fileName = f.getAbsolutePath();
                  String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                  suffix = suffix.toUpperCase();
                  BufferedImage bmp;
                  if (!suffix.equals("BMP") && !suffix.equals("JPG") && !suffix.equals("PNG") && !suffix.equals("JPEG") && !suffix.equals("GIF")) {
                     if (suffix.equals("XJ")) {
                        try {
                           ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));

                           try {
                              GraphicsPanel.ty_shuzu = (List)ois.readObject();
                           } catch (Throwable var12) {
                              try {
                                 ois.close();
                              } catch (Throwable var11) {
                                 var12.addSuppressed(var11);
                              }

                              throw var12;
                           }

                           ois.close();
                        } catch (Exception var13) {
                           var13.printStackTrace();
                        }

                        for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
                           if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1) {
                              ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu = new BufferedImage(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_g, 2);
                              ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan = new BufferedImage(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_g, 2);
                              ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu.setRGB(0, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_g, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wt_w);
                              ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan.setRGB(0, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_w, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_g, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wei_tu_yuan_, 0, ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wty_w);
                           }
                        }

                        if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).gong_lv != 0 && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).shen_du != 0) {
                           jSlider1.setValue(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).gong_lv);
                           jSlider2.setValue(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).shen_du);
                        }

                        GraphicsPanel.quan_beishu = ((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).quan_beishu_;
                        if (hb != null) {
                           hb.repaint();
                        }
                     } else {
                        jie_xi_PLT plt;
                        if (suffix.equals("PLT")) {
                           plt = new jie_xi_PLT();
                           plt.jie_xi_PLT(f);
                           bmp = null;
                           hb.updateUI();
                        } else if (suffix.equals("HPGL")) {
                           plt = new jie_xi_PLT();
                           plt.jie_xi_PLT(f);
                           bmp = null;
                           hb.updateUI();
                        } else if (!suffix.equals("SVG") && suffix.equals("DXF")) {
                           jie_xi_dxf.jie_xi(f);
                           Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
                           hb.updateUI();
                           Che_xiao.tian_jia();
                        }
                     }
                  } else {
                     try {
                        bmp = ImageIO.read(new File(fileName));
                        if (bmp.getWidth() > 2400 || bmp.getHeight() > 2400) {
                           double bi;
                           if (bmp.getWidth() > bmp.getHeight()) {
                              bi = 2400.0 / (double)bmp.getWidth();
                           } else {
                              bi = 2400.0 / (double)bmp.getHeight();
                           }

                           bmp = Tu_pian.zoomImage(bmp, bi);
                        }

                        GraphicsPanel.ty_shuzu.add(Tu_yuan.chuang_jian(1, bmp));

                        for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
                           ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
                        }

                        ((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
                        Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
                        if (hb != null) {
                           hb.repaint();
                        }
                     } catch (IOException var14) {
                     }
                  }
               }

               return true;
            }
         }
      } catch (Exception var15) {
         Exception e = var15;
         e.printStackTrace();
         return true;
      }
   }

   public boolean canImport(TransferHandler.TransferSupport support) {
      return true;
   }
}
