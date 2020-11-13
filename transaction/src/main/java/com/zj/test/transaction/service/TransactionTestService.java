package com.zj.test.transaction.service;

import org.springframework.transaction.annotation.Transactional;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/13 13:43
 * @description: 事务测试service
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public interface TransactionTestService {

    /**
      * 事务原子性测试
      */

    public void atomicityTest();

}
