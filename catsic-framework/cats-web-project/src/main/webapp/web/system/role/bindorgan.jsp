<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="organ_layout" class="easyui-layout" data-options="fit:true,border:false">
     <div data-options="region:'west',split:true,border:false" style="width:400px;">
			<%@ include file= "../../tree/organ.jsp" %>
     </div>
     <div data-options="region:'center',border:false" >
     	<div class="right" id="setting" style="margin-left: 10px">
		<ul class="info">
			<li class="title">
			<ul class="list">
				<li class="highlight_red">
					<a id="expandAllBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-reload'">展开所有机构</a>
					<a id="resetBtn" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls: 'icon-reload'">重新加载机构</a>
				</li>
				<li><p>父子关联关系：<br/>
						被勾选时：<input type="checkbox" id="py" class="checkbox first" checked /><span>关联父</span>
						<input type="checkbox" id="sy" class="checkbox first" checked /><span>关联子</span><br/>
						取消勾选时：<input type="checkbox" id="pn" class="checkbox first" checked /><span>关联父</span>
						<input type="checkbox" id="sn" class="checkbox first" checked /><span>关联子</span><br/>
						<ul id="code" class="log" style="height:20px;"></ul></p>
				</li>
				</ul>
			</li>
		</ul>
		</div>
		<span id = "organIds"></span>
    </div>
</div>
<form:form id="bindorganform" style="display:none">
</form:form>