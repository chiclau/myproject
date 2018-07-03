(function($) {
	$.Precipitation_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			query_table_precipitation 	:$('#query_table_precipitation')		//页面BootStrapTable的ID
			,query_searchName_pptn		:$('#query_searchName_pptn')	//页面模糊查询input
			,query_startTime_pptn		:$('#startTime')	//页面查询起始时间
			,query_endTime_pptn			:$('#endTime')	//页面查询结束时间
			,query_stcd_pptn			:$('#stcd_pptn')	//页面查询根据测站名称
			,query_info_precipitation	:$("#query_info_precipitation")	//双击事件窗口id
			,query_ref					:$("#query_ref")		//模糊查询
			,select_engineerCode		:$("#engineerCode")		//查询条件供货厂商
			,edit_dialog_precipitation	:$("#edit_dialog_precipitation")		//添加编辑窗口
			,query_add_precipitation	:$("#query_add_precipitation")//添加按钮
			,btn_del_precipitation		:$("#btn_del_precipitation")//批量删除按钮
			,info_form_precipitation	:$("#info_form_precipitation")//添加编辑的表单
			,engineerCode				:$('#engineerCode') //编辑页工程编码
			,info_upload				:$("#info_upload")	//文件上传模态框
			,btn_into_pptn				:$("#btn_into_pptn")	//导入按钮
			,btn_outAll_pptn			:$('#btn_outAll_pptn') //导出全部按钮
			,btn_outPage_pptn			:$('#btn_outPage_pptn') //导出当前页按钮
			,btn_stcd_pptn				:$('#stcd_pptn_list')		//测站下拉框
		}
		
		//B、请求地址URL
		var opt_url={
			url_list			:basePath + "business/pptn!list.action"//查询数据URL
			,url_save			:basePath + "business/pptn!save.action"//添加或修改保存主合同URL
			,url_remove			:basePath + "business/pptn!removeids.action"//删除URL
			,url_edit			:basePath + "business/pptn!edit.action"//修改查询数据
			,url_import			:basePath + "business/pptn!importPptn.action"//查询物料详细信息
			,url_export			:basePath + "business/pptn!export.action"//查询物料详细信息
			,url_engineer		:basePath + "contInfo/contInfo!queryAllEngineer.action"//所属工程下拉数据
			,url_upstcd_Stbprp  :basePath + "stbprp/stbprp!upstcd_Stbprp.action"//下拉列表
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		var TableQueryParams;
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);   
			opt_all.query_table_precipitation.bootstrapTable($.extend(g_bootstrapTable_Options,
				{
				      url			 	:opt_all.url_list   // 请求后台的URL（*）
			         ,queryParams	  	:queryParams		// 传递参数（*）
			         ,onDblClickRow 	:onDblClickRow		// 行双击事件
			         ,onSort			:onSort             // 排序事件
		             ,rowStyle			:comm_rowStyle		//行样式，可自定义
		             ,onLoadSuccess		:comm_onLoadSuccess //数据加载错误
		             ,onCheckAll 		:onCheckAll   //全选
		             ,onCheck  			:onCheck   //单选
		             ,onUncheck  		:onUncheck   //不选
		             ,onUncheckAll 		:onUncheckAll  //全不选
		             ,singleSelect		:false
		             ,uniqueId			:"ID"
		             ,pageSize:15
				}));
			//选中多行改变表格背景色
			   function onCheckAll(rows){
			    for(var i=0;i<rows.length;i++){
			     commRowStyle(i);
			    }
			   }
			   //循环改变所有行颜色
			   function commRowStyle(i){
			    $("#query_table_precipitation tbody tr[data-index="+i+"]").addClass("success");
			   }
			   //全不选时颜色恢复
			   function onUncheckAll(){
			    $("#query_table_precipitation tbody tr").removeClass("success");
			   }
			   //选中一行改变表格背景色
			   function onCheck(rows){
			    $("#query_table_precipitation tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			   }
			   //不选中时颜色恢复
			   function onUncheck(rows){
			    $("#query_table_precipitation tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			   }
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				TableQueryParams = params;
				//查询条件
				var opt_parms={
						"mPptnFormBean.searchName"			:opt_control.query_searchName_pptn.val()// 查询关键字
					   ,"mPptnFormBean.startTime"			:opt_control.query_startTime_pptn.val()// 查询关键字
					   ,"mPptnFormBean.endTime"				:opt_control.query_endTime_pptn.val() // 查询关键字
					   ,"mPptnFormBean.stcd"				:opt_control.btn_stcd_pptn.val()// 查询关键字
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "mPptnFormBean.pageBean.limit"			: params.limit   // 页面大小
			      ,"mPptnFormBean.pageBean.offset"			: params.offset  // 当前记录偏移条数
			      ,"mPptnFormBean.pageBean.sort"			: params.sort  	  // 排序列名
			      ,"mPptnFormBean.pageBean.sortOrder" 		: params.order   // 排位命令（desc，asc）
			    };
			    temp=$.extend(temp,opt_parms,opt);
				return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				if (row) {
					_table(row.STCD);
				}
			}
			// 排序事件
			function onSort(name, order){
				_refresh();
			}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.query_add_precipitation.bind("click",event_add);
			opt_all.btn_del_precipitation.bind("click",event_del);
			opt_all.query_ref.bind("click",event_ref);
			opt_all.btn_outAll_pptn.bind("click",exportAllPptn);
			opt_all.btn_outPage_pptn.bind("click",exportPagePptn);
			opt_all.btn_into_pptn.bind("click",upload_model_show);
			
			//初始化主合同编辑表单 保存button类型为submit
			opt_all.info_form_precipitation.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
		}
		//下拉数据初始化
		this.InitSelect = function (){
			Load_select_stcd_pptn();
			Load_select_stcd_pptn_list();
		}
		
		function Load_select_stcd_pptn(){
			opt_control.query_stcd_pptn.empty();
			var url = opt_all.url_upstcd_Stbprp;
			opt_control.query_stcd_pptn.append("<option value=''>请选择测站名称</option>");
			common_ajax(null,url, function(data) {
				console.log(JSON.stringify(data));
				$.each(data.rows, function(i) {
					opt_control.query_stcd_pptn.append(
						'<option value=' + data.rows[i].STCD + '>'+ data.rows[i].STNM + '</option>');
				});
			});
		}
		function Load_select_stcd_pptn_list(){
			opt_control.btn_stcd_pptn.empty();
			var url = opt_all.url_upstcd_Stbprp;
			opt_control.btn_stcd_pptn.append("<option value=''>请选择测站名称</option>");
			common_ajax(null,url, function(data) {
				console.log(JSON.stringify(data));
				$.each(data.rows, function(i) {
					opt_control.btn_stcd_pptn.append(
							'<option value=' + data.rows[i].STCD + '>'+ data.rows[i].STNM + '</option>');
				});
			});
		}
		
	    // 修改记录，调出窗体,公开函数
		this.edit=function(stcd,tm) {
			_edit(stcd,tm);
		}
		// 履约评价
		this.evaluation=function(id) {
			_Evaluation(id);
		}
		//显示合同详细
		this.onClickContract = function(id){
			window.location.href = opt_all.url_details + "?contractId="+id;
		}
		//单条删除
		this.del=function(stcd,tm){
			if(tm != "" && stcd != ""){
				_del(stcd,tm);
			}
		}
		
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		
		// 批量删除
		function _removeids(){
           var ids = g_select_and_tip(opt_all.query_table_precipitation,"STCD");
           var ids_=(tm.substring(tm.length-1)==',')?tm.substring(0,tm.length-1):tm;
          
           if (ids.length==0 && ids_.length==0) return;
           
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           
           var url= opt_all.url_remove+"?mPptnFormBean.mPptnInfoBean.stcd="+ids+"&mPptnFormBean.mPptnInfoBean.tm="+ids_
            
           confirm("资料管理","您确定要删除这些记录吗？","icon-remove-sign", function(result) {
			   if(result){
			 		common_ajax(null, url, function(response){
						// 删除后，从后台取出返回数据
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
						    
			 		}); 
               }
			});    
		}
		
		//删除单条记录
		function _del(stcd,tm){
			var url= opt_all.url_remove+"?mPptnFormBean.mPptnInfoBean.stcd="+stcd+"&mPptnFormBean.mPptnInfoBean.tm="+tm
			$(".table tbody tr[data-uniqueid="+stcd+"] td").addClass("row-bcground");
			confirm("资料管理","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
			   if(result){
			 		common_ajax(null, url, function(response){
						// 删除后，从后台取出返回数据
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
			 		}); 
               }
			}); 
		}
		
		//新增和编辑函数
		function _edit(stcd,tm){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
				function(){ 
					LoadEditData(stcd,tm);
				}
			);
		}
		
		//显示详情
		function _table(queryId){
			var url = opt_all.url_list+"?mPptnFormBean.mPptnInfoBean.stcd=" + queryId;
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
				   if($("#"+key+"_pptnDetail")[0]){
					   $("#"+key+"_pptnDetail").html(response.rows[0][key]);
				   }
				}
				var now = new Date(response.rows[0].TM.time);
				var year = now.getFullYear(),
				　　month = now.getMonth() + 1,
				　　date = now.getDate(),
				　　hour = now.getHours(),
				　　minute = now.getMinutes(),
				　　second = now.getSeconds();
				var tm = year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
				$("#TM_pptnDetail").html(tm);
			});
			opt_all.query_info_precipitation.modal({
				show 	   : true
				,backdrop  : "static" // 背景遮挡
					,moveable  : true
			}).on('shown.zui.modal', function() {
			});
		}
		
		//加载所属工程下拉
		function Load_select_engineer(){
			opt_control.select_engineerCode.empty();
			var url = opt_all.url_engineer;
			opt_control.select_engineerCode.append("<option value=''>请选择所属工程</option>");
			common_ajax(null,url, function(data) {
				$.each(data.engineers, function(i) {
					opt_control.select_engineerCode.append(
						'<option value=' + data.engineers[i].NM + '>'+ data.engineers[i].ENGINEER_NAME + '</option>');
				});
			});
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(stcd,tm){
			if(tm != null && stcd!=""){
				var url = opt_all.url_edit+"?mPptnFormBean.mPptnInfoBean.stcd=" + stcd+"&mPptnFormBean.mPptnInfoBean.tm="+tm;
				// 动态加载页面数据
				common_ajax(null,url, function(response) {
					$('#stcd_pptn').val(response.mPptnFormBean.stcd);
					$('#tm_pptn').val(response.mPptnFormBean.tm);
					
					comm_loadFormData_flag(response.mPptnFormBean,"_pptn");
					
					$('#stcd_pptn').prop('disabled',true);
					$('#tm_pptn').prop('disabled',true);
					
					opt_all.edit_dialog_precipitation.find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;修改降水量");
					///////////////////////////////////////////
				});
			}else{
				$('#stcd_pptn').prop('disabled',false);
				$('#tm_pptn').prop('disabled',false);
				
				opt_all.edit_dialog_precipitation.find('.modal-title').html("<i class='icon icon-plus-sign'></i>&nbsp;新增降水量") ;
			}
			
			opt_all.edit_dialog_precipitation.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				$('#stcd_pptn').val('');
				$('#tm_pptn').val('');
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
           });
			
		}
		
		// 保存主合同数据
		function _save() {
			$('#stcd_pptn_').val("");
			$('#laydate_').val("")
			$("#stcd_pptn_").val($('#stcd_pptn').val());
			$("#laydate_").val($('#tm_pptn').val());
	 		var json=opt_all.info_form_precipitation.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.edit_dialog_precipitation.modal("hide");
			    //重置表单
			    _reset();
			    // 刷新列表
			    _refresh();
	 		});
		}
		
		//文件上传
		function filesUpload(){
			var file_options = {
				//初始化参数
				url:opt_all.url_import	//文件上传地址
			   ,filters:{//文件过滤器
				   	// 只允许上传Excel
				    mime_types: [
				        {title: 'Excel', extensions: 'xlsx,xls'},
				    ],
				    // 最大上传文件为 10MB
				    max_file_size: '10mb',
				    // 不允许上传重复文件
				    prevent_duplicates: true
				}
			   ,responseHandler:function(responseObject, file){
				   // 当服务器返回的文本内容包含 `'error'` 文本时视为上传失败
				   if(responseObject.response=='error') {
					   var msg = new $.zui.Messager("消息提示：导入失败", {placement: "center",type:"primary"});
					   msg.show();	
					   return '导入失败';
				   }else if(responseObject.response=='success') {
					   _refresh();
				   }
			   }
			}
			$('#myUploader').uploader(file_options);
		}
		
		//文件上传模态框
		function upload_model_show(){
			filesUpload();
			opt_all.info_upload.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
	
	        });
		}
		
		//导出全部
		function exportAllPptn(){
			var url= opt_all.url_export+"?";
			var data="mPptnFormBean.pageBean.limit=99999999"
					+"&mPptnFormBean.pageBean.offset=0" 
					+"&mPptnFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&mPptnFormBean.pageBean.sort="+TableQueryParams.sort
					+"&mPptnFormBean.searchName="+opt_all.query_searchName_pptn.val();
			confirm("<i class='icon icon-reply'></i>&nbsp;导出全部","您确定要导出全部信息吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data;
               }
			});
		}
		
		//导出当前页
		function exportPagePptn(){
			var url= opt_all.url_export+"?";
			var data_page="mPptnFormBean.pageBean.limit="+TableQueryParams.limit
						 +"&mPptnFormBean.pageBean.offset="+TableQueryParams.offset
						 +"&mPptnFormBean.pageBean.sortOrder="+TableQueryParams.order
						 +"&mPptnFormBean.pageBean.sort="+TableQueryParams.sort
						 +"&mPptnFormBean.searchName"+opt_all.query_searchName_pptn.val();
			confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出当前页","您确定要导出当前页吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data_page;
			   }
			});
		}
		
		//****绑定事件
		//绑定添加或修改事件
		function event_add(){
			_edit("","");
		}

		//绑定刷新事件
		function event_ref(){
			// _table();
			 _refresh();
		}
		
		//删除物资输入框
		function event_selfRemove(){
    		var myId = $(this).data("id");
    		if(myId==null){
    			$(this).parent("td").parent("tr").remove();
    		}
		}
		
		//****绑定事件******end
	    // 刷新
	    function _refresh(){
	    	opt_all.query_table_precipitation.bootstrapTable('refresh');
	    }
	    this.flash=function(){
			_refresh();
		}
		// 重置主合同
		function _reset(){
			opt_all.info_form_precipitation.data('bootstrapValidator').resetForm(true);
		}
		//公开函数
		this.reset=function(){
			_reset();
		}
		////////////////////////////////////////////////////////////////////////////////
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback){
			//所有编辑页面下拉框加载
			callback();
		}
	};
})(jQuery);
			
