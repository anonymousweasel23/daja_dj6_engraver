package com.pnuema.java.barcode;

import java.util.List;

public interface IBarcode {
   String getEncodedValue();

   String getRawData();

   List getErrors();

   void clearErrors();
}
