<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>信息维护</title>
<%@include file="../../../common/inc/inc.inc"%>
<%@include file="../../../common/inc/treegrid.inc"%>
</head>
<body>
	<div class="container-fluid">
		<%@include file="../../../common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">行政区域代码管理</li>
				</ol>
			</h3>
		</div>
		<div id="maincontent" class="row-fluid">
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="row-fluid btn-toolbar">
				<div class="btn-group">
					<button class="btn btn-primary" id="btn_add_addvcd" type="button">
						<div class="visible-md visible-lg">
							<i class="icon icon-file-o"></i>&nbsp;新增根节点
						</div>
						<div class="visible-xs visible-sm">
							<i class="icon icon-file-o"></i>
						</div>
					</button>
				</div>
				<div class="btn-group pull-left visible-lg visible-md visible-sm">
					<button class="btn btn-primary" id="btn_into_add" type="button">
						   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;导入</div>
						   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div>
					</button>
				</div>
				<div
					class="input-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
					<select id="select_pcode_" class="form-control">
						<option value="">显示全部数据</option>
					</select>
				</div>
			</div>
			<!-- 按钮工具条结束 -->
			<br>
			<div class="row-fluid col-md-12">
				<table id="treeGrid_addvcd" class="table-hover">
				</table>
			</div>
		</div>
	</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<%@include file="../../../common/import/importInfo.inc"%>
<script type="text/javascript" src="<%=basePath %>common/import/importInfo.js"></script>
<script src="def.js"></script>
<script src="list.js"></script>
