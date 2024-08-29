package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class Code11 extends BarcodeCommon implements IBarcode {
   private final String[] C11_Code = new String[]{"101011", "1101011", "1001011", "1100101", "1011011", "1101101", "1001101", "1010011", "1101001", "110101", "101101", "1011001"};

   public Code11(String input) {
      this.setRawData(input);
   }

   private String encodeCode11() {
      if (!checkNumericOnly(this.getRawData().replace("-", ""))) {
         this.error("EC11-1: Numeric data and '-' Only");
      }

      int weight = 1;
      int CTotal = 0;
      String dataToEncodeWithChecksums = this.getRawData();

      int i;
      for(i = this.getRawData().length() - 1; i >= 0; --i) {
         if (weight == 10) {
            weight = 1;
         }

         if (this.getRawData().charAt(i) != '-') {
            CTotal += Integer.parseInt(String.valueOf(this.getRawData().charAt(i))) * weight++;
         } else {
            CTotal += 10 * weight++;
         }
      }

      i = CTotal % 11;
      dataToEncodeWithChecksums = dataToEncodeWithChecksums + String.valueOf(i);
      if (this.getRawData().length() >= 10) {
         weight = 1;
         int KTotal = 0;

         for(int j = dataToEncodeWithChecksums.length() - 1; j >= 0; --j) {
            if (weight == 9) {
               weight = 1;
            }

            if (dataToEncodeWithChecksums.charAt(j) != '-') {
               KTotal += Integer.parseInt(String.valueOf(dataToEncodeWithChecksums.charAt(j))) * weight++;
            } else {
               KTotal += 10 * weight++;
            }
         }

         i = KTotal % 9;
         dataToEncodeWithChecksums = dataToEncodeWithChecksums + i;
      }

      String space = "0";
      StringBuilder builder = new StringBuilder();
      builder.append(this.C11_Code[11]);
      builder.append(space);
      char[] var7 = dataToEncodeWithChecksums.toCharArray();
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         char c = var7[var9];
         int index = c == '-' ? 10 : Integer.parseInt(String.valueOf(c));
         builder.append(this.C11_Code[index]);
         builder.append(space);
      }

      builder.append(this.C11_Code[11]);
      return builder.toString();
   }

   public String getEncodedValue() {
      return this.encodeCode11();
   }
}
