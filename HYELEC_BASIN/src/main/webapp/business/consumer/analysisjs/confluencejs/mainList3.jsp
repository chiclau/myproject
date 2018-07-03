<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<style type="text/css">
</style>
</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid" id="cljs_mainList">
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>分析计算</li>
					<li style="color: black;">汇流计算</li>
				</ol>
			</h3>
		</div>
		<hr style="margin-top:-5px;">
						导入净雨值。表中Rt为产流计算稳定入渗fc步骤内的R改值，时间相应对应。
	<div id="mainList2-body" class="container-fluid">
		<div class="row" style="width:100%;height:300px;">
			<div id="wdrs_div" class="col-md-12" style="height:100%;">
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
				<table class="layui-hide" id="wdrs_tab" lay-filter="hszlb_table"></table>
			</div>
		</div>
		</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script type="text/javascript">
//初始化统计图				
//var myChart = echarts.init(document.getElementById('tsqx_chart'));

$(function(){
	getwdrsTable("#wdrs_div","#wdrs_tab","test_jls_tab.json");
//	getGxt("tsqx_chart","test_tsd3.json","");
})
//测站列表数据表格
	function getwdrsTable(chart, tab, url){
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
	              
	                    field: 'TM',
	                    title: '时间',
	                },{
	                    field: 'Rt',
	                    title: 'Rt',
	                    edit: 'text'
	                }]
	            ],
	            page: false
	        });
	    });
    }
    
    function getGxt(chart,url,data) {
        $.ajax({
            url : url,
            type : "get",
            dataType : "JSON",
            async : false,
            traditional: true,
            // data : data,
            success : function(response) {
                getChart(chart,response,data);
            }
        });
    }

  //生成统计图
    function  getChart(chart,response,data) {
    	var series = {};
    	var yAxis = {};
   		var myChart = echarts.init(document.getElementById('tsqx_chart'));
   		series = getSeries(response.DATA);
   		yAxis = getYAxis(response.DATA);
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
            
            xAxis : {
            	type: 'category',
      			splitLine: {show: false},
     			data: response.EXTRADATA.XDATA
            },
            yAxis: yAxis,
            series:  series
        };
        
        console.log("========");
        console.log(option);
        console.log("========");
    // 使用刚指定的配置项和数据显示图表。
       	myChart.clear();
       	myChart.setOption(option);
    }
  //单Y轴
    function getYAxis(){
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
    function getLegend_data(data) {
        var temp = new Array(data.length);
        for(var i = 0 ; i < data.length ; i < i++){
            temp[i] = data[i].NAME;
        }
        return temp;
    }
window.onresize = function () {
	$(".layui-border-box").width($("#wdrs_div").width() - 2);
};


//格式化时间
function getFormatDate(date) {
    var seperator1 = "/";
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
        + " " + hours + seperator2 + minut
        + seperator2 + seconds;
    return currentdate;
}
</script>
