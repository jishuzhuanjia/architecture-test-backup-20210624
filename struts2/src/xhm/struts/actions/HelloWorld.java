package xhm.struts.actions;

import org.apache.struts2.convention.annotation.Action;

import org.apache.struts2.convention.annotation.Result;


/* 自动扫描包的形式：
 *  注意struts.convention.package.locators.basePackage下的Action需要指定namespace注解
 * 否则任何namespace都能访问此Action
 * 如果想要/webapp/name.action进行访问
 * 则设置@namespace(value="/")
 * 
 * struts.convention.package.locators.basePackage子包下的Action可以不用namespace
 * namespace就是包名
 * */
/*@Namespace(value="/test1")*/
public class HelloWorld implements com.opensymphony.xwork2.Action{
	/**
	 *  
	 */
	@Action(value="HelloWorldxxxddd",results= {
			@Result(name="success",location="/jsp/hello.jsp"),
			@Result(name="error",location="/jsp/success.jsp")
			}) 
	
	public String execute() throws Exception {
		return "error";
	}
	
}
