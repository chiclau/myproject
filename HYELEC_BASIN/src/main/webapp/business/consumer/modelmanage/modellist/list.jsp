<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
	<div class="container-fluid" style="height:100%;">
		<div class="row-fluid" style="height:30px;">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>模型管理</li>
                    <li style="color:black;">模型列表</li>
				</ol>
			</h3>
		</div>
		<div class="row-fluid" style="height:calc(100% - 30px);">
					<!-- 按钮工具条开始 -->
			    <div class="row-fluid col-md-12" style="height:35px;">
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
								<input type="text" id="query_searchName" class="form-control" placeholder="输入关键字进行模糊查询"> 
								<span class="input-group-btn">
									<button class="btn btn-primary form-control" id="query_ref" type="button">
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

					</div>
				</div>
					<!-- 按钮工具条结束 -->
				<div id="query_modellist_table_div" style="height:calc(100% - 80px);">
					<table id="query_modellist_table"  class="table-condensed table-hover table-cursor">
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
	  </div> <!-- maincontent -->
<!-- 不要改变以下引用顺序 -->
<%@include file="upload.jsp"%>
<%@include file="edit.jsp"%>
<%@include file="query_details.jsp" %>

<script src="../business/consumer/modelmanage/modellist/query.js"></script>
<script src="../business/consumer/modelmanage/modellist/list.js"></script>
