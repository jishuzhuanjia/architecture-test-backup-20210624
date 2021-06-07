package com.zj.util.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @time 2019�?11�?25�? 下午12:21:04
 * @author zhoujian
 * @corporation luku
 * @description: http请求结果Bean�?
 */
public class ResultBean {
	private int errcode;
	private String msg;

	/**
	 * SUCCESS：请求被接受 + 成功返回 
	 * FAILED：请求被接受 + 处理失败 
	 * DENIED：请求被拒绝 
	 * EXCEPTION：请求被接受 + 出现异常
	 */
	public static enum Result {
		SUCCESS, FAILED, EXCEPTION
	}

	/**
	 * default：SUCCESS
	 * 
	 */
	public ResultBean() {
		errcode = 10000;
		msg = "success";
	}

	/**
	 * 指定状�?�码，会使用默认的msg提示文本
	 */
	public ResultBean(Result result) {
		switch (result) {
		case SUCCESS:
			errcode = 10000;
			msg = "success";
			break;
		case FAILED:
			errcode = 10001;
			msg = "the request was processed, but failed";
			break;
		case EXCEPTION:
			errcode = 10002;
			msg = "an exception occurred during the processing of the request";
			break;
		default:
			break;

		}
	}
	
	/**
	 * 用指定的错误消息进行构�??,但是Result只能是已有的枚举类型
	 * */
	public ResultBean(Result result,String msg) {
		switch (result) {
		case SUCCESS:
			errcode = 10000;
			break;
		case FAILED:
			errcode = 10001;
			break;
		case EXCEPTION:
			errcode = 10002;
			break;
		default:
			break;
		}
		
		this.msg = msg;
	}
	
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public int getErrcode() {
		return errcode;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	
	public static void main(String[] args) {
		ResultBean resultBean = new ResultBean(Result.EXCEPTION);
		ObjectMapper oMapper = new ObjectMapper();
		try {
			System.err.println(oMapper.writeValueAsString(resultBean));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
