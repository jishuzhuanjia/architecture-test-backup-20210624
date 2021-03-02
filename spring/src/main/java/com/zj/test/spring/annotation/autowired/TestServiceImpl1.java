package com.zj.test.spring.annotation.autowired;

import com.zj.test.spring.annotation.TestService;
import com.zj.test.util.TestHelper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/1 16:48
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Component("testServiceImpl1")
public class TestServiceImpl1 implements TestService {

    String requireTestStr;

    public String getRequireTestStr() {
        return requireTestStr;
    }

    @Required
    public void setRequireTestStr(String requireTestStr) {
        this.requireTestStr = requireTestStr;
    }

    @Override
    public void test() {
        TestHelper.println("TestServiceImpl1: Hello");

    }
}
