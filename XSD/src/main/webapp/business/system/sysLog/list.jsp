<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>信息维护</title>
<%@include file="/common/inc/inc.inc"%>
<%@include file="/common/inc/bootstrapTable.inc"%>
<%@include file="/common/inc/treegrid.inc"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="/common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">系统日志管理</li>
				</ol>
			</h3>
		</div>
		<div style="display: none;"></div>
		<div id="maincontent" class="row-fluid">

			<div class="row-fluid col-md-12">

				<!-- 按钮工具条开始 -->
				<div id="tbar" class="btn-toolbar">

					<div
						class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
						<div class="input-group ">
							<input type="text" id="searchName" class="form-control"
								placeholder="输入关键字进行模糊查询"> <span class="input-group-btn">
								<button class="btn" id="btn_ref" type="button">
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

					<div class="btn-group pull-right visible-lg visible-md visible-sm">
						<select class="chosen-select form-control"
							data-placeholder="选择操作类型..." id="S_DictnmOpttype"">
						</select>
					</div>

				</div>
				<br>
				<!-- 按钮工具条结束 -->
				<table id="tbinfo" class="table-condensed table-hover">
					<thead>
						<tr>
							<th data-halign="center" data-align="center"
								data-sortable="false" data-width="60" data-formatter="FMT_Num">
								序号</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="LOGTIME" data-width="80">操作时间</th>
							<th data-halign="center" data-align="left" data-sortable="true"
								data-field="NAME" data-width="100">操作员</th>
							<th data-halign="center" data-align="left" data-sortable="false"
								data-field="MENUFLAG" data-width="100">模块唯一标识</th>
							<th data-halign="center" data-align="center" data-sortable="true"
								data-field="EXAMINE" data-width="80">操作类型</th>
							<th data-halign="center" data-align="left" data-sortable="true"
								data-field="OLDDATA" data-width="250">旧数据</th>
							<th data-halign="center" data-align="left" data-sortable="true"
								data-field="NEWDATA" data-width="250">新数据</th>
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
<script src="<%=basePath%>business/system/sysDict/def_q.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>
