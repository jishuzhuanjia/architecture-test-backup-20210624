package com.zj.springboot.test.annotation.ConfigurationPropertiesToMap;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 13:08
 * @description: @ConfigurationProperties测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
// 必须和序列化注解一起使用,否则报错
// 可以注入来自全局application配置和@ImportResource引入的配置
@ConfigurationProperties(prefix = "test")
@Getter
@Setter
@ToString
public class ConfigurationPropertiesToMapTest {
    /*

    test.map.username=zhou
    test.map.password=password
    */
    // 下面的写法将会注入以上username,password配置
    Map<Object,Object> map;

}
