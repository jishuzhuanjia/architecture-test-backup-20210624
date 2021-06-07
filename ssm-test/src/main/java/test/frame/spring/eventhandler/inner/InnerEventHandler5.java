package test.frame.spring.eventhandler.inner;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;



/*
 * 未进行调用时机测试 
 * 
 * */


@Component
public class InnerEventHandler5 implements ApplicationListener<RequestHandledEvent>{

	public void onApplicationEvent(RequestHandledEvent event) {
		
		System.out.println("Request Handled");
	}
}








