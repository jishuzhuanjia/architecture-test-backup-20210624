package com.zj.test.spring.async;

/* @author: zhoujian
 * @create-time: 2020/9/24 11:04
 * @description: spring异步线程池配置类
 * @version: 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
/*
@EnableAsync:

【不添加此注解,ThreadPoolTaskExecutor虽然被实例化，但是spring不会使用它去执行异步任务】
必须有此注解，ThreadPoolTaskExecutor才被应用
*/
@EnableAsync
public class AsyncThreadPoolConfig {
    private static final int corePoolSize = 10;     // 核心线程数（默认线程数）
    private static final int maxPoolSize = 100;     // 最大线程数
    private static final int keepAliveTime = 10;    // 允许线程空闲时间（单位：默认为秒）
    private static final int queueCapacity = 200;   // 缓冲队列数
    private static final String threadNamePrefix = "Spring-Test-Async-Thread-"; // 线程池名前缀

    /*
     * 【如何使用该ThreadPoolTaskExecutor】
     * 使用@Async时，指定ThreadPoolTaskExecutor name即可。
     *
     * 如果不指定名字,spring会查找存在的ThreadPoolTaskExecutor执行任务，
     * */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor createThreadPoolTaskExecutor() {
        //001.ThreadPoolTaskExecutor - spring提供的
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 默认线程数，如果不设置默认为1
        // 初始化时就会创建corePoolSize个线程
        executor.setCorePoolSize(corePoolSize);
        // 最大线程池线程数
        executor.setMaxPoolSize(maxPoolSize);
        /*
        002.阻塞队列(BlockingQueue)中存放待执行的任务，当达到线程最大值且队列满时，
        新加入的任务将会被拒绝。
        */
        executor.setQueueCapacity(queueCapacity);
        /*003.corePoolSize数量之外的线程如果空闲时间达到keepAliveTime，会被销毁*/
        executor.setKeepAliveSeconds(keepAliveTime);
        // 设置线程名的前缀，不要过长，因为threadNamePrefix和线程编号拼接后超过15个字符的部分，会从字符串头部截取：如Spring-Test-Async-Thread-1 -> -Async-Thread-1
        // 线程编号: 从1开始
        executor.setThreadNamePrefix(threadNamePrefix);
        /*
        allowCoreThreadTimeOut: 设置核心线程空闲超时时是否销毁

        true: 会销毁核心线程
        false: 不会销毁核心线程，会销毁额外创建的线程。
        */
        executor.setAllowCoreThreadTimeOut(false);
        executor.setWaitForTasksToCompleteOnShutdown(false);
        /*
         * 004.线程池拒绝策略
         * CallerRunsPolicy: 使用此策略，如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行。就像是个急脾气的人，我等不到别人来做这件事就干脆自己干。
         *
         * AbortPolicy: 该策略是线程池的默认策略。使用该策略时，如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常。
         *
         * DiscardPolicy:这个策略和AbortPolicy的slient版本，如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常。
         *
         * DiscardOldestPolicy: 这个策略从字面上也很好理解，丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。
         * 队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队。
         * */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}
