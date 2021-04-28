package com.zj.test.transaction.service;

import com.zj.test.transaction.exception.TransactionTestException;

import java.io.IOException;

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

    /**
     * @Transaction注解作用的异常种类测试
     */
    public void exceptionTypetTest() throws TransactionTestException, IOException;

    public void changeExceptionTypeTest() throws IOException, ClassNotFoundException;

}
