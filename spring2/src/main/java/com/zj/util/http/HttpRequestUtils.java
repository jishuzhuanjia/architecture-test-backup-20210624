package com.zj.util.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

import com.zj.util.StreamUtils;

/**
 * @time 2019年11月16日 下午12:03:39
 * @author zhoujian
 * @corporation luku
 * @description:http中格式化工具
 * @finished：true
 * @finishTime: 2019年12月27日 14:33:30
 * @version 1.0
 */
public final class HttpRequestUtils {

	//private static Logger logger = Logger.getLogger(HttpRequestUtils.class);
	
	// default request headers
	public static String DEFAULT_ACCEPT_HEADER = "*/*";
	public static String DEFAULT_CONNECTION_HEADER = "Keep-Alive";
	public static String DEFAULT_USERAGENT_HEADER = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";

	/* default timeout */
	public static int DEFAULT_CONNECTION_TIMEOUT = 5000;
	public static int DEFAULT_READ_TIMEOUT = 15000;

	public static enum ASCIISortType {
		ASC, DESC
	}

	/** 避免实例化 */
	private HttpRequestUtils() {
		
	}

	/**
	 * 签名方法，在内容后拼接"&"+key
	 * 
	 * @return 如果content==null || content.equals("") || key==null || key.equals("") 为true,
	 * 返回null。
	 */
	public static String sign(String content, String key) {
		String signedContent = null;
		if (content != null && !content.equals("") && key != null && !key.equals("")) {
			signedContent = new StringBuilder(content).append("&key=").append(key).toString();
		}
		return signedContent;
	}

	/**
	 * 发送GET请求
	 * 
	 * @param url  请求页面url，不可携带参数，不能为空或者空串或空格。否则IllegalAraugmentException将会被抛出。
	 * @param paramsStr  参数部分字符串，可以为空，如果为空，当作空串来处理
	 * 
	 */
	public static String sendGet(String url, String paramsStr) {

		String resultStr = null;
		if (url != null && !url.trim().equals("")) {
			String newUrl = url + "?" + (paramsStr==null ? "" : paramsStr);
			URL requestUrl;
			URLConnection connection;
			InputStream in = null;

			try {
				requestUrl = new URL(newUrl);
				connection = requestUrl.openConnection();

				// 设置超时时间，0代表无限等待
				// 超时时间指的是建立连接的过程：包括DNS解析，链路传输所用时间
				// 只要服务器域名解析正确，不管指定的资源是否存在都会迅速返回。
				// 一般此设置是针对网络连接状况的，如突然间的断网。
				connection.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT);
				connection.setReadTimeout(DEFAULT_READ_TIMEOUT);

				// 设置请求头
				connection.setRequestProperty("Accept", DEFAULT_ACCEPT_HEADER);
				connection.setRequestProperty("Connection", DEFAULT_CONNECTION_HEADER);
				connection.setRequestProperty("User-Agent", DEFAULT_USERAGENT_HEADER);

				// 发起连接
				connection.connect();
				in = connection.getInputStream();
				
				// 结果字符串
				resultStr = StreamUtils.convertInputStreamAsString(connection.getInputStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				StreamUtils.closeInputStream(in);
			}

		} else {
			throw new IllegalArgumentException("invalid param url");
		}

		return resultStr;
	}

	/**
	 * 发送POST请求:
	 * post请求是body携带参数字符串,这种写法就相当于Content-Type=application/x-www-form-urlencode
	 * 
	 * @param url 请求页面url，请求url，可携带参数,不能为空或者空串或空格。否则IllegalAraugmentException将会被抛出。
	 * @param paramsStr
	 *            消息体内容
	 * 
	 */
	public static String sendPost(String url, String paramsStr) {
		String resultStr = null;

		if (url != null && !url.trim().equals("")) {
			URL requestUrl;
			URLConnection connection;
			InputStream in = null;
			OutputStream out = null;
			PrintWriter writer;

			try {
				requestUrl = new URL(url);
				connection = requestUrl.openConnection();

				// 设置请求头
				connection.setRequestProperty("Accept", DEFAULT_ACCEPT_HEADER);
				connection.setRequestProperty("Connection", DEFAULT_CONNECTION_HEADER);
				connection.setRequestProperty("User-Agent", DEFAULT_USERAGENT_HEADER);
				// 解决上传参数乱码,这是默认头
				// connection.setRequestProperty("Content-Type",
				// "application/x-www-form-urlencoded; charset=utf-8");

				// post请求需要需要开启input,output流
				connection.setDoInput(true);
				connection.setDoOutput(true);

				// post不需要调用此方法
				// connection.connect();

				out = connection.getOutputStream();
				writer = new PrintWriter(out);
				writer.print(paramsStr);
				writer.flush();

				// 获取输入流
				in = connection.getInputStream();
				resultStr = StreamUtils.convertInputStreamAsString(in);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				StreamUtils.closeInputStream(in);
				StreamUtils.closeOutputStream(out);
			}

		}else {
			throw new IllegalArgumentException("invalid param url");
		}
		return resultStr;
	}

	/*
	 * 发送JSON请求
	 */

	/*
	 * static class A1{ String access_token;
	 * 
	 * public String getAccess_token() { return access_token; }
	 * 
	 * public void setAccess_token(String access_token) { this.access_token =
	 * access_token; }
	 * 
	 * }
	 */

	/**
	 * 请求参数字符串格式化工具：参数字符串按照字典序(ASCII)排序
	 * 
	 * @param paramsMap 参数map
	 * 		不能为null或者尺寸为0，否则直接返回null
	 * 
	 * @param ascIISortType
	 * 		枚举值，按照字典升序/降序,可选值ASCIISortType.ASC,ASCIISortType.ASC
	 * 
	 * @param urlEncoded
	 *            是否对参数值进行urlencoding编码
	 * 
	 * @return 
	 * 		如果paramsMap为空或尺寸为0,以及map中key都是空串(包括空格)，返回null，其他情况下返回格式化后的字符串
	 *      返回结果示例：升序+编码：age=23&ced=2321312321dqw&gj=23213123d22&username=z_j%E5%91%A8%E5%81%A5&wdasdge=dasd22&xdasdge=23213123
	 */
	public static String formatParamsMapByASCII(Map<String, Object> paramsMap, ASCIISortType ascIISortType,
			boolean urlEncoded) {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}

		StringBuilder sb;
		String resStr;
		List<Map.Entry<String, Object>> paramsList;

		// sort
		sb = new StringBuilder();
		paramsList = new ArrayList<Map.Entry<String, Object>>(paramsMap.entrySet());

		if (ascIISortType == ASCIISortType.ASC) {
			
			Collections.sort(paramsList, new Comparator<Map.Entry<String, Object>>() {
				public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
					return o1.getKey().compareTo(o2.getKey());
				}
			});
		} else {
			Collections.sort(paramsList, new Comparator<Map.Entry<String, Object>>() {
				
				public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
					return o2.getKey().compareTo(o1.getKey());
				}
			});
		}

		// format params list
		for (Entry<String, Object> entry : paramsList) {
			String key = entry.getKey();
			String val = (String) entry.getValue();

			if (urlEncoded)
				try {
					val = URLEncoder.encode(val, "UTF-8");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}

			//key不能为"" 或类似"  "
			if (!key.trim().equals(""))
				sb.append(key).append("=").append(val).append("&");
		}

		resStr = sb.toString();
		
		//移除最后一个&
		return resStr.equals("") ? null : resStr.substring(0, resStr.length() - 1);
	}

	/**
	 * 获取远程主机ip地址,包括代理。获取真正的客户端ip地址
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		// 一般来说，X-Forwarded-For是用于记录代理信息的，每经过一级代理(匿名代理除外)，代理服务器都会把这次请求的来源IP追加在X-Forwarded-For中
		// X-Forwarded-For: 1.1.1.1, 2.2.2.2, 3.3.3.3
		
		String ipStr = request.getHeader("x-forwarded-for");
		
		// 截取源ip地址
		if(ipStr.indexOf(',') != -1) {
			ipStr = ipStr.substring(0,ipStr.indexOf(','));
		}
		
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("Proxy-Client-IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ipStr == null || ipStr.length() == 0 || "unknown".equalsIgnoreCase(ipStr)) {
			ipStr = request.getRemoteAddr();
		}
		return ipStr;
	}

	/*public static void main(String[] args) {
		// String content = "dsadasqwwda2";
		// System.out.println(sign(content, "hemingiojriof"));
		
		 * sendRequestByGet("https://api.weixin.qq.com/cgi-bin/token",
		 * "grant_type=client_credential&appid=wxd5fa2a6a8bfb5c89&secret=1e3da98bd58f5a96a04486756c4b5349"
		 * );
		 

		
		 * String res = com.luke.util.HttpRequest.sendGet(
		 * "https://www.lkpetq.com/petMaker/admin/user/selectPetAndPerson",
		 * "unionid=o_vAO1cwvsi_PrY1HlH09yoJwt5M");
		 * 
		 * Gson gson = new GsonBuilder().create(); A1 a =gson.fromJson(res, A1.class);
		 * System.out.println(a.getAccess_token());
		 * 
		 * //https://api.weixin.qq.com/wxa/msg_sec_check?access_token=ACCESS_TOKEN res =
		 * com.luke.util.HttpRequest.sendPost(
		 * "https://api.weixin.qq.com/wxa/msg_sec_check",
		 * "access_token="+a.getAccess_token()+"content="+ "123");
		 * System.out.println(res);
		 

		 //发送get请求
		 //String sendGet = sendGet("","");
		 //System.out.println(sendGet);
		
		//Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: -1
		//System.out.println("我爱你".substring(0,-1));

		// HttpRequestUtils hrUtils = new HttpRequestUtils();
		
		//1null
		//System.out.println("1"+null);
	}*/

}
