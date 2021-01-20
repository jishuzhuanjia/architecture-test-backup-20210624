package com.zj.test.datasource;

import com.zj.test.datasource.mapper.TestMapper;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/20 23:50
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = com.zj.test.datasource.UtilboxDataSourceApplication.class)
@RunWith(SpringRunner.class)
public class UnitTest {

    @Autowired
    TestMapper testMapper;

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
        TestHelper.println(testMapper.selectTest());
    }
}
