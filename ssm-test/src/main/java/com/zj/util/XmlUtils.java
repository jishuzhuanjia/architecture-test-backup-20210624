package com.zj.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @time 2019年12月19日 下午4:32:00
 * @author zhoujian
 * @corporation luku
 * @description: Xml文件。
 */
public class XmlUtils {
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
}



