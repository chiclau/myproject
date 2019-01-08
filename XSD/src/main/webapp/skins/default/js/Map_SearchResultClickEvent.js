/**
 * Created by HTKM on 2017/9/27.
 */
function onVectorLayerFeatureOver(selectFeature) {
    if(selectFeature.layer.name == "统计百分比"){
        closelabelInfowin();
        // 弹窗内容
        var contentHTML = "<div style='color: #000; background-color: #fff;width: 300px;'>";
        contentHTML += "ENNM:<strong>" + selectFeature.attributes.lon + "</strong><br>";
        contentHTML += "DTUPDT:<strong>" + selectFeature.attributes.lon + "</strong><br>";
        contentHTML += "LTTD:<strong>" + selectFeature.attributes.lon + "</strong><br>";
        contentHTML += "</div>";

        // 弹出框大小
        var infowinSize = (SuperMap.Browser.name == "firefox") ? new SuperMap.Size(150, 105) : new SuperMap.Size(140, 90);

        // 弹出窗地理位置
        var lonLat = map.getLonLatFromPixel(infowinPosition);
        labelInfowin = new SuperMap.Popup(
            "labelInfowin",
            lonLat,
            infowinSize,
            contentHTML,
            null,
            true,
            function(){
                closelabelInfowin();
            });
        labelInfowin.setBackgroundColor("#fff");
        labelInfowin.setOpacity(0.9);
        if (labelInfowin) map.removePopup(labelInfowin);
        map.addPopup(labelInfowin);
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