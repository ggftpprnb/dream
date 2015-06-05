<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>角色管理</title>
	<jsp:include page="/JSP/commons/easyui.jsp"></jsp:include>
</head>

<body>
	<p>角色列表</p>
</body>
</html>