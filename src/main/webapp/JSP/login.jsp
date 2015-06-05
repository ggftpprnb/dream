<%@page import="dream.browser.constant.MainConstant.SessionKey"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理登录页</title>
	<jsp:include page="/JSP/commons/easyui.jsp"></jsp:include>
	<script type="text/javascript" src="<%=basePath%>js/crypto/hmac-md5.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/crypto/hmac-sha1.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/browser/login.js"></script>
</head>

<body>
	<form id="login" action="main.json?m=login" method="post">
		<div align="center">
			<table>
				<tbody>
					<tr>
						<td><span>用户名:</span></td>
						<td><input type="text" id="username" value="" required="required"></td>
					</tr>
					<tr>
						<td><span>密码:</span></td>
						<td><input type="password" id="password" value="" required="required"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td></td>
						<td align="right">
							<input type="hidden" id="loginSalt" value="<%=session.getAttribute(SessionKey.LOGIN_SALT)%>">
							<a class="easyui-linkbutton" onclick="login();">登录</a>
							<a class="easyui-linkbutton" onclick="reset();">重置</a>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</form>
</body>
</html>