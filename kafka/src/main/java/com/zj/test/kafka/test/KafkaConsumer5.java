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

@Component
@Slf4j
public class KafkaConsumer5 {

    /* ----------------------------------------- 测试：props.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId-by-config"); ---------------------------------------- */
    /*
    测试结果：
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId-by-config")设置的默认groupId
    不会被用到没有指定groupId的 @KafkaListener

    这就导致了没有指定groupId的多个@KafkaListener中会有一个会消费消息
     */

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,containerFactory = "simpleFactory")
    public void default_groupId_01(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("default_groupId_01 收到kafka消息:");
            TestHelper.println("topic" ,topic);
            TestHelper.println("message" ,msg);
            TestHelper.println("consumer groupId" ,"group-a");

            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,containerFactory = "simpleFactory")
    public void default_groupId_03(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("default_groupId_03 收到kafka消息:");
            TestHelper.println("topic" ,topic);
            TestHelper.println("message" ,msg);
            TestHelper.println("consumer groupId" ,"group-a");

            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,groupId = "default-groupId",containerFactory = "simpleFactory")
    public void default_groupId_02(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("default_groupId_02 收到kafka消息:");
            TestHelper.println("topic" ,topic);
            TestHelper.println("message" ,msg);
            TestHelper.println("consumer groupId" ,"group-a");

            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,groupId = "dqdqwdqwdq1121",containerFactory = "simpleFactory")
    public void groupId_04(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            TestHelper.println("groupId_04 收到kafka消息:");
            TestHelper.println("topic" ,topic);
            TestHelper.println("message" ,msg);
            TestHelper.println("consumer groupId" ,"group-a");

            ack.acknowledge();
        }
    }
}
