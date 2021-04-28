package com.zj.test.spring.async;

import com.zj.test.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/* @author: zhoujian
 * @create-time: 2020/9/24 11:29
 * @description: Spring Boot单元测试 + spring异步线程池测试
 * @version: 1.0
 */

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:resource/applicationContext.xml")*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UnitTest {
    @Autowired
    AsyncThreadPoolServiceImpl asyncThreadPoolService;

    /*运行结果：spring线程池异步任务执行成功
    2020-09-24 12:33:42.402  INFO 14360 --- [-Async-Thread-1] t.s.async.AsyncThreadPoolServiceImpl     : sendMessage1
    2020-09-24 12:33:42.402  INFO 14360 --- [-Async-Thread-2] t.s.async.AsyncThreadPoolServiceImpl     : sendMessage2*/
    @Test
    public void test1(){
        // 1
        for(int i=0;i<209;i++){
            asyncThreadPoolService.sendMessage2();
        }

        asyncThreadPoolService.sendMessage1();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
