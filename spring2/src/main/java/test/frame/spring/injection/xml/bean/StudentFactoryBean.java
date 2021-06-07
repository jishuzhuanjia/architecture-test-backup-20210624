package test.frame.spring.injection.xml.bean;

/**
 * @CreateTime：2020年2月9日 下午5:24:52
 * @Author：zhoujian
 * @QQ：2025513
 * @FileDescription：
 * @IsFinished：false
 */

public class StudentFactoryBean {
	
	// 工厂方法,可以为private
	private Student createStudent() {
		return new Student("student4",21);
	}
}
