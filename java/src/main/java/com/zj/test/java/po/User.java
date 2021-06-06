package com.zj.test.java.po;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 *      全局测试PO类
 * </p>
 *
 * @author: zhoujian
 * @e-mail: 2025513@qq.com
 * @create-time: 2021/6/6 11:13
 * @is-finished: false
 *
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class User {
    private String username;
    private String password;
    int age;
}
