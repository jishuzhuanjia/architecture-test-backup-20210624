package test.frame.spring.injection.xml.bean;

import org.apache.commons.io.output.ThresholdingOutputStream;

/**
 * @CreateTime：2020年2月9日 下午4:34:43
 * @Author：zhoujian
 * @QQ：2025513
 * @FileDescription：
 * @IsFinished：false
 */

public class Student {
	
	// 静态工厂方法
	// 可以为private
	private static Student createStudent() {
		return new Student(22, "Student3");
	}
	
	private String name;
	
	private int age;
	
	boolean oldEnough;
	
	public Student() {
		// TODO Auto-generated constructor stub
		System.out.println("Default Student Construtor");
	}
	
	public Student(int age,String name) {
		// TODO Auto-generated constructor stub
		System.out.println("Student Construtor2");
		this.name = name;
		this.age=age;
	}
	
	public Student(String name,int age) {
		// TODO Auto-generated constructor stub
		System.out.println("Student Construtor1");
		this.name = name;
		this.age=age;
	}

	public void setOldEnough(boolean oldEnough) {
		this.oldEnough = oldEnough;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Student name: " +name +",age: " + age + ",oldEnough: " + oldEnough;
	}
	
	//init-method
	//可以指定返回类型
	public String initMethod() {
		System.out.println("Init-method called");
		return "1";
	}
	
	//destroy-method
	//调用applicationContext.close()销毁容器之前会首先调用bean的destroy-method
	public void destroyMethod() {
		System.out.println("Destroy-method  called");
	}
}
