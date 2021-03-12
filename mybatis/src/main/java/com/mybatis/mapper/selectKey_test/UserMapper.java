package com.mybatis.mapper.selectKey_test;

import com.mybatis.mapper.selectKey_test.po.UserPO;
import org.apache.ibatis.annotations.Param;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/8 9:15
 * @description: 用户表user Mapper
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public interface UserMapper {

    /**
     * 插入新用户
     */
    public Long insertUser(@Param("user") UserPO user);

    /**
     * 插入新用户, 并返回最后一次插入数据创建的主键
     */
    public int insertUser2(@Param("user") UserPO user);
}
