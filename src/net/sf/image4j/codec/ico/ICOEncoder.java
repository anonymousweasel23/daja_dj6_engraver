package net.sf.image4j.codec.ico;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import net.sf.image4j.codec.bmp.BMPEncoder;
import net.sf.image4j.codec.bmp.InfoHeader;
import net.sf.image4j.io.LittleEndianOutputStream;
import net.sf.image4j.util.ConvertUtil;

public class ICOEncoder {
   private ICOEncoder() {
   }

   public static void write(BufferedImage image, File file) throws IOException {
      write(image, -1, (File)file);
   }

   public static void write(BufferedImage image, OutputStream os) throws IOException {
      write(image, -1, (OutputStream)os);
   }

   public static void write(List images, OutputStream os) throws IOException {
      write(images, (int[])null, (boolean[])null, (OutputStream)os);
   }

   public static void write(List images, File file) throws IOException {
      write(images, (int[])null, (File)file);
   }

   public static void write(List images, int[] bpp, File file) throws IOException {
      write(images, bpp, (OutputStream)(new FileOutputStream(file)));
   }

   public static void write(List images, int[] bpp, boolean[] compress, File file) throws IOException {
      write(images, bpp, compress, (OutputStream)(new FileOutputStream(file)));
   }

   public static void write(BufferedImage image, int bpp, File file) throws IOException {
      FileOutputStream fout = new FileOutputStream(file);

      try {
         BufferedOutputStream out = new BufferedOutputStream(fout);
         write(image, bpp, (OutputStream)out);
         out.flush();
      } finally {
         try {
            fout.close();
         } catch (IOException var10) {
         }

      }

   }

   public static void write(BufferedImage image, int bpp, OutputStream os) throws IOException {
      List list = new ArrayList(1);
      list.add(image);
      write(list, new int[]{bpp}, new boolean[]{false}, (OutputStream)os);
   }

   public static void write(List images, int[] bpp, OutputStream os) throws IOException {
      write(images, bpp, (boolean[])null, (OutputStream)os);
   }

   public static void write(List images, int[] bpp, boolean[] compress, OutputStream os) throws IOException {
      LittleEndianOutputStream out = new LittleEndianOutputStream(os);
      int count = images.size();
      writeFileHeader(count, 1, out);
      int fileOffset = 6 + count * 16;
      List infoHeaders = new ArrayList(count);
      List converted = new ArrayList(count);
      List compressedImages = null;
      if (compress != null) {
         compressedImages = new ArrayList(count);
      }

      ImageWriter pngWriter = null;

      int i;
      BufferedImage img;
      for(i = 0; i < count; ++i) {
         img = (BufferedImage)images.get(i);
         int b = bpp == null ? -1 : bpp[i];
         BufferedImage imgc = b == -1 ? img : convert(img, b);
         converted.add(imgc);
         InfoHeader ih = BMPEncoder.createInfoHeader(imgc);
         IconEntry e = createIconEntry(ih);
         if (compress != null) {
            if (compress[i]) {
               if (pngWriter == null) {
                  pngWriter = getPNGImageWriter();
               }

               byte[] compressedImage = encodePNG(pngWriter, imgc);
               compressedImages.add(compressedImage);
               e.iSizeInBytes = compressedImage.length;
            } else {
               compressedImages.add((Object)null);
            }
         }

         ih.iHeight *= 2;
         e.iFileOffset = fileOffset;
         fileOffset += e.iSizeInBytes;
         e.write(out);
         infoHeaders.add(ih);
      }

      for(i = 0; i < count; ++i) {
         img = (BufferedImage)images.get(i);
         BufferedImage imgc = (BufferedImage)converted.get(i);
         if (compress != null && compress[i]) {
            byte[] compressedImage = (byte[])compressedImages.get(i);
            out.write(compressedImage);
         } else {
            InfoHeader ih = (InfoHeader)infoHeaders.get(i);
            ih.write(out);
            if (ih.sBitCount <= 8) {
               IndexColorModel icm = (IndexColorModel)imgc.getColorModel();
               BMPEncoder.writeColorMap(icm, out);
            }

            writeXorBitmap(imgc, ih, out);
            writeAndBitmap(img, out);
         }
      }

   }

   public static void writeFileHeader(int count, int type, LittleEndianOutputStream out) throws IOException {
      out.writeShortLE((short)0);
      out.writeShortLE((short)type);
      out.writeShortLE((short)count);
   }

   public static IconEntry createIconEntry(InfoHeader ih) {
      IconEntry ret = new IconEntry();
      ret.bWidth = ih.iWidth == 256 ? 0 : ih.iWidth;
      ret.bHeight = ih.iHeight == 256 ? 0 : ih.iHeight;
      ret.bColorCount = ih.iNumColors >= 256 ? 0 : ih.iNumColors;
      ret.bReserved = 0;
      ret.sPlanes = 1;
      ret.sBitCount = ih.sBitCount;
      int cmapSize = BMPEncoder.getColorMapSize(ih.sBitCount);
      int xorSize = BMPEncoder.getBitmapSize(ih.iWidth, ih.iHeight, ih.sBitCount);
      int andSize = BMPEncoder.getBitmapSize(ih.iWidth, ih.iHeight, 1);
      int size = ih.iSize + cmapSize + xorSize + andSize;
      ret.iSizeInBytes = size;
      ret.iFileOffset = 0;
      return ret;
   }

   public static void writeAndBitmap(BufferedImage img, LittleEndianOutputStream out) throws IOException {
      WritableRaster alpha = img.getAlphaRaster();
      int w;
      int h;
      int bytesPerLine;
      byte[] line;
      int bi;
      int i;
      int a;
      int b;
      if (img.getColorModel() instanceof IndexColorModel && img.getColorModel().hasAlpha()) {
         w = img.getWidth();
         h = img.getHeight();
         bytesPerLine = BMPEncoder.getBytesPerLine1(w);
         line = new byte[bytesPerLine];
         IndexColorModel icm = (IndexColorModel)img.getColorModel();
         Raster raster = img.getRaster();

         for(bi = h - 1; bi >= 0; --bi) {
            for(i = 0; i < w; ++i) {
               a = i / 8;
               b = i % 8;
               int p = ((Raster)raster).getSample(i, bi, 0);
               a = icm.getAlpha(p);
               b = ~a & 1;
               line[a] = setBit(line[a], b, b);
            }

            out.write(line);
         }
      } else {
         int y;
         if (alpha == null) {
            w = img.getHeight();
            h = img.getWidth();
            bytesPerLine = BMPEncoder.getBytesPerLine1(h);
            line = new byte[bytesPerLine];

            for(y = 0; y < bytesPerLine; ++y) {
               line[y] = 0;
            }

            for(y = w - 1; y >= 0; --y) {
               out.write(line);
            }
         } else {
            w = img.getWidth();
            h = img.getHeight();
            bytesPerLine = BMPEncoder.getBytesPerLine1(w);
            line = new byte[bytesPerLine];

            for(y = h - 1; y >= 0; --y) {
               for(int x = 0; x < w; ++x) {
                  bi = x / 8;
                  i = x % 8;
                  a = alpha.getSample(x, y, 0);
                  b = ~a & 1;
                  line[bi] = setBit(line[bi], i, b);
               }

               out.write(line);
            }
         }
      }

   }

   private static byte setBit(byte bits, int index, int bit) {
      int mask = 1 << 7 - index;
      bits = (byte)(bits & ~mask);
      bits = (byte)(bits | bit << 7 - index);
      return bits;
   }

   private static void writeXorBitmap(BufferedImage img, InfoHeader ih, LittleEndianOutputStream out) throws IOException {
      Raster raster = img.getRaster();
      switch (ih.sBitCount) {
         case 1:
            BMPEncoder.write1(raster, out);
            break;
         case 4:
            BMPEncoder.write4(raster, out);
            break;
         case 8:
            BMPEncoder.write8(raster, out);
            break;
         case 24:
            BMPEncoder.write24(raster, out);
            break;
         case 32:
            Raster alpha = img.getAlphaRaster();
            BMPEncoder.write32(raster, alpha, out);
      }

   }

   public static BufferedImage convert(BufferedImage img, int bpp) {
      BufferedImage ret = null;
      switch (bpp) {
         case 1:
            ret = ConvertUtil.convert1(img);
            break;
         case 4:
            ret = ConvertUtil.convert4(img);
            break;
         case 8:
            ret = ConvertUtil.convert8(img);
            break;
         case 24:
            int b = img.getColorModel().getPixelSize();
            if (b != 24 && b != 32) {
               ret = ConvertUtil.convert24(img);
            } else {
               ret = img;
            }
            break;
         case 32:
            int b2 = img.getColorModel().getPixelSize();
            if (b2 != 24 && b2 != 32) {
               ret = ConvertUtil.convert32(img);
            } else {
               ret = img;
            }
      }

      return ret;
   }

   private static ImageWriter getPNGImageWriter() {
      ImageWriter ret = null;
      Iterator itr = ImageIO.getImageWritersByFormatName("png");
      if (itr.hasNext()) {
         ret = (ImageWriter)itr.next();
      }

      return ret;
   }

   private static byte[] encodePNG(ImageWriter pngWriter, BufferedImage img) throws IOException {
      ByteArrayOutputStream bout = new ByteArrayOutputStream();
      ImageOutputStream output = ImageIO.createImageOutputStream(bout);
      pngWriter.setOutput(output);
      pngWriter.write(img);
      bout.flush();
      byte[] ret = bout.toByteArray();
      return ret;
   }
}
