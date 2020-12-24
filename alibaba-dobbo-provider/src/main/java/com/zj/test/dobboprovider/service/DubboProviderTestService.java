package com.zj.test.dobboprovider.service;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 10:44
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public interface DubboProviderTestService {

    public String repeat(String content,int count);

    // timeout参数测试
    public String timeoutTest();
}
