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
href="<%=basePath%>business/search/css/search1.css">
<!-- 引入ztree.js -->
<script type="text/javascript" src="<%=basePath%>common/js/ztree/js/jquery-1.4.4.min.js"></script>
</head>

<body>
<div id="map" style="height:596px;">
					
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

</script>
</html>






