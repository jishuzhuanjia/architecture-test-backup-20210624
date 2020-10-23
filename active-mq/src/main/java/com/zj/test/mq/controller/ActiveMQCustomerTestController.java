package com.zj.test.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/23 14:46
 * @description: activemq 消费者测试Controller
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月23日 16:29:15
 */
@RestController
@RequestMapping("test/mq/activemq")
@Slf4j
public class ActiveMQCustomerTestController {

    /***
     * 配置监听queue队列消息
     * 对于Queue队列: 一旦有新消息就会尝试获取
     *
     * active消息监听器参数类型转换规律:
     * 1.参数为Object,不转换类型,如:
     * String消息 -> ActiveMQTextMessage。
     * Object消息 -> ActiveMQObjectMessage
     *
     * 2.参数PO类型: activeMQ尝试转换成PO类型，前提是PO类在白名单内。
     */
    @JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
    public void listenQueueMessage(Object message) {
        log.info("从queue消息队列获取新消息:{}", message);
        // String类型 > 消息类型:class org.apache.activemq.command.ActiveMQTextMessage
        // PO类型UserPO > 消息类型:class org.apache.activemq.command.ActiveMQObjectMessage
        log.info("消息类型:{}", message.getClass());
    }

    /***
     * topic消息可以被重复消费
     * 对于topic队列: 一旦有新消息就会尝试获取
     * 配置用来监听topic队列消息
     */
    @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
    public void listenTopicMessage1(String message) {
        log.info("listenTopicMessage1从topic消息队列获取新消息:{}", message);
    }

    /***
     * topic消息可以被重复消费
     * 配置用来监听topic队列消息
     */
    @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
    public void listenTopicMessage2(String message) {
        log.info("listenTopicMessage2从topic消息队列获取新消息:{}", message);
    }

    /**
     * 1.queue消息  vs  topic消息
     * 1.1.消费次数: 每条queue消息最多只能被消费1次,当没有消费者的时候, 不会被消费。
     * 而topic消息可以重复消费,消费次数0~N, N取决于订阅的消费者个数。
     *
     * 1.2.queue消息强制消费,topic消息不强制消费
     * 对于queue: 当有消费者时,会强制要求消费者依次从消费队列中获取消息并消费，直到队列消息为0。
     *
     * 对于topic: 当有消费者时, 不会强制要求消费者消费之前产生的消息。
     * 即topic消息有实时性要求，只有发布消息时，已经订阅的消费者才能消费，后来才订阅的消费者不能消费消息。
     */
}