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
	       #tbinfo_ListnmSysRole tbody tr td:nth-child(4){
	         position:relative;
	       }
	       .icon-filter:before{
	        position: absolute;
		    left: -16px;
		    top: 34px;
		    font-size: 21px;
		    color: black;
	       }
	       .icon-backward:before,.icon-forward:before{
	        display:none;
	       }
	       .form-control {
			    padding: 5px 9px;
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
					<li class="active">角色功能菜单授权</li>
				</ol>
			</h3>
		</div>
		<div id="maincontent" class="row-fluid" style="height:calc(100% - 30px);">
			<div class="row-fluid col-md-5" style="height:100%;">
				<div class="btn-toolbar" style="height:35px;">
					<button type="button" onclick="Select_S_SysRole('')" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-refresh"></i>&nbsp;系统角色
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-refresh"></i>
								</div>
							</button>
				</div>
					<div id="tbinfo_ListnmSysRole_div" class="row-fluid col-md-12" style="height:calc(100% - 130px);">
						<table id="tbinfo_ListnmSysRole"
							class="table-condensed table-hover">
							<thead>
								<tr>
									<th data-halign="center" data-align="center"
										data-sortable="false" data-width="60"
										data-formatter="FMT_Num_SysRole">序号<input type="hidden" id="refApk"> </th>
									<th data-halign="center" data-align="center"
										data-field="ROLE_CODE" data-width="200">角色编码</th>
									<th data-halign="center" data-align="center"
										data-field="ROLE_NAME" data-width="200">角色名称</th>
									<th data-halign="center" data-align="center"
										data-sortable="false" data-width="50"
										data-formatter="FMT_Oper_SysRole">设置</th>
								</tr>
							</thead>
						</table>
					</div>

			</div>

			<div class="row-fluid col-md-7" style="height:100%;">
				<!-- 按钮工具条开始 -->
				<div class="btn-toolbar" style="height:35px;">
						<div class="btn-group col-lg-2 col-md-2 col-sm-4 col-xs-6">
							<div class="input-group ">
								<input type="text" class="form-control" id="S_SysRole_Name"
									readonly placeholder="请先选中角色，然后再授权">
							</div>
						</div>

						<div class="btn-group">
							<button type="button" id="btn_sq_all" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-check"></i>&nbsp;授权全部
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-check"></i>
								</div>
							</button>
						</div>

						<div class="btn-group">
							<button type="button" id="btn_qx_all" class="btn btn-primary">
								<div class="visible-md visible-lg">
									<i class="icon icon-times"></i>&nbsp;取消全部
								</div>
								<div class="visible-xs visible-sm">
									<i class="icon icon-times"></i>
								</div>
							</button>
						</div>

					<div
						class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<select id="select_root_SysMenu" class="form-control">
							<option value="">显示全部数据</option>
						</select>
					</div>

				</div>
				<!-- 按钮工具条结束 -->
				<div id="tbinfo_TreenmSysMenu_div" style="height:calc(100% - 35px);overflow:auto;" class="row-fluid col-md-12">
					<table id="tbinfo_TreenmSysMenu" class="table-condensed table-hover">
					</table>
				</div>

			</div>
		</div>
		<!-- maincontent -->
	</div>
	</body>
<!-- 不要改变以下引用顺序 -->
<script src="../business/system/sysRole_sysMenu/def_q.js"></script>
<script src="../business/system/sysRole_sysMenu/menu_def_q.js"></script>
<script src="../business/system/sysRole_sysMenu/list.js"></script>