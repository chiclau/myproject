<%@ page language="java" pageEncoding="UTF-8"%>
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
	href="<%=basePath%>common/leaflet/base/leaflet.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/leaflet/base/LeafletStyleSheet.css">
	
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/assets/css_/iconfont.css">
<!-- eachars -->
<script src="<%=basePath%>common/eCharts/echarts.min.js"></script>
<link href="<%=basePath%>common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<link href="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/bootstraptable/bootstrap-table.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>business/search/css/search2.css">
<link rel="stylesheet" href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css"/>
<link rel="stylesheet" href="<%=basePath%>common/js/treegrid/css/jquery.treegrid.css" type="text/css">
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
<script type="text/javascript" src="<%=basePath%>common/leaflet/base/leaflet.js"></script>

<script type="text/javascript" src="<%=basePath%>common/leaflet/base/PruneCluster.js"></script>
<!-- 其他地图插件 -->
<script type="text/javascript" src='<%=basePath%>common/leaflet/plugins/leaflet.ChineseTmsProviders.js'></script>
<!-- 热点图  zui样式冲突，无法显示热点-->
<script type="text/javascript" src='<%=basePath%>common/leaflet/plugins/leaflet-heat.js'></script>

</head>
<body style="padding-top:0px;">
	<div class="container-fluid" style="height:70px;">
		<%@include file="/common/inc/top.jsp"%>
	</div>
	<div class="container-fluid" style="height: calc(100% - 70px);">
		<div class="row" style="height: 100%;">
			<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12" style="height: 100%;">
				<div id="maincontent" class="row-fluid" style="height: 100%;">
					<div id="map" style="height:100%;"></div>
				</div>
				<div id="nomapmaincontent" class="row-fluid" style="height:100%;display:none;overflow:auto;"></div>
			</div>
		</div>
		<div id="maplayercontent" style="display:none;background-color:#ffffff;opacity:0.9;height:400px;z-index:100;width:100%;" class="row">
			<div style="background-color:#0c343d;" class="tobottom" align="center" onclick="bottomFangxiangChange(this)"><img id="bottomflag" src="<%=basePath%>common/assets/img/xiangxia.png"></div>
			<div id="maplayerbottom" style="width:100%;height:calc(100% - 20px);overflow-y:auto;"></div>
		</div>
		<div id="left_tools_panel" class="row">
			
		</div>
		<div id="right_tools_panel" class="row">

			
		</div>
		<!-- 搜索框  -->
		<div id="mapsearchdiv" style="margin-top:20px;" class="input-group map-search-div">
			<div
				class="input-control search-box search-box-circle has-icon-left has-icon-right search-example"
				id="searchboxExample">
				<input id="inputSearchExample3" type="search"
					class="form-control search-input" placeholder="请输入查询站点的名称">
			</div>
			<span class="input-group-btn">
				<button class="btn btn-success form-control" type="button">查询</button>
			</span>
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


</body>
<script type="text/javascript">    

//初始化窗体大小
$(function(){
    $("#map").height($(window).height()-70);
}); 
function bottomFangxiangChange(obj){
	if($(obj).hasClass("tobottom")){
		$("#maplayerbottom").hide();
		$("#maplayercontent").css("margin-top",$(window).height()-190);
		$(obj).html("<img src='"+basePath+"common/assets/img/xiangshang.png'/>");
		$(obj).removeClass("tobottom");
		$(obj).addClass("totop");
	}else if($(obj).hasClass("totop")){
		$("#maplayerbottom").show();
		$("#maplayercontent").css("margin-top",$(window).height()-570);
		$(obj).html("<img src='"+basePath+"common/assets/img/xiangxia.png'/>");
		$(obj).removeClass("totop");
		$(obj).addClass("tobottom");
	}
}
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
	var leafletView = new PruneClusterForLeaflet();
	var baseLayers = {
		"城市图": normal,
		"卫星图": image,
		"地形图": mapbox,
	}
	var overlayLayers = {
 
    }
	
	var map = L.map("map", {
		center: [28.1365, 113.0436],
		zoom:5,
		layers: [image],
		zoomControl: false
	});
			
	L.control.layers(baseLayers, overlayLayers).addTo(map);
	L.control.zoom({
		zoomInTitle: '放大',
		zoomOutTitle: '缩小'
	}).addTo(map);	
	 
//自定义图标marker
//定义雨量站图标
var Icon_rain = L.Icon.Default.extend({  
options:{
	iconUrl:"<%=basePath%>common/images/rst.png"
}
});
var myIcon_rain = new Icon_rain();
//定义水文站图标 
var Icon_hydrology = L.Icon.Default.extend({            
options:{
	iconUrl:"<%=basePath%>common/images/hly.png"
}
});
var myIcon_hydrology = new Icon_hydrology();

//定义水位站图标
var Icon_waterLevel = L.Icon.Default.extend({            
options:{
	iconUrl:"<%=basePath%>common/images/wll.png"
}
});
var myIcon_waterLevel = new Icon_waterLevel();
//marker标记自定义组
var myGroup = new L.LayerGroup();    
  //加载初始化数据，创建marker标记
function loadStData(){
  	//清空地图上的marker标记
	if(myGroup != null)
		myGroup.clearLayers(); 
 
       $.ajax({
			   url:basePath+'search/search!searchStation.action',
			   type:'post',
			   dataType:'json',
			   success:function(datas){
			   	  for(var i = 0 ; i < datas.length;i++){
			   		  //alert(JSON.stringify(datas[i]))
			   		  	datas[i].popup=datas[i].STNM;
			   		    if(datas[i].STTP=='PP'){
			   		    	datas[i].icon=L.icon({
							    iconUrl: basePath+"common/images/rst.png",
							    iconSize: [15, 15]
						    });
				    	}else if(datas[i].STTP=='ZS' || datas[i].STTP=='ZF'){
				    		datas[i].icon=L.icon({
							    iconUrl: basePath+"common/images/hly.png",
							    iconSize: [15, 15]
						    });
				    	}else if(datas[i].STTP == 'ZZ' || datas[i].STTP == 'RR' || datas[i].STTP == 'DD'){
				    		datas[i].icon=L.icon({
							    iconUrl: basePath+"common/images/wll.png",
							    iconSize: [15, 15]
						    });
				    	}
			   		    var marker = new PruneCluster.Marker(datas[i].LTTD1,datas[i].LGTD1,datas[i]);
						marker.on({"click":function(e){
							
						}});  
						leafletView.RegisterMarker(marker);
				  }
				  //加载所有marker标记到地图中
				  map.addLayer(leafletView); 
			   }
		});
    }

 loadStData();
/**
///树形
	  var setting = {
	   async : {    
            enable : true,   
            dataType: "json",
            url:basePath+'search/search!searchLyTree.action',
            autoParam : [ "RVCD", "NAME" ]  //ajax提交的时候，传的是id值  
        },    
        data:{ // 必须使用data    
	            simpleData : {    
	                enable : true,    
	                idKey : "RVCD", // id编号命名     
	                pIdKey : "PID", // 父id编号命名      
	                rootId : 0  
	           	 } ,
	    		key: {
					 name : "NAME"
				}
        },    
        //回调函数    
        callback : {    
           // onClick : function(event, treeId, treeNode, clickFlag) {    
           //    if(true) {  
            //       //alert(" 节点id是：" + treeNode.RVCD + ", 节点文本是：" + treeNode.NAME);        
           //    }    
          // },    
            onClick: zTreeOnClick,
            onAsyncSuccess : function(event, treeId, treeNode, msg){    
            } 
          
        }  
        } 
    $(document).ready(function(){
        $.fn.zTree.init($("#station_tree"), setting);
    });
*/
    /** 展示单个水电站信息  */
   function zTreeOnClick(event, treeId, treeNode){ 
 	   var url = basePath+'search/search!stationView.action?searchFormBean.stcd='+treeNode.RVCD;
 	   document.getElementById("station_view").src=url;
 	   $('#myLgModal').modal({
 				 show : true
 				,backdrop : "static" //背景遮挡
 				,moveable : true

 		}).on('shown.zui.modal', function() {
        });
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
</script>
</html>






