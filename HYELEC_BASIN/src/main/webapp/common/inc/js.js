var g_treelist_size=100000000;// 查找TreeList的记录条数
var g_SelectParms={
		       "formBean.pageBean.limit": g_treelist_size  // 记录条数大小
		      ,"formBean.pageBean.offset": 0  // 当前记录偏移条数				
		      ,"formBean.pageBean.sort": "code"  // 排序列名
		      ,"formBean.pageBean.sortOrder": "asc"// 排位命令（desc，asc）
		}   // 使用$.extend函数进行合并，相同项，后面覆盖前面项。

function comm_chose_init(ctrl_select){
	ctrl_select.chosen({
		width: "100%",
		no_results_text: '没有找到',    // 当检索时没有找到匹配项时显示的提示文本
	    disable_search_threshold: 10, // 10 个以下的选择项则不显示检索框
	    search_contains: true         // 从任意位置开始检索
	});
}

var g_menu_state = true;
//BootStrapTable基础Options
var g_bootstrapTable_Options={
		 method:"get"													// 服务器数据的请求方式 'get' or 'post'
	    ,striped:true 													// 设置为 true 会有隔行变色效果
	    ,dataType:"json"                                           		// 服务器返回的数据类型
	    ,pagination:true                           		       			// 设置为true会在表格底部显示分页条
	    ,pageNumber:1                               		        	// 如果设置了分页，首页页码
	    ,pageSize:15                                		        	// 如果设置了分页，页面数据条数
	    ,paginationPreText:"<i class='icon icon-backward'></i> 上页"		// 指定分页条中上一页按钮的图标或文字
	    ,paginationNextText:"下页 <i class='icon icon-forward'></i>"		// 指定分页条中下一页按钮的图标或文字
	    ,paginationHAlign:"right"                                       // 分页靠齐方向
	    ,pageList:[10,15,20,50,100,"All"]								// 如果设置了分页，设置可供选择的页面数据条数。设置为"All"
																		// 则显示所有记录。[10,15,20,50,100,"All"]
	    ,sidePagination:"server" 										// 设置在哪里进行分页，可选值为'client'或者'server'。
	    																//设置'server'时，必须设置服务器数据地址（url）或者重写ajax方法
	    ,uniqueId: "id"													// 每一行的唯一标识，一般为主键列
	    ,sortable:true													// 设置为false将禁止所有列的排序
		,sortName:"id"													// 定义排序列,通过url方式获取数据填写字段名，否则填写下标
		,sortOrder: "desc"	                          					// 定义排序方式 'asc'或者'desc'	    
	    // ,showToggle:true //是否显示 切换试图（table/card）按钮	
	    // ,showPaginationSwitch:true //是否显示 数据条数选择框
	    // ,showRefresh:true //是否显示 刷新按钮
	    // ,search:true //是否启用搜索框
	    // ,searchAlign:"left" //指定 搜索框 水平方向的位置。'left' or 'right'
	    // ,searchOnEnterKey:true //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
	    // ,toolbarAlign:"right" //指定 toolbar 水平方向的位置。'left' or 'right'
	    // ,showColumns:true //是否显示 内容列下拉框，选择显示的列
	    ,showHeader:true                           						// 是否显示列头
	    ,showFooter:false                          						// 是否显示列脚
	    ,showExport:true                                            	// 显示导出按钮
	    ,exportDataType: ""										    	// 导出类型
	    ,keyEvents:true //支持热键<- ->翻页
        // ,detailView:true //设置为 true 可以显示详细页面模式。
        // ,detailFormatter:detailFormatter //详细页面显示函数
	    ,onLoadSuccess:comm_onLoadSuccess  								//数据加载错误
	    ,rowStyle:comm_rowStyle
	    //,height:comm_getHeight()										// 定义表格的高度
	    ,formatShowingRows:comm_formatShowingRows						// 翻页工具条
}

function comm_formatShowingRows(pageFrom, pageTo, totalRows) {
    return  pageFrom + ' 到 ' + pageTo + ' 条，总记录数：' + totalRows + ' 条';
}

var g_bootstrapTable_OptionsNotList={
		 method:"get"													// 服务器数据的请求方式 'get' or 'post'
	    ,striped:true 													// 设置为 true 会有隔行变色效果
	    ,dataType:"json"                                           		// 服务器返回的数据类型
	    //,pagination:true                           		       			// 设置为true会在表格底部显示分页条
	    ,pageNumber:1                               		        	// 如果设置了分页，首页页码
	    ,pageSize:15                                		        	// 如果设置了分页，页面数据条数
	    ,paginationPreText:"<i class='icon icon-backward'></i> 上页"		// 指定分页条中上一页按钮的图标或文字
	    ,paginationNextText:"下页 <i class='icon icon-forward'></i>"		// 指定分页条中下一页按钮的图标或文字
	    ,paginationHAlign:"right"                                       // 分页靠齐方向
	    ,pageList:[10,15,20,50,100,"All"]								// 如果设置了分页，设置可供选择的页面数据条数。设置为"All"
																		// 则显示所有记录。[10,15,20,50,100,"All"]
	    ,sidePagination:"server" 										// 设置在哪里进行分页，可选值为'client'或者'server'。
	    																//设置'server'时，必须设置服务器数据地址（url）或者重写ajax方法
	    ,uniqueId: "id"													// 每一行的唯一标识，一般为主键列
	    ,sortable:true													// 设置为false将禁止所有列的排序
		,sortName:"id"													// 定义排序列,通过url方式获取数据填写字段名，否则填写下标
		,sortOrder: "desc"	                          					// 定义排序方式 'asc'或者'desc'	    
	    // ,showToggle:true //是否显示 切换试图（table/card）按钮	
	    // ,showPaginationSwitch:true //是否显示 数据条数选择框
	    // ,showRefresh:true //是否显示 刷新按钮
	    // ,search:true //是否启用搜索框
	    // ,searchAlign:"left" //指定 搜索框 水平方向的位置。'left' or 'right'
	    // ,searchOnEnterKey:true //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
	    // ,toolbarAlign:"right" //指定 toolbar 水平方向的位置。'left' or 'right'
	    // ,showColumns:true //是否显示 内容列下拉框，选择显示的列
	    ,showHeader:true                           						// 是否显示列头
	    ,showFooter:false                          						// 是否显示列脚
	    ,showExport:true                                            	// 显示导出按钮
	    ,exportDataType: ""										    	// 导出类型
	    ,keyEvents:true //支持热键<- ->翻页
       // ,detailView:true //设置为 true 可以显示详细页面模式。
       // ,detailFormatter:detailFormatter //详细页面显示函数
	    ,onLoadSuccess:comm_onLoadSuccess  								//数据加载错误
	    ,rowStyle:comm_rowStyle
	    //,height:comm_getHeight()										// 定义表格的高度
}

// 获取可视高度
function comm_getHeight(){
    var zh=$(window).height()-44;
    if (zh<350) zh=350;	
    return zh;
}
//行自定义行样式
function comm_rowStyle(row,index){
    var classes = ['active', 'success', 'info', 'warning', 'danger'];
    if (index<0) {
        return {
            classes: classes[3]
        };
    }
    return {};
}

//注销用户
function logout(){
	
   confirm("注销用户","确认注销当前登录用户吗?","icon-remove-sign", function(result) {
	  if(result){
		 window.location.href=basePath+"login/system!logout.action";   	    
	  }
   }); 
}

//修改密码
function updatePwd(){
    $("#_dialog").modal({
		 show : true
		,backdrop : "static" // 背景遮挡
		,moveable : true
	}).on('shown.zui.modal', function() {
		$("#pwd").val("");
		$("#newPwd").val("");
		$("#confirmPwd").val("");
   });
}

  //点击按钮修改密码
  $("#btn_save").click(function(){
	  var parms=$("#_form").serialize();
	  var url= basePath+"system/sysacct!editPwd.action"	
	  common_ajax(parms,url, function(response) {
		  if(response.retflag=="error"){
			  var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
			  msg.show();	
			  return;
		  }
		  setTimeout(function(){$("#_dialog").modal("hide")},100);
		  var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
		  msg.show();	
	  });
  });

// 隐藏菜单函数
function hideMenu() {
	$("#mainmenu").hide();
	$("#maincontent").removeClass("col-md-10");
	$("#maincontent").addClass("col-md-12");
}

// 显示菜单函数
function showMenu() {
	$("#mainmenu").show();
	$("#maincontent").removeClass("col-md-12");
	$("#maincontent").addClass("col-md-10");
}

// 切换菜单状态
function switchMenu() {
	if (g_menu_state) {
		hideMenu();
		g_menu_state = false;
	} else {
		showMenu();
		g_menu_state = true;

	}
}

// 加载页面内容
function loadpage(url) {
	$.ajaxSetup({
		cache : false
	});
	// $("#maincontent").load(url);
	$("#maincontent").load(url, function(result, status) {

		if (status == "success") {
			// 将被加载页的JavaScript加载到本页执行
			$result = $(result);
			$result.find("script").appendTo('#maincontent');
		}

	});

}

// 多条记录选择，提示加返回 通用函数 传入zuitable名称和字段名称
function g_select_and_tip(table, fieldname) {
	var ids = "";
	var bt_ids = table.bootstrapTable('getSelections');
	if (bt_ids.length == 0) {
		var msg = new $.zui.Messager(
				"<i class='icon icon-info-sign'></i> 请选择需要操作的记录！", {
					placement : "center",
					type : "primary"
				});
		msg.show();
		return ids;
	}
	for (var i = 0; i < bt_ids.length; i++) {
		ids = ids + "," + bt_ids[i][fieldname];
	}
	ids = ids.substring(1);
	return ids;
}
//如果没有数据不显示messager
function g_select_and_tip_no_messager(table, fieldname) {
	var ids = "";
	var bt_ids = table.bootstrapTable('getSelections');
	if (bt_ids.length == 0) {
		return ids;
	}
	for (var i = 0; i < bt_ids.length; i++) {
		ids = ids + "," + bt_ids[i][fieldname];
	}
	ids = ids.substring(1);
	return ids;
}
/**
 * 通用的调用ajax方法，返回json格式数据
 * 必须含 response.retflag ，当为 SessionTimeOut 时，
 * @param form
 * @param url
 * @param callback
 */
//异步调用
function common_ajax(json, url, callback){
	common_ajax_async(json, url, callback,true);
}
//同步调用
function common_ajax_noasync(json, url, callback){
	common_ajax_async(json, url, callback,false);
}
function common_ajax_async(json, url, callback,asyncflag) {
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		async:asyncflag,
		data : json,
		success : function(response) {
			if (response) {
				if (response.retflag == "SessionTimeOut") {
					var msg = new $.zui.Messager("消息提示：" + response.message, {
						placement : "center",
						type : "primary"
					});
					msg.show();
					window.location = basePath + "login.jsp";
				}
				
				if (response.retflag == "error") {
					var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"danger"});
			        msg.show();
				}
				if (response.retflag == "success") {
					//正常情况，回调函数  从后台取出返回数据,后台必须返回infoBean对象，含id
					callback(response);
				}
			} else {
				var msg = new $.zui.Messager("消息提示：服务器无反应，请重试！", {
					placement : "center",
					type : "danger"
				});
				msg.show();
			}

		}
	});
}


//增加或修改时，加载JSON对象数据到界面
function comm_loadFormData(data){
   for(var key in data){
	   if($("#"+key)[0]){
		   $("#"+key).val(data[key]);
	   }
   }
		
}

//增加或修改时，加载JSON对象数据到界面 <尽量使用这个方法>
//flag 《id唯一标识》
function comm_loadFormData_flag(data,flag){
   for(var key in data){
	   if($("#"+key+flag)[0]){
		   $("#"+key+flag).val(data[key]);
	   }
   }
}

//增加或修改时，加载JSON对象数据到界面---加载到 .html
//flag 《id唯一标识》
function comm_loadFormData_flag_html(data,flag){
 for(var key in data){
	   if($("#"+key+flag)[0]){
		   $("#"+key+flag).html(data[key]);
	   }
 }
}

//加载select下拉框数据,nm,code,name
//url，加载数据地址，parms传递json参数,c_select为select控件
//数据加载完成后调用回调函数
function comm_loadSelectData(url,parms,c_select,callback){
	common_ajax(parms,url, function(response) {
		var html="";
		var rows=response.rows;
		for (var i = 0 ; i< rows.length;i++){
			html+="<option value='"+rows[i].CODE+"'>"+rows[i].NAME+"</option>";
		}
		c_select.html(html);
		if (callback!=null)
			callback();
	});
}

//加载部门与系统人员数据
function _comm_loadSelectData(url,parms,c_select,callback){
	common_ajax(parms,url, function(response) {
		var html="";
		var rows=response.rows;
		for (var i = 0 ; i< rows.length;i++){
			html+="<option value='"+rows[i].NM+"'>"+rows[i].NAME+"</option>";
		}
		c_select.html(html);
		if (callback!=null)
			callback();
	});
}

function comm_loadSelectDataChoose(url,parms,c_select,callback){
	common_ajax(parms,url, function(response) {
		var html="";
		var rows=response.rows;
		html+="<option value=''>请选择</option>";
		for (var i = 0 ; i< rows.length;i++){
			html+="<option value='"+rows[i].CODE+"'>"+rows[i].NAME+"</option>";
		}
		c_select.html(html);
		if (callback!=null)
			callback();
	});
}

//加载select下拉框数据,nm,code,name
//url，加载数据地址，parms传递json参数,c_select为select控件
//数据加载完成后调用回调函数
function comm_loadSelectData_(url,parms,c_select,callback){
	common_ajax(parms,url, function(response) {
		var rows=response.rows;
		for (var i = 0 ; i< rows.length;i++){
			var option = $("<option>").text("("+rows[i].CODE+")"+rows[i].NAME).val(rows[i].NM);
			c_select.append(option);
		}
		if (callback!=null)
			callback();
	});
}

/**
 * 调用URL 返回 JSON数据格式
 * 
 * @param url
 * @param callback
 * @returns
 */
function common_getJSON(url, callback) {
	$.getJSON(url, "", function(response) {
		if (response) {
			if (response.retflag == "SessionTimeOut") {
				var msg = new $.zui.Messager("消息提示：" + response.message, {
					placement : "center",
					type : "primary"
				});
				msg.show();
				window.location = basePath + "/login.jsp";
			}
			if (response.retflag == "error") {
				var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"danger"});
		        msg.show();
			}
			if (response.retflag == "success") {
				//正常情况，回掉函数
				callback(response);
			}
		} else {
			var msg = new $.zui.Messager("消息提示：服务器无反应，请重试！", {
				placement : "center",
				type : "danger"
			});
			msg.show();
		}
	});

}

//调用后台检查session是否存在
function comm_checksession(){
	var url= basePath+"login/system!checksession.do";
	//common_ajax(null, url, function(response){
			    
 		//}); 
}


//加载BootStrapTable数据发生错误时
function comm_onLoadSuccess(response){
	if (response.retflag == "error") {
		var msg = new $.zui.Messager("消息提示：" + response.message, {
			placement : "center",
			type : "danger"
		});
		msg.show();
	}
}
/**
 * title:确认框标题;
 * params: 提示信息;
 * icon：确认框图标;
 */   
function confirm(title,params,icon,fun) {
    if ($("#myConfirm").length > 0) {
        $("#myConfirm").remove();
    } 
    var html = "<div class='modal fade' id='myConfirm' >";
        html += "<div class='modal-backdrop in' style='opacity:0; '></div>";
        html += "<div class='modal-dialog' style='z-index:2901; margin-top:60px; width:400px; '>";
        html += "<div class='modal-content'>";
        html += "<div class='modal-header'  style='font-size:16px; height:40px;'>";
        html += "<button type='button' class='btn btn-link close confirmClose' id=''>";
        html += "<span aria-hidden='true'><i class='icon icon-times'></i></span><span class='sr-only'>关闭</span>";
        html += "</button>";
        html += "<h4 class='modal-title' style='line-height:15px;'><span class='glyphicon glyphicon-envelope'></span>" ;
        if(icon=="icon-info"){
        	html += "";
        }else if(icon=="icon-remove-sign"){
        	html += "<i class='icon-trash'></i>&nbsp;";
        }
        html += "<span>"+title+"</span></h4><button type='button' class='close' data-dismiss='modal'>";
        html += "<span style='font-size:20px;' class='glyphicon glyphicon-remove'></span></button></div>";
        html += "<div class='modal-body text-center' id='myConfirmContent' style='font-size:18px; '>";
        html += "<i style='margin-left:-50px'><img src='"+basePath+getIconUrlByParam(icon)+"'/></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+params;
        html += "</div>";
        html += "<div class='modal-footer ' style=' height:45px;'>";
        html += "<button class='btn btn-primary' id='confirmOk' style='margin-top:-9px' >确定</button>";
        html += "<button class='btn confirmClose' id='' style='margin-top:-9px;margin-right:-5px'>取消</button>";
        html += "</div></div></div></div>";
    $("body").append(html);

    $("#myConfirm").modal("show");

    $("#confirmOk").on("click", function() {
        $("#myConfirm").modal("hide");
        fun(params); // 执行函数
    });
    
	$(".confirmClose").on("click", function() {
		$("#myConfirm").modal("hide");
		var rows = $(".fixed-table-container tbody tr td");
        rows.removeClass("row-bcground");
	});
}

//根据图标参数获取图表路径
function getIconUrlByParam(param){
	var url="";
	switch (param) {
		case "icon-ok-sign"://成功图标
			url = "common/images/success.png";
			break;
		case "icon-remove-sign"://移除图标
			url = "common/images/delete.png";
			break;
		case "icon-warning-sign"://警告图标
			url = "common/images/warning.png";
			break;
		case "icon-info"://提示图标
			url = "common/images/info.png";
			break;
	}
	return url;
}

/**
 * 
 * 删除和修改时进行验证，提示加返回 通用函数
 * 1、table：表格名称
 * 2、fieldName：字段名称
 * 3、id:主键
 * 
 * */
function verdict_del_update(table, fieldName,id) {
	var ids="";
	var bt_ids = table.bootstrapTable('getSelections');
	for (var i = 0; i < bt_ids.length; i++) {
		ids = ids + "," + bt_ids[i][fieldName];
	}
	ids = ids.substring(1);
	getForm(bt_ids,ids,id);
	return ids;
}

function getForm(bt_ids,ids,id){
	var html="";
	if(bt_ids.length == 1 && ids != id) {
		html="<i class='icon icon-info-sign'></i> 请选择对应的记录！";
	}else if (bt_ids.length > 1 ) {
		html="<i class='icon icon-info-sign'></i> 请选择一条的记录！";
	}
	if(html != ""){
		new $.zui.Messager(html,{
			placement : "center",
			type : "primary"
		}).show();
	}
}


/**
 * 
 * 设置全局主题色
 * 
 * */
var _color=sessionStorage.getItem("globalColor");
var _globalColor='green';
$(function(){
	if(_color == "" || _color == null){
		$("a[data-value='"+_globalColor+"']").attr("class","icon-blank icon-ok");
		sessionStorage.setItem("globalColor",_globalColor);
	}
});

 /*启用选项卡*/
$("#themes a").click(function(e){
   e.preventDefault();/*不要执行与事件有关的默认动作*/
   var color = $(e.target).attr("data-value");
   sessionStorage.setItem("globalColor",color);
   $("#themes a").attr("class","icon-blank");
   $(e.target).attr("class","icon-blank icon-ok");
   var href=basePath+'common/zui/css/zui-theme-'+color+'.css';
   document.getElementById("zui_theme_link_css").href=href;
});
 
//onloadColor();
function onloadColor(){
	var color=sessionStorage.getItem("globalColor");
	$('a[data-value='+color+']').attr("class","icon-blank icon-ok");
	var href=basePath+'common/zui/css/zui-theme-'+color+'.css';
	document.getElementById("zui_theme_link_css").href=href;
}

/**
 * 切换工程事件
 */
$("#engineering_li a").click(function(e){
	e.preventDefault();/*不要执行与事件有关的默认动作*/
	var engineeringNm = $(e.target).attr("data-value");
	$("#engineering_li a").attr("class","icon-blank");
	$(e.target).attr("class","icon-blank icon-ok");
	sessionStorage.setItem("engineeringNm",engineeringNm);
	window.location.reload();
});

//重写toFixed()方法
Number.prototype.toFixed=function(len){
 var changenum=(Math.round(this * Math.pow( 10, len))/ Math.pow( 10, len)).toString(); 
 var index=changenum.indexOf("."); 
 var i;
 if(index<0 && len>0){ 
    changenum=changenum+"."; 
    for(i=0;i<len;i++){ 
      changenum=changenum+"0"; 
    } 
 }else{ 
   index=changenum.length-index; 
   for(i=0;i<(len-index)+1;i++){ 
     changenum=changenum+"0"; 
   } 
 } 
 return changenum; 
}

//数组初始化
function InitArr(size){
   return Array.apply(null, Array(size)).map(function(item, i) {
	  return 0;
   });
}
function getColor(){
	var color = new Array();
	color=['#9fbddf','#7388a2','#156dac','#0357a5','#201d90','#4b419b','#786bb0','#ed8435','#eb5703',
		   '#a69aca','#01afec','#53c2f1','#b7cdda','#b04185','#c50082','#c94583','#903f84','#715a72',
		   '#b391b3','#cfa6cf','#00a0ea','#0098d1','#3e8a96','#4ea7ac','#66a9de','#03419a','#325295',
		   '#59c1d0','#2c685f','#03a985','#f9d7a8','#bec667','#d8e480','#d9e646','#dcdf01','#acce07',
           '#6eb92b','#00a93e','#6ebc59','#018956','#f4b0b2','#ef847e','#cc736c','#af7c48','#d0712f',
		   '#ee8300','#f6ae3a','#d69534']
	
	return color;
}

/**
 * 
 * json字符串key小写转为大写
 * 
 * */
function upperJSONKey(jsonObj){  
    for (var key in jsonObj){  
        jsonObj["\""+key.toUpperCase()+"\""] = jsonObj[key];  
        delete(jsonObj[key]);  
    }  
    return jsonObj;  
} 
