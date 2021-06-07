package com.zj.util.wx;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zj.util.MD5Utils;
import com.zj.util.StreamUtils;
import com.zj.util.http.HttpRequestUtils;
import com.zj.util.string.StringUtils;

/**
 * @time 2019年12月18日 下午3:13:29
 * @author zhoujian
 * @corporation luku
 * @description: 微信支付工具
 */

public class WxPayUtils {
	// appid
	private static final String appid = "";

	// 商户号
	private static final String mch_id = "";
	private static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private static final String key = "tempkey";

	/*
	 * 下单参数 必填： appid String(32)。 mch_id String(32)。 nonce_str String(32) - 临时随机字符串。
	 * out_trade_no - 商户点单号，商户系统内唯一。 total_fee Int - 标价金额,单位为分。
	 * spbill_create_ip-String(64) - 终端IP notify_url String(256) - 通知地址，不能携带参数
	 * trade_type String(16) -
	 * 交易类型，JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付，
	 * 不同trade_type决定了调起支付的方式，请根据支付产品正确上传 body - 商品描述 sign String(64) - 签名：MD5(字典序
	 * +key).uppercase()
	 * 
	 * 选填
	 * 
	 * 
	 */
	public static Map<String, String> pay(String body, BigDecimal total_fee, String spbill_create_ip,
			String notify_url) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		// 必须参数
		paramsMap.put("appid", appid);
		paramsMap.put("mch_id", mch_id);
		String nonceStr = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
		String outTradeNo = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
		paramsMap.put("nonce_str", nonceStr);
		paramsMap.put("out_trade_no", outTradeNo);
		paramsMap.put("total_fee", total_fee.toString());
		paramsMap.put("spbill_create_ip", spbill_create_ip);
		paramsMap.put("notify_url", notify_url);
		paramsMap.put("trade_type", "JSAPI");
		paramsMap.put("body", body);
		// System.out.println(HttpRequestUtils.formatParamsMapByASCII(paramsMap,HttpRequestUtils.ASCIISortType.ASC
		// , true));
		String signStr = HttpRequestUtils.sign(
				HttpRequestUtils.formatParamsMapByASCII(paramsMap, HttpRequestUtils.ASCIISortType.ASC, true), key);
		paramsMap.put("sign", MD5Utils.encode(signStr, true));
		
		// 发送post请求
		String xmlStr = mapToXML(paramsMap);
		System.err.println(xmlStr);
		String result = HttpRequestUtils.sendPost(UNIFIEDORDER_URL, xmlStr);
		System.err.println(result);
		
		Map<String, String> resultMap = xmlToMap(result);
		Iterator<Map.Entry<String, String>> iterator = resultMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		return null;

	}

	public static String mapToXML(Map<String, Object> src) {
		StringBuilder stringBuilder = new StringBuilder("<xml>");

		Iterator<Map.Entry<String, Object>> iterator = src.entrySet().iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			// 类可以转换String
			String value = (String) entry.getValue();
			if (!StringUtils.isDouble(value)) {
				stringBuilder.append("<").append(key).append(">").append("<![CDATA[").append(value).append("]]></")
						.append(key).append(">");
			} else {
				stringBuilder.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
			}
		}

		stringBuilder.append("</xml>");

		return stringBuilder.toString();
	}

	/**
	 * 将XML字符串转换成Map,深度仅限1
	 */
	public static Map<String, String> xmlToMap(String xmlStr) {
		Map<String, String> resMap = null;
		if (xmlStr == null) {
			return resMap;
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		InputStream in = null;
		resMap = new HashMap<String, String>();
		try {
			// 禁用dtd，可以防止绝大多数的xml注入
			dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
			// 如果不能完全禁用dtd，采用如下设置
			dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
			dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
			dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
			// 关闭XML Inclusions (XInclude) Version 1.0.处理
			dbf.setXIncludeAware(false);
			dbf.setExpandEntityReferences(false);

			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			in = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
			// 解析
			Document document = documentBuilder.parse(in);
			// 规范化Xml
			document.getDocumentElement().normalize();

			// 遍历
			NodeList nodeList = document.getDocumentElement().getChildNodes();
			int length = nodeList.getLength();
			for (int i = 0; i < length; i++) {
				Node node = nodeList.item(i);
				// 判断节点类型,如果是元素节点，添加到map
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					// getTextContent-获取文本内容，会保留空格
					resMap.put(element.getNodeName(), element.getTextContent());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			StreamUtils.closeInputStream(in);
		}

		return resMap;
	}

	public static void main(String[] args) {
		pay("腾讯-Q币", new BigDecimal(125), "192.168.0.1", "www.lkpetq.com");

		String xml = "<xml><username>  zhoujian  </username></xml>";
		Map<String, String> map = xmlToMap(xml);

		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
