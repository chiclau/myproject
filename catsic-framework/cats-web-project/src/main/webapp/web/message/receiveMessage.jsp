<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<div id="datagrid-toolbar">
	<form id="listform">
		<input type="hidden" id="organId" name="organId" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.organId}">
		<input type="hidden" id="userId"  value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userId}">
		<input type="hidden" id="userName"  value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
		<label for="user">发件人：</label>
		<input id="senduser" class="easyui-combobox" data-options="width:'164px',required:true,missingMessage:'必须填写'"/>
		<label for="addressee">收件人：</label>
		<input id="addressee" class="easyui-combobox" data-options="width:'164px',required:true,missingMessage:'必须填写'"/>
   				<a id="search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询 </a>
   				<a id="back_message" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-edit'">回信</a>
   				<a id="del_message" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-remove'">删除</a>
   				<a id="review" href="#" class="easyui-linkbutton icon-view">查看</a>	
	</form>
</div>
<table id="datagrid" title="收件箱" style="display:none">
	<thead>
		<tr>
			<th data-options="field:'item',checkbox:true">选择</th>
			<th data-options="field:'receiveMessageId',hidden:true">收件ID</th>
			<th data-options="field:'userId' ,hidden:true">发件人ID</th>
	     	<th data-options="field:'userName',width:50,sortable:true">发件人</th>
			<th data-options="field:'title',width:70">邮件主题</th>
	        <th data-options="field:'content',width:250">邮件内容</th>
	     	<th data-options="field:'addresseeId' ,hidden:true">收件人ID</th>
	     	<th data-options="field:'addresseeName',width:50,sortable:true">收件人</th>
	     	<th data-options="field:'code',width:50,hidden:true">收件人类型code</th>
	     	<th data-options="field:'addresseeTypeName',width:50,sortable:true">收件人类型</th>	     	
		</tr>
	</thead>
</table>
<script type="text/javascript" src="<c:url value='/scripts/web/message/receiveMessage.js'/>"></script>