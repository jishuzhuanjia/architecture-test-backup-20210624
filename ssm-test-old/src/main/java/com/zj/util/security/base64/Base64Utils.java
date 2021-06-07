package com.zj.util.security.base64;
/*
 * @Time：2020年1月26日 下午5:16:38
 * @Author：zhoujian
 * @QQ：2025513
 * @Description：
 * @IsFinished：false
 */

/**
 * 64位
 * 可逆的，安全度较低，不过，也可以作为最基础最简单的加密算法用于加密要求较弱的情况
 * 
 * */
public class Base64Utils {

	private Base64Utils() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 编码字符串
	 * 
	 * */
	public static String encodeString(String string) {
		return org.springframework.util.Base64Utils.encodeToString(string.getBytes());
	}
	
	public static byte[] encode(byte[] bytes) {
		return org.springframework.util.Base64Utils.encode(bytes);
	}
	
	public static byte[] decode(byte[] bytes) {
		return org.springframework.util.Base64Utils.decode(bytes);
	}
	
	public static void main(String[] args) {
		String encode = encodeString("周健");
		System.out.println(encode);
		
		String decode =new String(decode(encode.getBytes())) ;
		System.out.println(decode);
		
		byte[] encodeBytes = encode("周健".getBytes());
		System.out.println(new String(decode(encodeBytes)));
	}

}
