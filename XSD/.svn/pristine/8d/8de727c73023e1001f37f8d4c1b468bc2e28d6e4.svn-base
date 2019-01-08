<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String address = URLDecoder.decode(URLDecoder.decode(request.getParameter("address"),"utf-8"),"utf-8");
String bj = request.getParameter("bj");//用request得到
%>

<head>
<link rel="stylesheet" href="<%=basePath%>common/layui/css/layui.css">

<style>
.list-group {
	margin-bottom: 10px;
}

.list-group-one .list-group-item {
	font-size: 14px;
	padding: 6px 15px;
}

.list-group-one .list-group-item:nth-child(1) {
	/*   background: #568fc3; */
	/*  color:white; */
	/* font-size:15px; */
	/* border-top-left-radius:5px;
      border-top-right-radius:5px; */
	
}

.list-group-one .list-group-item:last-child {
	border-bottom-left-radius: 5px;
	border-bottom-right-radius: 5px;
}

.span-left {
	display: inline-block;
	width: 10%;
	text-align: right;
}

.span-center {
	display: inline-block;
	width: 70%;
	text-indent: 2em
}

#one {
	width: 101%;
	height: 40px;
	background: #568fc3;
	color: white;
	font-size: 15px;
	line-height: 40px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	border-bottom: 1px solid white;
}

.table {
	width: 101%;
	left: 30%;
	border-collapse: collapse;
	border-spacing: 0;
	margin-bottom: 10px;
}

.fixedThead {
	display: block;
	width: 100%;
}

.scrollTbody {
	display: block;
	height: 190px;
	overflow: auto;
	width: 101%;
	border: 1px solid #dddddd;
	border-bottom-right-radius: 6px;
	border-bottom-left-radius: 6px;
}

.table td {
	width: 300px;
	border-bottom: #333 1px dashed;
	padding: 5px;
	background-color: white;
}

.table th {
	width: 300px;
	border-bottom: 1px solid #dddddd;
	padding: 5px;
	line-height: 24px;
	background-color: #cfc;
}

.table tr {
	border-bottom: 1px solid #dddddd;
	line-height: 24px;
	padding: 5px;
	background: red;
}

.table>thead>tr>th {
	background-color: #568fc3;
	text-align: center;
	color: white;
	white-space: nowrap;
	font-size: 13px;
	font-weight: 100;
}

table i:hover {
	cursor: pointer;
	color: #dddddd;
}

.table td:nth-child(4) {
	text-align: right;
}
</style>
</head>
<body>
	<div style="width: 100%; height: calc(100%);">
		<div id="one">
			<i class="icon icon-align-left"
				style="margin-right: 10px; margin-left: 10px;"></i><span
				id="title_id_xsd">小水电数量统计（全国）</span>
		</div>
		<table class="table" align="center">
			<thead class="fixedThead" align="center">
				<tr>
					<th>序号</th>
					<th>市县</th>
					<th>数量（座）<i id="i-one" class="icon icon-long-arrow-down"
						onclick="togbutton()"></i><i id="i-two"
						class="icon icon-long-arrow-up" style="display: none"></i></th>
					<th>占比</th>
					<th>排名</th>
				</tr>
			</thead>
			<tbody id="sdz_id" class="scrollTbody" align="center">
			</tbody>
		</table>
		<div id="one">
			<i class="icon icon-align-left"
				style="margin-right: 10px; margin-left: 10px;"></i><span
				id="title_id_zjrl">小水电装机容量统计（全国）</span>
		</div>
		<table class="table" align="center">
			<thead class="fixedThead" align="center">
				<tr>
					<th>序号</th>
					<th>市县</th>
					<th>容量（万千瓦）<i id="i-oner" class="icon icon-long-arrow-down"
						onclick="togbuttonqian()"></i><i id="i-twor"
						class="icon icon-long-arrow-up" style="display: none"></i></th>
					<th>占比</th>
					<th>排名</th>
				</tr>
			</thead>
			<tbody id="zjzgm_id" class="scrollTbody" align="center">
			</tbody>
		</table>

	</div>
</body>
<!-- 不要改变以下引用顺序 -->
<%-- <%@include file="edit.inc"%>
<%@include file="flag.jsp"%> --%>
<!-- 引入jquery.js样式 -->
<!-- 解决ie -->
<!--  [if lt IE 9]> -->
<%-- <script src="<%=basePath %>common/zui/lib/ieonly/html5shiv.js"></script>
<script src="<%=basePath %>common/zui/lib/ieonly/respond.js"></script>
<script src="<%=basePath %>common/zui/lib/ieonly/excanvas.js"></script>
<!--   <![endif] -->
<script src="<%=basePath %>common/layui/layui.all.js"></script> --%>
<script>
$(function(){
	address = '<%=address %>';
	bj = '<%=bj %>';
	countDz_right("i-two",bj,address);
	countZjzgm_right("i-twor",bj,address)
})
function countZjzgm_right(css,bj,address){
	if(bj == null){
		bj = 0;
	}
	if(bj = 1){
		$("#title_id_zjrl").text(address+"小水电装机规模排名");
	}
	if(css == "i-oner"){
		var css = "1";
	}else{
		var css = "2";
	}
	$.ajax({
		url:basePath+"homePage/homePage!countZjzgm_right.action",
		data:{"css":css,"bj":bj,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			var tr = "";
			var len = 1;
			var value = 1;
			for (var i = 0; i < data.length; i++) {
				var num = data[i].bfb
				tr += "<tr>"
						+"<td>"+(len++)+"</td>"
						+"<td>"+"<a onclick = 'queryCity(\""+data[i].SHENG+"\")'>"+(data[i].SHENG)+"</a>"+"</td>"
						+"<td>"+(data[i].SUM)+"</td>"
						+"<td>"+(Number(num.toString().match(/^\d+(?:\.\d{0,2})?/)) + "%")+"</td>"
						+"<td>"+(value++)+"</td>"
						+"</tr>"
				
			}
			$("#zjzgm_id").append(tr);
		}
	});
}
function countDz_right(css,bj,address){
	if(bj == null){
		bj = 0;
	}
	if(bj = 1){
		$("#title_id_xsd").text(address+"小水电数量排名");
	}
	if(css == "i-one"){
		var css = "1";
	}else{
		var css = "2";
	}
	$.ajax({
		url:basePath+"homePage/homePage!countDz_right.action",
		data:{"css":css,"bj":bj,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			var tr = "";
			var len = 1;
			var value = 1;
			for (var i = 0; i < data.length; i++) {
				var num = data[i].SUM/data[i].zs*100
				//alert(data[i].SHENG + ":"+Number(num.toString().match(/^\d+(?:\.\d{0,2})?/)))
				tr += "<tr>"
						+"<td>"+(len++)+"</td>"
						+"<td>"+"<a onclick = 'queryCity(\""+data[i].SHENG+"\")'>"+(data[i].SHENG)+"</a>"+"</td>"
						+"<td>"+(data[i].SUM)+"</td>"
						+"<td>"+(Number(num.toString().match(/^\d+(?:\.\d{0,2})?/)) + "%")+"</td>"
						+"<td>"+(value++)+"</td>"
						+"</tr>"
			}
			$("#sdz_id").append(tr);
		}
	});
}

var js = 2;
/*点击图标标切换 */
function togbutton(){
	$("#sdz_id").html("");
	if(js % 2 == 0){
		countDz_right("i-one",bj,address);
		$("#i-one").removeClass("icon icon-long-arrow-down");
		$("#i-one").addClass("icon icon-long-arrow-up")
	}else{
		countDz_right("i-two",bj,address);
		$("#i-one").removeClass("icon icon-long-arrow-up");
		$("#i-one").addClass("icon icon-long-arrow-down")
	}
	js++
} 
function queryCity(address){
//	alert(1111)
	layer.open({
		  type: 2, 
		  title: '('+address+')小水电数量/容量统计图',
		  area: ['1300px', '607px'],
		  skin: 'layui-layer-molv' ,
		  icon: 6,
		  content: basePath+'business/cezhanchaxun/modalFrame_xs.jsp?bj='+9+'&address='+address,
	});
}
var zj = 2;
/*点击图标标切换 */
function togbuttonqian(){
	$("#zjzgm_id").html("");
	if(zj % 2 == 0){
		countZjzgm_right("i-oner",bj,address)
		$("#i-oner").removeClass("icon icon-long-arrow-down");
		$("#i-oner").addClass("icon icon-long-arrow-up")
	}else{
		countZjzgm_right("i-twor",bj,address);
		$("#i-oner").removeClass("icon icon-long-arrow-up");
		$("#i-oner").addClass("icon icon-long-arrow-down")
	}
	zj++
} 
 </script>
