package test.frame.spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.frame.spring.injection.TestPojo;
import test.frame.spring.injection.TestServiceImpl;

/*
 * @time  2019年12月31日 下午2:56:26
 * @author  zhoujian
 * @corporation  luku
 * @description   
 * @finished  false
 * @finishTime  
 * @version  1.0
 */
public class JunitTest {
	
	public static void main(String[] args) {
		
		// 会扫描base-package包下及其子包的类
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("test/frame/spring/applicationContext.xml");
		
		TestServiceImpl testServiceImpl = applicationContext.getBean(TestServiceImpl.class);
		//testServiceImpl.test1();
		System.out.println(testServiceImpl.username);
		//System.out.println(testServiceImpl.age);
		//System.out.println(testServiceImpl.firstEnter);
		//  bean name是大小写区分的,myTestServiceImpl 和myTestServiceimpl不是一回事，会导致异常
		//TestServiceImpl testServiceImpl1 = (TestServiceImpl) applicationContext.getBean("myTestServiceImpl");
		//testServiceImpl1.test1();
		
		TestPojo testPojo = applicationContext.getBean(TestPojo.class);
		testPojo.testServiceImpl.test1();
		/*testPojo.testServiceImpl2.test1();
		testPojo.testServiceImpl3.test1();*/
		//testPojo.testServiceImpl4.test1();
		
		// 会导致bean 销毁方法的调用。
		applicationContext.close();
	}
}



