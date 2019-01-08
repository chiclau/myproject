function querySelectedLiuyu(point,type,callback) {
    var queryByGeometryParams,queryByGeometryService;
    var zoom = map.getZoom();
    var liuyudataset = "水系界@liuymap#1";

    queryByGeometryParams = new SuperMap.REST.QueryByGeometryParameters({
        queryParams: new Array(new SuperMap.REST.FilterParameter({
            name: liuyudataset
            // fields: ["时间"],
            // attributeFilter: "SmID > -1"
        })),
        geometry: point,
        spatialQueryMode: SuperMap.REST.SpatialQueryMode.INTERSECT
    });

    queryByGeometryService = new SuperMap.REST.QueryByGeometryService(liuyuUrl,{
        eventListeners: {
            "processCompleted": function(queryEventArgs){
            	var result = queryEventArgs.result;
                var recordsets = result.recordsets;
                var features = recordsets[0].features;
                if(globalliuyufeature!=undefined&&globalliuyufeature!=null&&globalliuyufeature==features[0]){
                    alert("已经点击过该流域了！");
                    return;
                }
                globalliuyufeature = features[0];
                map.setLayerIndex(vectorLayerBorder, map.layers.length - 2);
                queryDianzhanInLiuyu(globalliuyufeature);
                if(callback){
                	callback(features[0],type);
                }
            },
            "processFailed": processFailedQuerySelectedLiuyu
        },
        isInTheSameDomain:true
    });
    queryByGeometryService.processAsync(queryByGeometryParams);
}
var globalliuyufeature;
// function processCompletedQuerySelectedLiuyu(queryEventArgs) {
//     var result = queryEventArgs.result;
//     var recordsets = result.recordsets;
//     var features = recordsets[0].features;
//     globalliuyufeature = features[0];
//     map.setLayerIndex(vectorLayerBorder, map.layers.length - 2);
//     queryDianzhanInLiuyu(globalliuyufeature);
//     if(callback){
//     	callback(features[0],type);
//     }
// }

function processFailedQuerySelectedLiuyu(e) {
    alert(e.error.errorMsg);
}

//根据查询到的feature再做一次几何查询
function queryDianzhanInLiuyu(liuyufeature) {
    var queryByGeometryParams,queryByGeometryService;
    queryByGeometryParams = new SuperMap.REST.QueryByGeometryParameters({
        queryParams: new Array(new SuperMap.REST.FilterParameter({
            name: dianzhanQueryDataset,
            attributeFilter: "SmID > -1"
        })),
        // queryOption: SuperMap.REST.QueryOption.ATTRIBUTE,
        geometry: liuyufeature.geometry,
        spatialQueryMode: SuperMap.REST.SpatialQueryMode.INTERSECT
    });

    queryByGeometryService = new SuperMap.REST.QueryByGeometryService(dianzhanUrl,{
        eventListeners: {
            "processCompleted": processCompletedDianzhanInLiuyu,
            "processFailed": processFailedDianzhanInLiuyu
        },
        isInTheSameDomain:true
    });
    queryByGeometryService.processAsync(queryByGeometryParams);

}

function processCompletedDianzhanInLiuyu(queryEventArgs) {
    if(!queryEventArgs.result.recordsets[0].features[0]||!queryEventArgs.result.recordsets){
        var d = dialog({
            content: "当前范围内无电站！",
            zIndex: 2087
        });
        d.show();
        var left = ($(window).width() - $("#left").width() - $(".ui-popup").width())/2 + $("#left").width();
        $(".ui-dialog-body").css({'padding':'10px'});
        $(".ui-popup").css({'left': left+'px','top':'175px'});
        setTimeout(function () {
            d.close().remove();
        }, 3000);
        return;
    }

    var result = queryEventArgs.result;
    var recordsets = result.recordsets;
    var features = recordsets[0].features;

    var style = {
        fillColor:"#00FFFF",
        fillOpacity:0.1,
        strokeColor:"#00FFFF",
        strokeDashstyle:"dashot",  //dot,dash,dashot,longdash,longdashdot,solid
        strokeOpacity:1,
        strokeWidth:2
    };
    globalliuyufeature.style = style;
    vectorLayerBorder.addFeatures(globalliuyufeature);
    console.log("添加一次流域");
//    vectorLayerBorder.redraw();
    map.zoomToExtent(globalliuyufeature.geometry.bounds);
    vectorLayerBorder.redraw();

//    addThemeLayer(features);
}

function processFailedDianzhanInLiuyu(e) {
    alert(e.error.errorMsg);
}

