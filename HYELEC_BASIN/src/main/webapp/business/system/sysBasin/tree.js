$(function(){
	loadTreeData();
});

function loadTreeData(){
	var url=basePath+"system/ennmcd!list.action";
	common_ajax(null,url, function(response) {
		initTreeData(response.rows);
	});
}

function initTreeData(treeNodes){
	var setting={
		data:{
			simpleData:{
				enable: true,
				idKey: "RVCD",
				pIdKey: "PID",
				rootPId: ""
			},
			key: {
				name:"NAME"
			}
		},
		check:{
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "s", "N": "s" } 
		},
		view:{
			expandSpeed: "fast",
			showLine: true,
			showTitle: true,
			selectedMulti: false
		},
		callback:{
			onCheck: zTreeOnCheck
		}
	};
	var zNodes=treeNodes;
	zNodes[0].open=true; //默认展开
	function zTreeOnCheck(event, treeId, treeNode) {
		var zNodeId="";
		var zTree = $.fn.zTree.getZTreeObj("basinTree");
		var checkNodes=zTree.getCheckedNodes(true);
		for(var i=0;i<checkNodes.length;i++){
			zNodeId+=checkNodes[i].RVCD+",";
		}
		zNodeId=(zNodeId.substring(zNodeId.length-1)==',')?zNodeId.substring(0,zNodeId.length-1):zNodeId;
		$("#zNodeId").val(zNodeId);
	};
	$.fn.zTree.init($("#basinTree"), setting, zNodes);
}

$("#basin_zTree").click(function(){
	var zNodeId=$("#zNodeId").val();
	var url=basePath+"system/sysgroup!setbasinName.action";
	var obj={
		"mSysGroupFormBean.ids":$("#id_basin").val(),
		"mSysGroupFormBean.mSysGroupInfoBean.basinCode":zNodeId
	}
	if(zNodeId==""){
		var msg = new $.zui.Messager("消息提示：请选择流域！！！", {placement: "center",type:"primary"});
	    msg.show();	
		return false;
	}
	$.ajax({
		data:obj,
		url:url,
		type: "POST",
		dataType:"json",
		success:function(response){
			if(response.success=="success"){
        		message="设置流域成功!!!";
        	}else if(response.error=="error"){
        		message="设置流域失败!!!";
        	}
			$('#set_basin_dialog').modal("hide");
			$("#tbinfo_group").bootstrapTable('refresh');
			var msg = new $.zui.Messager("消息提示："+message, {placement: "center",type:"primary"});
		    msg.show();
		}
	});
});
