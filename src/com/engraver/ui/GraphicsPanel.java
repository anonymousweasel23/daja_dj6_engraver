package com.engraver.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicsPanel extends JPanel {
   public static int quan_x = 0;
   public static int quan_y = 0;
   public static double quan_beishu = 1.0;
   public static int quan_x2 = 0;
   public static int quan_y2 = 0;
   public static double quan_beishu2 = 1.0;
   public static List ty_shuzu = new ArrayList();
   public static int dang_qian2 = -1;
   public AffineTransform Tx = new AffineTransform();
   public static boolean suo = true;
   public JPanel win;
   public JPanel jp;
   public JPanel jp2;
   public JLabel wb;
   public JTextField wb1;
   public JTextField wb2;
   public JTextField wb3;
   public JTextField wb4;
   public ApplicationFrame win2 = null;
   public int x = 0;
   public int y = 0;
   public int ww = 0;
   public int hh = 0;
   public boolean LeiXing_sl = false;
   public boolean LeiXing_tp = false;

   public void paint(Graphics g) {
      super.paint(g);
      Graphics2D g2D = (Graphics2D)g;
      boolean xuan_zhong = false;

      for(int i = 0; i < ty_shuzu.size(); ++i) {
         GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)ty_shuzu.get(i)).lu_jing);
         lu_jing2.transform(((Tu_yuan)ty_shuzu.get(i)).Tx);
         Rectangle rect = lu_jing2.getBounds();
         double mm = (double)Engraver.state.width / 10.0 / (double)rect.width;
         int w = (int)(mm / 0.02);
         if (w < 1) {
            w = 1;
         }

         if (i != 0) {
            g2D.setPaint(Color.BLACK);
            lu_jing2.setWindingRule(0);
            if (((Tu_yuan)ty_shuzu.get(i)).tian_chong && !((Tu_yuan)ty_shuzu.get(i)).wen_zi) {
               Image image2 = (new ImageIcon(this.getClass().getResource("/tu/" + (10 - Tu_yuan.tian_chong_md) + ".png"))).getImage();
               int kk = image2.getWidth((ImageObserver)null);
               int gg = image2.getHeight((ImageObserver)null);
               BufferedImage image = new BufferedImage(kk, gg, 1);
               Graphics2D g2 = (Graphics2D)image.getGraphics();
               g2.drawImage(image2, 0, 0, kk, gg, (ImageObserver)null);
               Rectangle2D.Float rect2 = new Rectangle2D.Float(0.0F, 0.0F, (float)kk, (float)gg);
               TexturePaint textPaint = new TexturePaint(image, rect2);
               g2D.setPaint(textPaint);
               g2D.fill(lu_jing2);
            }

            if (((Tu_yuan)ty_shuzu.get(i)).wen_zi) {
               g2D.setPaint(Color.BLACK);
               g2D.fill(lu_jing2);
            }

            g2D.setColor(Color.BLUE);
            g2D.draw(lu_jing2);
            g2D.setColor(Color.BLUE);
            if (Tu_yuan.tuo) {
               g2D.draw(Tu_yuan.shu_biao);
            }

            g2D.setColor(Color.RED);
            g2D.setColor(Color.BLACK);
         } else {
            g2D.setColor(Color.LIGHT_GRAY);
            g2D.draw(lu_jing2);

            int j;
            for(j = 0; j < Engraver.state.width / 10 + 1; ++j) {
               if ((double)j % (double)w == 0.0) {
                  g2D.drawString(String.valueOf(j), (int)((double)rect.x + (double)j * ((double)rect.width / ((double)Engraver.state.width / 10.0))), rect.y);
               }
            }

            for(j = 0; j < Engraver.state.height / 10 + 1; ++j) {
               if ((double)j % (double)w == 0.0) {
                  g2D.drawString(String.valueOf(j), rect.x - 16, (int)((double)rect.y + (double)j * ((double)rect.height / ((double)Engraver.state.height / 10.0)) + 10.0));
               }
            }

            g2D.setColor(Color.BLACK);
         }

         if (((Tu_yuan)ty_shuzu.get(i)).lei_xing == 1) {
            g2D.drawImage(((Tu_yuan)ty_shuzu.get(i)).wei_tu, ((Tu_yuan)ty_shuzu.get(i)).Tx, (ImageObserver)null);
         }

         if (((Tu_yuan)ty_shuzu.get(i)).xuan_zhong) {
            xuan_zhong = true;
         }
      }

      if (xuan_zhong && !Tu_yuan.tuo) {
         Rectangle rect = Tu_yuan.qu_jv_xing(ty_shuzu);
         g2D.setColor(Color.GREEN);
         g2D.draw(rect);
         g2D.drawImage((new ImageIcon(this.getClass().getResource("/tu/ic_icon_delete.png"))).getImage(), rect.x - 15, rect.y - 20, 40, 40, (ImageObserver)null);
         g2D.drawImage((new ImageIcon(this.getClass().getResource("/tu/ic_icon_rotate.png"))).getImage(), rect.x + rect.width - 20, rect.y - 20, 40, 40, (ImageObserver)null);
         g2D.drawImage((new ImageIcon(this.getClass().getResource("/tu/ic_icon_scale.png"))).getImage(), rect.x + rect.width - 20, rect.y + rect.height - 20, 40, 40, (ImageObserver)null);
         g2D.setColor(Color.BLUE);
         g2D.setFont(new Font(g2D.getFont().getName(), g2D.getFont().getStyle(), 16));
         Point2D d = this.zhuan_huan(rect.getLocation());
         this.x = (int)Math.round(d.getX() * Engraver.state.scaleFactor);
         this.y = (int)Math.round(d.getY() * Engraver.state.scaleFactor);
         d = this.zhuan_huan(new Point(rect.x + rect.width, rect.y + rect.height));
         this.ww = (int)Math.round(d.getX() * Engraver.state.scaleFactor) - this.x;
         this.hh = (int)Math.round(d.getY() * Engraver.state.scaleFactor) - this.y;
         this.jp.setLocation(rect.x - 18, rect.y - 60);
         this.jp.setSize(130, 35);
         this.wb1.setText(String.valueOf(this.x));
         this.wb2.setText(String.valueOf(this.y));
         this.wb3.setText(String.valueOf(this.ww));
         this.wb4.setText(String.valueOf(this.hh));
         g2D.setColor(Color.BLACK);
      }

      if (this.jp != null && !xuan_zhong) {
         this.jp.setLocation(2, -this.jp.getHeight());
      }

   }

   void xuan_zhong_lei_xing() {
      this.LeiXing_sl = false;
      this.LeiXing_tp = false;

      for(int i = 1; i < ty_shuzu.size(); ++i) {
         if (((Tu_yuan)ty_shuzu.get(i)).xuan_zhong) {
            if (((Tu_yuan)ty_shuzu.get(i)).lei_xing == 0) {
               this.LeiXing_sl = true;
            } else {
               this.LeiXing_tp = true;
            }
         }
      }

   }

   Point2D zhuan_huan(Point2D m) {
      GeneralPath lu_jing2 = new GeneralPath(((Tu_yuan)ty_shuzu.get(0)).lu_jing);
      lu_jing2.transform(((Tu_yuan)ty_shuzu.get(0)).Tx);
      Rectangle rect = lu_jing2.getBounds();
      AffineTransform sf = AffineTransform.getTranslateInstance((double)(0 - rect.x), (double)(0 - rect.y));
      sf.transform(m, m);
      sf = AffineTransform.getScaleInstance(1.0 / quan_beishu, 1.0 / quan_beishu);
      sf.transform(m, m);
      return m;
   }

   public void di_tu() {
      Tu_yuan.hui_fu();
      Tu_yuan ty = new Tu_yuan();
      ty.lei_xing = 0;
      quan_beishu = 1.0;
      quan_x = 0;
      quan_y = 0;

      int j;
      for(j = 0; j < Engraver.state.height + 1; ++j) {
         if (j == 0 || j % 5 == 0 || j == Engraver.state.height) {
            ty.lu_jing.moveTo(0.0, (double)j / Engraver.state.scaleFactor);
            ty.lu_jing.lineTo((double)Engraver.state.width / Engraver.state.scaleFactor, (double)j / Engraver.state.scaleFactor);
         }
      }

      for(j = 0; j < Engraver.state.width + 1; ++j) {
         if (j == 0 || j % 5 == 0 || j == Engraver.state.width) {
            ty.lu_jing.moveTo((double)j / Engraver.state.scaleFactor, 0.0);
            ty.lu_jing.lineTo((double)j / Engraver.state.scaleFactor, (double)Engraver.state.height / Engraver.state.scaleFactor);
         }
      }

      ty_shuzu.set(0, ty);
      this.repaint();
      Tu_yuan.hui_fu_xian_chang();
   }
}
