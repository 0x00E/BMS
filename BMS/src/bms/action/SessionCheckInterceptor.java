package bms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import bms.util.BMS;
import bms.util.JDBC;

public class SessionCheckInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		if(!JDBC.isAvailable()){
			return "dberror";
		}
		if(BMS.checkSkipAction(arg0.getAction())){
			return arg0.invoke();
		}
		
		HttpServletRequest context=ServletActionContext.getRequest();
		HttpSession session=context.getSession();
		if(session.getAttribute("bms_username")==null){
			return Action.ERROR;
		}
		return arg0.invoke();
	}

}
