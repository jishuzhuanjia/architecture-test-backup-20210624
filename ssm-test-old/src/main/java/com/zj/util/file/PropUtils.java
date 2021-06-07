package com.zj.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.zj.util.StreamUtils;

/**
 * @author zhoujian
 * @finished true
 * @finishTime 2019年12月27日 16:46:30
 * @version 1.0
 * */

/* 保存的方法为store，文件注释默认为#/n#时间， 参数添加的内容就是紧跟在第一个#后,可以把参数设置为null第一行#就没有了
 * 加载已有文件到内容用load,
 * 也可以不加载，直接设置到对象中，然后再保存对象
 * Properties不仅可以用于.propertis文件，也可以.txt
 * 
 * {@link com.zj.util.file.PropUtils#getProperties(File)} - ctrl +鼠标点击可以实现跳转
 * {@code} - 输出代码 
 * */

/**
 * 属性文件读取工具，支持.properties,.txt，甚至是所有类型。只要是内容满足key-value格式，都可以读取，
 * 当然保存的时候也可以保存为各种拓展类型。
 * 
 * <<pre>
 * 常用添加/设置属性方法：{@link #setProperty(File, String, String)},{@link #setProperty(String, String, String)}
 * 常用获取属性方法：{@link #getProperty(File, Object)},{@link #getProperty(String, Object)}
 * </pre>
 * */
public class PropUtils {
	
	/**
	 * 将设置更改/存储到本地文件中
	 * 
	 * @param  file  后缀可为任意的文件File对象
	 * @param  key  如果为null,抛出IllegalArgumentException,key可以为""，但是整个文件中只能有一个
	 * @param value 如果为null,抛出IllegalArgumentException
	 * */
	public static void setProperty(File file,String key,String value) {
		
		if(key==null || value ==null) {
			throw new IllegalArgumentException("param key or value is null");
		}
		
		Properties prop = getProperties(file);
		if(prop!=null) {
			prop.setProperty(key, value);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				
				/*
				 * comments，会被添加到文件第一行。多次添加的comments会替换之前的comments
				 * 第二行是#Fri Dec 27 16:58:41 CST 2019，表示最后修改的时间。
				 * */
				prop.store(fos,null);
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				StreamUtils.closeOutputStream(fos);
			}
		}
		
	}

	public static void setProperty(String path,String key,String value) {
		setProperty(new File(path),key,value);
	}
	
	public static String getProperty(File file,Object key) {
		
		//参数合法检测，不满足直接返回null
		if(file==null || !file.exists() || file.isDirectory() || key==null) {
			return null;
		}
		
		//文件必定存在，则直接加载
		Properties properties = getProperties(file);
		return properties.getProperty((String) key);
		
	}
	
	public static String getProperty(String file,Object key) {
		return getProperty(new File(file),key);
	}
	
	/**返回由指定文件加载的prop对象,当文件不存在或者为目录时返回Null
	 * */
	private static Properties getProperties(File file) {
		Properties prop = new Properties();
		
		if(!file.exists()||file.isDirectory() || file.isDirectory()) {
			return prop;
		}
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			StreamUtils.closeInputStream(fis);
		}
		return prop;
	}

	/*public static void main(String[] args) {
		//getProperties(new File("C:\\Users\\Administrator\\Desktop\\表单管理\\演示模板.properties"));
	    //read(new File("C:\\Users\\Administrator\\Desktop\\表单管理\\演示模板.properties"),null);
	    //setProperty("C:\\Users\\Administrator\\Desktop\\12.properties","","333323");
	   System.out.println( getProperty("C:\\Users\\Administrator\\Desktop\\12.xx",""));
	    //getProperty("C:\\Users\\Administrator\\Desktop\\1.txt","key1");
		
		文件最终内容：
		#1 - 这个是comments
		#Fri Dec 27 16:35:04 CST 2019
		key2=dddww
		=23
	}*/
}