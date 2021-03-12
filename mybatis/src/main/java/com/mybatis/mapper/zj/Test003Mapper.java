package com.mybatis.mapper.zj;

import java.util.List;
import java.util.Map;

//用该注解注解的接口会被mybatis扫描到
// 如果mapper文件不书写在@MapperScan指定的包中，需要添加该注解
// 不能用于mapper jar的mapper扫描
//@Mapper
public interface Test003Mapper {

    // 测试PageHelper插件
    public List<Map<Object,Object>> testPageHelper();

}
