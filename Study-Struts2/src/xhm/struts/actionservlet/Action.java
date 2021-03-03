package xhm.struts.actionservlet;

import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*1.ActioContext方式
 * 解耦合的方式操作域对象数据:只能用于存储数据，而不能调用其他的域对象api。
 * 通过ActionContext可以直接获取application,session域，
 * ActionContext的设计就是为了替代request的使用，而request则认为是ActionContext本身，
 * 获得的域实际上就是一个Map，通过对Map的操作就是对域的操作，而request域就是ActionContext本身。
 * 
 *注意：ActionContext的获取：静态方法而不是new：
 * ActionContext ac = ActionContext.getContext();
 * 
 *ActionContext可以获取的域
 * application
 * session
 * request - ActionContext
 * valueStack - 值栈
 * */
public class Action extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	public String value() {
		/*
		 * ActionContext生命周期和request相同
		 * */
		
		ActionContext ac = ActionContext.getContext();
		
		/*1.获取域：可以认为ac就是request域
		 * 1.1 application
		 * 对map的操作就是对域的操作*/
		Map<String,Object> applicationScope = ac.getApplication();
		applicationScope.put("application", "application");
		
		//1.2获取session
		Map<String,Object> sessionScope = ac.getSession();
		sessionScope.put("session", "session");
		
		//1.3获取值栈
		ac.getValueStack();
		
		//1.4request
		//经过测试：此方法等同request.setAttribute(key,value);
		ac.put("request", "request");
		
		return "ok";
	}
}
