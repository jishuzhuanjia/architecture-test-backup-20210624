package com.zj.test.dubbocustomer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zj.test.dobboprovider.service.DubboProviderTestService;
import com.zj.test.dubbocustomer.service.DubboCustomerTestService;
import org.springframework.stereotype.Service;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 10:45
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service
public class DubboProviderTestServiceImpl implements DubboCustomerTestService {
    // 一直为null?
    /**
     * 1.消费者Reference注解参数说明:
     * 1.1.retries参数: 当调用provider接口方法超时时，重试的次数,默认值0
     * 默认值0: 实际上为2次，假设每次都超时，则provider方法共调用3次。
     * n: provider接口方法调用次数: n+1次
     *
     * 1.2.timeout: 提供者接口方法必须在此时间内完成响应，否则抛出异常。
     * 默认值:0 ,注意，实际上是1000ms。
     *
     * 当消费者调用提供者方法超时时, 不管retries值为多少,只抛出一次异常：
     * com.alibaba.dubbo.rpc.RpcException
     * ...
     * Caused by: com.alibaba.dubbo.remoting.TimeoutException: Waiting server-side response timeout. start time: 2020-10-23 10:20:47.397, end time: 2020-10-23 10:20:48.401, client elapsed: 0 ms, server elapsed: 1004 ms, timeout: 1000 ms, request: Request [id=2, version=2.0.0, twoway=true, event=false, broken=false, data=RpcInvocation [methodName=timeoutTest, parameterTypes=[], arguments=[], attachments={path=com.zj.test.dobboprovider.service.DubboProviderTestService, interface=com.zj.test.dobboprovider.service.DubboProviderTestService, version=0.0.0, timeout=1000}]], channel: /172.16.200.28:3919 -> /172.16.200.28:20890
     */

    /**
     * 2.provider timeout 和costomer timeout
     * 假设provider和customer都设置了timeout属性,则最终值判断逻辑如下:
     * 1.两者都是0，则为默认值1000ms。
     * 2.1方为0，另一方不等于0，则最终值由非0方决定。
     * 3.两者都非0,由customer决定。
     *
     * 3.provider retries 和customer retries
     * 同timeout
     */

    /**
     * 4.retries参数的设置
     * 因为customer调用超时时会抛出异常,因此如果不对异常进行捕获,就会中断代码.
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
     * */
    @Reference(timeout = 1000,retries = -1)
    DubboProviderTestService dubboProviderTestService;

    @Override
    public void helloDubboTest() {
        dubboProviderTestService.helloDubbo();
    }

    @Override
    public void timeoutTest() {
        dubboProviderTestService.timeoutTest();
    }
}
