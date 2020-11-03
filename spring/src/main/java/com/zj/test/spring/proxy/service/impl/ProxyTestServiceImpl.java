package com.zj.test.spring.proxy.service.impl;

import com.zj.test.spring.proxy.service.ProxyTestService;
import lombok.extern.slf4j.Slf4j;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/3 23:22
 * @description: cglib和动态代理技术代理使用的接口实现类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Slf4j
public class ProxyTestServiceImpl implements ProxyTestService {

    @Override
    public void insert() {
        log.info("插入数据");
    }

    @Override
    public void delete() {
        log.info("删除数据");
    }

    @Override
    public void update() {
        log.info("更新数据");
    }

    @Override
    public void select() {
        log.info("查询数据");
    }
}
