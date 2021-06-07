package com.zj.util.wx;

import java.util.Map;
//import net.sf.json.JSONObject;

/**
 * @time 2019年11月20日 上午9:43:19
 * @author z_j
 * @corporation luku
 * @description:微信发布 订阅消息 工具
 * uncompleted
 */

/**
 * 发布订阅消息后端实现，不包括获取模板 ID和获取下发权限。
 * */
public class WxSubscribeUtils {
	
	public static final String API_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send";
	
	/**发布消息*/
	public static void sendMessage(Message message) {
		
		//String access_token  = 
		//@SuppressWarnings("unused")
		//JSONObject jsonObject = JSONObject.fromObject(message);
	}
	
	/**订阅消息类
	 * */
	public static class Message{
		
		//接收者（用户）的 openid
		private String touser;
		
		//所需下发的订阅模板id
		private String template_id;
		
		//点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
		private String page;
		
		//模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
		private Map<String,  Map<String, String>> data;

		public String getTouser() {
			return touser;
		}

		public void setTouser(String touser) {
			this.touser = touser;
		}

		public String getTemplate_id() {
			return template_id;
		}

		public void setTemplate_id(String template_id) {
			this.template_id = template_id;
		}

		public String getPage() {
			return page;
		}

		public void setPage(String page) {
			this.page = page;
		}

		public Map<String, Map<String, String>> getData() {
			return data;
		}

		public void setData(Map<String, Map<String, String>> data) {
			this.data = data;
		}
	}
}



