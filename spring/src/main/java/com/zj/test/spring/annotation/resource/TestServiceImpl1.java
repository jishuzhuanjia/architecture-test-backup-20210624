package com.zj.test.spring.annotation.resource;

import com.zj.test.util.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.ws.RequestWrapper;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/1 16:48
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

@Service("resource-testServiceImpl1")
public class TestServiceImpl1 implements TestService {

    String requireTestStr;

    TestServiceImpl2 testServiceImpl2;

    // @Required测试
    // 在高版本spring中 @Required注解已废弃无效
    /* public String getRequireTestStr() {
        return requireTestStr;
    }

    @Required
    public void setRequireTestStr(String requireTestStr) {
        this.requireTestStr = requireTestStr;
    }
    */

    // 如果没有默认构造函数,spring实例化注解会调用其他的有参构造函数
    // 默认情况下,这些参数都是要求强制注入的,哪怕是String类型
    // 并且@Autowired(required = false)也不能取消其强制性
    // 因此，如果不要求强制注入，请总是为实体类添加默认参数构造函数。
    //@Autowired(required = true)
    public TestServiceImpl1(TestServiceImpl2 testServiceImpl2, String requireTestStr) {
        this.testServiceImpl2 = testServiceImpl2;
        this.requireTestStr = requireTestStr;
    }

    /*
    请注意:
    1.如果没有默认构造函数，会自动调用其他的构造方法,本类中将会调用:
    public TestServiceImpl1(TestServiceImpl2 testServiceImpl2)
    并且会自动按照类型注入(如果指定类型的Bean存在的话)。
     */
    /*public TestServiceImpl1(){}*/

    @Override
    public void test() {
        TestHelper.println("TestServiceImpl1: Hello");
    }
}
