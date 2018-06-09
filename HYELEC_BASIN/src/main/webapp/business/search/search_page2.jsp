<%@page import="com.lyht.util.DateUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn" class="screen-desktop-wide device-desktop">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
<title>实时预报展示</title>
<%@include file="/common/inc/inc.inc"%>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/leaflet/base/leaflet.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/assets/css_/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>business/search/css/search2.css">

</head>
<body>
	<div class="container-fluid">
		<%@include file="/common/inc/top.jsp"%>
	</div>
	<div class="container-fluid " style="height:100%;">
		<div class="row" style="height:100%;">
			<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12"
				style="height:100%;">
				<div class="row-fluid" style="height:100%;">
					<div id="map" style="height:100%;"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-11 col-lg-11 col-sm-11 col-xs-11">
				<ul>
					<li><h2>应用信息</h2></li>
					<li
						onclick="javascript:window.location.href='<%=basePath%>business/search/search_page2.jsp'; "><h3>
							<i class="iconfont icon-iconset0457"></i> 实时预报
						</h3></li>
					<li
						onclick="javascript:window.location.href='<%=basePath%>business/search/search_page3.jsp'; "><h3>
							<i class="iconfont icon-iconset0457"></i> 实时雨情
						</h3></li>
					<li
						onclick="javascript:window.location.href='<%=basePath%>business/search/search_page4.jsp'; "><h3>
							<i class="iconfont icon-iconset0457"></i> 实时水情
						</h3></li>
					<%--                   <li onclick="javascript:window.location.href='<%=basePath%>business/search/search_page5.jsp'; "><h3><i class="iconfont icon-iconset0457"></i> 等值线面</h3></li> --%>
				</ul>
			</div>
			<div class="col-md-1 col-lg-1 col-sm-1 col-xs-1">
				<i class="iconfont icon-zuojiantou" style="color:white"></i>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
				<div class="col-md-2 col-lg-2 col-sm-2 col-xs-2">
					<i class="iconfont icon-arrow-right-copy-copy" style="color:white"></i>
				</div>
				<div class="col-md-8 col-lg-8 col-sm-8 col-xs-8">
					<div class="radio">
						<label> <input type="radio" name="time_radio_group" checked="checked" value="1" onchange="loadData()"> 1小时
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="time_radio_group" value="3" onchange="loadData()"> 3小时
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="time_radio_group" value="6" onchange="loadData()">
							6小时
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="time_radio_group" value="12" onchange="loadData()">
							12小时
						</label>
					</div>

					<div class="row">
						<div class="col-md-12" id="watrrd">
							<table  class="table table-condensed bordered">
								<tr>
									<th>地区</th>
									<th>测站名称</th>
									<th>雨量</th>
									<th>水位</th>
								</tr>
							</table>
						</div>
					</div>
					<div class="row" style="width:293px;height:275px;overflow:auto;">
						<div class="col-md-12" id="watersmy">
							<table class="table table-condensed bordered" id="waters">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

<!--  对话框 -->
 <div class="modal fade" id="rain_modal" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">关闭</span></button>
        <h4 class="modal-title">实时雨情统计</h4>
      </div>
      <div class="modal-body">
      		<div class="row-fluid col-md-12">
      			<form id="search_rain_form" name="search_rain_form">
 					<div class="btn-group pull-left ">
						<div class="input-group ">
							 <input type='text' class="form-control laydatetime" autofocus="autofocus" 
							 	style="width: 150px; height: 30px;"  readonly="readonly"  
							 	value='<%=DateUtil.addDay(DateUtil.getDate(), -7)+" 00:00:00"%>'
							 	id="startTime"  name="searchFormBean.startTime" 
							 	required data-bv-notempty-message="时间不能为空" placeholder="请选择日期" /> 
							 	<span class="input-group-addon" style="width: 39px; height: 30px;background-color:#f9f9f9;">
							 	<span class="icon-calendar"></span></span>
							 	
							 	<input type='text' class="form-control laydatetime" autofocus="autofocus" 
							 	style="width: 150px; height: 30px;"  readonly="readonly"  
							 	id="endTime"   name="searchFormBean.endTime" 
							 	value='<%=DateUtil.getDate()+" 23:59:59"%>'
							 	required data-bv-notempty-message="时间不能为空" placeholder="请选择日期" /> 
							 	<span class="input-group-addon" style="width: 39px; height: 30px;background-color:#f9f9f9;">
							 	<span class="icon-calendar"></span></span>
								
						</div>
					</div>
					<div class="btn-group pull-right ">
						<div class="input-group ">
						 <input class="btn btn-defalut" value="查询" type="button" >
							<input class="btn btn-success" value="前一天" type="button" >
							<input class="btn btn-success" value="后一天" type="button" >
						</div>
					</div>
					</form>
				</div>  
      		<br>
        	<div  id="myEcharts" style="width: 560px; height:500px;"></div>
      </div>
    </div>
  </div>
</div>  

</body>
<script type="text/javascript" src="<%=basePath%>common/leaflet/base/leaflet.js"></script>
<!-- 其他地图插件 -->
<script type="text/javascript" src='<%=basePath%>common/leaflet/plugins/leaflet.ChineseTmsProviders.js'></script>
<!-- eachars -->
<script src="<%=basePath%>common/eCharts/echarts.min.js"></script>
	
<script>
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

    //定义标签数组
    var myGroup = new L.LayerGroup();  
	//自定义marker图标
	var Icon = L.Icon.Default.extend({            
		options:{
			iconUrl:"<%=basePath%>common/images/syqq.png"
		}
	});
	var myIcon = new Icon();   
	
//加载初始化数据，创建marker标记
function loadData(){
	var time_type = $('input[name="time_radio_group"]:checked').val();
	var show_text = "";
	//清空右侧显示列表
	$("#waters tbody tr").remove();
	//清空地图上的marker标记
	if(myGroup != null)
		myGroup.clearLayers(); 
	
	//加载地图实时数据，并创建marker标记
	$.ajax({
		url:basePath+'search/search!searchRain.action?searchFormBean.time='+time_type,
		type:'post',
		dataType:'json',
		async:false,
		success:function(datas){ 
		    var new_stcd  , old_stcd; //当前测站和上一次的测站
			for(var i = 0 ; i < datas.length;i++){
				//显示在右侧的内容
				show_text += "<tr onclick='setHotMarker("+datas[i].LTTD1+","+datas[i].LGTD1+")'>";
				show_text +=" <td>XX地</td><td>"+datas[i].STNM+"</td><td>"+datas[i].DRP+"</td><td>"+datas[i].DYP+"</td></tr>";
				
				//测站的标签 创建一个即可
				new_stcd = datas[i].STCD;
				if(old_stcd == new_stcd)//如果是同一个测站，则忽略不显示
					continue;
				
				var marker = L.marker([datas[i].LTTD1,datas[i].LGTD1],{mid:datas[i].STCD,title:datas[i].STNM});
				marker.setIcon(myIcon);
				marker.bindPopup(datas[i].STNM); 
				marker.on('click', function(e) {   
					rain_view(this.options.mid);
				});  
				
				myGroup.addLayer(marker);
				
				old_stcd =  datas[i].STCD;
			}
	 	}   
	});
	
	//加载所有marker标记到地图中
	map.addLayer(myGroup);  
	//加载右侧table 数据列数据
	$("#waters").append(show_text);
}

function setHotMarker(x , y){
	map.setView([x, y],10);    
}

/** 展示实时统计的图标信息  */
function rain_view(stcd){ 
 	   $('#rain_modal').modal({
 				 show : true
 				,backdrop : "static" //背景遮挡
 				,moveable : true

 		}).on('shown.zui.modal', function() {
        });
        var times=[];//用来盛放X轴坐标值：时间
        var rains=[];//用来盛放Y坐标值：雨量  
        var waters=[];//用来盛放Y坐标值：水位 
        $.ajax({
				url:basePath+'search/search!searchRain.action?searchFormBean.stcd='+stcd,
				type:'post',
				dataType:'json',
				async:false,
				data:$("#search_rain_form").serialize(), 
				success:function(datas){ 
					for(var i=0;i<datas.length;i++){  
                        times.push(datas[i].TM);      
                    }
                    for(var i=0;i<datas.length;i++){  
                        rains.push(datas[i].DRP);    
                    }
                    for(var i=0;i<datas.length;i++){  
                        waters.push(datas[i].DYP);  
                    }  
			 	}   
			});
        
        
        //统计图表初始化
	 	var myChart = echarts.init(document.getElementById('myEcharts'));
                      // 指定图表的配置项和数据
        option = {
		    title : {
		        text: '实时雨情水位',
		        //subtext: '2018-03',
		        x: 'center'
		    },
		    tooltip : {
		        trigger: 'axis',
		        formatter: function(params) {
		            return params[0].name + '<br/>'
		                   + params[0].seriesName + ' : ' + params[0].value + ' <br/>'
		                   + params[1].seriesName + ' : ' + -params[1].value + ' (mm)';
		        }
		    },
		    legend: {
		        data:['水位','雨量'],
		        x: 'left'
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
// 		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
// 		            restore : {show: true},
// 		            saveAsImage : {show: true}   
		        }
		    },
		    dataZoom : {
		        show : true,
		        realtime : true,
		        start : 0,
		        end : 100
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            axisLine: {onZero: false},
		            data : times
		        }
		    ],
		    yAxis : [
		        {
		            name : '水位',
		            type : 'value',
		            max : 500
		        },
		        {
		            name : '雨量(mm)',
		            type : 'value',
		            axisLabel : {
		                formatter: function(v){
		                    return - v;
		                }
		            }
		        }
		    ],
		    series : [
		        {
		            name:'水位',
		            type:'line',
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:rains
		        },
		        {
		            name:'雨量',
		            type:'line',
		            yAxisIndex:1,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data: (function(){
		                var oriData = waters;
		                var len = oriData.length;
		                while(len--) {
		                    oriData[len] *= -1;
		                }
		                return oriData;
		            })()
		        }
		    ]
		};
		                    
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);  
}

$(function(){
	//加载默认方法
   loadData();
 
    //左边栏目的隐藏
    $('.container-fluid .row:nth-child(2) .col-md-1').click(function(){
    	 $('.container-fluid .row:nth-child(2) .col-md-11').toggle(function(){
        	 if($('.container-fluid .row:nth-child(2) .col-md-1 i').hasClass("iconfont icon-zuojiantou")){
        		 $('.container-fluid .row:nth-child(2) .col-md-1 i').removeClass("iconfont icon-zuojiantou")
        		 $('.container-fluid .row:nth-child(2) .col-md-1 i').addClass("iconfont icon-arrow-right-copy-copy")
        	}else{
        		 $('.container-fluid .row:nth-child(2) .col-md-1 i').removeClass("iconfont icon-arrow-right-copy-copy")
        		 $('.container-fluid .row:nth-child(2) .col-md-1 i').addClass("iconfont icon-zuojiantou")
        	}
          })
     }); 
          
    //右边栏目的隐藏
      $('.container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1)').click(function(){
 		 $('.container-fluid .row:nth-child(3) .col-md-12 .col-md-8').toggle(function(){
 			 if($('.container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1) i').hasClass("iconfont icon-arrow-right-copy-copy")){
 				 $('.container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1) i').removeClass("iconfont icon-arrow-right-copy-copy")
 				 $('.container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1) i').addClass("iconfont icon-zuojiantou")
 			 }else{
 				 $('.container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1) i').removeClass("iconfont icon-zuojiantou")
 				 $('.container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1) i').addClass("iconfont icon-arrow-right-copy-copy")
 			 }
 		 })
 	   });
  
 	    	   
});
 

 </script>
</html>