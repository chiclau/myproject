<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
   //项目路径
   var basePath = '<%=basePath%>';
</script>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">	

<!-- zui css -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/bootstrap/css/bootstrap.min.css">
	
<!-- jquery js -->
<script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js"></script>
<!-- zui js -->
<script src="<%=basePath%>common/bootstrap/js/bootstrap.min.js"></script>


<script src="<%=basePath%>common/inc/js.js"></script>