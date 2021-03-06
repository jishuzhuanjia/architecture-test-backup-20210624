package xhm.struts.datawrap;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*3.实现模型驱动接口：ModelDriven<T>：
 * T就是要返回的pojo类型，接口中getModel()方法返回属性存放的pojo实例。
 * 这个pojo对象需要手动实例化。
 * 
 * 要求：pojo类中的属性有setter方法并且实现模型驱动接口。
 * 
 * 优点：pojo对象不需要setter,getter方法，只需要实现接口即可。
 * 
 * 缺点：只能将参数封装到一个pojo对象中。
 * 
 * 但是这是最常用的方式 - 因为大多数情况下，一个pojo对象就能够存储所有的参数。
 */ 
public class Wrapper3 extends ActionSupport implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user = new User();

	@Override
	public String execute() throws Exception {
		System.out.println(user.name);
		System.out.println(user.sex);
		System.out.println(user.age);
		return "wrapper3";
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}
