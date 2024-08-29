package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class JAN13 extends BarcodeCommon implements IBarcode {
   public JAN13(String input) {
      this.setRawData(input);
   }

   private String encodeJAN13() {
      if (!this.getRawData().startsWith("49")) {
         this.error("EJAN13-1: Invalid Country Code for JAN13 (49 required)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EJAN13-2: Numeric Data Only");
      }

      return (new EAN13(this.getRawData())).getEncodedValue();
   }

   public String getEncodedValue() {
      return this.encodeJAN13();
   }
}
