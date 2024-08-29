package net.sf.image4j.codec.bmp;

import java.io.IOException;
import net.sf.image4j.io.LittleEndianInputStream;
import net.sf.image4j.io.LittleEndianOutputStream;

public class InfoHeader {
   public int iSize;
   public int iWidth;
   public int iHeight;
   public short sPlanes;
   public short sBitCount;
   public int iCompression;
   public int iImageSize;
   public int iXpixelsPerM;
   public int iYpixelsPerM;
   public int iColorsUsed;
   public int iColorsImportant;
   public int iNumColors;

   public InfoHeader(LittleEndianInputStream in) throws IOException {
      this.iSize = in.readIntLE();
      this.init(in, this.iSize);
   }

   public InfoHeader(LittleEndianInputStream in, int infoSize) throws IOException {
      this.init(in, infoSize);
   }

   protected void init(LittleEndianInputStream in, int infoSize) throws IOException {
      this.iSize = infoSize;
      this.iWidth = in.readIntLE();
      this.iHeight = in.readIntLE();
      this.sPlanes = in.readShortLE();
      this.sBitCount = in.readShortLE();
      this.iNumColors = (int)Math.pow(2.0, (double)this.sBitCount);
      this.iCompression = in.readIntLE();
      this.iImageSize = in.readIntLE();
      this.iXpixelsPerM = in.readIntLE();
      this.iYpixelsPerM = in.readIntLE();
      this.iColorsUsed = in.readIntLE();
      this.iColorsImportant = in.readIntLE();
   }

   public InfoHeader() {
      this.iSize = 40;
      this.iWidth = 0;
      this.iHeight = 0;
      this.sPlanes = 1;
      this.sBitCount = 0;
      this.iNumColors = 0;
      this.iCompression = 0;
      this.iImageSize = 0;
      this.iXpixelsPerM = 0;
      this.iYpixelsPerM = 0;
      this.iColorsUsed = 0;
      this.iColorsImportant = 0;
   }

   public InfoHeader(InfoHeader source) {
      this.iColorsImportant = source.iColorsImportant;
      this.iColorsUsed = source.iColorsUsed;
      this.iCompression = source.iCompression;
      this.iHeight = source.iHeight;
      this.iWidth = source.iWidth;
      this.iImageSize = source.iImageSize;
      this.iNumColors = source.iNumColors;
      this.iSize = source.iSize;
      this.iXpixelsPerM = source.iXpixelsPerM;
      this.iYpixelsPerM = source.iYpixelsPerM;
      this.sBitCount = source.sBitCount;
      this.sPlanes = source.sPlanes;
   }

   public void write(LittleEndianOutputStream out) throws IOException {
      out.writeIntLE(this.iSize);
      out.writeIntLE(this.iWidth);
      out.writeIntLE(this.iHeight);
      out.writeShortLE(this.sPlanes);
      out.writeShortLE(this.sBitCount);
      out.writeIntLE(this.iCompression);
      out.writeIntLE(this.iImageSize);
      out.writeIntLE(this.iXpixelsPerM);
      out.writeIntLE(this.iYpixelsPerM);
      out.writeIntLE(this.iColorsUsed);
      out.writeIntLE(this.iColorsImportant);
   }
}
