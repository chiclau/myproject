<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 按钮工具条开始 -->
<style type="text/css">
.margin {margin-right:960px;}
.className{
    width:270px;
    height:150px;
    margin:0 auto;
}
</style>
<div class="row-fluid col-md-12">
	<div class="btn-group margin" align="left">
		<button type="button" class="btn btn-primary" id="btn_chart_tl">图形</button>
		<button type="button" class="btn btn-default" id="btn_table_tl">表格</button>
	</div>
	<div id="tf" style="font-size:20px">推理峰量法表格</div>
	<div class="table-responsive">
			<table id="tbinfo_tl" class="table table-bordered text-center tailSaleChartTable">
				<!-- <tr>
					<td>12</td>
					<td>34</td>
					<td>65</td>
					<td>35</td>
					<td>34</td>
					<td>53</td>
				</tr> -->
			</table>
	</div>
	<div id="tlflf_main" style="width:800px;height:400px;" class="className">
	</div>
</div>
