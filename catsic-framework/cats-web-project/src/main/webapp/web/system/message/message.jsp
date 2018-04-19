<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table id="datagrid" title="消息列表" style="display:none">
    <thead>
        <tr>
        	<th data-options="field:'item',checkbox:true">选择</th>
        	<th data-options="field:'id',hidden:true,align:'center',width:50">ID</th>
     	    <th data-options="field:'acceptObjId',hidden:true,width:50,sortable:true">接收对象ID</th>
            <th data-options="field:'acceptObjName',width:100,sortable:true">接收对象名称</th>
            <th data-options="field:'title',width:200,sortable:true">标题</th>
            <th data-options="field:'content',width:200,sortable:true">内容</th>
            <th data-options="field:'url',width:100,sortable:true">URL</th>
            <th data-options="field:'status',width:50,sortable:true">状态</th>
        </tr>
    </thead>
</table>
<script type="text/javascript" src="<c:url value='/scripts/web/system/message/message.js'/>"></script>