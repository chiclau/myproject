/**
 * Created by Gerhard on 2017/10/9.
 */
// 创建一个三维柱状统计专题图图层
graphBar3DLayer = new SuperMap.Layer.Graph("graphBar3DLayer", "Bar3D");

// 指定用于专题图制作的属性字段
graphBar3DLayer.themeFields = ["Shape_Leng", "Shape_Area" ];
// 压盖处理权重
// graphBar3DLayer.overlayWeightField = "KIND";

// 注册 click 事件
graphBar3DLayer.on("click", moveToCapital);

// 配置图表参数
graphBar3DLayer.chartsSetting = { // 配置图表参数
	// width，height，codomain 分别表示图表宽、高、数据值域；此三项参数为必设参数
	width : 100,
	height : 80,
	codomain : themeParams.codomain, // 允许图表展示的值域范围，此范围外的数据将不制作图表
	YOffset : -50, // 向上偏移 50 像素
	XOffset : 40, // 向右偏移 90 像素
	// 3d 柱条正面样式（3d 柱条的侧面和顶面会以 3d 柱条正面样式为默认样式）
	barFaceStyle : {
		stroke : true
	},
	// 按字段设置 3d 柱条正面样式
	barFaceStyleByFields : [ {
		fillColor : "#FFB980"
	}, {
		fillColor : "#5AB1EF"
	}, {
		fillColor : "#B6A2DE"
	}, {
		fillColor : "#2EC7C9"
	}, {
		fillColor : "#D87A80"
	} ],
	// 3d 柱条正面 hover 样式（3d 柱条的侧面和顶面 hover 会以 3d 柱条正面 hover 样式为默认 hover 样式）
	barFaceHoverStyle : {
		stroke : true,
		strokeWidth : 1,
		strokeColor : "#ffff00"
	},
	xShapeBlank : [ 5, 5 ], // 水平方向上的空白间距参数
	// axisYTick: 2, // y 轴刻度数量
	useXReferenceLine : false, // 使用参考线
	useAxis : false,
	// xReferenceLineStyle: {strokeColor: "#008acd", strokeOpacity: 0.4}, //
	// 参考线样式
	// axisYLabels: [ "60", "40", "20", "0"], // y 轴标签
	// axisXLabels: ["KIND", "Shape_Leng", "Shape_Area"], // x 轴标签
	useBackground : false,
	leaderLineDisplayed : true,
	flowEnabled : true
// backgroundStyle: { // 背景样式
// fillColor: "#d1eeee",
// shadowBlur: 12,
// shadowColor: "#d1eeee"
// },
// backgroundRadius: [5, 5, 5, 5] // 背景框圆角参数
};

// 获取 feature 数据, 专题图的数据必须是 SuperMap.Feature.Vector
function addGraphBar3DLayer() {
	removeAllThemeLayer();
    isCilckedAddGraphBar3DLayer = true;
	map.events.un({
		"click" : mapClickGetClickPosition
	});
	map.events.un({
		"click" : mapClickGetClickPosition
	});
	map.addLayer(graphBar3DLayer);
	// 调整图层顺序
	map.setLayerIndex(graphBar3DLayer, map.layers.length - 1);
	var queryParam, queryBySQLParams, queryBySQLService;
	queryParam = new SuperMap.REST.FilterParameter({
		name : themeParams.queryParamNmae,
        attributeFilter: themeParams.attributeFilter
    });
	queryBySQLParams = new SuperMap.REST.QueryBySQLParameters({
		queryParams : [ queryParam ],
        queryOption: SuperMap.REST.QueryOption.ATTRIBUTE
        // geometry: point
    });
	queryBySQLService = new SuperMap.REST.QueryBySQLService(themeParams.url, {
		eventListeners : {
			"processCompleted" : processCompletedGraphBar3D,
			"processFailed" : processFailedGraphBar3D
		}
	});
	queryBySQLService.processAsync(queryBySQLParams);
}
function processCompletedGraphBar3D(queryEventArgs) {
	map.events.un({
		"click" : mapClickGetClickPosition
	});
	map.events.un({
		"click" : mapClickGetClickPosition
	});

	var i, result = queryEventArgs.result;
	if (result && result.recordsets) {
		for (i = 0; i < result.recordsets.length; i++) {
			if (result.recordsets[i].features) {
                // 省居民消费水平（单位：元）信息
                var features = [];
                var feainfo = result.recordsets[i].features;
                for(j=0;j<feainfo.length;j++){
                var geo = new SuperMap.Geometry.Point(feainfo[j].attributes.CenterLon, feainfo[j].attributes.CenterLat);
                var attrs = { };
                attrs.Name = feainfo[j].attributes.Name;
                attrs.Shape_Leng = feainfo[j].attributes.Shape_Leng;
                attrs.Shape_Area = feainfo[j].attributes.Shape_Area;

                var fea = new SuperMap.Feature.Vector(geo, attrs);
                features.push(fea);
                }
                // 向专题图层添加用于制作专题图的feature数据
                graphBar3DLayer.addFeatures(features);
            }
		}
	}
}

function processFailedGraphBar3D(e) {
	alert(e.error.errorMsg);
}

// 定位到首都城市
function moveToCapital(e) {
	if (e.target && e.target.refDataID) {
		closeGraphBar3DInfoWin();

		// 获取图形对应的 feature
		var fea = graphBar3DLayer.getFeatureById(e.target.refDataID);
		// feature 的 bounds 中心
		var geoCenter = fea.geometry.getBounds().getCenterLonLat();
		// 定位到 feature 的 bounds 中心
		var lonLat = new SuperMap.LonLat(geoCenter.lon, geoCenter.lat);
		// map.setCenter(lonLat, 4);

		var info = e.target.dataInfo;
		// 弹窗内容
		var contentHTML = "<div style='color: #000; background-color: #fff'>";
		contentHTML += "<strong><i>" + fea.attributes.Name + "</i></strong>";
		contentHTML += "<hr style='margin: 3px'>";
		switch (info.field) {
		case "KIND":
			contentHTML += "KIND:<strong>" + fea.attributes.KIND + "</strong>";
			break;
		case "Shape_Leng":
			contentHTML += "Shape_Leng:<strong>" + fea.attributes.Shape_Leng
					+ "</strong>";
			break;
		case "Shape_Area":
			contentHTML += "Shape_Area:<strong>" + fea.attributes.Shape_Area
					+ "</strong>";
			break;
		default:
			contentHTML += "No Data";
		}
		contentHTML += "</div>";

		infowin = new SuperMap.Popup("infowin", lonLat, new SuperMap.Size(200,
				80), contentHTML, true, false, null);
		infowin.setBackgroundColor("#fff");
		infowin.setOpacity(0.8);
		if (infowin)
			map.removePopup(infowin);
		map.addPopup(infowin);
		infowin.updateSize();

	}
}

function clearGraphBar3DLayer() {
	// map.removeLayer(graphBar3DLayer,true);
	// map.events.on({
	// 	"click" : mapClickGetClickPosition
	// });
	graphBar3DLayer.clear();
	closeGraphBar3DInfoWin();
}

// 移除地图弹窗
function closeGraphBar3DInfoWin() {
	if (infowin) {
		try {
			map.removePopup(infowin);
		} catch (e) {
			alert(e.message);
		}
	}
}
