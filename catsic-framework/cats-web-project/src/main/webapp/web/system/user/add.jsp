<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
.verifyPsd_easy{width:0px;height:16px;margin-top:6px;margin-left:10px;background-color:#ff6633;text-align:center;line-height:16px;font-size:10px;color:white; font-family:"宋体"}
.verifyPsd_middle{width:0px;height:16px;margin-top:6px;margin-left:10px;background-color:#ffcc33;text-align:center;line-height:16px;font-size:10px;color:white; font-family:"宋体"}
.verifyPsd_hard{width:0px;height:16px;margin-top:6px;margin-left:10px;background-color:#339933;text-align:center;line-height:16px;font-size:10px;color:white; font-family:"宋体"}
</style>
<script type="text/javascript" src="<c:url value='/scripts/common/pwdintensity.js'/>"></script>
<form:form id="addform" class="easyui-form" style="padding:20px 25px 5px 25px">
<input type="hidden" id="id" name="id">
<table class="show-information">
   <tr>
        <td><span>登录昵称:</span></td>
        <td><input id="username" style="width:200px;height:32px" type="text" name="username" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'登录昵称必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
   <tr id="dlmm">
        <td><span>登录密码:</span></td>
        <td><input id="password" style="width:200px;height:32px" type="password" name="password" class="easyui-textbox" data-options="required:true,validType:['pwd'],missingMessage:'登录密码必须填写'"></input></td>
   </tr>
   <tr  id="qrmm">
        <td><span>确认密码:</span></td>
        <td><input id="rpw" style="width:200px;height:32px" type="password" class="easyui-textbox"  required="required" missingMessage="确认密码必须填写" data-options="validType:'equalTo[password]'"></input></td>
   </tr>
   <tr id="mmqd">
   		<td><span>密码强度:</span></td>
        <td><div id="PasswordCheck"  style="text-align:center"></div></td>
   </tr>
   <tr>
   		<td><span>真实姓名:</span></td>
        <td><input id="realname" style="width:200px;height:32px" type="text"  name="realname"  class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'真实姓名必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
   <tr>
        <td><span>身份证号:</span></td>
        <td><input id="identity" style="width:200px;height:32px" type="text" name="identity" class="easyui-textbox" data-options="validType:'idcard'"></input></td>
   </tr>
   <tr>
        <td><span>所属部门:</span></td>
        <td><input id="departId" style="width:200px;height:32px" type="text" name="departId" class="easyui-combobox" data-options="width:155"></input></td>
   </tr>
   <tr>
   		<td><span>联系电话:</span></td>
        <td><input id="mobile" style="width:200px;height:32px" type="text" name="mobile" class="easyui-textbox" data-options="required:true,validType:'phoneOrMobile',missingMessage:'联系电话必须填写'"></input></td>
   </tr>
   <tr>
   		<td><span>电子邮件:</span></td>
        <td><input id="email" style="width:200px;height:32px" type="text" name="email" class="easyui-textbox" data-options="required:true,validType:'email',missingMessage:'eamil必须填写',invalidMessage:'请输入有效的email！'"></input></td>
   </tr>
</table>
</form:form>