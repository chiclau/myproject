(function($) {
	$.System_SysMenuQ = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 treeGrid 		:$('#tbinfo_TreenmSysMenu')		//BootStrapTreeList的ID--
			,select_pcode	:$('#select_root_SysMenu') 	//选中根节点--
		}
		//B、数据状态参数变量
		var opt_state={
			 flag_cur		:0 ////审核状态,当前List状态 flag=0
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"system/sysmenu!list.action"			//查询数据URL
			,url_listroot   :basePath+"system/sysmenu!listroot.action"		//获取根数据URL--
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend({},opt_control,opt_state,opt_url,option));
	    //////////////////////////////////////////////////////////////////////////////////////////////////
		//加载根数据
		this.LoadRootData=function(ctl_select){
			_LoadRootData(ctl_select);
		}
		// 动态加载页面基础数据：下拉框数据
		function _LoadRootData(ctl_select){
			//加载根单位下拉框数据
			common_ajax(null,opt_all.url_listroot, function(response) {
				ctl_select.empty();
				ctl_select.append($("<option>").text("显示全部数据").val(""));
				var rows=response.rows;
				for (var i = 0 ; i< rows.length;i++){
					var option = $("<option>").text("("+rows[i].SCODE+")"+rows[i].MENU_NAME).val(rows[i].SCODE);
					ctl_select.append(option);
				}
			});
			
		}
		//设置查询条件
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(opt){
			opt_tb_query=$.extend({},opt_tb_query,opt);
		}
		//加载数据
		this.Init_TB_Data=function(){
			_LoadData();
		}
	    //加载treegrid数据
		function _LoadData(){
		    var temp = {   
				  "mSysMenuFormBean.pageBean.limit": 100000000   // 页面大小
				 ,"mSysMenuFormBean.pageBean.offset": 0  // 当前记录偏移条数	
		 	      // 在此增加查询条件
				 ,"mSysMenuFormBean.mSysMenuInfoBean.fCode":opt_all.select_pcode.val()
				 ,"mSysMenuFormBean.mSysMenuInfoBean.state":1
		 	    };
		    
		    temp=$.extend({},temp,opt_tb_query);
			common_ajax(temp,opt_all.url_list, function(response) {
				var html="";
				opt_all.treeGrid.html("");
				/**表头*/
			html="<thead><tr>"
				+"<th>名称</th>"
				+"<th width='150'>编码</th>"
				+"<th width='60'>操作</th>"
				+"</tr></thead>";	
			//$treeGrid.append(html);
			/**内容*/
			var rows=response.rows;
			for (var i = 0 ; i< rows.length;i++){
				    html=html+" <tr class='treegrid-"+rows[i].FCODE;
				if (rows[i].SUPER_CODE.length>0){
					html=html+" treegrid-parent-"+rows[i].SUPER_CODE;
				}
					html=html+"' ondblclick='_edit("+rows[i].MENU_CODE+",true)'>"
                        +"   <td>"+rows[i].MENU_NAME+"</td>"
                        +"   <td>"+rows[i].FCODE+"</td>"
	                    +"   <td>"
	                    +"       &nbsp;<a href='#' title='过滤' onclick='Select_TreenmSysMenu(\""+rows[i].MENU_CODE+"\")'><i class='icon icon-filter'></i></a>"
	                    +"   </td>"
                        +" </tr>";
			}
			
			opt_all.treeGrid.append(html);
			
			opt_all.treeGrid.addClass("table");
			opt_all.treeGrid.addClass("table-striped");	 //
			opt_all.treeGrid.addClass("table-bordered"); //边框
			opt_all.treeGrid.addClass("table-condensed");//更为紧凑
			
			opt_all.treeGrid.treegrid({
				 initialState:'expanded'
				,expanderExpandedClass:  'icon icon-minus'
	            ,expanderCollapsedClass: 'icon icon-plus'
	        });	
		});
	}
  };
})(jQuery);


			
