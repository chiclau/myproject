<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="easyui-layout" data-options="fit:true,border:false">
     <div data-options="region:'west',split:true,border:false" style="width:400px;" title="机构">
		<%@ include file= "../../tree/organ.jsp" %>
     </div>

     <div data-options="region:'center',border:false,fit:true" title="机构列表">
	   <div id="datagrid-toolbar" style="display:none">
		</div>
		<table id='datagrid' style="display:none">
			<thead>
				<tr>
					<th data-options="field:'id',hidden:true">ID</th>
					<th data-options="field:'name',width:100">名称</th>
					<th data-options="field:'code',width:100">编码</th>
				</tr>
			</thead>
		</table>
     </div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/system/organ/organ.js'/>"></script>