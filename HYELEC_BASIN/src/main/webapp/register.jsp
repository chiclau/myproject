<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn" style="overflow: hidden;">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<title>注册页面</title>
<style type="text/css">
	*{margin:0;padding:0;}
</style>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
</head>
<!-- overflow: hidden; -->
<body style="margin-top: -64px;height: 112%;overflow: hidden;">
	<div class="container-fluid" >
		<div class="row-fluid">
			<h3 class="text-primary" stlyle="margin-bottom: -14px;">
				<ol class="breadcrumb">
					<li>&nbsp;&nbsp;&nbsp;用户注册</li>
				</ol>
			</h3>
		</div>
		<hr>
		<div class="row-fluid">
				<form class="form-horizontal" name="form1" id="form1"
					autocomplete="off" method="post"
					data-bv-message="This value is not valid"
					data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
					data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
					data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
					<div class="form-group">
						<label  class="col-md-4 control-label">
							<span class="text-danger">*&nbsp;</span>账号：
						</label>
						<div class="col-md-4 rowGroup">
						<input type="text" class="form-control" onblur="verUserName()"
						       id="regUserName" name="regUserName" placeholder="请输入账号">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-4 control-label">
							<span class="text-danger">*&nbsp;</span>密码：
						</label>
						<div class="col-md-4 rowGroup">
						<input type="password" class="form-control"
						       id="userPwd" name="userPwd"
							   placeholder="请输入密码">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-4 control-label">
							<span class="text-danger">*&nbsp;</span>重复密码：
						</label>
						<div class="col-md-4 rowGroup">
						<input type="password" class="form-control"
							   id="newPwd" onblur="retPwd()" name="newPwd"
							   placeholder="请输入密码">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-4 control-label">
							<span class="text-danger">*&nbsp;</span>真实姓名：
						</label>
						<div class="col-md-4 rowGroup">
						<input type="text" class="form-control"
							   id="staffName" name="staffName"
							   placeholder="请输入真实姓名">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-4 control-label">
							<span class="text-danger">*&nbsp;</span>所属单位：
						</label>
						<div class="col-md-4 rowGroup">
						<input type="text" class="form-control" 
							   id="treeNmDept" name="treeNmDept"
							   placeholder="请输入所属单位">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-4 control-label">
							部门：
						</label>
						<div class="col-md-4 rowGroup">
						<input type="text" class="form-control"
							   id="staffDept" name="staffDept"
							   placeholder="请输入部门">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-4 control-label">
							<span class="text-danger">*&nbsp;</span>手机号码：
						</label>
						<div class="col-md-4 rowGroup">
						<input type="text" class="form-control"
							   id="linkPhone" name="linkPhone"
							   placeholder="请输入联系电话">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-4 control-label">
							现居住地：
						</label>
						<div class="col-md-2 rowGroup"><!-- onChange="selectcityarea('selectp','selectc','form1');" -->
							<select class="form-control" id="sel_1" required name="selectp" onchange="selectCityArea()">
								<option value="0" selected>请选择</option>
							</select>
						</div>
						<div class="col-md-2 rowGroup">
							<select class="form-control" id="sel_2" required name="selectc">
								<option value="0" selected>请选择</option>
							</select>
						</div>
					</div>
					<div class="col-md-4">
					</div>
					<div class="col-md-4" style="text-align: center;">
					    <button type="button" id="btn_" class="btn btn-large btn-primary" 
					    		onclick="register()"
					    		style="margin-right: 100px;">
					    	<i class="icon icon-save"></i>&nbsp;注册
					    </button>
					    <button type="button" class="btn" onclick="returnLogin()">
							<i class="icon icon-signout"></i> 返回
						</button>
					</div>
				</form>
		</div>
	</div>
</body>
</html>
<script type="text/javascript" src="<%=basePath %>common/inc/city.js"></script>
<script type="text/javascript" src="<%=basePath %>common/inc/register.js"></script>
