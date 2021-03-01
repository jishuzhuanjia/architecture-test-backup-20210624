package com.zj.test.spring.proxy.cglib;

import com.zj.test.spring.proxy.service.ProxyTestService;
import org.junit.Test;

public class UnitTest {

    @Test
    public void getProxyTestService(){

        ProxyTestServiceCglibProxyFactory proxyTestServiceCglibProxyFactory = new ProxyTestServiceCglibProxyFactory();

        ProxyTestService proxyTestService = proxyTestServiceCglibProxyFactory.getProxyTestServiceProxy();

        proxyTestService.insert();
        proxyTestService.delete();
        proxyTestService.update();
        proxyTestService.select();
    }

}