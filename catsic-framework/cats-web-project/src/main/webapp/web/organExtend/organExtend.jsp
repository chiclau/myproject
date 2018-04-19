<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp"%>

<script type="text/javascript" src="<c:url value='/scripts/web/organExtend/organExtend.js'/>"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
     <div data-options="region:'west',split:true,border:false" style="width:200px;" title='机构'>
     		<div id = 'organTree'></div>
     </div>
     
      
     <div data-options="region:'center',fit:true,border:false" id="organDiv"> 
     
		  <form id="organExtendForm"  class="easyui-form" > 

		  <table class="show-information">
		  <input type="hidden" id="id" name="id" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.organId}">
		       <tr>
		       		<td>机构名称:</td>
		      		<td><input type="text" class="easyui-textbox"  id="name" name="name" data-options="required:true,missingMessage:'机构名称必须填写'"></input></td>
		       </tr>
		       <tr>
		       		<td>机构邮编:</td>
		      		<td><input type="text" class="easyui-textbox"  id="zipCode" name="zipCode" data-options="required:true,missingMessage:'机构邮编必须填写'"></input></td>
		       </tr>
		       <tr>
		       		<td>联系人:</td>
		      		<td><input type="text" class="easyui-textbox"  id="contactor" name="contactor" data-options="required:true,missingMessage:'机构联系人必须填写'"></input></td>
		       </tr>
		       <tr>
		       		<td>联系电话:</td>
		      		<td><input type="text" class="easyui-textbox"  id="telephone" name="telephone" data-options="required:true,missingMessage:'机构电话必须填写'"></input></td>
		       </tr>
		       <tr>
		       		<td>地址:</td> 
		      		<td><input type="text" class="easyui-textbox"  id="address" name="address" data-options="required:true,missingMessage:'机构地址必须填写'"></input></td>
		       </tr>
		       <tr>
		       		<td>账户:</td> 
		      		<td><input type="text" class="easyui-textbox"  id="account" name="account" data-options="required:true,missingMessage:'机构账户必须填写'"></input></td>
		       </tr>
               <tr>
		       		<td>银行:</td> 
		      		<td><input type="text" class="easyui-textbox"  id="bank" name="bank" data-options="required:true,missingMessage:'机构银行必须填写'"></input></td>
		       </tr>
               <tr>
		       		<td>政府机构:</td> 
		      		<td><input type="text" class="easyui-textbox"  id="government" name="government" data-options="required:true,missingMessage:'政府机构必须填写'"></input></td>
		       </tr>
               <tr>
		       		<td>复议机构:</td> 
		      		<td><input type="text" class="easyui-textbox"  id="reconsiderationOrgan" name="reconsiderationOrgan" data-options="required:true,missingMessage:'复议机构必须填写'"></input></td>
		       </tr>
		       <tr>
		       		<td>内置属性:</td> 
		      		<td><select class="easyui-combobox" id="isQuery" name="isQuery" name="state" style="width:155px;" data-options="panelHeight:'auto',value:'否',required:true,missingMessage:'必须填写'">
			        	<option value="否">否</option>
			        	<option value="是">是</option>
			        </select><span style="color:red">注：是：表示可以查询父节点下所有子节点的数据</span></td>
		       </tr>
               <tr>
		       		<td colspan="2">
		       			<a id="save_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">保存</a>
		       			<a id="clear_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" >重置</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		       			<!-- <a id="delete_link" href="#" class="easyui-linkbutton" >删除</a> -->
		       		</td>
		       </tr>
		       </table>
	     </form>
	 </div>
</div>

