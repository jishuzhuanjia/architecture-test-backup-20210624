package xhm.struts.datawrap1;

import com.opensymphony.xwork2.ActionSupport;
import xhm.struts.datawrap.User;

import java.util.List;

/*4.���ݲ�����װ��List<User>
 * ��Ҫ�ṩpojo�����setter,getter
 * �б���Ҫʵ����
 * */
public class Wrapper4 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	//form��input��name="userList[0].name"
	//form��input��name="userList[0].age"
	//form��input��name="userList[1].name"
	//form��input��name="userList[1].age"
	List<User> userList;

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

	@Override
	public String execute() throws Exception {

		//��Ҫ�ж����Բ���Ϊ�գ�Ϊ�ջ��쳣��
		if(userList != null) {
			for (User user : userList) {
				System.out.println(user.name + "," + user.sex + "," +user.age);
			}
		}

		return "wrapper4";
	}
}
