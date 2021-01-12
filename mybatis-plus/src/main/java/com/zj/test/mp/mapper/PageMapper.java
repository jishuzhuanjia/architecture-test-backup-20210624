package com.zj.test.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zj.test.mp.po.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/11 16:21
 * @description: mybatis-plus分页查询xml实现demo
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
// 可以不继承BaseMapper
public interface PageMapper /*extends BaseMapper<Teacher>*/ {

    /**
     * 1.mybatis-plus实现无分页查询方式
     */
    List<Teacher> queryNoPage(String name);


    /**
     * 2.mybatis xml方式实现分页查询
     * 分页查询的结果封装在List中
     */
    List<Teacher> queryByPage(Page<?> page,@Param("name") String name);

    /**
     * 3.mybatis xml方式实现分页查询
     * 分页查询的结果封装在Page中
     */
    IPage<Teacher> queryByPage2(@Param("name") String name,Page<?> page);
}
