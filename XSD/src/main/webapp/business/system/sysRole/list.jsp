<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>水资源管理系统</title>
		 <style>
		 .fixed-table-pagination{
		  display:none!important;
		 }
		 #tbar{
		      margin-top: 3px;
		      margin-bottom: 0px;
		      padding-left:2px;
		      padding-right:2px;
		 }
		 #tbinfo_user_div{
		  padding-left:10px;
		  padding-right:10px;
		 }
		 
		 .input-group .form-control:first-child, .input-group-addon:first-child, .input-group-btn:first-child > .btn, .input-group-btn:first-child > .btn-group > .btn, .input-group-btn:first-child > .dropdown-toggle, .input-group-btn:last-child > .btn:not(:last-child):not(.dropdown-toggle), .input-group-btn:last-child > .btn-group:not(:last-child) > .btn 
		 {
		  color:black;
		 }
		 .form-control{
		  padding: 4px 10px;
		 }
		 input::-webkit-input-placeholder{
            color:black!important;
        }
       input::-moz-placeholder{  
           color:black!important;
       }
       input:-moz-placeholder{    
           color:black!important;
       }
       input:-ms-input-placeholder{  
           color:black!important;
       }
       a .icon, a [class^="icon-"], a [class*=" icon-"]:nth-child(4){
        color:black;
        position: static; 
       top: auto; 
        left: auto;
        font-size:16px;
       }
        a .icon, a [class^="icon-"], a [class*=" icon-"]:nth-child(4):hover{
        color:black!important;
       }
     .fixed-table-container tbody tr td:nth-child(9){
       text-align:center;
     }
    .icon icon-repeat{
       color:red!important;
     }
		 </style>
		
	</head>
	<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div style="height:100%;">
		<div class="row-fluid" style="height:30px;">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">角色管理</li>
				</ol>
			</h3>
		</div>
		<div id="maincontent" class="row-fluid" style="height:calc(100% - 30px);">
			<div class="row-fluid col-md-12" style="height:100%;">
				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar" style="height:40px;">
					<div class="btn-group">
						<button type="button" id="btn_add_role" class="btn btn-primary">
							<div class="visible-md visible-lg">
								<i class="icon icon-file-o"></i>&nbsp;新增
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-file-o"></i>
							</div>
						</button>
					</div>
					<div class="btn-group">
						<button type="button" id="set_user" class="btn btn-primary">
							<div class="visible-md visible-lg">
								<i class="icon icon-user"></i>&nbsp;设置用户
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-user"></i>
							</div>
						</button>
					</div>
					<div class="btn-group">
						<button type="button" id="set_group" class="btn btn-primary">
							<div class="visible-md visible-lg">
								<i class="icon icon-tasks"></i>&nbsp;设置分组
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-user"></i>
							</div>
						</button>
					</div>
					<div class="btn-group">
						<button type="button" id="btn_del_role" class="btn btn-primary">
							<div class="visible-md visible-lg">
								<i class="icon icon-times"></i>&nbsp;删除
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-times"></i>
							</div>
						</button>
					</div>
					<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group ">
							<input type="text" id="searchName_role" class="form-control"
								placeholder="输入关键字进行模糊查询"><span class="input-group-btn">
								<button class="btn" id="btn_ref_role" type="button" style="background:#338ccc;color:white">
									<div class="visible-md visible-lg">
										<i class="icon icon-search"></i>&nbsp;查询
									</div>
									<div class="visible-xs visible-sm">
										<i class="icon icon-search"></i>
									</div>
								</button>
							</span>
						</div>
					</div>
				</div>
				<!-- 按钮工具条结束 -->
				<div id="tbinfo_role_div" style="width:100%;height:calc(100% - 0px)">
					<table id="tbinfo_role" class="table-condensed table-hover">
						<thead>
							<tr>
								<th data-halign="center" data-align="center"
									data-sortable="false" data-field="state" data-checkbox="true"
									data-formatter="FMT_Check_Role"></th>
								<th data-halign="center" data-align="center"
									data-sortable="false" data-width="60" data-formatter="FMT_Num_Role">
									序号</th>
								<th data-halign="center" data-align="center" data-sortable="false"
									data-field="ROLE_CODE" data-width="200">角色编码</th>
								<th data-halign="center" data-align="center" data-sortable="false"
									data-field="ROLE_NAME" data-width="200">角色名称</th>
								<th data-halign="center" data-align="center" data-sortable="false"
									data-field="GROUP_NAME" data-width="300">已分配分组</th>
								<th data-halign="center" data-align="center" data-sortable="false"
									data-field="STAFF_NAME" data-width="300">已分配用户</th>
								<th data-halign="center" data-align="center" data-sortable="false"
									data-field="MENU_NAME" data-formatter="FMT_Num_menu"
									data-width="300">已分配菜单</th>
								<!-- <th data-halign="center" data-align="center" data-sortable="true"
									data-field="REMARK" class="visible-md visible-lg">备注</th> -->
								<th data-halign="center" data-align="center"
									data-sortable="false" data-width="50" 
									data-formatter="FMT_Oper_Role">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<!-- maincontent -->
	</div>
	</body>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<%@include file="../../../business/system/sysGroup/setUser.jsp"%>
<%@include file="setGroup.jsp"%>
<script src="../business/system/sysRole/def.js"></script>
<script src="../business/system/sysRole/list.js"></script>
