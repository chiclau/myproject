/**
 * Created by HTKM on 2017/9/28.
 */
var queryXZQLevel = "";
//第一次几何查询：根据当前的地图缩放级别，根据当前点击的点的位置去查询对应的行政区面
function querySelectedXingzhengqu(point,type,callback) {
    var queryByGeometryParams,queryByGeometryService;
    var zoom = map.getZoom();
    var nameBaseonMapLevel;
    if(zoom<=2){
        queryXZQLevel = "sheng";
        nameBaseonMapLevel = xingzhengquQueryDataset[0];
    }else if(zoom>=3&zoom <=4){
        queryXZQLevel = "shi";
        nameBaseonMapLevel = xingzhengquQueryDataset[0];//[1]
    }else if(zoom>=5){
        queryXZQLevel = "xian";
        nameBaseonMapLevel = xingzhengquQueryDataset[0];//[2]
    }
    
    queryByGeometryParams = new SuperMap.REST.QueryByGeometryParameters({
        queryParams: new Array(new SuperMap.REST.FilterParameter({
            name: nameBaseonMapLevel
            // fields: ["时间"],
            // attributeFilter: "SmID > -1"
        })),
        geometry: point,
        spatialQueryMode: SuperMap.REST.SpatialQueryMode.INTERSECT
    });
    queryByGeometryService = new SuperMap.REST.QueryByGeometryService(xingzhengquUrl,{
        eventListeners: {
            "processCompleted": function(queryEventArgs){
            	var result = queryEventArgs.result;
                var recordsets = result.recordsets;
                var features = recordsets[0].features;
                if(globalxingzhengqufeature!=undefined&&globalxingzhengqufeature!=null&&globalxingzhengqufeature==features[0]){
                    console.log("已经点击过该行政区了！");
                    return;
                }
                globalxingzhengqufeature = features[0];
                map.setLayerIndex(vectorLayerBorder, map.layers.length - 2);
                queryDianzhanInXingzhengqu(globalxingzhengqufeature);
                if(callback){
                	callback(features[0],type);
                }
            },
            "processFailed": processFailedQuerySelectedXingzhengqu
        },
        isInTheSameDomain:true
    });
//    queryByGeometryService = new SuperMap.REST.QueryByGeometryService(xingzhengquUrl,{
//        eventListeners: {
//            "processCompleted": processCompletedQuerySelectedXingzhengqu,
//            "processFailed": processFailedQuerySelectedXingzhengqu
//        },
//        isInTheSameDomain:true
//    });
    queryByGeometryService.processAsync(queryByGeometryParams);
}

var globalxingzhengqufeature;
// function processCompletedQuerySelectedXingzhengqu(queryEventArgs) {
//     var result = queryEventArgs.result;
//     var recordsets = result.recordsets;
//     var features = recordsets[0].features;
//     globalxingzhengqufeature = features[0];
//     queryDianzhanInXingzhengqu(globalxingzhengqufeature);
// }

function processFailedQuerySelectedXingzhengqu(e) {
    alert(e.error.errorMsg);
}

//根据查询到的feature再做一次几何查询
function queryDianzhanInXingzhengqu(xingzhengqufeature) {
    var queryByGeometryParams,queryByGeometryService;
    queryByGeometryParams = new SuperMap.REST.QueryByGeometryParameters({
        queryParams: new Array(new SuperMap.REST.FilterParameter({
            name: dianzhanQueryDataset,
            attributeFilter: "SmID > -1"
        })),
        // queryOption: SuperMap.REST.QueryOption.ATTRIBUTE,
        geometry: xingzhengqufeature.geometry,
        spatialQueryMode: SuperMap.REST.SpatialQueryMode.INTERSECT
    });

    queryByGeometryService = new SuperMap.REST.QueryByGeometryService(dianzhanUrl,{
        eventListeners: {
            "processCompleted": processCompletedDianzhanInXingzhengqu,
            "processFailed": processFailedDianzhanInXingzhengqu
        },
        isInTheSameDomain:true
    });
    queryByGeometryService.processAsync(queryByGeometryParams);
}

function processCompletedDianzhanInXingzhengqu(queryEventArgs) {
    if(!queryEventArgs.result.recordsets[0].features[0]||!queryEventArgs.result.recordsets){
        var d = dialog({
            content: "当前范围内无电站！",
            zIndex: 2087
        });
        d.show();
        var left = ($(window).width() - $("#left").width() - $(".ui-popup").width())/2;
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
    globalxingzhengqufeature.style = style;
    vectorLayerBorder.addFeatures(globalxingzhengqufeature);
    console.log("添加一次行政区！");
    vectorLayerBorder.redraw();
    map.zoomToExtent(globalxingzhengqufeature.geometry.bounds);
   
    //addThemeLayer(features);
}

function processFailedDianzhanInXingzhengqu(e) {
    alert(e.error.errorMsg);
}


var BSIDArr = ['611','60JSJ3'];
var ADDVCDArr = ['430000','530000'];
// var BSIDArr = ['611'];
// var ADDVCDArr = ['430000'];


