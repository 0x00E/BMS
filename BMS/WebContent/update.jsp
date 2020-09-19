<%@page import="bms.util.BMS"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bms.util.JDBC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
String p_id=request.getParameter("id");
ArrayList<LinkedHashMap<String, Object>> al=JDBC.executeQueryC("SELECT * FROM 图书管理表 WHERE 编号 = ?", p_id);
LinkedHashMap<String, Object> map=al.get(0);
Object id=map.get("编号");
Object name=map.get("书名");
Object chubanshe=map.get("出版社");
Object author=map.get("作者");
Object number=map.get("数量");
Object price=map.get("定价");
Object image=map.get("封面");
Object ext=map.get("备注");
Object date=map.get("日期");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=p_id==null?"添加图书":"修改图书"%></title>
</head>
<body>
<form action="<%=p_id==null?"doAdd":"doUpdate"%>">
书名：<br>
<input type="text" name="name" required value="<%=BMS.null2Empty(name)%>"/><br>
类别：<br>
<select name="category">
	<option value="计算机">计算机</option>
	<option value="医学">医学</option>
	<option value="科学">科学</option>
	<option value="工业">工业</option>
	<option value="杂志">杂志</option>
	<option value="动漫">动漫</option>
</select>
<br>
出版社：<br>
<input type="text" name="chubanshe" required value="<%=BMS.null2Empty(chubanshe)%>"/><br>
作者：<br>
<input type="text" name="author" required value="<%=BMS.null2Empty(author)%>"/><br>
数量：<br>
<input type="text" name="number" required value="<%=BMS.null2Empty(number)%>" pattern="^[0-9]*$"/><br>
定价：<br>
<input type="text" name="price" required value="<%=BMS.null2Empty(price)%>" pattern="^\+?(?!0+(\.00?)?$)\d+(\.\d\d?)?$"/><br>
日期：<br>
<input type="text" name="date" id="date" required value="<%=BMS.null2Empty(date)%>"/>
<br>
封面：<br>
<input type="text" name="image" value="<%=BMS.null2Empty(image)%>"/><br>
备注：<br>
<input type="text" name="ext" value="<%=BMS.null2Empty(ext)%>"/><br><br>
<input type="submit" value="确认"/><br>
<input type="hidden" value="<%=BMS.null2Empty(id)%>" name="id"/>
</form>

<script src="js/laydate/laydate.js"></script>
<script>
lay('#version').html('-v'+ laydate.v);

laydate.render({
  elem: '#date' 
});
</script>
</body>
</html>