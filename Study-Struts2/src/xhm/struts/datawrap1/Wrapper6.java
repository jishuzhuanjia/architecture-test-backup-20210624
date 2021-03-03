package xhm.struts.datawrap1;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;

import xhm.struts.datawrap.User;

/* 使用模型驱动将属性存入map中,List<User>暂不讨论
 * 
 *  要求：需要实例化，key与value都要实例化
 *  
 *  前端书写：不能写map，必须写map中的属性，否则
 *  Unexpected Exception caught setting 'user1.name' on 'class xhm.struts.datawrap1.Wrapper6: Error setting expression 'user1.name' with value ['321321', ]
	正确写法：
	姓名：<input type="text" name="user1.name"><br>
 *  
 *  */
public class Wrapper6 implements ModelDriven<Map<String,User>> {
	
	//1.实例化一个map
	private Map<String,User> userMap = new HashMap<String,User>();

	@Override
	public Map<String,User> getModel() {
		// TODO Auto-generated method stub
		
		//2. 需要手动添加map中的pojo,这里不讨论key的原则。
		/*userMap.put("user1", new User());
		userMap.put("user2", new User());
		userMap.put("user3", new User());*/
		return userMap;
		  
		/*3. 在form中的写法
		姓名：<input type="text" name="user1.name"><br>
		性别：<input type="text" name="user1.sex" value="男"><br>
		年龄：<input type="text" name="user1.age"><br> */
	}
	
	public String execute() {
		
		if(userMap != null) {
			for ( String key: userMap.keySet()) {
				//map中keySet顺序是不可预料的
				User user = userMap.get(key);
				System.out.println(key+":" + user.name + user.age+user.sex);
			}
		}
		return "wrapper6";
	}

}
