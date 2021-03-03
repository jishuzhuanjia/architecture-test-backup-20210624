package xhm.struts.json;

import com.opensymphony.xwork2.Action;

import xhm.struts.datawrap.User;

/* Json测试action */
public class JsonAction implements Action {
	
	private User jsonUser;
	
	/*返回Json数据方式：
	 * 1.传统方式返回ajax数据：这种方式不需要在配置文件中配置。
	 * 
	 * 2.struts2返回json数据，需要在struts.xml配置要返回的数据。
	 * 
	 * */
	
	public User getJsonUser() {
		return jsonUser;
	}

	/* struts返回json */
	public String testStrutsJson() {
		
		jsonUser = new xhm.struts.datawrap.User();
		
		jsonUser.setName("xiaohongmao");
		jsonUser.setAge(25);
		jsonUser.setSex("男");
		
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}
}
