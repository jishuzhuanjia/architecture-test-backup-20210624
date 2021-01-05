package com.zj.test.dubboconsumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.zj.test.dobboprovider.service.DubboProviderTestService;
import com.zj.test.dubboconsumer.service.DubboConsumerTestService;
import com.zj.test.dubboconsumer.service.GenericServiceInvokeService;
import com.zj.test.util.TestHelper;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021年1月5日 10:37:48
 * @description: 泛化服务泛化调用demo
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service()
public class GenericServiceInvokeTestImpl implements GenericServiceInvokeService {



    /*
    测试过程中的一个小插曲：
    相同接口的服务分组为group1和group2,只有group1可以调用，group2就报错：    java.lang.IllegalArgumentException: Can not set com.alibaba.dubbo.rpc.service.GenericService field com.zj.test.dubboconsumer.service.impl.GenericServiceInvokeTestImpl.dubboProviderTestService to com.alibaba.dubbo.common.bytecode.proxy0
    将注册分组名分别改成gp1 和 gp2后，正常

    注意：开发中不要使用含有group的值作为group属性值，以防万一报错，带来不必要的麻烦。
     */
    // 泛化服务需要使用alibaba提供的泛化服务类：GenericService,否则会出现报错

    // 指定interfaeName而不指定genric=true,且变量类型为GenricService,则报错：java.lang.IllegalArgumentException: Can not set com.alibaba.dubbo.rpc.service.GenericService field com.zj.test.dubboconsumer.service.impl.GenericServiceInvokeTestImpl.dubboProviderTestService to com.alibaba.dubbo.common.bytecode.proxy0
    // 指定interaceName而不指定generic=true,且变量类型为接口，不会报错，调用正常。
    // GenericService,则generic=true、interfaceName也必须指定。
    @Reference(generic=true,interfaceName = "com.zj.test.dobboprovider.service.DubboProviderTestService",timeout = -1, retries = 1, check = false,loadbalance = "roundrobin",group="gp2")
    GenericService dubboProviderTestService;

    @Override
    public void helloDubboTest() {
        //dubboProviderTestService.helloDubbo();
        dubboProviderTestService.$invoke("helloDubbo",null,null);
    }

    @Override
    public String timeoutTest() {
        // 这里不实现泛化调用，直接给它返回
        return "ok";
    }
}
