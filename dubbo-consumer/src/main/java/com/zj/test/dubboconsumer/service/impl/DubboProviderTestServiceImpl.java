package com.zj.test.dubboconsumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.zj.test.dobboprovider.service.DubboProviderTestService;
import com.zj.test.dubboconsumer.service.DubboConsumerTestService;
import com.zj.test.util.TestHelper;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 10:45
 * @description: dubbo consumer实现类：调用provider服务。
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
// 不能和spring bean注解一起使用，否则服务会注册失败。
@Service()
public class DubboProviderTestServiceImpl implements DubboConsumerTestService {
    // 一直为null?
    /**
     * 1.消费者Reference注解参数说明
     *
     * 1.1.retries参数: 当调用provider接口方法超时时，重试的次数,默认值0
     * 默认值0: 实际上为2次，假设每次都超时，则provider方法共调用3次。
     * n: provider接口方法调用次数: n+1次
     * 注：由于次数是整数，因此参数值的讨论结果如下：
     * 当是n<= -1的整数时，不会重试，重试次数为0。
     * 当n=0时，重试次数为2次。
     * 当n>=1时，重试次数为n次。
     *
     * 1.2.timeout: 提供者接口方法必须在此时间内完成响应，否则抛出异常。
     * 默认值为0，实际上是1000ms。
     * 注意：当timeout<=0 ,注意，实际上是1000ms。
     *
     * 1.3.重试过程
     * 第一次超时达到timeout -> 再次调用provider方法 ->如果超时，继续重试到retries次-> consumer代码报错,如果不捕获异常，代码终止。
     *
     * 1.4.异常情况
     * 方法调用异常捕获是可选的，如果不捕获，一旦超时，代码报错，中断。
     * 如果捕获，代码还能继续执行。
     *
     * 当消费者调用提供者方法超时时, 不管retries值为多少,只抛出一次异常：
     * com.alibaba.dubbo.rpc.RpcException
     * ...
     * Caused by: com.alibaba.dubbo.remoting.TimeoutException: Waiting server-side response timeout. start time: 2020-10-23 10:20:47.397, end time: 2020-10-23 10:20:48.401, client elapsed: 0 ms, server elapsed: 1004 ms, timeout: 1000 ms, request: Request [id=2, version=2.0.0, twoway=true, event=false, broken=false, data=RpcInvocation [methodName=timeoutTest, parameterTypes=[], arguments=[], attachments={path=com.zj.test.dobboprovider.service.DubboProviderTestService, interface=com.zj.test.dobboprovider.service.DubboProviderTestService, version=0.0.0, timeout=1000}]], channel: /172.16.200.28:3919 -> /172.16.200.28:20890
     *
     * 【provider timeout 和consumer timeout】
     * 假设provider和consumer都设置了timeout属性,则最终值判断逻辑如下:
     * 1.两者都是0，则为默认值1000ms。                     // true,如果都不设置，默认也为1000ms
     * 2.一方为0，另一方不等于0，则最终值由非0方决定。         // true
     * 3.两者都非0,由consumer决定。                       // true
     *
     * 【provider retries 和consumer retries】
     * 1.两者都是0，则重试2次。最多共调用provider方法3次。    // true
     * 2.一方为0，另一方不等于0，则由非0方决定。              // true
     * 3.两者都非0，由consumer决定。                      // true
     *
     * 【retries参数的设置】
     * 因为consumer调用超时时会抛出异常,因此如果不对异常进行捕获,就会中断代码.
     * 可是对于provider而言，方法会继续执行，直到完成为止。
     * 因此当retries >= 0时，就相当于执行一个多线程的任务。
     *
     * 因此retries的设置要分情况来决定:
     * 1.如果provider多次调用执行的是相同的内容。则建议设置为-1，不重复调用。
     * 如更新用户密码，因为多次执行相同的任务没有任何意义，此时不用重复调用。
     *
     * 2.如果provider多次调用执行的任务不是相同的任务，如每次从用户表中获取1000个不同的用户并更新他们的信息，因为多次重复的调用
     * 修改的是不同的内容，可以适当增加retries、并降低tomeout值来达到一个多线程的效果。需要注意的是这种方式后续的重试线程都会等待至少一个timeout，
     * 越后面的线程等待的时间越久。因此任务还没有执行就已经浪费了很多的启动时间，这种重试方式实现的多线程可用在对速度要求不是很高的场景下，并且它在
     * 一定程度上降低主机的压力。
     *
     * 1.5.check,默认true
     * 【作用】
     * 启动时是否检测指定的provider服务是否存在，如果不存在，则会将代理对象设置为null来表示服务处理错误的状态,
     * 即使后来privider服务启动并注册了，代理对象也将一直为null。
     *
     * 如果为false,启动时不会检测侧指定的provider是否存在，并且注入的是代理对象，不为空。
     * 实际调用的过程中，如果provider不存在,会抛出异常:
     * om.alibaba.dubbo.rpc.RpcException: No provider available from registry localhost:2181 for service com.zj.test.dobboprovider.service.DubboProviderTestService on consumer 172.16.200.122 use dubbo version 2.5.6, may be providers disabled or not registered ?
     * 	at com.alibaba.dubbo.registry.integration.RegistryDirectory.doList(RegistryDirectory.java:573) ~[dubbo-2.5.6.jar:2.5.6]
     * 	at com.alibaba.dubbo.rpc.cluster.directory.AbstractDirectory.list(AbstractDirectory.java:73) ~[dubbo-2.5.6.jar:2.5.6]
     *
     * 【报错】
     * 1.check=true,会在启动的时候检查是否存在对应的provider服务,如果没有，会报错：
     * java.lang.IllegalStateException: Failed to check the status of the service com.zj.test.dobboprovider.service.DubboProviderTestService. No provider available for the service com.zj.test.dobboprovider.service.DubboProviderTestService from the url zookeeper://localhost:2181/com.alibaba.dubbo.registry.RegistryService?application=springboot-dubbo-consumer&dubbo=2.5.6&interface=com.zj.test.dobboprovider.service.DubboProviderTestService&methods=timeoutTest,helloDubbo&pid=15268&retries=1&side=consumer&timeout=-1&timestamp=1609401613900 to the consumer 172.16.200.122 use dubbo version 2.5.6
     * 	at com.alibaba.dubbo.config.ReferenceConfig.createProxy(ReferenceConfig.java:407) ~[dubbo-2.5.6.jar:2.5.6]
     * 	at com.alibaba.dubbo.config.ReferenceConfig.init(ReferenceConfig.java:320) ~[dubbo-2.5.6.jar:2.5.6]
     *
     * 注意：该报错不会中断服务的启动。
     *
     * 2.check=false
     * 在服务启动的时候， 不会报错。
     *
     * 【配置提示】
     * 1.如果dubbo服务需要严格按照 consumer -> provider的顺序启动，则设置check=true(默认)。
     *
     * 2.如果dubbo服务不需要按照顺序启动，支持动态发现，则设置check=false(常用)。
     *
     *
     * 1.6.lazy.默认false
     * 懒加载，是否使用的时候才去连接provider服务。
     * 会出现如下的信息：
     * 2020-12-31 16:27:58.872  INFO 20172 --- [:20890-thread-3] c.a.d.r.p.d.LazyConnectExchangeClient    :  [DUBBO] Lazy connect to dubbo://172.16.200.122:20880/com.zj.test.dobboprovider.service.DubboProviderTestService?anyhost=true&application=springboot-dubbo-consumer&check=false&codec=dubbo&dubbo=2.5.6&generic=false&heartbeat=60000&interface=com.zj.test.dobboprovider.service.DubboProviderTestService&lazy=true&methods=timeoutTest,helloDubbo&pid=20172&remote.timestamp=1609403271268&retries=1&send.reconnect=true&side=consumer&timeout=-1&timestamp=1609403236902, dubbo version: 2.5.6, current host: 172.16.200.122
     * 2020-12-31 16:27:58.929  INFO 20172 --- [:20890-thread-3] c.a.d.remoting.transport.AbstractClient  :  [DUBBO] Successed connect to server /172.16.200.122:20880 from NettyClient 172.16.200.122 using dubbo version 2.5.6, channel is NettyChannel [channel=[id: 0x2331b4a6, /172.16.200.122:11201 => /172.16.200.122:20880]], dubbo version: 2.5.6, current host: 172.16.200.122
     * ...
     *
     * lazy=true在什么情况下奏效？
     * 1.当check=true时，启动时必须有对应的provider,之后调用时provider存在，才会懒加载，否则代理对象将为null。
     * 2.当check=false时，调用时provider服务需要存在，才会懒加载,否则不加载。
     *
     * 1.7.version,默认"",不论版本
     * 接口版本控制,调用接口时，会调用版本符合的接口。如果没有：
     * com.alibaba.dubbo.rpc.RpcException: Failed to invoke the method timeoutTest in the service com.zj.test.dobboprovider.service.DubboProviderTestService. No provider available for the service com.zj.test.dobboprovider.service.DubboProviderTestService:1.0.0 from registry localhost:2181 on the consumer 172.16.200.122 using the dubbo version 2.5.6. Please check if the providers have been started and registered.
     *
     * 需要注意的是，不指定版本的情况下, version默认值不是1.0.0
     *
     * 【jmeter调用时version设置】
     * 如果服务没有指定版本，则null(必须是小写)或不填写都可以。
     *
     * 【@Reference调用时version设置】
     * 如果调用的服务没有指定版本，只能设置""来调用,而不能设置null(语法错误)。
     *
     * 在check=true时也会检查。如果不存在：
     * java.lang.IllegalStateException: Failed to check the status of the service com.zj.test.dobboprovider.service.DubboProviderTestService. No provider available for the service com.zj.test.dobboprovider.service.DubboProviderTestService:1.0.0 from the url zookeeper://localhost:2181/com.alibaba.dubbo.registry.RegistryService?application=springboot-dubbo-consumer&dubbo=2.5.6&interface=com.zj.test.dobboprovider.service.DubboProviderTestService&methods=timeoutTest,helloDubbo&pid=17440&retries=1&revision=1.0.0&side=consumer&timeout=-1&timestamp=1609404275812&version=1.0.0 to the consumer 172.16.200.122 use dubbo version 2.5.6
     *
     * 1.如果provider暴露的接口指定了version,则此处必须指定version,且必须与provider提供的version一致，否则接口实例为null。
     * 2.如果provider暴露的接口没有指定version，则此处不能指定version(当然，指定默认值""是可以的),否则也会因找不到指定的接口而报错：
     * com.alibaba.dubbo.rpc.RpcException: Failed to invoke the method timeoutTest in the service com.zj.test.dobboprovider.service.DubboProviderTestService. No provider available for the service com.zj.test.dobboprovider.service.DubboProviderTestService:1.0.0 from registry localhost:2181 on the consumer 172.16.200.122 using the dubbo version 2.5.6. Please check if the providers have been started and registered.
     *
     * 1.8.group
     * 用来区分相同接口的不同实现。
     *
     * 如果服务指定了group,@Reference注解也需要显示指定group参数，否则：com.alibaba.dubbo.rpc.RpcException: No provider available from registry localhost:2181 for...
     *
     * 1.9.loadbalance(相同权重的服务会依次调用，总调用次数差<=1)
     * 负载均衡策略，只有在不同的ip之间才能看出效果。
     * 测试过在单机注册多个相同服务，结果调用服务时，均衡策略无法生效。
     *
     * 注意，经测试，默认情况下，dubbo使用了默认的均衡策略-random(基于权重的随机负载均衡)。
     *
     *
     * 注意：loadbalance均衡策略应小写，如RoundRobin会报错：No such extension RoundRobin for loadbalance/com.alibaba
     * 应该使用roundrobin
     *
     * 【timeout对均衡策略的影响】
     * 1.对于robin,如果当前调用的服务A超时,会尝试调用B,需要注意的是，下一次请求还是从B开始调用。
     *
     * 1.10.genric-泛化引用泛化服务
     * 使用场景：跨语言或consumer没有对应的接口的情况,但是consumer需要自己定义接口。
     *
     * 【泛化调用注意点】
     * 1.@Reference必须指定interface参数,否则@Reference默认获取成员变量类型对应的服务，一般情况下会在调用时报错：
     * com.alibaba.dubbo.rpc.RpcException: Failed to invoke the method helloDubbo in the service com.zj.test.dubboconsumer.service.GenericServiceTestService. No provider available for the service group2/com.zj.test.dubboconsumer.service.GenericServiceTestService from registry localhost:2181 on the consumer 172.16.200.122 using the dubbo version 2.5.6. Please check if the providers have been started and registered.
     *
     * 2.在指定interface参数的情况下，如果不指定generic=true,@Reference注入的对象将为null,调用服务时会报错。
     *
     * 【备注】
     * 未成功引用，后续有需要再进行测试。
     * */
    @Reference(/*generic=true,interfaceName = "com.zj.test.dobboprovider.service.DubboProviderTestService",*/timeout = -1, retries = 1, check = false,loadbalance = "roundrobin",group="gp1")
    DubboProviderTestService dubboProviderTestService;

    @Override
    public void helloDubboTest() {
        dubboProviderTestService.helloDubbo();
    }

    @Override
    public String timeoutTest() {
        TestHelper.println("invoking provider...");
        //try {
        dubboProviderTestService.timeoutTest();
        // } catch (Exception e) {
        //   e.printStackTrace();
        //}

        return "ok";
    }

    /**
     *
     * 什么情况下,@Reference注入的对象会是null?
     * 当check=true且指定的provider不存在时,代理对象会被赋值为null。来向程序员表明，启动时就报错了,
     * 是一种错误的状态，即使后来privider上线了，也仍然是null。
     *
     * bug: 2.5.4存在bug:
     * 1.先启动consumer，@Reference注入的代理对象将一直为null。
     * 【原因】
     * 该版本存在bug,尝试修改check=false无效，会默认使用check=true,所以会导致代理对象一直为null。
     */
}
