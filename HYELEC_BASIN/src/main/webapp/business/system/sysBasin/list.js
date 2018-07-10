var opt={};
var sysBasin=new $.sysBasin();
$(function(){
	comm_checksession();
	sysBasin.InitData(opt); //初始化表格数据
	sysBasin.InitAddEditDel();
});
function _add_(opt){
	sysBasin.add(opt);
}
function _edit_(opt){
	sysBasin.edit(opt);
}
function _removeids_(opt){
	sysBasin.removeids(opt);
}
