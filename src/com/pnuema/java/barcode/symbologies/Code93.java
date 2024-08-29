package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Code93 extends BarcodeCommon implements IBarcode {
   private final List C93_Code = new ArrayList();

   public Code93(String input) {
      this.setRawData(input);
   }

   private Entry findRowByValue(String match) {
      Entry returnValue = null;
      Iterator var3 = this.C93_Code.iterator();

      while(var3.hasNext()) {
         Entry entry = (Entry)var3.next();
         if (entry.getValue().equals(match)) {
            returnValue = entry;
         }
      }

      return (Entry)Objects.requireNonNull(returnValue);
   }

   private Entry findRowByCharacter(String match) {
      Entry returnValue = null;
      Iterator var3 = this.C93_Code.iterator();

      while(var3.hasNext()) {
         Entry entry = (Entry)var3.next();
         if (entry.getCharacter().equals(match)) {
            returnValue = entry;
         }
      }

      return (Entry)Objects.requireNonNull(returnValue);
   }

   private String encodeCode93() {
      this.initCode93();
      String FormattedData = this.addCheckDigits(this.getRawData());
      StringBuilder result = new StringBuilder(this.findRowByCharacter("*").getEncoding());
      char[] var3 = FormattedData.toCharArray();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char c = var3[var5];

         try {
            result.append(this.findRowByCharacter(String.valueOf(c)).getEncoding());
         } catch (Exception var8) {
            this.error("EC93-1: Invalid data.");
         }
      }

      result.append(this.findRowByCharacter("*").getEncoding());
      result.append("1");
      this.C93_Code.clear();
      return result.toString();
   }

   private void initCode93() {
      this.C93_Code.clear();
      this.C93_Code.add(new Entry("0", "0", "100010100"));
      this.C93_Code.add(new Entry("1", "1", "101001000"));
      this.C93_Code.add(new Entry("2", "2", "101000100"));
      this.C93_Code.add(new Entry("3", "3", "101000010"));
      this.C93_Code.add(new Entry("4", "4", "100101000"));
      this.C93_Code.add(new Entry("5", "5", "100100100"));
      this.C93_Code.add(new Entry("6", "6", "100100010"));
      this.C93_Code.add(new Entry("7", "7", "101010000"));
      this.C93_Code.add(new Entry("8", "8", "100010010"));
      this.C93_Code.add(new Entry("9", "9", "100001010"));
      this.C93_Code.add(new Entry("10", "A", "110101000"));
      this.C93_Code.add(new Entry("11", "B", "110100100"));
      this.C93_Code.add(new Entry("12", "C", "110100010"));
      this.C93_Code.add(new Entry("13", "D", "110010100"));
      this.C93_Code.add(new Entry("14", "E", "110010010"));
      this.C93_Code.add(new Entry("15", "F", "110001010"));
      this.C93_Code.add(new Entry("16", "G", "101101000"));
      this.C93_Code.add(new Entry("17", "H", "101100100"));
      this.C93_Code.add(new Entry("18", "I", "101100010"));
      this.C93_Code.add(new Entry("19", "J", "100110100"));
      this.C93_Code.add(new Entry("20", "K", "100011010"));
      this.C93_Code.add(new Entry("21", "L", "101011000"));
      this.C93_Code.add(new Entry("22", "M", "101001100"));
      this.C93_Code.add(new Entry("23", "N", "101000110"));
      this.C93_Code.add(new Entry("24", "O", "100101100"));
      this.C93_Code.add(new Entry("25", "P", "100010110"));
      this.C93_Code.add(new Entry("26", "Q", "110110100"));
      this.C93_Code.add(new Entry("27", "R", "110110010"));
      this.C93_Code.add(new Entry("28", "S", "110101100"));
      this.C93_Code.add(new Entry("29", "T", "110100110"));
      this.C93_Code.add(new Entry("30", "U", "110010110"));
      this.C93_Code.add(new Entry("31", "V", "110011010"));
      this.C93_Code.add(new Entry("32", "W", "101101100"));
      this.C93_Code.add(new Entry("33", "X", "101100110"));
      this.C93_Code.add(new Entry("34", "Y", "100110110"));
      this.C93_Code.add(new Entry("35", "Z", "100111010"));
      this.C93_Code.add(new Entry("36", "-", "100101110"));
      this.C93_Code.add(new Entry("37", ".", "111010100"));
      this.C93_Code.add(new Entry("38", " ", "111010010"));
      this.C93_Code.add(new Entry("39", "$", "111001010"));
      this.C93_Code.add(new Entry("40", "/", "101101110"));
      this.C93_Code.add(new Entry("41", "+", "101110110"));
      this.C93_Code.add(new Entry("42", "%", "110101110"));
      this.C93_Code.add(new Entry("43", "(", "100100110"));
      this.C93_Code.add(new Entry("44", ")", "111011010"));
      this.C93_Code.add(new Entry("45", "#", "111010110"));
      this.C93_Code.add(new Entry("46", "@", "100110010"));
      this.C93_Code.add(new Entry("-", "*", "101011110"));
   }

   private String addCheckDigits(String input) {
      int[] aryCWeights = new int[input.length()];
      int curweight = 1;

      for(int i = input.length() - 1; i >= 0; --i) {
         if (curweight > 20) {
            curweight = 1;
         }

         aryCWeights[i] = curweight++;
      }

      int[] aryKWeights = new int[input.length() + 1];
      curweight = 1;

      int sum;
      for(sum = input.length(); sum >= 0; --sum) {
         if (curweight > 15) {
            curweight = 1;
         }

         aryKWeights[sum] = curweight++;
      }

      sum = 0;

      int checksumValue;
      for(checksumValue = 0; checksumValue < input.length(); ++checksumValue) {
         sum += aryCWeights[checksumValue] * Integer.parseInt(this.findRowByCharacter(String.valueOf(input.toCharArray()[checksumValue])).getValue());
      }

      checksumValue = sum % 47;
      input = input + this.findRowByValue(String.valueOf(checksumValue)).getCharacter();
      sum = 0;

      for(int i = 0; i < input.length(); ++i) {
         sum += aryKWeights[i] * Integer.parseInt(this.findRowByCharacter(String.valueOf(input.toCharArray()[i])).getValue());
      }

      checksumValue = sum % 47;
      input = input + this.findRowByValue(String.valueOf(checksumValue)).getCharacter();
      return input;
   }

   public String getEncodedValue() {
      return this.encodeCode93();
   }

   private static class Entry {
      private final String value;
      private final String character;
      private final String encoding;

      Entry(String value, String character, String encoding) {
         this.value = value;
         this.character = character;
         this.encoding = encoding;
      }

      String getValue() {
         return this.value;
      }

      String getCharacter() {
         return this.character;
      }

      String getEncoding() {
         return this.encoding;
      }
   }
}
