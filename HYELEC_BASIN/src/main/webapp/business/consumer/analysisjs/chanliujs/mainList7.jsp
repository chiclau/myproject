<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>资料管理</title>
<style type="text/css">
</style>
</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid">
		<div class="row" style="width:100%;height:500px;">
			<div id="wdrs_div" class="col-md-12" style="height:100%;">
				<div style="width:320px;height:32px;float: right;">
						<label>站名:</label>
						<input type="text" name="" value="江永站" class="form-control" 
							readonly style="width: 80;display:inline;">
						<label>流域面积(km²):</label>
						<input type="text" class="form-control" value="200"
							readonly style="width: 100;display:inline;">
					</div>
				<table class="layui-hide" id="wdrs_tab" lay-filter="hszlb_table"></table>
			</div>
		</div>
	</div>
</body>
<script>
//初始化统计图				

$(function(){
	getwdrsTable("#wdrs_div","#wdrs_tab","test_jls_tab.json");
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
                	 colspan:6,
                	 title:'产流'},{
                	 colspan:6,
                	 title:'下渗'
                	 }],
	                [{
	                    field: 'DATE',
	                    title: '日期',
	                    width:'180',
	                },{
	                    field: 'XH',
	                    title: '序号',
	                },{
	                    field: 'P',
	                    title: 'P',
	                },{
	                    field: 'E',
	                    title: 'E雨',
	                    edit: 'text'
	                },{
	                    field: 'P-E',
	                    title: 'P-E雨',
	                },{
	                    field: '3P-E',
	                    title: '∑(P-E雨)',
	                },{
	                    field: 'Pa3PE',
	                    title: 'Pa+∑(P-E雨)',
	                },{
	                    field: '3RC',
	                    title: '∑R查',
	                },{
	                    field: 'RC',
	                    title: '时段R查',
	                },{
	                    field: 'RG',
	                    title: 'R改',
	                },{
	                    field: 'RGDX',
	                    title: 'R改大→小',
	                },{
	                    field: '3RG',
	                    title: '∑R改',
	                }]
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
</html>
<!-- 不要改变以下引用顺序 -->