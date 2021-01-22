package com.zj.springboot.test.annotation.ConfigurationProperties;

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

@Component
// 必须和序列化注解一起使用,否则报错
// 可以注入来自全局application配置和@ImportResource引入的配置
@ConfigurationProperties(prefix = "configuration.properties")
@Getter
@Setter
@ToString
public class ConfigurationPropertiesTest {

    String username;

    String password;
}
