<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file= "/common/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>全国交通运输行政执法综合管理信息系统</title>
		<link   type="text/css" rel="stylesheet" href="<c:url value='/css/web/login.css'/>">
		<script type="text/javascript" src="<c:url value='/scripts/common/easyui.common.js'/>" ></script>
		<script type="text/javascript" src="<c:url value='/scripts/common/common.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/web/certificate/login.js'/>"></script>
	</head>
	<body>
		<div class="page">
			<div class="header">
				<div class="h-content">
					<div class="logo"></div>
					<div class="title"></div>
				</div>
			</div>
			<div class="content">
				<div class="content-bg">
				<div class="con-tit">用户登录</div>
					<div class="con-form">
						<form method = "post" id="loginform">
							<select class="easyui-combobox" id="certificate" style="width:30%;height:26px;"></select>
						</form>
					</div>
				</div>
			</div>
			<div class="footer">Copyright&copy;版权所有交科院  </div>
		</div>
	</body>
</html>