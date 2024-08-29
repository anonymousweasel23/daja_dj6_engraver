package com.engraver.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public abstract class DoubleBuffer extends Canvas {
   private int bufferWidth;
   private int bufferHeight;
   private Image bufferImage;
   private Graphics bufferGraphics;

   public void paint(Graphics g) {
      if (this.bufferWidth != this.getSize().width || this.bufferHeight != this.getSize().height || this.bufferImage == null || this.bufferGraphics == null) {
         this.resetBuffer();
      }

      if (this.bufferGraphics != null) {
         this.bufferGraphics.clearRect(0, 0, this.bufferWidth, this.bufferHeight);
         this.paintBuffer(this.bufferGraphics);
         g.drawImage(this.bufferImage, 0, 0, this);
      }

   }

   public abstract void paintBuffer(Graphics var1);

   private void resetBuffer() {
      this.bufferWidth = this.getSize().width;
      this.bufferHeight = this.getSize().height;
      if (this.bufferGraphics != null) {
         this.bufferGraphics.dispose();
         this.bufferGraphics = null;
      }

      if (this.bufferImage != null) {
         this.bufferImage.flush();
         this.bufferImage = null;
      }

      System.gc();
      this.bufferImage = this.createImage(this.bufferWidth, this.bufferHeight);
      this.bufferGraphics = this.bufferImage.getGraphics();
   }
}
