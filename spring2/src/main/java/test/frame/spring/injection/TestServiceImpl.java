package test.frame.spring.injection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.zj.test.util.TestHelper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
 * @time  2019年12月31日 下午2:55:14
 * @author  zhoujian
 * @corporation  luku
 * @description  测试简单类型注入
 * @finished  false
 * @finishTime  
 * @version  1.0
 */

/*
 * 注意：为了支持动态代理，注入的属性的bean需要定义为接口，而不是具体的实现类。
 * 
 * */


// @Service("myTestServiceImpl")
// @Component("myTestServiceImpl")
// @Repository("myTestServiceImpl")
// 经测试，这些注解作用一样，只是语义的差别。
//这种写法会记录class 和 bean name
//@Controller("myTestServiceImpl")

//如果不提供bean name，则记录class，bean name等于类名的驼峰命名，此处等于testServiceImpl
@Service
//单独此注解没有意义
//@Scope(scopeName="prototype")  
public class TestServiceImpl implements TestService{
	//1.@Value注入简单类型,会自动完成从字符串到指定类型的转换
	// 该注解可用于属性和方法
	// 1.1.字符串 
	@Value("zhoujian")
	public String username;
	
	// 1.2.数值 
	@Value("25")
	public int age;
	
	// 1.3.boolean
	@Value("true")
	public boolean firstEnter;
	

	public String getUsername() {
		return username;
	}

	//@Required：强制为属性指定值。如果添加Required注解，而没有添加Value注解，报错：
	// Property 'username' is required for bean 'testServiceImpl'
	@Required
	@Value("z_j")
	public void setUsername(String username) {
		System.out.println("setUsername");
		this.username = username;
	}

	public void test1() {
		// TODO Auto-generated method stub
		
		System.out.println("test1()");
		
	}
	
	/**
	 * 初始化以后就调用，在setter之前。
	 * */
	/*@PostConstruct
	public void postConstrut() {
		System.out.println("postConstrut");
	}*/
	
	/**
	 * 容器销毁之前调用
	 * */
	/*@PreDestroy
	public void preDestroy() {
		System.out.println("preDestroy1");
	}*/

	/*public TestServiceImpl(){
		TestHelper.println("TestServiceImpl: 无参构造函数");
	}*/

	/*public TestServiceImpl(String s){
		TestHelper.println("TestServiceImpl: 有参构造函数");
	}*/
	
}