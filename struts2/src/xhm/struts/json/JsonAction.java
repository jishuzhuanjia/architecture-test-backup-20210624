package xhm.struts.json;

import com.opensymphony.xwork2.Action;
import xhm.struts.datawrap.User;

/* Json����action */
public class JsonAction implements Action {
	
	private User jsonUser;
	
	/*����Json���ݷ�ʽ��
	 * 1.��ͳ��ʽ����ajax���ݣ����ַ�ʽ����Ҫ�������ļ������á�
	 * 
	 * 2.struts2����json���ݣ���Ҫ��struts.xml����Ҫ���ص����ݡ�
	 * 
	 * */
	
	public User getJsonUser() {
		return jsonUser;
	}

	/* struts����json */
	public String testStrutsJson() {
		
		jsonUser = new xhm.struts.datawrap.User();
		
		jsonUser.setName("xiaohongmao");
		jsonUser.setAge(25);
		jsonUser.setSex("��");
		
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return null;
	}
}
