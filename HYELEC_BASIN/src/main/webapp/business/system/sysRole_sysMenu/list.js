var sysRole=new $.System_SysRoleQ();
var sysMenu=new $.System_SysMenuQ();
$(function(){
	//检查session
	comm_checksession();
	//字典分类，设置双击事件
	var opt_tb_sysRole={
		rowStyle: function rowStyle(row, index) {
			 	if (row.SYSFLAG==1){
			 		return { classes: 'danger'};
			 	}
				return {};
		       }
		,onDblClickRow:onDblClickRow_sysRole
	}

	//初始化BootStrapTable的数据
	sysRole.Init_TB_Option(opt_tb_sysRole);//设置表格参数
	sysRole.Init_TB_QueryParms();//设置查询及分页参数
	sysRole.Init_TB_Data();//加载数据    
	
	//加载下拉框数据
	sysMenu.LoadRootData($("#select_root_SysMenu"));
	sysMenu.Init_TB_Data($("#tbinfo_TreenmSysMenu"));

	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
	$("#btn_sq_all").bind("click",event_sq_all);
	$("#btn_qx_all").bind("click",event_qx_all);
	$("#select_root_SysMenu").bind("change",changeRoot);
});

//全部授权
function event_sq_all(){
	var menuCode="";
	var sqtype="sq";
	rela_sq(menuCode,sqtype);
}
//全部取消授权
function event_qx_all(){
	var menuCode="";
	var sqtype="qx";
	rela_sq(menuCode,sqtype);
}

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo_ListnmSysRole").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function onDblClickRow_sysRole(row){
	SysRole_filter(row);
}

//下拉框变更事件
function changeRoot(){
	var fCode=$('#select_root_SysMenu').val();
	var roleCode=$("#refApk").val();
	var roleName=$("#roleName").val();
	var obj={
		"ROLE_CODE":roleCode,
		"ROLE_NAME":roleName,
		"FCODE":fCode
	};
	if (!(roleCode.length>0)){
	    var msg = new $.zui.Messager("消息提示：请先选中角色，再进行查看！", {placement: "center",type:"danger"});
	    msg.show();	
		return;
	}
	SysRole_filter(obj);
}
//授权操作
function rela_sq(menuCode,flag){
	var url=basePath+"system/sysoperref!authorize.action";
	var roleCode=$("#S_SysRole").val();
	var obj={
		"mSysOperRefFormBean.mSysOperRefBean.refApk":roleCode
		,"mSysOperRefFormBean.mSysOperRefBean.refAname":"SYS_ROLE"
		,"mSysOperRefFormBean.mSysOperRefBean.refBpk":menuCode
		,"mSysOperRefFormBean.mSysOperRefBean.refBname":"SYS_MENU"
		,"mSysOperRefFormBean.parmBean.parm1":flag
	};
	if (!(roleCode.length>0)){
	    var msg = new $.zui.Messager("消息提示：请先选中角色，再进行授权！", {placement: "center",type:"danger"});
	    msg.show();	
		return;
	}
	$.ajax({
		data:obj,
		url:url,
		type: "POST",
		dataType:"json",
		success:function(response){
			sysMenu.Init_TB_Data($("#tbinfo_TreenmSysMenu"));
			var msg = new $.zui.Messager("消息提示："+response.prompt, {placement: "center",type:"primary"});
		    msg.show();
		    
		}
	});
}

function FMT_Num_SysRole(value,row,index){
	 var pageNumber = $("#tbinfo_ListnmSysRole").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_ListnmSysRole").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}

//操作
function FMT_Oper_SysRole(value,row) {
	var obj={
		"ID"	   :row.ID,	
		"ROLE_CODE":row.ROLE_CODE,
		"ROLE_NAME":row.ROLE_NAME
	}
	return html="<a href='#' title='过滤信息，或在数据行上双击鼠标左键。' onclick='javascript:SysRole_filter("+JSON.stringify(obj)+")'><i class='icon icon-filter'><i></a>"; 
}
//根据角色过滤权限
function SysRole_filter(obj){
	$("#S_SysRole").val(obj.ROLE_CODE);
	$("#refApk").val(obj.ROLE_CODE);
	$("#roleName").val(obj.ROLE_NAME);
	if (obj.ROLE_CODE!="") {
		$("#S_SysRole_Name").val("("+obj.ROLE_CODE+")"+obj.ROLE_NAME);
	}
	sysMenu.Init_TB_Data($("#tbinfo_TreenmSysMenu"));
}