(function($) {
	var opt_all;
	$.sysBasin = function(option) {
		//绑定页面按钮
		var opt_control={
			btn_add:$("#btn_add_ennmcd") //新增根节点按钮
			,select_pcode_:$("#select_pcode_basin")  //文本框改变查询
			,btn_save:$("#btn_save_ennmcd") //保存按钮
			,info_form:$("#info_form_ennmcd")
			,treeGrid:$("#treeGrid_ennmcd")
			,info_dialog:$("#info_dialog_ennmcd")
			,btn_into_pptn:$("#btn_into_sys") //导入按钮
			,import_dialog:$("#import_dialog")	//文件上传模态框
		};
		var opt_url={
			url_list		:basePath+"system/ennmcd!list.action"			        //查询数据URL
			,url_add		:basePath+"system/ennmcd!initChildTreeFormData.action"	//新增子节点数据URL--
			,url_edit		:basePath+"system/ennmcd!initRootTreeFormData.action"	//编辑根节点数据URL
			,url_save		:basePath+"system/ennmcd!saveTreeFormData.action"	    //保存数据URL
			,url_remove		:basePath+"system/ennmcd!removeIds.action"		    	//删除数据URL
			,url_listroot   :basePath+"system/ennmcd!listroot.action"		    	//获取根数据URL--
		};
		opt_all=($.extend(opt_control,opt_url,option));
		
		//初始化tree表格、下拉列表数据方法
		this.InitData=function(opt){
			opt_all=$.extend(opt_all,opt);
			_LoadBaseData();
			_LoadData(opt);
		}
		
		//初始化绑定按钮
		this.InitAddEditDel=function(){
			opt_all=$.extend(opt_all,opt);
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_save.bind("click",event_save);
			opt_all.select_pcode_.bind("change",event_change);
			opt_all.btn_into_pptn.bind("click",upload_model_show);
		}
		
		//动态加载页面下拉列表数据
		function _LoadBaseData(){
			common_ajax(null,opt_all.url_listroot, function(response) {
				opt_all.select_pcode_.empty();
				opt_all.select_pcode_.append($("<option>").text("显示全部数据").val(""));
				var rows=response.rows;
				for (var i = 0 ; i< rows.length;i++){
					var option = $("<option>").text("("+rows[i].RVCD+")"+rows[i].NAME).val(rows[i].RVCD);
					opt_all.select_pcode_.append(option);
				}
                comm_chose_init(opt_all.select_pcode_);
			});
		}
		
		//下拉列表改变事件查询
		function event_change(){
			var rvcd=$("#select_pcode_").val();
			var obj={
				"mEnnmcdFormBean.mEnnmcdInfoBean.rvcd":rvcd
			}
			_LoadData(obj);
		}
		
		//初始化tree表格数据
		function _LoadData(opt){
			var temp={
				"mEnnmcdFormBean.pageBean.limit": 100000000   // 页面大小
				,"mEnnmcdFormBean.pageBean.offset": 0  // 当前记录偏移条数
			};
			temp=$.extend(temp,opt);
			$.ajax({
				data:temp,
				url:opt_all.url_list,
				type: "POST",
				dataType:"json",
				success:function(response){
					var html=new Array();
					opt_all.treeGrid.empty();
					html.push("<thead><tr>");
					html.push("<th width='100'>流域名称</th>");
					html.push("<th width='100'>流域编码</th>");
					html.push("<th width='100'>经度</th>");
					html.push("<th width='100'>纬度</th>");
					html.push("<th width='100'>排序</th>");
					html.push("<th width='100'>操作</th>");
					html.push("</tr></thead>");	
					var rows=response.rows;
					for (var i = 0 ; i< rows.length;i++){
						var LGTD=rows[i].LGTD==null?"":rows[i].LGTD;
						var LTTD=rows[i].LTTD==null?"":rows[i].LTTD;
						var PAIXU=rows[i].PAIXU==null?"":rows[i].PAIXU;
						html.push(" <tr class='treegrid-"+rows[i].RVCD);
						if (rows[i].PID>0){
							html.push(" treegrid-parent-"+rows[i].PID);
						}
						html.push("' ondblclick='_edit("+rows[i].ID+",true)'>");
						html.push("   <td>"+rows[i].NAME+"</td>");
						html.push("   <td>"+rows[i].RVCD+"</td>");
						html.push("   <td>"+LGTD+"</td>");
						html.push("   <td>"+LTTD+"</td>");
						html.push("   <td>"+PAIXU+"</td>");
						html.push("   <td>");
						html.push("&nbsp;<a href='#' title='新增子项' onclick='_add_("+JSON.stringify(rows[i].RVCD)+")'><i class='icon icon-node'></i></a>");
						html.push("&nbsp;<a href='#' title='编辑信息，或在数据行上双击鼠标左键。' onclick='_edit_("+JSON.stringify(rows[i].RVCD)+")'><i class='icon icon-edit'></i></a>");
						html.push("&nbsp;<a href='#' title='删除本项及所有子项' onclick='_removeids_("+JSON.stringify(rows[i].RVCD)+")'><i class='icon icon-remove'></i></a>");
						html.push("   </td>");
						html.push(" </tr>");
					}
					opt_all.treeGrid.append(html.join(""));
					opt_all.treeGrid.addClass("table");
					opt_all.treeGrid.addClass("table-striped");
					opt_all.treeGrid.addClass("table-bordered");
					opt_all.treeGrid.addClass("table-condensed");//更为紧凑
					if ($("#select_pcode_").val()==""){
						opt_all.treeGrid.treegrid({
							initialState:'collapsed'
							,expanderExpandedClass:  'icon icon-minus'
					        ,expanderCollapsedClass: 'icon icon-plus'
						});
					}else{
						opt_all.treeGrid.treegrid({
							initialState:'expanded'
							,expanderExpandedClass:  'icon icon-minus'
					        ,expanderCollapsedClass: 'icon icon-plus'
						});
					}
				}
			});
		}
		
		//初始化新增子节点FORM表单
		this.add=function(id){
			add_(id);
		}
		
		function add_(id){
			_add(id);
		}
		
		//初始化新增子节点FORM表单数据
		function _add(id){
			Load_EditSelectData( 
				function(){ 
					LoadAddData(id);
				}
			);
		}
		
		//初始化新增子节点FORM表单
		function LoadAddData(id){
			$("#ids_sysBasin").val(id);
			var title="信息维护";
			var obj={
				"mEnnmcdFormBean.mEnnmcdInfoBean.rvcd":id	
			};
			common_ajax(obj, opt_all.url_add, function(response){
				comm_loadFormData_flag(response.mEnnmcdInfoBean,"_sysBasin"); //显示本级数据
				showFormDataP(response.mPEnnmcdInfoBean,id);  //显示父节点数据
				title="<i class='icon icon-edit'></i> 新增子节点";
				opt_all.info_dialog.find('.modal-title').html(title) ;
				opt_all.info_dialog.modal({
					show : true
					,backdrop : false
					,moveable : true
				}).on('shown.zui.modal', function() {
					
				});
			});
		}
		
		//初始化编辑FORM表单
		this.edit=function(id,onlyread){
			_edit(id,onlyread);
		}
		
		//初始化form表单数据
		function _edit(id,onlyread){
			Load_EditSelectData( 
				function(){ 
					LoadEditData(id,onlyread);
				}
			);
		}
		
		//初始化FORM表单
		function LoadEditData(id,onlyread){
			var title="信息维护";
			var obj={
				"mEnnmcdFormBean.mEnnmcdInfoBean.rvcd":id	
			};
			$.ajax({
				data:obj,
				url:opt_all.url_edit,
				type: "POST",
				dataType:"json",
				success:function(response){
					comm_loadFormData_flag(response.mEnnmcdInfoBean,"_sysBasin"); //显示本级数据
					showFormDataP(response.mPEnnmcdInfoBean,id);  //显示父节点数据
					if(id>0){
						title="<i class='icon icon-edit'></i> 编辑信息";
					}else{
						title="<i class='icon icon-edit'></i> 新增信息";
					}
					opt_all.info_dialog.find('.modal-title').html(title) ;
					if (onlyread){
						opt_all.btn_save.hide();
					} else {
						opt_all.btn_save.show();
					}
					opt_all.info_dialog.modal({
						show : true
						,backdrop : false
						,moveable : true
					}).on('shown.zui.modal', function() {
						
					});
				}
			});
		}
		
		//加载父节点数据
		function showFormDataP(data,id){
			var rvcd=data.rvcd;
			var rvnm=data.rvnm;
			var prvcd=data.prvcd;
			$("#pname_sysBasin").val(rvcd+"_"+prvcd);
			if(rvcd.length>0){
				$("#pname_sysBasin").val("("+rvcd+"_"+prvcd+")"+rvnm+"");
			}else{
				$("#pname_sysBasin").val("根节点");
			}
		}
		
		//保存事件
		function event_save(){
			var json=opt_all.info_form.serialize();
			common_ajax(json, opt_all.url_save, function(response){
				opt_all.info_dialog.modal("hide");
				_refresh();
				var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
			    msg.show();	
	 		});
		}
		
		//删除操作
		this.removeids=function(id){
			_removeids(id);
		}
		
		function _removeids(id){
			var obj={
				"mEnnmcdFormBean.mEnnmcdInfoBean.rvcd":id
			};
			bootbox.confirm("确认需要删除本级及所有子项记录吗?", function(result) {
				if(result){
					$.ajax({
						data:obj,
						url:opt_all.url_remove,
						type: "POST",
						dataType:"json",
						success:function(response){
							_refresh();
							var msg = new $.zui.Messager("消息提示："+response.message, {placement: "center",type:"primary"});
						    msg.show();	
						}
					});
				}
			});
		}
		
		//文件上传模态框
		function upload_model_show(){
			   var url=basePath+"system/ennmcd!importPptn.action";
			   filesUpload(url,"myUploader",_refresh); 
			   opt_all.import_dialog.find('.modal-title').html("<i class='icon icon-cloud-download'></i>&nbsp;流域水系信息") ;
			   opt_all.import_dialog.modal({
					 show : true
					,backdrop : false // 背景遮挡
					,moveable : true
				}).on('shown.zui.modal', function() {
					
	          });
		   }
		
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
	/**************************************************************************************/
		
		//绑定新增事件
		function event_add(){
			_edit(0);
		}
		// 刷新
	    function _refresh(){
	    	_LoadData();
	    }
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback){
			callback();
		}
	}
})(jQuery);