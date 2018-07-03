var opt={};
var sysMenu=new $.System_SysMenu();
$(function(){
	//检查session
	comm_checksession();
	//初始化BootStrapTreeList的数据
	sysMenu.InitData(opt);
	//初始化新增、编辑和删除
	sysMenu.InitAddEditDel();
	// 窗体变化时，调整组件的大小
	$(window).resize(function(){
		_AutoSize();
	});
	_AutoSize();
	
})
// 调整界面布局大小
function _AutoSize(){
	// 设置组件的高度
} 
function _reset(){
	sysMenu.reset();
}
function _add(id){
	sysMenu.add(id);
}
function _edit(id,onlyread){
	sysMenu.edit(id,onlyread);
}
function _removeids(id){
	sysMenu.removeids(id);
}
