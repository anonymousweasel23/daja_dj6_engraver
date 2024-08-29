package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import java.util.TreeMap;

public class EAN13 extends BarcodeCommon implements IBarcode {
   private final String[] EAN_CodeA = new String[]{"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
   private final String[] EAN_CodeB = new String[]{"0100111", "0110011", "0011011", "0100001", "0011101", "0111001", "0000101", "0010001", "0001001", "0010111"};
   private final String[] EAN_CodeC = new String[]{"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};
   private final String[] EAN_Pattern = new String[]{"aaaaaa", "aababb", "aabbab", "aabbba", "abaabb", "abbaab", "abbbaa", "ababab", "ababba", "abbaba"};
   private final TreeMap countryCodes = new TreeMap();

   public EAN13(String input) {
      this.setRawData(input);
      this.calculateCheckDigit();
   }

   private String encodeEAN13() {
      if (this.getRawData().length() < 12 || this.getRawData().length() > 13) {
         this.error("EEAN13-1: Data length invalid. (Length must be 12 or 13)");
      }

      if (!checkNumericOnly(this.getRawData())) {
         this.error("EEAN13-2: Numeric Data Only");
      }

      String patterncode = this.EAN_Pattern[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[0]))];
      StringBuilder result = new StringBuilder("101");

      int pos;
      for(pos = 0; pos < 6; ++pos) {
         if (patterncode.toCharArray()[pos] == 'a') {
            result.append(this.EAN_CodeA[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos + 1]))]);
         } else if (patterncode.toCharArray()[pos] == 'b') {
            result.append(this.EAN_CodeB[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos + 1]))]);
         }
      }

      result.append("01010");
      pos = 1;

      while(pos <= 5) {
         result.append(this.EAN_CodeC[Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[pos++ + 6]))]);
      }

      int cs = Integer.parseInt(String.valueOf(this.getRawData().toCharArray()[this.getRawData().length() - 1]));
      result.append(this.EAN_CodeC[cs]);
      result.append("101");
      this.initCountryCodes();
      String countryAssigningManufacturerCode = "N/A";
      int twodigitCode = Integer.parseInt(this.getRawData().substring(0, 2));
      int threedigitCode = Integer.parseInt(this.getRawData().substring(0, 3));

      try {
         String var10000;
         try {
            var10000 = (String)this.countryCodes.get(threedigitCode);
         } catch (Exception var15) {
            try {
               var10000 = (String)this.countryCodes.get(twodigitCode);
            } catch (Exception var14) {
               this.error("EEAN13-3: Country assigning manufacturer code not found.");
            }
         }
      } finally {
         this.countryCodes.clear();
      }

      return result.toString();
   }

   private void createCountryCodeRange(int startingNumber, int endingNumber, String countryDescription) {
      for(int i = startingNumber; i <= endingNumber; ++i) {
         this.countryCodes.put(i, countryDescription);
      }

   }

   private void initCountryCodes() {
      this.countryCodes.clear();
      this.createCountryCodeRange(0, 19, "US / CANADA");
      this.createCountryCodeRange(20, 29, "IN STORE");
      this.createCountryCodeRange(30, 39, "US DRUGS");
      this.createCountryCodeRange(40, 49, "Used to issue restricted circulation numbers within a geographic region (MO defined)");
      this.createCountryCodeRange(50, 59, "GS1 US reserved for future use");
      this.createCountryCodeRange(60, 99, "US / CANADA");
      this.createCountryCodeRange(100, 139, "UNITED STATES");
      this.createCountryCodeRange(200, 299, "Used to issue GS1 restricted circulation number within a geographic region (MO defined)");
      this.createCountryCodeRange(300, 379, "FRANCE AND MONACO");
      this.createCountryCodeRange(380, 380, "BULGARIA");
      this.createCountryCodeRange(383, 383, "SLOVENIA");
      this.createCountryCodeRange(385, 385, "CROATIA");
      this.createCountryCodeRange(387, 387, "BOSNIA AND HERZEGOVINA");
      this.createCountryCodeRange(389, 389, "MONTENEGRO");
      this.createCountryCodeRange(400, 440, "GERMANY");
      this.createCountryCodeRange(450, 459, "JAPAN");
      this.createCountryCodeRange(460, 469, "RUSSIA");
      this.createCountryCodeRange(470, 470, "KYRGYZSTAN");
      this.createCountryCodeRange(471, 471, "TAIWAN");
      this.createCountryCodeRange(474, 474, "ESTONIA");
      this.createCountryCodeRange(475, 475, "LATVIA");
      this.createCountryCodeRange(476, 476, "AZERBAIJAN");
      this.createCountryCodeRange(477, 477, "LITHUANIA");
      this.createCountryCodeRange(478, 478, "UZBEKISTAN");
      this.createCountryCodeRange(479, 479, "SRI LANKA");
      this.createCountryCodeRange(480, 480, "PHILIPPINES");
      this.createCountryCodeRange(481, 481, "BELARUS");
      this.createCountryCodeRange(482, 482, "UKRAINE");
      this.createCountryCodeRange(483, 483, "TURKMENISTAN");
      this.createCountryCodeRange(484, 484, "MOLDOVA");
      this.createCountryCodeRange(485, 485, "ARMENIA");
      this.createCountryCodeRange(486, 486, "GEORGIA");
      this.createCountryCodeRange(487, 487, "KAZAKHSTAN");
      this.createCountryCodeRange(488, 488, "TAJIKISTAN");
      this.createCountryCodeRange(489, 489, "HONG KONG");
      this.createCountryCodeRange(490, 499, "JAPAN");
      this.createCountryCodeRange(500, 509, "UNITED KINGDOM");
      this.createCountryCodeRange(520, 521, "GREECE");
      this.createCountryCodeRange(528, 528, "LEBANON");
      this.createCountryCodeRange(529, 529, "CYPRUS");
      this.createCountryCodeRange(530, 530, "ALBANIA");
      this.createCountryCodeRange(531, 531, "MACEDONIA");
      this.createCountryCodeRange(535, 535, "MALTA");
      this.createCountryCodeRange(539, 539, "REPUBLIC OF IRELAND");
      this.createCountryCodeRange(540, 549, "BELGIUM AND LUXEMBOURG");
      this.createCountryCodeRange(560, 560, "PORTUGAL");
      this.createCountryCodeRange(569, 569, "ICELAND");
      this.createCountryCodeRange(570, 579, "DENMARK, FAROE ISLANDS AND GREENLAND");
      this.createCountryCodeRange(590, 590, "POLAND");
      this.createCountryCodeRange(594, 594, "ROMANIA");
      this.createCountryCodeRange(599, 599, "HUNGARY");
      this.createCountryCodeRange(600, 601, "SOUTH AFRICA");
      this.createCountryCodeRange(603, 603, "GHANA");
      this.createCountryCodeRange(604, 604, "SENEGAL");
      this.createCountryCodeRange(608, 608, "BAHRAIN");
      this.createCountryCodeRange(609, 609, "MAURITIUS");
      this.createCountryCodeRange(611, 611, "MOROCCO");
      this.createCountryCodeRange(613, 613, "ALGERIA");
      this.createCountryCodeRange(615, 615, "NIGERIA");
      this.createCountryCodeRange(616, 616, "KENYA");
      this.createCountryCodeRange(618, 618, "IVORY COAST");
      this.createCountryCodeRange(619, 619, "TUNISIA");
      this.createCountryCodeRange(620, 620, "TANZANIA");
      this.createCountryCodeRange(621, 621, "SYRIA");
      this.createCountryCodeRange(622, 622, "EGYPT");
      this.createCountryCodeRange(623, 623, "BRUNEI");
      this.createCountryCodeRange(624, 624, "LIBYA");
      this.createCountryCodeRange(625, 625, "JORDAN");
      this.createCountryCodeRange(626, 626, "IRAN");
      this.createCountryCodeRange(627, 627, "KUWAIT");
      this.createCountryCodeRange(628, 628, "SAUDI ARABIA");
      this.createCountryCodeRange(629, 629, "UNITED ARAB EMIRATES");
      this.createCountryCodeRange(640, 649, "FINLAND");
      this.createCountryCodeRange(690, 699, "CHINA");
      this.createCountryCodeRange(700, 709, "NORWAY");
      this.createCountryCodeRange(729, 729, "ISRAEL");
      this.createCountryCodeRange(730, 739, "SWEDEN");
      this.createCountryCodeRange(740, 740, "GUATEMALA");
      this.createCountryCodeRange(741, 741, "EL SALVADOR");
      this.createCountryCodeRange(742, 742, "HONDURAS");
      this.createCountryCodeRange(743, 743, "NICARAGUA");
      this.createCountryCodeRange(744, 744, "COSTA RICA");
      this.createCountryCodeRange(745, 745, "PANAMA");
      this.createCountryCodeRange(746, 746, "DOMINICAN REPUBLIC");
      this.createCountryCodeRange(750, 750, "MEXICO");
      this.createCountryCodeRange(754, 755, "CANADA");
      this.createCountryCodeRange(759, 759, "VENEZUELA");
      this.createCountryCodeRange(760, 769, "SWITZERLAND AND LIECHTENSTEIN");
      this.createCountryCodeRange(770, 771, "COLOMBIA");
      this.createCountryCodeRange(773, 773, "URUGUAY");
      this.createCountryCodeRange(775, 775, "PERU");
      this.createCountryCodeRange(777, 777, "BOLIVIA");
      this.createCountryCodeRange(778, 779, "ARGENTINA");
      this.createCountryCodeRange(780, 780, "CHILE");
      this.createCountryCodeRange(784, 784, "PARAGUAY");
      this.createCountryCodeRange(786, 786, "ECUADOR");
      this.createCountryCodeRange(789, 790, "BRAZIL");
      this.createCountryCodeRange(800, 839, "ITALY, SAN MARINO AND VATICAN CITY");
      this.createCountryCodeRange(840, 849, "SPAIN AND ANDORRA");
      this.createCountryCodeRange(850, 850, "CUBA");
      this.createCountryCodeRange(858, 858, "SLOVAKIA");
      this.createCountryCodeRange(859, 859, "CZECH REPUBLIC");
      this.createCountryCodeRange(860, 860, "SERBIA");
      this.createCountryCodeRange(865, 865, "MONGOLIA");
      this.createCountryCodeRange(867, 867, "NORTH KOREA");
      this.createCountryCodeRange(868, 869, "TURKEY");
      this.createCountryCodeRange(870, 879, "NETHERLANDS");
      this.createCountryCodeRange(880, 880, "SOUTH KOREA");
      this.createCountryCodeRange(884, 884, "CAMBODIA");
      this.createCountryCodeRange(885, 885, "THAILAND");
      this.createCountryCodeRange(888, 888, "SINGAPORE");
      this.createCountryCodeRange(890, 890, "INDIA");
      this.createCountryCodeRange(893, 893, "VIETNAM");
      this.createCountryCodeRange(896, 896, "PAKISTAN");
      this.createCountryCodeRange(899, 899, "INDONESIA");
      this.createCountryCodeRange(900, 919, "AUSTRIA");
      this.createCountryCodeRange(930, 939, "AUSTRALIA");
      this.createCountryCodeRange(940, 949, "NEW ZEALAND");
      this.createCountryCodeRange(950, 950, "GS1 GLOBAL OFFICE SPECIAL APPLICATIONS");
      this.createCountryCodeRange(951, 951, "EPC GLOBAL SPECIAL APPLICATIONS");
      this.createCountryCodeRange(955, 955, "MALAYSIA");
      this.createCountryCodeRange(958, 958, "MACAU");
      this.createCountryCodeRange(960, 961, "GS1 UK OFFICE: GTIN-8 ALLOCATIONS");
      this.createCountryCodeRange(962, 969, "GS1 GLOBAL OFFICE: GTIN-8 ALLOCATIONS");
      this.createCountryCodeRange(977, 977, "SERIAL PUBLICATIONS (ISSN)");
      this.createCountryCodeRange(978, 979, "BOOKLAND (ISBN) 979-0 USED FOR SHEET MUSIC (ISMN-13, REPLACES DEPRECATED ISMN M- NUMBERS)");
      this.createCountryCodeRange(980, 980, "REFUND RECEIPTS");
      this.createCountryCodeRange(981, 984, "GS1 COUPON IDENTIFICATION FOR COMMON CURRENCY AREAS");
      this.createCountryCodeRange(990, 999, "GS1 COUPON IDENTIFICATION");
   }

   private void calculateCheckDigit() {
      try {
         String rawDataHolder = this.getRawData().substring(0, 12);
         int even = 0;
         int odd = 0;

         int i;
         for(i = 0; i < rawDataHolder.length(); ++i) {
            if (i % 2 == 0) {
               odd += Integer.parseInt(rawDataHolder.substring(i, i + 1));
            } else {
               even += Integer.parseInt(rawDataHolder.substring(i, i + 1)) * 3;
            }
         }

         i = even + odd;
         int cs = i % 10;
         cs = 10 - cs;
         if (cs == 10) {
            cs = 0;
         }

         this.setRawData(rawDataHolder + String.valueOf(cs).toCharArray()[0]);
      } catch (Exception var6) {
         this.error("EEAN13-4: Error calculating check digit.");
      }

   }

   public String getEncodedValue() {
      return this.encodeEAN13();
   }
}
