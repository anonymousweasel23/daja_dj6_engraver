package io.nayuki.qrcodegen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

public final class QrCodeGeneratorDemo {
   public static void main(String[] args) throws IOException {
      doBasicDemo();
      doVarietyDemo();
      doSegmentDemo();
      doMaskDemo();
   }

   private static void doBasicDemo() throws IOException {
      String text = "Hello, world!";
      QrCode.Ecc errCorLvl = QrCode.Ecc.LOW;
      QrCode qr = QrCode.encodeText(text, errCorLvl);
      BufferedImage img = qr.toImage(10, 4);
      File imgFile = new File("hello-world-QR.png");
      ImageIO.write(img, "png", imgFile);
      String svg = qr.toSvgString(4);
      File svgFile = new File("hello-world-QR.svg");
      Files.write(svgFile.toPath(), svg.getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
   }

   private static void doVarietyDemo() throws IOException {
      QrCode qr = QrCode.encodeText("314159265358979323846264338327950288419716939937510", QrCode.Ecc.MEDIUM);
      writePng(qr.toImage(13, 1), "pi-digits-QR.png");
      qr = QrCode.encodeText("DOLLAR-AMOUNT:$39.87 PERCENTAGE:100.00% OPERATIONS:+-*/", QrCode.Ecc.HIGH);
      writePng(qr.toImage(10, 2), "alphanumeric-QR.png");
      qr = QrCode.encodeText("こんにちwa、世界！ αβγδ", QrCode.Ecc.QUARTILE);
      writePng(qr.toImage(10, 3), "unicode-QR.png");
      qr = QrCode.encodeText("Alice was beginning to get very tired of sitting by her sister on the bank, and of having nothing to do: once or twice she had peeped into the book her sister was reading, but it had no pictures or conversations in it, 'and what is the use of a book,' thought Alice 'without pictures or conversations?' So she was considering in her own mind (as well as she could, for the hot day made her feel very sleepy and stupid), whether the pleasure of making a daisy-chain would be worth the trouble of getting up and picking the daisies, when suddenly a White Rabbit with pink eyes ran close by her.", QrCode.Ecc.HIGH);
      writePng(qr.toImage(6, 10), "alice-wonderland-QR.png");
   }

   private static void doSegmentDemo() throws IOException {
      String silver0 = "THE SQUARE ROOT OF 2 IS 1.";
      String silver1 = "41421356237309504880168872420969807856967187537694807317667973799";
      QrCode qr = QrCode.encodeText(silver0 + silver1, QrCode.Ecc.LOW);
      writePng(qr.toImage(10, 3), "sqrt2-monolithic-QR.png");
      List segs = Arrays.asList(QrSegment.makeAlphanumeric(silver0), QrSegment.makeNumeric(silver1));
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.LOW);
      writePng(qr.toImage(10, 3), "sqrt2-segmented-QR.png");
      String golden0 = "Golden ratio φ = 1.";
      String golden1 = "6180339887498948482045868343656381177203091798057628621354486227052604628189024497072072041893911374";
      String golden2 = "......";
      qr = QrCode.encodeText(golden0 + golden1 + golden2, QrCode.Ecc.LOW);
      writePng(qr.toImage(8, 5), "phi-monolithic-QR.png");
      segs = Arrays.asList(QrSegment.makeBytes(golden0.getBytes(StandardCharsets.UTF_8)), QrSegment.makeNumeric(golden1), QrSegment.makeAlphanumeric(golden2));
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.LOW);
      writePng(qr.toImage(8, 5), "phi-segmented-QR.png");
      String madoka = "「魔法少女まどか☆マギカ」って、　ИАИ　ｄｅｓｕ　κα？";
      qr = QrCode.encodeText(madoka, QrCode.Ecc.LOW);
      writePng(qr.toImage(9, 4), "madoka-utf8-QR.png");
      segs = Arrays.asList(QrSegmentAdvanced.makeKanji(madoka));
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.LOW);
      writePng(qr.toImage(9, 4), "madoka-kanji-QR.png");
   }

   private static void doMaskDemo() throws IOException {
      List segs = QrSegment.makeSegments("https://www.nayuki.io/");
      QrCode qr = QrCode.encodeSegments(segs, QrCode.Ecc.HIGH, 1, 40, -1, true);
      writePng(qr.toImage(8, 6), "project-nayuki-automask-QR.png");
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.HIGH, 1, 40, 3, true);
      writePng(qr.toImage(8, 6), "project-nayuki-mask3-QR.png");
      segs = QrSegment.makeSegments("維基百科（Wikipedia，聆聽i/ˌwɪkᵻˈpiːdi.ə/）是一個自由內容、公開編輯且多語言的網路百科全書協作計畫");
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 0, true);
      writePng(qr.toImage(10, 3), "unicode-mask0-QR.png");
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 1, true);
      writePng(qr.toImage(10, 3), "unicode-mask1-QR.png");
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 5, true);
      writePng(qr.toImage(10, 3), "unicode-mask5-QR.png");
      qr = QrCode.encodeSegments(segs, QrCode.Ecc.MEDIUM, 1, 40, 7, true);
      writePng(qr.toImage(10, 3), "unicode-mask7-QR.png");
   }

   private static void writePng(BufferedImage img, String filepath) throws IOException {
      ImageIO.write(img, "png", new File(filepath));
   }
}
