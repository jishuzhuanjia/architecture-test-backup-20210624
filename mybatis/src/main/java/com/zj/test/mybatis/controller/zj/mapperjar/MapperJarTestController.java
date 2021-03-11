package com.zj.test.mybatis.controller.zj.mapperjar;

import com.zj.test.util.TestResultTips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zj.test.mybatis.mapper.zj.Test005Mapper;
import com.zj.test.mybatis.po.zj.UserPO;
import com.zj.test.mybatis.util.zj.TestHelper;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/* @author: zhoujian
 * @create-time: 2020/9/18 17:29
 * @description: mapper.jar crud测试
 * @version: 1.0
 * @finish-time: 2020年9月21日 15:18:03
 */
/*-----------------------------mapper.jar使用demo-------------------------------------*/
@RestController
@RequestMapping("test/mybatis/mapperjar")
public class MapperJarTestController {
    @Autowired
    Test005Mapper mapper;

    /*1.增
     *
     * 向user表插入数据
     * */

    @RequestMapping("test1")
    public String test1() {
        TestHelper.startTest("mapper.jar insert测试");
        TestHelper.printSubTitle("insert(po)测试");
        UserPO insertUser = new UserPO();
        insertUser.setName("testUser");
        /*1.1.insert(po): 将po对象的值插入到数据库表中，包括那些值为null的属性。
        使用注意点：
        如果表中对应字段不能为空，则PO类中的属性不能为空，否则会报错：
        Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'password' cannot be null
        如果表中字段可以为空，则PO类属性是否为空都可以。
        */

        // 1.2.如果表中字段可为null，可以不进行赋值。这样插入Null值。
        //insertUser.setPassword("testPassword");
        mapper.insert(insertUser);

        // 1.3.insertSelective使用
        // 使用场景：如果某些字段有默认值，我们希望插入默认值，而不是被null覆盖，则使用insertSelective插入数据
        TestHelper.printSubTitle("mapper.insertSelective()测试");
        UserPO insertUserPO = new UserPO();
        insertUserPO.setPassword("123456");
        TestHelper.println("插入数据条数: " + mapper.insertSelective(insertUserPO));

        //1.4.mapper.insertList
        // 每条数据的插入相当于insert(po)，会覆盖字段默认值，null值也会被插入。
        TestHelper.printSubTitle("mapper.insertList测试");
        List<UserPO> insertList = new ArrayList<>();
        UserPO insertUser1, insertUser2, insertUser3;
        insertUser1 = new UserPO();
        insertUser1.setPassword("111");
        insertUser2 = new UserPO();
        insertUser2.setPassword("111");
        insertUser3 = new UserPO();
        insertUser3.setPassword("111");
        insertList.add(insertUser1);
        insertList.add(insertUser2);
        insertList.add(insertUser3);
        mapper.insertList(insertList);

        //作用未知，暂不讨论
        /*TestHelper.printSubTitle("mapper.insertUseGenerateKeys");
        UserPO inertUser4 = new UserPO();
        inertUser4.setPassword("123456");
        TestHelper.println("插入条数: " ,mapper.insertUseGeneratedKeys(inertUser4));*/
        TestHelper.finishTest();

        return TestResultTips.SEE_AT_DATABASE;
    }

    // 2.删除：删除user表中的数据
    @RequestMapping("test2")
    public String test2() {
        TestHelper.startTest("mapper.jar删除测试");
        // delete(po): 将po中的所有字段组合成where ... and...and...语句，进行多条件删除。
        // 2.1.delete(po)根据主键删除记录 - delete根据主键删除某条数据。
        UserPO deleteUser1 = new UserPO();
        //如果只有主键属性，则相当于根据主键删除记录。
        deleteUser1.setId(10);
        //deleteUser1.setPassword("111");
        // 2.1.1.delete(po)返回类型为int，为此次删除的行数，如果没有删除，则返回0.
        int affectRows1 = mapper.delete(deleteUser1);
        TestHelper.println("mapper.delete(): 根据主键删除记录数: " + affectRows1);

        /*2.2.delete(po)根据某个字段删除记录 - 可以批量删除
         *传递的PO对象的属性字段会作为条件进行删除
         * 下面的测试就相当于:
         * delete from user where username="333"
         * 因此delete方法可以实现批量删除。
         *
         *组合字段删除：下面的代码就相当于delete from user where username="333" and password="110"
         * Test005User deleteUser2 = new Test005User();
         * deleteUser2.setName("333");
         * deleteUser2.setPassword("110");
         * int affectRows2 = mapper.delete(deleteUser2);
         * 也可以实现批量删除。
         * */
        UserPO deleteUser2 = new UserPO();
        deleteUser2.setName("333");
        int affectRows2 = mapper.delete(deleteUser2);
        TestHelper.println("mapper.delete(): 根据非主键字段删除记录数: " + affectRows2);

        /*2.3.deleteByExample的使用*/

        Example example = new Example(UserPO.class);
        // 删除username==username且password=111的记录
        example.createCriteria().andEqualTo("name", "testUser")
                .andEqualTo("password", "111");
        int affectRows3 = mapper.deleteByExample(example);
        // deleteByExample(example): 根据条件删除记录数: 3
        TestHelper.println("deleteByExample(example): 根据条件删除记录数: " + affectRows3);

        /*2.4.deleteByPrimaryKey的使用
         * 可以从传递的参数类型:
         * 1.int/Integer,需要传递主键值。
         * 2.PO类型,需要对@Id属性进行赋值,会作为deleteByPrimaryKey的主键条件。
         * */
        /*Integer deletePirmaryKey = 12;
        int affectRows4 = mapper.deleteByPrimaryKey(deletePirmaryKey);*/
        UserPO deletePirmaryKeyUser = new UserPO();
        deletePirmaryKeyUser.setId(11);
        int affectRows4 = mapper.deleteByPrimaryKey(deletePirmaryKeyUser);
        TestHelper.println("deleteByPrimaryKey(): 根据主键删除记录数: " + affectRows4);

        TestHelper.finishTest();
        return TestResultTips.SEE_AT_DATABASE;
    }

    /*3.改(更新)
     *updateByExample(po,object): 根据Example查询符合条件的记录，并将po的属性值更新到表中
     *参数:
     *  po: 用来更新值的po对象
     *  object: Example对象
     *注意:
     *  1.空值null也会被插入到表中，这意味着Null也会覆盖表字段原来的值
     *  2.不管po是否设置了主键字段，主键字段都不会被更新到表中,会被忽略
     *
     *updateByExampleSelective: 与updateByExample不同的是: null属性不会被更新到表中
     *
     *updateByPrimaryKey(po): 根据主键更新，null值会被插入表
     *updateByPrimaryKeySelective(): 根据主键更新，null值不会被插入表
     *
     * 2020年9月21日 22:30:01
     * */
    @RequestMapping("test3")
    public String test3() {
        // 3.1.updateByExample(po,object) 2020年9月21日 13:58:49
        // po: po对象，其属性值将会被更新到表中字段。
        // object: 带有查询条件的Example对象
        // 会忽略对主键(@Id字段)的更新,因此对主键字段进行赋值是多余的操作。
        // po对象中的表属性都会被写入到表中，即使是null，会覆盖表中已有的数据。

        TestHelper.startTest("mapper.jar: updateByExample的使用");
        // 设置where条件
        Example example = new Example(UserPO.class);
        example.createCriteria()
                .andEqualTo("name", "zhoujian")
                .andEqualTo("password", "123456")
        /*.andEqualTo("id",83)*/;

        UserPO updatePO = new UserPO();
        updatePO.setName("myname");
        updatePO.setPassword("4567890");

        // updateByExample: 设置主键字段是多余的，主键不会被更新。
        updatePO.setId(88);
        int affectRows1 = mapper.updateByExample(updatePO, example);
        TestHelper.println("修改的行数: " + affectRows1);
        TestHelper.finishTest();

        // 3.2.mapper.updateByExampleSelective()测试 2020年9月21日 13:58:40
        // 如果为po对象中的表属性为null，不会更新到表中。
        TestHelper.startTest("mapper.updateByExampleSelective()测试");
        Example example2 = new Example(UserPO.class);
        example2.createCriteria().andEqualTo("name", "zhoujian")
                .andEqualTo("111");
        TestHelper.println("修改的行数", mapper.updateByExampleSelective(updatePO, example2));
        TestHelper.finishTest();

        //  3.3.updateByPrimaryKey
        // mapper.updateByPrimaryKey();
        // 略
        return TestResultTips.SEE_AT_DATABASE;
    }

    /*4.查
     *查询的方法较多，请注意比较
     *
     *List<T> select(T): 会将非空属性作为查询的条件,如果只将主键字段作为条件,相当于
     *   selectByPrimaryKey(Object)
     *
     *T selectByPrimaryKey(Object o): 根据主键查询返回记录,其他的字段不会作为查询的条件
     *
     *List<T> selctAll(): 返回表中所有的记录
     *List<T> mapper.selectByExample(Object): 根据example条件查询并返回符合条件的记录
     *
     * 2020年9月21日 22:21:25
     * */

    @RequestMapping("test4")
    public String test4() {

        TestHelper.startTest("mapper.jar select(po) 查询测试");
        UserPO selectPO = new UserPO();
        selectPO.setName("myname");
        selectPO.setAge(14);
        // 4.1.select(po): 会将po对象中的属性值作为查询的条件
        // 返回符合条件的PO列表
        // 如果只设置主键属性，则相当于mapper.selectByPrimaryKey(object)
        TestHelper.println("select-1查询到数据: " + mapper.select(selectPO).size() + "条");
        UserPO selectPoByPrimaryKey = new UserPO();
        selectPoByPrimaryKey.setId(88);
        TestHelper.println("select-2查询到数据: " + mapper.select(selectPoByPrimaryKey).size() + "条");
        TestHelper.finishTest();

        /*4.2.List<T> selctAll(): 返回表中所有的记录。
        2020年9月21日 14:17:55
        */
        TestHelper.startTest("mapper.jar selctAll测试");
        // 可以使用PageHelper进行分页
        // 返回第一页,3条数据
        // PageHelper.startPage(1,3);
        TestHelper.println("selectAll-1查询到的数据数: " + mapper.selectAll().size());
        TestHelper.finishTest();

        //4.3.mapper.selectByExample()
        // 略

        //4.4.T mapper.selectByPrimaryKey(Object)
        // 001.传递参数类型可以为int/Integer
        TestHelper.startTest("mapper.jar selectByPrimaryKey(Object)测试");
        //传递参数类型为int
        TestHelper.printSubTitle("传递参数类型为int");
        UserPO userPO = mapper.selectByPrimaryKey(84);
        // 查询到的值: UserPO{id=84, name='myname', password='4567890', age=null}
        TestHelper.println("查询到的值: " + userPO);

        // 传递参数类型为Integer
        TestHelper.printSubTitle("传递参数类型为Integer");
        UserPO userPO2 = mapper.selectByPrimaryKey(Integer.valueOf(83));
        // 查询到的值: UserPO{id=84, name='myname', password='4567890', age=null}
        TestHelper.println("查询到的值: " + userPO2);

        // 002.传递参数类型还可以为PO类
        TestHelper.printSubTitle("传递参数类型为PO类");
        UserPO selectPO2 = new UserPO();
        selectPO2.setId(90);

        // 003.只有id字段有效，其他字段不会被添加到查询条件。
        //此时设置其他的属性无效
        selectPO2.setName("xasdadaw2qe232r12");
        TestHelper.println("查询到的值: " + mapper.selectByPrimaryKey(selectPO2));
        TestHelper.finishTest();
        return TestResultTips.SEE_AT_DATABASE;
    }

    /*5.mapper.jar计数方法

     *selectCount(PO): 每个非空字段会作为条件,查询符合条件的记录条数
     *selectCountByExample(): 根据Example条件查询符合条件的记录条数
     *
     *selectOne(po): 查询符合条件的至多唯一一条数据
     * */
    @RequestMapping("test5")
    public String tesqt5() {
        TestHelper.startTest("mapper.jar 计数函数测试");

        //5.1.selectCount(PO): 每个非空字段会作为条件,查询符合条件的记录条数
        //PO作为查询的条件，每个字段都作为一个条件
        //主键也可以作为条件
        //可以有一个或多个条件
        TestHelper.printSubTitle("查询一个条件:");
        UserPO selectCountPO = new UserPO();
        //selectCountPO.setName("myname");
        selectCountPO.setId(88);
        //条数: 4
        TestHelper.println("条数: " + mapper.selectCount(selectCountPO));

        //5.2.selectCountByExample()
        // 略

        /*5.3.selectOne: 查询满足条件的至多一个记录,如果没有满足的记录，返回null
        001.注: 查询的结果不能超过一个,否则报错:
        org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 4

        002.使用场景：用在查询结果只有一条的场景，如通过主键或者多主键查询，要保证结果只有一条数据。*/
        TestHelper.printSubTitle("selectOne测试: ");
        UserPO selectOne = new UserPO();
        selectOne.setName("myname");

        //将主键作为查询的条件，可以保证查询的结果只有一条，不会抛出异常
        //selectOne.setId(88);
        TestHelper.println("查询结果: " + mapper.selectOne(selectOne));

        TestHelper.finishTest();
        // mapper.selectCountByExample()

        return TestResultTips.SEE_AT_DATABASE;
    }
}