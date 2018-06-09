var opt={};
var floodTranQuery=new $.FloodTran_Query
var waterLevelFlowQuery=new $.WaterLevelFlow_Query
var storageCapacityQuery=new $.StorageCapacity_Query
var sectionTestQuery=new $.SectionTest_Query

$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTable的数据
	//延迟500毫秒
	floodTranQuery.InitData(opt);
	floodTranQuery.InitAddEditDel(opt);
	floodTranQuery.InitSelect();
	waterLevelFlowQuery.InitData(opt);
	waterLevelFlowQuery.InitAddEditDel(opt);
	//waterLevelFlowQuery.InitSelect();
	storageCapacityQuery.InitData(opt);
	storageCapacityQuery.InitAddEditDel(opt);
	storageCapacityQuery.InitSelect();
	sectionTestQuery.InitData(opt);
	sectionTestQuery.InitAddEditDel(opt);
	sectionTestQuery.InitSelect();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
});

// 调整界面布局大小
function _AutoSize(){
	// 设置Table的高度
	$("#query_table_floodTran").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
	$("#query_table_waterLevelFlow").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
	$("#query_table_storageCapacity").bootstrapTable("resetView",{"height":comm_getHeight()-80});      
	$("#query_table_sectionTest").bootstrapTable("resetView",{"height":comm_getHeight()-80});  
	
} 
function _reset_(){
	floodTranQuery.reset();
}

////////////////////////////格式化BootStrap表中的格式
//列格式化-选择
function FMT_Check(value,row,index) {
    if (index<0) {
        return {
            disabled: true,
            checked: false
        };
    }
    return value;
}

//列格式化-时间
function fmt_date(value,row,index){
	return new Date(row.MSTM.time).format("yyyy-MM-dd");
}
//列格式化-序号
function FMT_Num1(value,row,index){
	 var pageNumber = $("#query_table_floodTran").bootstrapTable('getOptions').pageNumber;
  	 var pageSize   = $("#query_table_floodTran").bootstrapTable('getOptions').pageSize;
  	 return (pageNumber-1) * pageSize+index+1;
}
function FMT_Num2(value,row,index){
	var pageNumber = $("#query_table_waterLevelFlow").bootstrapTable('getOptions').pageNumber;
	var pageSize   = $("#query_table_waterLevelFlow").bootstrapTable('getOptions').pageSize;
	return (pageNumber-1) * pageSize+index+1;
}
function FMT_Num3(value,row,index){
	var pageNumber = $("#query_table_storageCapacity").bootstrapTable('getOptions').pageNumber;
	var pageSize   = $("#query_table_storageCapacity").bootstrapTable('getOptions').pageSize;
	return (pageNumber-1) * pageSize+index+1;
}
function FMT_Num4(value,row,index){
	var pageNumber = $("#query_table_sectionTest").bootstrapTable('getOptions').pageNumber;
	var pageSize   = $("#query_table_sectionTest").bootstrapTable('getOptions').pageSize;
	return (pageNumber-1) * pageSize+index+1;
}
function FMT_amount(value,row){
	return Number(value).toFixed(2);
}
//操作(洪水传播时间表)
function FMT_handle1(value,row) {
    var html="";
    html+="<a href='#' onclick='javascript:floodTranQuery.edit(\""+row.UPSTCD + "\",\""+row.DWSTCD + "\")'>" +
		"<button class='btn btn-xs btn-primary'>" +
		"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
		"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
		"</button></a>" +
		"&nbsp;&nbsp;" +
		"<a href='#' onclick='javascript:floodTranQuery.del(\""+row.UPSTCD + "\",\""+row.DWSTCD + "\")'>" +
		"<button class='btn btn-xs btn-danger btn_del_color'>" +
		"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>" +
		"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>" +
		"</button></a>" +
		"&nbsp;&nbsp;";
    return html;
}

//水位流量关系曲线
function FMT_handle2(value,row){
	var html="";
	html+="<a href='#' onclick='javascript:waterLevelFlowQuery.edit(\""+row.STCD + "\",\""+row.LNNM + "\")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:waterLevelFlowQuery.del(\""+row.STCD + "\",\""+row.LNNM + "\")'>" +
	"<button class='btn btn-xs btn-danger btn_del_color'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;";
	return html;
}

//库（湖）容曲线
function FMT_handle3(value,row){
	var html="";
	tm="";
	if(row.MSTM!=""){
		tm+=new Date(row.MSTM.time).format("yyyy-MM-dd hh:mm:ss")+",";
	}else{
		tm=row.MSTM;
	}
	html+="<a href='#' onclick='javascript:storageCapacityQuery.edit(\""+row.STCD + "\",\""+tm + "\")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:storageCapacityQuery.del(\""+row.STCD + "\",\""+tm + "\")'>" +
	"<button class='btn btn-xs btn-danger btn_del_color'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;";
	return html;
}

//断面测验成果表
function FMT_handle4(value,row){
	var html="";
	tm="";
	if(row.MSTM!=""){
		tm+=new Date(row.MSTM.time).format("yyyy-MM-dd hh:mm:ss")+",";
	}else{
		tm=row.MSTM;
	}
	html+="<a href='#' onclick='javascript:sectionTestQuery.edit(\""+row.STCD + "\",\""+tm + "\")'>" +
	"<button class='btn btn-xs btn-primary'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-pencil'></i>&nbsp;修改</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-pencil'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;" +
	"<a href='#' onclick='javascript:sectionTestQuery.del(\""+row.STCD + "\",\""+tm + "\")'>" +
	"<button class='btn btn-xs btn-danger btn_del_color'>" +
	"<div class='visible-md visible-lg'><i class='icon icon-trash'></i>&nbsp;删除</div>" +
	"<div class='visible-xs visible-sm'><i class='icon icon-trash'></i></div>" +
	"</button></a>" +
	"&nbsp;&nbsp;";
	return html;
}

