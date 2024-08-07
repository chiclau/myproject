$(function(){
	//加载下拉列表
	Load_select_stcd_zqrl();
});
var opt_control={
		btn_stcd_zvarl				:$('#stcd_zqrl')	
}
var shui ; //水位
var liu;  //流量
/**
 * 查询
 * @returns
 */
function search(){
	//测站
	//曲线

	$.ajax({
		url : basePath + "search/search!search.action",
		success : function(data) {
			var obj=JSON.parse(data); //将返回的String转换成Json对象取数据
	
		}
	});
	

}

/*function btn_fj(a,b){
	var myCharts = echarts.init(document.getElementById("cjfa_main"));
	var symbolSize = 3;
	var points = [];
	option = {
			title: {
		        text: '统计图表'
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['水位','流量']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: ['2018-1','2018-2','2018-3','2018-4','2018-5','2018-6','2018-7']
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [
		        {
		            name:'水位',
		            type:'line',
		            stack: '总量',
		            data:[120, 132, 101, 134, 90, 230, 210]
		        },
		        {
		            name:'流量',
		            type:'line',
		            stack: '总量',
		            data:[220, 182, 191, 234, 290, 330, 310]
		        }
		    ]
	};
	myCharts.setOption(option);
	
	$("#cjfa_fxjs").find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;统计图表");
	$("#cjfa_fxjs").modal({
		 show : true
		,backdrop : false // 背景遮挡
		,moveable : true
	}).on('hide.zui.modal', function() {
		//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
    });

}*/
/**
 * 下拉列表
 * @returns
 */
function Load_select_stcd_zqrl(){
	var selDom = $("#stcd_zqrl");
	selDom.empty();
	var url =basePath + "stbprp/stbprp!upstcd_Stbprp.action";
	selDom.prepend("<option value=''>请选择测站名称</option>");
	common_ajax(null,url, function(data) {
			for(var i=0;i<data.rows.length;i++){
				//alert(data.rows[i].STNM)
				selDom.append("<option value='"+data.rows[i].STCD +"'>"+data.rows[i].STNM+"</option>"); 
			}
	});
}

//////雨径流相关图
function btn_rr(){
	var myCharts1 = echarts.init(document.getElementById("rrff_main"));
	option = {
			title: {
				text: '降雨径流相关图'
			},
			tooltip: {
				trigger: 'axis'  
			},
			legend: {
				data:[]
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			xAxis: {
				type: 'category',
				boundaryGap: false,
				name:"R(mm)",
				nameLocation:'middle',
				data: []
				
			},
			yAxis: {
				name:"P(mm)",
				boundaryGap: false,
				type: 'value',
				
			},
			series: [
			]
	};
	$.ajax({
		url : basePath + "runoff/runoff!list.action",
		success : function(response) {
            var legends = [];  
            var Series = [];  
			var json=JSON.parse(response); //将返回的String转换成Json对象取数据
			var R =[];
			var P =[];
			var Item = function(){  
		            return {  
		                name:'',  
		                type:'line',  
		                data:[]  
		            } 
		        };
		    var arr = [];
			for (var i = 0; i < json.length; i++) {
				var it = new Item();
				P = json[i]['P']
				arr = json[i]['R']
				it.name = 'Pa='+json[i]['Pa']+'';  
				option.legend.data.push('Pa='+json[i]['Pa']+'')
				it.data = P;  
				Series.push(it);
				for(var j = 0;j<arr.length;j++){
					R.push(arr[j])
				}
			}
			option.xAxis.data =R; 
			option.series = Series; // 设置图表  
			console.log(option); 
			myCharts1.setOption(option,true);// 重新加载图表  
		}
	});
	$("#rrff_rain").find('.modal-title').html("<i class='icon icon-pencil'></i>&nbsp;雨径流相关图");
	$("#rrff_rain").modal({
		show : true
		,backdrop : false // 背景遮挡
			,moveable : true
	}).on('hide.zui.modal', function() {
		//_reset();//当第一次验证正确后，关闭窗体。再进来时，重置窗体(保留窗体上数据)。
	});

}