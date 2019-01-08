var opt={};
var sysGroup=new $.System_sysGroup();
$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	sysGroup.Init_TB_Option();//设置表格参数
	sysGroup.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysGroup.Init_TB_Data();//加载数据
	//初始化新增、编辑和删除
	sysGroup.InitAddEditDel();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo_group").bootstrapTable("resetView",{"height":$("#tbinfo_group_div").height()}); 
} 

function reset_group(){
	sysGroup.reset();
}

function FMT_Check_group(value,row,index) {
    if (index<0) {
        return {
            disabled: true,
            checked: false
        };
    }
    return value;
}

////////////////////////////格式化BootStrap表中的格式
//列格式化-序号
function FMT_Num_group(value,row,index) {
     //return index+1;
	 var pageNumber = $("#tbinfo_group").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_group").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
// 操作
function FMT_Oper_group(value,row) {
    return html="<a href='#' onclick='javascript:sysGroup.edit("+row.ID+")'><i class='icon icon-edit'><i></a>"; 
}
//格式化流域名称
function FMT_rvnmName(value,row){
	var html="";
	html += "<div class='autocut' title="+value+">"+value+"</div>";
	return html;
}
