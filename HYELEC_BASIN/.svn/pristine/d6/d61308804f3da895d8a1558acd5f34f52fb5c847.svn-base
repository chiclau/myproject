<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 按钮工具条开始 -->
<div class="row-fluid col-md-12">
	<div id="tbar" class="btn-toolbar">
		<div class="btn-group">
			<button type="button" id="query_add_floodTran" class="btn btn-primary">
			   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增</div>
			   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
			</button>
		</div>
		<div class="btn-group">
			<button type="button" id="btn_del_floodTran" class="btn btn-danger btn_del_color">
			   <div class="visible-md visible-lg"><i class="icon-trash"></i>&nbsp;批量删除</div>
			   <div class="visible-xs visible-sm"><i class="icon-trash"></i></div>
			</button>
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
		
		<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
			<div class="input-group">
				<input type="text" id="query_searchName" autofocus="autofocus" class="form-control" placeholder="输入测站名称进行模糊查询"> 
				<span class="input-group-btn">
					<button class="btn btn-primary" id="query_ref" type="button">
					   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
					   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
					</button>
				</span>
			</div>
		</div>
	</div>
</div>
<!-- 按钮工具条结束 -->
<table id="query_table_floodTran"  class="table-condensed table-hover table-cursor">
    <thead>
        <tr>
        	<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
			<th data-halign="center" data-align="center" data-sortable="false" data-width="40px" data-formatter="FMT_Num1">序号</th>
			<th data-halign="center" data-align="center" data-sortable="false" data-field="STNM_1" data-width="" data-formatter="">上游名称</th>
			<th data-halign="center" data-align="center" data-sortable="false" data-field="STNM_2" data-width="">下游名称</th>
			<th data-halign="center" data-align="right" data-sortable="false" data-field="RCHLEN" data-width="" data-formatter="">河段长(km)</th>
			<th data-halign="center" data-align="right" data-sortable="false" data-field="SFTQ" data-width="" data-formatter="">安全泄量(m<sup>3</sup>/s)</th>
			<th data-halign="center" data-align="center" data-sortable="false" data-field="QMGN" data-width="" data-formatter="">流量量级(m<sup>3</sup>/s)</th>
			<th data-halign="center" data-align="right" data-sortable="false" data-field="MNTRTM" data-width="" data-formatter="">最小传播时间</th>
			<th data-halign="center" data-align="right" data-sortable="false" data-field="MXTRTM" data-width="" data-formatter="">最大传播时间</th>
			<th data-halign="center" data-align="right" data-sortable="false" data-field="AVTRTM" data-width="" data-formatter="">平均传播时间</th>
			<th data-halign="center" data-align="center" data-sortable="false" data-field="UPSTCD" data-width="140px" data-formatter="FMT_handle1">操作</th>
        </tr>
    </thead>
</table>
