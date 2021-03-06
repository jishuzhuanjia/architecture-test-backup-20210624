package xhm.struts.action;

/*
 *1.Action的pojo类：不需要实现接口和继承类,
 */
public class HelloAction {
	
	public String execute() {
		return "hello";
	}
	
	public String execute2() {
		return "hello";
	}
	
	public String update() {
		System.out.println("来到Update");
		return "update_ok";
	}
	
	public String delete() {
		return "ok";
	}
	
	public String add() {
		return "add_ok";
	}
	
	public String dynamicMethod() {
		return "ok";
	}
	
}
