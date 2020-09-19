package bms.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import bms.action.Guest;
import bms.action.Login;

public class BMS {
	
	public static String object2OneLine(ArrayList<LinkedHashMap<String, Object>> al) {
		StringBuilder str=new StringBuilder("<table border='1px'>");
		str.append("<tr>");
		for(String col:al.get(0).keySet()) {
			str.append("<td>");
			str.append(col);
			str.append("</td>");
		}
		
		for(LinkedHashMap<String, Object> map:al) {
			str.append("<tr>");
			for(Object value:map.values()) {
				str.append("<td>");
				str.append(null2Empty(value));
				str.append("</td>");
			}
			str.append("</tr>");
		}
		str.append("</table>");
		return str.toString();
	}
	
	
	public static String object2HTMLString(ArrayList<LinkedHashMap<String, Object>> al) {
		if(al.get(0).get("编号").equals("")) {
			return "不存在指定数据！";
		}
		StringBuilder str=new StringBuilder("<table border='1px'>");
		str.append("<tr>");
		str.append("<td>");
		str.append("批量");
		str.append("</td>");
		for(String col:al.get(0).keySet()) {
			str.append("<td>");
			str.append(col);
			str.append("</td>");
		}
		str.append("<td colspan='2'>操作</td></tr>");
		for(LinkedHashMap<String, Object> map:al) {
			str.append("<tr>");
			str.append("<td>");
			str.append("<input type='checkbox' name='box' id_attr='"+map.get("编号")+"'/>");
			str.append("</td>");
			for(Object value:map.values()) {
				str.append("<td>");
				str.append(null2Empty(value));
				str.append("</td>");
			}
			str.append("<td>");
			str.append("<a href='delete?id="+map.get("编号")+"'>删除</a>");
			str.append("</td>");
			str.append("<td>");
			str.append("<a href='update?id="+map.get("编号")+"'>修改</a>");
			str.append("</td>");
			str.append("</tr>");
		}
		str.append("</table>");
		return str.toString();
	}
	
	public static Object null2Empty(Object obj) {
		if(obj==null)return "";
		return obj;
	}
	
	public static String checkGuest(){
		HttpServletRequest context=ServletActionContext.getRequest();
		HttpSession session=context.getSession();
		if(session.getAttribute("bms_username").equals("guest")){
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public static boolean checkGuestBool(){
		HttpServletRequest context=ServletActionContext.getRequest();
		HttpSession session=context.getSession();
		if(session.getAttribute("bms_username").equals("guest")){
			return true;
		}
		return false;
	}

	public static boolean checkSkipAction(Object action) {
		if(action instanceof Guest||action instanceof Login){
			return true;
		}
		return false;
	}


	public static InputStream objectExportTxt(ArrayList<LinkedHashMap<String, Object>> data) {
		StringBuilder builder=new StringBuilder();
		for(String col:data.get(0).keySet()){
			builder.append(col+",");
		}
		builder.append("\r\n");
		for(LinkedHashMap<String, Object> map:data){
			for(Object obj:map.values()){
				builder.append(null2Empty(obj)+",");
			}
			builder.append("\r\n");
		}
		return new ByteArrayInputStream(builder.toString().getBytes());
	}
}