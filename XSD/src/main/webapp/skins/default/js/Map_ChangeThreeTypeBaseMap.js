/**
 * Created by HTKM on 2017/9/27.
 */
//切换底图：流域、行政区、公司
var curBaseMapType = "Liuyu";

function changeBaseMapBaseOnType(typename,callback) {
    globalliuyufeature = undefined;
    globalxingzhengqufeature = undefined;
    removeAllThemeLayer();
    vectorLayerStatisticLabel.setVisibility(1);
    if (typename == "Xingzhengqu") {
        changeToXingzhengqu(callback);
    } else if (typename == "Liuyu") {
        changeToLiuyu(callback);
    } else if (typename == "Gongsi") {
        changeToGongsi(callback);
    }
}
function changeToXingzhengqu(callback) {
    vectorLayerStatisticLabel.setVisibility(1);
    vectorLayerBorder.removeAllFeatures();
    if(liuyuRestlayer)liuyuRestlayer.setVisibility(0);
    if(xingzhengquRestLayer)xingzhengquRestLayer.setVisibility(1);

    curBaseMapType = "Xingzhengqu";
    map.setLayerIndex(vectorLayerStatisticLabel, map.layers.length - 1);
     if(callback){
     	callback(map);
     }
}

function changeToLiuyu(callback) {
    vectorLayerStatisticLabel.setVisibility(1);
    vectorLayerBorder.removeAllFeatures();
    if(liuyuRestlayer)liuyuRestlayer.setVisibility(1);
    if(xingzhengquRestLayer)xingzhengquRestLayer.setVisibility(0);
    curBaseMapType = "Liuyu";
    if(callback){
    	callback(map);
    }
}

function changeToGongsi(callback) {
    if(liuyuRestlayer)liuyuRestlayer.setVisibility(0);
    if(xingzhengquRestLayer)xingzhengquRestLayer.setVisibility(0);
    curBaseMapType = "Gongsi";
    vectorLayerStatisticLabel.setVisibility(0);
    removeAllThemeLayer();
    if(callback){
    	callback(map);
    }
}
