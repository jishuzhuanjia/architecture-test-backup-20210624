package com.zj.test.datasource.config;

import com.zj.util.datasource.dynamic.config.AbstractDataSourceConfig;

import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/21 13:26
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class DataSourceConfig1 extends AbstractDataSourceConfig {
    @Override
    public String getTypeAliasesPackage() {
        return null;
    }

    @Override
    public List<String> getMapperLocations() {
        return null;
    }

    @Override
    public Map<Object, Object> getDataSourceMap() {
        return null;
    }
}
