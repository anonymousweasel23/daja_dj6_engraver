package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.Barcode;
import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import com.pnuema.java.barcode.utils.Utils2of5;

public class Interleaved2of5 extends BarcodeCommon implements IBarcode {
   private final String[] I25_Code = new String[]{"NNWWN", "WNNNW", "NWNNW", "WWNNN", "NNWNW", "WNWNN", "NWWNN", "NNNWW", "WNNWN", "NWNWN"};
   private final Barcode.TYPE type;

   public Interleaved2of5(String input, Barcode.TYPE encodingType) {
      this.setRawData(input);
      this.type = encodingType;
   }

   private String encodeInterleaved2Of5() {
      if (this.getRawData().length() % 2 != (this.type == Barcode.TYPE.Interleaved2of5_Mod10 ? 1 : 0)) {
         this.error("EI25-1: Data length invalid.");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EI25-2: Numeric Data Only");
      }

      StringBuilder result = new StringBuilder("1010");
      String data = this.getRawData() + (this.type == Barcode.TYPE.Interleaved2of5_Mod10 ? Utils2of5.CalculateMod10CheckDigit(this.getRawData()) : "");

      for(int i = 0; i < data.length(); i += 2) {
         boolean bars = true;
         String patternbars = this.I25_Code[Integer.parseInt(String.valueOf(data.charAt(i)))];
         String patternspaces = this.I25_Code[Integer.parseInt(String.valueOf(data.charAt(i + 1)))];

         StringBuilder patternmixed;
         for(patternmixed = new StringBuilder(); !patternbars.isEmpty(); patternspaces = patternspaces.substring(1)) {
            patternmixed.append(patternbars.charAt(0)).append(patternspaces.toCharArray()[0]);
            patternbars = patternbars.substring(1);
         }

         char[] var8 = patternmixed.toString().toCharArray();
         int var9 = var8.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            char c1 = var8[var10];
            if (bars) {
               result.append(c1 == 'N' ? "1" : "11");
            } else {
               result.append(c1 == 'N' ? "0" : "00");
            }

            bars = !bars;
         }
      }

      result.append("1101");
      return result.toString();
   }

   public String getEncodedValue() {
      return this.encodeInterleaved2Of5();
   }
}
