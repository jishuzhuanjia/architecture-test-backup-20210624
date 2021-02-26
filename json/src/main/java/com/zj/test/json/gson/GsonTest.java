package com.zj.test.json.gson;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * @time 2019年12月19日 下午5:35:04
 * @author Administrator
 * @corporation luku
 * @description: Google gson.jar专题
 * 功能丰富，支持对象的序列化反序列化，对象创建更加灵活。
 * 数组添加各种类型元素更加方便。
 * 对象添加基本数据类型、引用类型更加方便。
 */
public class GsonTest {
	@SuppressWarnings("unused")
	private static Logger logger= Logger.getLogger("GsonTest");
	
	/**
	 * 1.Object -> JSON字符串
	 * */
	@Test /* @Test要求方法public非静态  */
	public void Object2JSON() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		User testUser = new User();
		testUser.setUsername("zhoujian");
		testUser.setPassword("123");
		testUser.setLoginDate(new Date(System.currentTimeMillis()));
		testUser.setLoginUtilDate(new java.util.Date());
		testUser.setLoginTimestamp(new Timestamp(System.currentTimeMillis()));
		//1.1.Gson.toJson(Object)
		System.out.println(gson.toJson(testUser));
		
		//1.2.Gson.toJson(<T extends JsonElement>)
		JsonObject jsonObject =new JsonObject();
		
		/** 添加属性的几个方法 - 基本数据类型*/
		// addProperty(String property, Boolean value)
		jsonObject.addProperty("isAdult", true);
		// addProperty(String property, Number value) - Number类型可接受所有类型整型，会自动拆装包。
		jsonObject.addProperty("age", 25.523);
		// addProperty(String property, String value)
		jsonObject.addProperty("school", "BeiJing University");
		// addProperty(String property, Character value)
		jsonObject.addProperty("state", '1');
		
		/** 如何添加一个对象 - 引用类型*/
		JsonObject fieldObject = new JsonObject();
		fieldObject.addProperty("goodTitle", "iPhone11 手机壳");
		jsonObject.add("goodInfo", fieldObject);
		
		/** 如何添加一个数组类型 */
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(true);
		jsonArray.add("1");
		jsonArray.add(13);
		jsonArray.add("dsadsda");
		/** 如何添加null,不能直接add(null) - ambigious */
		jsonArray.add(JsonNull.INSTANCE);
		// addAll(JsonArray array) - 不测试
		jsonObject.add("fieldArray", jsonArray);
		// {"isAdult":true,"age":25.523,"school":"BeiJing University","state":"1","goodInfo":{"goodTitle":"iPhone11 手机壳"},"fieldArray":[true,"1",13,"dsadsda"]}
		System.out.println(gson.toJson(jsonObject));
	}
	
	/** 2.JSON -> Object
	 * 
	 * 缺省情况下，如果属性值为null,不会被序列化。
	 * 
	 * */
	@Test
	public void json2Object() {
		Gson gson = new GsonBuilder().create();
		
		User user = new User();
		user.setUsername("zhou");
		// zhou,null
		System.out.println(user);
		// {"username":"zhou"} - 缺省情况下不会序列化null属性，那么如何开启？
		// new GsonBuilder().serializeNulls().create(); 
		// 则输出：{"username":"zhou","password":null,"loginDate":null,"loginUtilDate":null,"loginTimestamp":null}
		System.out.println(gson.toJson(user));
		
		// 反序列化
		User user1 = gson.fromJson(gson.toJson(user), User.class);
		// zhou,null
		System.out.println(user1);
		
		/** 反序列化的几个方法 */
		// gson.fromJson(String, Class)
		// gson.fromJson(Reader, classOfT)
		// gson.fromJson(JsonReader, classOfT)
		// gson.fromJson(JsonElement, classOfT)
	}

	static class User {

		private String username;
		private String password;

		private Date loginDate;

		private java.util.Date loginUtilDate;
		private Timestamp loginTimestamp;

		public Timestamp getLoginTimestamp() {
			return loginTimestamp;
		}

		public void setLoginTimestamp(Timestamp loginTimestamp) {
			this.loginTimestamp = loginTimestamp;
		}

		public java.util.Date getLoginUtilDate() {
			return loginUtilDate;
		}

		public void setLoginUtilDate(java.util.Date loginUtilDate) {
			this.loginUtilDate = loginUtilDate;
		}

		public Date getLoginDate() {
			return loginDate;
		}

		public void setLoginDate(Date loginDate) {
			this.loginDate = loginDate;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return username + "," + password;
		}
	}

	public static void main(String[] args) {
		// The date format will be used to serialize and deserialize java.
		// util.Date, java.sql.Timestamp and java.sql.Date.

//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		User user = new User();
//		user.setUsername("zhoujian");
//		user.setPassword("323");
//		user.setLoginDate(new Date(System.currentTimeMillis()));
//		user.setLoginUtilDate(new Date(System.currentTimeMillis()));
//		user.setLoginTimestamp(new Timestamp(System.currentTimeMillis()));
//		
//		//1.对象转字符串
//		String json = gson.toJson(user);
//		
//		// {"username":"zhoujian","password":"323","loginDate":"2019-12-19
//		// 17:48:44","loginUtilDate":"2019-12-19 17:48:44","loginTimestamp":"2019-12-19
//		// 17:48:44"}
//		System.err.println(json);
//		
//		//2.json转对象
//		//gson.fromJson(JsonElement, classOfT)
//		//gson.fromJson(JsonElement, classOfT)
//		//gson.fromJson(json, classOfT)
//		
		
	}
}
