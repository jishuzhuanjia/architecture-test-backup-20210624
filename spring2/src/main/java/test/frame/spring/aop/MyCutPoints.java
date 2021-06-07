package test.frame.spring.aop;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class MyCutPoints {
	
	public static void staticMethod() {
		System.out.println("static method called");
	}

	// 定义一个私有方法
	 void privateMethod() { 
		System.out.println("被切点调用的私有方法");
	}
	 
	 public void methodWithParam(String s) {
		 System.out.println("methodWithParam called");
		 //int i=1/0;
	 }
	 
	 public void methodWithParam(String s,int i) {
		 System.out.println("methodWithParam called");
		 //int i=1/0;
	 }
	 
	 public void methodWithParam(String s,Integer i) {
		 System.out.println("methodWithParam called");
		 //int i=1/0;
	 }
	 
	 public void methodWithParam(String s,float f) {
		 System.out.println("methodWithParam called");
		 //int i=1/0;
	 }
	 
	 public void methodWithParam(String s,double d) {
		 System.out.println("methodWithParam called");
		 //int i=1/0;
	 }
	 
	 public void methodWithParam(String s,boolean b) {
		 System.out.println("methodWithParam called");
		 //int i=1/0;
	 }
	 
	 
	 
	 public void add() {
		privateMethod();
		System.out.println("业务add");
		

		// int i = 1/0;
	}

	public void delete() {
		System.out.println("业务delete");
	}

	public void update() {
		System.out.println("业务update");
	}
}
