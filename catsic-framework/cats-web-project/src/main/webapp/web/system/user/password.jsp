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
<form id="passwordform" class="easyui-form">
<table style="font-size:12px;margin:20px 0 0 20px;" >
   <tr >
        <td align="right" style="width:60px"><span>原密码:</span></td>
        <td><input id="oldPassword" type="password" name="oldPassword" class="easyui-textbox"  style='width:200px;height:30px;' data-options="required:true,validType:'length[8,20]',missingMessage:'原密码必须填写',invalidMessage:'密码长度8-20个字符！'"/></td>
   </tr>
   <tr >
        <td align="right"  style="width:60px"><span>新密码:</span></td>
        <td><input id="newPassword" type="password" name="newPassword" class="easyui-textbox" style='width:200px;height:30px;'  data-options="required:true,validType:['pwd'],missingMessage:'新密码必须填写'" /><div id="PasswordCheck"  style="text-align:center;float:right"></div></td>
   </tr>
   <tr >
        <td align="right" style="width:60px"><span>确认密码:</span></td>
        <td><input id="rpw" type="password" class="easyui-textbox" style='width:200px;height:30px;'  required="required" missingMessage="确认密码必须填写" data-options="validType:'equalTo[\'#newPassword\']'"></input></td>
   </tr>
   
</table>
</form>
