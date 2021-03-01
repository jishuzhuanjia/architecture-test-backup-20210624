package com.zj.test.spring.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/* @author: zhoujian
 * @create-time: 2020/9/24 11:30
 * @description:
 * @version: 1.0
 */
@Service
@Slf4j
public class AsyncThreadPoolServiceImpl {

    /*001.如果指定bean name，必须存在，否则：
    org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxxx' available: No matching Executor bean found for qualifier 'xxxx' - neither qualifier match nor bean name match!*/
    /*002.如果不指定，则会自动去查找存在的TaskExecutor*/
    @Async("taskExecutor")
    public void sendMessage1(){
        log.info("Message1");
    }

    // 声明一个异步任务并执行TaskExecutor
    @Async("taskExecutor")
    public void sendMessage2(){
        log.info("Message2");
        // spring异步任务允许睡眠
       try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("code after try catch!");
    }
}
