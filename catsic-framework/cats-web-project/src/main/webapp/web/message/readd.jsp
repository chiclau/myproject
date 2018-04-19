<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',border:false,width:'500px'">
		  <form:form id="addform" >
			  <table class="show-information" style="width:450px">
			   <tr>
			        <td><span>标题:</span></td>
			        <td>
			        	<input id="title2" class="easyui-textbox"  data-options="multiline:true,height:'40px'" name="title" style="width:275px;">
			        </td>
			   </tr>
			   <tr>
			        <td><span>正文:</span></td>
			        <td>
			        	<input id="contents2" class="easyui-textbox"  data-options="multiline:true,height:'150px'" name="content" style="width:275px;">
			        </td>
			   </tr>
				<tr>
			        <td><span>发件人:</span></td>
			        <td>
			        	<select id="adduser" class="easyui-combobox" data-options="width:'200px',required:true,missingMessage:'必须填写'"></select>
			        </td>
			   </tr>
			</table>
		</form:form>
		<table style="border:1px solid #dddddd;border-collapse:collapse;border-spacing:0;">
			<tr>
				<td>
					<table id="addGrid" style="display:none" data-options="region:'center',width:'400px',border:false">
						<thead>
							<tr>
								<th data-options="field:'item',checkbox:true">选择</th>
						     	<th data-options="field:'addresseeId',width:1,sortable:true,hidden:true">收件人ID</th><!-- ,hidden:true -->
						     	<th data-options="field:'addresseeName',width:220,sortable:true">收件人</th>
						     	<th data-options="field:'code',sortable:true,hidden:true">收件人类型code</th>
						     	<th data-options="field:'addresseeTypeName',width:100,sortable:true">类型</th>
							</tr>
						</thead>
					</table>
				</td>
				<td>
					<a id="delAddressee" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" >删除</a>
				</td>
			</tr>
		</table>
	</div>
	
	
	<div data-options="region:'east',border:false,split:true"  style="width:42%">
		<table style="width:300px;font-size:12px;">
			<tr>
		        <td>
		        	<span>收件人类型:</span>
		        </td>
		        <td>
		        	<select id="code" class="easyui-combobox" data-options="width:'130px',required:true,missingMessage:'必须填写'"></select>
		        	<a id="addAddressee" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
		        </td>
		   </tr>
		   <tr>
		   		<td>
		        		<span>手机提醒:</span>
		        </td>
		        <td>
		        <input type="radio" name="remind" value="no" id="no" checked="checked">
		        <label for="no">否</label>
		        <input type="radio" name="remind" id="yes" value="yes">
		        <label for="yes">是</label>
		        </td>  
		   
		   </tr>
			<tr>
				<td colspan="2" style="height: 360px">
					<table id="listGrid" style="display:none" data-options="region:'center',border:false,idField:'addresseeId'" >
						<thead>
						<tr>
							<th data-options="field:'item',checkbox:true">选择</th>
					     	<th data-options="field:'addresseeId',width:50,sortable:true,hidden:true">收件人ID</th><!-- ,hidden:true -->
					     	<th data-options="field:'addresseeName',width:120,sortable:true">收件人</th>
					     	<th data-options="field:'code',sortable:true,hidden:true">收件人类型code</th>
					     	<th data-options="field:'addresseeTypeName',width:38,sortable:true">类型</th>
						</tr>
						</thead>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/message/readd.js'/>"></script>