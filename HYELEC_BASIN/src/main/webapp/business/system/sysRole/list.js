var opt={};
var sysRole=new $.System_Sysrole();
$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	sysRole.Init_TB_Option();//设置表格参数
	sysRole.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysRole.Init_TB_Data();//加载数据
	//初始化新增、编辑和删除
	sysRole.InitAddEditDel();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo_role").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function reset_role(){
	sysRole.reset();
}

function FMT_Check_Role(value,row,index) {
    if(index<0){
    	return {
            disabled: true,
            checked: false
        };
    }
    return value;
}

////////////////////////////////////////////////

////////////////////////////格式化BootStrap表中的格式
//列格式化-序号
function FMT_Num_Role(value,row,index) {
     //return index+1;
	 var pageNumber = $("#tbinfo_role").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_role").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
// 操作
function FMT_Oper_Role(value,row) {
	var html;
	if(row.ROLE_CODE!="admins"){
		html="<a href='#' onclick='javascript:sysRole.edit("+row.ID+")'><i class='icon icon-edit'><i></a>"; 
	}else{
		html="";
	}
    return html;
}
function FMT_Num_menu(value,row){
	var html;
	html="<a href='"+basePath+"business/system/sysRole_sysMenu/list.jsp'>"+row.MENU_NAME+"</a>";
	return html;
}
