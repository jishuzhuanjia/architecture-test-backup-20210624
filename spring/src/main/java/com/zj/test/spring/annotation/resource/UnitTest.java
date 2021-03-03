package com.zj.test.spring.annotation.resource;

import com.zj.test.spring.Application;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/1 16:50
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitTest {

    /*
        如果不指定name属性，作用相当于@Autowired,如果没有指定类型的bean:
        org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'com.zj.test.spring.annotation.resource.UnitTest': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.zj.test.spring.annotation.autowired.TestService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@javax.annotation.Resource(shareable=true, lookup=, name=, description=, authenticationType=CONTAINER, type=class java.lang.Object, mappedName=)}
         */
    //@Resource(/*name = "testServiceImpl1"*/)


    /*org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'com.zj.test.spring.annotation.resource.UnitTest': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'resource-testServiceImpl2' is expected to be of type 'com.zj.test.spring.annotation.resource.TestService' but was actually of type 'com.zj.test.spring.annotation.resource.TestServiceImpl2'
    @Resource(name = "resource-testServiceImpl2")*/

    @Resource(name = "resource-testServiceImpl1")
    TestServiceImpl1 testService;

    /**
     * 1.测试: @Resource测试
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void resourceTest() {
        testService.test();
        TestHelper.println(testService.testServiceImpl2);
    }
}
