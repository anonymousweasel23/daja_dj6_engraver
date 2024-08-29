package io.nayuki.qrcodegen;

import java.util.BitSet;
import java.util.Objects;

public final class BitBuffer implements Cloneable {
   private BitSet data = new BitSet();
   private int bitLength = 0;

   public int bitLength() {
      assert this.bitLength >= 0;

      return this.bitLength;
   }

   public int getBit(int index) {
      if (index >= 0 && index < this.bitLength) {
         return this.data.get(index) ? 1 : 0;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   public void appendBits(int val, int len) {
      if (len >= 0 && len <= 31 && val >>> len == 0) {
         if (Integer.MAX_VALUE - this.bitLength < len) {
            throw new IllegalStateException("Maximum length reached");
         } else {
            for(int i = len - 1; i >= 0; ++this.bitLength) {
               this.data.set(this.bitLength, QrCode.getBit(val, i));
               --i;
            }

         }
      } else {
         throw new IllegalArgumentException("Value out of range");
      }
   }

   public void appendData(BitBuffer bb) {
      Objects.requireNonNull(bb);
      if (Integer.MAX_VALUE - this.bitLength < bb.bitLength) {
         throw new IllegalStateException("Maximum length reached");
      } else {
         for(int i = 0; i < bb.bitLength; ++this.bitLength) {
            this.data.set(this.bitLength, bb.data.get(i));
            ++i;
         }

      }
   }

   public BitBuffer clone() {
      try {
         BitBuffer result = (BitBuffer)super.clone();
         result.data = (BitSet)result.data.clone();
         return result;
      } catch (CloneNotSupportedException var2) {
         CloneNotSupportedException e = var2;
         throw new AssertionError(e);
      }
   }
}
