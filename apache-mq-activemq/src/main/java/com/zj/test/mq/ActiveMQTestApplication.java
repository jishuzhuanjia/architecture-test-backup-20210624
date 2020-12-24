package com.zj.test.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/23 13:37
 * @description:
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月23日 16:29:00
 */
@SpringBootApplication
@EnableJms// 经测试,本版本该注解可有可无,为了避免错误发生,添加此注解
public class ActiveMQTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMQTestApplication.class, args);
    }
}
