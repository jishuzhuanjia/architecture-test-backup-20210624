package com.zj.test.jpa.test.field_mapping.entity;

import lombok.Data;

import javax.persistence.Embeddable;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/15 17:13
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@Embeddable // 如果没有该注解，当此类用做jpa实体类的属性时,会报错。
@Data
public class Name {

    String firstName;

    String lastName;
}
