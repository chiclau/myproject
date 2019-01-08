/**
 * Created by HTKM on 2017/10/20.
 */

function queryXingzhengquBySQL(keyword) {
    var datasetNameBaseonXingzhengquType;
    if(keyword && keyword.length>2 && keyword.substring(0,2)+"0000"==keyword){
        datasetNameBaseonXingzhengquType = xingzhengquQueryDataset[0];
    }else if(keyword && keyword.length>4 && keyword.substring(0,4)+"00"==keyword){
        datasetNameBaseonXingzhengquType = xingzhengquQueryDataset[1];
    }else if(keyword){
        datasetNameBaseonXingzhengquType = xingzhengquQueryDataset[2];
    }
    var queryParam, queryParams, queryService;
    var sql = "ADDVCD = '" + keyword + "'";
    queryParam = new SuperMap.REST.FilterParameter({
        name: datasetNameBaseonXingzhengquType,
        // fields: ["SmID", "SmX", "SmY"],
        attributeFilter: sql
    });
    queryParams = new SuperMap.REST.QueryBySQLParameters({
        queryParams: [queryParam]
    });
    queryService = new SuperMap.REST.QueryBySQLService(xingzhengquUrl, {
        eventListeners: {
            "processCompleted": processCompletedXingzhengquBySQL,
            "processFailed": processFailedXingzhengquBySQL
        }
    });
    queryService.processAsync(queryParams);
}

function processCompletedXingzhengquBySQL(queryEventArgs) {
    var result = queryEventArgs.result;
    var recordsets = result.recordsets;
    var features = recordsets[0].features;
    globalxingzhengqufeature = features[0];
    queryDianzhanInXingzhengqu(globalxingzhengqufeature);
}

function processFailedXingzhengquBySQL(e) {
    alert(e.error.errorMsg);
}