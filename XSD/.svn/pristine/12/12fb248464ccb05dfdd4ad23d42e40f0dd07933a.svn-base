/**
 * Created by HTKM on 2017/10/20.
 */
function queryLiuyuBySQL(keyword) {
    var queryParam, queryParams, queryService;
    queryDatasetName = liuyuQueryDataset;
    var sql = "RVCD = '" + keyword + "'"; //IDNAME
    queryParam = new SuperMap.REST.FilterParameter({
        name: queryDatasetName,
        // fields: ["SmID", "SmX", "SmY"],
        attributeFilter: sql
    });
    queryParams = new SuperMap.REST.QueryBySQLParameters({
        queryParams: [queryParam]
    });
    queryService = new SuperMap.REST.QueryBySQLService(liuyuUrl, {
        eventListeners: {
            "processCompleted": processCompletedLiuyuBySQL,
            "processFailed": processFailedLiuyuBySQL
        }
    });
    queryService.processAsync(queryParams);
}
function processCompletedLiuyuBySQL(queryEventArgs) {
    var result = queryEventArgs.result;
    var recordsets = result.recordsets;
    var features = recordsets[0].features;
    globalliuyufeature = features[0];
    map.setLayerIndex(vectorLayerBorder, map.layers.length - 2);
    queryDianzhanInLiuyu(globalliuyufeature)

    //
    // var liuyufeature;
    // //获取feature
    // liuyufeature = queryEventArgs.result.recordsets[0].features[0];
    // queryDianzhanInLiuyu(liuyufeature);
}

function processFailedLiuyuBySQL(e) {
    alert(e.error.errorMsg);
}