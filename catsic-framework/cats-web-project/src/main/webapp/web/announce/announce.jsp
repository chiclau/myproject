<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8" %>
	<%-- <table id="datagrid" title="通知公告管理" style="display:none" >
				<thead>
					<tr>
				       	<th data-options="field:'item',checkbox:true,width:50">ID</th>
			           <th data-options="field:'title',width:100,sortable:true">标题</th>
			           <th data-options="field:'typeName',width:100,sortable:true">公告类型</th>
			           <th data-options="field:'finishDate',width:150,sortable:true">截止日期</th>
			           <th data-options="field:'createName',width:100,sortable:true">发布人</th>
					</tr>
				</thead>
			</table>
	 <div id="datagrid-toolbar" >
		<form:form id="listform">
	        <label for="search_announceType">公告类型:</label>
			<input class="easyui-combobox" id="search_announceType" name="typeName">
			<label for="search_title">公告标题:</label><input type="text" class="easyui-textbox" id="search_title" name="title"/>
			<c:forEach items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.resources}" var="item"> 
   		<c:if test="${item.id=='500300100100'}">
   		<a id="search_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询 </a>
   		</c:if>
	</c:forEach>
		</form:form>
		<c:forEach items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.resources}" var="item"> 
   		<c:if test="${item.id=='500300100101'}">
   		<a id="add_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
   		</c:if>
   		<c:if test="${item.id=='500300100103'}">
   		<a id="view_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">详情</a>
   		</c:if>
   		<c:if test="${item.id=='500300100102'}">
   		<a id="delete_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
   		</c:if>
	</c:forEach>
   		<a id="upload_link" href="#" class="easyui-linkbutton icon-uploading" >上传附件</a>
	</div>
	<div id="dialog">
	</div>
	<div id="dialogs">
	</div>
<script type="text/javascript" src="<c:url value='/scripts/web/announce/announce.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/announce/view.js'/>"></script> --%>