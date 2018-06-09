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
					<li class="active">角色功能菜单授权</li>
				</ol>
			</h3>
		</div>
		<div style="display: none;">
			S_SysRole<input type="text" class="form-control" id="S_SysRole" name="S_SysRole"/>
			refApk<input type="text" class="form-control" id="refApk" name="refApk"/>
			roleName<input type="text" class="form-control" id="roleName" name="roleName"/>
			parm1<input type="text" class="form-control" id="parm1" name="parm1" value="tree"/>
		</div>
		<div id="maincontent" class="row-fluid">
			<div class="row-fluid col-md-3">
				<ul class="nav nav-tabs">
					<li class="active"><a data-tab href="#tabContent"
						onclick="Select_S_SysRole('')">系统角色 <i
							class="icon icon-refresh"></i></a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane  active " id="tabContent_SysRole">
						<table id="tbinfo_ListnmSysRole"
							class="table-condensed table-hover">
							<thead>
								<tr>
									<th data-halign="center" data-align="center"
										data-sortable="false" data-width="60"
										data-formatter="FMT_Num_SysRole">序号</th>
									<th data-halign="center" data-align="center" data-sortable="true"
										data-field="ROLE_CODE" data-width="200">角色编码</th>
									<th data-halign="center" data-align="center" data-sortable="true"
										data-field="ROLE_NAME" data-width="200">角色名称</th>
									<th data-halign="center" data-align="center"
										data-sortable="false" data-width="50"
										data-formatter="FMT_Oper_SysRole">设置</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>

			</div>

			<div class="row-fluid col-md-9">
				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar">
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
				<br>
				<div class="row-fluid col-md-12">
					<table id="tbinfo_TreenmSysMenu" class="table-hover">
					</table>
				</div>

			</div>
		</div>
		<!-- maincontent -->
	</div>

</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script src="<%=basePath%>business/system/sysRole_sysMenu/def_q.js"></script>
<script src="<%=basePath%>business/system/sysRole_sysMenu/menu_def_q.js"></script>
<script src="list.js"></script>