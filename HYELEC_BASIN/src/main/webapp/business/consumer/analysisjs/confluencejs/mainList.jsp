<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
        .div_items {  
            position: relative;  
            width: 100%;  
            height: 200px;  
            border: 1px solid #66afe9;  
            border-top: 0px;  
            overflow: auto;  
            display: none;  
        }  
        .div_item {  
            width: 100%;  
            height: 20px;  
            margin-top: 1px;  
            font-size: 13px;  
            line-height: 20px;  
        }  
#stnm{
 width:200px;
}
#base{
 margin-bottom:10px;
}
.date{
 margin-top:0px;
}
/* #table1{
 width:1124px;
} */
.addTd2{
  width:172px;
}
.datetimepicker{
 margin-top:30px;margin-left:-7px;
}
</style>
	<div id="hljs_mainList_step1">
		<div style="width:100%;height:400px;">
			<div style="height:40px;width:60%;">
				<div style="padding-left:30px;float:left;width:40%;line-height:40px;">1、测站地表径流过程计算数据统计表</div>
				<div style="width:60%;float: right;line-height:30px;">
					<label>站名:</label>
					<input id="huiliujisuan_step1_stcd" class="form-control" style="width: 150px;display:inline;"></input>
					<label>洪号:</label>
					<select id="huiliujisuan_step1_pch" class="form-control" onchange="changeStep1Pch()" style="width: 150px;display:inline;"></select>
					<button class="layui-btn" style="height:35px;" onclick="queryHuiLiuStep1Table1()" value="查询">查询</button>
				</div>
			</div>
			<div id="wdrs_div" style="height:calc(100% - 40px);width:60%;">
				<table class="layui-hide" id="wdrs_tab" lay-filter="hszlb_table"></table>
			</div>
		</div>
		<div style="width:100%;height:200px;margin-top:20px;">
			<div style="height:40px;">
				<div style="width:50%;float:left;padding-left:30px;line-height:40px;">2.推理公式计算,汇流参数m计算表</div>
				<div style="width:50%;float:right;">
					<input type="hidden" id="huiliujisuan_hjr" value="">
					<label>tc:</label><input type="text" id="huiliujisuan_tc" class="form-control" style="display:inline;width:100px;">
					<button class="layui-btn" onclick="calcMvalue()" value="计算">计算</button>
					<button class="layui-btn" onclick="deleteStepResult()" value="删除">删除</button>
						<input type="button" onclick="hl_excel1()" id="huiliu_export1" style="margin-left: 20px;" value="导出到excel"  class="layui-btn" >
				</div>
			</div>
			<div id="tlgs_div" style="height:calc(100% - 40px);width:100%;">
				<table class="layui-hide" id="tlgs_tab" lay-filter="hszlb_table"></table>
			</div>
		</div>
		<div style="width:100%;height:200px;margin-top:20px;">
			<div style="height:40px;padding-left:30px;line-height:40px;">3.推理公式计算,汇流参数求和计算表
				<input type="button" onclick="hl_excel2()" id="huiliu_export2" style="margin-left: 20px;" value="导出到excel"  class="layui-btn" >
			</div>
			<div id="qhjs_div" style="height:calc(100% - 40px);width:100%;">
				<table class="layui-hide" id="qhjs_tab" lay-filter="hszlb_table"></table>
			</div>
		</div>
		<div style="width:100%;height:400px;margin-top:20px;">
			<div style="height:40px;">
				<div style="width:50%;float:left;padding-left:30px;line-height:40px;">4.推理公式计算,单站综合及误差统计</div>
				<div style="width:50%;float:right;">
					<input type="button" onclick="hl_excel3()" id="huiliu_export3" style="margin-left: 20px;" value="导出到excel"  class="layui-btn" >
					<button class="layui-btn" value="单站综合及误差统计" onclick="cezhnZhcx()">单站综合及误差统计</button><br>
				</div>
			</div>
			<div style="height:calc(100% - 40px);width:100%;">
				<div id="wctj_div" style="width:50%;height:100%;float:left;">
					<table class="layui-hide" id="wctj_tab" lay-filter="hszlb_table"></table>
				</div>
				<div id="wctj_echart" style="width:50%;height:100%;float:right;">
					<div id="huiliu_step1_echarts" style="height:100%;"></div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
var submitDataForm={};
$(function(){
	initHuiliuStep1StcdCombox();
})
	//导出到excel
	function hl_excel1(){
		var qjpch=$("#huiliujisuan_step1_pch").val();
		if(qjpch==''||qjpch==null){
			confirm("汇流计算", "请选择一条记录")
			return false;
		}
		var url= basePath + "huiliu/huiliu!huiliuExcel1.action?huiliuFormBean.stcd="+qjstcd+"&huiliuFormBean.pch="+qjpch;
		confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出到excel","您确定要导出到excel吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url;
			   }
			}); 
	}
function deleteStepResult(){
	var stcd = $("#huiliujisuan_step1_stcd").combox("getValue");
	var pch = $("#huiliujisuan_step1_pch").val();
	var url= basePath + "huiliu/huiliu!deleteStep1Result.action";
	layer.confirm('确定要删除以前计算结果吗？', {
			  btn: ['确定','取消'] //按钮
	}, function(index){
		layer.close(index);
		$.ajax({
			url:url,
			type:"post",
			data:{"stcd":stcd,"pch":pch},
			dataType:"json",
			success:function(response){
				if(response.reflag==1||response.reflag=="1"){
					queryHuiLiuStep1Table1();
				}else{
					layer.msg(response.message);
				}
			}
		});
	}, function(index){
		layer.close(index);
	});
}
function hl_excel2(){
		var qjpch=$("#huiliujisuan_step1_pch").val();
		if(qjpch==''||qjpch==null){
			confirm("汇流计算", "请选择一条记录")
			return false;
		}
		var url= basePath + "huiliu/huiliu!huiliuExcel2.action?huiliuFormBean.stcd="+qjstcd+"&huiliuFormBean.pch="+qjpch;
		confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出到excel","您确定要导出到excel吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url;
			   }
			}); 
	}
function hl_excel3(){
	var stcd = $("#huiliujisuan_step1_stcd").combox("getValue");
	var qjpch=$("#huiliujisuan_step1_pch").val();
	if(qjpch==''||qjpch==null){
		confirm("汇流计算", "请选择一条记录")
		return false;
	}
	var url= basePath + "huiliu/huiliu!huiliuExcel3.action?STCD="+stcd;
	confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出到excel","您确定要导出到excel吗？","icon-info", function(result) {
		   if(result){
			   window.location.href=url;
		   }
		}); 
}
function changeStep1Pch(){
	qjpch=$("#huiliujisuan_step1_pch").val();
}
function initHuiliuStep1StcdCombox(){
	$("#huiliujisuan_step1_stcd").combox({
		url:basePath + "huiliu/huiliu!huiliuStep2StcdSelect.action",
		label:"STNM",
		value:"STCD",
		dataKey:"dataList",
		height:'250px',
		width:'150px',
		isPager:false,
		select:function(item){
			qjstcd=item.STCD;
			qjstnm=item.STNM;
			loadHuiliuStep1PchSelect();
		}
	});
	if(qjstcd!=null && $.trim(qjstcd).length>0 && qjstnm!=null && $.trim(qjstnm).length>0){
		$("#huiliujisuan_step1_stcd").combox("setValue",{label:qjstnm,value:qjstcd});
		loadHuiliuStep1PchSelect();
	}
}
function loadHuiliuStep1PchSelect(){
	var stcd = $("#huiliujisuan_step1_stcd").combox("getValue");
	var url= basePath + "huiliu/huiliu!huiliuStep2PchSelect.action";
	$.ajax({
		url:url,
		type:"post",
		data:{"stcd":stcd},
		dataType:"json",
		success:function(response){
			var pchList = response.dataList;
			$("#huiliujisuan_step1_pch").empty();
			if(pchList !=null && pchList.length>0){
				for (var i = 0; i < pchList.length; i++) {
					if(i==0 && (qjpch==null || $.trim(qjpch).length<1)){
						qjpch=pchList[i].PCH;
					}
					$("#huiliujisuan_step1_pch").append("<option value='"+pchList[i].PCH+"' > "+pchList[i].PCH+"</option>");
				}
				$("#huiliujisuan_step1_pch").val(qjpch);
				if(qjpch!=null && $.trim(qjpch).length>0){
					queryHuiLiuStep1Table1();
				}
			}else{
				$("#huiliujisuan_step1_pch").append("<option value='' selected='selected' > 请选择  </option>")
			}
		}
	});
}
//单站综合查询
function cezhnZhcx(){
	var stcd = $("#huiliujisuan_step1_stcd").combox("getValue");
	var url= basePath + "huiliu/huiliu!huiliuStep1Search.action?STCD="+stcd;
	common_ajax(null, url, function(response) {
			if(response.reflag==1||response.reflag=="1"){
				var mjList = response.mjDataList;
				var mcList = response.mcDataList;
				var cxList = response.cxDataList;
				getwctj_tab("#wctj_div","#wctj_tab",cxList);
				var dataOne = [];
				var dataTwo = [];
				for(var i=0;i<mjList.length;i++){
					dataOne.push([mjList[i].m,mjList[i].ertc]);
				}
				for(var j=0;j<mcList.length;j++){
					dataTwo.push([mcList[j].m,mcList[j].ertc]);
				}
				loadZhcxEchart(dataOne,dataTwo);
			}	
	});
}
function loadZhcxEchart(dataOne,dataTwo){
	var myChart = echarts.getInstanceByDom(document.getElementById('huiliu_step1_echarts'));
	if(myChart){
		myChart.dispose();
	}
	myChart = echarts.init(document.getElementById('huiliu_step1_echarts'));
	option = {
	    title : {
	        text: '散点分部趋势图'
	    },
	    legend: {
	        data:['m计散点','趋势图']
	    },
	    xAxis : [
	        {
	            type : 'value',
	            name:'mm',
	            scale:true,
	            axisLabel : {
	                formatter: '{value}'
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            name:'mm/h',
	            scale:true,
	            axisLabel : {
	                formatter: '{value}'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'m计',
	            type:'scatter',
	            data: dataOne,
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            }
	        },
	        {
	            name:'趋势图',
	            type:'line',
	            data: dataTwo,
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            }
	        }
	    ]
	};
	myChart.setOption(option);	
}
//计算M值
function calcMvalue(){
	var tcvalue=$("#huiliujisuan_tc").val();
	if(tcvalue==null || $.trim(tcvalue).length<1){
		layer.msg("tc值不能为空!");
		return ;
	}
	if(submitDataForm.table2Data==null){
		layer.msg("表格2中无数据!");
		return ;
	}
	if(submitDataForm.table2Data.LLMJ==null ||$.trim(submitDataForm.table2Data.LLMJ).length<1){
		layer.msg("流域面积不能为空!");
		return ;
	}
	if(submitDataForm.table2Data.L==null ||$.trim(submitDataForm.table2Data.L).length<1){
		layer.msg("L不能为空!");
		return ;
	}
	if(submitDataForm.table2Data.J==null ||$.trim(submitDataForm.table2Data.J).length<1){
		layer.msg("J不能为空!");
		return ;
	}
	//
		if(submitDataForm.requestData!=null && submitDataForm.requestData.length>0){
			for(var i=0;i<submitDataForm.requestData.length;i++){
				if(submitDataForm.requestData[i].Rt==null || submitDataForm.requestData[i].Rt==''){
					layer.alert("调用接口的参数中Rt值不能为空!错误数据："+JSON.stringify(submitDataForm.requestData[i]));
					return ;
				}
				if(submitDataForm.requestData[i].t==null || submitDataForm.requestData[i].t==''){
					layer.alert("调用接口的参数中t值不能为空!错误数据："+JSON.stringify(submitDataForm.requestData[i]));
					return ;
				}
				if(parseFloat(submitDataForm.requestData[i].Rt)<0){
					layer.alert("调用接口的参数中Rt值不能为负数!错误数据："+JSON.stringify(submitDataForm.requestData[i]));
					return ;
				}
				if(parseFloat(submitDataForm.requestData[i].t)<0){
					layer.alert("调用接口的参数中t值不能为负数!错误数据："+JSON.stringify(submitDataForm.requestData[i]));
					return ;
				}
			}
		}
		//调用接口，获取m值
		var data={
			Qm:submitDataForm.table2Data.QJLL,
			F:submitDataForm.table2Data.LLMJ,
			tc:tcvalue,
			Rup:submitDataForm.table2Data.RG,
			L:submitDataForm.table2Data.L,
			J:submitDataForm.table2Data.J,
			DATA:JSON.stringify({DATA:submitDataForm.requestData})
		};
		console.log("----------request--data-------"+JSON.stringify(data));
		$.ajax({
			url:serverUrl+"/CHLService.asmx/getm",
			type:'post',
			data:data,
			dataType:"json",
			success:function(response){
				if(response.CODE==1||response.CODE=="1"){
					layer.msg("计算成功!");
					var rdata=response.DATA;
					console.log("------response---data-----"+JSON.stringify(rdata));
					if(rdata!=null && rdata.length>0){
						//获取到m值，然后发送到后台，计算
						var stcd = $("#huiliujisuan_step1_stcd").combox("getValue");
						var pch = $("#huiliujisuan_step1_pch").val();
						submitDataForm.STCD=stcd;
						submitDataForm.PCH=pch;
						submitDataForm.TC=tcvalue;
						submitDataForm.table2Data.M=rdata[0].m;
						submitDataForm.table2Data.HLSJ=rdata[0].t;
						submitDataForm.table2Data.RG=rdata[0].Rup;
						submitDataForm.table2Data.J13=rdata[0]["J1/3"];
						submitDataForm.table2Data.L3F=rdata[0]["L/J1/3F"];
						submitDataForm.table2Data.QM=rdata[0]["Qm3/4"];
						submitDataForm.table2Data.QMR=rdata[0]["Qm3/4/ R"];
						var url= basePath + "huiliu/huiliu!calcStep1Total.action";
						var saveData={data:JSON.stringify(submitDataForm)};
						common_ajax(saveData, url, function(response) {
							if(response.reflag==1||response.reflag=="1"){
								var table2Data=response.table2Data;
								getTlgsTable("#tlgs_div","#tlgs_tab",table2Data);
							}	
						});
					}
				}else{
					layer.msg("调用计算m接口出错!");
				}
			}
		});
	//
}
function queryHuiLiuStep1Table1(){
	var stcd = $("#huiliujisuan_step1_stcd").combox("getValue");
	var stnm = $("#huiliujisuan_step1_stcd").combox("getLabel");
	var pch = $("#huiliujisuan_step1_pch").val();
	if(stnm==null || stnm.length<1){
		layer.msg("测站不能为空!");
		return ;
	}
	if(stcd==null ||stcd.length<1){
		layer.msg("未选中测站!");
		return ;
	}
	if(pch==null ||pch.length<1){
		layer.msg("批次号不能为空!");
		return ;
	}
	var url= basePath + "huiliu/huiliu!huiliuStep1.action?huiliuFormBean.stcd="+stcd+"&huiliuFormBean.pch="+pch;
	common_ajax(null, url, function(response) {
		if(response.reflag==1||response.reflag=="1"){
			//table2
			var table1Data=response.table1Data;
			getwdrsTable("#wdrs_div","#wdrs_tab",table1Data);
			var table2Data=response.table2Data;
			submitDataForm.table2Data=table2Data;
			$("#huiliujisuan_tc").val(table2Data.TC);
			getTlgsTable("#tlgs_div","#tlgs_tab",table2Data);
			var table3Data=response.table3Data;
			submitDataForm.table3Data=table3Data;
			var columnList=response.columnList;
			getQhtjtable("#qhjs_div","#qhjs_tab",table3Data,columnList);
			$("#huiliujisuan_hjr").val(response.rhj);
			submitDataForm.requestData=response.submitData;
		}
	});
}
//测站列表数据表格
	function getwdrsTable(chart, tab, table1Data){
	    var height = $(chart).height();
	    var width = $(chart).width();
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            height: height,
	            width: width,
	            id:'table1',
	            limit:table1Data.length,
	            data:table1Data,
	            cols: [
	                [{
	                    field: 'DATE',
	                    title: '时间',
	                    width:'200'
	                },{
	                    field: 'LL',
	                    title: '<div>流量</div><div>地下</div>',
	                    width:'200'
	                },{
	                    field: 'LLDX',
	                    title: '流量(地下)'
	                }]
	            ],
	            page: false,
	            done: function(res, curr, count){
			        submitDataForm.table2Data=res.data&&res.data.length>0?res.data[0]:null;
			    }
	        });
	    });
    }

	//2、推理公式计算
	function getTlgsTable(chart, tab, table2data){
	    var height = $(chart).height();
	    var width = $(chart).width();
	    if(table2data.TC==null || table2data.TC==""){
	    	$("#huiliujisuan_tc").val(table2data.HLSJ);
	    }else{
	    	$("#huiliujisuan_tc").val(table2data.TC);
	    }
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            height: height,
	            width: width,
	            id:'table2',
	            data:[table2data],
	            limit:1,
	            cols: [
	                [{
	                    field: 'PCH',
	                    title: '洪号',
	                    width:160
	                },{
	                    field: 'QJLL',
	                    title: 'Q净(m³/s)',
	                    width:100
	                },{
	                    field: 'HLSJ',
	                    title: '汇流时间t(小时)',
	                    width:160,
	                    edit: 'text'
	                },{
	                    field: 'RG',
	                    title: '地表净雨深R(mm)'
	                },{
	                    field: 'LLMJ',
	                    title: '流域面积F(km²)',
	                },{
	                    field: 'L',
	                    title: 'L',
	                    width:60,
	                    edit: 'text'
	                },{
	                    field: 'J',
	                    title: 'J',
	                    width:100,
	                    edit: 'text'
	                },{
	                    field: 'J13',
	                    title: 'J<sup>1/3</sup>',
	                    width:100
	                },{
	                    field: 'L3F',
	                    title: 'L/(J<sup>1/3</sup>*F)',
	                    width:100
	                },{
	                    field: 'QM',
	                    title: 'Q<sub>m</sub><sup>3/4</sup>',
	                    width:80
	                },{
	                    field: 'QMR',
	                    title: 'Q<sub>m</sub><sup>3/4</sup>/ R',
	                },{
	                    field: 'M',
	                    title: '汇流参数m',
	                    width:100
	                }]
	            ],
	            page: false,
	            done:function(res,curr,count){
	            	submitDataForm.table2Data=res.data&&res.data.length>0?res.data[0]:{};
	            }
	        });
	    });
    }
    function getQhtjtable(chart,tab,table3Data,columnList){
    	var height = $(chart).height();
	    var width = $(chart).width();
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            height: height,
	            width: width,
	            id:'table3',
	            data:table3Data,
	            cols:[columnList],
	            page: false,
	            done: function(res, curr, count){
			        submitDataForm.table3Data=res.data&&res.data.length>0?res:null;
			    }
	        });
	    });
    }
    //单站综合及误差统计table
	function getwctj_tab(chart, tab, table4Data){
	    var height = $(chart).height();
	    var width = $(chart).width();
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            height: height,
	            width: width,
	            id:'table4',
	            limit:table4Data.length,
	            data:table4Data,
	            cols: [
	            	[ //标题栏
	            	    {field: 'PCH', title: '洪号', rowspan: 2,align: 'center',width:130} //rowspan即纵向跨越的单元格数
	            	    ,{field: 'ERET', title: 'R上/tc(毫米/小时) ',  rowspan: 2,align: 'center',width:150}
	            	    ,{field: 'MJ', title: 'm计 ',  rowspan: 2,align: 'center',width:80}
	            	    ,{align: 'center', title: 'R上/tc ~m', colspan: 3} //colspan即横跨的单元格数，这种情况下不用设置field和width
	            	  ], [
	            	    {field: 'MC', title: 'm查', align: 'center',width:80}
	            	    ,{field: 'MS', title: '(m计-m查)/ m计',align: 'center',width:150 }
	            	    ,{field: 'SFHG', title: '是否合格', align: 'center',width:100}
	            	  ]
	            ],
	            page: false
	        });
	    });
    }

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


