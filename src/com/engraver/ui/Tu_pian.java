package com.engraver.ui;

import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ImageObserver;
import java.util.Arrays;

public class Tu_pian {
   public static BufferedImage zoomImage(BufferedImage im, double resizeTimes) {
      BufferedImage result = null;

      try {
         int width = im.getWidth();
         int height = im.getHeight();
         int toWidth = (int)((double)width * resizeTimes);
         int toHeight = (int)((double)height * resizeTimes);
         result = new BufferedImage(toWidth, toHeight, 2);
         result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, 4), 0, 0, (ImageObserver)null);
      } catch (Exception var8) {
         Exception e = var8;
         System.out.println("创建缩略图发生异常" + e.getMessage());
      }

      return result;
   }

   private static int colorToRGB(int alpha, int red, int green, int blue) {
      int newPixel = 0;
      newPixel += alpha;
      newPixel <<= 8;
      newPixel += red;
      newPixel <<= 8;
      newPixel += green;
      newPixel <<= 8;
      newPixel += blue;
      return newPixel;
   }

   public static BufferedImage hui_du(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      BufferedImage grayImage = new BufferedImage(width, height, image.getType());

      for(int i = 0; i < width; ++i) {
         for(int j = 0; j < height; ++j) {
            int color = image.getRGB(i, j);
            int a = color >> 24 & 255;
            int r = color >> 16 & 255;
            int g = color >> 8 & 255;
            int b = color & 255;
            //short a;
            if (a < 128) {
               a = 255;
               r = 255;
               g = 255;
               b = 255;
            } else {
               a = 255;
            }

            int gray = 0;
            gray = (int)((double)(r + g + b) / 3.0);
            grayImage.setRGB(i, j, colorToRGB(a, gray, gray, gray));
         }
      }

      image = null;
      return grayImage;
   }

   public static BufferedImage hui_du2(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[] pixels = new int[width * height];
      int[] pixels2 = new int[width * height];
      image.getRGB(0, 0, width, height, pixels, 0, width);
      image = null;

      for(int i = 0; i < pixels.length; ++i) {
         int a = pixels[i] >> 24 & 255;
         int r = pixels[i] >> 16 & 255;
         int g = pixels[i] >> 8 & 255;
         int b = pixels[i] & 255;
         //short a;
         if (a < 128) {
            a = 255;
            r = 255;
            g = 255;
            b = 255;
         } else {
            a = 255;
         }

         int gray = gray = (int)((double)(r + g + b) / 3.0);
         pixels2[i] = colorToRGB(a, gray, gray, gray);
      }

      BufferedImage mBitmap = new BufferedImage(width, height, 2);
      mBitmap.setRGB(0, 0, width, height, pixels2, 0, width);
      pixels = null;
      pixels2 = null;
      return mBitmap;
   }

   public static BufferedImage qu_you_xiao(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[] pixels = new int[width * height];
      image.getRGB(0, 0, width, height, pixels, 0, width);
      int shang = 0;
      int xia = 0;
      int zuo = 0;
      int you = 0;

      int i;
      int j;
      int grey;
      for(i = 0; i < height; ++i) {
         for(j = 0; j < width; ++j) {
            grey = pixels[width * i + j];
            if ((grey & 16777215) != 16777215) {
               shang = i;
               break;
            }
         }

         if (shang > 0) {
            break;
         }
      }

      for(i = height; i > 0; --i) {
         for(j = 0; j < width; ++j) {
            grey = pixels[width * (i - 1) + j];
            if ((grey & 16777215) != 16777215) {
               xia = i;
               break;
            }
         }

         if (xia > 0) {
            break;
         }
      }

      for(i = 0; i < width; ++i) {
         for(j = 0; j < height; ++j) {
            grey = pixels[width * j + i];
            if ((grey & 16777215) != 16777215) {
               zuo = i;
               break;
            }
         }

         if (zuo > 0) {
            break;
         }
      }

      for(i = width; i > 0; --i) {
         for(j = 0; j < height; ++j) {
            grey = pixels[width * j + (i - 1)];
            if ((grey & 16777215) != 16777215) {
               you = i;
               break;
            }
         }

         if (you > 0) {
            break;
         }
      }

      if (zuo - 3 > 0) {
         zuo -= 3;
      }

      if (shang - 3 > 0) {
         shang -= 3;
      }

      if (you + 3 < width) {
         you += 3;
      }

      if (xia + 3 < height) {
         xia += 3;
      }

      return image.getSubimage(zuo, shang, you - zuo, xia - shang);
   }

   public static BufferedImage convertGreyImgByFloyd(BufferedImage img, int zhi) {
      int width = img.getWidth();
      int height = img.getHeight();
      int[] pixels = new int[width * height];
      img.getRGB(0, 0, width, height, pixels, 0, width);
      int[] gray = new int[height * width];

      int e = 0;
      int i;
      int j;
      int g;
      for(e = 0; e < height; ++e) {
         for(i = 0; i < width; ++i) {
            j = pixels[width * e + i];
            g = (j & 16711680) >> 16;
            g += 128 - zhi;
            if (g > 255) {
               g = 255;
            }

            if (g < 0) {
               g = 0;
            }

            gray[width * e + i] = g;
         }
      }

      for(i = 0; i < height; ++i) {
         for(j = 0; j < width; ++j) {
            g = gray[width * i + j];
            if (g >= 128) {
               pixels[width * i + j] = 33554431;
               e = g - 255;
            } else {
               pixels[width * i + j] = -16777216;
               e = g - 0;
            }

            if (j < width - 1 && i < height - 1) {
               gray[width * i + j + 1] += 3 * e / 8;
               gray[width * (i + 1) + j] += 3 * e / 8;
               gray[width * (i + 1) + j + 1] += e / 4;
            } else if (j == width - 1 && i < height - 1) {
               gray[width * (i + 1) + j] += 3 * e / 8;
            } else if (j < width - 1 && i == height - 1) {
               gray[width * i + j + 1] += e / 4;
            }
         }
      }

      img = null;
      BufferedImage mBitmap = new BufferedImage(width, height, 2);
      mBitmap.setRGB(0, 0, width, height, pixels, 0, width);
      return mBitmap;
   }

   public static BufferedImage heibai(BufferedImage bb, int zhi) {
      BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
      int[] pixels = new int[bb.getWidth() * bb.getHeight()];
      nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());

      for(int i = 0; i < pixels.length; ++i) {
         int clr = pixels[i];
         int a = clr >> 24 & 255;
         int r = clr >> 16 & 255;
         int g = clr >> 8 & 255;
         int b = clr & 255;
         int gray = (int)((double)(r + g + b) / 3.0);
         if (gray < zhi) {
            pixels[i] = -16777216;
         } else {
            pixels[i] = 33554431;
         }
      }

      BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
      mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
      bb = null;
      nb = null;
      return mBitmap;
   }

   public static BufferedImage fanse(BufferedImage bb) {
      BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
      int[] pixels = new int[bb.getWidth() * bb.getHeight()];
      nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());

      for(int i = 0; i < pixels.length; ++i) {
         int clr = pixels[i];
         int red = (clr & 16711680) >> 16;
         if (red == 255) {
            pixels[i] = -16777216;
         } else {
            pixels[i] = 33554431;
         }
      }

      BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
      mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
      bb = null;
      nb = null;
      return mBitmap;
   }

   public static BufferedImage jing_xiang_x(BufferedImage bb) {
      BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
      int[] pixels = new int[bb.getWidth() * bb.getHeight()];
      int[] pixels2 = new int[bb.getWidth() * bb.getHeight()];
      nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
      int k = nb.getWidth();
      int g = nb.getHeight();

      for(int i = 0; i < g; ++i) {
         for(int j = 0; j < k; ++j) {
            pixels2[i * k + j] = pixels[i * k + (k - j - 1)];
         }
      }

      BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
      mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels2, 0, nb.getWidth());
      bb = null;
      nb = null;
      return mBitmap;
   }

   public static BufferedImage jing_xiang_y(BufferedImage bb) {
      BufferedImage nb = bb.getSubimage(0, 0, bb.getWidth(), bb.getHeight());
      int[] pixels = new int[bb.getWidth() * bb.getHeight()];
      int[] pixels2 = new int[bb.getWidth() * bb.getHeight()];
      nb.getRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels, 0, nb.getWidth());
      int k = nb.getWidth();
      int g = nb.getHeight();

      for(int i = 0; i < k; ++i) {
         for(int j = 0; j < g; ++j) {
            pixels2[j * k + i] = pixels[(g - j - 1) * k + i];
         }
      }

      BufferedImage mBitmap = new BufferedImage(nb.getWidth(), nb.getHeight(), 2);
      mBitmap.setRGB(0, 0, nb.getWidth(), nb.getHeight(), pixels2, 0, nb.getWidth());
      bb = null;
      nb = null;
      return mBitmap;
   }

   public static BufferedImage qu_lunkuo(BufferedImage img, int zhi) {
      int width = img.getWidth();
      int height = img.getHeight();
      int[] pixels = new int[width * height];
      int[] pixels2 = new int[(width + 2) * (height + 2)];
      Arrays.fill(pixels2, 16777215);
      img.getRGB(0, 0, width, height, pixels, 0, width);
      int[] gray = new int[(height + 2) * (width + 2)];

      int i;
      int j;
      for(i = 0; i < height + 2; ++i) {
         for(j = 0; j < width + 2; ++j) {
            if (i != 0 && j != 0 && j != width + 1 && i != height + 1) {
               int grey = pixels[width * (i - 1) + j - 1];
               int red = (grey & 16711680) >> 16;
               if (red > zhi) {
                  gray[(width + 2) * i + j] = 16777215;
               } else {
                  gray[(width + 2) * i + j] = -16777216;
               }
            } else {
               gray[(width + 2) * i + j] = 16777215;
            }
         }
      }

      height += 2;
      width += 2;

      for(i = 1; i < height; ++i) {
         for(j = 1; j < width; ++j) {
            if (gray[width * i + j] != gray[width * i + (j - 1)]) {
               if (gray[width * i + j] == -16777216) {
                  pixels2[width * i + j] = -16777216;
               } else {
                  pixels2[width * i + (j - 1)] = -16777216;
               }
            }

            if (gray[width * i + j] != gray[width * (i - 1) + j]) {
               if (gray[width * i + j] == -16777216) {
                  pixels2[width * i + j] = -16777216;
               } else {
                  pixels2[width * (i - 1) + j] = -16777216;
               }
            }
         }
      }

      BufferedImage mBitmap = new BufferedImage(width, height, 2);
      mBitmap.setRGB(0, 0, width, height, pixels2, 0, width);
      img = null;
      return mBitmap;
   }

   static int[] getGray(int[] pixels, int width, int height) {
      int[] gray = new int[width * height];

      for(int i = 0; i < width - 1; ++i) {
         for(int j = 0; j < height - 1; ++j) {
            int index = width * j + i;
            int rgba = pixels[index];
            int g = ((rgba & 16711680) >> 16) * 3 + ((rgba & '\uff00') >> 8) * 6 + (rgba & 255) * 1;
            gray[index] = g / 10;
         }
      }

      return gray;
   }

   static int[] getInverse(int[] gray) {
      int[] inverse = new int[gray.length];
      int i = 0;

      for(int size = gray.length; i < size; ++i) {
         inverse[i] = 255 - gray[i];
      }

      return inverse;
   }

   static int[] guassBlur(int[] inverse, int width, int height) {
      int[] guassBlur = new int[inverse.length];

      for(int i = 0; i < width; ++i) {
         for(int j = 0; j < height; ++j) {
            int temp = width * j + i;
            if (i != 0 && i != width - 1 && j != 0 && j != height - 1) {
               int i0 = width * (j - 1) + (i - 1);
               int i1 = width * (j - 1) + i;
               int i2 = width * (j - 1) + i + 1;
               int i3 = width * j + (i - 1);
               int i4 = width * j + i;
               int i5 = width * j + i + 1;
               int i6 = width * (j + 1) + (i - 1);
               int i7 = width * (j + 1) + i;
               int i8 = width * (j + 1) + i + 1;
               int sum = inverse[i0] + 2 * inverse[i1] + inverse[i2] + 2 * inverse[i3] + 4 * inverse[i4] + 2 * inverse[i5] + inverse[i6] + 2 * inverse[i7] + inverse[i8];
               sum /= 16;
               guassBlur[temp] = sum;
            } else {
               guassBlur[temp] = 0;
            }
         }
      }

      return guassBlur;
   }

   static int[] deceasecolorCompound(int[] guassBlur, int[] gray, int width, int height) {
      int[] output = new int[guassBlur.length];

      for(int i = 0; i < width; ++i) {
         for(int j = 0; j < height; ++j) {
            int index = j * width + i;
            int b = guassBlur[index];
            int a = gray[index];
            int temp = a + a * b / (256 - b);
            float ex = (float)(temp * temp) * 1.0F / 255.0F / 255.0F;
            temp = (int)((float)temp * ex);
            a = Math.min(temp, 255);
            output[index] = a;
         }
      }

      return output;
   }

   static BufferedImage create(int[] pixels, int[] output, int width, int height) {
      int i = 0;

      for(int size = pixels.length; i < size; ++i) {
         int gray = output[i];
         int pixel = pixels[i] & -16777216 | gray << 16 | gray << 8 | gray;
         output[i] = pixel;
      }

      BufferedImage mBitmap = new BufferedImage(width, height, 2);
      mBitmap.setRGB(0, 0, width, height, output, 0, width);
      return mBitmap;
   }

   public static BufferedImage su_miao(BufferedImage bitmap) {
      int width = bitmap.getWidth();
      int height = bitmap.getHeight();
      int[] pixels = new int[width * height];
      bitmap.getRGB(0, 0, width, height, pixels, 0, width);
      int[] gray = getGray(pixels, width, height);
      int[] inverse = getInverse(gray);
      int[] guassBlur = guassBlur(inverse, width, height);
      int[] output = deceasecolorCompound(guassBlur, gray, width, height);
      bitmap = null;
      return create(pixels, output, width, height);
   }

   public static BufferedImage su_miao2(BufferedImage old) {
      BufferedImage b1 = hui_du2(old);
      b1 = invert2(b1);
      float[][] matric = gaussian2DKernel(3, 3.0F);
      b1 = convolution(b1, matric);
      b1 = deceaseColorCompound(old, b1);
      ColorSpace cs1 = ColorSpace.getInstance(1003);
      ColorConvertOp op1 = new ColorConvertOp(cs1, (RenderingHints)null);
      BufferedImage b2 = new BufferedImage(old.getWidth(), old.getHeight(), 1);
      op1.filter(b1, b2);
      old = null;
      b1 = null;
      return b2;
   }

   public static BufferedImage shi_liang(BufferedImage old) {
      BufferedImage b1 = discolor(old);
      b1 = invert(b1);
      float[][] matric = gaussian2DKernel(3, 3.0F);
      b1 = convolution(b1, matric);
      b1 = deceaseColorCompound(old, b1);
      ColorSpace cs1 = ColorSpace.getInstance(1003);
      ColorConvertOp op1 = new ColorConvertOp(cs1, (RenderingHints)null);
      BufferedImage b2 = new BufferedImage(old.getWidth(), old.getHeight(), 1);
      op1.filter(b1, b2);
      return b2;
   }

   public static BufferedImage discolor(BufferedImage sourceImage) {
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();
      BufferedImage retImage = new BufferedImage(width, height, 2);

      for(int i = 0; i < width; ++i) {
         for(int j = 0; j < height; ++j) {
            int color1 = sourceImage.getRGB(i, j);
            int a1 = color1 >> 24 & 255;
            int r1 = color1 >> 16 & 255;
            int g1 = color1 >> 8 & 255;
            int b1 = color1 & 255;
            double sumA = (double)a1;
            double sumR = 0.0;
            double sumG = 0.0;
            double sumB = 0.0;
            sumR = sumG = sumB = (double)r1 * 0.299 + (double)g1 * 0.587 + (double)b1 * 0.114;
            int result = (int)sumA << 24 | (int)sumR << 16 | (int)sumG << 8 | (int)sumB;
            retImage.setRGB(i, j, result);
         }
      }

      return retImage;
   }

   public static BufferedImage invert(BufferedImage sourceImage) {
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();
      BufferedImage retImage = new BufferedImage(width, height, 2);

      for(int i = 0; i < width; ++i) {
         for(int j = 0; j < height; ++j) {
            int color1 = sourceImage.getRGB(i, j);
            int a1 = color1 >> 24 & 255;
            int r1 = color1 >> 16 & 255;
            int g1 = color1 >> 8 & 255;
            int b1 = color1 & 255;
            int a = a1;
            int r = 255 - r1;
            int g = 255 - g1;
            int b = 255 - b1;
            int result = a << 24 | r << 16 | g << 8 | b;
            if (result > 255) {
               result = 255;
            }

            retImage.setRGB(i, j, result);
         }
      }

      return retImage;
   }

   public static BufferedImage invert2(BufferedImage sourceImage) {
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();
      int[] pixels = new int[width * height];
      int[] pixels2 = new int[width * height];
      sourceImage.getRGB(0, 0, width, height, pixels, 0, width);

      for(int i = 0; i < pixels.length; ++i) {
         int a1 = pixels[i] >> 24 & 255;
         int r1 = pixels[i] >> 16 & 255;
         int g1 = pixels[i] >> 8 & 255;
         int b1 = pixels[i] & 255;
         int a = a1;
         int r = 255 - r1;
         int g = 255 - g1;
         int b = 255 - b1;
         pixels2[i] = a << 24 | r << 16 | g << 8 | b;
      }

      BufferedImage mBitmap = new BufferedImage(width, height, 2);
      mBitmap.setRGB(0, 0, width, height, pixels2, 0, width);
      pixels = null;
      pixels2 = null;
      return mBitmap;
   }

   public static BufferedImage deceaseColorCompound(BufferedImage sourceImage, BufferedImage targetImage) {
      int width = sourceImage.getWidth() > targetImage.getWidth() ? sourceImage.getWidth() : targetImage.getWidth();
      int height = sourceImage.getHeight() > targetImage.getHeight() ? sourceImage.getHeight() : targetImage.getHeight();
      BufferedImage retImage = new BufferedImage(width, height, 2);

      for(int i = 0; i < width; ++i) {
         for(int j = 0; j < height; ++j) {
            if (i < sourceImage.getWidth() && j < sourceImage.getHeight()) {
               if (i < targetImage.getWidth() && j < targetImage.getHeight()) {
                  int color1 = sourceImage.getRGB(i, j);
                  int color2 = targetImage.getRGB(i, j);
                  int a1 = color1 >> 24 & 255;
                  int r1 = color1 >> 16 & 255;
                  int g1 = color1 >> 8 & 255;
                  int b1 = color1 & 255;
                  int a2 = color2 >> 24 & 255;
                  int r2 = color2 >> 16 & 255;
                  int g2 = color2 >> 8 & 255;
                  int b2 = color2 & 255;
                  int a = deceaseColorChannel(a1, a2);
                  int r = deceaseColorChannel(r1, r2);
                  int g = deceaseColorChannel(g1, g2);
                  int b = deceaseColorChannel(b1, b2);
                  int result = a << 24 | r << 16 | g << 8 | b;
                  retImage.setRGB(i, j, result);
               } else {
                  retImage.setRGB(i, j, sourceImage.getRGB(i, j));
               }
            } else if (i < targetImage.getWidth() && j < targetImage.getHeight()) {
               retImage.setRGB(i, j, targetImage.getRGB(i, j));
            } else {
               retImage.setRGB(i, j, 0);
            }
         }
      }

      return retImage;
   }

   private static int deceaseColorChannel(int source, int target) {
      int result = source + source * target / (256 - target);
      return result > 255 ? 255 : result;
   }

   public static BufferedImage convolution(BufferedImage image, float[][] kernel) {
      int width = image.getWidth();
      int height = image.getHeight();
      int radius = kernel.length / 2;
      BufferedImage retImage = new BufferedImage(width, height, 2);

      for(int i = 0; i < width; ++i) {
         for(int j = 0; j < height; ++j) {
            double sumA = 0.0;
            double sumR = 0.0;
            double sumG = 0.0;
            double sumB = 0.0;

            int x;
            for(x = i - radius; x <= i + radius; ++x) {
               for(int y = j - radius; y <= j + radius; ++y) {
                  int posX = x < 0 ? 0 : (x >= width ? width - 1 : x);
                  int posY = y < 0 ? 0 : (y >= height ? height - 1 : y);
                  int color = image.getRGB(posX, posY);
                  int a = color >> 24 & 255;
                  int r = color >> 16 & 255;
                  int g = color >> 8 & 255;
                  int b = color & 255;
                  int kelX = x - i + radius;
                  int kelY = y - j + radius;
                  sumA += (double)(kernel[kelX][kelY] * (float)a);
                  sumR += (double)(kernel[kelX][kelY] * (float)r);
                  sumG += (double)(kernel[kelX][kelY] * (float)g);
                  sumB += (double)(kernel[kelX][kelY] * (float)b);
               }
            }

            x = (int)sumA << 24 | (int)sumR << 16 | (int)sumG << 8 | (int)sumB;
            retImage.setRGB(i, j, x);
         }
      }

      return retImage;
   }

   public static float[][] gaussian2DKernel(int radius, float sigma) {
      int length = 2 * radius;
      float[][] matric = new float[length + 1][length + 1];
      float sigmaSquare2 = 2.0F * sigma * sigma;
      float sum = 0.0F;

      int x;
      int y;
      for(x = -radius; x <= radius; ++x) {
         for(y = -radius; y <= radius; ++y) {
            matric[radius + x][radius + y] = (float)(Math.pow(Math.E, (double)((float)(-(x * x + y * y)) / sigmaSquare2)) / (Math.PI * (double)sigmaSquare2));
            sum += matric[radius + x][radius + y];
         }
      }

      for(x = 0; x < length; ++x) {
         for(y = 0; y < length; ++y) {
            matric[x][y] /= sum;
         }
      }

      return matric;
   }
}
