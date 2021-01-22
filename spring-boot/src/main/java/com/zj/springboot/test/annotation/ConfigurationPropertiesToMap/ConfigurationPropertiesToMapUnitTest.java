package com.zj.springboot.test.annotation.ConfigurationPropertiesToMap;

import com.zj.springboot.SpringBootApplication;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 13:11
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = SpringBootApplication.class)
@RunWith(SpringRunner.class)
public class ConfigurationPropertiesToMapUnitTest {
    @Autowired
    ConfigurationPropertiesToMapTest configurationPropertiesTest;

    /**
     * ${请输入序号}.测试: ${请输入测试标题}
     *
     * 【测试输出】
     * {username={first=zhou, last=jian},
     * password=password,
     * dynamic={datasource1={url=url-datasource1, username=username-datasource1},
     *          datasource2={url=url-datasource2, driver=driver-datasource2}}}
     *
     * 【结论】
     *
     */
    @Test
    public void test(){
        TestHelper.println("映射到Map的属性个数",configurationPropertiesTest.getMap().size());
        TestHelper.println(configurationPropertiesTest.getMap());
        // class java.util.LinkedHashMap
        TestHelper.println(configurationPropertiesTest.getMap().get("dynamic").getClass());
    }
}
