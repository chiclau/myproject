var crossSearchIsOn = false;
var selectedLiuyuFeatures = new Set();
var selectedXingzhengquFeatures = new Set();

var selectedLiuyuIDandNameMap = new HashMap();
var selectedXingzhengquIDandNameMap = new HashMap();

function dealSelectParamCode(){
	var lyKeySet = selectedLiuyuIDandNameMap.keySet();
	var lydmstr="";
	var lynamestr="";
	console.log(lyKeySet);
	if(lyKeySet){
		for(var i=0;i<lyKeySet.length;i++){
			var key=lyKeySet[i];
			if(key){
				lydmstr=(lydmstr!=null && lydmstr.length>0)?lydmstr+","+key:lydmstr+key;
				var lyname=selectedLiuyuIDandNameMap.get(key);
				if(lyname){
					lynamestr=(lynamestr!=null && lynamestr.length>0)?lynamestr+","+lyname:lynamestr+lyname;
				}
			}
		}
	}
	lydmstr=lydmstr.replace(/,/g,"','");
	var sfdmstr="";
	var sfnamestr="";
	var sfKeySet=selectedXingzhengquIDandNameMap.keySet();
	if(sfKeySet){
		for(var i=0;i<sfKeySet.length;i++){
			var key=sfKeySet[i];
			if(key){
				sfdmstr=(sfdmstr!=null && sfdmstr.length>0)?sfdmstr+","+key:sfdmstr+key;
				var sfname=selectedXingzhengquIDandNameMap.get(key);
				if(sfname){
					sfnamestr=(sfnamestr!=null && sfnamestr.length>0)?sfnamestr+","+sfname:sfnamestr+sfname;
				}
			}
		}
	}
	sfdmstr=sfdmstr.replace(/,/g,"','");
	
	if(mapSearchParam.cxfw=="ly"){
		mapSearchParam.lydm=lydmstr;
		mapSearchParam.lyname=lynamestr;
		mapSearchParam.cxsfdm=sfdmstr;
		mapSearchParam.cxsfname=sfnamestr;
	}else if(mapSearchParam.cxfw=="sf"){
		mapSearchParam.sfdm=sfdmstr;
		mapSearchParam.sfname=sfnamestr;
		mapSearchParam.cxlydm=lydmstr;
		mapSearchParam.cxlyname=lynamestr;
	}
}
function crossSearchXingzhengqu() {
    crossSearchIsOn = true;
    curBaseMapType = "Xingzhengqu";
    vectorLayerStatisticLabel.setVisibility(1);
    if(liuyuRestlayer)liuyuRestlayer.setVisibility(0);
    if(xingzhengquRestLayer)xingzhengquRestLayer.setVisibility(1);
    map.setLayerIndex(vectorLayerStatisticLabel, map.layers.length - 1);
}

function crossSearchLiuyu() {
    crossSearchIsOn = true;
    curBaseMapType = "Liuyu";
    vectorLayerStatisticLabel.setVisibility(1);
    if(liuyuRestlayer)liuyuRestlayer.setVisibility(1);
    if(xingzhengquRestLayer)xingzhengquRestLayer.setVisibility(0);
    map.setLayerIndex(vectorLayerStatisticLabel, map.layers.length - 1);
}

function stateBack() {
    crossSearchIsOn = false;
    // vectorLayerBorder.removeAllFeatures();
    themeLayerDianzhanPoint.clear();
    if(curBaseMapType=="Liuyu"){
        var features = vectorLayerBorder.getFeaturesByAttribute("type","liuyu");
        vectorLayerBorder.removeFeatures(features);
        selectedLiuyuFeatures.clear();
        curBaseMapType="Xingzhengqu";
        vectorLayerStatisticLabel.setVisibility(1);
        if(liuyuRestlayer)liuyuRestlayer.setVisibility(0);
        if(xingzhengquRestLayer)xingzhengquRestLayer.setVisibility(1);
        map.setLayerIndex(vectorLayerStatisticLabel, map.layers.length - 1);
    }else if(curBaseMapType=="Xingzhengqu"){
        //移除掉行政区
        var features = vectorLayerBorder.getFeaturesByAttribute("type","xingzhengqu");
        vectorLayerBorder.removeFeatures(features);
        selectedXingzhengquFeatures.clear();
        curBaseMapType="Liuyu";
        vectorLayerStatisticLabel.setVisibility(1);
        if(liuyuRestlayer)liuyuRestlayer.setVisibility(1);
        if(xingzhengquRestLayer)xingzhengquRestLayer.setVisibility(0);
        map.setLayerIndex(vectorLayerStatisticLabel, map.layers.length - 1);
    }
}

