package com.zj.test.jpa;

import com.zj.test.jpa.dao.TeacherDAO;
import com.zj.test.jpa.entity.Teacher;
import com.zj.test.util.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2021/1/13 16:52
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
@SpringBootTest(classes = JpaApplication.class )
@RunWith(SpringRunner.class)
public class JpaUnitTest {

    @Autowired
    TeacherDAO teacherDAO;


    /**
     * 1.测试: JPA入门测试
     *
     * 【测试输出】
     * Hibernate: select teacher0_.id as id1_0_0_, teacher0_.age as age2_0_0_, teacher0_.name as name3_0_0_, teacher0_.sex as sex4_0_0_ from teacher teacher0_ where teacher0_.id=?
     * teacherDAO.getOne(2764816l): Teacher(id=2764816, name=name1, age=69, sex=null)
     *
     * 【结论】
     * 1.测试过程报了个错:
     * org.hibernate.LazyInitializationException: could not initialize proxy [com.zj.test.jpa.entity.Teacher#2764816] - no Session
     *
     * 原因: hibernate默认是懒加载的。
     *
     * 解决: 在实体类上添加@Proxy(lazy=false)即可。
     *
     */
    @Test
    public void test1(){

        Teacher one = teacherDAO.getOne(2764816);

        TestHelper.println("teacherDAO.getOne(2764816l)",one);
    }

    /**
     * 2.CRUD api测试
     *
     * 2.1.测试: 插入操作
     *
     * 【测试输出】
     *
     * 【结论】
     *
     */
    @Test
    public void test2(){
        Teacher teacher1= new Teacher();
        teacher1.setName("jpa-insert-teacher");
        teacher1.setAge(20);
        teacher1.setSex("男");
        Teacher save = teacherDAO.save(teacher1);
        TestHelper.println("teacherDAO.save(teacher1)",save);

        /*
        测试: save是否可以用来更新?

        结果: 是的,会自动判断:
        如果实体对象没有指定主键 -> 自动生成主键并插入
        如果实体对象指定主键 ->
         */
        save.setAge(66);
        Teacher save1 = teacherDAO.save(save);
        TestHelper.println("teacherDAO.save(teacher1)",save1);

        /*
        测试: save一个主键不存在的记录，会怎样

        结果:
        测试时，虽然我传递的id在表中没有存在，但是hibernate并没有使用我指定的id,
        不知道是不是@GeneratedValue覆盖了我传递的值。
         */
        Teacher save2 = new Teacher();
        save2.setName("插入一条主键不存在的记录");
        save2.setAge(25);
        save2.setId(11);

        Teacher save3 = teacherDAO.save(save2);
        TestHelper.println("插入一条主键不存在的记录",save3);
    }

    /**
     * 2.2.demo: 更新操作
     *
     * 【测试输出】
     * Hibernate: select teacher0_.id as id1_0_0_, teacher0_.age as age2_0_0_, teacher0_.name as name3_0_0_, teacher0_.sex as sex4_0_0_, teacher0_.xsdd as xsdd5_0_0_ from teacher teacher0_ where teacher0_.id=?
     * Hibernate: update teacher set age=?, name=?, sex=?, xsdd=? where id=?
     * Hibernate: select teacher0_.id as id1_0_0_, teacher0_.age as age2_0_0_, teacher0_.name as name3_0_0_, teacher0_.sex as sex4_0_0_, teacher0_.xsdd as xsdd5_0_0_ from teacher teacher0_ where teacher0_.id=?
     * teacherDAO.save(teacher): Teacher(id=4, name=jpa-update-name, age=22, sex=女, xsdd=null)
     *
     * 【结论】
     * 1.save方法用来插入/更新的。
     *
     * 2.由于save方法内部会去检测指定主键的数据是否存在来判断是更新还是插入，因此会有额外的查询，会影响性能。
     */
    @Test
    public void test3(){
        Teacher teacher = new Teacher();
        teacher.setId(5);
        teacher.setName("jpa-update-name2");
        teacher.setSex("女");
        teacher.setAge(22);
        teacherDAO.save(teacher);
        TestHelper.println("teacherDAO.save(teacher)",teacherDAO.save(teacher));
    }

    /**
     * saveAll测试
     *
     * 【测试输出】
     * Hibernate: select teacher0_.id as id1_0_0_, teacher0_.age as age2_0_0_, teacher0_.name as name3_0_0_, teacher0_.sex as sex4_0_0_, teacher0_.xsdd as xsdd5_0_0_ from teacher teacher0_ where teacher0_.id=?
     * Hibernate: select next_val as id_val from hibernate_sequence for update
     * Hibernate: update hibernate_sequence set next_val= ? where next_val=?
     * Hibernate: insert into teacher (age, name, sex, xsdd, id) values (?, ?, ?, ?, ?)
     * Hibernate: update teacher set age=?, name=?, sex=?, xsdd=? where id=?
     * teacherDAO.saveAll(Arrays.asList(teacher1,teacher2)): [Teacher(id=4, name=new name for update, age=55, sex=null, xsdd=null), Teacher(id=6, name=new name for insert, age=55, sex=null, xsdd=null)]
     *
     * 【结论】
     * 1.saveAll方法会自动判断是插入还是更新。
     *
     * 2.这也暴露出jpa的一个缺陷:
     * 插入和更新界限不明显,会执行额外的sql语句。
     *
     * 3.从目前测试进度看，jpa的插入和更新情况下，都不要求实体类属性在表中一定要存在。
     */
    @Test
    public void test4(){
        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();

        teacher1.setId(4);
        teacher1.setAge(55);
        teacher1.setName("new name for update");

        //teacher2.setId(4);
        teacher2.setAge(55);
        teacher2.setName("new name for insert");

        TestHelper.println("teacherDAO.saveAll(Arrays.asList(teacher1,teacher2))",
                teacherDAO.saveAll(Arrays.asList(teacher1,teacher2)));
    }
}
