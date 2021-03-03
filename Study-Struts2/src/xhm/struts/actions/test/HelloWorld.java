package xhm.struts.actions.test;

import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.convention.annotation.Result;

public class HelloWorld implements com.opensymphony.xwork2.Action{
	/**
	 *  
	 */

	@Action(value="ttttt",results= {
			@Result(name="success",location="/jsp/hello.jsp"),
			@Result(name="error",location="/jsp/success.jsp")
			})
	
	public String execute() throws Exception {
		return "error";
	} 
	
}
