package com.zj.test.jpa.test.field_mapping;

import com.zj.test.jpa.JpaApplication;
import com.zj.test.jpa.test.field_mapping.dao.FieldMappingDAO;
import com.zj.test.jpa.test.field_mapping.entity.MapKey;
import com.zj.test.jpa.test.field_mapping.entity.Name;
import com.zj.test.jpa.test.field_mapping.entity.TeacherEntity;
import com.zj.test.jpa.test.field_mapping.enums.SexEnum;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/14 17:37
 * @description: JPA字段映射单元测试类
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = JpaApplication.class)
@RunWith(SpringRunner.class)
public class FieldMappingUnitTest {

    @Autowired
    FieldMappingDAO fieldMappingDAO;

    /**
     * 1.测试: 在进行查询时，是否要求实体类中所有属性都是表属性?
     *
     * 测试实体定义:
     * public class TeacherEntity {
     *
     *     @Id
     *     private Integer t_id;
     *
     *     Integer age;
     *
     *     // 表中没有的字段
     *     String address;
     * }
     *
     * 【测试输出】
     * fieldMappingDAO.getOne(2764815): TeacherEntity(t_id=2764815, age=45)
     *
     * 【结论】
     * 1.查询时，实体类属性如果没有映射的表字段，会动态创建。
     *
     * 注: 即使没有@Column注解的字段,也要求表中有映射的字段，如果没有，也会创建、
     */
    @Test
    public void test1(){
        TeacherEntity one = fieldMappingDAO.getOne(2764815);
        TestHelper.println("fieldMappingDAO.getOne(2764815)",one);
    }

    /**
     * 2.插入操作时，如果实体类属性没有映射的表字段，是否会自动创建表字段。
     *
     * 【测试输出】
     * 自动创建了表字段:
     * `favorite` varchar(255) DEFAULT NULL,
     *
     * 【结论】
     * 会
     *
     */
    @Test
    public void test2(){
        TeacherEntity insertTeacherEntity = new TeacherEntity();
        insertTeacherEntity.setName("小明");
        insertTeacherEntity.setFavorite("play computer games");
        insertTeacherEntity.setAge(20);
        fieldMappingDAO.save(insertTeacherEntity);
    }

    /**
     * 3.demo: JPA自定义列定义
     *
     * 在本demo中,实体类label属性在表中没有映射的字段。
     *
     * 【测试输出】
     * 1.经测试，默认情况下，jpa为表自动添加了label定义:
     * `label` varchar(255) DEFAULT NULL,
     *
     * 【结论】
     *
     */
    @Test
    public void test3(){
        TeacherEntity insertTeacherEntity = new TeacherEntity();
        insertTeacherEntity.setName("小明");
        insertTeacherEntity.setFavorite("play computer games");
        insertTeacherEntity.setAge(20);
        insertTeacherEntity.setLabel("lazy boy");
        fieldMappingDAO.save(insertTeacherEntity);
    }

    /**
     * 4.测试: 列精度测试
     *
     * precision = 5,scale = 2
     *
     * 解释:
     * precision: 数据的最大长度,如对于decimal<5,2>,可能有以下的数据: 1.12,2132.1
     * scale: 小数部分的长度
     *
     * 【测试输出】
     * teacherEntity.setD3(new BigDecimal(1.21321321312));          // 成功,按照精度插入了数据
     * teacherEntity.setD2(1.123213125);                            // 成功，按照精度插入了数据
     *
     * 最终插入f=1.11123
     * 【结论】
     * 1.precision和scale属性只会BigDecimal有效，其他的无效，无效的可以通过columnDefinition来定义。
     *
     * 2.decimal对于小数位的保留采用的是四舍五入。
     * 如:
     * 1.124213125 -> 1.12
     * 1.21521321312 -> 1.22
     *
     */
    @Test
    public void test4(){
        TeacherEntity teacherEntity= new TeacherEntity();
        teacherEntity.setAge(11);
        teacherEntity.setName("precision test2");
        teacherEntity.setF(1.111232112254456f);

        // d2: double类型,但是通过columnDefinition定义了精度
        teacherEntity.setD2(1.124213125);
        // decimal
        teacherEntity.setD3(new BigDecimal(1.21521321312));
        fieldMappingDAO.save(teacherEntity);
    }

    /**
     * 5.测试: @GeneratedValue主键生成策略
     *
     * 【测试输出】
     *
     * 【结论】
     * 1.有以下策略:
     * GenerationType.AUTO:
     * 该策略会在数据库添加hibernate_sequence表，
     * 该表中next_val用来指定下一条插入数据的主键,next_val是递增的,
     * next_val值在第一次创建hibernate_sequence的时候设置为1，每次插入都会自增一次。
     *
     * 注意:
     * 1.如果next_val主键已经被占用,则会抛出异常:
     * Caused by: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '36' for key 'PRIMARY'
     * 并且将next_val +1;
     *
     * 2.如果没有hibernate_sequence表,hibernate会自动创建并且将next_val置1,
     * 如果表中已经有大量的数据，就会导入插入频繁报错，因此该策略不适合已有大量数据的表，
     * 可以通过指定正确的next_val来解决该问题。
     *
     * GenerationType.IDENTITY
     * 也会创建hibernate_sequence表
     * 生成的主键不是由AUTO_INCREMENT决定,而是由表主键最大id决定,等于max(id) + 1
     * 插入数据不会修改表的AUTO_INCREMENT属性
     *
     * GenerationType.TABLE
     *
     *
     *
     * ------------------------------------------------
     * 来自互联网的内容:
     *
     * JPA自带的主键生成策略有以下四种：
     *
     * AUTO：主键由程序控制，默认的主键生成策略，能够适应数据库变化，Oracle默认是序列方式，Mysql默认是主键自增长方式。
     *
     * IDENTITY：主键由数据库自动生成（主要是自动增长型），Mysql支持，Oracle不支持。
     * 主键自增策略       // true
     *
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列，Oracle支持，Mysql不支持。
     *
     * TABLE：使用一个特定的数据库表格来保存主键。
     */
    @Test
    public void generatedValueStrategy(){
        // 1.自动生成策略: 会根据数据库类型自定决定策略。mysql是IDENTITY,oracle是SEQUENCE
        /*TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setAge(22);
        teacherEntity.setName("generatedValueStrategy-default");
        fieldMappingDAO.save(teacherEntity)*/

        // 2.主键生成策略: GenerationType.IDENTITY
        // 也会创建hibernate_sequence表,但是不是通过该表记录主键
        // 生成的主键等于表AUTO_INCRMENT值
        // 因此该策略就是主键自增策略
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setAge(22);
        teacherEntity.setName("generatedValueStrategy-IDENTITY");
        fieldMappingDAO.save(teacherEntity);

        // 3.GenerationType.TABLE
        // 通过表hibernate_sequence表的next_val来记录下一次插入的主键
        // 使用时需要确保正确的next_val,以免插入时主键重复报错:
        // Duplicate entry '2' for key 'PRIMARY'

        // 4.GenerationType.SEQUENCE
        // mysql不支持,这里不进行测试。
    }

    /**
     * 6.测试: JPA枚举类型序列化测试
     *
     * 枚举属性: sex
     *
     * 【测试输出】
     * 1.EnumType.STRING时: 自动生成VARCHAR类型的字段
     * SexEnum.FEMALE -> ,序列化结果为FEMALE
     * 即序列化枚举常量名。
     *
     * 2.EnumType.ORDINAL   自动生成INT类型的字段
     * SexEnum.FEMALE -> 序列化结果为1
     * SexEnum.MALE -> 序列化结果为0
     * 即序列化的
     * 【注意】
     * 1.@Enumerated注解是用在实体类属性上，而不是枚举类型属性上。
     * 2.EnumType.ORDINAL序列化成int时，是字符串常量在枚举类型中的序号，从0开始。
     * 与枚举类型的属性无关。
     */
    @Test
    public void enumTypeSerializate(){
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName("张老师");
        teacherEntity.setAge(22);
        teacherEntity.setSex(SexEnum.MALE);

        fieldMappingDAO.save(teacherEntity);
    }

    /**
     * 7.实体多表映射
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void multiTablesMapping(){
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName("张老师");
        teacherEntity.setAge(22);
        teacherEntity.setSex(SexEnum.MALE);
        teacherEntity.setBook("呐喊");

        fieldMappingDAO.save(teacherEntity);
    }

    /**
     * 8.demo: JPA嵌套对象的映射
     *
     * 【测试输出】
     *
     * 【结论】
     * 1.JPA实体如果想要映射String外的引用类型，类型定义上要加@Embeddable注解。
     * 2.JPA默认情况下会映射嵌套类所有属性。
     * 可以通过@AttributeOverrides和@AttributeOverride来覆盖某些字段的默认映射,
     * 对于没有使用@AttributeOverride的嵌套字段，依然使用默认的映射。
     */
    @Test
    public void demo(){
        Name name = new Name();
        name.setFirstName("zhou");
        name.setLastName("jian");
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName("张老师");
        teacherEntity.setAge(22);
        teacherEntity.setSex(SexEnum.MALE);
        teacherEntity.setBook("呐喊");
        teacherEntity.setNames(name);

        fieldMappingDAO.save(teacherEntity);

    }

    /**
     * 9.demo: 集合字段插入1对多
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void collectionMapping(){
        Name name = new Name();
        name.setFirstName("zhou");
        name.setLastName("jian");
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName("周老师");
        teacherEntity.setLabels(Arrays.asList("label1","label2"));
        fieldMappingDAO.save(teacherEntity);
    }

    /**
     * 10.demo: Map字段插入1对多
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void mapMapping(){
        Name name = new Name();
        name.setFirstName("zhou");
        name.setLastName("jian");
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setName("周女士");

        /*Map<Integer,String> address = new HashMap<Integer,String>();
        address.put(1,"地址1");
        address.put(2,"地址2");*/

        Map<MapKey,String> address = new HashMap<MapKey,String>();
        MapKey mapKey = new MapKey();
        mapKey.setDes("首选地址");
        mapKey.setOrderNo(1);
        address.put(mapKey,"地址1");
        address.put(mapKey,"地址2");
        teacherEntity.setRecvAddress(address);

        fieldMappingDAO.save(teacherEntity);

    }
}