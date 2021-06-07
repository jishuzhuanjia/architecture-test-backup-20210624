package com.zj.util.security.md;
/*
 * @Time：2020年1月26日 下午6:00:30
 * @Author：zhoujian
 * @QQ：2025513
 * @Description：
 * @IsFinished：false
 */

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 了解即可
 * 
 * */
public class HmacUtils {

	private HmacUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static byte[] encode() {
		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance("HmacMD5");
			SecretKey secretKey = keyGenerator.generateKey();
			//获得密钥-默认生成
			//byte[] key = secretKey.getEncoded();
			
			byte[] key = "aaaaaaaaaa".getBytes();
			
			//还原密钥
			SecretKey secretKey2 = new SecretKeySpec(key, "HmacMD5");
			//实例化Mac
			Mac mac = Mac.getInstance(secretKey2.getAlgorithm());
			mac.init(secretKey2);
			
			byte[] hmacBytes = mac.doFinal("周健".getBytes("utf-8"));
			System.out.println(new String(hmacBytes));
			
			mac.init(secretKey);
			System.out.println(new String(mac.doFinal(hmacBytes),"utf-8"));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		encode();
	}
	

}
