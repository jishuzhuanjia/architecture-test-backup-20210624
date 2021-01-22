package com.zj.springboot.test.annotation.propertysource;

import com.zj.springboot.SpringBootApplication;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 10:55
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = SpringBootApplication.class)
@RunWith(SpringRunner.class)
public class PropertySourceUnitTest {

   @Autowired
    PropertySourceTest propertySourceTest;

   // 共用其他@PropertySource加载的配置,可以直接注入
    @Value("${db.password}")
    String password;

    @Test
    public void test() {
        TestHelper.println(propertySourceTest.username);
        TestHelper.println(propertySourceTest.password);

        TestHelper.println(password);
    }
}
