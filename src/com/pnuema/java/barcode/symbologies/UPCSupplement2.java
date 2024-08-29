package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class UPCSupplement2 extends BarcodeCommon implements IBarcode {
   private final String[] EAN_CodeA = new String[]{"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
   private final String[] EAN_CodeB = new String[]{"0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111"};
   private final String[] UPC_SUPP_2 = new String[]{"aa", "ab", "ba", "bb"};

   public UPCSupplement2(String input) {
      this.setRawData(input);
   }

   private String encodeUPCSupplemental2() {
      if (this.getRawData().length() != 2) {
         this.error("EUPC-SUP2-1: Invalid data length. (Length = 2 required)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EUPC-SUP2-2: Numeric Data Only");
      }

      String pattern = "";

      try {
         pattern = this.UPC_SUPP_2[Integer.parseInt(this.getRawData().trim()) % 4];
      } catch (Exception var8) {
         this.error("EUPC-SUP2-3: Invalid Data. (Numeric only)");
      }

      StringBuilder result = new StringBuilder("1011");
      int pos = 0;
      char[] var4 = pattern.toCharArray();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         char c = var4[var6];
         if (c == 'a') {
            result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos]))]);
         } else if (c == 'b') {
            result.append(this.EAN_CodeB[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos]))]);
         }

         if (pos++ == 0) {
            result.append("01");
         }
      }

      return result.toString();
   }

   public String getEncodedValue() {
      return this.encodeUPCSupplemental2();
   }
}
