package com.zj.test.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/20 23:41
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class})
@MapperScan("com.zj.test.datasource.mapper")
public class UtilboxDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilboxDataSourceApplication.class, args);
    }
}
