package bms.action;

import com.opensymphony.xwork2.ActionSupport;

import bms.util.BMS;
import bms.util.JDBC;

public class Delete extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		if(BMS.checkGuestBool()){
			return ERROR;
		}
		JDBC.executeUpdate("DELETE FROM 图书管理表 WHERE 编号 IN("+id+")");
		return SUCCESS;
	}
	
}
