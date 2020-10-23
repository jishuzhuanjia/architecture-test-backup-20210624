package com.zj.test.spring.annotation;

import com.zj.test.spring.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 15:18
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class NotNullTest {
    @Autowired
    NotNullTestServiceImpl notNullTestService;

    @Test
    public void test1(){
        notNullTestService.println(null,100);
    }
}
