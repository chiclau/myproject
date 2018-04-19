<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="width:100%;">
		  <form id="addform" style="padding:0;margin:0">
				<input type="hidden" id="userId"  value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userId}">
				<input type="hidden" id="userName"  value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
			<table class="show-information" style="margin:0;width:100%;height:100%;">
			  	<tr>
			        <td align="right"  style="width:60px;height:35px;">发件人:</td>
			        <td>
			        	<input id="adduser" class="easyui-combobox" data-options="width:'220px',height:'30px',required:true,missingMessage:'必须填写'"/>
			        </td>
			   </tr>
			   <tr>
			        <td align="right"  style="width:60px;height:35px;">收件类型:</td>
			        <td>
			        	<input id="code" class="easyui-combobox" data-options="width:'220px',height:'30px',required:true,missingMessage:'必须填写'"/>
			        </td>
			   </tr>
			   <tr>
			   		<td align="right"  style="width:60px;height:35px;">手机提醒:</td>
			   		<td>
			   			<input type="radio" name="remind" value="no" id="no" checked="checked">
				        <label for="no">否</label>
				        <input type="radio" name="remind" id="yes" value="yes">
				        <label for="yes">是</label>
			   		</td>
			   </tr>
			   <tr>
			        <td align="right" style="width:60px;height:35px;">标题:</td>
			        <td>
			        	<input id="title" class="easyui-textbox"  data-options="required:true,missingMessage:'必须填写',multiline:true,height:'40px'" name="title" style="width:100%;">
			        </td>
			   </tr>
			   <tr>
			        <td align="right"  style="width:60px;">正文:</td>
			        <td>
			        	<input id="content" class="easyui-textbox"  data-options="required:true,missingMessage:'必须填写',multiline:true" name="content" style="width:100%;height:96%">
			        </td>
			   </tr>
			   <tr>
			   		<td align="right" style="width:60px;height:35px;">操作:</td>
			   		<td><a id="save_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">发送</a></td>
			   </tr>
			</table>					   	
		</form>
	</div>
	
	
	<div data-options="region:'east',border:false,split:true"  style="width:23%;">
	<div style="background:#CCCCCC;">
	<!-- 	<input id="search" class="easyui-combobox" data-options="width:'180px'"/> -->
				<a id="addAddressee" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
			</div>
			<div style="height:50%;border-bottom:1px solid #95b8e7">
			<table id="listGrid" style="display:none;" data-options="region:'center',border:false,idField:'addresseeId'" >
				<thead>
					<tr>
						<th data-options="field:'item',checkbox:true">选择</th>
						<th data-options="field:'addresseeId',sortable:true,hidden:true">收件人ID</th><!-- ,hidden:true -->
						<th data-options="field:'addresseeName',width:120,sortable:true">收件人</th>
						<th data-options="field:'code',sortable:true,hidden:true">收件人类型ID</th>
						<th data-options="field:'addresseeTypeName',width:38,sortable:true">类型</th>
					</tr>
				</thead>
			</table>
			</div>
			<div style="background:#CCCCCC;">
				<a id="delAddressee" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >删除</a>
			</div>
			<table id="addGrid" style="display:none;height:200px;" data-options="region:'center',border:false">
				<thead>
				<tr>
					<th data-options="field:'item',checkbox:true">选择</th>
					<th data-options="field:'addresseeId',sortable:true,hidden:true">收件人ID</th><!-- ,hidden:true -->
					<th data-options="field:'addresseeName',width:190,sortable:true">收件人</th>
					<th data-options="field:'code',sortable:true,hidden:true">收件人类型ID</th>
					<th data-options="field:'addresseeTypeName',width:38,sortable:true">类型</th>
				</tr>
				</thead>
			</table>
			


	</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/message/write.js'/>"></script>