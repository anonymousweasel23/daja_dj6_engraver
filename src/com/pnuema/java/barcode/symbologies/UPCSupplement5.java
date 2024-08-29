package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class UPCSupplement5 extends BarcodeCommon implements IBarcode {
   private final String[] EAN_CodeA = new String[]{"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
   private final String[] EAN_CodeB = new String[]{"0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111"};
   private final String[] UPC_SUPP_5 = new String[]{"bbaaa", "babaa", "baaba", "baaab", "abbaa", "aabba", "aaabb", "ababa", "abaab", "aabab"};

   public UPCSupplement5(String input) {
      this.setRawData(input);
   }

   private String encodeUPCSupplemental5() {
      if (this.getRawData().length() != 5) {
         this.error("EUPC-SUP5-1: Invalid data length. (Length = 5 required)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EUPC-SUP5-2: Numeric Data Only");
      }

      int even = 0;
      int odd = 0;

      int total;
      for(total = 0; total <= 4; total += 2) {
         odd += Integer.parseInt(this.getRawData().substring(total, total + 1)) * 3;
      }

      for(total = 1; total < 4; total += 2) {
         even += Integer.parseInt(this.getRawData().substring(total, total + 1)) * 9;
      }

      total = even + odd;
      int cs = total % 10;
      String pattern = this.UPC_SUPP_5[cs];
      StringBuilder result = new StringBuilder();
      int pos = 0;
      char[] var8 = pattern.toCharArray();
      int var9 = var8.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         char c = var8[var10];
         if (pos == 0) {
            result.append("1011");
         } else {
            result.append("01");
         }

         if (c == 'a') {
            result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos]))]);
         } else if (c == 'b') {
            result.append(this.EAN_CodeB[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos]))]);
         }

         ++pos;
      }

      return result.toString();
   }

   public String getEncodedValue() {
      return this.encodeUPCSupplemental5();
   }
}
