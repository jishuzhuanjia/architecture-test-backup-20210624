package com.zj.test.jpa.test.field_mapping.enums;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/15 14:12
 * @description: JPA枚举字段序列化测试枚举类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public enum SexEnum {

    MALE(2,"男"),FEMALE(3,"女");

    /*
    @Enumerated
    EnumType.ORDINAL: 映射成Integer
    EnumType.STRING: 映射成String
     */

    Integer code;

    String des;

    private SexEnum(Integer code,String des){
        this.code = code;
        this.des = des;
    }
}
