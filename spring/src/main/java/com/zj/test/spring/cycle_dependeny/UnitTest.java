package com.zj.test.spring.cycle_dependeny;

import com.zj.test.spring.Application;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/16 13:41
 * @description: 循环依赖测试controller
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class UnitTest {
    @Autowired A a;

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
        TestHelper.println(a.getB());

    }
}
