package test.frame.spring.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * @time  2019年12月31日 下午3:29:57
 * @author  zhoujian
 * @corporation  luku
 * @description   测试引用类型属性的注入
 * @finished  true
 * @finishTime  2020年1月18日20:51:34
 * @version  1.0
 */

@Component
public class TestPojo {
	
	/* 1. 按照类型注入
	 * 不需要参数，缺点是只能按照类型注入
	 * 注意：为了支持动态代理，必须定义为接口类型，而不能是具体的实现类。
	 * 
	 * 以下定义方法就是错误的：
	 * TestServiceImpl testServiceImpl;
	 * */
	@Autowired
	public TestService testServiceImpl;
	
	/* 2.@Resource
	 * 如果不提供name属性，按照class注入
	 * 如果提供name属性，则按照bean name注入，不按照class注入。
	 * 如果bean name 不存在，则报错。
	 * 缺点是只能按照beanname/class进行绑定
	 * */ 
	
	//@Resource(name="testServiceImpl")
	public TestServiceImpl testServiceImpl2;
	
	/* 3.下面这个注解就为了解决不能同时指定bean name和class的缺点
	 * @Qualifier- 单独存在没有意义，需要和@Autowired一起使用
	 * 
	 * 如果@Autowired和@Qualifier同辉存在，则要求bean同时满足类型和name要求，
	 * 如果没有指定的bean，则会报错。
	 * */
	
	//@Autowired
	//@Qualifier("testServiceImpl")
	public TestServiceImpl testServiceImpl3;
	
	// 单独存在没有意义，虽然bean name存在，testServiceImpl4为null
	// 但是不会报错
	//@Qualifier("testServiceImpl")
	//public TestServiceImpl testServiceImpl4;
}