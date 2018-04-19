<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ include file= "/common/include.jsp" %> --%>
<link rel="stylesheet" type="text/css"  href="<c:url value='/scripts/web/upload/plupload/js/jquery.plupload.queue/css/jquery.plupload.queue.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/web/upload/plupload/js/plupload.full.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/upload/plupload/js/jquery.plupload.queue/jquery.plupload.queue.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/upload/plupload/js/i18n/cn.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/web/upload/plupload/js/upload.js'/>"></script>

<input type="hidden" id="fid" value="${param.fid}">
<input type="hidden" id="note" value="${param.note}">
<div>
	<div style="width: 780px; margin: 0px auto">
		<form id="formId"  method="post" enctype="multipart/form-data">
			<div id="uploader">
				<p>您的浏览器未安装 Flash, Silverlight, Gears, BrowserPlus 或者支持 HTML5 .</p>
			</div>
		</form>
	</div>
</div>