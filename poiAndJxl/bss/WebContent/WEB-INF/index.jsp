<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="gbk"%>
<% String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
验证码：<input type="text" name="check">
<img alt="验证码" src="<%=request.getContextPath()%>/servlet/ImageServlet">
<a href="javascript:reloadCode()">看不清楚，点击刷新</a>
 <br>
</body>
</html>