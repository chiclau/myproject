<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table id="datagrid" style="display: none">
    <thead>
        <tr>
        	<th data-options="field:'item',checkbox:true,align:'center',width:50,resizable:false">选择</th>
        	<th data-options="field:'id',hidden:true,align:'center',width:50">接口ID</th>
            <th data-options="field:'url',width:300,sortable:true">地址</th>
            <th data-options="field:'name',width:100,sortable:true">名称</th>
            <th data-options="field:'methodName',width:50,sortable:true">方法</th>
            <th data-options="field:'type',hidden:true,width:100,sortable:true">类型</th>
            <th data-options="field:'typeName',width:100,sortable:true">类型名称</th>
            <th data-options="field:'organId',width:200,hidden:true,sortable:true">所属省份ID</th>
            <th data-options="field:'organName',width:100,sortable:true">所属省份</th>
            <th data-options="field:'status',width:50,sortable:true">状态</th>
        </tr>
    </thead>
</table>
<form:form id="listform" >
	<input type="hidden" name="id" id="id">
</form:form>

<div id="datagrid-toolbar" style="display: none">
</div>
<script type="text/javascript" src="<c:url value='/scripts/common/drawer.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/scripts/web/system/wservice/wservice.js'/>"></script>
