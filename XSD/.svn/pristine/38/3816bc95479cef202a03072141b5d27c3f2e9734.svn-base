/**
 * Created by HTKM on 2017/9/29.
 */
var featureslessthan112 = [];
var featuresbiggerthan112 = [];
//不使用features展示电站，使用专题图来展示，有自动避让功能，也可交互。
function removeFeatureslessthan112(){
    themeLayerDianzhanPoint.removeFeatures(featureslessthan112);
}

function addFeatureslessthan112(){
    themeLayerDianzhanPoint.addFeatures(featureslessthan112);
}

function addThemeLayer(featuresResult) {
    //显示电站的时候，电站可以交互，此时把“统计百分比”图层影藏掉
//    for(i=0;i<featuresResult.length;i++){
//        if(featuresResult[i].data["LGTD"] < 112){
//            featureslessthan112.push(featuresResult[i]);
//        }else{
//            featuresbiggerthan112.push(featuresResult[i]);
//        }
//    }
    vectorLayerStatisticLabel.setVisibility(0);
    //显示点专题图层
    themeLayerDianzhanPoint.setVisibility(1);
    clearDianzhanThemeLayer();
//    var features = featuresResult;
//    for (var i = 0, len = features.length; i < len; i++) {
//        // 省居民消费水平（单位：元）信息
//        var geo = features[i].geometry;
//        features[i].attributes["CON2009"] = 9999;
//    }
    themeLayerDianzhanPoint.addFeatures(featuresResult);
    map.setLayerIndex(themeLayerDianzhanPoint, map.layers.length - 1);

}

// 清除专题图层中的内容
function clearDianzhanThemeLayer() {
    themeLayerDianzhanPoint.clear();
    closeDianzhanInfoWin();
}

// 显示地图弹窗
var ee;
function showDianzhanInfoWin(e) {
    // e.target 是图形对象，即数据的可视化对象，柱状图中是柱条;
    // 图形对象的 refDataID 属性是数据（feature）的 id 属性，它指明图形对象是由那个数据制作而来;
    // 图形对象的 dataInfo 属性是图形对象表示的具体数据，他有两个属性，field 和 value;
    if (e.target && e.target.refDataID && e.target.dataInfo) {
        ee = e;
        // console.log(e);
        closeDianzhanInfoWin();
        // 获取图形对应的数据 (feature)
        var fea = themeLayerDianzhanPoint.getFeatureById(e.target.refDataID);

        // 弹窗内容
        var contentHTML = fea.attributes.content;
        var width=220;
        var height=105;
        if(fea.attributes.width){
        	width=fea.attributes.width;
        }
        if(fea.attributes.height){
        	height=fea.attributes.height;
        }
        if(contentHTML){
        	// 弹出框大小
            var infowinSize = new SuperMap.Size(width, height);

            // 弹出窗地理位置
            var lonLat = map.getLonLatFromPixel(infowinPosition);
            dianzhanInfowin = new SuperMap.Popup.Anchored(
                "dianzhanInfowin",
                lonLat,
                infowinSize,
                contentHTML,
                null,
                false,
                null);

            // dianzhanInfowin = new SuperMap.Popup.Anchored(
            //     "labelInfowin",
            //     lonLat,
            //     infowinSize,
            //     contentHTML,
            //     null,
            //     false,
            //     function(){
            //
            //     });
            dianzhanInfowin.setBackgroundColor("#fff");
            dianzhanInfowin.setOpacity(0.9);

            if (dianzhanInfowin) map.removePopup(dianzhanInfowin);
            map.addPopup(dianzhanInfowin);
        }
    }
}

// 移除和销毁地图弹窗
function closeDianzhanInfoWin() {
    if (dianzhanInfowin) {
        try {
            map.removePopup(dianzhanInfowin);
        }
        catch (e) {
            alert(e.message);
        }
    }
}

//点击电站
var clickingDianzhan = false;
function clickDianzhan(e) {
    // e.target 是图形对象，即数据的可视化对象，柱状图中是柱条;
    // 图形对象的 refDataID 属性是数据（feature）的 id 属性，它指明图形对象是由那个数据制作而来;
    // 图形对象的 dataInfo 属性是图形对象表示的具体数据，他有两个属性，field 和 value;
    if (e.target && e.target.refDataID && e.target.dataInfo) {
        clickingDianzhan = true;
        ee = e;
        closeDianzhanInfoWin();
        // 获取图形对应的数据 (feature)
        var fea = themeLayerDianzhanPoint.getFeatureById(e.target.refDataID);

        var info = e.target.dataInfo;

        alert("详细信息：\n"
            +"名称："+fea.attributes.ENNM+"\n"
            +"时间："+fea.attributes.DTUPDT+"\n"
        );
    }
}