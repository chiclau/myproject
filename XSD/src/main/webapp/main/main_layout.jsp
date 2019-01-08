<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.lyht.util.DateUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html style="padding-top:0px;">
<head>
<title>长江经济带小水电基础信息管理系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script>
   //项目路径
   var basePath = '<%=basePath%>';
</script>

<!-- zui css -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui.css">
<!--bootstrap 样式 -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/js/bootstrapValidator/css/bootstrapValidator.css">

<link rel="stylesheet" type="text/css" id="zui_theme_link_css"	href="<%=basePath%>common/zui/css/zui-theme-green.css">
<!--自定义样式-->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/inc/css.css"> 

<!-- jquery js -->
<script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js" charset="utf-8"></script>
<!-- zui js -->
<script src="<%=basePath%>common/zui/js/zui.min.js" charset="utf-8"></script>
<!-- 提示组件 -->
<script src="<%=basePath%>common/js/bootbox.js" charset="utf-8"></script>
<!-- bootstrapValidator js-->
<script src="<%=basePath%>common/js/bootstrapValidator/js/bootstrapValidator.min.js" charset="utf-8"></script>
<script src="<%=basePath%>common/js/bootstrapValidator/js/language/zh_CN.js" charset="utf-8"></script>
<!--zui，下拉选择查找chosen-->
<link href="<%=basePath%>common/zui/lib/chosen/chosen.min.css" rel="stylesheet">
<script src="<%=basePath%>common/zui/lib/chosen/chosen.min.js" charset="utf-8"></script>
<!--日期选择-->
<script src="<%=basePath%>common/datejs/laydate.dev.js" charset="utf-8"></script>
<script src="<%=basePath%>common/zui/lib/datetimepicker/datetimepicker.min.js" type="text/javascript" charset="utf-8" ></script>
<!-- 自定义函数及变量 -->
<script src="<%=basePath%>common/inc/js.js" charset="utf-8"></script>
<link href="<%=basePath%>business/system/home_page/sty.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css">
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css">
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/combox/combox.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/icon/iconfont.css">
<link rel="stylesheet" href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
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
<script type="text/javascript" src='<%=basePath %>common/js/combox/combox.js'></script>

<script src="<%=basePath%>business/cezhanchaxun/js/cezhanchaxun_map.js"></script>
<link rel="stylesheet" href="http://www.lanyuhuitong.cn/arcgis_js_api/library/3.26/3.26/dijit/themes/claro/claro.css">
<link rel="stylesheet" href="http://www.lanyuhuitong.cn/arcgis_js_api/library/3.26/3.26/esri/css/esri.css">


<link rel="stylesheet" href="<%=basePath%>common/ztree/css/zTreeStyle/zTreeStyle.css">
<script src="http://www.lanyuhuitong.cn/arcgis_js_api/library/3.26/3.26/init.js"></script>


<style>
	 html, body, #map {
        height: 100%; width: 100%; margin: 0; padding: 0;
      }
	.anchorBL{
		display:none;
	}
	.ui_city_change_top .ui_city_change_inner, .ui_city_change_bottom .ui_city_change_inner{
      position: relative;
	 display: inline-block;
    height: 32px!important;
    width: 100px;
    color: white!important;
    background:#2E8B57!important;
    height: 32px;
    border-radius: 6px;
    line-height: 24px;
    border: 1px solid #c4c7cc;
    padding: 0 10px 0 10px;
	}
	#selCityWd{
	 height: 22px!important;
	}
	.city_content_top{
	height: 42px!important;
	}
	.ui_city_change_top .ui_city_change_inner em{
	position: absolute;
    top: -8px;
    left: 19px;
	}
	#cur_city_name{
	  display:inline-block;
	  margin-left:10px;
	  margin-top: 3px;
	  font-size: 16px;
	 }
	.havemap-main-layout,.nomap-main-layout{
		width:100%;
		height:calc(100% - 70px);
		overflow:auto;
	}
	.map-frame-left-div,.map-frame-right-div{
		height:calc(100% - 20px);
		margin-top:8px;
		margin-bottom:10px;
	}
	.map-frame-left-div{
	   position:relative;
		margin-left:10px;
		margin-right:5px;
		width:calc(74% - 17px);
		float:left;
	}
	i{
	 font-size:18px;
	 color:white;
	}
	.navbar-nav>li>a{
	    position:relative;
		margin-top: 42px;
	    padding-top: 0px!important; 
	    padding-bottom: 0px!important; 
	}
	a .icon, a [class^="icon-"], a [class*=" icon-"]{
	      position: absolute;
		    top: -29px;
		    left: 34px;
		    font-size: 26px;
		    display: inline;
	}
	.map-frame-right-div{
		margin-left:5px;
		margin-right:10px;
		width:calc(26% - 17px);
		float:right;
	}
	.map-view{
		width:100%;
		height:100%;
	}
	.nomap-main-layout{
		display:none;
	}
	.map-frame-top{
		width:100%;
	}
	.map-frame-bottom{
		width:100%;
	}
	.map-left-tool{
	    position: absolute;
	    top:20px;
	    left:0px;
	    z-index: 10000;
	}
	body{
		padding-top:0px;
		width:100%;
		height:100%;
	}
	/* 新加样式 */
	#mapdiv{
	    height:540px!important;
	    border-radius:5px;
	}	
	#map_ly {
	   width:100px;
	   height:30px;
	   background:#5ea1dc;
	   color:white;
	   border:0px;
	   border-radius:5px;
	}
	#map_sf{
	   width:100px;
	   margin-top:10px;
	   height:30px;
	   background:#6bc667;
	   color:white;
	   border:0px;
	   border-radius:5px;
	}
	.form-control{
	padding:5px 30px;
	}
	#ulall{
	    position: absolute;
	    width: 280px;
	    background: white;
	    height: 335px;
	    transform: translate(50%,0px);
	    overflow: auto;
	    top: 52px;
	    background: rgba(94,161,220,.8);
	    border-radius: 10px;
	    right: 50%;
	    z-index: 100011;
	    border: 1px solid rgba(255,255,255,0.1);
	}
	#ulall a{
		display: block;
	    padding: 3px 10px 3px 38px;
	    font-size: 16px;
	    width: 100%;
	    color: white;
	   
	}
	#ulall a:hover{
		text-decoration: none;
	}
	#ulall li:hover{
	
		 background:#0e60aa;
		color:white; 
		
	}
	
    .hiddened{
     display:none;
    }
   .esriSimpleSliderTL{
	     position:absolute;
	     right:18px;
	     left:auto;
   }
   input{
      color:white;
   }
     input::-webkit-input-placeholder{
            color:white!important;
        }
       input::-moz-placeholder{  
           color:white!important;
       }
       input:-moz-placeholder{    
           color:white!important;
       }
       input:-ms-input-placeholder{  
           color:white!important;
       }
       input:focus{color:white}
     #souzuokuang:hover{
           color:#568fc3;
       }
       .cd-accordion-menu li：last-child{
         border-bottom-left-radius: 6px;
         border-bottom-right-radius: 6px;
       }
       .ione{
        position:absolute;
        left: auto;
        right: 19px;
        z-index:333;
        top: 93px;
        line-height:30px;
        border-radius:6px;
        text-align:center;
        display:inline-block;
        width:30px;
        height:30px;
        background: white;
        color:#4c4c4c;
       }
      .ione:hover{
         cursor:pointer;
         background:#0e60aa;
       }
     .hideBtnclass{
       display:none;
     }
     #tree_1_ico{
       display:inline-block;
       width:40px;
       height:40px;
     }
     
  #tree{
    position:absolute;
    z-index:1000;
   display:none; 
   left:auto;
   right: 19px;
    top: 93px;
   width:200px;
   height:300px;
   overflow:auto;
   background: white;
   border-radius:6px;
  }
   #tree:hover{
    cursor:pointer;
    display:block;
  }
  .ione:hover +#tree{
         display:block;
   } 
</style>
</head>
<body style="padding-top:0px;">
	<div class="container-fluid" style="height:70px;">
		<%@include file="/common/inc/top.jsp"%>
	</div>
	<div id="map-frame-content" class="havemap-main-layout">
		<div class="map-frame-top">
			<div id="mapdiv_container" class="map-frame-left-div">
				<div id="map" class="claro" style="height:540px;border-radius:6px">
				</div>
				<div class="map-left-tool">
					<button id="map_ly">长江经济带</button><br/>
					<button id="map_sf">全国</button>
				</div> 
		        <i class="icon icon-list-alt ione"></i>
		         <ul id="tree" class="tree " data-ride="tree">
					  <li class="open in">
					    <a href="#">图层</a>
					    <ul id = "layer_id">
					    </ul>
					  </li>
					</ul>
				 <div class="input-group" style="position:absolute;width:280px;top:20px;right: 50%;transform:translate(50%,0px);z-index:100011">
				  <div class="input-control search-box search-box-circle has-icon-left has-icon-right search-example" id="searchboxExample">
				    <input id="inputSearchExample3" style="background: rgba(94,161,220,1);border-radius:25px" type="search" class="form-control search-input" placeholder="请输入省市名称或区域名称">
				    <label for="inputSearchExample3" class="input-control-icon-left search-icon"><i id="souzuokuang" style="position: relative;top: -24px;left: 10px;z-index: 1000;display: inline-block;margin-top: -20px;" class="icon icon-search" onclick="keyUp()"></i></label>
				  </div>
				  <%-- <span class="input-group-btn">
				    <button style="margin-top:-20px;margin-left:20px;border-radius:6px;" class="btn btn-primary" type="button">搜索</button>
				  </span> --%>
				</div>
				<ul id="ulall" class="hiddened"></ul> 
			</div>
			<div id="map_frame_right_div" class="map-frame-right-div"></div>
		</div>
		<div id="map_frame_bottom_div" class="map-frame-bottom">
			
		</div>
	</div>
	<div id="nomap-frame-content" class="container-fluid nomap-main-layout">
		
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
</body>
<script type="text/javascript">    
var mapSearchParam={_defautlZoom:3,_overLay:5,cxfw:"ly",lydm:"",lyname:"",sfdm:"",sfname:""};
	function keyUp(){
  	   	var bj = $("#inputSearchExample3").val();
  	  if (bj.indexOf("县") >= 0||bj.indexOf("镇") >= 0) {
  		layer.msg("提示：无法定位县/镇 ");
          return false;
  	  }
  	   	$("#ulall").html("");
  	   	if(bj == null || bj == ""){
  	   		layer.msg("请输入");
  	   		return false;
  	   	}else{
  	   		$.ajax({
  	   	   		url:basePath+"homePage/homePage!keyUp.action",
  	   	   		data:{"bj":bj},
  	   	   		type: "POST",
  	   	   		dataType:"json",
  	   	   		success:function(response){
  	   	   			var data = response.rows;
  	   	   			if(data != null && data.length > 0){
  	   	   				$("#ulall").removeClass("hiddened");
  	   	   				var li = "";
  	   	   				for (var i = 0; i < data.length; i++) {
  	   	   					if(data[i].DJ != null && data[i].DJ != ""){
  		   	   					li += '<li style="position:relative;border-bottom:1px solid rgb(255,255,255)"><img src="../common/images/电站.png" style="margin-left:2%;margin-bottom:10px;display:inline-block;width:8%;position:absolute;top:0px;left:0px;">'
  		 	   						  +'<a style="display:inline-block;width:90%" href="#" onclick="changeEnabled('+JSON.stringify(data[i]).replace(/\"/g,"'")+')">'+data[i].名称+'</a>';
  			   	   					  +'</li>';
  	   	   					}else{
  		   	   					li += '<li style="position:relative;border-bottom:1px solid rgb(255,255,255)"><img src="../common/images/位置.png" style="margin-left:2%;margin-bottom:10px;display:inline-block;width:8%;position:absolute;top:0px;left:0px;">'
  		 	   						  +'<a style="display:inline-block;width:90%" href="#" onclick="changeEnabled('+JSON.stringify(data[i]).replace(/\"/g,"'")+')">'+data[i].名称+'</a>';
  			   	   					  +'</li>';
  	   	   					}
  	   	   				}
  	   	   				$("#ulall").append(li);
  	   	   			}else{
  	   	   				layer.msg("未搜索到定位信息");
  	   	   			}
  	   	   		}
  	   	   	});
  	   	}
  	}
</script>
</html>






