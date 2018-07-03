(function($) {
	$.System_SysMenuQ = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 treeGrid 		:$('#tbinfo_TreenmSysMenu')		//BootStrapTreeList的ID--
			,select_pcode	:$('#select_root_SysMenu') 	//选中根节点--
		}
		//C、请求地址URL
		var opt_url={
			 url_list		:basePath+"system/sysmenu!list.action"	    //查询数据URL
			,url_reflist	:basePath+"system/sysoperref!list.action"	    //查询数据URL
			,url_listroot   :basePath+"system/sysmenu!listroot.action"		//获取根数据URL--
		}

		//全部变量，自定义的覆盖默认变量
		var opt_all=($.extend({},opt_control,opt_url,option));
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
					var option = $("<option>").text("("+rows[i].FCODE+")"+rows[i].MENU_NAME).val(rows[i].FCODE);
					ctl_select.append(option);
				}
			});
			
		}
		//设置查询条件
		var opt_tb_query={};
		this.Init_TB_QueryParms=function(option){
			opt_tb_query=$.extend({},opt_tb_query,option);
		}
		//加载数据
		this.Init_TB_Data=function(opt){
			_LoadData(option);
		}
		 //加载treegrid数据
		function _LoadData(opt){
			var refApk=$("#refApk").val();
			var fcode=$('#select_root_SysMenu').val();
			var temp = {   
				"mSysMenuFormBean.pageBean.limit": 100000000   // 页面大小
				,"mSysMenuFormBean.pageBean.offset": 0  // 当前记录偏移条数	
				,"mSysMenuFormBean.mSysMenuInfoBean.fCode":fcode
				,"mSysOperRefFormBean.mSysRoleBean.roleCode":refApk
				,"SysOperRefFormBean.parmBean.parm1":"tree"
			};
			$.ajax({
				data:temp,
				url:opt_all.url_reflist,
				type: "POST",
				dataType:"json",
				success:function(response){
					var html=[];
					opt_all.treeGrid.html("");
					html.push("<thead><tr>");
					html.push("<th>系统菜单名称</th>");
					html.push("<th width='100'>系统菜单编码</th>");
					html.push("<th width='100'>授权状态</td>");
					html.push("<th width='100'>操作</th>");
					html.push("</tr></thead>");	
					var rows=response.rows;
					for (var i = 0 ; i< rows.length;i++){
						html.push(" <tr class='treegrid-"+rows[i].FCODE);
						if (rows[i].SUPER_CODE.length>0){
							html.push(" treegrid-parent-"+rows[i].SUPER_CODE);
						}
							html.push("' ondblclick='_edit("+rows[i].MENU_CODE+",true)'>");
							html.push("   <td>"+rows[i].MENU_NAME+"</td>");
							html.push("   <td>"+rows[i].FCODE+"</td>");
							if(rows[i].RELAID>0){
								html.push("   <td align='center' ><font color='green'>已授权</font></td>");
							}else{
								html.push("   <td align='center' ></td>");
							}
							html.push("   <td class='visible-md visible-lg'>");
							html.push("   &nbsp;<a href='#' title='功能授权' id='fun' onclick='rela_sq(\""+rows[i].MENU_CODE+"\",\"sq\")'><i class='icon icon-check'></i></a>");
							if (typeof(rows[i].RELAID)!="undefined" && !isNaN(rows[i].RELAID)){ 
								if(rows[i].RELAID>0){
									html.push("   &nbsp;<a href='#' title='取消授权' onclick='rela_sq(\""+rows[i].MENU_CODE+"\",\"qx\")'><i class='icon icon-times'></i></a>");
								}
							}
							html.push("   </td>");
							html.push(" </tr>");
					}
					
					opt_all.treeGrid.append(html.join(""));
					
					opt_all.treeGrid.addClass("table");
					opt_all.treeGrid.addClass("table-striped");
					opt_all.treeGrid.addClass("table-bordered");
					opt_all.treeGrid.addClass("table-condensed");//更为紧凑
					opt_all.treeGrid.treegrid({
						 initialState:'expanded'
						,expanderExpandedClass:  'icon icon-minus'
			            ,expanderCollapsedClass: 'icon icon-plus'
			        });
				}
			});
	   }
    };
})(jQuery);