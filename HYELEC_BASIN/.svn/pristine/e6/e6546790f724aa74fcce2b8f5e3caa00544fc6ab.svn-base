(function($) {
	$.System_SysLogQ = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			table 			:$('#tbinfo_ListnmSysLog')		//BootStrapTable的ID
		}
		//B、数据状态参数变量
		var opt_state={
			 flag_cur		:1 ////审核状态,当前List状态 flag=0
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"system/syslog!list.action"			//查询数据URL
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend({},opt_control,opt_state,opt_url,option));
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
	         ,onSort		:onSort             // 排序事件
             ,rowStyle		:comm_rowStyle		//行样式，可自定义
             ,onLoadSuccess	:comm_onLoadSuccess //数据加载错误	
		}
		// 提交查询函数
		function queryParams(params) {  // 配置参数

		    var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		       "formBean.pageBean.limit"	: params.limit   // 页面大小
		      ,"formBean.pageBean.offset"	: params.offset  // 当前记录偏移条数
		      ,"formBean.pageBean.sort"		: params.sort  // 排序列名
		      ,"formBean.pageBean.sortOrder": params.order// 排位命令（desc，asc）
		      ,"formBean.infoBean.flag"	:1
		      ,"formBean.infoBean.sysflag"	:""
		    };
		    temp=$.extend({},temp,opt_tb_query);
		    return temp;
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
		
		this.refresh=function(){
			_refresh();
		}
	    // 刷新
	    function _refresh(){
	    	opt_all.table.bootstrapTable('refresh');
	    }  
	    
	};
})(jQuery);
			
