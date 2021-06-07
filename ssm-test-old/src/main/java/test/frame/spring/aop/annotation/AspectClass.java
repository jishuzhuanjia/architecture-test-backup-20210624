package test.frame.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
  * @time：2020年1月2日 下午9:28:46
  * @author：zhoujian
  * @corporation：luke
  * @description：切面类，包括切点和通知
  * @finished：false
  * @finishTime：
  * 
  */

/**
 * 注意：正确的指定顺序：
 * start...
   advice around-before    @Around
   advice before           @Before
   in des method		   des method
   advice around-after     @Around 
   advice after            @After 
 * 						   @AfterReturning(必须满足条件才会执行)
 * 						   @afterThrowing(发生异常才会执行)
 * */

/*
 * @Aspect必须和@Service或其他实例化注解一起使用，否则无效
 * */
@Service
@Aspect
public class AspectClass {
	
	/**下面演示Java注解配置Aspect*/
	
	/**
	 * 首先,定义切点，引用时通过方法名()来引用，不要忘记()
	 * 
	 * @Pointcut注解可以加在任何返回类型的方法上，但是方法体需要返回对应类型，这样才能通过编译。
	 * 实际上，代码不会执行。
	 * 因此，一般定义返回类型为void且方法体为空的方法即可。
	 * */
	@Pointcut("execution(* test.frame.spring.aop..*.*(..))")
	public String myCutpoint() {System.out.println("dasdadsadd");return "112";}
	
	/**
	 * before通知
	 * */
	@Before(value="myCutpoint()")
	public void before(JoinPoint joinPoint) {
		System.out.println("--------------以下讨论进阶方法介绍---------------");
		//  getTarget() - 获取切点对象实例
		//  class test.frame.spring.aop.annotation.Student
		//System.out.println("joinPoint.getTarget().getClass():" + joinPoint.getTarget().getClass());
		
		System.out.println("advice before");
	}
	
	/**
	 * after
	 * */
	@After(value="myCutpoint()")
	public void after() {
		System.out.println("advice after");
	}
	
	/**
	 * 注意：afterReturning通知不总是执行。
	 * @AfterReturning参数说明：
	 * 1.value和pointcut作用一样，都能定义切点表达式和引用方法切点，当两者都定义时，后者覆盖前者。
	 * 2.returning表示通知方法用来访问目标方法返回值的形参名。
	 * 3.注意returning形参类型表示哪些返回类型的方法会被拦截，Object不能用来拦截所有方法，如：
	 * @AfterReturning(value = "myCutpoint()", returning = "result")
	   public void afterReturing(JoinPoint joinPoint,String result) {}
	         则只有满足切点表达式，且返回值为String类型的目标方法才会执行@AfterReturning通知方法。
	   int/Integer可拦截int返回值，包装类类推,当形参类型为int,会将把int返回类型的目标方法转换成Integer
	 * 
	 * 以上不拦截情况发生在和around配合使用时，只有类型匹配时，才会执行此方法，否则不执行。
	 * */
	@AfterReturning(value = "myCutpoint()", returning = "result")
	public void afterReturing(JoinPoint joinPoint,int result) {
		System.out.println("advice afterReturning");
		System.out.println("拦截到的返回值:" + result);
	}
	
	/**
	 * around
	 * 
	 *注意：ProceedingJoinPoint.proceed()可能返回null
	 * 注意：
	 * 1.around中.ProceedingJoinPoint.proceed()返回类型受到@AfterReturning影响，如果不符合
	 * @AfterReturning形参类型，则proceed()返回值为null。而且如果不符合形参要求，afterThrowing方法
	 * 也不会执行。
	 * */
	@Around(value="myCutpoint()")
	public Object around(ProceedingJoinPoint pjp) {
		Object retVal = null;
		try {
			System.out.println("advice around-before");
			retVal = pjp.proceed();
			System.err.println("retValue:   " + retVal);
			System.out.println("advice around-after");
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	/**
	 * afterThrowing
	 * */
	@AfterThrowing("myCutpoint()")
	public void afterThrowing() {
		System.out.println("advice afterThrowing");
	}
	
	/*
	 *切面定义相关注解速记
	 *@Aspect: 需要和实例化注解(@Component,@Service等)一起使用,单独使用没有任何意义。
	 *
	 *@Pointcut:定义切点表达式。
	 *
	 *@Before
	 *@Around
	 *@After
	 *@AfterReturning
	 *@AfterThrowing
	 * */
}
