var opt={};
var sysAccout=new $.sysAccout();
$(function(){
	//first("selectp","selectc","info_form_user",0,0); //初始化省市区下拉数据
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	sysAccout.Init_TB_Option();//设置表格参数
	sysAccout.Init_TB_QueryParms(opt);//设置查询及分页参数
	sysAccout.Init_TB_Data();//加载数据
	sysAccout.initProvAndCity();//初始化省份与市区数据
	
	//初始化新增、编辑和删除
	sysAccout.InitAddEditDel();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
})

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#tbinfo_user").bootstrapTable("resetView",{"height":comm_getHeight()-55}); 
} 

function reset_user(){
	sysAccout.reset();
}
function FMT_Check_user(value,row,index) {
    if (index<0) {
        return {
            disabled: true,
            checked: false
        };
    }
    return value;
}
////////////////////////////////////////////////
//状态
function FMT_state(value,row,index){
	var html="";
	if(row.USER_STATE=="0"){
		html="未生效";
	}
	if(row.USER_STATE=="1"){
		html="生效";
	}
	return html;
}
//列格式化-序号
function FMT_Num_user(value,row,index) {
	 var pageNumber = $("#tbinfo_user").bootstrapTable('getOptions').pageNumber;
  	 var pageSize =   $("#tbinfo_user").bootstrapTable('getOptions').pageSize;
  	  return (pageNumber-1) * pageSize+index+1;
}
// 操作
function FMT_Oper_user(value,row,index) {
   var html ="<a href='#' title='修改' onclick='sysAccout.edit("+JSON.stringify(row.STAFF_CODE)+")'><i class='icon icon-edit'></i></a>";
   html +="&nbsp;&nbsp;<a href='#' title='审核' onclick='sysAccout._flag_("+JSON.stringify(row.STAFF_CODE)+")'><i class='icon icon-flag'></i></a>";
   html +="&nbsp;&nbsp;<a href='#' title='初始化密码' onclick='sysAccout.initPwd("+JSON.stringify(row.STAFF_CODE)+")'><i class='icon icon-repeat'></i></a>";
   return html;
}
//刷新
function refresh_user(){
	sysAccout.refresh();
}

//省份与市区联动
function selectCityArea(){
	$('#selectc_staff').empty();
	var prov=$('#selectp_staff').val();
    var url=basePath+"system/addvcd!loadCityData.action";
	$.post(url,{"prov":prov},function(data){
		var rows=JSON.parse(data).rows;
		for(var i=0;i<rows.length;i++){
			$('#selectc_staff').append("<option value='"+rows[i].ADDVCD+"'>"+rows[i].NAME+"</option>");
		}
	});
}