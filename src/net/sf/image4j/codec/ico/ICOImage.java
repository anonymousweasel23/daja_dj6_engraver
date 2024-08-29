package net.sf.image4j.codec.ico;

import java.awt.image.BufferedImage;
import net.sf.image4j.codec.bmp.BMPImage;
import net.sf.image4j.codec.bmp.InfoHeader;

public class ICOImage extends BMPImage {
   protected IconEntry iconEntry;
   protected boolean pngCompressed = false;
   protected int iconIndex = -1;

   public ICOImage(BufferedImage image, InfoHeader infoHeader, IconEntry iconEntry) {
      super(image, infoHeader);
      this.iconEntry = iconEntry;
   }

   public IconEntry getIconEntry() {
      return this.iconEntry;
   }

   public void setIconEntry(IconEntry iconEntry) {
      this.iconEntry = iconEntry;
   }

   public boolean isPngCompressed() {
      return this.pngCompressed;
   }

   public void setPngCompressed(boolean pngCompressed) {
      this.pngCompressed = pngCompressed;
   }

   public InfoHeader getInfoHeader() {
      return super.getInfoHeader();
   }

   public int getIconIndex() {
      return this.iconIndex;
   }

   public void setIconIndex(int iconIndex) {
      this.iconIndex = iconIndex;
   }

   public int getWidth() {
      return this.iconEntry == null ? -1 : (this.iconEntry.bWidth == 0 ? 256 : this.iconEntry.bWidth);
   }

   public int getHeight() {
      return this.iconEntry == null ? -1 : (this.iconEntry.bHeight == 0 ? 256 : this.iconEntry.bHeight);
   }

   public int getColourDepth() {
      return this.iconEntry == null ? -1 : this.iconEntry.sBitCount;
   }

   public int getColourCount() {
      int bpp = this.iconEntry.sBitCount == 32 ? 24 : this.iconEntry.sBitCount;
      return bpp == -1 ? -1 : 1 << bpp;
   }

   public boolean isIndexed() {
      return this.iconEntry == null ? false : this.iconEntry.sBitCount <= 8;
   }
}
