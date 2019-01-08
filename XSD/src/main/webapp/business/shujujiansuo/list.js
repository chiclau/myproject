$(function(){
    // showData(); // 初始化页面数据
    jsztInit(); // 下拉框数据查询
    initTable();
    getZtree();
});
var areaWidth=['90%','90%']
//下拉选择数据来源
var sjyl;
function sjly(f){
	var t;
	if(f==1){
	$("#sjly_").val("1")
		t='水利部'
		$("#all_li").show(); //如果选择任意1个部门，可以选全部，选择了全部，就不显示全部
	}else if(f==2){
		$("#sjly_").val("2")
		t='生态环境部'
			$("#all_li").show();
	}else if(f==3){
		$("#sjly_").val("3")
		t='国家能源局'
			$("#all_li").show();
	}else if(f==0){
		$("#sjly_").val(0)
		t='全部'
			$("#all_li").hide();
	}
	$("#sjly_id").text(t);
}
// 下拉框数据查询
function jsztInit(){
	$.ajax({
		url : basePath + "sjjs/sjjs!listjszt.action",
		type : "post",
		dateType : "JSON",
		async : true,
		traditional : true,
		success : function(response){
			var datas = $.parseJSON( response );
			var data = datas.rows;
			var kffs = "";
			var jszt = "";
			var xmszly = "";
			for(var i = 0;i < data.length; i++){
				if(data[i].listnm_sys_dict_cate == "jszt"){
					jszt += "<option value='"+data[i].nm+"'>"+data[i].name+"</option>";
				}
				if(data[i].listnm_sys_dict_cate == "xmszly"){
					xmszly += "<option value='"+data[i].nm+"'>"+data[i].name+"</option>";
				}
				if(data[i].listnm_sys_dict_cate == "kffs"){
					kffs += "<option value='"+data[i].nm+"'>"+data[i].name+"</option>";
				}
			}
			$("#xmszly").append(xmszly);
			$("#jszt").append(jszt);
			$("#kffs").append(kffs);
		}
	})
}

// 表格数据查询
function initTable(query){
	  layui.use('table', function(){
	   	  var table = layui.table;
	   	  table.render({
	   	    elem: '#searchList'
	   	    ,url: basePath+"sjjs/sjjs!list.action"
	   	    ,toolbar: '#toolbarDemo'
	   	    ,title: '数据检索表表'
	   	    ,where: query
	   	    ,cols: [[
	   	      {field:'id',type:'numbers', title:'序号', align:'center', width:80, fixed: 'left',unresize:true}
	   	      ,{field:'xmmc', title:'项目名称', align:'left', width:300, fixed: 'left',event:'details'}
	   	      ,{field:'sheng', title:'省', align:'center', width:150}
	   	      ,{field:'shi', title:'市（州）', align:'center',width:90}
	   	      ,{field:'xian', title:'县（区）',align:'center', width:90}
	   	      ,{field:'xmwz', title:'项目位置',align:'center', width:100}
	   	      ,{field:'xmszly', title:'项目所在流域',align:'center', width:200}
	   	      ,{field:'yjzl', title:'一级支流',align:'center', width:120}
	   	      ,{field:'jthl', title:'具体河流',align:'center', width:100}
	   	      ,{field:'zjrl', title:'装机容量（千瓦）',align:'right', width:140}
	   	      ,{field: 'ztz', title:'总投资（万元）',align:'right',  width:150}
	   	      ,{field: 'sjfdl', title:'实际发电量', align:'right', width:150}
	   	      ,{field: 'tzxz', title:'投资性质', align:'center', width:120}
	   	      ,{field: 'tzly', title:'投资来源', align:'center', width:120}
	   	      ,{field: 'bwqk', title:'并网情况', align:'center', width:120}
	   	      ,{field: 'kfname', title:'开发方式', align:'center', width:120}
	   	      ,{field: 'jsname', title:'建设运营状态', align:'center', width:120}
	   	      ,{field: 'sfyxmhz', title:'是否有项目核准（审批）',align:'center',  width:180}
	   	      ,{field: 'sffhgh', title:'是否符合规划', align:'center', width:120}
	   	    ]]
	   	  	,limit: 30
	   	  	,limits: [30,50,100]
	   	    ,page: true
	   	  });
	   	table.on('row(test1)', function(obj){
	   		debugger;
	   	    var id = obj.data.id;
	   	 var name = obj.data.xmmc;
	   	    layer.open({
			  type: 2, 
			  title: '数据展示',
			  area: ['900px', '600px'],
			  skin: 'layui-layer-molv' ,
			  icon: 6,
			  content: basePath+'business/cezhanchaxun/modalFrames.jsp?id='+id+"&name="+name,
			 
			});
	   	  });
	   	});
	}

// 查询条件赋值并查询数据
function searchData(type){
	/*var xzqh=$("[name='sheng']").val();
	if(xzqh=="长江经济带"){
		xzqh="江苏省,江西省,浙江省,安徽省,湖北省,湖南省,重庆市,四川省,云南省,贵州省,上海市";
	}
	if(xzqh=="其他"){
		xzqh="天津市,河北省,山西省,内蒙古自治区,辽宁省,吉林省,黑龙江省,福建省,山东省,河南省,广东省,广西壮族自治区,海南省,西藏自治区" +
				",陕西省,青海省,宁夏回族自治区,新疆维吾尔自治区";
	}*/
	var data = {
			"formBean.sjly" : $("#sjly_").val(), // 数据来源
			"formBean.xmmc" : $("[name='xmmc']").val(), // 项目名称
			//"formBean.sheng" : xzqh, // 省市
			"formBean.sheng" : xian, // 省市
			"formBean.xmszly" : $("[name='xmszly']").val(), // 所在流域
			"formBean.jszt" : $("[name='jszt']").val(), // 建设运营状态
			"formBean.tcny" : $("[name='tcny']").val(), // 投产时间
			"formBean.tcnyEnd" : $("[name='tcnyEnd']").val(), // 最晚投产时间
			"formBean.zjrl" : $("[name='zjrl']").val(), // 装机容量
			"formBean.zjrlMax" : $("[name='zjrlMax']").val(), // 最大装机容量
			"formBean.kffs" : $("[name='kffs']").val(), // 开发方式
			"formBean.tzxz" : $("[name='tzxz']").val(), // 投资性质 
			"formBean.bwqk" : $("[name='bwqk']").val(), // 并网情况
			"formBean.sfyxmhz" : $("[name='sfyxmhz']").val(), // 是否有项目核准
			"formBean.sffhgh" : $("[name='sffhgh']").val(), // 是否符合规划
			"formBean.sffhghhp" : $("[name='sffhghhp']").val(), // 是否符合规划环评
			"formBean.sftgjghbys" : $("[name='sftgjghbys']").val(), // 是否通过竣工环保验收
			"formBean.sftgxmhp" : $("[name='sftgxmhp']").val(), // 是否通过项目环评
			"formBean.hpsjzytcsj" : $("[name='hpsjzytcsj']").val(), // 环评时间早于投产
			"formBean.sfyjgbg" : $("[name='sfyjgbg']").val(), // 是否有项目竣工报告
			"formBean.stllxfcs" : $("[name='stllxfcs']").val(), // 生态流量泄放措施
			"formBean.stljkss" : $("[name='stljkss']").val(), // 生态流量监控设施
			"formBean.gycs" : $("[name='gycs']").val(), // 过鱼措施
			"formBean.zzflcs" : $("[name='zzflcs']").val(), // 增殖放流措施
			"formBean.sfsjzrbhq" : $("[name='sfsjzrbhq']").val(), // 是否涉及自然保护区
			"formBean.hxq" : $("[name='hxq']").val(), // 涉及核心区
			"formBean.bxsfcztsgk" : $("[name='bxsfcztsgk']").val(), // 坝下是否存在脱水干涸
			"formBean.hcq" : $("[name='hcq']").val(), // 涉及缓冲区
			"formBean.sys" : $("[name='sys']").val(), // 涉及试验区
			"formBean.dztcsjybhqslsjxhgx" : $("[name='dztcsjybhqslsjxhgx']").val(), // 电站投产时间与保护区设立时间先后关系
			"formBean.wfq" : $("[name='wfq']").val() // 保护区未分区
	};
	$("#meiki").html("");
	// 根据选择的添加标签
	addLabel();
	if(type == 1){
		initTable(data);		
	}else{
		daochu(data);
	}
}

// 添加选择条件
function addLabel(){
	if($("[name='xmmc']").val()!=""){
		$("#meiki").append("<div class='meiki'>项目名称</div>");
	}
	if($("[name='sheng']").val()!=""){
		$("#meiki").append("<div class='meiki'>行政区划</div>");
	}
	if($("[name='xmszly']").val()!=""){
		$("#meiki").append("<div class='meiki'>所在流域</div>");
	}
	if($("[name='jszt']").val()!=""){
		$("#meiki").append("<div class='meiki'>建设运营状态</div>");
	}
	if($("[name='kffs']").val()!=""){
		$("#meiki").append("<div class='meiki'>开发方式</div>");
	}
	if($("[name='tzxz']").val()!=""){
		$("#meiki").append("<div class='meiki'>投资性质</div>");
	}
	if($("[name='bwqk']").val()!=""){
		$("#meiki").append("<div class='meiki'>并网情况</div>");
	}
	if($("[name='sffhgh']").val()!=""){
		$("#meiki").append("<div class='meiki'>规划符合状况</div>");
	}
	if($("[name='sfyxmhz']").val()!=""){
		$("#meiki").append("<div class='meiki'>项目审批状况</div>");
	}
	if($("[name='tcny']").val()!="" || $("[name='tcnyEnd']").val()!=""){
		$("#meiki").append("<div class='meiki'>投产时间</div>");
	}
	if($("[name='zjrl']").val()!="" || $("[name='zjrlMax']").val()!=""){
		$("#meiki").append("<div class='meiki'>装机容量</div>");
	}
	if($("[name='sffhghhp']").val()!=""){
		$("#meiki").append("<div class='meiki'>是否符合规划环评</div>");
	}
	if($("[name='sftgjghbys']").val()!=""){
		$("#meiki").append("<div class='meiki'>是否通过竣工环保验收</div>");
	}
	if($("[name='sfyjgbg']").val()!=""){
		$("#meiki").append("<div class='meiki'>是否有项目竣工报告</div>");
	}
	if($("[name='sftgxmhp']").val()!=""){
		$("#meiki").append("<div class='meiki'>是否通过项目环评</div>");
	}
	if($("[name='hpsjzytcsj']").val()!=""){
		$("#meiki").append("<div class='meiki'>环评时间早于投产时间</div>");
	}
	if($("[name='stllxfcs']").val()!=""){
		$("#meiki").append("<div class='meiki'>生态流量泄放措施</div>");
	}
	if($("[name='stljkss']").val()!=""){
		$("#meiki").append("<div class='meiki'>生态流量监控设施</div>");
	}
	if($("[name='gycs']").val()!=""){
		$("#meiki").append("<div class='meiki'>过鱼措施</div>");
	}
	if($("[name='zzflcs']").val()!=""){
		$("#meiki").append("<div class='meiki'>鱼类增殖放流措施</div>");
	}
	if($("[name='bxsfcztsgk']").val()!=""){
		$("#meiki").append("<div class='meiki'>坝下是否存在脱水干涸</div>");
	}
	if($("[name='sfsjzrbhq']").val()!=""){
		$("#meiki").append("<div class='meiki'>是否涉及自然保护区</div>");
	}
	if($("[name='hxq']").val()!=""){
		$("#meiki").append("<div class='meiki'>涉及核心区</div>");
	}
	if($("[name='hcq']").val()!=""){
		$("#meiki").append("<div class='meiki'>涉及缓冲区</div>");
	}
	if($("[name='sys']").val()!=""){
		$("#meiki").append("<div class='meiki'>涉及试验区</div>");
	}
	if($("[name='wfq']").val()!=""){
		$("#meiki").append("<div class='meiki'>保护区未分区</div>");
	}
	if($("[name='dztcsjybhqslsjxhgx']").val()!=""){
		$("#meiki").append("<div class='meiki'>电站投产时间与保护区设立时间先后关系</div>");
	}
}

// 重置查询条件
function resetQuery(){
	$("[name='xmmc']").val("");
	$("[name='sheng']").val(""), // 省市
	$("[name='xmszly']").val(""), // 所在流域
	$("[name='jszt']").val(""), // 建设运营状态
	$("[name='tcny']").val(""), // 投产时间
	$("[name='tcnyEnd']").val(""), // 最晚投产时间
	$("[name='zjrl']").val(""), // 装机容量
	$("[name='zjrlMax']").val(""), // 最大装机容量
	$("[name='kffs']").val(""), // 开发方式
	$("[name='tzxz']").val(""), // 投资性质 
	$("[name='bwqk']").val(""), // 并网情况
	$("[name='sfyxmhz']").val(""), // 是否有项目核准
	$("[name='sffhgh']").val(""), // 是否符合规划
	$("[name='sffhghhp']").val(""), // 是否符合规划环评
	$("[name='sftgjghbys']").val(""), // 是否通过竣工环保验收
	$("[name='sftgxmhp']").val(""), // 是否通过项目环评
	$("[name='hpsjzytcsj']").val(""), // 环评时间早于投产
	$("[name='sfyjgbg']").val(""), // 是否有项目竣工报告
	$("[name='stllxfcs']").val(""), // 生态流量泄放措施
	$("[name='stljkss']").val(""), // 生态流量监控设施
	$("[name='gycs']").val(""), // 过鱼措施
	$("[name='zzflcs']").val(""), // 增殖放流措施
	$("[name='sfsjzrbhq']").val(""), // 是否涉及自然保护区
	$("[name='hxq']").val(""), // 涉及核心区
	$("[name='bxsfcztsgk']").val(""), // 坝下是否存在脱水干涸
	$("[name='hcq']").val(""), // 涉及缓冲区
	$("[name='sys']").val(""), // 涉及试验区
	$("[name='dztcsjybhqslsjxhgx']").val(""), // 电站投产时间与保护区设立时间先后关系
	$("[name='wfq']").val("") // 保护区未分区
	$("#meiki").html("");
}

//时间验证
layui.use(['laydate','jquery'], function(){
    var laydate = layui.laydate;
	//执行一个laydate实例
	laydate.render({
	    elem: '#tcny', //指定元素
	    type: 'date'
	});
	laydate.render({
	    elem: '#tcnyEnd', //指定元素
	    type: 'date'
	});
});
//行政区划
$("#loadTreeDate").on({
	mouseover:function(){
		$("#zTreeDiv").show();
	},
	mouseout:function(){
		$("#zTreeDiv").hide();
	}
});
// 查询树
function getZtree(xzlyqh){
    $.ajax({
    	type: "POST",
    	url:  basePath + "jcxx/jcxx!getZtree.action",
    	dataType: "json",
    	async : true,
        traditional: true,
    	success:function(data){
    		loadzTreeInfo(data.treeData);
    		
    	}
    });
}
var xian = "";
function loadzTreeInfo(jsonArray){
	var setting = {
			data: {
				simpleData: {
					enable: true,
                    idKey: "codeID",
                    pIdKey: "parentID",
                    rootPId: 0
				},
			},
			view: {
				selectedMulti: false,
				},
			check: {
				chkStyle: "checkbox",
					enable: true,
					chkboxType: { "Y" : "ps", "N" : "ps" }
				},
			callback: {
					onCheck: zTreeOnCheck
				}
		};
	var zNodes =jsonArray;
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodes = treeObj.getNodes();
    var node = treeObj.getNodeByParam("name", "长江经济带");
    treeObj.checkNode(node,true,true);
    treeObj.updateNode(node);
    for (var i = 0; i < nodes.length; i++) { //设置节点展开
        treeObj.expandNode(nodes[i], true, false, true);
        treeObj.checkNode(nodes[i], false, false);
    }
  //选中/取消
    function zTreeOnCheck(event,treeId,treeNode) {
    	var arr = new Array();
    	var tree = $.fn.zTree.getZTreeObj('treeDemo');
    	var treeData = tree.getCheckedNodes(true);
    	var shengName = "";
    	xian = "";
    	if (treeData.length != 0) {
    	   for (var i = 0; i < treeData.length; i++) {
    	       if (!treeData[i].isParent) {
    	          // alert(JSON.stringify(treeData[i].name))
    	    	   xian += treeData[i].id + ","
    	        }else{
    	        	if(treeData[i].parentID == 2 || treeData[i].parentID == 10086 ){
    	        		shengName += treeData[i].name + ","
    	        	}
    	        }
    	    }
    	}
    	if(shengName != ""){
    		$("#sheng").val(shengName)
    	}else{
    		$("#sheng").val("")
    	}
    	/*else{
    		if(sheng.indexOf(treeNode.name) != -1 || treeNode.name == "全国"){
    			if(sheng.indexOf(treeNode.name) == 0){
    				sheng = sheng.replace(treeNode.name,"")    				
    			}else{
    				sheng = sheng.replace(","+treeNode.name,"")
    			}
    			$("#sheng").val(sheng)
    		}else{    			
    			$("#sheng").val(sheng + "," + treeNode.name)
    		}
    	}*/
    } 
}

// 根据查询条件导出表格
function daochu(query){
	$.ajax({
		url : basePath + "sjjs/sjjs!exportDetails.action",
		type : "post",
		dateType : "JSON",
		async : true,
        data : query,
		success : function(response){
			window.location.href=basePath + "sjjs/sjjs!uploadDetails.action"			
		}
	})
}

// 当是否通过项目环评选择为是时可选
function xmhp(){
	if($("[name='sftgxmhp']").val() == "是"){
		$("[name='hpsjzytcsj']").removeAttr('disabled');
	}else{
		$("[name='hpsjzytcsj']").attr('disabled','disabled');
		$("[name='hpsjzytcsj']").val("");
	}
}

// 当涉及自然保护区选择为是时可选
function sjbhq(){
	if($("[name='sfsjzrbhq']").val() == "是"){
		$("[name='hcq']").removeAttr('disabled');
		$("[name='hxq']").removeAttr('disabled');
		$("[name='sys']").removeAttr('disabled');
		$("[name='wfq']").removeAttr('disabled');
		$("[name='dztcsjybhqslsjxhgx']").removeAttr('disabled');
	}else{
		$("[name='hcq']").attr('disabled','disabled');
		$("[name='hcq']").val("");
		$("[name='hxq']").attr('disabled','disabled');
		$("[name='hxq']").val("");
		$("[name='sys']").attr('disabled','disabled');
		$("[name='sys']").val("");
		$("[name='wfq']").attr('disabled','disabled');
		$("[name='wfq']").val("");
		$("[name='dztcsjybhqslsjxhgx']").attr('disabled','disabled');
		$("[name='dztcsjybhqslsjxhgx']").val("");
	}
}

jQuery(document).ready(function(){
	var accordionsMenu = $('.cd-accordion-menu');
	if( accordionsMenu.length > 0 ) {
		accordionsMenu.each(function(){
			var accordion = $(this);
			accordion.on('change', 'input[type="checkbox"]', function(){
				var checkbox = $(this);
				console.log(checkbox.prop('checked'));
				( checkbox.prop('checked') ) ? checkbox.siblings('ul').attr('style', 'display:none;').slideDown(300) : checkbox.siblings('ul').attr('style', 'display:block;').slideUp(300);
			});
		});
	}
});




























/*
if(treeNode.name == "全国"){
	if(sheng == "全国"){
		$("#sheng").val(shengName)
		if(shengName == ""){
			$("#sheng").val("")
		}
	}else{
		$("#sheng").val(treeNode.name)
	}
	return false;
}
if(treeNode.name == "长江经济带"){
	if(sheng == "全国"){
		$("#sheng").val("其他")
		var nodes = tree.getNodes();
	    var node = tree.getNodeByParam("name", "其他");
	    tree.checkNode(node,true,true);
	}
	if(sheng == "长江经济带"){
		if(shengName == ""){
			$("#sheng").val("其他")
			var nodes = tree.getNodes();
		    var node = tree.getNodeByParam("name", "其他");
		    tree.checkNode(node,true,true);
		}
	}
}
if(treeNode.name == "其他"){
	if(sheng == "全国"){
		$("#sheng").val("长江经济带")
		var nodes = tree.getNodes();
	    var node = tree.getNodeByParam("name", "长江经济带");
	    tree.checkNode(node,true,true);
	}
	if(sheng == "其他"){
		$("#sheng").val(shengName)
		if(shengName == ""){
			$("#sheng").val("长江经济带")
			var nodes = tree.getNodes();
		    var node = tree.getNodeByParam("name", "长江经济带");
		    tree.checkNode(node,true,true);
		}
	}
	return false;
}
if(sheng == "长江经济带"){
	if(treeNode.name == "长江经济带"){
		$("#sheng").val(shengName)
		if(shengName == ""){
			$("#sheng").val("")
		}
	}else if(treeNode.name == "其他"){
		$("#sheng").val("全国")
	}
	return false;
}
if(sheng == "其他"){
	if(treeNode.name == "其他"){
		$("#sheng").val(shengName)
		if(shengName == ""){
			$("#sheng").val("")
		}
	}else if(treeNode.name == "长江经济带"){
		$("#sheng").val("全国")
	}
	return false;
}*/