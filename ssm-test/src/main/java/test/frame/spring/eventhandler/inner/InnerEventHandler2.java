package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;


/*
 * Spring内置事件处理: stoped
 * 注意： 只能实现一个ApplicationEvent接口：
 * 如实现了ApplicationListener<ContextStartedEvent>就不能实现ApplicationListener<ContextStoppedEventedEvent>
 * 
 * 
 * 内置事件： started,refreshed,stoped,closed和RquestHandledEvent
 * */

@Component
public class InnerEventHandler2 implements ApplicationListener<ContextStoppedEvent>{

	public void onApplicationEvent(ContextStoppedEvent event) {
		System.out.println("Spring context stoped");
	}
}








