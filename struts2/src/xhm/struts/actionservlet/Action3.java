package xhm.struts.actionservlet;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
/*
 * 3:通过实现接口来得到原生request,servletcontext，session*/
public class Action3 extends ActionSupport implements ServletContextAware,ServletRequestAware,SessionAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request ;
	
	ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {

		this.servletContext = servletContext;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		this.request = httpServletRequest;
	}
	
	public String value() {
		servletContext .setAttribute("111servletContext", "213213");
		request.getSession().setAttribute("session", "123123");
		request.setAttribute("request1", "312321321");
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
	}
}
