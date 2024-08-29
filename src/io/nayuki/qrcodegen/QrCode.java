package io.nayuki.qrcodegen;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public final class QrCode {
   public final int version;
   public final int size;
   public final Ecc errorCorrectionLevel;
   public final int mask;
   private boolean[][] modules;
   private boolean[][] isFunction;
   public static final int MIN_VERSION = 1;
   public static final int MAX_VERSION = 40;
   private static final int PENALTY_N1 = 3;
   private static final int PENALTY_N2 = 3;
   private static final int PENALTY_N3 = 40;
   private static final int PENALTY_N4 = 10;
   private static final byte[][] ECC_CODEWORDS_PER_BLOCK = new byte[][]{{-1, 7, 10, 15, 20, 26, 18, 20, 24, 30, 18, 20, 24, 26, 30, 22, 24, 28, 30, 28, 28, 28, 28, 30, 30, 26, 28, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}, {-1, 10, 16, 26, 18, 24, 16, 18, 22, 22, 26, 30, 22, 22, 24, 24, 28, 28, 26, 26, 26, 26, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28}, {-1, 13, 22, 18, 26, 18, 24, 18, 22, 20, 24, 28, 26, 24, 20, 30, 24, 28, 28, 26, 30, 28, 30, 30, 30, 30, 28, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}, {-1, 17, 28, 22, 16, 22, 28, 26, 26, 24, 28, 24, 28, 22, 24, 24, 30, 28, 28, 26, 28, 30, 24, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30}};
   private static final byte[][] NUM_ERROR_CORRECTION_BLOCKS = new byte[][]{{-1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 7, 8, 8, 9, 9, 10, 12, 12, 12, 13, 14, 15, 16, 17, 18, 19, 19, 20, 21, 22, 24, 25}, {-1, 1, 1, 1, 2, 2, 4, 4, 4, 5, 5, 5, 8, 9, 9, 10, 10, 11, 13, 14, 16, 17, 17, 18, 20, 21, 23, 25, 26, 28, 29, 31, 33, 35, 37, 38, 40, 43, 45, 47, 49}, {-1, 1, 1, 2, 2, 4, 4, 6, 6, 8, 8, 8, 10, 12, 16, 12, 17, 16, 18, 21, 20, 23, 23, 25, 27, 29, 34, 34, 35, 38, 40, 43, 45, 48, 51, 53, 56, 59, 62, 65, 68}, {-1, 1, 1, 2, 4, 4, 4, 5, 6, 8, 8, 11, 11, 16, 16, 18, 16, 19, 21, 25, 25, 25, 34, 30, 32, 35, 37, 40, 42, 45, 48, 51, 54, 57, 60, 63, 66, 70, 74, 77, 81}};

   public static QrCode encodeText(String text, Ecc ecl) {
      Objects.requireNonNull(text);
      Objects.requireNonNull(ecl);
      List segs = QrSegment.makeSegments(text);
      return encodeSegments(segs, ecl);
   }

   public static QrCode encodeBinary(byte[] data, Ecc ecl) {
      Objects.requireNonNull(data);
      Objects.requireNonNull(ecl);
      QrSegment seg = QrSegment.makeBytes(data);
      return encodeSegments(Arrays.asList(seg), ecl);
   }

   public static QrCode encodeSegments(List segs, Ecc ecl) {
      return encodeSegments(segs, ecl, 1, 40, -1, true);
   }

   public static QrCode encodeSegments(List segs, Ecc ecl, int minVersion, int maxVersion, int mask, boolean boostEcl) {
      Objects.requireNonNull(segs);
      Objects.requireNonNull(ecl);
      if (1 <= minVersion && minVersion <= maxVersion && maxVersion <= 40 && mask >= -1 && mask <= 7) {
         int version = minVersion;

         while(true) {
            int dataCapacityBits = getNumDataCodewords(version, ecl) * 8;
            int dataUsedBits = QrSegment.getTotalBits(segs, version);
            if (dataUsedBits != -1 && dataUsedBits <= dataCapacityBits) {
               assert dataUsedBits != -1;

               Ecc[] var12 = QrCode.Ecc.values();
               dataCapacityBits = var12.length;

               int padByte;
               for(padByte = 0; padByte < dataCapacityBits; ++padByte) {
                  Ecc newEcl = var12[padByte];
                  if (boostEcl && dataUsedBits <= getNumDataCodewords(version, newEcl) * 8) {
                     ecl = newEcl;
                  }
               }

               BitBuffer bb = new BitBuffer();
               Iterator var15 = segs.iterator();

               while(var15.hasNext()) {
                  QrSegment seg = (QrSegment)var15.next();
                  bb.appendBits(seg.mode.modeBits, 4);
                  bb.appendBits(seg.numChars, seg.mode.numCharCountBits(version));
                  bb.appendData(seg.data);
               }

               assert bb.bitLength() == dataUsedBits;

               dataCapacityBits = getNumDataCodewords(version, ecl) * 8;

               assert bb.bitLength() <= dataCapacityBits;

               bb.appendBits(0, Math.min(4, dataCapacityBits - bb.bitLength()));
               bb.appendBits(0, (8 - bb.bitLength() % 8) % 8);

               assert bb.bitLength() % 8 == 0;

               for(padByte = 236; bb.bitLength() < dataCapacityBits; padByte ^= 253) {
                  bb.appendBits(padByte, 8);
               }

               byte[] dataCodewords = new byte[bb.bitLength() / 8];

               for(int i = 0; i < bb.bitLength(); ++i) {
                  dataCodewords[i >>> 3] = (byte)(dataCodewords[i >>> 3] | bb.getBit(i) << 7 - (i & 7));
               }

               return new QrCode(version, ecl, dataCodewords, mask);
            }

            if (version >= maxVersion) {
               String msg = "Segment too long";
               if (dataUsedBits != -1) {
                  msg = String.format("Data length = %d bits, Max capacity = %d bits", dataUsedBits, dataCapacityBits);
               }

               throw new DataTooLongException(msg);
            }

            ++version;
         }
      } else {
         throw new IllegalArgumentException("Invalid value");
      }
   }

   public QrCode(int ver, Ecc ecl, byte[] dataCodewords, int msk) {
      if (ver >= 1 && ver <= 40) {
         if (msk >= -1 && msk <= 7) {
            this.version = ver;
            this.size = ver * 4 + 17;
            this.errorCorrectionLevel = (Ecc)Objects.requireNonNull(ecl);
            Objects.requireNonNull(dataCodewords);
            this.modules = new boolean[this.size][this.size];
            this.isFunction = new boolean[this.size][this.size];
            this.drawFunctionPatterns();
            byte[] allCodewords = this.addEccAndInterleave(dataCodewords);
            this.drawCodewords(allCodewords);
            this.mask = this.handleConstructorMasking(msk);
            this.isFunction = null;
         } else {
            throw new IllegalArgumentException("Mask value out of range");
         }
      } else {
         throw new IllegalArgumentException("Version value out of range");
      }
   }

   public boolean getModule(int x, int y) {
      return 0 <= x && x < this.size && 0 <= y && y < this.size && this.modules[y][x];
   }

   public BufferedImage toImage(int scale, int border) {
      if (scale > 0 && border >= 0) {
         if (border <= 1073741823 && (long)this.size + (long)border * 2L <= (long)(Integer.MAX_VALUE / scale)) {
            BufferedImage result = new BufferedImage((this.size + border * 2) * scale, (this.size + border * 2) * scale, 1);

            for(int y = 0; y < result.getHeight(); ++y) {
               for(int x = 0; x < result.getWidth(); ++x) {
                  boolean color = this.getModule(x / scale - border, y / scale - border);
                  result.setRGB(x, y, color ? 0 : 16777215);
               }
            }

            return result;
         } else {
            throw new IllegalArgumentException("Scale or border too large");
         }
      } else {
         throw new IllegalArgumentException("Value out of range");
      }
   }

   public String toSvgString(int border) {
      if (border < 0) {
         throw new IllegalArgumentException("Border must be non-negative");
      } else {
         long brd = (long)border;
         StringBuilder sb = (new StringBuilder()).append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n").append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n").append(String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" viewBox=\"0 0 %1$d %1$d\" stroke=\"none\">\n", (long)this.size + brd * 2L)).append("\t<rect width=\"100%\" height=\"100%\" fill=\"#FFFFFF\"/>\n").append("\t<path d=\"");

         for(int y = 0; y < this.size; ++y) {
            for(int x = 0; x < this.size; ++x) {
               if (this.getModule(x, y)) {
                  if (x != 0 || y != 0) {
                     sb.append(" ");
                  }

                  sb.append(String.format("M%d,%dh1v1h-1z", (long)x + brd, (long)y + brd));
               }
            }
         }

         return sb.append("\" fill=\"#000000\"/>\n").append("</svg>\n").toString();
      }
   }

   private void drawFunctionPatterns() {
      for(int i = 0; i < this.size; ++i) {
         this.setFunctionModule(6, i, i % 2 == 0);
         this.setFunctionModule(i, 6, i % 2 == 0);
      }

      this.drawFinderPattern(3, 3);
      this.drawFinderPattern(this.size - 4, 3);
      this.drawFinderPattern(3, this.size - 4);
      int[] alignPatPos = this.getAlignmentPatternPositions();
      int numAlign = alignPatPos.length;

      for(int i = 0; i < numAlign; ++i) {
         for(int j = 0; j < numAlign; ++j) {
            if ((i != 0 || j != 0) && (i != 0 || j != numAlign - 1) && (i != numAlign - 1 || j != 0)) {
               this.drawAlignmentPattern(alignPatPos[i], alignPatPos[j]);
            }
         }
      }

      this.drawFormatBits(0);
      this.drawVersion();
   }

   private void drawFormatBits(int msk) {
      int data = this.errorCorrectionLevel.formatBits << 3 | msk;
      int rem = data;

      int bits;
      for(bits = 0; bits < 10; ++bits) {
         rem = rem << 1 ^ (rem >>> 9) * 1335;
      }

      bits = (data << 10 | rem) ^ 21522;

      assert bits >>> 15 == 0;

      int i;
      for(i = 0; i <= 5; ++i) {
         this.setFunctionModule(8, i, getBit(bits, i));
      }

      this.setFunctionModule(8, 7, getBit(bits, 6));
      this.setFunctionModule(8, 8, getBit(bits, 7));
      this.setFunctionModule(7, 8, getBit(bits, 8));

      for(i = 9; i < 15; ++i) {
         this.setFunctionModule(14 - i, 8, getBit(bits, i));
      }

      for(i = 0; i < 8; ++i) {
         this.setFunctionModule(this.size - 1 - i, 8, getBit(bits, i));
      }

      for(i = 8; i < 15; ++i) {
         this.setFunctionModule(8, this.size - 15 + i, getBit(bits, i));
      }

      this.setFunctionModule(8, this.size - 8, true);
   }

   private void drawVersion() {
      if (this.version >= 7) {
         int rem = this.version;

         int bits;
         for(bits = 0; bits < 12; ++bits) {
            rem = rem << 1 ^ (rem >>> 11) * 7973;
         }

         bits = this.version << 12 | rem;

         assert bits >>> 18 == 0;

         for(int i = 0; i < 18; ++i) {
            boolean bit = getBit(bits, i);
            int a = this.size - 11 + i % 3;
            int b = i / 3;
            this.setFunctionModule(a, b, bit);
            this.setFunctionModule(b, a, bit);
         }

      }
   }

   private void drawFinderPattern(int x, int y) {
      for(int dy = -4; dy <= 4; ++dy) {
         for(int dx = -4; dx <= 4; ++dx) {
            int dist = Math.max(Math.abs(dx), Math.abs(dy));
            int xx = x + dx;
            int yy = y + dy;
            if (0 <= xx && xx < this.size && 0 <= yy && yy < this.size) {
               this.setFunctionModule(xx, yy, dist != 2 && dist != 4);
            }
         }
      }

   }

   private void drawAlignmentPattern(int x, int y) {
      for(int dy = -2; dy <= 2; ++dy) {
         for(int dx = -2; dx <= 2; ++dx) {
            this.setFunctionModule(x + dx, y + dy, Math.max(Math.abs(dx), Math.abs(dy)) != 1);
         }
      }

   }

   private void setFunctionModule(int x, int y, boolean isBlack) {
      this.modules[y][x] = isBlack;
      this.isFunction[y][x] = true;
   }

   private byte[] addEccAndInterleave(byte[] data) {
      Objects.requireNonNull(data);
      if (data.length != getNumDataCodewords(this.version, this.errorCorrectionLevel)) {
         throw new IllegalArgumentException();
      } else {
         int numBlocks = NUM_ERROR_CORRECTION_BLOCKS[this.errorCorrectionLevel.ordinal()][this.version];
         int blockEccLen = ECC_CODEWORDS_PER_BLOCK[this.errorCorrectionLevel.ordinal()][this.version];
         int rawCodewords = getNumRawDataModules(this.version) / 8;
         int numShortBlocks = numBlocks - rawCodewords % numBlocks;
         int shortBlockLen = rawCodewords / numBlocks;
         byte[][] blocks = new byte[numBlocks][];
         byte[] rsDiv = reedSolomonComputeDivisor(blockEccLen);

         for(int i = 0; i < numBlocks; ++i) {
            byte[] dat = Arrays.copyOfRange(data, i, i + shortBlockLen - blockEccLen + (i < numShortBlocks ? 0 : 1));
            i += dat.length;
            byte[] block = Arrays.copyOf(dat, shortBlockLen + 1);
            byte[] ecc = reedSolomonComputeRemainder(dat, rsDiv);
            System.arraycopy(ecc, 0, block, block.length - blockEccLen, ecc.length);
            blocks[i] = block;
         }

         byte[] result = new byte[rawCodewords];
         int i = 0;

         for(int k = 0; i < blocks[0].length; ++i) {
            for(int j = 0; j < blocks.length; ++j) {
               if (i != shortBlockLen - blockEccLen || j >= numShortBlocks) {
                  result[k] = blocks[j][i];
                  ++k;
               }
            }
         }

         return result;
      }
   }

   private void drawCodewords(byte[] data) {
      Objects.requireNonNull(data);
      if (data.length != getNumRawDataModules(this.version) / 8) {
         throw new IllegalArgumentException();
      } else {
         int i = 0;

         for(int right = this.size - 1; right >= 1; right -= 2) {
            if (right == 6) {
               right = 5;
            }

            for(int vert = 0; vert < this.size; ++vert) {
               for(int j = 0; j < 2; ++j) {
                  int x = right - j;
                  boolean upward = (right + 1 & 2) == 0;
                  int y = upward ? this.size - 1 - vert : vert;
                  if (!this.isFunction[y][x] && i < data.length * 8) {
                     this.modules[y][x] = getBit(data[i >>> 3], 7 - (i & 7));
                     ++i;
                  }
               }
            }
         }

         assert i == data.length * 8;

      }
   }

   private void applyMask(int msk) {
      if (msk >= 0 && msk <= 7) {
         for(int y = 0; y < this.size; ++y) {
            for(int x = 0; x < this.size; ++x) {
               boolean invert;
               switch (msk) {
                  case 0:
                     invert = (x + y) % 2 == 0;
                     break;
                  case 1:
                     invert = y % 2 == 0;
                     break;
                  case 2:
                     invert = x % 3 == 0;
                     break;
                  case 3:
                     invert = (x + y) % 3 == 0;
                     break;
                  case 4:
                     invert = (x / 3 + y / 2) % 2 == 0;
                     break;
                  case 5:
                     invert = x * y % 2 + x * y % 3 == 0;
                     break;
                  case 6:
                     invert = (x * y % 2 + x * y % 3) % 2 == 0;
                     break;
                  case 7:
                     invert = ((x + y) % 2 + x * y % 3) % 2 == 0;
                     break;
                  default:
                     throw new AssertionError();
               }

               boolean[] var10000 = this.modules[y];
               var10000[x] ^= invert & !this.isFunction[y][x];
            }
         }

      } else {
         throw new IllegalArgumentException("Mask value out of range");
      }
   }

   private int handleConstructorMasking(int msk) {
      if (msk == -1) {
         int minPenalty = Integer.MAX_VALUE;

         for(int i = 0; i < 8; ++i) {
            this.applyMask(i);
            this.drawFormatBits(i);
            int penalty = this.getPenaltyScore();
            if (penalty < minPenalty) {
               msk = i;
               minPenalty = penalty;
            }

            this.applyMask(i);
         }
      }

      assert 0 <= msk && msk <= 7;

      this.applyMask(msk);
      this.drawFormatBits(msk);
      return msk;
   }

   private int getPenaltyScore() {
      int result = 0;

      int x;
      boolean runColor;
      int runY;
      int[] runHistory;
      int y;
      for(x = 0; x < this.size; ++x) {
         runColor = false;
         runY = 0;
         runHistory = new int[7];

         for(y = 0; y < this.size; ++y) {
            if (this.modules[x][y] == runColor) {
               ++runY;
               if (runY == 5) {
                  result += 3;
               } else if (runY > 5) {
                  ++result;
               }
            } else {
               this.finderPenaltyAddHistory(runY, runHistory);
               if (!runColor) {
                  result += this.finderPenaltyCountPatterns(runHistory) * 40;
               }

               runColor = this.modules[x][y];
               runY = 1;
            }
         }

         result += this.finderPenaltyTerminateAndCount(runColor, runY, runHistory) * 40;
      }

      for(x = 0; x < this.size; ++x) {
         runColor = false;
         runY = 0;
         runHistory = new int[7];

         for(y = 0; y < this.size; ++y) {
            if (this.modules[y][x] == runColor) {
               ++runY;
               if (runY == 5) {
                  result += 3;
               } else if (runY > 5) {
                  ++result;
               }
            } else {
               this.finderPenaltyAddHistory(runY, runHistory);
               if (!runColor) {
                  result += this.finderPenaltyCountPatterns(runHistory) * 40;
               }

               runColor = this.modules[y][x];
               runY = 1;
            }
         }

         result += this.finderPenaltyTerminateAndCount(runColor, runY, runHistory) * 40;
      }

      int total;
      for(x = 0; x < this.size - 1; ++x) {
         for(total = 0; total < this.size - 1; ++total) {
            boolean color = this.modules[x][total];
            if (color == this.modules[x][total + 1] && color == this.modules[x + 1][total] && color == this.modules[x + 1][total + 1]) {
               result += 3;
            }
         }
      }

      x = 0;
      boolean[][] var12 = this.modules;
      runY = var12.length;

      for(int var13 = 0; var13 < runY; ++var13) {
         boolean[] row = var12[var13];
         boolean[] var7 = row;
         int var8 = row.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            boolean color = var7[var9];
            if (color) {
               ++x;
            }
         }
      }

      total = this.size * this.size;
      runY = (Math.abs(x * 20 - total * 10) + total - 1) / total - 1;
      result += runY * 10;
      return result;
   }

   private int[] getAlignmentPatternPositions() {
      if (this.version == 1) {
         return new int[0];
      } else {
         int numAlign = this.version / 7 + 2;
         int step;
         if (this.version == 32) {
            step = 26;
         } else {
            step = (this.version * 4 + numAlign * 2 + 1) / (numAlign * 2 - 2) * 2;
         }

         int[] result = new int[numAlign];
         result[0] = 6;
         int i = result.length - 1;

         for(int pos = this.size - 7; i >= 1; pos -= step) {
            result[i] = pos;
            --i;
         }

         return result;
      }
   }

   private static int getNumRawDataModules(int ver) {
      if (ver >= 1 && ver <= 40) {
         int size = ver * 4 + 17;
         int result = size * size;
         result -= 192;
         result -= 31;
         result -= (size - 16) * 2;
         if (ver >= 2) {
            int numAlign = ver / 7 + 2;
            result -= (numAlign - 1) * (numAlign - 1) * 25;
            result -= (numAlign - 2) * 2 * 20;
            if (ver >= 7) {
               result -= 36;
            }
         }

         assert 208 <= result && result <= 29648;

         return result;
      } else {
         throw new IllegalArgumentException("Version number out of range");
      }
   }

   private static byte[] reedSolomonComputeDivisor(int degree) {
      if (degree >= 1 && degree <= 255) {
         byte[] result = new byte[degree];
         result[degree - 1] = 1;
         int root = 1;

         for(int i = 0; i < degree; ++i) {
            for(int j = 0; j < result.length; ++j) {
               result[j] = (byte)reedSolomonMultiply(result[j] & 255, root);
               if (j + 1 < result.length) {
                  result[j] ^= result[j + 1];
               }
            }

            root = reedSolomonMultiply(root, 2);
         }

         return result;
      } else {
         throw new IllegalArgumentException("Degree out of range");
      }
   }

   private static byte[] reedSolomonComputeRemainder(byte[] data, byte[] divisor) {
      Objects.requireNonNull(data);
      Objects.requireNonNull(divisor);
      byte[] result = new byte[divisor.length];
      byte[] var3 = data;
      int var4 = data.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         byte b = var3[var5];
         int factor = (b ^ result[0]) & 255;
         System.arraycopy(result, 1, result, 0, result.length - 1);
         result[result.length - 1] = 0;

         for(int i = 0; i < result.length; ++i) {
            result[i] = (byte)(result[i] ^ reedSolomonMultiply(divisor[i] & 255, factor));
         }
      }

      return result;
   }

   private static int reedSolomonMultiply(int x, int y) {
      assert x >> 8 == 0 && y >> 8 == 0;

      int z = 0;

      for(int i = 7; i >= 0; --i) {
         z = z << 1 ^ (z >>> 7) * 285;
         z ^= (y >>> i & 1) * x;
      }

      assert z >>> 8 == 0;

      return z;
   }

   static int getNumDataCodewords(int ver, Ecc ecl) {
      return getNumRawDataModules(ver) / 8 - ECC_CODEWORDS_PER_BLOCK[ecl.ordinal()][ver] * NUM_ERROR_CORRECTION_BLOCKS[ecl.ordinal()][ver];
   }

   private int finderPenaltyCountPatterns(int[] runHistory) {
      int n = runHistory[1];

      assert n <= this.size * 3;

      boolean core = n > 0 && runHistory[2] == n && runHistory[3] == n * 3 && runHistory[4] == n && runHistory[5] == n;
      return (core && runHistory[0] >= n * 4 && runHistory[6] >= n ? 1 : 0) + (core && runHistory[6] >= n * 4 && runHistory[0] >= n ? 1 : 0);
   }

   private int finderPenaltyTerminateAndCount(boolean currentRunColor, int currentRunLength, int[] runHistory) {
      if (currentRunColor) {
         this.finderPenaltyAddHistory(currentRunLength, runHistory);
         currentRunLength = 0;
      }

      currentRunLength += this.size;
      this.finderPenaltyAddHistory(currentRunLength, runHistory);
      return this.finderPenaltyCountPatterns(runHistory);
   }

   private void finderPenaltyAddHistory(int currentRunLength, int[] runHistory) {
      if (runHistory[0] == 0) {
         currentRunLength += this.size;
      }

      System.arraycopy(runHistory, 0, runHistory, 1, runHistory.length - 1);
      runHistory[0] = currentRunLength;
   }

   static boolean getBit(int x, int i) {
      return (x >>> i & 1) != 0;
   }

   public static enum Ecc {
      LOW(1),
      MEDIUM(0),
      QUARTILE(3),
      HIGH(2);

      final int formatBits;

      private Ecc(int fb) {
         this.formatBits = fb;
      }
   }
}
