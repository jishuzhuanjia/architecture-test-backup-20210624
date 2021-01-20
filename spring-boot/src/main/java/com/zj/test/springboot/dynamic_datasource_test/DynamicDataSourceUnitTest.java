package com.zj.test.springboot.dynamic_datasource_test;

import com.zj.test.util.TestHelper;
import com.zj.util.datasource.TestServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/18 17:44
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes= Springboo.class )
@RunWith(SpringRunner.class)
public class DynamicDataSourceUnitTest {

    @Autowired
    TestServiceImpl testService;

    /**
     * ${请输入序号}.测试: ${请输入测试标题}
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void test(){
        TestHelper.println("多数据源查询结果",testService.selectTest());
    }


}
