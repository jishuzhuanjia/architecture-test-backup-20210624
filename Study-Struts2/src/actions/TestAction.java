package actions;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

/* 通过 struts.convention.action.packages 来指定
 * 
 * 
 * 1.是否需要添加 @Namespace? 
 * 注意：struts.convention.action.packages下的Action需要指定namespace注解
 * 否则任何namespace都能访问,struts.convention.action.packages子包中Action可以不指定namespace，
 * 因为子包会作为namespace。
 * */


/*如何引用过滤器？
 * 1.指定@ParentPackage("testInterceptorAnnotation") testInterceptorAnnotation是xml中package的name属性，
 * 
 * 2.定义父包
 * xml中package不用定义namespace属性：
 * <package name="testInterceptorAnnotation" extends="struts-default">
    	<interceptors>
    		<interceptor name="myInterceptorOne" class="xhm.struts.interceptor.MyInterceptor1"/>
    		
    		<interceptor-stack name="myInterceptorStack">
    			<interceptor-ref name="myInterceptorOne"></interceptor-ref>
    		</interceptor-stack>
    		
    	</interceptors>
    
    
    </package>
 * 3.引用：可以引用单个拦截器interceptor或者interceptor-stack
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
