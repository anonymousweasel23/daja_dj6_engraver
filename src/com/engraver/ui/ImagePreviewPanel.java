package com.engraver.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ImagePreviewPanel extends JPanel implements PropertyChangeListener {
   private int width;
   private int height;
   private ImageIcon icon;
   private BufferedImage image;
   private static final int ACCSIZE = 155;
   private Color bg;

   public ImagePreviewPanel() {
      this.setPreferredSize(new Dimension(155, -1));
      this.bg = this.getBackground();
   }

   public void propertyChange(PropertyChangeEvent e) {
      String propertyName = e.getPropertyName();
      if (propertyName.equals("SelectedFileChangedProperty")) {
         File selection = (File)e.getNewValue();
         if (selection == null) {
            return;
         }

         String name = selection.getAbsolutePath();
         if (name != null && name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".gif") || name.toLowerCase().endsWith(".bmp") || name.toLowerCase().endsWith(".png")) {
            try {
               this.image = ImageIO.read(selection);
               this.scaleImage();
               this.repaint();
            } catch (IOException var6) {
               IOException ex = var6;
               Logger.getLogger(ImagePreviewPanel.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         }
      }

   }

   private void scaleImage() {
      this.width = this.image.getWidth(this);
      this.height = this.image.getHeight(this);
      double ratio = 1.0;
      if (this.width >= this.height) {
         ratio = 150.0 / (double)this.width;
         this.width = 150;
         this.height = (int)((double)this.height * ratio);
      } else if (this.getHeight() > 150) {
         ratio = 150.0 / (double)this.height;
         this.height = 150;
         this.width = (int)((double)this.width * ratio);
      } else {
         ratio = (double)this.getHeight() / (double)this.height;
         this.height = this.getHeight();
         this.width = (int)((double)this.width * ratio);
      }

   }

   public void paintComponent(Graphics g) {
      g.setColor(this.bg);
      g.fillRect(0, 0, 155, this.getHeight());
      g.drawImage(this.image, this.getWidth() / 2 - this.width / 2 + 5, this.getHeight() / 2 - this.height / 2, this.width, this.height, this);
   }

   public static void main(String[] args) {
      JFileChooser chooser = new JFileChooser();
      ImagePreviewPanel preview = new ImagePreviewPanel();
      chooser.setAccessory(preview);
      chooser.addPropertyChangeListener(preview);
      chooser.showOpenDialog((Component)null);
   }
}
