<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="easyui-layout" data-options="fit:true,border:false">
      <div data-options="region:'center',split:true,border:false">
        <form:form id="addform" method="post" >
	<table id= "myTable" class="easyui-form show-information"  >	
   <tr>
        <td ><span>标题:</span></td>
        <td ><span id="title"  ></span></td>
   </tr>
   
   <tr>
        <td ><span>公告类型:</span></td>
        <td ><span id="typeName" ></span></td>
   </tr>
   <tr>
         <tr>
        <td><span>操作人:</span></td>
        <td ><span id="createName" ></span></td>
   </tr>
      <tr>
        <td><span>有效期至:</span></td>
        <td ><span id="finishDate" ></span></td>
   </tr>
   <tr>
        <td ><span>公告内容:</span></td>
        <td ><span id="content" ></span></td>
   </tr>
</table>
</form:form>
    <div data-options="region:'center',split:true,border:false">
	    <div id="attachment-list">
	        <table id="attachment" class="easyui-datagrid" data-options="border:false">
		        <thead>
		            <tr>
		                <th data-options="field:'id',width:80,hidden:'true'">附件ID</th>
		                <th data-options="field:'fileName',width:340,formatter:formatlink">附件名称</th>
		                <th data-options="field:'opt',width:65,align:'center',formatter:formatFile">操作</th>
		            </tr>
		        </thead>
	       </table>
	    </div>
	</div>
</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/announce/view.js'/>"></script>
