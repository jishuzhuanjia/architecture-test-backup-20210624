package com.zj.test.spring.proxy.dynamic_proxy;

import com.zj.test.spring.proxy.service.ProxyTestService;
import com.zj.test.spring.proxy.service.impl.ProxyTestServiceImpl;
import org.junit.Test;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/3 23:32
 * @description: 动态代理ProxyTestServiceProxyFactory单元测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class UnitTest {

    @Test
    public void getProxyTestServiceProxy(){

        ProxyTestServiceImpl proxyTestServiceImpl = new ProxyTestServiceImpl();

        ProxyTestServiceProxyFactory proxyTestServiceProxyFactory = new ProxyTestServiceProxyFactory(proxyTestServiceImpl);

        ProxyTestService proxyTestServiceProxy = proxyTestServiceProxyFactory.getProxyTestServiceProxy();

        proxyTestServiceProxy.insert();
        proxyTestServiceProxy.delete();
        proxyTestServiceProxy.update();
        proxyTestServiceProxy.select();
    }
}
