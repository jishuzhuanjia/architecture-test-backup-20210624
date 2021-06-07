package test.frame.spring.injection.xml;

import org.apache.activemq.console.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.frame.spring.injection.xml.bean.Student;

/**
 * @CreateTime：2020年2月9日 下午4:28:45
 * @Author：zhoujian @QQ：2025513
 * @FileDescription：xml创建实例和属性注入
 * @IsFinished：false
 */

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration("test/frame/spring/injection/xml/applicationContext.xml")
public class JUnitTest {

	public static void main(String[] args) {
		//这里clsspath只能是小写
		//classpath:后/是可选的
		AbstractApplicationContext abstractApplicationContext = new ClassPathXmlApplicationContext(
				"classpath:/test/frame/spring/injection/xml/applicationContext.xml");

		// 1.通过Class类型获取bean
		// Student student = (Student)
		// abstractApplicationContext.getBean(Student.class);
		// System.out.println(student);

		// Student student2 = (Student) abstractApplicationContext.getBean("student2");
		// System.out.println(student2);

		// Student student3 = (Student) abstractApplicationContext.getBean("student3");
		// System.out.println(student3);
		
		// Student student4 = (Student) abstractApplicationContext.getBean("student4");
		// System.out.println(student4);
		
		// 2.当同类型bean有多个时，通过类型获取会抛出异常:
		// org.springframework.beans.factory.NoUniqueBeanDefinitionException
		// Student anyStudent = abstractApplicationContext.getBean(Student.class);
		// System.out.println(anyStudent);
		//false 
		System.out.println(abstractApplicationContext.getBean("student4") == abstractApplicationContext.getBean("student4"));
	}
}
