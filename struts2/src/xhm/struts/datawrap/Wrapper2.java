package xhm.struts.datawrap;

import com.opensymphony.xwork2.ActionSupport;
/*2.属性注入：pojo封装
 * Http传输参数会保留参数的首尾空格
 * 
 * 要求：Action 中 pojo 封装对象需要提供setter 和  getter：
 * 如果不提供getter，虽然不会报错，但是因为没有getter方法,会为每一个参数创建一个pojo，
 * 对pojo中单个属性进行赋值，再调用setter赋值到Action,由于多次调用setter，最终的Pojo对象是
 * 最后一次创建的pojo，其他的参数丢失。
 *
 * 优点：如果需要，可以将属性封装到多个pojo对象中。
 * */

public class Wrapper2 extends ActionSupport {

	private static final long serialVersionUID = 1L;
	StudentInfo sinfo;
	
	public StudentInfo getSinfo() {
		return sinfo;
	}

	public void setSinfo(StudentInfo sinfo) {
		this.sinfo = sinfo;
	}

	User user;

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(user.name);
		System.out.println(user.sex);
		System.out.println(user.age);
		System.out.println(sinfo.time);
		System.out.println(sinfo.grade);
		System.out.println(sinfo.price);
		return "wrapper2";
	}

	public User getUser() {
		return user;
	}
}
