var zNodes;
var chanliu;
var huiliu;
$(function(){
	$.ajax({
		url : basePath + "cjfa/cjfa!model.action",
		success : function(data) {
			var obj=JSON.parse(data); //将返回的String转换成Json对象取数据
			$("#createStaff_modelprogram").val(obj.mSysStaff.staffName);//创建人
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
		check:{
			enable: true,
		},
		callback:{
			onClick: zTreeOnClick,
			onCheck: zTreeOnCheck  
		}
	};
	 zNodes= [{RVCD:"",PID:1,NAME:'产流模型',open: true, children:chanliu
	},
				{RVCD:"",PID:2,NAME:'汇流模型',open: true, children: huiliu
		}
	];
	zNodes[0].open=true; //默认展开
	
	function zTreeOnClick(event, treeId, treeNode) {
		$.ajax({
			url : basePath + "myplan/myplan!list.action",
			data:{modeCode:treeNode.RVCD},
			success:function(data){
				query.InitData(data);
			}
		});
		

	};
	var modeCode =[];
	function zTreeOnCheck(event, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("ztree"); 
		var changedNodes = zTree.getChangeCheckedNodes();  
		for ( var i=0 ; i < changedNodes.length ; i++ ){  
		    var treeNode = changedNodes[i];  
		    console.log((treeNode?treeNode.RVCD:"root") + "checked " +(treeNode.checked?"true":"false"));  
		    modeCode.push(treeNode?treeNode.RVCD:"");
		}  
			
		$.ajax({
			async :true,
			url : basePath + "myplan/myplan!list.action",
			data:{modelCode:modeCode},
			success:function(data){
				//alert(data)
			}
		});
			};
	
	$.fn.zTree.init($("#ztree"), setting, zNodes);
}

/*$("#ztree").click(function(){
	
});*/
