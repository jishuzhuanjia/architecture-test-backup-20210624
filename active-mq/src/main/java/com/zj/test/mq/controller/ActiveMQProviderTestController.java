package com.zj.test.mq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/23 14:30
 * @description: 消息生产者测试Controller
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年10月23日 16:32:14
 */
@RestController
@Slf4j
@RequestMapping("test/mq/activemq")
public class ActiveMQProviderTestController {

    @Autowired
    Queue queue;

    @Autowired
    Topic topic;

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 发送消息到activemq queue队列
     */
    @RequestMapping("sendQueue")
    public String sendQueue() {
        jmsMessagingTemplate.convertAndSend(queue, "hello queue");

        // defaultDestinationName默认为null
        // null
        log.info("jmsMessagingTemplate.getDefaultDestinationName(): {}",jmsMessagingTemplate.getDefaultDestinationName());
        return "send activemq message to queue";
    }

    /**
     * 测试: 发送PO对象
     */
    @RequestMapping("sendQueueObject")
    public String sendQueueObject() {
        UserPO userPO = new UserPO();
        userPO.username="ZhouJian";
        userPO.age= 25;

        // org.springframework.jms.support.converter.MessageConversionException: Cannot convert object of type [com.zj.test.mq.controller.UserPO] to JMS message. Supported message payloads are: String, byte array, Map<String,?>, Serializable object.
        jmsMessagingTemplate.convertAndSend(queue,userPO);

        return "send activemq message Object to queue";
    }

    /**
     * 发送消息到active topic队列
     */
    @RequestMapping("sendTopic")
    public String sendTopic() {
        // 转换topic/queue消息模板并发送消息
        jmsMessagingTemplate.convertAndSend(topic, "hello topic");
        return "send activemq message to topic";
    }
}