package xhm.struts.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/* 自定义拦截器，需要继承自AbstractInterceptor */
public class MyInterceptor1 extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022051242717027488L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("请求经过拦截器1");
		
		//如果通过此拦截器调用该方法，否则阻止请求
		String obj = invocation.invoke();
		System.out.println("请求返回经过拦截器1");
		return obj;
	}

}
