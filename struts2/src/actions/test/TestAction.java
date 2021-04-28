package actions.test;


import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;



/* ͨ�� struts.convention.action.packages ��ָ��
 * ��Ҫ���@Namespace ע�⣬�����κ�namespace�����Է��ʴ�Action
 * 
 * �Ӱ�����䵱namespace
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
