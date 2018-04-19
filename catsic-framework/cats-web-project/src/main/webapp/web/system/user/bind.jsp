<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
<form:form id="bindform" method="post" >
	<table class="alert-panel">
<%-- 		<c:forEach var="role" items="${urole}">
			<input type="hidden" name="rid" value="${role.id}">
		</c:forEach>
		<c:set value="0" var="index"></c:set>
		<c:forEach var="data" items="${orole}">
		    <tr>
		        <td><input type="checkbox" name="roles[${index}].id" value="${data.id}"></input><span style="font-size: 15px;font-weight: bold;">${data.name}</span></td>
		    </tr>
		    <c:set value="${index+1}" var="index"></c:set>
	    </c:forEach> --%>
	</table>
</form:form>
</div>