(function($) {
	//当前工程内码
	var engineeringNm = sessionStorage.getItem("engineeringNm");
	$.DiurnalEvaporation_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			query_table_diurnalEvaporation 	:$('#query_table_diurnalEvaporation')		//页面BootStrapTable的ID
			,query_searchName				:$('#query_searchName_dayev')	//页面模糊查询input
			,query_info_diurnalEvaporation	:$("#query_info_diurnalEvaporation")	//双击事件窗口id
			,query_ref						:$("#query_ref_dayev")		//模糊查询
			,query_form						:$("#query_form")
			,edit_dialog_diurnalEvaporation	:$("#edit_dialog_diurnalEvaporation")		//添加编辑窗口
			,query_add_diurnalEvaporation	:$("#query_add_diurnalEvaporation")//添加按钮
			,btn_del_diurnalEvaporation		:$("#btn_del_diurnalEvaporation")//批量删除按钮
			,info_form_diurnalEvaporation	:$("#info_form_diurnalEvaporation")//添加编辑的表单
			,info_upload					:$("#info_upload")	//文件上传模态框
			,btn_into_pptn					:$("#btn_into_pptn_dayev")	//导入按钮
			,btn_outAll_pptn	   	 		:$('#btn_outAll_pptn_dayev') //导出全部按钮
			,btn_outPage_pptn				:$('#btn_outPage_pptn_dayev') //导出当前页按钮
			,btn_save						:$('#btn_save')		//保存按钮
			,btn_stcd_dayev					:$('#stcd_dayev')		//测站下拉框
		}
		
		//B、请求地址URL
		var opt_url={
			url_list			:basePath + "dayev/dayev!list.action"//查询数据URL
			,url_save			:basePath + "dayev/dayev!save.action"//添加或修改保存主合同URL
			,url_remove			:basePath + "dayev/dayev!removeids.action"//删除URL
			,url_edit			:basePath + "dayev/dayev!edit.action"//修改查询数据
			,url_import			:basePath + "dayev/dayev!importPptn.action"//导入
			,url_export			:basePath + "dayev/dayev!export.action"//导出
			,url_upstcd_Stbprp  :basePath + "stbprp/stbprp!upstcd_Stbprp.action"//下拉列表
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);   
			opt_all.query_table_diurnalEvaporation.bootstrapTable($.extend(g_bootstrapTable_Options,
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
			    $("#query_table_diurnalEvaporation tbody tr[data-index="+i+"]").addClass("success");
			   }
			   //全不选时颜色恢复
			   function onUncheckAll(){
			    $("#query_table_diurnalEvaporation tbody tr").removeClass("success");
			   }
			   //选中一行改变表格背景色
			   function onCheck(rows){
			    $("#query_table_diurnalEvaporation tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			   }
			   //不选中时颜色恢复
			   function onUncheck(rows){
			    $("#query_table_diurnalEvaporation tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			   }
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				TableQueryParams = params;
				var opt_parms={
					 "mDayevFormBean.searchName"			:opt_control.query_searchName.val() // 查询关键字
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "mDayevFormBean.pageBean.limit"			: params.limit   // 页面大小
			      ,"mDayevFormBean.pageBean.offset"			: params.offset  // 当前记录偏移条数
			      ,"mDayevFormBean.pageBean.sort"				: params.sort  	  // 排序列名
			      ,"mDayevFormBean.pageBean.sortOrder" 		: params.order   // 排位命令（desc，asc）
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
			opt_all.query_add_diurnalEvaporation.bind("click",event_add);
			opt_all.btn_del_diurnalEvaporation.bind("click",event_del);
			opt_all.query_ref.bind("click",event_ref);
			opt_all.btn_into_pptn.bind("click",upload_model_show);
			opt_all.btn_outPage_pptn.bind("click",exportPagePptn);
			opt_all.btn_outAll_pptn.bind("click",exportAllPptn);
			//初始化主合同编辑表单 保存button类型为submit
			opt_all.info_form_diurnalEvaporation.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			
		}
		
		//下拉数据初始化
		this.InitSelect = function (){
			Load_select_stcd_dayev();
		}
		
		function Load_select_stcd_dayev(){
			opt_control.btn_stcd_dayev.empty();
			var url = opt_all.url_upstcd_Stbprp;
			opt_control.btn_stcd_dayev.append("<option value=''>请选择测站名称</option>");
			common_ajax(null,url, function(data) {
				console.log(JSON.stringify(data));
				$.each(data.rows, function(i) {
					opt_control.btn_stcd_dayev.append(
						'<option value=' + data.rows[i].STCD + '>'+ data.rows[i].STNM + '</option>');
				});
			});
		}
		
	    // 修改记录，调出窗体,公开函数
		this.edit=function(stcd,tm) {
			_edit(stcd,tm);
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
           // 获取删除选中的ids
           var ids = g_select_and_tip(opt_all.query_table_diurnalEvaporation,"STCD");
           var ids_=(tm.substring(tm.length-1)==',')?tm.substring(0,tm.length-1):tm;
           if (ids.length==0 && ids_==0) return;
           
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           
           var url= opt_all.url_remove+"?mDayevFormBean.mDayevInfoBean.stcd="+ids+"&mDayevFormBean.mDayevInfoBean.tm="+ids_;
            
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
			var url= opt_all.url_remove+"?mDayevFormBean.mDayevInfoBean.stcd="+stcd+"&mDayevFormBean.mDayevInfoBean.tm="+tm;
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
			var url = opt_all.url_list+"?mDayevFormBean.mDayevInfoBean.stcd=" + queryId;
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
					//主合同信息
				   if($("#"+key+"_detail")[0]){
					   $("#"+key+"_detail").html(response.rows[0][key]);
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
				$("#TM_detail").html(tm);
			});
			opt_all.query_info_diurnalEvaporation.modal({
				show 	   : true
				,backdrop  : "static" // 背景遮挡
					,moveable  : true
			}).on('shown.zui.modal', function() {
			});
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
		
		//上传文件
		this.uploading=function(contId,evalId,engineerCode){
			//履约评价的文件上传
			filesUpload_eval(evalId,engineerCode);
			//主合同的文件上传
			filesUpload(contId,engineerCode);
			filesUploadShow();
		}
		
		//导出全部
		function exportAllPptn(){
			var url= opt_all.url_export+"?";
			var data="mDayevFormBean.pageBean.limit=99999999"
					+"&mDayevFormBean.pageBean.offset=0" 
					+"&mDayevFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&mDayevFormBean.pageBean.sort="+TableQueryParams.sort
					+"&mDayevFormBean.searchName="+opt_all.query_searchName.val();
			confirm("<i class='icon icon-reply'></i>&nbsp;导出全部","您确定要导出全部信息吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data;
               }
			});
		}
		
		//导出当前页
		function exportPagePptn(){
			var url= opt_all.url_export+"?";
			var data_page="mDayevFormBean.pageBean.limit="+TableQueryParams.limit
						 +"&mDayevFormBean.pageBean.offset="+TableQueryParams.offset
						 +"&mDayevFormBean.pageBean.sortOrder="+TableQueryParams.order
						 +"&mDayevFormBean.pageBean.sort="+TableQueryParams.sort
						 +"&mDayevFormBean.searchName"+opt_all.query_searchName.val();
			confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出当前页","您确定要导出当前页吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data_page;
			   }
			});
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(stcd,tm){
			if(tm != "" && stcd!=""){
				var url = opt_all.url_edit+"?mDayevFormBean.mDayevInfoBean.stcd=" + stcd+"&mDayevFormBean.mDayevInfoBean.tm="+tm;
				common_ajax(null,url, function(response) {
					$('#stcd_dayev').val(response.mDayevFormBean.stcd);
					$('#tm_dayev').val(response.mDayevFormBean.tm);
					
					comm_loadFormData_flag(response.mDayevFormBean,"_dayev");//显示主合同信息
					
					$('#stcd_dayev').prop('disabled',true);
					$('#tm_dayev').prop('disabled',true);
					
					opt_all.edit_dialog_diurnalEvaporation.find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;修改日蒸发量");
				});
			}else{
				$('#stcd_dayev').prop('disabled',false);
				$('#tm_dayev').prop('disabled',false);
				//先清除添加过的数据再弹窗
				opt_all.edit_dialog_diurnalEvaporation.find('.modal-title').html("<i class='icon icon-plus-sign'></i>&nbsp;新增日蒸发量") ;
			}
			opt_all.edit_dialog_diurnalEvaporation.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				$('#stcd_dayev').val('');
				$('#tm_dayev').val('');
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
           });
		}
		
		// 保存主合同数据
		function _save() {
			$('#stcd_dayev_').val('');
			$('#tm_dayev_').val('');
			
			$('#stcd_dayev_').val($('#stcd_dayev').val());
			$('#tm_dayev_').val($('#tm_dayev').val());
			
	 		var json=opt_all.info_form_diurnalEvaporation.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.edit_dialog_diurnalEvaporation.modal("hide");
			    //重置表单
			    _reset();
			    // 刷新列表
			    _refresh();
	 		});
		}
		
		//****绑定事件
		//绑定添加或修改事件
		function event_showZtree(){
			opt_all.enginner_dialog.modal({
				show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
//				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
			});
		}
		
		function event_add(){
			_edit("","");
		}

		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//****绑定事件******end
	    // 刷新
	    function _refresh(){
	    	opt_all.query_table_diurnalEvaporation.bootstrapTable('refresh');
	    }
	    this.flash=function(nm){
	    	engineeringNm=nm;
			_refresh();
		}
		// 重置主合同
		function _reset(){
			opt_all.info_form_diurnalEvaporation.data('bootstrapValidator').resetForm(true);
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
			
