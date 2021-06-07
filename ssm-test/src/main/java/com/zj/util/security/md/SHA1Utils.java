package com.zj.util.security.md;
/*
 * @Time：2020年1月26日 下午5:38:50
 * @Author：zhoujian
 * @QQ：2025513
 * @Description：
 * @IsFinished：false
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;
import com.zj.util.HexUtils;
import com.zj.util.NullByteArrayException;

/**
 * 只使用SHA1
 * */
public class SHA1Utils {

	private SHA1Utils() {}
	
	public static String encode(String originString) {
		MessageDigest digest = null;
		String resString = null;
		try {
			// SHA1/SHA是一样的
			digest = MessageDigest.getInstance("SHA");
			//utf-8
			digest.update(originString.getBytes("utf-8"));
			resString = HexUtils.toHexString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NullByteArrayException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return resString;
	}
	
	public static void main(String[] args) {
		//  c1b7f147cd8d012c8e10bc0f8626028a035c301b
		System.out.println(encode("周健"));
		
		// utf-8
		// c1b7f147cd8d012c8e10bc0f8626028a035c301b
		System.out.println(new SimpleHash("SHA1", "周健", null, 1));
	}

}
