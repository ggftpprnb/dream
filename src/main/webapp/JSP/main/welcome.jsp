<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>运营管理登录页</title>
	<jsp:include page="/JSP/commons/easyui.jsp"></jsp:include>
</head>

<body>
	<p>欢迎使用运营管理系统</p>
</body>
</html>