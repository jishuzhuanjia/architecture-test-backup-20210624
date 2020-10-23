package com.zj.test.spring.spring_beans;

import com.zj.test.util.TestHelper;
import org.springframework.beans.BeanUtils;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/21 17:37
 * @description: copyProperties属性复制规律探究
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月21日 20:15:54
 */
public class BeanUtilsCopyPropertiesTest2 {

    // 被复制类
    public static class A_1 {
        int a;
        Integer b;
        int c;
        Integer d;
        Integer e;
        int f;
        boolean g;
        short h;
        Short i;

        Short j;
        short k;

        long l;

        int m;
        Integer n;

        public int getA() {
            return a;
        }

        public Integer getB() {
            return b;
        }

        public int getC() {
            return c;
        }

        public Integer getD() {
            return d;
        }

        public Integer getE() {
            return e;
        }

        public int getF() {
            return f;
        }

        public boolean isG() {
            return g;
        }

        public short getH() {
            return h;
        }

        public Short getI() {
            return i;
        }

        public Short getJ() {
            return j;
        }

        public short getK() {
            return k;
        }

        public long getL() {
            return l;
        }

        public int getM() {
            return m;
        }

        public Integer getN() {
            return n;
        }

        @Override
        public String toString() {
            return "A_1{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    ", f=" + f +
                    ", g=" + g +
                    ", h=" + h +
                    ", i=" + i +
                    ", j=" + j +
                    ", k=" + k +
                    ", l=" + l +
                    ", m=" + m +
                    ", n=" + n +
                    '}';
        }
    }

    /**
     * 1.属性注意需要注意的点
     * copyProperties不是通过属性反射直接赋值，而是通过target setter设置
     * 获取属性值的时候是通过source中的getter
     * setter和getter缺一就会导致复制失败
     * source必须有getter,target必须有setter，否则属性复制不上。
     */
    // 复制到的类
    static class A_2 {
        int a;
        Integer b;
        Integer c;
        int d;
        boolean e;
        boolean f;
        int g;
        int h;
        int i;

        short j;
        Short k;
        Long l;
        Long m;
        Integer n;

        public void setA(int a) {
            this.a = a;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        public void setC(Integer c) {
            this.c = c;
        }

        public void setD(int d) {
            this.d = d;
        }

        public void setE(boolean e) {
            this.e = e;
        }

        public void setF(boolean f) {
            this.f = f;
        }

        public void setG(int g) {
            this.g = g;
        }

        public void setH(int h) {
            this.h = h;
        }

        public void setI(int i) {
            this.i = i;
        }

        public void setJ(short j) {
            this.j = j;
        }

        public void setK(Short k) {
            this.k = k;
        }

        public void setL(Long l) {
            this.l = l;
        }

        public void setM(Long m) {
            this.m = m;
        }

        public void setN(Integer n) {
            this.n = n;
        }

        @Override
        public String toString() {
            return "A_2{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    ", f=" + f +
                    ", g=" + g +
                    ", h=" + h +
                    ", i=" + i +
                    ", j=" + j +
                    ", k=" + k +
                    ", l=" + l +
                    ", m=" + m +
                    ", n=" + n +
                    '}';
        }
    }

    /**
     * 2.属性值复制的条件:
     * 需要属性名相同 + 同类型(包装类和简单类视为同一类型)
     *
     * <p>
     * int <-> Integer
     * <p>
     * short 不能转 int
     * Short 不能转 int
     * <p>
     * int/Integer 不能转 boolean
     * <p>
     * 注意包装类转换到简单类型要防止null，否则：
     * Exception in thread "main" org.springframework.beans.FatalBeanException: Could not copy property 'j' from source to target; nested exception is java.lang.IllegalArgumentException
     */

    /**
     * 3.满足复制条件的情况下, source中的null值可以覆盖target中的非null值。
     */

    public static void main(String[] args) {
        TestHelper.startTest("BeanUtils.copyProperties复制测试");

        // 创建被复制对象
        A_1 a_1 = new A_1();
        a_1.a = 1;
        a_1.b = 1;
        a_1.c = 1;
        a_1.d = 1;
        a_1.e = 1;
        a_1.f = 1;
        a_1.g = true;
        a_1.h = 1;
        a_1.i = 1;
        a_1.j = 1;
        a_1.k = 1;
        a_1.l = 1;
        a_1.m = 1;
        a_1.n = null;
        TestHelper.println("被复制的对象",a_1);

        // 创建复制到的对象
        A_2 a_2 = new A_2();
        a_2.n = 1;
        BeanUtils.copyProperties(a_1, a_2);
        TestHelper.println("复制属性后的对象",a_2);
        TestHelper.finishTest();
    }
}