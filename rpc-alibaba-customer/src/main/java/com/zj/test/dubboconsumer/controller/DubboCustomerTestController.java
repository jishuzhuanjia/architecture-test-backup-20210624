package com.zj.test.dubboconsumer.controller;

import com.zj.test.dubboconsumer.service.DubboConsumerTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/22 21:42
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@RequestMapping("/test/dubbo/customer")
@RestController
public class DubboCustomerTestController {
    //@Autowired
    DubboConsumerTestService dubboCustomerTestService;
    @RequestMapping("test1")
    public String test1(){
        dubboCustomerTestService.helloDubboTest();
        return "调用成功";
    }

    /**
     * 1.消费者调用提供者的接口，如果超时为返回将抛出异常:
     *com.alibaba.dubbo.rpc.RpcException: Failed to invoke the method timeoutTest in the service com.zj.test.dobboprovider.service.DubboProviderTestService. Tried 3 times of the providers [172.16.200.28:20890] (1/1) from the registry localhost:2181 on the consumer 172.16.200.28 using the dubbo version 2.5.3. Last error is: Invoke remote method timeout. method: timeoutTest, provider: dubbo://172.16.200.28:20890/com.zj.test.dobboprovider.service.DubboProviderTestService?anyhost=true&application=springboot-dubbo-customer&check=false&dubbo=2.5.3&interface=com.zj.test.dobboprovider.service.DubboProviderTestService&methods=repeat,timeoutTest&pid=14424&side=consumer&timeout=1000&timestamp=1603419641931, cause: Waiting server-side response timeout. start time: 2020-10-23 10:20:47.397, end time: 2020-10-23 10:20:48.401, client elapsed: 0 ms, server elapsed: 1004 ms, timeout: 1000 ms, request: Request [id=2, version=2.0.0, twoway=true, event=false, broken=false, data=RpcInvocation [methodName=timeoutTest, parameterTypes=[], arguments=[], attachments={path=com.zj.test.dobboprovider.service.DubboProviderTestService, interface=com.zj.test.dobboprovider.service.DubboProviderTestService, version=0.0.0, timeout=1000}]], channel: /172.16.200.28:3919 -> /172.16.200.28:20890
     * 	at com.alibaba.dubbo.rpc.cluster.support.FailoverClusterInvoker.doInvoke(FailoverClusterInvoker.java:101)
     * 	at com.alibaba.dubbo.rpc.cluster.support.AbstractClusterInvoker.invoke(AbstractClusterInvoker.java:227)
     * 	at com.alibaba.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker.invoke(MockClusterInvoker.java:72)
     * 	at com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler.invoke(InvokerInvocationHandler.java:52)
     * 	at com.alibaba.dubbo.common.bytecode.proxy0.timeoutTest(proxy0.java)
     * 	at com.zj.test.dubbocustomer.service.impl.DubboProviderTestServiceImpl.testTimeout(DubboProviderTestServiceImpl.java:29)
     * 	at com.zj.test.dubbocustomer.controller.DubboCustomerTestController.test2(DubboCustomerTestController.java:31)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     * 	at java.lang.reflect.Method.invoke(Method.java:498)
     * 	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)
     * 	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)
     * 	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)
     * 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:891)
     * 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:797)
     * 	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
     * 	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)
     * 	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)
     * 	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)
     * 	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866)
     * 	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635)
     * 	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851)
     * 	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * 	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * 	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
     * 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * 	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)
     * 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * 	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:93)
     * 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * 	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200)
     * 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
     * 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193)
     * 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166)
     * 	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)
     * 	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96)
     * 	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:493)
     * 	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)
     * 	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81)
     * 	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)
     * 	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342)
     * 	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:800)
     * 	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
     * 	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:806)
     * 	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1498)
     * 	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
     * 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
     * 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
     * 	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
     * 	at java.lang.Thread.run(Thread.java:748)
     * Caused by: com.alibaba.dubbo.remoting.TimeoutException: Waiting server-side response timeout. start time: 2020-10-23 10:20:47.397, end time: 2020-10-23 10:20:48.401, client elapsed: 0 ms, server elapsed: 1004 ms, timeout: 1000 ms, request: Request [id=2, version=2.0.0, twoway=true, event=false, broken=false, data=RpcInvocation [methodName=timeoutTest, parameterTypes=[], arguments=[], attachments={path=com.zj.test.dobboprovider.service.DubboProviderTestService, interface=com.zj.test.dobboprovider.service.DubboProviderTestService, version=0.0.0, timeout=1000}]], channel: /172.16.200.28:3919 -> /172.16.200.28:20890
     * 	at com.alibaba.dubbo.remoting.exchange.support.DefaultFuture.get(DefaultFuture.java:107)
     * 	at com.alibaba.dubbo.remoting.exchange.support.DefaultFuture.get(DefaultFuture.java:84)
     * 	at com.alibaba.dubbo.rpc.protocol.dubbo.DubboInvoker.doInvoke(DubboInvoker.java:96)
     * 	at com.alibaba.dubbo.rpc.protocol.AbstractInvoker.invoke(AbstractInvoker.java:144)
     * 	at com.alibaba.dubbo.monitor.support.MonitorFilter.invoke(MonitorFilter.java:75)
     * 	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
     * 	at com.alibaba.dubbo.rpc.protocol.dubbo.filter.FutureFilter.invoke(FutureFilter.java:53)
     * 	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
     * 	at com.alibaba.dubbo.rpc.filter.ConsumerContextFilter.invoke(ConsumerContextFilter.java:48)
     * 	at com.alibaba.dubbo.rpc.protocol.ProtocolFilterWrapper$1.invoke(ProtocolFilterWrapper.java:91)
     * 	at com.alibaba.dubbo.rpc.listener.ListenerInvokerWrapper.invoke(ListenerInvokerWrapper.java:74)
     * 	at com.alibaba.dubbo.rpc.protocol.InvokerWrapper.invoke(InvokerWrapper.java:53)
     * 	at com.alibaba.dubbo.rpc.cluster.support.FailoverClusterInvoker.doInvoke(FailoverClusterInvoker.java:77)
     * 	... 60 more
     * 调用接口超时
     *
     * @return
     */
    @RequestMapping("test2")
    public String test2(){

        try {
            dubboCustomerTestService.timeoutTest();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("调用接口超时");
        }
        return "调用成功";
    }
}
