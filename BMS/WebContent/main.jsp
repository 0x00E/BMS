<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="bms.util.BMS"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bms.util.JDBC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理系统</title>
<script src="js/jquery.js"></script>
<script src="js/main.js"></script>
</head>
<body>

<%

Object username=session.getAttribute("bms_username");

Object search=BMS.null2Empty(request.getParameter("search"));

Object books=JDBC.executeQueryC("SELECT COUNT(*) 条数 FROM 图书管理表").get(0).get("条数");

Object booksNum=JDBC.executeQueryC("SELECT SUM(数量) 总库存 FROM 图书管理表").get(0).get("总库存");

Object max=JDBC.executeQueryC("SELECT MAX(定价) 最高定价 FROM 图书管理表").get(0).get("最高定价");

Object min=JDBC.executeQueryC("SELECT MIN(定价) 最低定价 FROM 图书管理表").get(0).get("最低定价");

Object cateCount=JDBC.executeQueryC("SELECT COUNT(*) 类别数 FROM (SELECT 类别 FROM 图书管理表 GROUP BY 类别) A").get(0).get("类别数");

Object pressCount=JDBC.executeQueryC("SELECT COUNT(*) 出版社数 FROM (SELECT 出版社 FROM 图书管理表 GROUP BY 出版社) A").get(0).get("出版社数");

Object minDate=JDBC.executeQueryC("SELECT MIN(日期) 最小日期 FROM 图书管理表").get(0).get("最小日期");

Object maxDate=JDBC.executeQueryC("SELECT MAX(日期) 最大日期 FROM 图书管理表").get(0).get("最大日期");

%>

<i>欢迎，</i>
<%=username %>
<a href="logout">注销</a>
<hr>
<%

if(!username.equals("guest")){
	out.print("<a href='add'>添加图书</a>");
}

%>

<form>
<input type="search" name="search" required placeholder="书名/作者" value="<%=search%>"/>
<input type="submit" value="搜索"/>
<input type="button" value="消除搜索" onclick="location='main'"/>
</form>


<h2>图书管理表</h2>
实时统计：<br>
&nbsp;&nbsp;&nbsp;&nbsp;总收录图书：<%=books %><br>
&nbsp;&nbsp;&nbsp;&nbsp;图书库存(本)：<%=booksNum %><br>
&nbsp;&nbsp;&nbsp;&nbsp;最高定价：<%=max %><br>
&nbsp;&nbsp;&nbsp;&nbsp;最低定价：<%=min %><br>
&nbsp;&nbsp;&nbsp;&nbsp;类别数目：<%=cateCount %><br>
&nbsp;&nbsp;&nbsp;&nbsp;入库年份：<%=minDate %> > <%=maxDate %><br>
&nbsp;&nbsp;&nbsp;&nbsp;出版社数：<%=pressCount %><br>

<hr>
<%

int showPageNumber=15;
int page0=Integer.parseInt(request.getParameter("page")!=null?request.getParameter("page"):"1");
Object pageCount;

ArrayList<LinkedHashMap<String, Object>> al;

if(search.equals("")){
	al=JDBC.executeQueryC("SELECT 编号,书名,出版社,类别,作者,数量,定价,日期,备注 FROM 图书管理表 LIMIT ?,?"
	,(page0-1)*showPageNumber
	,showPageNumber
	);
	pageCount=JDBC.executeQueryC("SELECT COUNT(*) 条数 FROM 图书管理表").get(0).get("条数");
}else{
	al=JDBC.executeQueryC("SELECT 编号,书名,出版社,类别,作者,数量,定价,日期,备注 FROM 图书管理表 WHERE 书名 LIKE '%"+search+"%' OR 作者 LIKE '%"+search+"%' LIMIT ?,?"
	,(page0-1)*showPageNumber
	,showPageNumber
	);
	pageCount=JDBC.executeQueryC("SELECT COUNT(*) 条数 FROM 图书管理表 WHERE 书名 LIKE '%"+search+"%' OR 作者 LIKE '%"+search+"%'").get(0).get("条数");
}
String text;
if(!username.equals("guest")){
	text=BMS.object2HTMLString(al);
}else{
	text=BMS.object2OneLine(al);
}

if(text.equals("不存在指定数据！")){
	out.println(text);
	return;
}



if(!username.equals("guest")){
	out.print("批量操作：<a href='#' onclick='del()'>删除</a><br><a href='#' onclick='selectAll()'>全选</a> <a href='#' onclick='unSelectAll()'>全不选</a> <a href='#' onclick='inverse()'>反选</a>");
	out.print("<br>导出文本：<a href='#' onclick='exportAllTxt()'>全部数据</a> <a href='#' onclick='exportTxt(\""+search+"\","+(page0-1)*showPageNumber+","+showPageNumber+")'>本页</a> ");
	if(search.equals("")){
		out.print("搜索结果");
	}else{
		out.print("<a href='#' onclick='exportTxt(\""+search+"\")'>搜索结果</a>");
	}
	
	out.print("<br>导出Excel表格：全部数据 本页 搜索结果<hr>");
}

out.println(text);

long pc=Long.parseLong(pageCount.toString());;

if(page0!=1){
	out.print("<a href='main?page=1&search="+search+"'>首页</a>");
}else{
	out.print("首页");
}

%>

&nbsp;

<%

if(page0!=1){
	out.print("<a href='main?page="+(page0-1)+"&search="+search+"'>上一页</a>");
}else{
	out.print("上一页");
}

%>

&nbsp;
<% 


long pages=Math.round((double)pc/showPageNumber);
for(int i=1;i<=pages;i++){
	if(i==page0){
		out.print("["+i+"]");
	}else{
		out.print("<a href='main?page="+i+"&search="+search+"'>["+i+"]</a>");
	}
	
}

%>

<% 
if(page0!=pages){
	out.print("<a href='main?page="+(page0+1)+"&search="+search+"'>下一页</a>");
}else{
	out.print("下一页");
}

%>

&nbsp;

<%

if(page0!=pages){
	out.print("<a href='main?page="+(pages)+"'>尾页</a>");
}else{
	out.print("尾页");
}

%>
</body>
</html>