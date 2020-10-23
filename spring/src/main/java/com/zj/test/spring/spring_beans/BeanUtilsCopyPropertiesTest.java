package com.zj.test.spring.spring_beans;

import com.zj.test.util.TestHelper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/10/20 10:09
 * @description: spring-beans.jar BeanUtils工具测试
 * @version: 1.0
 * @finished: true
 * @finished-time: 2020年10月20日 14:52:19
 */
public class BeanUtilsCopyPropertiesTest {

    @Setter
    @Getter
    class BeanUtilsTestDTO{
        String username;
        String password;
        Integer age;
        String des;
    }

    @Setter
    @Getter
    static class BeanUtilsTestPO {
        String username;
        String password;

        @Override
        public String toString() {
            return "BeanUtilsTestPO{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @Setter
    @Getter
    public static class BeanUtilsTestEditablePO extends  BeanUtilsTestPO{
        String username;
    }

    /**
     * 1.copyProperties()测试
     *
     * <p>
     * 使用注意点：
     * <ul>
     *     <li>1.只能复制同名、且类型相同的属性。</li>
     * </ul>
     * </p>
     * */
    @Test
    public void copyProperties(){
        String username="zhoujian";
        BeanUtilsTestDTO beanUtilsTestDTO = new BeanUtilsTestDTO();
        beanUtilsTestDTO.setUsername(username);
        beanUtilsTestDTO.setPassword("123456");
        beanUtilsTestDTO.setAge(25);
        beanUtilsTestDTO.setDes("这是一个相当聪明的人");

        /**
         * 001.copyProperties(source,target)
         * 作用：将source中的属性值，复制给target中的同名属性。
         *
         * 注意：
         * 1.source、target为对象。
         * */
        TestHelper.startTest("BeanUtils.copyProperties(source,target)");
        BeanUtilsTestPO beanUtilsTestPO = new BeanUtilsTestPO();
        BeanUtils.copyProperties(beanUtilsTestDTO,beanUtilsTestPO);
        Assert.assertEquals(username,beanUtilsTestPO.getUsername());
        TestHelper.finishTest();

        /**
         * 002.copyProperties(source,target,editable)
         *
         * 作用：
         * 1.取target.class和editable.class类的属性交集。
         * 2.将source属性赋值给1中的同名属性，就是最终的结果
         *
         * 注意：editable类必须为target类的父类(包括Object.class)，或者相同类.
         *
         * */
        TestHelper.startTest("BeanUtils.copyProperties(source,target,editable)");
        BeanUtilsTestPO beanUtilsTestPO1 = new BeanUtilsTestPO();
        // java.lang.IllegalArgumentException: Target class [com.zj.test.spring.spring_beans.BeanUtilsTest$BeanUtilsTestPO] not assignable to Editable class [com.zj.test.spring.spring_beans.BeanUtilsTest$BeanUtilsTestEditablePO]
        //BeanUtils.copyProperties(beanUtilsTestDTO,beanUtilsTestPO1,BeanUtilsTestEditablePO.class);
        BeanUtils.copyProperties(beanUtilsTestDTO,beanUtilsTestPO1,BeanUtilsTestPO.class);
        TestHelper.println("复制得到的对象: " + beanUtilsTestPO1);
        TestHelper.finishTest();

        /**
         * 003.copyProperties(Object source, Object target, String... ignoreProperties)
         * 将source属性复制给target的同名属性，ignoreProperties可以指定忽略的属性
         *
         * 注意:
         * 1.ignoreProperties属性名需要和类中属性名大小写一致，否则不会被忽略。
         *
         * 例：忽略password字段。
         * */
        TestHelper.startTest("copyProperties(Object source, Object target, String... ignoreProperties)");
        BeanUtilsTestPO beanUtilsTestPO2 = new BeanUtilsTestPO();
        BeanUtils.copyProperties(beanUtilsTestDTO,beanUtilsTestPO2,"password");
        TestHelper.println("忽略password字段，复制的结果: " + beanUtilsTestPO2);
        TestHelper.finishTest();

        /** 004.测试copyProperties()是否自动转换类型
         *
         * 结果: 不会自动类型转换，必须属性名 + 类型相同才会复制属性。
         * */
        TestHelper.startTest("copryProperties自动转换测试");

        TypeConvDTO typeConvDTO = new TypeConvDTO();
        typeConvDTO.setInt_2_Boolean(1);
        typeConvDTO.setInt_2_boolean(1);
        typeConvDTO.setInt_2_Double(1);
        typeConvDTO.setInt_2_double(1);
        typeConvDTO.setInt_2_Float(1);
        typeConvDTO.setInt_2_float(1);
        typeConvDTO.setInteger_2_Double(1);
        typeConvDTO.setInteger_2_double(1);
        typeConvDTO.setInteger_2_Float(1);
        typeConvDTO.setInteger_2_float(1);
        // 相同类型的属性
        typeConvDTO.setInt_same(1);
        typeConvDTO.setFloat_same(1);
        typeConvDTO.setBoolean_same(true);
        TestHelper.println("被复制的对象",typeConvDTO);
        TypeConvPO typeConvPO = new TypeConvPO();
        BeanUtils.copyProperties(typeConvDTO,typeConvPO);
        TestHelper.println("复制得到的对象: " + typeConvPO);
        TestHelper.finishTest();
    }

    static class TypeConvDTO {
        int int_2_float;
        int int_2_double;

        int int_2_Float;
        int int_2_Double;

        int Integer_2_float;
        int Integer_2_double;

        int Integer_2_Float;
        int Integer_2_Double;

        int int_2_boolean;
        int int_2_Boolean;

        int int_same;

        float float_same;

        boolean boolean_same;

        public int getInt_same() {
            return int_same;
        }

        public void setInt_same(int int_same) {
            this.int_same = int_same;
        }

        public float getFloat_same() {
            return float_same;
        }

        public void setFloat_same(float float_same) {
            this.float_same = float_same;
        }

        public boolean getBoolean_same() {
            return boolean_same;
        }

        public void setBoolean_same(boolean boolean_same) {
            this.boolean_same = boolean_same;
        }

        @Override
        public String toString() {
            return "TypeConvPO{" +
                    "int_2_float=" + int_2_float +
                    ", int_2_double=" + int_2_double +
                    ", int_2_Float=" + int_2_Float +
                    ", int_2_Double=" + int_2_Double +
                    ", Integer_2_float=" + Integer_2_float +
                    ", Integer_2_double=" + Integer_2_double +
                    ", Integer_2_Float=" + Integer_2_Float +
                    ", Integer_2_Double=" + Integer_2_Double +
                    ", int_2_boolean=" + int_2_boolean +
                    ", int_2_Boolean=" + int_2_Boolean +
                    ", int_same=" + int_same +
                    ", float_same=" + float_same +
                    ", boolean_same=" + boolean_same +
                    '}';
        }

        public int getInt_2_float() {
            return int_2_float;
        }

        public void setInt_2_float(int int_2_float) {
            this.int_2_float = int_2_float;
        }

        public int getInt_2_double() {
            return int_2_double;
        }

        public void setInt_2_double(int int_2_double) {
            this.int_2_double = int_2_double;
        }

        public int getInt_2_Float() {
            return int_2_Float;
        }

        public void setInt_2_Float(int int_2_Float) {
            this.int_2_Float = int_2_Float;
        }

        public int getInt_2_Double() {
            return int_2_Double;
        }

        public void setInt_2_Double(int int_2_Double) {
            this.int_2_Double = int_2_Double;
        }

        public int getInteger_2_float() {
            return Integer_2_float;
        }

        public void setInteger_2_float(int integer_2_float) {
            Integer_2_float = integer_2_float;
        }

        public int getInteger_2_double() {
            return Integer_2_double;
        }

        public void setInteger_2_double(int integer_2_double) {
            Integer_2_double = integer_2_double;
        }

        public int getInteger_2_Float() {
            return Integer_2_Float;
        }

        public void setInteger_2_Float(int integer_2_Float) {
            Integer_2_Float = integer_2_Float;
        }

        public int getInteger_2_Double() {
            return Integer_2_Double;
        }

        public void setInteger_2_Double(int integer_2_Double) {
            Integer_2_Double = integer_2_Double;
        }

        public int getInt_2_boolean() {
            return int_2_boolean;
        }

        public void setInt_2_boolean(int int_2_boolean) {
            this.int_2_boolean = int_2_boolean;
        }

        public int getInt_2_Boolean() {
            return int_2_Boolean;
        }

        public void setInt_2_Boolean(int int_2_Boolean) {
            this.int_2_Boolean = int_2_Boolean;
        }
    }

    class TypeConvPO {
        float int_2_float;
        double int_2_double;

        Float int_2_Float;
        Double int_2_Double;

        float Integer_2_float;
        double Integer_2_double;

        Float Integer_2_Float;
        Double Integer_2_Double;

        boolean int_2_boolean;
        Boolean int_2_Boolean;

        int int_same;

        float float_same;

        boolean boolean_same;

        public int getInt_same() {
            return int_same;
        }

        public void setInt_same(int int_same) {
            this.int_same = int_same;
        }

        public float getFloat_same() {
            return float_same;
        }

        public void setFloat_same(float float_same) {
            this.float_same = float_same;
        }

        public boolean getBoolean_same() {
            return boolean_same;
        }

        public void setBoolean_same(boolean boolean_same) {
            this.boolean_same = boolean_same;
        }

        @Override
        public String toString() {
            return "TypeConvPO{" +
                    "int_2_float=" + int_2_float +
                    ", int_2_double=" + int_2_double +
                    ", int_2_Float=" + int_2_Float +
                    ", int_2_Double=" + int_2_Double +
                    ", Integer_2_float=" + Integer_2_float +
                    ", Integer_2_double=" + Integer_2_double +
                    ", Integer_2_Float=" + Integer_2_Float +
                    ", Integer_2_Double=" + Integer_2_Double +
                    ", int_2_boolean=" + int_2_boolean +
                    ", int_2_Boolean=" + int_2_Boolean +
                    ", int_same=" + int_same +
                    ", float_same=" + float_same +
                    ", boolean_same=" + boolean_same +
                    '}';
        }

        public float getInt_2_float() {
            return int_2_float;
        }

        public void setInt_2_float(float int_2_float) {
            this.int_2_float = int_2_float;
        }

        public double getInt_2_double() {
            return int_2_double;
        }

        public void setInt_2_double(double int_2_double) {
            this.int_2_double = int_2_double;
        }

        public Float getInt_2_Float() {
            return int_2_Float;
        }

        public void setInt_2_Float(Float int_2_Float) {
            this.int_2_Float = int_2_Float;
        }

        public Double getInt_2_Double() {
            return int_2_Double;
        }

        public void setInt_2_Double(Double int_2_Double) {
            this.int_2_Double = int_2_Double;
        }

        public float getInteger_2_float() {
            return Integer_2_float;
        }

        public void setInteger_2_float(float integer_2_float) {
            Integer_2_float = integer_2_float;
        }

        public double getInteger_2_double() {
            return Integer_2_double;
        }

        public void setInteger_2_double(double integer_2_double) {
            Integer_2_double = integer_2_double;
        }

        public Float getInteger_2_Float() {
            return Integer_2_Float;
        }

        public void setInteger_2_Float(Float integer_2_Float) {
            Integer_2_Float = integer_2_Float;
        }

        public Double getInteger_2_Double() {
            return Integer_2_Double;
        }

        public void setInteger_2_Double(Double integer_2_Double) {
            Integer_2_Double = integer_2_Double;
        }

        public boolean isInt_2_boolean() {
            return int_2_boolean;
        }

        public void setInt_2_boolean(boolean int_2_boolean) {
            this.int_2_boolean = int_2_boolean;
        }

        public Boolean getInt_2_Boolean() {
            return int_2_Boolean;
        }

        public void setInt_2_Boolean(Boolean int_2_Boolean) {
            this.int_2_Boolean = int_2_Boolean;
        }
    }
}