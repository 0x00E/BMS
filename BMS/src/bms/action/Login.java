package bms.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import bms.util.JDBC;

public class Login extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;


	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String execute() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		ArrayList<LinkedHashMap<String, Object>> al=JDBC.executeQueryC("SELECT pw FROM user WHERE user = ?", username);
		if(al.get(0).get("pw").equals("")) {
			return ERROR;
		}else {
			session.setAttribute("bms_username", username);
			return SUCCESS;
		}
		
		
	}

}
