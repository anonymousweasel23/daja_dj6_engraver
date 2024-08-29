package net.sf.image4j.io;

public class EndianUtils {
   public static short swapShort(short value) {
      return (short)((value & '\uff00') >> 8 | (value & 255) << 8);
   }

   public static int swapInteger(int value) {
      return (value & -16777216) >> 24 | (value & 16711680) >> 8 | (value & '\uff00') << 8 | (value & 255) << 24;
   }

   public static long swapLong(long value) {
      return (value & -72057594037927936L) >> 56 | (value & 71776119061217280L) >> 40 | (value & 280375465082880L) >> 24 | (value & 1095216660480L) >> 8 | (value & 4278190080L) << 8 | (value & 16711680L) << 24 | (value & 65280L) << 40 | (value & 255L) << 56;
   }

   public static float swapFloat(float value) {
      int i = Float.floatToIntBits(value);
      i = swapInteger(i);
      return Float.intBitsToFloat(i);
   }

   public static double swapDouble(double value) {
      long l = Double.doubleToLongBits(value);
      l = swapLong(l);
      return Double.longBitsToDouble(l);
   }

   public static String toHexString(int i, boolean littleEndian, int bytes) {
      if (littleEndian) {
         i = swapInteger(i);
      }

      StringBuilder sb = new StringBuilder();
      sb.append(Integer.toHexString(i));
      if (sb.length() % 2 != 0) {
         sb.insert(0, '0');
      }

      while(sb.length() < bytes * 2) {
         sb.insert(0, "00");
      }

      return sb.toString();
   }

   public static StringBuilder toCharString(StringBuilder sb, int i, int bytes, char def) {
      int shift = 24;

      for(int j = 0; j < bytes; ++j) {
         int b = i >> shift & 255;
         char c = b < 32 ? def : (char)b;
         sb.append(c);
         shift -= 8;
      }

      return sb;
   }

   public static String toInfoString(int info) {
      StringBuilder sb = new StringBuilder();
      sb.append("Decimal: ").append(info);
      sb.append(", Hex BE: ").append(toHexString(info, false, 4));
      sb.append(", Hex LE: ").append(toHexString(info, true, 4));
      sb.append(", String BE: [");
      sb = toCharString(sb, info, 4, '.');
      sb.append(']');
      sb.append(", String LE: [");
      sb = toCharString(sb, swapInteger(info), 4, '.');
      sb.append(']');
      return sb.toString();
   }
}
