package com.zj.test.dobboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zj.test.dobboprovider.service.DubboProviderTestService;
import com.zj.test.util.TestHelper;

import java.util.Date;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 10:45
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service(timeout = 2000,retries = 2,group = "gp1")
public class DubboProviderTestServiceImpl implements DubboProviderTestService {

    /**
     * 1.测试: provider服务是否有效检测方法实现
     *
     * 【测试工具】
     * jemter
     *
     * 【测试结果】
     * 调用成功
     */
    @Override
    public String helloDubbo() {
        TestHelper.println("DubboProviderTestServiceImpl: Hello Dubbo!");
        return "Hello Dubbo!";
    }

    /***
     * 2.测试: timeout参数测试
     *
     * 【测试过程】
     * 设置service timeout为2000ms，而方法调用耗时3000+ms
     * jmeter timeout设置5000，测试timeout参数是否有效
     *
     * 【结论】
     * 使用jmeter测试时，@Service timeout没有起作用。
     */
    @Override
    public String timeoutTest() {
        TestHelper.startTest("timeoutTest");
        Date date = new Date();
        TestHelper.println("time: " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
        TestHelper.println("timeoutTest() is invoking...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestHelper.println("timeoutTest() invoked success");
        return "timeoutTest() invoked success";
    }
}
