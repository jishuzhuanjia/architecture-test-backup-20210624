package test.frame.spring.junittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.frame.spring.injection.TestPojo;

/*
 * @time  2019锟斤拷12锟斤拷31锟斤拷 锟斤拷锟斤拷4:22:43
 * @author  zhoujian
 * @corporation  luku
 * @description  演示spring test的使用
 * @finished  true
 * @finishTime  
 * @version  1.0
 */

/*
 * 会自动初始化spring容器，并对测试类进行注解扫描，自动完成注入。
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test/frame/spring/applicationContext.xml")
public class JunitTest {
	
	@Autowired
	TestPojo testPojo;
	
	@Test
	public void test1() {
		//System.out.println(testPojo.testServiceImpl.age);
	}
}