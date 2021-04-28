package xhm.struts.actionservlet;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*2.ʹ��ԭ��api��������󷽷�����
 * ServletActionContext
 * 
 *ServletActionContext����ֱ�ӻ�ȡ����
 *ServletContext
 *HttpServletRequest
 *PageContext
 *ע��sesion����ͨ��HttpServletRequestֱ�ӻ�ȡ
 *
 *
 * */
public class Action2 extends ActionSupport {
	private static final long serialVersionUID = 1L;

	public String value() {
		
		//1.��ȡServletContext - applicationScope
		ServletContext sc = ServletActionContext.getServletContext();
		sc.setAttribute("application", "application");
		
		/*2.��ȡHttpSession - sessionScope
		 *ServletActionContextû��ֱ�ӻ�ȡHttpSession�ķ�����
		 *ͨ��HttpServletRequest����getSession()
		 * */
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("session", "session");
		
		//3.��ȡHttpServletRequest - requestScope
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("request", "request");
		
		/*4.��ȡPageContext- pageContext
		PageContext pc = ServletActionContext.getPageContext();
		��ʱpc��ָ�룬���Ƽ��˴���pageContext����и�ֵ��*/
		//pc.setAttribute("pageContext", "pageContext");
		return "ok";
	}
}
