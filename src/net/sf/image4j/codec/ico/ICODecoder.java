package net.sf.image4j.codec.ico;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import net.sf.image4j.codec.bmp.BMPDecoder;
import net.sf.image4j.codec.bmp.ColorEntry;
import net.sf.image4j.codec.bmp.InfoHeader;
import net.sf.image4j.io.CountingInputStream;
import net.sf.image4j.io.EndianUtils;
import net.sf.image4j.io.LittleEndianInputStream;

public class ICODecoder {
	private static Logger log = Logger.getLogger(ICODecoder.class.getName());
	private static final int PNG_MAGIC = -1991225785;
	private static final int PNG_MAGIC_LE = 1196314761;
	private static final int PNG_MAGIC2 = 218765834;
	private static final int PNG_MAGIC2_LE = 169478669;

	private ICODecoder() {
	}

	public static List read(File file) throws IOException {
		FileInputStream fin = new FileInputStream(file);

		List var2;
		try {
			var2 = read((InputStream)(new BufferedInputStream(fin)));
		} finally {
			try {
				fin.close();
			} catch (IOException var9) {
				log.log(Level.FINE, "Failed to close file input for file " + file);
			}

		}

		return var2;
	}

	public static List readExt(File file) throws IOException {
		FileInputStream fin = new FileInputStream(file);

		List var2;
		try {
			var2 = readExt((InputStream)(new BufferedInputStream(fin)));
		} finally {
			try {
				fin.close();
			} catch (IOException var9) {
				IOException ex = var9;
				log.log(Level.WARNING, "Failed to close file input for file " + file, ex);
			}

		}

		return var2;
	}

	public static List read(InputStream is) throws IOException {
		List list = readExt(is);
		List ret = new ArrayList(list.size());

		for(int i = 0; i < list.size(); ++i) {
			ICOImage icoImage = (ICOImage)list.get(i);
			BufferedImage image = icoImage.getImage();
			ret.add(image);
		}

		return ret;
	}

	@SuppressWarnings("unchecked")
	private static IconEntry[] sortByFileOffset(IconEntry[] entries) {
		List list = Arrays.asList(entries);
		Collections.sort(list, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return ((IconEntry)o1).iFileOffset - ((IconEntry)o2).iFileOffset;
			}
		});
		return (IconEntry[])list.toArray(new IconEntry[list.size()]);
	}

	public static List readExt(InputStream is) throws IOException {
		LittleEndianInputStream in = new LittleEndianInputStream(new CountingInputStream(is));
		short sReserved = in.readShortLE();
		short sType = in.readShortLE();
		short sCount = in.readShortLE();
		IconEntry[] entries = new IconEntry[sCount];

		for(int i = 0; i < sCount; i = (short)(i + 1)) {
			entries[i] = new IconEntry(in);
		}

		List ret = new ArrayList(sCount);

		int i = 0;
		try {
			for(i = 0; i < sCount; ++i) {
				int fileOffset = in.getCount();
				if (fileOffset != entries[i].iFileOffset) {
					throw new IOException("Cannot read image #" + i + " starting at unexpected file offset.");
				}

				int info = in.readIntLE();
				log.log(Level.FINE, "Image #" + i + " @ " + in.getCount() + " info = " + EndianUtils.toInfoString(info));
				if (info != 40) {
					if (info != 1196314761) {
						throw new IOException("Unrecognized icon format for image #" + i);
					}

					int info2 = in.readIntLE();
					if (info2 != 169478669) {
						throw new IOException("Unrecognized icon format for image #" + i);
					}

					IconEntry e = entries[i];
					int size = e.iSizeInBytes - 8;
					byte[] pngData = new byte[size];
					in.readFully(pngData);
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					DataOutputStream dout = new DataOutputStream(bout);
					dout.writeInt(-1991225785);
					dout.writeInt(218765834);
					dout.write(pngData);
					byte[] pngData2 = bout.toByteArray();
					ByteArrayInputStream bin = new ByteArrayInputStream(pngData2);
					ImageInputStream input = ImageIO.createImageInputStream(bin);
					ImageReader reader = getPNGImageReader();
					reader.setInput(input);
					BufferedImage img = reader.read(0);
					IconEntry iconEntry = entries[i];
					ICOImage icoImage = new ICOImage(img, (InfoHeader)null, iconEntry);
					icoImage.setPngCompressed(true);
					icoImage.setIconIndex(i);
					ret.add(icoImage);
				} else {
					InfoHeader infoHeader = BMPDecoder.readInfoHeader(in, info);
					InfoHeader andHeader = new InfoHeader(infoHeader);
					andHeader.iHeight = infoHeader.iHeight / 2;
					InfoHeader xorHeader = new InfoHeader(infoHeader);
					xorHeader.iHeight = andHeader.iHeight;
					andHeader.sBitCount = 1;
					andHeader.iNumColors = 2;
					BufferedImage xor = BMPDecoder.read(xorHeader, in);
					BufferedImage img = new BufferedImage(xorHeader.iWidth, xorHeader.iHeight, 2);
					ColorEntry[] andColorTable = new ColorEntry[]{new ColorEntry(255, 255, 255, 255), new ColorEntry(0, 0, 0, 0)};
					//int y;
					//int x;
					//int r;
					if (infoHeader.sBitCount == 32) {
						int size = entries[i].iSizeInBytes;
						int infoHeaderSize = infoHeader.iSize;
						int dataSize = xorHeader.iWidth * xorHeader.iHeight * 4;
						int skip = size - infoHeaderSize - dataSize;
						int skip2 = entries[i].iFileOffset + size - in.getCount();
						if (in.skip(skip, false) < skip && i < sCount - 1) {
							throw new EOFException("Unexpected end of input");
						}

						WritableRaster srgb = xor.getRaster();
						WritableRaster salpha = xor.getAlphaRaster();
						WritableRaster rgb = img.getRaster();
						WritableRaster alpha = img.getAlphaRaster();

						for(int y = xorHeader.iHeight - 1; y >= 0; --y) {
							for(int x = 0; x < xorHeader.iWidth; ++x) {
								int r = srgb.getSample(x, y, 0);
								int g = srgb.getSample(x, y, 1);
								int b = srgb.getSample(x, y, 2);
								int a = salpha.getSample(x, y, 0);
								rgb.setSample(x, y, 0, r);
								rgb.setSample(x, y, 1, g);
								rgb.setSample(x, y, 2, b);
								alpha.setSample(x, y, 0, a);
							}
						}
					} else {
						BufferedImage and = BMPDecoder.read(andHeader, in, andColorTable);
						WritableRaster srgb = xor.getRaster();
						WritableRaster rgb = img.getRaster();
						WritableRaster alpha = img.getAlphaRaster();
						WritableRaster salpha = and.getRaster();

						for(int y = 0; y < xorHeader.iHeight; ++y) {
							for(int x = 0; x < xorHeader.iWidth; ++x) {
								x = xor.getRGB(x, y);
								int r = x >> 16 & 255;
							int g = x >> 8 & 255;
							y = x & 255;
							rgb.setSample(x, y, 0, r);
							rgb.setSample(x, y, 1, g);
							rgb.setSample(x, y, 2, y);
							r = and.getRGB(x, y);
							alpha.setSample(x, y, 0, r);
							}
						}
					}

					IconEntry iconEntry = entries[i];
					ICOImage icoImage = new ICOImage(img, infoHeader, iconEntry);
					icoImage.setPngCompressed(false);
					icoImage.setIconIndex(i);
					ret.add(icoImage);
				}
			}

			return ret;
		} catch (IOException var31) {
			IOException ex = var31;
			throw new IOException("Failed to read image # " + i, ex);
		}
	}

	private static ImageReader getPNGImageReader() {
		ImageReader ret = null;
		Iterator itr = ImageIO.getImageReadersByFormatName("png");
		if (itr.hasNext()) {
			ret = (ImageReader)itr.next();
		}

		return ret;
	}
}
