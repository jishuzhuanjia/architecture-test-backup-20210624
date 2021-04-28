package test;

import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class HomeAction implements ServletRequestAware{
	ServletRequest request;
	
	
	public String home() {
		
		return "home";
	}
	
	
	
	public String changeColor() {
		
		return null;
	}



	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}
	
	
	
	

}
