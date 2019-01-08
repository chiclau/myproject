<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>水资源管理系统</title>
		 <!-- 引入头部icon图标 -->	
		 <link rel="stylesheet" href="<%=basePath%>common/zui/fonts/favicon.ico">
		 <!-- 引入icon图标 -->
		 <link rel="stylesheet" href="<%=basePath%>common/zui/fonts/iconfont.css">
		 <link rel="stylesheet" href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css">
		  <link rel="stylesheet" href="<%=basePath%>common/ztree/css/demo.css">
	
		  <link rel="stylesheet" href="<%=basePath%>common/layui/css/layui.css">
		 <!-- 引入本页面的样式 -->
	
	</head>
<body>	
	<ul id="tree" class="ztree" style="height:619px; overflow:auto;border:1px solid #dddddd; border-radius: 6px;"></ul>		
</body>
<!-- 不要改变以下引用顺序 -->
<%-- <%@include file="edit.inc"%>
<%@include file="flag.jsp"%> --%>
<!-- 引入jquery.js样式 -->
	 <!-- 解决ie -->
		<!--  [if lt IE 9]> -->
		    <script src="<%=basePath %>common/zui/lib/ieonly/html5shiv.js"></script>
		    <script src="<%=basePath %>common/zui/lib/ieonly/respond.js"></script>
		    <script src="<%=basePath %>common/zui/lib/ieonly/excanvas.js"></script>
		<!--   <![endif] -->
	<script src="<%=basePath%>common/layui/layui.all.js"></script>
	<script src="<%=basePath %>common/zui/lib/jquery/jquery.js"></script>
	<script src="<%=basePath%>common/js/ztree/js/jquery.ztree.all.js"></script>
    <!-- 引入zui.js样式 -->
	<script src="<%=basePath %>common/eCharts/echarts.min.js"></script>
	<script src="<%=basePath %>common/zui/js/zui.lite.min.js"></script>
	<script src="<%=basePath %>common/zui/js/zui.js"></script>
	<script>
	var setting = {
			
			  check : {
		            enable : true
		        },
			view: {
				showLine: false
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"pNode 1", open:true},
			{ id:11, pId:1, name:"pNode 11"},
			{ id:111, pId:11, name:"leaf node 111"},
			{ id:112, pId:11, name:"leaf node 112"},
			{ id:113, pId:11, name:"leaf node 113"},
			{ id:114, pId:11, name:"leaf node 114"},
			{ id:12, pId:1, name:"pNode 12"},
			{ id:121, pId:12, name:"leaf node 121"},
			{ id:122, pId:12, name:"leaf node 122"},
			{ id:123, pId:12, name:"leaf node 123"},
			{ id:124, pId:12, name:"leaf node 124"},
			{ id:13, pId:1, name:"pNode 13 - no child", isParent:true},
			{ id:2, pId:0, name:"pNode 2"},
			{ id:21, pId:2, name:"pNode 21", open:true},
			{ id:211, pId:21, name:"leaf node 211"},
			{ id:212, pId:21, name:"leaf node 212"},
			{ id:213, pId:21, name:"leaf node 213"},
			{ id:214, pId:21, name:"leaf node 214"},
			{ id:22, pId:2, name:"pNode 22"},
			{ id:221, pId:22, name:"leaf node 221"},
			{ id:222, pId:22, name:"leaf node 222"},
			{ id:223, pId:22, name:"leaf node 223"},
			{ id:224, pId:22, name:"leaf node 224"},
			{ id:23, pId:2, name:"pNode 23"},
			{ id:231, pId:23, name:"leaf node 231"},
			{ id:232, pId:23, name:"leaf node 232"},
			{ id:233, pId:23, name:"leaf node 233"},
			{ id:234, pId:23, name:"leaf node 234"},
			{ id:3, pId:0, name:"pNode 3 - no child", isParent:true}
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#tree"), setting, zNodes);
		});
	 
	</script>
   
