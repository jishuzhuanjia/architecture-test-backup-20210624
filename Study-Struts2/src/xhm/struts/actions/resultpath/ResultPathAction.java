package xhm.struts.actions.resultpath;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;


@Result(name="success",location="hello.jsp")/*classÈ«¾ÖÒ³Ãæ*/
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
