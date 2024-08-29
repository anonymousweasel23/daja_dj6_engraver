package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class FIM extends BarcodeCommon implements IBarcode {
   public FIM(String input) {
      input = input.trim();
      String[] FIM_Codes = new String[]{"110010011", "101101101", "110101011", "111010111", "101000101"};
      switch (input) {
         case "A":
         case "a":
            this.setRawData(FIM_Codes[FIM.FIMTypes.FIM_A.ordinal()]);
            break;
         case "B":
         case "b":
            this.setRawData(FIM_Codes[FIM.FIMTypes.FIM_B.ordinal()]);
            break;
         case "C":
         case "c":
            this.setRawData(FIM_Codes[FIM.FIMTypes.FIM_C.ordinal()]);
            break;
         case "D":
         case "d":
            this.setRawData(FIM_Codes[FIM.FIMTypes.FIM_D.ordinal()]);
            break;
         case "E":
         case "e":
            this.setRawData(FIM_Codes[FIM.FIMTypes.FIM_E.ordinal()]);
            break;
         default:
            this.error("EFIM-1: Could not determine encoding type. (Only pass in A, B, C, D, or E)");
      }

   }

   private String encodeFIM() {
      StringBuilder encoded = new StringBuilder();
      char[] var2 = this.getRawData().toCharArray();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         char c = var2[var4];
         encoded.append(c).append("0");
      }

      encoded = new StringBuilder(encoded.substring(0, encoded.length() - 1));
      return encoded.toString();
   }

   public String getEncodedValue() {
      return this.encodeFIM();
   }

   public static enum FIMTypes {
      FIM_A,
      FIM_B,
      FIM_C,
      FIM_D,
      FIM_E;
   }
}
