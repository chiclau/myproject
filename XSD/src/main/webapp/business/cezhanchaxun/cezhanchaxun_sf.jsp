<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String address = URLDecoder.decode(URLDecoder.decode(request.getParameter("address"),"utf-8"),"utf-8");
String bj = request.getParameter("bj");//用request得到
%>
<!DOCTYPE html>
<html style="padding-top: 0px;">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/layui/css/layui.css">
<style>
.col-md-6 {
	height: 340px;
}

.col-md-6:nth-child(2), .col-md-6:nth-child(4) {
	padding-left: 0px;
}

#item-one {
	color: white;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	background: #568fc3;
}

.layui-layer-molv .layui-layer-title {
	background: #0e60aa;
}

.layui-layer-setwin .layui-layer-close1 {
	color: white;
}

.table-icon {
	float: right;
	padding: 0px 8px;
	border-radius: 5px;
}

.desplay {
	display: none;
}

.layui-table-body {
	height: 182px;
}

.classtableone thead tr th {
	text-align: center;
	border: none;
}

.classtabletwo tbody tr td {
	text-align: center;
}

.classtablethird tbody tr td {
	text-align: center;
	border: none;
	background: #e6e6e6;
}

#mainonedes_sf_jszt  thead th {
	padding: 3px 0px;
	white-space: normal;
}

#mainonedes_sf_jszt  .layui-table[lay-size=sm] td, .layui-table[lay-size=sm] th
	{
	padding: 3px 0px;
	white-space: nowrap;
}

#mainonedes_sf_kffs  thead th {
	padding: 3px 0px;
	white-space: normal;
}

#mainonedes_sf_kffs  .layui-table[lay-size=sm] td, .layui-table[lay-size=sm] th
	{
	padding: 3px 0px;
	white-space: nowrap;
}
</style>
</head>

<body>
	<div style="height: 300px; width: 100%;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6" style="margin-top: 2px;">
					<ul class="list-group list-group-one" style="position: relative">
						<li class="list-group-item" id="item-one"><i
							class="icon icon-area-chart" style="margin-right: 10px"></i><span
							id="title_id_js"></span>
						<button id="table-icon_sf_jszt" class="btn btn-primary table-icon"
								type="button">切换表格</button>
								<button style="margin-right: 5px;" id="table-iconone"
								class="btn btn-primary table-icon desplay" type="button"
								onclick="export_jstl_qg(1)">导出表格</button>
								</li>
						<li class="list-group-item" id="mainone"
							style="height: 300px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;">
						</li>
						<li class="list-group-item desplay" id="mainonedes_sf_jszt"
							style="padding: 0px; position: absolute; left: 0px; top: 32px; width: 100%; z-index: 333; height: 300px;">
							<div class="layui-table-header">
								<table class="layui-table classtableone" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col>
									</colgroup>
									<thead>
										<tr>
											<th>序号</th>
											<th>地区名称</th>
											<th>总装机规模（万千瓦）/座数</th>
											<th>运行装机规模（万千瓦）/座数</th>
											<th>在建装机规模（万千瓦）/座数</th>
											<th>拟建装机规模（万千瓦）/座数</th>
											<th>拆除装机规模（万千瓦）/座数</th>
											<th>废弃装机规模（万千瓦）/座数</th>
										<th>未知建设状态装机规模（万千瓦）/座数</th>
										</tr>

									</thead>
								</table>
							</div>
							<div class="layui-table-body layui-table-main"
								style="height: 206px;">
								<table class="layui-table classtabletwo" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col>
									</colgroup>
									<tbody id="table_sf_jszt">
									</tbody>
								</table>
							</div>
							<table class="layui-table classtablethird" lay-size="sm"
								style="margin: 0px;">
								<colgroup>
									<col width="80">
									<col width="80">
									<col width="150">
									<col width="200">
									<col width="200">
									<col width="200">
									<col width="200">
									<col width="200">
									<col width="200">
									<col>
								</colgroup>
								<tbody id="zj_table_sf_jszt">
								</tbody>
							</table>
						</li>
					</ul>
				</div>
				<div class="col-md-6" style="margin-top: -8px;">
					<ul class="list-group list-group-one" style="position: relative">
						<li class="list-group-item" id="item-one"><i
							class="icon icon-area-chart" style="margin-right: 10px"></i><span
							id="title_id_kffs"></span>
						<button id="table-icon_kffs_sf" class="btn btn-primary table-icon"
								type="button">切换表格</button>
									<button style="margin-right: 5px;" id="kffs-iconone"
								class="btn btn-primary table-icon desplay" type="button"
								onclick="export_jstl_qg(2)">导出表格</button>
								</li>
						<li class="list-group-item" id="maintwo"
							style="height: 300px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;">
						</li>
						<li class="list-group-item desplay" id="mainonedes_sf_kffs"
							style="padding: 0px; position: absolute; left: 0px; top: 32px; width: 100%; z-index: 333; height: 300px;">
							<div class="layui-table-header">
								<table class="layui-table classtableone" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col>
									</colgroup>
									<thead>
										<tr>
											<th>序号</th>
											<th>地区名称</th>
											<th>总装机规模（万千瓦）/座数</th>
											<th>引水式装机规模（万千瓦）/座数</th>
											<th>坝式装机规模（万千瓦）/座数</th>
											<th>混合式装机规模（万千瓦）/座数</th>
											<th>未知开发方式装机规模（万千瓦）/座数</th>
										</tr>

									</thead>
								</table>
							</div>
							<div class="layui-table-body layui-table-main"
								style="height: 226px;">
								<table class="layui-table classtabletwo" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
									    <col width="200">
										<col>
									</colgroup>
									<tbody id="table_sf_kffs">
									</tbody>
								</table>
							</div>
							<table class="layui-table classtablethird" lay-size="sm"
								style="margin: 0px;">
								<colgroup>
									<col width="80">
									<col width="80">
									<col width="150">
									<col width="200">
									<col width="200">
									<col width="200">
									<col width="200">
									<col width="200">
									<col>
								</colgroup>
								<tbody id="zj_table_sf_kffs">
								</tbody>
							</table>
						</li>
					</ul>
				</div>
				<div class="col-md-12">
					<ul class="list-group list-group-one" style="position: relative">
						<li class="list-group-item" id="item-one"><i
							class="icon icon-area-chart" style="margin-right: 10px"></i><span
							id="title_id_ljtc"></span>
						<button id="table-icon_sf_dz_fzlc"
								class="btn btn-primary table-icon" type="button">切换表格</button>
								<button style="margin-right: 5px;" id="fzlc-iconone"
								class="btn btn-primary table-icon desplay" type="button"
								onclick="export_jstl_qg(3)">导出表格</button>
								</li>
						<li class="list-group-item" id="mainthree"
							style="height: 300px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;">
						</li>
						<li class="list-group-item desplay" id="mainonedes_sf_kffs_date"
							style="padding: 0px; position: absolute; left: 0px; top: 32px; width: 100%; z-index: 333; height: 300px;">
							<div class="layui-table-header">
								<table class="layui-table classtableone" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
												<col width="200">
										<col>
									</colgroup>
									<thead>
										<tr>
											<th>序号</th>
											<th>地区名称</th>
											<th>总装机规模（万千瓦）/座数</th>
											<th>引水式装机规模（万千瓦）/座数</th>
											<th>坝式装机规模（万千瓦）/座数</th>
											<th>混合式装机规模（万千瓦）/座数</th>
											<th>未知开发方式装机规模（万千瓦）/座数</th>
										</tr>

									</thead>
								</table>
							</div>
							<div class="layui-table-body layui-table-main"
								style="height: 242px;">
								<table class="layui-table classtabletwo" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
												<col width="200">
										<col>
									</colgroup>
									<tbody id="table_sf_kffs_date">
									</tbody>
								</table>
							</div>
							<table class="layui-table classtablethird" lay-size="sm"
								style="margin: 0px;">
								<colgroup>
									<col width="80">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">
									<col>
								</colgroup>
								<tbody id="zj_table_sf_kffs_date">
								</tbody>
							</table>
						</li>
					</ul>
				</div>
				<div class="col-md-12">
					<ul class="list-group list-group-one" style="position: relative">
						<li class="list-group-item" id="item-one"><i
							class="icon icon-area-chart" style="margin-right: 10px"></i><span
							id="title_id_zjrl1"></span>
						<button id="table-icon_sf_zj_fzlc"
								class="btn btn-primary table-icon" type="button">切换表格</button>
								<button style="margin-right: 5px;" id="qg_zj_fzlc-iconone"
								class="btn btn-primary table-icon desplay" type="button"
								onclick="export_jstl_qg(4)">导出表格</button>
								</li>
						<li class="list-group-item" id="mainfore"
							style="height: 300px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px;">
						</li>
						<li class="list-group-item desplay"
							id="mainonedes_sf_kffs_date_zj"
							style="padding: 0px; position: absolute; left: 0px; top: 32px; width: 100%; z-index: 333; height: 300px;">
							<div class="layui-table-header">
								<table class="layui-table classtableone" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">	
										<col>
									</colgroup>
									<thead>
										<tr>
											<th>序号</th>
											<th>地区名称</th>
											<th>总装机规模（万千瓦）/座数</th>
											<th>引水式装机规模（万千瓦）/座数</th>
											<th>坝式装机规模（万千瓦）/座数</th>
											<th>混合式装机规模（万千瓦）/座数</th>
											<th>未知开发方式装机规模（万千瓦）/座数</th>
										</tr>

									</thead>
								</table>
							</div>
							<div class="layui-table-body layui-table-main"
								style="height: 242px;">
								<table class="layui-table classtabletwo" lay-size="sm"
									style="margin: 0px;">
									<colgroup>
										<col width="80">
										<col width="80">
										<col width="150">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">
										<col width="200">	
										<col>
									</colgroup>
									<tbody id="table_sf_kffs_date_zj">
									</tbody>
								</table>
							</div>
							<table class="layui-table classtablethird" lay-size="sm"
								style="margin: 0px;">
								<colgroup>
									<col width="80">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">
									<col width="230">	
									<col>
								</colgroup>
								<tbody id="zj_table_sf_kffs_date_zj">
								</tbody>
							</table>
						</li>
					</ul>
				</div>
			</div>
		</div>

	</div>
</body>
<script src="<%=basePath%>common/layui/lay/modules/layer.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	address = '<%=address %>';
	bj = '<%=bj %>';
 	initJszt(bj,address);
    initKffs(bj,address);
    initDzsl(bj,address);
    initZjrl(bj,address);
})
function export_jstl_qg(num){
	address = '<%=address %>';
	 var tabTitle;
	 var sqlTitle;
	 var sql ;
	 var fileName;
	 if(num==1){
		 fileName = address+"小水电建设状态统计" 
		  tabTitle = ['序号','地区','总装机数量（万千瓦）','总电站数量（座）','拟建装机规模（万千瓦）','拟建电站数量（座）'
			 ,'在建装机规模（万千瓦）','在建电站数量（座）','运行装机规模（万千瓦）','运行电站数量（座）'
			 ,'废弃装机规模（万千瓦）','废弃电站数量（座）','拆除装机规模（万千瓦）','拆除电站数量（座）'
			 ,'未知建设状态装机规模（万千瓦）','未知建设状电站数量（座）']
		  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4','rl5','dz5','rl6','dz6']
		 		sql =" select H.shi AS name,CAST(Round(SUM(H.zjrl)/10000,2) as DECIMAL(18,2)) AS rlz,COUNT(H.id) AS dzz,    "
		 			+ " SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz1,   "
		 			+ " SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz2,  "
		 			+ " SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3,  "
		 			+ " SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz4,  "
		 			+ " SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz5,  "
		 			+ " SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl1,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl2,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl3, "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl4, "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl5,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl6  "
		 			+ " from hps_info_sum1 H  WHERE H.sheng = '"+address+"' "
		 			+ " GROUP BY H.shi,H.sheng HAVING COUNT(H.id) > 0   "
		 			+ " UNION "
		 			+ " select '总数'AS name,CAST(Round(SUM(H.zjrl)/10000,2) as DECIMAL(18,2)) AS rlz,COUNT(H.id) AS dzz, "
		 			+ " SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz1,  "
		 			+ " SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz2, "
		 			+ " SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3,  "
		 			+ " SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz4, "
		 			+ " SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz5,  "
		 			+ " SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl1,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl2,   "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl3,   "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl4,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl5,    "
		 			+ " CAST(Round(SUM(CASE WHEN H.jszt = 6 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2))AS rl6   "
		 			+ " from hps_info_sum1 H  WHERE H.sheng = '"+address+"'  "
		 			+ " GROUP BY H.sheng HAVING COUNT(H.id) > 0       " 
		 			/*  " select H.sheng AS name, Round(SUM(H.zjrl)/10000,2) AS rlz,COUNT(H.id) AS dzz,  "
				       +" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz1,   "
				       +" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz2,  "
				       +" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3,  "
				       +" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz4,  "
				       +" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz5,  "
				       +" SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6,  "
				       +" Round(SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )/10000,2) AS rl2,   "
				       +" Round(SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )/10000,2) AS rl1, "
				       +" Round(SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )/10000,2) AS rl3, "
				       +" Round(SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )/10000,2) AS rl4, "
				       +" Round(SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )/10000,2) AS rl5, "
				       +" Round(SUM(CASE WHEN H.jszt = 6 then H.zjrl else 0 end )/10000,2) AS rl6 "
				       +" from hps_info_sum1 H  "
				       +" GROUP BY H.sheng HAVING COUNT(H.id) > 0 and H.sheng ='"+address+"' "
				       +" UNION ALL  "
				       +" SELECT '总计' AS name, Round(SUM(H.zjrl)/10000,2) AS rlz,COUNT(H.id) AS dzz,  "
				       +" SUM(CASE WHEN H.jszt = 4 then 1 else 0 end )AS dz1,   "
				       +" SUM(CASE WHEN H.jszt = 5 then 1 else 0 end )AS dz2,  "
				       +" SUM(CASE WHEN H.jszt = 3 then 1 else 0 end )AS dz3,  "
				       +" SUM(CASE WHEN H.jszt = 2 then 1 else 0 end )AS dz4,  "
				       +" SUM(CASE WHEN H.jszt = 1 then 1 else 0 end )AS dz5,  "
				       +" SUM(CASE WHEN H.jszt = 6 then 1 else 0 end )AS dz6,  "
				       +" Round(SUM(CASE WHEN H.jszt = 5 then H.zjrl else 0 end )/10000,2) AS rl2,   "
				       +" Round(SUM(CASE WHEN H.jszt = 4 then H.zjrl else 0 end )/10000,2) AS rl1, "
				       +" Round(SUM(CASE WHEN H.jszt = 3 then H.zjrl else 0 end )/10000,2) AS rl3, "
				       +" Round(SUM(CASE WHEN H.jszt = 2 then H.zjrl else 0 end )/10000,2) AS rl4, "
				       +" Round(SUM(CASE WHEN H.jszt = 1 then H.zjrl else 0 end )/10000,2) AS rl5, "
				       +" Round(SUM(CASE WHEN H.jszt = 6 then H.zjrl else 0 end )/10000,2) AS rl6 "
				       +" from hps_info_sum1 H WHERE H.sheng ='"+address+"'"
				       +" HAVING COUNT(H.id) > 0  " */
	 }else if(num==2){
		 fileName = address+"小水电开发方式统计" 
			  tabTitle = ['序号','地区','总装机数量（万千瓦）','总电站数量（座）'
				  ,'坝后式装机规模（万千瓦）','坝后式电站数量（座）'
				 ,'河床式装机规模（万千瓦）','河床式电站数量（座）'
				 ,'混合式装机规模（万千瓦）','混合式电站数量（座）'
				 ,'引水式装机规模（万千瓦）','引水式电站数量（座）'
				 ,'未知开发方式装机规模（万千瓦）','未知开发方式电站数量（座）']
			  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4','rl5','dz5']
		 		sql = " SELECT H.shi AS name,CAST(Round(SUM(H.zjrl)/10000,2) as DECIMAL(18,2))  AS rlz,COUNT(H.id) AS dzz,  "
		 			+ " SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1,  "
		 			+ " SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
		 			+ " SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3,  "
		 			+ " SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4,  "
		 			  +" SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz5,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl1,   "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl2, "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl3, "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl4, "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl5 "
		 			+ " FROM hps_info_sum1 H  "
		 			+ " GROUP BY H.shi,H.sheng HAVING COUNT(H.id) > 0 AND H.sheng = '"+address+"'  "
		 			+ " UNION "
		 			+ " SELECT '总数' AS name,CAST(Round(SUM(H.zjrl)/10000,2) as DECIMAL(18,2)) AS rlz,COUNT(H.id) AS dzz,  "
		 			+ " SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1, "
		 			+ " SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
		 			+ " SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, "
		 			+ " SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4, "
		 			+ " SUM(CASE WHEN H.kffs = 5 then 1 else 0 end )AS dz5, "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl1, "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl2, "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl3,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl4,  "
		 			+ " CAST(Round(SUM(CASE WHEN H.kffs = 5 then H.zjrl else 0 end )/10000,2) as DECIMAL(18,2)) AS rl5  "
		 			+ " FROM hps_info_sum1 H  "
		 			+ " GROUP BY H.sheng HAVING COUNT(H.id) > 0 AND H.sheng = '"+address+"' "
	 }else if(num==3){
		 fileName = address+"小水电发展历程" 
			  tabTitle = ['序号','年份','总装机数量（万千瓦）','总电站数量（座）'
				  ,'坝后式装机规模（万千瓦）','坝后式电站数量（座）'
				 ,'河床式装机规模（万千瓦）','河床式电站数量（座）'
				 ,'混合式装机规模（万千瓦）','混合式电站数量（座）'
				 ,'引水式装机规模（万千瓦）','引水式电站数量（座）']
			  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4']
			     	sql = " SELECT A.date AS name,CAST(SUM(ISNULL(H.zjrl, 0))/10000 AS DECIMAL(18,2))  AS rlz,COUNT(H.id) AS dzz,   "
			     		+ " SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1, "
			     		+ " SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
			     		+ " SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, "
			     		+ " SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl1, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl2, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl3, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl4  "
			     		+ " FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.dysj) AND H.sheng ='"+address+"' "
			     		+ " GROUP BY A.date HAVING A.date <= GETDATE() "
			     		+ " UNION "
			     		+ " SELECT '总数' AS name,CAST(SUM(ISNULL(H.zjrl, 0))/10000 AS DECIMAL(18,2)) AS rlz,COUNT(H.id) AS dzz, "
			     		+ " SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1,  "
			     		+ " SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
			     		+ " SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, "
			     		+ " SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl1,  "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl2, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl3, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl4 "
			     		+ " FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.dysj) AND H.sheng ='"+address+"' "
	 }else if(num==4){
		 fileName = address+"小水电发展历程" 
			  tabTitle = ['序号','年份','总装机数量（万千瓦）','总电站数量（座）'
				  ,'坝后式装机规模（万千瓦）','坝后式电站数量（座）'
				 ,'河床式装机规模（万千瓦）','河床式电站数量（座）'
				 ,'混合式装机规模（万千瓦）','混合式电站数量（座）'
				 ,'引水式装机规模（万千瓦）','引水式电站数量（座）']
			  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4']
				 sql = " SELECT A.date AS name,CAST(SUM(ISNULL(H.zjrl, 0))/10000 AS DECIMAL(18,2))  AS rlz,COUNT(H.id) AS dzz,   "
			     		+ " SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1, "
			     		+ " SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
			     		+ " SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, "
			     		+ " SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl1, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl2, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl3, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl4  "
			     		+ " FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.dysj) AND H.sheng ='"+address+"' "
			     		+ " GROUP BY A.date HAVING A.date <= GETDATE() "
			     		+ " UNION "
			     		+ " SELECT '总数' AS name,CAST(SUM(ISNULL(H.zjrl, 0))/10000 AS DECIMAL(18,2)) AS rlz,COUNT(H.id) AS dzz, "
			     		+ " SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1,  "
			     		+ " SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
			     		+ " SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3, "
			     		+ " SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl1,  "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl2, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl3, "
			     		+ " CAST(SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )/10000 AS DECIMAL(18,2))AS rl4 "
			     		+ " FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.dysj) AND H.sheng ='"+address+"' "
	 }
	 daochu(fileName,tabTitle,sqlTitle,sql)
}
function daochu(fileName,tabTitle,sqlTitle,sql){
	 $.ajax({
	  	 url : basePath + "sjjs/sjjs!comDetails.action",
	 	 type : "post",
	     dateType : "JSON",
	 	 async : false,
	 	 traditional:true,
	     data : {"uploadBean.tabTitle":tabTitle
	    	 	,"uploadBean.sqlTitle":sqlTitle
	    	 	,"uploadBean.sql":sql
	    	 	,"uploadBean.fileName":fileName
	    	 	},
	 	 success : function(response){
	  		 window.location.href=basePath + "sjjs/sjjs!comUpload.action"   
	  	 }
	})
}

function initTable(bj){
	$.ajax({
		url:basePath+"homePage/homePage!countTbzh.action",
		data:{"bj":bj,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows;
			var tr = "";
			var len = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			var zj_zj4 =0; var zj_dz4 =0; var zj_zj5 =0; var zj_dz5 =0; 
			var zj_zj6=0; var zj_dz6 =0;
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				zj_zj +=row.rlz;zj_dz +=row.dzz;
				zj_zj1+=row.rl1;zj_dz1+=row.dz1;zj_zj2+=row.rl2;zj_dz2+=row.dz2
				zj_zj3+=row.rl3;zj_dz3+=row.dz3;zj_zj4+=row.rl4;zj_dz4+=row.dz4
				zj_zj5+=row.rl5;zj_dz5+=row.dz5;zj_zj6+=row.rl6;zj_dz6+=row.dz6;
				tr +="<tr>"
					+"<td>"+(len++)+"</td>"
					+"<td>"+row.name+"</td>"
					+"<td>"+(row.rlz/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dzz+"</td>"
					+"<td>"+(row.rl1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz1+"</td>"
					+"<td>"+(row.rl2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz2+"</td>"
					+"<td>"+(row.rl3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz3+"</td>"
					+"<td>"+(row.rl4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz4+"</td>"
					if(bj == "sf_jszt"){
						tr += "<td>"+(row.rl5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz5+"</td>" 
						tr += "<td>"+(row.rl6/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz6+"</td>" 
					}
					+"</tr>"
			}
			if(bj == "sf_jszt"){
				$("#table_sf_jszt").html("");
				$("#table_sf_jszt").append(tr);
			}
			if(bj == "sf_kffs"){
				$("#table_sf_kffs").html("");
				$("#table_sf_kffs").append(tr);
			}
			if(bj == "sf_kffs_date"){
				$("#table_sf_kffs_date").html("");
				$("#table_sf_kffs_date").append(tr);
			}
			if(bj == "sf_kffs_date_zj"){
				$("#table_sf_kffs_date_zj").html("");
				$("#table_sf_kffs_date_zj").append(tr);
			}
			if(bj != "sf_kffs_date" && bj != "sf_kffs_date_zj"){
				var zj_tr = "<tr>"
					+"<td colspan = '2'style='display:inline-block;width: 120px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
					+"<td>"+(zj_zj4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
					if(bj == "sf_jszt"){
						var zj_tr = "<tr>"
							+"<td colspan = '2'style='display:inline-block;width: 80px;'>总计</td>"
							+"<td>"+(zj_zj/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
							+"<td>"+(zj_zj1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
							+"<td>"+(zj_zj2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
							+"<td>"+(zj_zj3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
							+"<td>"+(zj_zj4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
					+	"<td>"+(zj_zj5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
						+"<td>"+(zj_zj6/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz6+"</td>"
					}
					+"</tr>";
			}else{
				var zj_tr = "<tr>"
					/* +"<td>"+(len++)+"</td>" */
					+"<td colspan = '1'style='width: 165px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
					+"<td>"+(zj_zj4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
				/* 	+"<td>"+(zj_zj5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>" */
					if(bj == "sf_jszt"){
						zj_tr +="<td>"+(zj_zj5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
						zj_tr +="<td>"+(zj_zj6/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz6+"</td>"
					}
					+"</tr>";
			}
			if(bj == "sf_jszt"){
				$("#zj_table_sf_jszt").html("");
				$("#zj_table_sf_jszt").append(zj_tr);
			}
			if(bj == "sf_kffs"){
				$("#zj_table_sf_kffs").html("");
				$("#zj_table_sf_kffs").append(zj_tr);
			}
			if(bj == "sf_kffs_date"){
				$("#zj_table_sf_kffs_date").html("");
				$("#zj_table_sf_kffs_date").append(zj_tr);
			}
			if(bj == "sf_kffs_date_zj"){
				$("#zj_table_sf_kffs_date_zj").html("");
				$("#zj_table_sf_kffs_date_zj").append(zj_tr);
			}
		}
	})
}
function initKffs(bj,address){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('maintwo'));
    var kffsLeng = [];
    var kffs = [];
    var datas = [];
    var value = 0;
    if(bj == "1"){
		$("#title_id_kffs").text(address+"小水电开发方式统计");
		var text = address+"小水电开发方式统计"
 	}
     $.ajax({
		url:basePath+"homePage/homePage!countKffs.action",
		data:{"bj":bj,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				kffsLeng.push(data[i].name);
				kffs.push(data[i])
				value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: text,
		     		        x:'center'
		     		    },
		     		 tooltip: {
		     		        trigger: 'item',
		     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		     		    },
		     		    legend: {
		     		        orient: 'horizontal',
		     		        bottom: 5,
		     		        data:kffsLeng
		     		    },
		     		   color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D'],
		     		    series: [
		     		    	 {
			     		            name:'总数',
			     		            type:'pie',
			     		            center:['50%','46.5%'],
			     		            selectedMode: 'single',
			     		            radius: [0, '32.5%'],
			     		            label: {
			     		            	show:true,
			     		                normal: {
			     		                    position: 'center',
			     		                    formatter: '{b}\n {c}',
			     		                    color:'white'
			     		                }
			     		            },
			     		            labelLine: {
			     		                normal: {
			     		                    show: false
			     		                }
			     		            },
			     		           data:datas
			     		           
			     		        },
		     		        {
		     		            name:'',
		     		            type:'pie',
		     		            radius: ['40%', '55%'],
		     		            label: {
		     		                normal: {
		     		                	show: true,
		     		                    formatter: '{b}: {c}({d}%)'
		     		                }
		     		            },
		     		            data:kffs
		     		        }
		     		    ]
		     };
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '('+address+')小水电数量/容量统计图',
					  area: ['1300px', '607px'],
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame.jsp?name='+name+"&bj="+4+"&address="+address,
				});
			});
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
		}
	});
     //点击切换图标文字和显示隐藏table
     $('#table-icon_kffs_sf').click(function(){
    	 if( $('#mainonedes_sf_kffs').hasClass('desplay')){
    		 $('#table-icon_kffs_sf').text('切换图表');
    		 $('#mainonedes_sf_kffs').removeClass('desplay');
    		 if($('#kffs-iconone').hasClass('desplay')){
    			 $('#kffs-iconone').removeClass('desplay');
    		 }else{
    			 $('#table-iconone').addClass('desplay');
    		 }
    		 initTable("sf_kffs")
    	 }
    	  else{
    		  $('#mainonedes_sf_kffs').addClass('desplay');
    		  $('#table-icon_kffs_sf').text('切换表格');
    		  $('#kffs-iconone').addClass('desplay');
    	 }
     })
}
        
/////////////////////////////////////////////////建设状态统计////////////////////////////////////////////////////////////////
        // 基于准备好的dom，初始化echarts实例
function initJszt(bj,address){
	 var myChart = echarts.init(document.getElementById('mainone'));
	 if(bj == "1"){
			$("#title_id_js").text(address+"小水电建设状态统计 ");
			var text = address+"小水电建设状态统计 "
	 }
     var leng = [];
     var jszt = [];
     var datas = [];
     var value = 0;
     $.ajax({
 		url:basePath+"homePage/homePage!countJszt.action",
 		data:{"bj":bj,"address":address},
 		type: "POST",
 		dataType:"json",
 		success:function(response){
 			var data = response.rows
 			for (var i = 0; i < data.length; i++) {
					leng.push(data[i].name);
					jszt.push(data[i])
					value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
				     // 指定图表的配置项和数据
				     var option = {
				     		 title : {
				  		        text: text,
				  		        x:'center'
				  		    },
				     		 tooltip: {
				     		        trigger: 'item',
				     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
				     		    },
				     		    legend: {
				     		        orient: 'horizontal',
				     		        bottom: 5,
				     		        data:leng
				     		    },
				     		   color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D',' #989898',],
				     		    series: [
				     		        {
				     		            name:'总数',
				     		            type:'pie',
				     		            center:['50%','46.5%'],
				     		            selectedMode: 'single',
				     		            radius: [0, '32.5%'],
				     		           color:'#007bbb',
				     		            label: {
				     		            	show:true,
				     		                normal: {
				     		                    position: 'center',
				     		                    formatter: '{b}\n {c}',
				     		                    color:'white'
				     		                }
				     		            },
				     		            labelLine: {
				     		                normal: {
				     		                    show: false
				     		                }
				     		            },
				     		           data:datas
				     		           
				     		        },
				     		        {
				     		            name:'',
				     		            type:'pie',
				     		            radius: ['40%', '55%'],
				     		            label: {
				     		                normal: {
				     		                	show: true,
				     		                    formatter: '{b}: {c}({d}%)'
				     		                }
				     		            },
				     		            data:jszt
				     		        }
				     		    ]
				     };
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '('+address+')小水电数量/容量统计图',
					  area: ['1300px', '607px'],
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame.jsp?name='+name+"&bj="+5+"&address="+address,
				});
			});
				     // 使用刚指定的配置项和数据显示图表。
				     myChart.setOption(option);
 		}
 	});
     //点击切换图标文字和显示隐藏table
     $('#table-icon_sf_jszt').click(function(){
    	 if( $('#mainonedes_sf_jszt').hasClass('desplay')){
    		 $('#table-icon_sf_jszt').text('切换图表');
    		 $('#mainonedes_sf_jszt').removeClass('desplay');
    		 if($('#table-iconone').hasClass('desplay')){
    			 $('#table-iconone').removeClass('desplay');
    		 }else{
    			 $('#table-iconone').addClass('desplay');
    		 }
    		 initTable("sf_jszt")
    	 }
    	  else{
    		  $('#mainonedes_sf_jszt').addClass('desplay');
    		  $('#table-icon_sf_jszt').text('切换表格');
    		  $('#table-iconone').addClass('desplay');
    		  
    	 }
     })
}
        
        
///////////////////////////////////////////////装机容量折线图/////////////////////////////////////////////////////        
function initZjrl(bj,address){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('mainfore'));
    if(bj == "1"){
		$("#title_id_zjrl1").text(address+"小水电发展历程");
		var text =address+"小水电发展历程"
 	}
    var sum = [];
    var sum1 = [];
    var yer = [];
    $.ajax({
		url:basePath+"homePage/homePage!countZjrl.action",
		data:{"bj":bj,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				sum.push(data[i].sun);
				yer.push(data[i].yer);
				sum1.push(data[i].sum);
			}
			var colors = ['#007bbb','#008e57'];
			option = {
			    color: colors,
			    title : {
			    	text:text,
     		        x:'center'
     		    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    grid: {
			    	 right:"100", //组件离容器右侧的距离,百分比字符串或整型数字 bottom:"auto", //组件离容器下侧的距离,百分比字符串或整型数字 width:"auto", //图例宽度 height:"auto", //图例高度
			    	 left:"100",
			    	 top:"70",  
			    	 bottom:"70",
			    },
			    toolbox: {
			        feature: {
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    legend: {
			    	 bottom: 5,
			        data:['对应年份投产水电站装机规模','累积投运水电站装机规模']
			    },
			    xAxis: [
			        {
			            type: 'category',
			            axisLabel: {
                           interval:0,
                           rotate:40
                        },
			            axisTick: {
			                alignWithLabel: true
			            },
			            data:yer
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '累积投运水电站装机规模(万千瓦)',
			            min: 0,
			            position: 'right',
			            axisLine: {
			                lineStyle: {
			                    color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        { },
			        {
			            type: 'value',
			            name: '对应年份投产水电站装机规模(万千瓦)',
			            min: 0,
			            position: 'left',
			            axisLine: {
			                lineStyle: {
			                    color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series: [
			        {
			            name:'累积投运水电站装机规模',
			            type:'bar',
			            data:sum1
			        },
			        {
			            name:'对应年份投产水电站装机规模',
			            type:'line',
			            yAxisIndex: 2,
			            data:sum
			        }
			    ]
			};
		    myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '('+address+')小水电发展历程详细信息',
					  area: ['1300px', '607px'],
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame_fzlc.jsp?name='+name+"&bj="+3+"&address="+address,
				});
			});
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		    window.onresize = myChart.resize;
		}
	});
    $('#table-icon_sf_zj_fzlc').click(function(){
      	 if( $('#mainonedes_sf_kffs_date_zj').hasClass('desplay')){
      		 $('#table-icon_sf_zj_fzlc').text('切换图表');
      		 $('#mainonedes_sf_kffs_date_zj').removeClass('desplay');
      		 if($('#qg_zj_fzlc-iconone').hasClass('desplay')){
    			 $('#qg_zj_fzlc-iconone').removeClass('desplay');
    		 }else{
    			 $('#table-iconone').addClass('desplay');
    		 }
      		 initTable("sf_kffs_date_zj")
      	 }
      	  else{
      		  $('#mainonedes_sf_kffs_date_zj').addClass('desplay');
      		  $('#table-icon_sf_zj_fzlc').text('切换表格');
      		  $('#qg_zj_fzlc-iconone').addClass('desplay');
      	 }
       })
}
        
        
        
////////////////////////////////////电站数量统计////////////////////////////////////////////      
    // 基于准备好的dom，初始化echarts实例
function initDzsl(bj,address){
    var myChart = echarts.init(document.getElementById('mainthree'));
    if(bj == "1"){
		$("#title_id_ljtc").text(address+"小水电发展历程");
		var text = address+"小水电发展历程"
 	}
    var sum3 = [];
    var sum4 = [];
    var yer4 = [];
    $.ajax({
		url:basePath+"homePage/homePage!countDz.action",
		data:{"bj":bj,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				sum3.push(data[i].sun);
				yer4.push(data[i].yer);
				sum4.push(data[i].sum);
			} 
			var colors = ['#007bbb','#008e57'];

			option = {
			    color: colors,
			    title : {
			    text:text,
     		        x:'center'
     		    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    grid: {
			    	 right:"100", //组件离容器右侧的距离,百分比字符串或整型数字 bottom:"auto", //组件离容器下侧的距离,百分比字符串或整型数字 width:"auto", //图例宽度 height:"auto", //图例高度
			    	 left:"100",
			    	 top:"70",  
			    	 bottom:"70",
			    },
			    toolbox: {
			        feature: {
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    legend: {
			    	 bottom: 5,
			        data:['对应年份投产水电站数量','累积投运水电站数量']
			    },
			    xAxis: [
			        {
			            type: 'category',
                       axisLabel: {
                           interval:0,
                           rotate:40
                        },
			            axisTick: {
			                alignWithLabel: true
			            },
			            data: yer4
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '累积投运水电站数量(座)',
			            min: 0,
			            position: 'right',
			           // splitLine:{show: false},//去除网格线
			            axisLine: {
			                lineStyle: {
			                	 color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        { },
			        {
			            type: 'value',
			            name: '对应年份投产水电站数量(座)',
			            //splitLine:{show: false},//去除网格线
			            min: 0,
			            position: 'left',
			            axisLine: {
			                lineStyle: {
			                	 color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series: [
			        {
			            name:'累积投运水电站数量',
			            type:'bar',
			            data:sum4
			        },
			        {
			            name:'对应年份投产水电站数量',
			            type:'line',
			            yAxisIndex: 2,
			            data:sum3
			        }
			    ]
			};
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '('+address+')小水电发展历程详细信息',
					  area: ['1300px', '607px'],
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame_fzlc.jsp?name='+name+"&bj="+3+"&address="+address,
				});
			});
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		    window.onresize = myChart.resize;
		}
	});
    //点击切换图标文字和显示隐藏table
    $('#table-icon_sf_dz_fzlc').click(function(){
   	 if( $('#mainonedes_sf_kffs_date').hasClass('desplay')){
   		 $('#table-icon_sf_dz_fzlc').text('切换图表');
   		 $('#mainonedes_sf_kffs_date').removeClass('desplay');
   		 if($('#fzlc-iconone').hasClass('desplay')){
			 $('#fzlc-iconone').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
   		 initTable("sf_kffs_date")
   	 }
   	  else{
   		  $('#mainonedes_sf_kffs_date').addClass('desplay');
   		  $('#table-icon_sf_dz_fzlc').text('切换表格');
   		  $('#fzlc-iconone').addClass('desplay');
   	 }
    })
}
</script>