package com.zj.springboot.test.annotation.EnableConfigurationProperties;

import com.zj.springboot.test.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/22 14:24
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
// 实例化指定的类，指定的类需要有ConfigurationProperties注解，可以没有实例化注解
@EnableConfigurationProperties(User.class)
// @Component
public class EnableConfigurationPropertiesTest {

    // 因为@EnableConfigurationProperties已经实例化了指定类型的Bean,这里只需要注入即可
    @Autowired
    User user;
}
