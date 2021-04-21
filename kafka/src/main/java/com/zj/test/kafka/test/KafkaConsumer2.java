package com.zj.test.kafka.test;

import com.zj.test.util.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * author: zhou jian
 * qq: 2025513
 * description: 
 * version: 1.0
 * create-time: 2021/4/21 16:34
 * finished: false
 * finished-time: 
 */

//@Component
@Slf4j
public class KafkaConsumer2 {

    /* ----------------------------------------- 测试相同groupId的consumer是否会多次消费 ---------------------------------------- */

    /*
     * 1.同一项目中，同一groupId只应该出现在@KafkaListener中一次，同时为多个@KafkaListener
     * 指定相同groupId最终起作用的只有一个。
     */


    /**
     *
     */
    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = "group-a",containerFactory = "simpleFactory")
    public void group_a_01(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("group_a_01 收到kafka消息:");
            TestHelper.println("topic" ,topic);
            TestHelper.println("message" ,msg);
            TestHelper.println("consumer groupId" ,"group-a");

            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = "group-a",containerFactory = "simpleFactory")
    public void group_a_02(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("group_a_02 收到kafka消息:");
            TestHelper.println("topic" ,topic);
            TestHelper.println("message" ,msg);
            TestHelper.println("consumer groupId" ,"group-a");

            ack.acknowledge();
        }
    }
}
