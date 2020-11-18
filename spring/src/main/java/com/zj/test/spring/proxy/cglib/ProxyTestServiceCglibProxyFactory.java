package com.zj.test.spring.proxy.cglib;


import com.zj.test.spring.proxy.service.ProxyTestService;
import com.zj.test.spring.proxy.service.impl.ProxyTestServiceImpl;
import com.zj.test.util.TestHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/3 23:36
 * @description: cglig代理实现
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

/**
 * cglib代理是spring-core提供的技术
 */
@Slf4j
public class ProxyTestServiceCglibProxyFactory implements MethodInterceptor {

    ProxyTestService getProxyTestServiceProxy() {

        Enhancer enhancer = new Enhancer();
        /**
         * 1.cglib代理的原理是继承extends
         */
        enhancer.setSuperclass(ProxyTestServiceImpl.class);

        enhancer.setCallback(this);

        return (ProxyTestService) enhancer.create();
    }

    /**
     * o: cglib生成的代理类，是子类
     *
     * objects: 方法调用参数
     *
     * methodProxy: cglib代理方法
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        log.info("打开事务");

        // true
        TestHelper.println("o instanceof ProxyTestServiceImpl", o instanceof ProxyTestServiceImpl);

        // class com.zj.test.spring.proxy.service.impl.ProxyTestServiceImpl$$EnhancerByCGLIB$$979a33ef
        TestHelper.println("o instanceof ProxyTestServiceImpl", o.getClass());

        TestHelper.println("method.getName()",method.getName());

        TestHelper.println("method.getClass()",method.getClass());

        // 与动态代理不同，如果方法没有参数，则传递的参数Object[]数组size为0，objects不为null。
        TestHelper.println(method.getName()+" 接受参数objects==null?", Objects.isNull(objects));
        // 0
        TestHelper.println(method.getName()+" 接受参数个数",objects.length);
        // []
        TestHelper.println(method.getName()+" 接受参数", Arrays.toString(objects));

        // 和动态代理一样，代理方法返回类型为void时，返回null。
        Object returnVal = methodProxy.invokeSuper(o, objects);
        TestHelper.println(method.getName()+" 返回值", returnVal);

        /*
        不能这样调用，否则：
        java.lang.reflect.InvocationTargetException

        会循环调用代理方法导致出错。
        */
        //method.invoke(o,objects);

        log.info("关闭事务");

        return returnVal;
    }
}
