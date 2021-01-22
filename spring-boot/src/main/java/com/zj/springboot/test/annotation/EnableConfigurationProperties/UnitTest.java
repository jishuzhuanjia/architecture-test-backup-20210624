package com.zj.springboot.test.annotation.EnableConfigurationProperties;

import com.zj.springboot.SpringBootApplication;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 14:27
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = SpringBootApplication.class)
@RunWith(SpringRunner.class)
public class UnitTest {
    @Autowired EnableConfigurationPropertiesTest enableConfigurationPropertiesTest;

    @Test
    public void test(){
        TestHelper.println(enableConfigurationPropertiesTest.user);
    }
}
