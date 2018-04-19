<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="actionform" class="easyui-form">
<table cellpadding="5" style="padding:10px 10px 10px 30px;margin: 30px 20px 0 50px;">
   <tr>
        <td><span style="font-size: 15px;display:inline-block;margin-top:15px;">前端组件:</span></td>
        <td><span style="font-size: 10px;">前端页面组件，如：按钮ID等</span><br><input id="assembly" type="text" name="assembly" style="width:200px;height:32px;" class="easyui-textbox"></input></td>
   </tr>
   <tr>
   		<td><span style="font-size: 15px;display:inline-block;margin-top:15px;">函数名称:</span></td>
        <td><span style="font-size: 10px;">组件标识需要执行的前端函数，如：js函数等</span><br>
        <input id="jsFunction" type="text" name="jsFunction" style="width:200px;height:32px" class="easyui-textbox"></input></td>
   </tr>
   <tr> 
        <td><span style="font-size: 15px;display:inline-block;margin-top:15px;">图标:</span></td>
        <td><span style="font-size: 10px;">组件标识需要显示的图标，如：icon-add</span><br><input id="icon" type="text" name="icon" style="width:200px;height:32px" class="easyui-textbox"></input></td>
   <tr>
   <tr> 
        <td><span style="font-size: 15px;">名称:</span></td>
        <td><input id="name" type="text" name="name" style="width:200px;height:32px" class="easyui-textbox"></input></td>
   <tr>
   <tr> 
        <td><span style="font-size: 15px;display:inline-block;margin-top:15px;">模式:</span></td>
        <td><span style="font-size: 10px;">后台控制器，如：springMVC控制器中的方法等</span><br><input id="pattern" type="text" name="pattern" style="width:200px;height:32px" class="easyui-textbox"></input></td>
   <tr>
   		<td><span style="font-size: 15px;display:inline-block;margin-top:15px;">视图:</span></td>
        <td><span style="font-size: 10px;">前端页面，如：jsp或HTML等</span><br><input id="views" type="text" name="views" style="width:200px;height:32px" class="easyui-textbox"></input></td>
   </tr>
   <tr>
        <td><span style="font-size: 15px;display:inline-block;margin-top:15px;">行为类型:</span></td>
        <td><span style="font-size: 10px;">行为类型：标识是否需要在前端生成组件，如：生成按钮；公共权限：表示无需生成组件</span><br>
        <input id="drawerCode" type="text" name="drawerCode" style="width:200px;height:32px" class="easyui-combobox" data-options=""></input></td>
   </tr>
   <tr>
   		<td><span style="font-size: 15px;display:inline-block;margin-top:15px;">授权分类:</span></td>
        <td><span style="font-size: 10px;">注：所属功能，具体就是对应页面上或功能上的按钮</span><br><input id="authAreaCode" type="text" name="authAreaCode" style="width:200px;height:32px" class="easyui-combobox"></input></td>
   </tr>
</table>
</form:form>