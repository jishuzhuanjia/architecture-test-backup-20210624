package com.zj.test.spring.task;

import com.zj.test.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *      spring task 测试
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/7 13:32
 * @is-finished: false
 *
 */

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TaskUnitTest {

    /**
     */
    @Test
    public void test(){
        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
