package com.zj.test.datasource.mapper;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/18 17:41
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */


import com.zj.util.datasource.dynamic.annotation.DataSource;

import java.util.List;
import java.util.Map;

// 如果指定名的数据源不存在，则会使用默认数据源
// 因为在构造动态数据源的时候数据源对象null是不合法的,会启动报错
// 所以可以认为只要@DataSource指定的数据源名存在，则数据源可用
@DataSource("dynamic1") //放到impl,controller层也可以
public interface TestMapper {
    public List<Map<Object,Object>> selectTest();
}
