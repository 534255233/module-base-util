package com.zlp.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {

	/**
	 * 等比例压缩算法： 
	 * 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图 
	 * 
	 * @param input     原图输入流 
	 * @param comBase   压缩基数 
	 * @param scale     压缩限制(宽/高)比例 一般用1：
	 *                  当scale>=1,缩略图height=comBase,width按原图宽高比例;
	 *                  若scale<1,缩略图width=comBase,height按原图宽高比例 
	 * @throws Exception
	 */
	public static RenderedImage genMinImage(InputStream input, double comBase, double scale) throws Exception {
		Image src = ImageIO.read(input);
		final int srcHeight = src.getHeight(null);//图片原来高度
		final int srcWidth = src.getWidth(null);//图片原来宽度
		final double srcScale = (double) srcHeight / srcWidth; //高度 宽度比例
		
		int deskHeight = 0;// 缩略图高
		int deskWidth = 0;// 缩略图宽
		/** 缩略图宽高算法 */
		if ((double) srcHeight > comBase || (double) srcWidth > comBase) { //原图宽高任意一个大于压缩基数
			if (srcScale >= scale) {//原图片的高度大于宽度
				deskHeight = (int) comBase;
				deskWidth = srcWidth * deskHeight / srcHeight;
			} else {//原图片的宽度大于高度
				deskWidth = (int) comBase;
				deskHeight = srcHeight * deskWidth / srcWidth;
			}
		} else {
			deskHeight = srcHeight;
            deskWidth = srcWidth;
		}
		BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
		// 绘制缩小后的图
		tag.getGraphics().drawImage(src.getScaledInstance(deskWidth, deskHeight, Image.SCALE_SMOOTH), 0, 0, deskWidth, deskHeight, null); 
		return tag;
	}
	
	/*/
	public static RenderedImage saveMinPhoto(InputStream input, String deskURL, double comBase, double scale) throws Exception {
		Image src = ImageIO.read(input);
		final int srcHeight = src.getHeight(null);//图片原来高度
		final int srcWidth = src.getWidth(null);//图片原来宽度
		final double srcScale = (double) srcHeight / srcWidth; //高度 宽度比例
		int deskHeight = 0;// 缩略图高
		int deskWidth = 0;// 缩略图宽
		// 缩略图宽高算法 /
		if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			} 
		} else {
            deskHeight = srcHeight;
            deskWidth = srcWidth;
        }

		BufferedImage tag = new BufferedImage(deskWidth, deskHeight, BufferedImage.TYPE_3BYTE_BGR);
		tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
		
//		FileOutputStream deskImage = new FileOutputStream(deskURL); // 输出到文件流
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
//		encoder.encode(tag); // 近JPEG编码
//		deskImage.close();
		
		return tag;
	}//*/

}
