package com.engraver.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class NewClass {
   public static void main(String[] args) throws Exception {
      int width = 150;
      int height = 30;
      String out = new String("今夕是何年？");
      double rate = 1.9;
      BufferedImage image = new BufferedImage(width, height, 4);
      Graphics g = image.getGraphics();
      g.setColor(new Color(200, 192, 184));
      g.fill3DRect(0, 0, width, height, true);
      g.setColor(Color.BLACK);
      g.setFont(new Font("宋体", 1, 20));
      int x = (int)((double)(width / 2) - rate * (double)g.getFontMetrics().stringWidth(out) / 2.0);
      int y = height / 2 + g.getFontMetrics().getHeight() / 3;
      MyDrawString(out, x, y, rate, g);
      ImageIO.write(image, "png", new File("d:/2.png"));
   }

   public static void MyDrawString(String str, int x, int y, double rate, Graphics g) {
      new String();
      int orgStringWight = g.getFontMetrics().stringWidth(str);
      int orgStringLength = str.length();
      int tempx = x;

      for(int tempy = y; str.length() > 0; tempx = (int)((double)tempx + (double)orgStringWight / (double)orgStringLength * rate)) {
         String tempStr = str.substring(0, 1);
         str = str.substring(1, str.length());
         g.drawString(tempStr, tempx, tempy);
      }

   }
}
