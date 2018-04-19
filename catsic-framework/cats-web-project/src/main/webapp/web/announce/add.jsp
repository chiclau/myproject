<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="easyui-layout" data-options="fit:true,border:false">
       <div data-options="region:'west',split:true,border:false" style="width:250px;">
     		<div id = 'organTree'></div>
     		<form:form id="form" method="post" >
     		<input type="hidden" name="id" id="id" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.organId}">
     		</form:form>
     </div>
      <div data-options="region:'center',split:true,border:false">
        <form:form id="addform" method="post" >
        <input type="hidden" name="createId" id="createId" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userId}">
	<table id= "myTable" class="easyui-form show-information">	
   <tr>
        <td><span>标题:</span></td>
        <td><input id="title" type="text" name="title" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'标题必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
   
   <tr>
        <td><span>公告类型:</span></td>
        <td>
        	<input class="easyui-combobox" 
			            id="type" name="type" data-options="required:true,missingMessage:'公告类型必须填写'">
        </td>
   </tr>

   <tr>
        <td><span>通知内容:</span></td>
        <td>
        	<input id="content" type="text" name="content" class="easyui-textbox" data-options="required:true,missingMessage:'公告内容必须填写',multiline:true,width:300,height:50,"></input>
        </td>
   </tr>
    <tr>
        <td><span>有效期至:</span></td>
        <td>
        	 <input class="easyui-datebox" id="finishDate" type="datetime" name="finishDate" data-options="required:true,missingMessage:'时间必须填写',validType:'notLessThanCurrentDate[finishDate]'"></input>
        </td>
   </tr> 
</table>
</form:form>
</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/announce/add.js'/>"></script>