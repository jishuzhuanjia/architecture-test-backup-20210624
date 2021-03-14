package com.mybatis.mapperjar;

import com.mybatis.TestApplication;
import com.zj.test.util.TestHelper;
import com.zj.test.util.TestResultTips;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mybatis.mapper.zj.MappejarMapper;
import com.mybatis.UserPO;
import tk.mybatis.mapper.entity.Example;

/* @author: zhoujian
 * @create-time: 2020/9/18 17:29
 * @description: mapper.jar crud测试
 * @version: 1.0
 * @finish-time: 2020年9月21日 15:18:03
 */
/*-----------------------------mapper.jar使用demo-------------------------------------*/
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class UnitTest {
    @Autowired
    MappejarMapper mapper;

    /**
     * 1.增(insert)
     * 测试：向user表插入数据
     *
     * 相关方法：
     * 1.int insert(T record)
     * 插入一条记录：会插入所有的字段，包括那些值为null值字段。
     * 注意：
     * 1.当插入null值时，需要保证数据库对应表字段是可NULL的，否则插入报错。
     * 2.如果指定主键会使用指定的主键，缺点是可能主键已存在，会导致插入报错，如果不指定，会使用表自增分配的主键，优点是不会主键重复。
     *-------------------------------
     * 2.int insertSelective(T record)
     * 插入一条记录：会插入所有非null字段，有多少个非null字段insert语句就有多少个列：
     * INSERT INTO user  ( username ) VALUES( ? )
     * 注意：对象中值为null的字段对应的表字段需要有默认值，否则插入报错,
     *
     * 对于1,2方法，表字段只要设置可为空,默认值NULL就可满足：
     * Navicat会为可为空的字段设置默认值NULL,
     * 手动ddl语句来创建时，需要手动添加DEFAULT NULL。
     * 养成设置默认值的好习惯，哪怕默认值是NULL。
     *-------------------------------
     * 3.int insertList(java.util.List<? extends T> recordList)
     * 批量插入，和insert(T)一样,会插入null值，因此限制和insert(T)相同
     * 最终SQL(包含所有非主键列),形如:
     * SQL: INSERT INTO user  ( username,password,age,last_login_time )  VALUES   ( ?,?,?,? ) , ( ?,?,?,? ) , ( ?,?,?,? )
     *-------------------------------
     * 4.int insertUseGeneratedKeys(T record)
     * 插入一条记录，和insert(T)不同的是: 会忽略指定的主键值。
     * 注意：
     * 1.和insert(T)一样，会插入null字段，和insert(T)一样的限制。
     *
     * */
    @Test
    public void insertTest() {
        /*
        1.1.insert(po): 将po对象的值插入到数据库表中，包括那些值为null的属性。
        使用注意点：
        1.经测试：insert(po)会将null值插入到数据库，因此只能用来插入那些字段可为null的字段：
        对于那些表字段不能为空的字段，要保证插入的对象对应字段不为null,
        如果表中对应字段不能为空，则PO类中的属性不能为空，否则会报错：
        Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'password' cannot be null
        如果表中字段可以为空，则PO类属性是否为空都可以。
        */
        /*TestHelper.printSubTitle("mapper.jar insert测试");
        TestHelper.startSubTest("insert(T)测试");
        UserPO insertUser = new UserPO();
        insertUser.setId(1234);
        insertUser.setName("user-insert(T)");
        // 表中该字段NOT NULL,必须赋值，否则报错：
        // Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'password' cannot be null
        insertUser.setPassword("123456");
        mapper.insert(insertUser);*/

        // 1.2.insertSelective使用: 只会插入非null的字段,最终插入语句只会包含非null字段。
        // 但是不被包含的字段应有默认值，否则会报错。
        /*
        需要注意的是只有有默认值的字段才可以不赋值。
        可为null的字段本身就有默认值null: `p` varchar(255) DEFAULT NULL
        NOT null字段则需要手动设置默认值,否则没有默认值：`p2` varchar(255) NOT NULL
         */

        // 使用场景：如果某些字段有默认值，我们希望插入默认值，而不是被null覆盖，则使用insertSelective插入数据
        // last_login_time有默认值CURRENT_TIMESTAMP
        /*TestHelper.startSubTest("mapper.insertSelective()测试");
        UserPO insertUserPO = new UserPO();
        insertUserPO.setName("user-mapperjar-insertSelective(T record)");
        // 此时password我们设置为not null
        //insertUserPO.setPassword("123456");

        // 最终SQL: INSERT INTO user  ( username ) VALUES( ? )
        mapper.insertSelective(insertUserPO);*/

        /*
        1.3.mapper.insertList
        每条数据的插入相当于insert(po)，和insert(po)限制相同：插入null值时，表字段需要是可NULL的。
        */
        /*TestHelper.printSubTitle("mapper.insertList测试");
        List<UserPO> insertList = new ArrayList<>();
        UserPO insertUser1, insertUser2, insertUser3;
        insertUser1 = new UserPO();
        insertUser1.setPassword("password-insertList1");
        insertUser2 = new UserPO();
        insertUser2.setPassword("password-insertList2");
        insertUser3 = new UserPO();
        insertUser3.setPassword("password-insertList3");
        insertList.add(insertUser1);
        insertList.add(insertUser2);
        insertList.add(insertUser3);
        mapper.insertList(insertList);*/

        /*
        1.4.insertUseGeneratedKeys(T record)
        和insert(T)一样，会插入null字段
        会忽略实体主键字段的值，使用表自增主键分配的值,换言之，不能手动设置主键值。
         */
        /*TestHelper.printSubTitle("mapper.insertUseGenerateKeys");
        UserPO inertUser4 = new UserPO();
        // 会被忽略
        inertUser4.setId(1314112);
        //inertUser4.setPassword("123456-insertUseGeneratedKeys");
        mapper.insertUseGeneratedKeys(inertUser4);*/
    }

    /**
     * 2.删除(delete)
     * 测试：删除user表中的数据
     *
     * 相关方法：
     * 2.1.int delete(T record)
     * 根据条件删除，可批量
     * 条件删除: 将po中的所有字段组合成where ... and...and...语句，进行多条件删除，因此可批量删除
     *
     * 返回值：为此次删除的行数，如果没有删除，则返回0
     *
     * 注意：
     * 1.如果只有主键属性，则相当于根据主键删除记录
     * 2.如果不设置任何字段的值，则会全量删除表中的数据，相当于truncate table，开发中需要格外注意
     *-----------------------------------------------------------------------------------
     *
     * 2.2.int deleteByExample(Object example)
     * 根据条件删除，可批量，不同的是可以设置排序
     * 注意：
     * 1.andEqualTo: property为DO类字段名，而不是表字段名
     * 2.主键字段也可以作为删除条件
     *-----------------------------------------------------------------------------------
     *
     * 2.3.int deleteByPrimaryKey(Object key)
     * 根据主键删除，经测试，可以传递的参数类型:
     * 1.int/Integer,需要传递主键值。
     * 2.PO类型,需要对@Id属性进行赋值,会作为deleteByPrimaryKey的主键条件。
     *
     */
    @Test
    public void deleteTest() {
        /**
         * 2.1.int delete(T record)
         * 条件删除: 将po中的所有字段组合成where ... and...and...语句，进行多条件删除，因此可批量删除
         *
         * 返回值：为此次删除的行数，如果没有删除，则返回0
         *
         * 注意：
         * 1.如果只有主键属性，则相当于根据主键删除记录
         * 2.如果不设置任何字段的值，则会全量删除表中的数据，相当于truncate table，开发中需要格外注意
         *
         */
        /*// 2.1.demo: 删除id为26，名为zhoujian的记录。
        UserPO deleteUser1 = new UserPO();
        // 如果不设置任何字段，则是表数据全量查询，开发中需要格外注意。
        deleteUser1.setId(27);
        deleteUser1.setName("zhoujian");
        TestHelper.println("mapper.delete(deleteUser1)删除记录数",mapper.delete(deleteUser1));*/

        /**
         * 2.2.int deleteByExample(Object example)
         *
         * 注意：
         * 1.andEqualTo: property为DO类字段名，而不是表字段名
         * 2.主键字段也可以作为删除条件
         */
        /*// 2.2.deleteByExample的使用
        Example example = new Example(UserPO.class);
        // 删除username=testUser且password=111的记录
        example.createCriteria().andEqualTo("username", "testUser")
                .andEqualTo("password", "111");
        TestHelper.println("deleteByExample(example): 根据条件删除记录数" , mapper.deleteByExample(example));*/

        /**
         * 2.3.int deleteByPrimaryKey(Object key)
         * 根据主键删除，经测试，可以传递的参数类型:
         * 1.int/Integer,需要传递主键值。
         * 2.PO类型,需要对@Id属性进行赋值,会作为deleteByPrimaryKey的主键条件。
         * */
        /*// 经测试: 主键可以是Integer类型
        Integer deletePirmaryKey = 1239;*/
        /*// 经测试: 主键可以是int类型
        int deletePirmaryKey = 1241;*/

        // 经测试：主键可以通过DO @Id字段传递
        /*UserPO userPO = new UserPO();
        userPO.setId(1242);
        TestHelper.println("deleteByPrimaryKey(): 根据主键删除记录数: " , mapper.deleteByPrimaryKey(userPO));*/
    }

    /**
     * 3.改(update)
     * 相关方法
     *
     * 3.1.updateByExample(po,object)
     * 根据条件批量更新数据
     *
     * 参数:
     * po: 用来更新值的po对象
     * object: Example对象
     *
     * 注意:
     * 1.updateByExample会将null值序列化到表中,因此表对应字段需要是可NULL的
     * 2.po对象的主键字段不会被更新到表中，因此为po对象赋值主键是多余的操作。
     *--------------------------------------------------------------
     *
     * 3.2.updateByExampleSelective
     * 根据条件批量更新数据
     *
     * 与updateByExample不同的是，只会更新po的非null字段到表中,并且也不会将id字段序列化到表中
     *--------------------------------------------------------------
     *
     * 3.3.updateByPrimaryKey(po): 根据主键更新，null值会被插入表
     *
     * 根据主键更新数据,需要注意的是也会将po中的null值更新到表中
     *
     * 注：
     * 1.po的id字段是需要更新数据的主键。
     *--------------------------------------------------------------
     *
     * 3.4.updateByPrimaryKeySelective(): 根据主键更新，null值不会被插入表
     *
     * 根据主键更新，与updateByPrimaryKey不同的是，只有po中的非null值才会被更新到表中
     *
     * 2020年9月21日 22:30:01
     * 2021年3月14日 10:55:43
     * */
    @Test
    public void updateTest() {
        /**
         3.1.updateByExample(po,object) 2020年9月21日 13:58:49
         条件更新

         po: 序列化到表的对象，其属性值将会被更新到表中字段
         object: 带有查询条件的Example对象

         注意:
         1.updateByExample会将null值序列化到表中,因此表对应字段需要是可NULL的
         2.po对象的主键字段不会被更新到表中，因此为po对象赋值主键是多余的操作。
         */
        // 设置where条件
        /*Example example = new Example(UserPO.class);
        example.createCriteria()
                .andEqualTo("username", "zhoujian");
        //.andEqualTo("id",1243);// 可以将主键字段作为条件

        // 更新po对象
        UserPO updatePO = new UserPO();
        updatePO.setPassword("password-updateByExample");
        updatePO.setId(88);// 设置主键字段是多余的，主键不会被更新到表
        TestHelper.println("updateByExample(updatePO, example)修改记录数",mapper.updateByExample(updatePO, example));*/

        /**
         * 3.2.int updateByExampleSelective(po,example)
         * 条件更新
         *
         * 与updateByExample不同的是，只会更新po的非null字段到表中,并且也不会将id字段序列化到表中
         */
        /*Example example = new Example(UserPO.class);
        example.createCriteria()
                .andEqualTo("username", "zhoujian");

        // 更新po对象
        UserPO updatePO = new UserPO();
        updatePO.setPassword("password-updateByExampleSelective");
        updatePO.setId(88);// 设置主键字段是多余的，主键不会被更新到表
        TestHelper.println("updateByExampleSelective(updatePO, example)修改记录数",mapper.updateByExampleSelective(updatePO,example));*/

        /**
         * 3.3.int updateByPrimaryKey(po)
         * 根据主键更新数据,需要注意的是也会将po中的null值更新到表中
         *
         * 注：
         * 1.po的id字段是需要更新数据的主键。
         */
        // mapper.updateByPrimaryKey();
        /*UserPO updatePO = new UserPO();
        updatePO.setPassword("password-updateByPrimaryKey");
        updatePO.setId(1243);//设置需要更新数据的主键
        // 也会将null值序列化到表中
        TestHelper.println("updateByPrimaryKey(updatePO)更新记录数",mapper.updateByPrimaryKey(updatePO));*/


        /**
         * 3.4.int updateByPrimaryKeySelective(po)
         *
         * 根据主键更新，与updateByPrimaryKey不同的是，只有po中的非null值才会被更新到表中
         */
        /*UserPO updatePO = new UserPO();
        updatePO.setPassword("password-updateByPrimaryKey");
        updatePO.setId(1243);//设置需要更新数据的主键
        // 也会将null值序列化到表中
        TestHelper.println("updateByPrimaryKeySelective(updatePO)更新记录数",mapper.updateByPrimaryKeySelective(updatePO));*/

    }

    /**
     * 4.查(select)
     * 查询的方法较多，请注意比较
     *
     * 4.1.List<T> select(T)
     * 会将非空属性作为查询的条件,如果只将主键字段作为条件,相当于selectByPrimaryKey(Object)
     *-------------------------------------------------
     * 4.2.selectByExample(Object)
     * 根据example条件查询并返回符合条件的记录
     *-------------------------------------------------
     * 4.3.List<T> selctAll()
     * 查询表中所有的记录
     *-------------------------------------------------
     * 4.4.T selectByPrimaryKey(Object o)
     * 根据主键查询记录
     * o的类型可以是int/Integer,DO类
     * 如果传递参数是DO类型,除主键字段外，其他的字段不会作为查询的条件
     *
     * 2020年9月21日 22:21:25
     * 2021年3月14日 11:03:00
     */
    @Test
    public String selectTest() {

        TestHelper.startTest("mapper.jar select(po) 查询测试");
        UserPO selectPO = new UserPO();
        selectPO.setUsername("myname");
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
        selectPO2.setUsername("xasdadaw2qe232r12");
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
        selectOne.setUsername("myname");

        //将主键作为查询的条件，可以保证查询的结果只有一条，不会抛出异常
        //selectOne.setId(88);
        TestHelper.println("查询结果: " + mapper.selectOne(selectOne));

        TestHelper.finishTest();
        // mapper.selectCountByExample()

        return TestResultTips.SEE_AT_DATABASE;
    }
}