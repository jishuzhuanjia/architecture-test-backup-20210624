package com.zj.test.dubboconsumer.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zj.test.dubboconsumer.service.DubboConsumerProviderTestService;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/22 21:49
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service
public class DubboConsumerProviderTestServiceImpl implements DubboConsumerProviderTestService {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am a Customer,but i also a provider");
    }
}
