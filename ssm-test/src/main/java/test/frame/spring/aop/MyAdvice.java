package test.frame.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 *5种通知advice(按照执行顺序书写):
 *
 *1.前置  before：在around之前调用。
 * 
 *2.环绕  around
 *
 *3.异常  afterThrowing：在around-after之前执行。
 *
 *4.方法后置  afterReturning：在around-after后执行。
 *
 *5.后置 after
 * 
 *方法执行顺序(相比于注解形式，配置方式顺序更合理)：
 *before -> around-before -> (method) 
 *	  -> after-throwing -> around-after 
 *    -> after-returning -> after 
 * */

/**
 * 通知类
 * */
public class MyAdvice {
	
	public void before() {
		System.out.println("before");
	}
	
	public void after() {
		System.out.println("after");
	}
	
	public Object around(ProceedingJoinPoint joinPoint) {
		System.out.println("round-before");
		Object object = null;
		try {
			//  执行目标方法
			object = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("round-after");
		return object;
	}
	
	public void afterThrowing() {
		System.out.println("afterThrowing");
	}
	
	public void afterReturning() {
		System.out.println("afterReturing");
	}
}