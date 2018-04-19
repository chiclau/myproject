<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="addform" method="post">
<table class="show-information">
   <tr>
        <td><span>IP地址:</span></td>
        <td><input id="ftpip" style="width:200px;height:32px" type="text" name="ftpip" class="easyui-textbox" data-options="required:true,validType:['ip'],missingMessage:'IP地址必须填写',invalidMessage:'请填写正确的IP地址'"></input></td>
   </tr>
   <tr>
        <td><span>端口号:</span></td>
        <td><input id="ftpport" style="width:200px;height:32px" type="text" name="ftpport" class="easyui-numberbox" data-options="required:true,validType:'length[2,2]',missingMessage:'端口号必须填写',invalidMessage:'端口号为2位数字！'"></input></td>
   </tr>
   <tr>
        <td><span>用户名:</span></td>
        <td><input id="ftpuser" style="width:200px;height:32px" type="text" name="ftpuser" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'登录名必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
   <tr>
        <td><span>密码:</span></td>
        <td><input id="ftppassword" style="width:200px;height:32px" type="password" name="ftppassword" class="easyui-textbox"  data-options="required:true,missingMessage:'密码必须填写',invalidMessage:'长度必须在{8-20}字符之间'"></input></td>
   </tr>
   <tr  >
        <td><span>确认密码:</span></td>
        <td><input id="rpw" style="width:200px;height:32px" type="password" class="easyui-textbox"  required="required"  data-options="validType:'equalTo[\'#ftppassword\']',missingMessage:'确认密码必须填写'"></input></td>
   </tr>
</table>
</form:form>