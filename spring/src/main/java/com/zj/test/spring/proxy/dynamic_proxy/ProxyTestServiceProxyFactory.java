package com.zj.test.spring.proxy.dynamic_proxy;

import com.zj.test.spring.proxy.service.ProxyTestService;
import com.zj.test.spring.proxy.service.impl.ProxyTestServiceImpl;
import com.zj.test.util.TestHelper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/3 23:25
 * @description: 动态代理技术demo: 模拟开启事务
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月18日 14:57:35
 */

/**
 * 动态代理技术是 java反射 提供的
 *
 * ProxyTestService代理工厂实现类
 */
@Slf4j
public class ProxyTestServiceProxyFactory implements InvocationHandler {
    ProxyTestService proxyTestService;

    public ProxyTestServiceProxyFactory(ProxyTestService proxyTestService) {
        this.proxyTestService = proxyTestService;
    }

    /**
     * 获取ProxyTestService接口代理实例对象
     */
    public ProxyTestService getProxyTestServiceProxy() {

        // java.lang.IllegalArgumentException: interface com.zj.test.spring.proxy.service.ProxyTestService is not visible from class loader

        // 应该是ProxyTestServiceImpl.class.getInterfaces()而不是ProxyTestService.class.getInterfaces()，否则:
        // java.lang.ClassCastException: com.sun.proxy.$Proxy4 cannot be cast to com.zj.test.spring.proxy.service.ProxyTestService
        /*ProxyTestService o = (ProxyTestService) Proxy.newProxyInstance(ProxyTestService.class.getClassLoader(), ProxyTestService.class.getInterfaces(),
                this);*/

        /*
        返回指定接口的代理类的实例

        1.loader：不能是Class.class.getClassLoader(),否则会:
        java.lang.IllegalArgumentException: interface com.zj.test.spring.proxy.service.ProxyTestService
            is not visible from class loader

        2.interfaces：必须是要代理的接口，否则可能会发生类转换异常：
        java.lang.ClassCastException:
            com.sun.proxy.$Proxy4 cannot be cast to com.zj.test.spring.proxy.service.ProxyTestService

        这里也可以通过proxyTestService.class.getInterfaces[]

        interfaces几种获取方式：
        1.直接通过接口, 指定需要代理的接口。
        这是最好的一种方式，而不用管接口的具体实现。
        new Class[]{ProxyTestService.class}

        2.通过实现类，但是这种方式不常用，因为往往我们不知道具体的实现类。
        并且会代理实现类实现的所有接口，这往往不符合业务要求。
        ProxyTestServiceImpl.class.getInterfaces()

        3.通过接口实现类实例对象
        proxyTestService.getClass().getInterfaces()
        虽然我们也不用管具体的实现，
        和2一样会代理实现类实现的所有接口，这往往不符合业务要求。
        */
        ProxyTestService o = (ProxyTestService) Proxy.newProxyInstance(
                ProxyTestService.class.getClassLoader(),new Class[]{ProxyTestService.class}, this);
        return o;
    }

    /**
     * method: 代理接口中的方法
     *
     * args: 调用method传递的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 模拟打开事务
        log.info("打开事务...");

        // 如果接口中业务方法无参，则args==null。
        TestHelper.println(method.getName() +" 接受参数", Arrays.toString(args));

        // 调用业务代码
        Object returnVal = method.invoke(proxyTestService, args);

        // 如果接口中业务代码方法返回类型为void,返回值为null
        TestHelper.println(method.getName() +" 返回值", returnVal);

        // 模拟关闭事务
        log.info("关闭事务...");

        return returnVal;
    }
}
