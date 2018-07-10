<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<style type="text/css">
	.form-control{
		width: 200;
		display:inline"
	}
	.dropdown-menu{
		top:30px;
	}
	/* .container-fluid{
		padding:30px;
	} */
	.chart{
	    width: 100%;
	    height: 500px;
	}
	.Gxt{
	    width: 100%;
	    height: 500px;
	}
	.hszlb_title{
		width:100%;
		min-width:530px;
		height:90px;
	}
	.col-md-6{
		padding:20px;
		padding-top:0px;
	}
	.layui-border-box{
		margin-top:0px;
	}
	.row {
    margin-right: 0px;
    margin-left: -10px;
	}
</style>
</head>
<body style="background-color: #FCFCFC;">
	<div id="mainList2-body" class="container-fluid">
		<!-- 	<h1 style="text-align: center;">产流计算</h1> -->
		<div class="hszlb_title">
			<form id="" name="" method="post">
					<label>站名:</label>
					<input type="text" id="stnm" name="mStbprpFormBean.mStbprpInfoBean.stnm" value="" class="form-control" 
						readonly style="width: 200;display:inline;">
					<input type="hidden" id="stcd" name="stcd">
					<label>请选择开始时间:</label>
					<input id="start" type="text" class="form-control date form_datetime"
						size="16" readonly style="width: 200;display:inline;margin:0px">
					<label>请选择截至时间:</label>
					<input id="end" type="text" class="form-control date form_datetime"
						size="16" readonly style="width: 200;display:inline;margin:0px">
					<label>时间间隔:</label>
					<input id="jiange" type="text" class="form-control" value="60"
						style="width: 200;display:inline;margin:0px">
					<input type="button" onclick="search()" name="" value="查询" class="btn">
			</form>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div id="hszlb_div" class="chart">
					<div style="width:516px;height:32px;float: right;">
							<label>站名:</label>
						<input id="TAB_STNM" type="text" name="" class="form-control" 
							readonly style="width: 80;display:inline;">
						<label>洪号:</label>
						<input id="TAB_PCH" type="text" class="form-control"
							style="width: 100;display:inline;">
						<label>流域面积(km²):</label>
						<input id="TAB_LYMJ" type="text" class="form-control"
							style="width: 100;display:inline;">
						<input type="button" onclick="saveTabTitle();" value="保存" class="btn">
					</div>
					<table class="layui-hide" id="hszlb_tab" lay-filter="hszlb_table"></table>
				</div>
			</div>
			<div class="col-md-6" style="margin-top:-20px">
				<div id="hszlb_chart_div" class="Gxt">
					<div id="hszlb_chart" style="width: 100%;height:100%;highlight-color:#000000;margin-top: 20px"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script type="text/javascript">
$(function(){
	//stnm stcd start end jiange
	$("#stnm").val(stnm);
	$("#stcd").val(stcd);
	$("#start").val(beginDate);
	$("#end").val(endDate);
	$("#jiange").val(interval);
	//测站列表数据表格
	getHszlbChart("#hszlb_div", "#hszlb_tab",basePath + "chanliu/chanliu!step2.action",{"stcd":stcd,"start":beginDate, "end":endDate});
	
	getHszlbGxt("hszlb_chart",basePath + "chanliu/chanliu!step2chart.action",{"stcd":stcd,"start":beginDate, "end":endDate} );
	//getHszlbGxt("hszlb_chart",basePath+"/business/consumer/analysisjs/chanliujs/testsyq_1-n.json",{"stcd":stcd,"start":beginDate, "end":endDate} );
})
	$(".form_datetime").datetimepicker({
		format : " yyyy-mm-dd hh:ii:ss"
	});
	$(".form-datetime").datetimepicker({
		format : " yyyy-mm-dd hh:ii:ss"
	});
	function search(){
		getHszlbChart("#hszlb_div", "#hszlb_tab",basePath + "chanliu/chanliu!step2.action",{"stcd":$("#stcd").val(),"start":$("#start").val(), "end":$("#end").val(),"interval":$("#jiange").val()});
		
		getHszlbGxt("hszlb_chart",basePath + "chanliu/chanliu!step2chart.action",{"stcd":$("#stcd").val(),"start":$("#start").val(), "end":$("#end").val(),"interval":$("#jiange").val()} );
	}
	//测站列表数据表格
	function getHszlbChart(chart, tab, url ,data) {
	    var height = $(chart).height();
	    var width = $(chart).width();
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            url: url,
	            where: data,
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
	                    field: 'JYL',
	                    title: '降雨量',
	                    edit: 'text'
	                },{
	                    field: 'LL',
	                    title: '流量',
	                    edit: 'text'
	                }]
	            ],
	            limit:5,
	            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档 'limit', 'count', 'prev', 'page', 'next', 'skip'
	                layout: ['prev', 'page', 'next', 'skip'] //自定义分页布局
	                //,curr: 5 //设定初始在第 5 页
	                ,
	                groups: 3 //只显示 1 个连续页码
	                ,
	                first: false //不显示首页
	                ,
	                last: false //不显示尾页
	            },
	            done: function(res, curr, count){
	                //如果是异步请求数据方式，res即为你接口返回的信息。
	                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
	                $("#TAB_STNM").val(res.EXTRADATA[0].STNM);
	                $("#TAB_PCH").val(res.EXTRADATA[0].PCH);
	                $("#TAB_LYMJ").val(res.EXTRADATA[0].LYMJ);
	                //得到当前页码
	                console.log(curr); 
	                
	                //得到数据总量
	                console.log(count);
	              }
	        });
	    });
	    }
	    
	    function saveTabTitle(){
	    	var json={
    			"STNM":$("#TAB_STNM").val(),
    			"PCH":$("#TAB_PCH").val(),
    			"LYMJ":$("#TAB_LYMJ").val()
	    	}
	    	console.log(json);
	    }
	    function getHszlbGxt(chart,url,data) {
	    	/* common_ajax("", url, function(response) {
        		alert(1);
        		console.log(response);
                getChart(chart,response);
        	}); */
	    	
	        $.ajax({
	            url : url,
	            type : "post",
	            dataType : "json",
	            async : false,
	            traditional: true,
	            data : data,
	            success : function(response) {
	            	console.log("=========");
	            	console.log(response);
	            	console.log("=========");
	                getChart(chart,response);
	            }
	        });
	    }
	    var myChart = echarts.init(document.getElementById('hszlb_chart'));
	    function  getChart(chart,response) {
	    	myChart = echarts.init(document.getElementById('hszlb_chart'));
    		var series = getSeries(response.DATA);
		    var yAxis = getYAxis(response.DATA);
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
		        dataZoom: [//缩放
		            /*{
		                show: true,//最下边控制轴
		                start: 0,//统计图初始化缩放开始方位百分比
		                end: 100//统计图初始化缩放结束百分百
		            },*/
		            {
		                type: 'inside',//统计图缩放
		                start: 0,//统计图初始化缩放开始方位百分比
		                end: 100//统计图初始化缩放结束百分百
		            }
		        ],
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
		        series: series
		    };
			console.log(myChart);
		// 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		}
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
	    function getSeries(data){
	        var temp = new Array(data.length);
	        for(var i = 0 ; i<data.length ;i++){
	            if(data[i].TYPE == "bar"){
	                temp[i] = {
	                    "name":data[i].NAME,
	                    "type":data[i].TYPE,
	                    "yAxisIndex":1,
	                    //"barWidth":10,
	                    "data":data[i].DATA
	                };
	            }else if(data[i].TYPE == "line"){
	                temp[i] = {
	                    "name":data[i].NAME,
	                    "type":data[i].TYPE,
	                    "showSymbol": false,
	                    "lineStyle": {
	                    "normal":
	                    {
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
	  //eChart宽高自适应
	    var resizeWorldMapContainer = function () {
	    	document.getElementById('hszlb_chart').style.width = document.getElementById('hszlb_chart_div').innerWidth+'px';
	    	document.getElementById('hszlb_chart').style.height = document.getElementById('hszlb_chart_div').innerHeight+'px';
	    };

	    resizeWorldMapContainer();
	    
	    layui.use('table', function(){
    	  var table = layui.table;
    	  
    	  //监听单元格编辑
    	  table.on('edit(hszlb_table)', function(obj){
    	    var value = obj.value //得到修改后的值
    	    ,data = obj.data //得到所在行所有键值
    	    ,time = obj.time //得到所在行所有键值
    	    ,field = obj.field //得到字段
    	    var json = {
   	    		"STCD" : stcd,
   	    		"DATE" : data.DATE,
    	    }
    	    json[field] = value;
    	    console.log(json);
    	    
    	    $.ajax({
	            url : basePath + "chanliu/chanliu!updateData.action",
	            type : "post",
	            dataType : "json",
	            async : false,
	            traditional: true,
	            data : json,
	            success : function(response) {
	            	getHszlbGxt("hszlb_chart",basePath + "chanliu/chanliu!step2chart.action",{"stcd":stcd,"start":beginDate, "end":endDate} );
	            	console.log("=========");
	            	console.log(response);
	            	console.log("=========");
	            }
	        });
    	    
    	    layer.msg('[ID: '+ data.DATE +'] ' + field + ' 字段更改为：'+ value);
    	  });
    	});
	    window.onresize = function() {
	    	$(".layui-border-box").width($(".chart").width() - 2);
	    	//重置容器高宽
	        resizeWorldMapContainer();
	        myChart.resize();
	    }
</script>
