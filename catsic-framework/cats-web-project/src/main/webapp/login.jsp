<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file= "/common/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cats</title>
		<link   type="text/css" rel="stylesheet" href="<c:url value='/css/web/login.css'/>">
		<script type="text/javascript" src="<c:url value='/scripts/common/easyui.common.js'/>" ></script>
		<script type="text/javascript" src="<c:url value='/scripts/common/common.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/scripts/web/login.js'/>"></script>
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
				<div class="con-tit">认证模式</div>
					<div style="position:absolute;width:300px;left:370px;top:58px;margin-left:-5px;">
								<!-- <select class="oauth">
									<option value="1">用户名登录</option>
									<option value="2">证书登录</option>
								</select> -->
								<input id="oauth" class="easyui-combobox" style="width:137px;" 
								 data-options="panelHeight:'auto',height:'32px',
								 		valueField: 'value',
										textField: 'label',
										data: [{
											label: '用户名登录',
											value: '1'
										},{
											label: '证书登录',
											value: '2'
										}],value:'1'">
							</div>
					<div class="con-form">
						<form action="<%=request.getContextPath() %>/login" method = "post" id="loginform">
						<!-- <form method = "post" id="loginform"> -->	
							<div class="div_tr">
								<label>用户名：</label><input id="username" name="username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"  class="easyui-textbox" style="width:350px;height:40px;padding:12px" data-options="prompt:'请输入用户名',iconCls:'icon-man',iconWidth:38"/>
							</div>
							<div class="div_tr">
								<label> 密&nbsp;码：</label><input id="password" name="password" class="easyui-textbox" type="password" style="width:350px;height:40px;padding:12px" data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38"/>
							</div>
                            <div class="div_code"> <label>验证码: </label>
                    			<input type='text' id="captcha" name='captcha' class="required"  size='5' />  
                     			 <img id="imageF" class="imageF" src="<c:url value="imageCode.jsp"/>" />  
                      			<a href="#" id="flashImage" class="flashImage">换一张</a>  
                      			<input type="checkbox" class="remember-me" name="remember-me">
                    			<span class="remember-text">记住用户名</span>
                   			</div>   
							<div class="sub div_tr">
								<a id="login" class="login" href="javascript:void(0)"></a>
								<input class="reset" type="reset" value="重置" >
							</div>
						</form>
					</div>
					<div class="con-form" style="display:none;margin-top:60px;">
						<form action="<%=request.getContextPath() %>/web/certificate.jsp" method = "post" id="certloginform">
							<div class="sub div_tr">
								<a id="certlogin" class="login" href="javascript:void(0)"></a>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="footer">Copyright&copy;版权所有交科院  </div>
		</div>
	</body>
	<c:if test="${param.error == true && !empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
		 <script>
		 	message("${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}");
		 </script>
	</c:if>
</html>