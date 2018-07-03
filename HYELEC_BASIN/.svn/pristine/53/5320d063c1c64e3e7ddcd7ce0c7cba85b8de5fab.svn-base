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
推理峰量计算。需要输入参数Q净，R上均、tc均，流域面积F，河道，L, J参数，进行计算
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
			<button class="layui-btn" value="单站综合及误差统计">单站综合及误差统计</button><br>
				<table class="layui-hide" id="wctj_tab" lay-filter="hszlb_table"></table>
			</div>
		</div>
	
					
		</div>
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<script type="text/javascript">
$(function(){
	getwdrsTable("#wdrs_div","#wdrs_tab","test_jls_tab.json");
	getwctj_tab("#wdrs_div","#wctj_tab","test_jls_tab.json");
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
	            	[ //标题栏
	            	    {field: 'PCH', title: '洪号', width: 80, rowspan: 2} //rowspan即纵向跨越的单元格数
	            	    ,{field: 'QJ', title: '净峰流量Q净(m3/s) ', width: 180, rowspan: 2,edit:"text"}
	            	    ,{field: 'RS', title: 'R上 ', width: 80, rowspan: 2,edit:"text"}
	            	    ,{field: 'TCJ', title: 'tc均(小时)	 ', width: 80, rowspan: 2,edit:"text"}
	            	    ,{field: 'RSJ', title: 'R上均(mm) ', width: 80, rowspan: 2,edit:"text"}
	            	    ,{field: 'F', title: '流域面积F(km2) ', width: 80, rowspan: 2,edit:"text"}
	            	    ,{field: 'L', title: 'L ', width: 50, rowspan: 2,edit:"text",edit:"text"}
	            	    ,{field: 'J', title: 'J ', width: 50, rowspan: 2,edit:"text",edit:"text"}
	            	    ,{field: 'T', title: ' τ=(0.278FR上)/Qm ', width: 150, rowspan: 2,edit:"text"}
	            	   
	            	    ,{align: 'center', title: 'N=1.2', colspan: 4} //colspan即横跨的单元格数，这种情况下不用设置field和width
	            	  ], [
	            	    {field: 'K', title: 'K', width: 80}
	            	    ,{field: 'R', title: 'R^n上均/t^0.4c均', width: 120}
	            	    ,{field: 'QMK', title: 'QM/K', width: 80}
	            	    ,{field: 'M', title: '汇流参数m', width: 100}
	            	  ]
	            ],
	            page: false
	        });
	    });
    }
    //单站综合及误差统计table
	function getwctj_tab(chart, tab, url){
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
	            	[ //标题栏
	            	    {field: 'PCH', title: '洪号', rowspan: 2,align: 'center'} //rowspan即纵向跨越的单元格数
	            	    ,{field: 'RTC', title: 'R上/tc(毫米/小时) ',  rowspan: 2,align: 'center'}
	            	    ,{field: 'SUMM', title: 'm计 ',  rowspan: 2,align: 'center'}
	            	   
	            	   
	            	    ,{align: 'center', title: 'R上/tc ~m', colspan: 3} //colspan即横跨的单元格数，这种情况下不用设置field和width
	            	  ], [
	            	    {field: 'MC', title: 'm查', align: 'center'}
	            	    ,{field: 'MJJMC', title: '(m计-m查)/ m计',align: 'center' }
	            	    ,{field: 'SFHG', title: '是否合格', align: 'center'}
	            	  ]
	            ],
	            page: false
	        });
	    });
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
