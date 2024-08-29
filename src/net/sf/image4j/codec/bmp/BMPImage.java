package net.sf.image4j.codec.bmp;

import java.awt.image.BufferedImage;

public class BMPImage {
   protected InfoHeader infoHeader;
   protected BufferedImage image;

   public BMPImage(BufferedImage image, InfoHeader infoHeader) {
      this.image = image;
      this.infoHeader = infoHeader;
   }

   public InfoHeader getInfoHeader() {
      return this.infoHeader;
   }

   public void setInfoHeader(InfoHeader infoHeader) {
      this.infoHeader = infoHeader;
   }

   public BufferedImage getImage() {
      return this.image;
   }

   public void setImage(BufferedImage image) {
      this.image = image;
   }

   public int getWidth() {
      return this.infoHeader == null ? -1 : this.infoHeader.iWidth;
   }

   public int getHeight() {
      return this.infoHeader == null ? -1 : this.infoHeader.iHeight;
   }

   public int getColourDepth() {
      return this.infoHeader == null ? -1 : this.infoHeader.sBitCount;
   }

   public int getColourCount() {
      int bpp = this.infoHeader.sBitCount == 32 ? 24 : this.infoHeader.sBitCount;
      return bpp == -1 ? -1 : 1 << bpp;
   }

   public boolean isIndexed() {
      return this.infoHeader == null ? false : this.infoHeader.sBitCount <= 8;
   }
}
