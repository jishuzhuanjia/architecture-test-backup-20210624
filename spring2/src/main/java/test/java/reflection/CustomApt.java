package test.java.reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/* *
 * @time：2020年2月12日 下午5:07:50
 * @Author：zhoujian
 * @QQ：2025513
 * @description：自定义注解处理器
 */

/**
 * 例：通过对象实例获取其类、方法、属性、参数、方法局部变量处的注解。
 * */
public class CustomApt {
	
	public static void main(String[] args) {
		Student student = new Student();
	
		Class<? extends Student> cls = student.getClass();
		// 1.获取类上的AlertMessage注解
		// 如果没有，返回null
		AlertMessage classAnnotation = (AlertMessage) cls.getAnnotation(AlertMessage.class);
		if(classAnnotation != null) {
			for (int i = 0; i < classAnnotation.alertCount(); i++) {
				System.out.println("[大喇叭通知 ]: " + classAnnotation.value() );
			}
		}
		
		// 2.获取public属性username属性的注解
		try {
			Field usernameField = cls.getDeclaredField("username");
			AlertMessage fieldAnnotation = (AlertMessage) usernameField.getAnnotation(AlertMessage.class);
			if(classAnnotation != null) {
				for (int i = 0; i < fieldAnnotation.alertCount(); i++) {
					System.out.println("[大喇叭通知 ]: " + fieldAnnotation.value() );
				}
			}
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 3.获取private属性int注解
		
		try {
			// getField只能获取public属性
			Field ageField = cls.getField("age");
			AlertMessage ageFieldAlertMessage = ageField.getAnnotation(AlertMessage.class);
			for(int i=0;i<ageFieldAlertMessage.alertCount();i++) {
				System.out.println(ageFieldAlertMessage.value());
			}
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 4.doHomeWork方法注解获取
		try {
			Method method4 = cls.getMethod("doHomeWork");
			System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 获取私有方法
		// getMethod只能获取共有方法
		try {
			// getDeclaredMethod可以获取私有方法
			Method method4 = cls.getDeclaredMethod("getInternetCafe");
			System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// getMethod可以获取父类中的public方法
			Method method4 = cls.getMethod("toString");
			//System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			// getMethod可以获取父类中的public方法
			Method method4 = cls.getMethod("toString");
			//System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			// getMethod可以获取父类中的public方法
			// getDeclaredMethod - 只能获取类中定义的方法(包括实现接口的方法)，可以是任何修饰符，不能获取父类中的方法
			// java.lang.NoSuchMethodException: test.java.reflection.Student.toString()
			Method method5 = cls.getDeclaredMethod("toString");
			//System.out.println(method4.getAnnotation(AlertMessage.class).value());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@Test
	public void test1() {
		
	}

}

interface HomeWork{
	public void doHomeWork();
}

@AlertMessage(value="作为学生,你必须要遵守校纪！！！重要的事情我说三遍",alertCount=3)
class Student implements HomeWork{
	
	@AlertMessage(value="冠状病毒同学,我已经警告你三次了，我要对你进行强制措施~")
	private String username;
	
	@AlertMessage("3岁的冠状病毒")
	public String age;
	
	/** 学生去网吧的方法 */
	@AlertMessage(value="作为学生,你必须要遵守校纪！！！",alertCount=3)
	private void getInternetCafe() {
		
		@AlertMessage(value="呦,10块钱一小时?这次给你罚款1000",alertCount=1)
		int pay;
		
	}

	@AlertMessage("做作业")
	public void doHomeWork() {
		// TODO Auto-generated method stub
		
	}
}

// 此注解用来在使用它的地方提供警告信息
@Retention(value=RetentionPolicy.RUNTIME)
@interface AlertMessage{
	// 警告信息
	public String value();
	
	// 警告次数,默认一次
	public int alertCount() default 1;
}




