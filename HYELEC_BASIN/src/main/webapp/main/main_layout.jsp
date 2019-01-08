<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.lyht.util.DateUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html style="padding-top:0px;">
<head>
<title></title>
<%@include file="/common/inc/inc.inc"%>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/leaflet/base/leaflet.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="<%=basePath%>business/system/home_page/sty.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" type="text/css"
href="<%=basePath%>business/search/css/search1.css">

<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/leaflet/base/leaflet-src-1.0.3.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/leaflet/base/MarkerCluster.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/leaflet/base/MarkerCluster.Default.css">	
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/assets/css_/iconfont.css">
<!-- eachars -->
<script src="<%=basePath%>common/eCharts/echarts.min.js"></script>
<link href="<%=basePath%>common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<link href="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/bootstraptable/bootstrap-table.min.css"/>
<link rel="stylesheet" href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css"/>
<link rel="stylesheet" href="<%=basePath%>common/js/treegrid/css/jquery.treegrid.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>business/search/css/search2.css">

<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/combox/combox.css">
<%--//修改了源代码bootstrap-table.js中分页的样式，不引用min.js--%>
<script src="<%=basePath%>common/js/bootstraptable/bootstrap-table.js"></script>
<script src="<%=basePath%>common/js/bootstraptable/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=basePath%>common/js/bootstraptable/extensions/key-events/bootstrap-table-key-events.min.js"></script>
<script src="<%=basePath%>common/js/bootstrap-datetime/moment-with-locales(1).js"></script>
<script src="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript" src="<%=basePath%>common/js/treegrid/js/jquery.treegrid.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/treegrid/js/jquery.treegrid.bootstrap3.js"></script>

<script src="<%=basePath%>common/zui/lib/uploader/zui.uploader.min.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/ztree/js/jquery.ztree.all.js"></script>
<script type="text/javascript" src="<%=basePath%>common/echarts3.8/echarts.min.js"></script>
<script src="<%=basePath%>common/layui/layui.js"></script>
<script src="<%=basePath%>common/layui/layui.all.js"></script>
<script src="<%=basePath%>common/eCharts/chanliu-echarts.min.js"></script>
<script type="text/javascript" src="<%=basePath%>common/leaflet/base/leaflet-src-1.0.3.js"></script>

<script type="text/javascript" src="<%=basePath%>common/leaflet/base/leaflet.markercluster-src.js"></script>
<!-- 其他地图插件 -->
<script type="text/javascript" src='<%=basePath%>common/leaflet/plugins/leaflet.ChineseTmsProviders.js'></script>
<!-- 热点图  zui样式冲突，无法显示热点-->
<script type="text/javascript" src='<%=basePath%>common/leaflet/plugins/leaflet-heat.js'></script>
<script type="text/javascript" src='<%=basePath %>common/js/combox/combox.js'></script>
<style>
	.left-map-tool .col-md-11{
           box-shadow: 2px 1px 8px #888888;
           padding-right:0px;
           height:100%;
           color: white;
           background:#3CB7F3;
        }
        .container-fluid .row:nth-child(2) .col-md-11{
           padding-left:0px;
        }
	.left-map-tool .col-md-11 ul{
          padding-left:0px;
          height:100%;
    }
    .left-map-tool ul li h2{
          line-height:50px;
          text-indent: 1em;
          font-weight: 200;
          font-size:24px;
     }
     .left-map-tool ul li h3{
          cursor: pointer;
          text-indent: 1em;
          font-weight: 200;
          line-height: 50px;
          font-size: 20px;
          height:50px;
          margin:0px;
      }
        
      .left-map-tool ul li h3:hover{
          background-color: #50A2CB;
        }
       .left-map-tool ul li h3{
          margin-top:0px;
          margin-bottom:0px; 
        }
        .left-map-tool ul li i{
         font-size: 32px;
        }
        .left-map-tool ul li:nth-child(1){
          border-bottom:2px solid #61AECC;
        }
        .left-map-tool .col-md-1{  
            top:113px;      	        	        			  
		    width:10px;
		    height: 65px;
        	cursor: pointer;
            text-align: center;
		    border-top-right-radius: 30px;
		    border-bottom-right-radius: 30px;
		    background: #3EB7EE;
        }
        .left-map-tool .col-md-1 i{
         	position: relative;
         	top:25px;
         	right:9px;
         	margin-top:23px; 
         	margin-right:50px; 
         }
       
       .right-map-tool .col-md-12{
          height:100%;
          padding-left:0px;
          padding-right:0px;
        }
         .right-map-tool .col-md-12 .col-md-2:nth-child(1){
          position: relative;
          z-index: 222;
          top: 136px;
          width:10px;
          line-height:100px; 
          height:100px;
          cursor: pointer;
          border-top-left-radius:30px;
          border-bottom-left-radius:30px;
          background: #3EB7EE;
        }
          .right-map-tool .col-md-12 .col-md-2:nth-child(1):hover{
            background-color: #50A2CB;     
          }
         .right-map-tool .col-md-12 .col-md-2:nth-child(1) i{
          display: inline-block;
          padding-right: 20px;
        }
         .right-map-tool .col-md-12 .col-md-8{
          padding-left:2px;
          padding-right:10px;
          margin-right: 0px;
          width:300px;
          height:364px;
          background: #3EB7EE;
          box-shadow: 5px 4px 13px #888888;
        }
        tr.error{
        	border:2px solid #ff0000 !important;
        }
</style>
</head>
<body style="padding-top:0px;">
	<div class="container-fluid" style="height:70px;">
		<%@include file="/common/inc/top.jsp"%>
	</div>
	<div class="container-fluid" style="height: calc(100% - 70px);overflow:auto;">
		<div class="row" style="height: 100%;">
			<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12" style="height: 100%;">
				<div id="maincontent" class="row-fluid" style="height: 100%;">
					<div id="map" style="height:100%;"></div>
				</div>
				<div id="nomapmaincontent" class="row-fluid" style="height:100%;top:0px;display:none;overflow:auto;"></div>
			</div>
		</div>
		<div id="maplayercontent" style="display:none;background-color:#ffffff;opacity:0.9;height:400px;z-index:100;width:100%;" class="row">
			<div style="background-color:#0c343d;color:#ffffff;" class="tobottom" align="center">
				<span id="yincangformdiv" onclick="bottomFangxiangChange(this,'yincang')">隐藏</span>
				<span id="huanyuanformdiv" onclick="bottomFangxiangChange(this,'huanyuan')" style="margin-left:20px;display:none;">还原</span>
				<span id="quanpingformdiv" onclick="bottomFangxiangChange(this,'quanping')" style="margin-left:20px;">全屏</span>
			</div>
			<div id="maplayerbottom" style="width:100%;height:calc(100% - 20px);overflow-y:auto;"></div>
		</div>
		<!-- 搜索框  -->
		<div id="mapsearchdiv" style="margin-top:20px;" class="input-group map-search-div">
			<div class="input-control search-box search-box-circle has-icon-left has-icon-right search-example"
				id="searchboxExample">
				<input id="inputSearchExample3" type="search"
					class="form-control search-input" placeholder="请输入查询站点的名称">
			</div>
			<span class="input-group-btn">
				<button onclick="searchCzxxAndDingwei();" class="btn btn-success form-control" type="button">查询</button>
			</span>
		</div>
		<div id="mapsearchresultdiv" style="z-index:1000;display:none;border:1px solid #3e8f3e;overflow-y:auto;background-color:#ffffff;margin-top:53px;height:300px;" class="input-group map-search-div"></div>
		<div id="layer-change-tool" class="input-group map-search-div" style="margin-top:20px;margin-left:70%;">
			<div class="input-control search-box search-box-circle has-icon-left has-icon-right search-example">
				<input type="radio" name="maplayerTool" onclick="changeMapLayer(0);" style="width:20px;height:20px;" class="form-control search-input"><label style="width:70px;height:30px;" class="form-control search-input">影像图</label>
				<input type="radio" name="maplayerTool" onclick="changeMapLayer(1);" checked="checked" style="width:20px;height:20px;" class="form-control search-input"><label style="width:70px;height:30px;" class="form-control search-input">矢量图</label>
			</div>
		</div>
		<div id="left_tools_panel" class="input-group left-map-tool" style="display:none;margin-top:100px;margin-left:-20px;width:250px;height:300px;z-index:100;">
			
		</div>
		<div id="right_tools_panel" class="input-group right-map-tool" style="display:none;margin-top:100px;height:320px;margin-left:calc(100% - 340px);z-index:100;">
			
		</div>
		<!-- 左侧栏目  -->
		<!-- 
		<div class="lanmu-left">
			<h2>站点</h2>
			<div id="tree">
				<ul id="station_tree" class="ztree">
				</ul>
			</div>
		</div>
		-->
	</div>


	<!--  对话框 -->
	<div class="modal fade" id="myLgModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">测站基本信息</h4>
				</div>
				<div class="modal-body">
					 <iframe src="" id="station_view" name="zbqcQcml_view" frameborder="0" scrolling="no" height="400"  width="100%" marginheight="0" marginwidth="0" ></iframe>
				</div>
			</div>
		</div>
	</div>
	<!-- 数据查询Modal -->
	<div class="modal fade" id="dialog_sjcx_modal">
		<div class="modal-dialog modal-lg" style="width: 800px;height: 500px;">
			<div class="modal-content" style="width: 800px;height: 500px;">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">数据查询</h4>
				</div>
				<div class="modal-body" style="padding-top: 8px;height:460px;overflow-y:auto;">
					<div style="display: none;">
							stcd<input type="text" class="form-control" id="dialog_sjcx_stcd"
							name="mTsqxFormBean.ids">
							<input type="text" class="form-control" id="dialog_sjcx_searchtype" value="jyl">
					</div>
					<div style="width:100%;">
						<div style="width:40%;float:left;">
						    开始时间:		<a class='date' style="left:10px;"><label
									class="input" style="display: inline;width:200px;"> <input
										type='text' class="laydatetime" autofocus="autofocus"
										style="width: 170px; height: 30px;background-color:#fff;" readonly="readonly"
										value='<%=DateUtil.addDay(DateUtil.getDate(), -30)+" 00:00:00"%>'
										id="startTime_map_river" name="startTime" required
										data-bv-group=".rowGroup"
										data-bv-notempty-message="时间不能为空"
										placeholder="请选择时间"> <span
										style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
											class="icon-calendar"></span>
										</span>
									</label>
								</a>
						   </div>
						   <div style="width:60%;float:right;">
						 结束时间:   		<a class='date' style="left:10px;"><label
									class="input" style="display: inline;width:200px;"> <input
										type='text' class="laydatetime" autofocus="autofocus"
										style="width: 170px; height: 30px;background-color:#fff;" 
										data-bv-group=".rowGroup"
										data-bv-notempty-message="时间不能为空"
										readonly="readonly" id="endTime_map_river" name="endTime"
										value='<%=DateUtil.getDate()+" 00:00:00"%>' required
										placeholder="请选择时间"> <span
										style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
											class="icon-calendar"></span>
										</span>
									</label>
								</a>
								<span id="searchTypeSpan" style="display:none;">
									<input onclick="searchTypeChange('jyl')" type="radio" checked="checked" name="searchType">降雨量
									<input onclick="searchTypeChange('ll')" type="radio" name="searchType">流量
								</span>
								  		<button class="btn btn-primary" style="width:80px;float:right;" id="query_ref" type="button" onclick="searchMapData()">
									  	   <div class="visible-md visible-lg"><i class="icon icon-search"></i>&nbsp;查询</div>
									   </button>
						   </div>	
					</div>
					<div id="sjcx_div" class="chart" style="height:430px;">
						<table class="layui-hide" id="sjcx_table" lay-filter="sjcx_table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">    
var _sttpList={
	MM:"气象站",
	BB:"蒸发站",
	DD:"堰闸水文站",
	TT:"潮位站",
	DP:"泵站",
	SS:"墒情站",
	PP:"雨量站",
	ZZ:"河道水文站",
	ZS:"河道水位站",
	RR:"水库水文站",
	ZB:"分洪水位站"
};
//初始化窗体大小
$(function(){
    $("#map").height($(window).height()-70);
    $("#map").click(function(){
    	$("#mapsearchresultdiv").hide();
    });
}); 
function searchTypeChange(flag){
	$("#dialog_sjcx_searchtype").val(flag);
}
function bottomFangxiangChange(obj,flag){
	$("#yincangformdiv").show();
	$("#huanyuanformdiv").show();
	$("#quanpingformdiv").show();
	$(obj).hide();
	if(flag=="yincang"){
		$("#maplayerbottom").hide();
		$("#maplayercontent").css("height","400px");
		$("#maplayercontent").css("top","");
		$("#maplayercontent").css("margin-top",$(window).height()-190);
	}else if(flag=="huanyuan"){
		$("#maplayerbottom").show();
		$("#maplayercontent").css("height","400px");
		$("#maplayercontent").css("top","");
		$("#maplayercontent").css("margin-top",$(window).height()-570);
	}else if(flag=="quanping"){
		$("#maplayerbottom").show();
		$("#maplayercontent").css("height",($(window).height()-70)+"px");
		$("#maplayercontent").css("margin-top","0");
		$("#maplayercontent").css("top",70);
	}
}
var mak;
var circles=[];
 //地图部分
		var normalm = L.tileLayer.chinaProvider('GaoDe.Normal.Map', {
				    maxZoom: 18,
				    minZoom: 2
		});
		var imgm = L.tileLayer.chinaProvider('GaoDe.Satellite.Map', {
				    maxZoom: 18,
				    minZoom: 2
		});
		var imga = L.tileLayer.chinaProvider('GaoDe.Satellite.Annotion', {
				    maxZoom: 18,
				    minZoom: 2
		});
		var mapbox = L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
				    attribution : '测试',
					maxZoom : 18,
				    id : 'mapbox.streets',
				    accessToken : 'pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw'
		});
	
		var normal = L.layerGroup([normalm]),
		image = L.layerGroup([imgm, imga]);
		mapbox = L.layerGroup([mapbox]);
	
		var baseLayers = {
			"城市图": normal,
			"地形图": mapbox
		};
		var overlayMaps = {
		    "卫星图": image,
		};
		var map = L.map("map", {
			center: [28.1365, 113.0436],
			zoom:5,
			layers: [normal],
			zoomControl: true
		});
		L.control.layers(baseLayers, overlayMaps).addTo(map);
		L.control.zoom({
			zoomInTitle: '放大',
			zoomOutTitle: '缩小'
		}).addTo(map);	
		map.on("zoomend",function(e){
			var center = map.getCenter();
			map.setView(center);
		});
		var markers = L.markerClusterGroup({showCoverageOnHover: false, zoomToBoundsOnClick: false});
		function changeMapLayer(flag){
			if(flag==0){
				map.removeLayer(normal);
				map.addLayer(image);
			}else if(flag==1){
				map.removeLayer(image);
				map.addLayer(normal);
			}
		}
		function loadStData(){
		  	//清空地图上的marker标记
			if(markers != null){
				markers.clearLayers(); 
	        	$.ajax({
				   url:basePath+'search/search!searchStation.action',
				   type:'post',
				   dataType:'json',
				   success:function(datas){
				   	  for(var i = 0 ; i < datas.length;i++){
				   		    //console.log(JSON.stringify(datas[i]))
				   		    if(datas[i].STTP=='PP'){
				   		    	//雨量站
				   		    	datas[i].icon=L.icon({
								    //iconUrl: basePath+"common/images/rst.png",
								    iconUrl: basePath+"common/images/yuliangzhan.png",
								    iconSize: [30, 30]
							    });
					    	}else if(datas[i].STTP=='ZS' || datas[i].STTP=='ZF'){
					    		//水位站
					    		datas[i].icon=L.icon({
								    //iconUrl: basePath+"common/images/hly.png",
								    iconUrl: basePath+"common/images/shuiweizhan.png",
								    iconSize: [30, 30]
							    });
					    	}else if(datas[i].STTP == 'ZZ' || datas[i].STTP == 'RR' || datas[i].STTP == 'DD'){
					    		//水文站
					    		datas[i].icon=L.icon({
								    //iconUrl: basePath+"common/images/wll.png",
								    iconUrl: basePath+"common/images/shuiwenzhan.png",
								    iconSize: [30, 30]
							    });
					    	}else{
					    		datas[i].icon=L.icon({
								    //iconUrl: basePath+"common/images/wll.png",
								    iconUrl: basePath+"common/images/default.png",
								    iconSize: [30, 30]
							    });
					    	}
					      datas[i].title=datas[i].STNM;
					      datas[i].alt=datas[i].STNM;
				   		  var marker = L.marker([datas[i].LTTD1,datas[i].LGTD1],datas[i]);
				   		mak=marker;
						  marker.on('click', function(e) {   
							stationMapClickEvent(this);
						  });  
						  markers.addLayer(marker);
					  }
					  //加载所有marker标记到地图中
					  map.addLayer(markers); 
				   }
				});
			}
	    }

		loadStData();
		//查询测站信息
		function cezhanchaxun(marker){
			$("#dialog_sjcx_stcd").val(marker.options.STCD);
			var url= basePath + "stbprp/stbprp!edit.action?mStbprpFormBean.mStbprpInfoBean.stcd="+marker.options.STCD;
			$.ajax({
					url:url,
					type:"post",
					dataType:"json",
					success:function(response){
						if(response.reflag==1||response.reflag=="1"){
							var stcd=response.beanInfo.stcd;
							var stnm=response.beanInfo.stnm;
							var rvnm=response.beanInfo.rvnm?rvnm=response.beanInfo.rvnm:"";
							
							var hnnm=response.beanInfo.hnnm?response.beanInfo.hnnm:"";
							var bsnm=response.beanInfo.bsnm?response.beanInfo.bsnm:"";
							var stlc=response.beanInfo.stlc?response.beanInfo.stlc:"";
							var lgtd1=response.beanInfo.lgtd1?response.beanInfo.lgtd1:"";
							var lttd1=response.beanInfo.lttd1?response.beanInfo.lttd1:"";
							var dtmnm=response.beanInfo.dtmnm?response.beanInfo.dtmnm:"";
							var dtmel=response.beanInfo.dtmel?response.beanInfo.dtmel:"";
							var frgrd=response.beanInfo.frgrd?response.beanInfo.frgrd:"";
							var drna=response.beanInfo.drna?response.beanInfo.drna:"";
							var esstym=response.beanInfo.esstym?response.beanInfo.esstym:"";
							var bgfrym=response.beanInfo.bgfrym?response.beanInfo.bgfrym:"";
							var sttp=response.beanInfo.sttp?response.beanInfo.sttp:"";
							var	 table = "<table style='width:300px;'>"+
							"	<tr>"+
							"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
								"<lable>测站编码:<input type='hidden' id='sjcx_sttp' value='"+sttp+"'></lable></td>"+
							"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
								"<lable>"+stcd+"</td>"+
							"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
								"<lable>测站名称:</lable></td>"+
							"<td style='width:80px; height:40px;text-align: center; font-weight: bold; background: '>"+
								"       "+stnm+"             </td></tr>"+
								"	<tr>"+
								"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
									"<lable>河流名称:</lable></td>"+
								"<td style='width: 80px; height:40px;text-align: center; font-weight: bold; background: '>"+
									" "+rvnm+"       </td>"+
								"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
									"<lable>水系名称:</lable></td>"+
								"<td style='width:80px; height:40px;text-align: center; font-weight: bold; background: '>"+
									"           "+hnnm+"              </td></tr>"+
									"	<tr>"+
									"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
										"<lable>流域名称:</lable></td>"+
									"<td style='width: 80px;height:40px; text-align: center; font-weight: bold; background: '>"+
										"    "+bsnm+"  </td>"+
									"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
										"<lable>站类型:</lable></td>"+
									"<td style='width:80px; height:40px;text-align: center; font-weight: bold; background: '>"+
										"          "+_sttpList[sttp]+"                </td></tr>"+
										"<tr><td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
										"<lable>站址:</lable></td>"+
										"<td colspan='2' style='height:40px;text-align: center; font-weight: bold; background: '>"+
										"          "+stlc+"                </td></tr>"+
										"	<tr>"+
										"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
											"<lable>经度:</lable></td>"+
										"<td style='width: 80px; height:40px;text-align: center; font-weight: bold; background: '>"+
											"    "+lgtd1+"  </td>"+
										"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
											"<lable>纬度:</lable></td>"+
										"<td style='width:80px; height:40px;text-align: center; font-weight: bold; background: '>"+
											"         "+lttd1+"                 </td></tr>"+
											"	<tr>"+
											"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
												"<lable>基面名称:</lable></td>"+
											"<td style='width: 80px;height:40px; text-align: center; font-weight: bold; background: '>"+
												"    "+dtmnm+"  </td>"+
											"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
												"<lable>基面高程:</lable></td>"+
											"<td style='width:80px; height:40px;text-align: center; font-weight: bold; background: '>"+
												"          "+dtmel+"                </td></tr>"+
												"	<tr>"+
												"<td style='width:80px; height:40px;text-align: center; font-weight: bold; background: '>"+
													"<lable>报汛等级:</lable></td>"+
												"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
													"    "+frgrd+"  </td>"+
												"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
													"<lable>集水面积:</lable></td>"+
												"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
													"           "+drna+"               </td></tr>"+
													"	<tr>"+
													"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
														"<lable>建站年月:</lable></td>"+
													"<td style='width: 80px; height:40px;text-align: center; font-weight: bold; background: '>"+
														"    "+esstym+"  </td>"+
													"<td style='width: 80px; text-align: center; font-weight: bold; background: '>"+
														"<lable>始报年月:</lable></td>"+
													"<td style='width:80px; text-align: center; font-weight: bold; background: '>"+
														"            "+bgfrym+"              </td></tr>"+
											
								"</table>"+
								" <center>	<button class='btn btn-success ' type='button' style=''onclick='sjcx("+JSON.stringify(stcd)+")'>数据查询</button> </center>";
								
							
								marker.bindPopup(table).openPopup();
						}else{
							var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
							msg.show();	
						}
					}
				});
		}
		//数据查询
	function sjcx(id){
			$("#dialog_sjcx_modal").modal({
				 show : false
				,backdrop : false // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				 show : false
	       }).on('shown.zui.modal',function(){
	       		searchMapData();
	       });
	       $("#dialog_sjcx_modal").modal("show");
		}
		
		//查询
		function searchMapData(){
			var id=	$("#dialog_sjcx_stcd").val();
			var s =$("#startTime_map_river").val();
			var e=$("#endTime_map_river").val();
			var sjType=$("#sjcx_sttp").val();
			var url=basePath + "search/search!searchMapData.action?stcd=" + id+"&startTime="+s+"&endTime="+e+"&sjType="+sjType;
			getSjcx("#sjcx_div","#sjcx_table",url);
		}
	//测站点击事件	
	function stationMapClickEvent(marker){
		var stcd=marker.options.STCD;
		var stnm=marker.options.STNM;
		var stTp=marker.options.STTP;//测站类型
		if(golabelParam.menuFlag=="cezhanchaxun"){
			//测站查询菜单界面
			cezhanchaxun(marker)
		}else if(golabelParam.menuFlag=="shishiyubao"){
			//实时预报菜单界面
		}else if(golabelParam.menuFlag=="shishiyubao"){
			//实时预报菜单界面
		}else if(golabelParam.menuFlag=="moxingliebiao"){
			//模型列表菜单界面
		}else if(golabelParam.menuFlag=="wodefangan"){
			//我的方案列表界面
		}else if(golabelParam.menuFlag=="chuangjianfangan"){
			//创建方案菜单
			if(stTp=='PP'){
				alert("注意：雨量站不能点击和输入");
				return false;
			}else{
				$("#zd").val(stnm); //测站名称回显
				$("#stcd_cjfa").val(stcd);//测站编码
			}
			
		}else if(golabelParam.menuFlag=="huiliujisuan"){
			//汇流计算菜单
			var url= basePath + "huiliu/huiliu!checkChanliujisuanStcd.action";
			common_ajax(null, url, function(response) {
				
			});
		}else if(golabelParam.menuFlag=="chanliujisuan"){
			$("#stnm1").val(stnm); //测站名称回显
			$("#stcd").val(stcd);//测站编码
			//产流计算菜单
		}else if(golabelParam.menuFlag=="cezhanxinxi"){
			//测站信息菜单
		}else if(golabelParam.menuFlag=="yunxingshuju"){
			//运行数据菜单
		}else if(golabelParam.menuFlag=="shuiwenshuju"){
			//水文数据菜单
		}else if(golabelParam.menuFlag=="xingzhengquyudaimaguanli"){
			//行政区域代码管理菜单
		}else if(golabelParam.menuFlag=="liuyushuixidaimaguanli"){
			//流域水系代码管理菜单
		}else if(golabelParam.menuFlag=="yonghuguanli"){
			//用户管理菜单
		}else if(golabelParam.menuFlag=="jueseguanli"){
			//角色管理菜单
		}else if(golabelParam.menuFlag=="fenzuguanli"){
			//分组管理菜单
		}else if(golabelParam.menuFlag=="juesecaidanpeizhi"){
			//角色菜单配置菜单
		}else if(golabelParam.menuFlag=="caidanguanli"){
			//菜单管理菜单
		}
	}	
    function openDialog(url,title){
    	var dialog=$('<div class="modal fade">'
    	+'<div class="modal-dialog modal-lg">'
    	+'<div class="modal-content">'
    	+'  <div class="modal-header">'
		+'    <button type="button" class="close" data-dismiss="modal">'
		+'		<span aria-hidden="true">×</span><span class="sr-only">关闭</span>'
		+'	  </button>'
		+'	  <h4 class="modal-title"></h4>'
		+'		</div>'
		+'		<div class="modal-body">'
		+'		</div>'
		+'	</div>'
		+'</div>'
		+'</div>');
    }
    
	function getSjcx(chart, tab, url) {
	    var height = 400;
	    var width = 750;
	    $(chart).css("height","430");
	    var sjType=$("#sjcx_sttp").val();//获取测站类型，根据测站类型来展示数据
	    var cols;//声明列名，在下面判断的时候赋值
	    var searchType="";
	    if(sjType=="ZZ"){
	    	$("#searchTypeSpan").show();
	    	searchType=$("#dialog_sjcx_searchtype").val();
	    }else{
	    	$("#searchTypeSpan").hide();
	    	if(sjType=="PP"||sjType=="RR"||sjType=="DD"){//如果测站类型是雨量站，水文站，展示雨量数据
	    		searchType="jyl";
	    	}else if(sjType=="ZS"||sjType=="ZB"||sjType=="ZQ"){
	    		searchType="ll";
	    	}
	    }
	    if(searchType=="jyl"){//如果测站类型是雨量站，水文站，展示雨量数据
	    	cols= [
                [{
                    field: 'TM',
                    title: '日期',
                    width:'400'
                },{
                    field: 'DRP',
                    title: '降雨量',
                    width:'300'
                }]
            ]
	    }else if(searchType=="ll"){//如果是水位站，则显示水位流量的数据
	    	cols= [
                [{
                    field: 'TM',
                    title: '日期',
                    width:'300'
                },
                	{
                    field: 'Z',
                    title: '水位',
                    width:'200'
                },{
                    field: 'Q',
                    title: '流量',
                    width:'200'
                }]
            ]
	    	
	    }
	    url = url.indexOf("?")>-1?url+"&searchType="+searchType:"?searchType="+searchType;
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem:tab,
	            url:url,
	            height:height,
	             width: width,
	            id:'czmap',
	            request: {
	            	pageName: 'pageIndex' //页码的参数名称，默认：page
	                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
	            },
	            response: {
	                statusName: 'CODE' //数据状态的字段名称，默认：code
	                ,
	                statusCode: 1 //成功的状态码，默认：0
	                ,
	                msgName: 'MESSAGE' //状态信息的字段名称，默认：msg
	                ,
	                countName: 'TOTALAMOUNT' //数据总数的字段名称，默认：count
	                ,
	                dataName: 'DATA'
	            } //数据列表的字段名称，默认：data} //如果无需自定义数据响应名称，可不加该参数
	            ,
	            cols: cols,
	            page: true,
	            done: function(res, curr, count){
	                console.log(curr); 
	                //得到数据总量
	                table.resize();
	            }
	        });
	    });
	    }
	function dingweiPoint(lon,lat){
		removeAllCircle();
		map.setView({lon:lon,lat:lat},12);
		var circle = L.circle([lat,lon], {
				  color: 'green',
				  fillColor: '#f03',
				  fillOpacity: 0.5,
				  radius: 1000
		}).addTo(map);
		circles.push(circle);
	}
	function removeAllCircle(){
		if(circles!=null){
			for(var i=0;i<circles.length;i++){
				map.removeLayer(circles[i]);
			}
		}
		circles=[];
	}
	function searchCzxxAndDingwei(){
		var searchText=$("#inputSearchExample3").val();
		$.ajax({
			url:basePath + "search/search!searchMapStationByName.action",
			type:"post",
			data:{searchText:searchText},
			dataType:"json",
			success:function(response){
				if(response!=null && response.length>0){
					if(response.length==1){
						var stobj = response && response.length>0?response[0]:null;
						if(stobj!=null){
							console.log(JSON.stringify(stobj))
							dingweiPoint(stobj.LGTD1,stobj.LTTD1);
						}
					}else{
						$("#mapsearchresultdiv").empty();
						var div=$("<div style='width:100%;height:300px;overflow-y:auto;'></div>")
						var table=$("<table border='1' style='width:100%;'></table>");
						for(var i=0;i<response.length;i++){
							var sttname=_sttpList[response[i].STTP]?_sttpList[response[i].STTP]:"其他";
							table.append("<tr onclick='trclick(\""+response[i].LGTD1+"\",\""+response[i].LTTD1+"\")'>"+
							"<td style='height:25px;'>"+response[i].STNM+"</td>"+
							"<td style='height:25px;'>"+sttname+"</td>"+
							"</tr>");
						}
						div.append(table);
						$("#mapsearchresultdiv").append(div);
						$("#mapsearchresultdiv").show();
					}
				}
			}
		});
    }
    function trclick(lgtd,lttd){
    	$("#mapsearchresultdiv").hide();
    	dingweiPoint(lgtd,lttd);
    }
    /*
    $("#inputSearchExample3").combox({
		url:basePath + "search/search!searchMapStation.action",
		label:"STNM",
		value:"STCD",
		dataKey:"dataList",
		top:'35px',
		left:'0px',
		height:'250px',
		width:'330px',
		isPager:false,
		select:function(item){
			
		}
	});
	**/
</script>
</html>






