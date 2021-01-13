package com.zj.test.mp.test.code_generator.teacher_module.entity;

/*import 你自己的父类实体,没有就不用设置!;*/
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-01-13
 */
@Data
/*@EqualsAndHashCode(callSuper = true)*/
public class Teacher{

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private Integer flag;

    private String sex;

    private String teachCourse;


}
