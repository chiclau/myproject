<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
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

</head>

<body>
	<div class="container-fluid">
		<%@include file="/common/inc/top.jsp"%>
		<div id="maincontent" class="row-fluid">
			<br> <br>
			<div id="map" style="height:597px;"></div>
		</div>
		<!-- 搜索框  -->
		<div class="input-group">
			<div
				class="input-control search-box search-box-circle has-icon-left has-icon-right search-example"
				id="searchboxExample">
				<input id="inputSearchExample3" type="search"
					class="form-control search-input" placeholder="请输入查询站点的名称">
			</div>
			<span class="input-group-btn">
				<button class="btn btn-success" type="button">查询</button>
			</span>
		</div>
		<!-- 左侧栏目  -->
		<div class="lanmu-left">
			<h2>站点</h2>
			<div id="tree">
				<ul id="station_tree" class="ztree">
				</ul>
			</div>
		</div>
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
<script type="text/javascript" src="<%=basePath%>common/leaflet/base/leaflet.js"></script>
<!-- 其他地图插件 -->
<script type="text/javascript" src='<%=basePath%>common/leaflet/plugins/leaflet.ChineseTmsProviders.js'></script>
<!-- 热点图  zui样式冲突，无法显示热点-->
<script type="text/javascript"
	src='<%=basePath%>common/leaflet/plugins/leaflet-heat.js'></script>
<!--ztree.js-->
<script type="text/javascript" src="<%=basePath%>common/js/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/ztree/js/jquery.ztree.excheck.js"></script>

<!-- eachars -->
<script src="<%=basePath%>common/eCharts/echarts.min.js"></script>

<script type="text/javascript">    

//初始化窗体大小
$(function(){
    $("map").height($(window).height()-67);
}); 

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
		"卫星图": image,
		"地形图": mapbox,
	}

	var map = L.map("map", {
		center: [28.1365, 113.0436],
		zoom:5,
		layers: [image],
		zoomControl: false
	});
			
	L.control.layers(baseLayers, null).addTo(map);
	L.control.zoom({
		zoomInTitle: '放大',
		zoomOutTitle: '缩小'
	}).addTo(map);	
	 
//自定义图标marker
var Icon = L.Icon.Default.extend({            
options:{
	iconUrl:"<%=basePath%>common/images/sdz.png"
}
});
var myIcon = new Icon();
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
							var marker = L.marker([datas[i].LTTD1,datas[i].LGTD1],{mid:datas[i].STCD,title:datas[i].STNM}).addTo(map);
							marker.setIcon(myIcon);
							marker.bindPopup(datas[i].STNM);//.openPopup(); 
							marker.on('click', function(e) {   
								station_view(this.options.mid);
							});  
							
						myGroup.addLayer(marker);
				  }
				  //加载所有marker标记到地图中
				  map.addLayer(myGroup); 
			   }
		});
    }
  
 loadStData();
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
            onAsyncSuccess : function(event, treeId, treeNode, msg){    
            } 
        }  
        } 
   
    $(document).ready(function(){
        $.fn.zTree.init($("#station_tree"), setting);
    });
    
    /** 展示单个水电站信息  */
   function station_view(stcd){ 
 	   var url = basePath+'search/search!stationView.action?searchFormBean.stcd='+stcd;
 	   document.getElementById("station_view").src=url;
 	   $('#myLgModal').modal({
 				 show : true
 				,backdrop : "static" //背景遮挡
 				,moveable : true

 		}).on('shown.zui.modal', function() {
        });
    }
</script>
</html>






