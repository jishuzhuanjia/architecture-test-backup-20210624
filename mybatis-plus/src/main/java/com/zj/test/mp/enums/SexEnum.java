package com.zj.test.mp.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.zj.test.util.TestHelper;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/12 17:20
 * @description: 通用枚举测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public enum SexEnum {
    MALE(0,"男"),FEMALE(1,"女");

    /*
    标记序列化到数据库的属性,也可以:

    方式二： 枚举属性，实现 IEnum 接口如下：

    public enum AgeEnum implements IEnum<Integer> {
        ONE(1, "一岁"),
        TWO(2, "二岁"),
        THREE(3, "三岁");

        private int value;
        private String desc;

        @Override
        public Integer getValue() {
            return this.value;
        }
    }*/
    @EnumValue// 注意，如果没有添加@EnumValue注解，则会序列化枚举常量,在这里指的是MALE/FEMALE.
    private int code;

    private String des;

    private SexEnum(int code,String des){
        this.code = code;
        this.des=des;
    }

    // 本人在测试时,toString并没有被调用,也没有被序列化到数据库
    @Override
    public String toString() {
        TestHelper.println("SexEnum.toString()...");
        return "hahaha";
    }
}
