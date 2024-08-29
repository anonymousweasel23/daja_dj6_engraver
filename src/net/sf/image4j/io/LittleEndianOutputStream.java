package net.sf.image4j.io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class LittleEndianOutputStream extends DataOutputStream {
   public LittleEndianOutputStream(OutputStream out) {
      super(out);
   }

   public void writeShortLE(short value) throws IOException {
      value = EndianUtils.swapShort(value);
      super.writeShort(value);
   }

   public void writeIntLE(int value) throws IOException {
      value = EndianUtils.swapInteger(value);
      super.writeInt(value);
   }

   public void writeFloatLE(float value) throws IOException {
      value = EndianUtils.swapFloat(value);
      super.writeFloat(value);
   }

   public void writeLongLE(long value) throws IOException {
      value = EndianUtils.swapLong(value);
      super.writeLong(value);
   }

   public void writeDoubleLE(double value) throws IOException {
      value = EndianUtils.swapDouble(value);
      super.writeDouble(value);
   }

   public void writeUnsignedInt(long value) throws IOException {
      int i1 = (int)(value >> 24);
      int i2 = (int)(value >> 16 & 255L);
      int i3 = (int)(value >> 8 & 255L);
      int i4 = (int)(value & 255L);
      this.write(i1);
      this.write(i2);
      this.write(i3);
      this.write(i4);
   }

   public void writeUnsignedIntLE(long value) throws IOException {
      int i1 = (int)(value >> 24);
      int i2 = (int)(value >> 16 & 255L);
      int i3 = (int)(value >> 8 & 255L);
      int i4 = (int)(value & 255L);
      this.write(i4);
      this.write(i3);
      this.write(i2);
      this.write(i1);
   }
}
