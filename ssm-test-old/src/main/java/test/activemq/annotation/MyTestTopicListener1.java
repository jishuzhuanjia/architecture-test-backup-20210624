package test.activemq.annotation;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * 注解形式实现MQ监听,注解形式不需要实现MessageListener接口
 * 可以定义为pojo，如果需要设置默认类中所有监听器默认topic消息队列名，请extends MessageListenerAdapter
 * */
@Component("myTestTopicListener1")
public class MyTestTopicListener1 extends MessageListenerAdapter{
	
	@Override
	public void setDefaultResponseTopicName(String destinationName) {
		// TODO Auto-generated method stub
		super.setDefaultResponseTopicName("135.topic");
	}

	public MyTestTopicListener1() {
		// TODO Auto-generated constructor stub
		System.out.println("MyTestTopicListener created");
	}
	
	@PostConstruct
	public void test1() {
		System.out.println("after created");
	}

	/**
	 * <ul>
	 * <li>1.destination：消息队列名,@JmsListener必须指定的属性</li>
	 * <li>2.containerFactory：如果不配置也会去找名为jmsListenerContainerFactory的bean如果找不到，报错</li>
	 * <li>3.决定监听器是topic/quque类型，主要看containerFactory bean pubSubDomain属性，true则是topic类型</li>
	 * <li>4.concurrency：并发限制，10则表示上限10,此时下限为1，5-10则表示范围，注意，底层容器可能支持也可能不支持所有特性。例如，它可能无法缩放，在这种情况下只使用上限。</li>
	 * 
	 * </ul>
	 * */
	@JmsListener(concurrency="1",destination="135.topic",containerFactory="jmsListenerContainerTopicFactory")
	public void onMessage(Message message) {
		
		System.out.println("mq topic listener by annotation");
		try {
			System.out.println(((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
