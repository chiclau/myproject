<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript"src="<c:url value='/scripts/echarts/echarts.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/scripts/common/jquery-1.8.3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/common/jsonutil.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/common/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/common/ajax.global.js'/>"></script>
<link href="<c:url value='/images/favicon.ico'/>" rel="shortcut icon" type="image/x-icon"/>
<link href="<c:url value='/images/favicon.ico'/>" rel="icon" type="image/x-icon"/>
<link href="<c:url value='/scripts/web/easyui/themes/default/easyui.css'/>" rel="stylesheet" type="text/css" >
<link href="<c:url value='/scripts/web/easyui/themes/icon.css'/>" rel="stylesheet" type="text/css" >
<link rel="stylesheet" href="<c:url value='/scripts/web/ztree/css/zTreeStyle/zTreeStyle.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.core-3.5.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.excheck-3.5.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/ztree/js/jquery.ztree.exedit-3.5.js'/>"></script>
<link href="<c:url value='/css/web/catsic.css'/>"     rel="stylesheet" type="text/css" >
<%
/**设置IE兼容性，强制浏览器以IE8标准加载页面*/
response.setHeader("X-UA-Compatible","IE=8");
%>
<script type="text/javascript">
	var ctx  = "<%=request.getContextPath() %>";
</script>
<script type="text/javascript">
String.prototype.trim=function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
</script>