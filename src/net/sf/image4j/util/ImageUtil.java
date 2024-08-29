package net.sf.image4j.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class ImageUtil {
   public static BufferedImage scaleImage(BufferedImage src, int width, int height) {
      Image scaled = src.getScaledInstance(width, height, 0);
      BufferedImage ret = null;
      ret = new BufferedImage(width, height, 2);
      Graphics2D g = ret.createGraphics();
      g.drawImage(scaled, 0, 0, (ImageObserver)null);
      return ret;
   }
}
