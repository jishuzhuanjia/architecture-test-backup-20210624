package com.zj.test.mq.controller;

import com.zj.test.util.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.jms.Queue;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/26 9:11
 * @description: activemq性能测试: 消息获取速度测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Slf4j
@RequestMapping("test/my/activemq/capacity")
@RestController
public class CapacityTest {
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    int capacity;

    Long startTime;


    long currentTime;

    // 测试queue
    Queue testQueue = new ActiveMQQueue("test-queue");

    /**
     * * @Capacity activemq消息入列性能测试
     * <p>
     * <pre>环境:
     * 系统: win10
     * cpu: i5-8265U, 4核8线程
     * 内存: 16GB</pre>
     *
     * <p><pre>测试结果:
     * 每秒消息获取数: 230
     * 每秒消息获取数: 257
     * 每秒消息获取数: 263
     * 每秒消息获取数: 274
     * 每秒消息获取数: 228
     * 每秒消息获取数: 278
     * </pre>
     * </p>
     * <p>
     * 结论: 200+。
     * 如果使用多线程进行消息入列, 可达到500+
     */
    @JmsListener(destination = "test-queue", containerFactory = "queueListener")
    public void listenQueueMessage(String message) {
        if (startTime == null)
            startTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();
        capacity++;

        if (1000 < (currentTime - startTime) && (currentTime - startTime) <= 1002) {
            TestHelper.println("每秒消息获取数: " + capacity);
            capacity = 0;
            startTime = System.currentTimeMillis();
        }
    }

    public void ETTE() {

    }


    /**
     * activemq消息入列性能测试
     * <p>
     * 注意: 添加到本地activemq，可以几乎忽略网络延迟的影响，因此效率会比实际高。
     *
     * <p><pre>环境:
     * 系统: win10
     * cpu: i5-8265U, 4核8线程
     * 内存: 16GB</pre>
     *
     * <p><pre>测试结果
     * 批量添加2000条消息成功, 用时: 7699ms, 平均速度: 259.77399662293806条/s
     * 批量添加4000条消息成功, 用时: 16997ms, 平均速度: 235.3356474672001条/s
     * 批量添加10000条消息成功, 用时: 39194ms, 平均速度: 255.1410930244425条/s
     * 批量添加12000条消息成功, 用时: 46817ms, 平均速度: 256.31714975329476条/s
     * </pre>
     *
     * <p>
     * 综上，消息入列大约在200+/s
     */
    @RequestMapping("addMessage")
    public String batchSendMessageToLocalMq(Integer count) {
        if (null == count)
            return "添加消息失败: 请指定插入消息条数";
        String message;
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= count; i++) {
            message = "Queue Message-" + (i);
            try {
                jmsMessagingTemplate.convertAndSend(testQueue, message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();

        finishedThreadCount++;
        if (finishedThreadCount == threadCount) {
            TestHelper.println("多线程添加用时: " + ((System.currentTimeMillis() - threadStartTime) / 1000d) + "S");
        }

        long usedTime = endTime - startTime;
        double usedSecond = usedTime / 1000d;
        return "批量添加" + count + "条消息成功, 用时: " + usedTime + "ms, 平均速度: " + (count / usedSecond) + "条/s";
    }

    long threadStartTime;
    int finishedThreadCount = 0;
    int threadCount = 10;

    /**
     * 性能测试: 多线程添加队列消息性能
     *
     * <p><pre>测试结果:
     * 524.6/s      10线程4000条
     * 539.0        10线程10000条
     * </pre>
     *
     * <p><pre>结论:
     * </pre>
     */
    @PostConstruct
    public void asynAddMessage() {
        log.info("synChronizeAddMessage ,every thread 10000");
        threadStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    batchSendMessageToLocalMq(1000);
                }
            }).start();
        }
    }

}