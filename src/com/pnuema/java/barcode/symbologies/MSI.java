package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.Barcode;
import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;

public class MSI extends BarcodeCommon implements IBarcode {
   private final String[] MSI_Code = new String[]{"100100100100", "100100100110", "100100110100", "100100110110", "100110100100", "100110100110", "100110110100", "100110110110", "110100100100", "110100100110"};
   private final Barcode.TYPE encodedType;

   public MSI(String input, Barcode.TYPE EncodedType) {
      this.encodedType = EncodedType;
      this.setRawData(input);
   }

   private String encodeMSI() {
      if (!checkNumericOnly(this.getRawData())) {
         this.error("EMSI-1: Numeric Data Only");
      }

      String PreEncoded = this.getRawData();
      String odds;
      String evens;
      int evensum;
      int oddsum;
      char[] var6;
      int checksum;
      int var8;
      Character c;
      if (this.encodedType == Barcode.TYPE.MSI_Mod10 || this.encodedType == Barcode.TYPE.MSI_2Mod10) {
         odds = "";
         evens = "";

         for(evensum = PreEncoded.length() - 1; evensum >= 0; evensum -= 2) {
            odds = PreEncoded.toCharArray()[evensum] + odds;
            if (evensum - 1 >= 0) {
               evens = PreEncoded.toCharArray()[evensum - 1] + evens;
            }
         }

         odds = String.valueOf(Integer.parseInt(odds) * 2);
         evensum = 0;
         oddsum = 0;
         var6 = evens.toCharArray();
         checksum = var6.length;

         for(var8 = 0; var8 < checksum; ++var8) {
            c = var6[var8];
            evensum += Integer.parseInt(c.toString());
         }

         var6 = odds.toCharArray();
         checksum = var6.length;

         for(var8 = 0; var8 < checksum; ++var8) {
            c = var6[var8];
            oddsum += Integer.parseInt(c.toString());
         }

         checksum = (oddsum + evensum) % 10;
         checksum = checksum == 0 ? 0 : 10 - checksum;
         PreEncoded = PreEncoded + String.valueOf(checksum);
      }

      if (this.encodedType == Barcode.TYPE.MSI_Mod11 || this.encodedType == Barcode.TYPE.MSI_Mod11_Mod10) {
         int sum = 0;
         int weight = 2;

         for(evensum = PreEncoded.length() - 1; evensum >= 0; --evensum) {
            if (weight > 7) {
               weight = 2;
            }

            sum += Integer.parseInt(String.valueOf(PreEncoded.toCharArray()[evensum])) * weight++;
         }

         evensum = sum % 11;
         oddsum = evensum == 0 ? 0 : 11 - evensum;
         PreEncoded = PreEncoded + String.valueOf(oddsum);
      }

      if (this.encodedType == Barcode.TYPE.MSI_2Mod10 || this.encodedType == Barcode.TYPE.MSI_Mod11_Mod10) {
         odds = "";
         evens = "";

         for(evensum = PreEncoded.length() - 1; evensum >= 0; evensum -= 2) {
            odds = PreEncoded.toCharArray()[evensum] + odds;
            if (evensum - 1 >= 0) {
               evens = PreEncoded.toCharArray()[evensum - 1] + evens;
            }
         }

         odds = String.valueOf(Integer.parseInt(odds) * 2);
         evensum = 0;
         oddsum = 0;
         var6 = evens.toCharArray();
         checksum = var6.length;

         for(var8 = 0; var8 < checksum; ++var8) {
            c = var6[var8];
            evensum += Integer.parseInt(c.toString());
         }

         var6 = odds.toCharArray();
         checksum = var6.length;

         for(var8 = 0; var8 < checksum; ++var8) {
            c = var6[var8];
            oddsum += Integer.parseInt(c.toString());
         }

         checksum = 10 - (oddsum + evensum) % 10;
         PreEncoded = PreEncoded + String.valueOf(checksum);
      }

      odds = "110";
      char[] var12 = PreEncoded.toCharArray();
      evensum = var12.length;

      for(oddsum = 0; oddsum < evensum; ++oddsum) {
         c = var12[oddsum];
         odds = odds + this.MSI_Code[Integer.parseInt(c.toString())];
      }

      odds = odds + "1001";
      return odds;
   }

   public String getEncodedValue() {
      return this.encodeMSI();
   }
}
