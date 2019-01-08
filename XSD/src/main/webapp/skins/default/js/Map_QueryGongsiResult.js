/**
 * Created by HTKM on 2017/10/20.
 */
function queryGongsiBySQL(keyword, resultType, xingzhenquLevel) {
    // $.ajax({
    //     success: function (result) {
    //
    //     }
    // });
    var result = {}
    var resultArry = [];
    resultArry.push({lon: 112.95000885692, lat: 28.228112777593,ENNM:'长沙'});
    resultArry.push({lon: 106.65655945507, lat: 26.490393760253,ENNM:'贵阳'});
    resultArry.push({lon: 108.37317322459, lat: 23.111411216309,ENNM:'南宁'});
    result.resultArray = resultArry;
    var features = convertHoutaiResultToFeatures(result);
    //计算bounds
    var collection = new SuperMap.Geometry.Collection();
    for(i=0;i<features.length;i++){
        collection.addComponents(features[i].geometry);
    }
    collection.calculateBounds();
    map.zoomToExtent(collection.bounds);
    //添加到地图中
    addThemeLayer(features);
}

function convertHoutaiResultToFeatures(result) {
    //1、先构造geojson，包括赋予geojson属性
    var geoJsonFetureColection = {};
    geoJsonFetureColection.type = "FeatureCollection";
    geoJsonFetureColection.features = [];

    for (var i = 0; i < result.resultArray.length; i++) {

        var lon = parseFloat(result.resultArray[i].lon);
        var lat = parseFloat(result.resultArray[i].lat);
        var tempProperties = {};
        var feature = {
            "type": "Point",
            "geometry": {
                "type": "Point",
                "coordinates": [lon, lat]
            },
            "properties": tempProperties
        }
        geoJsonFetureColection.features.push(feature);
    }

    //2、geojson转feature
    var format = new SuperMap.Format.GeoJSON();
    var dataReadFeature = format.read(geoJsonFetureColection, false);
    for (var j = 0; j < dataReadFeature.length; j++) {  //dataReadFeature末尾多一个，囧
        var attibute = {};
        var data = {};
        attibute.ENNM = result.resultArray[j].ENNM;
        data.ENNM = result.resultArray[j].ENNM;
        dataReadFeature[j].attributes = attibute;
        dataReadFeature[j].data = data;
    }
    return dataReadFeature;


}