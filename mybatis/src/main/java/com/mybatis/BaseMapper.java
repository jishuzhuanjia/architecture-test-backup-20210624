package com.mybatis;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

// 默认mybatis mapper.jar提供的BaseMapper没有selectByExample，以下为加强版接口
// 这里的T是用来对应表名的，因此不能使用Map这样的类型，应该是表对应的实体类。
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
