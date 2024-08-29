package com.pnuema.java.barcode.symbologies;

import com.pnuema.java.barcode.BarcodeCommon;
import com.pnuema.java.barcode.IBarcode;
import com.pnuema.java.barcode.utils.CharUtils;
import java.util.HashMap;

public class Code39 extends BarcodeCommon implements IBarcode {
   private final HashMap C39_Code = new HashMap();
   private final HashMap ExtC39_Translation = new HashMap();
   private boolean _AllowExtended = false;
   private boolean _EnableChecksum = false;

   public Code39(String input) {
      this.setRawData(input);
   }

   public Code39(String input, boolean AllowExtended) {
      this.setRawData(input);
      this._AllowExtended = AllowExtended;
   }

   public Code39(String input, boolean AllowExtended, boolean EnableChecksum) {
      this.setRawData(input);
      this._AllowExtended = AllowExtended;
      this._EnableChecksum = EnableChecksum;
   }

   private String encodeCode39() {
      this.init_Code39();
      this.init_ExtendedCode39();
      String strNoAstr = this.getRawData().replace("*", "");
      String strFormattedData = "*" + strNoAstr + (this._EnableChecksum ? this.getChecksumChar(strNoAstr) : "") + "*";
      if (this._AllowExtended) {
         this.InsertExtendedCharsIfNeeded(strFormattedData);
      }

      StringBuilder result = new StringBuilder();
      char[] var4 = strFormattedData.toCharArray();
      int var5 = var4.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         char c = var4[var6];

         try {
            result.append((String)this.C39_Code.get(c));
            result.append("0");
         } catch (Exception var9) {
            if (this._AllowExtended) {
               this.error("EC39-1: Invalid data.");
            } else {
               this.error("EC39-1: Invalid data. (Try using Extended Code39)");
            }
         }
      }

      result = new StringBuilder(result.substring(0, result.length() - 1));
      this.C39_Code.clear();
      return result.toString();
   }

   private void init_Code39() {
      this.C39_Code.clear();
      this.C39_Code.put('0', "101001101101");
      this.C39_Code.put('1', "110100101011");
      this.C39_Code.put('2', "101100101011");
      this.C39_Code.put('3', "110110010101");
      this.C39_Code.put('4', "101001101011");
      this.C39_Code.put('5', "110100110101");
      this.C39_Code.put('6', "101100110101");
      this.C39_Code.put('7', "101001011011");
      this.C39_Code.put('8', "110100101101");
      this.C39_Code.put('9', "101100101101");
      this.C39_Code.put('A', "110101001011");
      this.C39_Code.put('B', "101101001011");
      this.C39_Code.put('C', "110110100101");
      this.C39_Code.put('D', "101011001011");
      this.C39_Code.put('E', "110101100101");
      this.C39_Code.put('F', "101101100101");
      this.C39_Code.put('G', "101010011011");
      this.C39_Code.put('H', "110101001101");
      this.C39_Code.put('I', "101101001101");
      this.C39_Code.put('J', "101011001101");
      this.C39_Code.put('K', "110101010011");
      this.C39_Code.put('L', "101101010011");
      this.C39_Code.put('M', "110110101001");
      this.C39_Code.put('N', "101011010011");
      this.C39_Code.put('O', "110101101001");
      this.C39_Code.put('P', "101101101001");
      this.C39_Code.put('Q', "101010110011");
      this.C39_Code.put('R', "110101011001");
      this.C39_Code.put('S', "101101011001");
      this.C39_Code.put('T', "101011011001");
      this.C39_Code.put('U', "110010101011");
      this.C39_Code.put('V', "100110101011");
      this.C39_Code.put('W', "110011010101");
      this.C39_Code.put('X', "100101101011");
      this.C39_Code.put('Y', "110010110101");
      this.C39_Code.put('Z', "100110110101");
      this.C39_Code.put('-', "100101011011");
      this.C39_Code.put('.', "110010101101");
      this.C39_Code.put(' ', "100110101101");
      this.C39_Code.put('$', "100100100101");
      this.C39_Code.put('/', "100100101001");
      this.C39_Code.put('+', "100101001001");
      this.C39_Code.put('%', "101001001001");
      this.C39_Code.put('*', "100101101101");
   }

   private void init_ExtendedCode39() {
      this.ExtC39_Translation.clear();
      this.ExtC39_Translation.put(CharUtils.getChar(0), "%U");
      this.ExtC39_Translation.put(CharUtils.getChar(1), "$A");
      this.ExtC39_Translation.put(CharUtils.getChar(2), "$B");
      this.ExtC39_Translation.put(CharUtils.getChar(3), "$C");
      this.ExtC39_Translation.put(CharUtils.getChar(4), "$D");
      this.ExtC39_Translation.put(CharUtils.getChar(5), "$E");
      this.ExtC39_Translation.put(CharUtils.getChar(6), "$F");
      this.ExtC39_Translation.put(CharUtils.getChar(7), "$G");
      this.ExtC39_Translation.put(CharUtils.getChar(8), "$H");
      this.ExtC39_Translation.put(CharUtils.getChar(9), "$I");
      this.ExtC39_Translation.put(CharUtils.getChar(10), "$J");
      this.ExtC39_Translation.put(CharUtils.getChar(11), "$K");
      this.ExtC39_Translation.put(CharUtils.getChar(12), "$L");
      this.ExtC39_Translation.put(CharUtils.getChar(13), "$M");
      this.ExtC39_Translation.put(CharUtils.getChar(14), "$N");
      this.ExtC39_Translation.put(CharUtils.getChar(15), "$O");
      this.ExtC39_Translation.put(CharUtils.getChar(16), "$P");
      this.ExtC39_Translation.put(CharUtils.getChar(17), "$Q");
      this.ExtC39_Translation.put(CharUtils.getChar(18), "$R");
      this.ExtC39_Translation.put(CharUtils.getChar(19), "$S");
      this.ExtC39_Translation.put(CharUtils.getChar(20), "$T");
      this.ExtC39_Translation.put(CharUtils.getChar(21), "$U");
      this.ExtC39_Translation.put(CharUtils.getChar(22), "$V");
      this.ExtC39_Translation.put(CharUtils.getChar(23), "$W");
      this.ExtC39_Translation.put(CharUtils.getChar(24), "$X");
      this.ExtC39_Translation.put(CharUtils.getChar(25), "$Y");
      this.ExtC39_Translation.put(CharUtils.getChar(26), "$Z");
      this.ExtC39_Translation.put(CharUtils.getChar(27), "%A");
      this.ExtC39_Translation.put(CharUtils.getChar(28), "%B");
      this.ExtC39_Translation.put(CharUtils.getChar(29), "%C");
      this.ExtC39_Translation.put(CharUtils.getChar(30), "%D");
      this.ExtC39_Translation.put(CharUtils.getChar(31), "%E");
      this.ExtC39_Translation.put("!", "/A");
      this.ExtC39_Translation.put("\"", "/B");
      this.ExtC39_Translation.put("#", "/C");
      this.ExtC39_Translation.put("$", "/D");
      this.ExtC39_Translation.put("%", "/E");
      this.ExtC39_Translation.put("&", "/F");
      this.ExtC39_Translation.put("'", "/G");
      this.ExtC39_Translation.put("(", "/H");
      this.ExtC39_Translation.put(")", "/I");
      this.ExtC39_Translation.put("*", "/J");
      this.ExtC39_Translation.put("+", "/K");
      this.ExtC39_Translation.put(",", "/L");
      this.ExtC39_Translation.put("/", "/O");
      this.ExtC39_Translation.put(":", "/Z");
      this.ExtC39_Translation.put(";", "%F");
      this.ExtC39_Translation.put("<", "%G");
      this.ExtC39_Translation.put("=", "%H");
      this.ExtC39_Translation.put(">", "%I");
      this.ExtC39_Translation.put("?", "%J");
      this.ExtC39_Translation.put("[", "%K");
      this.ExtC39_Translation.put("\\", "%L");
      this.ExtC39_Translation.put("]", "%M");
      this.ExtC39_Translation.put("^", "%N");
      this.ExtC39_Translation.put("_", "%O");
      this.ExtC39_Translation.put("{", "%P");
      this.ExtC39_Translation.put("|", "%Q");
      this.ExtC39_Translation.put("}", "%R");
      this.ExtC39_Translation.put("~", "%S");
      this.ExtC39_Translation.put("`", "%W");
      this.ExtC39_Translation.put("@", "%V");
      this.ExtC39_Translation.put("a", "+A");
      this.ExtC39_Translation.put("b", "+B");
      this.ExtC39_Translation.put("c", "+C");
      this.ExtC39_Translation.put("d", "+D");
      this.ExtC39_Translation.put("e", "+E");
      this.ExtC39_Translation.put("f", "+F");
      this.ExtC39_Translation.put("g", "+G");
      this.ExtC39_Translation.put("h", "+H");
      this.ExtC39_Translation.put("i", "+I");
      this.ExtC39_Translation.put("j", "+J");
      this.ExtC39_Translation.put("k", "+K");
      this.ExtC39_Translation.put("l", "+L");
      this.ExtC39_Translation.put("m", "+M");
      this.ExtC39_Translation.put("n", "+N");
      this.ExtC39_Translation.put("o", "+O");
      this.ExtC39_Translation.put("p", "+P");
      this.ExtC39_Translation.put("q", "+Q");
      this.ExtC39_Translation.put("r", "+R");
      this.ExtC39_Translation.put("s", "+S");
      this.ExtC39_Translation.put("t", "+T");
      this.ExtC39_Translation.put("u", "+U");
      this.ExtC39_Translation.put("v", "+V");
      this.ExtC39_Translation.put("w", "+W");
      this.ExtC39_Translation.put("x", "+X");
      this.ExtC39_Translation.put("y", "+Y");
      this.ExtC39_Translation.put("z", "+Z");
      this.ExtC39_Translation.put(CharUtils.getChar(127), "%T");
   }

   private void InsertExtendedCharsIfNeeded(String FormattedData) {
      StringBuilder output = new StringBuilder();
      char[] var3 = FormattedData.toCharArray();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         char c = var3[var5];

         try {
            String var10000 = (String)this.C39_Code.get(c);
            output.append(c);
         } catch (Exception var9) {
            Object oTrans = this.ExtC39_Translation.get(String.valueOf(c));
            output.append(oTrans.toString());
         }
      }

      FormattedData = output.toString();
   }

   private char getChecksumChar(String strNoAstr) {
      String Code39_Charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
      this.InsertExtendedCharsIfNeeded(strNoAstr);
      int sum = 0;

      for(int i = 0; i < strNoAstr.length(); ++i) {
         sum += Code39_Charset.indexOf(strNoAstr.toCharArray()[i]);
      }

      return Code39_Charset.toCharArray()[sum % 43];
   }

   public String getEncodedValue() {
      return this.encodeCode39();
   }
}
