package bms;

import com.opensymphony.xwork2.ActionSupport;

import bms.util.BMS;

public class Update extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return BMS.checkGuest();
	}
	
}
