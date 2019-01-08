var globalSelectedLiuyus = [];
var globalSelectedXingzhengqus = [];
function queryLiuyuCross(bsids) {
    var sql = "BSID IN(";
    for(var i =0;i<bsids.length;i++){
        sql = sql +"'" +bsids[i]+"'";
        if(i!=(bsids.length-1)){
            sql = sql + ",";
        }
    }
    sql = sql + ")";
    console.log(sql);
    var queryParam, queryParams, queryService;
    queryParam = new SuperMap.REST.FilterParameter({
        name: "水系界@liuymap#1",
        // attributeFilter: "BSID IN('611','60JSJ3')"
        attributeFilter: sql
    });
    queryParams = new SuperMap.REST.QueryBySQLParameters({
        queryParams: [queryParam]
    });
    queryService = new SuperMap.REST.QueryBySQLService(liuyuUrl, {
        eventListeners: {
            "processCompleted": processCompletedLiuyuCross,
            "processFailed": processFailedLiuyuCross
        }
    });
    queryService.processAsync(queryParams);
}
function processCompletedLiuyuCross(queryEventArgs) {
    var result = queryEventArgs.result;
    var recordsets = result.recordsets;
    var features = recordsets[0].features;
    var style = {
        fillColor:"#FFFF00",
        fillOpacity:0.5,
        strokeColor:"#FFFF00",
        strokeDashstyle:"solid",  //dot,dash,dashot,longdash,longdashdot,solid
        strokeOpacity:1,
        strokeWidth:2
    };
    for(var i=0;i<features.length;i++){
        features[i].style = style;
        globalSelectedLiuyus.push(features[i]);
    }

    vectorLayerBorder.addFeatures(features);
    vectorLayerBorder.setVisibility(true);
    zoomToVectorBorderExtent();
}

function processFailedLiuyuCross(e) {
    alert(e.error.errorMsg);
}


function queryXingzhengquCross(addvcds) {
    var queryParam, queryParams, queryService;
    // var sql = "ADDVCD = '" + 430000 + "'"; //IDNAME
    var sql = "ADDVCD IN(";
    for(var i =0;i<addvcds.length;i++){
        sql = sql + addvcds[i];
        if(i!=(addvcds.length-1)){
            sql = sql + ",";
        }
    }
    sql = sql + ")";
    queryParam = new SuperMap.REST.FilterParameter({
        name: xingzhengquQueryDataset[0],
        attributeFilter: sql
    });
    queryParams = new SuperMap.REST.QueryBySQLParameters({
        queryParams: [queryParam]
    });
    queryService = new SuperMap.REST.QueryBySQLService(xingzhengquUrl, {
        eventListeners: {
            "processCompleted": processCompletedXingzhengquCross,
            "processFailed": processFailedXingzhengquCross
        }
    });
    queryService.processAsync(queryParams);
}
function processCompletedXingzhengquCross(queryEventArgs) {
    var result = queryEventArgs.result;
    var recordsets = result.recordsets;
    var features = recordsets[0].features;
    var style = {
        fillColor:"#00FFFF",
        fillOpacity:0.5,
        strokeColor:"#00FFFF",
        strokeDashstyle:"solid",  //dot,dash,dashot,longdash,longdashdot,solid
        strokeOpacity:1,
        strokeWidth:2
    };
    for(var i=0;i<features.length;i++){
        features[i].style = style;
        globalSelectedXingzhengqus.push(features[i]);
    }

    vectorLayerBorder.addFeatures(features);
    vectorLayerBorder.setVisibility(true);
    themeLayerDianzhanPoint.setVisibility(true);
    zoomToVectorBorderExtent();
}

function processFailedXingzhengquCross(e) {
    alert(e.error.errorMsg);
}

function zoomToVectorBorderExtent() {
    var bounds = vectorLayerBorder.getDataExtent();
    if(bounds!=null){
        if(bounds.bottom==bounds.top&&bounds.left==bounds.right){
            map.setCenter(new SuperMap.LonLat(bounds.left,bounds.bottom),14);
        }else{
            map.zoomToExtent(bounds);
        }
    }
}

function FindCommonAreaOfLiuyuAndXingzhengqu() {
    globalSelectedLiuyus
    globalSelectedXingzhengqus
}