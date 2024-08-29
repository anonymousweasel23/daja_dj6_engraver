package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class ISBN extends BarcodeCommon implements IBarcode {
   public ISBN(String input) {
      this.setRawData(input);
   }

   private String encodeISBNBookland() {
      if (!checkNumericOnly(this.getRawData())) {
         this.error("EBOOKLANDISBN-1: Numeric Data Only");
      }

      String type = "UNKNOWN";
      if (this.getRawData().length() != 10 && this.getRawData().length() != 9) {
         if (this.getRawData().length() == 12 && this.getRawData().startsWith("978")) {
            type = "BOOKLAND-NOCHECKDIGIT";
         } else if (this.getRawData().length() == 13 && this.getRawData().startsWith("978")) {
            type = "BOOKLAND-CHECKDIGIT";
            this.setRawData(this.getRawData().substring(0, 12));
         }
      } else {
         if (this.getRawData().length() == 10) {
            this.setRawData(this.getRawData().substring(0, 9));
         }

         this.setRawData("978" + this.getRawData());
         type = "ISBN";
      }

      if ("UNKNOWN".equals(type)) {
         this.error("EBOOKLANDISBN-2: Invalid input.  Must start with 978 and be length must be 9, 10, 12, 13 characters.");
      }

      return (new EAN13(this.getRawData())).getEncodedValue();
   }

   public String getEncodedValue() {
      return this.encodeISBNBookland();
   }
}
