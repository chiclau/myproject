<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="container-fluid" style="height:100%;">
		<div class="row-fluid" style="height:30px;">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">行政区域代码管理</li>
				</ol>
			</h3>
		</div>
		<div id="maincontent" class="row-fluid" style="height:calc(100% - 30px);">
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="row-fluid btn-toolbar" style="width:400px;">
				<div style="width:200px;float:left;">
					<button class="btn btn-primary" id="btn_add_addvcd" type="button">
						<div class="visible-md visible-lg">
							<i class="icon icon-file-o"></i>&nbsp;新增根节点
						</div>
						<div class="visible-xs visible-sm">
							<i class="icon icon-file-o"></i>
						</div>
					</button>
					<button class="btn btn-primary" id="btn_into_add" type="button">
						   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;导入</div>
						   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div>
					</button>
				</div>
				<div style="width:200px;float:right;">
					<select id="select_pcode_addvcd" class="form-control" style="width:150px;float:right;">
						<option value="">显示全部数据</option>
					</select>
				</div>
			</div>
			<!-- 按钮工具条结束 -->
			<div style="width:100%;height:calc(100% - 120px);overflow:auto;">
				<table id="treeGrid_addvcd" class="table-hover">
				</table>
			</div>
		</div>
	</div>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<%@include file="../../../common/import/importInfo.inc"%>
<script type="text/javascript" src="../common/import/importInfo.js"></script>
<script src="../business/system/addvcd/def.js"></script>
<script src="../business/system/addvcd/list.js"></script>
