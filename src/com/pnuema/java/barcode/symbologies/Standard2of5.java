package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.Barcode;
import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import com.pnuema.java.barcode.utils.Utils2of5;

public class Standard2of5 extends BarcodeCommon implements IBarcode {
   private final String[] S25_Code = new String[]{"10101110111010", "11101010101110", "10111010101110", "11101110101010", "10101110101110", "11101011101010", "10111011101010", "10101011101110", "11101010111010", "10111010111010"};
   private final Barcode.TYPE type;

   public Standard2of5(String input, Barcode.TYPE encodingType) {
      this.setRawData(input);
      this.type = encodingType;
   }

   private String encodeStandard2Of5() {
      if (!checkNumericOnly(this.getRawData())) {
         this.error("ES25-1: Numeric Data Only");
      }

      StringBuilder result = new StringBuilder("11011010");
      String data = this.getRawData() + (this.type == Barcode.TYPE.Standard2of5_Mod10 ? Utils2of5.CalculateMod10CheckDigit(this.getRawData()) : "");
      char[] var3 = data.toCharArray();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char c = var3[var5];
         result.append(this.S25_Code[Integer.parseInt(String.valueOf(c))]);
      }

      result.append(this.type == Barcode.TYPE.Standard2of5_Mod10 ? this.S25_Code[Utils2of5.CalculateMod10CheckDigit(this.getRawData())] : "");
      result.append("1101011");
      return result.toString();
   }

   public String getEncodedValue() {
      return this.encodeStandard2Of5();
   }
}
