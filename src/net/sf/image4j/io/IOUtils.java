package net.sf.image4j.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
   public static int skip(InputStream in, int count, boolean strict) throws IOException {
      int skipped;
      for(skipped = 0; skipped < count; ++skipped) {
         int b = in.read();
         if (b == -1) {
            break;
         }
      }

      if (skipped < count && strict) {
         throw new EOFException("Failed to skip " + count + " bytes in input");
      } else {
         return skipped;
      }
   }
}
