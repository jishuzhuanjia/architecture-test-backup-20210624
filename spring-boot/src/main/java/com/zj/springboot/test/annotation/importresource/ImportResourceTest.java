package com.zj.springboot.test.annotation.importresource;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 10:20
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// @ImportResource必须和实例化注解一起使用,如@Configuration,@Service,@Component,否则无效
// classpath:不能省略,否则报错
//@Configuration
@Slf4j
// 如果文件不在根目录下,需要使用'/'作为路径分隔符,而不能使用'.',否则会报文件找不到错误
// classpath:不能省略,否则报错
// @ImportResource({"classpath:com.zj.springboot.test.annotation.importresource.applicationContext.xml"})
@ImportResource({"classpath:com/zj/springboot/test/annotation/importresource/applicationContext.xml"})
public class ImportResourceTest {

    public ImportResourceTest(){
        log.info("ImportResourceTest");
    }
}
