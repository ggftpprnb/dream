<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>运营管理平台首页</title>
	<jsp:include page="/JSP/commons/easyui.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>js/browser/main/main.js"></script>
</head>

 <body class="easyui-layout">
    <div data-options="region:'north',title:'登录信息',split:true,collapsible:false" style="height:100px;"></div>
    <div data-options="region:'west',title:'导航菜单',split:true,collapsible:false" style="width:250px;">
    	<div id="accordion" class="easyui-accordion" data-options="fit:true,border:false,animate:true">
    		<div></div>
    		<c:forEach items="${topMenuList}" var="topMenu">
    			<div id="${topMenu.id}" title="${topMenu.name}">
	    			<div class="easyui-panel" data-options="fit:true,border:false">
						<ul id="tree_${topMenu.id}" class="easyui-tree" data-options="isLoad:false"></ul>
					</div>
				</div>
    		</c:forEach>
    	</div>
    </div>
    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
    	<div id="contentTabs" class="easyui-tabs"  data-options="fit:true,border:false,tabWidth:150">
    		<div title="欢迎页" data-options="closable:true,href:'main?m=toWelcome'"></div>
    	</div>
    </div>
    <div data-options="region:'south',title:'',split:true,collapsible:false" style="height:100px;"></div>
</body>
</html>