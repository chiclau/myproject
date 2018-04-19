<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/include.jsp"%>
<style type="text/css">
  #camera {
            margin:10 40;
			width: 700px;
			height: 400px;
  }
</style>
      <div id="cc" class="easyui-layout" fit=true style="width:100%;height:100%;">  
		    <div region="center" collapsed="false" split="true" style="width:900px;">
   <div region="west" collapsed="false" split="true"  title="文书列表" style="width:800px;">
	 <div id="document-list">
	        <input type="hidden" name="caseeId" id="caseeId" value="${param.id}">
	        <input type="hidden" id="id" >
	        <input type="text" name="otherfile" id="otherfile" style="width:280px;" class="easyui-validatebox textbox" >
	        <a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
	        <a id="del" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</a>
	        <table id="datagrid" class="easyui-datagrid"  
	            data-options="fitColumns:true,
	            		singleSelect:true,
	            		collapsible:false,
	            		url:'<c:url value='/linkDocument/getDocsListByCurrentUserOrganId'/>',
	            		method:'get',
	            		sortName:'id',
	            		sortOrder:'desc',
	            		onLoadSuccess:initDatagrid">
	        <thead>
	            <tr>
	                <th data-options="field:'docId',width:80,hidden:'true'">ID</th>
	                <th data-options="field:'title',width:100">公告标题</th>
	                <th data-options="field:'attachmentSum',width:80,align:'center'">附件个数</th>
	                <th data-options="field:'rid',width:80,hidden:'true'">关联ID</th>
	                <th data-options="field:'opt',width:80,align:'center',formatter:format">操作</th>
	            </tr>
	        </thead>
	    </table>
	    </div>
	</div>
	<div region="center" collapsed="false" title="已上传附件" split="true">
	    <div id="attachment-list">
	        <table id="attachment" class="easyui-datagrid">
	        <thead>
	            <tr>
	                <th data-options="field:'id',width:80,hidden:'true'">附件ID</th>
	                <th data-options="field:'fileName',width:340,formatter:formatlink">附件名称</th>
	                <th data-options="field:'fid',width:80,hidden:'true'">附件docID</th>
	                <th data-options="field:'randomFileName',width:80,hidden:'true'">附件随机名称</th>
	                <th data-options="field:'opt',formatter:formatFile,fit:true">操作</th>
	            </tr>
	        </thead>
	    </table>
	    </div>
	</div>
</div>
		        
		    </div>  
