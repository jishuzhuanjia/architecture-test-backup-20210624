package com.zj;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/20 16:27
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@MapperScan("com.zj.util.datasource")
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class})
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class,args);
    }
}
