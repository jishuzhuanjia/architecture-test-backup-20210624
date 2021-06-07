package ssm.test.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zj.service.TestService;

@Controller
@RequestMapping("/ssmtest")
public class SSMTestController {
	/**
	 * 測試controller包扫描是否开启
	 * */
	@RequestMapping(value="controllerTest.action",method= {RequestMethod.POST})
	public String test1(String username,@RequestBody User user ) {
		System.out.println("controllerTest.action");
		System.out.println(username);
		System.out.println(user);
		return "/jsp/success.jsp";
	}
	
	/**
	 * 测试拦截器是否正确配置
	 * */
	@RequestMapping("interceptorTest.action")
	public String test2() {
		return "/jsp/success.jsp";
	}
	
	/**
	 * 测试拦截器是否正确配置
	 * */
	@RequestMapping("exceptionTest.action")
	public String test3() {
		@SuppressWarnings("unused")
		int i = 1/0;
		
		return "/jsp/success.jsp";
	}
	
	/**
	 * 国际化测试
	 * */
	@RequestMapping("localeTest.action")
	public String test4(HttpServletRequest request) {
		
		
		//Locale locale  = new Locale("en","US");
		//request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
		
		//手动设置Locale
		//request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
		
		return "/jsp/locale.jsp";
	}
	
	@RequestMapping("uploadTest.action")
	public String test5(@RequestParam(value="file1",required=false) MultipartFile file1) {
		System.err.println("上传测试");
		System.err.println("name: " + file1.getName());
		System.err.println("originalFileName: " + file1.getOriginalFilename());
		System.err.println("ContentType: " + file1.getContentType());
		System.err.println("size: " + file1.getSize());
		try {
			System.err.println("bytes: " + file1.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/jsp/success.jsp";
	}
	
	/*
	 * 测试json
	 * @ResponseBody注解可以在public前或返回类型前，都可以
	 * */
	@RequestMapping("jsonTest.action")
	public @ResponseBody User test6() {
		User user = new User();
		user.setUsername("xiaohongmao");
		user.setAge(15);
		return user;
	}
	
	/*测试：前端控制器url-parttern为/*，能否正常返回jsp
	 * 不能访问，因为/*的设置会导致处理业务的Servlet大量缺失
	 *
	 * No mapping found for HTTP request with URI [/MavenSsm/jsp/success.jsp] in DispatcherServlet with name 'springmvc'
	 * */
	@RequestMapping("test1Jsp")
	public String test7() {   
		return "/jsp/success.jsp";    
	}
	
	/**
	 * 测试视图名称解析处理器
	 * 是否成功：是
	 * */
	@RequestMapping("testInternalResourceViewResolver")
	public String test8() {   
		return "success";    
	}
	
	/**
	 * 测试异常映射处理器:避免了自定义异常处理器的麻烦，只需要指定异常和视图资源即可快速处理异常。
	 * 是否成功：是
	 * */
	@RequestMapping("testSimpleMappingExceptionResolver")
	public String test9() {  
		@SuppressWarnings("unused")
		int i = 1/0;
		return "success";    
	}
	
	@Autowired
	TestService testServiceImpl;
	/**
	 * 检测动态数据源
	 * 是否成功：切面设置数据库ok(可以设置不存在targetDataSources中的数据源名字，不会报错)，但是DynamicDataSource.determineCurrentLookupKey还没有调用，
	 * 只有当执行数据库操作时，才会调用该方法
	 * */
	@RequestMapping("testDynamicDataSource")
	public String test10() {  
		testServiceImpl.test1();
		return "success";    
	}
	
	/**
	 * 检测事务管理
	 * 如果成功，则在执行sql前会注册同步的事务:
	 * Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * 
	 * 释放：
	 * Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * 提交
	 * Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * 取消注册
	 * Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * 关闭
	 * Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4256f6c8]
	 * */
	@RequestMapping("testTransactionManager")
	public String test11() {  
		testServiceImpl.insertARow(100);
		return "success";    
	}
	
	/**
	 * <pre>
	 * 代码实现shiro 认证/权限验证
	 * 
	 *未授权会500异常：
	 * org.apache.shiro.authz.AuthorizationException:
	 *     Not authorized to invoke method: public java.lang.String ssm.test.controller.SSMTestController.test12()
	 * 
	 * 默认情况下：不添加注解，不调用SecurityUtils相关方法，不会触发认证/验证
	 * </pre>
	 * */
	@RequestMapping("testshiro")
	public void test12() {  
		
		// false 
		System.out.println("isRemembered:" + SecurityUtils.getSubject().isRemembered());
		
		System.out.println("(#^.^#)");
		// 1.身份验证 - 在用户登录时，包含以下代码逻辑
		System.err.println("开始认证...");
		UsernamePasswordToken token = new UsernamePasswordToken("zhoujian", "123456");
		// 设置remember Cookie 默认false,rememberMe=deleteMe; Path=/SsmDevProject; Max-Age=0; Expires=Fri, 24-Jan-2020 03:32:50 GMT
		 
		//true:<!-- 如果token.setRememberMe(true) 服务器返回Cookie：-->
		/*<!-- Set-Cookie:rememberMe=deleteMe; Path=/SsmDevProject; Max-Age=0; Expires=Fri, 
				24-Jan-2020 04:24:54 GMT 
				Set-Cookie:rememberMe=FUT++bPZ8SEZz6n7EZ3fW6L1QK/c29zT1irtwHn+um4WNZpU7yuSh9uYABUGXH10Wk8Z1zMLgOpFzFwJiceyjyH0Xcxx/1WpYfumBdw9kMh8ue/YOTqcL0DosUFxmOO1pQcSO/U3jY+/jGe4TBA+2nXCsXnBxzNSJFR7SG5P4MC5EuNcWJ49grACnREBlukgyxN9n64CEaGpMTd1uF9SWKXLwC66c2Dgp09cjz8qB3A/pCwoTN0tbnqpq0TKcDIHxRdIU/elNRjtFJc6hUv5qwIxZklZGWSAfZq+2piPTOVZxCaaLDLpT4HTq6+v5QBsphuGOJ8Y6bNY+al/ieu0IY0AzLi7KNnaxxsuOEtC6H6hbvgevJvB4nGEufRrxKSWAcuxzsI6ad2qIMYz2u9SQDDICq529LGeRjcgFyO3OkEqXe8e7fjH4EDhbRS/0pvMzjP3qsL4jeaBSpZ2Y/uS/0Xy++G29V8qZ39maf8tRU3zF8YcB1W+VvJP/pUIs0Pv; 
				Path=/SsmDevProject; Max-Age=2592000; Expires=Mon, 24-Feb-2020 04:24:54 GMT; 
				HttpOnly -->*/
		token.setRememberMe(true);
		//  验证token
		//  account-账号 
		//  Credential - 相当于密码
		//  如果密码错误：返回500页面：org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - zhoujian, rememberMe=false] did not match the expected credentials.
		SecurityUtils.getSubject().login(token);
		System.out.println("认证成功，代码继续执行");
		
		// 2.权限验证
		System.err.println("开始权限授权...");
		//验证失败不会跑出异常，需要自己判断结果
		boolean permitted = SecurityUtils.getSubject().isPermitted("delete");
		System.out.println((permitted ? "权限验证成功": "权限验证失败"));
		System.out.println("--------");
		
		// false
		System.out.println("isRemembered:" + SecurityUtils.getSubject().isRemembered());
		
		// 注意：如果此url是表单认证过滤器loginURL,应该返回类型为void，否则不会跳转到目标url。
		// return "authencation";    
	}
	
	/**
	 * <pre>
	 * @RequiresAuthentication 使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证。如果没有使用SecurityUtils相关认证方法，且未通过认证，则抛出UnauthenticatedException异常，Controller方法不会执行。
	 * @RequiresGuest 使用该注解标注的类，实例，方法在访问或调用时，当前Subject可以是“gust”身份（游客），不需要经过认证或者在原先的session中存在记录。注意：
	 * 只有游客可以访问，如果当前用户已经认证了身份，则不能访问，否则：UnauthenticatedException: Attempting to perform a guest-only operation. The current Subject is not a guest (they have been authenticated or remembered from a previous login). Access denied.
	 * @RequiresPermissions 必须同时拥有所有声明的权限，否则UnauthorizedException: Subject does not have permission [xxxx]
	 * @RequiresRoles 要求指定的角色权限，角色权限通过simpleAuthorizationInfo.addRole(..)添加，而不等于权限
	 * @RequiresUser 验证用户是否被记忆，user有两种含义：一种是成功登录的（subject.isAuthenticated() 结果为true）；
     * 另外一种是被记忆的（subject.isRemembered()结果为true）。
	 * </pre>
	 *   
	 * */
	@RequestMapping(value="shiroannotation")
	// 登录后，可重启浏览器，还是可以访问，只要sid cookie存在，因为登录信息已经存在服务器
    @RequiresAuthentication
	// @RequiresGuest
	// @RequiresPermissions 
	//@RequiresRoles(value= {"admin"})
	 
	@RequiresUser
	public String test13() {
		
		//  错误：试图解析器也会作用到绝对路径
		// 404：SsmDevProject/html//html/shiroannotation.html
		// return "/html/shiroannotation";  
		
		 return "shiroannotation";  
	}
}
