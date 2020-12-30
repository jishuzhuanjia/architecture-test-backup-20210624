package com.zj.test.kafka;

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

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/12/29 17:31
 * @description: kafka Consumer配置
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {
    public KafkaConsumerConfig() {
        log.info("Initializing Kafka");
    }

    /**
     * 【kafka区别】
     * 一、topic下引入partition的作用：
     * topic是逻辑的概念，partition是物理的概念。
     * 为了性能考虑，如果topic内的消息只存于一个broker，那这个broker会成为瓶颈，无法做到水平扩展。kafka通过算法尽可能的把partition分配到集群的不同服务器上。
     * partition也可以理解为segment的封装。一个partition对应多个segment。一个segment包含一个数据文件和一个索引文件
     *
     * 二、kafka分区分配策略：
     *
     * partition.assignment.strategy= range（默认值） 或 roundrobin
     *
     * range策略:分区顺序排序，消费者按照字母排序。
     * partitions的个数除于消费者线程的总数来决定每个消费者线程消费几个分区。如果除不尽，那么前面几个消费者线程将会多消费一个分区。
     * 假设有3个消费者11个分区
     * C1-0 将消费 0, 1, 2, 3 分区
     * C1-2 将消费 4, 5, 6, 7 分区
     * C1-3 将消费 8, 9, 10 分区
     * roundrobin策略：分区按照hashcode排序，消费者按照字母排序
     * 假设有3个消费者11个分区
     * C1-0 将消费 0, 3, 6, 9 分区
     * C1-2 将消费 1, 4, 7, 10 分区
     * C1-3 将消费 2, 5, 8 分区
     *
     * 注意：
     * 1、一个分区只能被一个消费者消费，但一个消费者可以消费多个分区的数据
     * 2、新的api中预留了自己实现分配策略的可能性class org.apache.kafka.clients.consumer.RangeAssignor
     *
     * 三、分区修改./kafka-topics.sh --alter --topic topic1 --zookeeper zkip:2181/kafka --partitions 6
     *
     *
     *
     * 【java代码中的写法】
     * 1.每个@KakfaListener执行一个特定的任务，并指定concurrency参数。
     * 2.@KafkaListener添加groupId参数后，似乎进行了分区一般，会出现消息只能被相同groupId中的某一个@KafkaListener消费。
     */
    @Bean(name = "simpleFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
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
        1.想要AUTO_OFFSET_RESET_CONFIG生效，@KafkaListener中groupId需要和group.id相同。
        2.相同groupId对每个分区只能消费一次,因此再次消费时earliest不能从头消费，但是将groupId修改为一个未使用过的新值可以从头消费。

        当auto.offset.reset=latest
        1.就算修改为新的groupId也不会从头消费,只会消费新消息。
        */
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        // 设置group id
        // 只要不更改group.id，每次重新消费kafka，都是从上次消费结束的地方继续开始，不论"auto.offset.reset”属性设置的是什么 - 待验证
        props.put("group.id", "lsp001");
        return props;
    }
}