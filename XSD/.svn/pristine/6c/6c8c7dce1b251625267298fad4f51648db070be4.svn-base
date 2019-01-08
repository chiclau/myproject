/**
 * Created by HTKM on 2017/10/20.
 */
function querySearchResult(keyword, resultType){
//	//避让标识
//    themeLayerDianzhanPoint.isOverLay = false;
    if(resultType==QueryResultTypeStruct.Liuyu){
        changeBaseMapBaseOnType('Liuyu');
        vectorLayerStatisticLabel.setVisibility(0);
        queryLiuyuBySQL(keyword);
    }else if(resultType==QueryResultTypeStruct.Xingzhengqu){
        changeBaseMapBaseOnType("Xingzhengqu");
        vectorLayerStatisticLabel.setVisibility(0);
        queryXingzhengquBySQL(keyword);
    }
}