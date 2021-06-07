package test.java.reflection;

import org.junit.Test;

/*
 * @CreateTime：2020年2月12日 下午4:34:09
 * @Author：zhoujian
 * @QQ：2025513
 * @FileDescription：
 * @IsFinished：false
 */

/**
 * 测试: Class.getInterfaces()返回哪些接口？
 * 结论：只能返回本类定义通过implements显示添加的接口,而不能返回父类实现的接口。
 * */
public class ReflectionInterfaceTest {
	/**
	 * 测试1：什么样的接口会被Class.getInterfaces返回?
	 * 
	 * 答: 该Class定义必须显示定义implements InterfaceName...否则不会被返回，就算父类实现了
	 * 该接口也没用。
	 * */
	@Test
	public void test1() {
		Class<?>[] interfacesB = B.class.getInterfaces();
		Class<?>[] interfacesC = C.class.getInterfaces();
		Class<?>[] interfacesD = D.class.getInterfaces();
		System.out.println("-----B实现的接口----");
		
		for (Class<?> interface1 : interfacesB) {
			// interface test.java.reflection.TestInterface
			System.out.println(interface1);
		}
		
		System.out.println("-----C实现的接口----");
		for (Class<?> interface2 : interfacesC) {
			// interface test.java.reflection.TestInterface
			System.out.println(interface2);
		}
		// false
		System.out.println(interfacesC ==null);
		// 0
		System.out.println(interfacesC.length);
		
		System.out.println("-----d实现的接口----");
		for (Class<?> interface3 : interfacesD) {
			// interface test.java.reflection.TestInterface
			System.out.println(interface3);
		}
		
		
	}

}

interface TestInterface{
	public void interfaceMethod();
}

class A implements TestInterface{

	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("interfaceMethod A");
	}
}

/** 注：由于父类已经实现了接口,子类如果再实现接口,不会提示添加方法,但是可以覆盖 */
// -----B实现的接口----
// interface test.java.reflection.TestInterface
class B extends A implements TestInterface{

	@Override
	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("interfaceMethod B");
	}
}

// -----C实现的接口----
class C extends A {

	@Override
	public void interfaceMethod() {
		// TODO Auto-generated method stub
		System.out.println("interfaceMethod C");
	}
	
}

// -----d实现的接口----
// interface test.java.reflection.TestInterface
class D extends A implements TestInterface{
}







