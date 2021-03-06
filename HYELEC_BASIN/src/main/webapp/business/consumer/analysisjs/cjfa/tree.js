var zNodes;
var chanliu;
var huiliu;
$(function(){
	$.ajax({
		url : basePath + "cjfa/cjfa!model.action",
		success : function(data) {
			var obj=JSON.parse(data); //将返回的String转换成Json对象取数据
			$("#createStaff_modelprogram").val(obj.mSysStaff.staffName);//创建人
			$("#createTime_modelprogram").val(obj.date);//创建时间
			zNodes=obj.rows;
			chanliu=obj.chanliu;
			huiliu=obj.huiliu;
			initTreeData();
		}
	});
	
});

function initTreeData(){
	//ztree数加载用同步
	var setting={
		data:{
			simpleData:{
				enable: true,
				idKey: "RVCD",
				pIdKey: "PID",
				rootPId: ""
			},
			key: {
				name:"NAME",
			}
		},
		check: {enable: true,chkStyle: "radio",chkboxType: { "Y":"s","N":"s"},radioType: "all"},
		callback:{
			onClick: zTreeOnClick,
			onCheck: zTreeOnCheck  
		}
	};
	 zNodes= [{RVCD:110100101,PID:1,NAME:'产流模型',open: true, children:chanliu
	},
				{RVCD:110100102,PID:2,NAME:'汇流模型',open: true, children: huiliu
		}
	];
	zNodes[0].open=true; //默认展开
	
	function zTreeOnClick(event, treeId, treeNode) {
/*
		$.ajax({
			url : basePath + "cjfa/cjfa!getPara.action",
			data:{MODEL_CODE:treeNode.RVCD},
			success : function(data) {
				var obj=JSON.parse(data); //将返回的String转换成Json对象取数据
			}
		});*/
	};
	
	function zTreeOnCheck(event, treeId, treeNode) {
		$("#modeCode").val(treeNode.RVCD);
		$.ajax({
			async :true,
			url : basePath + "cjfa/cjfa!getPara.action",
			data:{modelCode:treeNode.RVCD},
			success : function(data) {
				var obj=JSON.parse(data); //将返回的String转换成Json对象取数据
				//$("#"+obj.list[i].PARA_CODE+"").remove();
				if(0<obj.list.length){
					$("#clg").empty();//清除然后再加载
					var valueObject={'K':'1.2','B':'0.3','C':'0.1','IM':'0.02','WM':'130','WUM':'0.2','WLM':'0.8','SM':'60','EX':'1.5','KG':'0.4','KI':'0.3'};
					for(var i=0;i<obj.list.length;i++){
						//alert(obj.list[i].PARA_CODE+obj.list[i].PARA_NAME)
						var  name=obj.list[i].PARA_NAME;
						var  cs=obj.list[i].PARA_SYMBOL;
						var code=obj.list[i].PARA_CODE;
						var max=obj.list[i].PARA_MAX;
						var div="";
						div += "<div style='height:30px;margin-top:15px;width:100%;'>" +
							"<div style='width:70%;float:left;text-align:right;line-height:30px;'>" +
							"<span class='text-danger'>*&nbsp;</span>" +name+"("+ cs + ")&nbsp;：" +
							"<input type='hidden' name='modelprogramFormBean.modelParaValueBean.paraCode' value='" + code + "'>" +
							"</div>" +
							"<div style='width:30%;float:right;'>" +
							"  <input type='text' " +
							" class='form-control' style='width:100px;'" +
							" name='modelprogramFormBean.modelParaValueBean.paraValue'" +
							" value='"+(valueObject[cs]!=null?valueObject[cs]:"")+"'"+
							" data-bv-group='.rowGroup'" +
							" required" +
							" maxlength='25'" +
							" data-bv-stringlength-max='" + max + "' data-bv-stringlength-message='不能超过" + max + "'" +
							"placeholder='请输入" + name + "'" +
							" >" +
							"</div> " +
							"</div>";
						$("#clg").append(div);
						}
				}else{
				//	$(".h").empty();
				}
				
				
			}
		});
			};
	$.fn.zTree.init($("#ztree"), setting, zNodes);
}

/*$("#ztree").click(function(){
	
});*/
