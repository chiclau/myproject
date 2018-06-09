<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>资料管理</title>
<%@include file="/common/inc/inc.inc"%>
<%@include file="/common/inc/bootstrapTable.inc"%>
<style type="text/css">
.clgl-13{ width:100%; margin:0 auto;}
.clgl-14{ float:left; width:20%;  padding-top:20px; line-height:35px;  text-align:center; }
.clgl-16{ float:left; width:80%;  padding-top:20px; line-height:35px; text-align:center; }
</style>
</head>
<script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<%@include file="/common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>分析计算</li>
                    <li style="color:black;">产流计算</li>
				</ol>
			</h3>
		</div>
		<hr style="margin-top: -5px;">
		<div class="clgl-13">
			<div class="clgl-14">
		 		<div><input type="checkbox" name="chk" checked="checked" value="降雨径流要素计算">&nbsp;&nbsp;降雨径流要素计算</div>
		 		<div style="margin-right:52px;"><input type="checkbox" name="chk" value="蓄满产流" >&nbsp;&nbsp;蓄满产流</div>
		 		<div style="margin-right:14px;"><input type="checkbox" name="chk" value="降雨径流相关图" >&nbsp;&nbsp;降雨径流相关图</div>
		 		<div style="margin-left:10px;"><input type="checkbox"  name="chk" value="稳定入流的fe的计算">&nbsp;&nbsp;稳定入流的fe的计算</div>
		 		<div style="margin-right:36px;"><input type="checkbox" name="chk" value="超渗产流法" >&nbsp;&nbsp;超渗产流法</div>
			</div>
			<div id="maincontent" class="clgl-16 row-fluid">
				<!-- tab -->
				<ul class="nav nav-tabs" style="margin-top: -36px;margin-bottom: 5px;" id="ul_xxk">
					<li class="active"><a data-tab href="#tabContent1">降雨径流要素计算</a></li>
					<!-- <li class=""><a data-tab href="#tabContent2">蓄满产流</a></li> -->
				</ul>
				<div class="tab-content">
	  				<div class="tab-pane active" id="tabContent0">
				  		<%@include file="jyjlysjs/list.jsp"%>
				  	</div>
	  				<div class="tab-pane" id="tabContent1">
				  		<%@include file="tlflfxmcl/list.jsp"%>
				  	</div>
				  	<div class="tab-pane" id="tabContent2">
				  		<%@include file="jyjl/list.jsp"%>
				  	</div>
				  	<div class="tab-pane" id="tabContent3">
				  		<%@include file="wdrl/list.jsp"%>
				  	</div>
				  	<div class="tab-pane" id="tabContent4">
				  		<%@include file="cc/list.jsp"%>
				  	</div>
				</div>
	        </div>
        </div>
	  </div> <!-- maincontent -->
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script src="jyjlysjs/query.js"></script>
<script src="tlflfxmcl/query.js"></script>
<script src="jyjl/query.js"></script>
<script src="wdrl/query.js"></script>
<script src="cc/query.js"></script>
<script type="text/javascript">
$("input[name='chk']").click(function(){
	$("#ul_xxk").empty();
	var value=$("input[name='chk']:checked");
	for(var i=0;i<value.length;i++){
		if(value[i].checked==true){
			if(i=='0'){
				ht = "<li class='active'><a data-tab href='#tabContent"+i+"'>"+value[i].value+"</a></li>";
			}else{
				ht = "<li><a data-tab href='#tabContent"+i+"'>"+value[i].value+"</a></li>";
			}
			$("#ul_xxk").append(ht);
		}
	}
})
</script>
