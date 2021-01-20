package com.zj.test.datasource.config;

import com.zj.util.datasource.dynamic.config.AbstractDataSourceConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/20 23:42
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

@Configuration
@PropertySource("classpath:db.properties")
public class DataSourceConfig extends AbstractDataSourceConfig {
    public DataSourceConfig() {
    }

    @Bean({"defaultDataSource"})
    @ConfigurationProperties(
            prefix = "spring.datasource.default"
    )
    protected DataSource defaultDataSource() {
        return super.defaultDataSource();
    }

    @Bean({"dataSource1"})
    @ConfigurationProperties(
            prefix = "spring.datasource.dynamic1"
    )
    protected DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    public Map<Object, Object> getDataSourceMap() {
        Map<Object, Object> dataSourceMap = new HashMap();
        dataSourceMap.put("dynamicDataSource1", this.dataSource1());
        return dataSourceMap;
    }

    public String getTypeAliasesPackage() {
        return "com.zj.dassd";
    }

    public List<String> getMapperLocations() {
        List<String> mapperLocations = new ArrayList();
        mapperLocations.add("classpath:xml/*.xml");
        mapperLocations.add("classpath:xml2/*.xml");
        return mapperLocations;
    }
}

