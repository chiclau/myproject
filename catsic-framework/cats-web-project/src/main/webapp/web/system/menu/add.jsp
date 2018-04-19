<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="addform" class="easyui-form">
<table  id="menutable" cellpadding="5" style="padding:10px 10px 10px 30px;margin: 30px 10px 0 70px;">
   <tr>
        <td><span style="font-size: 15px;">名称:</span></td>
        <td><input id="name" type="text" name="name" class="easyui-textbox" style="width:200px;height:32px;" data-options="required:true,validType:'length[1,20]',missingMessage:'名称必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
   <tr>
   		<td><span style="font-size: 15px;">路径:</span></td>
        <td><input id="path" type="text" name="path" class="easyui-textbox" style="width:200px;height:32px"></input></td>
   </tr>
   <tr>
   		<td><span style="font-size: 15px;">图标:</span></td>
        <td><input id="icon" type="text" name="icon" class="easyui-textbox" style="width:200px;height:32px" ></input></td>
   </tr>
   <tr>
   		<td><span style="font-size: 15px;">资源类型:</span></td>
        <td><input id="displayAreaCode" type="text" name="displayAreaCode" class="easyui-combobox" style="width:200px;height:32px" data-options="required:true"></input></td>
   </tr>
   <tr>
        <td><span style="font-size: 15px;">顺序:</span></td>
        <td><input id="sort" type="text" name="sort" class="easyui-numberspinner" style="width:200px;height:32px" data-options="required:true,missingMessage:'顺序必须填写',min:0,max:100"></input></td>
   </tr>
   <tr>
   		<td><span style="font-size: 15px;">状态:</span></td>
        <td><input id="status" type="text" name="status" class="easyui-combobox" style="width:200px;height:32px" data-options="required:true,valueField:'id',textField:'text',value:'有效',data: [{id: '有效',	text: '有效'	},{id: '无效',text: '无效'}]"></input></td>
   </tr>
   <tr>
        <td><span style="font-size: 15px;">系统类型:</span></td>
        <td><input id="systemTypeCode" type="text" name="systemTypeCode" class="easyui-combobox" style="width:200px;height:32px" data-options="required:true,missingMessage:'必须填写'"></input></td>
   </tr>
</table>
</form:form>