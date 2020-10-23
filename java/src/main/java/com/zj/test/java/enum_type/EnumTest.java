package com.zj.test.java.enum_type;

import org.omg.CORBA.UNKNOWN;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/19 9:03
 * @description: 枚举类型测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class EnumTest {

    public enum SexEnum {
        /**
         * 001.实际是调用构造函数，如果有有参构造函数时，需要调用缺省构造函数时，
         * 则需要显示定义缺省构造函数。
         */
        MALE("男性", 1),
        FEMAIL("女性", 2),
        UNKNOWN();

        private String sexStr;
        private int sexIndex;

        /** 002.枚举类型构造函数只允许使用缺省或private修饰*/
        private SexEnum() {
        }

        SexEnum(String sexStr, int sexIndex) {
            this.sexStr = sexStr;
            this.sexIndex = sexIndex;
        }

        public static SexEnum forIndex(int index){
            for(SexEnum sexEnum: values()){
                if(sexEnum.sexIndex==index)
                    return sexEnum;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // UNKNOWN
        System.out.println(SexEnum.forIndex(0));
        // MALE
        System.out.println(SexEnum.forIndex(1));
        // FEMAIL
        System.out.println(SexEnum.forIndex(2));
        // null
        System.out.println(SexEnum.forIndex(3));
    }
}
