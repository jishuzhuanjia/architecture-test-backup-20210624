package xhm.struts.valuestack;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ognl.OgnlValueStack;
import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.ServletActionContext;
import xhm.struts.datawrap.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*值栈的生命周期贯穿整个Action
 * */

/*ValueStack是Struts2的一个接口，字面意义为值栈，
 * OgnlValueStack是 ValueStack的实现类，客 户端发起一个请求，
 * struts2架构会创建一个action实例同时创建一个OgnlValueStack值栈实例，
 * OgnlValueStack贯穿整个Action的生命周期，struts2中使用OGNL将
 * 请求Action的参数封装为对象存储 到值栈中，并通过OGNL表达式读取值栈中的
 * 对象属性值。
 **/

/*CompoundRoot
 * CompoundRoot：存储了action实例，它作为OgnlContext的Root对象。
 * CompoundRoot继承ArrayList 实现压栈和出栈功能，拥有栈的特点，
 * 先进后出，后进先出，最后压进栈的数据在栈顶(数组第一的位置)。
 * struts2对原OGNL作出的改进就是Root使用CompoundRoot(自定义栈)，使用OnglValueStack
 * 的findValue方法可以在CompoundRoot中从栈顶向栈底查找对象的属性值。
 * CompoundRoot作为OgnlContext的Root对象，并且在CompoundRoot中
 * action实例位于栈顶， 当读取achon的属性值时会先从栈顶对象中查找对应的属性，
 * 如果找不到则继续查找栈中的其它对象， 如果未找到则到ContextMap中去查找，
 * 未找到，则返回null。  
 * */

/*root - ArrayList的子类
 * ArrayList[0] - HashMap<K,V> 
 *     [0] - HashMap$Node<K,V> //就是存放valueStack.set(key,Object),
 *     每次调用valueStack.set(key,Object)都会创建一个Map<K,V>放在栈顶
 *     查找的时候所有的Map都会查找
 * ArrayList[1] - ValueStackAction  //会将Action压栈,包括Action的属性。
 *                //会被查找, 直接通过属性名访问。
 * ArrayList[2] - DefaultTeextProvider
 * 
 * */

/*Root的查找
 * 从栈顶开始查找，找到以后不再继续,否则继续查找
 * context Map -> Action
 * */

public class ValueStackAction extends ActionSupport {
OgnlValueStack s;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6366981946861249766L;
	
	String valueStackName = "ValueStackAction";
	
	User actionUser;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		/*1. 获取值栈
		 * 1.1. 通过ActionContext.getValueStack()
		 * */
		//ValueStack vs = ActionContext.getContext().getValueStack();

		/*1.2. 通过request对象, 需要使用ServletActionContext
		 * 获取HttpServletRequest,
		 * 因为值栈已经被存储在request域中,key为STRUTS_VALUESTACK_KEY
		 */
		ValueStack valueStack = (ValueStack) ServletActionContext.getRequest().getAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY);

		//true
		//System.out.println(vs == valueStack);
		
		actionUser = new User();
		actionUser.name="actionUserName";

		valueStack.set("首富","王健林");
		valueStack.set("city","深圳");

		List<User> userList = new ArrayList<User>();
		User u1 = new User();
		u1.name="111";
		u1.age=111;
		User u2 = new User();
		u2.name="222";
		u2.age=222;
		User u3 = new User();
		u3.name="333";
		u3.age=333;
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		valueStack.set("userList", userList);

		//如果没有查到指定的属性，返回null
		//System.out.println(valueStack.findString("首富"));
		//System.out.println(valueStack.findString("首富2"));

		//将对象压入栈顶
		//valueStack.push(user4);

		//push就是将一个对象添加到栈顶
		//set:会从栈顶获取HashMap<K,V> 如果没有,则会创建一个然后压入栈顶
		//比如当使用Push以后再调用set会创建一个Map
		//查找的时候从栈顶Map开始查找
		
		//多为框架调用，push的对象不能用Ognl获取
		valueStack.push("pushObject");
		
		//会通过ognl获取
		valueStack.set("afterpush", "afterpush");
		valueStack.set("首富","周健");
		
		//向request域存入
		ActionContext.getContext().put("key2351s", "jiijjnnooi");
		Map<String,String> userMap = new HashMap<String,String>();
		userMap.put("player1", "周健");
		userMap.put("player2", "马云");
		
		ActionContext.getContext().put("userMap", userMap);

		return "ValueStackAction";
	}
}
