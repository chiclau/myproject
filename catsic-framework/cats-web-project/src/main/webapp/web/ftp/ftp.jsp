<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		  <table id="datagrid" title="FTP管理" style="display:none" >
				<thead>
					<tr>
				       <th data-options="field:'id',checkbox:true,width:50">ID</th>
			           <th data-options="field:'ftpip',width:100,sortable:true">IP地址</th>
			           <th data-options="field:'ftpport',width:100,sortable:true">端口</th>
			           <th data-options="field:'ftpuser',width:150,sortable:true">用户名</th>
			           <th data-options="field:'organName',width:100">机构名称</th>
			           <th data-options="field:'organCode',width:100,hidden:true">机构编码</th>
			           <th data-options="field:'organId',width:100,hidden:true">机构ID</th>
					</tr>
				</thead>
			</table>
	 <div id="datagrid-toolbar" >
		<form id="listform" style="margin-bottom:-1px">
	        <label for="search_announceType">端口号:</label>
			<input type="text" class="easyui-textbox" id="search_ftpport" name="ftpport">
			<label for="search_ftpuser">用户名:</label><input type="text" class="easyui-textbox" id="search_ftpuser" name="ftpuser"/>
			<label for="search_organName">机构:</label><input type="text" class="easyui-textbox" id="search_organName" name="organName"/>
		</form>
	</div>
<script type="text/javascript" src="<c:url value='/scripts/common/file.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/ftp/ftp.js'/>"></script>
<!-- <a id="searchFtp" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询 </a>
	<a id="addFtp" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
	<a id="updateFtp" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
	<a id="deleteFtp" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
	<a id="test" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true">测试</a>
	<a id="upload" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-uploading',plain:true">上传</a>
	<a id="view" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-detail',plain:true">详情</a>
	<a id="uploadGpy" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-uploading',plain:true">启动高拍仪</a>
	<a id="webservice" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">调用webservice</a> -->
