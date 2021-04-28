package xhm.struts.actionservlet;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

/*1.ActioContext��ʽ
 * ����ϵķ�ʽ�������������:ֻ�����ڴ洢���ݣ������ܵ��������������api��
 * ͨ��ActionContext����ֱ�ӻ�ȡapplication,session��
 * ActionContext����ƾ���Ϊ�����request��ʹ�ã���request����Ϊ��ActionContext����
 * ��õ���ʵ���Ͼ���һ��Map��ͨ����Map�Ĳ������Ƕ���Ĳ�������request�����ActionContext����
 * 
 *ע�⣺ActionContext�Ļ�ȡ����̬����������new��
 * ActionContext ac = ActionContext.getContext();
 * 
 *ActionContext���Ի�ȡ����
 * application
 * session
 * request - ActionContext
 * valueStack - ֵջ
 * */
public class Action extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	public String value() {
		/*
		 * ActionContext�������ں�request��ͬ
		 * */
		
		ActionContext ac = ActionContext.getContext();
		
		/*1.��ȡ�򣺿�����Ϊac����request��
		 * 1.1 application
		 * ��map�Ĳ������Ƕ���Ĳ���*/
		Map<String,Object> applicationScope = ac.getApplication();
		applicationScope.put("application", "application");
		
		//1.2��ȡsession
		Map<String,Object> sessionScope = ac.getSession();
		sessionScope.put("session", "session");
		
		//1.3��ȡֵջ
		ac.getValueStack();
		
		//1.4request
		//�������ԣ��˷�����ͬrequest.setAttribute(key,value);
		ac.put("request", "request");
		
		return "ok";
	}
}
