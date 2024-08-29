package com.pnuema.java.barcode;

import java.util.ArrayList;
import java.util.List;

public abstract class BarcodeCommon {
   private String rawData = "";
   private List errors = new ArrayList();

   protected void setRawData(String rawData) {
      this.rawData = rawData;
   }

   public String getRawData() {
      return this.rawData;
   }

   public List getErrors() {
      return this.errors;
   }

   public void clearErrors() {
      this.errors.clear();
   }

   protected void error(String ErrorMessage) {
      this.errors.add(ErrorMessage);
      throw new RuntimeException(ErrorMessage);
   }

   protected static boolean checkNumericOnly(String data) {
      return data.matches("^\\d+$");
   }
}
