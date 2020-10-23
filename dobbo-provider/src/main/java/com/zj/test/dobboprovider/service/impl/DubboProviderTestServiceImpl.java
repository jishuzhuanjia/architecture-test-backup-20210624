package com.zj.test.dobboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zj.test.dobboprovider.service.DubboProviderTestService;

import java.util.Date;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 10:45
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service(timeout = 4000,retries = 4)
public class DubboProviderTestServiceImpl implements DubboProviderTestService {
    @Override
    public String repeat(String content, int count) {
        for(int i=0;i<count;i++){
            System.out.println(content);
        }
        return "method repeat invoked success";
    }

    /***
     * 1.timeout参数测试
     *
     * 假设service timeout为2000ms，而方法调用超过2000ms,测试结果
     */
    @Override
    public String timeoutTest() {
        System.out.println("provider method timeoutTest invoking..." + new Date());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method timeoutTest invoked success");

        return "method timeoutTest invoked success";
    }
}
