<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html style="padding-top:0px;">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/layui/css/layui.css">
<style>
      .col-md-6{
       height:340px;
      }
      .col-md-6:nth-child(2), .col-md-6:nth-child(4){
       padding-left:0px;
      }
      #item-one{
       color:white;
       border-top-left-radius:5px;
	   border-top-right-radius:5px; 
       background: #568fc3;
      }
      .layui-layer-molv .layui-layer-title{
       background:#0e60aa;
      }
      .layui-layer-setwin .layui-layer-close1{
       color:white;
      }
</style>
</head>

<body>
<div style="height:300px;width:100%;">
	 <div class="container-fluid">
          <div class="row">
              <div class="col-md-6" style="margin-top:2px;">
                 <ul class="list-group list-group-one">
					  <li class="list-group-item" id="item-one">
					   		<i class="icon icon-area-chart" style="margin-right:10px"></i>（全国）小水电建设状态统计图
					  </li>
					  <li class="list-group-item" id="mainone" style="height:300px">
					   		
					  </li>
				 </ul>
              </div>
              <div class="col-md-6" style="margin-top:-8px;">
                   <ul class="list-group list-group-one">
					  <li class="list-group-item" id="item-one">
					   		<i class="icon icon-area-chart" style="margin-right:10px"></i>（全国）小水电开发方式统计图
					  </li>
					  <li class="list-group-item" id="maintwo" style="height:300px">
					   		
					  </li>
				 </ul>
              </div>
              <div class="col-md-6">
                  <ul class="list-group list-group-one">
					  <li class="list-group-item" id="item-one">
					   		<i class="icon icon-area-chart" style="margin-right:10px"></i>（全国）各年小水电投产累计数量统计图
					  </li>
					  <li class="list-group-item" id="mainthree" style="height:300px">
					   		
					  </li>
				 </ul>
              </div>
              <div class="col-md-6">
                 <ul class="list-group list-group-one">
					  <li class="list-group-item" id="item-one">
					   		<i class="icon icon-area-chart" style="margin-right:10px"></i>（全国）各年小水电投产累计装机容量统计图
					  </li>
					  <li class="list-group-item" id="mainfore" style="height:300px">
					   		
					  </li>
				 </ul>
              </div>
            </div>
        </div>
        
</div>
</body>
  <script type="text/javascript">
$(function(){
 initKffs(${param.bj});
 initJszt(${param.bj});
 initDzsl(${param.bj});
 initZjrl(${param.bj});
})
function initKffs(bj){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('maintwo'));
    var kffsLeng = [];
    var kffs = [];
    var datas = [];
    var value = 0;
    if(bj == null){
		bj = 0;
	}
     $.ajax({
		url:basePath+"homePage/homePage!countKffs.action",
		data:{"bj":bj},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				kffsLeng.push(data[i].name);
				kffs.push(data[i])
				value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: '（全国）小水电开发方式统计图',
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
		     		        data:kffsLeng
		     		    },
		     		     color:['#7991d4','#dfd75d','#d88577','#FFBB00 '],
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
		     		            data:kffs
		     		        }
		     		    ]
		     };
			myChart.on('click', function (params) {
			//	alert(params.name);
			});
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
		}
	});
}
        
/////////////////////////////////////////////////建设状态统计////////////////////////////////////////////////////////////////
        // 基于准备好的dom，初始化echarts实例
function initJszt(bj){
	 var myChart = echarts.init(document.getElementById('mainone'));
	 if(bj == null){
			bj = 0;
		}
     var leng = [];
     var jszt = [];
     var datas = [];
     var value = 0;
     $.ajax({
 		url:basePath+"homePage/homePage!countJszt.action",
 		data:{"bj":bj},
 		type: "POST",
 		dataType:"json",
 		success:function(response){
 			var data = response.rows
 			for (var i = 0; i < data.length; i++) {
					leng.push(data[i].name);
					jszt.push(data[i])
					value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
				     // 指定图表的配置项和数据
				     var option = {
				     		 title : {
				  		        text: '（全国）小水电建设状态统计图',
				  		        x:'center'
				  		    },
				     		 tooltip: {
				     		        trigger: 'item',
				     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
				     		    },
				     		    legend: {
				     		        orient: 'horizontal',
				     		        bottom: 5,
				     		        data:leng
				     		    },
				     		    color:['#7991d4','#dfd75d','#d88577'],
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
				     		            data:jszt
				     		        }
				     		    ]
				     };
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '浙江省小水电数量，规模，容量统计',
					  area: ['1200px', '600px'],
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: '<%=basePath%>business/cezhanchaxun/modalFrame.jsp?name='+name,
					});
			});
				     // 使用刚指定的配置项和数据显示图表。
				     myChart.setOption(option);
 		}
 	});
}
        
        
        
        
        
        
        
        
        
///////////////////////////////////////////////装机容量折线图/////////////////////////////////////////////////////        
function initZjrl(bj){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('mainfore'));
    if(bj == null){
		bj = 0;
	}
    var sum = [];
    var sum1 = [];
    var yer = [];
    $.ajax({
		url:basePath+"homePage/homePage!countZjrl.action",
		data:{"bj":bj},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				if(i < 7){
					sum.push(data[i].sun);
					yer.push(data[i].yer);
				}else{
					sum1.push(data[i].sun);
				}
			}
			var colors = ['#5793f3', '#d14a61', '#675bba','#333'];
			option = {
			    color: colors,
			    title : {
			    	text:'（全国）各年小水电投产累计装机容量统计图',
     		        x:'center'
     		    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    grid: {
			        right: '20%'
			    },
			    toolbox: {
			        feature: {
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    legend: {
			    	 bottom: 5,
			        data:['累计装机容量','增量装机容量']
			    },
			    xAxis: [
			        {
			            type: 'category',
			            axisTick: {
			                alignWithLabel: true
			            },
			            data:yer
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '累计装机容量(千瓦)',
			            min: 0,
			            position: 'right',
			            axisLine: {
			                lineStyle: {
			                    color: colors[3]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        { },
			        {
			            type: 'value',
			            name: '增量装机容量(千瓦)',
			            min: 0,
			            position: 'left',
			            axisLine: {
			                lineStyle: {
			                    color: colors[3]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series: [
			        {
			            name:'累计装机容量',
			            type:'bar',
			            data:sum1
			        },
			        {
			            name:'增量装机容量',
			            type:'line',
			            yAxisIndex: 2,
			            data:sum
			        }
			    ]
			};

		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		}
	});
}
        
        
        
////////////////////////////////////电站数量统计////////////////////////////////////////////      
    // 基于准备好的dom，初始化echarts实例
function initDzsl(bj){
    var myChart = echarts.init(document.getElementById('mainthree'));
    if(bj == null){
		bj = 0;
	}
    var sum3 = [];
    var sum4 = [];
    var yer4 = [];
    $.ajax({
		url:basePath+"homePage/homePage!countDz.action",
		data:{"bj":bj},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				if(i < 7){
					sum3.push(data[i].sun);
					yer4.push(data[i].yer);
				}else{
					sum4.push(data[i].sun);
				}
			}
			var colors = ['#5793f3', '#d14a61', '#675bba','#333'];

			option = {
			    color: colors,
			    title : {
			    text:'（全国）各年小水电投产累计数量统计图',
     		        x:'center'
     		    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    grid: {
			        right: '20%'
			    },
			    toolbox: {
			        feature: {
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    legend: {
			    	 bottom: 5,
			        data:['增量投产数量','累计投产数量']
			    },
			    xAxis: [
			        {
			            type: 'category',
			            axisTick: {
			                alignWithLabel: true
			            },
			            data: yer4
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '增量投产数量(座)',
			            min: 0,
			            position: 'right',
			           // splitLine:{show: false},//去除网格线
			            axisLine: {
			                lineStyle: {
			                	 color: colors[3]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        { },
			        {
			            type: 'value',
			            name: '累计投产数量(座)',
			            //splitLine:{show: false},//去除网格线
			            min: 0,
			            position: 'left',
			            axisLine: {
			                lineStyle: {
			                	 color: colors[3]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series: [
			        {
			            name:'累计投产数量',
			            type:'bar',
			            data:sum4
			        },
			        {
			            name:'增量投产数量',
			            type:'line',
			            yAxisIndex: 2,
			            data:sum3
			        }
			    ]
			};
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		}
	});
}
        
        
        
        
        
        
        
    </script>