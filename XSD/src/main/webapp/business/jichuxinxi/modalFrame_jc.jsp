<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String name = request.getParameter("name");//用request得到
	String sheng = request.getParameter("sheng");//用request得到
	String shi = request.getParameter("shi");//用request得到
	String xian = request.getParameter("xian");//用request得到
	String tab = request.getParameter("tab");//用request得到
	String seriesName = request.getParameter("seriesName");//用request得到
	
%>
<!DOCTYPE html>
<html style="padding-top:0px;">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/layui/css/layui.css">


<!-- zui css -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui.css">
<!--bootstrap 样式 -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/js/bootstrapValidator/css/bootstrapValidator.css">

<link rel="stylesheet" type="text/css" id="zui_theme_link_css"	href="<%=basePath%>common/zui/css/zui-theme-green.css">
<!--自定义样式-->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/inc/css.css"> 

<!-- jquery js -->
<script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js" charset="utf-8"></script>
<script src="<%=basePath%>common/jquery/jquery.cookie.js" charset="utf-8"></script>
<!-- zui js -->
<script src="<%=basePath%>common/zui/js/zui.min.js" charset="utf-8"></script>
<script src="<%=basePath%>common/layui/layui.js"></script>
<!-- 提示组件 -->
<script src="<%=basePath%>common/js/bootbox.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>common/echarts3.8/echarts.min.js"></script>

<style>
      .col-md-6{
       height:340px;
      }
      .col-md-6:nth-child(2), .col-md-6:nth-child(4){
       padding-left:0px;
      }
      #item-one{
       color:white;
       background: #568fc3;
      }
      .list-group-item{
        padding: 5px 13px;
      }
 
      .table{
       margin-bottom:0px;
      }
      .layui-table, .layui-table-view{
       margin:0px;
      }
      .container-tablecss{
       height:200px;
       overflow:auto;
       width: 101%;
      }
      .layui-table tr th{
        border:0px solid red;
        background: #518cc2;
        color:white;
       
      }
      .layui-table td, .layui-table th{
       text-align:center;
      }
      .layui-table-tool{
      	display:none;
      }
</style>
</head>

<body>
<div style="height:100%;width:100%;">
	 <div class="container-fluid">
            <div class="row">
                   <div class="col-md-12" style="margin-top:8x;padding-left:0px;padding-right:0px;">
				         <table class="layui-hide" id=jc lay-filter="test"></table>
				            <script type="text/html" id="id">
 				{{d.LAY_TABLE_INDEX+1}}
				</script>
              </div>
           
            </div>
        </div>
        
</div>
</body>

  <script type="text/javascript">
$(function(){
	 basePath = '<%=basePath%>';
	 name = '<%=name%>';
	 shi = '<%=shi%>';
	 xian = '<%=xian%>';
	 sheng = '<%=sheng%>';
	 tab= '<%=tab%>';
	 seriesName= '<%=seriesName%>';
 initTable(name,sheng,shi,xian,tab,seriesName);
})
        var areaWidth=['80%','90%']
function initTable(name,sheng,shi,xian,tab,seriesName){
	if(name == "总数(座)"||name == "总数(万千瓦)"){
		 name = "";
	}
	  var table = layui.table;
	  layui.use('table', function(){
	   	  var table = layui.table;
	   	  table.render({
	   	    elem: '#jc'
	   	    ,url:'<%=basePath%>'+"jcxx/jcxx!list.action"
	   	    ,toolbar: '#toolbarDemo'
	   	    ,title: '用户数据表'
	   	    ,where:{"name":name,"sheng":sheng,"shi":shi,"xian":xian,"tab":tab,"seriesName":seriesName}
	   	    ,cols: [[
	   	      {field:'id', title:'序号', width:80, fixed: 'left',templet:'#id'}
	   	      ,{field:'xmmc', title:'项目名称',fixed: 'left', width:250}
	   	      ,{field:'xmwz', title:'项目位置', width:150}
	   	      ,{field:'jthl', title:'所在河流（对应具体河流）', width:200}
	   	      ,{field:'zjrl', title:'装机容量(千瓦)', width:150}
	   	      ,{field:'ztz', title:'总投资(万元)', width:150}
	   	      ,{field:'sjnfdl', title:'设计年发电量(万千瓦时)', width:150}
	   	      ,{field:'jszt', title:'建设（运营）状态', width:150}
	   	      ,{field:'tzxz', title:'投资性质/来源', width:150}
	   	      ,{field:'bwqk', title:'并网情况', width:150}
	   	      ,{field: 'kffs', title:'开发方式',  width:150}
	   	      ,{field: 'sfyxmhz', title:'是否有项目核准', width:180}
	   	    ]]
	   	    ,page: true
	   	  });
	   	table.on('row(test)', function(obj){
	   	    var id = obj.data.id;
	   	 var name = obj.data.xmmc;
	   	    layer.open({
			  type: 2, 
			  title: '数据展示',
			  area:areaWidth,
		/* 	  skin: 'layui-layer-molv' , */
			  background:"#518cc2",
			  icon: 6,
			  content: basePath+'business/cezhanchaxun/modalFrames.jsp?id='+id+"&name="+name,
			 
			});
	   	  });
	   	});
}
      
        
    </script>