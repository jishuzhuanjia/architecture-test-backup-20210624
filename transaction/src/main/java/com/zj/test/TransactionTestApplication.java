package com.zj.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/13 13:45
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zj.test.transaction.mapper")
public class TransactionTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionTestApplication.class,args);
    }
}
