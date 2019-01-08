/**
 * Created by Gerhard on 2017/9/30.
 */
$(function() {
    changePageSize();

    //窗体大小改变时重设各个窗口的尺寸
    $(window).resize(changePageSize);
});
function changePageSize() {
    $("#main").height($(window).height() - 32);
    $("#left").height($(window).height() - 32);
    $("#mapDiv").height($(window).height() - 32);
    $("#mapDiv").width($(window).width()-150);
}
