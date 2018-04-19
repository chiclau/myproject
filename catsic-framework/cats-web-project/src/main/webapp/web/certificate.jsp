<!-- 证书登录 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file= "/web/index.jsp" %>
<script>
$(document).ready(function(){
	/**
	 *证书登录，取消修改密码，退出功能
	 */
	$("#loginOut").attr("href","#");
	$("#loginOut p").text($("#index #username").val());
	
	$("#setUp").unbind().bind("click",function() {
		$('#setUpDialog').dialog({
			title : '设置',
			width : 400,
			height : 220,
			inline : false,
			closed : false,
			cache : false,
		    onClose:function(){
		    	destroy('setUpDialog','#dialogDiv');
		    },
			
			modal : true,
			/* content : "<div class='alert-div'><ul><li><a href='#' onclick='changePassWords();'>密码修改</a></li></ul></div>" */
			content : "<div class='alert-div'><ul><li></li></ul></div>"
		});
	});
})
</script>