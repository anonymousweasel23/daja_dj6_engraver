package net.sf.image4j.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.sf.image4j.codec.bmp.BMPDecoder;
import net.sf.image4j.codec.bmp.BMPEncoder;
import net.sf.image4j.codec.ico.ICODecoder;
import net.sf.image4j.codec.ico.ICOEncoder;

public class Test {
   public static void main(String[] args) {
      String strInFile = "input.ico";
      String strOutFile = "output.ico";

      try {
         System.out.println("Decoding ICO file '" + strInFile + "'.");
         File inFile = new File(strInFile);
         List images = ICODecoder.read(inFile);
         System.out.println("ICO decoding...OK");
         System.out.println("  image count = " + images.size());
         System.out.println("  image summary:");

         int k;
         for(int i = 0; i < images.size(); ++i) {
            BufferedImage img = (BufferedImage)images.get(i);
            k = img.getColorModel().getPixelSize();
            int width = img.getWidth();
            int height = img.getHeight();
            System.out.println("    # " + i + ": size=" + width + "x" + height + "; colour depth=" + k + " bpp");
         }

         System.out.println("  saving separate images:");
         String format = "png";

         String name;
         File file;
         for(int j = 0; j < images.size(); ++j) {
            BufferedImage img = (BufferedImage)images.get(j);
            name = strInFile + "-" + j;
            file = new File(name + ".bmp");
            File pngFile = new File(name + ".png");
            System.out.println("    writing '" + name + ".bmp'");
            BMPEncoder.write(img, file);
            System.out.println("    writing '" + name + ".png'");
            ImageIO.write(img, format, pngFile);
         }

         System.out.println("BMP encoding...OK");
         System.out.println("  reloading BMP files:");
         List images2 = new ArrayList(images.size());

         for(k = 0; k < images.size(); ++k) {
            name = strInFile + "-" + k + ".bmp";
            file = new File(name);
            System.out.println("    reading '" + name + "'");
            BufferedImage image = BMPDecoder.read(file);
            images2.add(image);
         }

         System.out.println("BMP decoding...OK");
         System.out.println("Encoding ICO file '" + strOutFile + "'.");
         File outFile = new File(strOutFile);
         ICOEncoder.write(images, outFile);
         System.out.println("ICO encoding...OK");
      } catch (IOException var11) {
         IOException ex = var11;
         ex.printStackTrace();
      }

   }

   private static void usage() {
   }
}
