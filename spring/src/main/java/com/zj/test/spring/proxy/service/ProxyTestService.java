package com.zj.test.spring.proxy.service;

import lombok.extern.slf4j.Slf4j;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/3 23:19
 * @description: cglib和动态代理代理实现的接口、该接口模拟持久层增删改查操作
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public interface ProxyTestService {

    /**
     * 增
     */
    public void insert();

    /**
     * 删
     */
    public void delete();

    /**
     * 改
     */
    public void update();

    /**
     * 查
     */
    public void select();
}
