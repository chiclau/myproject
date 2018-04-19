<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="<c:url value='/scripts/web/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/system/logs/logs.js'/>"></script>
<table id="datagrid" style="display: none">
    <thead>
        <tr>
        	<th data-options="field:'item', checkbox:true,align:'center',width:50,resizable:false">选择</th>
        	<th data-options="field:'id',hidden:true,align:'center',width:50">ID</th>
        	<th data-options="field:'organName',width:100,sortable:true">机构</th>
            <th data-options="field:'ip',width:100,sortable:true">IP地址</th>
            <th data-options="field:'module',width:150,sortable:true">功能名称</th>
            <th data-options="field:'createName',width:70,sortable:true">操作人</th>
            <th data-options="field:'operation',width:220,sortable:true">操作名称</th>
            <!-- <th data-options="field:'content',width:350,sortable:true">内容</th> -->
            <th data-options="field:'createTime',width:160,sortable:true">时间</th>
        </tr>
    </thead>
</table>
<input type="hidden" name="id" id="id">
<div id="datagrid-toolbar">
	<form:form id = "logsform">
	<span class="texttitle texttitle-frist"  >机构名称:</span> <input class="easyui-textbox" name="organName" id="organName" style="width:100px;">
	<span class="texttitle texttitle-frist"  >功能名称:</span> <input class="easyui-textbox" name="module" id="module" style="width:80px;">
	<span class="texttitle texttitle-frist"  >操作名称:</span> <input class="easyui-textbox" name="operation" id="operation" style="width:80px;">
	<span class="texttitle texttitle-frist"  >操作人:</span> <input class="easyui-textbox" name="createName" id="createName" style="width:80px;">
	<span class="texttitle texttitle-frist"  >操作日期: </span><input class="easyui-datebox" name="startDate" id="startDate" data-options="formatter:myformatter,parser:myparser,editable:false" style="width:120px;">
    <span class="texttitle texttitle-frist" style="width:20px;" >至:  </span><input class="easyui-datebox" name="endDate" id="endDate" data-options="formatter:myformatter,parser:myparser,editable:false" style="width:120px;">
	<a id="search_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
	<a id="reset_link" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-reload'">重置</a>
	</form:form>
</div>
