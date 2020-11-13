package com.zj.test.transaction;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/13 13:59
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import com.zj.test.TransactionTestApplication;
import com.zj.test.transaction.impl.TransactionTestServiceImpl;
import com.zj.test.transaction.mapper.TransactionTestMapper;
import com.zj.test.transaction.service.TransactionTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@org.springframework.boot.test.context.SpringBootTest(classes = TransactionTestApplication.class)
@RunWith(SpringRunner.class)
public class SpringBootTest {

    @Autowired
    TransactionTestMapper mapper;

    @Autowired
    TransactionTestService transactionTestService;

    /**
     * 初始化测试环境:
     *  CREATE TABLE `user` (
     *   `uid` int(11) NOT NULL,
     *   `username` varchar(18) NOT NULL,
     *   `password` varchar(18) NOT NULL,
     *   `age` tinyint(4) DEFAULT NULL,
     *   `money` double DEFAULT NULL,
     *   PRIMARY KEY (`uid`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8
     */
    @Test
    public void initTestEnviroment() {
        mapper.initTestEnvironment();
    }

    /**
     * author: 2025513
     *
     * 1.测试事务原子性
     *
     * 测试思路:
     *
     * 结果:
     * 业务过程中如果抛出异常，事务会自动回滚，如果手动try-catch而没有抛出异常，
     * 则不会回滚
     *
     * 结论:
     * @Transactional注解可以添加在service或impl的方法上，都可以。
     */
    @Test
    public void atomicityTest() {
        transactionTestService.atomicityTest();

    }


}
