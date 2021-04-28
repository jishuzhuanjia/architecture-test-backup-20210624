package xhm.struts.datawrap1;

import com.opensymphony.xwork2.ModelDriven;
import xhm.struts.datawrap.User;

import java.util.HashMap;
import java.util.Map;

/* ʹ��ģ�����������Դ���map��,List<User>�ݲ�����
 * 
 *  Ҫ����Ҫʵ������key��value��Ҫʵ����
 *  
 *  ǰ����д������дmap������дmap�е����ԣ�����
 *  Unexpected Exception caught setting 'user1.name' on 'class xhm.struts.datawrap1.Wrapper6: Error setting expression 'user1.name' with value ['321321', ]
	��ȷд����
	������<input type="text" name="user1.name"><br>
 *  
 *  */
public class Wrapper6 implements ModelDriven<Map<String,User>> {
	
	//1.ʵ����һ��map
	private Map<String,User> userMap = new HashMap<String,User>();

	@Override
	public Map<String,User> getModel() {
		// TODO Auto-generated method stub
		
		//2. ��Ҫ�ֶ����map�е�pojo,���ﲻ����key��ԭ��
		/*userMap.put("user1", new User());
		userMap.put("user2", new User());
		userMap.put("user3", new User());*/
		return userMap;
		  
		/*3. ��form�е�д��
		������<input type="text" name="user1.name"><br>
		�Ա�<input type="text" name="user1.sex" value="��"><br>
		���䣺<input type="text" name="user1.age"><br> */
	}
	
	public String execute() {
		
		if(userMap != null) {
			for ( String key: userMap.keySet()) {
				//map��keySet˳���ǲ���Ԥ�ϵ�
				User user = userMap.get(key);
				System.out.println(key+":" + user.name + user.age+user.sex);
			}
		}
		return "wrapper6";
	}

}
