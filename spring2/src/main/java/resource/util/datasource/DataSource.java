package resource.util.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @time 2019年12月25 14:02:07
 * @author zj
 * @corporation luku
 * @description: 动态数据源注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface DataSource {
	String value();
}



