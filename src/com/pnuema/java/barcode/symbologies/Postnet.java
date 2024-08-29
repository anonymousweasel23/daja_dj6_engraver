package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class Postnet extends BarcodeCommon implements IBarcode {
   private final String[] POSTNET_Code = new String[]{"11000", "00011", "00101", "00110", "01001", "01010", "01100", "10001", "10010", "10100"};

   public Postnet(String input) {
      this.setRawData(input);
   }

   private String encodePostnet() {
      this.setRawData(this.getRawData().replace("-", ""));
      if (!checkNumericOnly(this.getRawData())) {
         this.error("EPOSTNET-1: Numeric Data Only");
         return "";
      } else {
         switch (this.getRawData().length()) {
            case 7:
            case 8:
            case 10:
            default:
               this.error("EPOSTNET-2: Invalid data length. (5, 6, 9, or 11 digits only)");
            case 5:
            case 6:
            case 9:
            case 11:
               StringBuilder result = new StringBuilder("1");
               int checkdigitsum = 0;
               char[] var3 = this.getRawData().toCharArray();
               int checkdigit = var3.length;

               for(int var5 = 0; var5 < checkdigit; ++var5) {
                  char c = var3[var5];
                  int index = Integer.parseInt(String.valueOf(c));
                  result.append(this.POSTNET_Code[index]);
                  checkdigitsum += index;
               }

               int temp = checkdigitsum % 10;
               checkdigit = 10 - (temp == 0 ? 10 : temp);
               result.append(this.POSTNET_Code[checkdigit]);
               result.append("1");
               return result.toString();
         }
      }
   }

   public String getEncodedValue() {
      return this.encodePostnet();
   }
}
