package com.zj.test.spring.spring_beans.po;

import lombok.Data;
import lombok.Getter;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/3/23 10:24
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Data
public class UserDO extends UserDOEditable {
    String username;
    String password;
}
