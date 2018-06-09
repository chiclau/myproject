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
					<li class="active">用户管理</li>
				</ol>
			</h3>
		</div>
		<div style="display: none;"></div>
		<div id="maincontent" class="row-fluid">

			<div class="row-fluid col-md-12">

				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar">
					<div class="btn-group">
						<button type="button" id="btn_add_user" class="btn btn-primary">
							<div class="visible-md visible-lg">
								<i class="icon icon-file-o"></i>&nbsp;新增
							</div>
							<div class="visible-xs visible-sm">
								<i class="icon icon-file-o"></i>
							</div>
						</button>
					</div>
					<div class="btn-group">
						<button type="button" id="btn_del_user" class="btn btn-primary">
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
							<input type="text" id="searchName_user" class="form-control"
								placeholder="输入关键字进行模糊查询"> <span class="input-group-btn">
								<button class="btn" id="btn_ref_user" type="button">
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
				<table id="tbinfo_user" class="table-condensed table-hover">
					<thead>
						<tr>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-field="STATE" data-checkbox="true"
								data-formatter="FMT_Check_user"></th>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-width="60" data-formatter="FMT_Num_user">
								序号</th>
							<th data-halign="center" data-align="center" 
								data-sortable="true" data-visible="false"
								data-field="STAFF_CODE" data-width="100">人员编号</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="STAFF_NAME" data-width="200">姓名</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="USER_NAME" data-width="200">账号</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="LINK_PHONE" data-width="200">联系方式</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="TREENM_DEPT" data-width="200">所属单位</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="STAFF_DEPT" data-width="200">部门</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-formatter="FMT_state"
								data-field="USER_STATE" data-width="200">状态</th>
							<th data-halign="center" 
								data-sortable="false" data-width="100" 
								data-formatter="FMT_Oper_user">操作</th>
						</tr>
					</thead>
				</table>

			</div>
		</div>
	</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<%@include file="flag.jsp"%>
<script src="def.js"></script>
<script src="list.js"></script>
<script type="text/javascript" src="<%=basePath %>common/inc/city.js"></script>
