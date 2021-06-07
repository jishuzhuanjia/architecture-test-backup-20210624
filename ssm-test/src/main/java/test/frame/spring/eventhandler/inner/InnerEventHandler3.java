package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/*
 * Spring内置事件处理: refreshed
 * 注意： 只能实现一个ApplicationEvent接口：
 * 如实现了ApplicationListener<ContextStartedEvent>就不能实现ApplicationListener<ContextStoppedEventedEvent>
 * 
 * 
 * 内置事件： started,refreshed,stoped,closed和RquestHandledEvent
 * */

@Component
public class InnerEventHandler3 implements ApplicationListener<ContextRefreshedEvent>{


	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("Spring context refreshed");
	}
}







