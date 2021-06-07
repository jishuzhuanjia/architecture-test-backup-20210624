package com.zj.util;
/*
 * @Time：2020年1月26日 下午4:22:47
 * @Author：zhoujian
 * @QQ：2025513
 * @Description：
 * @IsFinished：false
 */

public class NullStringException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullStringException() {
		// TODO Auto-generated constructor stub
		super("String is null");
	}

	public NullStringException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NullStringException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NullStringException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NullStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
