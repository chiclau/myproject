(function($) {
	$.sysAccout = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 btn_add		:$('#btn_add_user')		 //增加按钮
			,btn_del     	:$('#btn_del_user')		 //删除按钮	
			,btn_ref     	:$('#btn_ref_user')		 //刷新按钮
			,table 			:$('#tbinfo_user')		 //BootStrapTable的ID
			,searchInput	:$('#searchName_user')	 //模糊查询input
			,info_dialog	:$('#info_dialog_user')  //新增和编辑对应的窗体，注意和info_form的区别
			,info_form		:$('#info_form_user')    //新增和编辑对应的表单，注意和info_dialog的区别
            ,btn_save		:$('#btn_save_user')	 //保存按钮
            ,dialog_flag	:$('#dialog_user_flag')	 //审核对话框
            ,form_flag		:$('#form_user_flag')	 //审核对话框表单
            ,btn_flag		:$('#btn_flag_user')	 //审核按钮
            ,btn_initPwd	:$('#btn_initPwd_user')	 //初始化密码按钮
            ,userName_id	:$('#userName_user')	 //验证账户是否唯一
            ,retPwd_id	    :$('#retPwd_user')	     //验证密码是否重复
            ,btn_userFlag	:$('#btn_save_userFlag') //验证密码是否重复
            ,staffName_staff	:$('#staffName_staff')	 //验证账户是否唯一
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"system/sysaccount!list.action"		//查询数据URL
			,url_edit		:basePath+"system/sysaccount!edit.action"		//编辑数据URL
			,url_save		:basePath+"system/sysaccount!save.action"		//保存数据URL
			,url_remove		:basePath+"system/sysaccount!delete.action"		//删除数据URL
			,url_flag		:basePath+"system/sysaccount!flag.action"		//审核数据URL
			,url_initPwd	:basePath+"system/sysaccount!initPwd.action"	//初始化密码数据URL
			,url_userName	:basePath+"system/sysuser!validateAccountInfo.action"	//验证账户是否存在URL
			,url_initFlag	:basePath+"system/sysaccount!initFlag.action"	//初始化审核框数据URL
			,url_staffName	:basePath+"system/sysstaff!validateAccountInfo.action" //验证真实姓名是否存在
		}
		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend({},opt_control,opt_url,option));
		//////////////////////////////////////////////////////////////////////////////////////////////////
		//查询条件及页面分页信息设置
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		}
		//D、BootStrapTable参数
		var opt_tb_base={
	      	  url			:opt_all.url_list   // 请求后台的URL（*）
	         ,queryParams	:queryParams		// 传递参数（*）
	         ,onDblClickRow	:onDblClickRow		// 行双击事件
	         ,onSort		:onSort             // 排序事件
             ,rowStyle		:comm_rowStyle		//行样式，可自定义
             ,onLoadSuccess	:comm_onLoadSuccess //数据加载错误
             ,uniqueId		:"STAFF_CODE"
		}
		function queryParams(params) {  // 配置参数
			//查询条件
		    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		       "mSysAccountFormBean.pageBean.limit"	: params.limit   // 页面大小
		      ,"mSysAccountFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
		      ,"mSysAccountFormBean.pageBean.sort"		: params.sort  // 排序列名
		      ,"mSysAccountFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
		      ,"mSysAccountFormBean.searchName"		: opt_control.searchInput.val() // 查询关键字
		    };
		    temp=$.extend({},temp,opt_tb_query);
		    return temp;
		}
		// 双击事件
		function onDblClickRow(row){
			if (row) {
				_edit(row.STAFF_CODE,true);
			}
		}
		// 排序事件
		function onSort(name, order){
			_refresh;
		}
		
		//全部BootStrapTable参数变量
		var opt_tb_all=$.extend({},g_bootstrapTable_Options,opt_tb_base);
		//公用函数，初始化BootStrapTable参数变量，自定义覆盖
		this.Init_TB_Option=function(opt){
			opt_tb_all=$.extend({},opt_tb_all,opt);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////
	    //加载主表数据，必须提供url_list，toolbar,opt-传递查询条件，可覆盖
		this.Init_TB_Data=function(){
			opt_all.table.bootstrapTable(opt_tb_all);
			// 提交查询函数
		}////end this.InitData
		////////////////////////////////////////////////////////////////////////////////////////////////////
		// 初始化增加、修改和删除,公开函数
		this.InitAddEditDel=function(opt){
			opt_all=$.extend({},opt_all,opt);
			opt_all.btn_add.bind("click",event_add);
			opt_all.btn_del.bind("click",event_del);
			opt_all.btn_ref.bind("click",event_ref);
			opt_all.userName_id.bind("blur",event_userName);
			opt_all.retPwd_id.bind("blur",event_retPwd);
			opt_all.btn_userFlag.bind("click",event_userFlag);
			opt_all.staffName_staff.bind("blur",event_staffName);
			
			// 保存button类型为submit
			opt_all.info_form.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// //校验数据正确
			});	
		}
		
		//验证账户是否存在 
		function event_userName(){
			var flag=false;
			var obj={
				"mSysUserFormBean.mSysUserInfoBean.userName":$("#userName_user").val()
			};
			$.ajax({
				data:obj,
				url:opt_all.url_userName,
				type: "POST",
				dataType:"json",
				success:function(response){
					if(response.success=="success"){
						flag=true;
					}else if(response.error=="error"){
						var msg = new $.zui.Messager("消息提示：账户名称不能重复!!!", {placement: "center",type:"primary"});
					    msg.show();	
					    $("#userName_user").val("");
					}
				}
			});
			return flag;
		}
		
		//验证真实姓名是否存在 
		function event_staffName(){
			var flag=false;
			var obj={
				"mSysStaffFormBean.mSysStaffInfoBean.staffName":$("#staffName_staff").val()
			};
			$.ajax({
				data:obj,
				url:opt_all.url_staffName,
				type: "POST",
				dataType:"json",
				success:function(response){
					if(response.success=="success"){
						flag=true;
					}else if(response.error=="error"){
						var msg = new $.zui.Messager("消息提示：真实姓名不能重复!!!", {placement: "center",type:"primary"});
					    msg.show();	
					    $("#staffName_staff").val("");
					}
				}
			});
			return flag;
		}
		
		//验证密码是否重复
		var userPwd_;
		function event_retPwd(){
			var flag=false;
			var userPwd=$("#userPwd_user").val().trim();
			var newPwd=$("#retPwd_user").val().trim();
			if(userPwd==newPwd){
				flag=true;
				userPwd_=$("#retPwd_user").val().trim();
			}else{
				var msg = new $.zui.Messager("消息提示：请输入正确密码!!!", {placement: "center",type:"primary"});
			    msg.show();	
			    $("#retPwd_user").val("");
			}
			return flag;
		}
		
		// 修改记录，调出窗体,公开函数
		this.edit=function(id,onlyread) {
			_edit(id);
		}
		//新增和编辑函数
		function _edit(id,onlyread){
			//加载Edit页面基本选择数据，成功后调用显示编辑页面
			//加载字典分类数据下拉框数据
			Load_EditSelectData( 
				function(){ 
					LoadEditData(id,onlyread);
				}
			);
		}
		//加载Edit页面上数据，并调出增加或编辑页面显示
		function LoadEditData(id,onlyread){
			var obj={
				"mSysAccountFormBean.mSysStaffInfoBean.staffCode":id
			};
			$.ajax({
				data:obj,
				url:opt_all.url_edit,
				type: "POST",
				dataType:"json",
				success:function(response){
					var sysUser=response.mSysUserInfoBean;
	                var sysStaff=response.mSysStaffInfoBean;
	                 
	                var title="信息维护";
					if (id=="") {
						title="<i class='icon icon-file-o'></i> 新增信息";
					}else if (id!=0){
						title="<i class='icon icon-edit'></i> 编辑信息";
					}
					
					//是否显示保存按钮
					if (onlyread){
						opt_all.btn_save.hide();
					} else {
						opt_all.btn_save.show();
					}
					opt_all.info_dialog.find('.modal-title').html(title);
					opt_all.info_dialog.modal({
						 show : true
						,backdrop : "static" // 背景遮挡
						,moveable : true
					}).on('shown.zui.modal', function() {
						if (id=="") {
							$(".hid").show();
							clearFormData(sysUser);
						}else{
							$(".hid").hide();
							backFillData(sysUser,sysStaff);
						}
		            });
				}
			});
		}
		
		//清空FORM表单数据
		function clearFormData(sysUser){
			$("#StaffCode_user").val("");
			$("#userCode_user").val("");
			$("#userName_user").val("");
			$("#userPwd_user").val(sysUser.userPwd);
			$("#state_user").val("");
			$("#staffCode_staff").val("");
			$("#staffName_staff").val("");
			$("#staffDept_staff").val("");
			$("#treeNmDept_staff").val("");
			$("#state_staff").val("");
			$("#linkPhone_staff").val("");
			$("#jig_staff").val("");
		}
		
		//FORM表单回填数据
		function backFillData(sysUser,sysStaff){
			var staffAddress=sysStaff.staffAddress.split(',');
			$("#StaffCode_user").val(sysUser.staffCode);
			$("#userCode_user").val(sysUser.userCode);
			$("#userName_user").val(sysUser.userName);
			$("#userPwd_user").val(sysUser.userPwd);
			$("#state_user").val(sysUser.state);
			$("#staffCode_staff").val(sysStaff.staffCode);
			$("#staffName_staff").val(sysStaff.staffName);
			$("#staffDept_staff").val(sysStaff.staffDept);
			$("#treeNmDept_staff").val(sysStaff.treeNmDept);
			$("#state_staff").val(sysStaff.state);
			$("#linkPhone_staff").val(sysStaff.linkPhone);
			$("#jig_staff").val(sysStaff.jig);
			$("#selectp_staff").val(staffAddress[0]);
			$("#selectc_staff").val(staffAddress[1]);
		}
		
		//初始化省份与市区数据
		this.initProvAndCity=function(){
			initProv();
			initCity();
		}
		
		//初始化省份数据
		function initProv(){
			$('#selectp_staff').empty();
			var url=basePath+"system/addvcd!listroot.action";
			$('#selectp_staff').append("<option value=''>请选择</option>");
			common_ajax(null, url, function(response){
				var rows=response.rows;
				for(var i=0;i<rows.length;i++){
					$('#selectp_staff').append("<option value='"+rows[i].ADDVCD+"'>"+rows[i].NAME+"</option>");
				}
			});
		}
		
		//初始化市区数据
		function initCity(){
			$('#selectc_staff').empty();
			var url=basePath+"system/addvcd!loadCityData.action";
			$('#selectc_staff').append("<option value=''>请选择</option>");
			common_ajax(null, url, function(response){
				var rows=response.rows;
				for(var i=0;i<rows.length;i++){
					$('#selectc_staff').append("<option value='"+rows[i].ADDVCD+"'>"+rows[i].NAME+"</option>");
				}
			});
		}
		
	    // 保存数据
		function _save() {
			var pro=$("#selectp_staff").find("option:selected").val();  //省份
			var city=$("#selectc_staff").find("option:selected").val(); //市区
			var obj={
				"mSysAccountFormBean.mSysUserInfoBean.StaffCode":$("#StaffCode_user").val().trim()
				,"mSysAccountFormBean.mSysUserInfoBean.userCode":$("#userCode_user").val().trim()
				,"mSysAccountFormBean.mSysUserInfoBean.state":$("#state_user").val().trim()
				,"mSysAccountFormBean.mSysUserInfoBean.userName":$("#userName_user").val().trim() //账户名称
				,"mSysAccountFormBean.mSysUserInfoBean.userPwd":$("#userPwd_user").val().trim() //用户密码
				,"mSysAccountFormBean.mSysStaffInfoBean.staffCode":$("#staffCode_staff").val().trim()
				,"mSysAccountFormBean.mSysStaffInfoBean.state":$("#state_staff").val().trim()
				,"mSysAccountFormBean.mSysStaffInfoBean.jig":$("#jig_staff").val().trim()
				,"mSysAccountFormBean.mSysStaffInfoBean.staffName":$("#staffName_staff").val().trim() //真实姓名
				,"mSysAccountFormBean.mSysStaffInfoBean.treeNmDept": $("#treeNmDept_staff").val().trim() //所属单位
				,"mSysAccountFormBean.mSysStaffInfoBean.staffDept":$("#staffDept_staff").val().trim()  //部门
				,"mSysAccountFormBean.mSysStaffInfoBean.linkPhone":$("#linkPhone_staff").val().trim() //联系电话
				,"mSysAccountFormBean.mSysStaffInfoBean.staffAddress":(pro+","+city).trim() //现居住地
			}
			$.ajax({
				data:obj,
				url:opt_all.url_save,
				type: "POST",
				dataType:"json",
				success:function(response){
					var message="";
					if(response.success=="success"){
						message="保存成功!!!";
					}else if(response.error=="error"){
						message="保存失败!!!";
					}
					opt_all.info_dialog.modal("hide");
					refresh_user();
					var msg = new $.zui.Messager("消息提示："+message, {placement: "center",type:"primary"});
				    msg.show();	
				}
			});
		}
	    
		//初始化审核框
		this._flag_=function(staffCode){
			_flag(staffCode);
		}
		function _flag(staffCode){
			$("#staffCode_userFlag").val(staffCode);
			var title="审核";
			var obj={
				"mSysAccountFormBean.mSysStaffInfoBean.StaffCode":staffCode
			};
			$.ajax({
				data:obj,
				url:opt_all.url_initFlag,
				type: "POST",
				dataType:"json",
				success:function(response){
					comm_loadFormData_flag(response.mSysUserInfoBean,"_userFlag");
				}
			});
			opt_all.info_dialog.find('.modal-title').html(title) ;
			opt_all.dialog_flag.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('shown.zui.modal', function() {
				
			});
		}
		//审核数据
		function event_userFlag(){
			var title;
			var obj={
				"mSysAccountFormBean.mSysUserInfoBean.StaffCode":$("#staffCode_userFlag").val(),
				"mSysAccountFormBean.mSysStaffInfoBean.StaffCode":$("#staffCode_userFlag").val(),
				"mSysAccountFormBean.mSysUserInfoBean.state":$("#state_userFlag").val(),
				"mSysAccountFormBean.mSysStaffInfoBean.state":$("#state_userFlag").val()
			};
			$.ajax({
				data:obj,
				url:opt_all.url_flag,
				type: "POST",
				dataType:"json",
				success:function(response){
					if(response.success=="success"){
						title="审核成功！！！";
					}else if(response.error=="error"){
						title="审核失败！！！";
					}
					opt_all.dialog_flag.modal("hide");
					refresh_user();
					var msg = new $.zui.Messager("消息提示："+title, {placement: "center",type:"primary"});
				    msg.show();	
				}
			});
		}
		//初始化密码
		this.initPwd=function(staffCode){
			_initPwd(staffCode);
		}
		function _initPwd(staffCode){
			var title;
			bootbox.confirm("您确认初始化密码【123456】吗?", function(result) {
				if(result){
					$.ajax({
						data:{"mSysAccountFormBean.mSysStaffInfoBean.staffCode":staffCode},
						url:opt_all.url_initPwd,
						type: "POST",
						dataType:"json",
						success:function(response){
							if(response.success=="success"){
								title="初始化密码成功！！！";
							}else if(response.error=="error"){
								title="初始化密码失败！！！";
							}
							refresh_user();
							var msg = new $.zui.Messager("消息提示："+title, {placement: "center",type:"primary"});
						    msg.show();	
						}
					});
	            }
			}); 
		}
		
	    // 批量删除
		function _removeids(){
           var ids=g_select_and_tip(opt_all.table,"STAFF_CODE");
           if (ids.length==0){
        	   return;
           }
           var url= opt_all.url_remove;
           var obj={
        	   "mSysAccountFormBean.mSysStaffInfoBean.staffCode":ids
           };
		   bootbox.confirm("确认需要删除数据吗?", function(result) {
			   if(result){
				   $.post(url,obj,function(response){ 
					   var obj_=JSON.parse(response);
					   if(obj_.success=="success"){
							title="删除成功！！！";
						}else if(obj_.error=="error"){
							title="删除失败！！！";
						}
					   _refresh();
					    var msg = new $.zui.Messager("消息提示："+title, {placement: "center",type:"primary"});
					    msg.show();
				   });
               }
			});  
		}
		
	 //////////////////////////////////////////////////////////////////
		//****绑定事件
		//绑定新增事件
		function event_add(){
			_edit("");
		}
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		//绑定删除事件
		function event_del(){
			_removeids();
		}
		
		//****绑定事件******end
	    //公开函数
	    this.refresh=function(){
	    	_refresh();
	    }
	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
		//公开函数
		this.reset=function(){
			_reset();
		}
		// 重置窗体
		function _reset(){
			opt_all.info_form.data('bootstrapValidator').resetForm(false);
		}
		//加载所有外键表到下拉框
		function Load_EditSelectData(callback){
			//所有编辑页面下拉框加载
			callback();
		}
	   };
	})(jQuery);
