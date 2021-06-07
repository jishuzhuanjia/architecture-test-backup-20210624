package com.zj.util;
/*
 * @Time：2020年1月26日 下午4:22:47
 * @Author：zhoujian
 * @QQ：2025513
 * @Description：
 * @IsFinished：false
 */

public class NullByteArrayException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullByteArrayException() {
		// TODO Auto-generated constructor stub
		super("Byte array is null");
	}

	public NullByteArrayException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NullByteArrayException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NullByteArrayException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NullByteArrayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
