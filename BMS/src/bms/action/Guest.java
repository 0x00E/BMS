package bms.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Guest extends ActionSupport {

	private static final long serialVersionUID = 1L;



	@Override
	public String execute() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		session.setAttribute("bms_username", "guest");
		return SUCCESS;
	}

}
