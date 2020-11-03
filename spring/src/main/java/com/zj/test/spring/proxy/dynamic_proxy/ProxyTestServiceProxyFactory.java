package com.zj.test.spring.proxy.dynamic_proxy;

import com.zj.test.spring.proxy.service.ProxyTestService;
import com.zj.test.spring.proxy.service.impl.ProxyTestServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/3 23:25
 * @description: 动态代理技术demo: 模拟开启事务
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Slf4j
public class ProxyTestServiceProxyFactory implements InvocationHandler {
    ProxyTestService proxyTestService;

    public ProxyTestServiceProxyFactory(ProxyTestService proxyTestService) {
        this.proxyTestService = proxyTestService;
    }

    /**
     * 获取代理对象
     */
    public ProxyTestService getProxyTestServiceProxy() {

        // java.lang.IllegalArgumentException: interface com.zj.test.spring.proxy.service.ProxyTestService is not visible from class loader

        // 应该是ProxyTestServiceImpl.class.getInterfaces()而不是ProxyTestService.class.getInterfaces()，否则:
        // java.lang.ClassCastException: com.sun.proxy.$Proxy4 cannot be cast to com.zj.test.spring.proxy.service.ProxyTestService
        /*ProxyTestService o = (ProxyTestService) Proxy.newProxyInstance(ProxyTestService.class.getClassLoader(), ProxyTestService.class.getInterfaces(),
                this);*/

        ProxyTestService o = (ProxyTestService) Proxy.newProxyInstance(ProxyTestService.class.getClassLoader(), ProxyTestServiceImpl.class.getInterfaces(),
                this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 模拟打开事务
        log.info("打开事务...");

        // 业务代码
        Object returnVal = method.invoke(proxyTestService, args);

        // 模拟关闭事务
        log.info("关闭事务...");

        return returnVal;
    }
}
