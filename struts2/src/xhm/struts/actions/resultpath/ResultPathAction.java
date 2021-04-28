package xhm.struts.actions.resultpath;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;


@Result(name="success",location="hello.jsp")/*classȫ��ҳ��*/
public class ResultPathAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Action(value="ResultPathAction")
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	
}
