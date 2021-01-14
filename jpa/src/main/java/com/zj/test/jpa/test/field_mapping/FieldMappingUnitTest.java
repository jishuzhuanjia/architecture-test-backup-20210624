package com.zj.test.jpa.test.field_mapping;

import com.zj.test.jpa.JpaApplication;
import com.zj.test.jpa.test.field_mapping.dao.FieldMappingDAO;
import com.zj.test.jpa.test.field_mapping.entity.TeacherEntity;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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
}
