package com.engraver.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Tu_yuan implements Serializable {
   public static final int lei_xing_tupian = 1;
   public static final int lei_xing_lujing = 0;
   public int lei_xing = 0;
   public transient BufferedImage wei_tu = null;
   public transient BufferedImage wei_tu_yuan = null;
   public int[] wei_tu_ = null;
   public int[] wei_tu_yuan_ = null;
   public int wt_w = 0;
   public int wt_g = 0;
   public int wty_w = 0;
   public int wty_g = 0;
   public int gong_lv = 0;
   public int shen_du = 0;
   public double quan_beishu_ = 1.0;
   public int chuli_fs = 1;
   public boolean chuli_fan = false;
   public boolean chuli_jxx = false;
   public boolean chuli_jxy = false;
   public boolean tian_chong = false;
   public static int tian_chong_md = 5;
   public int yu_zhi = 50;
   public GeneralPath lu_jing = new GeneralPath();
   public boolean xuan_zhong = false;
   public AffineTransform Tx = new AffineTransform();
   public static Rectangle zui_zhong_wjx = new Rectangle();
   public static Rectangle wei_tu_wjx = new Rectangle();
   public static Rectangle shi_liang_wjx = new Rectangle();
   public static Rectangle shu_biao = new Rectangle();
   public static boolean tuo = false;
   public double jiao_du = 0.0;
   public static int dk_gonglv = 100;
   public static int dk_shendu = 10;
   public static int dk_gonglv_sl = 100;
   public static int dk_shendu_sl = 10;
   public static int dk_cishu = 1;
   public static int dk_ruo_guang = 10;
   public static int dk_jing_du = 2;
   public static List dian = null;
   public liu_shui_hao lsh = null;
   public boolean wen_zi = false;
   static int tu_kuan = 0;
   static int tu_gao = 0;
   static int da;

   public static List fu_zhi(List ty) {
      List fh = new ArrayList();

      for(int i = 0; i < ty.size(); ++i) {
         fh.add(fu_zhi((Tu_yuan)ty.get(i)));
      }

      return fh;
   }

   public static Tu_yuan fu_zhi(Tu_yuan ty) {
      Tu_yuan fh = chuang_jian(ty.lei_xing, ty.wei_tu_yuan);
      fh.Tx = new AffineTransform(ty.Tx);
      fh.xuan_zhong = ty.xuan_zhong;
      fh.lu_jing = new GeneralPath(ty.lu_jing);
      fh.chuli_fan = ty.chuli_fan;
      fh.chuli_fs = ty.chuli_fs;
      fh.chuli_jxx = ty.chuli_jxx;
      fh.chuli_jxy = ty.chuli_jxy;
      fh.tian_chong = ty.tian_chong;
      fh.yu_zhi = ty.yu_zhi;
      fh.wen_zi = ty.wen_zi;
      return fh;
   }

   void ping_yi(int x, int y) {
      AffineTransform Tx2 = AffineTransform.getTranslateInstance((double)x, (double)y);
      Tx2.concatenate(this.Tx);
      this.Tx = Tx2;
   }

   void xuan_zhuan(double jiao, double zx, double zy) {
      this.jiao_du += jiao;
      jiao = jiao * 3.14 / 180.0;
      AffineTransform Tx2 = AffineTransform.getRotateInstance(jiao, zx, zy);
      Tx2.concatenate(this.Tx);
      this.Tx = Tx2;
   }

   void suo_fang(double beishu_x, double beishu_y) {
      AffineTransform Tx2 = AffineTransform.getScaleInstance(beishu_x, beishu_y);
      Tx2.concatenate(this.Tx);
      this.Tx = Tx2;
   }

   static void jing_xiang_dxf() {
      Rectangle rect = qu_jv_xing(GraphicsPanel.ty_shuzu);

      for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(-rect.x, -rect.y);
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(1.0, -1.0);
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(rect.x, rect.y);
         }
      }

   }

   static void zhong_xin(List sz) {
      Rectangle rect = qu_jv_xing(GraphicsPanel.ty_shuzu);
      GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
      lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
      Rectangle rect2 = lu_jing2.getBounds();
      double x = (double)(rect2.x + rect2.width / 2);
      double y = (double)(rect2.y + rect2.height / 2);

      for(int i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi((int)(x - (double)(rect.x + rect.width / 2)), (int)(y - (double)(rect.y + rect.height / 2)));
         }
      }

   }

   public static Rectangle qu_jv_xing(Tu_yuan ty) {
      GeneralPath lu_jing2 = new GeneralPath(ty.lu_jing);
      lu_jing2.transform(ty.Tx);
      Rectangle rect = lu_jing2.getBounds();
      return rect;
   }

   public static Rectangle qu_jv_xing(List sz) {
      GeneralPath lu_jing2 = new GeneralPath();

      for(int i = 0; i < sz.size(); ++i) {
         if (((Tu_yuan)sz.get(i)).xuan_zhong) {
            GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
            lu_jing3.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
            lu_jing2.append(lu_jing3, true);
         }
      }

      Rectangle rect = lu_jing2.getBounds();
      return rect;
   }

   public static void kuang_xuan(List sz, Rectangle rect) {
      for(int i = 1; i < sz.size(); ++i) {
         GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
         lu_jing3.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
         if (rect.contains(lu_jing3.getBounds())) {
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = true;
         } else {
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong = false;
         }
      }

   }

   public static Tu_yuan chuang_jian_wen_zi(String ss, Font zt, boolean sl) {
      Tu_yuan fh = chuang_jian(0, (BufferedImage)null);
      fh.lu_jing = Wen_zi.getGeneralPath(ss, zt, false);
      fh.wen_zi = !sl;
      return fh;
   }

   public static Tu_yuan chuang_jian_wen_zi_shu(String ss, Font zt, int gao, boolean sl) {
      Tu_yuan fh = chuang_jian(0, (BufferedImage)null);
      fh.lu_jing = Wen_zi.getGeneralPath(ss, zt, true);
      fh.wen_zi = !sl;
      return fh;
   }

   public static Tu_yuan chuang_jian_wen_zi2(String ss, Font zt, boolean sl) {
      BufferedImage image = new BufferedImage(1, 1, 2);
      Graphics2D graphics = image.createGraphics();
      FontMetrics fm = graphics.getFontMetrics(zt);
      int g = fm.getHeight();
      g = (int)((double)g + (double)g * 0.3);
      String[] sz = ss.split("\n");
      int da = 0;

      for(int i = 0; i < sz.length; ++i) {
         int dq = fm.stringWidth(sz[i]);
         if (dq > da) {
            da = dq;
         }
      }

      da = (int)((double)da + (double)da * 0.3);
      graphics.dispose();
      BufferedImage bimg2 = new BufferedImage(da, sz.length * g, 2);
      Graphics2D g2d2 = (Graphics2D)bimg2.getGraphics();
      g2d2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      g2d2.setBackground(Color.WHITE);
      g2d2.setFont(zt);
      g2d2.clearRect(0, 0, bimg2.getWidth(), bimg2.getHeight());
      g2d2.setColor(Color.BLACK);

      for(int i = 0; i < sz.length; ++i) {
         g2d2.drawString(sz[i], 0, (int)((double)(g * (i + 1)) - (double)g * 0.3));
      }

      if (!sl) {
         Tu_yuan fh = chuang_jian(1, bimg2);
         return fh;
      } else {
         GeneralPath lj = to_ls(bimg2);
         Tu_yuan fh = chuang_jian(0, (BufferedImage)null);
         fh.lu_jing = new GeneralPath(lj);
         return fh;
      }
   }

   public static Tu_yuan chuang_jian_wen_zi_shu2(String ss, Font zt, int gao, boolean sl) {
      BufferedImage bimg = new BufferedImage(10, 10, 2);
      Graphics2D g2d = (Graphics2D)bimg.getGraphics();
      Font font = new Font(zt.getName(), zt.getStyle(), zt.getSize());
      g2d.setFont(font);
      int k = g2d.getFontMetrics().stringWidth("信");
      int g = g2d.getFontMetrics().getHeight();
      FontMetrics fm = g2d.getFontMetrics(font);
      LineMetrics line = fm.getLineMetrics(ss, g2d);
      BufferedImage bimg2 = new BufferedImage(k, ss.length() * (g + gao), 2);
      Graphics2D g2d2 = (Graphics2D)bimg2.getGraphics();
      g2d2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      g2d2.setBackground(Color.WHITE);
      g2d2.setFont(font);
      g2d2.clearRect(0, 0, bimg2.getWidth(), bimg2.getHeight());
      g2d2.setColor(Color.BLACK);
      new String();

      for(int i = 0; i < ss.length(); ++i) {
         String tempStr = ss.substring(i, i + 1);
         g2d2.drawString(tempStr, 0.0F, line.getAscent() + (float)((g + gao) * i));
      }

      if (!sl) {
         Tu_yuan fh = chuang_jian(1, bimg2);
         return fh;
      } else {
         GeneralPath lj = to_ls(bimg2);
         Tu_yuan fh = chuang_jian(0, (BufferedImage)null);
         fh.lu_jing = new GeneralPath(lj);
         return fh;
      }
   }

   public static Tu_yuan chuang_jian(int leixing, BufferedImage wt) {
      Tu_yuan ty = new Tu_yuan();
      GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
      lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
      Rectangle rect = lu_jing2.getBounds();
      ty.Tx.translate((double)rect.x, (double)rect.y);
      AffineTransform sf = AffineTransform.getScaleInstance(GraphicsPanel.quan_beishu, GraphicsPanel.quan_beishu);
      ty.Tx.concatenate(sf);
      switch (leixing) {
         case 0:
            ty.lei_xing = 0;
            ty.lu_jing.moveTo(0.0F, 0.0F);
            ty.lu_jing.lineTo(400.0F, 0.0F);
            ty.lu_jing.lineTo(400.0F, 400.0F);
            ty.lu_jing.lineTo(0.0F, 400.0F);
            ty.lu_jing.closePath();
            break;
         case 1:
            double bi = 0.0;
            ty.wei_tu_yuan = wt;
            ty.wei_tu_yuan = Tu_pian.qu_you_xiao(ty.wei_tu_yuan);
            ty.lei_xing = 1;
            ty.chuli_fs = 1;
            ty.wei_tu = Tu_pian.hui_du(ty.wei_tu_yuan);
            ty.wei_tu = Tu_pian.heibai(ty.wei_tu, 128);
            ty.lu_jing.moveTo(0.0F, 0.0F);
            ty.lu_jing.lineTo((float)ty.wei_tu.getWidth(), 0.0F);
            ty.lu_jing.lineTo((float)ty.wei_tu.getWidth(), (float)ty.wei_tu.getHeight());
            ty.lu_jing.lineTo(0.0F, (float)ty.wei_tu.getHeight());
            ty.lu_jing.closePath();
            break;
         case 2:
            ty.lei_xing = 0;
            Ellipse2D.Float d = new Ellipse2D.Float(1.0F, 1.0F, 400.0F, 400.0F);
            ty.lu_jing.append(d, false);
            break;
         case 3:
            ty.lei_xing = 0;
            ty.lu_jing.moveTo(197.0F, 102.0F);
            ty.lu_jing.lineTo(212.0F, 69.0F);
            ty.lu_jing.lineTo(224.0F, 48.0F);
            ty.lu_jing.lineTo(242.0F, 27.0F);
            ty.lu_jing.lineTo(266.0F, 10.0F);
            ty.lu_jing.lineTo(304.0F, 0.0F);
            ty.lu_jing.lineTo(343.0F, 10.0F);
            ty.lu_jing.lineTo(363.0F, 27.0F);
            ty.lu_jing.lineTo(378.0F, 48.0F);
            ty.lu_jing.lineTo(387.0F, 69.0F);
            ty.lu_jing.lineTo(393.0F, 102.0F);
            ty.lu_jing.lineTo(390.0F, 150.0F);
            ty.lu_jing.lineTo(372.0F, 208.0F);
            ty.lu_jing.lineTo(343.0F, 264.0F);
            ty.lu_jing.lineTo(295.0F, 322.0F);
            ty.lu_jing.lineTo(197.0F, 394.0F);
            ty.lu_jing.lineTo(98.0F, 322.0F);
            ty.lu_jing.lineTo(50.0F, 264.0F);
            ty.lu_jing.lineTo(20.0F, 208.0F);
            ty.lu_jing.lineTo(3.0F, 150.0F);
            ty.lu_jing.lineTo(0.0F, 102.0F);
            ty.lu_jing.lineTo(6.0F, 69.0F);
            ty.lu_jing.lineTo(15.0F, 48.0F);
            ty.lu_jing.lineTo(29.0F, 27.0F);
            ty.lu_jing.lineTo(50.0F, 10.0F);
            ty.lu_jing.lineTo(88.0F, 0.0F);
            ty.lu_jing.lineTo(128.0F, 10.0F);
            ty.lu_jing.lineTo(151.0F, 27.0F);
            ty.lu_jing.lineTo(170.0F, 48.0F);
            ty.lu_jing.lineTo(183.0F, 69.0F);
            ty.lu_jing.closePath();
            break;
         case 4:
            ty.lei_xing = 0;
            ty.lu_jing.moveTo(121.0F, 0.0F);
            ty.lu_jing.lineTo(149.0F, 93.0F);
            ty.lu_jing.lineTo(241.0F, 94.0F);
            ty.lu_jing.lineTo(169.0F, 149.0F);
            ty.lu_jing.lineTo(196.0F, 241.0F);
            ty.lu_jing.lineTo(122.0F, 188.0F);
            ty.lu_jing.lineTo(46.0F, 241.0F);
            ty.lu_jing.lineTo(72.0F, 149.0F);
            ty.lu_jing.lineTo(0.0F, 94.0F);
            ty.lu_jing.lineTo(92.0F, 93.0F);
            ty.lu_jing.closePath();
      }

      return ty;
   }

   public BufferedImage qu_tu() {
      GeneralPath lu_jing2 = new GeneralPath(this.lu_jing);
      lu_jing2.transform(this.Tx);
      Rectangle jx = lu_jing2.getBounds();
      System.out.println(jx);
      System.out.println(this.Tx);
      AffineTransform tx2 = new AffineTransform(this.Tx.getScaleX(), this.Tx.getShearY(), this.Tx.getShearX(), this.Tx.getScaleY(), this.Tx.getTranslateX() - (double)jx.x, this.Tx.getTranslateY() - (double)jx.y);
      System.out.println(tx2);
      BufferedImage fh = new BufferedImage(jx.width, jx.height, 2);
      Graphics2D g2D = fh.createGraphics();
      g2D.setBackground(Color.WHITE);
      g2D.clearRect(0, 0, jx.width, jx.height);
      g2D.drawImage(this.wei_tu_yuan, tx2, (ImageObserver)null);
      if (this.lei_xing == 0) {
         Rectangle rect;
         int i;
         if (this.chuli_jxx) {
            this.chuli_jxx = false;
            rect = qu_jv_xing(GraphicsPanel.ty_shuzu);

            for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
               if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(-rect.x - rect.width / 2, 0);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(-1.0, 1.0);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(rect.x + rect.width / 2, 0);
               }
            }
         }

         if (this.chuli_jxy) {
            this.chuli_jxy = false;
            rect = qu_jv_xing(GraphicsPanel.ty_shuzu);

            for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
               if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(0, -rect.y - rect.height / 2);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(1.0, -1.0);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(0, rect.y + rect.height / 2);
               }
            }
         }
      } else {
         switch (this.chuli_fs) {
            case 1:
               fh = Tu_pian.hui_du(fh);
               fh = Tu_pian.heibai(fh, (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_fan) {
                  fh = Tu_pian.fanse(fh);
               }

               if (this.chuli_jxx) {
                  fh = Tu_pian.jing_xiang_x(fh);
               }

               if (this.chuli_jxy) {
                  fh = Tu_pian.jing_xiang_y(fh);
               }
               break;
            case 2:
               fh = Tu_pian.hui_du(fh);
               fh = Tu_pian.convertGreyImgByFloyd(fh, (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_fan) {
                  fh = Tu_pian.fanse(fh);
               }

               if (this.chuli_jxx) {
                  fh = Tu_pian.jing_xiang_x(fh);
               }

               if (this.chuli_jxy) {
                  fh = Tu_pian.jing_xiang_y(fh);
               }
               break;
            case 3:
               fh = Tu_pian.hui_du(fh);
               fh = Tu_pian.heibai(fh, 128);
               fh = Tu_pian.qu_lunkuo(fh, (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_jxx) {
                  fh = Tu_pian.jing_xiang_x(fh);
               }

               if (this.chuli_jxy) {
                  fh = Tu_pian.jing_xiang_y(fh);
               }
               break;
            case 4:
               fh = Tu_pian.su_miao(fh);
               fh = Tu_pian.heibai(fh, 50 + (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_fan) {
                  fh = Tu_pian.fanse(fh);
               }

               if (this.chuli_jxx) {
                  fh = Tu_pian.jing_xiang_x(fh);
               }

               if (this.chuli_jxy) {
                  fh = Tu_pian.jing_xiang_y(fh);
               }
         }
      }

      fh = fh.getSubimage(3, 3, fh.getWidth() - 6, fh.getHeight() - 6);
      return fh;
   }

   public void chu_li() {
      if (this.lei_xing == 0) {
         Rectangle rect;
         int i;
         if (this.chuli_jxx) {
            this.chuli_jxx = false;
            rect = qu_jv_xing(GraphicsPanel.ty_shuzu);

            for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
               if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(-rect.x - rect.width / 2, 0);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(-1.0, 1.0);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(rect.x + rect.width / 2, 0);
               }
            }
         }

         if (this.chuli_jxy) {
            this.chuli_jxy = false;
            rect = qu_jv_xing(GraphicsPanel.ty_shuzu);

            for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
               if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).xuan_zhong) {
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(0, -rect.y - rect.height / 2);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).suo_fang(1.0, -1.0);
                  ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).ping_yi(0, rect.y + rect.height / 2);
               }
            }
         }
      } else {
         switch (this.chuli_fs) {
            case 1:
               this.wei_tu = Tu_pian.hui_du(this.wei_tu_yuan);
               this.wei_tu = Tu_pian.heibai(this.wei_tu, (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_fan) {
                  this.wei_tu = Tu_pian.fanse(this.wei_tu);
               }

               if (this.chuli_jxx) {
                  this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
               }

               if (this.chuli_jxy) {
                  this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
               }
               break;
            case 2:
               this.wei_tu = Tu_pian.hui_du(this.wei_tu_yuan);
               this.wei_tu = Tu_pian.convertGreyImgByFloyd(this.wei_tu, (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_fan) {
                  this.wei_tu = Tu_pian.fanse(this.wei_tu);
               }

               if (this.chuli_jxx) {
                  this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
               }

               if (this.chuli_jxy) {
                  this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
               }
               break;
            case 3:
               this.wei_tu = Tu_pian.qu_lunkuo(this.wei_tu_yuan, (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_jxx) {
                  this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
               }

               if (this.chuli_jxy) {
                  this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
               }
               break;
            case 4:
               this.wei_tu = Tu_pian.su_miao2(this.wei_tu_yuan);
               this.wei_tu = Tu_pian.heibai(this.wei_tu, 50 + (int)((double)this.yu_zhi * 2.56));
               if (this.chuli_fan) {
                  this.wei_tu = Tu_pian.fanse(this.wei_tu);
               }

               if (this.chuli_jxx) {
                  this.wei_tu = Tu_pian.jing_xiang_x(this.wei_tu);
               }

               if (this.chuli_jxy) {
                  this.wei_tu = Tu_pian.jing_xiang_y(this.wei_tu);
               }
         }
      }

   }

   public static void qu_jvxing(List sz) {
      int z = 0;
      int d = 0;
      int y = 0;
      int x = 0;

      for(int i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
         lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
         Rectangle jx = lu_jing2.getBounds();
         if (i == 1) {
            z = jx.x;
            d = jx.y;
            y = jx.x + jx.width;
            x = jx.y + jx.height;
         } else {
            if (jx.x < z) {
               z = jx.x;
            }

            if (jx.y < d) {
               d = jx.y;
            }

            if (jx.x + jx.width > y) {
               y = jx.x + jx.width;
            }

            if (jx.y + jx.height > x) {
               x = jx.y + jx.height;
            }
         }
      }

      GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
      lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
      Rectangle jx2 = lu_jing2.getBounds();
      jx2.createIntersection(new Rectangle(z, d, y - z, x - d));
      zui_zhong_wjx = (new Rectangle(z, d, y - z, x - d)).createIntersection(jx2).getBounds();
      if (zui_zhong_wjx.width > 0 && zui_zhong_wjx.height > 0) {
         zui_zhong_wjx = (new Rectangle(z, d, y - z, x - d)).createIntersection(jx2).getBounds();
      } else {
         zui_zhong_wjx = new Rectangle();
      }

      GeneralPath lu_jing3;
      Rectangle jx;
      int i;
      for(i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 0) {
            lu_jing3 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
            lu_jing3.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
            jx = lu_jing3.getBounds();
            if (i == 1) {
               z = jx.x;
               d = jx.y;
               y = jx.x + jx.width;
               x = jx.y + jx.height;
            } else {
               if (jx.x < z) {
                  z = jx.x;
               }

               if (jx.y < d) {
                  d = jx.y;
               }

               if (jx.x + jx.width > y) {
                  y = jx.x + jx.width;
               }

               if (jx.y + jx.height > x) {
                  x = jx.y + jx.height;
               }
            }
         }
      }

      shi_liang_wjx = new Rectangle(z, d, y - z, x - d);

      for(i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1) {
            lu_jing3 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
            lu_jing3.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
            jx = lu_jing3.getBounds();
            if (i == 1) {
               z = jx.x;
               d = jx.y;
               y = jx.x + jx.width;
               x = jx.y + jx.height;
            } else {
               if (jx.x < z) {
                  z = jx.x;
               }

               if (jx.y < d) {
                  d = jx.y;
               }

               if (jx.x + jx.width > y) {
                  y = jx.x + jx.width;
               }

               if (jx.y + jx.height > x) {
                  x = jx.y + jx.height;
               }
            }
         }
      }

      wei_tu_wjx = new Rectangle(z, d, y - z, x - d);
   }

   public static void hui_fu() {
      GeneralPath lu_jing3 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
      lu_jing3.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
      Rectangle rect3 = lu_jing3.getBounds();
      GraphicsPanel.quan_x2 = rect3.x;
      GraphicsPanel.quan_y2 = rect3.y;
      GraphicsPanel.quan_beishu2 = GraphicsPanel.quan_beishu;

      for(int j = 0; j < 2; ++j) {
         GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).lu_jing);
         lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(0)).Tx);
         Rectangle rect = lu_jing2.getBounds();
         AffineTransform sf = AffineTransform.getTranslateInstance((double)(0 - rect.x), (double)(0 - rect.y));
         GraphicsPanel.quan_x = 0;
         GraphicsPanel.quan_y = 0;

         int i;
         AffineTransform fb;
         for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
            fb = new AffineTransform(sf);
            fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
         }

         sf = AffineTransform.getScaleInstance(1.0 / GraphicsPanel.quan_beishu, 1.0 / GraphicsPanel.quan_beishu);
         GraphicsPanel.quan_beishu = 1.0;

         for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
            fb = new AffineTransform(sf);
            fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
            ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
         }
      }

   }

   public static void hui_fu_xian_chang() {
      GraphicsPanel.quan_x = GraphicsPanel.quan_x2;
      GraphicsPanel.quan_y = GraphicsPanel.quan_y2;
      GraphicsPanel.quan_beishu = GraphicsPanel.quan_beishu2;
      AffineTransform sf = AffineTransform.getScaleInstance(GraphicsPanel.quan_beishu, GraphicsPanel.quan_beishu);

      int i;
      AffineTransform fb;
      for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         fb = new AffineTransform(sf);
         fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
         ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
      }

      sf = AffineTransform.getTranslateInstance((double)GraphicsPanel.quan_x, (double)GraphicsPanel.quan_y);

      for(i = 0; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         fb = new AffineTransform(sf);
         fb.concatenate(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
         ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx = fb;
      }

   }

   public static void shu_chu(BufferedImage bb, String ss) {
      File outputfile = new File(ss);

      try {
         ImageIO.write(bb, "png", outputfile);
      } catch (IOException var4) {
         IOException ex = var4;
         Logger.getLogger(Tu_yuan.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   public static BufferedImage qu_tu(List sz) {
      BufferedImage fh = new BufferedImage((int)((double)Engraver.state.width / Engraver.state.scaleFactor) - 2, (int)((double)Engraver.state.height / Engraver.state.scaleFactor) - 2, 2);
      Graphics2D g2D = fh.createGraphics();
      g2D.setBackground(Color.WHITE);
      g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
      boolean you = false;

      for(int i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1 && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_fs != 3 || ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wen_zi) {
            GeneralPath lu_jing2;
            if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wen_zi) {
               lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
               lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
               g2D.setPaint(Color.BLACK);
               g2D.fill(lu_jing2);
            } else {
               lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
               lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
               Rectangle jx = lu_jing2.getBounds();
               g2D.drawImage(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).qu_tu(), jx.x, jx.y, (ImageObserver)null);
            }

            you = true;
         }
      }

      if (you) {
         BufferedImage fh2 = new BufferedImage((int)((double)Engraver.state.width / Engraver.state.scaleFactor), (int)((double)Engraver.state.height / Engraver.state.scaleFactor), 2);
         Graphics2D g2D2 = fh2.createGraphics();
         g2D2.setBackground(Color.WHITE);
         int w = fh2.getWidth();
         int h = fh2.getHeight();
         g2D2.clearRect(0, 0, w, h);
         g2D2.drawImage(fh, 1, 1, (ImageObserver)null);
         qu_jvxing(GraphicsPanel.ty_shuzu);
         int xx = 0;
         int yy = 0;
         int ww = 0;
         int hh = 0;
         Rectangle jx = new Rectangle(0, 0, w, h);
         new Rectangle();
         if (zui_zhong_wjx.x == 0 && zui_zhong_wjx.y == 0 && zui_zhong_wjx.width == 0 && zui_zhong_wjx.height == 0) {
            return null;
         } else if (!jx.contains(zui_zhong_wjx.x, zui_zhong_wjx.y) && !jx.contains(zui_zhong_wjx.x + zui_zhong_wjx.width, zui_zhong_wjx.y + zui_zhong_wjx.height)) {
            return null;
         } else {
            Rectangle jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
            if (jx2.x + jx2.width + 5 >= fh2.getWidth()) {
               w = jx2.width;
            } else {
               w = jx2.width + 5;
            }

            if (jx2.y + jx2.height + 5 >= fh2.getHeight()) {
               h = jx2.height;
            } else {
               h = jx2.height + 5;
            }

            return fh2.getSubimage(jx2.x, jx2.y, w, h);
         }
      } else {
         return null;
      }
   }

   public static BufferedImage qu_tu_sl(List sz) {
      BufferedImage fh = new BufferedImage((int)((double)Engraver.state.width / Engraver.state.scaleFactor), (int)((double)Engraver.state.height / Engraver.state.scaleFactor), 2);
      Graphics2D g2D = fh.createGraphics();
      g2D.setBackground(Color.WHITE);
      g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
      boolean you = false;

      for(int i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         GeneralPath lu_jing4 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
         lu_jing4.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 0 && !((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).wen_zi) {
            g2D.setColor(Color.BLACK);
            g2D.draw(lu_jing4);
            you = true;
         }

         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 1 && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).chuli_fs == 3) {
            GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
            lu_jing2.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
            Rectangle jx = lu_jing2.getBounds();
            g2D.setColor(Color.BLACK);
            g2D.drawImage(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).qu_tu(), jx.x, jx.y, (ImageObserver)null);
            you = true;
         }
      }

      if (you) {
         try {
            BufferedImage fh2 = new BufferedImage((int)((double)Engraver.state.width / Engraver.state.scaleFactor) + 4, (int)((double)Engraver.state.height / Engraver.state.scaleFactor) + 4, 2);
            Graphics2D g2D2 = fh2.createGraphics();
            g2D2.setBackground(Color.WHITE);
            int w = fh2.getWidth();
            int h = fh2.getHeight();
            g2D2.clearRect(0, 0, w, h);
            g2D2.drawImage(fh, 2, 2, (ImageObserver)null);
            qu_jvxing(GraphicsPanel.ty_shuzu);
            int xx = 0;
            int yy = 0;
            int ww = 0;
            int hh = 0;
            Rectangle jx = new Rectangle(0, 0, w, h);
            new Rectangle();
            if (zui_zhong_wjx.x == 0 && zui_zhong_wjx.y == 0 && zui_zhong_wjx.width == 0 && zui_zhong_wjx.height == 0) {
               return null;
            } else if (!jx.contains(zui_zhong_wjx.x, zui_zhong_wjx.y) && !jx.contains(zui_zhong_wjx.x + zui_zhong_wjx.width, zui_zhong_wjx.y + zui_zhong_wjx.height)) {
               return null;
            } else {
               Rectangle jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
               if (jx2.x + jx2.width + 5 >= fh2.getWidth()) {
                  w = jx2.width;
               } else {
                  w = jx2.width + 5;
               }

               if (jx2.y + jx2.height + 5 >= fh2.getHeight()) {
                  h = jx2.height;
               } else {
                  h = jx2.height + 5;
               }

               try {
                  return fh2.getSubimage(jx2.x, jx2.y, w, h);
               } catch (Exception var15) {
                  Exception ex = var15;
                  JOptionPane.showMessageDialog((Component)null, ex);
                  return null;
               }
            }
         } catch (Exception var16) {
            JOptionPane.showMessageDialog((Component)null, var16);
            return null;
         }
      } else {
         return null;
      }
   }

   public static BufferedImage qu_tu_sl_tc(List sz) {
      BufferedImage fh = new BufferedImage((int)((double)Engraver.state.width / Engraver.state.scaleFactor), (int)((double)Engraver.state.height / Engraver.state.scaleFactor), 2);
      Graphics2D g2D = fh.createGraphics();
      g2D.setBackground(Color.WHITE);
      g2D.clearRect(0, 0, fh.getWidth(), fh.getHeight());
      boolean you = false;

      for(int i = 1; i < GraphicsPanel.ty_shuzu.size(); ++i) {
         GeneralPath lu_jing4 = new GeneralPath(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lu_jing);
         lu_jing4.transform(((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).Tx);
         if (((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).lei_xing == 0 && ((Tu_yuan)GraphicsPanel.ty_shuzu.get(i)).tian_chong) {
            g2D.setColor(Color.BLACK);
            lu_jing4.setWindingRule(0);
            g2D.fill(lu_jing4);
            you = true;
         }
      }

      if (you) {
         BufferedImage fh2 = new BufferedImage((int)((double)Engraver.state.width / Engraver.state.scaleFactor) + 4, (int)((double)Engraver.state.height / Engraver.state.scaleFactor) + 4, 2);
         Graphics2D g2D2 = fh2.createGraphics();
         g2D2.setBackground(Color.WHITE);
         int w = fh2.getWidth();
         int h = fh2.getHeight();
         g2D2.clearRect(0, 0, w, h);
         g2D2.drawImage(fh, 2, 2, (ImageObserver)null);
         qu_jvxing(GraphicsPanel.ty_shuzu);
         int xx = 0;
         int yy = 0;
         int ww = 0;
         int hh = 0;
         Rectangle jx = new Rectangle(0, 0, w, h);
         new Rectangle();
         if (zui_zhong_wjx.x == 0 && zui_zhong_wjx.y == 0 && zui_zhong_wjx.width == 0 && zui_zhong_wjx.height == 0) {
            return null;
         } else if (!jx.contains(zui_zhong_wjx.x, zui_zhong_wjx.y) && !jx.contains(zui_zhong_wjx.x + zui_zhong_wjx.width, zui_zhong_wjx.y + zui_zhong_wjx.height)) {
            return null;
         } else {
            Rectangle jx2 = jx.createIntersection(zui_zhong_wjx).getBounds();
            if (jx2.x + jx2.width + 5 >= fh2.getWidth()) {
               w = jx2.width;
            } else {
               w = jx2.width + 5;
            }

            if (jx2.y + jx2.height + 5 >= fh2.getHeight()) {
               h = jx2.height;
            } else {
               h = jx2.height + 5;
            }

            return fh2.getSubimage(jx2.x, jx2.y, w, h);
         }
      } else {
         return null;
      }
   }

   static Dian qu_xiao(Dian d1, Dian d2, Dian d3) {
      if (d1.x == 242 && d1.y == 85) {
         d1.x = 242;
      }

      int lxiao = Math.abs(d1.x - d2.x) > Math.abs(d1.y - d2.y) ? Math.abs(d1.y - d2.y) : Math.abs(d1.x - d2.x);
      int lxiao2 = Math.abs(d1.x - d3.x) > Math.abs(d1.y - d3.y) ? Math.abs(d1.y - d3.y) : Math.abs(d1.x - d3.x);
      return lxiao < lxiao2 ? d2 : d3;
   }

   static Dian qu_jindian(Dian d, BufferedImage bb) {
      Dian fh = new Dian(50000, 0);
      List zd = new ArrayList();

      for(int c = 1; c < da; ++c) {
         int ls = d.y - c;

         int b;
         for(b = d.x - c; b < d.x + c; ++b) {
            if (b > 0 && b < tu_kuan && ls > 0 && ls < tu_gao && (new Color(bb.getRGB(b, ls))).getRed() == 0) {
               zd.add(new Dian(b, ls));
            }
         }

         ls = d.x + c;

         for(b = d.y - c; b < d.y + c; ++b) {
            if (b > 0 && b < tu_gao && ls > 0 && ls < tu_kuan && (new Color(bb.getRGB(ls, b))).getRed() == 0) {
               zd.add(new Dian(ls, b));
            }
         }

         ls = d.y + c;

         for(b = d.x + c; b > d.x - c; --b) {
            if (b > 0 && b < tu_kuan && ls > 0 && ls < tu_gao && (new Color(bb.getRGB(b, ls))).getRed() == 0) {
               zd.add(new Dian(b, ls));
            }
         }

         ls = d.x - c;

         for(b = d.y + c; b > d.y - c; --b) {
            if (b > 0 && b < tu_gao && ls > 0 && ls < tu_kuan && (new Color(bb.getRGB(ls, b))).getRed() == 0) {
               zd.add(new Dian(ls, b));
            }
         }

         Dian fh2 = new Dian(0, 0);
         if (zd.size() > 0) {
            for(int i = 0; i < zd.size(); ++i) {
               if (i == 0) {
                  fh2 = (Dian)zd.get(i);
               } else {
                  fh2 = qu_xiao(d, fh2, (Dian)zd.get(i));
               }
            }

            return fh2;
         }
      }

      return fh;
   }

   static List pai_xu(BufferedImage tu_diaoke2) {
      BufferedImage bb = tu_diaoke2.getSubimage(0, 0, tu_diaoke2.getWidth(), tu_diaoke2.getHeight());
      List fh = new ArrayList();
      tu_kuan = bb.getWidth();
      tu_gao = bb.getHeight();
      da = bb.getWidth() > bb.getHeight() ? bb.getWidth() : bb.getHeight();

      for(int i = 0; i < dian.size(); ++i) {
         if (i == 0) {
            fh.add((Dian)dian.get(i));
            fh.add(new Dian(30000, 30000));
            bb.setRGB(((Dian)dian.get(i)).x, ((Dian)dian.get(i)).y, Color.WHITE.getRGB());
         } else {
            new Dian(0, 0);
            Dian d2;
            if (((Dian)fh.get(fh.size() - 1)).x != 30000 && ((Dian)fh.get(fh.size() - 1)).x != 50000) {
               d2 = (Dian)fh.get(fh.size() - 1);
            } else {
               d2 = (Dian)fh.get(fh.size() - 2);
            }

            Dian d = qu_jindian(d2, bb);
            if (d.x == 50000) {
               break;
            }

            if (!xiang_lian(d, d2)) {
               fh.add(new Dian(50000, 50000));
               fh.add(d);
               fh.add(new Dian(30000, 30000));
            } else {
               fh.add(d);
            }

            bb.setRGB(d.x, d.y, Color.WHITE.getRGB());
         }
      }

      fh.add(new Dian(50000, 50000));
      return fh;
   }

   static List pai_xu2(BufferedImage tu_diaoke2) {
      BufferedImage bb = tu_diaoke2.getSubimage(0, 0, tu_diaoke2.getWidth(), tu_diaoke2.getHeight());
      List fh = new ArrayList();
      tu_kuan = bb.getWidth();
      tu_gao = bb.getHeight();
      da = bb.getWidth() > bb.getHeight() ? bb.getWidth() : bb.getHeight();

      for(int i = 0; i < dian.size(); ++i) {
         if (i == 0) {
            fh.add((Dian)dian.get(i));
            bb.setRGB(((Dian)dian.get(i)).x, ((Dian)dian.get(i)).y, Color.WHITE.getRGB());
         } else {
            if (((Dian)fh.get(fh.size() - 1)).x == 242 && ((Dian)fh.get(fh.size() - 1)).y == 87) {
               ((Dian)fh.get(fh.size() - 1)).x = 242;
            }

            Dian d = qu_jindian((Dian)fh.get(fh.size() - 1), bb);
            if (d.x == 50000) {
               break;
            }

            fh.add(d);
            bb.setRGB(d.x, d.y, Color.WHITE.getRGB());
         }
      }

      return fh;
   }

   static List qu_tian_chong(BufferedImage img, int jian_ge) {
      List fh = new ArrayList();
      int width = img.getWidth();
      int height = img.getHeight();
      int[] pixels = new int[width * height];
      img.getRGB(0, 0, width, height, pixels, 0, width);

      for(int i = 1; i < height; i += jian_ge) {
         for(int j = 1; j < width; ++j) {
            if ((new Color(pixels[width * i + j])).getRed() == 0) {
               fh.add(new Dian(j, i));
            }
         }
      }

      return fh;
   }

   static List qudian(BufferedImage img) {
      List fh = new ArrayList();
      int width = img.getWidth();
      int height = img.getHeight();
      int[] pixels = new int[width * height];
      img.getRGB(0, 0, width, height, pixels, 0, width);

      for(int i = 1; i < height; ++i) {
         for(int j = 1; j < width; ++j) {
            if ((new Color(pixels[width * i + j])).getRed() == 0) {
               fh.add(new Dian(j, i));
            }
         }
      }

      return fh;
   }

   static int qu_fang_xiang(Dian d1, Dian d2) {
      if (d2.x - d1.x == 1 && d2.y - d1.y == 1) {
         return 4;
      } else if (d2.x - d1.x == -1 && d2.y - d1.y == -1) {
         return 8;
      } else if (d2.x - d1.x == 1 && d2.y - d1.y == -1) {
         return 2;
      } else if (d2.x - d1.x == -1 && d2.y - d1.y == 1) {
         return 6;
      } else if (d2.x - d1.x == -1 && d2.y - d1.y == 0) {
         return 7;
      } else if (d2.x - d1.x == 1 && d2.y - d1.y == 0) {
         return 3;
      } else if (d2.x - d1.x == 0 && d2.y - d1.y == -1) {
         return 1;
      } else if (d2.x - d1.x == 0 && d2.y - d1.y == 1) {
         return 5;
      } else {
         return Math.abs(d2.x - d1.x) > 1 && Math.abs(d2.y - d1.y) > 1 ? 9 : 9;
      }
   }

   public static List you_hua(List dd) {
      List fh = new ArrayList();
      int fx = 0;

      for(int i = 0; i < dd.size(); ++i) {
         if (i == 0) {
            fh.add((Dian)dd.get(i));
         } else if (i == 1) {
            fh.add((Dian)dd.get(i));
            fx = qu_fang_xiang((Dian)fh.get(fh.size() - 2), (Dian)fh.get(fh.size() - 2));
         } else {
            int fx2 = qu_fang_xiang((Dian)fh.get(fh.size() - 1), (Dian)dd.get(i));
            if (fx == fx2 && fx != 9) {
               fh.remove(fh.size() - 1);
               fh.add((Dian)dd.get(i));
            } else {
               fh.add((Dian)dd.get(i));
               fx = fx2;
            }
         }
      }

      return fh;
   }

   static boolean xiang_lian(Dian a, Dian b) {
      return Math.abs(a.x - b.x) <= 2 && Math.abs(a.y - b.y) <= 2;
   }

   public static double getDistance(int x1, int x2, int y1, int y2) {
      return Math.sqrt((double)((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
   }

   public static double qu_gao(double a, double b, double c) {
      double p = (a + b + c) / 2.0;
      double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
      return 2.0 * s / b;
   }

   public static double qu_gao_da(List dd, int d1, int d2) {
      double da = 0.0;

      for(int i = 0; i < d2 - d1 - 1; ++i) {
         if (((Dian)dd.get(i + d1)).x != 30000 && ((Dian)dd.get(i + d1)).x != 50000) {
            double a = getDistance(((Dian)dd.get(d1)).x, ((Dian)dd.get(i + d1)).x, ((Dian)dd.get(d1)).y, ((Dian)dd.get(i + d1)).y);
            double b = getDistance(((Dian)dd.get(d1)).x, ((Dian)dd.get(d2)).x, ((Dian)dd.get(d1)).y, ((Dian)dd.get(d2)).y);
            double c = getDistance(((Dian)dd.get(i + d1)).x, ((Dian)dd.get(d2)).x, ((Dian)dd.get(i + d1)).y, ((Dian)dd.get(d2)).y);
            double d = qu_gao(a, b, c);
            if (d > da) {
               da = d;
            }
         }
      }

      return da;
   }

   public static List you_hua2(List dd) {
      List fh = new ArrayList();
      int yi = 0;

      for(int i = 0; i < dd.size(); ++i) {
         if (((Dian)dd.get(i)).x == 30000) {
            fh.add((Dian)dd.get(i - 1));
            fh.add((Dian)dd.get(i));
            yi = i - 1;
         } else if (((Dian)dd.get(i)).x == 50000) {
            fh.add((Dian)dd.get(i - 1));
            fh.add((Dian)dd.get(i));
            yi = i + 1;
         } else if (i != 0) {
            int ge_shu = i - yi;
            if (ge_shu > 1) {
               double da = qu_gao_da(dd, yi, i);
               if (da > 0.7) {
                  fh.add((Dian)dd.get(i - 1));
                  yi = i - 1;
               }
            }
         }
      }

      return fh;
   }

   public static GeneralPath to_ls(BufferedImage bb) {
      List fh = null;
      GeneralPath lj = new GeneralPath();
      bb = Tu_pian.qu_lunkuo(bb, 128);
      if (bb != null) {
         dian = qudian(bb);
         fh = pai_xu(bb);
         fh = you_hua2(fh);
      }

      new Dian(0, 0);
      Dian qi = new Dian(0, 0);
      boolean kai_jg = false;

      for(int i = 0; i < fh.size(); ++i) {
         Dian ls = (Dian)fh.get(i);
         if (i == 0) {
            lj.moveTo((float)ls.x, (float)ls.y);
            new Dian(ls.x, ls.y);
         } else if (((Dian)fh.get(i)).x == 30000) {
            kai_jg = true;
            qi = new Dian(((Dian)fh.get(i - 1)).x, ((Dian)fh.get(i - 1)).y);
         } else if (((Dian)fh.get(i)).x == 50000) {
            kai_jg = false;
            if (xiang_lian(qi, (Dian)fh.get(i - 1))) {
               lj.closePath();
            }
         } else if (kai_jg) {
            lj.lineTo((float)ls.x, (float)ls.y);
            new Dian(ls.x, ls.y);
         } else {
            lj.moveTo((float)ls.x, (float)ls.y);
            new Dian(ls.x, ls.y);
         }
      }

      if (xiang_lian(qi, (Dian)fh.get(fh.size() - 1))) {
         lj.closePath();
      }

      return lj;
   }

   public static List qu_dian(List sz) {
      List fh = null;
      BufferedImage bb = qu_tu_sl(GraphicsPanel.ty_shuzu);
      if (bb != null) {
         dian = qudian(bb);
         fh = pai_xu2(bb);
      }

      BufferedImage bb2 = qu_tu_sl_tc(GraphicsPanel.ty_shuzu);
      if (bb2 != null) {
         fh.addAll(0, qu_tian_chong(bb2, 11 - tian_chong_md));
      }

      return fh;
   }
}
