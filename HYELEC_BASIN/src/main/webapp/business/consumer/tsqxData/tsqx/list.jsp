<%@ page language="java" pageEncoding="UTF-8"%>
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
								<input type="text" id="query_searchName_tsqx" autofocus="autofocus" class="form-control" placeholder="输入测站名称进行模糊查询"> 
								<span class="input-group-btn">
									<button class="btn btn-primary" id="query_ref_tsqx" type="button">
									   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
									   <div class="visible-xs visible-sm"><i class="icon icon-search"></i></div>
									</button>
								</span>
							</div>
						</div>
						<div class="btn-group pull-left visible-lg visible-md visible-sm">
							<button class="btn btn-primary" id="btn_into_tsqx" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;导入</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div>
							</button>
						</div>
						<div class="btn-group pull-left visible-lg visible-md visible-sm">
							<button class="btn btn-primary" id="btn_outAll_tsqx" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-reply"></i>&nbsp;导出全部</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-reply"></i></div>
							</button>
						</div>
						<div class="btn-group pull-left visible-lg visible-md visible-sm">
							<button class="btn btn-primary" id="btn_outPage_tsqx" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-circle-arrow-up"></i>&nbsp;导出当前页</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-circle-arrow-up"></i></div>
							</button>
						</div>
						<div class="btn-group pull-left visible-lg visible-md visible-sm">
							<button class="btn btn-primary" id="btn_muban_tsqx" type="button">
								   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;下载模板</div>
								   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div>
							</button>
						</div>
					</div>
				</div>
					<!-- 按钮工具条结束 -->
			<div id="query_table_tsqx_div" style="width:100%;height:calc(100% - 40px);overflow:auto;">
			    <table id="query_table_tsqx"  class="table-condensed table-hover table-cursor">
			        <thead>
			            <tr>
			            	<th data-halign="center" data-align="center" data-sortable="false" data-width="50" data-field="state"  data-checkbox="true" data-formatter="FMT_Check"></th>
							<th data-halign="center" data-align="center" data-sortable="false" data-width="40px" data-formatter="FMT_Num">序号</th>
							<th data-halign="center" data-align="center" data-sortable="false" data-field="STNM" data-width="" data-formatter="">站码</th>
							<th data-halign="center" data-align="center" data-sortable="false" data-field="USERNAME" data-width="">用户名</th>
<!-- 							<th data-halign="center" data-align="right" data-sortable="false" data-field="QM" data-width="" data-formatter="">洪峰</th>
							<th data-halign="center" data-align="right" data-sortable="false" data-field="Q" data-width="" data-formatter="">流量</th>
							<th data-halign="center" data-align="center" data-sortable="false" data-field="T" data-width="" data-formatter="">时段</th> -->
							<th data-halign="center" data-align="center" data-sortable="false" data-field="" data-width="140px" data-formatter="FMT_handle">操作</th>
			            </tr>
			        </thead>
			    </table>
		</div>
<!-- 不要改变以下引用顺序 -->
<%@include file="/business/consumer/tsqxData/tsqx/upload.jsp"%>
<%@include file="/business/consumer/tsqxData/tsqx/edit.jsp"%>
<%@include file="/business/consumer/tsqxData/tsqx/query_details.jsp" %>
<%@include file="/business/consumer/tsqxData/tsqx/edit_x.jsp"%>

<script src="../business/consumer/tsqxData/tsqx/query.js"></script>
<script src="../business/consumer/tsqxData/tsqx/list.js"></script>
