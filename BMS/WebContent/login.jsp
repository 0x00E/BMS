<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form action="login" method="post">
用户名：<br>
<input type="text" name="username" required/><br>
密码：<br>
<input type="password" name="password" required/><br><br>
<input type="submit" value="管理员登录"/>
<input type="submit" value="游客登录" form="guest"/>
</form>
<form id="guest" action="guest"></form>
</body>
</html>