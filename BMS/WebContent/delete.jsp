<%@page import="java.util.LinkedHashMap"%>
<%@page import="bms.util.BMS"%>
<%@page import="bms.util.JDBC"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认删除页面</title>
</head>
<body>
<%

String id=request.getParameter("id");
ArrayList<LinkedHashMap<String, Object>> al
=JDBC.executeQueryC("SELECT * FROM 图书管理表 WHERE 编号 IN ("+id+")");
out.println(BMS.object2OneLine(al));
out.println("你确定要删除以上数据吗？");
%>
<form action="doDelete">
<input type="hidden" name="id" value="<%=id%>"/>
<input type="submit" value="确认"/>
<input type="button" value="返回" onclick="history.back()"/>
</form>
</body>
</html>