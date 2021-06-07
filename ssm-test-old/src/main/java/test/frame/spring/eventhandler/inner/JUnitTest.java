package test.frame.spring.eventhandler.inner;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.frame.spring.eventhandler.custom.CustomPublisher;

/* 
 * @RunWith(SpringJUnit4ClassRunner.class)
 * @ContextConfiguration("classpath:cn.xhm.frametest.spring.eventhandler.inner.beans.xml")
 */
public class JUnitTest {

	// spring内置事件测试
	@Test
	public void test1() {
		/*
		 * 内置事件的调用时机： 默认情况下，只有refresh会调用，还是在创建ApplicatinContext的时候调用。
		 * 只有在调用close代码后不能进行bean获取，调用其他的方法不会影响后续ApplicationContext的使用，依然可以获取bean。
		 * 
		 * 1.refreshed: 
		 * 1.容器初始化时候如：new ClassPathXmlApplicationContext(
		 * "classpath:cn/xhm/frametest/spring/eventhandler/beans.xml"); 
		 * 2.手动调用refresh方法。
		 * 注：refresh调用之后可以发布started,stopped等事件。
		 * 
		 * 2.started: 只有调用start()时才会触发started事件。 初始化容器的时候不会触发，需要手动触发。
		 * 
		 * 3.stopped： 只有调用stop()时才会触发stopped事件
		 * 
		 * 4.close: close以后调用start(),stop()无效,start()和stop()会立刻返回,不会触发事件，不会中断代码。
		 * 但是如果close之后再调用方法获取bean，则会异常，代码中断。
		 * 但是调用refresh()是可以的，首先会从Xml文件重新加载ApplicationContext，然后调用ContextRefreshedEvent接口
		 * 注：close的作用是暂停提供获取bean的作用，稍后如果调用refresh()后，可以重新使用。
		 * 
		 * 5.RequestHandledEvent: 略
		 */
		ConfigurableApplicationContext cap = new ClassPathXmlApplicationContext(
				"classpath:test/frame/spring/applicationContext.xml");
		// 获取自定义事件发布器并发布事件
		CustomPublisher cPublisher = cap.getBean(CustomPublisher.class);
		cPublisher.publish();

		//
		cap.close();
	}
}