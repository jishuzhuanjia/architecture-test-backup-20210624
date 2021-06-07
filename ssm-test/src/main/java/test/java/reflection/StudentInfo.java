package test.java.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


import org.junit.Test;

/* *
 * @time：2020年2月13日 下午8:39:20
 * @Author：zhoujian
 * @QQ：2025513
 * @description：
 */

/**
 * 学生信息实体类
 * */
public class StudentInfo {
	
	// 枚举类型
	private static  Sex sex = Sex.male;
	
	// 静态公开常量：成年的年纪
	static public int DEFAULT_ADULT_AGE = 18;
	
	// 静态私有常量：默认学生性别，男
	private static final String DEFAULT_SEX = "男";
	
	// 私有属性：学生姓名
	private String sName;
	
	// 公开属性：学生年纪
	public int sAge;
	
	// Test class should have exactly one public zero-argument constructor
	public StudentInfo() {
		// TODO Auto-generated constructor stub
	}
	
	// Test class can only have one construtor
	/*public StudentInfo(String sName,int sAge) {
		// TODO Auto-generated constructor stub
		this.sName = sName;
		this.sAge = sAge;
	}*/
	
	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}



	/**
	 * 获取属性
	 * 
	 * 获取属性的几个方法：
	 * 1. Field java.lang.Class.getDeclaredField(String name) 
	 * 2. Field[] java.lang.Class.getDeclaredFields() 
	 * 
	 * 3. Field java.lang.Class.getFields(String name) 
	 * 4. Field[] java.lang.Class.getFields()
	 * 
	 * getDeclaredField/getDeclaredFields  VS  getFields/getFields
	 * 前者只能返回本类定义的属性，可以是任何修饰符的属性，后者可以获取父类/接口中的属性，需要注意的是后者只能获取公开
	 * public修饰的属性。
	 * 
	 * 
	 * */
	@Test
	public void test1() {
		
		// 1.获取Class
		Class<?> cls = StudentInfo.class;
		
		
		// 2.获取属性：以下只是通过映射获取代表属性的对象Field，而不是获取属性值。
		// 如果找不到指定的属性就会抛出NoSuchFieldException异常。
		try {
			// 2.1.获取静态公开常量
			Field field1 = cls.getDeclaredField("DEFAULT_ADULT_AGE");
			
			// 2.2.获取静态私有常量
			Field field2 = cls.getDeclaredField("DEFAULT_SEX");
			// 2.3.获取私有属性：学生姓名
			Field field3 = cls.getDeclaredField("sName");
			// 2.3.获取公开属性：学生姓名
			Field field4 = cls.getDeclaredField("sAge");
			
			// 3.getDeclaredFields()的使用
			for(Field field: cls.getDeclaredFields()) {
				// 4.探索Field常用方法
				// 4.1.获取属性名
				String fieldName = field.getName();
				System.out.println("Name: " + fieldName);
				
				// 4.2.获取表示修饰符组合的整数,不同的组合有不同的值
				int modifiersInt = field.getModifiers();
				// 修饰符int -> 修饰符字符串
				String modifiersString = Modifier.toString(modifiersInt);
				System.out.println("Modifiers: " + modifiersString);
				
				// 4.3.Accessiable
				// 设置值为true表示反射对象应该在使用时抑制Java语言访问检查。
				// 设置值为false表示反射对象应强制执行Java语言访问检查。
				// 从输出可以看到不管private/public属性，该方法都返回false
				System.out.println("Accessable：" + field.isAccessible());
				
				// 4.4.检测属性是否为枚举常量，需要注意的是Field必须是注解Class的，否则返回false
				System.out.println("isEnumConstant：" + field.isEnumConstant());
				
				// 4.5.合成属性 - 与内部类和及其外部类相互引用有关，子类中有父类的引用属性，用来引用父类
				// 子类中也会为属性添加方法，用来访问子类的属性。
				// class name:com.synthetic.MainClass$SubClass-access$100:true
				// class name:com.synthetic.MainClass$SubClass-access$200:true
				
				//参考：http://blog.sina.com.cn/s/blog_1534f339a0102y88n.html
				System.out.println("isSynthetic：" + field.isSynthetic());
				
				// 4.7.打印
				/*toGenericString是Constructor,Method,Field这三个类专有的方法，
				如Constructor类，给出的是对应的构造函数的细节，比如修饰符，构造函数名和参数列表等。
				这个方法其他类，如String或List无法调用。*/
				System.out.println("toGenericString：" + field.toGenericString());
				// 两者输出是一样的。
				System.out.println("toString：" + field.toString());
				
				System.out.println("-----");
			} 
			
			/* output:
			Name: sex
			Modifiers: private static
			Accessable：false
			isEnumConstant：false
			isSynthetic：false
			toGenericString：private static test.java.reflection.Sex test.java.reflection.StudentInfo.sex
			toString：private static test.java.reflection.Sex test.java.reflection.StudentInfo.sex
			-----
			Name: DEFAULT_ADULT_AGE
			Modifiers: public static
			Accessable：false
			isEnumConstant：false
			isSynthetic：false
			toGenericString：public static int test.java.reflection.StudentInfo.DEFAULT_ADULT_AGE
			toString：public static int test.java.reflection.StudentInfo.DEFAULT_ADULT_AGE
			-----
			Name: DEFAULT_SEX
			Modifiers: private static final
			Accessable：false
			isEnumConstant：false
			isSynthetic：false
			toGenericString：private static final java.lang.String test.java.reflection.StudentInfo.DEFAULT_SEX
			toString：private static final java.lang.String test.java.reflection.StudentInfo.DEFAULT_SEX
			-----
			Name: sName
			Modifiers: private
			Accessable：false
			isEnumConstant：false
			isSynthetic：false
			toGenericString：private java.lang.String test.java.reflection.StudentInfo.sName
			toString：private java.lang.String test.java.reflection.StudentInfo.sName
			-----
			Name: sAge
			Modifiers: public
			Accessable：false
			isEnumConstant：false
			isSynthetic：false
			toGenericString：public int test.java.reflection.StudentInfo.sAge
			toString：public int test.java.reflection.StudentInfo.sAge
			-----
			-----*/
			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
