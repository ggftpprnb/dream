<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String menuId = request.getParameter("menuId");
%>
<!DOCTYPE html>
<html>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<iframe src="main?m=toMenu&menuId=<%=menuId%>" width="100%" height="100%" style="border: 1px;"></iframe>
	</div>
</body>
</html>