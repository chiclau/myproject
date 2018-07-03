<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>资料管理</title>
</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<div class="row" style="width:100%;height:500px;">
			<div id="hszlb_div" class="col-md-6" style="height:100%;">
				<div id="hszlb_chart" style="width: 100%;height:100%;"></div>
			</div>
			<div id="tsqx_div" class="col-md-6" style="height:100%;">
				<div id="tsqx_chart" style="width: 100%;height:100%;"></div>
			</div>
			<div class="col-md-12" style="height:50px;">
				<button class="btn" onclick="saveLine2()">选择节点保存提交</button>
			</div>
			<div id="jls_div" class="col-md-12" style="height:100%;">
				<div style="width:460px;height:32px;float: right;">
							<label>站名:</label>
						<input type="text" name="" value="江永站" class="form-control" 
							readonly style="width: 80;display:inline;">
						<label>洪号:</label>
						<input type="text" class="form-control" value="20180501 "
							readonly style="width: 100;display:inline;">
						<label>流域面积(km²):</label>
						<input type="text" class="form-control" value="200"
							readonly style="width: 100;display:inline;">
					</div>
				<table class="layui-hide" id="jls_tab" lay-filter="hszlb_table"></table>
			</div>
		</div>
	</div>
</body>
<script>
var symbolSize = 20;
var newData = [];
var oldData = [];
var lineData = [];
var lineData2 = [];
var tempData = [];

$(function(){
	
	getHszlbGxt("hszlb_chart",basePath + "chanliu/chanliu!step2chart.action",{"stcd":stcd,"start":beginDate, "end":endDate} );
	getHszlbGxt("tsqx_chart",basePath + "chanliu/chanliu!getTsqx.action",{"stcd":stcd,"start":beginDate, "end":endDate} );
	//getJlsTable("#jls_div","#jls_tab","test_jls_tab.json");
})
//提交单场洪水数据
function saveLine2(){

    var json = {
   		"STCD" : stcd,
   		"BEGINDATE" : beginDate,
   		"ENDDATE" : endDate,
   		"INTERVAL" : interval,
   		"DATA" : [lineData2]
    }
	console.log(json);
    $.ajax({
        url : basePath + "chanliu/chanliu!saveLineFor3.action",
        type : "post",
        dataType : "json",
        async : false,
        traditional: true,
        data : json,
        success : function(response) {
        	getJlsTable("#jls_div","#jls_tab",basePath + "chanliu/chanliu!qiuHe.action?STCD="+stcd);
        }
    });
}

//测站列表数据表格
	function getJlsTable(chart, tab, url){
	    var height = $(chart).height();
	    var width = $(chart).width();
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            url: url,
	            height: height,
	            width: width,
	            id:'cz',
	            request: {pageName: 'pageIndex' //页码的参数名称，默认：page
	                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
	            },
	            response: {
	                statusName: 'CODE' //数据状态的字段名称，默认：code
	                ,
	                statusCode: 1 //成功的状态码，默认：0
	                ,
	                msgName: 'MESSAGE' //状态信息的字段名称，默认：msg
	                ,
	                countName: 'TOTALAMOUNT' //数据总数的字段名称，默认：count
	                ,
	                dataName: 'DATA'
	            } //数据列表的字段名称，默认：data} //如果无需自定义数据响应名称，可不加该参数
	            ,
	            cols: [
	                [{
	                    field: 'DATE',
	                    title: '日期',
	                },{
	                    field: 'SC',
	                    title: '时差',
	                },{
	                    field: 'LL',
	                    title: '流量',
	                },{
	                    field: 'QT',
	                    title: 'Q*T',
	                }]
	            ],
	            page: false
	        });
	    });
    }

//初始化统计图				
var myChart = echarts.init(document.getElementById('hszlb_chart'));
var myChart2 = echarts.init(document.getElementById('tsqx_chart'));

function getHszlbGxt(chart,url,data) {
    $.ajax({
        url : url,
        type : "post",
        dataType : "JSON",
        async : false,
        traditional: true,
        data : data,
        success : function(response) {
        	//初始化点击退水段数据添加到降雨流量统计图的坐标
        	if(chart == "hszlb_chart"){
        		for(var i = 0;i < response.DATA.length ;i++){
        			if(response.DATA[i].TYPE == "line"){
        				lineData = response.DATA[i].DATA;
        			}
        		}
        		tempData[0] = response.DATA[0].DATA[0];
        	}
            getChart(chart,response,data);
        }
    });
}

//生成统计图
function  getChart(chart,response,data) {
	var series = {};
	var yAxis = {};
	if(chart == "hszlb_chart"){
		var myChart = echarts.init(document.getElementById('hszlb_chart'));
		series = getSeriesOfTsqx(response.DATA);
		yAxis = getYAxis(response.DATA);
	}else if(chart == "tsqx_chart"){
		var myChart2 = echarts.init(document.getElementById('tsqx_chart'));
		series = getSeries(response.DATA);
		yAxis = getYAxisOf1Y();
	}
    var legend_data= getLegend_data(response.DATA);
    var title = response.EXTRADATA.CHARTTITLE;
    var start = response.EXTRADATA.START;
    var end = response.EXTRADATA.END;
// 指定图表的配置项和数据
    var option = {
        title : {
            text: title,//主标题
            //subtext: '2009-6-12 2:00 - 2009-10-18 8:00',//副标题
            x: 'center',//标题剧中
            padding:0
        },
        color:['#000000','#0000EE','#6699FF','#FF8833','#6666FF','#FF9966','#66CCFF','#FFCC66','#99CCFF','#FFCC99','#CCFFFF'],
        grid: {
            left: '3%',
            right: '4%',
            top:20,
            bottom: 30,
            containLabel: true
        },
        tooltip : {//鼠标悬浮提示信息
            trigger: 'axis',//显示横坐标信息
            axisPointer: {//xy提示轴线
                type: 'cross',
                label: {
                    backgroundColor: '#505765'
                }
            }
        },
        legend: {
            data:legend_data, //最上边的切换按钮
            //x: 'left'//按钮位置
            bottom:0
        },
        xAxis : [
            {
                splitLine:{show: false},//去除网格线
                type : 'time',
                min:start,
                max:end,
            },
            {
                splitLine:{show: false},//去除网格线
                type : 'time',
                axisLine: {
                    onZero: false,
                }
            }
        ],
        yAxis: yAxis,
        series:  series
    };
    if(chart == "tsqx_chart"){
    	option["xAxis"] = {
   		        type: 'category',
   		        splitLine: {show: false},
   		        data: response.EXTRADATA.XDATA
        };
    }
    
    console.log("========");
    console.log(option);
    console.log("========");
// 使用刚指定的配置项和数据显示图表。
    if(chart == "hszlb_chart"){
    	myChart.clear();
    	myChart.setOption(option);
    	myChart.setOption({
            graphic: echarts.util.map(newData, function (item, dataIndex) {
                return {
                    type: 'circle',
                    position: myChart.convertToPixel('grid', item),
                    shape: {
                        r: symbolSize / 2
                    },
                    invisible: true,
                    draggable: true,
                    ondrag: echarts.util.curry(onPointDragging, dataIndex),
                    onmousemove: echarts.util.curry(showTooltip, dataIndex),
                    onmouseout: echarts.util.curry(hideTooltip, dataIndex),
                    z: 100
                };
            })
        });
	}else if(chart == "tsqx_chart"){
		myChart2.clear();
		myChart2.setOption(option);
		myChart2.on('click', function (params) {
			newData = this.getOption().series[params.seriesIndex].data;
		    oldData = this.getOption().series[params.seriesIndex].data;
			
			var newValue = parseInt(tempData[0][1]);
			var oldValue = parseInt(oldData[0][1]);
			var diffValue = newValue - oldValue;
			for(var i = 0 ; i < newData.length ; i++){
				var newTempTime = lineData[i][0];
				var newTempValue = parseInt(oldData[i][1]) + diffValue;
				newData[i][0] = getFormatDate(new Date(lineData[i][0]));
				oldData[i][0] = getFormatDate(new Date(lineData[i][0]));
				newData[i][1] = newTempValue;
				oldData[i][1] = newTempValue;
			}
			getHszlbGxt("hszlb_chart",basePath + "chanliu/chanliu!step2chart.action",{"stcd":stcd,"start":beginDate, "end":endDate} );
		    //getHszlbGxt("hszlb_chart","testsyq_1-n.json","");
		});
	}

}
//窗口大小改变重新计算隐藏的拖拽节点的坐标
window.addEventListener('resize', function () {
    myChart.setOption({
        graphic: echarts.util.map(newData, function (item, dataIndex) {
            return {
                position: myChart.convertToPixel('grid', item)
            };
        })
    });
});

function showTooltip(dataIndex) {
    myChart.dispatchAction({
        type: 'showTip',
        seriesIndex: 0,
        dataIndex: dataIndex
    });
}

function hideTooltip(dataIndex) {
    myChart.dispatchAction({
        type: 'hideTip'
    });
    lineData2 = [];
    for(var i = 0 ; i < lineData.length ; i++){
    	if(new Date(lineData[i][0]).getTime() < new Date(newData[dataIndex][0]).getTime()){
    		lineData2[i] = lineData[i];
    	}
    }
    var lineLength = lineData2.length;
    for(var y = dataIndex,z=0 ; y < newData.length ; y++,z++){
    	lineData2[lineLength+z] = newData[y];
    }
	console.log(lineData2);    
	myChart.setOption({
        series: [{
            id: 'line2',
            data: lineData2
        }],
    });
    //鼠标移出当前节点重新计算隐藏可拖拽节点的坐标  避免其余坐标不变导致其余节点不可继续拖拽的问题
    myChart.setOption({
        graphic: echarts.util.map(newData, function (item, dataIndex) {
            return {
                position: myChart.convertToPixel('grid', item)
            };
        })
    });
}

function onPointDragging(dataIndex, dx, dy) {
	newData[dataIndex] = myChart.convertFromPixel('grid', this.position);
	
	//dataIndex  当前拖拽节点在数组中的索引   根据所拖拽的节点 对比拖拽前后数据的差值   做数据同步
    var newTime = new Date(newData[dataIndex][0]);
	var oldTime = new Date(oldData[dataIndex][0]);
	var newValue = parseInt(newData[dataIndex][1]);
	var oldValue = parseInt(oldData[dataIndex][1]);
	var diffTime = newTime - oldTime;
	var diffValue = newValue - oldValue;
	for(var i = 0 ; i < newData.length ; i++){
		var newTempTime = new Date(oldData[i][0]).getTime() + diffTime;
		var newTempValue = parseInt(oldData[i][1]) + diffValue;
		newData[i][0] = getFormatDate(new Date(newTempTime));
		newData[i][1] = newTempValue;
		oldData[i][0] = getFormatDate(new Date(newTempTime));
		oldData[i][1] = newTempValue;
	}
	//   更新echart  形成线拖拽的效果
    myChart.setOption({
        series: [{
            id: 'tsqx',
            data: newData
        }],
    });
}
//双Y轴
function getYAxis(data){
    var temp = new Array(data.length);
    for(var i = 0 ; i < data.length ; i < i++){
        if(data[i].TYPE == "line"){
            temp[i] = {
                "splitLine":{"show": false},//去除网格线
                "name": data[i].NAME,
                "type": "value",
                "max":data[i].MAX,
                "min":data[i].MIN
            }
        }else if(data[i].TYPE == "bar"){
            temp[i] = {
                "splitLine":{show: false},//去除网格线
                "name": data[i].NAME,
                "nameLocation": 'start',
                "type": 'value',
                "inverse": true,//反向坐标
                "max":data[i].MAX,
                "min":data[i].MIN
            }
        }

    }
    return temp;
}
//单Y轴
function getYAxisOf1Y(){
    var temp = {type: 'value'};
    return temp;
}
function getSeries(data){
    var temp = new Array();
    for(var i = 0 ; i<data.length ;i++){
        if(data[i].TYPE == "bar"){
            temp[i] = {
                "name":data[i].NAME,
                "type":data[i].TYPE,
                "yAxisIndex":1,
                "barWidth":10,
                "data":data[i].DATA
            };
        }else if(data[i].TYPE == "line"){
            temp[i] = {
                "name":data[i].NAME,
                "type":data[i].TYPE,
                "showSymbol": false,
                "smooth": true,
                "lineStyle": {
	                "normal":{
	                	"width": 1.5
	                }
                },
                "data":data[i].DATA
            };
        }

    }
    return temp;
}
function getSeriesOfTsqx(data){
    var temp = new Array();
    for(var i = 0 ; i<data.length ;i++){
        if(data[i].TYPE == "bar"){
            temp[i] = {
                "name":data[i].NAME,
                "type":data[i].TYPE,
                "yAxisIndex":1,
                "barWidth":10,
                "data":data[i].DATA
            };
        }else if(data[i].TYPE == "line"){
            temp[i] = {
                "name":data[i].NAME,
                "type":data[i].TYPE,
                "showSymbol": false,
                "smooth": true,
                "lineStyle": {
	                "normal":{
	                	"width": 1.5
	                }
                },
                "data":data[i].DATA
            };
        }

    }
    temp[data.length] = {
        	"id":"tsqx",
            "type": "line",
            "data": newData,
            
            "name":"退水曲线",
            "showSymbol": true,
            "smooth": true,
            "lineStyle": {
    	        "normal":{
    	        	"width": 1.5
    	        }
            }
        }
    temp[data.length+1] = {
        	"id":"line2",
            "type": "line",
            "data": lineData2,
            
            "name":"单场洪水",
            "showSymbol": true,
            "smooth": true,
            "lineStyle": {
    	        "normal":{
    	        	"width": 1.5
    	        }
            }
        }
    console.log("=======1656651561=========")
    console.log(temp)
    return temp;
}
function getLegend_data(data) {
    var temp = new Array(data.length);
    for(var i = 0 ; i < data.length ; i < i++){
        temp[i] = data[i].NAME;
    }
    return temp;
}
//eChart宽高自适应
var resizeWorldMapContainer = function () {
	document.getElementById('hszlb_chart').style.width = document.getElementById('hszlb_div').innerWidth+'px';
	document.getElementById('hszlb_chart').style.height = document.getElementById('hszlb_div').innerHeight+'px';
	document.getElementById('tsqx_chart').style.width = document.getElementById('tsqx_div').innerWidth+'px';
	document.getElementById('tsqx_chart').style.height = document.getElementById('tsqx_div').innerHeight+'px';
};

resizeWorldMapContainer();
window.onresize = function () {
	$(".layui-border-box").width($("#jls_div").width() - 2);
    //重置容器高宽
    resizeWorldMapContainer();
    myChart.resize();
    myChart2.resize();
};


//格式化时间
function getFormatDate(date) {
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if(month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if(strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var hours="";
    var minut="";
    var seconds="";
    if(date.getHours()<10){
        hours="0"+date.getHours();
    }else{
        hours=date.getHours();
    }

    if(date.getMinutes()<10){
        minut="0"+date.getMinutes();
    }else{
        minut=date.getMinutes();
    }

    if(date.getSeconds()<10){
        seconds="0"+date.getSeconds();
    }else{
        seconds=date.getSeconds();
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + hours + seperator2 + minut;
        /* + seperator2 + seconds; */
    console.log(currentdate);
    return currentdate;
}
</script>
</html>
<!-- 不要改变以下引用顺序 -->