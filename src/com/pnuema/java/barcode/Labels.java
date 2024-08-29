package com.pnuema.java.barcode;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

class Labels {
   public static Image Label_ITF14(Barcode Barcode, BufferedImage img) {
      try {
         Font font = Barcode.getLabelFont();
         Graphics2D g = img.createGraphics();
         g.drawImage(img, 0, 0, (ImageObserver)null);
         RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         g.setRenderingHints(rh);
         g.setColor(Barcode.getBackColor());
         Rectangle rect = new Rectangle(0, img.getHeight() - (font.getSize() - 2), img.getWidth(), font.getSize());
         g.drawRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
         g.setColor(Barcode.getForeColor());
         String text = Barcode.getAlternateLabel() == null ? Barcode.getRawData() : Barcode.getAlternateLabel();
         drawCenteredString(g, text, rect, font);
         float lineThickness = (float)img.getHeight() / 16.0F;
         g.drawRect(0, img.getHeight() - font.getSize() - 2 - (int)lineThickness / 2, img.getWidth(), img.getHeight() - font.getSize() - 2 + (int)lineThickness / 2);
         g.dispose();
         return img;
      } catch (Exception var8) {
         Exception ex = var8;
         throw new RuntimeException("ELABEL_ITF14-1: " + ex.getMessage());
      }
   }

   static Image labelGeneric(Barcode Barcode, BufferedImage img) {
      try {
         Font font = Barcode.getLabelFont();
         Graphics2D g = img.createGraphics();
         g.drawImage(img, 0, 0, (ImageObserver)null);
         RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         g.setRenderingHints(rh);
         int LabelY = 0;
         switch (Barcode.getLabelPosition()) {
            case BOTTOM:
               LabelY = img.getHeight() - font.getSize();
            default:
               g.setColor(Barcode.getBackColor());
               g.drawRect(0, LabelY, img.getWidth(), font.getSize());
               g.setColor(Barcode.getForeColor());
               Rectangle rect = new Rectangle(0, LabelY, img.getWidth(), font.getSize());
               drawCenteredString(g, Barcode.getAlternateLabel() == null ? Barcode.getRawData() : Barcode.getAlternateLabel(), rect, font);
               g.dispose();
               return img;
         }
      } catch (Exception var7) {
         Exception ex = var7;
         throw new RuntimeException("ELABEL_GENERIC-1: " + ex.getMessage());
      }
   }

   static Image Label_EAN13(Barcode Barcode, BufferedImage img) {
      try {
         int iBarWidth = Barcode.getWidth() / Barcode.getEncodedValue().length();
         String defTxt = Barcode.getRawData();
         int fontSize = getFontsize(Barcode.getWidth() - Barcode.getWidth() % Barcode.getEncodedValue().length(), img.getHeight(), defTxt);
         Font labFont = new Font("Serif", 0, fontSize);
         Font smallFont = new Font(labFont.getFamily(), labFont.getStyle(), (int)((float)fontSize * 0.5F));
         int shiftAdjustment = getShiftAdjustment(Barcode);
         Graphics2D g = img.createGraphics();
         g.drawImage(img, 0, 0, (ImageObserver)null);
         RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         g.setRenderingHints(rh);
         int LabelY = img.getHeight() - labFont.getSize();
         float w1 = (float)(iBarWidth * 4);
         float w2 = (float)(iBarWidth * 42);
         float w3 = (float)(iBarWidth * 42);
         float s1 = (float)(shiftAdjustment - iBarWidth);
         float s2 = s1 + (float)(iBarWidth * 4);
         float s3 = s2 + w2 + (float)(iBarWidth * 5);
         g.setColor(Barcode.getBackColor());
         g.drawRect((int)s2, LabelY, (int)w2, labFont.getSize());
         g.drawRect((int)s3, LabelY, (int)w3, labFont.getSize());
         g.setColor(Barcode.getForeColor());
         g.drawString(defTxt.substring(0, 1), s1, (float)img.getHeight() - (float)((double)smallFont.getSize() * 0.9));
         g.drawString(defTxt.substring(1, 6), s2, (float)LabelY);
         g.drawString(defTxt.substring(7), s3 - (float)iBarWidth, (float)LabelY);
         g.dispose();
         return img;
      } catch (Exception var17) {
         Exception ex = var17;
         throw new IllegalArgumentException("ELABEL_EAN13-1: " + ex.getMessage());
      }
   }

   public static Image Label_UPCA(Barcode Barcode, BufferedImage img) {
      try {
         int iBarWidth = Barcode.getWidth() / Barcode.getEncodedValue().length();
         int halfBarWidth = (int)((double)iBarWidth * 0.5);
         String defTxt = Barcode.getRawData();
         int fontSize = getFontsize((int)((float)(Barcode.getWidth() - Barcode.getWidth() % Barcode.getEncodedValue().length()) * 0.9F), img.getHeight(), defTxt);
         Font labFont = new Font("Serif", 0, fontSize);
         Font smallFont = new Font(labFont.getFamily(), labFont.getStyle(), (int)((float)fontSize * 0.5F));
         int shiftAdjustment = getShiftAdjustment(Barcode);
         Graphics2D g = img.createGraphics();
         g.drawImage(img, 0, 0, (ImageObserver)null);
         RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
         g.setRenderingHints(rh);
         int LabelY = img.getHeight() - labFont.getSize();
         float w1 = (float)(iBarWidth * 4);
         float w2 = (float)(iBarWidth * 34);
         float w3 = (float)(iBarWidth * 34);
         float s1 = (float)(shiftAdjustment - iBarWidth);
         float s2 = s1 + (float)(iBarWidth * 12);
         float s3 = s2 + w2 + (float)(iBarWidth * 5);
         float s4 = s3 + w3 + (float)(iBarWidth * 8) - (float)halfBarWidth;
         g.setColor(Barcode.getBackColor());
         g.drawRect((int)s2, LabelY, (int)w2, labFont.getSize());
         g.drawRect((int)s3, LabelY, (int)w3, labFont.getSize());
         g.setColor(Barcode.getForeColor());
         g.drawString(defTxt.substring(0, 1), s1, (float)img.getHeight() - (float)smallFont.getSize());
         g.drawString(defTxt.substring(1, 5), s2 - (float)iBarWidth, (float)LabelY);
         g.drawString(defTxt.substring(6, 11), s3 - (float)iBarWidth, (float)LabelY);
         g.drawString(defTxt.substring(11), s4, (float)(img.getHeight() - smallFont.getSize()));
         g.dispose();
         return img;
      } catch (Exception var19) {
         Exception ex = var19;
         throw new RuntimeException("ELABEL_UPCA-1: " + ex.getMessage());
      }
   }

   public static int getFontsize(int wid, int hgt, String lbl) {
      int fontSize = 10;
      if (lbl.length() > 0) {
         BufferedImage fakeImage = new BufferedImage(1, 1, 2);
         Graphics g = fakeImage.createGraphics();

         for(int i = 1; i <= 100; ++i) {
            int text_size = ((Graphics)g).getFontMetrics().stringWidth(lbl);
            if (text_size > wid || text_size > hgt) {
               fontSize = i - 1;
               break;
            }
         }

         ((Graphics)g).dispose();
      }

      return fontSize;
   }

   public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
      FontMetrics metrics = g.getFontMetrics(font);
      int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
      int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
      g.setFont(font);
      g.drawString(text, x, y);
   }

   private static int getShiftAdjustment(Barcode barcode) {
      switch (barcode.getAlignmentPosition()) {
         case LEFT:
            return 0;
         case RIGHT:
            return barcode.getWidth() % barcode.getEncodedValue().length();
         case CENTER:
         default:
            return barcode.getWidth() % barcode.getEncodedValue().length() / 2;
      }
   }

   public static enum LabelPositions {
      TOP,
      BOTTOM;
   }
}
