package xhm.struts.datawrap1;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import xhm.struts.datawrap.User;

/*4.数据参数封装到List<User>
 * 需要提供pojo对象的setter,getter
 * 列表不需要实例化
 * */
public class Wrapper4 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	//form中input：name="userList[0].name"
	//form中input：name="userList[0].age"
	//form中input：name="userList[1].name"
	//form中input：name="userList[1].age"
	List<User> userList;

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

	@Override
	public String execute() throws Exception {

		//需要判断属性不能为空，为空会异常。
		if(userList != null) {
			for (User user : userList) {
				System.out.println(user.name + "," + user.sex + "," +user.age);
			}
		}

		return "wrapper4";
	}
}
