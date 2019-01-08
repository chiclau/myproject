   // 百度地图API功能
    var map = new BMap.Map("mapdiv1");    // 创建Map实例
    var boundary = ["河北省"];
    var color = ["#FFFFFF"];
    var address = ["河北省"];
    map.centerAndZoom(new BMap.Point(114.527, 38.071), 5);  // 初始化地图,设置中心点坐标和地图级别
    //map.centerAndZoom("河北", 9);  // 初始化地图,第一个参数可以使用中心点坐标,也可以使用城市名称;第二个参数为地图级别
    //添加地图类型控件 
    map.addControl(new BMap.MapTypeControl({
        mapTypes:[
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]}));  
    //去除路网
  map.setMapStyle({
    styleJson:[
      {
        "featureType": "road",
        "elementType": "all",
        "stylers": {
          "color": "#ffffff",
          "visibility": "off"
        }
      },
      {
        "featureType": "building",
        "elementType": "all",
        "stylers": {
          "visibility": "off"
        }
      },
      {
        "featureType": "poilabel",
        "elementType": "all",
        "stylers": {
          "visibility": "off"
        }
      },
      {
        "featureType": "manmade",
        "elementType": "all",
        "stylers": {
          "visibility": "off"
        }
      },
    ]
      });
   map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
  
    for (var i = 0; i < boundary.length; i++) {
        getBoundary(boundary[i],color[i]);  //行政区划覆盖遮罩
    }
    for (var j = 0; j < address.length; j++) {
        getPoint(address[j]);  //地图描点
    }

    //点聚合
    /* var markers = [];
    for (var i = 0; i < 10; i++) {
       pt = new BMap.Point(Math.random() * 40 + 85, Math.random() * 30 + 21);
       markers.push(new BMap.Marker(pt));
    }
    var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers}); */

    //单击获取点击的经纬度
    map.addEventListener("click",function(e){
        alert(e.point.lng + "," + e.point.lat);
      alert(this.getZoom());
    });
   map.addEventListener("click",function(e){
     map.centerAndZoom(new BMap.Point(e.point.lng, e.point.lat), 				this.getZoom()+5);
     map.clearOverlays(); 
   		getBoundary("太原市",color);
      	getPoint("太原市");  //地图描点
    });
   
 map.addEventListener("zoomend", function(){    
   // alert("地图缩放至：" + this.getZoom() + "级"); 
});
    function getBoundary(area,color){       
        var bdary = new BMap.Boundary();
        bdary.get(area, function(rs){       //获取行政区域
            //map.clearOverlays();        //清除地图覆盖物       
            var count = rs.boundaries.length; //行政区域的点有多少个
            if (count === 0) {
                alert('未能获取当前输入行政区域');
                return ;
            }
            var pointArray = [];
            for (var i = 0; i < count; i++) {
                var ply = new BMap.Polygon(rs.boundaries[i], {strokeColor:"green",fillColor: color ,fillOpacity:"0.3",strokeOpacity:0.3,strokeWeight: 2}); //建立多边形覆盖物
                map.addOverlay(ply);  //添加覆盖物
                pointArray = pointArray.concat(ply.getPath());
            }    
            //map.setViewport(pointArray);    //调整视野  
        });   
    }
    // 将地址解析结果显示在地图上,并调整地图视野
    function getPoint(address) {
        // 创建地址解析器实例
        var myGeo = new BMap.Geocoder();
        myGeo.getPoint(address, function(point){
            if (point) {
                //map.centerAndZoom(point, 16);
                var marker = new BMap.Marker(point);
                map.addOverlay(marker);  //描点
                                      var label = new BMap.Label("30200", { offset: new BMap.Size(0, -15) });
                        marker.setLabel(label);
                        label.setStyle({
                            color: "White",
                            fontSize: "14px",
                            backgroundColor: "#5CACEE",
                            border: "0"
                        });      
                var opts = {
                    width : 280,     // 信息窗口宽度
                    height: 150,     // 信息窗口高度
                    title : "<span class='content'>详细信息</span>" , // 信息窗口标题
                }
                var infoWindow = new BMap.InfoWindow("<font class='content'>地址："+address+"<br/>坐标:"+point.lng+"," +point.lat+
                        "<br/><a href='javascript:void(0)' onclick='alert(\"啦啦啦!!!\")' style='font-size:18px;color:blue;text-decoration:underline;'>点击有惊喜！！！</a></font>"
                        ,opts);  //创建信息窗口对象 
                //鼠标点击事件
                marker.addEventListener("click", function(){          
                    map.openInfoWindow(infoWindow,point); //开启信息窗口
                    //map.centerAndZoom(point,14);
                });
                //鼠标移入事件
                marker.addEventListener("mouseover", function(){          
                    map.openInfoWindow(infoWindow,point); //开启信息窗口  
                }); 
            }else{
                alert("您选择地址没有解析到结果!"); 
            }
        }, "石家庄市");
    }