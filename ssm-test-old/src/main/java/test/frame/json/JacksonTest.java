package test.frame.json;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.junit.Test;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @time 2019年12月12日 下午3:23:50
 * @author Administrator
 * @corporation luku
 * @description: springmvc内嵌json框架jackson的使用
 */
public class JacksonTest {

	// 测试实体类-登录用户
	static class User {
		
		private String username ;
		private String password;
		
		private int age;

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getUsername() {
			return username;
		}
		
		//@JsonIgnore
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

	/**
	 * 使用一：readValue() - 将json转换成pojo类(反序列化过程)
	 * 
	 * 注意:json中有未知属性时，需要ignoreUnknown=true
	 */
	public static void test1() {
		ObjectMapper objectMapper = new ObjectMapper();

		// 1.json来源 - 字符串
		/*
		 * String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}";
		 * User user = null; try { //test.main.JacksonTest$User@68be2bc2 user =
		 * objectMapper.readValue(jsonStr, User.class); } catch (JsonMappingException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (JsonProcessingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.println(user);
		 */

		// 2.json来源-字节
		/*
		 * String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}"; try
		 * { User user = objectMapper.readValue(jsonStr.getBytes(), User.class);
		 * System.out.println(user); } catch (JsonParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (JsonMappingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// 3.json来源-File-C:\Users\Administrator\Desktop
		// 例子中json.text内容：{"username":"username","password":"password"}
		// 注意：不能有转移字符如\\
		/*
		 * try {
		 * 
		 * User user = objectMapper.readValue(new
		 * File("C:/Users/Administrator/Desktop/json.txt"), User.class);
		 * System.out.println(user); } catch (JsonParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (JsonMappingException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// 4.json来源-InputStream-此处用字节流代替
		/*
		 * String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}";
		 * ByteArrayInputStream byteArrayInputStream = new
		 * ByteArrayInputStream(jsonStr.getBytes());
		 * 
		 * try {
		 * System.out.println(objectMapper.readValue(byteArrayInputStream,User.class));
		 * } catch (JsonParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (JsonMappingException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }finally { if(null !=
		 * byteArrayInputStream) try { byteArrayInputStream.close(); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		 */

		// 5.json来源-Reader
		String jsonStr = "{\"username\":\"username\",\"password\":\"password\"}";
		ByteArrayInputStream byteArrayInputStream = null;
		BufferedReader reader = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(jsonStr.getBytes());
			reader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
			User user = objectMapper.readValue(reader, User.class);
			System.out.println(user);
			//json转Map
			//HashMap<String,Object> jsonMap = objectMapper.readValue(reader, HashMap.class);
			
			//json转List
			ArrayList<String> testList = new ArrayList<String>();
			testList.add("string1");
			testList.add("string2");
			
			String listJson = objectMapper.writeValueAsString(testList);
			//["string1","string2"]
			System.out.println(listJson);
			@SuppressWarnings("unchecked")
			ArrayList<String> sList = objectMapper.readValue(listJson, ArrayList.class);
			//[string1, string2]
			System.out.println(sList);
		} catch (Exception e2) {
			// TODO: handle exception
		} finally {
			if (byteArrayInputStream != null)
				try {
					byteArrayInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 使用二：将pojo对象转换成Json字符串(序列化过程)
	 * 
	 * */
	public static void test2() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		//objectMapper.writeValueAsString(Object);
		//objectMapper.writeValueAsBytes(Object)
		User testUser = new User();
		testUser.setUsername("zhou");
		testUser.setPassword("123456");
		
		//测试writValueAsString(Object)
		//使用此方法，Jaskson的@JsonIgnore是否生效？   答：是，当该注解使用在setter/getter，就相当于用在属性上，序列化和反序列化都无效。
		try {
			System.out.println(objectMapper.writeValueAsString(testUser));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 如何控制属性序列化
	 * 默认情况下Include.ALWAYS
	 * 此设置对于Map和List无效。
	 * 当对象没有属性被序列化， 返回{}
	 * */
	@Test
	public void test3() {
		ObjectMapper mapper = new ObjectMapper();
		// Include.ALWAYS - 默认
		// Include.NON_EMPTY - ""和null都不会被序列化
		// Include.NON_NULL - ""会被序列化，
		// Include.NON_DEFAULT - 默认值不会被序列化，即初始化的值不会被初始化。int为0 引用类型为null。
		mapper.setSerializationInclusion(Include.NON_DEFAULT);
		User user = new User();
		//user.setUsername("");
		try {
			System.out.println(mapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//test1();
		test2();
	}

}
