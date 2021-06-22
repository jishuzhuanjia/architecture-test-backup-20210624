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
public class User implements Comparable {
    private String username;
    private String password;
    int age;

    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User() {
    }

    /**
     * Collections.sort测试
     */
    @Override
    public int compareTo(Object o) {
        //降序
        //return ((User)o).getAge() - this.age;

        //升序
        return this.age - ((User) o).getAge();
    }
}
