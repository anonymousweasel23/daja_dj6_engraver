package com.engraver.ui;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Wen_zi {
   public static GeneralPath getGeneralPath233(String text, Font font, boolean shu) {
      FontRenderContext context = new FontRenderContext((AffineTransform)null, false, false);
      GeneralPath shape = new GeneralPath();
      List shu_zu = new ArrayList();
      TextLayout layout2 = new TextLayout("0", font, context);
      GeneralPath outline2 = (GeneralPath)layout2.getOutline((AffineTransform)null);
      double jian_ge_y = outline2.getBounds2D().getHeight() * 0.15;
      double jian_ge_x = outline2.getBounds2D().getWidth() * 0.15;

      int m;
      for(m = 0; m < text.length(); ++m) {
         shu_zu.add(text.substring(m, m + 1));
      }

      int shang_n;
      double yy;
      if (shu) {
         m = 0;
         int n = 0;
         shang_n = 0;
         int shang_m = 0;

         for(int i = 0; i < shu_zu.size(); ++i) {
            String tempStr = (String)shu_zu.get(i);
            if (!tempStr.equals("\n")) {
               TextLayout layout = new TextLayout(tempStr, font, context);
               GeneralPath outline = (GeneralPath)layout.getOutline((AffineTransform)null);
               if (i != 0) {
                  yy = (double)n + (double)shang_n * jian_ge_y;
                  double xx = (double)m + (double)shang_m * jian_ge_x;
                  outline.transform(AffineTransform.getTranslateInstance(yy, xx));
                  shang_n = outline.getBounds().height;
                  shang_m = outline.getBounds().width;
                  shape.append(outline, false);
               } else {
                  shang_n = outline.getBounds().height;
                  shang_m = outline.getBounds().width;
                  shape.append(outline, false);
               }
            }
         }

         return shape;
      } else {
         m = 0;
         int n = 0;

         for(shang_n = 0; shang_n < text.length(); ++shang_n) {
            ++n;
            String tempStr = text.substring(shang_n, shang_n + 1);
            if (tempStr.equals("\n")) {
               ++m;
               n = 0;
            }

            TextLayout layout = new TextLayout(tempStr, font, context);
            GeneralPath outline = (GeneralPath)layout.getOutline((AffineTransform)null);
            yy = (double)n * jian_ge_y;
            yy = (double)m * jian_ge_x;
            outline.transform(AffineTransform.getTranslateInstance(yy, yy));
            shape.append(outline, false);
         }

         return shape;
      }
   }

   public static GeneralPath getGeneralPath3(String text, Font font, boolean shu) {
      FontRenderContext context = new FontRenderContext((AffineTransform)null, false, false);
      GeneralPath shape = new GeneralPath();
      TextLayout layout2 = new TextLayout("信", font, context);
      GeneralPath outline2 = (GeneralPath)layout2.getOutline((AffineTransform)null);
      double jian_ge_y = (double)outline2.getBounds().height + outline2.getBounds2D().getHeight() * 0.15;
      double jian_ge_x = (double)outline2.getBounds().width + outline2.getBounds2D().getWidth() * 0.15;
      int m;
      int n;
      int i;
      String tempStr;
      TextLayout layout;
      GeneralPath outline;
      double yy;
      double xx;
      if (shu) {
         m = 0;
         n = 0;

         for(i = 0; i < text.length(); ++i) {
            ++n;
            tempStr = text.substring(i, i + 1);
            if (tempStr.equals("\n")) {
               ++m;
               n = 0;
            }

            layout = new TextLayout(tempStr, font, context);
            outline = (GeneralPath)layout.getOutline((AffineTransform)null);
            yy = (double)n * jian_ge_y;
            xx = (double)m * jian_ge_x;
            outline.transform(AffineTransform.getTranslateInstance(xx, yy));
            shape.append(outline, false);
         }

         return shape;
      } else {
         m = 0;
         n = 0;

         for(i = 0; i < text.length(); ++i) {
            ++n;
            tempStr = text.substring(i, i + 1);
            if (tempStr.equals("\n")) {
               ++m;
               n = 0;
            }

            layout = new TextLayout(tempStr, font, context);
            outline = (GeneralPath)layout.getOutline((AffineTransform)null);
            yy = (double)n * jian_ge_y;
            xx = (double)m * jian_ge_x;
            outline.transform(AffineTransform.getTranslateInstance(yy, xx));
            shape.append(outline, false);
         }

         return shape;
      }
   }

   public static GeneralPath getGeneralPath(String text, Font font, boolean shu) {
      FontRenderContext context = new FontRenderContext((AffineTransform)null, false, false);
      GeneralPath shape = new GeneralPath();
      TextLayout layout2 = new TextLayout("z", font, context);
      GeneralPath outline2 = (GeneralPath)layout2.getOutline((AffineTransform)null);
      double jian_ge = outline2.getBounds2D().getWidth() * 0.25;
      layout2 = new TextLayout("信", font, context);
      outline2 = (GeneralPath)layout2.getOutline((AffineTransform)null);
      double jian_ge2 = outline2.getBounds2D().getHeight();
      double xx;
      if (shu) {
         int m = 0;
         int n = 0;
         xx = 0.0;

         for(int i = 0; i < text.length(); ++i) {
            String tempStr = text.substring(i, i + 1);
            TextLayout layout = new TextLayout(tempStr, font, context);
            GeneralPath outline = (GeneralPath)layout.getOutline((AffineTransform)null);
            outline.transform(AffineTransform.getTranslateInstance(xx - (double)outline.getBounds().x, xx - (double)outline.getBounds().y));
            xx = xx + jian_ge + (double)outline.getBounds().height;
            shape.append(outline, false);
            if (tempStr.equals("\n")) {
               xx = xx + jian_ge2 + jian_ge2 * 0.25;
               xx = 0.0;
            }
         }

         return shape;
      } else {
         double yy = 0.0;
         xx = 0.0;

         for(int i = 0; i < text.length(); ++i) {
            String tempStr = text.substring(i, i + 1);
            TextLayout layout = new TextLayout(tempStr, font, context);
            GeneralPath outline = (GeneralPath)layout.getOutline((AffineTransform)null);
            System.out.println(outline.getBounds());
            outline.transform(AffineTransform.getTranslateInstance(xx - (double)outline.getBounds().x, yy));
            xx = xx + jian_ge + (double)outline.getBounds().width;
            shape.append(outline, false);
            if (tempStr.equals("\n")) {
               yy = yy + jian_ge2 + jian_ge2 * 0.25;
               xx = 0.0;
            }
         }

         return shape;
      }
   }
}
