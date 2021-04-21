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
public class KafkaConsumer3 {

    /* ----------------------------------------- 测试相同groupId的consumer是否会多次消费 ---------------------------------------- */
    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = "group-a",containerFactory = "simpleFactory")
    public void group_a_03(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("group_a_03 收到kafka消息:");
            TestHelper.println("topic" ,topic);
            TestHelper.println("message" ,msg);
            TestHelper.println("consumer groupId" ,"group-a");

            ack.acknowledge();
        }
    }

}
