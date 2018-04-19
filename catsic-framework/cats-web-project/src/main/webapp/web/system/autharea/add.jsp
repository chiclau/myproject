<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form id="addform" class="easyui-form">
<table  id="menutable" cellpadding="5" style="margin: 30px 10px 0 50px;">
   <tr>
        <td><span style="font-size: 15px;">名称:</span></td>
        <td><input id="name" type="text" name="name" class="easyui-textbox" style="width:200px;height:32px;" data-options="required:true,validType:'length[1,20]',missingMessage:'名称必须填写',invalidMessage:'不能超过20个字符！'"></input></td>
   </tr>
   <tr>
   		<td><span style="font-size: 15px;">路径:</span></td>
        <td><input id="path" type="text" name="path" class="easyui-textbox" style="width:200px;height:32px"></input></td>
   </tr>
</table>
</form:form>