<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<div  style="width:100%;height:100%;overflow-y:auto;">
		<div style="width:100%;height:500px;">
			<div id="hszlb_step3_div"  style="width:50%;height:100%;float: left;">
				<div id="hszlb_step3_chart" style="width: 90%;height:90%;margin-left: 5px;highlight-color:#000000;" ></div>
			</div>
			<div id="tsqx_div"  style="width:50%;height:100%;float: right;">
				<div id="tsqx_step3_chart" style="width: 90%;height:90%;"></div>
			</div>
		</div>	
			<div style="width:100%;height:50px;">
				<div style="width:50%;height:100%;float: left;">
				<button class="btn" onclick="saveStep3Result()">选择节点保存提交</button>
				<button class="btn" onclick="deleteStep3Result()">删除</button>
				<button class="btn" onclick="okDrag()">确定</button>
				<input type="button" onclick="cl_excel3()" id="chanliu_daochu3" style="margin-left: 20px;" value="导出到excel"  class="btn btn-primary" >
				</div>
					<div style="width:50%;height:100%;float: right;">
								<label>站名:<input type="hidden" name="" id="userName"></label>
							<input type="text" name="mTsqxFormBean.mTsqxInfoBean.username" id="stnm" class="form-control" 
								readonly style="width: 80px;display:inline;">
							<label>洪号:</label>
							<input type="text" class="form-control"  id="TAB_PCH"
								readonly style="width: 150px;display:inline;">	
							<label>流域面积(km²):</label>
							<input type="text" id="lymj" class="form-control" 
								readonly style="width: 100px;display:inline;">
					</div>
			</div>  

			<div id="jls_step3_div" style="width: 100%;height: calc(100% - 550px);" >
				<table id="chanliu_step3_table" lay-filter="hszlb_table"></table>
			</div>
		</div>

<%-- <script src="../business/consumer/analysisjs/chanliujs/chanliu3.js"></script> --%>
<script>
var symbolSize = 20;
var newData = [];
var oldData = [];
var lineData = [];
var lineData2 = [];
var tempData = [];
var niheResult=[];//拟合结果
var selectResult=[];
var effectScatterParam={
		start:null,
		end:null,
		left:null,//左边交叉点
		right:null,//右边交叉点
		first:null,//起点位置
		last:null
	};
var myChart2 = echarts.init(document.getElementById('tsqx_step3_chart'));
var myChart = echarts.init(document.getElementById('hszlb_step3_chart'));
var registDragEvent=false;
var operFlag=null;
var isOk=false;
//导出到excel
function cl_excel3(){
	var h=hh;
	if(h==''){
		confirm("产流计算", "请选择一条计算结果")
		return false;
	}
	var url= basePath + "chanliu/chanliu!chanLiuExportExcel3.action?pch="+h+
			"&stcd="+stcd +"&beginDate="+secondBeginDate+"&endDate="+secondEndDate+"&llhj="+llhj+"&rs3="+rs3 ;      
	confirm("<i class='icon icon-circle-arrow-up'></i>&nbsp;导出到excel","您确定要导出到excel吗？","icon-info", function(result) {
		   if(result){
			   window.location.href=url;
		   }
		});
	
	loadChanliuStepEchart();
}
$(function(){
	loadChanliuStepEchart();
	//getHszlbGxt("hszlb_step3_chart",basePath + "chanliu/chanliu!step2chart.action",{"stcd":stcd,"start":beginDate,"pch":hh, "end":endDate,"INTERVAL":interval} );
	//getHszlbGxt("tsqx_step3_chart",basePath + "chanliu/chanliu!getTsqx.action",{"stcd":stcd,"start":beginDate, "end":endDate,"username":staffCode} );
	//LoadEditData(stcd,staffName);
	//getJlsTable("#jls_step3_div","#chanliu_step3_table","test_jls_tab.json");
	$("#stnm").val(stnm);
	$("#TAB_PCH").val(hh);//洪号
})
var llhj;//流量合计
var rs3;//R实
function deleteStep3Result(){
			layer.confirm("确定要删除原有保存数据吗?", { title: "删除确认" }, function (index) {
             	layer.close(index);
             	var url = basePath + "chanliu/chanliu!deleteChanliuStep3Result.action";
				$.ajax({
					url:url,
					type:"post",
					data:{stcd:stcd,pch:hh},
					dataType:"json",
					success:function(response){
						if(response.reflag==1||response.reflag=="1"){
							niheResult=[];
							loadChanliuStepEchart();
						}else{
							layer.msg(response.message);
						}
					}
				});
        });
}
function loadChanliuStepEchart(){
	$.ajax({
        url : basePath + "chanliu/chanliu!chanliuStep3ChartData.action",
        type : "post",
        dataType : "JSON",
        async : false,
        traditional: true,
        data : {"stcd":stcd,"start":secondBeginDate, "end":secondEndDate,"username":staffCode,"pch":hh,"INTERVAL":interval},
        success : function(response) {
          if(response.reflag==1||response.reflag=="1"){
        	xdata=response.tsqxXdata;
        	var tsqxData={
        		xdata:response.tsqxXdata,
        		max:response.tsqxMax,
        		min:response.tsqxMin,
        		data:response.tsqxData
        	};
        	loadTsqxEchart(tsqxData);
        	var thridResult=response.thirdResult;
        	var llAndJylData={
        		llData:response.llData,
        		maxLL:response.maxLL,
        		minLL:response.minLL,
        		jylData:response.jylData,
        		maxJYL:response.maxJYL,
        		maxTSQX:response.tsqxMax,
        		minTSQX:response.tsqxMin,
        		thridResult:thridResult,
        		thridLess:response.thirdLess?response.thirdLess:null
        	};
        	loadLLandJYLEchart(llAndJylData);
        	var secondList = response.secondList;
        	if(secondList!=null && secondList.length>0){
        		$("#lymj").val(secondList[0].LYMJ);//流域面积
        	}
        	llhj=null;
        	rs3=null;
        	var dataList=response.tableList;
        		if(dataList!=null && dataList.length>0 
        		&& secondList!=null && secondList.length>0){
        			var second=secondList[0];
					llhj=second.SQT3;
					rs3=second.RS;
        		}
        		if(llhj==null){
        			llhj=0;
        			for(var i=0;i<dataList.length-1;i++){
						var qt=Number(dataList[i].QT).toFixed(3);
						llhj=parseFloat(llhj)+parseFloat(qt);
        			}
        		}
				//dataList.push({DT:"流量合计",INTERVAL:"",Q:"",QT:llhj});
				var lymj3=$("#lymj").val();
				rs3=(lymj3!=null && lymj3!=0)?parseFloat(llhj)/parseFloat(lymj3)/1000:0;
				rs3=Number(rs3).toFixed(1);
				dataList.push({DT:"R实(mm)",INTERVAL:"",Q:"",QT:rs3});
        		loadStep3Table("#jls_step3_div","#chanliu_step3_table",dataList);
          }else{
          	layer.msg(response.message);
          }
        }
    });
}
function loadLLandJYLEchart(llAndJylData){
	var maxLL=llAndJylData.maxLL;
	var minLL=llAndJylData.minLL;
	var maxJYL=llAndJylData.maxJYL;
	var maxTSQX=llAndJylData.maxTSQX;
	var minTSQX=llAndJylData.minTSQX;
	maxLL=maxLL>maxTSQX?maxLL:maxTSQX;
	minLL=minLL<minTSQX?minLL:minTSQX;
	if(maxLL==0){
	   maxLL=10;
	}
	var llScale=standard(maxLL,minLL,4);
	var llValue=llScale[3];
	maxLL=llScale[0]+llValue*4;
	minLL=llScale[1];
	if(maxJYL==0){
	   maxJYL=10;
	}
	var jylScale=standard(maxJYL,0,4);
	var jylValue=jylScale[3];
	maxJYL=jylScale[0]+jylValue*4;
	var llData=llAndJylData.llData;
	lineData=llData;
	selectResult=llData;
	tempData[0]=llData!=null && llData.length>0?llData[0]:null;
	var llLen=llData!=null?llData.length:0;
	var minLLX=llData!=null && llLen>0?llData[0][0]:null;
	var maxLLX=llData!=null && llLen>0?llData[llLen-1][0]:null;
	var jylData=llAndJylData.jylData;
	var jylLen=jylData!=null?jylData.length:0;
	var minJYLX=jylData!=null && jylLen>0?jylData[0][0]:null;
	var maxJYLX=jylData!=null && jylLen>0?jylData[jylLen-1][0]:null;
	var thridResult=llAndJylData.thridResult;
	var thridLess=llAndJylData.thridLess?llAndJylData.thridLess:null;
	if(thridLess!=null && thridLess.length>0){
		for(var i=0;i<thridLess.length;i++){
			thridResult.unshift(thridLess[i]);
		}
	}
	var minX=null;
	if(minLLX!=null && minJYLX!=null){
		minX=minLLX<minJYLX?minLLX:minJYLX;
	}else if(minLLX!=null){
		minX=minLLX;
	}else if(minJYLX!=null){
		minX=minJYLX;
	}
	var maxX=null;
	if(maxLLX!=null && maxJYLX!=null){
		maxX=maxLLX>maxJYLX?maxLLX:maxJYLX;
	}else if(maxLLX!=null){
		maxX=maxLLX;
	}else if(maxJYLX!=null){
		maxX=maxJYLX;
	}
	var resultMaxX=(thridResult!=null && thridResult.length>0)?thridResult[thridResult.length-1].DT:null;
	if(resultMaxX!=null && resultMaxX.substring(0,16)>maxX){
		maxX=resultMaxX.substring(0,16);
	}
	if(minX!=null){
		var mt=new Date(minX+":00").getTime()-3600*1000;
		minX=new Date(mt).getTime();
	}
	if(maxX!=null){
		var mt=new Date(maxX+":00").getTime()+3600*1000;
		maxX=new Date(mt).getTime();
	}
	var leged=["流量","降雨量"];
	var series = new Array();
    series.push({
                "name":"流量",
                type: 'line',
				smooth:true,
				smoothMonotone:'none',
                "lineStyle": {
                color:"#000000",
                "normal":
                {
                "width": 1.5
                }
                },
                "data":llData
            });
    series.push({
                "name":"降雨量",
                "itemStyle":{
                	color:"#0000EE"
                },
                "type":"bar",
                "yAxisIndex":1,
                "data":jylData
            });
            
    
    if(thridResult!=null && thridResult.length>0){
    	var niheData=new Array();
    	for(var i=0;i<thridResult.length;i++){
    		niheData.push([thridResult[i].DT.substring(0,16),thridResult[i].Q]);
    	}
    	leged.push("分割洪水");
    	series.push({
    			id:'fghsqx',
                "name":"分割洪水",
                type: 'line',
				smooth:true,
				smoothMonotone:'none',
                "lineStyle": {
                color:"#6699FF",
                "normal":
                {
                "width": 1.5
                }
                },
                "data":niheData
            });
    }
	var yAxis = new Array(); 
	yAxis.push({
                "name": "流量",
                "type": "value",
                "max":maxLL,
                "min":minLL,
            	interval:llValue
            });
    yAxis.push({
                "name": "降雨量",
                "nameLocation": 'start',
                "type": 'value',
                "inverse": true,//反向坐标
                "max":maxJYL,
                "min":0,
            	interval:jylValue
            });
     
	var option = {
		        title : {
		            x: 'center',//标题剧中
		            padding:0
		        },
		        grid: {
		        	right:'50px'
		        },
		        tooltip : {//鼠标悬浮提示信息
		            trigger: 'axis',//显示横坐标信息
				    axisPointer: {//xy提示轴线
		                type: 'cross'
		            },
		            formatter:function(params, ticket, callback){
		            	var desc="";
		            	if(params!=null && params.length>0){
		            		for(var i=0;i<params.length;i++){
		            			var label=params[i].seriesName;
		            			var data=params[i].data;
		            			if(i==0){
		            				desc=data[0]+"<br/>";
		            			}
		            			desc=desc+params[i].marker+label+":"+data[1]+"<br/>";
		            		}
		            	}
		            	return desc;
		            }
		        },
		        legend: {
		            data:leged, //最上边的切换按钮
		        },
		        xAxis : [
		            {
		                type : 'time'
		            }
		        ],
		        yAxis: yAxis,
		        series:  series
		    };
		myChart.clear();
    	myChart.setOption(option);
}
function loadTsqxEchart(tsqxData){
	var max=tsqxData.max;
	var min=tsqxData.min;
	var dataList=tsqxData.data;
	xdata=tsqxData.xdata;
	if(max==0){
	   max=10;
	}
	var scaleArr=standard(max,min,5);
	var corvalue=scaleArr[3];
	max=scaleArr[0]==max?max+corvalue:scaleArr[0];
	min=scaleArr[1];
	var	yAxis = {
    			type: 'value',
    			name:'流量Q(m³/s)',
				min:min,
				max:max,
				interval:corvalue
    		};
    var series=new Array();
    var legend_data = new Array();
    if(dataList!=null && dataList.length>0){
    	legend_data=getLegend_data(dataList);
    	for(var i=0;i<dataList.length;i++){
    		series.push({
                "name":dataList[i].NAME,
                type: 'line',
				smooth:true,
				smoothMonotone:'none',
                "lineStyle": {
                "normal":
                {
                "width": 1.5
                }
                },
                "data":dataList[i].DATA
            });
    	}
    }
	var title="退水曲线图";
	    var option = {
	            title : {
	                text: title,//主标题
	                x: 'left',//标题剧中
	                padding:0
	            },
	            color:['#000000','#0000EE','#6699FF','#FF8833','#6666FF','#FF9966','#66CCFF','#FFCC66','#99CCFF','#FFCC99','#CCFFFF'],
	            grid: {
	            	right:'50px'
	            },
	            tooltip : {//鼠标悬浮提示信息
	                trigger: 'axis',//显示横坐标信息
	    		    axisPointer: {//xy提示轴线
	                    type: 'cross',
	                    label: {
	                        backgroundColor: '#505765'
	                    }
	                }
	            },
	            legend: {
	                data:legend_data, //最上边的切换按钮
	            },
	            xAxis :
	                {
	                	name:"时段(T)",
	                    type : 'value',
	                    min:parseInt(xdata[0]),
	                    max:parseInt(xdata[xdata.length-1])+1,
	                },
	            yAxis: yAxis,
	            series:  series
	        };
	 	myChart2.clear();
		myChart2.setOption(option);
		myChart2.on('click', function (params) { //点击右边的曲线触发的方法
			var obj=this;
			layer.confirm('当前是切涨水段还是退水段？', {
			  btn: ['退水段','涨水段'] //按钮
			}, function(index){
				operFlag="right";
				layer.close(index);
			  	rightTsqxClickEvent(obj,params);
			}, function(index){
				operFlag="left";
				layer.close(index);
			  	rightTsqxClickEvent(obj,params);
			});
		});
}
function rightTsqxClickEvent(obj,params){
			newData = obj.getOption().series[params.seriesIndex].data;
   			oldData = obj.getOption().series[params.seriesIndex].data;
			var newValue = tempData[0]!=null?parseInt(tempData[0][1]):0;
			var oldValue = parseInt(oldData[0][1]);
			var diffValue = newValue ;
			var l=newData.length;
			if(newData!=null&&newData.length>0){
				var diyige=	newData[0][0];//右边第一个值
				var xin=[];
				for(var i = 0 ; i < newData.length ; i++){
					if(diyige==newData[i][0]){
						xin.push(newData[i]);
						diyige++;
					}else{
						var x1=newData[i-1][0];
						var y1=newData[i-1][1];
						var x2=newData[i][0];
						var y2=newData[i][1];
						var x=diyige;
						if(i>0 && diyige>x1 && diyige<x2){
							var l=y1-y2;
							var c1=x-x1;
							var c2=x2-x;
							var bl=c1/(c1+c2);
							var y=(x1+l*bl).toFixed(2);
							xin.push([x,y]);
							diyige++;
						}
					}
				}  
			}
	
			var zuobian1=lineData[0][0];
			if(xin!=null && xin.length>0){
				for(var i=0; i<xin.length;i++){
					xin[i][0]=lineData[i][0];
				}
			}
			newData=xin;
			loadRightTsqx(xin);
}
function loadRightTsqx(tsqxData){
				var options=myChart.getOption();
				var legend=options.legend[0];
				var odata=legend.data;
				var isexist=false;
				for(var i=0;i<odata.length;i++){
					if(odata[i]=="退水曲线"){
						isexist=true;
						break;
					}
				}
				if(!isexist){
					odata.push("退水曲线");
				}
				myChart.setOption({
					legend: {
			            data:odata, //最上边的切换按钮
			        },
			        series: [{
			            id: 'tsqx',
			            name:"退水曲线",
			            type: 'line',
						smooth:true,
						smoothMonotone:'none',
		                "lineStyle": {
		                color:"#FF8833",
		                "normal":
		                {
		                "width": 1.5
		                }
		                },
			            data: tsqxData
			        }],
			        graphic: echarts.util.map(newData, function (item, dataIndex) {
		                return {
		                    type: 'line',
		                    position: myChart.convertToPixel('grid', item),
		                    shape: {
		                        r: symbolSize / 2
		                    },
		                    invisible: true,
		                    draggable: true,
		                    ondrag: echarts.util.curry(onPointDragging, dataIndex),
		                    onmousemove: echarts.util.curry(showTooltip, dataIndex),
		                    onmouseup: echarts.util.curry(hideTooltip, dataIndex),//鼠标点击放开之后保证黄线不移动
		                    z: 100
		                };
		            })
			    });
}
function undragEvent(){
				myChart.setOption({
			        graphic: echarts.util.map(newData, function (item, dataIndex) {
		                return {
		                    type: 'line',
		                    position: myChart.convertToPixel('grid', item),
		                    draggable: false,
		                    ondrag: null,
		                    onmousemove: null,
		                    onmouseup: null,//鼠标点击放开之后保证黄线不移动
		                    z: 100
		                };
		            })
			    });
			    myChart.setOption({
			        graphic: echarts.util.map(niheResult, function (item, dataIndex) {
		                return {
		                    type: 'line',
		                    position: myChart.convertToPixel('grid', item),
		                    draggable: false,
		                    onclick:echarts.util.curry(onclickEvent, dataIndex),
		                    ondrag: null,
		                    onmousemove: null,
		                    onmouseup: null,//鼠标点击放开之后保证黄线不移动
		                    z: 100
		                };
		            })
			    });
}
function test(){
	var x = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
	var series = [{
               name: '总量',
               type: 'line',
               label: {
                   normal: {
                     show: true,
                  position: 'top'
               }
               },
                data: [0, 50, 100, 150, 200, 150, 100]
          }]
           var option = {
               xAxis: [{
                   type: 'category',
                  boundaryGap: false,
                  data: x
              }],
               yAxis: [{
                   type: 'value'
               }],
               series: series
           };
          var effectScatterData = [0, 50, 100, 150, 200, {value:150,symbolSize:30,}, 100]
          var effectScatter = {
              type: 'effectScatter',
              coordinateSystem: 'cartesian2d',
               data: effectScatterData, //2d坐标系
               symbolSize:0,
               showEffectOn: 'render',
               rippleEffect: {
                   brushType: 'stroke'
               },
               hoverAnimation: true,
               itemStyle: {
                  normal: {
                      color: 'red',
                      shadowBlur: 10,
                       shadowColor: '#333'
                   }
                },
                zlevel: 1
            };
          option.series.push(effectScatter);
          myChart2.setOption(option)
}
function selectResultLine(dataIndex,flag){
	//effectScatterData=niheResult;
	selectResult=[];
	var series = myChart.getOption().series;
	var name=null;
	if(flag=="start"){
		name="起点";
		effectScatterParam.start=niheResult[dataIndex];
		effectScatterParam.first=dataIndex;
	}else if(flag=="end"){
		name="终点";
		effectScatterParam.end=niheResult[dataIndex];
		effectScatterParam.last=dataIndex;
	}
	var startPoint = effectScatterParam.start?effectScatterParam.start:null;
	var endPoint = effectScatterParam.end?effectScatterParam.end:null;
	var b=0,e=niheResult.length-1;
	for(var i=0;i<niheResult.length;i++){
		var point = niheResult[i];
		if(startPoint!=null && startPoint[0]==point[0] && startPoint[1]==point[1]){
			b=i;
		}
		if(endPoint!=null && endPoint[0]==point[0] && endPoint[1]==point[1]){
			e=i;
		}
	}
	for(var i=b;i<e+1;i++){
		selectResult.push(niheResult[i]);
	}
		//console.log(effectScatterParam)
		series.push({
			  name:name,
              type: 'effectScatter',
              coordinateSystem: 'cartesian2d',
               data: [{value:niheResult[dataIndex],symbolSize:10}], //2d坐标系
               symbolSize:0,
               showEffectOn: 'render',
               rippleEffect: {
                   brushType: 'stroke'
               },
               hoverAnimation: true,
               itemStyle: {
                  normal: {
                      color: 'red',
                      shadowBlur: 10,
                      shadowColor: '#333'
                   }
                },
                zlevel: 1
            });
		myChart.setOption({
	        series: series
	    });
}
function onclickEvent(dataIndex){
	layer.confirm('选取该点作为起点还是终点？', {
			  btn: ['起点','终点'] //按钮
			}, function(index){
				layer.close(index);
				selectResultLine(dataIndex,"start");
			}, function(index){
				layer.close(index);
				selectResultLine(dataIndex,"end");
			});
}
//无左边交叉点算法
function calcNoHaveLeft(){
		var llhj=0,rs=0;
		var dataList = new Array();
		var sumData=new Array();
		for(var i=0;i<selectResult.length;i++){
			var qt=0;
			if(i<selectResult.length-1){
				var q1=Number(selectResult[i][1]).toFixed(3);
				var q2=Number(selectResult[i+1][1]).toFixed(3);
				qt=(parseFloat(q1)+parseFloat(q2))*parseInt(interval)*60/2;
				qt=Number(qt).toFixed(3);
				llhj=parseFloat(llhj)+parseFloat(qt);
			}
			sumData.push({DT:selectResult[i][0],INTERVAL:interval,Q:Number(selectResult[i][1]).toFixed(3),QT:qt});
			dataList.push({DT:selectResult[i][0],INTERVAL:interval,Q:Number(selectResult[i][1]).toFixed(3),QT:qt});
		}
		llhj=Number(llhj).toFixed(3);
// 		dataList.push({DT:"流量合计",INTERVAL:"",Q:"",QT:llhj});
		var lymj3=$("#lymj").val();
		var rs=lymj3!=0?parseFloat(llhj)/parseFloat(lymj3)/1000:0;
		rs=Number(rs).toFixed(1);
		dataList.push({DT:"R实(mm)",INTERVAL:"",Q:"",QT:rs});
		return {sumData:sumData,dataList:dataList,lessLineData:[],llhj:llhj,rs:rs};
}
function calHaveLeft(position){
		var llhj=0,rs=0;
		var dataList = new Array();
		var sumData=new Array();
		var lessLineData=new Array();
		var fobj={};//存放需要减去的qt
		for(var i=0;i<selectResult.length;i++){
			var p1=selectResult[i];
			var qt=0;
			if(i<selectResult.length-1){
				var p2=selectResult[i+1];
				var q1=Number(p1[1]).toFixed(3);
				var q2=Number(p2[1]).toFixed(3);
				qt=(parseFloat(q1)+parseFloat(q2))*parseInt(interval)*60/2;
				if(i<position){
					var key = formatDateTimeKey(p2[0]);
					qt=Number(qt).toFixed(3);
					fobj[key]=qt;
					lessLineData.push({DT:p2[0],INTERVAL:interval,Q:Number(p2[1]).toFixed(3),QT:qt});
				}else{
					var key = formatDateTimeKey(p1[0]);
					if(fobj[key]!=null){
						qt=parseFloat(qt)-parseFloat(fobj[key]);
						qt=Number(qt).toFixed(3);
					}
					llhj=parseFloat(llhj)+parseFloat(qt);
				}
			}
			if(i>=position){
				sumData.push({DT:p1[0],INTERVAL:interval,Q:Number(p1[1]).toFixed(3),QT:qt});
				dataList.push({DT:p1[0],INTERVAL:interval,Q:Number(p1[1]).toFixed(3),QT:qt});
			}
		}
		llhj=Number(llhj).toFixed(3);
// 		dataList.push({DT:"流量合计",INTERVAL:"",Q:"",QT:llhj});
		var lymj3=$("#lymj").val();
		var rs=parseFloat(llhj)/parseFloat(lymj3)/1000;
		rs=Number(rs).toFixed(1);
		dataList.push({DT:"R实(mm)",INTERVAL:"",Q:"",QT:rs});
		return {sumData:sumData,dataList:dataList,lessLineData:lessLineData,llhj:llhj,rs:rs};
}
function findQiedianPosition(){
	for(var i=1;i<selectResult.length-1;i++){
		if(new Date(selectResult[i-1][0]).getTime()>new Date(selectResult[i][0]).getTime()
		&& new Date(selectResult[i][0]).getTime()<new Date(selectResult[i+1][0]).getTime()){
			return i;
		}
	}
	return null;
}
function saveStep3Result(){
	var calResult = null;
	var position = findQiedianPosition();
	if(position==null){
		calResult=calcNoHaveLeft()
	}else{
		calResult=calHaveLeft(position);
	}
		if(calResult!=null){
			var sumData=calResult.sumData;
			var dataList=calResult.dataList;
			var lessLineData=calResult.lessLineData;
			var subObject={subdata:sumData,lessLineData:lessLineData,sumQt:calResult.llhj,rs:calResult.rs};
			loadStep3Table("#jls_step3_div","#chanliu_step3_table",dataList);
			var json = {
		       "pch":$("#TAB_PCH").val(),
		   		"stcd" : stcd,
		   		"BEGINDATE" : secondBeginDate,
		   		"ENDDATE" : secondEndDate,
		   		"INTERVAL" : interval,
		   		"DATA" : JSON.stringify(subObject)
		    }
		    $.ajax({
		        url : basePath + "chanliu/chanliu!saveStep3Result.action",
		        type : "post",
		        dataType : "json",
		        traditional: true,
		        data : json,
		        success : function(response) {
		        	if(response.reflag==1||response.reflag=="1"){
		        		layer.msg("保存成功!");
		        	}else{
		        		layer.msg(response.message);
		        	}
		        }
		    });
		}
}
//提交单场洪水数据
function saveLine2(){
	//debugger;
    var json = {
      "pch":$("#TAB_PCH").val(),
   		"STCD" : stcd,
   		"BEGINDATE" : secondBeginDate,
   		"LYMJ":lymj ,
   		"ENDDATE" : secondEndDate,
   		"INTERVAL" : interval,
   		"DATA" : [lineData2]
    }
    $.ajax({
        url : basePath + "chanliu/chanliu!saveLineFor3.action",
        type : "post",
        dataType : "json",
        async : false,
        traditional: true,
        data : json,
        success : function(response) {
        	rshi=response.rs
        	getStep3Table("#jls_step3_div","#chanliu_step3_table",basePath + "chanliu/chanliu!qiuHe.action?STCD="+stcd+"&INTERVAL="+interval+"&pch="+$("#TAB_PCH").val()+"&LYMJ="+lymj);
        }
    });
}
	function loadStep3Table(chart, tab, tableData){
		var height = $(chart).height();
	    var width = $(chart).width();
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            data:tableData,
	            height: height,
	            width: width,
	            limit:tableData.length,
	            id:'cz',
	            cols: [
	                [{
	                    field: 'DT',
	                    title: '日期',
	                },{
	                    field: 'INTERVAL',
	                    title: '时差(分钟)',
	                },{
	                    field: 'Q',
	                    title: '流量(m³/s)',
	                    templet:function(row){
	                    	if(row.Q!=null && row.Q!=""){
	                    		return Number(row.Q).toFixed(3);
	                    	}else{
	                    		return "0";
	                    	}
	                    }
	                },{
	                    field: 'QT',
	                    title: 'Q*T(m³)',
	                    templet:function(row){
	                    	if(row.QT!=null && row.QT!="" && row.QT>0){
	                    		return Number(row.QT).toFixed(3);
	                    	}else{
	                    		return "0";
	                    	}
	                    }
	                }]
	            ],
	            page: false
	        });
	    });
	}
//测站列表数据表格
	function getStep3Table(chart, tab, url){
	    var height = $(chart).height();
	    var width = $(chart).width();
	    layui.use('table', function() {
	        var table = layui.table;
	        table.render({
	            elem: tab,
	            url: url,
	            height: height,
	            width: width,
	            id:'cz',
	            request: {pageName: 'pageIndex' //页码的参数名称，默认：page
	                ,limitName: 'pageSize' //每页数据量的参数名，默认：limit
	            },
	            response: {
	                statusName: 'CODE' //数据状态的字段名称，默认：code
	                ,
	                statusCode: 1 //成功的状态码，默认：0
	                ,
	                msgName: 'MESSAGE' //状态信息的字段名称，默认：msg
	                ,
	                countName: 'TOTALAMOUNT' //数据总数的字段名称，默认：count
	                ,
	                dataName: 'DATA'
	            } //数据列表的字段名称，默认：data} //如果无需自定义数据响应名称，可不加该参数
	            ,
	            cols: [
	                [{
	                    field: 'DATE',
	                    title: '日期',
	                },{
	                    field: 'SC',
	                    title: '时差(分钟)',
	                },{
	                    field: 'LL',
	                    title: '流量(m³/s)',
	                },{
	                    field: 'QT',
	                    title: 'Q*T(m³)',
	                }]
	            ],
	            page: false
	        });
	    });
    }


//窗口大小改变重新计算隐藏的拖拽节点的坐标
window.addEventListener('resize', function () {
    myChart.setOption({
        graphic: echarts.util.map(newData, function (item, dataIndex) {
            return {
                position: myChart.convertToPixel('grid', item)
            };
        })
    });
});

function showTooltip(dataIndex) {
    myChart.dispatchAction({
        type: 'showTip',
        seriesIndex: 0,
        dataIndex: dataIndex
    });
}
//拟合曲线
function niheyunsuan(oldLineData,niheLineData){
	var result=new Array();
	if(oldLineData!=null && oldLineData.length>0){
		var position=-1;
		//查询拟合曲线第一个点所在的位置
		for(var i=0;i<oldLineData.length-1;i++){
			var opoint1=oldLineData[i];
			var opoint2=oldLineData[i+1];
			if (niheLineData != null && niheLineData.length > 0) {
				var nihepoint = niheLineData[0];
				if (nihepoint[0] > opoint1[0] && nihepoint[0] <= opoint2[0]) {
					var t1 = new Date(nihepoint[0]).getTime() - new Date(opoint1[0]).getTime();
					var t2 = new Date(opoint2[0]).getTime() - new Date(nihepoint[0]).getTime();
					if (t1 > t2) {
						position = (i + 1);
					} else {
						position = i;
					}
					break;
				}
			}
		}
		//求拟合曲线与真实曲线每个位置的差值
		if(position>-1){
			var nhposition=-1;
			var czlist=new Array();//计算拟合曲线与原曲线同位置的差值
			if (niheLineData != null && niheLineData.length > 0) {
				var ot=new Date(oldLineData[position][0]).getTime();
				for(var i=0;i<niheLineData.length;i++){
					var t1 = formatDateTime(new Date(ot + (Number(interval)*i) * 60 * 1000));
					niheLineData[i][0]=t1;
					var n=niheLineData[i];
					if(position+i<oldLineData.length){
						var o = oldLineData[position+i];
						if(parseFloat(o[1])>parseFloat(n[1])){
							czlist.push([i,parseFloat(o[1])-parseFloat(n[1])]);
						}else{
							czlist.push([i,parseFloat(n[1])-parseFloat(o[1])]);
						}
					}else{
						break;
					}
				}
				myChart.setOption({
			        series: [{
			            id: 'tsqx',
			            name:'退水曲线',
			            type: 'line',
						smooth:true,
						smoothMonotone:'none',
		                "lineStyle": {
		                "normal":
		                {
		                "width": 1.5
		                }
		                },
			            data: niheLineData
			        }],
			    });
			}
			//查找拟合点，把第一个升序的点作为拟合点
			for(var j=0;j<czlist.length-1;j++){
				if(czlist[j][1]<czlist[j+1][1]){
					nhposition=czlist[j][0];
					break ;
				}
			}
			//
			var isAddOne=(oldLineData[position+nhposition][1]<niheLineData[nhposition][1]);
			var olen=isAddOne?position+1:position;
			for(var k=0;k<olen+nhposition;k++){
				result.push(oldLineData[k]);
			}
			var dt=result[result.length-1][0];
			var nlen=isAddOne?nhposition+1:nhposition;
			for(var n=nlen;n<niheLineData.length;n++){
				result.push(niheLineData[n]);
			}
		}
	}
	return result;
}
function moveTsqx(dataIndex,oldLineData,niheLineData){
	if(oldLineData!=null && oldLineData.length>0){
		var position=-1;
		var dt=new Date(result[result.length-1][0]).getTime();
		if(dataIndex>0){
			for(var j=dataIndex-1;j>-1;j--){
				var t1 = formatDateTime(new Date(dt - (Number(interval)*(dataIndex-1-j)) * 60 * 1000));
				niheLineData[j][0]=t1;
			}
		}
		for(var k=dataIndex;k<niheLineData.length;k++){
			var t1 = formatDateTime(new Date(dt + Number(interval)*(k-dataIndex+1) * 60 * 1000));
			niheLineData[k][0]=t1;
		}
		myChart.setOption({
			        series: [{
			            id: 'tsqx',
			            name:'退水曲线',
			            type: 'line',
						smooth:true,
						smoothMonotone:'none',
		                "lineStyle": {
		                "normal":
		                {
		                "width": 1.5
		                }
		                },
			            data: niheLineData
			        }],
		});
	}
}
function niheRightByQiedian(dataIndex,oldLineData,niheLineData){
	var result=new Array();
	if(oldLineData!=null && oldLineData.length>0){
		var position=-1;
		//查询拟合曲线第一个点所在的位置
		for(var i=0;i<oldLineData.length;i++){
			if(oldLineData[i][0]<niheLineData[dataIndex][0]){
				if(i<oldLineData.length-1){
					var t1=new Date(oldLineData[i][0]);
					var t2=new Date(oldLineData[i+1][0]);
					var t=new Date(niheLineData[dataIndex][0]);
					if(t.getTime()-t1.getTime()>t2.getTime()-t.getTime()){
						result.push(oldLineData[i]);
					}
				}else{
					result.push(oldLineData[i]);
				}
			}
		}
		if(result!=null && result.length>0){
			var dt=new Date(result[result.length-1][0]).getTime();
			if(dataIndex>0){
				for(var j=dataIndex-1;j>-1;j--){
					var t1 = formatDateTime(new Date(dt - (Number(interval)*(dataIndex-1-j)) * 60 * 1000));
					niheLineData[j][0]=t1;
				}
			}
			for(var k=dataIndex;k<niheLineData.length;k++){
				var t1 = formatDateTime(new Date(dt + Number(interval)*(k-dataIndex+1) * 60 * 1000));
				niheLineData[k][0]=t1;
				result.push(niheLineData[k]);
				effectScatterParam.right=niheLineData[k];
			}
			myChart.setOption({
				        series: [{
				            id: 'tsqx',
				            name:'退水曲线',
				            type: 'line',
							smooth:true,
							smoothMonotone:'none',
			                "lineStyle": {
			                color:"#FFDC35",
			                "normal":
			                {
			                "width": 1.5
			                }
			                },
				            data: niheLineData
				        }],
			});
		}
	}
	return result;
}
function niheLeftByQiedian(dataIndex,oldLineData,niheLineData){
	var result=new Array();
	if(oldLineData!=null && oldLineData.length>0){
		var position=-1;
		//查询拟合曲线第一个点所在的位置
		for(var i=0;i<oldLineData.length;i++){
			if(oldLineData[i][0]>niheLineData[dataIndex][0]){
				if(i<oldLineData.length-1){
					result.push(oldLineData[i]);
				}
			}
		}
		if(result!=null && result.length>0){
			var dt=new Date(result[0][0]).getTime();
			if(dataIndex>0){
				for(var j=dataIndex-1;j>-1;j--){
					var t1 = formatDateTime(new Date(dt - (Number(interval)*(dataIndex-1-j)) * 60 * 1000));
					niheLineData[j][0]=t1;
				}
			}
			for(var k=dataIndex;k<niheLineData.length;k++){
				var t1 = formatDateTime(new Date(dt + Number(interval)*(k-dataIndex+1) * 60 * 1000));
				niheLineData[k][0]=t1;
				result.unshift(niheLineData[k]);
				effectScatterParam.left=niheLineData[k];
			}
			myChart.setOption({
				        series: [{
				            id: 'tsqx',
				            name:'退水曲线',
				            type: 'line',
							smooth:true,
							smoothMonotone:'none',
			                "lineStyle": {
			                color:"#FFDC35",
			                "normal":
			                {
			                "width": 1.5
			                }
			                },
				            data: niheLineData
				        }],
			});
		}
	}
	return result;
}
//已知切点的拟合运算
function niheyunsuanByQiedian(dataIndex,oldLineData,niheLineData){
	var result=new Array();
	if(niheResult==null || niheResult.length<1){
		niheResult=oldLineData;
	}
	if(operFlag=="right"){
		result=niheRightByQiedian(dataIndex,niheResult,niheLineData);
	}else if(operFlag=="left"){
		result=niheLeftByQiedian(dataIndex,niheResult,niheLineData);
	}
	return result;
}
function okDrag(){
	niheResult=lineData2;
	undragEvent();
}
function hideTooltip(dataIndex) {
    myChart.dispatchAction({
        type: 'hideTip'
    });
    lineData2 = [];
	lineData2=niheyunsuanByQiedian(dataIndex,lineData,newData);
	selectResult=lineData2;//选取提交的线条数据
		var options = myChart.getOption();
		var legend = options.legend[0];
		var odata = legend.data;
		var isexist = false;
		for (var i = 0; i < odata.length; i++) {
			if (odata[i] == "拟合曲线") {
				isexist = true;
				break;
			}
		}
		if (!isexist) {
			odata.push("拟合曲线");
		}
		myChart.setOption({
        series: [{
            id: 'nihequxian',
            name:'拟合曲线',
            type: 'line',
			smooth:true,
			smoothMonotone:'none',
			"lineStyle": {
		    	color:"#FFDC35",
		        "normal":{
		                "width": 1.5
		         }
		    },
            data: lineData2
        }],
    });
    //鼠标移出当前节点重新计算隐藏可拖拽节点的坐标  避免其余坐标不变导致其余节点不可继续拖拽的问题
    myChart.setOption({
        graphic: echarts.util.map(newData, function (item, dataIndex) {
            return {
                position: myChart.convertToPixel('grid', item)
            };
        })
    });
}
function onPointDragging(dataIndex, dx, dy) {
	//debugger;
	newData[dataIndex] = myChart.convertFromPixel('grid', this.position);
	//dataIndex  当前拖拽节点在数组中的索引   根据所拖拽的节点 对比拖拽前后数据的差值   做数据同步
    var newTime = new Date(newData[dataIndex][0]);
	var oldTime = new Date(oldData[dataIndex][0]);
	var newValue = parseInt(newData[dataIndex][1]);
	var oldValue = parseInt(oldData[dataIndex][1]);
	var diffValue = 0; //这个设置为0，不允许上下拖动，只允许左右拖动
	for(var i=dataIndex;i>-1;i--){
		var newTempTime = newTime.getTime() - (dataIndex-i)*interval*60*1000;
		var newTempValue = parseInt(oldData[i][1]) + diffValue;
		newData[i][0] = getFormatDate(new Date(newTempTime));
		newData[i][1] = newTempValue;
		oldData[i][0] = getFormatDate(new Date(newTempTime));
		oldData[i][1] = newTempValue;
	}
	for(var i = dataIndex+1 ; i < newData.length ; i++){
		var newTempTime = newTime.getTime() + (i-dataIndex)*interval*60*1000;
		var newTempValue = parseInt(oldData[i][1]) + diffValue;
		newData[i][0] = getFormatDate(new Date(newTempTime));
		newData[i][1] = newTempValue;
		oldData[i][0] = getFormatDate(new Date(newTempTime));
		oldData[i][1] = newTempValue;
	}
	//   更新echart  形成线拖拽的效果
	myChart.setOption({
        series: [{
            id: 'tsqx',
            name:"退水曲线",
            type: 'line',
			smooth:true,
			smoothMonotone:'none',
		    "lineStyle": {
		    	color:"#6666FF",
		        "normal":{
		                "width": 1.5
		         }
		    },
            data: newData
        }],
    });
}
function getLegend_data(data) {
    var temp = new Array(data.length);
    for(var i = 0 ; i < data.length ; i < i++){
        temp[i] = data[i].NAME;
    }
    return temp;
}
//eChart宽高自适应
var resizeWorldMapContainer = function () {
	document.getElementById('hszlb_step3_chart').style.width = document.getElementById('hszlb_step3_div').innerWidth+'px';
	document.getElementById('hszlb_step3_chart').style.height = document.getElementById('hszlb_step3_div').innerHeight+'px';
	document.getElementById('tsqx_step3_chart').style.width = document.getElementById('tsqx_div').innerWidth+'px';
	document.getElementById('tsqx_step3_chart').style.height = document.getElementById('tsqx_div').innerHeight+'px';
};

resizeWorldMapContainer();
window.onresize = function () {
	$(".layui-border-box").width($("#jls_step3_div").width() - 2);
    //重置容器高宽
    resizeWorldMapContainer();
    myChart.resize();
    myChart2.resize();
};




//格式化时间
function getFormatDate(date) {
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if(month >= 1 && month <= 9){
        month = "0" + month;
    }
    if(strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var hours="";
    var minut="";
    var seconds="";
    if(date.getHours()<10){
        hours="0"+date.getHours();
    }else{
        hours=date.getHours();
    }

    if(date.getMinutes()<10){
        minut="0"+date.getMinutes();
    }else{
        minut=date.getMinutes();
    }

    if(date.getSeconds()<10){
        seconds="0"+date.getSeconds();
    }else{
        seconds=date.getSeconds();
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + hours + seperator2 + minut;
        /* + seperator2 + seconds; */
    //console.log(currentdate);
    return currentdate;
}


var formatDateTime = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	var h = date.getHours();
	h = h < 10 ? ('0' + h) : h;
	var minute = date.getMinutes();
	minute = minute < 10 ? ('0' + minute) : minute;
	var second = date.getSeconds();
	second = second < 10 ? ('0' + second) : second;
	return y + '-' + m + '-' + d + ' ' + h + ':' + minute; // 返回时：分
};
function formatDateTimeKey(time){
	time=time.replace(/-/g, "");
	time=time.replace(/:/g,"");
	time=time.replace(/\s+/g, "");
	return time;
}
</script>
<!-- 不要改变以下引用顺序 -->