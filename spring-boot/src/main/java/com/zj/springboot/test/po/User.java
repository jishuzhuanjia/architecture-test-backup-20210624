package com.zj.springboot.test.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 10:23
 * @description: spring boot测试用PO类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "global")
public class User {
    String username;

    String password;
}
