package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//≤‚ ‘cn.xhm «∑Ò±ª…®√Ë
@Controller
@RequestMapping("/ssmtest")
public class MavenSsmTestController {
	
	@RequestMapping("testok")
	public String test1() {
		return "/jsp/success.jsp";  
	}
	
	@RequestMapping("testMq")
	public void test2() {
	}
}
