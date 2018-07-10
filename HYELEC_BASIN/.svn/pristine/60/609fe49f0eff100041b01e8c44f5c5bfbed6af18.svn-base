<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>等值线图</title>
    <%@include file="../../../common/inc/inc.inc"%>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>common/leaflet/base/leaflet.css">
    <script type="text/javascript" src="<%=basePath %>common/leaflet/base/leaflet.js"></script>
    <!-- 注意这里引用geoJSON数据，statesData对象定义在里面 
    <script src="../data/us-states.js"></script>
    -->
    <script src="http://leafletjs.com/examples/choropleth/us-states.js"></script>

    <style>
    .container-fluid:first-child .navbar-header {
		    margin-top: -3px;

        }
        .container-fluid:first-child .navbar-header h3{
		   font-size:20px;
        }
        #map {
            height: 500px;
            width: 800px;
        }
        /* 定义info control的container中的css */
        .info { 
            padding: 6px 8px;
            font: 14px/16px Arial, Helvetica, sans-serif;
            background: white;
            background: rgba(255,255,255,0.8);
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            border-radius: 5px; 
        }
        .info h4 {
            margin: 0 0 5px;
            color: #777;
        }
        /* 注意这里用<i></i>呈现图例小方格 */
        .legend { 
            text-align: left;
            line-height: 18px;
            color: #555;
        } 
        .legend i { 
            width: 18px;
            height: 18px;
            float: left;
            margin-right: 8px;
            opacity: 0.7;
        }
    </style>
</head>
<body>
     <div class="container-fluid">
         <%@include file="../../../common/inc/top.jsp"%>    
		<div class="row-fluid">
		  <div id="map"></div>
		</div>
     </div>
</body>
</html>
<script type="text/javascript"> 
        var map = L.map('map').setView([37.8, -96], 4);

        //token可以在https://www.mapbox.com/studio/account/tokens/查看
        var mapboxAccessToken = 'pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw';

        //id看起来是定义风格的
        L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=' + mapboxAccessToken, {
            id: 'mapbox.light'
        }).addTo(map);

        //创建info控件，默认位置position是'topright'，这里L.control()，而不是L.Control()，todo：搞不懂
        var info = L.control();
        //自定义控件需要重载onAdd方法，返回container，执行addTo(map)时会执行该方法，并将control添加到container
        info.onAdd = function (map) {
            //create(string tagName, string className[注：css], HTMLElement container?[注：追加到另一个container])，
            this._div = L.DomUtil.create('div', 'info');
            this.update();
            return this._div;
        }
        //响应事件，更新内容
        info.update = function (props) {
            //原div没有内容时，用innerHTML比较好，不必createElement->appendChild
            //又一次用到'?:'，非常方便
            this._div.innerHTML = '<h4>US Population Density</h4>' + (props ?
                '<b>' + props.name + '</b><br>' + props.density + 'people / mi<sup>2</sup>' 
                : 'Hover over a state');
        }

        info.addTo(map);

        //这里用了比较巧妙的方法，多次使用'?:'，没有使用if...else...
        var getColor = function (d) {
            return d > 1000 ? '#800026' :
                    d > 500  ? '#BD0026' :
                    d > 200  ? '#E31A1C' :
                    d > 100  ? '#FC4E2A' :
                    d > 50   ? '#FD8D3C' :
                    d > 20   ? '#FEB24C' :
                    d > 10   ? '#FED976' :
                                '#FFEDA0';
        }

        //定义geoJSON数据的风格，默认参数是feature，和geoJSON中type对应
        var style = function (feature) {
            return {
                weight: 2,
                opacity: 1,
                color: 'white',
                dashArray: '3',
                fillOpacity: 0.7,  //默认是0.2，不重新设置color会很淡     
                fillColor: getColor(feature.properties.density)
            }
        }

        var geojson;

        var highlightFeature = function (e) {
            //注意获取图层的方式
            var layer = e.target;
            layer.setStyle({
                weight: 5,
                color: '#666',
                dashArray: '',
                fillOpacity: 0.7
            });
            //注意这里获取props的方法
            info.update(layer.feature.properties);
        }

        var resetHighlight = function (e) {
            //将geoJSON的style设置为最初加载时的style，注意参数是layer
            geojson.resetStyle(e.target);
            info.update();
        }

        var zoomToFeature = function (e) {
            //缩放到适合的视口，常用的方法
            map.fitBounds(e.target.getBounds());
        }

        //geoJSON中每一个feature被create时执行
        var onEachFeature = function (feature, layer) {
            //监听鼠标悬停、离开、点击事件
            layer.on({
                mouseover: highlightFeature,
                mouseout: resetHighlight,
                click: zoomToFeature 
            })
        }

        //添加geoJSON，statesData定义在us_state.js中
        geojson = L.geoJSON(statesData, {
            style: style,
            onEachFeature: onEachFeature
        }).addTo(map);

        var legend = L.control({position: 'bottomright'});

        legend.onAdd = function () {
            this._div = L.DomUtil.create('div', 'info legend');

            var grades = [0, 10, 20, 50, 100, 200, 500, 1000],
                labels = [],
                from, to;

            for (let i = 0; i < grades.length; i++) {
                from = grades[i];
                to = grades[i + 1];

                //又一次用'?:'，不必在麻烦的判断grands index超出
                //利用<i></i>呈现小方格，'&ndash;'是html实体符号，代表'-'(注意带分号)，注意from + 1
                labels.push(
                    '<i style="background:' + getColor(from + 1) + '"></i>'
                    + from + (to ? '&ndash;' + to : '+'));
            }
            //用array，然后在join能够清楚的知道每一数据的值，也可以在for里使用labels += ...，但累加过多
            this._div.innerHTML = labels.join('<br>');

            return this._div;
        }

        legend.addTo(map);

    </script>