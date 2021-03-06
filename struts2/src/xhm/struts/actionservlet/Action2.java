package xhm.struts.actionservlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/*2.使用原生api操作域对象方法和域
 * ServletActionContext
 * 
 *ServletActionContext可以直接获取的域
 *ServletContext
 *HttpServletRequest
 *PageContext
 *注：sesion可以通过HttpServletRequest直接获取
 *
 *
 * */
public class Action2 extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String value() {
		
		//1.获取ServletContext - applicationScope
		ServletContext sc = ServletActionContext.getServletContext();
		sc.setAttribute("application", "application");
		
		/*2.获取HttpSession - sessionScope
		 *ServletActionContext没有直接获取HttpSession的方法：
		 *通过HttpServletRequest方法getSession()
		 * */
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("session", "session");
		
		//3.获取HttpServletRequest - requestScope
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("request", "request");
		
		/*4.获取PageContext- pageContext
		PageContext pc = ServletActionContext.getPageContext();
		此时pc空指针，不推荐此处对pageContext域进行赋值。*/
		//pc.setAttribute("pageContext", "pageContext");
		return "ok";
	}
}
