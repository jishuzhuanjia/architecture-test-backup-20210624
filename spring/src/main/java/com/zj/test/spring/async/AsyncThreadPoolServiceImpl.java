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
        log.info("sendMessage1");
    }

    // 声明一个异步任务并执行TaskExecutor
    @Async("taskExecutor")
    public void sendMessage2(){
        log.info("sendMessage2");
        /*001.使用TaskExcutor需要注意的问题: 任务中不能进行睡眠操作(Thread.sleep()):
        因为如果线程进入睡眠操作，当corePoolSize的线程都被分配了，继续添加任务到任务队列时，spring发现工作中的线程居然在睡觉,
        spring会打断正在睡眠的线程，正在睡觉的线程就会抛出InterruptedException，
        只有第一个被打断的线程sleep后的语句被执行，其他的都不会执行。因此就导致了任务进度的不可控。

        【建议】
        实际开发中，若执行的任务数大于corePoolSize，就应该禁止使用Thread.sleep()操作。
        */

       /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 只有第一个被打断的线程才会执行此语句。
        log.info("code after try catch!");*/
    }
}
