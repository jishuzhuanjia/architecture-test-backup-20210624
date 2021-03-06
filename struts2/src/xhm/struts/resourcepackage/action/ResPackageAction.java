package xhm.struts.resourcepackage.action;

import com.opensymphony.xwork2.ActionSupport;

//资源包测试action
public class ResPackageAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String
	execute() throws Exception {
		
		//获取方式1：ActionSupport getText()
		System.out.println(getText("username"));
		System.out.println(getText("password"));
		
		/*获取指定名的属性，会按照资源包查找顺序进行查找，如果没有，继续往下找
		 * 会往父包中查找
		 * */
		System.out.println(getText("age"));
		
		System.out.println(getText("school"));
		
		System.out.println(getText("xxx"));
		
		//事实证明，在查找父包的时候也会查找ActionName.properties，而不仅仅是package.properties
		System.out.println(getText("parent"));
		
		return SUCCESS;
	}

}
