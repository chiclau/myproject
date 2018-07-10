<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>资料管理</title>
<%@include file="/common/inc/inc.inc"%>
<%@include file="/common/inc/bootstrapTable.inc"%>

</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<%@include file="/common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>资料管理</li>
                    <li style="color:black;">水文数据</li>
				</ol>
			</h3>
		</div>
		<hr style="margin-top: -5px;">
		<div id="maincontent" class="row-fluid">
			<!-- tab -->
			<ul class="nav nav-tabs" style="margin-top: -36px;margin-bottom: 5px;">
				<li class="active"><a data-tab href="#tabContent1">降水量</a></li>
				<li class=""><a data-tab href="#tabContent2">日蒸发量</a></li>
				<li class=""><a data-tab href="#tabContent3">河道水情</a></li>
				<li class=""><a data-tab href="#tabContent4">水库水情</a></li>
				<li class=""><a data-tab href="#tabContent5">土壤墒情</a></li>
			</ul>
			<div class="tab-content">
  				<div class="tab-pane active" id="tabContent1">
			  		<%@include file="precipitation/list.jsp"%>
			  	</div>
  				<div class="tab-pane" id="tabContent2">
			  		<%@include file="diurnalEvaporation/list.jsp"%>
			  	</div>
  				<div class="tab-pane" id="tabContent3">
			  		<%@include file="riverWater/list.jsp"%>
			  	</div>
  				<div class="tab-pane" id="tabContent4">
			  		<%@include file="reservoirWater/list.jsp"%>
			  	</div>
  				<div class="tab-pane" id="tabContent5">
			  		<%@include file="soilMoisture/list.jsp"%>
			  	</div>
			</div>
        </div>
	  </div> <!-- maincontent -->
</body>
</html>
<script>
    var tm="";
</script>
<!-- 不要改变以下引用顺序 -->
<%@include file="upload.jsp"%>
<%@include file="precipitation/edit.jsp"%>
<%@include file="diurnalEvaporation/edit.jsp"%>
<%@include file="riverWater/edit.jsp"%>
<%@include file="reservoirWater/edit.jsp"%>
<%@include file="soilMoisture/edit.jsp"%>

<%@include file="precipitation/details.jsp" %>
<%@include file="diurnalEvaporation/details.jsp" %>
<%@include file="riverWater/details.jsp" %>
<%@include file="reservoirWater/details.jsp" %>
<%@include file="soilMoisture/details.jsp" %>

<script src="precipitation/query.js"></script>
<script src="diurnalEvaporation/query.js"></script>
<script src="riverWater/query.js"></script>
<script src="reservoirWater/query.js"></script>
<script src="soilMoisture/query.js"></script>
<script src="list.js"></script>
