package com.zj.test.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring Boot ActiveMQ配置类
 */
@Configuration
@Slf4j
public class ActiveConfig {

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.queue-name}")
    private String queueName;

    @Value("${spring.activemq.topic-name}")
    private String topicName;

    @PostConstruct
    public void Before() {
        log.info("初始化ActiveMQ配置...");
    }

    /**
     * 用来发送消息到queue队列的Destination对象
     */
    @Bean(name = "queue")
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

    /**
     * 用来发送消息到topic队列的Destination对象
     */
    @Bean(name = "topic")
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }

    /***
     * ActiveMQ连接工厂
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
        // 立即创建连接池
        /*try {
            activeMQConnectionFactory.createConnection().start();
        } catch (JMSException e) {
            log.error("ActiveMQ ConnectionFactory创建失败:",e);
        }*/

        // 从activemq中获取序列化的对象，转化时会出现异常
        // Caused by: java.lang.ClassNotFoundException: Forbidden class com.zj.test.mq.controller.UserPO! This class is not trusted to be serialized as ObjectMessage payload.
        // 因为activemq对类型转换进行了限制
        // 可以如下解决方案
        // 1.信任所有包: 开启后会尝试将消息转换为指定的类型
        //activeMQConnectionFactory.setTrustAllPackages(true);

        List<String> trustPackageList = new ArrayList<String>();
        // 信任com.zj.test.mq.controller包下所有类,如果可能，会尝试转换，否则报转换异常。
        trustPackageList.add("com.zj.test.mq.controller");
        activeMQConnectionFactory.setTrustedPackages(trustPackageList);
        return activeMQConnectionFactory;
    }

    /**
     * 消息模版: 用来发送消息
     */
    @Bean
    public JmsMessagingTemplate jmsMessageTemplate() {
        return new JmsMessagingTemplate(connectionFactory());
    }

    // 在Queue模式中，对消息的监听需要对containerFactory进行配置
    /***
     * 后续用来配置监听queue队列消息
     */
    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        // 是否是发布/订阅模式
        simpleJmsListenerContainerFactory.setPubSubDomain(false);
        return simpleJmsListenerContainerFactory;
    }

    //在Topic模式中，对消息的监听需要对containerFactory进行配置

    /***
     * 后续用来配置监听topic队列消息
     *
     * @param connectionFactory
     * @return
     */
    @Bean("topicListener")
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
        simpleJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        // 是否是发布/订阅模式
        // topic队列应设置为发布/订阅模式
        simpleJmsListenerContainerFactory.setPubSubDomain(true);
        return simpleJmsListenerContainerFactory;
    }
}