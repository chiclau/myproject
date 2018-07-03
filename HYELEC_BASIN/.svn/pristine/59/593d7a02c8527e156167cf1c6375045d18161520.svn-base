<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>信息维护</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/bootstrapTable.inc"%>
<%@include file="../../../common/inc/treegrid.inc"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">系统角色管理</li>
				</ol>
			</h3>
		</div>
		<div style="display: none;"></div>
		<div id="maincontent" class="row-fluid">
			<div class="row-fluid col-md-12">
				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar">
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
					<div
						class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group ">
							<input type="text" id="searchName_role" class="form-control"
								placeholder="输入关键字进行模糊查询"><span class="input-group-btn">
								<button class="btn" id="btn_ref_role" type="button">
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
				<br>
				<!-- 按钮工具条结束 -->
				<table id="tbinfo_role" class="table-condensed table-hover">
					<thead>
						<tr>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-field="state" data-checkbox="true"
								data-formatter="FMT_Check_Role"></th>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-width="60" data-formatter="FMT_Num_Role">
								序号</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="ROLE_CODE" data-width="200">角色编码</th>
							<th data-halign="center" data-align="center" data-sortable="true"
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
		<!-- maincontent -->
	</div>

</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<%@include file="../../../business/system/sysGroup/setUser.jsp"%>
<%@include file="setGroup.jsp"%>
<script src="def.js"></script>
<script src="list.js"></script>
