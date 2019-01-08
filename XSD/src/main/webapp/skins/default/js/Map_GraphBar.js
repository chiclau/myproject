/**
 * Created by Gerhard on 2017/9/28.
 */
// 创建一个三维柱状图（Bar3D）统计专题图图层
graphBarLayer = new SuperMap.Layer.Graph("graphBarLayer", "Bar3D");

// 指定用于专题图制作的属性字段
graphBarLayer.themeFields = ["CON2009", "CON2010", "CON2011", "CON2012", "CON2013"];

// 配置图表参数
graphBarLayer.chartsSetting = {
    // width，height，codomain 分别表示图表宽、高、数据值域；此三项参数为必设参数
    width: 260,
    height: 120,
    codomain: [0, 40000], // 允许图表展示的值域范围，此范围外的数据将不制作图表
    // 3d 柱条正面样式（3d 柱条的侧面和顶面会以 3d 柱条正面样式为默认样式）
    barFaceStyle: {
        stroke: true
    },
    // 按字段设置 3d 柱条正面样式
    barFaceStyleByFields: [{ fillColor: "#FFB980" }, { fillColor: "#5AB1EF" }, { fillColor: "#B6A2DE" }, { fillColor: "#2EC7C9" }, { fillColor: "#D87A80" }],
    // 3d 柱条正面 hover 样式（3d 柱条的侧面和顶面 hover 会以 3d 柱条正面 hover 样式为默认 hover 样式）
    barFaceHoverStyle: {
        stroke: true,
        strokeWidth: 1,
        strokeColor: "#ffff00"
    },
    xShapeBlank: [15, 15, 15],   // 水平方向上的空白间距参数
    axisYTick: 4,       // y 轴刻度数量
    useXReferenceLine: true,         // 使用参考线
    xReferenceLineStyle: {strokeColor: "#008acd", strokeOpacity: 0.4},         // 参考线样式
    axisYLabels: ["4万", "3万", "2万", "1万", "0"],         // y 轴标签
    axisXLabels: ["09年", "10年", "11年", "12年", "13年"],       // x 轴标签
    backgroundStyle: {         // 背景样式
        fillColor: "#d1eeee",
        shadowBlur: 12,
        shadowColor: "#d1eeee"
    },
    backgroundRadius: [5, 5, 5, 5]       // 背景框圆角参数
};

// 注册专题图 mousemove, mouseout 事件(注意：专题图图层对象自带 on 函数，没有 events 对象)
graphBarLayer.on("mousemove", showBarInfoWin);
graphBarLayer.on("mouseout", closeBarInfoWin);

graphBarLayer.setOpacity(0.9);

// 构建 feature 数据, 专题图的数据必须是 SuperMap.Feature.Vector
function addGraphBarLayer() {
    removeAllThemeLayer();
    map.events.un({
        "click" : mapClickGetClickPosition
    });
    map.events.un({
        "click" : mapClickGetClickPosition
    });
    // 统计图模块要求浏览器支持 Canvas 渲染
    if(!document.createElement('canvas').getContext){
        alert("您的浏览器不支持 Canvas，请升级！");
        return;
    }

    var features = [];
    for(var i = 0, len = chinaConsumptionLevel.length; i < len; i++){
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

    graphBarLayer.addFeatures(features);
    map.addLayer(graphBarLayer);
    //调整图层顺序
    map.setLayerIndex(graphBarLayer, map.layers.length - 1);
}

// 清除专题图层中的内容
function clearGraphBarLayer() {
    graphBarLayer.clear();
    closeBarInfoWin();
}

// 显示地图弹窗
function showBarInfoWin(e){
    // e.target 是图形对象，即数据的可视化对象，柱状图中是柱条;
    // 图形对象的 refDataID 属性是数据（feature）的 id 属性，它指明图形对象是由那个数据制作而来;
    // 图形对象的 dataInfo 属性是图形对象表示的具体数据，他有两个属性，field 和 value;
    if(e.target && e.target.refDataID && e.target.dataInfo){
        closeBarInfoWin();
        // 获取图形对应的数据 (feature)
        var fea = graphBarLayer.getFeatureById(e.target.refDataID);

        var info = e.target.dataInfo;

        // 弹窗内容
        var contentHTML = "<div style='color: #000; background-color: #fff'>";
        contentHTML += "省级行政区名称:<br><strong>" + fea.attributes.NAME + "</strong>";

        contentHTML += "<hr style='margin: 3px'>";
        switch(info.field){
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
        var infowinSize =  (SuperMap.Browser.name == "firefox")?  new SuperMap.Size(150, 125): new SuperMap.Size(140, 110);

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
        if(infowin) map.removePopup(infowin);
        map.addPopup(infowin);
        infowin.updateSize();
    }
}

// 移除和销毁地图弹窗
function closeBarInfoWin() {
    if(infowin) {
        try {
            map.removePopup(infowin);
        }
        catch(e) {
            alert(e.message);
        }
    }
}