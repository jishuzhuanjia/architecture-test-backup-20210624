package testproject.get;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import freemarker.cache.StrongCacheStorage;

public class GetActions  {
	
	/*
	 * GET请求测试，不带参数
	 * */
	public String getWithoutParams() {
		System.out.println("getWithoutParams");
		
		//获取参数
		HttpServletRequest request = ServletActionContext.getRequest();
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			String param = request.getParameter(paramName);
			System.out.println(paramName+": " +  param);
		} 
		
		
		
		return "getWithoutParams";
	}
	

}
