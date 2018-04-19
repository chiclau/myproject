<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<c:url value='/scripts/web/ztree/css/zTreeStyle/zTreeStyle.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.core-3.5.js'/>"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true,border:false"
		style="width: 300px;" title="资源树">
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="resourceTree" class="ztree"></ul>
			</div>
		</div>
	</div>

	<div data-options="region:'center',border:false" title="授权树">
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="authAreaTree" class="ztree"></ul>
			</div>
		</div>	
	</div>
	
	<div data-options="region:'east',split:true,border:false" style="width: 700px;">
		<div id="datagrid-toolbar" style="display: none">
				<a id="add_link" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true">添加</a>
				<a id="edit_link" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit',plain:true">修改</a>
				<a id="cancel_link" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:true">删除</a>
				<a id="default" href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true">默认</a>
		</div>
		<table id='datagrid' style="display: none">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'name'" style="width:30%">名称</th>
					<th data-options="field:'code'" style="width:30%">编码</th>
					<th data-options="field:'status'" style="width:20%">状态</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/system/autharea/autharea.js'/>"></script>