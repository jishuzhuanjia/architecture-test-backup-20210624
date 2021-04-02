package com.zj.test.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/11 13:55
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Setter
@Getter
@ToString
public class User {

    String username;
    String password;

    //@JsonFormat(pattern = "yyyy-MM-dd",name="loginTime") // zj,没有效果

    // name: 最终序列化的字段名
    // 注意: format优先级小于
    //@JSONField(format="yyyy-MM-dd")
    Date lastLoginTime;
}
