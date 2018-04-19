<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<c:url value='/scripts/web/ztree/css/zTreeStyle/zTreeStyle.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.core-3.5.js'/>"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true,border:false"
		style="width: 250px;" title="资源树">
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="menuTree" class="ztree"></ul>
			</div>
		</div>
	</div>

	<div data-options="region:'center',border:false,fit:true" title="系统资源">
		<div id="resource" class="easyui-tabs" data-options="tabWidth:112,split:true,fit:true,border:false">
		    <div id = "menu_grant" title="资源管理" data-options="tabWidth:112,split:true,border:false">
		    	<div id="datagrid-toolbar" style="display: none">
						<a id="add_link" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-add',plain:true">添加</a>
						<a id="edit_link" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-edit',plain:true">修改</a>
						<a id="cancel_link" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove',plain:true">删除</a>
				</div>
				<table id='datagrid' style="display: none">
					<thead>
						<tr>
							<th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'name'" style="width:10%">名称</th>
							<th data-options="field:'code'" style="width:10%">编码</th>
							<th data-options="field:'icon'" style="width:10%">图标</th>
							<th data-options="field:'path'" style="width:15%">路径</th>
							<th data-options="field:'displayArea'" style="width:10%">显示区域</th>
							<th data-options="field:'systemType'" style="width:10%">系统类型</th>
							<th data-options="field:'sort'" style="width:6%">顺序</th>
							<th data-options="field:'status'" style="width:10%">状态</th>
						</tr>
					</thead>
				</table>
		    </div>
		    <div id = "action_grant" title="行为管理" data-options="tabWidth:112,split:true,border:false">
	    		<div id="action_datagrid-toolbar" style="display: none">
						<a id="addAction" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-add',plain:true">添加</a>
						<a id="editAction" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-edit',plain:true">修改</a>
						<a id="deleteAction" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove',plain:true">删除</a>
				</div>
				<table id='action_datagrid' style="display: none;" >
					<thead data-options="frozen:true">
			            <tr>
			                <th data-options="field:'id',hidden:true">ID</th>
							<th data-options="field:'assembly'" style="width:7%" >组件标识</th>
							<th data-options="field:'jsFunction'" style="width:6%" >函数名称</th>
							<th data-options="field:'name'" style="width:7%" >名称</th>
							<th data-options="field:'code'" style="width:10%" >编码</th>
							<th data-options="field:'icon'" style="width:8%" >图标</th>
							<th data-options="field:'pattern'" style="width:10%" >模式</th>
							<th data-options="field:'views'" style="width:10%" >视图</th>
							<th data-options="field:'drawerName'" style="width:9%" >行为类型</th>
							<th data-options="field:'authArea'" style="width:8%" >授权分类</th>
							<th data-options="field:'status'" style="width:6%" >状态</th>
						</tr>
					</thead>
				</table>
	    	</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/system/menu/menu.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/system/resource/resource.js'/>"></script>