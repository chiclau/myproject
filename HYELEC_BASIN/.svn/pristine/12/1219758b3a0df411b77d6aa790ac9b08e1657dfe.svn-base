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
					<li>模型管理</li>
                    <li style="color:black;">模型列表</li>
				</ol>
			</h3>
		</div>
		<hr style="margin-top: -5px;">
		<div id="maincontent" class="row-fluid">
			<!-- tab -->
			<ul class="nav nav-tabs" style="margin-top: -36px;margin-bottom: 5px;">
				<!-- <li class="active"><a data-tab href="#tabContent1">模型列表</a></li> -->
			</ul>
			<div class="tab-content">
  				<div class="tab-pane active" id="tabContent1">
					<!-- 按钮工具条开始 -->
					<div class="row-fluid col-md-12">
					<div id="tbar" class="btn-toolbar">
						<div class="btn-group">
							<button type="button" id="query_add" class="btn btn-primary">
							   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增</div>
							   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
							</button>
						</div>
						<div class="btn-group">
							<button type="button" id="btn_del" class="btn btn-danger btn_del_color">
							   <div class="visible-md visible-lg"><i class="icon-trash"></i>&nbsp;批量删除</div>
							   <div class="visible-xs visible-sm"><i class="icon-trash"></i></div>
							</button>
						</div>
						<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
							<div class="input-group">
								<input type="text" id="query_searchName" autofocus="autofocus" class="form-control" placeholder="输入关键字进行模糊查询"> 
								<span class="input-group-btn">
									<button class="btn btn-primary" id="query_ref" type="button">
									   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
									   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
									</button>
								</span>
							</div>
						</div>
						<div class="btn-group pull-left visible-lg visible-md visible-sm">
							<button class="btn btn-primary" id="btn_into_pptn" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;导入</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div>
							</button>
						</div>
						<div class="btn-group pull-left visible-lg visible-md visible-sm">
							<button class="btn btn-primary" id="btn_outAll_pptn" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
							</button>
						</div>
						<div class="btn-group pull-left visible-lg visible-md visible-sm">
							<button class="btn btn-primary" id="btn_outPage_pptn" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
							</button>
						</div>
<!-- 						<label  class=" col-md-2  col-lg-2 col-sm-2"></label> -->
<!-- 						<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<%-- 							<select id="select_supply" class="form-control" > --%>
<%-- 							</select> --%>
<!-- 						</div> -->
<!-- 						<div class="btn-group pull-right visible-lg visible-md visible-sm"> -->
<!-- 							<h5>供货厂商：</h5> -->
<!-- 						</div> -->
					</div>
				</div>
					<!-- 按钮工具条结束 -->
			    <table id="query_table"  class="table-condensed table-hover table-cursor">
			        <thead>
			            <tr>
			            	<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
							<th data-halign="center" data-align="center" data-sortable="false" data-width="40px" data-formatter="FMT_Num">序号</th>
							<th data-halign="center" data-align="center" data-sortable="false" data-field="MODE_TYPE" data-width="" data-formatter="fenlei_handle">模型分类</th>
							<th data-halign="center" data-align="center" data-sortable="false" data-field="MODEL_NAME" data-width="">模型名称</th>
							<th data-halign="center" data-align="right" data-sortable="false" data-field="CSSL" data-width="" data-formatter="">参数数量</th>
							<th data-halign="center" data-align="right" data-sortable="false" data-field="REMARK" data-width="" data-formatter="">模型描述</th>
							<th data-halign="center" data-align="center" data-sortable="false" data-field="STATE" data-width="" data-formatter="state_handle">状态</th>
							<th data-halign="center" data-align="center" data-sortable="false" data-field="MODEL_CODE" data-width="140px" data-formatter="FMT_handle">操作</th>
			            </tr>
			        </thead>
			    </table>
			  </div>
			</div>
        </div>
	  </div> <!-- maincontent -->
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="upload.jsp"%>
<%@include file="edit.jsp"%>
<%@include file="query_details.jsp" %>

<script src="query.js"></script>
<script src="list.js"></script>
