package xhm.struts.annotation;


import com.opensymphony.xwork2.ActionSupport;

/*怎么样配置类才会被扫描?
 *类要实现Action接口，或者类名以Action结尾
 * */

/*@ParentPackage("struts-default")
@Namespace("/annotation_test")*/
public class TestAct extends ActionSupport {

	
	/*@Action("test_Annotation")*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*@Action(value="test_Annotation",results= {
			@Result(name="success",location="hello.jsp"),
			@Result(name="error",location="success.jsp",type="..")
			})*/
	public String test() {
		return "success";
	} 
}
