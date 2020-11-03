package com.zj.test.spring.proxy.cglib;


import com.zj.test.spring.proxy.service.ProxyTestService;
import com.zj.test.spring.proxy.service.impl.ProxyTestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/3 23:36
 * @description: cglig代理实现
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Slf4j
public class ProxyTestServiceCglibProxyFactory implements MethodInterceptor {

    ProxyTestService getProxyTestService(){

        Enhancer enhancer = new Enhancer();
        /**
         * 1.cglib代理的原理是继承extends
         */
        enhancer.setSuperclass(ProxyTestServiceImpl.class);

        enhancer.setCallback(this);

        return (ProxyTestService) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        log.info("打开事务");

        Object returnVal = methodProxy.invokeSuper(o,objects);

        log.info("关闭事务");

        return returnVal;
    }
}
