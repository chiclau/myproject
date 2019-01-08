<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String name = request.getParameter("name");//用request得到
	String id = request.getParameter("id");//用request得到
%>
<!DOCTYPE html>
<html style="padding-top: 0px;">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/layui/css/layui.css">
<!-- zui css -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/zui/css/zui.css">
<script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js"
	charset="utf-8"></script>
	<script src="<%=basePath%>common/eCharts/chanliu-echarts.min.js"></script>
<style>
.col-md-6 {
	height: 340px;
}

.col-md-6:nth-child(2), .col-md-6:nth-child(4) {
	padding-left: 0px;
}

#item-one {
	color: white;
	background: #568fc3;
}

.list-group-item {
	padding: 5px 13px;
}

.color-one {
	background: #518cc2 !important;
	color: white;
}

.style-one {
	white-space: normal nowrap;
	text-align: left !important;
}

table tr td:nth-child(1), table tr td:nth-child(3) {
	background: #edf0f6;
	text-align: right;
}
.layui-layer-title{
background:#518cc2;
}
</style>
</head>

<body style="padding: 15px;">
	<table class="layui-table" style="margin: 0px;">
		<colgroup>
			<col width="200">
			<col width="200">
			<col width="200">
			<col width="200">
			<col>
		</colgroup>
		<tbody id="table_id">
		</tbody>
	</table>
</body>
<script>
   //项目路径
var basePath = '<%=basePath%>';
var name = '<%=name%>';
var id = '<%=id%>';
$(function(){
   initTable(id,name);
})
function initTable(id,name){
	debugger;
	//var formBean.xmmc = name
	$.ajax({
		url:basePath+"sjjs/sjjs!selectDetails.action",
		data:{"formBean.id": id,"formBean.xmmc": name},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			$.ajax({
				url : basePath + "sjjs/sjjs!selectDzFrom.action",
				data:{"formBean.id": id,"formBean.xmmc": name},
				type : "post",
				dateType : "JSON",
				traditional : true,
				success : function(query){
					var q = $.parseJSON( query );
					var xmmc = tabData(q.rows.xmmc_flag);
					var sheng = tabData(q.rows.sheng_flag);
					var shi = tabData(q.rows.shi_flag);
					var xian = tabData(q.rows.xian_flag);
					var yjzll = tabData(q.rows.yjzl_flag);
					var jthll = tabData(q.rows.jthl_flag);
					var xmszlyl = tabData(q.rows.xmszly_flag);
					var jsNamel = tabData(q.rows.jszt_flag);
					var zjrll = tabData(q.rows.zjrl_flag);
					var kfNamel = tabData(q.rows.kffs_flag);
					var tcnyl = tabData(q.rows.tcny_flag);
					var sjnfdll = tabData(q.rows.sjnfdl_flag);
					var sjnfdlXzl = tabData(q.rows.sjnfdl_xz_flag);
					var ztzl = tabData(q.rows.ztz_flag);
					var tzxzl = tabData(q.rows.tzxz_flag);
					var tzlyl = tabData(q.rows.tzly_flag);
					var bwqkl = tabData(q.rows.bwqk_flag);
					var zhltqkl = tabData(q.rows.zhltqk_flag);
					var zrkl = tabData(q.rows.zrk_flag);
					var bgl = tabData(q.rows.bg_flag);
					var swdjl = tabData(q.rows.swdj_flag);
					var sfyxmhzl = tabData(q.rows.sfyxmhz_flag);
					var sffhghl = tabData(q.rows.sffhgh_flag);
					var sffhghhpl = tabData(q.rows.sffhghhp_flag);
					var sftgjghbysl = tabData(q.rows.sftgjghbys_flag);
					var sftgxmhpl = tabData(q.rows.sftgxmhp_flag);
					var hpspsjl = tabData(q.rows.hpspsj_flag);
					var spwjwhl = tabData(q.rows.spwjwh_flag);
					var stllxfcsl = tabData(q.rows.stllxfcs_flag);
					var stlljkssl = tabData(q.rows.stlljkss_flag);
					var gycsl = tabData(q.rows.gycs_flag);
					var zzflcsl = tabData(q.rows.zzflcs_flag);
					var sfsjzrbhql = tabData(q.rows.sfsjzrbhq_flag);
					var hxql = tabData(q.rows.hxq_flag);
					var bxsfcztsgkl = tabData(q.rows.bxsfcztsgk_flag);
					var hcql = tabData(q.rows.hcq_flag);
					var sysl = tabData(q.rows.sys_flag);
					var zrbhqslyjl = tabData(q.rows.zrbhqslyj_flag);
					var wfql = tabData(q.rows.wfq_flag);
					var sjnfdl = data.sjnfdl;
					if(sjnfdl == 0){
						sjnfdl = "";
					}
					var sjnfdlXz = data.sjnfdlXz;
					if(sjnfdlXz == 0){
						sjnfdlXz = "";
					}
					var ztz = data.ztz;
					if(ztz == 0){
						ztz = "";
					}
					var bg = data.bg;
					if(bg == 0){
						bg = "";
					}
					var swdj = data.swdj;
					if(swdj == 0){
						swdj = "";
					}
					var zjrl = data.zjrl;
					if(zjrl == 0){
						zjrl = "";
					}
					var zrbhqslyj = data.zrbhqslyj;
					if(zrbhqslyj == "1900-01-01"){
						zrbhqslyj = "";
					}
					var hpspsj = data.hpspsj;
					if(hpspsj == "1900-01-01"){
						hpspsj = "";
					}
					var tcny = data.tcny;
					if(tcny == "1900-01-01"){
						tcny = "";
					}
					var tr = '<tr>'
				      	+'<td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-search"></i>基础信息</td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>项目名称：</td>'
					   	+'<td>'+data.xmmc+'<span style="float:right;">'+ xmmc +'</c:if></span></td>'
					   	+'<td>省级：</td>'
					   	+'<td>'+data.sheng+'<span style="float:right;">'+ sheng +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>市级：</td>'
					   	+'<td>'+data.shi+'<span style="float:right;">'+ shi +'</c:if></span></td>'
					   	+'<td>县级：</td>'
					   	+'<td>'+data.xian+'<span style="float:right;">'+ xian +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>一级支流：</td>'
					   	+'<td>'+data.yjzl+'<span style="float:right;">'+ yjzll +'</c:if></span></td>'
					   	+'<td>所在流域：</td>'
					   	+'<td>'+data.xmszly+'<span style="float:right;">'+ xmszlyl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>所在具体支流：</td>'
					   	+'<td>'+data.jthl+'<span style="float:right;">'+ jthll +'</c:if></span></td>'
					   	+'<td>建设运营状态：</td>'
					   	+'<td>'+data.jsName+'<span style="float:right;">'+ jsNamel +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>装机容量（千瓦）：</td>'
					   	+'<td>'+zjrl+'<span style="float:right;">'+ zjrll +'</c:if></span></td>'
					   	+'<td>开发方式：</td>'
					   	+'<td>'+data.kfName+'<span style="float:right;">'+ kfNamel +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>投产时间：</td>'
					   	+'<td>'+tcny+'<span style="float:right;">'+ tcnyl +'</c:if></span></td>'
					   	+'<td></td>'
					   	+'<td></td>  '
					   	+'</tr>'
					   	+'<td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-table"></i>工程信息</td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>设计年发电量（万千瓦时）：</td>'
					   	+'<td>'+sjnfdl+'<span style="float:right;">'+ sjnfdll +'</c:if></span></td>'
					   	+'<td>年发电量（万千瓦时）：</td>'
					   	+'<td>'+sjnfdlXz+'<span style="float:right;">'+ sjnfdlXzl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>总投资（万元）：</td>'
					   	+'<td>'+ztz+'<span style="float:right;">'+ ztzl +'</c:if></span></td>'
					   	+'<td>投资性质（所有制）：</td>'
					   	+'<td>'+data.tzxz+'<span style="float:right;">'+ tzxzl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>投资来源：</td>'
					   	+'<td>'+data.tzly+'<span style="float:right;">'+ tzlyl +'</c:if></span></td>'
					   	+'<td>并网情况：</td>'
					   	+'<td>'+data.bwqk+'<span style="float:right;">'+ bwqkl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>综合利用情况：</td>'
					   	+'<td>'+data.zhltqk+'<span style="float:right;">'+ zhltqkl +'</c:if></span></td>'
					   	+'<td>总库容（万立方米）：</td>'
					   	+'<td>'+data.zrk+'<span style="float:right;">'+ zrkl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>坝高（米）：</td>'
					   	+'<td>'+bg+'<span style="float:right;">'+ bgl +'</c:if></span></td>'
					   	+'<td>上网电价（元/千瓦时）：</td>'
					   	+'<td>'+swdj+'<span style="float:right;">'+ swdjl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-paste"></i>审批及管理情况</td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td >是否有项目核准（审批）：</td>'
					   	+'<td>'+data.sfyxmhz+'<span style="float:right;">'+ sfyxmhzl +'</c:if></span></td>'
					   	+'<td>是否符合规划：</td>'
					   	+'<td>'+data.sffhgh+'<span style="float:right;">'+ sffhghl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>是否符合规划环评：</td>'
					   	+'<td>'+data.sffhghhp+'<span style="float:right;">'+ sffhghhpl +'</c:if></span></td>'
					   	+'<td >是否通过竣工环保验收：</td>'
					   	+'<td>'+data.sftgjghbys+'<span style="float:right;">'+ sftgjghbysl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>是否通过项目环评：</td>'
					   	+'<td>'+data.sftgxmhp+'<span style="float:right;">'+ sftgxmhpl +'</c:if></span></td>'
					   	+'<td>环评审批时间：</td>'
					   	+'<td>'+hpspsj+'<span style="float:right;">'+ hpspsjl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>审批文件文号：</td>'
					   	+'<td>'+data.spwjwh+'<span style="float:right;">'+ spwjwhl +'</c:if></span></td>'
					   	+'<td></td>'
					   	+'<td></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-carousel"></i>环保监管</td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>生态流量泄放措施：</td>'
					   	+'<td>'+data.stllxfcs+'<span style="float:right;">'+ stllxfcsl +'</c:if></span></td>'
					   	+'<td>生态流量监控措施：</td>'
					   	+'<td>'+data.stlljkss+'<span style="float:right;">'+ stlljkssl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>过鱼设施：</td>'
					   	+'<td>'+data.gycs+'<span style="float:right;">'+ gycsl +'</c:if></span></td>'
					   	+'<td>增殖放流措施：</td>'
					   	+'<td>'+data.zzflcs+'<span style="float:right;">'+ zzflcsl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>是否涉及自然保护区：</td>'
					   	+'<td>'+data.sfsjzrbhq+'<span style="float:right;">'+ sfsjzrbhql +'</c:if></span></td>'
					   	+'<td>涉及核心区：</td>'
					   	+'<td>'+data.hxq+'<span style="float:right;">'+ hxql +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td >坝下是否存在脱水干涸：</td>'
					   	+'<td>'+data.bxsfcztsgk+'<span style="float:right;">'+ bxsfcztsgkl +'</c:if></span></td>'
					   	+'<td>涉及缓冲区：</td>'
					   	+'<td>'+data.hcq+'<span style="float:right;">'+ hcql +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>涉及实验区：</td>'
					   	+'<td>'+data.sys+'<span style="float:right;">'+ sysl +'</c:if></span></td>'
					   	+'<td>自然保护区设立时间：</td>'
					   	+'<td>'+zrbhqslyj+'<span style="float:right;">'+ zrbhqslyjl +'</c:if></span></td>'
					   	+'</tr>'
					   	+'<tr>'
					   	+'<td>保护区未分区：</td>'
					   	+'<td>'+data.wfq+'<span style="float:right;">'+ wfql +'</c:if></span></td>'
					   	+'<td></td>'
					   	+'<td></td>'
					   	+'</tr>'
				$("#table_id").append(tr);
				}
			})   	
		}
	})	  
}

// 字段赋值
function tabData(data){
	if(data == 0){
		return "无来源";
	}
	if(data == 1){
		return "水利部";
	}
	if(data == 2){
		return "生态环境部";
	}
	if(data == 3){
		return "能源局";
	}
}
</script>
</html>