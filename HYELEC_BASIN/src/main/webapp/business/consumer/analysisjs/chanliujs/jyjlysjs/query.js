(function($) {
	 $("#t").hide();
	var myCharts = echarts.init(document.getElementById("jy_main"));
	var symbolSize = 3;
	var points = [];

	option = {
			/*toolbox: { //可视化的工具箱
                show: true,
                feature: {
                	tjt:{//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字    
                        show:true,//是否显示    
                        title:'统计图', //鼠标移动上去显示的文字    
                        icon:'image://../../../../common/images/ben.png', //图标    
                        option:{},    
                     }, 
                     sjb:{//自定义按钮 danielinbiti,这里增加，selfbuttons可以随便取名字    
                         show:true,//是否显示    
                         title:'数据表', //鼠标移动上去显示的文字    
                         icon:'image://../../../../common/images/ben.png', //图标    
                         option:{},    
                         onclick:function(option1) {//点击事件,这里的option1是chart的option信息    
                         	
                          }    
                      }
                }
            },*/
			title : {
		        text: '降雨径流要素计算图表',
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
	
	$("#btn_table_jy").click(function(){
		   $("#jy_main").hide();
		   $("#tbinfo_jy").show();
		   $("#t").show();
		   table();
		})
		$("#btn_chart_jy").click(function(){
		   $("#jy_main").show();
		   $("#tbinfo_jy").hide();
		   $("#t").hide();
		   $("#tbinfo_jy").empty();
		})
		function table(){
		$("#tbinfo_jy").empty();
			var tr = "";
			tr+=
				"<tr>"+
					"<td>2018-01</td>"+
					"<td>2018-02</td>"+
					"<td>2018-03</td>"+
					"<td>2018-04</td>"+
					"<td>2018-05</td>"+
					"<td>2018-06</td>"+
				"</tr>"+ 
				"<tr>"+
					"<td>12</td>"+
					"<td>34</td>"+
					"<td>65</td>"+
					"<td>35</td>"+
					"<td>34</td>"+
					"<td>53</td>"+
				"</tr>"+
				"<tr>"+
					"<td>12</td>"+
					"<td>34</td>"+
					"<td>65</td>"+
					"<td>35</td>"+
					"<td>34</td>"+
					"<td>53</td>"+
				"</tr>"+
				"<tr>"+
					"<td>12</td>"+
					"<td>34</td>"+
					"<td>65</td>"+
					"<td>35</td>"+
					"<td>34</td>"+
					"<td>53</td>"+
				"</tr>";			
			$("#tbinfo_jy").append(tr);
		}
	
})(jQuery);
			
