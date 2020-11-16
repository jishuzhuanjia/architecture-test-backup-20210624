package com.zj.test.dbcp.dbcp;

import com.zj.test.util.TestHelper;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/13 10:57
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class DbcpTest {

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
     * preparedStatement设置占位符的参数，index基于1。
     */
    @Test
    public void basicDataSource() {

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost/test5?serverTimezone=Asia/Shanghai");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");

        try {
            Connection connection = basicDataSource.getConnection();
            /*
            1.设置是否自动提交，默认false
            当设置为true时，意味着开启事务，需要手动commit才会提交更改
            */
            connection.setAutoCommit(false);
            TestHelper.println("connection==null", Objects.isNull(connection));
            TestHelper.println("getInitialSize()",basicDataSource.getInitialSize());
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tb5 values (?)");
            preparedStatement.setInt(1,111111);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
