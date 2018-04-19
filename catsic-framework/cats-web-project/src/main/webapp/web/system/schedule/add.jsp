<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="addform" method="post">
<table class="show-information">
   <tr>
        <td><span>定时器名称:</span></td>
        <td><input id="name" type="text" name="name" class="easyui-textbox" style="width:120px;height:32px" data-options="required:true,validType:'length[1,20]',missingMessage:'名称必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
        <td><span>定时器组名:</span></td>
        <td><input id="group" type="text" name="group" class="easyui-textbox" style="width:120px;height:32px"  data-options="required:true,validType:'length[1,20]',missingMessage:'必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
        <!-- <td><span>类型:</span></td>
        <td><input id="startUpType" type="text" name="type" class="easyui-combobox" style="width:100px" data-options="required:true,validType:'length[1,20]',missingMessage:'启动类型必须填写',invalidMessage:'不能超过20个字符！'"></input></td> -->
   </tr>
   <tr>
   		<td><span>表达式:</span></td>
        <td colspan="3">
        	<input id="cronExpression" type="text" name="cronExpression" class="easyui-textbox" style="width:300px;height:32px"  data-options="required:true,missingMessage:'必须填写'"></input>
        	<a id="cron" style="font-size: 10px;cursor: pointer;color:red" target="_blank" href="#">表达式组件</a>
        </td>
   </tr>
   <tr>
        <td><span>执行类名:</span></td>
        <td colspan="3"><input id="beanClass" type="text" name="beanClass" class="easyui-textbox" style="width:365px;height:32px" data-options="required:true,missingMessage:'必须填写'"></input></td>
   </tr>
   <tr >
        <td><span>说明:</span></td>
        <td colspan="3"><input id="note" type="text" name="note" style="width:365px;height:32px" class="easyui-textbox" data-options="multiline:true,height:'50px',width:'300px'" ></input></td>
   </tr>
</table>
</form:form>