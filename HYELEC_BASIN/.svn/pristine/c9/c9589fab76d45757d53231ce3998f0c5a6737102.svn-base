/**
 * 文件上传或导入
 * 
 * 1、url：后台路径
 * 2、myUploader：导入控件唯一ID
 * 3、fun：刷新页面回调方法
 * 
 * */
function filesUpload(url,myUploader,fun){
	var options = {
		url:url,
		filters:{
			mime_types:[{title: 'Excel', extensions: 'xlsx,xls,XLSX,XLS'}],
			max_file_size: '10mb',
			prevent_duplicates: true
		},
		responseHandler:function(responseObject, file){
			if(responseObject.response=='error') {
				var msg = new $.zui.Messager("消息提示：导入失败", {placement: "center",type:"primary"});
				msg.show();	
				return '导入失败';
			}else if(responseObject.response=='success') {
				fun(); //刷新页面
			}
		}
	}
	$('#'+myUploader).uploader(options);
	
}