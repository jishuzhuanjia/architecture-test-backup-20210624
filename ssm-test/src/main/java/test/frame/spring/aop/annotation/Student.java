package test.frame.spring.aop.annotation;

import org.springframework.stereotype.Service;

/**
 * @time：2020年1月2日 下午9:27:29
 * @author：zhoujian
 * @corporation：luke
 * @description：切点类
 * @finished：false @finishTime：
 *
 */

@Service
public class Student {

	private String name = "zhoujianxxxx";
	private int age = 25;

	public String getName() {
		System.out.println("in des method");
		//System.err.println("getName");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		System.out.println("in des method");
		//@SuppressWarnings("unused")
		//int i=1/0;
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
