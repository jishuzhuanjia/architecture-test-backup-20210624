package test.frame.spring.aop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 测试类
 * */
public class JUnitTest {
	
	@Test
	public void test1() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:test/frame/spring/aop/beans.xml");
		
		MyCutPoints cutPoints = context.getBean(MyCutPoints.class);
		
		//cutPoints.add();
		
		//cutPoints.privateMethod();
		
		// 1.public/缺省修饰的非静态方法会被AOP处理。
		// 2.切点内调用其他切点,被调用的切点不会触发AOP。
		//cutPoints.staticMethod();
		
		//context.close();
		
		//cutPoints.methodWithParam("Hello",true);
		
		// cutPoints.methodWithParam("Hello",1);
		/*cutPoints.methodWithParam("Hello", 1.0);
		cutPoints.methodWithParam("Hello", false);

		cutPoints.methodWithParam("", new Integer(1));*/
		cutPoints.methodWithParam("");
		
		//System.out.println(-1&1);
	}
	
	public static void main(String[] args) {
		FileOutputStream fileOutputStream = null;
		Scanner scanner = null;
		
		try {
			fileOutputStream = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\user.txt"),true);
		    scanner = new Scanner(System.in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true) {
			System.out.println("请输入内容:");
			String content = scanner.nextLine();
			System.out.println("用户输入:" + content);
			
			try {
				fileOutputStream.write(content.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
