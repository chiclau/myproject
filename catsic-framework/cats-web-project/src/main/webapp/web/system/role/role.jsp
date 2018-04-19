<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<c:url value='/scripts/web/ztree/css/zTreeStyle/zTreeStyle.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.core-3.5.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.excheck-3.5.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.exedit-3.5.js'/>"></script>
<div id="role_layout" class="easyui-layout" data-options="border:false,fit:true">
    <div data-options="region:'west',split:true,border:false" style="width:400px;">
    	<table id="datagrid" title="角色列表" style="display:none">
		    <thead>
		        <tr>
		        	<th data-options="field:'organId',hidden:true">机构ID</th>
		            <th data-options="field:'organName',width:650,hidden:true">机构名称</th>
		        	<th data-options="field:'id',hidden:true,align:'center'">角色ID</th>
		            <th data-options="field:'name',width:600">角色名称</th>
		        </tr>
		    </thead>
		</table>
		<input type="hidden" id="rt" value = "rt">
    </div>
    <div data-options="region:'center',split:true,border:false" title="授权管理">
    	<div id="datagrid-toolbar" style="display:none;border-top: 0px">
			<!-- <a id="add_link" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-add'">添加</a>
			<a id="edit_link" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-activate'">修改</a>
			<a id="delete_link" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-remove'">删除</a>
			<a id="bind_link" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-bind'" >绑定资源</a>
			<a id="bind_organ_link" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-bind'" href="#" >绑定机构</a> -->
		</div>
        <div id="grant" class="easyui-tabs" data-options="tabWidth:112,split:true,fit:true,border:false">
		    <div id = "resource_grant" title="资源授权" data-options="tabWidth:112,split:true,border:false">
		    </div>
		    <div id = "organ_grant" title="机构授权" data-options="tabWidth:112,split:true,border:false">
	    	</div>
		</div>
    </div>
</div>


<script type="text/javascript" src="<c:url value='/scripts/web/system/role/role.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/common/json2.js'/>"></script>