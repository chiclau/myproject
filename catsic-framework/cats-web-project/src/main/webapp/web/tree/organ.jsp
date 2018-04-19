<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<div id="organtreeform" style="line-height:25px;height:28px;padding:0px 10px;background:#eee;border-bottom:1px solid #ddd">
			机构名称：<input class="easyui-textbox" name="oname" id="oname" style="width:150px;">
			<a id="search_link" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>
			<a id="reset_link" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-reload'">重置</a>
		</div>
		<ul id="organTree" class="ztree"></ul>
	</div>
</div>
<script type="text/javascript" src="<c:url value='/scripts/common/tree.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/tree/organ.js'/>"></script>
