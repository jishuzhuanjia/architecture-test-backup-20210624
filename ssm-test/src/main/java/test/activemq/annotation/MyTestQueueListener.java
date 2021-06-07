package test.activemq.annotation;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 注解形式实现MQ监听,注解形式不需要实现MessageListener接口
 * 简单的POJO定义只能接受queue-p2p消息
 * */
@Component("myTestQueueListener1")
@EnableJms
public class MyTestQueueListener {
	
	public MyTestQueueListener() {
		// TODO Auto-generated constructor stub
		System.out.println("MyTestQueueListener created");
	}
	
	@PostConstruct
	public void test1() {
		System.out.println("after created");
		
	}

	/**
	 * <ul>
	 * <li>1.destination：消息队列名</li>
	 * <li>2.containerFactory：如果不配置也会去找名为jmsListenerContainerFactory的bean如果找不到，报错</li>
	 * <li>3.决定监听器是topic/quque类型，主要看containerFactory bean pubSubDomain属性，true则是topic类型</li>
	 * <li>4.concurrency：并发限制，10则表示上限10,此时下限为1，5-10则表示范围，注意，底层容器可能支持也可能不支持所有特性。例如，它可能无法缩放，在这种情况下只使用上限。</li>
	 * 
	 * </ul>
	 * */
	@JmsListener(concurrency="1",destination="135.queue",containerFactory="jmsListenerContainerFactory")
	public void onMessage(Message message) {
		
		System.out.println("mq queue listener by annotation");
		try {
			System.out.println(((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
