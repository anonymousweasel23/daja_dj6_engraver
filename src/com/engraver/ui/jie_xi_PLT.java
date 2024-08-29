package com.engraver.ui;

import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class jie_xi_PLT {
   void add_lj(GeneralPath lj) {
      lj.setWindingRule(0);
      new Tu_yuan();
      Tu_yuan ty = Tu_yuan.chuang_jian(0, (BufferedImage)null);
      ty.lu_jing = new GeneralPath(lj);
      GraphicsPanel.ty_shuzu.add(ty);
      ((Tu_yuan)GraphicsPanel.ty_shuzu.get(GraphicsPanel.ty_shuzu.size() - 1)).xuan_zhong = true;
   }

   void jie_xi_PLT(File file) {
      BufferedReader reader = null;
      StringBuffer sbf = new StringBuffer();

      String plt;
      try {
         reader = new BufferedReader(new FileReader(file));

         while((plt = reader.readLine()) != null) {
            sbf.append(plt);
         }

         reader.close();
      } catch (IOException var33) {
         IOException e = var33;
         e.printStackTrace();
      } finally {
         if (reader != null) {
            try {
               reader.close();
            } catch (IOException var32) {
               IOException e1 = var32;
               e1.printStackTrace();
            }
         }

      }

      plt = sbf.toString();
      plt.replaceAll("\r|\n", "");
      String[] strArr = plt.split(";");
      GeneralPath lj = new GeneralPath();
      boolean yi = true;
      boolean jue_dui = true;
      double d_x = 0.0;
      double d_y = 0.0;
      double q_x = 0.0;
      double q_y = 0.0;

      int i;
      for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
      }

      for(i = 0; i < strArr.length; ++i) {
         String ml = strArr[i].toUpperCase();
         String zb = "";
         if (ml.length() > 1) {
            ml = ml.substring(0, 2);
         }

         String[] zb2;
         int a;
         if (ml.equals("PU")) {
            zb = strArr[i].substring(2, strArr[i].length());
            a = zb.indexOf(",");
            if (a != -1) {
               zb2 = zb.split(",");
            } else {
               zb2 = zb.split(" ");
            }

            if (zb2.length == 2) {
               double x = Double.valueOf(zb2[0]) / 40.0 / Engraver.state.scaleFactor;
               double y = Double.valueOf(zb2[1]) / 40.0 / Engraver.state.scaleFactor;
               y = 0.0 - y;
               if (jue_dui) {
                  d_x = x;
                  d_y = y;
               } else {
                  d_x += x;
                  d_y += y;
               }

               if (yi) {
                  lj.moveTo(d_x, d_y);
                  yi = false;
               } else {
                  if (q_x == d_x && q_y == d_y) {
                  }

                  this.add_lj(lj);
                  lj = new GeneralPath();
                  lj.moveTo(d_x, d_y);
               }

               q_x = d_x;
               q_y = d_y;
            }
         } else {
            double y;
            if (ml.equals("PD")) {
               zb = strArr[i].substring(2, strArr[i].length());
               a = zb.indexOf(",");
               if (a != -1) {
                  zb2 = zb.split(",");
               } else {
                  zb2 = zb.split(" ");
               }

               for(int j = 0; j < zb2.length; j += 2) {
                  y = Double.valueOf(zb2[j]) / 40.0 / Engraver.state.scaleFactor;
                  //double y = Double.valueOf(zb2[j + 1]) / 40.0 / Quan_ju.diao_ke_ji.fen_bian_lv;
                  y = 0.0 - y;
                  if (jue_dui) {
                     d_x = y;
                     d_y = y;
                  } else {
                     d_x += y;
                     d_y += y;
                  }

                  lj.lineTo(d_x, d_y);
               }
            } else {
               double x;
               if (ml.equals("PA")) {
                  zb = strArr[i].substring(2, strArr[i].length());
                  zb2 = zb.split(" ");
                  if (zb2.length == 2) {
                     x = Double.valueOf(zb2[0]) / 40.0 / Engraver.state.scaleFactor;
                     y = Double.valueOf(zb2[1]) / 40.0 / Engraver.state.scaleFactor;
                     y = 0.0 - y;
                     jue_dui = true;
                     if (jue_dui) {
                        d_x = x;
                        d_y = y;
                     } else {
                        d_x += x;
                        d_y += y;
                     }

                     lj.lineTo(d_x, d_y);
                  }
               } else if (ml.equals("PR")) {
                  zb = strArr[i].substring(2, strArr[i].length());
                  zb2 = zb.split(" ");
                  if (zb2.length == 2) {
                     x = Double.valueOf(zb2[0]) / 40.0 / Engraver.state.scaleFactor;
                     y = Double.valueOf(zb2[1]) / 40.0 / Engraver.state.scaleFactor;
                     y = 0.0 - y;
                     jue_dui = false;
                     if (jue_dui) {
                        d_x = x;
                        d_y = y;
                     } else {
                        d_x += x;
                        d_y += y;
                     }

                     lj.lineTo(d_x, d_y);
                  }
               }
            }
         }
      }

      this.add_lj(lj);
      Tu_yuan.zhong_xin(GraphicsPanel.ty_shuzu);
   }
}
