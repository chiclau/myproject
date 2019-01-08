/**
 * Created by HTKM on 2017/9/28.
 */

//流域统计标签数据
//var liuyuStatisticData = [
//    {lon: 80.358208534746, lat: 39.950671921935, percent:30.3456},
//    {lon: 85.289329576141, lat: 33.251485462159, percent:35.3456},
//    {lon: 95.697419991259, lat: 40.460189454217, percent:40.3456},
//    {lon: 96.324615731321, lat: 32.17083177056, percent:50.3456},
//    {lon: 106.04945521666, lat: 31.820236877332, percent:60.3456},
//    {lon: 108.87571190562, lat: 25.068694236328, percent:70.3456},
//    {lon: 114.54933499065, lat: 37.523757102539, percent:80.3456},
//    {lon: 119.13937653889, lat: 29.576510925419, percent:90.3456},
//    {lon: 123.07094639076, lat: 47.521328598771, percent:99.3456}
//];

//添加流域统计标签
function addVectorLiuyuStatisticLabel(liuyuStatisticData) {
    if(vectorLayerStatisticLabel.features.length>0){
        vectorLayerStatisticLabel.removeAllFeatures();
    }
    var labelFeas=[];
    var label,labelfeature;
    for(var i=0;i<liuyuStatisticData.length;i++){
    	var text= liuyuStatisticData[i].text;
    	if(text){
    		var x=parseFloat(liuyuStatisticData[i].lon);
            var y = parseFloat(liuyuStatisticData[i].lat);
            label=  new SuperMap.Geometry.GeoText(x,y,text);
            labelfeature=new SuperMap.Feature.Vector(label,liuyuStatisticData[i]);
            labelFeas.push(labelfeature);
    	}
    }
    vectorLayerStatisticLabel.addFeatures(labelFeas);
    vectorLayerStatisticLabel.redraw();
}


//行政区统计标签数据
var xzqStatisticData = [
    {on: 91.200931085602, lat: 29.991726149516, percent:30.3456},
    {lon: 111.52331012627, lat: 40.626514057412, percent:40.3456},
    {lon: 113.79480844735, lat: 34.061492868, percent:40.3456},
    {lon: 91.420198996613, lat: 30.182862664212, percent:40.3456},
    {lon: 85.095547186997, lat: 41.338759543945, percent:50.3456},
    {lon: 103.7626919624, lat: 30.889388206053, percent:60.3456},
    {lon: 113.30603455274, lat: 23.205138328125, percent:70.3456},
    {lon: 121.54348646778, lat: 31.11569873822, percent:80.3456},
    {lon: 116.50683258742, lat: 40.560103538084, percent:90.3456},
    {lon: 126.67131718792, lat: 49.137968867397, percent:99.3456},
    {lon: 96.149166571762, lat: 35.735732200194, percent:60.3456}
];

//添加行政区统计标签
function addVectorXZQStatisticLabel(xzqStatisticData) {
    if(vectorLayerStatisticLabel.features.length>0){
        vectorLayerStatisticLabel.removeAllFeatures();
    }
    vectorLayerStatisticLabel.removeAllFeatures();
    var labelFeas=[];
    var label,labelfeature;
    for(var i=0;i<xzqStatisticData.length;i++){
    	var text= xzqStatisticData[i].text;
    	if(text){
    		var x=parseFloat(xzqStatisticData[i].lon);
            var y = parseFloat(xzqStatisticData[i].lat);
            label=  new SuperMap.Geometry.GeoText(x,y,text);
            labelfeature=new SuperMap.Feature.Vector(label,xzqStatisticData[i]);
            labelFeas.push(labelfeature);
    	}
    }
    vectorLayerStatisticLabel.addFeatures(labelFeas);
    vectorLayerStatisticLabel.redraw();
}


function onVectorLayerFeatureOver(selectFeature) {
    if(selectFeature.layer.name == "统计百分比"){
        closelabelInfowin();
        // 弹窗内容
        var contentHTML = selectFeature.attributes.content;
        var width=220;
        var height=105;
        if(selectFeature.attributes.width){
        	width=selectFeature.attributes.width;
        }
        if(selectFeature.attributes.height){
        	height=selectFeature.attributes.height;
        }
        if(contentHTML){
        	// 弹出框大小
            var infowinSize = new SuperMap.Size(width, height);

            // 弹出窗地理位置
            var lonLat = map.getLonLatFromPixel(infowinPosition);
            labelInfowin = new SuperMap.Popup.Anchored(
                "labelInfowin",
                lonLat,
                infowinSize,
                contentHTML,
                null,
                false,
                function(){
                    closelabelInfowin();
                });
            labelInfowin.setBackgroundColor("#fff");
            labelInfowin.setOpacity(0.9);
            if (labelInfowin) map.removePopup(labelInfowin);
            map.addPopup(labelInfowin);
        }
    }
}
function onVectorLayerFeatureClick(selectFeature) {
    if(selectFeature.layer.name == "vectorlayerBorder"){
        queryDianzhanInLiuyu(selectFeature);
    }
}

function closelabelInfowin() {
    if(labelInfowin)map.removePopup(labelInfowin);
}
//清除统计信息图层的内容
function clearVectorLayerStatisticLabel() {
	vectorLayerStatisticLabel.removeAllFeatures();
}
function onVectorLayerFeatureUnselect() {
    if (labelInfowin) {
        try {
            map.removePopup(labelInfowin);
        }
        catch (e) {
            alert(e.message);
        }
    }
}
