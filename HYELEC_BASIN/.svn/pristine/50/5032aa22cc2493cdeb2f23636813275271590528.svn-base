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
                    <li style="color:black;">汇流计算</li>
				</ol>
			</h3>
		</div>
		<hr style="margin-top: -5px;">
		 <div class="clgl-13">
			 <div class="clgl-14">
			 		<div><input type="checkbox" name="chk" checked="checked" value="推理公式法">&nbsp;&nbsp;推理公式法</div>
			 		<div><input type="checkbox" name="chk" value="推理峰量法">&nbsp;&nbsp;推理峰量法</div>
			 		<div style="margin-right:-10px;"><input type="checkbox" name="chk" value="推理过程线法">&nbsp;&nbsp;推理过程线法</div>
			 		<div><input id="ss" type="checkbox" name="chk" value="瞬时单位法">&nbsp;&nbsp;瞬时单位法</div>
			 		<div><input id="dm" type="checkbox" name="chk" value="地貌单位线">&nbsp;&nbsp;地貌单位线</div>
			 </div>
			<div id="maincontent" class="clgl-16  row-fluid">
					<ul class="nav nav-tabs" style="margin-top: -36px;margin-bottom: 5px;" id="ul_xxk">
						 <li class="active"><a data-tab href="#tabContent1">推理公式法</a></li>
						<!--<li class=""><a data-tab href="#tabContent2">推理峰量法</a></li> -->
					</ul>
				<!-- tab -->
				
				<div class="tab-content">
	  				<div class="tab-pane active" id="tabContent0" name="tabContent">
				  		<%@include file="tlgsf/list.jsp"%>
				  	</div>
	  				<div class="tab-pane" id="tabContent1" name="tabContent">
				  		<%@include file="tlflf/list.jsp"%>
				  	</div>
				  	<div class="tab-pane" id="tabContent2" name="tabContent">
				  		<%@include file="tlgcxf/list.jsp"%>
				  	</div>
				  	<div class="tab-pane" id="tabContent3" name="tabContent">
				  		<%@include file="ssdwf/list.jsp"%>
				  	</div>
				  	<div class="tab-pane" id="tabContent4" name="tabContent">
				  		<%@include file="dmdwf/list.jsp"%>
				  	</div>
				</div>
				
	        </div>
        </div>
	  </div> <!-- maincontent -->
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script src="tlgsf/query.js"></script>
<script src="tlflf/query.js"></script>
<script src="tlgcxf/query.js"></script>
<script src="ssdwf/query.js"></script>
<script src="dmdwf/query.js"></script>
<script type="text/javascript">
	$("input[name='chk']").click(function(){
		$("#ul_xxk").empty();
		var value=$("input[name='chk']:checked");
		/* var value_=$("div[name='tabContent']");*/
		for(var i=0;i<value.length;i++){
			if(value[i].checked==true){
				if(i=='0'){
					ht = "<li class='active'><a data-tab href='#tabContent"+i+"'>"+value[i].value+"</a></li>";
				}else{
					ht = "<li><a data-tab href='#tabContent"+i+"''>"+value[i].value+"</a></li>";
				}
				$("#ul_xxk").append(ht);
			}
			
		}
	})
</script>
