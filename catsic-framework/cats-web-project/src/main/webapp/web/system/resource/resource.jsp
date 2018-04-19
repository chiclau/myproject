<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="<c:url value='/scripts/web/ztree/css/zTreeStyle/zTreeStyle.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.core-3.5.js'/>"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true,border:false"
		style="width: 250px;" title="权限">
		<div class="content_wrap">
			<div class="zTreeDemoBackground left">
				<ul id="resourceTree" class="ztree"></ul>
			</div>
		</div>
	</div>

	<div data-options="region:'center',border:false,fit:true" title="权限列表">
		<div id="datagrid-toolbar" style="display: none">
				<c:forEach
					items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.resources}"
					var="item">
					<c:if test="${item.code=='100100105101'}">
						<a id="add_link" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-add',plain:true">添加</a>
					</c:if>
					<c:if test="${item.code=='100100105102'}">
						<a id="edit_link" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-edit',plain:true">修改</a>
					</c:if>
					<c:if test="${item.code=='100100105104'}">
						<a id="cancel_link" href="#" class="easyui-linkbutton"
							data-options="iconCls:'icon-remove',plain:true">删除</a>
					</c:if>
				</c:forEach>
<!-- 				<select onchange="$('#datagrid').datagrid({singleSelect:(this.value==0)})">
		            <option value="0">单选</option>
		            <option value="1">多选</option>
		        </select> -->
		</div>
		<table id='datagrid' style="display: none;" >
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'name'" style="width:10%" >名称</th>
					<th data-options="field:'code'" style="width:10%" >编码</th>
					<th data-options="field:'pattern'" style="width:20%" >模式</th>
					<th data-options="field:'views'" style="width:20%" >视图</th>
					<th data-options="field:'status'" style="width:10%" >状态</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<script type="text/javascript"
	src="<c:url value='/scripts/web/system/resource/resource.js'/>"></script>