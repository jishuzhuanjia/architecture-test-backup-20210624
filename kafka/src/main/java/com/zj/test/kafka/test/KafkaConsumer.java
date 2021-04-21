package com.zj.test.kafka.test;

import com.zj.test.util.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.Optional;

/**
 * 【kafka区别】
 * 一、topic下引入partition的作用：
 * topic是逻辑的概念，partition是物理的概念。
 * 为了性能考虑，如果topic内的消息只存于一个broker，那这个broker会成为瓶颈，无法做到水平扩展。
 * kafka通过算法尽可能的把partition分配到集群的不同服务器上。
 * partition也可以理解为segment的封装。一个partition对应多个segment。一个segment包含一个数据文件和一个索引文件
 *
 *
 * 二、kafka分区分配策略
 * partition.assignment.strategy= range（默认值） 或 roundrobin
 *
 * 1.range策略：按照范围分配分区，分配给消费者线程的分区号是连续的
 *
 * partition的个数除于消费者线程的总数来决定每个消费者线程消费几个分区。
 * 如果除不尽，那么前面几个消费者线程将会多消费一个分区。
 * 假设有3个消费者11个分区
 * C1-0 将消费 0, 1, 2, 3 分区
 * C1-2 将消费 4, 5, 6, 7 分区
 * C1-3 将消费 8, 9, 10 分区
 *
 * 2.roundrobin策略：分区按照hashcode排序，消费者按照字母排序
 * 假设有3个消费者11个分区
 * C1-0 将消费 0, 3, 6, 9 分区
 * C1-2 将消费 1, 4, 7, 10 分区
 * C1-3 将消费 2, 5, 8 分区
 *
 * 注意：
 * 1、一个分区只能被一个消费者线程消费。
 * 2、新的api中预留了自己实现分配策略的可能性class org.apache.kafka.clients.consumer.RangeAssignor
 *
 *
 * 三、分区修改命令
 * ./kafka-topics.sh --alter --topic topic1 --zookeeper zkip:2181/kafka --partitions 6
 *
 *
 * 【java代码中如何合理使用@KakfaListener】
 * 1.每个@KakfaListener执行一个特定的任务，并指定concurrency参数。
 * 2.对多个@KafkaListener添加相同groupId参数后，这几个@KafkaListener将作为一个消费者集群，消息会被某个@KafkaListener消费。
 */
//@Component
@Slf4j
public class KafkaConsumer {

    /**
     * KafkaListener注解属性：
     * topics：监听的topic，可指定多个
     * groupId：消费者集群id
     * containerFactory：配置类中生成的containerFactory
     *
     */
    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = "agroup",containerFactory = "simpleFactory")
    public void topic_test0(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("group agroup: topic_test0 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = "agroup",containerFactory = "simpleFactory")
    public void topic_test(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("group agroup: topic_test 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = "lsp001",containerFactory = "simpleFactory")
    public void topic_test1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("group lsp001: topic_test1 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST, groupId = "lsp001",containerFactory = "simpleFactory")
    public void topic_test2(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("group lsp001: topic_test2 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,containerFactory = "simpleFactory")
    public void topic_test3(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("no group: topic_test3 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,containerFactory = "simpleFactory")
    public void topic_test4(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("no group: topic_test4 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaProducer.TOPIC_TEST,containerFactory = "simpleFactory")
    public void topic_test5(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("no group: topic_test5 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    /***
     * 1.测试: 相同groupId的消费者集群对每个消息只消费一次?
     *
     * 【测试输出】
     * 2021-01-10 14:58:36.313  INFO 73756 --- [nio-8881-exec-9] com.zj.test.kafka.test.KafkaProducer     : 准备发送消息为："this is a kafka message!"
     * 2021-01-10 14:58:36.316  INFO 73756 --- [ad | producer-1] com.zj.test.kafka.test.KafkaProducer     : test-topic-2 - 生产者 发送消息成功：SendResult [producerRecord=ProducerRecord(topic=test-topic-2, partition=null, headers=RecordHeaders(headers = [], isReadOnly = true), key=null, value=this is a kafka message!, timestamp=null), recordMetadata=test-topic-2-1@0]
     * 2021-01-10 14:58:36.318  INFO 73756 --- [ntainer#7-1-C-1] com.zj.test.kafka.test.KafkaConsumer     : group tgd consumer2: 消费了： Topic:test-topic-2,Message:this is a kafka message!
     * 2021-01-10 14:58:39.297  INFO 73756 --- [nio-8881-exec-1] com.zj.test.kafka.test.KafkaProducer     : 准备发送消息为："this is a kafka message!"
     * 2021-01-10 14:58:39.301  INFO 73756 --- [ad | producer-1] com.zj.test.kafka.test.KafkaProducer     : test-topic-2 - 生产者 发送消息成功：SendResult [producerRecord=ProducerRecord(topic=test-topic-2, partition=null, headers=RecordHeaders(headers = [], isReadOnly = true), key=null, value=this is a kafka message!, timestamp=null), recordMetadata=test-topic-2-4@0]
     * 2021-01-10 14:58:39.301  INFO 73756 --- [ntainer#8-1-C-1] com.zj.test.kafka.test.KafkaConsumer     : group tgd consumer1: 消费了： Topic:test-topic-2,Message:this is a kafka message!
     * 2021-01-10 14:58:41.261  INFO 73756 --- [nio-8881-exec-6] com.zj.test.kafka.test.KafkaProducer     : 准备发送消息为："this is a kafka message!"
     * 2021-01-10 14:58:41.264  INFO 73756 --- [ad | producer-1] com.zj.test.kafka.test.KafkaProducer     : test-topic-2 - 生产者 发送消息成功：SendResult [producerRecord=ProducerRecord(topic=test-topic-2, partition=null, headers=RecordHeaders(headers = [], isReadOnly = true), key=null, value=this is a kafka message!, timestamp=null), recordMetadata=test-topic-2-3@0]
     * 2021-01-10 14:58:41.265  INFO 73756 --- [ntainer#8-0-C-1] com.zj.test.kafka.test.KafkaConsumer     : group tgd consumer1: 消费了： Topic:test-topic-2,Message:this is a kafka message!
     * 2021-01-10 14:58:44.785  INFO 73756 --- [nio-8881-exec-5] com.zj.test.kafka.test.KafkaProducer     : 准备发送消息为："this is a kafka message!"
     * 2021-01-10 14:58:44.789  INFO 73756 --- [ad | producer-1] com.zj.test.kafka.test.KafkaProducer     : test-topic-2 - 生产者 发送消息成功：SendResult [producerRecord=ProducerRecord(topic=test-topic-2, partition=null, headers=RecordHeaders(headers = [], isReadOnly = true), key=null, value=this is a kafka message!, timestamp=null), recordMetadata=test-topic-2-5@1]
     * 2021-01-10 14:58:44.789  INFO 73756 --- [ntainer#8-2-C-1] com.zj.test.kafka.test.KafkaConsumer     : group tgd consumer1: 消费了： Topic:test-topic-2,Message:this is a kafka message!
     * 2021-01-10 14:58:47.491  INFO 73756 --- [nio-8881-exec-2] com.zj.test.kafka.test.KafkaProducer     : 准备发送消息为："this is a kafka message!"
     * 2021-01-10 14:58:47.494  INFO 73756 --- [ad | producer-1] com.zj.test.kafka.test.KafkaProducer     : test-topic-2 - 生产者 发送消息成功：SendResult [producerRecord=ProducerRecord(topic=test-topic-2, partition=null, headers=RecordHeaders(headers = [], isReadOnly = true), key=null, value=this is a kafka message!, timestamp=null), recordMetadata=test-topic-2-0@0]
     * 2021-01-10 14:58:47.494  INFO 73756 --- [ntainer#7-0-C-1] com.zj.test.kafka.test.KafkaConsumer     : group tgd consumer2: 消费了： Topic:test-topic-2,Message:this is a kafka message!
     *
     * 可以看到，每次发送消息，相同groupId的消费者集群中只有一位消费者会消费掉消息。
     *
     * 【结论】
     * 1.相同groupId的消费者集群对每个消息只消费一次。
     * 实际上同一项目中相同groupId的消费者只有一个是有用的。
     *
     * 2.消费者集群可以降低消费者宕机几率,提高并发处理消息的能力。
     *
     *
     * 2.concurrency参数
     * 多线程处理消息：
     * 在服务启动的时候，会为消费者concurrency个线程分别分配分区。
     *
     * concurrency个线程负责不同的消息分区，哪个分区增加了信息消息，则对应的线程会去处理，该结论对于有无groupId的消费者都适用。
     *
     */
    @KafkaListener(concurrency = "3",topics = "test-topic-2",containerFactory = "simpleFactory",groupId = "tgd")
    public void topic_test6(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info(Thread.currentThread().getName() +"-group tgd consumer1: 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = "test-topic-2",containerFactory = "simpleFactory",groupId = "tgd")
    public void topic_test7(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("group tgd consumer2: 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }


    /**
     * 3.ConsumerRecord api方法测试
     *
     * 测试过程：
     * 消息生产者发送消息:
     *  ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-topic-2",2,System.currentTimeMillis(),"message-key",obj);
     * 【作用】
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @KafkaListener(topics = "test-topic-2",containerFactory = "simpleFactory")
    public void topic_test8(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC)/*kafka_receivedTopic*/ String topic) {

        TestHelper.startTest("ConsumerRecord api方法测试");
        //topic主题名
        // record.topic(): test-topic-2
        TestHelper.println("record.topic()",record.topic());

        // 消息key
        // record.key(): message-key
        TestHelper.println("record.key()",record.key());

        // 序列化的key的大小，未经过压缩,单位字节byte
        // 如果没有指定key,则返回-1              // true
        TestHelper.println("record.serializedKeySize()",record.serializedKeySize());

        // 消息内容
        // record.value(): this is a kafka message for ConsumerRecord test!
        TestHelper.println("record.value()",record.value());

        // 序列化的value的大小，未经过压缩,单位字节byte
        TestHelper.println("record.serializedValueSize()",record.serializedValueSize());

        // 生产者如果没有指定消息发送到的分区,则这里消息可能来自topic分区之一
        // 是个int类型的数值
        // record.partition(): 2
        TestHelper.println("record.partition()",record.partition());

        // offset: 每个分区parition中的offset都是从0开始,是个long类型的数值
        TestHelper.println("record.offset()",record.offset());

        // 时间戳,消息生产者在发送消息的时候甚至可以伪造时间戳发送消息。
        TestHelper.println("record.timestamp()",record.timestamp());
        TestHelper.finishTest();

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("no group consumer topic_test8: 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = "test-topic-2",containerFactory = "simpleFactory")
    public void topic_test9(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC)/*kafka_receivedTopic*/ String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("no group consumer topic_test9: 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(groupId = "xxxu",topics = "test-topic-2",containerFactory = "simpleFactory")
    public void topic_test10(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC)/*kafka_receivedTopic*/ String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("no group consumer topic_test10: 消费了： Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}