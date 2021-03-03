package xhm.struts.action;

import com.opensymphony.xwork2.Action;

/*2.Action的接口实现类：实现Action接口，接口中有以下定义：
 * 
 * 2.1.常量-这些常量我们也可以使用，struts也会使用
 * SUCCESS：默认execute方法返回值
 * ERROR
 * INPUT：默认栈会使用，如fileUpload拦截器，当文件不满足大小和类型要求时，会进入到此视图
 * NONE
 * LOGIN
 * 
 * 2.2.方法String execute()-默认返回SUCCESS，即"success"
 * */
public class HelloAction2 implements Action {

	@Override
	public String execute() throws Exception {
		return null;
	}
}
