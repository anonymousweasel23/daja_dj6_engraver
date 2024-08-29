package net.sf.image4j.codec.ico;

import java.io.IOException;
import net.sf.image4j.io.LittleEndianInputStream;
import net.sf.image4j.io.LittleEndianOutputStream;

public class IconEntry {
   public int bWidth;
   public int bHeight;
   public int bColorCount;
   public byte bReserved;
   public short sPlanes;
   public short sBitCount;
   public int iSizeInBytes;
   public int iFileOffset;

   public IconEntry(LittleEndianInputStream in) throws IOException {
      this.bWidth = in.readUnsignedByte();
      this.bHeight = in.readUnsignedByte();
      this.bColorCount = in.readUnsignedByte();
      this.bReserved = in.readByte();
      this.sPlanes = in.readShortLE();
      this.sBitCount = in.readShortLE();
      this.iSizeInBytes = in.readIntLE();
      this.iFileOffset = in.readIntLE();
   }

   public IconEntry() {
      this.bWidth = 0;
      this.bHeight = 0;
      this.bColorCount = 0;
      this.sPlanes = 1;
      this.bReserved = 0;
      this.sBitCount = 0;
      this.iSizeInBytes = 0;
      this.iFileOffset = 0;
   }

   public String toString() {
      StringBuffer sb = new StringBuffer();
      sb.append("width=");
      sb.append(this.bWidth);
      sb.append(",height=");
      sb.append(this.bHeight);
      sb.append(",bitCount=");
      sb.append(this.sBitCount);
      sb.append(",colorCount=" + this.bColorCount);
      return sb.toString();
   }

   public void write(LittleEndianOutputStream out) throws IOException {
      out.writeByte(this.bWidth);
      out.writeByte(this.bHeight);
      out.writeByte(this.bColorCount);
      out.writeByte(this.bReserved);
      out.writeShortLE(this.sPlanes);
      out.writeShortLE(this.sBitCount);
      out.writeIntLE(this.iSizeInBytes);
      out.writeIntLE(this.iFileOffset);
   }
}
