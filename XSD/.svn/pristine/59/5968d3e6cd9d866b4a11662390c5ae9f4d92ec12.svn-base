//有地图显示的菜单点击事件
function menuMapClickEvent(menuParams){
	$("#nomap-frame-content").hide();
	$("#map-frame-content").show();
	clearDianzhanThemeLayer();//清空地图上的电站
	clearVectorLayerStatisticLabel();//清空统计信息
	mapSearchParam.tabLyClickEvent=menuParams.tabLyClickEvent?menuParams.tabLyClickEvent:null;
	mapSearchParam.tabSfClickEvent=menuParams.tabSfClickEvent?menuParams.tabSfClickEvent:null;
	mapSearchParam.mapLySfClientEvent=menuParams.mapLySfClientEvent?menuParams.mapLySfClientEvent:null;
	mapSearchParam.mapPointEvent=menuParams.mapPointEvent?menuParams.mapPointEvent:null;
	var tabType = "",lydm = "", lyname = "", sfdm = "", sfname = "";
	if (mapSearchParam.cxfw == "ly") {
		tabType = "ly";
		lydm = mapSearchParam.lydm;
		lyname = mapSearchParam.lyname;
	} else if (mapSearchParam.cxfw == "sf") {
		tabType = "sf";
		sfdm = mapSearchParam.sfdm;
		sfname = mapSearchParam.sfname;
	}
	if (tabType == "ly") {
		mapSearchParam.tabLyClickEvent({
			map : map,
			flag : "lysf",
			lydm:lydm,
			lyname:lyname
		});
	} else if (tabType == "sf") {
		mapSearchParam.tabSfClickEvent({
			map : map,
			flag : "lysf",
			sfdm:sfdm,
			sfname:sfname
		});
	}
}
//点击菜单事件，每个菜单一个点击事件
function menu_cezhanchaxun(){
	menuMapClickEvent({
		tabLyClickEvent:function(options){
			loadTongjiCezhanDataByLy(options);
			loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_ly.jsp");
			loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_ly_right.jsp");
		},//点击流域tab时触发的事件
		tabSfClickEvent:function(options){
			loadTongjiCezhanDataBySf(options);
			loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf.jsp");
			loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf_right.jsp");
		},//点击省份tab时触发的事件
		mapLySfClientEvent:function(options){
			loadCezhanPointByLyOrSf(options);
			if(mapSearchParam.cxfw=="ly"){
				loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_ly.jsp?lydm="+options.lydm+"&lyname="+options.lyname);
				loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_ly_right.jsp?lydm="+options.lydm+"&lyname="+options.lyname);
			}else if(mapSearchParam.cxfw=="sf"){
				loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_sf.jsp?sfdm="+options.sfdm+"&sfname="+options.sfname);
				loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_sf_right.jsp?sfdm="+options.sfdm+"&sfname="+options.sfname);
			}
		},//点击某个流域或省份时触发事件
		mapPointEvent:function(dzinfo){
			var stcd = dzinfo.STCD;
	    	var stname = dzinfo.ENNM;
		}//点击某个站点时触发的事件
	});
}