package com.zj.springboot.test.annotation.propertysource;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 11:05
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

// @Component
// @PropertySource也需要和实例化注解一起使用,如@Configuration,@Componnet,否则无效
// 路径分隔符是/而不是.否则报错
// classpath:不能省略,否则报错
@PropertySource("classpath:com/zj/springboot/test/annotation/propertysource/db.properties")
public class PropertySourceTest {
    @Value("${db.username}")
    String username;

    @Value("${db.password}")
    String password;

}
