package ssm.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/*注意：自定义异常处理器如果不返回视图直接返回null，则会返回系统的错误页面:
 * HTTP Status 500 – Internal Server Error */
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		System.err.println("Entering MyHandlerExceptionResolver");
		System.out.println("exception type:" + ex.getClass());
		ModelAndView modelAndView = new ModelAndView();
		
		// 密码不正确：IncorrectCredentialsException
		
		// shiro异常处理
		if(ex instanceof AuthorizationException) {
			modelAndView.setViewName("success");
		}
		return modelAndView;
	}

}
