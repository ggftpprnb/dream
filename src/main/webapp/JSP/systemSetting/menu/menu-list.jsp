<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>菜单管理</title>
	<jsp:include page="/JSP/commons/easyui.jsp"></jsp:include>
</head>
<script type="text/javascript">
//最先加载
$(function(){
	$("#menuList").treegrid({
		/* url : 'js/browser/datagrid_data1.json', */
		url : 'menu?m=getMenuList',
		idField:'id',
	    treeField:'name',
		toolbar: [{
			iconCls: 'icon-add',
			handler: addMenu
		},'-',{
			iconCls: 'icon-edit',
			handler: editMenu
		}]
	});
});

function addMenu(){
	alert('addMenu');
}

function editMenu(){
	alert('editMenu');
}
</script>

<body>
	<table style="height: 480px;" id="menuList" class="easyui-treegrid" title="菜单列表" data-options="method:'get',fitColumns:true,pagination:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'id',width:80,hidden:true">菜单ID</th>
				<th data-options="field:'name',width:100">菜单名称</th>
				<th data-options="field:'listOrder',width:20,align:'right'">菜单顺序</th>
			</tr>
		</thead>
	</table>
</body>
</html>