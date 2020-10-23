package com.zj.test.dubbocustomer.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zj.test.dubbocustomer.service.DubboCustomerProviderTestService;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/22 21:49
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service
public class DubboCustomerProviderTestServiceImpl implements DubboCustomerProviderTestService {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am a Customer,but i also a provider");
    }
}
