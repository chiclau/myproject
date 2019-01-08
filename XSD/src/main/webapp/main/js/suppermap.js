//初始化加载默认地图数据
function initDefaultMapData(){
	try{
		$("#mapdiv").css("height",$("#mapdiv_container").height());
		mapSearchParam.cxfw="ly";//默认选中流域tab
		init(function(options){
			var zoom=map.getZoom();
			mapSearchParam.zoom=zoom;
			menu_cezhanchaxun();
			/*},{
			width:$(window).width(),
			height:$(window).height(),
			mapZoomendEvent:function(map){
				var zoom=map.getZoom();
				mapSearchParam.zoom=zoom;
				if(zoom>mapSearchParam._defautlZoom){
					if(zoom>mapSearchParam._overLay){
						//避让标识
						themeLayerDianzhanPoint.isOverLay = false;
					}else{
						//避让标识
						themeLayerDianzhanPoint.isOverLay = true;
					}
					setVectorLayerStatisticLabel(0);
					setThemeLayerDianzhanPoint(1);
					if(mapSearchParam.mapLySfClientEvent){
						mapSearchParam.mapLySfClientEvent({map:map,flag:"lysf",lydm:mapSearchParam.lydm,lyname:mapSearchParam.lyname,sfdm:mapSearchParam.sfdm,sfname:mapSearchParam.sfname});
					}
				}else{
					setVectorLayerStatisticLabel(1);
					setThemeLayerDianzhanPoint(0);
				}
			},
			mapEareaClickEvent:function(obj,type){
				mapSearchParam.cxfw=type;
				if(obj && obj.data){
					setVectorLayerStatisticLabel(0);
					setThemeLayerDianzhanPoint(1);
					if(type=="ly"){
						var lydm=obj.data.RVCD;
						var lyname=obj.data.LYNAME;
						mapSearchParam.lydm=lydm;
						mapSearchParam.lyname=lyname;
					}else if(type=="sf"){
						var sfdm=obj.data.ADDVCD;
						var sfname=obj.data.NAME;
						mapSearchParam.sfdm=sfdm;
						mapSearchParam.sfname=sfname;
					}
					if(mapSearchParam.mapLySfClientEvent){
						mapSearchParam.mapLySfClientEvent({map:map,flag:"lysf",lydm:mapSearchParam.lydm,lyname:mapSearchParam.lyname,sfdm:mapSearchParam.sfdm,sfname:mapSearchParam.sfname});
					}
				}
			},
			mapPointClickEvent:function(dzinfo){
				var stcd = dzinfo.STCD;
				var stname = dzinfo.ENNM;
				if(mapSearchParam.mapLySfClientEvent){
					mapSearchParam.mapLySfClientEvent({STCD:stcd,STNM:stname});
				}
			}*/
		});
	}catch(e){
		console.log("加载地图出错:"+e);
	}
}
/*//初始化地图左侧tab切换点击事件
function initMapTabClickEvent(){
  $("#map_ly").on('click',function(e){
  	mapSearchParam.cxfw="ly";
  	mapLyTabClickEvent();
  });
  //切换省份地图
  $("#map_sf").on('click',function(e){
	  alert(1)
  	mapSearchParam.cxfw="sf";
  	mapSfTabClickEvent();
  });
}*/
//地图上流域切换按钮点击事件
function mapLyTabClickEvent(){
	$("#map_sf").removeClass("active");
  	$("#map_ly").addClass("active");
  	mapExtent($(window).width(),$(window).height());//还原到原始
  	mapSearchParam.lydm="";
  	mapSearchParam.lyname="";
  	changeBaseMapBaseOnType('Liuyu',function(map){
  		//点击流域tab时，加载数据函数
  		if(mapSearchParam.tabLyClickEvent){
  			mapSearchParam.tabLyClickEvent({map:map,flag:"lysf"});
  		}
	});
}
//地图上行政切换按钮点击事件
function mapSfTabClickEvent(){
	$("#map_ly").removeClass("active");
  	$("#map_sf").addClass("active");
  	mapExtent($(window).width(),$(window).height());
  	mapSearchParam.sfdm="";
  	mapSearchParam.sfname="";
  	//点击行政区域tab时，加载数据函数
  	changeBaseMapBaseOnType('Xingzhengqu',function(map){
		if(mapSearchParam.tabSfClickEvent){
	  		mapSearchParam.tabSfClickEvent({map:map,flag:"lysf"});
	  	}
	});
}