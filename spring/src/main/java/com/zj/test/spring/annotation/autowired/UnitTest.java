package com.zj.test.spring.annotation.autowired;

import com.zj.test.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/1 16:50
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitTest {

    @Autowired()
    @Qualifier(value = "testServiceImpl2")
    TestService testService;

    /**
     * 1.测试: @Autowired测试
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void autowiredTest() {
        testService.test();

    }
}
