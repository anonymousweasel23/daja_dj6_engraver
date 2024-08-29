package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import java.util.HashMap;

public class UPCA extends BarcodeCommon implements IBarcode {
   private final String[] UPC_CodeA = new String[]{"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
   private final String[] UPC_CodeB = new String[]{"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};
   private String countryAssigningManufacturerCode = "N/A";
   private final HashMap countryCodes = new HashMap();

   public UPCA(String input) {
      this.setRawData(input);
   }

   private String encodeUPCA() {
      if (this.getRawData().length() != 11 && this.getRawData().length() != 12) {
         this.error("EUPCA-1: Data length invalid. (Length must be 11 or 12)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EUPCA-2: Numeric Data Only");
      }

      this.calculateCheckDigit();
      StringBuilder result = new StringBuilder("101");
      result.append(this.UPC_CodeA[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[0]))]);

      int pos;
      for(pos = 0; pos < 5; ++pos) {
         result.append(this.UPC_CodeA[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos + 1]))]);
      }

      result.append("01010");
      pos = 0;

      while(pos < 5) {
         result.append(this.UPC_CodeB[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos++ + 6]))]);
      }

      result.append(this.UPC_CodeB[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[this.getRawData().length() - 1]))]);
      result.append("101");
      this.init_CountryCodes();
      String twodigitCode = "0" + this.getRawData().charAt(0);

      try {
         this.countryAssigningManufacturerCode = (String)this.countryCodes.get(twodigitCode);
      } catch (Exception var8) {
         this.error("EUPCA-3: Country assigning manufacturer code not found.");
      } finally {
         this.countryCodes.clear();
      }

      return result.toString();
   }

   private void init_CountryCodes() {
      this.countryCodes.clear();
      this.countryCodes.put("00", "US / CANADA");
      this.countryCodes.put("01", "US / CANADA");
      this.countryCodes.put("02", "US / CANADA");
      this.countryCodes.put("03", "US / CANADA");
      this.countryCodes.put("04", "US / CANADA");
      this.countryCodes.put("05", "US / CANADA");
      this.countryCodes.put("06", "US / CANADA");
      this.countryCodes.put("07", "US / CANADA");
      this.countryCodes.put("08", "US / CANADA");
      this.countryCodes.put("09", "US / CANADA");
      this.countryCodes.put("10", "US / CANADA");
      this.countryCodes.put("11", "US / CANADA");
      this.countryCodes.put("12", "US / CANADA");
      this.countryCodes.put("13", "US / CANADA");
      this.countryCodes.put("20", "IN STORE");
      this.countryCodes.put("21", "IN STORE");
      this.countryCodes.put("22", "IN STORE");
      this.countryCodes.put("23", "IN STORE");
      this.countryCodes.put("24", "IN STORE");
      this.countryCodes.put("25", "IN STORE");
      this.countryCodes.put("26", "IN STORE");
      this.countryCodes.put("27", "IN STORE");
      this.countryCodes.put("28", "IN STORE");
      this.countryCodes.put("29", "IN STORE");
      this.countryCodes.put("30", "FRANCE");
      this.countryCodes.put("31", "FRANCE");
      this.countryCodes.put("32", "FRANCE");
      this.countryCodes.put("33", "FRANCE");
      this.countryCodes.put("34", "FRANCE");
      this.countryCodes.put("35", "FRANCE");
      this.countryCodes.put("36", "FRANCE");
      this.countryCodes.put("37", "FRANCE");
      this.countryCodes.put("40", "GERMANY");
      this.countryCodes.put("41", "GERMANY");
      this.countryCodes.put("42", "GERMANY");
      this.countryCodes.put("43", "GERMANY");
      this.countryCodes.put("44", "GERMANY");
      this.countryCodes.put("45", "JAPAN");
      this.countryCodes.put("46", "RUSSIAN FEDERATION");
      this.countryCodes.put("49", "JAPAN (JAN-13)");
      this.countryCodes.put("50", "UNITED KINGDOM");
      this.countryCodes.put("54", "BELGIUM / LUXEMBOURG");
      this.countryCodes.put("57", "DENMARK");
      this.countryCodes.put("64", "FINLAND");
      this.countryCodes.put("70", "NORWAY");
      this.countryCodes.put("73", "SWEDEN");
      this.countryCodes.put("76", "SWITZERLAND");
      this.countryCodes.put("80", "ITALY");
      this.countryCodes.put("81", "ITALY");
      this.countryCodes.put("82", "ITALY");
      this.countryCodes.put("83", "ITALY");
      this.countryCodes.put("84", "SPAIN");
      this.countryCodes.put("87", "NETHERLANDS");
      this.countryCodes.put("90", "AUSTRIA");
      this.countryCodes.put("91", "AUSTRIA");
      this.countryCodes.put("93", "AUSTRALIA");
      this.countryCodes.put("94", "NEW ZEALAND");
      this.countryCodes.put("99", "COUPONS");
      this.countryCodes.put("471", "TAIWAN");
      this.countryCodes.put("474", "ESTONIA");
      this.countryCodes.put("475", "LATVIA");
      this.countryCodes.put("477", "LITHUANIA");
      this.countryCodes.put("479", "SRI LANKA");
      this.countryCodes.put("480", "PHILIPPINES");
      this.countryCodes.put("482", "UKRAINE");
      this.countryCodes.put("484", "MOLDOVA");
      this.countryCodes.put("485", "ARMENIA");
      this.countryCodes.put("486", "GEORGIA");
      this.countryCodes.put("487", "KAZAKHSTAN");
      this.countryCodes.put("489", "HONG KONG");
      this.countryCodes.put("520", "GREECE");
      this.countryCodes.put("528", "LEBANON");
      this.countryCodes.put("529", "CYPRUS");
      this.countryCodes.put("531", "MACEDONIA");
      this.countryCodes.put("535", "MALTA");
      this.countryCodes.put("539", "IRELAND");
      this.countryCodes.put("560", "PORTUGAL");
      this.countryCodes.put("569", "ICELAND");
      this.countryCodes.put("590", "POLAND");
      this.countryCodes.put("594", "ROMANIA");
      this.countryCodes.put("599", "HUNGARY");
      this.countryCodes.put("600", "SOUTH AFRICA");
      this.countryCodes.put("601", "SOUTH AFRICA");
      this.countryCodes.put("609", "MAURITIUS");
      this.countryCodes.put("611", "MOROCCO");
      this.countryCodes.put("613", "ALGERIA");
      this.countryCodes.put("619", "TUNISIA");
      this.countryCodes.put("622", "EGYPT");
      this.countryCodes.put("625", "JORDAN");
      this.countryCodes.put("626", "IRAN");
      this.countryCodes.put("690", "CHINA");
      this.countryCodes.put("691", "CHINA");
      this.countryCodes.put("692", "CHINA");
      this.countryCodes.put("729", "ISRAEL");
      this.countryCodes.put("740", "GUATEMALA");
      this.countryCodes.put("741", "EL SALVADOR");
      this.countryCodes.put("742", "HONDURAS");
      this.countryCodes.put("743", "NICARAGUA");
      this.countryCodes.put("744", "COSTA RICA");
      this.countryCodes.put("746", "DOMINICAN REPUBLIC");
      this.countryCodes.put("750", "MEXICO");
      this.countryCodes.put("759", "VENEZUELA");
      this.countryCodes.put("770", "COLOMBIA");
      this.countryCodes.put("773", "URUGUAY");
      this.countryCodes.put("775", "PERU");
      this.countryCodes.put("777", "BOLIVIA");
      this.countryCodes.put("779", "ARGENTINA");
      this.countryCodes.put("780", "CHILE");
      this.countryCodes.put("784", "PARAGUAY");
      this.countryCodes.put("785", "PERU");
      this.countryCodes.put("786", "ECUADOR");
      this.countryCodes.put("789", "BRAZIL");
      this.countryCodes.put("850", "CUBA");
      this.countryCodes.put("858", "SLOVAKIA");
      this.countryCodes.put("859", "CZECH REPUBLIC");
      this.countryCodes.put("860", "YUGLOSLAVIA");
      this.countryCodes.put("869", "TURKEY");
      this.countryCodes.put("880", "SOUTH KOREA");
      this.countryCodes.put("885", "THAILAND");
      this.countryCodes.put("888", "SINGAPORE");
      this.countryCodes.put("890", "INDIA");
      this.countryCodes.put("893", "VIETNAM");
      this.countryCodes.put("899", "INDONESIA");
      this.countryCodes.put("955", "MALAYSIA");
      this.countryCodes.put("977", "INTERNATIONAL STANDARD SERIAL NUMBER FOR PERIODICALS (ISSN)");
      this.countryCodes.put("978", "INTERNATIONAL STANDARD BOOK NUMBERING (ISBN)");
      this.countryCodes.put("979", "INTERNATIONAL STANDARD MUSIC NUMBER (ISMN)");
      this.countryCodes.put("980", "REFUND RECEIPTS");
      this.countryCodes.put("981", "COMMON CURRENCY COUPONS");
      this.countryCodes.put("982", "COMMON CURRENCY COUPONS");
   }

   private void calculateCheckDigit() {
      try {
         String rawDataHolder = this.getRawData().substring(0, 11);
         int sum = 0;

         int i;
         for(i = 0; i < rawDataHolder.length(); ++i) {
            int parseInt = Integer.parseInt(rawDataHolder.substring(i, i + 1));
            if (i % 2 == 0) {
               sum += parseInt * 3;
            } else {
               sum += parseInt;
            }
         }

         i = (10 - sum % 10) % 10;
         this.setRawData(rawDataHolder + String.valueOf(i).toCharArray()[0]);
      } catch (Exception var5) {
         this.error("EUPCA-4: Error calculating check digit.");
      }

   }

   public String getEncodedValue() {
      return this.encodeUPCA();
   }
}
