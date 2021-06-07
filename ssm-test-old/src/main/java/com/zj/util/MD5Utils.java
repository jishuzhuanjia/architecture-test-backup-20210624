package com.zj.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @time 2019年11月16日 上午11:34:24
 * @author zhoujian
 * @corporation luku
 * @description 
 * @finished false
 * @finishTime 
 * @version 1.0
 */
public class MD5Utils {

	private MD5Utils() {
	}

	/**
	 * MD5编码
	 * 
	 * @param originString
	 *            原始字符串
	 * @param uppercase
	 *            编码结果是否以大写形式返回
	 *            
	 * @return 如果originString==null,则直接返回null。
	 */
	public static String encode(String originString, boolean uppercase) {
		String resultString = null;
		if (originString != null) {
			StringBuilder sb = new StringBuilder();
			MessageDigest md5 = null;

			try {
				md5 = MessageDigest.getInstance("MD5");
				byte[] originBytes = originString.getBytes("utf-8");
				byte[] encodedBytes = md5.digest(originBytes);

				for (int i = 0; i < encodedBytes.length; i++) {
					int val = ((int) encodedBytes[i]) & 0xff;

					if (val < 16)
						sb.append("0");
					sb.append(Integer.toHexString(val));
				}

				resultString = uppercase ? sb.toString().toUpperCase() : sb.toString();

			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static void main(String[] args) {
		// System.out.println(encode("", true));

		// formatParamsByASCII测试
		// HashMap<String, String> paramsMap = new HashMap<String, String>();
		// paramsMap.put("username", "z_j周健");
		// paramsMap.put("age", "23");
		// paramsMap.put("xdasdge", "23213123");
		// paramsMap.put("wdasdge", "dasd22");
		// paramsMap.put("gj", "23213123d22");
		// paramsMap.put("ced", "2321312321dqw");
		// //升序+编码：age=23&ced=2321312321dqw&gj=23213123d22&username=z_j%E5%91%A8%E5%81%A5&wdasdge=dasd22&xdasdge=23213123
		// System.out.println("升序+编码：" + formatParamsMapByASCII(paramsMap,
		// MD5Utils.ASCIISortType.ASC, true));
	}
}
