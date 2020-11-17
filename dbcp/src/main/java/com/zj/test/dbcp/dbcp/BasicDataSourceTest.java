package com.zj.test.dbcp.dbcp;

import com.zj.test.util.TestHelper;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/13 10:57
 * @description:
 * @version: 1.0
 * @finished: 1
 * @finished-time: 2020年11月17日 09:55:14
 */
public class BasicDataSourceTest {

    /**
     * author: 2025513
     *
     * 1.BasicDataSource数据源的使用
     *
     * 测试思路:
     *
     * 结果:
     *
     * 结论:
     * 1.preparedStatement设置占位符的参数，index基于1。
     *
     */
    @Test
    public void basicDataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost/test5?serverTimezone=Asia/Shanghai");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");
        /*
        1.setConnectionInitSqls
        设置创建连接时，执行的语句。
        每个Connection创建后，都会执行语句。

        在第一次调用下列方法之一时初始化池:
        getConnection、setLogwriter、setLoginTimeout、getLoginTimeout、getLogWriter。

        初始化后，调用setConnectionInitSqls()不会有效果。

        注意：
        1.initSqls执行的次数可能大于getConnection()调用的次数。
        */
        basicDataSource.setConnectionInitSqls(Arrays.asList("insert into tb5() values (121)"));

        try {
            Connection connection = basicDataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Connection connection = basicDataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // 因为数据源已经初始化，调用setConnectionInitSqls()无效
        basicDataSource.setConnectionInitSqls(Arrays.asList("insert into tb5() values (222)"));

        try {
            Connection connection = basicDataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Connection connection = basicDataSource.getConnection();
            /*
            1.设置是否自动提交，默认false
            当设置为true时，意味着开启事务，需要手动commit才会提交更改
            */
            connection.setAutoCommit(false);
            TestHelper.println("connection==null", Objects.isNull(connection));
            TestHelper.println("getInitialSize()",basicDataSource.getInitialSize());
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tb5() values (?)");
            preparedStatement.setInt(1,111111);
            preparedStatement.executeUpdate();

            // 手动提交
            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
