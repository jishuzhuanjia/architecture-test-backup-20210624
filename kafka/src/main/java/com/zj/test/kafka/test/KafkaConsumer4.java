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
public class KafkaConsumer4 {

    /* ----------------------------------------- 测试：不指定groupId会怎样 ---------------------------------------- */
    /*
    测试结果：多个没有指定groupId的@KafkaListener只会有一个会消费消息
     */

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, containerFactory = "simpleFactory")
    public void no_id_01(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("no_id_01 收到kafka消息:");
            TestHelper.println("topic", topic);
            TestHelper.println("message", msg);
            TestHelper.println("consumer groupId", "group-a");

            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, containerFactory = "simpleFactory")
    public void no_id_02(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("no_id_02 收到kafka消息:");
            TestHelper.println("topic", topic);
            TestHelper.println("message", msg);
            TestHelper.println("consumer groupId", "group-a");

            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, containerFactory = "simpleFactory")
    public void no_id_03(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("no_id_03 收到kafka消息:");
            TestHelper.println("topic", topic);
            TestHelper.println("message", msg);
            TestHelper.println("consumer groupId", "group-a");

            ack.acknowledge();
        }
    }
}
