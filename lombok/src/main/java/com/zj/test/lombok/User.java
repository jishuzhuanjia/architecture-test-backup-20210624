package com.zj.test.lombok;

import lombok.*;

/**
 * @author: zhou jian
 * @qq: 2025513
 * @description:
 * @version: 1.0
 * @create-time: 2021/4/22 11:22
 * @finished: false
 * @finished-time:
 */

@Setter
@Getter
@ToString
@EqualsAndHashCode
@Data
public class User {
    //为特定的属性生成setter方法
    //private @Setter String username;

    String username;
    String password;
}
