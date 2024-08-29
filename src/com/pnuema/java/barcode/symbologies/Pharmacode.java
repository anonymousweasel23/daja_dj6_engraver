package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class Pharmacode extends BarcodeCommon implements IBarcode {
   public Pharmacode(String input) {
      this.setRawData(input);
      if (this.getRawData().length() > 6) {
         this.error("EPHARM-2: Data too long (invalid data input length).");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EPHARM-1: Numeric Data Only");
      }

   }

   private String encodePharmacode() {
      if (!this.getErrors().isEmpty()) {
         return "";
      } else {
         int num = Integer.parseInt(this.getRawData());
         if (num < 3 || num > 131070) {
            this.error("EPHARM-4: Data contains invalid  characters (invalid numeric range).");
         }

         String thickBar = "111";
         String thinBar = "1";
         String gap = "00";
         StringBuilder result = new StringBuilder();

         do {
            if ((num & 1) == 0) {
               result.insert(0, thickBar);
               num = (num - 2) / 2;
            } else {
               result.insert(0, thinBar);
               num = (num - 1) / 2;
            }

            if (num != 0) {
               result.insert(0, gap);
            }
         } while(num != 0);

         return result.toString();
      }
   }

   public String getEncodedValue() {
      return this.encodePharmacode();
   }
}
