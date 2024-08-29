package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class UPCE extends BarcodeCommon implements IBarcode {
   private final String[] EAN_CodeA = new String[]{"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
   private final String[] EAN_CodeB = new String[]{"0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111"};
   private final String[] UPCE_Code_0 = new String[]{"bbbaaa", "bbabaa", "bbaaba", "bbaaab", "babbaa", "baabba", "baaabb", "bababa", "babaab", "baabab"};
   private final String[] UPCE_Code_1 = new String[]{"aaabbb", "aababb", "aabbab", "aabbba", "abaabb", "abbaab", "abbbaa", "ababab", "ababba", "abbaba"};

   public UPCE(String input) {
      this.setRawData(input);
   }

   private String encodeUPCE() {
      if (this.getRawData().length() != 6 && this.getRawData().length() != 8 && this.getRawData().length() != 12) {
         this.error("EUPCE-1: Invalid data length. (8 or 12 numbers only)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EUPCE-2: Numeric Data Only");
      }

      int numberSystem = Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[0]));
      if (numberSystem != 0 && numberSystem != 1) {
         this.error("EUPCE-3: Invalid Number System (only 0 & 1 are valid)");
      }

      int checkDigit = Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[this.getRawData().length() - 1]));
      String UPCECode;
      if (this.getRawData().length() == 12) {
         UPCECode = "";
         String manufacturer = this.getRawData().substring(1, 6);
         String productCode = this.getRawData().substring(6, 11);
         if (manufacturer.endsWith("000") || manufacturer.endsWith("100") || manufacturer.endsWith("200") && Integer.parseInt(productCode) <= 999) {
            UPCECode = UPCECode + manufacturer.substring(0, 2);
            UPCECode = UPCECode + productCode.substring(2, 5);
            UPCECode = UPCECode + String.valueOf(manufacturer.toCharArray()[2]);
         } else if (manufacturer.endsWith("00") && Integer.parseInt(productCode) <= 99) {
            UPCECode = UPCECode + manufacturer.substring(0, 3);
            UPCECode = UPCECode + productCode.substring(3, 5);
            UPCECode = UPCECode + "3";
         } else if (manufacturer.endsWith("0") && Integer.parseInt(productCode) <= 9) {
            UPCECode = UPCECode + manufacturer.substring(0, 4);
            UPCECode = UPCECode + productCode.toCharArray()[4];
            UPCECode = UPCECode + "4";
         } else if (!manufacturer.endsWith("0") && Integer.parseInt(productCode) <= 9 && Integer.parseInt(productCode) >= 5) {
            UPCECode = UPCECode + manufacturer;
            UPCECode = UPCECode + productCode.toCharArray()[4];
         } else {
            this.error("EUPCE-4: Illegal UPC-A entered for conversion.  Unable to convert.");
         }

         this.setRawData(UPCECode);
      }

      if (numberSystem == 0) {
         UPCECode = this.UPCE_Code_0[checkDigit];
      } else {
         UPCECode = this.UPCE_Code_1[checkDigit];
      }

      StringBuilder result = new StringBuilder("101");
      int pos = 0;
      char[] var6 = UPCECode.toCharArray();
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         char c = var6[var8];
         int i = Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos++]));
         if (c == 'a') {
            result.append(this.EAN_CodeA[i]);
         } else if (c == 'b') {
            result.append(this.EAN_CodeB[i]);
         }
      }

      result.append("01010");
      result.append("1");
      return result.toString();
   }

   public String getEncodedValue() {
      return this.encodeUPCE();
   }
}
