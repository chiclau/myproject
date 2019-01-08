﻿	      	var colors =['#007bbb','#008e57','#933d92',' #989898','#c48f00','#A0522D'];
	      	var sheng=[];
	      	var shi=[];
	      	var xian=[];		
	      	var areaWidth=['90%','90%']
$(function(){
	var xzlyqh="";
	hbtj(xzlyqh);
	 getZtree(xzlyqh);
})

function getcheckId(){
	var treeObj=$.fn.zTree.getZTreeObj("tree2");
    nodes=treeObj.getCheckedNodes(true);
    var arr = ""; // 向后台传送的id字符串
    for(var i = 0;i<nodes.length;i++){
    	if(nodes[i].level<=3){
        		arr += "," + nodes[i].id
    	}
    }
    arr = arr.substr(1)
    return arr;
}

function initTable_sjzrbhqk(tableId){
	var treeObj=$.fn.zTree.getZTreeObj("tree2");
    nodes=treeObj.getCheckedNodes(true);
    var arr = ""; // 向后台传送的id字符串
    for(var i = 0;i<nodes.length;i++){
    	if(nodes[i].level<=3){
        		arr += "," + nodes[i].id
    	}
    }
    arr = arr.substr(1)
    $.ajax({
		url:basePath + "hbtj/hbtj!echartsAndTable.action",
		data:{"tableId":tableId,"arr":arr},
		type: "POST",
		dataType:"json",
		traditional:true,
		success:function(response){
			var data = response.data;
			var tr = "";
			var len = 1;
			var flen = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			var zj_zj4 =0; var zj_dz4 =0; var zj_zj5 =0; var zj_dz5 =0; 
			var zj_zj6 =0; var zj_dz6 =0;
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				tr +="<tr>"
					if(row.name == '安徽省' || row.name == '贵州省'|| row.name == '上海市'|| row.name == '浙江省'|| row.name == '江西省'
						|| row.name == '湖北省'|| row.name == '湖南省'|| row.name == '重庆市'|| row.name == '四川省'|| row.name == '云南省'
							|| row.name == '江苏省'){
						if(i == 0){
							tr +="<td>"+(len)+"</td>"
							flen = len;
							zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
							zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
							zj_zj4+=row.zrl4;zj_dz4+=row.zdz4;zj_zj5+=row.zrl5;zj_dz5+=row.zdz5;
							zj_zj6+=row.zrl6;zj_dz6+=row.zdz6
						}else{
							tr +="<td>"+(len+=1)+"</td>"
							flen = len
							zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
							zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
							zj_zj4+=row.zrl4;zj_dz4+=row.zdz4;zj_zj5+=row.zrl5;zj_dz5+=row.zdz5;
							zj_zj6+=row.zrl6;zj_dz6+=row.zdz6
						}
					}else{
						tr +="<td>"+((flen += 0.1).toFixed(1))+"</td>"
					}
				tr +="<td>"+row.name+"</td>"
				tr +="<td>"+(row.zrl/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz+"</td>"
				tr +="<td>"+(row.zrl1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz1+"</td>"
				tr +="<td>"+(row.zrl2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz2+"</td>"
				tr +="<td>"+(row.zrl6/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz6+"</td>"
				tr +="<td>"+(row.zrl3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz3+"</td>"
				tr +="<td>"+(row.zrl4/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz4+"</td>"
				tr +="<td>"+(row.zrl5/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz5+"</td>"
				tr +="</tr>"
			}
			$("#sjzrbhqk_sj").html("");
			$("#sjzrbhqk_sj").append(tr);
			var zj_tr = "<tr>"
				+"<td colspan = '2'style='display:inline-block;width: 220px;'>总计</td>"
				+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
				+"<td>"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
				+"<td>"+(zj_zj2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
				+"<td>"+(zj_zj6/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz6+"</td>"
				+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
				+"<td>"+(zj_zj4/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
				+"<td>"+(zj_zj5/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
				+"</tr>";
			$("#sjzrbhqk_zj").html("");
			$("#sjzrbhqk_zj").append(zj_tr);
		}
    })
}

function initTable_hpqk(tableId){
	var treeObj=$.fn.zTree.getZTreeObj("tree2");
    nodes=treeObj.getCheckedNodes(true);
    var arr = ""; // 向后台传送的id字符串
    for(var i = 0;i<nodes.length;i++){
    	if(nodes[i].level<=3){
        		arr += "," + nodes[i].id
    	}
    }
    arr = arr.substr(1)
    $.ajax({
		url:basePath + "hbtj/hbtj!echartsAndTable.action",
		data:{"tableId":tableId,"arr":arr},
		type: "POST",
		dataType:"json",
		traditional:true,
		success:function(response){
			var data = response.data;
			var tr = "";
			var len = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; 	var zj_zj3 =0; var zj_dz3 =0; 
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				tr +="<tr>"
				tr +="<td>"+(len++)+"</td>"
				tr +="<td>"+row.date+"</td>"
				tr +="<td>"+(row.zrl/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz+"</td>"
				tr +="<td>"+(row.zrl1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz1+"</td>"
				tr +="<td>"+(row.zrl2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz2+"</td>"
				tr +="<td>"+(row.zrl3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz3+"</td>"
				tr +="</tr>"
				zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
				zj_zj2+=row.zrl2;zj_dz2+=row.zdz2; zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
			}
			if(tableId == "hpqk_id"){	
				$("#hpqk_sj").html("")
				   if(nodes.length==0){//没选
				    	return false;
				    }
				$("#hpqk_sj").append(tr)
			}
			if(tableId == "tcqhpqk_id"){
				$("#tcqhpqk_sj").html("")
				   if(nodes.length==0){//没选
				    	return false;
				    }
				$("#tcqhpqk_sj").append(tr)
			}
			var zj_tr = "<tr>"
				+"<td colspan = '2'style='display:inline-block;width:121px;'>总计</td>"
				+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
				+"<td>"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
				+"<td>"+(zj_zj2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
				+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
				+"</tr>";
			if(tableId == "hpqk_id"){
				var zj_tr = "<tr>"
					+"<td style='width: 150px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+(sfkzxmhp[0].value
					+sfkzxmhp[1].value+sfkzxmhp[2].value)+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfkzxmhp[0].value+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfkzxmhp[1].value+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfkzxmhp[2].value+"</td>"
					+"</tr>";
				$("#hpqk_zj").html("")
				$("#hpqk_zj").append(zj_tr)
			}
			if(tableId == "tcqhpqk_id"){
				$("#tcqhpqk_zj").html("")
				$("#tcqhpqk_zj").append(zj_tr)
			}
		}
    })
}

function initTable(tableId){
	var treeObj=$.fn.zTree.getZTreeObj("tree2");
    nodes=treeObj.getCheckedNodes(true);
    var arr = ""; // 向后台传送的id字符串
    for(var i = 0;i<nodes.length;i++){
    	if(nodes[i].level<=3){
        		arr += "," + nodes[i].id
    	}
    }
    arr = arr.substr(1)
    $.ajax({
		url:basePath + "hbtj/hbtj!echartsAndTable.action",
		data:{"tableId":tableId,"arr":arr},
		type: "POST",
		dataType:"json",
		traditional:true,
		success:function(response){
			var data = response.data;
			var tr = "";
			var len = 1;
			var flen = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				tr +="<tr>"
					if(row.name == '安徽省' || row.name == '贵州省'|| row.name == '上海市'|| row.name == '浙江省'|| row.name == '江西省'
						|| row.name == '湖北省'|| row.name == '湖南省'|| row.name == '重庆市'|| row.name == '四川省'|| row.name == '云南省'
							|| row.name == '江苏省'){
						if(i == 0){
							tr +="<td  >"+(len)+"</td>"
							flen = len;
							zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
							zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
						}else{
							tr +="<td >"+(len+=1)+"</td>"
							flen = len
							zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
							zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
						}
					}else{
						tr +="<td >"+((flen += 0.1).toFixed(1))+"</td>"
					}
				tr +="<td >"+row.name+"</td>"
				tr +="<td>"+(row.zrl/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz+"</td>"
				tr +="<td >"+(row.zrl1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz1+"</td>"
				tr +="<td >"+(row.zrl2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz2+"</td>"
				tr +="<td>"+(row.zrl3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz3+"</td>"
				tr +="</tr>"
			}
			var zj_tr = "<tr>"
				+"<td style='width: 150px;'>总计</td>"
				+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
				+"<td>"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
				+"<td>"+(zj_zj2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
				+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
				+"</tr>";
			if(tableId == "sthj_id"){
				$("#sthj_sj").html("");
				$("#sthj_sj").append(tr);
			}
			if(tableId == "hp_id"){
				$("#hp_sj").html("");
				$("#hp_sj").append(tr);
			}
			if(tableId == "hp_id"){
				var zj1_tr = "<tr>"
					+"<td style='width: 150px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+(sfkzxmhp[0].value+sfkzxmhp[1].value+sfkzxmhp[2].value)+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfkzxmhp[0].value+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfkzxmhp[1].value+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfkzxmhp[2].value+"</td>"
					+"</tr>";
				$("#hp_zj").html("");
				$("#hp_zj").append(zj1_tr);
			}
			//是否有环评审批统计
			if(tableId == "hp_id1"){
				$("#hpsp_sj").html("");
				$("#hpsp_sj").append(tr);
				var zj_tr = "<tr>"
					+"<td style='width: 150px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+(sfyhpsp[0].value
					+sfyhpsp[1].value+sfyhpsp[2].value)+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfyhpsp[0].value+"</td>"
					+"<td>"+0 +"&nbsp;&nbsp;/&nbsp;&nbsp;"+0+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+sfyhpsp[2].value+"</td>"
					+"</tr>";
				$("#hpsp_zj").html("");
				$("#hpsp_zj").append(zj_tr);
			}
			if(tableId == "stll_id"){
				$("#stll_sj").html("");
				$("#stll_sj").append(tr);
			}
			if(tableId == "gycs_id"){
				$("#gycs_sj").html("");
				$("#gycs_sj").append(tr);
			}
			if(tableId == "zzfl_id"){
				$("#zzfl_sj").html("");
				$("#zzfl_sj").append(tr);
			}
			if(tableId == "gh_id"){
				$("#gh_sj").html("");
				$("#gh_sj").append(tr);
			}
			if(tableId == "ghhp_id"){
				$("#ghhp_sj").html("");
				$("#ghhp_sj").append(tr);
			}
			if(tableId == "jgys_id"){
				$("#jgys_sj").html("");
				$("#jgys_sj").append(tr);
			}
			if(tableId == "bxtsgh_id"){
				$("#bxtsgh_sj").html("");
				$("#bxtsgh_sj").append(tr);
			}
			if(tableId == "sthj_id"){
				$("#sthj_zj").html("");
				$("#sthj_zj").append(zj_tr);
			}
			if(tableId == "stll_id"){
				$("#stll_zj").html("");
				$("#stll_zj").append(zj_tr);
			}
			if(tableId == "gycs_id"){
				$("#gycs_zj").html("");
				$("#gycs_zj").append(zj_tr);
			}
			if(tableId == "zzfl_id"){
				$("#zzfl_zj").html("");
				$("#zzfl_zj").append(zj_tr);
			}
			if(tableId == "gh_id"){
				$("#gh_zj").html("");
				$("#gh_zj").append(zj_tr);
			}
			if(tableId == "ghhp_id"){
				$("#ghhp_zj").html("");
				$("#ghhp_zj").append(zj_tr);
			}
			if(tableId == "jgys_id"){
				$("#jgys_zj").html("");
				$("#jgys_zj").append(zj_tr);
			}
			if(tableId == "bxtsgh_id"){
				$("#bxtsgh_zj").html("");
				$("#bxtsgh_zj").append(zj_tr);
			}
		}
	})
}
 function getZtree(xzlyqh){
	$.ajax({
        url : basePath + "hbtj/hbtj!getZtree.action",
        type : "post",
        dataType : "JSON",
        traditional: true,
        data : {"xzlyqh":xzlyqh},
        success : function(response) {
          if(response.reflag==1||response.reflag=="1"){
        		 $.fn.zTree.init($("#tree2"), setting, response.treeData);
        		 // 默认展开一级节点
       		  var treeObj = $.fn.zTree.getZTreeObj("tree2");
                 var nodes = treeObj.getNodes();
                 for (var i = 0; i < nodes.length; i++) { // 设置节点展开
                     treeObj.expandNode(nodes[i], true, false, true);
                     treeObj.checkNode(nodes[i], true, true);
                 }
        	//  layer.msg("加载数据成功！");
          }else{
          	layer.msg(response.message);
          }
        }
    });
}
/* ztree部分 */
var setting = {
		// 添加复选框
		check: {
            enable: true
        },
   	 data: {
         simpleData: {
             enable: true,
             idKey: "codeID",
             pIdKey: "parentID",
             rootPId: 0
         }
     },
    	callback:{
			onCheck: zTreeOnCheck  // 选中事件
		}
};

function zTreeOnCheck(event, treeId, treeNode) {
	var treeObj=$.fn.zTree.getZTreeObj("tree2");
    nodes=treeObj.getCheckedNodes(true);
	var arr=[];
	 sheng=[];
	 shi=[];
	 xian=[];
	if(nodes.length>0){// 选择了
		if(arr.length==1351||arr.length==1351){//全选
			arr=[];
			hbtj(arr);
		}else{
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].level==1){
					sheng.push(nodes[i].codeID);
				}else if(nodes[i].level==2){
					shi.push(nodes[i].codeID);
				}else if(nodes[i].level==3){
					xian.push(nodes[i].codeID);
			}
		}
			hbtj1(sheng,shi,xian);
		}
		console.log(arr.length);
	//	debugger;
		console.log(sheng);
		console.log(shi);
		console.log(xian);
	}else{// 没选
		var response={
			llxfTotal:// 生态流量泄放措施
				[{name:'有',value:0}
				,{name:'无',value:0}
				,{name:'未知',value:0}],
			lljkTotal:// 生态流量监控措施
				[{name:'有',value:0}
				,{name:'无',value:0}
				,{name:'未知',value:0}],
			gycsTotal:// 过鱼措施
				[{name:'有',value:0}
				,{name:'无',value:0}
				,{name:'未知',value:0}],
			zzlfTotal:// 增殖放流
				[{name:'有',value:0}
				,{name:'无',value:0}
				,{name:'未知',value:0}],
				kzxmhpqkTotal://是否开展项目环评
					[{name:'是',value:0}
					,{name:'否',value:0}
					,{name:'未知',value:0}],
					sfyhpspTotal://是否有环评审批
						[{name:'是',value:0}
						,{name:'否',value:0}
						,{name:'未知',value:0}],		
		 kzxmhpqk://开展项目环评情况
			 [{y1:0,y2:0,y3:0,y4:0,y5:0,y6:0}	,
				 {y1:0,y2:0,y3:0,y4:0,y5:0,y6:0}	],
		tcqwcspqk://投产前完成审批情况
			 [{y1:0,y2:0,y3:0,y4:0,y5:0,y6:0}	,
				 {y1:0,y2:0,y3:0,y4:0,y5:0,y6:0}	],
			fhghTotal://是否符合规划
						[{name:'是',value:0}
						,{name:'否',value:0}
						,{name:'未知',value:0}],
		ghhpTotal	://是否符合规划环评
			[{name:'是',value:0}
			,{name:'否',value:0}
			,{name:'未知',value:0}],
			hbysTotal	://是否通过竣工环保验收
				[{name:'是',value:0}
				,{name:'否',value:0}
				,{name:'未知',value:0}],
			tsgkTotal	://是否存在脱水干凅情况
					[{name:'是',value:0}
					,{name:'否',value:0}
					,{name:'未知',value:0}],		
			sfsjzrbhq	://是否涉及自然保护区
				[{name:'是',value:0}
				,{name:'否',value:0}
				,{name:'未知',value:0}],		
			sjzrbhqqk://涉及自然保护区情况，试验区，核心区，缓冲区
				[{name:'试验区',value:0}
				,{name:'核心区',value:0}
				,{name:'缓冲区',value:0}],	
		};
		loadEcharts(response,arr);//加载Echarts
		};
	}
	
function hbtj1(sheng,shi,xian){
	$.ajax({
        url : basePath + "hbtj/hbtj!queryHbtjData1.action",
        type : "post",
        dataType : "JSON",
        traditional: true,
        data : {"sheng":[sheng],"shi":[shi],"xian":[xian]},
        success : function(response) {
          if(response.reflag==1||response.reflag=="1"){
        	  loadEcharts(response,xian);
            $('#mainonedes_sthj').addClass('desplay');
      	    $('#sthj_id').text('切换表格');
      	    $('#sthj_dc_id').addClass('desplay');
      	    $('#mainonedes_sjzrbhqk').addClass('desplay');
      	    $('#sjzrbhqk_id').text('切换表格');
      	    $('#sjzrbhqk_dc_id').addClass('desplay');
      	    $('#mainonedes_bxtsgh').addClass('desplay');
      	    $('#bxtsgh_id').text('切换表格');
      	    $('#bxtsgh_dc_id').addClass('desplay');
      	    $('#mainonedes_jgys').addClass('desplay');
      		$('#jgys_id').text('切换表格');
      		$('#jgys_dc_id').addClass('desplay');
      	    $('#mainonedes_ghhp').addClass('desplay');
      	    $('#ghhp_id').text('切换表格');
      	    $('#ghhp_dc_id').addClass('desplay');
      	    $('#mainonedes_gh').addClass('desplay');
      	    $('#gh_id').text('切换表格');
      	    $('#gh_dc_id').addClass('desplay');
      	    $('#mainonedes_tcqhpqk').addClass('desplay');
      		$('#tcqhpqk_id').text('切换表格');
      		$('#tcqhpqk_dc_id').addClass('desplay');
      	    $('#mainonedes_hpqk').addClass('desplay');
      	    $('#hpqk_id').text('切换表格');
      	    $('#hpqk_dc_id').addClass('desplay');
      	    $('#mainonedes_zzfl').addClass('desplay');
      		$('#zzfl_id').text('切换表格');
      		$('#zzfl_dc_id').addClass('desplay');
      		$('#mainonedes_gycs').addClass('desplay');
     		$('#gycs_id').text('切换表格');
     		$('#gycs_dc_id').addClass('desplay');
     		$('#mainonedes_stll').addClass('desplay');
      		$('#stll_id').text('切换表格');
      		$('#stll_dc_id').addClass('desplay');
        	  layer.msg("加载数据成功！");
          }else{
          	layer.msg(response.message);
          }
        }
    });
}

 function hbtj(xzlyqh){
	$.ajax({
        url : basePath + "hbtj/hbtj!queryHbtjData.action",
        type : "post",
        dataType : "JSON",
        traditional: true,
        data : {"xzlyqh":[xzlyqh]},
        success : function(response) {
          if(response.reflag==1||response.reflag=="1"){
        	  loadEcharts(response,xzlyqh);
            $('#mainonedes_sthj').addClass('desplay');
      	    $('#sthj_id').text('切换表格');
      	    $('#sthj_dc_id').addClass('desplay');
      	    $('#mainonedes_sjzrbhqk').addClass('desplay');
      	    $('#sjzrbhqk_id').text('切换表格');
      	    $('#sjzrbhqk_dc_id').addClass('desplay');
      	    $('#mainonedes_bxtsgh').addClass('desplay');
      	    $('#bxtsgh_id').text('切换表格');
      	    $('#bxtsgh_dc_id').addClass('desplay');
      	    $('#mainonedes_jgys').addClass('desplay');
      		$('#jgys_id').text('切换表格');
      		$('#jgys_dc_id').addClass('desplay');
      	    $('#mainonedes_ghhp').addClass('desplay');
      	    $('#ghhp_id').text('切换表格');
      	    $('#ghhp_dc_id').addClass('desplay');
      	    $('#mainonedes_gh').addClass('desplay');
      	    $('#gh_id').text('切换表格');
      	    $('#gh_dc_id').addClass('desplay');
      	    $('#mainonedes_tcqhpqk').addClass('desplay');
      		$('#tcqhpqk_id').text('切换表格');
      		$('#tcqhpqk_dc_id').addClass('desplay');
      	    $('#mainonedes_hpqk').addClass('desplay');
      	    $('#hpqk_id').text('切换表格');
      	    $('#hpqk_dc_id').addClass('desplay');
      	    $('#mainonedes_zzfl').addClass('desplay');
      		$('#zzfl_id').text('切换表格');
      		$('#zzfl_dc_id').addClass('desplay');
      		$('#mainonedes_gycs').addClass('desplay');
     		$('#gycs_id').text('切换表格');
     		$('#gycs_dc_id').addClass('desplay');
     		$('#mainonedes_stll').addClass('desplay');
      		$('#stll_id').text('切换表格');
      		$('#stll_dc_id').addClass('desplay');
        	  layer.msg("加载数据成功！");
          }else{
          	layer.msg(response.message);
          }
        }
    });
}
var sfkzxmhp;//开展项目环评情况统计
var sfyhpsp;//是否有环评审批统计
function 	loadEcharts(response,xzlyqh){//具体见queryHuanBao,Service中的注释
	var sfsjzrbhq=response.sfsjzrbhq;
	var kzxmhpqk=response.kzxmhpqk;
	var kzxmhpqkTotal=response.kzxmhpqkTotal;
	sfkzxmhp=kzxmhpqkTotal;
	var sfyhpspTotal=response.sfyhpspTotal;
	sfyhpsp=sfyhpspTotal;
	var tcqwcspqk=response.tcqwcspqk;
	var sjzrbhqqk=response.sjzrbhqqk;
	var llxfTotal=response.llxfTotal;
	var lljkTotal=response.lljkTotal;
	var gycsTotal=response.gycsTotal;
	var zzlfTotal=response.zzlfTotal;
	var fhghTotal=response.fhghTotal;
	var ghhpTotal=response.ghhpTotal;
	var hbysTotal=response.hbysTotal;
	var tsgkTotal=response.tsgkTotal;
	var x1=['生态流量泄放设施','生态流量监控设施','过鱼设施','增殖放流措施'];
	var x2=['1986前','1986-1989','1989-1998','1998-2003','2003-2006','2006后'];
	var title1="环保措施情况统计";
	var title2='开展项目环评情况';
	var title3='投产前完成环评审批情况';
	var title4='涉及自然保护区情况';
	// 开展项目环评情况
	var hpSum = 0;
	var hp1 = [];
	var hp2 = [];
	for(var k in kzxmhpqk[0]){
		hp1.push(kzxmhpqk[0][k])
		hpSum += kzxmhpqk[0][k]
	}
	for(var k in kzxmhpqk[1]){
		hp2.push(kzxmhpqk[1][k])
		hpSum += kzxmhpqk[1][k]
	}
	// 投产前完成环评审批情况统计
	var tcSum = 0;
	var tc1 = [];
	var tc2 = [];
	for(var k in tcqwcspqk[0]){
		tc1.push(tcqwcspqk[0][k])
		tcSum += tcqwcspqk[0][k]
	}
	for(var k in tcqwcspqk[1]){
		tc2.push(tcqwcspqk[1][k])
		tcSum += tcqwcspqk[1][k]
	}
	
	
	
	// 环保措施情况统计
	   var myChart = echarts.init(document.getElementById('hbtj1'));
    var option = {
   		  title : {
   		        text: '生态环境流量泄放设施统计',
   		        x:'center'
   		    },
   		 tooltip: {
   		        trigger: 'item',
   		        formatter: "{a} <br/>{b}: {c} ({d}%)"
   		    },
   		    legend: {
        		     x : 'center',
     		        y : 'bottom',
     		       data:['有','无','未知',]
        		    },
   		     color:colors,
   		    series: [
   		    	 {
	     		            name:'总数',
	     		            type:'pie',
	     		            center:['50%','50%'],
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
	     		           data:[{'value':llxfTotal[0].value+llxfTotal[1].value+llxfTotal[2].value , "name":'在建及运行(座)' }]
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
   		            data:llxfTotal
   		        }
   		    ]
    };
    myChart.off("click");
    myChart.on('click', function (params) {
    	//debugger;
		var name = params.name;
		layer.open({
			  type: 2, 
			  title:  '生态环境流量泄放设施统计('+name+')',
			  area:areaWidth,
			  skin: 'layui-layer-molv' ,
			  icon: 6,
			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+15,
		});
	});
    
    $("#sthj_id").off("click")
    $('#sthj_id').click(function(){
      	 if( $('#mainonedes_sthj').hasClass('desplay')){
      		 $('#sthj_id').text('切换图表');
      		 $('#mainonedes_sthj').removeClass('desplay');
      		 if($('#sthj_dc_id').hasClass('desplay')){
    			 $('#sthj_dc_id').removeClass('desplay');
    		 }else{
    			 $('#table-iconone').addClass('desplay');
    		 }
      		 initTable("sthj_id")
      	 }else{
      		  $('#mainonedes_sthj').addClass('desplay');
      		  $('#sthj_id').text('切换表格');
      		 $('#sthj_dc_id').addClass('desplay');
      	 }
      	  
       })
    myChart.setOption(option);
    window.onresize = myChart.resize;
    
    var myChart = echarts.init(document.getElementById('hbtj2'));
    var option = {

     		  title : {
     		        text: '生态流量监控措施统计',
     		        x:'center'
     		    },
     		 tooltip: {
     		        trigger: 'item',
     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
     		    },
     		    legend: {
          		     x : 'center',
       		        y : 'bottom',
       		     data:['有','无','未知',]
          		    },
     		     color:colors,
     		    series: [
     		    	 {
  	     		            name:'总数',
  	     		            type:'pie',
  	     		            center:['50%','50%'],
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
  	     		           data:[{'value':lljkTotal[0].value+lljkTotal[1].value+lljkTotal[2].value  , "name":'在建及运行(座)' }]
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
     		            data:lljkTotal
     		        }
     		    ]
  };
    myChart.off("click");// 防止累计触发
    myChart.on('click', function (params) {
		var name = params.name;
		layer.open({
			  type: 2, 
			  title:  '生态流量监控措施统计('+name+')',
			  area:areaWidth,
			  skin: 'layui-layer-molv' ,
			  icon: 6,
			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+16,
		});
	});
    $("#stll_id").off("click")
    $('#stll_id').click(function(){
     	 if( $('#mainonedes_stll').hasClass('desplay')){
     		 $('#stll_id').text('切换图表');
     		 $('#mainonedes_stll').removeClass('desplay');
     		 if($('#stll_dc_id').hasClass('desplay')){
    			 $('#stll_dc_id').removeClass('desplay');
    		 }else{
    			 $('#table-iconone').addClass('desplay');
    		 }
     		 initTable("stll_id")
     	 }
     	  else{
     		  $('#mainonedes_stll').addClass('desplay');
     		  $('#stll_id').text('切换表格');
     		  $('#stll_dc_id').addClass('desplay');
     	 }
      })
  myChart.setOption(option);
  window.onresize = myChart.resize;
    
  var myChart = echarts.init(document.getElementById('hbtj3'));
  var option = {
   		  title : {
   		        text: '过鱼设施统计',
   		        x:'center'
   		    },
   		 tooltip: {
   		        trigger: 'item',
   		        formatter: "{a} <br/>{b}: {c} ({d}%)"
   		    },
   		    legend: {
        		     x : 'center',
     		        y : 'bottom',
        		        data:['有','无','未知',]
        		    },
   		     color:colors,
   		    series: [
   		    	 {
	     		            name:'总数',
	     		            type:'pie',
	     		            center:['50%','50%'],
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
	     		           data:[{'value':gycsTotal[0].value+gycsTotal[1].value+gycsTotal[2].value , "name":'在建及运行(座)' }]
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
   		            data:gycsTotal
   		        }
   		    ]
};
  myChart.off("click");// 防止累计触发
  myChart.on('click', function (params) {
		var name = params.name;
		layer.open({
			  type: 2, 
			  title:  '过鱼设施统计('+name+')',
			  area: areaWidth,
			  skin: 'layui-layer-molv' ,
			  icon: 6,
			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+17,
		});
	});
  $("#gycs_id").off("click")
  $('#gycs_id').click(function(){
   	 if( $('#mainonedes_gycs').hasClass('desplay')){
   		 $('#gycs_id').text('切换图表');
   		 $('#mainonedes_gycs').removeClass('desplay');
   		 if($('#gycs_dc_id').hasClass('desplay')){
			 $('#gycs_dc_id').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
   		 initTable("gycs_id")
   	 }
   	  else{
   		  $('#mainonedes_gycs').addClass('desplay');
   		  $('#gycs_id').text('切换表格');
   		 $('#gycs_dc_id').addClass('desplay');
   	 }
    })
myChart.setOption(option);
window.onresize = myChart.resize;
  

var myChart = echarts.init(document.getElementById('hbtj4'));
var option = {
 		  title : {
 		        text: '增殖放流措施统计',
 		        x:'center'
 		    },
 		 tooltip: {
 		        trigger: 'item',
 		        formatter: "{a} <br/>{b}: {c} ({d}%)"
 		    },
 		    legend: {
      		     x : 'center',
   		        y : 'bottom',
   		     data:['有','无','未知',]
      		    },
 		     color:colors,
 		    series: [
 		    	 {
	     		            name:'总数',
	     		            type:'pie',
	     		            center:['50%','50%'],
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
	     		           data:[{'value':zzlfTotal[0].value+zzlfTotal[1].value+zzlfTotal[2].value , "name":'在建及运行(座)' }]
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
 		            data:zzlfTotal
 		        }
 		    ]
};
myChart.off("click");// 防止累计触发
myChart.on('click', function (params) {
		var name = params.name;
		layer.open({
			  type: 2, 
			  title:  '增殖放流措施统计('+name+')',
			  area: areaWidth,
			  skin: 'layui-layer-molv' ,
			  icon: 6,
			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+18,
		});
	});
$("#zzfl_id").off("click")
$('#zzfl_id').click(function(){
 	 if( $('#mainonedes_zzfl').hasClass('desplay')){
 		 $('#zzfl_id').text('切换图表');
 		 $('#mainonedes_zzfl').removeClass('desplay');
 		 if($('#zzfl_dc_id').hasClass('desplay')){
			 $('#zzfl_dc_id').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
 		 initTable("zzfl_id")
 	 }
 	  else{
 		  $('#mainonedes_zzfl').addClass('desplay');
 		  $('#zzfl_id').text('切换表格');
 		 $('#zzfl_dc_id').addClass('desplay');
 	 }
  })
myChart.setOption(option);
window.onresize = myChart.resize;
    // 开展项目环评情况统计
		        var myChart = echarts.init(document.getElementById('hpqk'));
		        var option = {
		        		  title : {
		        		        text: title2,
		        		      x:'center'
		        		    },
		        		 tooltip : {
		        		        trigger: 'axis',
		        		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		        		            type : 'shadow'        // 默认为直线，可选为：'line'
															// | 'shadow'
		        		        }
		        		    },
		        		    legend: {
		        		    	orient: 'vertical',
		        		        right: 5,
		        		        top: 5,
		        		        bottom: 10,
		        		        data:['开展环评','未开展环评']
		        		    },
		        		    color:['#99d090','#86afd4'],
		        		    grid: {
		        		        left: '3%',
		        		        right: '4%',
		        		        bottom: '3%',
		        		        containLabel: true
		        		    },
		        		    xAxis : [
		        		        {
		        		            type : 'category',
		        		            data :x2,
		        		            axisLabel:{
		   						     interval:0,// 横轴信息全部显示
		   						}
		        		        }
		        		    ],
		        		    yAxis : [
		        		        {
		        		         	name:'座',
		        		            type: 'value',
		     	        	        
		        		        }
		        		    ],
		        		    series : [
		        		        {
		        		            name:'开展环评',
		        		            type:'bar',
		        		            data:hp1
		        		        },
		        		        {
		        		            name:'未开展环评',
		        		            type:'bar',
		        		            data:hp2,
		        	
		        		        },
		        		       
		        		    ]
		        }; 
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		var  seriesName=params.seriesName;
		        		layer.open({
		        			  type: 2, 
		        			  title:  ''+seriesName +'('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+32+"&seriesName="+seriesName,
		        		});
		        	});
		        myChart.on('mousemove', function (params) {//鼠标指向图表标题时显示右面的表格
	        		var name = params.name;
	        		$("#show_table1").show();
	        		$("#tcq_div").hide();
	        	});myChart.on('mouseout', function (params) {//移开
	        		var name = params.name;
	        		$("#show_table1").hide();
	        		$("#tcq_div").show();
	        	});

		        $("#hpqk_id").off("click")
		        $('#hpqk_id').click(function(){
		         	 if( $('#mainonedes_hpqk').hasClass('desplay')){
		         		 $('#hpqk_id').text('切换图表');
		         		 $('#mainonedes_hpqk').removeClass('desplay');
		         		 if($('#hpqk_dc_id').hasClass('desplay')){
		        			 $('#hpqk_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		         		 initTable_hpqk("hpqk_id")
		         	 }
		         	  else{
		         		  $('#mainonedes_hpqk').addClass('desplay');
		         		  $('#hpqk_id').text('切换表格');
		         		 $('#hpqk_dc_id').addClass('desplay');
		         	 }
		          })
		        myChart.setOption(option);
		        window.onresize = myChart.resize;
		        
		        // 投产前完成环评审批情况统计
		        var myChart = echarts.init(document.getElementById('huanpingduibisecond'));
		        var option = {
		        		  title : {
		      		        text: title3,
		      		      x:'center'
		      		    },
		        		 tooltip : {
		        		        trigger: 'axis',
		        		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		        		            type : 'shadow'        // 默认为直线，可选为：'line'
															// | 'shadow'
		        		        },
		        		    },
		        		    legend: {
		        		    	orient: 'vertical',
		        		        right: 5,
		        		        top: 5,
		        		        bottom: 10,
		        		        data:['完成环评审批手续','未完成环评审批手续']
		        		    },
		        		    color:['#99d090','#86afd4'],
		        		    grid: {
		        		        left: '3%',
		        		        right: '4%',
		        		        bottom: '3%',
		        		        containLabel: true
		        		    },
		        		    xAxis : [
		        		        {
		        		            type : 'category',
		        		            data : x2,
		        		            axisLabel:{
			   						     interval:0,// 横轴信息全部显示
			   						}
		        		        }
		        		    ],
		        		    yAxis : [
		        		        {
		        		        	name:'座',
		        		        	 type: 'value',
		        		        }
		        		    ],
		        		    series : [
		        		        {
		        		            name:'完成环评审批手续',
		        		            type:'bar',
		        		            data:tc1
		        		        },
		        		        {
		        		            name:'未完成环评审批手续',
		        		            type:'bar',
		        		            data:tc2,
		        		        },
		        		    ]
		        };
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		var  seriesName=params.seriesName;
		        		layer.open({
		        			  type: 2, 
		        			  title:  ''+seriesName +'('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+33+"&seriesName="+seriesName,
		        		});
		        	});
		        myChart.on('mousemove', function (params) {//鼠标指向图表标题时显示左面的表格
	        		var name = params.name;
	        		$("#show_table2").show();
	        		$("#kzxmhpqk_div").hide();
	        	});myChart.on('mouseout', function (params) {//移开
	        		var name = params.name;
	        		$("#show_table2").hide();
	        		$("#kzxmhpqk_div").show();
	        	});
		        $("#tcqhpqk_id").off("click")
		        $('#tcqhpqk_id').click(function(){
		         	 if( $('#mainonedes_tcqhpqk').hasClass('desplay')){
		         		 $('#tcqhpqk_id').text('切换图表');
		         		 $('#mainonedes_tcqhpqk').removeClass('desplay');
		         		 if($('#tcqhpqk_dc_id').hasClass('desplay')){
		        			 $('#tcqhpqk_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		         		 initTable_hpqk("tcqhpqk_id")
		         	 }
		         	  else{
		         		  $('#mainonedes_tcqhpqk').addClass('desplay');
		         		  $('#tcqhpqk_id').text('切换表格');
		         		 $('#tcqhpqk_dc_id').addClass('desplay');
		         	 }
		          })
		        myChart.setOption(option);
		        window.onresize = myChart.resize;
		        //是否开展项目环评 ,因为是最后加的所以ID后面加了20
		        var myChart = echarts.init(document.getElementById('hbtj20'));
		        var option = {
		         		  title : {
		         		        text: '是否开展项目环评',
		         		        x:'center'
		         		    },
		         		 tooltip: {
		         		        trigger: 'item',
		         		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		         		    },
		         		    legend: {
		              		     x : 'center',
		           		        y : 'bottom',
		              		        data:['是','否','未知',]
		              		    },
		         		     color:colors,
		         		    series: [
		         		    	 {
		        	     		            name:'总数（座）',
		        	     		            type:'pie',
		        	     		            center:['50%','50%'],
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
		        	     		           data:[{'value':kzxmhpqkTotal[0].value+kzxmhpqkTotal[1].value+kzxmhpqkTotal[2].value , "name":'在建及运行(座)' }]
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
		         		            data:kzxmhpqkTotal
		         		        }
		         		    ]
		        };
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		layer.open({
		        			  type: 2, 
		        			  title: '是否开展项目环评('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+35,
		        		});
		        	});
		        $("#hp_id").off("click");
		        $('#hp_id').click(function(){
		        	 if( $('#mainonedes_hp').hasClass('desplay')){
		        		 $('#hp_id').text('切换图表');
		        		 $('#mainonedes_hp').removeClass('desplay');
		        		 if($('#hp_dc_id').hasClass('desplay')){
		        			 $('#hp_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		        		 initTable("hp_id")
		        	 }
		        	  else{
		        		  $('#mainonedes_hp').addClass('desplay');
		        		  $('#hp_id').text('切换表格');
		        		  $('#hp_dc_id').addClass('desplay');
		        	 }
		         })
		        myChart.setOption(option);
		        window.onresize = myChart.resize;     
		        //是否有环评审批
		        var myChart = echarts.init(document.getElementById('hbtj21'));
		        var option = {
		         		  title : {
		         		        text: '是否有环评审批',
		         		        x:'center'
		         		    },
		         		 tooltip: {
		         		        trigger: 'item',
		         		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		         		    },
		         		    legend: {
		              		     x : 'center',
		           		        y : 'bottom',
		              		        data:['是','否','未知',]
		              		    },
		         		     color:colors,
		         		    series: [
		         		    	 {
		        	     		            name:'总数（座）',
		        	     		            type:'pie',
		        	     		            center:['50%','50%'],
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
		        	     		           data:[{'value':sfyhpspTotal[0].value+sfyhpspTotal[1].value+sfyhpspTotal[2].value , "name":'在建及运行(座)' }]
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
		         		            data:sfyhpspTotal
		         		        }
		         		    ]
		        };
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		layer.open({
		        			  type: 2, 
		        			  title: '是否有环评审批('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+36,
		        		});
		        	});
		        myChart.setOption(option);
		        window.onresize = myChart.resize;     
		        $("#hpsp_id").off("click");
		        $('#hpsp_id').click(function(){
		        	 if( $('#mainonedes_hpsp').hasClass('desplay')){
		        		 $('#hpsp_id').text('切换图表');
		        		 $('#mainonedes_hpsp').removeClass('desplay');
		        		 if($('#hpsp_dc_id').hasClass('desplay')){
		        			 $('#hpsp_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		        		 initTable("hp_id1")
		        	 }
		        	  else{
		        		  $('#mainonedes_hpsp').addClass('desplay');
		        		  $('#hpsp_id').text('切换表格');
		        		  $('#hpsp_dc_id').addClass('desplay');
		        	 }
		         })
		        
		        
		        var myChart = echarts.init(document.getElementById('hbtj5'));
		        var option = {
		         		  title : {
		         		        text: '是否符合规划',
		         		        x:'center'
		         		    },
		         		 tooltip: {
		         		        trigger: 'item',
		         		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		         		    },
		         		    legend: {
		              		     x : 'center',
		           		        y : 'bottom',
		              		        data:['是','否','未知',]
		              		    },
		         		     color:colors,
		         		    series: [
		         		    	 {
		        	     		            name:'在建及运行',
		        	     		            type:'pie',
		        	     		            center:['50%','50%'],
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
		        	     		           data:[{'value':fhghTotal[0].value+fhghTotal[1].value+fhghTotal[2].value , "name":'在建及运行(座)' }]
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
		         		            data:fhghTotal
		         		        }
		         		    ]
		        };
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		layer.open({
		        			  type: 2, 
		        			  title: '是否符合规划('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+19,
		        		});
		        	});
		        $("#gh_id").off("click")
		        $('#gh_id').click(function(){
		        	 if( $('#mainonedes_gh').hasClass('desplay')){
		        		 $('#gh_id').text('切换图表');
		        		 $('#mainonedes_gh').removeClass('desplay');
		        		 if($('#gh_dc_id').hasClass('desplay')){
		        			 $('#gh_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		        		 initTable("gh_id")
		        	 }
		        	  else{
		        		  $('#mainonedes_gh').addClass('desplay');
		        		  $('#gh_id').text('切换表格');
		        		  $('#gh_dc_id').addClass('desplay');
		        	 }
		         })
		        myChart.setOption(option);
		        window.onresize = myChart.resize;     
		        
		        var myChart = echarts.init(document.getElementById('hbtj6'));
		        var option = {
		         		  title : {
		         		        text: '是否符合规划环评',
		         		        x:'center'
		         		    },
		         		 tooltip: {
		         		        trigger: 'item',
		         		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		         		    },
		         		    legend: {
		              		     x : 'center',
		           		        y : 'bottom',
		              		        data:['是','否','未知',]
		              		    },
		         		     color:colors,
		         		    series: [
		         		    	 {
		        	     		            name:'在建及运行',
		        	     		            type:'pie',
		        	     		            center:['50%','50%'],
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
		        	     		           data:[{'value':ghhpTotal[0].value+ghhpTotal[1].value+ghhpTotal[2].value, "name":'在建及运行(座)' }]
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
		         		            data:ghhpTotal
		         		        }
		         		    ]
		        };
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		layer.open({
		        			  type: 2, 
		        			  title: '是否符合规划环评('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+20,
		        		});
		        	});
		        $("#ghhp_id").off("click")
		        $('#ghhp_id').click(function(){
		        	 if( $('#mainonedes_ghhp').hasClass('desplay')){
		        		 $('#ghhp_id').text('切换图表');
		        		 $('#mainonedes_ghhp').removeClass('desplay');
		        		 if($('#ghhp_dc_id').hasClass('desplay')){
		        			 $('#ghhp_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		        		 initTable("ghhp_id")
		        	 }
		        	  else{
		        		  $('#mainonedes_ghhp').addClass('desplay');
		        		  $('#ghhp_id').text('切换表格');
		        		  $('#ghhp_dc_id').addClass('desplay');
		        	 }
		         })
		        myChart.setOption(option);
		        window.onresize = myChart.resize;     
		        
		        var myChart = echarts.init(document.getElementById('hbtj7'));
		        var option = {
		         		  title : {
		         		        text: '是否通过竣工环保验收',
		         		        x:'center'
		         		    },
		         		 tooltip: {
		         		        trigger: 'item',
		         		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		         		    },
		         		    legend: {
		              		     x : 'center',
		           		        y : 'bottom',
		              		        data:['是','否','未知',]
		              		    },
		         		     color:colors,
		         		    series: [
		         		    	 {
		        	     		            name:'总数',
		        	     		            type:'pie',
		        	     		            center:['50%','50%'],
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
		        	     		           data:[{'value':hbysTotal[0].value+hbysTotal[1].value+hbysTotal[2].value, "name":'在建及运行(座)' }]
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
		         		            data:hbysTotal
		         		        }
		         		    ]
		        };
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		layer.open({
		        			  type: 2, 
		        			  title: '是否通过竣工环保验收('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+21,
		        		});
		        	});
		        $("#jgys_id").off("click")
		        $('#jgys_id').click(function(){
		        	 if( $('#mainonedes_jgys').hasClass('desplay')){
		        		 $('#jgys_id').text('切换图表');
		        		 $('#mainonedes_jgys').removeClass('desplay');
		        		 if($('#jgys_dc_id').hasClass('desplay')){
		        			 $('#jgys_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		        		 initTable("jgys_id")
		        	 }
		        	  else{
		        		  $('#mainonedes_jgys').addClass('desplay');
		        		  $('#jgys_id').text('切换表格');
		        		  $('#jgys_dc_id').addClass('desplay');
		        	 }
		         })

		        myChart.setOption(option);
		        window.onresize = myChart.resize;     
		        
		        var myChart = echarts.init(document.getElementById('hbtj8'));
		        var option = {
		         		  title : {
		         		        text: '是否存在坝下脱水干涸情况',
		         		        x:'center'
		         		    },
		         		 tooltip: {
		         		        trigger: 'item',
		         		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		         		    },
		         		    legend: {
		              		     x : 'center',
		           		        y : 'bottom',
		              		        data:['是','否','未知',]
		              		    },
		         		     color:colors,
		         		    series: [
		         		    	 {
		        	     		            name:'总数',
		        	     		            type:'pie',
		        	     		            center:['50%','50%'],
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
		        	     		           data:[{'value':tsgkTotal[0].value+tsgkTotal[1].value+tsgkTotal[2].value , "name":'在建及运行(座)' }]
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
		         		            data:tsgkTotal
		         		        }
		         		    ]
		        };

		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		layer.open({
		        			  type: 2, 
		        			  title:  '是否存在坝下脱水干涸情况('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+22,
		        		});
		        	});
		        $("#bxtsgh_id").off("click")
		        $('#bxtsgh_id').click(function(){
		        	 if( $('#mainonedes_bxtsgh').hasClass('desplay')){
		        		 $('#bxtsgh_id').text('切换图表');
		        		 $('#mainonedes_bxtsgh').removeClass('desplay');
		        		 if($('#bxtsgh_dc_id').hasClass('desplay')){
		        			 $('#bxtsgh_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		        		 initTable("bxtsgh_id")
		        	 }
		        	  else{
		        		  $('#mainonedes_bxtsgh').addClass('desplay');
		        		  $('#bxtsgh_id').text('切换表格');
		        		  $('#bxtsgh_dc_id').addClass('desplay');
		        	 }
		         })
		        myChart.setOption(option);
		        window.onresize = myChart.resize;     
		        
		        
		        
		        // 涉及自然保护区情况统计
		        var myChart = echarts.init(document.getElementById('huanpingduibithree'));
		        // 指定图表的配置项和数据
		        var option = {
		        		title : {
		        	        text: title4,
		        	        x:'center'
		        	    },
		        	    tooltip : {
		        	        trigger: 'item',
		        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
		        	    },
		        	    legend: {
		        	        x : 'center',
		        	        y : 'bottom',
		        	        data:['是','否','未知','核心区（未分区）','缓冲区','试验区']
		        	    },
		        	     color:['#007bbb','#008e57',' #989898','#933d92','#c48f00','#A0522D'],
		        	    toolbox: {
		        	        show : true,
		        	        feature : {
		        	            mark : {show: true},
		        	            dataView : {show: true, readOnly: false},
		        	            magicType : {
		        	                show: true,
		        	                type: ['pie', 'funnel']
		        	            },
		        	            restore : {show: true},
		        	            saveAsImage : {show: true}
		        	        }
		        	    },
		        	    calculable : true,
		        	    series : [
		        	        {
		        	            name:'涉及自然保护区情况统计',
		        	            type:'pie',
		        	            radius : '55%',
		        	            center : ['25%', '50%'],
		        	            label: {
		        	                emphasis: {
		        	                    show: true
		        	                }
		        	            },
		        	            lableLine: {
		        	                normal: {
		          		                 position: 'center',
		    		                    formatter: '{b}\n {c}',
		    		                    color:'white'
		          		                },
		        	                emphasis: {
		        	                    show: true
		        	                }
		        	            },
		        	            data:sfsjzrbhq,
		        	            itemStyle:{ 
		        	                normal:{ 
		        	                      label:{ 
		        	                        show: true, 
		        	                        formatter: '{b} : {c} ({d}%)' 
		        	                      }, 
		        	                      labelLine :{show:true} 
		        	                    } 
		        	                }
		        	        },
		        	        {
		        	            name:'涉及自然保护区',
		        	            type:'pie',
		        	            radius : '55%',
		 		                normal: {
		 		                    show: true,
		 		                    formatter: '{b}: {c}({d}%)'
		 		                },
		 		                
		        	            center : ['75%', '50%'],
		        	            data:sjzrbhqqk,
		        	            itemStyle:{ 
		        	                normal:{ 
		        	                      label:{ 
		        	                        show: true, 
		        	                        formatter: '{b} : {c} ({d}%)' 
		        	                      }, 
		        	                      labelLine :{show:true} 
		        	                    } 
		        	                }
		        	        }
		        	    ]
		        };
		        myChart.off("click");// 防止累计触发
		        myChart.on('click', function (params) {
		        		var name = params.name;
		        		var  seriesName=params.seriesName;
		        		layer.open({
		        			  type: 2, 
		        			  title:  ''+seriesName +'('+name+')',
		        			  area: areaWidth,
		        			  skin: 'layui-layer-molv' ,
		        			  icon: 6,
		        			  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+34+"&seriesName="+seriesName,
		        		});
		        	});
		        $("#sjzrbhqk_id").off("click")
		        $('#sjzrbhqk_id').click(function(){
		        	 if( $('#mainonedes_sjzrbhqk').hasClass('desplay')){
		        		 $('#sjzrbhqk_id').text('切换图表');
		        		 $('#mainonedes_sjzrbhqk').removeClass('desplay');
		        		 if($('#sjzrbhqk_dc_id').hasClass('desplay')){
		        			 $('#sjzrbhqk_dc_id').removeClass('desplay');
		        		 }else{
		        			 $('#table-iconone').addClass('desplay');
		        		 }
		        		 initTable_sjzrbhqk("sjzrbhqk_id")
		        	 }
		        	  else{
		        		  $('#mainonedes_sjzrbhqk').addClass('desplay');
		        		  $('#sjzrbhqk_id').text('切换表格');
		        		  $('#sjzrbhqk_dc_id').addClass('desplay');
		        	 }
		         })
		        // 使用刚指定的配置项和数据显示图表。
		        myChart.setOption(option);
		        window.onresize = myChart.resize;
		       
	}      
//通用导出
function export_sthj(num){
	var arr=getcheckId();
	 var fileName;//定义文件名称
	 var tabTitle;//表头
	 var sqlTitle;//sqlTitle
	 var sql;//sql，动态
	 var tabType;
	if(num==1){
		tabType="1";
		 fileName = "生态环境流量泄放设施统计" ;
		  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）','有生态流量泄放设施小水电总装机规模（千瓦）','有生态流量泄放设施小水电电站数量（座）'
			 ,'无生态流量泄放设施总装机规模（千瓦）','无生态流量泄放设施电站数量（座）','未知生态流量泄放设施总装机规模（千瓦）','未知生态流量泄放设施电站数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.stllxfcs = '有' then H.zjrl else 0 end )AS zrl1, "
			      +"SUM(CASE WHEN H.stllxfcs = '有' then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.stllxfcs = '无' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.stllxfcs = '无' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.stllxfcs IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.stllxfcs IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +"from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ") " 
			      +"ORDER BY A.id   "
	}else if(num==2){
		tabType="1";
		 fileName = "生态流量监控措施统计" ;
		  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）','有生态流量监控措施小水电总装机规模（千瓦）','有生态流量监控措施小水电总数量（座）'
			 ,'无生态流量监控措施小水电总装机规模（千瓦）','无生态流量监控措施小水电总数量（座）','未知生态流量监控措施小水电总装机规模（千瓦）','未知生态流量监控措施小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.stlljkss = '有' then H.zjrl else 0 end )AS zrl1, "
			      +"SUM(CASE WHEN H.stlljkss = '有' then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.stlljkss = '无' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.stlljkss = '无' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.stlljkss IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.stlljkss IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +"from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ") " 
			      +"ORDER BY A.id   "
	}else if(num==3){
		tabType="1";
		 fileName = "过鱼设施统计" ;
		  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）','有过鱼设施小水电总装机规模（千瓦）','有过鱼设施小水电总数量（座）'
			 ,'无过鱼设施小水电总装机规模（千瓦）','无过鱼设施小水电总数量（座）','未知过鱼设施小水电总装机规模（千瓦）','未知过鱼设施小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.gycs = '有' then H.zjrl else 0 end )AS zrl1, "
			      +"SUM(CASE WHEN H.gycs = '有' then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.gycs = '无' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.gycs = '无' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.gycs IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.gycs IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +"from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ") " 
			      +"ORDER BY A.id   "
	}else if(num==4){
		tabType="1";
		 fileName = "增殖放流措施统计" ;
		  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）','有增殖放流措施小水电总装机规模（千瓦）','有增殖放流措施小水电总数量（座）'
			 ,'无增殖放流措施小水电总装机规模（千瓦）','无增殖放流措施小水电总数量（座）','未知增殖放流措施小水电总装机规模（千瓦）','未知增殖放流措施小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.zzflcs = '有' then H.zjrl else 0 end )AS zrl1, "
			      +"SUM(CASE WHEN H.zzflcs = '有' then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.zzflcs = '无' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.zzflcs = '无' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.zzflcs IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.zzflcs IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +"from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ") " 
			      +"ORDER BY A.id   "
	}else if(num==5){
		tabType="2";
		 fileName = "开展项目环评情况统计" ;
		  tabTitle = ['序号','年份','总装机数量（千瓦）','总电站数量（座）','开展项目环评小水电总装机规模（千瓦）','开展项目环评小水电总数量（座）'
			 ,'未开展项目环评小水电总装机规模（千瓦）','未开展项目环评小水电总数量（座）']
		  sqlTitle = [     'date','zrl','zdz','zrl1','zdz1','zrl2','zdz2']
		  sql = " select D.date,ISNULL(SUM(H.zjrl), 0) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.sftgxmhp = '是' then H.zjrl else 0 end )AS zrl1, "
			      +" SUM(CASE WHEN H.sftgxmhp = '是' then 1 else 0 end )AS zdz1, "
			      +" SUM(CASE WHEN H.sftgxmhp IN ('否','',NULL) then H.zjrl else 0 end )AS zrl2, "
			      +" SUM(CASE WHEN H.sftgxmhp IN ('否','',NULL) then 1 else 0 end )AS zdz2  "
			      +" from date_sum D  LEFT JOIN  hps_info_sum1 H  ON DATENAME(yy, H.dysj) = D.date AND H.sd_shi_id IN (" + arr + ")   "
			      +"GROUP BY D.date HAVING D.date IS NOT NULL AND D.date <= GETDATE()   "
			      +" ORDER BY D.date DESC  "
	}else if(num==6){
		tabType="2";
		 fileName = "投产前完成环评审批情况统计" ;
		  tabTitle = ['序号','年份','总装机数量（千瓦）','总电站数量（座）','投产前完成环评审批小水电总装机规模（千瓦）','投产前完成环评审批小水电总数量（座）'
			 ,'投产前未完成环评审批小水电总装机规模（千瓦）','投产前未完成环评审批小水电总数量（座）']
		  sqlTitle = [     'date','zrl','zdz','zrl1','zdz1','zrl2','zdz2']
		  sql = " select D.date,ISNULL(SUM(H.zjrl), 0) AS zrl,COUNT(H.id) AS zdz, "
			      +" SUM(CASE WHEN H.hpspsj > H.dysj then H.zjrl else 0 end )AS zrl1, "
			      +" SUM(CASE WHEN H.hpspsj > H.dysj  then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.hpspsj < H.dysj  then H.zjrl else 0 end )AS zrl2, "
			      +" SUM(CASE WHEN H.hpspsj < H.dysj  then 1 else 0 end )AS zdz2 "
			      +" from date_sum D  LEFT JOIN  hps_info_sum1 H  ON DATENAME(yy, H.dysj) = D.date  AND H.sd_shi_id IN (" + arr + ")   "
			      +"GROUP BY D.date HAVING D.date IS NOT NULL AND D.date <= GETDATE()   "
			      +" ORDER BY D.date DESC  "
	}else if(num==7){
		tabType="1";
		 fileName = "是否符合规划" ;
		  tabTitle = ['序号','地区名称','总装机数量（千瓦）','总电站数量（座）','是符合规划小水电总装机规模（千瓦）','是符合规划小水电总数量（座）'
			 ,'否符合规划小水电总装机规模（千瓦）','否符合规划小水电总数量（座）' ,'未知符合规划小水电总装机规模（千瓦）','未知符合规划小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.sffhgh = '是' then H.zjrl else 0 end )AS zrl1,"
			      +" SUM(CASE WHEN H.sffhgh = '是' then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.sffhgh = '否' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.sffhgh = '否' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.sffhgh IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.sffhgh IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +" from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ")  "
			      +" ORDER BY A.id  "
	}else if(num==8){
		tabType="1";
		 fileName = "是否符合规划环评" ;
		  tabTitle = ['序号','地区名称','总装机数量（千瓦）','总电站数量（座）','是符合规划环评小水电总装机规模（千瓦）','是符合规划环评小水电总数量（座）'
			 ,'否符合规划环评小水电总装机规模（千瓦）','否符合规划环评小水电总数量（座）' ,'未知符合规划环评小水电总装机规模（千瓦）','未知符合规划环评小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,   "
			      +" SUM(CASE WHEN H.sffhghhp = '是' then H.zjrl else 0 end )AS zrl1, "
			      +" SUM(CASE WHEN H.sffhghhp = '是' then 1 else 0 end )AS zdz1,   "
			      +" SUM(CASE WHEN H.sffhghhp = '否' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.sffhghhp = '否' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.sffhghhp IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.sffhghhp IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +" from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ")  "
			      +" ORDER BY A.id  "
	}else if(num==9){
		tabType="1";
		 fileName = "是否通过竣工环保验收" ;
		  tabTitle = ['序号','地区名称','总装机数量（千瓦）','总电站数量（座）','是符合竣工验收小水电总装机规模（千瓦）','是符合竣工验收小水电总数量（座）'
			 ,'否符合竣工验收小水电总装机规模（千瓦）','否符合竣工验收小水电总数量（座）' ,'未知符合竣工验收小水电总装机规模（千瓦）','未知符合竣工验收小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,   "
			      +" SUM(CASE WHEN H.sftgjghbys = '是' then H.zjrl else 0 end )AS zrl1, "
			      +" SUM(CASE WHEN H.sftgjghbys = '是' then 1 else 0 end )AS zdz1,   "
			      +" SUM(CASE WHEN H.sftgjghbys = '否' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.sftgjghbys = '否' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.sftgjghbys IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.sftgjghbys IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +" from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ")  "
			      +" ORDER BY A.id  "
	}else if(num==10){
		tabType="1";
		 fileName = "是否存在坝下脱水干涸情况" ;
		  tabTitle = ['序号','地区名称','总装机数量（千瓦）','总电站数量（座）','是存在坝下脱水干涸小水电总装机规模（千瓦）','是存在坝下脱水干涸小水电总数量（座）'
			 ,'否存在坝下脱水干涸小水电总装机规模（千瓦）','否存在坝下脱水干涸小水电总数量（座）' ,'未知存在坝下脱水干涸小水电总装机规模（千瓦）','未知存在坝下脱水干涸小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,   "
			      +" SUM(CASE WHEN H.bxsfcztsgk = '是' then H.zjrl else 0 end )AS zrl1, "
			      +" SUM(CASE WHEN H.bxsfcztsgk = '是' then 1 else 0 end )AS zdz1,   "
			      +" SUM(CASE WHEN H.bxsfcztsgk = '否' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.bxsfcztsgk = '否' then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.bxsfcztsgk IN ('未知','',NULL) then H.zjrl else 0 end )AS zrl3,  "
			      +"SUM(CASE WHEN H.bxsfcztsgk IN ('未知','',NULL) then 1 else 0 end )AS zdz3  "
			      +" from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +" AND A.id IN (" + arr + ")  "
			      +" ORDER BY A.id  "
	}else if(num==11){
		tabType="1";
		 fileName = "涉及自然保护区情况统计" ;
		  tabTitle = ['序号','地区名称','总装机数量（千瓦）','总电站数量（座）','涉及自然保护区小水电总装机规模（千瓦）','涉及自然保护区小水电总数量（座）'
			 ,'不涉及自然保护区小水电总装机规模（千瓦）','不涉及自然保护区小水电总数量（座）' 
			 ,'涉及核心区小水电总装机规模（千瓦）','涉及核心区小水电总数量（座）'
			 ,'涉及缓冲区小水电总装机规模（千瓦）','涉及缓冲区小水电总数量（座）'
			 ,'涉及试验区小水电总装机规模（千瓦）','涉及试验区小水电总数量（座）'
			 ]
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3','zrl4','zdz4','zrl5','zdz5']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,   "
			      +" SUM(CASE WHEN H.sfsjzrbhq = '是' then H.zjrl else 0 end )AS zrl1,  "
			      +" SUM(CASE WHEN H.sfsjzrbhq = '是' then 1 else 0 end )AS zdz1,   "
			      +" SUM(CASE WHEN H.sfsjzrbhq IN ('否','',NULL) then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.sfsjzrbhq IN ('否','',NULL) then 1 else 0 end )AS zdz2, "
			      +" SUM(CASE WHEN H.hxq = '涉及' then H.zjrl else 0 end )AS zrl3, "
			      +" SUM(CASE WHEN H.hxq = '涉及' then 1 else 0 end )AS zdz3, "
			      +" SUM(CASE WHEN H.hcq = '涉及' then H.zjrl else 0 end )AS zrl4, "
			      +"  SUM(CASE WHEN H.hcq = '涉及' then 1 else 0 end )AS zdz4,  "
			      +" SUM(CASE WHEN H.sys = '涉及' then H.zjrl else 0 end )AS zrl5,  "
			      +" SUM(CASE WHEN H.sys = '涉及' then 1 else 0 end )AS zdz5 "
			      +" from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id   "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0   "
			      +" AND A.id IN (" + arr + ")  "
			      +"  ORDER BY A.id  "
	}else if(num==20){
		tabType="1";
		 fileName = "是否开展项目环评统计" ;
		  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）','开展项目环评小水电总装机规模（千瓦）','开展项目环评小水电总数量（座）'
				 ,'未开展项目环评小水电总装机规模（千瓦）','未开展项目环评小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,   "
			      +" SUM(CASE WHEN H.sffhghhp = '是' then H.zjrl else 0 end )AS zrl1,   "
			      +"  SUM(CASE WHEN H.sffhghhp = '是' then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.sffhghhp = '否' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.sffhghhp = '否' then 1 else 0 end )AS zdz2,  "
			      +" SUM(CASE WHEN H.sffhghhp  is null    then H.zjrl else 0 end )AS zrl3, "
			      +" SUM(CASE WHEN H.sffhghhp  is null    then 1 else 0 end )AS zdz3 "
			      +" from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  WHERE H.jszt in(1,2) "
			      +"   GROUP BY A.name,A.id HAVING COUNT(H.id) > 0   "
			      +" AND A.id IN (" + arr + ")   "
			      +" ORDER BY A.id  "
	}else if(num==21){
		tabType="1";
		 fileName = "是否有环评审批统计" ;
		  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）','开展项目环评小水电总装机规模（千瓦）','开展项目环评小水电总数量（座）'
				 ,'未开展项目环评小水电总装机规模（千瓦）','未开展项目环评小水电总数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,   "
			      +" SUM(CASE WHEN H.sffhghhp = '是' then H.zjrl else 0 end )AS zrl1,   "
			      +"  SUM(CASE WHEN H.sffhghhp = '是' then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.sffhghhp = '否' then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.sffhghhp = '否' then 1 else 0 end )AS zdz2,  "
			      +" SUM(CASE WHEN H.sffhghhp  is null    then H.zjrl else 0 end )AS zrl3, "
			      +" SUM(CASE WHEN H.sffhghhp  is null    then 1 else 0 end )AS zdz3 "
			      +" from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  WHERE H.jszt in(1,2) "
			      +"   GROUP BY A.name,A.id HAVING COUNT(H.id) > 0   "
			      +" AND A.id IN (" + arr + ")   "
			      +" ORDER BY A.id  "
	}
	 
	 daochu(fileName,tabTitle,sqlTitle,sql,tabType)
}



function daochu(fileName,tabTitle,sqlTitle,sql,t){
	 $.ajax({
	  	 url : basePath + "sjjs/sjjs!comDetails.action",
	 	 type : "post",
	     dateType : "JSON",
	 	 async : false,
	 	 traditional:true,
	     data : {"uploadBean.tabTitle":tabTitle
	    	 	,"uploadBean.sqlTitle":sqlTitle
	    	 	,"uploadBean.sql":sql
	    	 	,"uploadBean.fileName":fileName
	    	 	,"uploadBean.tabType":t
	    	 	},
	 	 success : function(response){
	  		 window.location.href=basePath + "sjjs/sjjs!comUpload.action"   
	  	 }
	})
}



autodivheight();
function autodivheight(){ // 函数：获取尺寸
    // 获取浏览器窗口高度
    var winHeight=0;
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;
    // 通过深入Document内部对body进行检测，获取浏览器窗口高度
    if (document.documentElement && document.documentElement.clientHeight)
        winHeight = document.documentElement.clientHeight;
    // DIV高度为浏览器窗口的高度
    document.getElementById("tree2").style.height= winHeight-77 +"px";
    
  
}

