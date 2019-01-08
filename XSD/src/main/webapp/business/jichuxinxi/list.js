﻿var xzqhdm=[];
var areaWidth=['90%','90%']
      	var colors =['#007bbb','#008e57','#933d92','#c48f00','#A0522D'];
$(function(){
	var xzlyqh="";
	getZtree(xzlyqh);//获取ztree
	jcxx(xzlyqh);//基础信息
//	checked();
	 initJszt(xzlyqh);//加载建设状态
	 initKffs(xzlyqh);//开发方式
	 initDzsl(0);//电站数量	
	 initZjrl(0);//装机
})

function getYixuanId(){
	var treeObj=$.fn.zTree.getZTreeObj("tree1");
    nodes=treeObj.getCheckedNodes(true);
    var arr = ""; // 向后台传送的id字符串
    for(var i = 0;i<nodes.length;i++){
    	if(nodes[i].level<=3){
        		arr += "," + nodes[i].id
    	}
    }
    arr = arr.substr(1)
   // alert(arr.length);
    return arr;
}

 function getZtree(xzlyqh){
	$.ajax({
        url : basePath + "jcxx/jcxx!getZtree.action",
        type : "post",
        dataType : "JSON",
        async : true,
        traditional: true,
        data : {"xzlyqh":xzlyqh},
        success : function(response) {
          if(response.reflag==1||response.reflag=="1"){
        		 $.fn.zTree.init($("#tree1"), setting, response.treeData);
        		 //默认展开一级节点
        		  var treeObj = $.fn.zTree.getZTreeObj("tree1");
                  var nodes = treeObj.getNodes();
                  for (var i = 0; i < nodes.length; i++) { //设置节点展开
                      treeObj.expandNode(nodes[i], true, false, true);
                      treeObj.checkNode(nodes[i], true, true);
                  }
        	//  layer.msg("加载数据成功！");
          }else{
          	layer.msg(response.message);
          }
        }
    });
}

var setting = {	
		check: {
            enable: true
        },
	    	 data: {
	                simpleData: {
	                    enable: true,
	                    idKey: "codeID",
	                    pIdKey: "parentID",
	                    rootPId: 0
	                }
	            },
    	callback:{
			onCheck: zTreeOnCheck , //选中事件
			//onAsyncSuccess: zTreeOnAsyncSuccess,
		}
};

var address=[];
function checked(){
	var treeObj=$.fn.zTree.getZTreeObj("tree1");
    nodes=treeObj.getCheckedNodes(true);
    for(var i=0;i<nodes.length;i++){
		if(nodes[i].level==2){//选省
			address.push(nodes[i].name);
		}
	}
}
var sheng=[];
var shi=[];
var xian=[];
function zTreeOnCheck(event, treeId, treeNode) {
	var treeObj=$.fn.zTree.getZTreeObj("tree1");
    nodes=treeObj.getCheckedNodes(true);
	var arr=[];
	var bj=2;
	address=[];
	 sheng=[];
	 shi=[];
	 xian=[];
	//alert(nodes.name);
	if(nodes.length>0){//选择了
		//debugger;
		if(nodes.length==3638||nodes.length==3653){//全选
			arr=[];
			bj=0;
			jcxx(arr);
			initJszt1(sheng,shi,xian)
			initKffs1(sheng,shi,xian);
		}else if(nodes.length==1352){//长江经济带
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].level==1){
				}else if(nodes[i].level==2){
					sheng.push(nodes[i].codeID);
				}
			}
				xzqhdm=arr;
				console.log(arr.length);
			jcxx1(sheng,shi,xian);
			initJszt1(sheng,shi,xian)
			initKffs1(sheng,shi,xian);
		}else{
			for(var i=0;i<nodes.length;i++){
				if(nodes[i].level==1){
				}else if(nodes[i].level==2){
					sheng.push(nodes[i].codeID);
				}else if(nodes[i].level==3){
					shi.push(nodes[i].codeID);
				}else if(nodes[i].level==4){
					xian.push(nodes[i].codeID);
				}
			}
			jcxx1(sheng,shi,xian);
			initJszt1(sheng,shi,xian)
			initKffs1(sheng,shi,xian);
		}
		
	/*	console.log("sheng_:"+sheng);
		console.log("shi_:"+shi);
		console.log("xian_:"+xian);*/
	}else{
		console.log(arr);
		//alert('反选')
		var response={
				jcxx1:[{name:'500以下',value:0}
						,{name:'500-1万',value:0}
						,{name:'1万-5万',value:0}]
			,jcxx2:[{name:'500以下',value:0}
						,{name:'500-1万',value:0}
						,{name:'1万-5万',value:0}],//['长江流域','珠江流域','淮河流域','黄河流域','东南沿海诸河','其他'
				szly:{jcxx2_2:[//所在流域，电站数量
					{name:'长江流域',value:0}
					,{name:'珠江流域',value:0}
					,{name:'淮河流域',value:0}
					,{name:'黄河流域', value:0}
					,{name:'东南沿海诸河',value:0}
					,{name:'其他',value:0}
				],jcxx2_1:[//所在流域 装机规模
					{name:'长江流域',value:0}
					,{name:'珠江流域',value:0}
					,{name:'淮河流域',value:0}
					,{name:'黄河流域', value:0}
					,{name:'东南沿海诸河',value:0}
					,{name:'其他',value:0}
				]
				},
				sfyxmhzqk:{//是否有项目核准情况
					sfyxmhz1:[
						{name:'是',value:0}
						,{name:'否',value:0}
						,{name:'未知',value:0}
					],
					sfyxmhz2:[
						{name:'是',value:0}
						,{name:'否',value:0}
						,{name:'未知',value:0}
					],
		
				},
				sjnfdl:0,
				bwqk:{//        		        data:['接入电网','直供电(化工)','直供电(其他)','直供电(民用电)','其他']
					bwqk1:[
						{name:'接入电网',value:0}
						,{name:'直供电(化工)',value:0}
						,{name:'直供电(其他)',value:0}
						,{name:'直供电(民用电)', value:0}
						,{name:'其他',value:0}
					],
					bwqk1_sum:[0,0,0,0,0],
					bwqk2:[
						{name:'接入电网',value:0}
						,{name:'直供电(化工)',value:0}
						,{name:'直供电(其他)',value:0}
						,{name:'直供电(民用电)', value:0}
						,{name:'其他',value:0}
					],
					bwqk2_sum:[0,0,0,0,0],
				},
				zjly:{//        		      ['国有','集体','民营','混合']
					zjly1:[
						{name:'国有',value:0}
						,{name:'集体',value:0}
						,{name:'民营',value:0}
						,{name:'混合', value:0}
					],
					zjly1_sum:[0,0,0,0,0],
					zjly2:[
						{name:'国有',value:0}
						,{name:'集体',value:0}
						,{name:'民营',value:0}
						,{name:'混合', value:0}
					],
					zjly2_sum:[0,0,0,0,0],
				}
		}
		var xzlyqh="";
		loadEcharts(response,xzlyqh);
		initJszt0(xzlyqh);//建设状态
		initKffs0(xzlyqh);//开发方式
	
		};
}

 function jcxx(xzlyqh){
	$.ajax({
        url : basePath + "jcxx/jcxx!queryJcxxData.action",
        type : "post",
        dataType : "JSON",
        traditional: true,
        data : {"xzlyqh":[xzlyqh]},
        success : function(response) {
          if(response.reflag==1||response.reflag=="1"){
        	  loadEcharts(response,xzlyqh);
        	  layer.msg("加载数据成功！");
          }else{
          	layer.msg(response.message);
          }
        }
    });
}
 
 function jcxx1(sheng,shi,xian){
		$.ajax({
	        url : basePath + "jcxx/jcxx!queryJcxxData1.action",
	        type : "post",
	        dataType : "JSON",
	        traditional: true,
	        data : {"sheng":[sheng],"shi":[shi],"xian":[xian]},
	        success : function(response) {
	          if(response.reflag==1||response.reflag=="1"){
	        	  loadEcharts(response,xian);
	        	  layer.msg("加载数据成功！");
	          }else{
	          	layer.msg(response.message);
	          }
	        }
	    });
	}
 var zjlx_zj_zdz;//装机类型 总电站数量
 var zjlx_zj_zrl;//装机类型 总容量
 var zjlx_zj1;
 var zjlx_zj2;
 var zjlx_zj3;
 var zjlx_zj4;
 var zjlx_zj5;
 var zjlx_zj6;
 var bwqk_zdz;//并网情况 总电站数量
 var bwqk_zrl;//并网情况 总容量
 var bwqk_dz1;
 var bwqk_dz2;
 var bwqk_dz3;
 var bwqk_dz4;
 var bwqk_dz5;
 var bwqk_rl1;
 var bwqk_rl2;
 var bwqk_rl3;
 var bwqk_rl4;
 var bwqk_rl5;
 var bwqk_dz_arr;
 var bwqk_rl_arr;
 var hz_zdz;
 var hz_zrl;
 var hz_dz1;
 var hz_dz2;
 var hz_dz3;
 var hz_rl1;
 var hz_rl2;
 var hz_rl3;
 var zjlyzdz;//资金来源总电站
 var zjlyzrl;//资金来源总容量
 var zjly1;//资金来源电站
 var zjly2;//资金来源容量
function loadEcharts(response,xzlyqh){
//	debugger;
	  var jcxx1=response.jcxx1;
	  if(jcxx1.length==3){
			zjlx_zj_zdz= jcxx1[0].value+jcxx1[1].value+jcxx1[2].value;
			zjlx_zj1=jcxx1[0].value;
			zjlx_zj2=jcxx1[1].value;
			zjlx_zj3=jcxx1[2].value;
	  }
	  var jcxx_=response.jcxx2;
	  var jcxx2=[]
	  for (var i = 0; i < jcxx_.length; i++) {
		  var data = jcxx_[i]
		  data.value = parseFloat((data.value/10000).toFixed(2))
		  jcxx2.push(data)
	  }
	  zjlx_zj4=(jcxx2[0].value).toFixed(2);
	  zjlx_zj5=(jcxx2[1].value).toFixed(2);
	  zjlx_zj6=(jcxx2[2].value).toFixed(2);
	  zjlx_zj_zrl=(jcxx2[0].value+jcxx2[1].value+jcxx2[2].value).toFixed(2);
	  var szly=response.szly;
	  var jcxx2_ = szly.jcxx2_2;
	  var jcxx2_2 = [];
	  for (var i = 0; i < jcxx2_.length; i++) {
		  var data = jcxx2_[i]
		  data.value = parseFloat((data.value/10000).toFixed(2))
		  jcxx2_2.push(data)
	}
	  var szlyleng=[];
		for (var i = 0; i < szly.jcxx2_1.length; i++) {
			szlyleng.push(szly.jcxx2_1[i].name);
	}
	  var  zjly=response.zjly;
	  zjly1=zjly.zjly1;
	  zjly2=zjly.zjly2;
	  var bwqk= response.bwqk;
	  if( bwqk.bwqk1.length==5){
		  bwqk_dz1=  bwqk.bwqk1[0].value;
		  bwqk_dz2=  bwqk.bwqk1[1].value;
		  bwqk_dz3=  bwqk.bwqk1[2].value;
		  bwqk_dz4=  bwqk.bwqk1[3].value;
		  bwqk_dz5=  bwqk.bwqk1[4].value;
		  bwqk_rl1= bwqk.bwqk2[0].value;
		  bwqk_rl2= bwqk.bwqk2[1].value;
		  bwqk_rl3= bwqk.bwqk2[2].value;
		  bwqk_rl4= bwqk.bwqk2[3].value;
		  bwqk_rl5= bwqk.bwqk2[4].value;
		  bwqk_dz_arr= bwqk.bwqk1;
		  bwqk_rl_arr=bwqk.bwqk2;
	  }
	  var  sfyxmhzqk= response.sfyxmhzqk;
	 // debugger;
	  if(sfyxmhzqk.sfyxmhz1.length==3){
		  hz_zdz= sfyxmhzqk.sfyxmhz1[0].value+sfyxmhzqk.sfyxmhz1[1].value+sfyxmhzqk.sfyxmhz1[2].value;//是否有项目核准总电站数量
			 hz_dz1= sfyxmhzqk.sfyxmhz1[0].value;
			 hz_dz2= sfyxmhzqk.sfyxmhz1[1].value;
			 hz_dz3= sfyxmhzqk.sfyxmhz1[2].value;
			
	  }
	  var sfyxmhz_ = sfyxmhzqk.sfyxmhz2
	  var sfyxmhz2 = [];
	  for (var i = 0; i < sfyxmhz_.length; i++) {
		  var data = sfyxmhz_[i]
		  data.value = parseFloat((data.value/10000).toFixed(2))
		  sfyxmhz2.push(data)
	 }
	  if(sfyxmhz_.length==3){
		  hz_zrl=((sfyxmhz2[0].value+sfyxmhz2[1].value+sfyxmhz2[2].value)).toFixed(2) ;//是否有项目核准总容量
		  hz_rl1=sfyxmhz2[0].value;
		  hz_rl2=sfyxmhz2[1].value;
		  hz_rl3=sfyxmhz2[2].value;
	  }
		var sjnfdl=response.sjnfdl;
	  var sum = [];
	    var yer = [];
	    var sum1 = [];
	    if( sjnfdl.length>0){
	    	for (var i = 0; i < sjnfdl.length; i++) {
				sum.push(sjnfdl[i].sun);
				yer.push(sjnfdl[i].yer);
				sum1.push(sjnfdl[i].sum)
			}
	    }
        var title7="全国按装机规模分类统计";
        var title8="全国小水电所在流域分布";
        var title9="全国小水电设计年发电量发展历程";
        var title10="全国小水电资金来源方式";
        var title11="全国小水电并网情况统计方式";

      	//var newString =xzlyqh.toString().replace(/,/g,"/");
      	//$(".jc_title").text(newString+"  ");
        // 使用刚指定的配置项和数据显示图表。
   
        var myChart = echarts.init(document.getElementById('zjrl1'));
        // 指定图表的配置项和数据
        var option = {
	     		  title : {
	     		        text: '按装机规模分类统计(电站数量)',
	     		        x:'center'
	     		    },
	     		 tooltip: {
	     		        trigger: 'item',
	     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
	     		    },
	     		    legend: {
	          		     x : 'center',
	       		        y : 'bottom',
	          		        data:['500以下','500-1万','1万-5万',]
	          		    },
	     		     color:colors,
	     		    series: [
	     		    	 {
		     		            name:'总数',
		     		            type:'pie',
		     		            center:['50%','50%'],
		     		            selectedMode: 'single',
		     		            radius: [0, '32.5%'],
		     		            label: {
		     		            	show:true,
		     		                normal: {
		     		                    position: 'center',
		     		                    formatter: '{b}\n {c}',
		     		                    color:'white'
		     		                }
		     		            },
		     		            labelLine: {
		     		                normal: {
		     		                    show: false
		     		                }
		     		            },
		     		           data:[{'value':  jcxx1[0].value+jcxx1[1].value+jcxx1[2].value, "name":'总数(座)' }]
		     		        },
	     		        {
	     		            name:'装机容量',
	     		            type:'pie',
	     		            radius: ['40%', '55%'],
	     		            label: {
	     		                normal: {
	     		                	show: true,
	     		                    formatter: '{b}: {c}({d}%)'
	     		                }
	     		            },
	     		            data:jcxx1
	     		        }
	     		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			//alert(address.length)
			layer.open({
				  type: 2, 
				  title: '按装机规模分类统计(装机规模:'+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+0,
			});
		});
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.onresize = myChart.resize;
        
        var myChart = echarts.init(document.getElementById('zjgm'));
        // 指定图表的配置项和数据
        option = {
	     		  title : {
	     		        text: '按装机规模分类统计(装机规模)',
	     		        x:'center'
	     		    },
	     		 tooltip: {
	     		        trigger: 'item',
	     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
	     		    },
	    		    legend: {
	     		        orient: 'horizontal',
	     		        bottom: 5,
	     		        data:['500以下','500-1万','1万-5万']
	     		    },
	     		     color:colors,
	     		    series: [
	     		    	 {
		     		            name:'总数(万千瓦)',
		     		            type:'pie',
		     		           center:['50%','50%'],
		     		            selectedMode: 'single',
		     		            radius: [0, '32.5%'],
		     		            label: {
		     		            	show:true,
		     		                normal: {
		     		                    position: 'center',
		     		                    formatter: '{b}\n {c}',
		     		                    color:'white'
		     		                }
		     		            },
		     		  data:[{'value':(jcxx2[0].value+jcxx2[1].value+jcxx2[2].value).toFixed(2), "name":'总数(万千瓦)' }]
		     		        },
	     		        {
	     		            name:'装机容量',
	     		            type:'pie',
	     		            radius: ['40%', '55%'],
	     		            label: {
	     		                normal: {
	     		                	show: true,
	     		                    formatter: '{b}: {c}({d}%)'
	     		                }
	     		            },
	     		            data:jcxx2
	     		        }
	     		    ]
        	};
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			layer.open({
				  type: 2, 
				  title: '按装机规模分类统计(装机规模:'+name+')',
				  area:areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+1,
			});
		});
        myChart.setOption(option);
        window.onresize = myChart.resize;
    	
        var myChart = echarts.init(document.getElementById('szly1'));
        // 指定图表的配置项和数据

        var option = {
	     		  title : {
	     		        text: '小水电所在流域分布(电站数量)',
	     		        x:'center'
	     		    },
	     		 tooltip: {
	     		        trigger: 'item',
	     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
	     		    },
	    		    legend: {
	     		        orient: 'horizontal',
	     		        bottom: -5,
	     		        data:['长江流域','珠江流域','淮河流域','黄河流域','东南沿海诸河','其他']
	     		    },
	     		     color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D','#989898',],
	     		    series: [
	     		    	 {
		     		            name:'所在流域',
		     		            type:'pie',
		     		           center:['50%','43%'],
		     		            selectedMode: 'single',
		     		            radius: [0, '32.5%'],
		     		            label: {
		     		            	show:true,
		     		                normal: {
		     		                    position: 'center',
		     		                    formatter: '{b}\n {c}',
		     		                    color:'white'
		     		                }
		     		            },
		     		  data:[{'value':szly.jcxx2_1[0].value+
		     			 szly.jcxx2_1[1].value+
		     			szly.jcxx2_1[2].value+
		     			szly.jcxx2_1[3].value+
		     			szly.jcxx2_1[4].value+
		     			szly.jcxx2_1[5].value
		     			  , "name":'总数(座)' }]
		     		        },
	     		        {
	     		            name:'所在流域',
	     		            type:'pie',
	     		            radius: ['40%', '55%'],
	     		           center:['50%','43%'],
	     		            label: {
	     		                normal: {
	     		                	show: true,
	     		                    formatter: '{b}: {c}({d}%)'
	     		                }
	     		            },
	     		            data:szly.jcxx2_1
	     		        }
	     		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			//alert(address.length)
			layer.open({
				  type: 2, 
				  title:'小水电所在流域分布('+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+2,
			});
		});
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
       // window.onresize = myChart.resize;
        

        var myChart = echarts.init(document.getElementById('szly2'));
        // 指定图表的配置项和数据
        var option = {
	     		  title : {
	     		        text: '小水电所在流域分布(装机规模)',
	     		        x:'center'
	     		    },
	     		 tooltip: {
	     		        trigger: 'item',
	     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
	     		    },
	    		    legend: {
	     		        orient: 'horizontal',
	     		        bottom: -5,
	     		        data:['长江流域','珠江流域','淮河流域','黄河流域','东南沿海诸河','其他']
	     		    },
	     		     color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D','#989898',],
	     		    series: [
	     		    	 {
		     		            name:'总数',
		     		            type:'pie',
		     		           center:['50%','43%'],
		     		            selectedMode: 'single',
		     		            radius: [0, '32.5%'],
		     		            label: {
		     		            	show:true,
		     		                normal: {
		     		                    position: 'center',
		     		                    formatter: '{b}\n {c}',
		     		                    color:'white'
		     		                }
		     		            },
		     		  data:[{'value':(jcxx2_2[0].value+
	     						 jcxx2_2[1].value+
		     						jcxx2_2[2].value+
		     						jcxx2_2[3].value+
		     						jcxx2_2[4].value+
		     						jcxx2_2[5].value).toFixed(2), "name":'总数(万千瓦)' }]
		     		        },
	     		        {
	     		            name:'所在流域',
	     		            type:'pie',
	     		            radius: ['40%', '55%'],
	     		           center:['50%','43%'],
	     		            label: {
	     		                normal: {
	     		                	show: true,
	     		                    formatter: '{b}: {c}({d}%)'
	     		                }
	     		            },
	     		            data:jcxx2_2
	     		        }
	     		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			//alert(address.length)
			layer.open({
				  type: 2, 
				  title: '小水电所在流域分布('+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+2,
			});
		});
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
       // window.onresize = myChart.resize;
        
   
        var sum3=0;
        var sum4=0;
        for(var i=0;i<zjly.zjly1_sum.length;i++){
        	sum3+=zjly.zjly1_sum[i];
        	sum4+=zjly.zjly2_sum[i];
        }
        zjlyzdz=sum3;
        zjlyzrl=sum4;
        // （全国）小水电资金来源方式
        var myChart = echarts.init(document.getElementById('zjly1'));
        var option = {
        		 title : {
        		        text:'小水电资金来源方式(电站数量)',
        		        x:'center'
        		    },
         		 tooltip: {
       		        trigger: 'item',
       		        formatter: "{a} <br/>{b}: {c} ({d}%)"
       		    },
       		    legend: {
       		     x : 'center',
    		        y : 'bottom',
       		        data:['国有','集体','民营','混合']
       		    },
       		     color:colors,
       		    series: [
       		        {
       		         name:'总数',
 		            type:'pie',
 		            center:['50%','46.5%'],
 		            selectedMode: 'single',
 		           radius: [0, '32.5%'],
       		            label: {
       		                normal: {
       		                 position: 'center',
 		                    formatter: '{b}\n {c}',
 		                    color:'white'
       		                }
       		            },
       		         data:[
   		                {value:sum3, name:'总数(座)', selected:true}
   		            ]
       		        },
       		        {
       		            name:'电站数量',
       		            type:'pie',
       		            radius: ['40%', '55%'],
       		         label: {
 		                normal: {
 		                    show: true,
 		                    formatter: '{b}: {c}({d}%)'
 		                }
 		            },
       		            data:zjly.zjly1
       		        }
       		    ]
        };

        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			//alert(address.length)
			layer.open({
				  type: 2, 
				  title: '小水电资金来源方式('+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+9,
			});
		});
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.onresize = myChart.resize;
        
        //（全国）小水电资金来源方式
        var myChart = echarts.init(document.getElementById('zjly2'));
        var option = {
        		 title : {
        		        text:'小水电资金来源方式(装机规模)',
        		        x:'center'
        		    },
         		 tooltip: {
       		        trigger: 'item',
       		        formatter: "{a} <br/>{b}: {c} ({d}%)"
       		    },
       		    legend: {
       		     x : 'center',
    		        y : 'bottom',
       		        data:['国有','集体','民营','混合']
       		    },
       		     color:colors,
       		    series: [
       		        {
       		         name:'总数',
 		            type:'pie',
 		            center:['50%','46.5%'],
 		            selectedMode: 'single',
 		           radius: [0, '32.5%'],       		       
 		           label: {
       		                normal: {
       		                 position: 'center',
 		                    formatter: '{b}\n {c}',
 		                    color:'white'
       		                }
       		            },
       		         data:[
   		                {value:(sum4/10000) .toFixed(2), name:'总数(万千瓦)', selected:true}
   		            ]
       		        },
       		        {
       		            name:'装机规模',
       		            type:'pie',
       		            radius: ['40%', '55%'],
       		         label: {
 		                normal: {
 		                    show: true,
 		                    formatter: '{b}: {c}({d}%)'
 		                }
 		            },
       		            data:zjly.zjly2
       		        }
       		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			//alert(address.length)
			layer.open({
				  type: 2, 
				  title: '小水电资金来源方式('+name+')',
				  area:areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+10,
			});
		});

        myChart.setOption(option);
        window.onresize = myChart.resize;
        //（全国）小水电并网情况统计方式
        var sum5=0;
        var sum6=0;
        for(var i=0;i<bwqk.bwqk1_sum.length;i++){
        	sum5+=bwqk.bwqk1_sum[i];
        	sum6+=bwqk.bwqk2_sum[i];
        }
        bwqk_zdz=sum5
        bwqk_zrl=sum6;
        var myChart = echarts.init(document.getElementById('bwqk1'));
        // 指定图表的配置项和数据
        var option = {
        		 title : {
        		        text: '小水电并网情况统计(电站数量)',
        		     x:'center'
        		    },
        		 tooltip: {
        		        trigger: 'item',
        		        formatter: "{a} <br/>{b}: {c} ({d}%)"
        		    },
        		    legend: {
        		    	  x : 'center',
        	   		        y : 'bottom',
        	   		      data:['接入电网','直供电(化工)','直供电(其他)','直供电(民用电)','其他']
        		    },
        		    color:colors,
        		    series: [
        		        {
        		            name:'总数',
        		            type:'pie',
        		            center:['45%','50%'],
        		            selectedMode: 'single',
        		            radius: [0, '32.5%'],
        		            label: {
        		                normal: {
        		                    position: 'center',
        		                    formatter: '{b}\n {c}',
        		                    color:'white'
        		                }
        		            },
        		            data: [{value:sum5 , name:'总数(座)', selected:true}]
        		        },
        		        {
        		            name:'并网情况',
        		            type:'pie',
        		            radius: ['40%', '55%'],
        		            center:['45%','55%'],
        		            label: {
        		                normal: {
        		                    show: true,
        		                    formatter: '{b}: {c}({d}%)'
        		                }
        		            },
        		            data:bwqk.bwqk1
        		        }
        		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			//alert(address.length)
			layer.open({
				  type: 2, 
				  title: '小水电并网情况统计('+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+11,
			});
		});
        myChart.setOption(option);
        window.onresize = myChart.resize;
  
        //小水电并网情况统计方式
        var myChart = echarts.init(document.getElementById('bwqk2'));
        // 指定图表的配置项和数据
        var option = {
        		 title : {
        		        text: '小水电并网情况统计(装机规模)',
        		     x:'center'
        		    },
        		 tooltip: {
        		        trigger: 'item',
        		        formatter: "{a} <br/>{b}: {c} ({d}%)"
        		    },
        		    legend: {
        		    	  x : 'center',
        	   		        y : 'bottom',
        		        data:['接入电网','直供电(化工)','直供电(其他)','直供电(民用电)','其他']
        		    },
        		    color:colors,
        		    series: [
        		        {
        		            name:'总数',
        		            type:'pie',
        		            center:['45%','50%'],
        		            selectedMode: 'single',
        		            radius: [0, '32.5%'],
        		            label: {
        		                normal: {
        		                    position: 'center',
        		                    formatter: '{b}\n {c}',
        		                    color:'white'
        		                }
        		            },
        		            data: [{value:sum6.toFixed(2) , name:'总数(万千瓦)', selected:true}]
        		        },
        		        {
        		            name:'并网情况',
        		            type:'pie',
        		            radius: ['40%', '55%'],
        		            center:['45%','55%'],
        		            label: {
        		                normal: {
        		                    show: true,
        		                    formatter: '{b}: {c}({d}%)'
        		                }
        		            },
        		            data:bwqk.bwqk2
        		        }
        		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			//alert(address.length)
			layer.open({
				  type: 2, 
				  title: '小水电并网情况统计('+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+12,
			});
		});
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.onresize = myChart.resize;
        
       
        //（全国）小水电项目核准情况统计
        var myChart = echarts.init(document.getElementById('xmhzqk1'));
        // 指定图表的配置项和数据
        var option = {
	     		  title : {
	     		        text: '小水电项目核准情况统计(电站数量)',
	     		        x:'center'
	     		    },
	     		 tooltip: {
	     		        trigger: 'item',
	     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
	     		    },
	     		    legend: {
	          		     x : 'center',
	       		        y : 'bottom',
	          		        data:['是','否','未知']
	          		    },
	     		     color:colors,
	     		    series: [
	     		    	 {
		     		            name:'总数',
		     		            type:'pie',
		     		            center:['50%','50%'],
		     		            selectedMode: 'single',
		     		            radius: [0, '32.5%'],
		     		            label: {
		     		            	show:true,
		     		                normal: {
		     		                    position: 'center',
		     		                    formatter: '{b}\n {c}',
		     		                    color:'white'
		     		                }
		     		            },
		     		            labelLine: {
		     		                normal: {
		     		                    show: false
		     		                }
		     		            },
		     		           data:[{'value':sfyxmhzqk.sfyxmhz1[0].value+sfyxmhzqk.sfyxmhz1[1].value+sfyxmhzqk.sfyxmhz1[2].value , "name":'总数(座)' }]
		     		        },
	     		        {
	     		            name:'',
	     		            type:'pie',
	     		            radius: ['40%', '55%'],
	     		            label: {
	     		                normal: {
	     		                	show: true,
	     		                    formatter: '{b}: {c}({d}%)'
	     		                }
	     		            },
	     		            data:sfyxmhzqk.sfyxmhz1
	     		        }
	     		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			layer.open({
				  type: 2, 
				  title: '小水电项目核准情况统计('+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+13,
			});
		});
        myChart.setOption(option);
        window.onresize = myChart.resize;
        
        var myChart = echarts.init(document.getElementById('xmhzqk2'));
        // 指定图表的配置项和数据
        var option = {
	     		  title : {
	     		        text: '小水电项目核准情况统计(装机规模)',
	     		        x:'center'
	     		    },
	     		 tooltip: {
	     		        trigger: 'item',
	     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
	     		    },
	     		    legend: {
	          		     x : 'center',
	       		        y : 'bottom',
	          		        data:['是','否','未知',]
	          		    },
	     		     color:colors,
	     		    series: [
	     		    	 {
		     		            name:'总数',
		     		            type:'pie',
		     		            center:['50%','50%'],
		     		            selectedMode: 'single',
		     		            radius: [0, '32.5%'],
		     		            label: {
		     		            	show:true,
		     		                normal: {
		     		                    position: 'center',
		     		                    formatter: '{b}\n {c}',
		     		                    color:'white'
		     		                }
		     		            },
		     		            labelLine: {
		     		                normal: {
		     		                    show: false
		     		                }
		     		            },
		     		           data:[{'value':((sfyxmhz2[0].value+sfyxmhz2[1].value+sfyxmhz2[2].value)).toFixed(2)  , "name":'总数(万千瓦)' }]
		     		        },
	     		        {
	     		            name:'',
	     		            type:'pie',
	     		            radius: ['40%', '55%'],
	     		            label: {
	     		                normal: {
	     		                	show: true,
	     		                    formatter: '{b}: {c}({d}%)'
	     		                }
	     		            },
	     		            data:sfyxmhz2
	     		        }
	     		    ]
        };
        myChart.off("click");//防止累计触发
        myChart.on('click', function (params) {
			var name = params.name;
			layer.open({
				  type: 2, 
				  title:'小水电项目核准情况统计('+name+')',
				  area: areaWidth,
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+14,
			});
		});
        myChart.setOption(option);
        window.onresize = myChart.resize;
}


function initKffs0(address){
	 // 基于准备好的dom，初始化echarts实例
   var myChart = echarts.init(document.getElementById('kffs1'));
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: '小水电开发方式统计',
		     		        x : 'center'
		     		    },
		     		 tooltip: {
		     		        trigger: 'item',
		     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		     		    },
		     		    legend: {
		     		        orient: 'horizontal',
		     		        bottom: 5,
		     		        data:['坝式','混合式','引水式','未知']
		     		    },
		     		     color:colors,
		     		    series: [
		     		    	 {
			     		            name:'总数',
			     		            type:'pie',
			     		            center:['50%','46.5%'],
			     		            selectedMode: 'single',
			     		            radius: [0, '32.5%'],
			     		            label: {
			     		            	show:true,
			     		                normal: {
			     		                    position: 'center',
			     		                    formatter: '{b}\n {c}',
			     		                    color:'white'
			     		                }
			     		            },
			     		            labelLine: {
			     		                normal: {
			     		                    show: false
			     		                }
			     		            },
			     		           data:[{"value":0, "name":'总数(座)', selected:true}]
			     		           
			     		        },
		     		        {
		     		            name:'',
		     		            type:'pie',
		     		            radius: ['40%', '55%'],
		     		            label: {
		     		                normal: {
		     		                	show: true,
		     		                    formatter: '{b}: {c}({d}%)'
		     		                }
		     		            },
		     		            data:[
		    						{name:'坝式',value:0}
		    						,{name:'混合式',value:0}
		    						,{name:'引水式', value:0}
		    						,{name:'未知',value:0}
		    					],
		     		        }
		     		    ]
		     };
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '小水电数量/容量统计图',
					  area:areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame.jsp?name='+name+"&bj="+1,
				});
			});
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
}
function initKffs(address){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('kffs1'));
    var kffsLeng = [];
    var kffs = [];
    var datas = [];
    var value = 0;
     $.ajax({
  		url:basePath+"jcxx/jcxx!countKffs.action",
 		data:{"address":[address]},
        type : "post",
        dataType : "JSON",
        async : true,
        traditional: true,
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				kffsLeng.push(data[i].name);
				kffs.push(data[i])
				value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: '小水电开发方式统计',
		     		        x : 'center'
		     		    },
		     		 tooltip: {
		     		        trigger: 'item',
		     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		     		    },
		     		    legend: {
		     		        orient: 'horizontal',
		     		        bottom: 5,
		     		        data:kffsLeng
		     		    },
		     		     color:colors,
		     		    series: [
		     		    	 {
			     		            name:'总数',
			     		            type:'pie',
			     		            center:['50%','46.5%'],
			     		            selectedMode: 'single',
			     		            radius: [0, '32.5%'],
			     		            label: {
			     		            	show:true,
			     		                normal: {
			     		                    position: 'center',
			     		                    formatter: '{b}\n {c}',
			     		                    color:'white'
			     		                }
			     		            },
			     		            labelLine: {
			     		                normal: {
			     		                    show: false
			     		                }
			     		            },
			     		           data:datas
			     		           
			     		        },
		     		        {
		     		            name:'',
		     		            type:'pie',
		     		            radius: ['40%', '55%'],
		     		            label: {
		     		                normal: {
		     		                	show: true,
		     		                    formatter: '{b}: {c}({d}%)'
		     		                }
		     		            },
		     		            data:kffs
		     		        }
		     		    ]
		     };
			  myChart.off("click");//防止累计触发
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '小水电开发方式统计( '+name+' )',
					  area:areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+30,
				});
			});
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
		     
		}
	});
}

function initKffs1(sheng,shi,xian){
	 // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('kffs1'));
    var kffsLeng = [];
    var kffs = [];
    var datas = [];
    var value = 0;
     $.ajax({
  		url:basePath+"jcxx/jcxx!countKffs1.action",
 		data:{"sheng":[sheng],"shi":[shi],"xian":[xian]},
        type : "post",
        dataType : "JSON",
        async : true,
        traditional: true,
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				kffsLeng.push(data[i].name);
				kffs.push(data[i])
				value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: '小水电开发方式统计',
		     		        x : 'center'
		     		    },
		     		 tooltip: {
		     		        trigger: 'item',
		     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		     		    },
		     		    legend: {
		     		        orient: 'horizontal',
		     		        bottom: 5,
		     		        data:kffsLeng
		     		    },
		     		     color:colors,
		     		    series: [
		     		    	 {
			     		            name:'总数',
			     		            type:'pie',
			     		            center:['50%','46.5%'],
			     		            selectedMode: 'single',
			     		            radius: [0, '32.5%'],
			     		            label: {
			     		            	show:true,
			     		                normal: {
			     		                    position: 'center',
			     		                    formatter: '{b}\n {c}',
			     		                    color:'white'
			     		                }
			     		            },
			     		            labelLine: {
			     		                normal: {
			     		                    show: false
			     		                }
			     		            },
			     		           data:datas
			     		           
			     		        },
		     		        {
		     		            name:'',
		     		            type:'pie',
		     		            radius: ['40%', '55%'],
		     		            label: {
		     		                normal: {
		     		                	show: true,
		     		                    formatter: '{b}: {c}({d}%)'
		     		                }
		     		            },
		     		            data:kffs
		     		        }
		     		    ]
		     };
			  myChart.off("click");//防止累计触发
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title:  '小水电开发方式统计( '+name+' )',
					  area: areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+30,
				});
			});
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
		     
		}
	});
}
/////////////////////////////////////////////////建设状态统计////////////////////////////////////////////////////////////////
        // 基于准备好的dom，初始化echarts实例

function initJszt0(address){
	 // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.getElementById('jszt1'));
			  // 指定图表的配置项和数据
		     var option = {
		     		  title : {
		     		        text: '小水电建设状态统计',
		     		        x : 'center'
		     		    },
		     		 tooltip: {
		     		        trigger: 'item',
		     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		     		    },
		     		    legend: {
		     		        orient: 'horizontal',
		     		        bottom: 5,
		     		        data:['运行','在建','拟建','拆除','废弃','未知']
		     		    },
		     		   color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D',' #989898',],
		     		    series: [
		     		    	 {
			     		            name:'总数',
			     		            type:'pie',
			     		            center:['50%','46.5%'],
			     		            selectedMode: 'single',
			     		            radius: [0, '32.5%'],
			     		            label: {
			     		            	show:true,
			     		                normal: {
			     		                    position: 'center',
			     		                    formatter: '{b}\n {c}',
			     		                    color:'white'
			     		                }
			     		            },
			     		            labelLine: {
			     		                normal: {
			     		                    show: false
			     		                }
			     		            },
			     		           data:[{"value":0, "name":'总数(座)', selected:true}]
			     		           
			     		        },
		     		        {
		     		            name:'',
		     		            type:'pie',
		     		            radius: ['40%', '55%'],
		     		            label: {
		     		                normal: {
		     		                	show: true,
		     		                    formatter: '{b}: {c}({d}%)'
		     		                }
		     		            },
		     		            data:[
		    						{name:'运行',value:0}
		    						,{name:'在建',value:0}
		    						,{name:'拟建',value:0}
		    						,{name:'拆除', value:0}
		    						,{name:'废弃',value:0}
		    						,{name:'未知',value:0}
		    					],
		     		        }
		     		    ]
		     };
		     myChart.off("click");//防止累计触发
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title:  '小水电建设状态统计( '+name+' )',
					  area: areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+31,
				});
			});
		     // 使用刚指定的配置项和数据显示图表。
		     myChart.setOption(option);
}

function initJszt(address){
	 var myChart = echarts.init(document.getElementById('jszt1'));
     var leng = [];
     var jszt = [];
     var datas = [];
     var value = 0;
     $.ajax({
 		url:basePath+"jcxx/jcxx!countJszt.action",
 		data:{"address":[address]},
        type : "post",
        dataType : "JSON",
        async : true,
        traditional: true,
 		success:function(response){
 			var data = response.rows
 			for (var i = 0; i < data.length; i++) {
					leng.push(data[i].name);
					jszt.push(data[i])
					value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
				     // 指定图表的配置项和数据
				     var option = {
				     		 title : {
				  		        text: '小水电建设状态统计',
				  		        x:'center'
				  		    },
				     		 tooltip: {
				     		        trigger: 'item',
				     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
				     		    },
				     		    legend: {
				     		        orient: 'horizontal',
				     		        bottom: 5,
				     		        data:leng
				     		    },
				     		   color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D',' #989898',],
				     		    series: [
				     		        {
				     		            name:'总数',
				     		            type:'pie',
				     		            center:['50%','46.5%'],
				     		            selectedMode: 'single',
				     		            radius: [0, '32.5%'],
				     		           color:'#007bbb',
				     		            label: {
				     		            	show:true,
				     		                normal: {
				     		                    position: 'center',
				     		                    formatter: '{b}\n {c}',
				     		                    color:'white'
				     		                }
				     		            },
				     		            labelLine: {
				     		                normal: {
				     		                    show: false
				     		                }
				     		            },
				     		           data:datas
				     		        },
				     		        {
				     		            name:'',
				     		            type:'pie',
				     		            radius: ['40%', '55%'],
				     		            label: {
				     		                normal: {
				     		                	show: true,
				     		                    formatter: '{b}: {c}({d}%)'
				     		                }
				     		            },
				     		            data:jszt
				     		        }
				     		    ]
				     };
			  myChart.off("click");//防止累计触发
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title:  '小水电建设状态统计( '+name+' )',
					  area: areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+31,
					});
				});
				     // 使用刚指定的配置项和数据显示图表。
				     myChart.setOption(option);
 		}
 	});
}

function initJszt1(sheng,shi,xian){
	 var myChart = echarts.init(document.getElementById('jszt1'));
     var leng = [];
     var jszt = [];
     var datas = [];
     var value = 0;
     $.ajax({
 		url:basePath+"jcxx/jcxx!countJszt1.action",
 		data:{"sheng":[sheng],"shi":[shi],"xian":[xian]},
        type : "post",
        dataType : "JSON",
        async : true,
        traditional: true,
 		success:function(response){
 			var data = response.rows
 			for (var i = 0; i < data.length; i++) {
					leng.push(data[i].name);
					jszt.push(data[i])
					value+=data[i].value;
			}
			datas.push({"value":value, "name":'总数(座)', selected:true})
				     // 指定图表的配置项和数据
				     var option = {
				     		 title : {
				  		        text: '小水电建设状态统计',
				  		        x:'center'
				  		    },
				     		 tooltip: {
				     		        trigger: 'item',
				     		        formatter: "{a} <br/>{b}: {c} ({d}%)"
				     		    },
				     		    legend: {
				     		        orient: 'horizontal',
				     		        bottom: 5,
				     		        data:leng
				     		    },
				     		   color:['#007bbb','#008e57','#933d92','#c48f00','#A0522D',' #989898',],
				     		    series: [
				     		        {
				     		            name:'总数',
				     		            type:'pie',
				     		            center:['50%','46.5%'],
				     		            selectedMode: 'single',
				     		            radius: [0, '32.5%'],
				     		           color:'#007bbb',
				     		            label: {
				     		            	show:true,
				     		                normal: {
				     		                    position: 'center',
				     		                    formatter: '{b}\n {c}',
				     		                    color:'white'
				     		                }
				     		            },
				     		            labelLine: {
				     		                normal: {
				     		                    show: false
				     		                }
				     		            },
				     		           data:datas
				     		        },
				     		        {
				     		            name:'',
				     		            type:'pie',
				     		            radius: ['40%', '55%'],
				     		            label: {
				     		                normal: {
				     		                	show: true,
				     		                    formatter: '{b}: {c}({d}%)'
				     		                }
				     		            },
				     		            data:jszt
				     		        }
				     		    ]
				     };
			  myChart.off("click");//防止累计触发
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title:  '小水电建设状态统计( '+name+' )',
					  area:areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/jichuxinxi/modalFrame_jc.jsp?name='+name+"&sheng="+sheng+"&shi="+shi+"&xian="+xian+"&tab="+31,
					});
				});
				     // 使用刚指定的配置项和数据显示图表。
				     myChart.setOption(option);
 		}
 	});
}


function initDzsl(bj){
	//debugger;
    var myChart = echarts.init(document.getElementById('fzlc1'));
    if(bj == null){
		bj = 0;
	}
    var sum3 = [];
    var sum4 = [];
    var yer4 = [];
    $.ajax({
		url:basePath+"jcxx/jcxx!countDz.action",
		data:{"bj":bj,"xzlyqh":xzqhdm},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				sum3.push(data[i].sun);
				yer4.push(data[i].yer);
				sum4.push(data[i].sum);
			} 
			var colors = ['#007bbb','#008e57' ];
			option = {
			    color: colors,
			    title : {
			    text:'设计年发电量随年份统计',
     		        x:'center'
     		    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    grid: {
			    	 right:"100", //组件离容器右侧的距离,百分比字符串或整型数字 bottom:"auto", //组件离容器下侧的距离,百分比字符串或整型数字 width:"auto", //图例宽度 height:"auto", //图例高度
			    	 left:"100",
			    	 top:"70",  
			    	 bottom:"70",
			    },
			    toolbox: {
			        feature: {
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    legend: {
			    	 bottom: 5,
			        data:['对应年份投产水电站数量','累积投运水电站数量']
			    },
			    xAxis: [
			        {
			            type: 'category',
                        axisLabel: {
                            interval:0,
                            rotate:40
                         },
			            axisTick: {
			                alignWithLabel: true
			            },
			            data: yer4
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '累积投运水电站数量(座)',
			            min: 0,
			            position: 'right',
			           // splitLine:{show: false},//去除网格线
			            axisLine: {
			                lineStyle: {
			                	 color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        { },
			        {
			            type: 'value',
			            name: '对应年份投产水电站数量(座)',
			            //splitLine:{show: false},//去除网格线
			            min: 0,
			            position: 'left',
			            axisLine: {
			                lineStyle: {
			                	 color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series: [
			        {
			            name:'累积投运水电站数量',
			            type:'bar',
			            data:sum4
			        },
			        {
			            name:'对应年份投产水电站数量',
			            type:'line',
			            yAxisIndex: 2,
			            data:sum3
			        }
			    ]
			};
			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '全国小水电发展历程详细信息',
					  area: areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame_fzlc.jsp?name='+name+"&bj="+1,
				});
			});
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		    window.onresize = myChart.resize;

		}
	});
    //点击切换图标文字和显示隐藏table
    $('#table-icon_qg_dz_fzlc1').click(function(){
   	 if( $('#mainonedes_qg_kffs_date1').hasClass('desplay')){
   		 $('#table-icon_qg_dz_fzlc1').text('切换图表');
   		 $('#mainonedes_qg_kffs_date1').removeClass('desplay');
   		 if($('#jc_export5').hasClass('desplay')){
			 $('#jc_export5').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
   		initFzlcTable("qg_kffs_date");
   	 } else{
   		  $('#mainonedes_qg_kffs_date1').addClass('desplay');
   		  $('#table-icon_qg_dz_fzlc1').text('切换表格');
   		 $('#jc_export5').addClass('desplay');
   	 }
    })
}
  
function initFzlcTable(bj){
	$.ajax({
		url:basePath+"homePage/homePage!countTbzh.action",
		data:{"bj":bj},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows;
			var tr = "";
			var len = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			var zj_zj4 =0; var zj_dz4 =0; var zj_zj5 =0; var zj_dz5 =0; 
			var zj_zj6=0; var zj_dz6 =0;
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				zj_zj +=row.rlz;zj_dz +=row.dzz;
				zj_zj1+=row.rl1;zj_dz1+=row.dz1;zj_zj2+=row.rl2;zj_dz2+=row.dz2
				zj_zj3+=row.rl3;zj_dz3+=row.dz3;zj_zj4+=row.rl4;zj_dz4+=row.dz4
				zj_zj5+=row.rl5;zj_dz5+=row.dz5;zj_zj6+=row.rl6;zj_dz6+=row.dz6;
				tr +="<tr>"
					+"<td>"+(len++)+"</td>"
					+"<td>"+row.name+"</td>"
					+"<td>"+(row.rlz/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dzz+"</td>"
					+"<td>"+(row.rl1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz1+"</td>"
					+"<td>"+(row.rl2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz2+"</td>"
					+"<td>"+(row.rl3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz3+"</td>"
					+"<td>"+(row.rl4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz4+"</td>"
					/* +"<td>"+(row.rl5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz5+"</td>" */
					if(bj == "qg_jszt"){
						tr +=  "<td>"+(row.rl5/10000).toFixed(2)+"&nbsp;/&nbsp"+row.dz5+"</td>" 
						tr +=  "<td>"+(row.rl6/10000).toFixed(2)+"&nbsp;/&nbsp"+row.dz6+"</td>" 
					}
					+"</tr>"
			}
			if(bj == "qg_kffs_date"){
				$("#table_qg_kffs_date1").html("");
				$("#table_qg_kffs_date1").append(tr);
			}
			debugger;
			if(bj == "qg_kffs_date_zj"){
				$("#table_qg_kffs_date2").html("");
				$("#table_qg_kffs_date2").append(tr);
			}
			if(bj != "qg_kffs_date" && bj != "qg_kffs_date_zj"){
				var zj_tr = "<tr>"
					+"<td colspan = '2'style='display:inline-block;width: 120px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
					+"<td>"+(zj_zj4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
					+"</tr>";
			}else{
				var zj_tr = "<tr>"
					+"<td colspan = '1'style='width: 165px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2)+"/"+zj_dz+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2)+"/"+zj_dz1+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2)+"/"+zj_dz2+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2)+"/"+zj_dz3+"</td>"
					+"<td>"+(zj_zj4/10000).toFixed(2)+"/"+zj_dz4+"</td>"
					+"</tr>";
			}
			if(bj == "qg_kffs_date"){
				$("#zj_table_qg_kffs_date1").html("");
				$("#zj_table_qg_kffs_date1").append(zj_tr);
			}
			if(bj == "qg_kffs_date_zj"){
				$("#zj_table_qg_kffs_date2").html("");
				$("#zj_table_qg_kffs_date2").append(zj_tr);
			}
		}
	})
}

$('#jc_qiehuan_fzlc').click(function(){
  	 if( $('#mainonedes_qg_kffs_date2').hasClass('desplay')){
  		 $('#jc_qiehuan_fzlc').text('切换图表');
  		 $('#mainonedes_qg_kffs_date2').removeClass('desplay');
  		 if($('#jc_export6').hasClass('desplay')){
			 $('#jc_export6').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
  		initFzlcTable("qg_kffs_date_zj");
  
  	 } else{
  		  $('#mainonedes_qg_kffs_date2').addClass('desplay');
  		  $('#jc_qiehuan_fzlc').text('切换表格');
  		 $('#jc_export6').addClass('desplay');
  	 }
   })


function initZjrl(bj){
	 // 基于准备好的dom，初始化echarts实例
   var myChart = echarts.init(document.getElementById('fzlc2'));
   if(bj == null){
		bj = 0;
	}
   var sum = [];
   var sum1 = [];
   var yer = [];
   $.ajax({
		url:basePath+"homePage/homePage!countZjrl.action",
		data:{"bj":bj},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows
			for (var i = 0; i < data.length; i++) {
				sum.push(data[i].sun);
				yer.push(data[i].yer);
				sum1.push(data[i].sum);
			}
			var colors = ['#007bbb','#008e57'];
			option = {
			    color: colors,
			    title : {
			    	text:'全国小水电发展历程',
    		        x:'center'
    		    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'cross'
			        }
			    },
			    grid: {
			    	 right:"100", //组件离容器右侧的距离,百分比字符串或整型数字 bottom:"auto", //组件离容器下侧的距离,百分比字符串或整型数字 width:"auto", //图例宽度 height:"auto", //图例高度
			    	 left:"100",
			    	 top:"70",  
			    	 bottom:"70",
			    },
			    toolbox: {
			        feature: {
			            restore: {show: true},
			            saveAsImage: {show: true}
			        }
			    },
			    legend: {
			    	 bottom: 5,
			        data:['对应年份投产水电站装机规模','累积投运水电站装机规模']
			    },
			    xAxis: [
			        {
			            type: 'category',
			            axisLabel: {
                           interval:0,
                           rotate:40
                        },
			            axisTick: {
			                alignWithLabel: true
			            },
			            data:yer
			        }
			    ],
			    yAxis: [
			        {
			            type: 'value',
			            name: '累积投运水电站装机规模(万千瓦)',
			            min: 0,
			            position: 'right',
			            axisLine: {
			                lineStyle: {
			                    color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        },
			        { },
			        {
			            type: 'value',
			            name: '对应年份投产水电站装机规模(万千瓦)',
			            min: 0,
			            position: 'left',
			            axisLine: {
			                lineStyle: {
			                    color: colors[1]
			                }
			            },
			            axisLabel: {
			                formatter: '{value}'
			            }
			        }
			    ],
			    series: [
			        {
			            name:'累积投运水电站装机规模',
			            type:'bar',
			            data:sum1
			        },
			        {
			            name:'对应年份投产水电站装机规模',
			            type:'line',
			            yAxisIndex: 2,
			            data:sum
			        }
			    ]
			};

			myChart.on('click', function (params) {
				var name = params.name;
				layer.open({
					  type: 2, 
					  title: '全国小水电发展历程详细信息',
					  area:areaWidth,
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame_fzlc.jsp?name='+name+"&bj="+1,
				});
			});
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		    window.onresize = myChart.resize;
		}
	});
}
//开发方式，切换表格
function initTable(bj){
	debugger;
	var zjbj=0;
	var xzqhdm=getYixuanId();
	if(xzqhdm.length==1869){//表示全选
		xzqhdm=null;
		zjbj=1;//总计标记，为0，不查，为1，查总计
	}else{
		bj="qg_kffs1"
	}
	if(xzqhdm==""){//表示没选
		$("#table_qg_kffs4").html("");
		$("#zj_table_qg_kffs14").html("");
	return false;
	}
	$.ajax({
		url:basePath+"jcxx/jcxx!countTbzh.action",
		data:{"bj":bj,"xzlyqh":xzqhdm},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.rows;
			var tr = "";
			var len = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			var zj_zj4 =0; var zj_dz4 =0; var zj_zj5 =0; var zj_dz5 =0; 
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				zj_zj +=row.rlz;zj_dz +=row.dzz;
				zj_zj1+=row.rl1;zj_dz1+=row.dz1;zj_zj2+=row.rl2;zj_dz2+=row.dz2
				zj_zj3+=row.rl3;zj_dz3+=row.dz3;zj_zj4+=row.rl4;zj_dz4+=row.dz4
				zj_zj5+=row.rl5;zj_dz5+=row.dz5;
				tr +="<tr>"
					+"<td>"+(len++)+"</td>"
					+"<td>"+row.name+"</td>"
					+"<td>"+(row.rlz/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dzz+"</td>"
					+"<td>"+(row.rl1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz1+"</td>"
					+"<td>"+(row.rl2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz2+"</td>"
					+"<td>"+(row.rl3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz3+"</td>"
					+"<td>"+(row.rl4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz4+"</td>"
					+"</tr>"
			}
			if(bj == "qg_kffs"){
				$("#table_qg_kffs4").html("");
				$("#table_qg_kffs4").append(tr);
			}
			if(bj == "qg_kffs1"){
				$("#table_qg_kffs4").html("");
				$("#table_qg_kffs4").append(tr);
			}
		/*	if(bj == "qg_kffs_date"){//qg_kffs_date
				$("#table_qg_kffs_date1").html("");
				$("#table_qg_kffs_date1").append(tr);
			}
			if(bj == "qg_kffs_date_zj"){
				$("#table_qg_kffs_date2").html("");
				$("#table_qg_kffs_date2").append(tr);
			} */
			if(bj != "qg_kffs_date" && bj != "qg_kffs_date_zj"){
				var zj_tr = "<tr>"
					 +"<td></td>"
					+"<td colspan = '2'style='display:inline-block;width: 80px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
					+"<td>"+(zj_zj4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
			/*		if(bj == "qg_kffs"){
						zj_tr +="<td>"+(zj_zj5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
					}*/
					+"</tr>";
			}else{
				var zj_tr = "<tr>"
					 +"<td></td>" 
					+"<td colspan = '1'style='width: 130px;'>总计</td>"
					+"<td>"+(zj_zj/10000).toFixed(2)+"&nbsp;/&nbsp;"+zj_dz+"</td>"
					+"<td>"+(zj_zj1/10000).toFixed(2)+"&nbsp;/&nbsp;"+zj_dz1+"</td>"
					+"<td>"+(zj_zj2/10000).toFixed(2)+"&nbsp;/&nbsp;"+zj_dz2+"</td>"
					+"<td>"+(zj_zj3/10000).toFixed(2)+"&nbsp;/&nbsp;"+zj_dz3+"</td>"
					+"<td>"+(zj_zj4/10000).toFixed(2)+"&nbsp;/&nbsp;"+zj_dz4+"</td>"
					+"</tr>";
			}
			if(bj == "qg_kffs"){
				$("#zj_table_qg_kffs14").html("");
				$("#zj_table_qg_kffs14").append(zj_tr);
			}
			if(bj == "qg_kffs1"){
				var zj_tr = "<tr>"
					 +"<td></td>" 
					+"<td colspan = '1'style=''>总计</td>"
					+"<td>"+(data[0].rlz/10000).toFixed(2)+"&nbsp;/&nbsp;"+data[0].dzz+"</td>"
					+"<td>"+(data[0].rl1/10000).toFixed(2)+"&nbsp;/&nbsp;"+data[0].dz1+"</td>"
					+"<td>"+(data[0].rl2/10000).toFixed(2)+"&nbsp;/&nbsp;"+data[0].dz2+"</td>"
					+"<td>"+(data[0].rl3/10000).toFixed(2)+"&nbsp;/&nbsp;"+data[0].dz3+"</td>"
					+"<td>"+(data[0].rl4/10000).toFixed(2)+"&nbsp;/&nbsp;"+data[0].dz4+"</td>"
					+"</tr>";
				$("#zj_table_qg_kffs14").html("");
				$("#zj_table_qg_kffs14").append(zj_tr);
			}
			if(bj == "qg_kffs_date"){
				$("#zj_table_qg_kffs_date1").html("");
				$("#zj_table_qg_kffs_date1").append(zj_tr);
			}
			if(bj == "qg_kffs_date_zj"){
				$("#zj_table_qg_kffs_date2").html("");
				$("#zj_table_qg_kffs_date2").append(zj_tr);
			}
		}
	})
}

//1按装机规模分类统计
$('#table-icon_qg_dz_fzlc11').click(function(){
  	 if( $('#mainonedes_qg_kffs_date11').hasClass('desplay')){
  		 $('#table-icon_qg_dz_fzlc11').text('切换图表');
  		 $('#mainonedes_qg_kffs_date11').removeClass('desplay');
  		 if($('#jc_export1').hasClass('desplay')){
			 $('#jc_export1').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
  		initTable1("1");
  	 }
  	  else{
  		  $('#mainonedes_qg_kffs_date11').addClass('desplay');
  		  $('#table-icon_qg_dz_fzlc11').text('切换表格');
 		 $('#jc_export1').addClass('desplay');
  	 }
   })
   
   //2
   $('#table-icon_qg_dz_fzlc12').click(function(){
  	 if( $('#mainonedes_qg_kffs_date12').hasClass('desplay')){
  		 $('#table-icon_qg_dz_fzlc12').text('切换图表');
  		 $('#mainonedes_qg_kffs_date12').removeClass('desplay');
 		 if($('#jc_export2').hasClass('desplay')){
			 $('#jc_export2').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
  		initTable1("2");
  
  	 }
  	  else{
  		  $('#mainonedes_qg_kffs_date12').addClass('desplay');
  		  $('#table-icon_qg_dz_fzlc12').text('切换表格');
  		 $('#jc_export2').addClass('desplay');
  	 }
   })
   //3建设状态
      $('#table-icon_qg_dz_fzlc13').click(function(){
   	 if( $('#mainonedes_qg_kffs_date13').hasClass('desplay')){
   		 $('#table-icon_qg_dz_fzlc13').text('切换图表');
   		 $('#mainonedes_qg_kffs_date13').removeClass('desplay');
		 if($('#jc_export3').hasClass('desplay')){
			 $('#jc_export3').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
   		 initTable3("3");
   
   	 } else{
   		  $('#mainonedes_qg_kffs_date13').addClass('desplay');
   		  $('#table-icon_qg_dz_fzlc13').text('切换表格');
   		 $('#jc_export3').addClass('desplay');
   	 }
    })
     //4开发方式
      $('#table-icon_qg_dz_fzlc14').click(function(){
   	 if( $('#mainonedes_qg_kffs_date14').hasClass('desplay')){
   		 $('#table-icon_qg_dz_fzlc14').text('切换图表');
   		 $('#mainonedes_qg_kffs_date14').removeClass('desplay');
   		 if($('#jc_export4').hasClass('desplay')){
			 $('#jc_export4').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
   		 initTable("qg_kffs");
   
   	 } else{
   		  $('#mainonedes_qg_kffs_date14').addClass('desplay');
   		  $('#table-icon_qg_dz_fzlc14').text('切换表格');
   		 $('#jc_export4').addClass('desplay');
   	 }
    })
    
    
$('#table-icon5').click(function(){
  	 if( $('#mainonedes15').hasClass('desplay')){
  		 $('#table-icon5').text('切换图表');
  		 $('#mainonedes15').removeClass('desplay');
  		 if($('#jc_export7').hasClass('desplay')){
			 $('#jc_export7').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
  		initTable5("5");
  	 }
  	  else{
  		  $('#mainonedes15').addClass('desplay');
  		  $('#table-icon5').text('切换表格');
  		 $('#jc_export7').addClass('desplay');
  	 }
   })
    
   $('#table-icon6').click(function(){
  	 if( $('#mainonedes16').hasClass('desplay')){
  		 $('#table-icon6').text('切换图表');
  		 $('#mainonedes16').removeClass('desplay');
  		 if($('#jc_export8').hasClass('desplay')){
			 $('#jc_export8').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
  		initTable5("6");
  	 }
  	  else{
  		  $('#mainonedes16').addClass('desplay');
  		  $('#table-icon6').text('切换表格');
  		 $('#jc_export8').addClass('desplay');
  	 }
   })
   
      $('#table-icon7').click(function(){
  	 if( $('#mainonedes17').hasClass('desplay')){
  		 $('#table-icon7').text('切换图表');
  		 $('#mainonedes17').removeClass('desplay');
  		 if($('#jc_export9').hasClass('desplay')){
			 $('#jc_export9').removeClass('desplay');
		 }else{
			 $('#table-iconone').addClass('desplay');
		 }
  		initTable1("7");
  	 }
  	  else{
  		  $('#mainonedes17').addClass('desplay');
  		  $('#table-icon7').text('切换表格');
  		 $('#jc_export9').addClass('desplay');
  	 }
   })
   function getZj(bj){//Bj代表第几个表格切换，得到总计，只有在全选的时候调用此方法
	var zj_tr ;
	if(bj=='1'){
		 zj_tr = "<tr>"
			+"<td ></td>"
			+"<td  >总计</td>"
			+"<td>"+zjlx_zj_zrl +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zjlx_zj_zdz+"</td>"
			+"<td >"+zjlx_zj6+"&nbsp;&nbsp;/&nbsp;&nbsp;"+ zjlx_zj3+"</td>"
			+"<td >"+zjlx_zj5 +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zjlx_zj2+"</td>"
			+"<td>"+zjlx_zj4+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zjlx_zj1+"</td>"
			+"</tr>";
	}
	if(bj=='5'){
		debugger;
		 zj_tr = "<tr>"
			+"<td ></td>"
			+"<td  >总计</td>"
			+"<td>"+(zjlyzrl/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zjlyzdz+"</td>"
			+"<td >"+ zjly2[0].value+"&nbsp;&nbsp;/&nbsp;&nbsp;"+ zjly1[0].value+"</td>"
			+"<td >"+ zjly2[2].value +"&nbsp;&nbsp;/&nbsp;&nbsp;"+  zjly1[2].value +"</td>"
			+"<td>"+ zjly2[3].value+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zjly1[3].value+"</td>"
			+"<td>"+ zjly2[1].value+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zjly1[1].value +"</td>"
			+"</tr>";
	}
	if(bj=='7'){
		 zj_tr = "<tr>"
			+"<td ></td>"
			+"<td  >总计</td>"
			+"<td>"+hz_zrl +"&nbsp;&nbsp;/&nbsp;&nbsp;"+hz_zdz+"</td>"
			+"<td >"+hz_rl1+"&nbsp;&nbsp;/&nbsp;&nbsp;"+ hz_dz1+"</td>"
			+"<td >"+hz_rl2 +"&nbsp;&nbsp;/&nbsp;&nbsp;"+hz_dz2+"</td>"
			+"<td>"+hz_rl3+"&nbsp;&nbsp;/&nbsp;&nbsp;"+hz_dz3+"</td>"
			+"</tr>";
	}
	if(bj=='6'){//并网情况
		 zj_tr = "<tr>"
			+"<td ></td>"
			+"<td  >总计</td>"
		+"<td>"+ bwqk_zrl +"&nbsp;&nbsp;/&nbsp;&nbsp;"+bwqk_zdz+"</td>"
		+"<td>"+bwqk_rl1 +"&nbsp;&nbsp;/&nbsp;&nbsp;"+bwqk_dz1+"</td>"
		+"<td>"+bwqk_rl4+"&nbsp;&nbsp;/&nbsp;&nbsp;"+bwqk_dz4 +"</td>"
		+"<td>"+bwqk_rl3+"&nbsp;&nbsp;/&nbsp;&nbsp;"+bwqk_dz3+"</td>"
		+"<td>"+bwqk_rl5+"&nbsp;&nbsp;/&nbsp;&nbsp;"+bwqk_dz5+"</td>"
		+"<td>"+bwqk_rl2+"&nbsp;&nbsp;/&nbsp;&nbsp;"+bwqk_dz2+"</td>"
		 zj_tr 		+="</tr>";
	}
	return zj_tr;
}
   function initTable1(bj){
	   debugger;
	var zjbj=0;
	var xzqhdm=getYixuanId();
	if(xzqhdm.length==1869){//表示全选
		xzqhdm=null;
		zjbj=1;//总计标记，为0，不查，为1，查总计
	}
	if(xzqhdm==""){//表示没选
		$("#table_qg_jszt1").html("");
		$("#zj_table_qg_jszt1").html("");
	return false;
	}
	$.ajax({
		url:basePath+"jcxx/jcxx!countTbzh.action",
		data:{"bj":bj,"xzlyqh":xzqhdm},
		type: "POST",
		dataType:"json",
		success:function(response){
			//debugger;
			var data = response.rows;
			var tr = "";
			var len = 1;
			var flen = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			for (var i = 0; i < data.length; i++) {
				if(data[i].name==null){
					data[i].name="东北诸河"
				}
				var row = data[i];
				tr +="<tr>"
					if(row.name == '安徽省' || row.name == '贵州省'|| row.name == '上海市'|| row.name == '浙江省'|| row.name == '江西省'
						|| row.name == '湖北省'|| row.name == '湖南省'|| row.name == '重庆市'|| row.name == '四川省'|| row.name == '云南省'
							|| row.name == '江苏省'|| row.name == '天津市'|| row.name == '河北省'|| row.name == '陕西省'|| row.name == '山西省'
								|| row.name == '内蒙古自治区'	|| row.name == '福建省'	|| row.name == '山东省'	|| row.name == '河南省'
									|| row.name == '辽宁省'){
						if(i == 0){
							tr +="<td>"+(len)+"</td>"
							flen = len;
							zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
							zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
						}else{
							tr +="<td>"+(len+=1)+"</td>"
							flen = len
							zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
							zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
						}
					}else{
						tr +="<td>"+((flen += 0.1).toFixed(1))+"</td>"
					}
				tr +="<td>"+row.name+"</td>"
				tr +="<td>"+(row.zrl/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz+"</td>"
				tr +="<td>"+(row.zrl1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz1+"</td>"
				tr +="<td>"+(row.zrl2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz2+"</td>"
				tr +="<td>"+(row.zrl3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz3+"</td>"
				tr +="</tr>"
				if(bj == "2"){
					zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
					zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
				}
			}
			
			var zj_tr = "<tr>"
				+"<td ></td>"
				+"<td  >总计</td>"
				+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
				+"<td >"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
				+"<td >"+(zj_zj2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
				+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
				+"</tr>";
		if(bj=='1'){
			if( zjbj==1){
				zj_tr=	getZj(bj);
			}
			$("#table_qg_jszt1").html("");
			$("#table_qg_jszt1").append(tr);
			$("#zj_table_qg_jszt1").html("");
			$("#zj_table_qg_jszt1").append(zj_tr);
		}
		if(bj=='2'){
			$("#table_qg_jszt2").html("");
			$("#table_qg_jszt2").append(tr);
			$("#zj_table_qg_jszt2").html("");
			$("#zj_table_qg_jszt2").append(zj_tr);
		}
		
		if(bj=='7'){
			if( zjbj==1){
				zj_tr=	getZj(bj);
			}
			$("#table_qg_jszt7").html("");
			$("#table_qg_jszt7").append(tr);
			$("#zj_table_qg_jszt7").html("");
			$("#zj_table_qg_jszt7").append(zj_tr);
		}
		}
	})
}


function initTable3(bj,xzqhdm){
	var zjbj=0;
	var xzqhdm=getYixuanId();
	if(xzqhdm.length==1869){//表示全选
		xzqhdm=null;
		zjbj=1;//总计标记，为0，不查，为1，查总计
	}else{
		bj="31"
	}
	if(xzqhdm==""){//表示没选
		$("#table_qg_jszt3").html("");
		$("#zj_table_qg_jszt3").html("");
	return false;
	}
	$.ajax({
		url:basePath+"jcxx/jcxx!countTbzh.action",
		data:{"bj":bj,"xzlyqh":xzqhdm},
		type: "POST",
		dataType:"json",
		success:function(response){
			//debugger;
			var data = response.rows;
			var tr = "";
			var tr1="";
			var len = 1;
			var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
			var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
			var zj_zj4 =0; var zj_dz4 =0; var zj_zj5 =0; var zj_dz5 =0; var zj_zj6 =0; var zj_dz6 =0; 
			for (var i = 0; i < data.length; i++) {
				var row = data[i];
				zj_zj +=row.rlz;zj_dz +=row.dzz;
				zj_zj1+=row.rl1;zj_dz1+=row.dz1;zj_zj2+=row.rl2;zj_dz2+=row.dz2
				zj_zj3+=row.rl3;zj_dz3+=row.dz3;zj_zj4+=row.rl4;zj_dz4+=row.dz4
				zj_zj5+=row.rl5;zj_dz5+=row.dz5;zj_zj6+=row.rl6;zj_dz6+=row.dz6;
				tr +="<tr>"
					+"<td>"+(len++)+"</td>"
					+"<td>"+row.name+"</td>"
					+"<td>"+row.rlz.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dzz+"</td>"
					+"<td>"+row.rl1.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz1+"</td>"
					+"<td>"+row.rl2.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz2+"</td>"
					+"<td>"+row.rl3.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz3+"</td>"
					+"<td>"+row.rl4.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz4+"</td>"
					if(bj == "3"){
						tr += "<td>"+row.rl5.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz5+"</td>" 
						+"<td>"+row.rl6.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz6+"</td>" 
					}
				if(bj == "31"){
			
					tr1 +="<tr>"
						+"<td>"+(len++)+"</td>"
						+"<td>"+row.name+"</td>"
						+"<td>"+(row.rlz/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dzz+"</td>"
						+"<td>"+(row.rl1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz1+"</td>"
						+"<td>"+(row.rl2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz2+"</td>"
						+"<td>"+(row.rl3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz3+"</td>"
						+"<td>"+(row.rl4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz4+"</td>"
						+ "<td>"+(row.rl5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz5+"</td>" 
						+"<td>"+(row.rl6/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.dz6+"</td>" 
				}
					+"</tr>"
			}
			if(bj == "3"){
				$("#table_qg_jszt3").html("");
				$("#table_qg_jszt3").append(tr);
			}
			if(bj == "31"){
				$("#table_qg_jszt3").html("");
				$("#table_qg_jszt3").append(tr1);
			}
			if(bj == "4"){
				$("#table_qg_kffs").html("");
				$("#table_qg_kffs").append(tr);
			}
			if(bj == "qg_kffs_date"){
				$("#table_qg_kffs_date").html("");
				$("#table_qg_kffs_date").append(tr);
			}
			if(bj == "qg_kffs_date_zj"){
				$("#table_qg_kffs_date_zj").html("");
				$("#table_qg_kffs_date_zj").append(tr);
			}
			if(bj != "qg_kffs_date" && bj != "qg_kffs_date_zj"){
				var zj_tr = "<tr>"
					 +"<td></td>" 
					+"<td colspan = '2'style='display:inline-block;width: 80px;'>总计</td>"
					+"<td>"+zj_zj.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
					+"<td>"+zj_zj1.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
					+"<td>"+zj_zj2.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
					+"<td>"+zj_zj3.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
					+"<td>"+zj_zj4.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
					+"<td>"+zj_zj5.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
					if(bj == "3"){
						zj_tr +="<td>"+zj_zj6.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz6+"</td>"
					}
					+"</tr>";
			}else{
				var zj_tr = "<tr>"
					/* +"<td>"+(len++)+"</td>" */
					+"<td colspan = '1'style='width: 165px;'>总计</td>"
					+"<td>"+zj_zj.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
					+"<td>"+zj_zj1.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
					+"<td>"+zj_zj2.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
					+"<td>"+zj_zj3.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
					+"<td>"+zj_zj4.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
					+"<td>"+zj_zj5.toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
				/*	if(bj == "qg_jszt"){
						zj_tr +="<td>"+zj_zj5.toFixed(2)+"/"+zj_dz5+"</td>"
					}*/
					+"</tr>";
			}
			if(bj == "3"){
				$("#zj_table_qg_jszt3").html("");
				$("#zj_table_qg_jszt3").append(zj_tr);
			}
			if(bj == "31"){
				var zj_tr = "<tr>"
					+"<td></td>"
					+"<td colspan = '1'style='width: 165px;'>总计</td>"
					+"<td>"+(data[0].rlz/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+data[0].dzz+"</td>"
					+"<td>"+(data[0].rl1/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+data[0].dz1+"</td>"
					+"<td>"+(data[0].rl2/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+data[0].dz2+"</td>"
					+"<td>"+(data[0].rl3/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+data[0].dz3+"</td>"
					+"<td>"+(data[0].rl4/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+data[0].dz4+"</td>"
					+"<td>"+(data[0].rl5/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+data[0].dz5+"</td>"
					+"<td>"+(data[0].rl6/10000).toFixed(2)+"&nbsp;&nbsp;/&nbsp;&nbsp;"+data[0].dz6+"</td>"
				/*	if(bj == "qg_jszt"){
						zj_tr +="<td>"+zj_zj5.toFixed(2)+"/"+zj_dz5+"</td>"
					}*/
					+"</tr>";
				$("#zj_table_qg_jszt3").html("");
				$("#zj_table_qg_jszt3").append(zj_tr);
			}
			if(bj == "4"){
				$("#zj_table_qg_kffs").html("");
				$("#zj_table_qg_kffs").append(zj_tr);
			}
			if(bj == "qg_kffs_date"){
				$("#zj_table_qg_kffs_date").html("");
				$("#zj_table_qg_kffs_date").append(zj_tr);
			}
			if(bj == "qg_kffs_date_zj"){
				$("#zj_table_qg_kffs_date_zj").html("");
				$("#zj_table_qg_kffs_date_zj").append(zj_tr);
			}
		}
	})
}

function initTable5(bj){
	var zjbj=0;
	var xzqh=getYixuanId();
	if(xzqh.length==1869){//表示全选
		xzqh=null;
		zjbj=1;
	}
	$.ajax({
		url:basePath+"jcxx/jcxx!countTbzh.action",
		data:{"bj":bj,"xzlyqh":xzqh},
		type: "POST",
		dataType:"json",
		success:function(response){
		var data = response.rows;
		var tr = "";
		var len = 1;
		var flen = 1;
		var zj_zj =0; var zj_dz =0; var zj_zj1 =0; var zj_dz1 =0;
		var zj_zj2 =0; var zj_dz2 =0; var zj_zj3 =0; var zj_dz3 =0; 
		var zj_zj4 =0; var zj_dz4 =0; var zj_zj5 =0; var zj_dz5 =0; 
		for (var i = 0; i < data.length; i++) {
			//debugger;
			var row = data[i];
			tr +="<tr>"
				if(row.name == '安徽省' || row.name == '贵州省'|| row.name == '上海市'|| row.name == '浙江省'|| row.name == '江西省'
					|| row.name == '湖北省'|| row.name == '湖南省'|| row.name == '重庆市'|| row.name == '四川省'|| row.name == '云南省'
					|| row.name == '江苏省'	|| row.name == '天津市'	|| row.name == '河北省'|| row.name == '山西省'|| row.name == '内蒙古自治区'
					|| row.name == '辽宁省'	|| row.name == '吉林省'	|| row.name == '	黑龙江省'	|| row.name == '	海南省'|| row.name == '广东省'
					|| row.name == '西藏自治区'||row.name == '陕西省'||row.name=='青海省'||row.name== '宁夏回族自治区'||row.name== '新疆维吾尔自治区'	){
					if(i == 0){
						tr +="<td style='width:80px;'>"+(len)+"</td>"
						flen = len;
						zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
						zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
						zj_zj4+=row.zrl4;zj_dz4+=row.zdz4;
						if(bj=='6'){
							zj_zj5+=row.zrl5;zj_dz5+=row.zdz5;
						}
					}else{
						tr +="<td >"+(len+=1)+"</td>"
						flen = len
						zj_zj +=row.zrl;zj_dz +=row.zdz;zj_zj1+=row.zrl1;zj_dz1+=row.zdz1;
						zj_zj2+=row.zrl2;zj_dz2+=row.zdz2;zj_zj3+=row.zrl3;zj_dz3+=row.zdz3;
						zj_zj4+=row.zrl4;zj_dz4+=row.zdz4;
						if(bj=='6'){
							zj_zj5+=row.zrl5;zj_dz5+=row.zdz5;
						}
					}
				}else{
					tr +="<td>"+((flen += 0.1).toFixed(1))+"</td>"
				}
			tr +="<td >"+row.name+"</td>"
			tr +="<td>"+(row.zrl/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz+"</td>"
			tr +="<td>"+(row.zrl1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz1+"</td>"
			tr +="<td>"+(row.zrl2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz2+"</td>"
			tr +="<td>"+(row.zrl3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz3+"</td>"
			tr +="<td>"+(row.zrl4/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz4+"</td>"
			if(bj=='6'){
				tr +="<td>"+(row.zrl5/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+row.zdz5+"</td>"
			}
			tr +="</tr>"
		}
		var zj_tr = "<tr>"
			+"<td '></td>"
			+"<td >总计</td>"
			+"<td>"+(zj_zj/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz+"</td>"
			+"<td>"+(zj_zj1/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz1+"</td>"
			+"<td>"+(zj_zj2/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz2+"</td>"
			+"<td>"+(zj_zj3/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz3+"</td>"
			+"<td>"+(zj_zj4/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz4+"</td>"
			if(bj=='6'){
				zj_tr	+="<td>"+(zj_zj5/10000).toFixed(2) +"&nbsp;&nbsp;/&nbsp;&nbsp;"+zj_dz5+"</td>"
			}
			+"</tr>";
		
		if(bj=='5'){
			if( zjbj==1){
				zj_tr=	getZj(bj);
			}
			$("#table_qg_jszt5").html("");
			$("#table_qg_jszt5").append(tr);
			$("#zj_table_qg_jszt5").html("");
			$("#zj_table_qg_jszt5").append(zj_tr);
		}
		if(bj=='6'){
			if( zjbj==1){
				zj_tr=	getZj(bj);
			}
			$("#table_qg_jszt6").html("");
			$("#table_qg_jszt6").append(tr);
			$("#zj_table_qg_jszt6").html("");
			$("#zj_table_qg_jszt6").append(zj_tr);
		}
		}
	})
}



//通用导出
function jc_export(num){
	var arr=getYixuanId();
	 var fileName;//定义文件名称
	 var tabTitle;//表头
	 var sqlTitle;//sqlTitle
	 var sql;//sql，动态
	 var tabType;//tabType
	 if(num==1){
		 tabType="1";
		 fileName = "按装机规模分类统计" ;
		  tabTitle = ['序号','地区','总装机规模（千瓦）','总电站数量（座）','5万千瓦到1万千瓦装机规模（千瓦）','5万千瓦到1万千瓦电站数量（座）'
			 ,'1万千瓦到500千瓦总装机规模（千瓦）','1万千瓦到500千瓦电站数量（座）'
			 ,'500千瓦以下总装机规模（千瓦）','500千瓦以下电站数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = "select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.zjrl>=10000 AND H.zjrl<=50000  then H.zjrl else 0 end )AS zrl1, "
			      +" SUM(CASE WHEN  H.zjrl>=10000 AND H.zjrl<=50000  then 1 else 0 end )AS zdz1, "
			      +"  SUM(CASE WHEN H.zjrl>=500 AND  H.zjrl<10000 then H.zjrl else 0 end )AS zrl2, "
			      +" SUM(CASE WHEN H.zjrl>=500 AND  H.zjrl<10000   then 1 else 0 end )AS zdz2,  "
			      +" SUM(CASE WHEN H.zjrl<500 then H.zjrl else 0 end )AS zrl3,  "
			      +" SUM(CASE WHEN   H.zjrl<500  then 1 else 0 end )AS zdz3   "
			      +"  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0"
			      +" AND A.id IN (" + arr + ")  " 
			      +"  ORDER BY A.id  "
	 }else if (num==2) {
		 tabType="2";
		 fileName = "小水电所在流域分布" ;
		  tabTitle = ['序号','流域名称','总装机规模（千瓦）','总电站数量（座）','5万千瓦到1万千瓦装机规模（千瓦）','5万千瓦到1万千瓦电站数量（座）'
			 ,'1万千瓦到500千瓦总装机规模（千瓦）','1万千瓦到500千瓦电站数量（座）'
			 ,'500千瓦以下总装机规模（千瓦）','500千瓦以下电站数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = " select S.name AS name ,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,"
			      +" SUM(CASE WHEN H.zjrl>=10000 AND H.zjrl<=50000 then  H.zjrl else 0 end )AS zrl1, "
			      +"  SUM(CASE WHEN  H.zjrl>=10000 AND H.zjrl<=50000 then 1 else 0 end )AS zdz1,  "
			      +" SUM(CASE WHEN H.zjrl>=500 AND  H.zjrl<10000 then H.zjrl else 0 end )AS zrl2, "
			      +" SUM(CASE WHEN H.zjrl>500 AND  H.zjrl<10000 then 1 else 0 end )AS zdz2,  "
			      +" SUM(CASE WHEN   H.zjrl<500 or H.zjrl is null  then H.zjrl else 0 end )AS zrl3,  "
			      +" SUM(CASE WHEN H.zjrl<500 or H.zjrl is null  then 1 else 0 end )AS zdz3      "
			      +"  from hps_info_sum1 H  LEFT JOIN Area A  ON  A.id = H.sd_sheng_id "
			      +" LEFT JOIN sys_dict S ON H.xmszly = S.nm AND S.listnm_sys_dict_cate = 'xmszly' "
			      +" WHERE A.id IN (" + arr + ")  " 
			      +"  GROUP BY S.name,S.nm "
			 //     +"  GROUP BY H.xmszly  "
			      +"  ORDER BY S.nm  "
	}else if (num==3) {
		 tabType="2";
		 fileName = "小水电建设状态统计" 
			  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）','拟建装机规模（千瓦）','拟建电站数量（座）'
				 ,'在建装机规模（千瓦）','在建电站数量（座）','运行装机规模（千瓦）','运行电站数量（座）','废弃装机规模（千瓦）','废弃电站数量（座）','拆除装机规模（千瓦）','拆除电站数量（座）']
			  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4','rl5','dz5']
			  sql = " select name, "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name ) as rlz ,  "
				      +" (SELECT count(id) from hps_info_sum1 where sheng=A.name ) as dzz, "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=4) as rl1,  "
				      +" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=4) as dz1, "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=5) as rl2,  "
				      +" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=5) as dz2,  "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=3) as rl3,  "
				      +" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=3) as dz3,  "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=2) as rl4, " 
				      +" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=2) as dz4,  "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where sheng=A.name and jszt=1) as rl5, "
				      +" (SELECT count(id) from hps_info_sum1 where sheng=A.name and jszt=1) as dz5 "
				      +" from Area A WHERE codeid LIKE '%0000' AND (SELECT count(id) from hps_info_sum1 where sheng=A.name ) > 0  "
				      +" UNION ALL SELECT '总数' AS name, ISNULL(Round(SUM(zjrl)/10000,2), 0) AS rlz,COUNT(id) AS dzz, "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where  jszt=4) as rl1,  "
				      +" (SELECT count(id) from hps_info_sum1 where jszt=4) as dz1, "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where jszt=5) as rl2,  "
				      +" (SELECT count(id) from hps_info_sum1 where jszt=5) as dz2,  "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where jszt=3) as rl3,  "
				      +" (SELECT count(id) from hps_info_sum1 where jszt=3) as dz3,  "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where jszt=2) as rl4, "
				      +" (SELECT count(id) from hps_info_sum1 where jszt=2) as dz4,  "
				      +" (SELECT ISNULL(Round(SUM(zjrl)/10000,2), 0) from hps_info_sum1 where jszt=1) as rl5,  "
				      +" (SELECT count(id) from hps_info_sum1 where jszt=1) as dz5 "
				      +" FROM hps_info_sum1 "
	}else if (num==4) {
		 tabType="2";
		 fileName = "小水电开发方式统计" 
			  tabTitle = ['序号','地区','总装机数量（千瓦）','总电站数量（座）'
				  ,'坝后式装机规模（千瓦）','坝后式电站数量（座）'
				 ,'河床式装机规模（千瓦）','河床式电站数量（座）'
				 ,'混合式装机规模（千瓦）','混合式电站数量（座）'
				 ,'引水式装机规模（千瓦）','引水式电站数量（座）']
			  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4']
			  sql = "  select H.sheng AS name,SUM(H.zjrl) AS rlz,COUNT(H.id) AS dzz, "
				      +" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1,  "
				      +" SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
				      +" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3,  "
				      +" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4,  "
				      +" SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl1,  "
				      +"SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )AS rl2, "
				      +" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3,  "
				      +" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl4"
				      +" from hps_info_sum1 H " 
				      +" GROUP BY H.sheng HAVING COUNT(H.id) > 0  "
				      +" ORDER BY COUNT(H.id) DESC "
	}else if (num==5) {
		 tabType="2";
		 fileName = "设计年发电量随年份统计" 
			  tabTitle = ['序号','地区','总装机数量（万千瓦）','总电站数量（座）'
				  ,'坝后式装机规模（千瓦）','坝后式电站数量（座）'
				 ,'河床式装机规模（千瓦）','河床式电站数量（座）'
				 ,'混合式装机规模（千瓦）','混合式电站数量（座）'
				 ,'引水式装机规模（千瓦）','引水式电站数量（座）']
			  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4']
			  sql = " SELECT A.date AS name,ISNULL(SUM(H.zjrl), 0) AS rlz,COUNT(H.id) AS dzz, "
				      +" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1,   "
				      +" SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
				      +" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3,  "
				      +" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4, "
				      +"  SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl1,  "
				      +" SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )AS rl2, "
				      +" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3,   "
				      +" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl4  "
				      +"  FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.dysj) " 
				      +" GROUP BY A.date HAVING A.date <= GETDATE()   "
				      +" ORDER BY A.date DESC  "
	}else if (num==6) {
		 tabType="2";
		 fileName = "小水电发展历程" 
			  tabTitle = ['序号','地区','总装机数量（万千瓦）','总电站数量（座）'
				  ,'坝后式装机规模（千瓦）','坝后式电站数量（座）'
				 ,'河床式装机规模（千瓦）','河床式电站数量（座）'
				 ,'混合式装机规模（千瓦）','混合式电站数量（座）'
				 ,'引水式装机规模（千瓦）','引水式电站数量（座）']
		  sqlTitle = [     'name','rlz','dzz','rl1','dz1','rl2','dz2','rl3','dz3','rl4','dz4']
		  sql = " SELECT A.date AS name,ISNULL(SUM(H.zjrl), 0) AS rlz,COUNT(H.id) AS dzz, "
			      +" SUM(CASE WHEN H.kffs = 2 then 1 else 0 end )AS dz1,   "
			      +" SUM(CASE WHEN H.kffs = 4 then 1 else 0 end )AS dz2, "
			      +" SUM(CASE WHEN H.kffs = 3 then 1 else 0 end )AS dz3,  "
			      +" SUM(CASE WHEN H.kffs = 1 then 1 else 0 end )AS dz4, "
			      +"  SUM(CASE WHEN H.kffs = 2 then H.zjrl else 0 end )AS rl1,  "
			      +" SUM(CASE WHEN H.kffs = 4 then H.zjrl else 0 end )AS rl2, "
			      +" SUM(CASE WHEN H.kffs = 3 then H.zjrl else 0 end )AS rl3,   "
			      +" SUM(CASE WHEN H.kffs = 1 then H.zjrl else 0 end )AS rl4  "
			      +"  FROM date_sum A LEFT JOIN hps_info_sum1 H ON A.date = DATENAME(yy, H.dysj) " 
			      +" GROUP BY A.date HAVING A.date <= GETDATE()   "
			      +" ORDER BY A.date DESC  "
	}else if (num==7) {
		 tabType="2";
		 fileName = "小水电资金来源方式(所有制)" 
			  tabTitle = ['序号','地区','总装机数量（万千瓦）','总电站数量（座）'
				  ,'国有性质总装机规模（千瓦）','国有性质总电站数量（座）'
				 ,'民营性质总装机规模（千瓦）','民营性质总电站数量（座）'
				 ,'集体性质总装机规模（千瓦）','集体性质总电站数量（座）'
				 ,'混合性质总装机规模（千瓦）','混合性质总电站数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3','zrl4','zdz4']
		  sql = " select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,     "
			      +" SUM(CASE WHEN H.tzxz='国有'  then H.zjrl else 0 end )AS zrl1,   "
			      +" SUM(CASE WHEN H.tzxz='国有'  then 1 else 0 end )AS zdz1,   "
			      +" SUM(CASE WHEN H.tzxz='集体'  then H.zjrl else 0 end )AS zrl2,  "
			      +" SUM(CASE WHEN H.tzxz='集体'  then 1 else 0 end )AS zdz2,  "
			      +"  SUM(CASE WHEN H.tzxz='民营'  then H.zjrl else 0 end )AS zrl3,   "
			      +" SUM(CASE WHEN H.tzxz='民营'  then 1 else 0 end )AS zdz3 , "
			      +" SUM(CASE WHEN H.tzxz='混合' then H.zjrl else 0 end )AS zrl4,  "
			      +"  SUM(CASE WHEN H.tzxz='混合'   then 1 else 0 end )AS zdz4  "
			      +"  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id   " 
			      +"  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			      +"  AND A.id IN (" + arr + ") "
			      +"  ORDER BY A.id "
	}else if (num==8) {
		 tabType="2";
		 fileName = "小水电并网情况统计" 
			  tabTitle = ['序号','地区','总装机数量（万千瓦）','总电站数量（座）'
				  ,'接入电网总装机规模（千瓦）','接入电网总电站数量（座）'
				 ,'直供电（民用）总装机规模（千瓦）','直供电（民用）总电站数量（座）'
				 ,'直供电（化工）总装机规模（千瓦）','直供电（化工）总电站数量（座）'
				 ,'直供电（其它）总装机规模（千瓦）','直供电（其它）总电站数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3','zrl4','zdz4']
		  sql ="    select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,     "
		 	+"   SUM(CASE WHEN H.bwqk='接入电网'  then H.zjrl else 0 end )AS zrl1, "
		 	+"  SUM(CASE WHEN H.bwqk='接入电网'   then 1 else 0 end )AS zdz1,"
			+"   SUM(CASE WHEN H.bwqk='直供电(化工)'   then H.zjrl else 0 end )AS zrl2,  "
			+"  SUM(CASE WHEN  H.bwqk='直供电(化工)'   then 1 else 0 end )AS zdz2,  "
			+"  SUM(CASE WHEN  H.bwqk='直供电(民用电)'   then H.zjrl else 0 end )AS zrl3,   "
			+"  SUM(CASE WHEN  H.bwqk='直供电(民用电)'  then 1 else 0 end )AS zdz3 ,   "
			+"   SUM(CASE WHEN H.bwqk='直供电(其他)' then H.zjrl else 0 end )AS zrl4,    "
			+"   SUM(CASE WHEN H.bwqk='直供电(其他)'   then 1 else 0 end )AS zdz4   "
			+"  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id     "
			+"  GROUP BY A.name,A.id HAVING COUNT(H.id) > 0  "
			+"  AND A.id IN (" + arr + ") "
			+"  ORDER BY A.id ";
	}else if (num==9) {
		 tabType="2";
		 fileName = "小水电项目核准情况统计" ;
		  tabTitle = ['序号','地区','总装机规模（千瓦）','总电站数量（座）','核准小水电总装机规模（千瓦）','核准小水电总电站数量（座）'
			 ,'未核准总装机规模（千瓦）','未核准电站数量（座）'
			 ,'核准状态未知总装机规模（千瓦）','核准状态未知电站数量（座）']
		  sqlTitle = [     'name','zrl','zdz','zrl1','zdz1','zrl2','zdz2','zrl3','zdz3']
		  sql = "select A.name,SUM(H.zjrl) AS zrl,COUNT(H.id) AS zdz,  "
			      +" SUM(CASE WHEN H.sfyxmhz='是' then H.zjrl else 0 end )AS zrl1, "
			      +" SUM(CASE WHEN  H.sfyxmhz='是'  then 1 else 0 end )AS zdz1, "
			      +"  SUM(CASE WHEN H.sfyxmhz='否' then H.zjrl else 0 end )AS zrl2, "
			      +" SUM(CASE WHEN H.sfyxmhz='否'   then 1 else 0 end )AS zdz2,  "
			      +" SUM(CASE WHEN H.sfyxmhz IN('',NULL,'未知')  then H.zjrl else 0 end )AS zrl3,  "
			      +" SUM(CASE WHEN   H.sfyxmhz IN('',NULL,'未知')   then 1 else 0 end )AS zdz3   "
			      +"  from hps_info_sum1 H LEFT JOIN Area A  ON A.id = H.sd_sheng_id OR A.id = H.sd_shi_id  "
			      +" GROUP BY A.name,A.id HAVING COUNT(H.id) > 0"
			      +" AND A.id IN (" + arr + ")  " 
			      +"  ORDER BY A.id  "
	}
	 
	 
	 daochu(fileName,tabTitle,sqlTitle,sql,tabType)
}

function daochu(fileName,tabTitle,sqlTitle,sql,t){
	 $.ajax({
	  	 url : basePath + "sjjs/sjjs!comDetails.action",
	 	 type : "post",
	     dateType : "JSON",
	 	 async : false,
	 	 traditional:true,
	     data : {"uploadBean.tabTitle":tabTitle
	    	 	,"uploadBean.sqlTitle":sqlTitle
	    	 	,"uploadBean.sql":sql
	    	 	,"uploadBean.fileName":fileName
	    	 	,"uploadBean.tabType":t
	    	 	},
	 	 success : function(response){
	  		 window.location.href=basePath + "sjjs/sjjs!comUpload.action"   
	  	 }
	})
}

autodivheight();
function autodivheight(){ //函数：获取尺寸
    //获取浏览器窗口高度
    var winHeight=0;
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;
    //通过深入Document内部对body进行检测，获取浏览器窗口高度
    if (document.documentElement && document.documentElement.clientHeight)
        winHeight = document.documentElement.clientHeight;
    //DIV高度为浏览器窗口的高度
    document.getElementById("tree1").style.height= winHeight-77 +"px";
    
  
}


