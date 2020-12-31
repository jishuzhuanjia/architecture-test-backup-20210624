package com.zj.test.kafka.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/12/29 17:02
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@RestController
@RequestMapping("/test")
public class KafkaTestController {

    @Autowired
    KafkaProducer kafkaProducer;

    @RequestMapping("send")
    public String send(){
        kafkaProducer.send("this is a kafka message!");
        return "ok";
    }
}
