package xhm.struts.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;


/* �Զ�ɨ�������ʽ��
 *  ע��struts.convention.package.locators.basePackage�µ�Action��Ҫָ��namespaceע��
 * �����κ�namespace���ܷ��ʴ�Action
 * �����Ҫ/webapp/name.action���з���
 * ������@namespace(value="/")
 * 
 * struts.convention.package.locators.basePackage�Ӱ��µ�Action���Բ���namespace
 * namespace���ǰ���
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
