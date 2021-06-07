package com.zj.util.security.md;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.zj.util.NullByteArrayException;
import com.zj.util.NullStringException;

/*
 * @Time：2020年1月26日15:45:52
 * @Author：zhoujian
 * @QQ：2025513
 * @Description：
 * @IsFinished：false
 */

/**
 * MD5工具
 * 
 * 摘要32位
 * 
 * 摘要算法主要分为MD，SHA和Hmac算法，摘要算法其实是用于效验数据完整性的，我们在下载某些文件时，
 * 会有MD5和SHA1值提供我们效验下载的文件是否完整，可以用于根据数据生成其唯一的摘要值，无法根据摘要值知道原数据，属于不可逆的
 */
public class MD5Utils {

	private MD5Utils() {
	}

	public static String encode(byte[] originBytes, boolean returnUppercase) 
	throws NullByteArrayException{
		if(originBytes ==null)
			throw new NullByteArrayException();
		
		String resultString = null;
		StringBuilder stringBuilder = new StringBuilder();
		MessageDigest md5 = null;

		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] encodedBytes = md5.digest(originBytes);

			for (int i = 0; i < encodedBytes.length; i++) {
				// OK
				// long val = (100l) & 0xff;

				// 计算会统一类型，右侧类型为int/long

				// 因为Byte,Short没有转换十六进制的方法，所有val定义为int类型
				// byte val = (byte) ((encodedBytes[i]) & 0xff);
				int val = encodedBytes[i] & 0xff;
				if (val < 16)
					stringBuilder.append("0");
				// 不带Ox/OX前缀
				// ab
				// be
				// 56
				// e0
				// System.out.println(Integer.toHexString(val));

				stringBuilder.append(Integer.toHexString(val));
			}
			resultString = returnUppercase ? stringBuilder.toString().toUpperCase() : stringBuilder.toString();

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		return resultString;
	}

	// int转byte会直接取低8位
	public static String encode(String originString, boolean returnUppercase) throws NullStringException,NullByteArrayException {
		if(originString ==null)
			throw new NullStringException();
		
		byte[] originBytes = null;
		try {
			originBytes = originString.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//  如果不想捕获异常，方法就需要跑出throws ..
		return encode(originBytes, returnUppercase);
	}

}
