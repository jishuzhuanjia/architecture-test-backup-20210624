package test.frame.spring.eventhandler.custom;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/*
 * 自定义事件处理器，实现ApplicationListener<T> 接口
 * 需要使用扫描注解
 * */
@Component
public class CustomHandler implements ApplicationListener<CustomEvent> {

	public void onApplicationEvent(CustomEvent event) {
		// TODO Auto-generated method stub
		System.out.println("自定义处理器");

		System.out.println("事件源是否是CustomPublisher: " + (event.getSource() instanceof CustomPublisher));
	}

}
