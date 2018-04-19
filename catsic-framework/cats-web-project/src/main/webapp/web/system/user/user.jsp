<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="easyui-layout" data-options="fit:true,border:false" >
     <div data-options="region:'west',split:true,border:false" title="机构" style="width:400px;">
		<%@ include file= "../../tree/organ.jsp" %>
     </div>
     <div data-options="region:'center',border:false" title="用户列表">
		 <table id="datagrid" style="display:none">
			    <thead>
			        <tr>
			        	<th data-options="field:'item',checkbox:true,width:50"></th>
			        	<th data-options="field:'id',hidden:true,align:'center',width:50">用户ID</th>
			            <th data-options="field:'username',width:100,sortable:true">登录昵称</th>
			            <th data-options="field:'realname',width:150,sortable:true">真实姓名</th>
			            <th data-options="field:'identity',width:200,sortable:true">身份证号</th>
			            <th data-options="field:'mobile',width:150,sortable:true">联系电话</th>
			            <th data-options="field:'email',width:200,sortable:true">电子邮件</th>
			            <th data-options="field:'status',width:100,sortable:true">状态</th>
			            <th data-options="field:'departName',hidden:true,width:200,sortable:true">部门名称</th>
			            <th data-options="field:'organName',hidden:true,width:200,sortable:true">机构名称</th>
			        </tr>
			    </thead>
		 </table>
     </div>
</div>
<div id="datagrid-toolbar" style="display:none">
	<form id = "listform" style="margin-top:0px;margin-bottom:0px">
		<!-- <span class="texttitle texttitle-frist"  >部门名称:</span> <input class="easyui-textbox" name="dname" id="dname" style="width:150px;"> -->
		<span class="texttitle texttitle-frist"  >用户昵称:</span> <input class="easyui-textbox" name="uname" id="uname" style="width:150px;">
	</form>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/system/user/user.js'/>"></script>