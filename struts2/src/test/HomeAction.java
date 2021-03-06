package test;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import javassist.compiler.ast.Variable;

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
