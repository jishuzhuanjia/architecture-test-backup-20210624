package xhm.struts.datawrap1;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import xhm.struts.datawrap.User;

/*5.数据封装到Map
 * 
 * 前端有两种写法：都需要从map开始写
 * 1.userMap['user1'].name
 * 2.userMap.user1.name
 * 都可以
 * 
 * 	Map不需要实例化map
 * */
public class Wrapper5 extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 579144554782982200L;

	/*form中: 
	 * 姓名: <input type="text" name="userMap['user1'].name"><br>
	 * 性别: <input type="text" name="userMap['user1'].sex" value="男"><br>
	 * 年龄: <input type="text" name="userMap['user1'].age" ><br> 
	 * 姓名: <input type="text" name="userMap['user2'].name"><br>
	 * 性别: <input type="text" name="userMap['user2'].sex" value="男"><br>
	 * 年龄: <input type="text" name="userMap['user2'].age" ><br> 
	 * 
	 * 下面的书写形式错误：
	 * <input type="text" name="userMap.user1.age" ><br> 
	 * Unexpected Exception caught setting 'user1.name' on 'class xhm.struts.datawrap1.Wrapper5: Error setting expression 'user1.name' with value ['zhoujian', ]
	 * 就算将map实例化且添加user1 key的User也不行
	 * Unexpected Exception caught setting 'user1.age' on 'class xhm.struts.datawrap1.Wrapper5: Error setting expression 'user1.age' with value ['54', ]
	 * 
	 *注意:  form中key的书写要求
	 * 不能书写空格，任何位置都不行,如: 
	 * 如 [' user1'].name,['user1 '].name,['us er1'].name //都是无效的
	 * */
	Map<String,User> userMap;

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	@Override
	public String execute() throws Exception {

		if(userMap != null) {
			for ( String key: userMap.keySet()) {
				//map中keySet顺序是不可预料的
				User user = userMap.get(key);
				System.out.println(key+":" + user.name + user.age+user.sex);
			}
		}

		return "wrapper5";
	}
	
	public Wrapper5() {
		
		//Unexpected Exception caught setting 'user1.age' on 'class xhm.struts.datawrap1.Wrapper5: Error setting expression 'user1.age' with value ['54', ]
		System.out.println("Action初始化");
		//userMap = new HashMap<String, User>();
		//User user1 = new User();
		/*User user2 = new User();
		User user3 = new User();*/
		//userMap.put("user1", user1);
	}
}
