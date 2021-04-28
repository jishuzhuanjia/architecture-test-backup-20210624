package xhm.struts.datawrap1;

import com.opensymphony.xwork2.ActionSupport;
import xhm.struts.datawrap.User;

import java.util.Map;

/*5.���ݷ�װ��Map
 * 
 * ǰ��������д��������Ҫ��map��ʼд
 * 1.userMap['user1'].name
 * 2.userMap.user1.name
 * ������
 * 
 * 	Map����Ҫʵ����map
 * */
public class Wrapper5 extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 579144554782982200L;

	/*form��: 
	 * ����: <input type="text" name="userMap['user1'].name"><br>
	 * �Ա�: <input type="text" name="userMap['user1'].sex" value="��"><br>
	 * ����: <input type="text" name="userMap['user1'].age" ><br> 
	 * ����: <input type="text" name="userMap['user2'].name"><br>
	 * �Ա�: <input type="text" name="userMap['user2'].sex" value="��"><br>
	 * ����: <input type="text" name="userMap['user2'].age" ><br> 
	 * 
	 * �������д��ʽ����
	 * <input type="text" name="userMap.user1.age" ><br> 
	 * Unexpected Exception caught setting 'user1.name' on 'class xhm.struts.datawrap1.Wrapper5: Error setting expression 'user1.name' with value ['zhoujian', ]
	 * ���㽫mapʵ���������user1 key��UserҲ����
	 * Unexpected Exception caught setting 'user1.age' on 'class xhm.struts.datawrap1.Wrapper5: Error setting expression 'user1.age' with value ['54', ]
	 * 
	 *ע��:  form��key����дҪ��
	 * ������д�ո��κ�λ�ö�����,��: 
	 * �� [' user1'].name,['user1 '].name,['us er1'].name //������Ч��
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
				//map��keySet˳���ǲ���Ԥ�ϵ�
				User user = userMap.get(key);
				System.out.println(key+":" + user.name + user.age+user.sex);
			}
		}

		return "wrapper5";
	}
	
	public Wrapper5() {
		
		//Unexpected Exception caught setting 'user1.age' on 'class xhm.struts.datawrap1.Wrapper5: Error setting expression 'user1.age' with value ['54', ]
		System.out.println("Action��ʼ��");
		//userMap = new HashMap<String, User>();
		//User user1 = new User();
		/*User user2 = new User();
		User user3 = new User();*/
		//userMap.put("user1", user1);
	}
}
