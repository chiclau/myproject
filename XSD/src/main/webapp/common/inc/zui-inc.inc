<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script>
   //项目路径
   var basePath = '<%=basePath%>';
</script>

	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> <!-- 禁用IE兼容模式 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    	     
<!-- zui css -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui.min.css">

<!--
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-indigo.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-bluegrey.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-black.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-yellow.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-brown.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-purple.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-red.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-green.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-blue.css">
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui-theme-default.css">


-->
<!--bootstrap 样式 -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/js/bootstrapValidator/css/bootstrapValidator.css">

<link rel="stylesheet" type="text/css" id="zui_theme_link_css"	href="<%=basePath%>common/zui/css/zui-theme-green.css">
<!--自定义样式-->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/inc/css.css"> 

 <!--[if lt IE 8]>
    <div class="alert alert-danger">您正在使用 <strong>过时的</strong> 浏览器. 是时候 <a href="http://browsehappy.com/">更换一个更好的浏览器</a> 来提升用户体验.</div>
  <![endif]-->    
   
<!--[if lt IE 9]>
  <script src="<%=basePath%>common/zui/lib/ieonly/html5shiv.js" charset="utf-8"></script>
  <script src="<%=basePath%>common/zui/lib/ieonly/respond.js" charset="utf-8"></script>
  <script src="<%=basePath%>common/zui/lib/ieonly/excanvas.js" charset="utf-8"></script>
<![endif]-->

<!-- jquery js -->
<script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js" charset="utf-8"></script>
<!-- zui js -->
<script src="<%=basePath%>common/zui/js/zui.min.js" charset="utf-8"></script>

<!-- 提示组件 -->
<script src="<%=basePath%>common/js/bootbox.js" charset="utf-8"></script>
<!-- bootstrapValidator js-->
<script src="<%=basePath%>common/js/bootstrapValidator/js/bootstrapValidator.min.js" charset="utf-8"></script>
<script src="<%=basePath%>common/js/bootstrapValidator/js/language/zh_CN.js" charset="utf-8"></script>
<!--zui，下拉选择查找chosen-->
<link href="<%=basePath%>common/zui/lib/chosen/chosen.min.css" rel="stylesheet">
<script src="<%=basePath%>common/zui/lib/chosen/chosen.min.js" charset="utf-8"></script>
<!--日期选择-->
<script src="<%=basePath%>common/datejs/laydate.dev.js" charset="utf-8"></script>
<script src="<%=basePath%>common/zui/lib/datetimepicker/datetimepicker.min.js" type="text/javascript" charset="utf-8" ></script>


<!-- 自定义函数及变量 -->
<script src="<%=basePath%>common/inc/js.js" charset="utf-8"></script>
