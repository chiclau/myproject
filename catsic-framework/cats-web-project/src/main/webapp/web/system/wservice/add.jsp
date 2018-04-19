<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="addform" class="easyui-form" style="padding:5px">
<input type="hidden" name="id" id="id"> 
	<table class="show-information">
	   <tr>
	        <td><span>接口名称:</span></td>
	        <td><input id="name" type="text" name="name"  style="width:300px;height:32px" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'接口名称必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
	   </tr>
	      <tr>
	        <td><span>接口类型:</span></td>
	        <td id="ttype">
	        <select id="type" style="width:300px;height:32px" class="easyui-combobox" data-options="required:true,missingMessage:'接口类型必须填写'" name="type">
	        </select>
	        </td>
	   </tr>
	   <tr>
	        <td><span>方法名称:</span></td>
	        <td><input id="methodName" type="text" name="methodName" style="width:300px;height:32px" class="easyui-textbox"></input></td>
	   </tr>
	   <tr>
	   		<td><span>URL地址:</span></td>
	        <td><input id="url" type="text"  name="url" style="width:300px;height:32px" class="easyui-textbox" data-options="required:true,validType:'url',missingMessage:'url必须填写'"></input></td>
	   </tr>
	</table>
</form:form>