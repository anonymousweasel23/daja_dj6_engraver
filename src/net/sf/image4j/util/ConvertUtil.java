package net.sf.image4j.util;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.IndexColorModel;

public class ConvertUtil {
   public static BufferedImage convert1(BufferedImage src) {
      IndexColorModel icm = new IndexColorModel(1, 2, new byte[]{0, -1}, new byte[]{0, -1}, new byte[]{0, -1});
      BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 12, icm);
      ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), (RenderingHints)null);
      cco.filter(src, dest);
      return dest;
   }

   public static BufferedImage convert4(BufferedImage src) {
      int[] cmap = new int[]{0, 8388608, 32768, 8421376, 128, 8388736, 32896, 8421504, 12632256, 16711680, 65280, 16776960, 255, 16711935, 65535, 16777215};
      return convert4(src, cmap);
   }

   public static BufferedImage convert4(BufferedImage src, int[] cmap) {
      IndexColorModel icm = new IndexColorModel(4, cmap.length, cmap, 0, false, 1, 0);
      BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 12, icm);
      ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), (RenderingHints)null);
      cco.filter(src, dest);
      return dest;
   }

   public static BufferedImage convert8(BufferedImage src) {
      BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 13);
      ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), (RenderingHints)null);
      cco.filter(src, dest);
      return dest;
   }

   public static BufferedImage convert24(BufferedImage src) {
      BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 1);
      ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), (RenderingHints)null);
      cco.filter(src, dest);
      return dest;
   }

   public static BufferedImage convert32(BufferedImage src) {
      BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), 2);
      ColorConvertOp cco = new ColorConvertOp(src.getColorModel().getColorSpace(), dest.getColorModel().getColorSpace(), (RenderingHints)null);
      cco.filter(src, dest);
      return dest;
   }
}
