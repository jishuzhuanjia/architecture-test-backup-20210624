package com.zj.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/12/29 15:58
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootApplication
public class KafkaApplication {

    /*topic不存在启动会报错*/
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class,args);
    }
}
