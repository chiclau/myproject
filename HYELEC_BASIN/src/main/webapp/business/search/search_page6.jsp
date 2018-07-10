<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <!DOCTYPE html>
<html lang="zh-cn" class="screen-desktop-wide device-desktop"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
  <title>实时水情展示</title>
 	 <%@include file="/common/inc/inc.inc"%>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>common/leaflet/base/leaflet.css">
    <link href="<%=basePath%>business/system/home_page/sty.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/assets/css/iconfont.css">
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
         
          border:1px solid #E4E4E4;
          background-color: #FFFFFF;
        }
        
         table tr th{
          padding:0px;
          text-align:center;
          height:30px;
          line-height:30px;
          border:1px solid #E4E4E4;
        }
        .table-condensed td, .table-condensed th{
          padding:0px;
        }
        
         table tr td{ 
          padding:0px;
          height:25px; 
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
		    margin-top: 11px;

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
        }
        .container-fluid .row:nth-child(2) .col-md-11{
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
         	top:23px;
         	right:8px;	
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
          height:360px;
          background: #3EB7EE;
          box-shadow: 5px 4px 13px #888888;
        }
        .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row {
          height:200px;
        	padding-left: 0px;
         	padding-right: 0px;
        	margin-top:30px;
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
               margin-top: 100px;
               margin-left:5px;
               width:100%;
               height:20px;
               margin-right: 0px;
          }
           .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row:nth-child(5) .col-md-12{
               width: 100%;
               height:20px;
               background-color: red;
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
              margin-top: 20px;
          }
         /* 给地图中间文字更改颜色*/
	      .leaflet-popup-content-wrapper{
	         color: black;
	      }
         /* 给navbaer刺次级导航加背景色*/
	     .navbar-nav>li>.dropdown-menu {
        background: #145CCD;
        top: 100%;
        color: white;
       }
        .navbar-nav>li>ul>li>a:hover{
          background:#3CB7F3;
        }
        .dropdown-menu>li>a:hover{
         background:#3CB7F3;
        }
        /* 给navbaer刺次级导航加背景色*/
       .dropdown-hover:hover>.dropdown-menu, .open>.dropdown-menu {
        background: #145CCD;
       }
      .dropdown-menu .divider{
            height: 0px;
       }
       .dropdown-submenu>.dropdown-menu.pull-left{
        background:  #145CCD;
       }
       .dropdown-menu>li>a span{
        color:white;
       }
       .btn-group .navbar-right .nav .open .dropdown-menu>li>a {
        color:white;
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
    .leaflet-right .leaflet-control,.leaflet-left .leaflet-control{
             margin-top:86px!important;
          }
           .container-fluid .row:nth-child(3) .col-md-12 .col-md-2:nth-child(1):hover{
            background-color: #50A2CB;     
          }
          .container-fluid .row:nth-child(2) .col-md-1:hover{
            background-color: #50A2CB;
          }
    </style>
</head>
<body>
 <div class="container-fluid">
	 <%@include file="/common/inc/top.jsp"%>   
</div>
     <br>
     <div class="container-fluid ">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
		          <div class="row-fluid">
			         <div id="map"  style="height:662px;"></div>
			       </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-11 col-lg-11 col-sm-11 col-xs-11">
                <ul>
                  <li><h2>应用信息</h2></li>
                  <li><h3><i class="iconfont icon-iconset0457"></i> 实时雨情</h3></li>
                  <li><h3><i class="iconfont icon-iconset0457"></i> 实时水情</h3></li>
                  <li><h3><i class="iconfont icon-iconset0457"></i> 等值线面</h3></li>
                  <li><h3><i class="iconfont icon-iconset0457"></i> 实时预报</h3></li>
               </ul>
            </div>
            <div class="col-md-1 col-lg-1 col-sm-1 col-xs-1"><i class="iconfont icon-zuojiantou" style="color:white"></i></div>
        </div>
        <div class="row">
            <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
	               <div class="col-md-2 col-lg-2 col-sm-2 col-xs-2"><i class="iconfont icon-arrow-right-copy-copy" style="color:white"></i></div>
	            <div class="col-md-8 col-lg-8 col-sm-8 col-xs-8">
	            	<div class="row">
	            		<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">
    	            			<select class="form-control">
            							  <option value="1">行政区</option>
            							  <option value="2">流域</option>
        							  </select>
	            	  </div>
	            		<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">
  	            			<select class="form-control">
        							  <option value="1">全省</option>
        							  <option value="2">湘江流域</option>
      							  </select>
	            		</div>
	            	</div>
	            	<div class="row">
	            		   &nbsp;站类选择 :
	            		  <label class="checkbox-inline">
      						  <input type="checkbox"> 水库
        						</label>
        						<label class="checkbox-inline">
        						  <input type="checkbox"> 河道
        						</label>
        						<label class="checkbox-inline">
        						  <input type="checkbox"> 堰闸
        						</label>
        						<label class="checkbox-inline">
        						  <input type="checkbox"> 潮汐
        						</label>
	            	</div>
	            	<div class="row">
                     &nbsp;水库类型 :
                    <label class="checkbox-inline">
                    <input type="checkbox"> 大型
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 中型
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 小型
                    </label>
                </div>
                <div class="row">
                     &nbsp;报讯等级 :
                    <label class="checkbox-inline">
                    <input type="checkbox"> 中央
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 省重点
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 省一般
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 山洪
                    </label>
                </div>
                <div>
                  <div class="row">
                    <div class="col-md-12" class="margin-top:-20px">
                        <form class="form-inline">
                        <div class="form-group">
                          <label class="sr-only" for="exampleInputEmail3">测站名称</label>
                          <input type="text" class="form-control" id="text" placeholder="测站名称">
                        </div>
                        <button type="submit" class="btn btn-default btn btn-success" style="color:white">查询</button>
                       </form>
                    </div>
                  </div>
                </div>
                      <div class="row">
                        <div class="col-md-12">
                             <table class="table table-condensed bordered">
                                  <tr>
                                      <th>地区</th>
                                      <th>县市</th>
                                      <th>站名</th>
                                      <th>水位</th>
                                  </tr>
                                  <tr >
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                  </tr>
                                  <tr>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                  </tr>
                                  <tr>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                  </tr>
                                  <tr>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                  </tr>
                                  <tr>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                  </tr>
                                  <tr>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                      <td></td>
                                  </tr>
                             </table>
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
    
    
 <script type="text/javascript" src="<%=basePath %>common/leaflet/base/leaflet.js"></script>
 <!-- 其他地图插件 -->
 <script type="text/javascript" src='<%=basePath %>common/leaflet/plugins/leaflet.ChineseTmsProviders.js'></script>
 <!-- 热点图  zui样式冲突，无法显示热点-->
 <script type="text/javascript" src='<%=basePath %>common/leaflet/plugins/leaflet-heat.js'></script>
 <script type="text/javascript" src='<%=basePath %>common/leaflet/demo/reword.js'></script>
 
 <!-- eachars -->
 <script src="<%=basePath %>common/eCharts/echarts.min.js"></script>
<script>
	
      $(function(){
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

			//marker 标记
			var marker2 = L.marker([28.1365,113.0436]).addTo(map);
			marker2.bindPopup('中南院').openPopup(); 
			 
      });
 </script>
    
    


 </body>
 </html>