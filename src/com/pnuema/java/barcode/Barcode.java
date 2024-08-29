package com.pnuema.java.barcode;

import com.pnuema.java.barcode.symbologies.Codabar;
import com.pnuema.java.barcode.symbologies.Code11;
import com.pnuema.java.barcode.symbologies.Code128;
import com.pnuema.java.barcode.symbologies.Code39;
import com.pnuema.java.barcode.symbologies.Code93;
import com.pnuema.java.barcode.symbologies.EAN13;
import com.pnuema.java.barcode.symbologies.EAN8;
import com.pnuema.java.barcode.symbologies.FIM;
import com.pnuema.java.barcode.symbologies.ISBN;
import com.pnuema.java.barcode.symbologies.ITF14;
import com.pnuema.java.barcode.symbologies.Interleaved2of5;
import com.pnuema.java.barcode.symbologies.JAN13;
import com.pnuema.java.barcode.symbologies.MSI;
import com.pnuema.java.barcode.symbologies.Pharmacode;
import com.pnuema.java.barcode.symbologies.Postnet;
import com.pnuema.java.barcode.symbologies.Standard2of5;
import com.pnuema.java.barcode.symbologies.Telepen;
import com.pnuema.java.barcode.symbologies.UPCA;
import com.pnuema.java.barcode.symbologies.UPCE;
import com.pnuema.java.barcode.symbologies.UPCSupplement2;
import com.pnuema.java.barcode.symbologies.UPCSupplement5;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.imageio.ImageIO;

public class Barcode {
   private IBarcode ibarcode;
   private String rawData;
   private String encodedValue;
   private String countryAssigningManufacturerCode;
   private TYPE encodedType;
   private Image encodedImage;
   private Color foreColor;
   private Color backColor;
   private int width;
   private int height;
   private Font labelFont;
   private Labels.LabelPositions labelPositions;
   private AlignmentPositions alignmentPosition;
   private String alternateLabel;
   private boolean includeLabel;
   private boolean standardizeLabel;
   private long encodingTime;
   private long drawTime;
   private Integer barWidth;
   private Double aspectRatio;

   public Barcode() {
      this.rawData = "";
      this.encodedValue = "";
      this.countryAssigningManufacturerCode = "N/A";
      this.encodedType = Barcode.TYPE.UNSPECIFIED;
      this.encodedImage = null;
      this.foreColor = Color.BLACK;
      this.backColor = Color.WHITE;
      this.width = 300;
      this.height = 150;
      this.labelFont = new Font("Serif", 1, 10);
      this.labelPositions = Labels.LabelPositions.BOTTOM;
      this.alignmentPosition = Barcode.AlignmentPositions.CENTER;
      this.standardizeLabel = true;
   }

   public Barcode(String data) {
      this.rawData = "";
      this.encodedValue = "";
      this.countryAssigningManufacturerCode = "N/A";
      this.encodedType = Barcode.TYPE.UNSPECIFIED;
      this.encodedImage = null;
      this.foreColor = Color.BLACK;
      this.backColor = Color.WHITE;
      this.width = 300;
      this.height = 150;
      this.labelFont = new Font("Serif", 1, 10);
      this.labelPositions = Labels.LabelPositions.BOTTOM;
      this.alignmentPosition = Barcode.AlignmentPositions.CENTER;
      this.standardizeLabel = true;
      this.setRawData(data);
   }

   public Barcode(String data, TYPE iType) {
      this(data);
      this.encodedType = iType;
   }

   public String getTitle() {
      return this.getClass().getPackage().getImplementationTitle();
   }

   public String getVersion() {
      return this.getClass().getPackage().getImplementationVersion();
   }

   public String getRawData() {
      return this.rawData;
   }

   public void setRawData(String rawData) {
      this.rawData = rawData;
   }

   public String getEncodedValue() {
      return this.encodedValue;
   }

   public String getCountryAssigningManufacturerCode() {
      return this.countryAssigningManufacturerCode;
   }

   public TYPE getEncodedType() {
      return this.encodedType;
   }

   public void setEncodedType(TYPE encoded_Type) {
      this.encodedType = encoded_Type;
   }

   public Image getEncodedImage() {
      return this.encodedImage;
   }

   public Color getForeColor() {
      return this.foreColor;
   }

   public void setForeColor(Color _ForeColor) {
      this.foreColor = _ForeColor;
   }

   public Color getBackColor() {
      return this.backColor;
   }

   public void setBackColor(Color _BackColor) {
      this.backColor = _BackColor;
   }

   public Font getLabelFont() {
      return this.labelFont;
   }

   public void setLabelFont(Font _LabelFont) {
      this.labelFont = _LabelFont;
   }

   public Labels.LabelPositions getLabelPosition() {
      return this.labelPositions;
   }

   public void setLabelPosition(Labels.LabelPositions _LabelPosition) {
      this.labelPositions = _LabelPosition;
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public String getAlternateLabel() {
      return this.alternateLabel;
   }

   public void setAlternateLabel(String alternateLabel) {
      this.alternateLabel = alternateLabel;
   }

   public boolean isIncludeLabel() {
      return this.includeLabel;
   }

   public void setIncludeLabel(boolean includeLabel) {
      this.includeLabel = includeLabel;
   }

   public boolean isStandardizeLabel() {
      return this.standardizeLabel;
   }

   public void setStandardizeLabel(boolean standardizeLabel) {
      this.standardizeLabel = standardizeLabel;
   }

   public long getEncodingTime() {
      return this.encodingTime;
   }

   private void setEncodingTime(long encodingTime) {
      this.encodingTime = encodingTime;
   }

   public long getDrawTime() {
      return this.drawTime;
   }

   private void setDrawTime(long drawTime) {
      this.drawTime = drawTime;
   }

   public Integer getBarWidth() {
      return this.barWidth;
   }

   public void setBarWidth(Integer barWidth) {
      this.barWidth = barWidth;
   }

   public Double getAspectRatio() {
      return this.aspectRatio;
   }

   public void setAspectRatio(Double aspectRatio) {
      this.aspectRatio = aspectRatio;
   }

   public List getErrors() {
      return this.ibarcode.getErrors();
   }

   public AlignmentPositions getAlignmentPosition() {
      return this.alignmentPosition;
   }

   public void setAlignmentPosition(AlignmentPositions alignment) {
      this.alignmentPosition = alignment;
   }

   public Image encode(TYPE iType, String stringToEncode, int width, int height) {
      this.setWidth(width);
      this.setHeight(height);
      return this.encode(iType, stringToEncode);
   }

   public Image encode(TYPE iType, String stringToEncode, Color foreColor, Color backColor, int width, int height) {
      this.setWidth(width);
      this.setHeight(height);
      return this.encode(iType, stringToEncode, foreColor, backColor);
   }

   public Image encode(TYPE iType, String stringToEncode, Color foreColor, Color backColor) {
      this.setBackColor(backColor);
      this.setForeColor(foreColor);
      return this.encode(iType, stringToEncode);
   }

   public Image encode(TYPE iType, String stringToEncode) {
      this.rawData = stringToEncode;
      return this.encode(iType);
   }

   private Image encode(TYPE iType) {
      this.encodedType = iType;
      return this.encode();
   }

   private Image encode() {
      long dtStartTime = System.currentTimeMillis();
      if (this.rawData.trim().isEmpty()) {
         throw new IllegalArgumentException("EENCODE-1: Input data not allowed to be blank.");
      } else if (this.getEncodedType() == Barcode.TYPE.UNSPECIFIED) {
         throw new IllegalArgumentException("EENCODE-2: Symbology type not allowed to be unspecified.");
      } else {
         this.encodedValue = "";
         this.countryAssigningManufacturerCode = "N/A";
         switch (this.encodedType) {
            case UCC12:
            case UPCA:
               this.ibarcode = new UPCA(this.rawData);
               break;
            case Industrial2of5_Mod10:
            case Industrial2of5:
            case Standard2of5_Mod10:
            case Standard2of5:
               this.ibarcode = new Standard2of5(this.rawData, this.getEncodedType());
               break;
            case Interleaved2of5_Mod10:
            case Interleaved2of5:
               this.ibarcode = new Interleaved2of5(this.rawData, this.getEncodedType());
               break;
            case UCC13:
            case EAN13:
               this.ibarcode = new EAN13(this.rawData);
               break;
            case LOGMARS:
            case CODE39:
               this.ibarcode = new Code39(this.rawData);
               break;
            case CODE39Extended:
               this.ibarcode = new Code39(this.rawData, true);
               break;
            case CODE39_Mod43:
               this.ibarcode = new Code39(this.rawData, false, true);
               break;
            case Codabar:
               this.ibarcode = new Codabar(this.rawData);
               break;
            case ISBN:
            case BOOKLAND:
               this.ibarcode = new ISBN(this.rawData);
               break;
            case JAN13:
               this.ibarcode = new JAN13(this.rawData);
               break;
            case MSI_Mod10:
            case MSI_2Mod10:
            case MSI_Mod11:
            case MSI_Mod11_Mod10:
            case Modified_Plessey:
               this.ibarcode = new MSI(this.rawData, this.encodedType);
               break;
            case UPC_SUPPLEMENTAL_2DIGIT:
               this.ibarcode = new UPCSupplement2(this.rawData);
               break;
            case UPC_SUPPLEMENTAL_5DIGIT:
               this.ibarcode = new UPCSupplement5(this.rawData);
               break;
            case UPCE:
               this.ibarcode = new UPCE(this.rawData);
               break;
            case PostNet:
               this.ibarcode = new Postnet(this.rawData);
               break;
            case EAN8:
               this.ibarcode = new EAN8(this.rawData);
               break;
            case USD8:
            case CODE11:
               this.ibarcode = new Code11(this.rawData);
               break;
            case CODE128:
               this.ibarcode = new Code128(this.rawData);
               break;
            case CODE128A:
               this.ibarcode = new Code128(this.rawData, Code128.TYPES.A);
               break;
            case CODE128B:
               this.ibarcode = new Code128(this.rawData, Code128.TYPES.B);
               break;
            case CODE128C:
               this.ibarcode = new Code128(this.rawData, Code128.TYPES.C);
               break;
            case CODE93:
               this.ibarcode = new Code93(this.rawData);
               break;
            case FIM:
               this.ibarcode = new FIM(this.rawData);
               break;
            case ITF14:
               this.ibarcode = new ITF14(this.rawData);
               break;
            case TELEPEN:
               this.ibarcode = new Telepen(this.rawData);
               break;
            case PHARMACODE:
               this.ibarcode = new Pharmacode(this.rawData);
               break;
            default:
               throw new IllegalArgumentException("EENCODE-2: Unsupported encoding type specified.");
         }

         this.ibarcode.clearErrors();
         this.encodedValue = this.ibarcode.getEncodedValue();
         this.rawData = this.ibarcode.getRawData();
         this.encodedImage = this.generateImage();
         this.setEncodingTime(System.currentTimeMillis() - dtStartTime);
         return this.encodedImage;
      }
   }

   private Image generateImage() {
      if (this.encodedValue.isEmpty()) {
         throw new IllegalArgumentException("EGENERATE_IMAGE-1: Must be encoded first.");
      } else {
         long dtStartTime = System.currentTimeMillis();
         BufferedImage bitmap;
         int ILHeight;
         int topLabelAdjustment;
         int iBarWidth;
         String labTxt;
         int pos;
         Graphics2D g;
         Font labFont;
         switch (this.encodedType) {
            case UPCA:
               if (this.getBarWidth() != null && !this.getEncodedValue().isEmpty()) {
                  this.setWidth(this.getBarWidth() * this.encodedValue.length());
               }

               if (this.getAspectRatio() != null) {
                  this.setHeight((int)((double)this.getWidth() / this.getAspectRatio()));
               }

               ILHeight = this.getHeight();
               topLabelAdjustment = 0;
               int shiftAdjustment = 0;
               shiftAdjustment = this.getWidth() / this.encodedValue.length();
               iBarWidth = this.getShiftAdjustment();
               if (this.isIncludeLabel()) {
                  if ((this.getAlternateLabel() == null || this.getRawData().startsWith(this.getAlternateLabel())) && this.isStandardizeLabel()) {
                     labTxt = this.getRawData();
                     labTxt = labTxt.charAt(0) + "--" + labTxt.substring(1, 6) + "--" + labTxt.substring(7);
                     labFont = this.getLabelFont();
                     labFont = new Font(labFont != null ? labFont.getFamily() : "Serif", 0, Labels.getFontsize(this.getWidth(), this.getHeight(), labTxt));
                     this.setLabelFont(labFont);
                     ILHeight -= labFont.getSize() / 2;
                     shiftAdjustment = this.getWidth() / this.encodedValue.length();
                  } else {
                     if ((this.getLabelPosition().ordinal() & Labels.LabelPositions.TOP.ordinal()) > 0) {
                        topLabelAdjustment = this.getLabelFont().getSize();
                     }

                     ILHeight -= this.getLabelFont().getSize();
                  }
               }

               bitmap = new BufferedImage(this.getWidth(), this.getHeight(), 2);
               if (shiftAdjustment <= 0) {
                  throw new IllegalArgumentException("EGENERATE_IMAGE-2: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel)");
               }

               pos = 0;
               g = bitmap.createGraphics();

               try {
                  ((Graphics)g).setColor(this.getBackColor());
                  ((Graphics)g).fillRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                  ((Graphics)g).setColor(this.getForeColor());

                  for(; pos < this.getEncodedValue().length(); ++pos) {
                     if (this.getEncodedValue().charAt(pos) == '1') {
                        ((Graphics)g).fillRect(pos * shiftAdjustment + iBarWidth, topLabelAdjustment, shiftAdjustment, ILHeight + topLabelAdjustment);
                     }
                  }
               } finally {
                  ((Graphics)g).dispose();
               }

               if (this.isIncludeLabel()) {
                  if ((this.getAlternateLabel() == null || this.getRawData().startsWith(this.getAlternateLabel())) && this.isStandardizeLabel()) {
                     Labels.Label_UPCA(this, bitmap);
                  } else {
                     Labels.labelGeneric(this, bitmap);
                  }
               }
               break;
            case EAN13:
               if (this.getBarWidth() != null) {
                  this.setWidth(this.getBarWidth() * this.getEncodedValue().length());
               }

               if (this.getAspectRatio() != null) {
                  this.setHeight((int)((double)this.getWidth() / this.getAspectRatio()));
               }

               ILHeight = this.getHeight();
               topLabelAdjustment = 0;
               iBarWidth = this.getShiftAdjustment();
               if (this.isIncludeLabel()) {
                  if ((this.getAlternateLabel() == null || this.getRawData().startsWith(this.getAlternateLabel())) && this.isStandardizeLabel()) {
                     String defTxt = this.getRawData();
                     labTxt = defTxt.charAt(0) + "--" + defTxt.substring(1, 6) + "--" + defTxt.substring(7);
                     Font font = this.getLabelFont();
                     labFont = new Font(font != null ? font.getFamily() : "Serif", 0, Labels.getFontsize(this.getWidth(), this.getHeight(), labTxt));
                     this.setLabelFont(labFont);
                     ILHeight -= labFont.getSize() / 2;
                  } else {
                     if ((this.getLabelPosition().ordinal() & Labels.LabelPositions.TOP.ordinal()) > 0) {
                        topLabelAdjustment = this.getLabelFont().getSize();
                     }

                     ILHeight -= this.getLabelFont().getSize();
                  }
               }

               bitmap = new BufferedImage(this.getWidth(), this.getHeight(), 2);
               shiftAdjustment = this.getWidth() / this.getEncodedValue().length();
               if (shiftAdjustment <= 0) {
                  throw new IllegalArgumentException("EGENERATE_IMAGE-2: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel)");
               }

               pos = 0;
               g = bitmap.createGraphics();

               try {
                  ((Graphics)g).setColor(this.getBackColor());
                  ((Graphics)g).fillRect(0, 0, this.getWidth(), this.getHeight());
                  ((Graphics)g).setColor(this.getForeColor());

                  for(; pos < this.getEncodedValue().length(); ++pos) {
                     if (this.getEncodedValue().charAt(pos) == '1') {
                        ((Graphics)g).fillRect(pos * shiftAdjustment + iBarWidth, topLabelAdjustment, shiftAdjustment, ILHeight + topLabelAdjustment);
                     }
                  }
               } finally {
                  ((Graphics)g).dispose();
               }

               if (this.isIncludeLabel()) {
                  if ((this.getAlternateLabel() == null || this.getRawData().startsWith(this.getAlternateLabel())) && this.isStandardizeLabel()) {
                     Labels.Label_EAN13(this, bitmap);
                  } else {
                     Labels.labelGeneric(this, bitmap);
                  }
               }
               break;
            case ITF14:
               if (this.getBarWidth() != null) {
                  this.setWidth((int)(1.362351611079706 * (double)this.encodedValue.length() * (double)this.getBarWidth() + 1.0));
               }

               if (this.getAspectRatio() != null) {
                  this.setHeight((int)((double)this.getWidth() / this.getAspectRatio()));
               }

               ILHeight = this.getHeight();
               if (this.isIncludeLabel()) {
                  ILHeight -= this.getLabelFont().getSize();
               }

               bitmap = new BufferedImage(this.getWidth(), this.getHeight(), 2);
               topLabelAdjustment = (int)((double)this.getWidth() / 12.05);
               iBarWidth = (int)Math.round((double)this.getWidth() * 0.05);
               shiftAdjustment = (this.getWidth() - topLabelAdjustment * 2 - iBarWidth * 2) / this.encodedValue.length();
               pos = (this.getWidth() - topLabelAdjustment * 2 - iBarWidth * 2) % this.encodedValue.length() / 2;
               if (shiftAdjustment > 0 && iBarWidth > 0) {
                  pos = 0;
                  g = bitmap.createGraphics();

                  try {
                     ((Graphics)g).setColor(this.getBackColor());
                     ((Graphics)g).fillRect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                     ((Graphics)g).setColor(this.getForeColor());

                     for(; pos < this.getEncodedValue().length(); ++pos) {
                        if (this.getEncodedValue().charAt(pos) == '1') {
                           ((Graphics)g).fillRect(pos * shiftAdjustment + pos + topLabelAdjustment + iBarWidth, 0, shiftAdjustment, this.getHeight());
                        }
                     }

                     int bearerBarWidth = ILHeight / 8;
                     ((Graphics)g).fillRect(0, 0, this.getWidth(), bearerBarWidth);
                     ((Graphics)g).fillRect(0, ILHeight - bearerBarWidth, this.getWidth(), bearerBarWidth);
                     ((Graphics)g).fillRect(0, 0, bearerBarWidth, ILHeight);
                     ((Graphics)g).fillRect(this.getWidth() - bearerBarWidth, bearerBarWidth, this.getWidth(), ILHeight);
                  } finally {
                     ((Graphics)g).dispose();
                  }

                  if (this.isIncludeLabel()) {
                     Labels.Label_ITF14(this, bitmap);
                  }
                  break;
               }

               throw new IllegalArgumentException("EGENERATE_IMAGE-3: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel or quiet zone determined to be less than 1 pixel)");
            default:
               if (this.getBarWidth() != null) {
                  this.setWidth(this.getBarWidth() * this.getEncodedValue().length());
               }

               if (this.getAspectRatio() != null) {
                  this.setHeight((int)((double)this.getWidth() / this.getAspectRatio()));
               }

               ILHeight = this.getHeight();
               topLabelAdjustment = 0;
               if (this.isIncludeLabel()) {
                  if ((this.getLabelPosition().ordinal() & Labels.LabelPositions.TOP.ordinal()) > 0) {
                     topLabelAdjustment = this.getLabelFont().getSize();
                  }

                  ILHeight -= this.getLabelFont().getSize();
               }

               bitmap = new BufferedImage(this.getWidth(), this.getHeight(), 2);
               iBarWidth = this.getWidth() / this.getEncodedValue().length();
               shiftAdjustment = this.getShiftAdjustment();
               if (iBarWidth <= 0) {
                  throw new IllegalArgumentException("EGENERATE_IMAGE-2: Image size specified not large enough to draw image. (Bar size determined to be less than 1 pixel)");
               }

               pos = 0;
               g = bitmap.createGraphics();

               try {
                  ((Graphics)g).setColor(this.getBackColor());
                  ((Graphics)g).fillRect(0, 0, this.getWidth(), this.getHeight());
                  ((Graphics)g).setColor(this.getForeColor());

                  for(; pos < this.getEncodedValue().length(); ++pos) {
                     if (this.getEncodedType() == Barcode.TYPE.PostNet) {
                        if (this.getEncodedValue().charAt(pos) == '0') {
                           ((Graphics)g).fillRect(pos * iBarWidth + shiftAdjustment, ILHeight / 2 + topLabelAdjustment, iBarWidth / 2, ILHeight / 2 + topLabelAdjustment);
                        } else {
                           ((Graphics)g).fillRect(pos * iBarWidth + shiftAdjustment, topLabelAdjustment, iBarWidth / 2, ILHeight + topLabelAdjustment);
                        }
                     } else if (this.getEncodedValue().charAt(pos) == '1') {
                        ((Graphics)g).fillRect(pos * iBarWidth + shiftAdjustment, topLabelAdjustment, iBarWidth, ILHeight + topLabelAdjustment);
                     }
                  }
               } finally {
                  ((Graphics)g).dispose();
               }

               if (this.isIncludeLabel()) {
                  Labels.labelGeneric(this, bitmap);
               }
         }

         this.encodedImage = bitmap;
         this.setDrawTime(System.currentTimeMillis() - dtStartTime);
         return bitmap;
      }
   }

   public byte[] getImageData(SaveTypes savetype) {
      byte[] imageData = null;

      try {
         if (this.encodedImage != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            this.saveImage((OutputStream)stream, savetype);
            imageData = stream.toByteArray();
            stream.flush();
            stream.close();
         }

         return imageData;
      } catch (Exception var4) {
         Exception ex = var4;
         throw new IllegalStateException("EGETIMAGEDATA-1: Could not retrieve image data. " + ex.getMessage());
      }
   }

   public void saveImage(String filename, SaveTypes fileType) throws IOException {
      try {
         if (this.getEncodedImage() != null) {
            String imageformat = this.getImageFormatFromFileType(fileType);
            ImageIO.write((RenderedImage)this.getEncodedImage(), imageformat, new File(filename));
         }

      } catch (IOException var4) {
         IOException ex = var4;
         throw new IOException("ESAVEIMAGE-1: Could not save image.\n\n=======================\n\n" + ex.getMessage());
      }
   }

   public void saveImage(OutputStream stream, SaveTypes fileType) throws IOException {
      try {
         if (this.getEncodedImage() != null) {
            String imageformat = this.getImageFormatFromFileType(fileType);
            ImageIO.write((RenderedImage)this.getEncodedImage(), imageformat, stream);
         }

      } catch (Exception var4) {
         Exception ex = var4;
         throw new IOException("ESAVEIMAGE-2: Could not save image.\n\n=======================\n\n" + ex.getMessage());
      }
   }

   private String getImageFormatFromFileType(SaveTypes saveType) {
      switch (saveType) {
         case BMP:
            return "bmp";
         case GIF:
            return "gif";
         case PNG:
            return "png";
         case TIFF:
            return "tif";
         case JPG:
         default:
            return "jpg";
      }
   }

   private int getShiftAdjustment() {
      int shiftAdjustment;
      switch (this.getAlignmentPosition()) {
         case LEFT:
            shiftAdjustment = 0;
            break;
         case RIGHT:
            shiftAdjustment = this.getWidth() % this.getEncodedValue().length();
            break;
         case CENTER:
         default:
            shiftAdjustment = this.getWidth() % this.getEncodedValue().length() / 2;
      }

      return shiftAdjustment;
   }

   public static Image DoEncode(TYPE iType, String data) {
      return (new Barcode()).encode(iType, data);
   }

   public static Image DoEncode(TYPE iType, String data, boolean includeLabel) {
      Barcode b = new Barcode();
      b.setIncludeLabel(includeLabel);
      return b.encode(iType, data);
   }

   public static Image DoEncode(TYPE iType, String data, boolean includeLabel, int width, int height) {
      Barcode b = new Barcode();
      b.setIncludeLabel(includeLabel);
      return b.encode(iType, data, width, height);
   }

   public static Image DoEncode(TYPE iType, String data, boolean includeLabel, Color drawColor, Color backColor) {
      Barcode b = new Barcode();
      b.setIncludeLabel(includeLabel);
      return b.encode(iType, data, drawColor, backColor);
   }

   public static Image DoEncode(TYPE iType, String data, boolean includeLabel, Color drawColor, Color backColor, int width, int height) {
      Barcode b = new Barcode();
      b.setIncludeLabel(includeLabel);
      return b.encode(iType, data, drawColor, backColor, width, height);
   }

   public class ImageSize {
      private double width;
      private double height;
      private boolean metric;

      public ImageSize(double width, double height, boolean metric) {
         this.setWidth(width);
         this.setHeight(height);
         this.setMetric(metric);
      }

      public double getWidth() {
         return this.width;
      }

      public void setWidth(double width) {
         this.width = width;
      }

      public double getHeight() {
         return this.height;
      }

      public void setHeight(double height) {
         this.height = height;
      }

      public boolean isMetric() {
         return this.metric;
      }

      public void setMetric(boolean metric) {
         this.metric = metric;
      }
   }

   public static enum AlignmentPositions {
      CENTER,
      LEFT,
      RIGHT;
   }

   public static enum SaveTypes {
      JPG,
      BMP,
      PNG,
      GIF,
      TIFF,
      UNSPECIFIED;
   }

   public static enum TYPE {
      UNSPECIFIED,
      UPCA,
      UPCE,
      UPC_SUPPLEMENTAL_2DIGIT,
      UPC_SUPPLEMENTAL_5DIGIT,
      EAN13,
      EAN8,
      Interleaved2of5,
      Interleaved2of5_Mod10,
      Standard2of5,
      Standard2of5_Mod10,
      Industrial2of5,
      Industrial2of5_Mod10,
      CODE39,
      CODE39Extended,
      CODE39_Mod43,
      Codabar,
      PostNet,
      BOOKLAND,
      ISBN,
      JAN13,
      MSI_Mod10,
      MSI_2Mod10,
      MSI_Mod11,
      MSI_Mod11_Mod10,
      Modified_Plessey,
      CODE11,
      USD8,
      UCC12,
      UCC13,
      LOGMARS,
      CODE128,
      CODE128A,
      CODE128B,
      CODE128C,
      ITF14,
      CODE93,
      TELEPEN,
      FIM,
      PHARMACODE;
   }
}
