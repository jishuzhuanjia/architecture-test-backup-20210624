package com.zj.test.transaction.impl;

import com.zj.test.transaction.mapper.TransactionTestMapper;
import com.zj.test.transaction.service.TransactionTestService;
import com.zj.test.util.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/13 13:51
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Service
public class TransactionTestServiceImpl implements TransactionTestService {
    @Autowired
    TransactionTestMapper mapper;

    @Transactional
    @Override
    public void atomicityTest() {
        mapper.addMoneyBy1000();

        /**
         * 如果捕获异常，而没有继续抛出异常，则不会自动回滚。
         * **/
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            /** 如果需要在捕获异常的情况下，实现回滚，添加下面一句就可以了。 */
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        //int i = 1 / 0;

        mapper.addMoneyBy1000();
    }
}
