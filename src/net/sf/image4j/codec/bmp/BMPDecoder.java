package net.sf.image4j.codec.bmp;

import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import net.sf.image4j.io.CountingInputStream;
import net.sf.image4j.io.LittleEndianInputStream;

public class BMPDecoder {
   private BufferedImage img;
   private InfoHeader infoHeader;

   public BMPDecoder(InputStream in) throws IOException {
      LittleEndianInputStream lis = new LittleEndianInputStream(new CountingInputStream(in));
      byte[] bsignature = new byte[2];
      lis.read(bsignature);
      String signature = new String(bsignature, "UTF-8");
      if (!signature.equals("BM")) {
         throw new IOException("Invalid signature '" + signature + "' for BMP format");
      } else {
         int fileSize = lis.readIntLE();
         int reserved = lis.readIntLE();
         int dataOffset = lis.readIntLE();
         this.infoHeader = readInfoHeader(lis);
         this.img = read(this.infoHeader, lis);
      }
   }

   private static int getBit(int bits, int index) {
      return bits >> 7 - index & 1;
   }

   private static int getNibble(int nibbles, int index) {
      return nibbles >> 4 * (1 - index) & 15;
   }

   public InfoHeader getInfoHeader() {
      return this.infoHeader;
   }

   public BufferedImage getBufferedImage() {
      return this.img;
   }

   private static void getColorTable(ColorEntry[] colorTable, byte[] ar, byte[] ag, byte[] ab) {
      for(int i = 0; i < colorTable.length; ++i) {
         ar[i] = (byte)colorTable[i].bRed;
         ag[i] = (byte)colorTable[i].bGreen;
         ab[i] = (byte)colorTable[i].bBlue;
      }

   }

   public static InfoHeader readInfoHeader(LittleEndianInputStream lis) throws IOException {
      InfoHeader infoHeader = new InfoHeader(lis);
      return infoHeader;
   }

   public static InfoHeader readInfoHeader(LittleEndianInputStream lis, int infoSize) throws IOException {
      InfoHeader infoHeader = new InfoHeader(lis, infoSize);
      return infoHeader;
   }

   public static BufferedImage read(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
      BufferedImage img = null;
      ColorEntry[] colorTable = null;
      if (infoHeader.sBitCount <= 8) {
         colorTable = readColorTable(infoHeader, lis);
      }

      img = read(infoHeader, lis, colorTable);
      return img;
   }

   public static BufferedImage read(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
      BufferedImage img = null;
      if (infoHeader.sBitCount == 1 && infoHeader.iCompression == 0) {
         img = read1(infoHeader, lis, colorTable);
      } else if (infoHeader.sBitCount == 4 && infoHeader.iCompression == 0) {
         img = read4(infoHeader, lis, colorTable);
      } else if (infoHeader.sBitCount == 8 && infoHeader.iCompression == 0) {
         img = read8(infoHeader, lis, colorTable);
      } else if (infoHeader.sBitCount == 24 && infoHeader.iCompression == 0) {
         img = read24(infoHeader, lis);
      } else {
         if (infoHeader.sBitCount != 32 || infoHeader.iCompression != 0) {
            throw new IOException("Unrecognized bitmap format: bit count=" + infoHeader.sBitCount + ", compression=" + infoHeader.iCompression);
         }

         img = read32(infoHeader, lis);
      }

      return img;
   }

   public static ColorEntry[] readColorTable(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
      ColorEntry[] colorTable = new ColorEntry[infoHeader.iNumColors];

      for(int i = 0; i < infoHeader.iNumColors; ++i) {
         ColorEntry ce = new ColorEntry(lis);
         colorTable[i] = ce;
      }

      return colorTable;
   }

   public static BufferedImage read1(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
      byte[] ar = new byte[colorTable.length];
      byte[] ag = new byte[colorTable.length];
      byte[] ab = new byte[colorTable.length];
      getColorTable(colorTable, ar, ag, ab);
      IndexColorModel icm = new IndexColorModel(1, 2, ar, ag, ab);
      BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 12, icm);
      WritableRaster raster = img.getRaster();
      int dataBitsPerLine = infoHeader.iWidth;
      int bitsPerLine = dataBitsPerLine;
      if (bitsPerLine % 32 != 0) {
         bitsPerLine = (bitsPerLine / 32 + 1) * 32;
      }

      int padBits = bitsPerLine - dataBitsPerLine;
      int padBytes = padBits / 8;
      int bytesPerLine = bitsPerLine / 8;
      int[] line = new int[bytesPerLine];

      for(int y = infoHeader.iHeight - 1; y >= 0; --y) {
         int x;
         for(x = 0; x < bytesPerLine; ++x) {
            line[x] = lis.readUnsignedByte();
         }

         for(x = 0; x < infoHeader.iWidth; ++x) {
            int i = x / 8;
            int v = line[i];
            int b = x % 8;
            int index = getBit(v, b);
            raster.setSample(x, y, 0, index);
         }
      }

      return img;
   }

   public static BufferedImage read4(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
      byte[] ar = new byte[colorTable.length];
      byte[] ag = new byte[colorTable.length];
      byte[] ab = new byte[colorTable.length];
      getColorTable(colorTable, ar, ag, ab);
      IndexColorModel icm = new IndexColorModel(4, infoHeader.iNumColors, ar, ag, ab);
      BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 12, icm);
      WritableRaster raster = img.getRaster();
      int bitsPerLine = infoHeader.iWidth * 4;
      if (bitsPerLine % 32 != 0) {
         bitsPerLine = (bitsPerLine / 32 + 1) * 32;
      }

      int bytesPerLine = bitsPerLine / 8;
      int[] line = new int[bytesPerLine];

      for(int y = infoHeader.iHeight - 1; y >= 0; --y) {
         int x;
         int b;
         for(x = 0; x < bytesPerLine; ++x) {
            b = lis.readUnsignedByte();
            line[x] = b;
         }

         for(x = 0; x < infoHeader.iWidth; ++x) {
            b = x / 2;
            int i = x % 2;
            int n = line[b];
            int index = getNibble(n, i);
            raster.setSample(x, y, 0, index);
         }
      }

      return img;
   }

   public static BufferedImage read8(InfoHeader infoHeader, LittleEndianInputStream lis, ColorEntry[] colorTable) throws IOException {
      byte[] ar = new byte[colorTable.length];
      byte[] ag = new byte[colorTable.length];
      byte[] ab = new byte[colorTable.length];
      getColorTable(colorTable, ar, ag, ab);
      IndexColorModel icm = new IndexColorModel(8, infoHeader.iNumColors, ar, ag, ab);
      BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 13, icm);
      WritableRaster raster = img.getRaster();
      int dataPerLine = infoHeader.iWidth;
      int bytesPerLine = dataPerLine;
      if (bytesPerLine % 4 != 0) {
         bytesPerLine = (bytesPerLine / 4 + 1) * 4;
      }

      int padBytesPerLine = bytesPerLine - dataPerLine;

      for(int y = infoHeader.iHeight - 1; y >= 0; --y) {
         for(int x = 0; x < infoHeader.iWidth; ++x) {
            int b = lis.readUnsignedByte();
            raster.setSample(x, y, 0, b);
         }

         lis.skip((long)padBytesPerLine);
      }

      return img;
   }

   public static BufferedImage read24(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
      BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 1);
      WritableRaster raster = img.getRaster();
      int dataPerLine = infoHeader.iWidth * 3;
      int bytesPerLine = dataPerLine;
      if (bytesPerLine % 4 != 0) {
         bytesPerLine = (bytesPerLine / 4 + 1) * 4;
      }

      int padBytesPerLine = bytesPerLine - dataPerLine;

      for(int y = infoHeader.iHeight - 1; y >= 0; --y) {
         for(int x = 0; x < infoHeader.iWidth; ++x) {
            int b = lis.readUnsignedByte();
            int g = lis.readUnsignedByte();
            int r = lis.readUnsignedByte();
            raster.setSample(x, y, 0, r);
            raster.setSample(x, y, 1, g);
            raster.setSample(x, y, 2, b);
         }

         lis.skip((long)padBytesPerLine);
      }

      return img;
   }

   public static BufferedImage read32(InfoHeader infoHeader, LittleEndianInputStream lis) throws IOException {
      BufferedImage img = new BufferedImage(infoHeader.iWidth, infoHeader.iHeight, 2);
      WritableRaster rgb = img.getRaster();
      WritableRaster alpha = img.getAlphaRaster();

      for(int y = infoHeader.iHeight - 1; y >= 0; --y) {
         for(int x = 0; x < infoHeader.iWidth; ++x) {
            int b = lis.readUnsignedByte();
            int g = lis.readUnsignedByte();
            int r = lis.readUnsignedByte();
            int a = lis.readUnsignedByte();
            rgb.setSample(x, y, 0, r);
            rgb.setSample(x, y, 1, g);
            rgb.setSample(x, y, 2, b);
            alpha.setSample(x, y, 0, a);
         }
      }

      return img;
   }

   public static BufferedImage read(File file) throws IOException {
      FileInputStream fin = new FileInputStream(file);

      BufferedImage var2;
      try {
         var2 = read((InputStream)(new BufferedInputStream(fin)));
      } finally {
         try {
            fin.close();
         } catch (IOException var9) {
         }

      }

      return var2;
   }

   public static BufferedImage read(InputStream in) throws IOException {
      BMPDecoder d = new BMPDecoder(in);
      return d.getBufferedImage();
   }

   public static BMPImage readExt(File file) throws IOException {
      FileInputStream fin = new FileInputStream(file);

      BMPImage var2;
      try {
         var2 = readExt((InputStream)(new BufferedInputStream(fin)));
      } finally {
         try {
            fin.close();
         } catch (IOException var9) {
         }

      }

      return var2;
   }

   public static BMPImage readExt(InputStream in) throws IOException {
      BMPDecoder d = new BMPDecoder(in);
      BMPImage ret = new BMPImage(d.getBufferedImage(), d.getInfoHeader());
      return ret;
   }
}
