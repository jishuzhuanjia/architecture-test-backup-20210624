package test.frame.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @CreateTime：2020年2月10日 下午2:06:58
 * @Author：zhoujian
 * @QQ：2025513
 * @FileDescription：
 * @IsFinished：false
 */

// AfterReturningAdvice 在返回通知之后，仅在常规方法返回时调用通知，而不是在抛出异常时调用。这样的通知可以看到返回值，但不能更改它。
public class AdviceImpl implements MethodBeforeAdvice,AfterReturningAdvice{

	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
	}
}
