package com.zj.test.java.collection;

import com.zj.test.util.TestHelper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* @author: zhoujian
 * @create-time: 2020/9/23 16:00
 * @description: List.stream()使用demo,只列举常用的
 * @version: 1.0
 */
public class CollectionStreamTest {

    // 测试PO
    @Setter
    @Getter
    class ListStreamTestUserPO {
        String username;
        String password;
        int age;

        @Override
        public String toString() {
            return "ListStreamTestUserPO{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            //001.保证对象为同一类型的一种实现
            if (o == null || getClass() != o.getClass()) return false;
            ListStreamTestUserPO that = (ListStreamTestUserPO) o;
            return age == that.age &&
                    /*002.Objects.euqls可用来进行对象比较
                    注意：null==null为true*/
                    Objects.equals(username, that.username) &&
                    Objects.equals(password, that.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, password, age);
        }
    }

    /**
     * 1.list.stream().map()测试：
     * 从已有list元素中提取某个属性，返回由该属性组成的List
     */
    @Test
    public void test1() {
        TestHelper.startTest("list.stream().map()测试");
        List<ListStreamTestUserPO> testList = new ArrayList<>();

        // 添加10条数据用以测试
        for (int i = 1; i <= 10; i++) {
            ListStreamTestUserPO insertPO = new ListStreamTestUserPO();
            insertPO.setUsername("username" + i);
            insertPO.setPassword("password" + i);
            insertPO.setAge(i);
            testList.add(insertPO);
        }
        TestHelper.println("测试List插入数据条数: " + testList.size());
        TestHelper.println("测试List: " + testList);
        TestHelper.println("usernameList", testList.stream().map(i -> i.getUsername()).collect(Collectors.toList()));
        TestHelper.println("password", testList.stream().map(i -> i.getPassword()).collect(Collectors.toList()));
        // 001.一个参数时,i的括号可以省略
        // 如果只有一条有返回值的语句，则可以不加{return  ;},如果是多条语句需要加{return  ;}
        TestHelper.println("ageList", testList.stream().map((i) -> {
            return i.getAge();
        }).collect(Collectors.toList()));
        TestHelper.printSubTitle("检查原来的测试list是否被修改:");
        // 002.从测试的结果可以看出，stream()不会修改原来的list内容。
        TestHelper.println("现在的测试list: " + testList);

        TestHelper.finishTest();
    }

    /*创建测试list
     *@param size 需要创建的list元素个数*/
    private List<ListStreamTestUserPO> createTestList(int size) {
        List<ListStreamTestUserPO> testList = new ArrayList<>(size);
        // 添加10条数据用以测试
        for (int i = 1; i <= size; i++) {
            ListStreamTestUserPO insertPO = new ListStreamTestUserPO();
            insertPO.setUsername("username" + i);
            insertPO.setPassword("password" + i);
            insertPO.setAge(i);
            testList.add(insertPO);
        }

        return testList;
    }

    /**
     * 2.list.stream().filter()测试：
     * 作用：用来从已有的List中过滤元素。
     * <p>
     * demo: 返回年龄大于等于18的用户。
     */
    @Test
    public void test2() {
        List<ListStreamTestUserPO> testList = createTestList(50);
        TestHelper.startTest("list.stream().filter()测试");
        TestHelper.println("原来的list: ", testList);
        List<ListStreamTestUserPO> filterList = testList.stream().filter(o -> {
            //  {}中必须有return语句
            /*001.true,则该元素符合要求，会被添加到结果List中*/
            if (o.getAge() >= 18)
                return true;
            return false;
        }).collect(Collectors.toList());
        TestHelper.println("过滤得到的List内容", filterList);
        TestHelper.finishTest();
    }

    /**
     * 3.list.stream().distinct()测试：
     * 作用：用来对已有的list进行去重操作
     * <p>
     * 注意：元素类需要实现equals（）和hascode()
     */
    @Test
    public void test3() {
        TestHelper.printSubTitle("list.stream().distinct()测试");
        List<ListStreamTestUserPO> testList = new ArrayList<>(5);
        ListStreamTestUserPO po1 = new ListStreamTestUserPO();
        po1.setUsername("zhou");
        ListStreamTestUserPO po2 = new ListStreamTestUserPO();
        po2.setUsername("zhou");
        ListStreamTestUserPO po3 = new ListStreamTestUserPO();
        po3.setUsername("zhou");
        ListStreamTestUserPO po4 = new ListStreamTestUserPO();
        po4.setUsername("li");
        ListStreamTestUserPO po5 = new ListStreamTestUserPO();
        po5.setUsername("li");

        testList.add(po1);
        testList.add(po2);
        testList.add(po3);
        testList.add(po4);
        testList.add(po5);
        // distinct后的结果: [ListStreamTestUserPO{username='zhou', password='null', age=0}, ListStreamTestUserPO{username='li', password='null', age=0}]
        /*001.请注意调用方式*/
        TestHelper.println("distinct后的结果", testList.stream().distinct().collect(Collectors.toList()));
    }
}
