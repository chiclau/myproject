<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<form id="addform" class="easyui-form" >
<table style="font-size:12px;padding:40px;">
	<tr>
		<td>名称:</td>
		<td><input class="easyui-textbox" type="text" id="name" name="name" style="width:200px;height:32px"
			data-options="required:true,missingMessage:'角色名称必须填写',validType:'length[1,100]',invalidMessage:'不能超过100个字符！'">
		</td>
	</tr>
</table>
</form>