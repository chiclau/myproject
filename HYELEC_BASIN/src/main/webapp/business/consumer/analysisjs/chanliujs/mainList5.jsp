<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>资料管理</title>
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
</head>
<body style="background-color: #FCFCFC;">
	<div class="container-fluid" id="cljs_mainList">
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>分析计算</li>
					<li style="color: black;">产流计算</li>
				</ol>
			</h3>
		</div>
		<hr style="margin-top:-5px;">
		<!-- 	<h1 style="text-align: center;">产流计算</h1> -->
		<form id="info_form_cjfa" name="" method="post">
	<%-- 		<%-- <div id="base" class="">
				请输入站名 : <input type="text" id="stnm" class="form-control" style="display:inline;"
					name="mStbprpFormBean.mStbprpInfoBean.stnm" > <input
					type="button" name="" style="width:50px;"  value="查询" onclick="getStp()" class="btn btn-primary"> <input
					type="button" name="" value="保存计算结果" onclick="save()" class="btn btn-save"> <input
					type="hidden" id="stcd" name="stcd"> &nbsp; 站名： <font
					color="green " style="display: none;" id="zhanming"></font> <font
					color="red " style="display: none;" id="m">提示 : 测站名称不存在</font>
					计算结果查询<select id="selectResult"></select>
			</div> --%>
			<div class="row-fluid">
				<!-- <div class="col-md-3" style="margin-left:16px;width: 28%">
					雨量站 : <input type="text" class="" style="width:200px" name="" value="" id="cols"><input
						type="button" style="margin-left:3px;" value="确定" onclick="autocreate()"  >
						 <input type="button" value="计算" onclick="jisuan()"  >
						
				</div> -->
				<div class="col-md-3" style="width: 20%;margin-left:4px;">
					<div class="input-append date form_datetime">
						时间区间 : <input size="16" id="start" type="text" readonly> <span
							class="add-on"><i class="icon-th"></i></span>
					</div>
				</div>
				<div class="col-md-2 " style="width:15%;">
					<div class="input-append date form_datetime">
						-- <input size="16" id="end" type="text" readonly> <span
							class="add-on"><i class="icon-th"></i></span>
					</div>
				</div>
				<div class="col-md-4">
					时间间隔 : <input type="text" id="jiange" name="" value="60">(时间精确到分钟)
				</div>
			</div>
				
				
				</div>
						<div id="hszlb_div" class="chart">
					<div style="height:32px;float: right;">
							<label>站名:</label>
						<input id="stnm" type="text" name="" class="form-control" 
							readonly style="width: 80;display:inline;">
						<label>洪号:</label>
						<input id="TAB_PCH" type="text" class="form-control"
							style="width: 100;display:inline;">
						<label>流域面积(km²):</label>
						<input id="TAB_LYMJ" type="text" class="form-control"
							style="width: 100;display:inline;">
						<label>前期影响雨量Pa(mm):</label>
						<input id="pa" type="text" class="form-control"
							style="width: 100;display:inline;">
								<input type="button" onclick="reckon();" value="计算" class="btn">
						<input type="button" onclick="saveTabTitle();" value="保存" class="btn">
					</div>
						<br/><br/>	
					<table class="layui-hide" id="hszlb_tab" lay-filter="hszlb_table"></table>
				</div>
			<br/><br/>			
			<!-- <div id="test"></div>-->
			<table border="1" id="table1">
				<tr id="tr1">
					<td rowspan="2" align="center">序号</td>
					<td rowspan="2" align="center" style="width:190px">日期</td>
					<td colspan="2" align="center" id="td1" class="addTd">
					<input	type="text" name="czmc"id="txt"  placeholder="请输入测站名"  style="width:100%">
							<div id="div_txt">			
				 				<div id="div_items_txt" class="div_items " onmouseleave="onLeave(this)">  
				                </div> 
				        	</div>
						</td>
					<td rowspan="2" align="center">雨面量</td>
					<td rowspan="2" align="center">Em</td>
					<td rowspan="2" align="center">Pa</td>
				</tr>
				<tr align="center" id="tr2">
					<td style="width:60px">权重</td>
					<td id="td2" class="addTd2" align="center"><input type="text"
						align="center" name="quanzhong" id="quanzhong"></td>
				</tr>
				<tr align="center" id="tr3">
					<td id="xuhao1">1</td>
					<td><input type="text" name="time" style="width:100%"></td>
					<td colspan="2" id="td4" class="addTd4" align="center"><input
						type="text" name="yu" align="center" style="width:100%"></td>
					<td colspan="" align="center"><input type="text"
						name="result" align="center" style="width:100%"></td>
						<td colspan="" align="center"><input type="text"
						name="Em" align="center" style="width:100%"></td>
						<td colspan="" align="center"><input type="text"
						name="Pa" align="center" style="width:100%"></td>
				</tr>
			</table>
			</form>
	</div>
	
</body>

<%-- <script type="text/javascript" src="<%=basePath%>business/consumer/analysisjs/chanliujs/xiala.js"></script> --%>
<script type="text/javascript">
$(function(){
$("#stnm").val(stnm);
$("#stcd").val(stcd);
$("#start").val(beginDate);
$("#end").val(endDate);
$("#jiange").val(interval);
})
function reckon(){
	var Pa=$("#pa").val();
	var DT=$("#jiange").val();
	var tr=$(".removeTr");
	for(var i = 0; i<tr.length; i++ ){
		   for(var j = 0; j<tr[i].cells.length; j++ ){    // 遍历该行的 td
		       alert("第"+(i+1)+"行，第"+(j+1)+"个td的值："+tr[i].cells[j].innerHTML+"。");           // 输出每个td的内容
		   }
		} 
/* 	var tb = document.getElementById('table1');    // table 的 id
	var rows = tb.rows;                           // 获取表格所有行
	for(var i = 0; i<rows.length; i++ ){
	   for(var j = 0; j<rows[i].cells.length; j++ ){    // 遍历该行的 td
	       alert("第"+(i+1)+"行，第"+(j+1)+"个td的值："+rows[i].cells[j].innerHTML+"。");           // 输出每个td的内容
	   }
	} */
}
	window.onresize = "";
	$(".form_datetime").datetimepicker({
		format : " yyyy-mm-dd hh:ii",
		autoclose : true,// 选择好自动关闭
		language : 'zh-CN', // 汉化
	});
	
    //移入移出效果  
    $(".div_item").hover(function () {  
        $(this).css('background-color', '#1C86EE').css('color', 'white');  
    }, function () {  
        $(this).css('background-color', 'white').css('color', 'black');  
    });  
    
    var flag = true;
    $("input[name='czmc']").on('compositionstart',function(){// compositionstart 在输入一段需要确认的文本如拼音to汉字、语音时会触发
        flag = false;
    })
    $("input[name='czmc']").on('compositionend',function(){//compositionend  在拼音选词完成、语音输入完毕时会触发
        flag = true;
    })
    $("input[name='czmc']").each(function(i){
    	
    }).on('input',function(){
        var _this = this;
        setTimeout(function(){
            if(flag){
                console.log($(_this).val());
                var json = "mStbprpFormBean.mStbprpInfoBean.stnm="+$(_this).val();
            	var url = basePath + "chanliu/chanliu!getStbprpMoHu.action"
            	common_ajax(json, url, function(response) {
            		var div="";
            		for (var i = 0; i < response.rows.length; i++) {
        			 div+="<div class='div_item' id='"+response.rows[i].STCD+"' onclick='on(this)''>"+response.rows[i].STNM+"</div>";
        			}
            		$("#div_items_txt").html(div);
            		
            	});
            }
        },1)
    })

 function sel(i){
	 var flag = true;
     $("input[name='czmc']").on('compositionstart',function(){// compositionstart 在输入一段需要确认的文本如拼音to汉字、语音时会触发
         flag = false;
     })
     $("input[name='czmc']").on('compositionend',function(){//compositionend  在拼音选词完成、语音输入完毕时会触发
         flag = true;
     })
    	   var txtid=$(i).attr("id");
    	   inputId=txtid;
    	   var divid="div_"+txtid;
    	   var div_items="div_items_"+txtid;
    	   show(div_items);//显示
    	    $("input[name='czmc']").on('input',function(){
                var _this = this;
                setTimeout(function(){
                    if(flag){
                        console.log($(_this).val());
                        var json = "mStbprpFormBean.mStbprpInfoBean.stnm="+$(_this).val();
                    	var url = basePath + "chanliu/chanliu!getStbprpMoHu.action"
                    	common_ajax(json, url, function(response) {
                    		var div="";
                    		for (var i = 0; i < response.rows.length; i++) {
                			 div+="<div class='div_item' id='"+response.rows[i].STCD+"' onclick='on(this)'' onmouseleave=onLeave(this)'>"+response.rows[i].STNM+"</div>";
                			}
                    		$("#"+div_items+"").html(div);
                    		
                    	});
                    }
                },1)
            })
    }

//项点击  回显
function on(i){
	var par=$(i).parent().parent().attr("id");
	var str=par.replace("div_","");
   $("#"+str+"").val($(i).text());  //选择的测站回显到输入框
    var stcd=$(i).attr("id");//获得测站编码
    var json = "mStbprpFormBean.mStbprpInfoBean.stcd="+stcd; //查询
	var url = basePath + "chanliu/chanliu!list.action" //路径
	common_ajax(json, url, function(response) {//ajax提交。回调函数
    	var shijian = document.getElementsByName("time");
		cle(shijian)//清空input
		for (var i = 0; i < response.rows.length; i++) {
			var date1=response.rows[i].TM.time;
			var d1=date1.toString();
			var newstr=d1.substring(0,d1.length-1);
			$("#"+date1+"").val(response.rows[i].DRP);//数据回显
		}
		rem();//隐藏下拉DIV选项
	});
}   
//鼠标移除div触发的方法    
function onLeave(i){
	 $(i).css('display', 'none');  
}

 //弹出列表框  
$("#txt").click(function () {  
    $("#div_items_txt").css('display', 'block');  
    return false;  
});  
function show(id){
	   $("#"+id+"").css('display', 'block');  
	   return false;  
}

function rem(){
		  $(".div_items").css('display', 'none');  
}
//移入移出效果  
$(".div_item").hover(function () {  
    $(this).css('background-color', '#1C86EE').css('color', 'white');  
}, function () {  
    $(this).css('background-color', 'white').css('color', 'black');  
});  

//清空input
function cle(shijian){
 	var date1 = new Date($("#start").val()); 
	var date2 = date1.getTime();
	var shu = Number($("#jiange").val()) * 60;// 分钟*秒
	var haomiao = shu * 1000;
	var num = $("#cols").val();
	var addTime = 0;
	for (var i = 0; i < shijian.length; i++) {// 下面的switch是生成时间都
		switch (i) {
		case 0:
			for(var j=0;j<num;j++){
				var x=2+j;
				var d1=date1.getTime();
				$("#"+d1+"").val("");
				//$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d1+j);
			}
			break;
		case 1:
			for(var j=0;j<num;j++){
				var x=2+j;
				var d2=date2 + haomiao;
				$("#"+d2+"").val("");
			//	$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d2+j);
			}
			var t1 = formatDateTime(new Date(date2 + haomiao));
			shijian[i].value = t1;
			break;
		default:
			for(var j=0;j<num;j++){
				var x=2+j;
				var d3=date2 + addTime;
				$("#"+d3+"").val("");
				$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d3+j);
			}
			var t2 = formatDateTime(new Date(date2 + addTime));
			shijian[i].value = t2;
			;
		}
		addTime += haomiao;// 每次叠加1个时间段
	}
}

    
    
</script>

</html>
