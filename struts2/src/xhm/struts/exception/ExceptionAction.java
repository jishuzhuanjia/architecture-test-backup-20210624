package xhm.struts.exception;

import com.opensymphony.xwork2.ActionSupport;

//“Ï≥£¥¶¿Ì≤‚ ‘Action
public class ExceptionAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("null")
	@Override
	public String execute() throws Exception {
		
		String string= null;
		string.subSequence(0, 10);
		
		//int i=1/0;
		//success
		return super.execute();
	}
}
