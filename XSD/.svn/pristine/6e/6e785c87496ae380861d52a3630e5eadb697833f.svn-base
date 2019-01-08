//var iserverUrl = "http://210.72.227.199:8008/iserver";
var iserverUrl = "http://113.240.224.29:1234/iserver";
//var iserverUrl = "http://192.168.1.101:1234/iserver";
// var iserverUrl = "http://192.168.1.101:1234/iserver";

//流域
var liuyuUrl= iserverUrl + "/services/map-liuyumap/rest/maps/liuyumap";
var liuyuQueryDataset = "水系界@liuymap#1";
//电站
var dianzhanUrl = iserverUrl + "/services/map-dzwks/rest/maps/dzmap";
var dianzhanQueryDataset = "dz@liuyu";
// var url="http://113.240.224.29:1234/iserver/services/map-SDLYmapsj/rest/maps/sdlymapsj";

var xingzhengquUrl = iserverUrl + "/services/map-ssx/rest/maps/省市";
var xingzhengquQueryDataset = ["省界@0927#2","市界@0927#2","县界@0927"];

var mgqUrl=iserverUrl+"/services/map-hbmap/rest/maps/mgqmap";//敏感区url
var layer1 = new SuperMap.Layer.AllTDTLayer({name:"矢量图", layerType: "vec", useCanvas: true});
var layer2 = new SuperMap.Layer.AllTDTLayer({name:"矢量标签图", layerType: "vec", isLabel: true, useCanvas: true});
var layer2_1 = new SuperMap.Layer.AllTDTLayer({name:"矢量标签图", layerType: "vec", isLabel: true, useCanvas: true});
var layer3 = new SuperMap.Layer.AllTDTLayer({name:"影像图", layerType: "img", useCanvas: true});
var layer4 = new SuperMap.Layer.AllTDTLayer({name:"影像标签图", layerType: "img", isLabel: true, useCanvas: true});
var layer4_1 = new SuperMap.Layer.AllTDTLayer({name:"影像标签图", layerType: "img", isLabel: true, useCanvas: true});

var QueryResultTypeStruct = {
    Liuyu:"Liuyu",
    Xingzhengqu:"Xingzhengqu",
    Gongsi:"Gongsi"
}

var XingzhengquLevelStruct = {
    Sheng:"Sheng",
    Shi:"Shi",
    Xian:"Xian"
}

isCilckedAddGraphBar3DLayer = false;

var themeParams = {
    themeFields:["Shape_Leng", "Shape_Area"],
    codomain:[ 0, 68 ],
    queryParamNmae:'水系界@liuymap#1',
    attributeFilter:'SmID > -1',
    url:liuyuUrl
};
