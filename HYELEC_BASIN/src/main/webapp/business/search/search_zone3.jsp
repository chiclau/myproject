<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <!DOCTYPE html>
<html lang="zh-cn" class="screen-desktop-wide device-desktop">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
<title>实时雨情展示</title>

<style>
.container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row .col-md-6 {
	width: 0px;
}

.container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row {
	height: 46px;
}

.container-fluid .row:nth-child(3) .col-md-12 {
	height: 211px;
}

#idone table tr td:nth-child(1) {
	width: 73px;
	height: 40px;
}

#idone table tr td:nth-child(2) {
	width: 71px;
}

#idone table tr td:nth-child(3) {
	width: 71px;
}

#idone table tr td:nth-child(4) {
	width: 72px;
}

#idone table tr td {
	vertical-align: middle;
	word-wrap: break-word;
	word-break: break-all;
}
</style>
<script type="text/javascript">

	//下拉列表加载数据
	function show_sub(v) {
		$("#mySelect").empty();
		//alert(v)
		if (v == 0) {
			$("#mySelect").append('<option value="0">-请选择-</option>')
			return;
		}
		$
				.ajax({
					url : basePath
							+ 'search/search!serchProvinceBasin.action?searchFormBean.administrativeRegionBasin='
							+ v,
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(datas) {
						//alert(datas)
						$("#mySelect").append(
								'<option value="0">-请选择-</option>')
						for (var i = 0; i < datas.length; i++) {
							$("#mySelect").append(
									'<option value='+datas[i].id+'>'
											+ datas[i].RVNM + '</option>')
						}
					}
				})
	}
</script>
</head>
<body>
	<div class="row"></div>
	<div class="row" id="posi">
		<div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">
			<select class="form-control"
				style="height: 30px; margin-top: 10px; width: 100px;"
				onchange="show_sub(this.options[this.options.selectedIndex].value)">
				<option value="0">-请选择-</option>
				<option value="1">行政区</option>
				<option value="2">流域</option>
			</select>
		</div>
		<div class="col-md-4 col-lg-4 col-sm-4 col-xs-4" style="float: right;left: -80px">
			<select class="form-control"
				style="height: 30px; margin-top: 10px; width: 100px;" id="mySelect">
				<option value="0">-请选择-</option>
			</select>
		</div>
	</div>

	<div style="height: 50px">
		<div class="row" style="height: 50px">
			<div class="col-md-12" style="margin-top: 12px" style="height:50px ">
				<form class="form-inline">
					<div class="form-group">
						<label class="sr-only" for="exampleInputEmail3">测站名称</label> <input
							type="text" class="form-control" id="text" placeholder="测站名称">
					</div>
					<button type="button" class="btn btn-default btn btn-success"
						style="color: white" onclick="loadDataSecond()">查询</button>
				</form>
			</div>
		</div>
	</div>
	<!-- <div class="row">
            <div class="col-md-12" id="idone">
                 <table class="table table-condensed bordered" >
                      <tr>
                          <th>地区</th>
                          <th>县市</th>
                          <th>站名</th>
                          <th>水位</th>
                      </tr>
                 </table>
	            <div class="row">
					<div class="col-md-12" id="watersmy">
						<table class="table table-condensed bordered" id="waters">
						</table>
					</div>
				</div>
            </div>
        </div> -->
	<div class="row">

		<div class="col-md-12">
			<table class="table table-condensed bordered">
				<tr>
					<th>地区</th>
					<th>站址</th>
					<th>站名</th>
					<th>水位</th>
				</tr>
			</table>
			<div class="row" style="top: 142px">
				<div class="col-md-12"
					style="overflow: auto; height: 178px; width: 298px;float: right;right: 0px">
					<table class="table table-condensed bordered" id="waters">
					</table>
				</div>
				<input type="hidden" value="0" id="mark1">
			</div>
		</div>
	</div>
</body>
</html>