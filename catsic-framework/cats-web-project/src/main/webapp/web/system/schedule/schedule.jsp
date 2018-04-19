<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table id="datagrid" title="定时器管理" style="display:none">
	<thead>
		<tr>	
		    <th data-options="field:'item',checkbox:true">选择</th>		  
		    <th data-options="field:'id',hidden:true,align:'center',width:50">ID</th>
	        <th data-options="field:'name',width:100,sortable:true">名称</th>
	        <th data-options="field:'group',width:100,sortable:true">组</th>
	        <th data-options="field:'beanClass',width:200,sortable:true">执行类名</th>
	        <th data-options="field:'cronExpression',width:150,sortable:true">秒 分 时 日 月 年</th>
	        <th data-options="field:'startTime',width:120,sortable:true">启动时间</th>
	        <th data-options="field:'prevTime',width:120,sortable:true">上次时间</th>
	        <th data-options="field:'nextTime',width:120,sortable:true">下次时间</th>
	        <th data-options="field:'endTime',width:120,sortable:true">结束时间</th>
			<th data-options="field:'status',width:70,sortable:true">状态</th>
			<!-- <th data-options="field:'desc',width:100,sortable:true">备注</th> -->
		</tr>
	</thead>
</table>
<div id="datagrid-toolbar" style="display:none" >
</div>	
<script type="text/javascript" src="<c:url value='/scripts/web/system/schedule/schedule.js'/>"></script>
    