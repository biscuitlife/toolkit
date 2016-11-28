package com.derekchou.string;

//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//public class ImageUtil {
//
//	public static boolean compress(String source, String target) {
//		return compress(source, target, Constants.COMPRESS_IMAGE_MINWIDTH, Constants.COMPRESS_IMAGE_MINHIGHT);
//	}
//
//	public static int imageSize(String source){
//		int ret = -1;
//		File file = new File(source);
//		Image img = null;
//		try {
//			img = ImageIO.read(file);
//			if(img!=null)
//				ret = img.getWidth(null);
//		} catch (IOException e) {
//		}
//		return ret;
//	}
//	
//	public static boolean compress(String source, String target, int minwidth, int minhight) {
//		File file = new File(source);
//		FileOutputStream out = null;
//		try {
//			Image img = ImageIO.read(file);
//			System.out.println(img.getWidth(null));
//			int newWidth;
//			int newHeight;
//			double rate1 = ((double) img.getWidth(null)) / (double) minwidth + 0.1;
//			double rate2 = ((double) img.getHeight(null)) / (double) minhight + 0.1;
//			// 根据缩放比率大的进行缩放控制
//			double rate = rate1 > rate2 ? rate1 : rate2;
//			newWidth = (int) (((double) img.getWidth(null)) / rate);
//			newHeight = (int) (((double) img.getHeight(null)) / rate);
//			BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
//			tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
//			out = new FileOutputStream(target);
//			// JPEGImageEncoder可适用于其他图片类型的转换
//			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//			encoder.encode(tag);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return true;
//	}
//}
