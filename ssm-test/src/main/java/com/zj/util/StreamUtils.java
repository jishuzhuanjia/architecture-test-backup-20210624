package com.zj.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * @time 2019年11月21日 下午5:46:36
 * @author zhoujian
 * @corporation luku
 * @description 流工具
 */
public class StreamUtils {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(StreamUtils.class);
	
	/**
	 * 将输入流转换为String
	 */
	public static String convertInputStreamAsString(InputStream in) {
		String line, res;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader;
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		try {
			// readLine - 如果到达文件尾部，返回null
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeInputStream(in);
		}

		res = stringBuilder.toString();

		// logger.info("get请求：" + url + "返回结果：" + result);
		return res;
	}

	/**
	 * 关闭输入流
	 */
	public static void closeInputStream(InputStream in) {
		if (null != in) {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** 关闭输出流 */
	public static void closeOutputStream(OutputStream out) {
		if (null != out) {
			try {
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
