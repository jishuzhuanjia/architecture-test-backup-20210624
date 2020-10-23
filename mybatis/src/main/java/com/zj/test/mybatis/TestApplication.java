package com.zj.test.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/* @author: zhoujian
 * @create-time: 2020/9/16 21:21
 * @description:
 * @version: 1.0
 */
@SpringBootApplication
//注意，如果使用mybatis mapper jar的开发方式，需要使用tk.mybatis.spring.annotation.MapperScan
//来替换@org.mybatis.spring.annotation.MapperScan
//否则会出现错误,like:
//tk.mybatis.mapper.MapperException: 无法获取实体类test.po.User对应的表名!
//注：之前的mybatis xml和mapper的开发模式仍然有效。
@MapperScan("com.zj.test.mybatis.mapper")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }
}
