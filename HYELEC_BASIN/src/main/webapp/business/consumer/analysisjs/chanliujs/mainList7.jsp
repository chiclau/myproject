<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div>
		<div id="step7tablerow" style="width:100%;height:400px;">
				<div style="width:100%;height:32px;">
						<label>站名:</label>
						<input type="text" id="step7_stnm" name="" value="" class="form-control" 
							readonly style="width: 80px;display:inline;">
						<input type="button" onclick="cl_excel7()" id="chanliu_daochu7" style="margin-left: 20px;" value="导出到excel"  class="btn btn-primary" >
						<input type="button" onclick="deleteChanliu()" id="chanliu_step7_delete" style="margin-left: 20px;" value="删除"  class="btn btn-primary" >
				</div>
				<div id="wdrs_div" style="height:calc(100% - 40px);">
					<table class="layui-hide" id="wdrs_tab" lay-filter="hszlb_table"></table>
				</div>
		</div>
	</div>
<script>
//初始化统计图				

$(function(){
	$("#step7_stnm").val(stnm);
	loadStep7Result();
})
//导出到Excel
function cl_excel7(){
	var h=hh;
	if(h==''){
		confirm("产流计算", "请选择一条计算结果")
		return false;
	}
	var url= basePath + "chanliu/chanliu!chanLiuExportExcel7.action?pch="+hh+"&stcd="+stcd +"&start="+beginDate+"&end="+endDate ;      
	confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出到excel","您确定要导出到excel吗？","icon-info", function(result) {
		   if(result){
			   window.location.href=url;
		   }
		});
}
function deleteChanliu(){
	var checkStatus = layui.table.checkStatus('chanliustep7table');
	if(checkStatus.data && checkStatus.data.length>0){
		layer.confirm("确定要删除选择洪水的计算结果数据吗?", { title: "删除确认" }, function (index) {
             	layer.close(index);
             	var url = basePath + "chanliu/chanliu!deleteAllDataResult.action";
				$.ajax({
					url:url,
					type:"post",
					data:{stcd:stcd,DATA:JSON.stringify(checkStatus.data)},
					dataType:"json",
					success:function(response){
						if(response.reflag==1||response.reflag=="1"){
							loadStep7Result();
						}else{
							layer.msg(response.message);
						}
					}
				});
        });
	}else{
		layer.msg("请选择要删除的洪水记录!");
	}
}
function loadStep7Result(){
	var url= basePath + "chanliu/chanliu!queryStep7ResultTableData.action";
	$.ajax({
	        url : url,
	        type : "post",
	        dataType : "json",
	        traditional: true,
	        data : {stcd:stcd,pch:hh},
	        success : function(response) {
	        	getwdrsTable("#wdrs_div","#wdrs_tab",response);
	        }
	  });
}
//测站列表数据表格
	function getwdrsTable(chart, tab, dataList){
	    var width = $(chart).width();
	    var height=(dataList.length+5)*30;
	    $(chart).css("height",height);
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            data:dataList,
	            width: width,
	            height:height,
	            id:'chanliustep7table',
	            limit:dataList.length,
	            cols: [
	                 [
	                 {
	                 	type:"checkbox",
	                 	rowspan:2
	                 },
	                 {
                	 colspan:13,
                	 title:'产流'},
                	 {
                	 colspan:7,
                	 title:'下渗'
                	 }],
	                [{
	                    field: 'PCH',
	                    title: '洪号',
	                    width:'130'
	                },{
	                    field: 'BTM',
	                    title: '开始时间',
	                    width:'150',
	                },{
	                    field: 'ETM',
	                    title: '结束时间',
	                    width:'150',
	                },{
	                    field: 'Q',
	                    title: 'Q',
	                    width:80
	                },{
	                    field: 'P',
	                    title: 'P',
	                    width:80
	                },{
	                    field: 'PA',
	                    title: 'Pa',
	                    width:80
	                },{
	                    field: 'E',
	                    title: 'E雨',
	                    edit: 'text',
	                    width:80
	                },{
	                    field: 'PAPE',
	                    title: 'Pa+P-E雨',
	                    width:100,
	                    templet:function(row){
	                    	var pa = row.PA!=null && row.PA.length>0?row.PA:0;
	                    	var e =row.E!=null && row.E.length>0?row.E:0;
	                    	var p = row.P!=null && row.P.length>0?row.P:0;
	                    	var v = parseFloat(pa)+parseFloat(p)-parseFloat(e);
	                    	return Number(v).toFixed(2);
	                    }
	                },{
	                    field: 'RS',
	                    title: 'R实',
	                    width:100
	                },{
	                    field: 'ERC',
	                    title: 'R查',
	                    width:100
	                },{
	                    field: 'SJR',
	                    title: '△R',
	                    width:100,
	                    templet:function(row){
	                    	var rs = row.RS!=null?row.RS:0;
	                    	var erc =row.ERC!=null?row.ERC:0;
	                    	var v = parseFloat(rs)-parseFloat(erc);
	                    	return Number(v).toFixed(2);
	                    }
	                },{
	                    field: 'SJRBRS',
	                    title: '△R/R实',
	                    width:120,
	                    templet:function(row){
	                    	var rs = row.RS!=null?row.RS:0;
	                    	var erc =row.ERC!=null?row.ERC:0;
	                    	var v = rs!=0?(parseFloat(rs)-parseFloat(erc))/parseFloat(rs):0;
	                    	v=v*100;
	                    	return Number(v).toFixed(2)+"%";
	                    }
	                },{
	                    field: 'CLSFHG',
	                    title: '合格否',
	                    width:80,
	                    templet:function(row){
	                    	var rs = row.RS!=null?row.RS:0;
	                    	var erc =row.ERC!=null?row.ERC:0;
	                    	var v = rs!=0?(parseFloat(rs)-parseFloat(erc))/parseFloat(rs):0;
	                    	v=v>0?v:-v;
	                    	return (parseFloat(v)<=0.2?"合格":"不合格");
	                    }
	                },{
	                    field: 'TC',
	                    title: 'tc(小时)',
	                    width:100
	                },{
	                    field: 'RC',
	                    title: 'Rc',
	                    width:100
	                },{
	                    field: 'RCTC',
	                    title: 'Rc/tc',
	                    width:100
	                },{
	                    field: 'FC',
	                    title: 'Fc',
	                    width:100
	                },{
	                    field: 'FCC',
	                    title: 'Fc查',
	                    edit: 'text',
	                    width:100
	                },{
	                    field: 'FCS',
	                    title: '(Fc-Fc查)/Fc',
	                    width:120
	                },{
	                    field: 'XSSFHG',
	                    title: '合格否',
	                    width:100,
	                    templet:function(row){
	                    	if(row.FCS!=null){
		                    	var v = parseFloat(row.FCS);
		                    	v=v>0?v:-v;
		                    	return (parseFloat(v)<=0.2?"合格":"不合格");
	                    	}else{
	                    		return "";
	                    	}
	                    }
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
<!-- 不要改变以下引用顺序 -->