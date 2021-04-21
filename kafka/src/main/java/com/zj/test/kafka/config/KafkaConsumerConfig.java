package com.zj.test.kafka.config;

import com.alibaba.fastjson.serializer.StringSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * author: zhoujian
 * qq: 2025513
 * create-time: 2020/12/29 17:31
 * description: kafka Consumer配置类
 * version: 1.0
 * finished: false
 * finished-time:
 */
@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {
    public KafkaConsumerConfig() {
        log.info("Initializing kafka Consumer config");
    }

    @Bean(name = "simpleFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        /** 经测试, 这里的groupId不会影响@KafkaListener的 groupId,不知道此处设置factory的groupId有什么用。 */
        factory.getContainerProperties().setGroupId("lsp001");
        //当使用手动提交时必须设置ackMode为MANUAL,否则会报错No Acknowledgment available as an argument, the listener container must have a MANUAL AckMode to populate the Acknowledgment.
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.getContainerProperties().setAckCount(10);
        factory.getContainerProperties().setAckTime(10000);
        return factory;
    }

    //@Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    //@Bean

    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        /*
        消费者使用的kafka服务的地址,如果不正确配置:
        2020-12-30 09:26:27.496  WARN 14844 --- [ntainer#0-1-C-1] org.apache.kafka.clients.NetworkClient   : [Consumer clientId=consumer-2, groupId=topic.group2] Connection to node -1 (/170.23.1.2:9092) could not be established. Broker may not be available.
        */
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 键的序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 值的序列化方式
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // earliest
        /*
        只要不更改group.id，每次重新消费kafka，都是从上次消费结束的地方继续开始，不论"auto.offset.reset”属性设置的是什么
        // true

        可选值
        earliest
        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
        latest
        当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
        none
        topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常

        也就是说无论哪种设置，只要kafka中相同group、partition中已经有提交的offset，则都无法从开始消费。  // true

        经检测(zj)：
        当auto.offset.reset=earliest时。
        1.@KafkaListener必须指定groupId才有效。
        2.已经消费过的groupId在重启消费者后不会从头开始消费。
        3.新增的groupId会从头开始消费。

        当auto.offset.reset=latest
        1.就算修改为新的groupId也不会从头消费,只会消费新消息。
        */
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 经测试，基本上这个设置没什么卵用
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "default-groupId");
        // 设置group id
        // 只要不更改group.id，每次重新消费kafka，都是从上次消费结束的地方继续开始，不论"auto.offset.reset”属性设置的是什么 - 待验证
        props.put("group.id", "lsp001");
        return props;
    }
}