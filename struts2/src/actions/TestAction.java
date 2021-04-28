package actions;


import org.apache.struts2.convention.annotation.*;

/* ͨ�� struts.convention.action.packages ��ָ��
 * 
 * 
 * 1.�Ƿ���Ҫ��� @Namespace? 
 * ע�⣺struts.convention.action.packages�µ�Action��Ҫָ��namespaceע��
 * �����κ�namespace���ܷ���,struts.convention.action.packages�Ӱ���Action���Բ�ָ��namespace��
 * ��Ϊ�Ӱ�����Ϊnamespace��
 * */


/*������ù�������
 * 1.ָ��@ParentPackage("testInterceptorAnnotation") testInterceptorAnnotation��xml��package��name���ԣ�
 * 
 * 2.���常��
 * xml��package���ö���namespace���ԣ�
 * <package name="testInterceptorAnnotation" extends="struts-default">
    	<interceptors>
    		<interceptor name="myInterceptorOne" class="xhm.struts.interceptor.MyInterceptor1"/>
    		
    		<interceptor-stack name="myInterceptorStack">
    			<interceptor-ref name="myInterceptorOne"></interceptor-ref>
    		</interceptor-stack>
    		
    	</interceptors>
    
    
    </package>
 * 3.���ã��������õ���������interceptor����interceptor-stack
 * interceptorRefs= {@InterceptorRef("myInterceptorOne")}
 * */
@ParentPackage("testInterceptorAnnotation")
@Namespace("/")
public class TestAction{

	
	/*@Action("test_Annotation")*/
	
	/**
	 * 
	 */

	@Action(interceptorRefs= {@InterceptorRef("myInterceptorOne")},value="HelloWorldhhh",results= {
			@Result(name="success",location="/jsp/hello.jsp"),
			@Result(name="error",location="/jsp/success.jsp")
			})
	public String execute() throws Exception {
		return "error";
	}
}
