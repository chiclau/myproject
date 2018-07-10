<%@page import="com.lyht.util.DateUtil"%>
<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <!DOCTYPE html>
<html lang="zh-cn" class="screen-desktop-wide device-desktop"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
  <title>实时雨情展示</title>
 	 <%@include file="/common/inc/inc.inc"%>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>common/leaflet/base/leaflet.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/assets/css_/iconfont.css">  
    <link href="<%=basePath%>business/system/home_page/sty.css" rel="stylesheet" type="text/css"/>
    
    <style type="text/css">
    
    	/*引入外部样式*/
		 html, body{
        padding-top:0px;
     }
	 ul li{
          list-style: none;
        }
        table{
          margin-top: 40px;
          color:black;
          margin-top:-5px;
          margin-bottom:0px;
          border:1px solid #E4E4E4;
          background-color: #FFFFFF;
        }
        
         table tr th{
          padding:0px;
          text-align:center;
          height:30px;
          line-height:30px!important;
          border:1px solid #E4E4E4;
        }
        .table-condensed td, .table-condensed th{
          padding:0px;
        }   
         table tr td{ 
          padding:0px;
          height:30px;
          text-align:center;    
          border:1px solid #E4E4E4;
        }
      
        body, html{
          width: 100%;
          height: 100%;
          border:1px solid #E4E4E4;
          overflow: hidden;
          line-height:1.38;
          margin:0;
          font-family:"微软雅黑";
        }
        .container-fluid:first-child .navbar-header {
		    margin-left: 7px;
		    margin-top:-3px;

        }
         .container-fluid:first-child .navbar-header h3{
		    font-size:20px;

        }
        #sass:hover{
        	background-color: #2A74EA;
        }
        .container-fluid:last-child{
          position: relative;
          height:100%;
          color:white;
          font-weight: 300px;
        }
        .container-fluid .row:nth-child(1){
          position: absolute;
          z-index: 1;
          width: 100%;
          height:100%;
          margin-left:0px; 
        }
         .container-fluid .row:nth-child(1) .col-md-12{
          height:100%;
          padding-right: 0px;
          padding-left: 0px;
        }

        .container-fluid .row:nth-child(2){
          position: absolute;
          z-index: 1;
          top:140px;
          left:0px;
          width:240px;
          height:300px;
        }
         .container-fluid .row:nth-child(2) .col-md-11{
           box-shadow: 2px 1px 8px #888888;
           padding-right:0px;
           height:100%;
           color: white;
           background:#3CB7F3;
           padding-left:0px;
        }
       
         .container-fluid .row:nth-child(2) .col-md-11 ul{
          padding-left:0px;
          height:100%;
        }
        .container-fluid .row:nth-child(2) .col-md-11 ul li h2{
          line-height:50px;
          text-indent: 1em;
          font-weight: 200;
          font-size:24px;
        }
         .container-fluid .row:nth-child(2) .col-md-11 ul li h3{
          cursor: pointer;
          text-indent: 1em;
          font-weight: 200;
          line-height: 50px;
          font-size: 20px;
          height:50px;
          margin:0px;
        }
        
        .container-fluid .row:nth-child(2) .col-md-11 ul li h3:hover{
          background-color: #50A2CB;
        }
        .container-fluid .row:nth-child(2) .col-md-11 ul li h3{
          margin-top:0px;
          margin-bottom:0px; 
        }
        .container-fluid .row:nth-child(2) .col-md-11 ul li i{
         font-size: 32px;
        }
         .container-fluid .row:nth-child(2) .col-md-11 ul li:nth-child(1){
          border-bottom:2px solid #61AECC;
        }
        .container-fluid .row:nth-child(3){
          position: absolute;
          top:155px;         
          z-index: 444;
          height:370px;
        }
        .container-fluid .row:nth-child(2) .col-md-1{  
            top:113px;      	        	        			  
		    width:10px;
		    height: 65px;
        	cursor: pointer;
            text-align: center;
		    border-top-right-radius: 30px;
		    border-bottom-right-radius: 30px;
		    background: #3EB7EE;
        }
        .container-fluid .row:nth-child(2){
           top: 171px;
        }
         .container-fluid .row:nth-child(2) .col-md-1 i{
         	position: relative;
         	top:25px;
         	right:9px;
         	margin-top:23px; 
         	margin-right:50px; 
         }
         .container-fluid .row:nth-child(3) .col-md-12{
          height:100%;
          padding-left:0px;
          padding-right:0px;
        }
         .container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1){
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
         .container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1) i{
          display: inline-block;
          padding-right: 20px;
        }
         .container-fluid .row:nth-child(3) .col-md-12 .col-md-8{
          padding-left:5px;
          padding-right:5px;
          margin-right: 0px;
          width:300px;
          height:370px;
          background: #3EB7EE;
          box-shadow: 5px 4px 13px #888888;
        }
        .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row {
            height:48px;
        	padding-left: 0px;
         	padding-right: 0px;
        }
        .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row .col-md-6 {
         	padding-left: 0px;
         	width: 80px;
         	padding-right: 0px;
         }
         .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row .col-md-6 .form-control{
         	padding: 0px;
         	margin-left: 20px;
         	width: 80px;
         	height:24px;
         	border-radius: 0px;
         }
         .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row {
               margin-top: 30px; 
          }
          .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row .col-md-6:nth-child(1) .form-control{
          	   margin-left: 10px;
          }
          .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row .col-md-6:nth-child(2) .form-control{
          	   padding-left: 10px;
          }
          #posi .col-md-4{
             width:80px;
             height:24px;
             margin-left:10px;
          }
          #posi .col-md-4:nth-child(1){           
             margin-left:-5px;
          }
          #posi .col-md-4 .form-control{
             padding:0px;
             width:80px;
             height:24px;        
          }
          
          .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row:nth-child(2){
          	   color: #00416A;
          	   margin-top:-110px;
          	   margin-left:10px;
          	   width:100%;
          	   height:20px;
          	   margin-right: 0px;
          }
        
          .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row:nth-child(3){
               color: #00416A;
               margin-top:-108px;
               margin-left:0px;
               width:100%;
               height:20px;
               margin-right: 0px;
          }
          .container-fluid .row:nth-child(3){
              top: 188px;
          }
          
          .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row:nth-child(4){
               position: relative;
               z-index: 888;
               color: #00416A;
               margin-top: 75px;
               margin-left:5px;
               width:100%;
               height:20px;
               margin-right: 0px;
          }
           .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row:nth-child(5) .col-md-12{
               width: 100%;
               height:20px;
              
          }
          .container-fluid .row:nth-child(3) .col-md-12 {
                height:200px;
                padding-left: 10px;
                padding-right: 10px;
            }
           
        /* 导航的次级导航*/
        .dropdown-menu{
             top:97%
           } 
        .navbar-nav>li>.dropdown-menu{
           top:107%;
           }        
       .navbar-default .navbar-nav>li>a{
             padding-bottom: 24px;
        }
           .checkbox-inline+.checkbox-inline, .radio-inline+.radio-inline {
              margin-top: 0;
              margin-left: 0px;
          }
          .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row {
               margin-top: 10px; 
          }
          .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row .col-md-6 .form-control {
              margin-top: 9px;
          }
         /* 给地图中间文字更改颜色*/
	      .leaflet-popup-content-wrapper{
	         color: black;
	      }
         /* 给navbaer刺次级导航加背景色*/
	     .navbar-nav>li>.dropdown-menu {
          top: 100%;
       }
       
      .dropdown-menu .divider{
            height: 0px;
       }
     
    /*所有导航i图标字体大小*/
    .navbar-inverse .navbar-nav>li>a i{
       font-size: 24px;
    }
     /*左右定位i图标字体大小*/
  .container-fluid .row:nth-child(3)  .col-md-2 i{  
        position: absolute;
        top: 40%;
        left: 20%;
        display: inline-block;
        width: 20px;
        height: 20px;
  }
    #user a i{
      display: inline-block;
      font-size:24px;
      margin-top: 10px;
    }
.container-fluid .row:nth-child(3){
     right: -10px;
    }
    .container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1):hover{
            background-color: #50A2CB;     
          }
          .container-fluid .row:nth-child(2) .col-md-1:hover{
            background-color: #50A2CB;
          }
          
        #textid{
        width:80px;
        height:24px;
        }
       
        #tablesecond tr td{  
        width:67px;
        height:30px;
        line-height:30px;
        }
     .leaflet-top {
	  top:60px;
	}
    </style>
</head>
<body>
 <div class="container-fluid">
	 <%@include file="/common/inc/top.jsp"%>   
</div>
     <div class="container-fluid" style="height:100%;">
        <div class="row" style="height:100%;">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12" style="height:100%;">
		          <div class="row-fluid" style="height:100%;">
			         <div id="map"  style="height:100%;"></div>
			       </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-11 col-lg-11 col-sm-11 col-xs-11">
                <ul>
                  <li><h2>应用信息</h2></li>
                  <li onclick="javascript:window.location.href='<%=basePath%>business/search/search_page2.jsp'; "><h3><i class="iconfont icon-iconset0457"></i> 实时预报</h3></li>
                  <li onclick="javascript:window.location.href='<%=basePath%>business/search/search_page3.jsp'; "><h3><i class="iconfont icon-iconset0457"></i> 实时雨情</h3></li>
                  <li onclick="javascript:window.location.href='<%=basePath%>business/search/search_page4.jsp'; "><h3><i class="iconfont icon-iconset0457"></i> 实时水情</h3></li>
<%--                   <li onclick="javascript:window.location.href='<%=basePath%>business/search/search_page5.jsp'; "><h3><i class="iconfont icon-iconset0457"></i> 等值线面</h3></li> --%>
               </ul>
            </div>
            <div class="col-md-1 col-lg-1 col-sm-1 col-xs-1"><i class="iconfont icon-zuojiantou" style="color:white"></i></div>
        </div>
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
	               <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2"><i class="iconfont icon-arrow-right-copy-copy" style="color:white"></i></div>
	            <div class="col-md-8 col-lg-8 col-sm-8 col-xs-8">
	            	<div class="row">
	            	</div>
	            	<div class="row" id="posi">
	            		  <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">
    	            			<select class="form-control" onchange="show_sub(this.options[this.options.selectedIndex].value)">
    	            					  <option value="0">-请选择-</option>
            							  <option value="1">行政区</option>
            							  <option value="2">流域</option>
        						 </select>
	            	  	  </div>
	            		<div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">
  	            				  <select class="form-control" id="mySelect">
  	            					  <option value="0">-请选择-</option>
      							  </select>
	            		</div>
	            		<!-- <div class="col-md-4 col-lg-4 col-sm-4 col-xs-4">
  	            			<input type="text" id="textid" style="padding-left:5px">
	            		</div> -->
	            	</div>
	            <div class="row">
                </div>
                <div class="row">
                </div>
                <div style="height:50px">
                  <div class="row" style="height:50px">
                    <div class="col-md-12" class="margin-top:-20px" style="height:50px">
                        <form class="form-inline">
                        <div class="form-group">
                          <label class="sr-only" for="exampleInputEmail3">测站名称</label>
                          <input type="text" class="form-control" id="text" placeholder="测站名称">
                        </div>
                        <button type="button" class="btn btn-default btn btn-success" style="color:white" onclick="loadData()">查询</button>
                       </form>
                    </div>
                  </div>
                </div>
	              <div class="row">
                        <div class="col-md-12">
                             <table class="table table-condensed bordered"  style="margin-bottom:0px">
                                  <tr>
                                      <th>地区</th>
                                      <th>站址</th>
                                      <th>站名</th>
                                      <th>水位</th>
                                  </tr>
                             </table>
	                      <div class="col-md-12" style="overflow:auto;height:178px;width:280px;padding-left:0px;padding-right:0px;">
                              <table class="table table-condensed bordered" id="waters">
                             </table>
                            </div>
                       </div>
                    </div>
	            </div>
	            <!-- <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2">
	            	<ul>
	            		<li><i class="icon icon-plus"></i></li>
	            		<li><i class="icon icon-minus"></i></li>
	            		<li><i class="icon icon-location-arrow"></i></li>
	            		<li><i class="icon icon-stack"></i></li>
	            		<li><i class="icon icon-info"></i></li>
	            		<li><i class="icon icon-share-sign"></i></li>
	            		<li><i class="icon icon-chat-line"></i></li>
	            		<li><i class="icon icon-reply"></i></li>
	            	</ul>
	            </div> -->
            </div>
        </div>
    </div>    
  
 <!--  对话框 -->
 <div class="modal fade" id="myLgModal" >
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
						 <input class="btn btn-defalut" value="查询" type="button" onclick="rain_view(stcd,100000000)">
							<input class="btn btn-success" value="前一天" type="button" onclick="rain_view(stcd,-1)">
							<input class="btn btn-success" value="后一天" type="button" onclick="rain_view(stcd,1)">
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
 <script type="text/javascript" src="<%=basePath %>common/leaflet/base/leaflet.js"></script>
 <!-- 其他地图插件 -->
 <script type="text/javascript" src='<%=basePath %>common/leaflet/plugins/leaflet.ChineseTmsProviders.js'></script>
 <!-- 热点图  zui样式冲突，无法显示热点-->
 <script type="text/javascript" src='<%=basePath %>common/leaflet/plugins/leaflet-heat.js'></script>
 <script type="text/javascript" src='<%=basePath %>common/leaflet/demo/reword.js'></script>
 
 <!-- eachars -->
 <script src="<%=basePath %>common/eCharts/echarts.min.js"></script>
<script>
	//下拉列表加载数据
	function show_sub(v){
		$("#mySelect").empty();
		//alert(v)
		if(v==0){
			$("#mySelect").append('<option value="0">-请选择-</option>')
			return;
		}
		$.ajax({
				url:basePath+'search/search!serchProvinceBasin.action?searchFormBean.administrativeRegionBasin='+v,
				type:'post',
				dataType:'json',
				async:false,
				success:function(datas){ 
						//alert(datas)
						$("#mySelect").append('<option value="0">-请选择-</option>')
						for (var i = 0; i < datas.length; i++) {
							$("#mySelect").append('<option value='+datas[i].id+'>'+datas[i].RVNM+'</option>')
						}
					}
				})
			}
      $(function(){
    	  loadData();
          //点击隐藏箭将查询栏隐藏
          $('#sass').mouseenter(function(){
            $('#sass').css({background:'#2A74EA'})
          }).mouseleave(function(){
            $('#sass').css({background:'#145CCD'})
          }).click(function(){
            $('#sass').css({background:'#2A74EA'})
          });
          
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

			var normal = L.layerGroup([normalm]),
			    image = L.layerGroup([imgm, imga]);

			var baseLayers = {
			    "地图": normal,
			    "影像": image,
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
	//alert($("#endTime").val());
	var show_text = "";
	//清空右侧显示列表
	$("#waters tbody tr").remove();
	//清空地图上的marker标记
	if(myGroup != null)
		myGroup.clearLayers();
	var administrativeRegionBasin = $("select option:checked").val();
	var provinceBasin = $("#mySelect").val();
	var stationName = $("#text").val();
	//加载地图实时数据，并创建marker标记
	$.ajax({
		url:basePath+'search/search!serchRainInformation.action?searchFormBean.stationName='+stationName+'&searchFormBean.provinceBasin='+provinceBasin+'&searchFormBean.administrativeRegionBasin='+administrativeRegionBasin,
		type:'post',
		dataType:'json',
		async:false,
		success:function(datas){ 
		    var new_stcd  , old_stcd; //当前测站和上一次的测站
			for(var i = 0 ; i < datas.length;i++){
				//显示在右侧的内容
				show_text += "<tr onclick='setHotMarker("+datas[i].LTTD1+","+datas[i].LGTD1+")'>";
				show_text +=" <td>"+datas[i].NAME+"</td><td>"+datas[i].STLC+"</td><td>"+datas[i].STNM+"</td><td>"+datas[i].DYP+"</td></tr>";
				
				//测站的标签 创建一个即可
				new_stcd = datas[i].STCD;
				if(old_stcd == new_stcd)//如果是同一个测站，则忽略不显示
					continue;
				var marker = L.marker([datas[i].LTTD1,datas[i].LGTD1],{mid:datas[i].STCD,title:datas[i].STNM});
				marker.setIcon(myIcon);
				marker.bindPopup(datas[i].STNM); 
				marker.on('click', function(e) {   
					rain_view(this.options.mid,0);
					stcd = this.options.mid;
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
//定义前后一日标记
ConditionalMarkup = 0;
/** 展示实时统计的图标信息  */
function rain_view(stcd,dayMarking){ 
 	   $('#myLgModal').modal({
 				 show : true
 				,backdrop : "static" //背景遮挡
 				,moveable : true

 		}).on('shown.zui.modal', function() {
        });
        var times=[];//用来盛放X轴坐标值：时间
        var timeLength=[];//用来盛放Y坐标值：时长
        var waters=[];//用来盛放Y坐标值：水位
        if(dayMarking != 0 && dayMarking!= 100000000){ //判断标记 用户是否点击前一日或者后一日 	100000000代表用户点击的根据时间查询按钮 下次打开重置时间
        	ConditionalMarkup = ConditionalMarkup+dayMarking
        }else{
        	//初始化标记 以免X掉以后重新打开数据不变
        	ConditionalMarkup = 0;
        	if(dayMarking != 100000000){
        		$("#endTime").val('<%=DateUtil.getDate()+" 23:59:59"%>');
        		$("#startTime").val('<%=DateUtil.addDay(DateUtil.getDate(), -7)+" 00:00:00"%>');
        	}
        }
        $.ajax({
				url:basePath+'search/search!serchRainInformation.action?searchFormBean.stcd='+stcd+'&searchFormBean.ConditionalMarkup='+ConditionalMarkup,
				type:'post',
				dataType:'json',
				async:false,
				data:$("#search_rain_form").serialize(), 
				success:function(datas){ 
					for(var i=0;i<datas.length;i++){  
                        times.push(datas[i].TM);  
                    }
                    for(var i=0;i<datas.length;i++){  
                    	timeLength.push(datas[i].PDR);    
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
		        data:['水位','时长'],
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
		            name : '时长',
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
		            data:waters
		        },
		        {
		            name:'时长',
		            type:'line',
		            yAxisIndex:1,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data: (function(){
		                var oriData =timeLength ;
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
 </script>
    
    


 </body>
 </html>