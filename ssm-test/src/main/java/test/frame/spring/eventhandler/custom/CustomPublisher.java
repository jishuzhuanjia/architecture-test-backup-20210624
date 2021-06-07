package test.frame.spring.eventhandler.custom;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/* 自定义事件发布类
 * 
 * Spring发布事件类：需要实现ApplicationEventPublishAware接口，保存发布器实例，并使用该实例发布事件，
 * 
 * 发布的时候获取自定义事件发布类，调用自己的发布方法。
 *  
 * 需要使用扫描注解
 *  */
@Component
public class CustomPublisher implements ApplicationEventPublisherAware {
	
	ApplicationEventPublisher publisher;
	
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		
		this.publisher =applicationEventPublisher;
	}
	
	/*
	 * 成员方法，不是继承来的方法
	 * */
	public void publish() {
		
		CustomEvent cEvent = new CustomEvent(this);
		
		//publishEvent(Object)
		publisher.publishEvent(cEvent);
		
	}
}
