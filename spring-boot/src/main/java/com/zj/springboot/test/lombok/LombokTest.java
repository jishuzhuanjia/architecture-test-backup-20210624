package com.zj.springboot.test.lombok;

import com.zj.springboot.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 *
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/9 11:25
 * @is-finished: false
 *
 */
@Slf4j
@SpringBootTest(classes = SpringBootApplication.class)
@RunWith(SpringRunner.class)
public class LombokTest {

    /**
     * 1.trace级别日志测试
     *
     * 【作用/描述】
     *
     * 【出/入参记录】
     *
     * 【使用注意点】
     *
     */
    @Test
    public void trace() {
        log.trace("this is message of level trace.");

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *     2.测试: log4j spring boot默认日志级别测试
     * </p>
     *
     * 【出入参记录】
     * 2021-06-09 11:37:51.279  INFO 18164 --- [           main] c.zj.springboot.test.lombok.LombokTest   : info message
     * 2021-06-09 11:37:51.279  WARN 18164 --- [           main] c.zj.springboot.test.lombok.LombokTest   : warn message
     * 2021-06-09 11:37:51.279 ERROR 18164 --- [           main] c.zj.springboot.test.lombok.LombokTest   : err message
     *
     * 【结论】
     * 默认情况下，日志级别>=info，才会打印。
     *
     * 【注意点】
     *
     */
    @Test
    public void defaultLoggingLevelTest() {
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("err message");
    }
}
