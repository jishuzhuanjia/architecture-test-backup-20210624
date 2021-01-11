package com.zj.test.dobboprovider.service;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 10:44
 * @description: dubbo provider测试Service
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public interface DubboProviderTestService {


    /**
     * 测试1: provider服务是否有效检测方法。
     */
    public String helloDubbo();

    /**
     * 测试2：timeout参数测试接口
     */
    public String timeoutTest();
}
