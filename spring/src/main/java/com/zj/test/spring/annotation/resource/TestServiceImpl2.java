package com.zj.test.spring.annotation.resource;

import com.zj.test.util.TestHelper;
import org.springframework.stereotype.Component;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/1 16:48
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Component("resource-testServiceImpl2")
public class TestServiceImpl2 implements TestService {
    @Override
    public void test() {
        TestHelper.println("TestServiceImpl2: Hello");
    }

    @Override
    public String toString() {
        return "TestServiceImpl2";
    }
}
