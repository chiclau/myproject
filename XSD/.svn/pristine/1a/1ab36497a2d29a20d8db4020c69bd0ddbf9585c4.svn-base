/**
 * Created by Gerhard on 2017/9/28.
 */
function addThemeGraph() {
    removeAllThemeLayer();
    //创建统计专题图对象，ThemeGraph 必设 items。
    //专题图参数 ThemeParameters 必设 theme、dataSourceName 和 datasetName
    var style = new SuperMap.REST.ServerStyle({
        fillForeColor: new SuperMap.REST.ServerColor(0, 189, 14),
        fillOpaqueRate: 90,
        lineColor: new SuperMap.REST.ServerColor(122, 216, 226),
        lineWidth: 0.1
    });
    item = new SuperMap.REST.ThemeGraphItem({
        caption: "流域电站数量",
        graphExpression: 10,
        uniformStyle: style
    });
    themeGraph = new SuperMap.REST.ThemeGraph({
        items: new Array(item),
        barWidth: 0.1,
        graduatedMode: SuperMap.REST.GraduatedMode.SQUAREROOT,
        //是否显示坐标轴
        graphAxes: new SuperMap.REST.ThemeGraphAxes({
            axesDisplayed: true,
            axesColor: new SuperMap.REST.ServerColor(0, 200, 0)
        }),
        graphSize: new SuperMap.REST.ThemeGraphSize({
            maxGraphSize: 40,
            minGraphSize: 8
        }),
        graphText: new SuperMap.REST.ThemeGraphText({
            graphTextDisplayed: true,
            graphTextFormat: SuperMap.REST.ThemeGraphTextFormat.VALUE,
            graphTextStyle: new SuperMap.REST.ServerTextStyle({
                sizeFixed: true,
                foreColor: new SuperMap.REST.ServerColor(99, 99, 99),
                bold: true,
                fontHeight: 10,
                fontWidth: 7
            })
        }),
        offset: new SuperMap.REST.ThemeOffset({
            offsetX: 0.1,
            offsetY: 0.1
        }),
        graphType: SuperMap.REST.ThemeGraphType.BAR3D,
        graphSizeFixed: true
    }),
        //专题图参数对象
        themeParameters = new SuperMap.REST.ThemeParameters({
            themes: [themeGraph],
            dataSourceNames: ["liuymap"],
            datasetNames: ["水系界"],
            types: ['REGION']
        }),

        //与服务端交互
        themeService = new SuperMap.REST.ThemeService(liuyuUrl, {
            eventListeners: {
                "processCompleted": themeGraphCompleted,
                "processFailed": themeGraphFailed
            }
        });
    themeService.processAsync(themeParameters);
}

//显示专题图。专题图在服务端为一个资源，每个资源都有一个 ID 号和一个 url
//要显示专题图即将资源结果的 ID 号赋值给图层的 layersID 属性即可
function themeGraphCompleted(themeEventArgs) {
    if (themeEventArgs.result.resourceInfo.id) {
        themeGraph = new SuperMap.Layer.TiledDynamicRESTLayer("三维柱专题图",
            liuyuUrl,
            {
                cacheEnabled: false,
                transparent: true,
                layersID: themeEventArgs.result.resourceInfo.id
            },
            {resolutions: restLayerResolutions});
        themeGraph.events.on({"layerInitialized": addThemeGraphLayer});
    }
}
function addThemeGraphLayer() {
    map.addLayer(themeGraph);
}
function themeGraphFailed(serviceFailedEventArgs) {
    //doMapAlert("",serviceFailedEventArgs.error.errorMsg,true);
    alert(serviceFailedEventArgs.error.errorMsg);
}
//移除专题图图层
function removeThemeGraph() {
    for (i = 0; i < map.layers.length; i++) {
        if (map.layers[i].name == "三维柱专题图") {
            map.removeLayer(themeGraph, true);
        }
    }
}