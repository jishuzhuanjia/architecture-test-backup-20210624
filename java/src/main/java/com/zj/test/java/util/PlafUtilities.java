package com.zj.test.java.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
 * to create a transparent Window set Frame's backgound color
 * for color like Color(0,0,0,0) and then the subclass need to
 * set Property Opaque see {@code setOpaque}
 * ���ǻ��adapter����Ӧ
 */
public class PlafUtilities {
	
	
	public static Robot robot;
	
	private static Toolkit kit = Toolkit.getDefaultToolkit();

	public static final int width;
	
	public static final int height;
	
	private static final Rectangle maximumWindowBounds;
	
	private static final Rectangle fullScreenBounds;
	

	//��ʼ��
	static {
		    try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			width = kit.getScreenSize().width;
			height = kit.getScreenSize().height;
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			maximumWindowBounds = ge.getMaximumWindowBounds();
			
			fullScreenBounds = new Rectangle(0,0,width,height);
	}

	// System tray
	private static final SystemTray sysTray;
	
	static {
		if (SystemTray.isSupported()) {
			sysTray = SystemTray.getSystemTray();
		} else {
			//��֧��
			sysTray = null;
		}
	}

	public static int getScreenWidth() {
		return width;
	}

	public static int getScreenHeight() {
		return height;
	}
	
	public static Rectangle getFullScreenBounds() {
		return fullScreenBounds;
	}

	public static Rectangle getMaximumWindowBounds(){
		/*Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
		int taskHeight = screenInsets.bottom;
		System.out.println("task height:" +taskHeight);
		System.out.println("scrren height widthout task��" + (height-taskHeight));
		return height - taskHeight;*/
		return maximumWindowBounds;
	}
	
	

	public static void centerWindow(JFrame frame) {
		int x = (int) ((width - frame.getSize().getWidth()) / 2);
		int y = (int) ((height - frame.getSize().getHeight()) / 2);
		frame.setLocation(x, y);

	}
	
	public static void centerWindow(JDialog dialog) {
		int x = (int) ((width - dialog.getSize().getWidth()) / 2);
		int y = (int) ((height - dialog.getSize().getHeight()) / 2);
		dialog.setLocation(x, y);

	}

	/*********** --------------------JButton create------------ *******/

	/**
	 * plain ones; the Insets of button ,left right17 top bottom5; ContentArea
	 * under the icon and text; if setContentAreaFilled(false)��the button will
	 * be transparent because the methos setOpaque default params is false;
	 * 
	 * method setFocusPainted() default is true,draw the border of the content
	 * that set by insets;
	 * 
	 * MinimumSize���ܹ������߾�insets�����ݵ���С�ߴ�; button�����Ź����п������ŵ���С��Ȳ�ȷ����
	 * 
	 * 
	 * 
	 * ������preferredSizeʱ�����ݵĴ�С���ǳߴ��ȥInsets
	 * 
	 * ���UIManger������LookandFeel ContentAreafilled���ܻᱻȡ����
	 * һ�㲻����setContentAreafilled(true)����ȻһЩ�����Ч����ȡ����
	 * 
	 * getMinmumSize()���ص�����С�ߴ�:����
	 * +insets;��getMaxPreferredSize,getPreferredSize��ͬ
	 * 
	 * setPreferredSize���ܱ�����Ҫ�Ŀռ�����С
	 * 
	 * ���������margin���ܻ��Զ����ÿؼ������С��
	 * ����setpreferred������ò�Ҫ����Component��������������JPanel�С���
	 * 
	 * BorderLayout :if add to north ,the later added Component replace the last
	 * Component position; if set the margin attri of north componnet,top and
	 * bottom is ok;
	 * 
	 * 
	 * ������LayoutManager ��setPreferredSize ���� ��setSize + setLocation =setBounds;
	 * 
	 * flowLayout���Ը������ø߶�Ϊ0���಻��������0���򿴲��� flowLayout��Panel���ս�����п�ߡ�
	 * 
	 * 
	 * BoxLayout��������ս�����еĿռ�
	 * 
	 * 
	 * 
	 * ����ʱ��Ӧע���еĲ�������flowlayout������Գߴ���й滮����л����֧�ֳ����谭��
	 **/
	
	/*��������.java�ļ����ǲ��ɼ��ģ��ڱ��ļ��пɼ�
	 * ����Ϊ�Ƿ��ܹ�new����*/
	private PlafUtilities() {}
	

	/**
	 * Add TrayIcon.
	 */
	public static void addTrayIcon(TrayIcon icon) {
		//ϵͳ��֧�֣�ֱ�ӷ���
		if (sysTray == null) {
			System.out.println("System does not support SystemTray");
			return;
		}

		try {

			sysTray.add(icon);
		} catch (AWTException e) {

			e.printStackTrace();
		}

	}

	public static void removeTrayIcon(TrayIcon icon) {
		sysTray.remove(icon);

	}
	/**
	 * capture the screen,if need,save it according the given format name
	 * @param rectangle if is null,capture the whole screen
	 * @param file if file already exist,cover it 
	 * @return if robot is null,return null;
	 * */
	public static BufferedImage captureScreen(Rectangle rectangle,File fileSave,String formatName){
		
		BufferedImage bi = null;
		if (robot != null) {
			bi = rectangle == null ? robot.createScreenCapture(new Rectangle(0, 0, width, height))
					: robot.createScreenCapture(rectangle);
			if (fileSave != null) {
				try {
					ImageIO.write(bi, formatName, fileSave);
					System.out.println("save capture file success");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bi;
	}
	
	public static void main(String[] args){
		//getMaximumWindowBounds();
		new PlafUtilities();
	}
	
}
