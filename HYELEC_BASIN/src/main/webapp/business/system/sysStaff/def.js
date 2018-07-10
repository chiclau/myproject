(function($) {
	$.sysStaff = function(option) {
		var opt_control={
			btn_ref     	:$('#btn_ref_staff')		//刷新按钮
			,table 			:$('#tbinfo_staff')	//BootStrapTable的ID
			,searchInput	:$('#searchName')	//模糊查询input
			,btn_save		:$('#btn_staff_save')	//模糊查询input
			,info_dialog	:$('#set_user_dialog')
		}
		var opt_url={
			url_staffList	:basePath+"system/sysstaff!list.action"	//加载人员信息数据URL
			,url_setStaffName:basePath+"system/sysgroup!setStaffNameBySysGroupId.action" //加载人员信息数据URL
			,url_setStaffName_:basePath+"system/sysrole!setStaffNameBySysRoleId.action"
		}
		var _opt_all_=($.extend({},opt_control,opt_url,option));
		//查询条件及页面分页信息设置
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		}
		//D、BootStrapTable参数
		var opt_tb_base={
	      	  url			:_opt_all_.url_staffList   // 请求后台的URL（*）
	         ,queryParams	:queryParams		// 传递参数（*）
	         ,onSort		:onSort             // 排序事件
             ,rowStyle		:comm_rowStyle		//行样式，可自定义
             ,onLoadSuccess	:comm_onLoadSuccess //数据加载错误	
		}
		function queryParams(params) {  // 配置参数
			//查询条件
		    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		       "mSysStaffFormBean.pageBean.limit"	: params.limit   // 页面大小
		      ,"mSysStaffFormBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
		      ,"mSysStaffFormBean.pageBean.sort"		: params.sort  // 排序列名
		      ,"mSysStaffFormBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
		      ,"mSysStaffFormBean.searchName"		: opt_control.searchInput.val() // 查询关键字
		    };
		    temp=$.extend({},temp,opt_tb_query);
		    return temp;
		}
		// 排序事件
		function onSort(name, order){
			_refresh;
		}
		var opt_tb_all=$.extend({},g_bootstrapTable_Options,opt_tb_base);
		this.Init_TB_Option=function(){
			opt_tb_all=$.extend({},opt_tb_all,opt);
		}
		this.Init_TB_Data=function(){
			_opt_all_.table.bootstrapTable(opt_tb_all);
		}
		this.InitAddEditDel=function(opt){
			_opt_all_=$.extend({},_opt_all_,opt);
			_opt_all_.btn_ref.bind("click",event_ref);
			_opt_all_.btn_save.bind("click",event_save);
		}
		//保存事件
		function event_save(){
			var message,url;
			var obj;
			var id=$("#id_group").val(); //分组信息主键id
			var flag=$("#mark").val(); //区分标记
			var staffcode=g_select_and_tip(_opt_all_.table,"STAFF_CODE");//人员编号
	        if (staffcode.length==0){
	        	return false;
	        }
	        if(flag=="role"){
	        	var str=staffcode.split(',').length;
	        	if (str>=2){
	        		var msg = new $.zui.Messager("消息提示：请选择一条记录！！！", {placement: "center",type:"primary"});
				    msg.show();	
		        	return false;
		        }
	        	obj={
	        		"mSysRoleFormBean.ids":id
	        		,"mSysRoleFormBean.mSysStaffInfoBean.staffCode":staffcode
		    	};
		        url=_opt_all_.url_setStaffName_;
	        }else{
	        	obj={
	    	        "mSysGroupFormBean.ids":id
	    	        ,"mSysGroupFormBean.mSysStaffInfoBean.staffCode":staffcode
	    	    };
	        	url=_opt_all_.url_setStaffName;
	        }
	        $.post(url,obj,function(response){
	        	var obj_=JSON.parse(response);
	        	if(obj_.success=="success"){
	        		message="设置人员成功!!!";
	        	}else if(obj_.error=="error"){
	        		message="设置人员失败!!!";
	        	}
				_opt_all_.info_dialog.modal("hide");
				if(flag=="role"){
					$("#tbinfo_role").bootstrapTable('refresh');
				}else{
					$("#tbinfo_group").bootstrapTable('refresh');
				}
				var msg = new $.zui.Messager("消息提示："+message, {placement: "center",type:"primary"});
			    msg.show();
	        });
		}
		//绑定刷新事件
		function event_ref(){
			 _refresh();
		}
		//****绑定事件******end
	    // 刷新
	    function _refresh(){
	    	_opt_all_.table.bootstrapTable('refresh');
	    }  
	    //公开函数
	    this.refresh=function(){
	    	_refresh();
	    }
	  };
	})(jQuery);
