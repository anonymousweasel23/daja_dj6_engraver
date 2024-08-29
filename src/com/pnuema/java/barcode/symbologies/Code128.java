package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import com.pnuema.java.barcode.utils.CharUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Code128 extends BarcodeCommon implements IBarcode {
   private final List C128_Code = new ArrayList();
   private final List formattedData = new ArrayList();
   private final List encodedData = new ArrayList();
   private Entry startCharacter = null;
   private TYPES type;

   public Code128(String input) {
      this.type = Code128.TYPES.DYNAMIC;
      this.setRawData(input);
   }

   public Code128(String input, TYPES type) {
      this.type = Code128.TYPES.DYNAMIC;
      this.type = type;
      this.setRawData(input);
   }

   private String encodeCode128() {
      this.initCode128();
      return this.getEncoding();
   }

   private void initCode128() {
      this.C128_Code.add(new Entry("0", " ", " ", "00", "11011001100"));
      this.C128_Code.add(new Entry("1", "!", "!", "01", "11001101100"));
      this.C128_Code.add(new Entry("2", "\"", "\"", "02", "11001100110"));
      this.C128_Code.add(new Entry("3", "#", "#", "03", "10010011000"));
      this.C128_Code.add(new Entry("4", "$", "$", "04", "10010001100"));
      this.C128_Code.add(new Entry("5", "%", "%", "05", "10001001100"));
      this.C128_Code.add(new Entry("6", "&", "&", "06", "10011001000"));
      this.C128_Code.add(new Entry("7", "'", "'", "07", "10011000100"));
      this.C128_Code.add(new Entry("8", "(", "(", "08", "10001100100"));
      this.C128_Code.add(new Entry("9", ")", ")", "09", "11001001000"));
      this.C128_Code.add(new Entry("10", "*", "*", "10", "11001000100"));
      this.C128_Code.add(new Entry("11", "+", "+", "11", "11000100100"));
      this.C128_Code.add(new Entry("12", ",", ",", "12", "10110011100"));
      this.C128_Code.add(new Entry("13", "-", "-", "13", "10011011100"));
      this.C128_Code.add(new Entry("14", ".", ".", "14", "10011001110"));
      this.C128_Code.add(new Entry("15", "/", "/", "15", "10111001100"));
      this.C128_Code.add(new Entry("16", "0", "0", "16", "10011101100"));
      this.C128_Code.add(new Entry("17", "1", "1", "17", "10011100110"));
      this.C128_Code.add(new Entry("18", "2", "2", "18", "11001110010"));
      this.C128_Code.add(new Entry("19", "3", "3", "19", "11001011100"));
      this.C128_Code.add(new Entry("20", "4", "4", "20", "11001001110"));
      this.C128_Code.add(new Entry("21", "5", "5", "21", "11011100100"));
      this.C128_Code.add(new Entry("22", "6", "6", "22", "11001110100"));
      this.C128_Code.add(new Entry("23", "7", "7", "23", "11101101110"));
      this.C128_Code.add(new Entry("24", "8", "8", "24", "11101001100"));
      this.C128_Code.add(new Entry("25", "9", "9", "25", "11100101100"));
      this.C128_Code.add(new Entry("26", ":", ":", "26", "11100100110"));
      this.C128_Code.add(new Entry("27", ";", ";", "27", "11101100100"));
      this.C128_Code.add(new Entry("28", "<", "<", "28", "11100110100"));
      this.C128_Code.add(new Entry("29", "=", "=", "29", "11100110010"));
      this.C128_Code.add(new Entry("30", ">", ">", "30", "11011011000"));
      this.C128_Code.add(new Entry("31", "?", "?", "31", "11011000110"));
      this.C128_Code.add(new Entry("32", "@", "@", "32", "11000110110"));
      this.C128_Code.add(new Entry("33", "A", "A", "33", "10100011000"));
      this.C128_Code.add(new Entry("34", "B", "B", "34", "10001011000"));
      this.C128_Code.add(new Entry("35", "C", "C", "35", "10001000110"));
      this.C128_Code.add(new Entry("36", "D", "D", "36", "10110001000"));
      this.C128_Code.add(new Entry("37", "E", "E", "37", "10001101000"));
      this.C128_Code.add(new Entry("38", "F", "F", "38", "10001100010"));
      this.C128_Code.add(new Entry("39", "G", "G", "39", "11010001000"));
      this.C128_Code.add(new Entry("40", "H", "H", "40", "11000101000"));
      this.C128_Code.add(new Entry("41", "I", "I", "41", "11000100010"));
      this.C128_Code.add(new Entry("42", "J", "J", "42", "10110111000"));
      this.C128_Code.add(new Entry("43", "K", "K", "43", "10110001110"));
      this.C128_Code.add(new Entry("44", "L", "L", "44", "10001101110"));
      this.C128_Code.add(new Entry("45", "M", "M", "45", "10111011000"));
      this.C128_Code.add(new Entry("46", "N", "N", "46", "10111000110"));
      this.C128_Code.add(new Entry("47", "O", "O", "47", "10001110110"));
      this.C128_Code.add(new Entry("48", "P", "P", "48", "11101110110"));
      this.C128_Code.add(new Entry("49", "Q", "Q", "49", "11010001110"));
      this.C128_Code.add(new Entry("50", "R", "R", "50", "11000101110"));
      this.C128_Code.add(new Entry("51", "S", "S", "51", "11011101000"));
      this.C128_Code.add(new Entry("52", "T", "T", "52", "11011100010"));
      this.C128_Code.add(new Entry("53", "U", "U", "53", "11011101110"));
      this.C128_Code.add(new Entry("54", "V", "V", "54", "11101011000"));
      this.C128_Code.add(new Entry("55", "W", "W", "55", "11101000110"));
      this.C128_Code.add(new Entry("56", "X", "X", "56", "11100010110"));
      this.C128_Code.add(new Entry("57", "Y", "Y", "57", "11101101000"));
      this.C128_Code.add(new Entry("58", "Z", "Z", "58", "11101100010"));
      this.C128_Code.add(new Entry("59", "[", "[", "59", "11100011010"));
      this.C128_Code.add(new Entry("60", "\\", "\\", "60", "11101111010"));
      this.C128_Code.add(new Entry("61", "]", "]", "61", "11001000010"));
      this.C128_Code.add(new Entry("62", "^", "^", "62", "11110001010"));
      this.C128_Code.add(new Entry("63", "_", "_", "63", "10100110000"));
      this.C128_Code.add(new Entry("64", "\u0000", "`", "64", "10100001100"));
      this.C128_Code.add(new Entry("65", CharUtils.getChar(1), "a", "65", "10010110000"));
      this.C128_Code.add(new Entry("66", CharUtils.getChar(2), "b", "66", "10010000110"));
      this.C128_Code.add(new Entry("67", CharUtils.getChar(3), "c", "67", "10000101100"));
      this.C128_Code.add(new Entry("68", CharUtils.getChar(4), "d", "68", "10000100110"));
      this.C128_Code.add(new Entry("69", CharUtils.getChar(5), "e", "69", "10110010000"));
      this.C128_Code.add(new Entry("70", CharUtils.getChar(6), "f", "70", "10110000100"));
      this.C128_Code.add(new Entry("71", CharUtils.getChar(7), "g", "71", "10011010000"));
      this.C128_Code.add(new Entry("72", CharUtils.getChar(8), "h", "72", "10011000010"));
      this.C128_Code.add(new Entry("73", CharUtils.getChar(9), "i", "73", "10000110100"));
      this.C128_Code.add(new Entry("74", CharUtils.getChar(10), "j", "74", "10000110010"));
      this.C128_Code.add(new Entry("75", CharUtils.getChar(11), "k", "75", "11000010010"));
      this.C128_Code.add(new Entry("76", CharUtils.getChar(12), "l", "76", "11001010000"));
      this.C128_Code.add(new Entry("77", CharUtils.getChar(13), "m", "77", "11110111010"));
      this.C128_Code.add(new Entry("78", CharUtils.getChar(14), "n", "78", "11000010100"));
      this.C128_Code.add(new Entry("79", CharUtils.getChar(15), "o", "79", "10001111010"));
      this.C128_Code.add(new Entry("80", CharUtils.getChar(16), "p", "80", "10100111100"));
      this.C128_Code.add(new Entry("81", CharUtils.getChar(17), "q", "81", "10010111100"));
      this.C128_Code.add(new Entry("82", CharUtils.getChar(18), "r", "82", "10010011110"));
      this.C128_Code.add(new Entry("83", CharUtils.getChar(19), "s", "83", "10111100100"));
      this.C128_Code.add(new Entry("84", CharUtils.getChar(20), "t", "84", "10011110100"));
      this.C128_Code.add(new Entry("85", CharUtils.getChar(21), "u", "85", "10011110010"));
      this.C128_Code.add(new Entry("86", CharUtils.getChar(22), "v", "86", "11110100100"));
      this.C128_Code.add(new Entry("87", CharUtils.getChar(23), "w", "87", "11110010100"));
      this.C128_Code.add(new Entry("88", CharUtils.getChar(24), "x", "88", "11110010010"));
      this.C128_Code.add(new Entry("89", CharUtils.getChar(25), "y", "89", "11011011110"));
      this.C128_Code.add(new Entry("90", CharUtils.getChar(26), "z", "90", "11011110110"));
      this.C128_Code.add(new Entry("91", CharUtils.getChar(27), "{", "91", "11110110110"));
      this.C128_Code.add(new Entry("92", CharUtils.getChar(28), "|", "92", "10101111000"));
      this.C128_Code.add(new Entry("93", CharUtils.getChar(29), "}", "93", "10100011110"));
      this.C128_Code.add(new Entry("94", CharUtils.getChar(30), "~", "94", "10001011110"));
      this.C128_Code.add(new Entry("95", CharUtils.getChar(31), CharUtils.getChar(127), "95", "10111101000"));
      this.C128_Code.add(new Entry("96", CharUtils.getChar(202), CharUtils.getChar(202), "96", "10111100010"));
      this.C128_Code.add(new Entry("97", CharUtils.getChar(201), CharUtils.getChar(201), "97", "11110101000"));
      this.C128_Code.add(new Entry("98", "SHIFT", "SHIFT", "98", "11110100010"));
      this.C128_Code.add(new Entry("99", "CODE_C", "CODE_C", "99", "10111011110"));
      this.C128_Code.add(new Entry("100", "CODE_B", CharUtils.getChar(203), "CODE_B", "10111101110"));
      this.C128_Code.add(new Entry("101", CharUtils.getChar(203), "CODE_A", "CODE_A", "11101011110"));
      this.C128_Code.add(new Entry("102", CharUtils.getChar(200), CharUtils.getChar(200), CharUtils.getChar(200), "11110101110"));
      this.C128_Code.add(new Entry("103", "START_A", "START_A", "START_A", "11010000100"));
      this.C128_Code.add(new Entry("104", "START_B", "START_B", "START_B", "11010010000"));
      this.C128_Code.add(new Entry("105", "START_C", "START_C", "START_C", "11010011100"));
      this.C128_Code.add(new Entry("", "STOP", "STOP", "STOP", "11000111010"));
   }

   private Entry findRow(String column, String match) {
      Iterator var3 = this.C128_Code.iterator();

      Entry entry;
      do {
         if (!var3.hasNext()) {
            return null;
         }

         entry = (Entry)var3.next();
         if (column.equalsIgnoreCase("A") && entry.getA().equals(match)) {
            return entry;
         }

         if (column.equalsIgnoreCase("B") && entry.getB().equals(match)) {
            return entry;
         }

         if (column.equalsIgnoreCase("C") && entry.getC().equals(match)) {
            return entry;
         }

         if (column.equalsIgnoreCase("encoding") && entry.encoding.equals(match)) {
            return entry;
         }
      } while(!column.equalsIgnoreCase("id") || !entry.getId().equals(match));

      return entry;
   }

   private CodeCharacter findStartorCodeCharacter(String s) {
      CodeCharacter returnValue = new CodeCharacter();
      if (s.length() <= 1 || !Character.isDigit(s.toCharArray()[0]) && (!String.valueOf(s.toCharArray()[0]).equals(CharUtils.getChar(200)) || !Character.isDigit(s.toCharArray()[1]) && !String.valueOf(s.toCharArray()[1]).equals(CharUtils.getChar(200)))) {
         boolean AFound = false;
         boolean BFound = false;
         Iterator var5 = this.C128_Code.iterator();

         while(var5.hasNext()) {
            Entry row = (Entry)var5.next();

            try {
               if (!AFound && s.equals(row.A)) {
                  AFound = true;
                  returnValue.col = 2;
                  if (this.startCharacter == null) {
                     this.startCharacter = this.findRow("A", "START_A");
                     returnValue.rows.add(this.startCharacter);
                  } else {
                     returnValue.rows.add(this.findRow("B", "CODE_A"));
                  }
               } else if (!BFound && s.equals(row.B)) {
                  BFound = true;
                  returnValue.col = 1;
                  if (this.startCharacter == null) {
                     this.startCharacter = this.findRow("A", "START_B");
                     returnValue.rows.add(this.startCharacter);
                  } else {
                     returnValue.rows.add(this.findRow("A", "CODE_B"));
                  }
               } else if (AFound && BFound) {
                  break;
               }
            } catch (Exception var8) {
               Exception ex = var8;
               this.error("EC128-1: " + ex.getMessage());
            }
         }

         if (returnValue.rows.isEmpty()) {
            this.error("EC128-2: Could not determine start character.");
         }
      } else {
         if (this.startCharacter == null) {
            this.startCharacter = this.findRow("A", "START_C");
            returnValue.rows.add(this.startCharacter);
         } else {
            returnValue.rows.add(this.findRow("A", "CODE_C"));
         }

         returnValue.col = 1;
      }

      return returnValue;
   }

   private String CalculateCheckDigit() {
      int checkSum = 0;

      int i;
      for(i = 0; i < this.formattedData.size(); ++i) {
         String s = ((String)this.formattedData.get(i)).replace("'", "''");
         Entry row = this.findRow("A", s);
         if (row == null) {
            row = this.findRow("B", s);
         }

         if (row == null) {
            row = this.findRow("C", s);
         }

         if (row == null) {
            this.error("EC128-3: No value found in encoding table");
            return null;
         }

         int value = Integer.parseInt(row.getId());
         int addition = value * (i == 0 ? 1 : i);
         checkSum += addition;
      }

      i = checkSum % 103;
      Entry retRows = this.findRow("id", String.valueOf(i));
      if (retRows != null) {
         return retRows.encoding;
      } else {
         return "";
      }
   }

   private void breakUpDataForEncoding() {
      StringBuilder temp = new StringBuilder();
      String tempRawData = this.getRawData();
      char[] var3;
      int var4;
      int var5;
      char c;
      if (this.type != Code128.TYPES.A && this.type != Code128.TYPES.B) {
         if (this.type == Code128.TYPES.C) {
            if (!checkNumericOnly(this.getRawData())) {
               this.error("EC128-6: Only numeric values can be encoded with C128-C.");
            }

            if (this.getRawData().length() % 2 > 0) {
               tempRawData = "0" + this.getRawData();
            }
         }

         var3 = tempRawData.toCharArray();
         var4 = var3.length;

         for(var5 = 0; var5 < var4; ++var5) {
            c = var3[var5];
            if (checkNumericOnly(String.valueOf(c))) {
               if (temp.length() == 0) {
                  temp.append(c);
               } else {
                  temp.append(c);
                  this.formattedData.add(temp.toString());
                  temp = new StringBuilder();
               }
            } else {
               if (temp.length() > 0) {
                  this.formattedData.add(temp.toString());
                  temp = new StringBuilder();
               }

               this.formattedData.add(String.valueOf(c));
            }
         }

         if (temp.length() > 0) {
            this.formattedData.add(temp.toString());
         }

      } else {
         var3 = this.getRawData().toCharArray();
         var4 = var3.length;

         for(var5 = 0; var5 < var4; ++var5) {
            c = var3[var5];
            this.formattedData.add(String.valueOf(c));
         }

      }
   }

   private void insertStartandCodeCharacters() {
      if (this.type != Code128.TYPES.DYNAMIC) {
         switch (this.type) {
            case A:
               this.formattedData.add(0, "START_A");
               break;
            case B:
               this.formattedData.add(0, "START_B");
               break;
            case C:
               this.formattedData.add(0, "START_C");
               break;
            default:
               this.error("EC128-4: Unknown start type in fixed type encoding.");
         }
      } else {
         try {
            Entry currentCodeSet = null;
            String currentCodeString = "";

            for(int i = 0; i < this.formattedData.size(); ++i) {
               CodeCharacter codeCharacter = this.findStartorCodeCharacter((String)this.formattedData.get(i));
               List tempStartChars = codeCharacter.rows;
               boolean sameCodeSet = false;
               Iterator var7 = tempStartChars.iterator();

               label48: {
                  Entry row;
                  do {
                     if (!var7.hasNext()) {
                        break label48;
                     }

                     row = (Entry)var7.next();
                  } while(!row.getA().endsWith(currentCodeString) && !row.getB().endsWith(currentCodeString) && !row.getC().endsWith(currentCodeString));

                  sameCodeSet = true;
               }

               if (currentCodeSet == null || !sameCodeSet) {
                  currentCodeSet = (Entry)tempStartChars.get(0);
                  switch (codeCharacter.col) {
                     case 0:
                        currentCodeString = currentCodeSet.getA().substring(currentCodeSet.getA().length() - 1);
                        this.formattedData.add(i++, currentCodeSet.getA());
                        break;
                     case 1:
                        currentCodeString = currentCodeSet.getB().substring(currentCodeSet.getB().length() - 1);
                        this.formattedData.add(i++, currentCodeSet.getB());
                        break;
                     case 2:
                        currentCodeString = currentCodeSet.getC().substring(currentCodeSet.getC().length() - 1);
                        this.formattedData.add(i++, currentCodeSet.getC());
                  }
               }
            }
         } catch (Exception var9) {
            Exception ex = var9;
            this.error("EC128-3: Could not insert start and code characters.\n Message: " + ex.getMessage());
         }
      }

   }

   private String getEncoding() {
      this.breakUpDataForEncoding();
      this.insertStartandCodeCharacters();
      StringBuilder encodedData = new StringBuilder();
      Iterator var2 = this.formattedData.iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         String s1 = s.replace("'", "''");
         Entry E_Row;
         switch (this.type) {
            case A:
               E_Row = this.findRow("A", s1);
               break;
            case B:
               E_Row = this.findRow("B", s1);
               break;
            case C:
               E_Row = this.findRow("C", s1);
               break;
            case DYNAMIC:
               E_Row = this.findRow("A", s1);
               if (E_Row == null) {
                  E_Row = this.findRow("B", s1);
                  if (E_Row == null) {
                     E_Row = this.findRow("C", s1);
                  }
               }
               break;
            default:
               E_Row = null;
         }

         if (E_Row == null) {
            this.error("EC128-5: Could not find encoding of a value( " + s1 + " ) in C128 type " + this.type);
            return null;
         }

         encodedData.append(E_Row.encoding);
         this.encodedData.add(E_Row.encoding);
      }

      encodedData.append(this.CalculateCheckDigit());
      this.encodedData.add(this.CalculateCheckDigit());
      Entry stopChar = this.findRow("A", "STOP");
      if (stopChar != null) {
         encodedData.append(stopChar.encoding);
         this.encodedData.add(stopChar.encoding);
      }

      encodedData.append("11");
      this.encodedData.add("11");
      return encodedData.toString();
   }

   public String getEncodedValue() {
      return this.encodeCode128();
   }

   private static class Entry {
      private final String id;
      private final String A;
      private final String B;
      private final String C;
      private final String encoding;

      Entry(String id, String a, String b, String c, String encoding) {
         this.id = id;
         this.A = a;
         this.B = b;
         this.C = c;
         this.encoding = encoding;
      }

      String getId() {
         return this.id;
      }

      String getA() {
         return this.A;
      }

      String getB() {
         return this.B;
      }

      String getC() {
         return this.C;
      }

      public String getEncoding() {
         return this.encoding;
      }
   }

   private static class CodeCharacter {
      public List rows;
      public int col;

      private CodeCharacter() {
         this.rows = new ArrayList();
      }

      // $FF: synthetic method
      CodeCharacter(Object x0) {
         this();
      }
   }

   public static enum TYPES {
      DYNAMIC,
      A,
      B,
      C;
   }
}
