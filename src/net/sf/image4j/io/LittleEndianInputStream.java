package net.sf.image4j.io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class LittleEndianInputStream extends DataInputStream implements CountingDataInput {
   public LittleEndianInputStream(CountingInputStream in) {
      super(in);
   }

   public int getCount() {
      return ((CountingInputStream)this.in).getCount();
   }

   public int skip(int count, boolean strict) throws IOException {
      return IOUtils.skip(this, count, strict);
   }

   public short readShortLE() throws IOException {
      int b1 = this.read();
      int b2 = this.read();
      if (b1 >= 0 && b2 >= 0) {
         short ret = (short)((b2 << 8) + (b1 << 0));
         return ret;
      } else {
         throw new EOFException();
      }
   }

   public int readIntLE() throws IOException {
      int b1 = this.read();
      int b2 = this.read();
      int b3 = this.read();
      int b4 = this.read();
      if (b1 >= -1 && b2 >= -1 && b3 >= -1 && b4 >= -1) {
         int ret = (b4 << 24) + (b3 << 16) + (b2 << 8) + (b1 << 0);
         return ret;
      } else {
         throw new EOFException();
      }
   }

   public float readFloatLE() throws IOException {
      int i = this.readIntLE();
      float ret = Float.intBitsToFloat(i);
      return ret;
   }

   public long readLongLE() throws IOException {
      int i1 = this.readIntLE();
      int i2 = this.readIntLE();
      long ret = ((long)i1 << 32) + ((long)i2 & 4294967295L);
      return ret;
   }

   public double readDoubleLE() throws IOException {
      long l = this.readLongLE();
      double ret = Double.longBitsToDouble(l);
      return ret;
   }

   public long readUnsignedInt() throws IOException {
      long i1 = (long)this.readUnsignedByte();
      long i2 = (long)this.readUnsignedByte();
      long i3 = (long)this.readUnsignedByte();
      long i4 = (long)this.readUnsignedByte();
      long ret = i1 << 24 | i2 << 16 | i3 << 8 | i4;
      return ret;
   }

   public long readUnsignedIntLE() throws IOException {
      long i1 = (long)this.readUnsignedByte();
      long i2 = (long)this.readUnsignedByte();
      long i3 = (long)this.readUnsignedByte();
      long i4 = (long)this.readUnsignedByte();
      long ret = i4 << 24 | i3 << 16 | i2 << 8 | i1;
      return ret;
   }
}
