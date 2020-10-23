package com.zj.test.spring.async;

/* @author: zhoujian
 * @create-time: 2020/9/24 11:04
 * @description: spring ThreadPoolTaskExcutor配置文件
 * @version: 1.0
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/*【1.ThreadPoolExecutor池子的处理流程如下】
1）当池子大小小于corePoolSize就新建线程，并处理请求
2）当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去从workQueue中取任务并处理
3）当workQueue放不下新入的任务时，新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize就用RejectedExecutionHandler来做拒绝处理
4）另外，当池子的线程数大于corePoolSize的时候，多余的线程会等待keepAliveTime长的时间，如果无请求可处理就自行销毁
其会优先创建  CorePoolSiz 线程， 当继续增加线程时，先放入Queue中，当 CorePoolSiz  和 Queue 都满的时候，就增加创建新线程，当线程达到MaxPoolSize的时候，就会抛出错 误 org.springframework.core.task.TaskRejectedException
另外MaxPoolSize的设定如果比系统支持的线程数还要大时，会抛出java.lang.OutOfMemoryError: unable to create new native thread 异常。*/

@Configuration
//【不添加此注解,ThreadPoolTaskExecutor虽然被实例化，但是spring不会去使用它进行异步操作】
//必须有此注解，ThreadPoolTaskExecutor才被应用
@EnableAsync
public class AsyncThreadPoolConfig {
    private static final int corePoolSize = 10;       		// 核心线程数（默认线程数）
    private static final int maxPoolSize = 100;			    // 最大线程数
    private static final int keepAliveTime = 10;			// 允许线程空闲时间（单位：默认为秒）
    private static final int queueCapacity = 200;			// 缓冲队列数
    private static final String threadNamePrefix = "Spring-Test-Async-Thread-"; // 线程池名前缀

    /*
     * 如果不指定名字,spring会查找存在的ThreadPoolTaskExecutor执行任务，
     * 如果指定，我们可以在使用@Async的时候指定ThreadPoolTaskExecutor名来使用指定的ThreadPoolTaskExecutor来执行我们的任务。
     * */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor createThreadPoolTaskExecutor(){
        //001.ThreadPoolTaskExecutor - spring提供的
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 默认线程数，如果不设置默认为1
        // 初始化时就会创建corePoolSize个线程
        executor.setCorePoolSize(corePoolSize);
        // 最大线程池线程数
        executor.setMaxPoolSize(maxPoolSize);
        /*002.阻塞队列中存放待执行的任务，当达到线程最大值且队列满时，
        新加入的任务将会被拒绝。*/
        executor.setQueueCapacity(queueCapacity);
        /*003.corePoolSize数量之外的线程如果空闲时间达到keepAliveTime，会被销毁*/
        executor.setKeepAliveSeconds(keepAliveTime);
        // 设置线程名的前缀，不要过长，因为threadNamePrefix和线程编号拼接后超过15个字符的部分，会从字符串头部截取：如Spring-Test-Async-Thread-1 -> -Async-Thread-1
        executor.setThreadNamePrefix(threadNamePrefix);
        /*设置是否当核心线程空闲一定时间会销毁：
        true: 最后会销毁核心线程
        false: 不会销毁核心线程，会销毁额外创建的线程。*/
        executor.setAllowCoreThreadTimeOut(false);
        executor.setWaitForTasksToCompleteOnShutdown(false);
        /*004.线程池对拒绝任务的处理策略
        * CallerRunsPolicy: 使用此策略，如果添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行。就像是个急脾气的人，我等不到别人来做这件事就干脆自己干。
        *
        * AbortPolicy: 该策略是线程池的默认策略。使用该策略时，如果线程池队列满了丢掉这个任务并且抛出RejectedExecutionException异常。
        *
        * DiscardPolicy:这个策略和AbortPolicy的slient版本，如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常。
        *
        * DiscardOldestPolicy: 这个策略从字面上也很好理解，丢弃最老的。也就是说如果队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列。
        *   为队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队。
        * */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}
