package actions.test;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;



/* 通过 struts.convention.action.packages 来指定
 * 需要添加@Namespace 注解，否则任何namespace都可以访问此Action
 * 
 * 子包不会充当namespace
 * */
@ParentPackage("struts-default")
public class TestAction extends ActionSupport {

	
	/*@Action("test_Annotation")*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value="HelloWorldyyy",results= {
			@Result(name="success",location="/jsp/hello.jsp"),
			@Result(name="error",location="/jsp/success.jsp")
			})
	@Override
	public String execute() throws Exception {
		return "error";
	}
}
