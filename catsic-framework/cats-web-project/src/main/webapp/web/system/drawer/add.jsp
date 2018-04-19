<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="addform" class="easyui-form" style="padding:20px 10px 5px 50px">
<table cellpadding="5" style="padding:10px 20px 5px 20px">
   <tr>
        <td><span style="font-size: 15px;">名称:</span></td>
        <td><input id="name" type="text" style="width:200px;height:32px" name="name" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'名称必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
   <tr>
        <td><span style="font-size: 15px;">值:</span></td>
        <td><input id="value" type="text" style="width:200px;height:32px"  name="value" class="easyui-textbox" data-options="required:true,validType:'length[1,20]',missingMessage:'必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
</table>
</form:form>