package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class EAN8 extends BarcodeCommon implements IBarcode {
   private final String[] EAN_CodeA = new String[]{"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
   private final String[] EAN_CodeC = new String[]{"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};

   public EAN8(String input) {
      this.setRawData(input);
      this.calculateCheckDigit();
   }

   private String encodeEAN8() {
      if (this.getRawData().length() != 8 && this.getRawData().length() != 7) {
         this.error("EEAN8-1: Invalid data length. (7 or 8 numbers only)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EEAN8-2: Numeric Data Only");
      }

      StringBuilder result = new StringBuilder("101");

      int i;
      for(i = 0; i < this.getRawData().length() / 2; ++i) {
         result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[i]))]);
      }

      result.append("01010");

      for(i = this.getRawData().length() / 2; i < this.getRawData().length(); ++i) {
         result.append(this.EAN_CodeC[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[i]))]);
      }

      result.append("101");
      return result.toString();
   }

   private void calculateCheckDigit() {
      if (this.getRawData().length() == 7) {
         int even = 0;
         int odd = 0;

         int total;
         for(total = 0; total <= 6; total += 2) {
            odd += Integer.parseInt(this.getRawData().substring(total, total + 1)) * 3;
         }

         for(total = 1; total <= 5; total += 2) {
            even += Integer.parseInt(this.getRawData().substring(total, total + 1));
         }

         total = even + odd;
         int checksum = total % 10;
         checksum = 10 - checksum;
         if (checksum == 10) {
            checksum = 0;
         }

         this.setRawData(this.getRawData() + checksum);
      }

   }

   public String getEncodedValue() {
      return this.encodeEAN8();
   }
}
