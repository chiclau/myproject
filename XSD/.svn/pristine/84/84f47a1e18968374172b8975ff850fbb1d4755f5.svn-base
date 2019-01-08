<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String name = request.getParameter("name");//用request得到
	String bj = request.getParameter("bj");//用request得到
	String address = request.getParameter("address");//用request得到
%>
<!DOCTYPE html>
<html style="padding-top: 0px;">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/layui/css/layui.css">
<!-- zui css -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/zui/css/zui.css">
<!--bootstrap 样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/js/bootstrapValidator/css/bootstrapValidator.css">
<link rel="stylesheet" type="text/css" id="zui_theme_link_css"
	href="<%=basePath%>common/zui/css/zui-theme-green.css">
<!--自定义样式-->
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/inc/css.css">

<!-- jquery js -->
<script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=basePath%>common/echarts3.8/echarts.min.js"></script>
<script src="<%=basePath%>common/layui/layui.js"></script>
<style>
.col-md-6 {
	height: 340px;
}

.col-md-6:nth-child(2), .col-md-6:nth-child(4) {
	padding-left: 0px;
}

#item-one {
	color: white;
	background: #568fc3;
}

.list-group-item {
	padding: 5px 13px;
}

.table {
	margin-bottom: 0px;
}

.layui-table, .layui-table-view {
	margin: 0px;
}

.container-tablecss {
	height: 200px;
	overflow: auto;
	width: 101%;
}

.layui-table tr th {
	border: 0px solid red;
	background: #518cc2;
	color: white;
}

.layui-table td, .layui-table th {
	text-align: center;
}

.layui-table-tool {
	display: none;
}

.layui-layer-molv .layui-layer-title {
	background: #0e60aa;
}

.layui-layer-setwin .layui-layer-close1 {
	color: white;
}

.table-icon {
	float: right;
	padding: 0px 8px;
	border-radius: 5px;
}

.desplay {
	display: none;
}

.layui-table-body {
	height: 182px;
}

.classtableone thead tr th {
	text-align: center;
	border: none;
	background: #568fc3;
	color: white;
}

.classtabletwo tbody tr td {
	text-align: center;
}

.classtablethird tbody tr td {
	text-align: center;
	border: none;
	background: #568fc3;
	color: white;
}
</style>
</head>

<body>
	<div style="height: 300px; width: 100%;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6"
					style="margin-top: 8x; padding-left: 0px; height: 590px;">
					<ul class="list-group list-group-one">
						<li class="list-group-item" id="item-one"><i
							class="icon icon-area-chart" style="margin-right: 10px"></i><span
							id="dzsl_id"></span></li>
						<li class="list-group-item" id="mainfive" style="height: 550px">

						</li>
					</ul>
				</div>
				<div class="col-md-6"
					style="margin-top: 8x; padding-left: 0px; height: 590px;">
					<ul class="list-group list-group-one">
						<li class="list-group-item" id="item-one"><i
							class="icon icon-area-chart" style="margin-right: 10px"></i><span
							id="dzrl_id"></span></li>
						<li class="list-group-item" id="mainseven" style="height: 550px">

						</li>
					</ul>
				</div>
				<div class="col-md-12" style="margin-top: 8x;">
					<div class="layui-table-header">
						<table class="layui-table classtableone" lay-size="sm"
							style="margin: 0px;">
							<colgroup>
								<col width="80">
								<col width="80">
								<col width="150">
								<col width="200">
								<col width="200">
								<col width="200">
								<col width="200">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>序号</th>
									<th>地区名称</th>
									<th>总装机规模（万千瓦）/座数</th>
									<th>拆除装机规模（万千瓦）/座数</th>
									<th>废弃装机规模（万千瓦）/座数</th>
									<th>拟建装机规模（万千瓦）/座数</th>
									<th>在建装机规模（万千瓦）/座数</th>
									<th>运行装机规模（万千瓦）/座数</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="layui-table-body layui-table-main">
						<table class="layui-table classtabletwo" lay-size="sm"
							style="margin: 0px;">
							<colgroup>
								<col width="80">
								<col width="80">
								<col width="150">
								<col width="200">
								<col width="200">
								<col width="200">
								<col width="200">
								<col width="200">
								<col>
							</colgroup>
							<tbody id="table_qg_kffs_date_zj">
							</tbody>
						</table>
					</div>
					<table class="layui-table classtablethird" lay-size="sm"
						style="margin: 0px;">
						<colgroup>
							<col width="80">
							<col width="80">
							<col width="150">
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="200">
							<col width="200">
							<col>
						</colgroup>
						<tbody id="zj_table_qg_kffs_date_zj">
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</body>

<script type="text/javascript">
$(function(){
 basePath = '<%=basePath%>';
 name = '<%=name%>';
 address = '<%=address%>';
 bj = '<%=bj%>';
 initDzsl(name,bj,address);
 initDzzjrl(name,bj,address);
 initTable11('xs_jszt');
})
function initTable11(bj){
	$.ajax({
		url:basePath+"homePage/homePage!countTbzh.action",
		data:{"bj":bj,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows;
			var tr = "";
			var len = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			var zj_zj4 =0; var zj_dz4 =0; var zj_zj5 =0; var zj_dz5 =0; 
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				zj_zj +=row.rlz;zj_dz +=row.dzz;
				zj_zj1+=row.rl1;zj_dz1+=row.dz1;zj_zj2+=row.rl2;zj_dz2+=row.dz2
				zj_zj3+=row.rl3;zj_dz3+=row.dz3;zj_zj4+=row.rl4;zj_dz4+=row.dz4
				zj_zj5+=row.rl5;zj_dz5+=row.dz5;
				tr +="<tr>"
					+"<td>"+(len++)+"</td>"
					+"<td>"+row.name+"</td>"
					+"<td>"+(row.rlz/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dzz+"</td>"
					+"<td>"+(row.rl1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz1+"</td>"
					+"<td>"+(row.rl2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz2+"</td>"
					+"<td>"+(row.rl3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz3+"</td>"
					+"<td>"+(row.rl4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz4+"</td>"
					+"<td>"+(row.rl5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz5+"</td>" 
					+"</tr>"
			}
			
			$("#table_qg_kffs_date_zj").html("");
			$("#table_qg_kffs_date_zj").append(tr);
			var zj_tr = "<tr>"
				/* +"<td>"+(len++)+"</td>" */
				+"<td colspan = '1'style='width: 165px;'>总计</td>"
				+"<td>"+(zj_zj/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
				+"<td>"+(zj_zj1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
				+"<td>"+(zj_zj2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
				+"<td>"+(zj_zj3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
				+"<td>"+(zj_zj4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
				+"<td>"+(zj_zj5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
				+"</tr>";
			$("#zj_table_qg_kffs_date_zj").html("");
			$("#zj_table_qg_kffs_date_zj").append(zj_tr);
		}
	})
}

function initTable(name,bj,address){
	if(name == "总数(座)"){
		 name = "总数";
	}
  layui.use('table', function(){
   	  var table = layui.table;
   	  table.render({
   	    elem: '#test'
   	    ,url:'<%=basePath%>'+"homePage/homePage!list.action"
   	    ,toolbar: '#toolbarDemo'
   	    ,title: '用户数据表'
   	    ,where:{"name":name,"bj":bj,"address":address}
   	    ,cols: [[
   	      {field:'id', title:'序号', width:80, fixed: 'left',templet:'#id'}
   	      ,{field:'xmmc', title:'项目名称',fixed: 'left', width:250}
   	      ,{field:'xmwz', title:'项目位置', width:150}
   	      ,{field:'jthl', title:'所在河流（对应具体河流）', width:200}
   	      ,{field:'zjrl', title:'装机容量（千瓦）', width:150}
   	      ,{field:'ztz', title:'总投资（万元）', width:150}
   	      ,{field:'sjnfdl', title:'设计年发电量（万千瓦时）', width:150}
   	      ,{field:'jszt', title:'建设（运营）状态', width:150}
   	      ,{field:'tzxz', title:'投资性质/来源', width:150}
   	      ,{field:'bwqk', title:'并网情况', width:150}
   	      ,{field: 'kffs', title:'开发方式',  width:150}
   	      ,{field: 'sfyxmhz', title:'是否有项目核准（审批）', width:180}
   	    ]]
   	    ,page: true
   	  });
   	});
}

   
function initDzzjrl(name,bj,address){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('mainseven'));
	 if(bj == 0 || bj == 1 || bj == 2 || bj == 3){
		if(name == "总数(座)"){
			 $("#dzrl_id").text("各省小水电装机容量统计（总数）");
			 tcText = "各省小水电装机容量统计（总数）";
			 name = "总数";
		 }else{
			 $("#dzrl_id").text("各省小水电装机容量统计（"+name+"）");
			 tcText = "各省小水电装机容量统计（"+name+"）";
		 }
	 }
	 if(bj == 4 || bj == 5){
		 if(name == "总数(座)"){
			 $("#dzrl_id").text("各市小水电装机容量统计（总数）");
			 tcText = "各市小水电装机容量统计（总数）";
			 name = "总数";
		 }else{
			 $("#dzrl_id").text("各市小水电装机容量统计（"+name+"）");
			 tcText = "各市小水电装机容量统计（"+name+"）";
		 }
	 }
	 if(bj == 6){
		 $("#dzrl_id").text("各市小水电装机容量统计");
		 tcText = "各市小水电装机容量统计";
	 }
	 if(bj == 7 || bj == 8){
		 if(name == "总数(座)"){
			 $("#dzrl_id").text("各县小水电装机容量统计（总数）");
			 tcText = "各县小水电装机容量统计（总数）";
			 name = "总数";
		 }else{
			 $("#dzrl_id").text("各县小水电装机容量统计（"+name+"）");
			 tcText = "各县小水电装机容量统计（"+name+"）";
		 }
	 }
	 if(bj == 9){
		 $("#dzrl_id").text("各县小水电装机容量统计");
		 tcText = "各县小水电装机容量统计";
	 }
	 var len = []
	 var datas = []
	 var rest = []
	 var value = 0;
     $.ajax({
		url:basePath+"homePage/homePage!countDzzjrlByYxzt.action",
		data:{"bj":bj,"name":name,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				len.push(data[i].name);
				rest.push(data[i])
				value+=data[i].value;
			}
			datas.push({"value":value.toFixed(2), "name":'总数(万千瓦)', selected:true})
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: tcText,
		     		        x:'center'
		     		    },
		     		 tooltip: {
		     		        trigger: 'item',
		     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		     		    },
		     		    legend: {
		     		        orient: 'horizontal',
		     		        bottom: 5,
		     		        data:len
		     		    },
		     		   color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D'],
		     		    series: [
		     		    	 {
			     		            name:'总数',
			     		            type:'pie',
			     		            center:['50%','46.5%'],
			     		            selectedMode: 'single',
			     		            radius: [0, '32.5%'],
			     		            label: {
			     		            	show:true,
			     		                normal: {
			     		                    position: 'center',
			     		                    formatter: '{b}\n {c}',
			     		                    color:'white'
			     		                }
			     		            },
			     		            labelLine: {
			     		                normal: {
			     		                    show: false
			     		                }
			     		            },
			     		           data:datas
			     		           
			     		        },
		     		        {
		     		            name:'',
		     		            type:'pie',
		     		            radius: ['40%', '55%'],
		     		            label: {
		     		                normal: {
		     		                	show: true,
		     		                    formatter: '{b}: {c}({d}%)'
		     		                }
		     		            },
		     		            data:rest
		     		        }
		     		    ]
		     };
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
		}
	});
}
function initDzsl(name,bj,address){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('mainfive'));
	 if(bj == 0 || bj == 1 || bj == 2 || bj == 3){
		 if(name == "总数(座)"){
			 $("#dzsl_id").text("各省小电站详细表（总数）");
			 zjText = "各省小水电投产数量统计（总数）";
			 name = "总数"
		 }else{
			 $("#dzsl_id").text("各省小电站详细表（"+name+"）");
			 zjText = "各省小水电投产数量统计（"+name+"）";
		 }
	 }
	 if(bj == 4 || bj == 5 ){
		 if(name == "总数(座)"){
			 $("#dzsl_id").text("各市小电站详细表（总数）");
			 zjText = "各市小水电投产数量统计（总数）";
			 name = "总数"
		 }else{
			 $("#dzsl_id").text("各市小水电投产数量统计（"+name+"）");
			 zjText = "各市小水电投产数量统计（"+name+"）";
		 }
	 }
	 if(bj == 6){
		 $("#dzsl_id").text("各市小水电投产数量统计");
		 zjText = "各市小水电投产数量统计";
	 }
	 if(bj == 7 || bj == 8){
		 if(name == "总数(座)"){
			 $("#dzsl_id").text("各县小电站详细表（总数）");
			 zjText = "各县小水电投产数量统计（总数）";
			 name = "总数"
		 }else{
			 $("#dzsl_id").text("各县小水电投产数量统计（"+name+"）");
			 zjText = "各县小水电投产数量统计（"+name+"）";
		 }
	 }
	 if(bj == 9){
		 $("#dzsl_id").text("各县小水电投产数量统计");
		 zjText = "各县小水电投产数量统计";
	 }
	 var len = []
	 var datas = []
	 var rest = []
	 var value = 0;
     $.ajax({
		url:basePath+"homePage/homePage!countDzslByYxzt.action",
		data:{"bj":bj,"name":name,"address":address},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				len.push(data[i].name);
				rest.push(data[i])
				value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: zjText,
		     		      //  subtext: '纯属虚构',
		     		        x:'center'
		     		    },
		     		 tooltip: {
		     		        trigger: 'item',
		     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		     		    },
		     		    legend: {
		     		        orient: 'horizontal',
		     		        bottom: 5,
		     		        data:len
		     		    },
		     		   color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D'],
		     		    series: [
		     		    	 {
			     		            name:'总数',
			     		            type:'pie',
			     		            center:['50%','46.5%'],
			     		            selectedMode: 'single',
			     		            radius: [0, '32.5%'],
			     		            label: {
			     		            	show:true,
			     		                normal: {
			     		                    position: 'center',
			     		                    formatter: '{b}\n {c}',
			     		                    color:'white'
			     		                }
			     		            },
			     		            labelLine: {
			     		                normal: {
			     		                    show: false
			     		                }
			     		            },
			     		           data:datas
			     		           
			     		        },
		     		        {
		     		            name:'',
		     		            type:'pie',
		     		            radius: ['40%', '55%'],
		     		            label: {
		     		                normal: {
		     		                	show: true,
		     		                    formatter: '{b}: {c}({d}%)'
		     		                }
		     		            },
		     		            data:rest
		     		        }
		     		    ]
		     };
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
		}
	});
}
        


        
        
        
        
        
        
        
        

        
        
        

        
        
        
        
        
        
        
    </script>