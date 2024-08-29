package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class ITF14 extends BarcodeCommon implements IBarcode {
   private final String[] ITF14_Code = new String[]{"NNWWN", "WNNNW", "NWNNW", "WWNNN", "NNWNW", "WNWNN", "NWWNN", "NNNWW", "WNNWN", "NWNWN"};

   public ITF14(String input) {
      this.setRawData(input);
      this.checkDigit();
   }

   private String encodeITF14() {
      if (this.getRawData().length() > 14 || this.getRawData().length() < 13) {
         this.error("EITF14-1: Data length invalid. (Length must be 13 or 14)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EITF14-2: Numeric Data Only");
      }

      StringBuilder result = new StringBuilder("1010");

      for(int i = 0; i < this.getRawData().length(); i += 2) {
         boolean bars = true;
         String patternbars = this.ITF14_Code[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[i]))];
         String patternspaces = this.ITF14_Code[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[i + 1]))];

         StringBuilder patternmixed;
         for(patternmixed = new StringBuilder(); patternbars.length() > 0; patternspaces = patternspaces.substring(1)) {
            patternmixed.append(String.valueOf(patternbars.toCharArray()[0])).append(String.valueOf(patternspaces.toCharArray()[0]));
            patternbars = patternbars.substring(1);
         }

         char[] var7 = patternmixed.toString().toCharArray();
         int var8 = var7.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            char c1 = var7[var9];
            if (bars) {
               if (c1 == 'N') {
                  result.append("1");
               } else {
                  result.append("11");
               }
            } else if (c1 == 'N') {
               result.append("0");
            } else {
               result.append("00");
            }

            bars = !bars;
         }
      }

      result.append("1101");
      return result.toString();
   }

   private void checkDigit() {
      if (this.getRawData().length() == 13) {
         int total = 0;

         int cs;
         for(cs = 0; cs <= this.getRawData().length() - 1; ++cs) {
            int temp = Integer.parseInt(this.getRawData().substring(cs, cs + 1));
            total += temp * (cs != 0 && cs % 2 != 0 ? 1 : 3);
         }

         cs = total % 10;
         cs = 10 - cs;
         if (cs == 10) {
            cs = 0;
         }

         this.setRawData(this.getRawData() + cs);
      }

   }

   public String getEncodedValue() {
      return this.encodeITF14();
   }
}
