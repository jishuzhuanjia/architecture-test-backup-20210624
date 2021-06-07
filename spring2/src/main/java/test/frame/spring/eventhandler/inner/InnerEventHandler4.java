package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;


/*
 * Spring内置事件处理
 * 注意： 只能实现一个ApplicationEvent接口：
 * 如实现了ApplicationListener<ContextStartedEvent>就不能实现ApplicationListener<ContextStoppedEventedEvent>
 * 
 * 
 * 内置事件： started,refreshed,stoped,closed和RquestHandledEvent
 * */

@Component
public class InnerEventHandler4 implements ApplicationListener<ContextClosedEvent>{


	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println("Spring context closed");
	}
}








