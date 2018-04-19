<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<c:url value='/scripts/web/ztree/css/zTreeStyle/zTreeStyle.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.core-3.5.js'/>"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true,border:false"
		style="width: 300px;" title="抽屉">
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="drawerTree" class="ztree"></ul>
			</div>
		</div>
	</div>

	<div data-options="region:'center',border:false,fit:true" title="抽屉列表">
		<div id="datagrid-toolbar" style="display: none">
		</div>
		<table id='datagrid' style="display: none">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'name',width:100">名称</th>
					<th data-options="field:'code',width:100">编码</th>
					<th data-options="field:'value',width:200">值</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/system/drawer/drawer.js'/>"></script>