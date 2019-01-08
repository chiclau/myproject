var curType = "img"; //当前地图类型
var isImg = true;

function changeMapBtn() {
    if(isImg){
        changeMapDouble('vec');
        $(".changeBaseMapBtn").find("span").text("影像");
        $(".changeBaseMapBtn").css('background',"url('images/baseimg.png')");
        isImg = false;
    }else{
        changeMapDouble('img');
        $(".changeBaseMapBtn").find("span").text("矢量");
        $(".changeBaseMapBtn").css('background',"url('images/basevec.png')");
        isImg = true;
    }
}
//响应鼠标点击
function changeMapDouble(mapType) {
    if (mapType == curType) {
        return;
    } else {
        changeMap(mapType);
    }
    curType = mapType;
}

function changeMap(mapType) {
    if (mapType == 'vec') {
        layer1.setVisibility(true);
        layer2.setVisibility(false);
        layer3.setVisibility(false);
        layer4.setVisibility(false);
        ovm.ovmap.layers[0].setVisibility(true);
        ovm.ovmap.layers[1].setVisibility(true);
        ovm.ovmap.layers[2].setVisibility(false);
        ovm.ovmap.layers[3].setVisibility(false);

    }
    if (mapType == 'img') {
        layer1.setVisibility(false);
        layer2.setVisibility(false);
        layer3.setVisibility(true);
        layer4.setVisibility(false);

        ovm.ovmap.layers[0].setVisibility(false);
        ovm.ovmap.layers[1].setVisibility(false);
        ovm.ovmap.layers[2].setVisibility(true);
        ovm.ovmap.layers[3].setVisibility(true);

    }
}


