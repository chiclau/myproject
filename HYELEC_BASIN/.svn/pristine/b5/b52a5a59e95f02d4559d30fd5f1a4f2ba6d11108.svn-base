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
                    <li style="color:black;">运行数据</li>
				</ol>
			</h3>
		</div>
		<hr style="margin-top: -5px;">
		<div id="maincontent" class="row-fluid">
			<!-- tab -->
			<ul class="nav nav-tabs" style="margin-top: -36px;margin-bottom: 5px;">
				<li class="active"><a data-tab href="#tabContent1">洪水传播时间表</a></li>
				<li class=""><a data-tab href="#tabContent5">退水曲线表</a></li>
				<li class=""><a data-tab href="#tabContent2">水位流量关系曲线</a></li>
				<li class=""><a data-tab href="#tabContent3">库（湖）容曲线</a></li>
				<li class=""><a data-tab href="#tabContent4">断面测试成果</a></li>
				<li>
					<button class="btn btn-primary" id="btn_fj1" type="button" onclick="btn_fj()">
						<div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;分析统计</div>
					</button>
				</li>
			</ul>
			<div class="tab-content">
  				<div class="tab-pane active" id="tabContent1">
			  		<%@include file="floodTran/list.jsp"%>
			  	</div>
  				<div class="tab-pane" id="tabContent2">
			  		<%@include file="waterLevelFlow/list.jsp"%>
			  	</div>
  				<div class="tab-pane" id="tabContent3">
			  		<%@include file="storageCapacity/list.jsp"%>
			  	</div>
  				<div class="tab-pane" id="tabContent4">
			  		<%@include file="sectionTest/list.jsp"%>
			  	</div>
			  	<div class="tab-pane" id="tabContent5">
			  		<%@include file="/business/consumer/tsqxData/tsqx/list.jsp"%>
			  	</div>
			</div>
        </div>
	  </div> <!-- maincontent -->
</body>
</html>
<script type="text/javascript">
 var tm="";
</script>
 <!-- 不要改变以下引用顺序 -->
<%@include file="upload.jsp"%>
<%@include file="floodTran/edit.jsp"%>
<%@include file="waterLevelFlow/edit.jsp"%>
<%@include file="waterLevelFlow/edit_x.jsp"%>
<%@include file="storageCapacity/edit.jsp"%>
<%@include file="storageCapacity/edit_x.jsp"%>
<%@include file="sectionTest/edit.jsp"%>
<%@include file="sectionTest/edit_x.jsp"%>
<%@include file="floodTran/details.jsp" %>
<%@include file="waterLevelFlow/details.jsp" %>
<%@include file="storageCapacity/details.jsp" %>
<%@include file="sectionTest/details.jsp" %>
<%@include file="myEcharts.jsp"%>



<script src="myEcharts.js"></script><!-- echarts -->
<script src="floodTran/query.js"></script>
<script src="waterLevelFlow/query.js"></script>
<script src="storageCapacity/query.js"></script>
<script src="sectionTest/query.js"></script>
<script src="list.js"></script>
<script src="../../../common/eCharts/echarts.min.js"></script>

