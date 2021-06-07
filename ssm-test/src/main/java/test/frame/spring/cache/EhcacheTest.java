package test.frame.spring.cache;

import junit.framework.TestCase;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @CreateTime：2020年2月12日 下午12:10:17
 * @Author：zhoujian @QQ：2025513 @FileDescription：
 * @IsFinished：false
 */

public class EhcacheTest extends TestCase {

	AbstractApplicationContext applicationContext;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		// super.setUp();
		System.out.println("-----setUp-----");
		applicationContext = new ClassPathXmlApplicationContext("resource/applicationContext.xml");
	}
	
	
	public void testMethod() {
		System.out.println("-----testMethod-----");
		UserCacheImpl userCacheImpl = applicationContext.getBean(UserCacheImpl.class);
		
		/*System.out.println(userCacheImpl.getCacahedUserName("100"));
		System.out.println(userCacheImpl.getCacahedUserName("100"));
		System.out.println(userCacheImpl.getCacahedUserName("100"));
		System.out.println(userCacheImpl.getCacahedUserName("100"));
		System.out.println(userCacheImpl.getCacahedUserName("100"));*/
		/*System.out.println(userCacheImpl.getCacahedUserName(100));
		System.out.println(userCacheImpl.getCacahedUserName(100));
		System.out.println(userCacheImpl.getCacahedUserName(100));
		System.out.println(userCacheImpl.getCacahedUserName(100));
		System.out.println(userCacheImpl.getCacahedUserName(100));*/
		/*System.out.println(userCacheImpl.getCacahedUserName(100,"xx"));
		System.out.println(userCacheImpl.getCacahedUserName(100,"xx"));
		System.out.println(userCacheImpl.getCacahedUserName(100,"xx"));
		System.out.println(userCacheImpl.getCacahedUserName(100,"xx"));
		System.out.println(userCacheImpl.getCacahedUserName(100,"xx"));*/
		
		// 由于用户满18岁只会执行一次目标方法，后续满18会直接从缓存中获取数据。
		System.out.println(userCacheImpl.testCondition(18));
		System.out.println(userCacheImpl.testCondition(18));
		System.out.println(userCacheImpl.testCondition(18));
		System.out.println(userCacheImpl.testCondition(18));
		System.out.println(userCacheImpl.testCondition(18));

		// 由于用户不满18岁,每一次都会调用方法获取结果。
		System.out.println(userCacheImpl.testCondition(7));
		System.out.println(userCacheImpl.testCondition(7));
		System.out.println(userCacheImpl.testCondition(7));
		System.out.println(userCacheImpl.testCondition(7));
		System.out.println(userCacheImpl.testCondition(7));
		
		
		
		applicationContext.close();
		
	}
	
	public static void main(String[] args) {
		EhcacheTest ehcacheTest = new EhcacheTest();
		try {
			ehcacheTest.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ehcacheTest.testMethod();
	}

	/*
	 * public static void main(String[] args) { ClassPathXmlApplicationContext
	 * applicationContext = new
	 * ClassPathXmlApplicationContext("resource/applicationContext.xml");
	 * 
	 * UserCacheImpl userCacheImpl =
	 * applicationContext.getBean(UserCacheImpl.class);
	 * 
	 * userCacheImpl.getCacahedUserName(1); userCacheImpl.getCacahedUserName(1);
	 * userCacheImpl.getCacahedUserName(2); userCacheImpl.getCacahedUserName(3); }
	 */
}
