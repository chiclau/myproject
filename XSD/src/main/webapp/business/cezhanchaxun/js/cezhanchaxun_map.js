$(function(){
	 dojo.require("esri.map");
	  	  dojo.require("esri.dijit.HomeButton");
	  	  var myMap;	
	  	  var mapInitExtent;
	  	  var administrativeMapService;		//行政区划，省市县
	  	  var changjiangMapService;			//长江经济带
	  	  var hydroPwrFtrLyr;				//水电站FeatureLayer
		  var hydroNamePwrGphLyr;			//水电站名称GraphicsLayer
	  	  var hydroNumFtrLyr;				//水电站数量,按数量渲染FeatureLayer颜色
	  	  var hydroVolFtrLyr;				//水电站装机容量,按数量渲染FeatureLayer颜色
	  	  var hydroNumGphLyr;				//水电站数量,气泡显示数量GraphicsLayer
	  	  var hydroVolGphLyr;				//水电站装机容量,气泡显示装机容量GraphicsLayer
	  	  var cityFtrLyr;					//市FeatureLayer，服务搜索功能
		  var cityGphLyr;					//
	  	  var hydroNumVolList;				//每个省的水电站数量和装机容量数组，[{"省份":"Maine", "个数":9, "装机":200}]
	  	  var googleSpatialReference;
	  	  var countryScale;
	  	  var provinceScale;
		  var cityScale;
		  var hydroScale;
	  	  var bluePictureMarkerSymbol;
	  	  var greenPictureMarkerSymbol;  
	  	  var numPolyRenderer;
		  var volPolyRenderer;
	  	  var hydryRenderer;
		  var font;
		  var color;
		  var bClicked;		//flag变量，单击省市选中状态时为1，再次单击取消选中为0
		  
		  
	  	  function init() {
	  		initVariable();
	        initBasemap();
	        myMap = new esri.Map("map",{extent: mapInitExtent, logo: false, isDoubleClickZoom: false});
	        myMap.addLayer(new GoogleMapLayer()); 
	        myMap.addLayer(new GoogleMapAnooLayer()); 
			//dojo.connect(myMap, "onLoad", getAllLayers);
			dojo.connect(myMap, "onDblClick", mapDblClicked);
			dojo.connect(myMap, "onClick", mapClicked);
			dojo.connect(myMap, "onExtentChange", onMapExtentChange);
					
			initLayers();				
			initWidget();	
		    $("#map_ly").on('click',function(e){
		    	  $("#inputSearchExample3").val("");
				  administrativeMapSwitch("长江经济带")
			});
			  //切换全国地图
			$("#map_sf").on('click',function(e){
				  $("#inputSearchExample3").val("");
				  administrativeMapSwitch("全国")
			});
			 var oTxt = document.getElementById("inputSearchExample3"); 
		    	oTxt.onkeyup = function(){ 
		    		var bj = $("#inputSearchExample3").val();
		    		if(bj == null || bj == ""){
		    			$("#ulall").addClass("hiddened");
		    		}
		    	} 
		     $('#inputSearchExample3').bind('keypress',function(event){
		         if(event.keyCode == "13"){  
		         	keyUp();
		         }
		     });
		   //  默认右侧展示长江经济带的数据
		     loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf.jsp?bj="+0);//bj为0查询全国，为1查询全省，为2查询全市
				loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf_right.jsp?bj="+0);
	  	  } 
	  	 function initLayers(){

	  		administrativeMapService = new esri.layers.ArcGISDynamicMapServiceLayer("http://www.lanyuhuitong.cn:6080/arcgis/rest/services/AdministrativeMap/MapServer", {
				id : "行政区划",
				opacity : 0.5
	        });
			myMap.addLayer(administrativeMapService);
			//changjiangMapService = new esri.layers.ArcGISDynamicMapServiceLayer("http://www.lanyuhuitong.cn:6080/arcgis/rest/services/ChangJiang/MapServer", {
				//id : "长江经济带",
				//opacity : 0.9
	        //});
			//myMap.addLayer(changjiangMapService);
			
			//第二步根据传入数据创建水电站的FeatureLayer
			createHydroPwrFtrLyr(); 
			
			
			//第三步根据传入数据创建按照水电站数量和装机容量进行渲染的FeatureLayer, hydroNumFtrLyrUpdateEnded
			//第四步根据传入数据创建包含水电站数量和装机容量标签的GraphicsLayer, createHydroNumGphLyr, createHydroVolGphLyr
	 		
	 		//hydroNumVolList = [{"省份":"四川省", "个数":9, "装机":200}, {"省份":"江苏省", "个数":30, "装机":500}, {"省份":"湖南省", "个数":60, "装机":300}];
	 		$.ajax({
				data:{"bj":0},
				url:basePath+"homePage/homePage!countQg.action",
				type: "POST",
				dataType:"json",
				success:function(response){
					hydroNumVolList = response.rows;
				}
			});
			
			//水电站数量FeatureLayer
	 		hydroNumFtrLyr = new esri.layers.FeatureLayer("http://www.lanyuhuitong.cn:6080/arcgis/rest/services/AdministrativeMap/MapServer/0", {
	             id : "水电站数量",
	 			mode : esri.layers.FeatureLayer.MODE_SNAPSHOT,
	             outFields: ["*"],
	 			visible: true
	         });
	 		myMap.addLayer(hydroNumFtrLyr);
	 		dojo.connect(hydroNumFtrLyr, "onUpdateEnd", hydroNumFtrLyrUpdateEnded);	//更新完成后，渲染FeatureLayer和GraphicsLayer
	 		dojo.connect(hydroNumFtrLyr, "onClick", hydroNumFtrLyrClicked);	//点击省份时，传出该省份的name
	 		
			//水电站装机容量FeatureLayer
			hydroVolFtrLyr = new esri.layers.FeatureLayer("http://www.lanyuhuitong.cn:6080/arcgis/rest/services/AdministrativeMap/MapServer/0", {
	            id : "水电站装机容量",
				mode : esri.layers.FeatureLayer.MODE_SNAPSHOT,
	            outFields: ["NAME"],
				visible: false
	        });
			myMap.addLayer(hydroVolFtrLyr);
			dojo.connect(hydroVolFtrLyr, "onUpdateEnd", hydroVolFtrLyrUpdateEnded);	//更新完成后，渲染FeatureLayer和GraphicsLayer
			dojo.connect(hydroVolFtrLyr, "onClick", hydroVolFtrLyrClicked);	//点击省份时，传出该省份的name

	 		//第五步创建市FeatureLayer，服务搜索功能
			cityFtrLyr = new esri.layers.FeatureLayer("http://www.lanyuhuitong.cn:6080/arcgis/rest/services/AdministrativeMap/MapServer/1", {
	            id : "市",
				mode : esri.layers.FeatureLayer.MODE_ONDEMAND,
	            outFields: ["*"]
	        });
			myMap.addLayer(cityFtrLyr);		
			//dojo.connect(cityFtrLyr, "onUpdateEnd", cityFtrLyrUpdateEnded);
			dojo.connect(cityFtrLyr, "onClick", cityFtrLyrClicked);	//点击市时，传出该市的name
			
			hydroVolGphLyr = new esri.layers.GraphicsLayer({id : "省级水电站装机容量注记"});
			hydroVolGphLyr.setMaxScale(provinceScale);		
	 		hydroVolGphLyr.spatialReference = googleSpatialReference;
	 		hydroVolGphLyr.initialExtent = mapInitExtent;
			dojo.connect(hydroVolGphLyr, "onClick", hydroVolGphLyrClicked);	
	 		myMap.addLayer(hydroVolGphLyr);
			
			cityGphLyr = new esri.layers.GraphicsLayer({id : "市级水电站装机容量注记"});
			cityGphLyr.setMinScale(provinceScale);
			cityGphLyr.setMaxScale(cityScale);
	 		cityGphLyr.spatialReference = googleSpatialReference;
			dojo.connect(cityGphLyr, "onClick", cityGphLyrClicked);	
			//createAllCityGphLyr();
	 		//cityGphLyr.initialExtent = mapInitExtent;
	 		myMap.addLayer(cityGphLyr);
			
			//水电站注记
			hydroNamePwrGphLyr = new esri.layers.GraphicsLayer({id : "水电站注记"});
			//hydroNamePwrGphLyr.setMaxScale(cityScale);
			hydroNamePwrGphLyr.setMinScale(cityScale);
			myMap.addLayer(hydroNamePwrGphLyr);			
			
			//mapSearch();
	 		
	 	  }
		  
		 //获取地图可视范围内的市
		 //地图放大至显示google地图的公路时，根据地图可视范围显示水电站
		 // on("extent-change"), on("pan"), on("zoom"), on("zoom-end"), on("zoom-start"), setExtent()
		 function getActiveCities(){
				var cities = [];
				var query = new esri.tasks.Query();
			    query.geometry = myMap.extent;
				cityFtrLyr.queryFeatures(query, function(results) {
					var features = results.features;
					for(var i=0;i<features.length-1;i++){
						if(features[i]!=null && features[i]["attributes"]!=null && features[i]["attributes"]["Name"]){
							cities.push(features[i]["attributes"]["Name"]);
						}
					}
				});	
				return cities;
		 }
		  
	  	 function mapDblClicked(){
			myMap.graphics.clear();
		  }
		  function mapClicked(){
			  if(bClicked == 0){
				myMap.graphics.clear();			
			  }else{
				bClicked = 0;
			  }
		  }
	 	  //DONE 返回图层列表
	 	  function getAllLayers(){
	 		var layer = myMap.getLayersVisibleAtScale();
	 		var li = "";
	 		for (var i = 0; i < layer.length; i++) {
	 			if(layer[i]["id"] == "卫星影像" || layer[i]["id"] == "卫星影像注记"|| layer[i]["id"] == "行政区划"|| layer[i]["id"] == "水电站数量"|| layer[i]["id"] == "水电站数量注记"){
	 				li +=  '<li> <input type="checkbox" name ="test"  checked="checked" onclick="checkboxOnclick(this,&quot; '+layer[i]["id"]+'&quot; )" value = '+(layer[i]["id"])+'><a href="#">'+layer[i]["id"]+'</a></li>'
	 			}else{
	 				li +=  '<li> <input type="checkbox" name ="test" onclick="checkboxOnclick(this,&quot; '+layer[i]["id"]+'&quot; )" value = '+(layer[i]["id"])+'><a href="#">'+layer[i]["id"]+'</a></li>'
	 			}
			}
	 		$("#layer_id").append(li);
	 		//return myMap.layerIds + myMap.graphicsLayerIds
	 		//myMap.getLayersVisibleAtScale()
	 	  }
	 	 checkboxOnclick = function (checkbox,id){
	 		if ( checkbox.checked == true){
	 			setLayerVisibility(id.replace(/(^\s*)|(\s*$)/g, ""),true);
	 		}else{
	 			setLayerVisibility(id.replace(/(^\s*)|(\s*$)/g, ""),false);
	 		}
	 	 }
	 		 
	 	  
	 	  //DONE 设置图层可见性
	 	  function setLayerVisibility(id,visibility){
	 		 myMap.getLayer(id).setVisibility(visibility);
	 			//myMap.getLayer("  行政区划").setVisibility(false);
	 		// return myMap.getLayer(id).setVisibility(visibility);
	 	  }
	 	  
	 	  //DONE 点击市时，传出该市的name
	 	  function cityFtrLyrClicked(evt){
			  bClicked=2;
	 		  if(hydroPwrFtrLyr != null){
	 			 hydroPwrFtrLyr.setVisibility(false);
	 		  }
	 		  if(evt.graphic.attributes["Name"] == undefined){
	 			 var address = evt.graphic.attributes["NAME"]
					layer.open({
						  type: 2, 
						  title: '('+address+')小水电数量/容量统计图',
						  area: ['1100px', '607px'],
						  skin: 'layui-layer-molv' ,
						  icon: 6,
						  content: basePath+'business/cezhanchaxun/modalFrame_xs.jsp?bj='+9+'&address='+address,
					});
	 		  }else{
		 		  var address = evt.graphic.attributes["Name"];
				  loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_ly_right_shi.jsp?bj="+2+"&address="+address);
		          loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_ly_shi.jsp?bj="+2+"&address="+address);
		 		  var data = {"级别":"市","经度":null,"纬度":null,"名称":address};
				  mapSearch(data);
	 		  }
	 	  }
		  
	 	 function createCityGphLyr(param){
	 		    hydroVolGphLyr.setVisibility(false);
	 		    hydroNumGphLyr.setVisibility(false);
				var query = new esri.tasks.Query();
			    query.where = "PROVINCENA = '"+param+"'";
				cityFtrLyr.queryFeatures(query, function(results) {
					var features = results.features;
					for(var i=0;i<features.length;i++){
						for(var j=0;j<hydroNumVolList.length;j++){
							if(features[i]["attributes"]["Name"] == hydroNumVolList[j]["省份"]){
								var textSymbol = new esri.symbol.TextSymbol(hydroNumVolList[j]["个数"], font, color);
								var graphicText = new esri.Graphic(features[i].geometry,textSymbol);	
								var graphicBg = new esri.Graphic(features[i].geometry,bluePictureMarkerSymbol);	
								cityGphLyr.add(graphicBg);
								cityGphLyr.add(graphicText);
							}
						}
					}
				});
			  }

		  //
	 	  function createAllCityGphLyr() {
				//查询所有市的统计数据，结果作为paramArray
			  $.ajax({
						data:{"bj":1,"address":null},
						url:basePath+"homePage/homePage!countQg.action",
						type: "POST",
						dataType:"json",
						success:function(paramArray){
							paramArray = paramArray.rows;
							var dq = [{"dq":"北京市"},{"dq":"天津市"},{"dq":"河北省"},{"dq":"山西省"},{"dq":"内蒙古自治区"}
							,{"dq":"辽宁省"},{"dq":"吉林省"},{"dq":"黑龙江省"},{"dq":"上海市"},{"dq":"江苏省"},{"dq":"浙江省"}
							,{"dq":"安徽省"},{"dq":"福建省"},{"dq":"江西省"},{"dq":"山东省"},{"dq":"河南省"},{"dq":"湖北省"}
							,{"dq":"湖南省"},{"dq":"广东省"},{"dq":"广西壮族自治区"},{"dq":"广西省"},{"dq":"海南省"},{"dq":"四川省"}
							,{"dq":"贵州省"},{"dq":"云南省"},{"dq":"西藏自治区"},{"dq":"重庆市"},{"dq":"陕西省"},{"dq":"甘肃省"}
							,{"dq":"青海省"},{"dq":"宁夏回族自治区"},{"dq":"新疆维吾尔自治区"},{"dq":"香港特别行政区"},{"dq":"澳门特别行政区"},{"dq":"台湾省"}]
							for (var i = 0; i < dq.length; i++) {
								var query = new esri.tasks.Query();
							    query.where = "PROVINCENA = '"+dq[i].dq+"'";
							    cityFtrLyr.queryFeatures(query, function(results) {
									var allCityFeatures = results.features;		
									for(var i = 0;i<allCityFeatures.length;i++){ 							
										for(var j = 0; j < paramArray.length; j++){
											//alert(allCityFeatures[i].attributes["Name"])
											if(allCityFeatures[i].attributes["Name"] == paramArray[j]["省份"]){
												var textSymbol = new esri.symbol.TextSymbol(paramArray[j]["个数"], font, color);
												var graphicText = new esri.Graphic(allCityFeatures[i].geometry,textSymbol);	
												var graphicBg = new esri.Graphic(allCityFeatures[i].geometry,bluePictureMarkerSymbol);	
												graphicBg.attributes = {};
												graphicBg.attributes["NAME"] = paramArray[j]["省份"];
												cityFtrLyr.add(graphicBg);
												cityFtrLyr.add(graphicText);
												//cityFtrLyr.setMinScale(hydroScale);
											}						
										}
									}
								})
							}
						}
				  });
	 			
	 		
	 	  }

		  function initAllCityFeatures(){
			var query = new esri.tasks.Query();
			    query.geometry = mapInitExtent;
				cityFtrLyr.queryFeatures(query, function(results) {
					allCityFeatures = results.features;					
				});	
		  }
	 	  
	 	  //DONE 点击省份时，传出该省份的name
	 	  function hydroVolFtrLyrClicked(evt){
	 		  //alert(evt.graphic.attributes["name"])
	 		//return {"点击":"省份","值":evt.graphic.attributes["name"]};
	 	  }
	 	  
	 	  //DONE 点击省份时，传出该省份的name
	 	  function hydroNumFtrLyrClicked(evt){
			  bClicked = 1;
	 		  var address = evt.graphic.attributes["NAME"];
			  loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_sf_right.jsp?bj="+1+"&address="+address);
			  loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_sf.jsp?bj="+1+"&address="+address);
			  var data = {"级别":"省","经度":null,"纬度":null,"名称":address};
			 /* $.ajax({
					data:{"bj":1,"address":address},
					url:basePath+"homePage/homePage!countQg.action",
					type: "POST",
					dataType:"json",
					success:function(response){
						hydroNumVolList = response.rows;
						createCityGphLyr(address)
					}
				});*/
			  mapSearch(data);
	 		//return {"点击":"省份","值":evt.graphic.attributes["name"]};
	 	  }
	 	  
	 	  //DONE 点击水电站时，传出该水电站的objectid
	 	  function hydroPwrFtrLyrClicked(evt){
	 		 //alert(JSON.stringify(evt.graphic.attributes['name']))
	 		 var name = evt.graphic.attributes['name'];
	 		 var id = evt.graphic.attributes['objectid'];
	 		 layer.open({
	 			type: 2, 
				  title: name+'详细信息',
				  area: ['1100px', '607px'],
				  skin: 'layui-layer-molv' ,
				  icon: 6,
				  content: basePath+'business/cezhanchaxun/modalFrames.jsp?id='+id+"&name="+name,
	 			});
	 		// alert(JSON.stringify(evt.graphic.attributes))
	 		// var data={"名称":evt.graphic.attributes['name'],"经度":"116.5601", "纬度":"31.9411", "装机":""}
	 		 //DONE [{"名称":"", "经度":"", "纬度":"", "装机":""}]
//		 	 /createHydroPwrFtrLyr(data)
	 		//return {"点击":"水电站","值":evt.graphic.attributes["objectid"]};
	 	  }
	 	  
	 	  //DONE 更新完成后，渲染FeatureLayer
	 	  function hydroNumFtrLyrUpdateEnded(){
	 		if(hydroNumVolList != null && hydroNumVolList.length > 0){
	 			for(var i = 0;i<hydroNumFtrLyr.graphics.length;i++){ 							
	 				for(var j = 0; j < hydroNumVolList.length; j++){
	 					if(hydroNumFtrLyr.graphics[i].attributes["NAME"] == hydroNumVolList[j]["省份"]){
	 						hydroNumFtrLyr.graphics[i].attributes["number"]=hydroNumVolList[j]["个数"];
	 					}
	 				}				
	 			}
	 			
	 			//用renderer进行渲染比用symbol渲染稳定			  
	 			hydroNumFtrLyr.setRenderer(numPolyRenderer);
	 			hydroNumFtrLyr.setOpacity(.40);
	 			hydroNumFtrLyr.setMaxScale(provinceScale);
	 			hydroNumFtrLyr.redraw();
	 		}	
	 		createHydroNumGphLyr();		
	 	  }
	 	  
	 	  //DONE 更新完成后，渲染FeatureLayer
	 	  function hydroVolFtrLyrUpdateEnded(){
	 		if(hydroNumVolList != null && hydroNumVolList.length > 0){
	 			for(var i = 0;i<hydroVolFtrLyr.graphics.length;i++){ 							
	 				for(var j = 0; j < hydroNumVolList.length; j++){
	 					if(hydroVolFtrLyr.graphics[i].attributes["NAME"] == hydroNumVolList[j]["省份"]){
	 						hydroVolFtrLyr.graphics[i].attributes["number"]=hydroNumVolList[j]["个数"];
	 					}
	 				}				
	 			}
	 			
	 			//用renderer进行渲染比用symbol渲染稳定			  
	 			hydroVolFtrLyr.setRenderer(volPolyRenderer);
	 			hydroVolFtrLyr.setOpacity(.40);
	 			hydroVolFtrLyr.setMaxScale(provinceScale);
	 			hydroVolFtrLyr.redraw();
	 		}	
	 		createHydroVolGphLyr();		
	 	  }
	 	  
	 	  //DONE 更新完成后，渲染GraphicsLayer
	 	  function createHydroVolGphLyr() {
	 		if(hydroNumVolList != null && hydroNumVolList.length > 0){
	 				 			
	 			for(var i = 0;i<hydroVolFtrLyr.graphics.length;i++){ 							
	 				for(var j = 0; j < hydroNumVolList.length; j++){
	 					if(hydroVolFtrLyr.graphics[i].attributes["NAME"] == hydroNumVolList[j]["省份"]){
	 						var textSymbol = new esri.symbol.TextSymbol(hydroNumVolList[j]["装机"], font, color);
	 						var graphicText = new esri.Graphic(hydroVolFtrLyr.graphics[i].geometry,textSymbol);	
	 						var graphicBg = new esri.Graphic(hydroVolFtrLyr.graphics[i].geometry,bluePictureMarkerSymbol);	
							graphicBg.attributes = {};
							graphicBg.attributes["NAME"] = hydroNumVolList[j]["省份"];
	 						hydroVolGphLyr.add(graphicBg);
	 						hydroVolGphLyr.add(graphicText);
							
	 					}						
	 				}
	 			}			
	 			
	 		}     
	 		//getAllLayers();
	 	  }
	 	  
	 	  //DONE 更新完成后，渲染GraphicsLayer
	 	  function createHydroNumGphLyr() {
	 		if(hydroNumVolList != null && hydroNumVolList.length > 0){
	 			hydroNumGphLyr = new esri.layers.GraphicsLayer({id : "水电站数量注记"});
	 			for(var i = 0;i<hydroNumFtrLyr.graphics.length;i++){ 							
	 				for(var j = 0; j < hydroNumVolList.length; j++){
	 					if(hydroNumFtrLyr.graphics[i].attributes["NAME"] == hydroNumVolList[j]["省份"]){
	 						var textSymbol = new esri.symbol.TextSymbol(hydroNumVolList[j]["个数"], font, color);
	 						var graphicText = new esri.Graphic(hydroNumFtrLyr.graphics[i].geometry,textSymbol);	
	 						var graphicBg = new esri.Graphic(hydroNumFtrLyr.graphics[i].geometry,bluePictureMarkerSymbol);	
							graphicBg.attributes = {};
							graphicBg.attributes["NAME"] = hydroNumVolList[j]["省份"];
	 						hydroNumGphLyr.add(graphicBg);
	 						hydroNumGphLyr.add(graphicText);
	 					}						
	 				}
	 			}			
	 			hydroNumGphLyr.setMaxScale(provinceScale);
	 			hydroNumGphLyr.spatialReference = googleSpatialReference;
	 			hydroNumGphLyr.initialExtent = mapInitExtent;
				dojo.connect(hydroNumGphLyr, "onClick", hydroNumGphLyrClicked);	
	 			myMap.addLayer(hydroNumGphLyr);
	 		}         
	 		getAllLayers();
	 	  }

		  function hydroNumGphLyrClicked(evt){
			  
			var query = new esri.tasks.Query();
			query.geometry = evt.graphic.geometry;
			query.spatialRelationship = esri.tasks.Query.SPATIAL_REL_CONTAINS;
			hydroNumFtrLyr.queryFeatures(query, function(results) {
				var features = results.features;
				var address = features[0].attributes["NAME"]
				layer.open({
					  type: 2, 
					  title: '('+address+')小水电数量/容量统计图',
					  area: ['1100px', '607px'],
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame_sf.jsp?bj='+6+'&address='+address,
				});
			});	
		  }

		  function hydroVolGphLyrClicked(){
			var query = new esri.tasks.Query();
			query.geometry = evt.graphic.geometry;
			query.spatialRelationship = esri.tasks.Query.SPATIAL_REL_CONTAINS;
			hydroVolFtrLyr.queryFeatures(query, function(results) {
				var features = results.features;
				var features = results.features;
			});	
		  }

		  function cityGphLyrClicked(){
			var query = new esri.tasks.Query();
			query.geometry = evt.graphic.geometry;
			query.spatialRelationship = esri.tasks.Query.SPATIAL_REL_CONTAINS;
			cityFtrLyr.queryFeatures(query, function(results) {
				var features = results.features;
				var address = features[0].attributes["NAME"]
				layer.open({
					  type: 2, 
					  title: '('+address+')小水电数量/容量统计图',
					  area: ['1100px', '607px'],
					  skin: 'layui-layer-molv' ,
					  icon: 6,
					  content: basePath+'business/cezhanchaxun/modalFrame_sf.jsp?bj='+6+'&address='+address,
				});
			});	
		  }

		  

	 	  //DONE 初始化变量
	 	  function initVariable(){
	 		googleSpatialReference = new esri.SpatialReference({ wkid: 102113 });
	 		mapInitExtent = new esri.geometry.Extent(6916776.8, 2877209.3, 16742502.6, 6220381.8, googleSpatialReference);
	 		countryScale = 36978590;
	 		provinceScale = 9244640;
			cityScale = 200000;
			hydroScale = 600000;
			bClicked = 0;
	 		bluePictureMarkerSymbol = new esri.symbol.PictureMarkerSymbol("https://static.arcgis.com/images/Symbols/Shapes/BluePin1LargeB.png", 48, 48);
	 		greenPictureMarkerSymbol = new esri.symbol.PictureMarkerSymbol("https://static.arcgis.com/images/Symbols/Shapes/GreenPin1LargeB.png", 32, 32);
	 		
	 		numPolyRenderer = new esri.renderer.SimpleRenderer(new esri.symbol.SimpleFillSymbol().setOutline(new esri.symbol.SimpleLineSymbol().setWidth(0.1).setColor(new esri.Color([128,128,128]))));
			numPolyRenderer.setColorInfo({
				field: "number",
				minDataValue: 0,
				maxDataValue: 2000,
				colors: [
					new esri.Color([1, 155, 155]),
					new esri.Color([127, 127, 127])
				]
			});	
				
			volPolyRenderer = new esri.renderer.SimpleRenderer(new esri.symbol.SimpleFillSymbol().setOutline(new esri.symbol.SimpleLineSymbol().setWidth(0.1).setColor(new esri.Color([128,128,128]))));
			volPolyRenderer.setColorInfo({
				field: "number",
				minDataValue: 0,
				maxDataValue: 2000000,
				colors: [
					new esri.Color([1, 155, 155]),
					new esri.Color([127, 127, 127])
				]
			});		
			font  = new esri.symbol.Font();
			font.setSize("8pt");
			font.setWeight(esri.symbol.Font.WEIGHT_BOLD);
			color = new esri.Color([255,255,255,1]);
	 	  }

	 	  //DONE [{"名称":"", "经度":"", "纬度":"", "装机":""}]
	 	  function createHydroPwrFtrLyr(paramArray){
 		    if(cityGphLyr != null){
 			   cityGphLyr.setVisibility(false);
 		    }
 		    if(hydroNamePwrGphLyr != null){
 		    	hydroNamePwrGphLyr.clear();
 		    }
			
	 		//paramArray = [{"名称":"first", "经度":112.5, "纬度":37.3, "装机":200},{"名称":"second", "经度":103.35, "纬度":36.17, "装机":500}]
	 		if(paramArray != null && paramArray.length > 0){
	 			var curFileds=[{"name": "objectid","alias": "objectid","type": "esriFieldTypeOID"},{"name": "name","alias":"name","type": "esriFieldTypeString"},{"name": "volume","alias": "volume","type": "esriFieldTypeInteger"}];
	 			var features = [];
	 			for(var i = 0; i < paramArray.length; i++){
					if( paramArray[i]["经度"]!= null && paramArray[i]["纬度"]!= null ){
						var curPoint = new esri.geometry.Point({
							longitude: paramArray[i]["经度"],
							latitude: paramArray[i]["纬度"],
							spatialReference:{wkid:4326}
						});
						var curFtr = {   
							"geometry": new esri.geometry.geographicToWebMercator(curPoint),
							"attributes":{
								"objectid": i,
								"name": paramArray[i]["名称"],
								"volume": paramArray[i]["装机"]
							}
						}
						features.push(curFtr);	
						//水电站注记
						var offsetPoint = new esri.geometry.Point({
							x: curFtr.geometry.x,
							y: curFtr.geometry.y+200,
							spatialReference:{wkid:102113}
						});
						var textSymbol = new esri.symbol.TextSymbol(paramArray[i]["名称"], font, color);
						var graphicText = new esri.Graphic(offsetPoint,textSymbol);	
						hydroNamePwrGphLyr.add(graphicText);
					}
	 				
	 			}
	 			
	 			var dz = {
	 			  //数据的基本属性
	 			  "displayFieldName": "", 
	 			  "fieldAliases": {
	 				"objectid": "objectid", 
	 				"name": "name", 
	 				"volume": "volume"
	 			  }, 
	 			  "geometryType": "esriGeometryPoint", 
	 			  "spatialReference": {
	 				"wkid": 102113, 
	 				"latestWkid": 102113
	 			  }, 
	 			  //所含有的字段信息
	 			  "fields": curFileds, 
	 			  //所含有的集合要素集
	 			  "features": features
	 			}

	 			
	 			var layerDefinition = {  
	 				"geometryType": "esriGeometryPoint",  
	 				"fields":curFileds
	 			}; 
	 			var featureSet = new esri.tasks.FeatureSet(dz);  
	 			var featureCollection = {  
	 				layerDefinition: layerDefinition,  
	 				featureSet: featureSet  
	 			};  
	 			/*if(myMap.getLayer("水电站") != null){
					myMap.removeLayer(hydroPwrFtrLyr);
				}*/
				
	 			hydroPwrFtrLyr = new esri.layers.FeatureLayer(featureCollection, {
	 				id : "水电站"
	 			});
	 			dojo.connect(hydroPwrFtrLyr, "onClick", hydroPwrFtrLyrClicked);	//点击水电站时，传出该水电站的objectid
	 			var greenRenderer = new esri.renderer.SimpleRenderer(greenPictureMarkerSymbol);
	             hydroPwrFtrLyr.setRenderer(greenRenderer);

	 			hydroPwrFtrLyr.setMinScale(hydroScale);
	 			//hydroPwrFtrLyr.setSelectionSymbol(symbol);
	 			hydroPwrFtrLyr.setVisibility(true);
	 			myMap.addLayer(hydroPwrFtrLyr);
	 		}

	 	  }
	 	  
	 	 	function keyUp(){
		  	   	var bj = $("#inputSearchExample3").val();
		  	   	$("#ulall").html("");
		   	  if (bj.indexOf("县") >= 0||bj.indexOf("镇") >= 0) {
		    		layer.msg("提示：无法定位县/镇 ");
		            return ;
		    	  }
		  	   	if(bj == null || bj == ""){
		  	   		layer.msg("请输入");
		  	   		return false;
		  	   	}else{
		  	   		$.ajax({
		  	   	   		url:basePath+"homePage/homePage!keyUp.action",
		  	   	   		data:{"bj":bj},
		  	   	   		type: "POST",
		  	   	   		dataType:"json",
		  	   	   		success:function(response){
		  	   	   			var data = response.rows;
		  	   	   			if(data != null && data.length > 0){
		  	   	   				$("#ulall").removeClass("hiddened");
		  	   	   				var li = "";
		  	   	   				for (var i = 0; i < data.length; i++) {
		  	   	   					if(data[i].DJ != null && data[i].DJ != ""){
		  		   	   					li += '<li style="position:relative;border-bottom:1px solid rgb(255,255,255)"><img src="../common/images/电站.png" style="margin-left:2%;margin-bottom:10px;display:inline-block;width:8%;position:absolute;top:0px;left:0px;">'
		  		 	   						  +'<a style="display:inline-block;width:90%" href="#" onclick="changeEnabled('+JSON.stringify(data[i]).replace(/\"/g,"'")+')">'+data[i].名称+'</a>';
		  			   	   					  +'</li>';
		  	   	   					}else{
		  		   	   					li += '<li style="position:relative;border-bottom:1px solid rgb(255,255,255)"><img src="../common/images/位置.png" style="margin-left:2%;margin-bottom:10px;display:inline-block;width:8%;position:absolute;top:0px;left:0px;">'
		  		 	   						  +'<a style="display:inline-block;width:90%" href="#" onclick="changeEnabled('+JSON.stringify(data[i]).replace(/\"/g,"'")+')">'+data[i].名称+'</a>';
		  			   	   					  +'</li>';
		  	   	   					}
		  	   	   				}
		  	   	   				$("#ulall").append(li);
		  	   	   			}else{
		  	   	   				layer.msg("未搜索到定位信息");
		  	   	   			}
		  	   	   		}
		  	   	   	});
		  	   	}
		  	}
		  changeEnabled = function(data){
			  if (data.名称.indexOf("县") >= 0||data.名称.indexOf("镇") >= 0) {
			  		layer.msg("提示：无法定位县/镇 ");
			          return false;
			  	  }
				/*if(data.经度==null&&data.纬度==null){//如果无经纬度，提示
		  			layer.msg("提示："+data.名称+"在地图上无坐标 ");
			          return  false;
		  		} */
		  		  $("#ulall").addClass("hiddened");
		  		  $("#inputSearchExample3").val(data.名称);
		  		  var address = data.名称;
		  		  /*alert(JSON.stringify(data))*/
		  		  if(data.级别 == "省"){
		  			if(hydroPwrFtrLyr != null){
			 			 hydroPwrFtrLyr.setVisibility(false);
			 		  }
		  			  loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_sf_right.jsp?bj="+1+"&address="+address);
		  			  loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_sf.jsp?bj="+1+"&address="+address);
					  var data = {"级别":"省","经度":null,"纬度":null,"名称":address};
					  $.ajax({
							data:{"bj":1,"address":address},
							url:basePath+"homePage/homePage!countQg.action",
							type: "POST",
							dataType:"json",
							success:function(response){
								hydroNumVolList = response.rows;
								createCityGphLyr(address)
							}
						});
		  		  }
		  		  if(data.级别 == "市"){
		  			 if(hydroPwrFtrLyr != null){
			 			 hydroPwrFtrLyr.setVisibility(false);
			 		  }
					  loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_ly_right_shi.jsp?bj="+2+"&address="+address);
			          loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_ly_shi.jsp?bj="+2+"&address="+address);
			 		  var data = {"级别":"市","经度":null,"纬度":null,"名称":address};
			 		  $.ajax({
							data:{"bj":2,"address":address},
							url:basePath+"homePage/homePage!countQg.action",
							type: "POST",
							dataType:"json",
							success:function(response){
								createHydroPwrFtrLyr(response.rows)
							}
					  });
		  		  }
		  		if(data.级别 == "电站"){
		  			if(hydroPwrFtrLyr != null){
			 			 hydroPwrFtrLyr.setVisibility(false);
			 		  }
		  			/*  var dw = data.定位;
		  			  if(dw != null){
		  				 var datas= {"级别":"市","经度":null,"纬度":null,"名称":dw};
		  				mapSearch(datas);
		  			  }*/
			 		  var data = {"级别":"电站","经度":data.经度,"纬度":data.纬度,"名称":address,"装机":1};
		  		  }
		  	
		  		mapSearch(data);
		  	}

		  function mapSearch(paramArray) {
			 // alert(JSON.stringify(paramArray))
			  //debugger;	
	/*		  if(paramArray["经度"] == null &&paramArray["纬度"] == null ){
					layer.msg("提示："+paramArray["名称"]+"在地图上无坐标 ");
			          return false ;
			  }*/
			  myMap.graphics.clear();
				if(paramArray != null ){
					if((paramArray["经度"] == null || paramArray["纬度"] == null) && paramArray["级别"] == "省"){
						var query = new esri.tasks.Query();
						query.where = "NAME = '"+paramArray["名称"]+"'";
						hydroNumFtrLyr.queryFeatures(query, function(results) {
							var features = results.features;
							if(features.length > 0){
								myMap.setExtent(features[0].geometry.getExtent());
								var sms = new esri.symbol.SimpleFillSymbol().setOutline(new esri.symbol.SimpleLineSymbol().setWidth(2).setColor(new esri.Color([255,255,255])))
								myMap.graphics.add(new esri.Graphic(features[0].geometry,sms));
							}
						});
					}else if((paramArray["经度"] == null || paramArray["纬度"] == null )&& paramArray["级别"] == "市"){
						var query = new esri.tasks.Query();
						query.where = "Name = '"+paramArray["名称"]+"'";
						cityFtrLyr.queryFeatures(query, function(results) {
							var features = results.features;
					//		debugger;
							//alert(JSON.stringify(features[0].geometry.getExtent()))
							if(features.length > 0){
								myMap.setExtent(features[0].geometry.getExtent());
								var sms = new esri.symbol.SimpleFillSymbol().setOutline(new esri.symbol.SimpleLineSymbol().setWidth(2).setColor(new esri.Color([255,255,255])))
								myMap.graphics.add(new esri.Graphic(features[0].geometry,sms));
							}
						});
					}else if(paramArray["经度"] != null || paramArray["纬度"] != null){
						var data = [];
						data.push(paramArray);
						createHydroPwrFtrLyr(data);
						/*var query = new esri.tasks.Query();
						query.where = "name = '"+paramArray["名称"]+"'";
						hydroPwrFtrLyr.queryFeatures(query, function(results) {
							var features = results.features;
							if(features.length > 0){
								myMap.setExtent(features[0].geometry.getExtent());
								var sms = new esri.symbol.SimpleFillSymbol().setOutline(new esri.symbol.SimpleLineSymbol().setWidth(2).setColor(new esri.Color([255,255,255])))
								myMap.graphics.add(new esri.Graphic(features[0].geometry,sms));
							}
						});*/
						var curPoint = new esri.geometry.Point({
							longitude: paramArray["经度"],
							latitude: paramArray["纬度"],
							spatialReference:{wkid:4326}
						});
						var curPoint = new esri.geometry.Point({
							longitude: paramArray["经度"],
							latitude: paramArray["纬度"],
							spatialReference:{wkid:4326}
						});
						myMap.centerAndZoom(curPoint,10);
						if(hydroPwrFtrLyr != null && hydroPwrFtrLyr.graphics.length > 0){
							//myMap.setExtent(hydroPwrFtrLyr.graphics[0].geometry);
							var sms = new esri.symbol.SimpleFillSymbol().setOutline(new esri.symbol.SimpleLineSymbol().setWidth(2).setColor(new esri.Color([255,255,255])))
							myMap.graphics.add(new esri.Graphic(hydroPwrFtrLyr.graphics[0].geometry,sms));
						}
					}						
				}	
			  }
	 		
	 	  //DONE
	 	  function initWidget(){
	 		var home = new esri.dijit.HomeButton({
	 			map: myMap
	 		  }, "MapHomeButton");
	 		  home.startup();
	 	  }

		  function onMapZoomEnd(){
			var currentScale = myMap.getScale();
	 		if( currentScale < provinceScale && currentScale > hydroScale  ){
				var city = getActiveCities();
	 			var address="";
	 			
	 			if(city != null && city.length>0){
	 				for (var i = 0; i < city.length; i++) {
	 					if(i == city.length-1){
	 						address += "'"+city[i]+"'"
	 					}else{
	 						address += "'"+city[i]+"',"
	 					}
					}
	 				$.ajax({
						data:{"bj":1,"address":address},
						url:basePath+"homePage/homePage!countQg.action",
						type: "POST",
						dataType:"json",
						success:function(response){
							createAllCityGphLyr(response.rows)
						}
				  });
	 			}
	 		}
		  }

	 	  //DONE 地图放大缩小时，返回当前行政级别
	 	  function onMapExtentChange() {
	 		var currentScale = myMap.getScale();
	 		if( currentScale > countryScale){
	 			return "国家级";
	 		}else if( currentScale < countryScale && currentScale > provinceScale  ){
	 			return "省级";
	 		}else if (currentScale < provinceScale && currentScale > hydroScale){
	 			createAllCityGphLyr();
	 			return "市级";
	 		}else if( currentScale < hydroScale ){
	 			var city = getActiveCities();
	 			var address="";
	 			if(city != null && city.length>0){
	 				for (var i = 0; i < city.length; i++) {
	 					if(i == city.length-1){
	 						address += "'"+city[i]+"'"
	 					}else{
	 						address += "'"+city[i]+"',"
	 					}
					}
	 			$.ajax({
						data:{"bj":2,"address":address},
						url:basePath+"homePage/homePage!countQg.action",
						type: "POST",
						dataType:"json",
						success:function(response){
							createHydroPwrFtrLyr(response.rows)
						}
				  });
	 			}
	 			//createAllCityGphLyr();
	 			return "水电站级";
	 		}
		
	 		/*else if( currentScale < hydroScale && currentScale > provinceScale){
				//显示可见范围内的水电站
	 			alert(1)
				getActiveCities();
				createHydroPwrFtrLyr();
			}*/
	 	  }
	 	  function cjjjd(){
	 			$.ajax({
					data:{"bj":0},
					url:basePath+"homePage/homePage!countCj.action",
					type: "POST",
					dataType:"json",
					success:function(response){
						hydroNumVolList = response.rows;
					}
				});
	 		//	myMap.setExtent(new esri.geometry.Extent(10567937.37, 2342183.86, 14035044.304, 4209087.594, googleSpatialReference));		
	 			loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf.jsp?bj="+0);//bj为0查询全国，为1查询全省，为2查询全市
				loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf_right.jsp?bj="+0);
	 	  }
	 	  //DONE 行政区划与长江经济带切换
	 	  function administrativeMapSwitch(administrativeMapName){
	 		if(administrativeMapName == "长江经济带"){					
	 			//administrativeMapService.setVisibleLayers([0,1,2,4]);
	 			myMap.graphics.clear();
//				if(hydroPwrFtrLyr != null){
//					hydroPwrFtrLyr.setVisibility(false);
//				}
//				if(hydroNamePwrGphLyr != null){
//					hydroNamePwrGphLyr.setVisibility(false);
//				}	 			
//				if(hydroNumFtrLyr != null){
//					hydroNumFtrLyr.setVisibility(true);	
//				}	 			
//				if(hydroVolFtrLyr != null){
//					hydroVolFtrLyr.setVisibility(false);
//				}	 				
//				if(hydroNumGphLyr != null){
//					hydroNumGphLyr.setVisibility(true);		
//				}	 			
//				if(hydroVolGphLyr != null){
//					hydroVolGphLyr.setVisibility(false);
//				}	 				
//				if(cityFtrLyr != null){
//					cityFtrLyr.setVisibility(false);	
//				}	 			
//				if(cityGphLyr != null){
//					cityGphLyr.setVisibility(false);
//				}
	 			$.ajax({
					data:{"bj":0},
					url:basePath+"homePage/homePage!countCj.action",
					type: "POST",
					dataType:"json",
					success:function(response){
						hydroNumVolList = response.rows;
					}
				});
	 			myMap.setExtent(new esri.geometry.Extent(10567937.37, 2342183.86, 14035044.304, 4209087.594, googleSpatialReference));
	 			//administrativeMapService.setVisibility(false);
	 			//changjiangMapService.setVisibility(true);
	 			loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf.jsp?bj="+0);//bj为0查询全国，为1查询全省，为2查询全市
				loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_sf_right.jsp?bj="+0);
	 		}else{
//	 			myMap.graphics.clear();
//	 			if(hydroPwrFtrLyr != null){
//					hydroPwrFtrLyr.setVisibility(false);
//				}
//				if(hydroNamePwrGphLyr != null){
//					hydroNamePwrGphLyr.setVisibility(false);
//				}	 			
//				if(hydroNumFtrLyr != null){
//					hydroNumFtrLyr.setVisibility(true);	
//				}	 			
//				if(hydroVolFtrLyr != null){
//					hydroVolFtrLyr.setVisibility(false);
//				}	 				
//				if(hydroNumGphLyr != null){
//					hydroNumGphLyr.setVisibility(true);		
//				}	 			
//				if(hydroVolGphLyr != null){
//					hydroVolGphLyr.setVisibility(false);
//				}	 				
//				if(cityFtrLyr != null){
//					cityFtrLyr.setVisibility(false);	
//				}	 			
//				if(cityGphLyr != null){
//					cityGphLyr.setVisibility(false);
//				}
	 			//administrativeMapService.setVisibleLayers([0,1,2,5]);
	 			loadDiv("#map_frame_bottom_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_ly.jsp?bj="+0);
				loadDiv("#map_frame_right_div",basePath+"business/cezhanchaxun/cezhanchaxun_qg_ly_right.jsp?bj="+0);
				$.ajax({
					data:{"bj":0},
					url:basePath+"homePage/homePage!countQg.action",
					type: "POST",
					dataType:"json",
					success:function(response){
						hydroNumVolList = response.rows;
					}
				});
				myMap.setExtent(new esri.geometry.Extent(6916776.8, 2877209.3, 17042502.6, 6820381.8, googleSpatialReference));
	 			//administrativeMapService.setVisibility(true);
	 			//changjiangMapService.setVisibility(false);
	 		}
	 		
	 	  }	  
	 	
	 	  //DONE 初始化底图
	       function initBasemap(){
	         dojo.declare("GoogleMapLayer", esri.layers.TiledMapServiceLayer, { // create WMTSLayer by extending esri.layers.TiledMapServiceLayer  
	                 constructor: function(){  
	                     this.spatialReference = new esri.SpatialReference({  
	                         wkid: 102113  
	                     });  
	 					this.id = "卫星影像";
	                     this.fullExtent = new esri.geometry.Extent(-20037508.342787, -20037508.342787, 20037508.342787, 20037508.342787, this.spatialReference);
	 					this.initialExtent = new esri.geometry.Extent(6916776.8, 2877209.3, 17042502.6, 6820381.8, this.spatialReference);
	 					//this.initialExtent = new esri.geometry.Extent(6916776.8, 2877209.3, 16742502.6, 6220381.8, this.spatialReference);
	                     //  
	                     this.tileInfo = new esri.layers.TileInfo({  
	                         "dpi": "90.71428571427429",  
	                         "format": "image/png",  
	                         "compressionQuality": 0,  
	                         "spatialReference": {  
	                             "wkid": "3857"  
	                         },  
	                         "rows": 256,  
	                         "cols": 256,  
	                         "origin": {  
	                             "x": -20037508.342787,
	 							"y": 20037508.342787 
	                         },  
	                           
	                         // Scales in DPI 96  
	                        "lods": [{"level": 0,"scale": 591657527.591555,"resolution": 156543.033928  
	                         }, {"level": 1,"scale": 295828763.795777,"resolution": 78271.5169639999  
	                         }, {"level": 2,"scale": 147914381.897889,"resolution": 39135.7584820001  
	                         }, {"level": 3,"scale": 73957190.948944,"resolution": 19567.8792409999  
	                         }, {"level": 4,"scale": 36978595.474472,"resolution": 9783.93962049996  
	                         }, {"level": 5,"scale": 18489297.737236,"resolution": 4891.96981024998  
	                         }, {"level": 6,"scale": 9244648.868618,"resolution": 2445.98490512499  
	                         }, {"level": 7,"scale": 4622324.434309,"resolution": 1222.99245256249  
	                         }, {"level": 8,"scale": 2311162.217155,"resolution": 611.49622628138  
	                        }, {"level": 9,"scale": 1155581.108577,"resolution": 305.748113140558  
	                         }, {"level": 10,"scale": 577790.554289,"resolution": 152.874056570411  
	                         }, {"level": 11,"scale": 288895.277144,"resolution": 76.4370282850732  
	                         }, {"level": 12,"scale": 144447.638572,"resolution": 38.2185141425366  
	                         }, {"level": 13,"scale": 72223.819286,"resolution": 19.1092570712683  
	                         }, {"level": 14,"scale": 36111.909643,"resolution": 9.55462853563415  
	                         }, {"level": 15,"scale": 18055.954822,"resolution": 4.77731426794937  
	                         }, {"level": 16,"scale": 9027.977411,"resolution": 2.38865713397468  
	                         }, {"level": 17,"scale": 4513.988705,"resolution": 1.19432856685505  
	                         }, {"level": 18,"scale": 2256.994353,"resolution": 0.597164283559817  
	                         }, {"level": 19,"scale": 1128.497176,"resolution": 0.298582141647617  
	                         }]  
	                     });  
	                     this.loaded = true;  
	                     this.onLoad(this);  
	                 },  
	                 getTileUrl: function(level, row, col){  
	                     return "http://mt" + (col % 4) + ".google.cn/vt/lyrs=s@112&hl=zh-CN&gl=cn&" + "x=" + col + "&" +  
	                     "y=" +  
	                     row +  
	                     "&" +  
	                     "z=" +  
	                   level +  
	                     "&s=";  
	                 }  
	             });            
	               
	            dojo.declare("GoogleMapAnooLayer", esri.layers.TiledMapServiceLayer, { // create WMTSLayer by extending esri.layers.TiledMapServiceLayer  
	                constructor: function(){  
	                    this.spatialReference = new esri.SpatialReference({  
	                         wkid: 102113  
	                    });  
	 					this.id = "卫星影像注记";
	                     this.fullExtent = new esri.geometry.Extent(-20037508.342787, -20037508.342787, 20037508.342787, 20037508.342787, this.spatialReference);
	 					this.initialExtent = new esri.geometry.Extent(6916776.8, 2877209.3, 16742502.6, 6220381.8, this.spatialReference);
	                  
	                     //  
	                    this.tileInfo = new esri.layers.TileInfo({  
	                         "dpi": "90.71428571427429",  
	                         "format": "image/png",  
	                         "compressionQuality": 0,  
	                         "spatialReference": {  
	                             "wkid": "3857"  
	                         },  
	                         "rows": 256,  
	                         "cols": 256,  
	                         "origin": {  
	                             "x": -20037508.342787,
	 							"y": 20037508.342787  
	                         },  
	                           
	                         // Scales in DPI 96  
	                         "lods": [{"level": 0,"scale": 591657527.591555,"resolution": 156543.033928  
	                         }, {"level": 1,"scale": 295828763.795777,"resolution": 78271.5169639999  
	                         }, {"level": 2,"scale": 147914381.897889,"resolution": 39135.7584820001  
	                         }, {"level": 3,"scale": 73957190.948944,"resolution": 19567.8792409999  
	                         }, {"level": 4,"scale": 36978595.474472,"resolution": 9783.93962049996  
	                         }, {"level": 10,"scale": 577790.554289,"resolution": 152.874056570411  
	                        }, {"level": 11,"scale": 288895.277144,"resolution": 76.4370282850732  
	                        }, {"level": 12,"scale": 144447.638572,"resolution": 38.2185141425366  
	                        }, {"level": 13,"scale": 72223.819286,"resolution": 19.1092570712683  
	                        }, {"level": 14,"scale": 36111.909643,"resolution": 9.55462853563415  
	                        }, {"level": 15,"scale": 18055.954822,"resolution": 4.77731426794937  
	                        }, {"level": 16,"scale": 9027.977411,"resolution": 2.38865713397468  
	                        }, {"level": 17,"scale": 4513.988705,"resolution": 1.19432856685505  
	                        }, {"level": 18,"scale": 2256.994353,"resolution": 0.597164283559817  
	                        }, {"level": 19,"scale": 1128.497176,"resolution": 0.298582141647617  
	                        }]  
	 						
	                     });  
	                     this.loaded = true;  
	                     this.onLoad(this);  
	                 },  
	                 getTileUrl: function(level, row, col){  
	                     return "http://mt" + (col % 4) + ".google.cn/vt/lyrs=h@177000000&hl=zh-CN&gl=cn&" + "x=" + col + "&" +  
	                     "y=" +  
	                     row +  
	                     "&" +  
	                     "z=" +  
	                     level +  
	                     "&s=";  
	                 }  
	             }); 
	     }
	    dojo.ready(init);
})



////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*var result;//ajax返回值（地图上的值）
function initBaiDuMap(qy) {
	var color = [ "#00CCFF" ];//遮罩图层颜色
	if(qy == "qg"){
		var map = new BMap.Map("mapdiv", {minZoom:5,maxZoom:10}); // 创建Map实例
		map.centerAndZoom(new BMap.Point(105.527, 38.071),5); // 初始化地图,设置中心点坐标和地图级别（全国）
		initDataQg(map,null,0);
	}else if(qy == "cj"){
		var map = new BMap.Map("mapdiv", {minZoom:6,maxZoom:10}); // 创建Map实例
		map.centerAndZoom(new BMap.Point(106.557, 29.55467),6); // 初始化地图,设置中心点坐标和地图级别（长江经济带）
		initDataCj(map,null,0);
	}
	// 添加地图类型控件
	map.addControl(new BMap.MapTypeControl({
		mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
	}));
	map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
	deleteRoad(map);//去除路网
	var size = new BMap.Size(0, 100);
	map.addControl(new BMap.CityListControl({
	    anchor: BMAP_ANCHOR_TOP_LEFT,
	    offset: size,
	    // 切换城市之后事件
        onChangeAfter:function(){
    	    map.clearOverlays();//清除地图覆盖物
    	    if($("#cur_city_name").html() == "全国")return;//如果用户点击全国则直接返回
    	    var quyu = $("#cur_city_name").html().substr($("#cur_city_name").html().length-1,1)
    	    if(quyu == "省"){
    	    	addressInversion(map,7);//逆转换方法（设置地图zoom的级别）
    	    }else if(quyu == "市"){
    	    	addressInversion(map,9);//逆转换方法（设置地图zoom的级别）
    	    }else if(quyu == "县"){
    	    	addressInversion(map,10);//逆转换方法（设置地图zoom的级别）
    	    }
		    getBoundary($("#cur_city_name").html(), color,map);//调用地图遮罩图层的方法
       }
	}));
	//监听地图等级鼠标滚动至5级时清除地图覆盖物
	map.addEventListener("zoomend", function(e) {
		if(qy == "qg" && this.getZoom() == 5){
			map.clearOverlays();
			initDataQg(map,null,0);
		}
		if(qy == "cj" && this.getZoom() == 6){
			map.clearOverlays();
			initDataCj(map,null,0);
		}
		console.log(e.drawingMode  )
	});
	//监听地图等级鼠标滚动至5级时清除地图覆盖物
	map.addEventListener("mouseover", function(e) {
		console.log(e)
	});
	// 单击获取点击的经纬度
	var geoc = new BMap.Geocoder(); 
	map.addEventListener("click", function(e) {
		map.clearOverlays();//清除地图覆盖物
		if(this.getZoom()<=7){//省
			map.centerAndZoom(new BMap.Point(e.point.lng, e.point.lat),7);
			geoc.getLocation(e.point, function(rs){
				var addComp = rs.addressComponents;
				if(qy == "qg"){
					initDataQg(map,addComp.province,1)
				}else if(qy == "cj"){
					cjly(addComp.province,map)//判断用户点击范围是否在长江经济带
					initDataCj(map,addComp.province,1);
				}
				getBoundary(addComp.province, color,map);//地图遮罩图层的方法
			});  
		}else if(this.getZoom()<=9){//市
			map.centerAndZoom(new BMap.Point(e.point.lng, e.point.lat),9);
			geoc.getLocation(e.point, function(rs){
				var addComp = rs.addressComponents;
				if(qy == "qg"){
					initDataQg(map,addComp.city,2)
				}else if(qy == "cj"){
					cjly(addComp.province,map)//判断用户点击范围是否在长江经济带
				}
				getBoundary(addComp.city, color,map);//地图遮罩图层的方法
			}); 
		}else{//县
			map.centerAndZoom(new BMap.Point(e.point.lng, e.point.lat),10);
			geoc.getLocation(e.point, function(rs){
				var addComp = rs.addressComponents;
				if(qy == "qg"){
					initDataQg(map,addComp.district,3)
				}else if(qy == "cj"){
					cjly(addComp.district,map)//判断用户点击范围是否在长江经济带
				}
				getBoundary(addComp.district, color,map);//地图遮罩图层的方法
			}); 
		}
	});
}

//判断用户点击范围是否在长江经济带
function cjly(address,map){
	var cjSheng = ["上海市","江苏省","浙江省","安徽省","江西省","湖北省","湖南省","重庆市","四川省","贵州省","云南省"]
	if(qy == "cj"){
		var bj = 0;
		for (var i = 0; i < cjSheng.length; i++) {
			if(address == cjSheng[i]){
				bj ++;
			}
		}
		if(bj == 0){
			map.centerAndZoom(new BMap.Point(106.557, 29.55467),6);
			alert("您选择的地址不是长江经济带区域！")
			return;
		}
	}
}
//将地址解析结果显示在地图上,并调整地图视野
function getPointXian(dj,bw,sum,map) {
	var point = new BMap.Point(dj,bw);
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker); // 添加marker图标
	var label = new BMap.Label(sum, {
		offset : new BMap.Size(0, -15)
	});
	marker.setLabel(label);//添加marker图标的label图
	label.setStyle({
		color : "White",
		fontSize : "10px",
		backgroundColor : "#5CACEE",
		border : "0"
	});
	var opts = {
			width : 280, // 信息窗口宽度
			height : 150, // 信息窗口高度
			title : "<span class='content'>详细信息</span>", // 信息窗口标题
	}
	var infoWindow = new BMap.InfoWindow(
			"<font class='content'>地址："
			+ "<br/>坐标:"
			+ point.lng
			+ ","
			+ point.lat
			+ "<br/><a href='javascript:void(0)' onclick='alert(\"啦啦啦!!!\")' style='font-size:18px;color:blue;text-decoration:underline;'>点击有惊喜！！！</a></font>",
			opts); // 创建信息窗口对象
	// 鼠标点击事件
	marker.addEventListener("click", function() {
		map.openInfoWindow(infoWindow, point); // 开启信息窗口
	});
	// 鼠标移入事件
	marker.addEventListener("mouseover",
			function() {
				map.openInfoWindow(infoWindow,
						point); // 开启信息窗口
			});
}*/

/*//初始化地图数据（全国）
function initDataQg(map,address,bj){
	$.ajax({
		data:{"address":address,"bj":bj},
		url:basePath+"homePage/homePage!countQg.action",
		type: "POST",
		dataType:"json",
        async:false,
		success:function(response){
			if(bj == 3){
				for (var i = 0; i < response.rows.length; i++) {
					sum = response.rows[i].SUM
					dj = response.rows[i].DJ
					bw = response.rows[i].BW
					getPointXian(dj,bw,sum,map)
				}
			}else{
				for (var i = 0; i < response.rows.length; i++) {
					sum = response.rows[i].SUM
					sheng = response.rows[i].SHENG
					getPoint(sheng,sum,map)
				}
			}
		}
	});
}*/

/*//初始化数据（长江经济带）
function initDataCj(map,address,bj){
	$.ajax({
		url:basePath+"homePage/homePage!countCj.action",
		type: "POST",
		dataType:"json",
        async:false,
		success:function(response){
			for (var i = 0; i < response.rows.length; i++) {
				sum = response.rows[i].SUM
				sheng = response.rows[i].SHENG
				getPoint(sheng,sum,map)
			}
		}
	});
}*/

/*//地图遮罩图层的方法
function getBoundary(area, color,map) {
	var bdary = new BMap.Boundary();
	bdary.get(area, function(rs) { // 获取行政区域
		var count = rs.boundaries.length; // 行政区域的点有多少个
		if (count === 0) {
			alert('未能获取当前输入行政区域');
			return;
		}
		var pointArray = [];
		for (var i = 0; i < count; i++) {
			var ply = new BMap.Polygon(rs.boundaries[i], {
				strokeColor : "green",
				fillColor : color,
				fillOpacity : "0.3",
				strokeOpacity : 0.3,
				strokeWeight : 2
			}); // 建立多边形覆盖物
			map.addOverlay(ply); // 添加覆盖物
			pointArray = pointArray.concat(ply.getPath());
		}
	});
}*/

/*//地址逆转换
function addressInversion(map,zoom){
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint($("#cur_city_name").html(), function(point){
		if (point) {
			map.centerAndZoom(point,zoom);
		}else{
			alert("您选择地址没有解析到结果!");
		}
	}, $("#cur_city_name").html());
}*/
/*//去除路网和建筑物
function deleteRoad(map){
	// 去除路网
	map.setMapStyle({
		styleJson : [ {
			"featureType" : "road",
			"elementType" : "all",
			"stylers" : {
				"color" : "#ffffff",
				"visibility" : "off"
			}
		}, {
			"featureType" : "building",
			"elementType" : "all",
			"stylers" : {
				"visibility" : "off"
			}
		}, {
			"featureType" : "poilabel",
			"elementType" : "all",
			"stylers" : {
				"visibility" : "off"
			}
		},{

			"featureType" : "manmade",
			"elementType" : "all",
			"stylers" : {
				"visibility" : "off"
			}
		},]
	});
}*/































// 点击流域tab按钮地图加载数据
function loadTongjiCezhanDataByLy(options){
		// 加载统计信息
		getTongjiCezhanDataOfLy(options);	
		if(mapSearchParam.zoom>mapSearchParam._defautlZoom){
			// 若地图放大到某个设置级别，则加载站点信息
			loadCezhanPointByLyOrSf({map:map,flag:"lysf"});// 加载电站信息
		}
}
function getTongjiCezhanDataOfLy(options){
	//可以ajax异步请求数据
	var dataList=[
		{RVCD:"AFC17006",RVNM:"大渡河",ZSL:10,TTPWR:1200,TTCP:300,SDZSL:5,SKSL:5,LGTD:101.925272,LTTD:31.092067},
		{RVCD:"603",RVNM:"雅砻江",ZSL:10,TTPWR:1200,TTCP:300,SDZSL:5,SKSL:5,LGTD:99.995284,LTTD:31.749428}
	];
	var lylist=new Array();
	for(var i=0;i<dataList.length;i++){
			var ly=new Object();
			ly.lon=dataList[i].LGTD;
			ly.lat=dataList[i].LTTD;
			ly.rvcd=dataList[i].RVCD;
			ly.name=dataList[i].RVNM;
			var ttpwr=dataList[i].TTPWR;
			ly.ttpwr=ttpwr?parseFloat(ttpwr):nullReturn();
			ly.text=dataList[i].ZSL?dataList[i].ZSL:null;
			var ttcp=dataList[i].TTCP?parseFloat(dataList[i].TTCP):nullReturn();
			var html="<div class='table-box'><table class='maptable'><thead><tr><th align='center' colspan='2'>"+dataList[i].RVNM+"</th></tr></thead>";
			html=html+"<tbody>";
			html = html+"<tr><td width='140' align='center'>电站(座)</td><td width='60' align='center'>"+(dataList[i].SDZSL?dataList[i].SDZSL:nullReturn())+"</td></tr>";
			html = html+"<tr><td width='140' align='center'>装机容量(兆w)</td><td width='60' align='center'>"+parseFloat(dataList[i].TTPWR)+"</td></tr>";
			html = html+"<tr><td width='140' align='center'>水库(座)</td><td width='60' align='center'>"+(dataList[i].SKSL?dataList[i].SKSL:nullReturn())+"</td></tr>";
			html = html+"<tr><td width='140' align='center'>总库容(亿m³)</td><td width='60' align='center'>"+ttcp+"</td></tr>";
			html=html+"</tbody>";
			html=html+"</table></div>";
			ly.content=html;
			ly.width=200;
			ly.height=110;
			lylist.push(ly);
	}
	if(lylist!=null && lylist.length>0){
		addVectorLiuyuStatisticLabel(lylist);
	}
}
function loadTongjiCezhanDataBySf(options){
	//加载统计信息
	getTongjiCezhanDataOfSf(options);	
	if(mapSearchParam.zoom>mapSearchParam._defautlZoom){
		//若地图放大到某个设置级别，则加载站点信息
		loadCezhanPointByLyOrSf({map:map,flag:"lysf"});//加载电站信息
	}
}
function getTongjiCezhanDataOfSf(options){
	//可以ajax异步请求数据
	var dataList=[
		{ADDVCD:"510000",NAME:"四川省",ZSL:10,TTPWR:1200,TTCP:300,SDZSL:5,SKSL:5,LGTD:102.709686,LTTD:30.61716},
		{ADDVCD:"110000",NAME:"北京市",ZSL:10,TTPWR:1200,TTCP:300,SDZSL:5,SKSL:5,LGTD:116.40,LTTD:39.90},
		{ADDVCD:"510000",NAME:"河北省",ZSL:10,TTPWR:1200,TTCP:300,SDZSL:5,SKSL:5,LGTD:114.52 ,LTTD:38.05}
	];
	var sflist=new Array();
	for(var i=0;i<dataList.length;i++){
		var sf=new Object();
		sf.lon=dataList[i].LGTD;
		sf.lat=dataList[i].LTTD;
		sf.addvcd=dataList[i].ADDVCD;
		sf.name=dataList[i].NAME;
		var ttpwr=dataList[i].TTPWR;
		sf.ttpwr=ttpwr?parseFloat(ttpwr):nullReturn();
		var zsl=dataList[i].ZSL;
		sf.text=zsl?zsl:null;
		var sdzsl=dataList[i].SDZSL;
		var sksl=dataList[i].SKSL;
		var ttcp=dataList[i].TTCP?parseFloat(dataList[i].TTCP):nullReturn();
		var html="<div class='table-box'><table class='maptable'><thead><tr><th align='center' colspan='2'>"+dataList[i].NAME+"</th></tr></thead>";
		html=html+"<tbody>";
		html = html+"<tr><td width='140' align='center'>电站(座)</td><td width='60' align='center'>"+(sdzsl?sdzsl:nullReturn())+"</td></tr>";
		html = html+"<tr><td width='140' align='center'>装机容量(兆w)</td><td width='60' align='center'>"+parseFloat(dataList[i].TTPWR)+"</td></tr>";
		html = html+"<tr><td width='140' align='center'>水库(座)</td><td width='60' align='center'>"+(sksl?sksl:nullReturn())+"</td></tr>";
		html = html+"<tr><td width='140' align='center'>总库容(亿m³)</td><td width='60' align='center'>"+ttcp+"</td></tr>";
		html=html+"</tbody>";
		html=html+"</table></div>";
		sf.content=html;
		sf.width=200;
		sf.height=110;
		sflist.push(sf);
	}
	if(sflist!=null && sflist.length>0){
		addVectorXZQStatisticLabel(sflist);
	}
}
//选中某个流域或某个省份，或者是放大地图层级时加载事件
function loadCezhanPointByLyOrSf(options){
	alert(options.map.center)
	console.log(options)
	//从后台获取数据
	var dataList=[
		{STNM:"冷竹关",STCD:"008660106100000001",LGTD:102.13838,LTTD:30.074865},
		{STNM:"金窝",STCD:"008660106200000001",LGTD:102.05403,LTTD:29.378777}
		];
	var ssstlist=new Array();
	for(var i=0;i<dataList.length;i++){
		var dz=dataList[i];
		if(dz!=null && dz.LTTD!=null && dz.LGTD!=null){
			dz.CON2009=9999;
			dz.IMG_FLAG="dz";
			var lon = parseFloat(dz.LGTD);
		    var lat = parseFloat(dz.LTTD);
		    var tempProperties = dz;
		    var feature = {
		        "type": "Point",
		        "geometry": {
		        "type": "Point",
		        "coordinates": [lon, lat]
		        },
		        "properties": tempProperties
		    }
		    ssstlist.push(feature);
		}
	}
	if(ssstlist!=null && ssstlist.length>0){
			var yjFetureColection = {};
		    yjFetureColection.type = "FeatureCollection";
		    yjFetureColection.features = ssstlist;
			var mapFeature=addSuperMapPointFeature(yjFetureColection);
	}
}