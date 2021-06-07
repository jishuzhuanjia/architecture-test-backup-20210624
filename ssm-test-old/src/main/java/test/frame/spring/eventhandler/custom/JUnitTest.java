package test.frame.spring.eventhandler.custom;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* 自定义事件测试类 */
public class JUnitTest {
	
	@Test   
	
	public void test1() {
		ConfigurableApplicationContext cac = new ClassPathXmlApplicationContext("classpath:cn/xhm/frametest/spring/eventhandler/beans.xml");
		
		//发布事件
		CustomPublisher cp = cac.getBean(CustomPublisher.class);
		cp.publish();
		
		cac.close();
	}

}
