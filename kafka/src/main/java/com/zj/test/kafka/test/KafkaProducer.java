package com.zj.test.kafka.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/***/
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /** 自定义topic */
    public static final String TOPIC_TEST = "topic.test";

    public static final String TOPIC_GROUP1 = "topic.group1";

    public static final String TOPIC_GROUP2 = "topic.group2";

    public void send(Object obj) {
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, obj);
        // 消息发送回调, 可以在send(..)发送后设置
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }

    /**
     * 向test-topic-2发送消息
     */
    public void send2(Object obj) {
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-topic-2", obj);
        // 消息发送回调, 可以在send(..)发送后设置
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info("test-topic-2" + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info("test-topic-2" + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }

    /**
     * 用来配合测试ConsumerRecord api方法
     */
    public void send3(Object obj) {
        String obj2String = JSONObject.toJSONString(obj);
        log.info("准备发送消息为：{}", obj2String);
        //发送消息
        //ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-topic-2", obj);

        // 可以指定分区partition
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-topic-2",2,System.currentTimeMillis(),"message-key",obj);


        // 消息发送回调, 可以在send(..)发送后设置
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info("test-topic-2" + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info("test-topic-2" + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
    }
}