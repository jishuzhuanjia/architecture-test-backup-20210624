package ssm.test.controller.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ssm.test.controller.User;
/*
 * RestController相当于@ResponseBody + @Controller
 * 快速注解json controller
 * */

//@ResponseResult - 我看到其他人的代碼中的註解，應該和@RestController作用一樣，存在于老版本中。
@RestController
@RequestMapping("annotationtest")
public class RestControllerTestController {

	/*
	 * 测试： @RestController当不返回值时前端返回：
	 * Content-Length:0
	   Date:Fri, 01 Nov 2019 09:47:02 GMT
	 * 
	 * */
	@RequestMapping("test1")
	public void test1() {
		System.out.println("annotation test1");
	} 
	
	/*
	 * Content-Disposition:inline;filename=f.txt
	   Content-Type:application/json;charset=UTF-8
	   Date:Fri, 01 Nov 2019 09:50:27 GMT
       Transfer-Encoding:chunked
	 * 
	 * 前端返回：
	 * {"username":"wangbaoqiang","age":50}
	 * */
	@RequestMapping("test2")
	public User test2() {
		User queryUser = new User();
		queryUser.setUsername("wangbaoqiang");
		queryUser.setAge(50);
		return queryUser;
	}
}
