package com.zj.test.java.reflect.field;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 11:32
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public interface ReflectTestInterface{

    // 接口中定义的属性会被添加final关键字，因此需要初始化
    // 接口中不允许private修饰属性
    public int interfacePublicInt = 0;

    public static int interfacePublicStaticInt = 0;
}
