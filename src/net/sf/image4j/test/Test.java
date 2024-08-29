package net.sf.image4j.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import net.sf.image4j.codec.bmp.BMPDecoder;
import net.sf.image4j.codec.bmp.BMPEncoder;
import net.sf.image4j.codec.ico.ICODecoder;
import net.sf.image4j.codec.ico.ICOEncoder;

public class Test {
   public static void main(String[] args) {
      if (args.length < 2) {
         System.out.println("Usage:\n\tTest <inputfile> <outputfile>");
         System.exit(1);
      }

      String strInFile = args[0];
      String strOutFile = args[1];
      InputStream in = null;

      try {
         if (strInFile.startsWith("http:")) {
            in = (new URL(strInFile)).openStream();
         } else {
            in = new FileInputStream(strInFile);
         }

         Object images;
         if (!strInFile.endsWith(".ico")) {
            images = new ArrayList(1);
            ((List)images).add(ImageIO.read((InputStream)in));
            System.out.println("Read image " + strInFile + "...OK");
         } else {
            System.out.println("Decoding ICO file '" + strInFile + "'.");
            images = ICODecoder.read((InputStream)in);
            System.out.println("ICO decoding...OK");
            System.out.println("  image count = " + ((List)images).size());
            System.out.println("  image summary:");

            int k;
            for(int i = 0; i < ((List)images).size(); ++i) {
               BufferedImage img = (BufferedImage)((List)images).get(i);
               k = img.getColorModel().getPixelSize();
               int width = img.getWidth();
               int height = img.getHeight();
               System.out.println("    # " + i + ": size=" + width + "x" + height + "; colour depth=" + k + " bpp");
            }

            System.out.println("  saving separate images:");
            String format = "png";

            String name;
            File file;
            for(int j = 0; j < ((List)images).size(); ++j) {
               BufferedImage img = (BufferedImage)((List)images).get(j);
               name = strOutFile + "-" + j;
               file = new File(name + ".bmp");
               File pngFile = new File(name + ".png");
               System.out.println("    writing '" + name + ".bmp'");
               BMPEncoder.write(img, file);
               System.out.println("    writing '" + name + ".png'");
               ImageIO.write(img, format, pngFile);
            }

            System.out.println("BMP encoding...OK");
            System.out.println("  reloading BMP files:");
            List images2 = new ArrayList(((List)images).size());

            for(k = 0; k < ((List)images).size(); ++k) {
               name = strOutFile + "-" + k + ".bmp";
               file = new File(name);
               System.out.println("    reading '" + name + "'");
               BufferedImage image = BMPDecoder.read(file);
               images2.add(image);
            }

            System.out.println("BMP decoding...OK");
         }

         System.out.println("Encoding ICO file '" + strOutFile + "'.");
         File outFile = new File(strOutFile);
         ICOEncoder.write((List)images, (File)outFile);
         System.out.println("ICO encoding...OK");
      } catch (IOException var19) {
         IOException ex = var19;
         ex.printStackTrace();
      } finally {
         try {
            ((InputStream)in).close();
         } catch (IOException var18) {
         }

      }

   }

   private static void usage() {
   }
}
