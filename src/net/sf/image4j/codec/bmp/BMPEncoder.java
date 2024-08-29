package net.sf.image4j.codec.bmp;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.sf.image4j.io.LittleEndianOutputStream;

public class BMPEncoder {
   private BMPEncoder() {
   }

   public static void write(BufferedImage img, File file) throws IOException {
      FileOutputStream fout = new FileOutputStream(file);

      try {
         BufferedOutputStream out = new BufferedOutputStream(fout);
         write(img, (OutputStream)out);
         out.flush();
      } finally {
         try {
            fout.close();
         } catch (IOException var9) {
         }

      }

   }

   public static void write(BufferedImage img, OutputStream os) throws IOException {
      InfoHeader ih = createInfoHeader(img);
      int mapSize = 0;
      IndexColorModel icm = null;
      if (ih.sBitCount <= 8) {
         icm = (IndexColorModel)img.getColorModel();
         mapSize = icm.getMapSize();
      }

      int headerSize = 14 + ih.iSize;
      int mapBytes = 4 * mapSize;
      int dataOffset = headerSize + mapBytes;
      int bytesPerLine = 0;
      switch (ih.sBitCount) {
         case 1:
            bytesPerLine = getBytesPerLine1(ih.iWidth);
            break;
         case 4:
            bytesPerLine = getBytesPerLine4(ih.iWidth);
            break;
         case 8:
            bytesPerLine = getBytesPerLine8(ih.iWidth);
            break;
         case 24:
            bytesPerLine = getBytesPerLine24(ih.iWidth);
            break;
         case 32:
            bytesPerLine = ih.iWidth * 4;
      }

      int fileSize = dataOffset + bytesPerLine * ih.iHeight;
      LittleEndianOutputStream out = new LittleEndianOutputStream(os);
      writeFileHeader(fileSize, dataOffset, out);
      ih.write(out);
      if (ih.sBitCount <= 8) {
         writeColorMap(icm, out);
      }

      switch (ih.sBitCount) {
         case 1:
            write1(img.getRaster(), out);
            break;
         case 4:
            write4(img.getRaster(), out);
            break;
         case 8:
            write8(img.getRaster(), out);
            break;
         case 24:
            write24(img.getRaster(), out);
            break;
         case 32:
            write32(img.getRaster(), img.getAlphaRaster(), out);
      }

   }

   public static InfoHeader createInfoHeader(BufferedImage img) {
      InfoHeader ret = new InfoHeader();
      ret.iColorsImportant = 0;
      ret.iColorsUsed = 0;
      ret.iCompression = 0;
      ret.iHeight = img.getHeight();
      ret.iWidth = img.getWidth();
      ret.sBitCount = (short)img.getColorModel().getPixelSize();
      ret.iNumColors = 1 << (ret.sBitCount == 32 ? 24 : ret.sBitCount);
      ret.iImageSize = 0;
      return ret;
   }

   public static void writeFileHeader(int fileSize, int dataOffset, LittleEndianOutputStream out) throws IOException {
      byte[] signature = "BM".getBytes("UTF-8");
      out.write(signature);
      out.writeIntLE(fileSize);
      out.writeIntLE(0);
      out.writeIntLE(dataOffset);
   }

   public static void writeColorMap(IndexColorModel icm, LittleEndianOutputStream out) throws IOException {
      int mapSize = icm.getMapSize();

      for(int i = 0; i < mapSize; ++i) {
         int rgb = icm.getRGB(i);
         int r = rgb >> 16 & 255;
         int g = rgb >> 8 & 255;
         int b = rgb & 255;
         out.writeByte(b);
         out.writeByte(g);
         out.writeByte(r);
         out.writeByte(0);
      }

   }

   public static int getBytesPerLine1(int width) {
      int ret = width / 8;
      if (ret * 8 < width) {
         ++ret;
      }

      if (ret % 4 != 0) {
         ret = (ret / 4 + 1) * 4;
      }

      return ret;
   }

   public static int getBytesPerLine4(int width) {
      int ret = width / 2;
      if (ret % 4 != 0) {
         ret = (ret / 4 + 1) * 4;
      }

      return ret;
   }

   public static int getBytesPerLine8(int width) {
      int ret = width;
      if (ret % 4 != 0) {
         ret = (ret / 4 + 1) * 4;
      }

      return ret;
   }

   public static int getBytesPerLine24(int width) {
      int ret = width * 3;
      if (ret % 4 != 0) {
         ret = (ret / 4 + 1) * 4;
      }

      return ret;
   }

   public static int getBitmapSize(int w, int h, int bpp) {
      int bytesPerLine = 0;
      switch (bpp) {
         case 1:
            bytesPerLine = getBytesPerLine1(w);
            break;
         case 4:
            bytesPerLine = getBytesPerLine4(w);
            break;
         case 8:
            bytesPerLine = getBytesPerLine8(w);
            break;
         case 24:
            bytesPerLine = getBytesPerLine24(w);
            break;
         case 32:
            bytesPerLine = w * 4;
      }

      int ret = bytesPerLine * h;
      return ret;
   }

   public static void write1(Raster raster, LittleEndianOutputStream out) throws IOException {
      int bytesPerLine = getBytesPerLine1(raster.getWidth());
      byte[] line = new byte[bytesPerLine];

      for(int y = raster.getHeight() - 1; y >= 0; --y) {
         int x;
         for(x = 0; x < bytesPerLine; ++x) {
            line[x] = 0;
         }

         for(x = 0; x < raster.getWidth(); ++x) {
            int bi = x / 8;
            int i = x % 8;
            int index = raster.getSample(x, y, 0);
            line[bi] = setBit(line[bi], i, index);
         }

         out.write(line);
      }

   }

   public static void write4(Raster raster, LittleEndianOutputStream out) throws IOException {
      int width = raster.getWidth();
      int height = raster.getHeight();
      int bytesPerLine = getBytesPerLine4(width);
      byte[] line = new byte[bytesPerLine];

      for(int y = height - 1; y >= 0; --y) {
         int x;
         for(x = 0; x < bytesPerLine; ++x) {
            line[x] = 0;
         }

         for(x = 0; x < width; ++x) {
            int bi = x / 2;
            int i = x % 2;
            int index = raster.getSample(x, y, 0);
            line[bi] = setNibble(line[bi], i, index);
         }

         out.write(line);
      }

   }

   public static void write8(Raster raster, LittleEndianOutputStream out) throws IOException {
      int width = raster.getWidth();
      int height = raster.getHeight();
      int bytesPerLine = getBytesPerLine8(width);

      for(int y = height - 1; y >= 0; --y) {
         int i;
         for(i = 0; i < width; ++i) {
            int index = raster.getSample(i, y, 0);
            out.writeByte(index);
         }

         for(i = width; i < bytesPerLine; ++i) {
            out.writeByte(0);
         }
      }

   }

   public static void write24(Raster raster, LittleEndianOutputStream out) throws IOException {
      int width = raster.getWidth();
      int height = raster.getHeight();
      int bytesPerLine = getBytesPerLine24(width);

      for(int y = height - 1; y >= 0; --y) {
         int i;
         for(i = 0; i < width; ++i) {
            int r = raster.getSample(i, y, 0);
            int g = raster.getSample(i, y, 1);
            int b = raster.getSample(i, y, 2);
            out.writeByte(b);
            out.writeByte(g);
            out.writeByte(r);
         }

         for(i = width * 3; i < bytesPerLine; ++i) {
            out.writeByte(0);
         }
      }

   }

   public static void write32(Raster raster, Raster alpha, LittleEndianOutputStream out) throws IOException {
      int width = raster.getWidth();
      int height = raster.getHeight();

      for(int y = height - 1; y >= 0; --y) {
         for(int x = 0; x < width; ++x) {
            int r = raster.getSample(x, y, 0);
            int g = raster.getSample(x, y, 1);
            int b = raster.getSample(x, y, 2);
            int a = alpha.getSample(x, y, 0);
            out.writeByte(b);
            out.writeByte(g);
            out.writeByte(r);
            out.writeByte(a);
         }
      }

   }

   private static byte setBit(byte bits, int index, int bit) {
      if (bit == 0) {
         bits = (byte)(bits & ~(1 << 7 - index));
      } else {
         bits = (byte)(bits | 1 << 7 - index);
      }

      return bits;
   }

   private static byte setNibble(byte nibbles, int index, int nibble) {
      nibbles = (byte)(nibbles | nibble << (1 - index) * 4);
      return nibbles;
   }

   public static int getColorMapSize(short sBitCount) {
      int ret = 0;
      if (sBitCount <= 8) {
         ret = (1 << sBitCount) * 4;
      }

      return ret;
   }
}
