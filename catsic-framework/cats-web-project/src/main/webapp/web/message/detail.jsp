<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',border:false,width:630">
		  <form:form id="addform" >
			  <table class="show-information">
			   <tr>
			        <td><span>标题:</span></td>
			        <td>
			        	<input id="titleShow" class="easyui-textbox"  data-options="multiline:true,height:'40px'" name="title" style="width:400px;">
			        </td>
			   </tr>
			   <tr>
			        <td><span>正文:</span></td>
			        <td>
			        	<input id="contentShow" class="easyui-textbox"  data-options="multiline:true,height:'250px'" name="content" style="width:400px;">
			        </td>
			   </tr>
				<tr>
			        <td><span>发件人:</span></td>
			        <td>
			        	<input id="adduserShow" class="easyui-textbox" data-options="width:'400px'">
			        </td>
			   </tr>
				<tr>
			        <td><span>收件人:</span></td>
			        <td>
			        	<input id="addresseeNameShow" class="easyui-textbox" data-options="width:'400px'">
			        </td>
			   </tr>
				<tr>
			        <td><span>收件人类型:</span></td>
			        <td>
			        	<input id="addresseeTypeNameShow" class="easyui-textbox" data-options="width:'400px'">
			        </td>
			   </tr>
			</table>
		</form:form>
	</div>	
</div>
<script type="text/javascript" src="<c:url value='/scripts/web/message/add.js'/>"></script>