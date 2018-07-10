(function($) {
	//当前工程内码
	var engineeringNm = sessionStorage.getItem("engineeringNm");
	$.ReservoirWater_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			query_table_reservoirWater 		:$('#query_table_reservoirWater')		//页面BootStrapTable的ID
			,query_searchName				:$('#query_searchName_rsvr')	//页面模糊查询input
			,query_startTime_rsvr			:$('#startTime_rsvr')	//页面查询起始时间
			,query_endTime_rsvr				:$('#endTime_rsvr')	//页面查询结束时间
			,query_stcd_rsvr				:$('#stcd_rsvr')	//页面查询根据测站名称
			,query_info_reservoirWater		:$("#query_info_reservoirWater")	//双击事件窗口id
			,query_ref						:$("#query_ref_rsvr")		//模糊查询
			,query_form						:$("#query_form")
			,edit_dialog_reservoirWater		:$("#edit_dialog_reservoirWater")		//添加编辑窗口
			,query_add_reservoirWater		:$("#query_add_reservoirWater")//添加按钮
			,btn_del_reservoirWater			:$("#btn_del_reservoirWater")//批量删除按钮
			,info_form_reservoirWater		:$("#info_form_reservoirWater")//添加编辑的表单
			,info_upload					:$("#info_upload")	//文件上传模态框
			,btn_into_pptn					:$("#btn_into_pptn_rsvr")	//导入按钮
			,btn_outAll_pptn	    		:$('#btn_outAll_pptn_rsvr') //导出全部按钮
			,btn_outPage_pptn				:$('#btn_outPage_pptn_rsvr') //导出当前页按钮
			,btn_stcd_rsvr					:$('#stcd_rsvr_list')		//测站下拉框
		}
		
		//B、请求地址URL
		var opt_url={
			url_list			:basePath + "rsvr/rsvr!list.action"//查询数据URL
			,url_save			:basePath + "rsvr/rsvr!save.action"//添加或修改保存主合同URL
			,url_remove			:basePath + "rsvr/rsvr!removeids.action"//删除URL
			,url_edit			:basePath + "rsvr/rsvr!edit.action"//修改查询数据
			,url_import			:basePath + "rsvr/rsvr!importPptn.action"//导入
			,url_export			:basePath + "rsvr/rsvr!export.action"//导出
			,url_upstcd_Stbprp  :basePath + "stbprp/stbprp!upstcd_Stbprp.action"//下拉列表
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);   
			opt_all.query_table_reservoirWater.bootstrapTable($.extend(g_bootstrapTable_Options,
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
			    $("#query_table_reservoirWater tbody tr[data-index="+i+"]").addClass("success");
			   }
			   //全不选时颜色恢复
			   function onUncheckAll(){
			    $("#query_table_reservoirWater tbody tr").removeClass("success");
			   }
			   //选中一行改变表格背景色
			   function onCheck(rows){
			    $("#query_table_reservoirWater tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			   }
			   //不选中时颜色恢复
			   function onUncheck(rows){
			    $("#query_table_reservoirWater tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			   }
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				TableQueryParams = params;
				var opt_parms={
					 "mRsvrFormBean.searchName"			:opt_control.query_searchName.val() // 查询关键字
					,"mRsvrFormBean.startTime"			:opt_control.query_startTime_rsvr.val()//页面查询起始时间
					,"mRsvrFormBean.endTime"			:opt_control.query_endTime_rsvr.val() //页面查询结束时间
					,"mRsvrFormBean.stcd"				:opt_control.btn_stcd_rsvr.val() //页面查询根据测站名称
				};	 
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "mRsvrFormBean.pageBean.limit"			: params.limit   // 页面大小
			      ,"mRsvrFormBean.pageBean.offset"			: params.offset  // 当前记录偏移条数
			      ,"mRsvrFormBean.pageBean.sort"			: params.sort  	  // 排序列名
			      ,"mRsvrFormBean.pageBean.sortOrder" 		: params.order   // 排位命令（desc，asc）
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
			opt_all.query_add_reservoirWater.bind("click",event_add);
			opt_all.btn_del_reservoirWater.bind("click",event_del);
			opt_all.query_ref.bind("click",event_ref);
			opt_all.btn_into_pptn.bind("click",upload_model_show);
			opt_all.btn_outPage_pptn.bind("click",exportPagePptn);
			opt_all.btn_outAll_pptn.bind("click",exportAllPptn);
			//初始化主合同编辑表单 保存button类型为submit
			opt_all.info_form_reservoirWater.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			
		}
		//下拉数据初始化
		this.InitSelect = function (){
			Load_select_stcd_rsvr();
			Load_select_stcd_rsvr_list();
		}
		
		function Load_select_stcd_rsvr(){
			opt_control.query_stcd_rsvr.empty();
			var url = opt_all.url_upstcd_Stbprp;
			opt_control.query_stcd_rsvr.append("<option value=''>请选择测站名称</option>");
			common_ajax(null,url, function(data) {
				console.log(JSON.stringify(data));
				$.each(data.rows, function(i) {
					opt_control.query_stcd_rsvr.append(
						'<option value=' + data.rows[i].STCD + '>'+ data.rows[i].STNM + '</option>');
				});
			});
		}
		function Load_select_stcd_rsvr_list(){
			opt_control.btn_stcd_rsvr.empty();
			var url = opt_all.url_upstcd_Stbprp;
			opt_control.btn_stcd_rsvr.append("<option value=''>请选择测站名称</option>");
			common_ajax(null,url, function(data) {
				console.log(JSON.stringify(data));
				$.each(data.rows, function(i) {
					opt_control.btn_stcd_rsvr.append(
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
           var ids = g_select_and_tip(opt_all.query_table_reservoirWater,"STCD");
           var ids_=(tm.substring(tm.length-1)==',')?tm.substring(0,tm.length-1):tm;
           if (ids.length==0 && ids_.length==0) return;
           
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           
           var url= opt_all.url_remove+"?mRsvrFormBean.mRsvrInfoBean.stcd="+ids+"&mRsvrFormBean.mRsvrInfoBean.tm="+ids_
            
           confirm("资料管理","您确定要删除这些记录吗？","icon-remove-sign", function(result) {
			   if(result){
			 		common_ajax(null, url, function(response){
					    _refresh();
					    var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
					    msg.show();	
						    
			 		}); 
               }
			});    
		}
		
		//删除单条记录
		function _del(stcd,tm){
			var url= opt_all.url_remove+"?mRsvrFormBean.mRsvrInfoBean.stcd="+stcd+"&mRsvrFormBean.mRsvrInfoBean.tm="+tm;
			$(".table tbody tr[data-uniqueid="+stcd+"] td").addClass("row-bcground");
			confirm("资料管理","您确定要删除该条记录吗？","icon-remove-sign", function(result) {
			   if(result){
			 		common_ajax(null, url, function(response){
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
			var url = opt_all.url_list+"?mRsvrFormBean.mRsvrInfoBean.stcd=" + queryId;
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
					//主合同信息
				   if($("#"+key+"_detail_s")[0]){					   
					   $("#"+key+"_detail_s").html(response.rows[0][key]);
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
				$("#TM_detail_s").html(tm);
			});
			opt_all.query_info_reservoirWater.modal({
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
			var data="mRsvrFormBean.pageBean.limit=99999999"
					+"&mRsvrFormBean.pageBean.offset=0" 
					+"&mRsvrFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&mRsvrFormBean.pageBean.sort="+TableQueryParams.sort
					+"&mRsvrFormBean.searchName="+opt_all.query_searchName.val();
			confirm("<i class='icon icon-reply'></i>&nbsp;导出全部","您确定要导出全部信息吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data;
               }
			});
		}
		
		//导出当前页
		function exportPagePptn(){
			var url= opt_all.url_export+"?";
			var data_page="mRsvrFormBean.pageBean.limit="+TableQueryParams.limit
						 +"&mRsvrFormBean.pageBean.offset="+TableQueryParams.offset
						 +"&mRsvrFormBean.pageBean.sortOrder="+TableQueryParams.order
						 +"&mRsvrFormBean.pageBean.sort="+TableQueryParams.sort
						 +"&mRsvrFormBean.searchName"+opt_all.query_searchName.val();
			confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出当前页","您确定要导出当前页吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data_page;
			   }
			});
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(stcd,tm){
			if(tm != "" && stcd!=""){
				var url = opt_all.url_edit+"?mRsvrFormBean.mRsvrInfoBean.stcd=" + stcd+"&mRsvrFormBean.mRsvrInfoBean.tm="+tm;
				common_ajax(null,url, function(response) {
					console.log(JSON.stringify(response.mRsvrFormBean))
					$('#stcd_rsvr').val(response.mRsvrFormBean.stcd);
					$('#tm_rsvr').val(response.mRsvrFormBean.tm);
					comm_loadFormData_flag(response.mRsvrFormBean,"_rsvr");//显示主合同信息
					$('#stcd_rsvr').prop('disabled',true);
					$('#tm_rsvr').prop('disabled',true);
					opt_all.edit_dialog_reservoirWater.find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;修改水库水情");
				});
			}else{
				$('#stcd_rsvr').prop('disabled',false);
				$('#tm_rsvr').prop('disabled',false);
				opt_all.edit_dialog_reservoirWater.find('.modal-title').html("<i class='icon icon-plus-sign'></i>&nbsp;新增水库水情") ;
			}
			opt_all.edit_dialog_reservoirWater.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				$('#stcd_rsvr').val("");
				$('#tm_rsvr').val("");
				_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
           });
		}
		
		// 保存主合同数据
		function _save() {
			$('#stcd_rsvr_').val("");
			$('#tm_rsvr_').val("");
			$('#stcd_rsvr_').val($('#stcd_rsvr').val());
			$('#tm_rsvr_').val($('#tm_rsvr').val());
	 		var json=opt_all.info_form_reservoirWater.serialize();
	 		console.log(json)
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.edit_dialog_reservoirWater.modal("hide");
			    _reset();
			    _refresh();
	 		});
		}
		
		//****绑定事件
		//绑定添加或修改事件
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
	    	opt_all.query_table_reservoirWater.bootstrapTable('refresh');
	    }
	    this.flash=function(nm){
	    	engineeringNm=nm;
			_refresh();
		}
		// 重置主合同
		function _reset(){
			opt_all.info_form_reservoirWater.data('bootstrapValidator').resetForm(true);
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
			
