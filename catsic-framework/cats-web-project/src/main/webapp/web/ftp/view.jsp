<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="easyui-layout" data-options="fit:true,border:false">
      <div data-options="region:'center',split:true,border:false">
        <form:form id="addform" method="post" >
			<table class="show-information" id="myTable" >	
			   <tr>
			        <td><span>IP地址:</span></td>
			        <td ><span id="ftpip"  ></span></td>
			   </tr> 
			   <tr>
			        <td><span>端口号:</span></td>
			        <td ><span id="ftpport" ></span></td>
			   </tr>
			   <tr>        
			   		<td><span>用户名:</span></td>
			        <td ><span id="ftpuser"></span></td>
			   </tr>  
			</table>
			<c:forEach items="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.resources}" var="item">
				<c:if test="${item.id=='9999104'}">
					<input type="hidden" id="fileauth" value="true">
			  	</c:if>
			</c:forEach>
	  </form:form>
	  </div>
	<div data-options="region:'east',split:true,border:false" style="width:400px;height:100px;">
		<table id="attachment1" class="easyui-datagrid" data-options="border:false">
	        <thead>
	            <tr>
	                <th data-options="field:'id',width:80,hidden:'true'">附件ID</th>
	                <th data-options="field:'fileName',width:400,formatter:formatlink">附件名称</th>
	            </tr>
	        </thead>
        </table>
		<div id="datagrid-toolbar-download" >
			
		</div>
	</div>
	<div data-options="region:'south',split:true,border:false" style="height:200px;">
	        <table id="attachment" class="easyui-datagrid" data-options="border:false">
		        <thead>
		            <tr>
		                <th data-options="field:'id',width:80,hidden:'true'">附件ID</th>
		                <th data-options="field:'fileName',width:200,formatter:formatlink">附件名称</th>
		                <th data-options="field:'opt',width:65,align:'center',formatter:formatFile">操作</th>
		            </tr>
		        </thead>
	       </table>
	</div>
</div>