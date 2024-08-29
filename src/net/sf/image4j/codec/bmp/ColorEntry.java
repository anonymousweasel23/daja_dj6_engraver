package net.sf.image4j.codec.bmp;

import java.io.IOException;
import net.sf.image4j.io.LittleEndianInputStream;

public class ColorEntry {
   public int bRed;
   public int bGreen;
   public int bBlue;
   public int bReserved;

   public ColorEntry(LittleEndianInputStream in) throws IOException {
      this.bBlue = in.readUnsignedByte();
      this.bGreen = in.readUnsignedByte();
      this.bRed = in.readUnsignedByte();
      this.bReserved = in.readUnsignedByte();
   }

   public ColorEntry() {
      this.bBlue = 0;
      this.bGreen = 0;
      this.bRed = 0;
      this.bReserved = 0;
   }

   public ColorEntry(int r, int g, int b, int a) {
      this.bBlue = b;
      this.bGreen = g;
      this.bRed = r;
      this.bReserved = a;
   }
}
