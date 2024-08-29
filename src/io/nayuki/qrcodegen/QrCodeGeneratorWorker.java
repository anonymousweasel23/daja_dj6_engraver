package io.nayuki.qrcodegen;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class QrCodeGeneratorWorker {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in, "US-ASCII");

      try {
         input.useDelimiter("\r\n|\n|\r");

         while(true) {
            if (processCase(input)) {
               continue;
            }
         }
      } catch (Throwable var5) {
         try {
            input.close();
         } catch (Throwable var4) {
            var5.addSuppressed(var4);
         }

         throw var5;
      }

      //input.close();
   }

   private static boolean processCase(Scanner input) {
      int length = input.nextInt();
      if (length == -1) {
         return false;
      } else if (length > 32767) {
         throw new RuntimeException();
      } else {
         boolean isAscii = true;
         byte[] data = new byte[length];

         int errCorLvl;
         int minVersion;
         for(errCorLvl = 0; errCorLvl < data.length; ++errCorLvl) {
            minVersion = input.nextInt();
            if (minVersion < 0 || minVersion > 255) {
               throw new RuntimeException();
            }

            data[errCorLvl] = (byte)minVersion;
            isAscii &= minVersion < 128;
         }

         errCorLvl = input.nextInt();
         minVersion = input.nextInt();
         int maxVersion = input.nextInt();
         int mask = input.nextInt();
         int boostEcl = input.nextInt();
         if (0 <= errCorLvl && errCorLvl <= 3 && -1 <= mask && mask <= 7 && boostEcl >>> 1 == 0 && 1 <= minVersion && minVersion <= maxVersion && maxVersion <= 40) {
            List segs;
            if (isAscii) {
               segs = QrSegment.makeSegments(new String(data, StandardCharsets.US_ASCII));
            } else {
               segs = Arrays.asList(QrSegment.makeBytes(data));
            }

            try {
               QrCode qr = QrCode.encodeSegments(segs, QrCode.Ecc.values()[errCorLvl], minVersion, maxVersion, mask, boostEcl != 0);
               System.out.println(qr.version);

               for(int y = 0; y < qr.size; ++y) {
                  for(int x = 0; x < qr.size; ++x) {
                     System.out.println(qr.getModule(x, y) ? 1 : 0);
                  }
               }
            } catch (DataTooLongException var13) {
               System.out.println(-1);
            }

            System.out.flush();
            return true;
         } else {
            throw new RuntimeException();
         }
      }
   }
}
