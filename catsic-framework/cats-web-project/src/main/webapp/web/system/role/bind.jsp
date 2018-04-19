<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="resources_layout" class="easyui-layout" data-options="fit:true">
     <div data-options="region:'west',split:true,border:false" style="width:30%">
	      <div id="menu" class="easyui-panel" title="资源列表" data-options="fit:true,border:false">
				<div class="content_wrap">
					<div class="zTreeDemoBackground left">
						<ul id="mtree" class="ztree"></ul>
					</div>
				</div>
	      </div>
     </div>
     <div data-options="region:'center',border:false"  style="width:70%">
		<div id="resource" class="easyui-panel" title="权限列表" data-options="fit:true,border:false">
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul id="rtree" class="ztree"></ul>
				</div>
			</div>	
	    </div>
     </div>
</div>
<form:form id="bindform" style="display:none">
</form:form>
