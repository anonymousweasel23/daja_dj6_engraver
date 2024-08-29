package net.sf.image4j.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountingDataInputStream extends DataInputStream implements CountingDataInput {
   public CountingDataInputStream(InputStream in) {
      super(new CountingInputStream(in));
   }

   public int getCount() {
      return ((CountingInputStream)this.in).getCount();
   }

   public int skip(int count, boolean strict) throws IOException {
      return IOUtils.skip(this, count, strict);
   }

   public String toString() {
      return this.getClass().getSimpleName() + "(" + this.in + ") [" + this.getCount() + "]";
   }
}
