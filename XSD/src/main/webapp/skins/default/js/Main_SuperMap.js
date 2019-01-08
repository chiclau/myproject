/**
 * Created by HTKM on 2017/9/25.
 * 显示电站的时候，电站可以交互，此时把“统计百分比”图层影藏掉
 */
var map, ovm;
var vectorLayerBorder;
//var mgqRestLayer;//
var centerLon=103.07741974559,centerLat=37.691298606445;
var clickDianzhanPointFlag = false;
//新建一个策略
var strategy, vectorLayerStatisticLabel, themeLayerDianzhanPoint;
var themeLayer,
    infowin,
    dianzhanInfowin, //电站的气泡
    labelInfowin,  //统计文字标签气泡
    infowinPosition;
// var hnpoiUrl = "http://222.247.40.204:8091/iserver/services/map-HNPOI/rest/maps/HNPOI";
var yhpoiUrl = "http://localhost:8091/iserver/services/map-yuhuawks/rest/maps/yuhuamap";

function init(callback,params) {
    //根据用户权限传递地图中心点，另外还要加权限控制，比如具有湖南省权限的用户，
    //在系统加载后，地图默认缩放到湖南省地图，显示湖南省的电站（此时不显示其他省份的电站）。
    vectorLayerBorder = new SuperMap.Layer.Vector("vectorlayerBorder");
    strategy = new SuperMap.Strategy.GeoText();
    //新建一个敏感区图层
    //mgqRestLayer=new SuperMap.Layer.TiledDynamicRESTLayer("敏感区",mgqUrl,{transparent:true,cacheEnabled:true},{resolutions:restLayerResolutions});
    
//新建一个标签专题图层
    vectorLayerStatisticLabel = new SuperMap.Layer.Vector("统计百分比", {strategies: [strategy]});
    // VectorLayerStatisticLabel是一个关键字
//设置标签的样式
    strategy.style = {
        fontColor: "#FF7F00",
        fontWeight: "bolder",
        fontSize: "14px",
        fill: true,
        fillColor: "#FFFFFF",
        fillOpacity: 1,
        stroke: true,
        strokeColor: "#8B7B8B"
    };

//用于标签分组的属性字段名称
    strategy.groupField = "percent";
//标签分组数组,根据每个城市的空气指数来设置分段。
    strategy.styleGroups = [
        {
            start: 0,
            end: 60,
            style: {
                fontColor: "#FFF",
                fillColor: "#FF0000",
                fontWeight: "bolder",
                fontSize: "17px"
            }
        },
        {
            start: 60,
            end: 75,
            style: {
                fontColor: "#FFF",
                fillColor: "#EAC100",
                fontWeight: "bolder",
                fontSize: "17px"
            }
        },
        {
            start: 75,
            end: 90,
            style: {
                fontColor: "#FFF",
                fillColor: "#0000E3",
                fontWeight: "bolder",
                fontSize: "19px"
            }
        },
        {
            start: 90,
            end: 100,
            style: {
                fontColor: "#FFF",
                fillColor: "#007500",
                fontWeight: "bolder",
                fontSize: "22px"
            }
        }
    ];


    map = new SuperMap.Map("mapdiv", {
        controls: [
            new SuperMap.Control.ScaleLine({id: "scaleLineControl"}),
            new SuperMap.Control.KeyboardDefaults({autoActivate: false}),
            new SuperMap.Control.LayerSwitcher(),
            new SuperMap.Control.Navigation({
                id: "navigationId",
                dragPanOptions: {
                    enableKinetic: true
                }
            })],//测线测面，标点，标线之类的都得在这里面写一些，否则功能无法启动
        // map.controls[2].maximizeControl();可以一直显示图层选择控件
        projection: "EPSG:4326",
        numZoomLevels: 21,  //貌似并没有什么用
        maxResolution: 3000,
        allOverlays: true

    });
    ovm = new SuperMap.Control.OverviewMap({
        layers: [layer1.clone(), layer2.clone(), layer3.clone(), layer4.clone()],
        mapOptions: {allOverlays: true},
        maximized: false
    });

    map.addControl(ovm);
    map.events.on({"zoomend": function(obj){
    	if(params.mapZoomendEvent){
    		params.mapZoomendEvent(this);
    	}
    }});
    map.events.on({"mousemove": mapMouseMove});
//     map.events.on({"mousedown":mapClickGetClickPosition});
    console.log("before map loded!");
    map.events.on({"click": function(e){
    	if(mapSearchParam.menuFlag!="jcxx_kjcx"){
    		mapClickGetClickPosition(e,params);
    	}
    }});
    console.log("after map loded!");

    // 注册 click 事件
    //graphBar3DLayer.on("click", moveToCapital);

    // 创建一个柱状图（Bar）统计专题图图层
    themeLayerDianzhanPoint = new SuperMap.Layer.Graph("电站结果专题图", "Bar");
    //避让标识
    themeLayerDianzhanPoint.isOverLay = true;
    // 指定用于专题图制作的属性字段
    themeLayerDianzhanPoint.themeFields = ["CON2009"];

    // 配置图表参数
    themeLayerDianzhanPoint.chartsSetting = {
        // width，height，codomain 分别表示图表宽、高、数据值域；此三项参数为必设参数
        width: 50,
        height: 100,
        codomain: [0, 40000],       // 允许图表展示的值域范围，此范围外的数据将不制作图表
        barStyle: {fillOpacity: 0.5},       // 柱状图中柱条的（表示字段值的图形）样式
        barHoverStyle: {fillOpacity: 0},       //  柱条 hover 样式
        xShapeBlank: [0, 0, 0],   // 水平方向上的空白间距参数
        YOffset: -50,         // 向上偏移 50 像素
        useAxis: false,       // 不显示坐标轴
        useBackground: false       // 不显示背景框
    };
// 注册专题图 mousemove, mouseout 事件(注意：专题图图层对象自带 on 函数，没有 events 对象)
    themeLayerDianzhanPoint.on("mousemove", showDianzhanInfoWin);
    themeLayerDianzhanPoint.on("click", function(e){
    	if (e.target && e.target.refDataID && e.target.dataInfo) {
    		var fea = themeLayerDianzhanPoint.getFeatureById(e.target.refDataID);
    		var dzinfo=fea.attributes;
    		if(dzinfo){
    			clickDianzhanPointFlag=true;
    			if(params.mapPointClickEvent){
    	    		params.mapPointClickEvent(dzinfo,e);
    	    	}
    		}
    	}
    });
//    mgqRestLayer.events.on({
//        "layerInitialized": function (layerparam) {
//            map.addLayer(layerparam);
//        }
//    });
    themeLayerDianzhanPoint.on("mouseout", closeDianzhanInfoWin);
    themeLayerDianzhanPoint.setOpacity(0.9);
    // 注册地图 mousemove，用于获取当前鼠标在地图中的像素位置
    map.events.on({
        "mousemove": function (e) {
            infowinPosition = e.xy.clone();
            // 偏移
            infowinPosition.x += 0;  //40
            infowinPosition.y -= 0;  //25
        }
    });
    // drawPoint = new SuperMap.Control.DrawFeature(vectorLayerBorder, SuperMap.Handler.Point);
    // drawPoint.events.on({"featureadded": drawCompleted});
    addLayers(callback,params);

}
function refreshMap(){
	map.updateSize();
}
function setVectorLayerBorderVisibility(flag){
	vectorLayerBorder.setVisibility(flag);
}
function setVectorLayerStatisticLabel(flag){
	vectorLayerStatisticLabel.setVisibility(flag);
}
function setThemeLayerDianzhanPoint(flag){
	themeLayerDianzhanPoint.setVisibility(flag);
}
function generSuperMapPointFeature(fetureColection){
	var format = new SuperMap.Format.GeoJSON();
    var dataReadFeature = format.read(fetureColection, false);
    return dataReadFeature;
}
function addSuperMapPointFeature(fetureColection){
	var format = new SuperMap.Format.GeoJSON();
    var dataReadFeature = format.read(fetureColection, false);
//    console.log(dataReadFeature)
//    for (var i = 0, len = dataReadFeature.length; i < len; i++) {
//        // 省居民消费水平（单位：元）信息
//    	dataReadFeature[i].attributes["CON2009"] = 9999;
//    }
    vectorLayerStatisticLabel.setVisibility(0);
    //显示点专题图层
    themeLayerDianzhanPoint.setVisibility(1);
//    clearDianzhanThemeLayer();
    themeLayerDianzhanPoint.addFeatures(dataReadFeature);
    map.setLayerIndex(themeLayerDianzhanPoint, map.layers.length - 1);
    return dataReadFeature;
}
//加载原有构造过的feature
function addOldFeature(dataReadFeature){
	vectorLayerStatisticLabel.setVisibility(0);
    //显示点专题图层
    themeLayerDianzhanPoint.setVisibility(1);
    themeLayerDianzhanPoint.addFeatures(dataReadFeature);
    map.setLayerIndex(themeLayerDianzhanPoint, map.layers.length - 1);
}
//查询到电站的结果，地图上定位到该电站
function locationPosition(lon,lat,zoom){
	map.setCenter(new SuperMap.LonLat(lon, lat), zoom);
}

function addLayers(callback,params) {
    map.addLayers([layer1, layer2, layer3, layer4, vectorLayerBorder, vectorLayerStatisticLabel,themeLayerDianzhanPoint]);
    layer1.setVisibility(false);
    layer2.setVisibility(false);
    if(params.width && params.width>1366 && params.height && params.height>768){
    	map.setCenter(new SuperMap.LonLat(centerLon, centerLat), 1);
    }else{
    	map.setCenter(new SuperMap.LonLat(centerLon, centerLat), 0);
    }
    addRestLayer(callback,params);

    //兼容PC与移动端
    var broz = SuperMap.Util.getBrowser();
    var callbacks = {};
    if (broz.device === 'android' || broz.device === 'apple') {
        callbacks = {
            click: onVectorLayerFeatureClick,
            clickout: onVectorLayerFeatureUnselect
        };
    }
    else {
        callbacks = {
            over: onVectorLayerFeatureOver,
            click: onVectorLayerFeatureClick,
            out: onVectorLayerFeatureUnselect
        };
    }
    var selectFeature = new SuperMap.Control.SelectFeature(vectorLayerStatisticLabel, {
        callbacks: callbacks,
        hover: false,
        repeat: true
    });
    map.addControl(selectFeature);
    selectFeature.activate();
}

var restLayerResolutions = [
    0.087890625,
    0.0439453125,
    0.02197265625,
    0.010986328125,
    0.0054931640625,
    0.00274658203125,
    0.001373291015625,
    0.0006866455078125,
    0.00034332275390625,
    0.000171661376953125,
    0.0000858306884765625,
    0.00004291534423828125,
    0.000021457672119140625,
    0.0000107288360595703125,
    0.00000536441802978515625,
    0.000002682209014892578125
];
var liuyuRestlayer;

function addRestLayer(callback,params) {
    liuyuRestlayer = new SuperMap.Layer.TiledDynamicRESTLayer("流域底图", liuyuUrl,
        {transparent: true, cacheEnabled: true}, {resolutions: restLayerResolutions});
    liuyuRestlayer.events.on({
        "layerInitialized": function (layerparam) {
            map.addLayer(layerparam);
            addXingzhengquRestLayer(callback,params);
        }
    });
    liuyuRestlayer.setVisibility(1);
    liuyuRestlayer.buffer = 2;
}

//移除所有专题图图层
function removeAllThemeLayer() {
    vectorLayerStatisticLabel.setVisibility(0);
    removeThemeGraph();
    clearGraphPieLayer();   //单个不会触发
    clearGraphBarLayer();  //单个不会触发
    clearGraphLineLayer();//单个不会触发
    clearGraphBar3DLayer();
    clearDianzhanThemeLayer();
    vectorLayerBorder.removeAllFeatures();
    isCilckedAddGraphBar3DLayer = false;
}


var xingzhengquRestLayer;

function addXingzhengquRestLayer(callback,params) {
    xingzhengquRestLayer = new SuperMap.Layer.TiledDynamicRESTLayer("行政区底图", xingzhengquUrl,
        {transparent: true, cacheEnabled: true}, {resolutions: restLayerResolutions});
    xingzhengquRestLayer.events.on({
        "layerInitialized": function (layerparam) {
            map.addLayer(layerparam);
            mapZoomend();
            if(callback){
            	params.map=map;
            	callback(params);
            }
        }
    });
    xingzhengquRestLayer.setVisibility(0);
//    if (xingzhengquRestLayer) xingzhengquRestLayer.setVisibility(0);
    xingzhengquRestLayer.buffer = 2;
}

function mapMouseMove(e) {
//    var px = new SuperMap.Pixel(e.offsetX, e.offsetY);
//    var lonlat = map.getLonLatFromPixel(px);
//    var point = new SuperMap.Geometry.Point(lonlat.lon, lonlat.lat);
}
function mapMgqClickGetClickPosition(e,callback){
	var px = new SuperMap.Pixel(e.offsetX, e.offsetY);
    var lonlat = map.getLonLatFromPixel(px);
    var point = new SuperMap.Geometry.Point(lonlat.lon, lonlat.lat);
    QueryByPoint(point,callback);
}
function mapClickGetClickPosition(e,params) {
    var px = new SuperMap.Pixel(e.offsetX, e.offsetY);
    var lonlat = map.getLonLatFromPixel(px);
    var point = new SuperMap.Geometry.Point(lonlat.lon, lonlat.lat);
    
    if(mapSearchParam.menuFlag=="jcxx_mgq"){
    	var mapMgqClickEvent=params.mapMgqClickEvent;
    	QueryByPoint(point,mapMgqClickEvent);
    }
    
    if (curBaseMapType == "Liuyu") {
        if (globalliuyufeature != undefined || globalliuyufeature != null) {
            if (globalliuyufeature.geometry.intersects(point)) {
                return;
            }
        }
    } else if (curBaseMapType == "Xingzhengqu") {
        if (globalxingzhengqufeature != undefined || globalxingzhengqufeature != null) {
            if (globalxingzhengqufeature.geometry.intersects(point)) {
                return;
            }
        }
    }
    if(clickDianzhanPointFlag){
        clickDianzhanPointFlag = false;
        return;
    }
    var mapEareaClickEvent=params.mapEareaClickEvent;
    if (curBaseMapType == "Xingzhengqu") {
        vectorLayerBorder.removeAllFeatures();
        querySelectedXingzhengqu(point,"sf",mapEareaClickEvent);
    } else if (curBaseMapType == "Liuyu") {
        vectorLayerBorder.removeAllFeatures();
        querySelectedLiuyu(point,"ly",mapEareaClickEvent);
    }
}

//start 点击查询 敏感区 fxl
function QueryByPoint(point,callback) {
    var queryParam, queryByGeometryParameters, queryService;
    queryParam = new SuperMap.REST.FilterParameter({name: "mgq@hbDB#1"});
    queryByGeometryParameters = new SuperMap.REST.QueryByGeometryParameters({
        queryParams: [queryParam],
        geometry: point,
        spatialQueryMode: SuperMap.REST.SpatialQueryMode.INTERSECT
    });
    queryService = new SuperMap.REST.QueryByGeometryService(mgqUrl, {
        eventListeners: {
            "processCompleted": function(queryEventArgs){
            	var i, j, result = queryEventArgs.result;
                if (result && result.recordsets) {
                    for (i = 0, recordsets = result.recordsets, len = recordsets.length; i < len; i++) {
                        if (recordsets[i].features) {
                            for (j = 0; j < recordsets[i].features.length; j++) {
                                var feature = recordsets[i].features[j];
                                if(callback){
                                	callback(feature);
                                }
                            }
                        }
                    }
                }
            },
            "processFailed": processFailed
        }, isInTheSameDomain: true
    });
    queryService.processAsync(queryByGeometryParameters);
}

function processCompleted(queryEventArgs) {
    var i, j, result = queryEventArgs.result;
    if (result && result.recordsets) {
        for (i = 0, recordsets = result.recordsets, len = recordsets.length; i < len; i++) {
            if (recordsets[i].features) {
                for (j = 0; j < recordsets[i].features.length; j++) {
                    var feature = recordsets[i].features[j];
                    
                    alert(feature.attributes["MGQID"]);
                }
            }
        }
    }
}
function processFailed(e) {
    alert(e.error.errorMsg);
}
//end


function mapZoomend() {
    layer2.setVisibility(0);
    layer4.setVisibility(0);
    // map.controls[4].maximizeControl(); //可以一直显示图层选择控件
    var zoom = map.getZoom();
    //需要判断当前的地图类型
    map.setLayerIndex(vectorLayerStatisticLabel, map.layers.length - 1);
    if (curBaseMapType == "Xingzhengqu") {
        // //控制图层显隐
        // vectorLayerBorder.setVisibility(1);
        // vectorLayerDianzhan.setVisibility(1);
        // //控制图层显示顺序
        // map.setLayerIndex(vectorLayerBorder, map.layers.length - 1);
        // map.setLayerIndex(vectorLayerDianzhan, map.layers.length - 2);
        // map.setLayerIndex(themeLayerDianzhanPoint, map.layers.length - 3);
        // if (liuyuRestlayer) map.setLayerIndex(liuyuRestlayer, 4);
        // if (xingzhengquRestLayer) map.setLayerIndex(xingzhengquRestLayer, 5);
        if (isCilckedAddGraphBar3DLayer != false) {
            themeParams.url = xingzhengquUrl;
            if ( zoom < 3) {
                themeParams.queryParamNmae = "省界@0927#2";
                removeAllThemeLayer();
                addGraphBar3DLayer();

            } else if (zoom < 6) {
                themeParams.queryParamNmae = "市界@0927#2";
                removeAllThemeLayer();
                addGraphBar3DLayer();

            } else if (zoom < 14) {
                themeParams.queryParamNmae = "县界@0927";
                removeAllThemeLayer();
                addGraphBar3DLayer();
            }
        }
    } else if (curBaseMapType == "Liuyu") {
        themeParams.url = liuyuUrl;
        // //控制图层显隐
        // vectorLayerBorder.setVisibility(1);
        // vectorLayerDianzhan.setVisibility(1);
        // //控制图层显示顺序
        // if (liuyuRestlayer) map.setLayerIndex(liuyuRestlayer, 4);
        // if (xingzhengquRestLayer) map.setLayerIndex(xingzhengquRestLayer, 5);

        // map.setLayerIndex(vectorLayerDianzhan, map.layers.length - 2);
        // // map.setLayerIndex(vectorLayerBorder, map.layers.length - 1);
        // map.setLayerIndex(vectorLayerStatisticLabel, map.layers.length - 2);
        // map.setLayerIndex(vectorLayerBorder, map.layers.length - 3);
        // map.setLayerIndex(themeLayerDianzhanPoint, map.layers.length - 1);


    } else if (curBaseMapType == "Gongsi") {
        //控制图层
        vectorLayerBorder.setVisibility(0);

    }

}
function removeVectorLayerBorder(){
	vectorLayerBorder.removeAllFeatures();
}
function removeDianzhanResult() {
    clearDianzhanThemeLayer();
    vectorLayerBorder.removeAllFeatures();
}
function removeFeature(feature){
	themeLayerDianzhanPoint.removeFeatures(feature);
}
function mapZoomIn() {
    map.zoomIn();
}

function mapZoomOut() {
    map.zoomOut();
}

//start 加载敏感区图层  fxl
function addMgqLayer(call,params){
	map.setLayerIndex(mgqRestLayer, map.layers.length - 1);
	mgqRestLayer.setVisibility(1);
}
function hideMgqLayer(){
	mgqRestLayer.setVisibility(0);
}
function removeMgqLayer(call,params){
	mgqRestLayer.setVisibility(0);
}
//end

function mapExtent(width,height) {
	if(width && width>1366 && height && height>768){
    	map.setCenter(new SuperMap.LonLat(centerLon, centerLat), 1);
    }else{
    	map.setCenter(new SuperMap.LonLat(centerLon, centerLat), 0);
    }
}

var isSearchResultPanelDivShown = false;
$(document).ready(function() {
    $(".searchBtnDiv").click(function(){
        if(!isSearchResultPanelDivShown){
            $("#searchResultPanelDiv").show();
            isSearchResultPanelDivShown = true;
        }else{
            $("#searchResultPanelDiv").hide();
            isSearchResultPanelDivShown = false;
        }
    })
})