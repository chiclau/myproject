/**
 * Created by Gerhard on 2017/10/9.
 */
// 创建一个柱状图（Bar）统计专题图图层
var graphLineLayer = new SuperMap.Layer.Graph("graphLineLayer", "Line");

// 指定用于专题图制作的属性字段
graphLineLayer.themeFields = ["CON2009", "CON2010", "CON2011", "CON2012", "CON2013"];

// 配置图表参数
graphLineLayer.chartsSetting = {
    // width，height，codomain 分别表示图表宽、高、数据值域；此三项参数为必设参数
    width: 240,
    height: 100,
    codomain: [0, 40000],       // 允许图表展示的值域范围，此范围外的数据将不制作图表
    barStyle: {fillOpacity: 0.7},       // 柱状图中柱条的（表示字段值的图形）样式
    barHoverStyle: {fillOpacity: 1},       //  柱条 hover 样式
    xShapeBlank: [10, 10, 10],      // 水平方向上的空白间距参数
    axisYTick: 4,         // y 轴刻度数量
    axisYLabels: ["4万", "3万", "2万", "1万", "0"],         // y 轴标签内容
    axisXLabels: ["09年", "10年", "11年", "12年", "13年"],         // x 轴标签内容
    backgroundStyle: {fillColor: "#CCE8CF"},        // 背景样式
    backgroundRadius: [5, 5, 5, 5],        // 背景框圆角参数
    //阴影开关 默认是打开
    showShadow: true,
    //阴影样式
    barShadowStyle: {shadowBlur: 8, shadowOffsetX: 2, shadowOffsetY: 2, shadowColor: "rgba(100,100,100,0.8)"},
    //按字段设置柱条样式[渐变开始颜色,渐变终止颜色]  与 graphBar3DLayer.themeFields 中的字段一一对应）
    barLinearGradient: [["#00FF00", "#00CD00"], ["#00CCFF", "#5E87A2"], ["#00FF66", "#669985"], ["#CCFF00", "#94A25E"], ["#FF9900", "#A2945E"]]
};

// 注册专题图 mousemove, mouseout 事件(注意：专题图图层对象自带 on 函数，没有 events 对象)
graphLineLayer.on("mousemove", showLineInfoWin);
graphLineLayer.on("mouseout", closeLineInfoWin);

graphLineLayer.setOpacity(0.9);

// 构建 feature 数据, 专题图的数据必须是 SuperMap.Feature.Vector
function addGraphLineLayer() {
    removeAllThemeLayer();
    map.events.un({
        "click" : mapClickGetClickPosition
    });
    map.events.un({
        "click" : mapClickGetClickPosition
    });
    var features = [];
    for (var i = 0, len = chinaConsumptionLevel.length; i < len; i++) {
        // 省居民消费水平（单位：元）信息
        var provinceInfo = chinaConsumptionLevel[i];
        var geo = new SuperMap.Geometry.Point(provinceInfo[1], provinceInfo[2]);
        var attrs = {};
        attrs.NAME = provinceInfo[0];
        attrs.CON2009 = provinceInfo[3];
        attrs.CON2010 = provinceInfo[4];
        attrs.CON2011 = provinceInfo[5];
        attrs.CON2012 = provinceInfo[6];
        attrs.CON2013 = provinceInfo[7];

        var fea = new SuperMap.Feature.Vector(geo, attrs);
        features.push(fea);
    }

    graphLineLayer.addFeatures(features);
    map.addLayer(graphLineLayer);
    //调整图层顺序
    map.setLayerIndex(graphLineLayer, map.layers.length - 1);
}

// 清除专题图层中的内容
function clearGraphLineLayer() {
    graphLineLayer.clear();
    closeLineInfoWin();
}

// 显示地图弹窗
function showLineInfoWin(e) {
    // e.target 是图形对象，即数据的可视化对象，柱状图中是柱条;
    // 图形对象的 refDataID 属性是数据（feature）的 id 属性，它指明图形对象是由那个数据制作而来;
    // 图形对象的 dataInfo 属性是图形对象表示的具体数据，他有两个属性，field 和 value;
    if (e.target && e.target.refDataID && e.target.dataInfo) {
        closeLineInfoWin();
        // 获取图形对应的数据 (feature)
        var fea = graphPieLayer.getFeatureById(e.target.refDataID);

        var info = e.target.dataInfo;

        // 弹窗内容
        var contentHTML = "<div style='color: #000; background-color: #fff'>";
        // contentHTML += "省级行政区名称:<br><strong>" + fea.attributes.NAME + "</strong>";
        contentHTML += "<hr style='margin: 3px'>";
        switch (info.field) {
            case "CON2009":
                contentHTML += "09年居民消费水平 <br/><strong>" + info.value + "</strong>（元）";
                break;
            case "CON2010":
                contentHTML += "10年居民消费水平 <br/><strong>" + info.value + "</strong>（元）";
                break;
            case "CON2011":
                contentHTML += "11年居民消费水平 <br/><strong>" + info.value + "</strong>（元）";
                break;
            case "CON2012":
                contentHTML += "12年居民消费水平 <br/><strong>" + info.value + "</strong>（元）";
                break;
            case "CON2013":
                contentHTML += "13年居民消费水平 <br/><strong>" + info.value + "</strong>（元）";
                break;
            default:
                contentHTML += "No Data";
        }
        contentHTML += "</div>";

        // 弹出框大小
        var infowinSize = (SuperMap.Browser.name == "firefox") ? new SuperMap.Size(150, 105) : new SuperMap.Size(140, 90);

        // 弹出窗地理位置
        var lonLat = map.getLonLatFromPixel(infowinPosition);
        infowin = new SuperMap.Popup(
            "infowin",
            lonLat,
            infowinSize,
            contentHTML,
            false,
            false,
            null);
        infowin.setBackgroundColor("#fff");
        infowin.setOpacity(0.8);
        if (infowin) map.removePopup(infowin);
        map.addPopup(infowin);
    }
}

// 移除和销毁地图弹窗
function closeLineInfoWin() {
    if (infowin) {
        try {
            map.removePopup(infowin);
        }
        catch (e) {
            alert(e.message);
        }
    }
}
