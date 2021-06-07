package resource.util.datasource;

import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @time 2019年12月25日 下午4:45:20
 * @author Administrator
 * @corporation luku
 * @description: 动态数据源通知类
 */
public class DynamicDataSourceAdvisor {
	
	/**
	 * 根据接口类决定数据源
	 * */
	public void before(JoinPoint joinPoint) {
		// 切点类实例对象
		Object target = joinPoint.getTarget();
		
		// 获取方法名: 因为@DataSource是方法注解
		String methodName = joinPoint.getSignature().getName();
		// 获取参数类型
		Class<?>[] paramTypes = ((MethodSignature)joinPoint.getSignature())
				.getMethod().getParameterTypes();
		
		// 获取对象实现的接口,默认查找第一个接口的方法注解，请确保service实现类只实现一个接口
		Class<?>[] interfaces = target.getClass().getInterfaces();
		
		// 获取指定的方法
		try {
			//找不到返回null
			Method method = interfaces[0].getMethod(methodName, paramTypes);
			
			/*method.isAnnotationPresent(annotationClass);
			 * 
			 * 如果此元素上存在指定类型的注释，则返回true，否则为false。此方法主要用于方便地访问标记注释。
	         *该方法返回的真值等于:getAnnotation(annotationClass) != null
	         *默认方法的主体指定为上面的代码。
	         * */
			
			//获取@DataSource注解
			
			//进行非空判断，因为如果实现多个接口，可能0号接口没有指定的方法
			if(method != null) {
				
				DataSource dataSource = method.getAnnotation(DataSource.class);
				if(dataSource != null) {
					System.out.println("线程"+ Thread.currentThread().getName()+ "(" +Thread.currentThread().getId()+")选择数据库:" +dataSource.value());
					DataSourceResolver.setDataSource(dataSource.value());
				}
			}
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}



