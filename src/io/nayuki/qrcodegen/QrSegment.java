package io.nayuki.qrcodegen;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public final class QrSegment {
   public final Mode mode;
   public final int numChars;
   final BitBuffer data;
   public static final Pattern NUMERIC_REGEX = Pattern.compile("[0-9]*");
   public static final Pattern ALPHANUMERIC_REGEX = Pattern.compile("[A-Z0-9 $%*+./:-]*");
   static final String ALPHANUMERIC_CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:";

   public static QrSegment makeBytes(byte[] data) {
      Objects.requireNonNull(data);
      BitBuffer bb = new BitBuffer();
      byte[] var2 = data;
      int var3 = data.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         byte b = var2[var4];
         bb.appendBits(b & 255, 8);
      }

      return new QrSegment(QrSegment.Mode.BYTE, data.length, bb);
   }

   public static QrSegment makeNumeric(String digits) {
      Objects.requireNonNull(digits);
      if (!NUMERIC_REGEX.matcher(digits).matches()) {
         throw new IllegalArgumentException("String contains non-numeric characters");
      } else {
         BitBuffer bb = new BitBuffer();

         int n;
         for(int i = 0; i < digits.length(); i += n) {
            n = Math.min(digits.length() - i, 3);
            bb.appendBits(Integer.parseInt(digits.substring(i, i + n)), n * 3 + 1);
         }

         return new QrSegment(QrSegment.Mode.NUMERIC, digits.length(), bb);
      }
   }

   public static QrSegment makeAlphanumeric(String text) {
      Objects.requireNonNull(text);
      if (!ALPHANUMERIC_REGEX.matcher(text).matches()) {
         throw new IllegalArgumentException("String contains unencodable characters in alphanumeric mode");
      } else {
         BitBuffer bb = new BitBuffer();

         int i;
         for(i = 0; i <= text.length() - 2; i += 2) {
            int temp = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".indexOf(text.charAt(i)) * 45;
            temp += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".indexOf(text.charAt(i + 1));
            bb.appendBits(temp, 11);
         }

         if (i < text.length()) {
            bb.appendBits("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".indexOf(text.charAt(i)), 6);
         }

         return new QrSegment(QrSegment.Mode.ALPHANUMERIC, text.length(), bb);
      }
   }

   public static List makeSegments(String text) {
      Objects.requireNonNull(text);
      List result = new ArrayList();
      if (!text.equals("")) {
         if (NUMERIC_REGEX.matcher(text).matches()) {
            result.add(makeNumeric(text));
         } else if (ALPHANUMERIC_REGEX.matcher(text).matches()) {
            result.add(makeAlphanumeric(text));
         } else {
            result.add(makeBytes(text.getBytes(StandardCharsets.UTF_8)));
         }
      }

      return result;
   }

   public static QrSegment makeEci(int assignVal) {
      BitBuffer bb = new BitBuffer();
      if (assignVal < 0) {
         throw new IllegalArgumentException("ECI assignment value out of range");
      } else {
         if (assignVal < 128) {
            bb.appendBits(assignVal, 8);
         } else if (assignVal < 16384) {
            bb.appendBits(2, 2);
            bb.appendBits(assignVal, 14);
         } else {
            if (assignVal >= 1000000) {
               throw new IllegalArgumentException("ECI assignment value out of range");
            }

            bb.appendBits(6, 3);
            bb.appendBits(assignVal, 21);
         }

         return new QrSegment(QrSegment.Mode.ECI, 0, bb);
      }
   }

   public QrSegment(Mode md, int numCh, BitBuffer data) {
      this.mode = (Mode)Objects.requireNonNull(md);
      Objects.requireNonNull(data);
      if (numCh < 0) {
         throw new IllegalArgumentException("Invalid value");
      } else {
         this.numChars = numCh;
         this.data = data.clone();
      }
   }

   public BitBuffer getData() {
      return this.data.clone();
   }

   static int getTotalBits(List segs, int version) {
      Objects.requireNonNull(segs);
      long result = 0L;
      Iterator var4 = segs.iterator();

      do {
         if (!var4.hasNext()) {
            return (int)result;
         }

         QrSegment seg = (QrSegment)var4.next();
         Objects.requireNonNull(seg);
         int ccbits = seg.mode.numCharCountBits(version);
         if (seg.numChars >= 1 << ccbits) {
            return -1;
         }

         result += 4L + (long)ccbits + (long)seg.data.bitLength();
      } while(result <= 2147483647L);

      return -1;
   }

   public static enum Mode {
      NUMERIC(1, new int[]{10, 12, 14}),
      ALPHANUMERIC(2, new int[]{9, 11, 13}),
      BYTE(4, new int[]{8, 16, 16}),
      KANJI(8, new int[]{8, 10, 12}),
      ECI(7, new int[]{0, 0, 0});

      final int modeBits;
      private final int[] numBitsCharCount;

      private Mode(int mode, int... ccbits) {
         this.modeBits = mode;
         this.numBitsCharCount = ccbits;
      }

      int numCharCountBits(int ver) {
         assert 1 <= ver && ver <= 40;

         return this.numBitsCharCount[(ver + 7) / 17];
      }
   }
}
