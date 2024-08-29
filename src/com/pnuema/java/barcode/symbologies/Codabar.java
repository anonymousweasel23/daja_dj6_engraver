package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import java.util.HashMap;
import java.util.Iterator;

public class Codabar extends BarcodeCommon implements IBarcode {
   private final HashMap codabarCode = new HashMap();

   public Codabar(String input) {
      this.setRawData(input);
   }

   private String encodeCodabar() {
      if (this.getRawData().length() < 2) {
         this.error("ECODABAR-1: Data format invalid. (Invalid length)");
      }

      switch (String.valueOf(this.getRawData().charAt(0)).toUpperCase().trim()) {
         default:
            this.error("ECODABAR-2: Data format invalid. (Invalid START character)");
         case "A":
         case "B":
         case "C":
         case "D":
            switch (String.valueOf(this.getRawData().charAt(this.getRawData().trim().length() - 1)).trim().toUpperCase()) {
               default:
                  this.error("ECODABAR-3: Data format invalid. (Invalid STOP character)");
               case "A":
               case "B":
               case "C":
               case "D":
                  this.initCodabar();
                  String temp = this.getRawData();
                  Iterator var8 = this.codabarCode.keySet().iterator();

                  while(var8.hasNext()) {
                     char c = (Character)var8.next();
                     if (!checkNumericOnly(String.valueOf(c))) {
                        temp = temp.replace(c, '1');
                     }
                  }

                  if (!checkNumericOnly(temp)) {
                     this.error("ECODABAR-4: Data contains invalid  characters.");
                  }

                  StringBuilder result = new StringBuilder();
                  char[] var7 = this.getRawData().toCharArray();
                  int var4 = var7.length;

                  for(int var5 = 0; var5 < var4; ++var5) {
                     char c = var7[var5];
                     result.append((String)this.codabarCode.get(c));
                     result.append("0");
                  }

                  result.deleteCharAt(result.length() - 1);
                  this.codabarCode.clear();
                  this.setRawData(this.getRawData().trim().substring(1, this.getRawData().trim().length() - 2));
                  return result.toString();
            }
      }
   }

   private void initCodabar() {
      this.codabarCode.clear();
      this.codabarCode.put('0', "101010011");
      this.codabarCode.put('1', "101011001");
      this.codabarCode.put('2', "101001011");
      this.codabarCode.put('3', "110010101");
      this.codabarCode.put('4', "101101001");
      this.codabarCode.put('5', "110101001");
      this.codabarCode.put('6', "100101011");
      this.codabarCode.put('7', "100101101");
      this.codabarCode.put('8', "100110101");
      this.codabarCode.put('9', "110100101");
      this.codabarCode.put('-', "101001101");
      this.codabarCode.put('$', "101100101");
      this.codabarCode.put(':', "1101011011");
      this.codabarCode.put('/', "1101101011");
      this.codabarCode.put('.', "1101101101");
      this.codabarCode.put('+', "101100110011");
      this.codabarCode.put('A', "1011001001");
      this.codabarCode.put('B', "1010010011");
      this.codabarCode.put('C', "1001001011");
      this.codabarCode.put('D', "1010011001");
      this.codabarCode.put('a', "1011001001");
      this.codabarCode.put('b', "1010010011");
      this.codabarCode.put('c', "1001001011");
      this.codabarCode.put('d', "1010011001");
   }

   public String getEncodedValue() {
      return this.encodeCodabar();
   }
}
