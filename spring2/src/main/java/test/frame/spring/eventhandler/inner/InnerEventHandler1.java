package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/*
 * Spring内置事件处理: started
 * 注意： 只能实现一个ApplicationEvent接口：
 * 如实现了ApplicationListener<ContextStartedEvent>就不能实现ApplicationListener<ContextStoppedEventedEvent>
 * 
 * 内置事件： started,refreshed,stopped,closed和RequestHandledEvent
 * */

//  事件处理器需要被实例化
@Component
public class InnerEventHandler1 implements ApplicationListener<ContextStartedEvent>{

	public void onApplicationEvent(ContextStartedEvent event) {
		System.out.println("Spring context started");
	}
}