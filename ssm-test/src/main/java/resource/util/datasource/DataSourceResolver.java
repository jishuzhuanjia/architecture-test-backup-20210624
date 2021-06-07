package resource.util.datasource;

import org.aspectj.lang.annotation.Aspect;

/**
 * @time 2019年12月25日 下午4:32:57
 * @author zj
 * @corporation luku
 * @description:
 */
@Aspect
public class DataSourceResolver {
	public static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	/**
	 * 设置当前线程对应的DataSource名
	 * */
	public static void setDataSource(String dataSourceName) {
		threadLocal.set(dataSourceName);
	}
	
	/**
	 * 获取当前线程DataSource名
	 * */
	public static String getDataSource() {
		return threadLocal.get();
	}
}



