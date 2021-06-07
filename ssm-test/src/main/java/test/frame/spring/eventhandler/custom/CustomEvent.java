package test.frame.spring.eventhandler.custom;

import org.springframework.context.ApplicationEvent;

/*
 * 自定义事件类:需要继承自ApplicationEvent
 * 注意是一个类，不是接口
 * */
public class CustomEvent extends ApplicationEvent{
	/**
	 */
	private static final long serialVersionUID = 1L;

	/* 作为事件源 */
	public CustomEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
}
