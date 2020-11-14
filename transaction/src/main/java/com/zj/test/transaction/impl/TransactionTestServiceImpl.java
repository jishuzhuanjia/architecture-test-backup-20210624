package com.zj.test.transaction.impl;

import com.zj.test.transaction.exception.TransactionTestException;
import com.zj.test.transaction.mapper.TransactionTestMapper;
import com.zj.test.transaction.service.TransactionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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

    /**
     * 2020年11月13日 15:09:50
     * 1.测试：Srping @Transaction对事务的支持 和 事务的原子性测试
     *
     * 1.spring的事务支持：当在事务期间发生异常时，会自动回滚。
     *
     * 2.spring默认支持unchecked错误和异常，即Error和RuntimeException及他们的子类。
     */
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

    /**
     * author: 2025513
     *
     * 2.测试:  @Transactional注解默认作用的异常种类测试
     *
     * 测试思路: 测试是否支持Error和RuntimeException和他们的子类
     *
     * 结果:
     *
     * 结论:
     */
    @Transactional
    @Override
    public void exceptionTypetTest() throws TransactionTestException, IOException {

        mapper.addMoneyBy1000();

        /*// 模拟产生RuntimeException(RuntimeException的子类)
        // ArithmeticException extends RuntimeException
        // 结果: 成功回滚
        int i =1/0;*/

        // 测试checked异常类
        // IOEXception extends Exception
        // 结果: 没有回滚
        ImageIO.read(new File("/com/xx/s.ong"));

        mapper.addMoneyBy1000();
    }

    /**
     * author: 2025513
     *
     * 3.更改spring @Transaction注解作用的异常和错误种类
     *
     * 测试思路:
     * 默认情况下不支持Exception异常，我们更改使之支持。
     *
     * 结果:
     * 成功
     *
     * 结论:
     * 1.rollbackFor和noRollbackFor
     * rollbackFor: 该异常或错误及其子类会回滚。
     * noRollbackFor: 在满足rollbackFor的条件下，对某些异常进行排除，排除的这些异常不再自动回滚。
     */
    @Transactional(rollbackFor = Exception.class, noRollbackFor = IOException.class)
    @Override
    public void changeExceptionTypeTest() throws IOException, ClassNotFoundException {
        mapper.addMoneyBy1000();
        ImageIO.read(new File("/com/xx/s.ong"));
        //Class.forName("com.xjxjxjx.UJJJ");
        mapper.addMoneyBy1000();
    }
}
