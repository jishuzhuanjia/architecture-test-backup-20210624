package com.zj.util.image;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import org.apache.commons.io.FilenameUtils;

/*
 * @author ZhouJian
 * @modifyTime  2019年12月30日 10:29:36
 * @finished  false
 * @finishTime   
 * @version  1.0
 */

/**
 * <p>
 * 图片工具类，包括对图片的基本参数如尺寸、格式的修改 作为拓展，添加了最火的水印功能，使用更加方便
 * <p>
 */
public class ImageUtilities {

	private ImageUtilities() {
	}

	/**
	 * 由File获取BufferedImage对象
	 * 
	 * @param  file  图片File
	 */
	public static BufferedImage getImageFromLocal(File file) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bufferedImage;
	}
	
	/**
	 * 将BufferedImage进行缩放因子缩放，并返回新的BufferedImage
	 * 
	 * @param  originImage  待缩放BufferedImage对象
	 * @param  factor  缩放因子
	 * */
	public static BufferedImage getScaledImage(BufferedImage originImage,double factor) {
		BufferedImage biTemp;
		Image temp;
		
		int newWidth,newHeight;
		newWidth = (int) (originImage.getWidth(null) * factor);
		newHeight = (int) (originImage.getHeight(null) * factor);
		
		temp = originImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		biTemp = new BufferedImage(newWidth,newHeight,originImage.getType());
		
		Graphics g = biTemp.createGraphics();
		g.drawImage(temp, 0, 0,null);
		g.dispose();
		
		return biTemp;
	}

	/**
	 * 由路径名获取BufferedImage对象
	 * 
	 * 
	 * @param  pathname  图片所在路径名
	 */
	public static BufferedImage getImageFromLocal(String pathname) {
		return getImageFromLocal(new File(pathname));
	}
		
	
	/**
	 * see @{@link #scaleLocal2LocalByFactor(File, File, double)}
	 * */
	public static void scaleLocal2LocalByFactor(String scrPath, String srcPath, double factor) {
		scaleLocal2LocalByFactor(new File(scrPath),new File(srcPath),factor);
	}

	/**
	 * 缩放因子缩放：最终长宽等于原始长宽*factor(缩放因子)
	 * 
	 * @param  srcFile  如果文件不存在抛出java.io.FileNotFoundException
	 * @param  desFile  如果文件不存在抛出java.io.FileNotFoundException
	 * @param factor 缩放因子
	 * 
	 */
	public static void scaleLocal2LocalByFactor(File srcFile, File desFile, double factor) {

		if (factor <= 0) {
			throw new IllegalArgumentException("Parameter factor cannot be less than or equal to 0");
		}

		BufferedImage biSrc = null;
		Image temp = null;
		try {
			biSrc = getImageFromLocal(srcFile);
			
			int newWidth = (int) (biSrc.getWidth() * factor);
			int newHeight = (int) (biSrc.getHeight() * factor);

			temp = biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			
			// TYPE_3BYTE_BGR(5) - jpg
			// TYPE_4BYTE_ABGR(6) - png
			// getType方法并不是简单通过后缀名来获取类型，而是通过数据结构。
			// BufferedImage的类型就决定了ImageIO.write的输出数据结构，如当BufferedImage类型为png,存储为jpg，则还是png,依旧能看到透明像素。
			BufferedImage biTemp = new BufferedImage(newWidth, newHeight,biSrc.getType());
			Graphics g = biTemp.createGraphics();
			g.drawImage(temp, 0, 0, null);
			g.dispose();
			
			/*AffineTransformOp affineTransformOp = new AffineTransformOp(AffineTransform
					.getScaleInstance(factor, factor), null);*/
			//save
			ImageIO.write(biTemp, FilenameUtils.getExtension(desFile.getName()), desFile);
			
			/*
			 * 方法2： 
			 * double fw,fh; fw = width / bi.getWidth(); 
			 * fh = height / bi.getHeight();
			 * System.out.println(fw + "" + fh);
			 *  AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(fw, fh),null); 
			 *  temp = ato.filter(bi, null);
			 */
			
			/*
			 * 方法3：创建图形设备兼容图像 
			 * create a BufferedImage compatible width the screen
			 * BufferedImage biOut; 
			 * GraphicsEnvironment ge = raphicsEnvironment.getLocalGraphicsEnvironment(); 
			 * GraphicsDevice gd = ge.getDefaultScreenDevice(); 
			 * GraphicsConfiguration gcf =gd.getDefaultConfiguration(); 
			 * int transparency = Transparency.OPAQUE;
			 * //BITMASK 和 TRANSLUCENT颜色变红，OPAQUE表现正常 biOut
			 * image=gcf.createCompatibleImage(width, height, transparency);
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * see @{@link #scaleLocal2LocalByRagid(File, File, int, int)}
	 * */
	public static void scaleLocal2LocalByRagid(String srcPath, String desPath, int width, int height) {
		scaleLocal2LocalByRagid(new File(srcPath), new File(desPath), width, height);
	}

	/** 
	 * 给定值缩放
	 * <p>
	 * <b>NOTE：</b>此方法可能会导致图形的失真和变形
	 * <p>
	 * 
	 * @see #scaleLocalImage2LocalByFactor(File, File, double)
	 *  */
	public static void scaleLocal2LocalByRagid(File srcFile, File desFile, int newWidth, int newHeight) {
		if(newWidth <= 0 || newHeight <= 0) {
			throw new IllegalArgumentException("Parameters newWidth or newHeight cannot be less than or equal to 0");
		}
		
		BufferedImage biSrc = null;
		Image temp = null;
		try {
			biSrc = getImageFromLocal(srcFile);
			temp = biSrc.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			BufferedImage biTemp = new BufferedImage(newWidth, newHeight, biSrc.getType());
			Graphics g = biTemp.createGraphics();
			g.drawImage(temp, 0, 0, null);
			g.dispose();
			ImageIO.write(biTemp, FilenameUtils.getExtension(desFile.getName()), desFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * 通过质量压缩图片
	 * <p>
	 * quality参数越小压缩越狠,就会越不清楚,经过测试0.4时压缩最好：文件小且图片没有明显的模糊，此时压缩大约70%内存 
	 * <p>
	 * */
	public static byte[] compressImageByQuality(byte[] imgByte, float quality) {
		byte[] inByte = null;
		try {
			ByteArrayInputStream byteInput = new ByteArrayInputStream(imgByte);
			BufferedImage image = ImageIO.read(byteInput);
			if (image == null) {
				return null;
			}
			Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");// 得到迭代器
			ImageWriter writer = iter.next(); // 得到writer
			ImageWriteParam iwp = writer.getDefaultWriteParam();
			iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);// 明确的，显示的
			// 0到1 0的压缩质量设置通常被解释为“高压缩是重要的”，
			iwp.setCompressionQuality(quality); // 设置压缩质量参数
			// 设置渐进模式写入图像
			iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
			ColorModel colorModel = ColorModel.getRGBdefault();
			// 设置目的类型
			iwp.setDestinationType(
					new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
			// 开始打包图片，写入byte[]
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
			// 创建写出IIOImage
			IIOImage iIamge = new IIOImage(image, null, null);
			// 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
			// 设置输出流
			writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
			writer.write(null, iIamge, iwp);
			inByte = byteArrayOutputStream.toByteArray();
			byteInput.close();
			byteArrayOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inByte;
	}
	
	public static BufferedImage addWaterMake(String srcPath, 
			String logoPath,
			Point leftTop,
			float markAlpha,
			int rotateDegree) {
		return addWaterMake(new File(srcPath),new File(logoPath),leftTop,markAlpha,rotateDegree);
		
	}

	/**
	 * 为本地图片添加水印并返回BufferedImage对象
	 * 
	 * 
	 * @param  srcFile  需要添加水印的图片File
	 * @param  markPath  水印File
	 * @param  leftTop  水印图左上角在背景图中的位置，单位px
	 * @param  markAlpha  水印图的透明度
	 * @param  rotateDegree  水印图旋转角度，单位度，顺时针为正。
	 */
	public static BufferedImage addWaterMake(File srcFile, 
			File markPath,
			Point leftTop,
			float markAlpha,
			int rotateDegree) {
		// 不会报错
		BufferedImage biBg = null;
		BufferedImage biMark = null;
		BufferedImage biResult;
		int width, height;
		
		biBg = getImageFromLocal(srcFile);
		biMark = getImageFromLocal(markPath);
		
		width = biBg.getWidth(null);
		height = biBg.getHeight(null);
		biResult = new BufferedImage(width, height, judgeType(biBg, biMark));
		
		// 得到画笔对象
		// Graphics g= buffImg.getGraphics();
		Graphics2D g = biResult.createGraphics();
		// 设置对线段的锯齿状边缘处理 ,差值对缩放、旋转或以其他方式转换的坐标系中呈现时的像素颜色取样
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(biBg, 0, 0, null);
		g.translate(leftTop.x, leftTop.y);
		// 设置水印旋转 ,旋转可能会把原点移动到图片区域之外
		// 旋转按原来原点和指定点连线，绕给定点进行旋转
		g.rotate(Math.toRadians(rotateDegree), // 转换弧度值
				biMark.getWidth(null) / 2, biMark.getHeight(null) / 2);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));
		// 此时原点已经偏移，不需要添加偏移量
		g.drawImage(biMark, 0,0, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.dispose();
		return biResult;
	}
	
	/**
	 * 为BufferedImage对象添加本地磁盘水印，并返回BufferedImage对象
	 * 
	 * @param  biBg  需要添加水印的BufferedImage对象
	 * @param  logoPath  水印文件File
	 * @param  leftTop  水印图左上角在背景图中的位置,单位px
	 * @param  markAlpha  水印图的透明度
	 * @param  rotateDegree  水印图旋转角度，单位度，顺时针为正。
	 * */
	public static BufferedImage addWaterMake(BufferedImage biBg, 
			File logoPath,
			Point leftTop,
			float markAlpha,
			int rotateDegree) {
		// 不会报错
		BufferedImage biMark = null;
		BufferedImage biResult;
		int width, height;
		
		//biBg = getImageFromLocal(srcPath);
		biMark = getImageFromLocal(logoPath);
		
		width = biBg.getWidth(null);
		height = biBg.getHeight(null);
		biResult = new BufferedImage(width, height, judgeType(biBg,biMark));
		
		// 得到画笔对象
		// Graphics g= buffImg.getGraphics();
		Graphics2D g = biResult.createGraphics();
		// 设置对线段的锯齿状边缘处理 ,差值对缩放、旋转或以其他方式转换的坐标系中呈现时的像素颜色取样
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(biBg, 0, 0, null);
		g.translate(leftTop.x, leftTop.y);
		// 设置水印旋转 ,旋转可能会把原点移动到图片区域之外
		// 旋转按原来原点和指定点连线，绕给定点进行旋转
		// 转换弧度值，顺时针为正
		g.rotate(Math.toRadians(rotateDegree), 
				biMark.getWidth(null) / 2, biMark.getHeight(null) / 2);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, markAlpha));
		// 此时原点已经偏移，不需要添加偏移量
		g.drawImage(biMark, 0,0, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g.dispose();
		return biResult;
	}
	
	/**
	 * 根据背景图和水印图类型，判断应该生成的BufferImage type
	 * 
	 * 如果背景图和水印图片之一为png,则最终生成png，否则按照背景图格式生成。
	 * 
	 * @param  bk  背景图
	 * @param  mark  水印图
	 * 
	 * @return  
	 * <pre>
	 * JPEG - {@link java.awt.image.BufferedImage#TYPE_3BYTE_BGR}
	 * PNG - {@link java.awt.image.BufferedImage#TYPE_4BYTE_ABGR}
	 * </pre>
	 * */
	private static int judgeType(BufferedImage bk,BufferedImage mark) {
		int bgType = bk.getType() ;
		int markType = mark.getType();
		int type = bgType;
		if(markType ==BufferedImage.TYPE_4BYTE_ABGR) {
			type = BufferedImage.TYPE_4BYTE_ABGR;
		}
		
		return type;
	}
}
