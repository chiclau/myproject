(function($) {
	//当前工程内码
	var engineeringNm = sessionStorage.getItem("engineeringNm");
	$.cjfa_Query = function(option) {
		//option自定义参数覆盖
		//A、界面控件变量
		var opt_control={
			 query_table 			:$('#query_table')		//页面BootStrapTable的ID
			//,btn_save				:$("#btn_save")		//保存方案
			,cjfa_fxjs				:$("#cjfa_fxjs")		
			//,btn_fj					:$("#btn_fj")			//分析计算按钮
			,info_form_cjfa			:$("#info_form_cjfa") //保存方案表单
			,info_form_fxjs			:$("#info_form_fxjs") //分析计算表单
			,modellist_form			:$("#modellist_form")		//添加编辑的表单
		}
		
		//B、请求地址URL
		var opt_url={
			//	url_list			:basePath + "cjfa/cjfa!list.action"//查询数据URL
				//,url_save			:basePath + "cjfa/cjfa!save.action"//添加或修改保存主合同URL
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
				}
	)
	);		
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
			function queryParams(params) {}
			// 双击事件
			function onDblClickRow(row){
				if (row) {
					_table(row.ID);
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
			opt_all.btn_fj.bind("click",event_add);
			//opt_all.btn_save.bind("click",_save);
			opt_all.info_form_fxjs.bootstrapValidator().on("success.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
				// 校验数据正确,执行保存数据
				_save();
				
			}).on("error.form.bv", function(e) {
				e.preventDefault(); // 去掉默认提交事件
			});	
			
		}
		}
		
		//新增和编辑函数
		function _edit(id){
			LoadEditData(id)
		}
		
		function LoadEditData(id){/*
			var myCharts = echarts.init(document.getElementById("cjfa_main"));
			var symbolSize = 3;
			var points = [];
			option = {
					title : {
				        text: '分析计算图表',
				        x: 'center',
				        align: 'right'
				    },
			    tooltip: {
			    	show:true,
			        formatter: function (params) {
			            var data = params.data || [0, 0];
			            return data[0].toFixed(2) + ', ' + data[1].toFixed(2);
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: [{
			    	type : 'category',
			    	boundaryGap : false,
		            data : function (){
		                var list = [];
		                for (var i = 1; i <= 6; i++) {
		                    if(i<= 12){
		                        list.push('2018-'+i);
		                    }else{
		                        list.push('2018-'+(i-12));
		                    }
		                }
		                return list;
		            }()
			    }],
			    yAxis: {
			        type: 'value',
			        axisLine: {onZero: false}
			    },
			    series: [
			        {
			            id: 'a',
			            type: 'line',
			            smooth: true,
			            symbolSize: symbolSize,
			            data:[800, 300, 500, 800, 300, 600,500,600]
			        },
			        {
			            id: 'b',
			            type: 'line',
			            smooth: true,
			            symbolSize: symbolSize,
			            data:[600, 300, 400, 200, 300, 300,200,400]
			        }
			    ]
			};
			myCharts.setOption(option);
			
			opt_all.cjfa_fxjs.find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;分析计算");
			opt_all.cjfa_fxjs.modal({
				 show : true
				,backdrop : "static" // 背景遮挡
				,moveable : true
			}).on('hide.zui.modal', function() {
				//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
            });
		*/}
		
		// 保存数据
		function _save() {
			// 参数需要保存的表单，保存url,需要更新的bootstrapTable,必须设置uniqueId: "id"
	 		var json=opt_all.info_form_cjfa.serialize();
	 		alert(opt_all.url_save)
	 		common_ajax(json, opt_all.url_save, function(response){
			    //重置表单
			    _reset();
			    // 刷新列表
			    _refresh();
	 		});
		}
		//****绑定事件
		function event_add(){
			_edit(0);
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
		//公开函数
		this.reset=function(){
			_reset();
		}
		
		// 重置窗体
	   /* function _reset(){
			opt_all.modellist_form.data('bootstrapValidator').resetForm(true);
		}*/
		////////////////////////////////////////////////////////////////////////////////
		//加载所有外键表到下拉框，无
		function Load_EditSelectData(callback){
			//所有编辑页面下拉框加载
			callback();
		}

	

	
})(jQuery);
			
