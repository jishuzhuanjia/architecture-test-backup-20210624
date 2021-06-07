package test.frame.spring.aop.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
  * @time：2020年1月2日 下午9:31:26
  * @author：zhoujian
  * @corporation：luke
  * @description：
  * @finished：false
  * @finishTime：
  *
  */

@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration("classpath:test/frame/spring/applicationContext.xml")
public class Junit {
	@Autowired
	Student student;
	
	@Test
	public void test1() {
		try {
			try {
				/* try{
					    try{
					        //@Before
					        method.invoke(..);
					    }finally{
					        //@After
					    }
					    //@AfterReturning
					}catch(){
					    //@AfterThrowing
					}
				 * 
				 * 
				 * 正确的顺序
				 * before
				   getName
				   after
				   afterReturning
				   zhoujianxxxx
				   afterThrowing
				 * 
				 * 
				 * 
				 * */
				System.err.println("start...");
				System.err.println(student.getName());
				System.err.println("----------------");
				System.err.println(student.getAge());
			} finally {
				// TODO: handle finally clause
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(1);
	}
	
	
	
	

}
