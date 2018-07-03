(function($) {
	//当前工程内码
	var engineeringNm = sessionStorage.getItem("engineeringNm");
	$.Tsqx_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 query_table 			:$('#query_table_tsqx')		//页面BootStrapTable的ID
			,query_searchName		:$('#query_searchName_tsqx')	//页面模糊查询input
			,query_info_show		:$("#query_info_show_tsqx")	//双击事件窗口id
			,query_ref				:$("#query_ref_tsqx")		//模糊查询
			,query_form				:$("#query_form_tsqx")
			,query_add				:$("#query_add")		//添加按钮
			,btn_del				:$("#btn_del")			//批量删除按钮
			,edit_dialog			:$("#edit_dialog_tsqx")//添加编辑窗口
			,info_form				:$("#info_form_tsqx")		//添加编辑的表单
			,uploading_data_dialog	:$('#uploading_data_dialog') //文件上传模态框
			,enginner_dialog		:$('#enginner_dialog') //选择工程模态框
			,info_upload			:$("#info_upload")	//文件上传模态框
			,btn_into_Tsqx			:$("#btn_into_tsqx")	//导入按钮
			,btn_outAll_Tsqx	    :$('#btn_outAll_tsqx') //导出全部按钮
			,btn_outPage_Tsqx		:$('#btn_outPage_tsqx') //导出当前页按钮
			,btn_stcd_rvsect		:$('#stcd_tsqx')		//测站下拉框
			,edit_dialog_tsqxData_x	:$("#edit_dialog_tsqxData_x")//修改页面
			,query_add_tsqxData_x	:$('#query_add_tsqxData_x')	//修改页面增加
			,myEcharts_tsqx			:$('#myEcharts_tsqx')//折线图
			,btn_save_tsqx			:$('#btn_save_tsqx')//修改保存
			,info_form_tsqxData_x	:$('#info_form_tsqxData_x')//修改表单
		}
		
		//B、请求地址URL
		var opt_url={
			url_list			:basePath + "tsqx/tsqx!list.action"//查询数据URL
			,url_save			:basePath + "tsqx/tsqx!save.action"//添加或修改保存主合同URL
			,url_save_x			:basePath + "tsqx/tsqx!saveX.action"//添加或修改保存主合同URL
			,url_remove			:basePath + "tsqx/tsqx!removeids.action"//删除URL
			,url_edit			:basePath + "tsqx/tsqx!edit.action"//修改查询数据
			,url_import			:basePath + "tsqx/tsqx!importTsqx.action"//导入
			,url_export			:basePath + "tsqx/tsqx!export.action"//导出
			,url_upstcd_Stbprp  :basePath + "stbprp/stbprp!upstcd_Stbprp.action"//下拉列表
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend(opt_control,opt_url,option));
		var TableQueryParams;
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);   
			opt_all.query_table.bootstrapTable($.extend(g_bootstrapTable_Options,
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
			    $("#query_table tbody tr[data-index="+i+"]").addClass("success");
			   }
			   //全不选时颜色恢复
			   function onUncheckAll(){
			    $("#query_table tbody tr").removeClass("success");
			   }
			   //选中一行改变表格背景色
			   function onCheck(rows){
			    $("#query_table tbody tr[data-uniqueid="+rows.id+"]").addClass("success");
			   }
			   //不选中时颜色恢复
			   function onUncheck(rows){
			    $("#query_table tbody tr[data-uniqueid="+rows.id+"]").removeClass("success");
			   }
			// 提交查询函数
			function queryParams(params) {  // 配置参数
				//查询条件
				TableQueryParams = params;
				var opt_parms={
					 "mTsqxFormBean.searchName"			:opt_control.query_searchName.val() // 查询关键字
				};
			    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			       "mTsqxFormBean.pageBean.limit"			: params.limit   // 页面大小
			      ,"mTsqxFormBean.pageBean.offset"			: params.offset  // 当前记录偏移条数
			      ,"mTsqxFormBean.pageBean.sort"			: params.sort  	  // 排序列名
			      ,"mTsqxFormBean.pageBean.sortOrder" 		: params.order   // 排位命令（desc，asc）
			    };
			    temp=$.extend(temp,opt_parms,opt);
				return temp;
			}
			// 双击事件
			function onDblClickRow(row){
				if (row) {
					_table(row.STCD,row.USERNAME);
				}
			}
			// 排序事件
			function onSort(name, order){
				_refresh();
			}
			function comm_onLoadSuccess(data){
				var data = $('#query_table_tsqx').bootstrapTable('getData', true);
				/*mergeCells(data,"STNM",1, $('#query_table_waterLevelFlow'));
				mergeCells(data,"LNNM",1, $('#query_table_waterLevelFlow'));
				mergeCells(data,"STCD",1, $('#query_table_waterLevelFlow'));*/
		}
		}
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend(opt_all,opt);
			opt_all.query_add.bind("click",event_add);
			opt_all.btn_del.bind("click",event_del);
			opt_all.query_ref.bind("click",event_ref);
			opt_all.btn_into_Tsqx.bind("click",upload_model_show);
			opt_all.btn_outAll_Tsqx.bind("click",exportAllTsqx);
			opt_all.btn_outPage_Tsqx.bind("click",exportPageTsqx);
			opt_all.query_add_tsqxData_x.bind("click",x_xzcs);
			opt_all.btn_save_tsqx.bind("click",btn_save_x);
			
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});
			
		}
		//下拉数据初始化
		this.InitSelect = function (){
			Load_select_stcd_rvsect();
		}
	    // 修改记录，调出窗体,公开函数
		this.edit=function(stcd,username) {
			_edit(stcd,username);
		}
		//单条删除
		this.del=function(stcd){
			if(stcd != null && stcd != ""){
				_del(stcd);
			}
		}
		function Load_select_stcd_rvsect(){
			opt_control.btn_stcd_rvsect.empty();
			var url = opt_all.url_upstcd_Stbprp;
			opt_control.btn_stcd_rvsect.append("<option value=''>请选择测站名称</option>");
			common_ajax(null,url, function(data) {
				var rows=data.rows;
				for(var i=0;i<rows.length;i++){
					opt_control.btn_stcd_rvsect.append("<option value='"+rows[i].STCD+"'>"+rows[i].STNM+"</option>");
				}
			});
		}
		//点击保存按钮
		function btn_save_x(){
			var $form=$('#info_form_tsqxData_x').serialize();
			common_ajax($form, opt_all.url_save_x, function(response){
	 			opt_all.edit_dialog_tsqxData_x.modal("hide");
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
		function exportAllTsqx(){
			var url= opt_all.url_export+"?";
			var data="mTsqxFormBean.pageBean.limit=99999999"
					+"&mTsqxFormBean.pageBean.offset=0" 
					+"&mTsqxFormBean.pageBean.sortOrder="+TableQueryParams.order
					+"&mTsqxFormBean.pageBean.sort="+TableQueryParams.sort
					+"&mTsqxFormBean.searchName="+opt_all.query_searchName.val();
			confirm("<i class='icon icon-reply'></i>&nbsp;导出全部","您确定要导出全部信息吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data;
               }
			});
		}
		
		//导出当前页
		function exportPageTsqx(){
			var url= opt_all.url_export+"?";
			var data_page="mTsqxFormBean.pageBean.limit="+TableQueryParams.limit
						 +"&mTsqxFormBean.pageBean.offset="+TableQueryParams.offset
						 +"&mTsqxFormBean.pageBean.sortOrder="+TableQueryParams.order
						 +"&mTsqxFormBean.pageBean.sort="+TableQueryParams.sort
						 +"&mTsqxFormBean.searchName"+opt_all.query_searchName.val();
			confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出当前页","您确定要导出当前页吗？","icon-info", function(result) {
			   if(result){
				   window.location.href=url+data_page;
			   }
			});
		}
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		// 批量删除
		function _removeids(){
           // 获取删除选中的ids
           var ids = g_select_and_tip(opt_all.query_table,"STCD");
           if (ids.length==0) return;
           
           $(".fixed-table-container tbody tr.selected td").addClass("row-bcground");
           
           var url= opt_all.url_remove+"?mTsqxFormBean.ids="+ids
            
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
		function _del(stcd){
			var url= opt_all.url_remove+"?mTsqxFormBean.ids="+stcd
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
		function _edit(stcd,username){
			Load_EditSelectData( 
				function(){ 
					LoadEditData(stcd,username);
				}
			);
		}
		
		function _save() {
	 		var json=opt_all.info_form.serialize();
	 		common_ajax(json, opt_all.url_save, function(response){
	 			opt_all.edit_dialog.modal("hide");
			    _refresh();
	 		});
		}
		
		//显示详情
		function _table(queryId,username){
			var url = opt_all.url_list+"?mTsqxFormBean.mTsqxInfoBean.stcd=" + queryId+"&mTsqxFormBean.mTsqxInfoBean.username=" + username;
			common_ajax(null,url, function(response) {
				for(var key in response.rows[0]){
				   if($("#"+key+"_detail")[0]){
					   $("#"+key+"_detail").html(response.rows[0][key]);
				   }
				}
			});
			opt_all.query_info_show.modal({
				show 	   : true
				,backdrop  : "static" // 背景遮挡
					,moveable  : true
			}).on('shown.zui.modal', function() {
			});
		}
		
		//上传文件
		this.uploading=function(contId,evalId,engineerCode){
			//履约评价的文件上传
			filesUpload_eval(evalId,engineerCode);
			//主合同的文件上传
			filesUpload(contId,engineerCode);
			filesUploadShow();
		}
		
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(stcd,username){
			var QM = [];
			var Q = [];
			var T = [];
			var index = [];
			if(stcd != null && stcd!=""){
				var url = opt_all.url_list+"?mTsqxFormBean.mTsqxInfoBean.stcd=" + stcd+"&mTsqxFormBean.mTsqxInfoBean.username=" + username;
				// 动态加载页面数据
				common_ajax(null,url, function(response) {
					var table = $("#query_table_tsqxData_x");
					table.empty();
					var tr = "<tr>"+
					"<td style='width: 90px; text-align: center; font-weight: bold; background: #f1f1f1'>"+
						"<lable>时段</lable></td>"+
					"<td style='width: 90px; text-align: center; font-weight: bold; background: #f1f1f1'>"+
						"<lable>流量</lable></td>"+
					"<td style='width: 90px; text-align: center; font-weight: bold; background: #f1f1f1'>"+
						"<lable>洪峰</lable></td>"+
					/*"<td style='width: 100px; text-align: center; font-weight: bold; background: #f1f1f1'>"+
						"<lable>修改时间：</lable></td>"+*/
					"<td style='width: 90px; text-align: center; font-weight: bold; background: #f1f1f1'>"+
						"<lable>操作</lable></td></tr>";
					table.append(tr);
					for(var i =0;i<response.rows.length;i++){
						$("#stcd_tsqx_x").val(response.rows[i].STCD);
						$("#stnm_tsqx_x").val(response.rows[i].STNM);
						$("#userName_tsqx_x").val(response.rows[i].USERNAME);
						QM.push(response.rows[i].QM);
						Q.push(response.rows[i].Q);
						T.push(response.rows[i].T);
						index.push(i);
						var tr = "<tr>"+
						"<td style='text-align: left'><input style='margin-left: 15px;width: 90px' class='a' id='qm_tsqx_x' value="+response.rows[i].QM+" name='qm'></td>"+
						"<td style='text-align: left'><input style='margin-left: 15px;width: 90px' class='a' id='q_tsqx_x' value="+response.rows[i].Q+" name='q'></td>"+
						"<td style='text-align: left'><input style='margin-left: 15px;width: 90px' class='a' id='t_tsqx_x' value="+response.rows[i].T+" name='t'></td>"+
						/*"<td style='text-align: left'><input style='margin-left: 15px;' class='a' id='moditime_zqrl_x' value="+response.mZqrlFormBean[i].MODITIME+" name='mZqrlFormBean.mZqrlInfoBean.moditime'></td>"+*/
						"<td style='text-align: left'>" +
						/*"<a href='#' onclick='addsj_(this)'>&nbsp;&nbsp;保存</a>" +*/
						"<a href='#' onclick='delsj(this)'>&nbsp;&nbsp;删除</a></td>"+
					"</tr>";
						table.append(tr);
					}
//					// 获取到数据，显示在界面上
					opt_all.edit_dialog_tsqxData_x.find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;修改退水曲线信息");
					opt_all.edit_dialog_tsqxData_x.modal({
						 show : true
						,backdrop : "static" // 背景遮挡
						,moveable : true
					}).on('hide.zui.modal', function() {
						_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
		            });
					//加载折线图
					var myChart = echarts.init(document.getElementById('myEcharts_tsqx'));
					option = {
							title: {
								text: '退水折线图'
							   	},
							tooltip: {
								trigger: 'axis'
								},
							legend: {
								 data:['洪峰','流量','时段']
								 },
							xAxis: {
								type: 'category',
							    boundaryGap: false,
								data: index
								},
							yAxis: {
								type: 'value'
								},
							series: [{
								name:'洪峰',
								type: 'line',
								data: T
								  },
								{
								 name:'流量',
								 data: Q,
								 type: 'line'
								},{
									 name:'时段',
									 data: QM,
									 type: 'line'
								}]
						};	
		            var monthLineChart = echarts.init(document.getElementById("myEcharts_tsqx"));
		            //清空画布，防止缓存
		            monthLineChart.clear();
		            myChart.setOption(option); 
			});
			}else{
				$("#stcd_tsqx").val("");
				$('#stcd_tsqx_').val("");
				$('#stcd_tsqx').prop('disabled',false);
				//先清除添加过的数据再弹窗
				opt_all.edit_dialog.find('.modal-title').html("<i class='icon icon-plus-sign'></i>&nbsp;新增退水曲线信息") ;
				opt_all.edit_dialog.modal({
					 show : true
					,backdrop : "static" // 背景遮挡
					,moveable : true
				}).on('hide.zui.modal', function() {
					_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
	            });
			}
		}
		
		//****绑定事件
		//绑定添加或修改事件
		
		function event_add(){
			_edit("");
		}

		
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		
		//****绑定事件******end
	    // 刷新
	    function _refresh(){
	    	opt_all.query_table.bootstrapTable('refresh');
	    }
	    this.flash=function(nm){
	    	engineeringNm=nm;
			_refresh();
		}
		// 重置主合同
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(true);
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
		function x_xzcs(){
			var table = $("#query_table_tsqxData_x");
			var tr = "<tr>"+
				"<td style='text-align: left'><input style='margin-left: 15px;width: 90px' name='qm' id='qm_tsqx_x'></td>"+
				"<td style='text-align: left'><input style='margin-left: 15px;width: 90px' name='q' id='q_tsqx_x'></td>"+
				"<td style='text-align: left'><input style='margin-left: 15px;width: 90px' name='t' id='t_tsqx_x'></td>"+
				/*"<td style='text-align: left'><input style='margin-left: 15px;' id='moditime_zqrl_x'></td>"+*/
				"<td style='text-align: left'>" +
				/*"<a href='#' onclick='addsj_(this)'>&nbsp;&nbsp;保存</a>" +*/
				"<a href='javascript:void(0)' onclick='delsj(this)'>&nbsp;&nbsp;删除</a></td>"+
			"</tr>";
			table.append(tr);
		}
		
		//删除参数
		delsj = function(obj){
			len=--len;
			$(obj).parent().parent().remove();
		  }
	};
})(jQuery);
			
