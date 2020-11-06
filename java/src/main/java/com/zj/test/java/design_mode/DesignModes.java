package com.zj.test.java.design_mode;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/6 14:29
 * @description: 常用设计模式
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class DesignModes {

    /**
     * 1.抽象工厂模式
     *          Fruit                          FruitFactory(getApple,getBanana)
     *      ↓           ↓                        ↓                        ↓
     *Apple             Banana       ChineseFruitFactory                 AmericaFruitFactory
     *
     * 优缺点: 便于横向拓展，纵向拓展困难。
     *
     * 工厂： 不同的工厂实现类用于获取继承树上具体实现类对象。
     */
    public void test1(){

    }
}
