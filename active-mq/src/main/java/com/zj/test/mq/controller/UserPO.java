package com.zj.test.mq.controller;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/23 15:28
 * @description: activeMQ消息类型转换测试PO类
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月23日 16:32:44
 */
@Setter
@Getter
public class UserPO implements Serializable {
    String username;
    String password;
    int age;
}