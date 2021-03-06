package xhm.struts.datawrap;

import com.opensymphony.xwork2.ActionSupport;

/*1.属性注入：简单属性
 * 要求：Action类中相应属性有setter方法。
 * */
public class Wrapper1 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String name;
	
	//struts2 自动完成字符串到数值的转换
	private int age;
	
	private String sex;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String execute() throws Exception {
		System.out.println(name);
		System.out.println(sex);
		System.out.println(age);
		return "wrapper1";
	}
}
