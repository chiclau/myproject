
     //弹出列表框  
    $("#txt1").click(function () {  
        $("#div_items").css('display', 'block');  
        return false;  
    });  
  
    //隐藏列表框  
    $("body").click(function () {  
        $("#div_items").css('display', 'none');  
    });  
  
    //移入移出效果  
    $(".div_item").hover(function () {  
        $(this).css('background-color', '#1C86EE').css('color', 'white');  
    }, function () {  
        $(this).css('background-color', 'white').css('color', 'black');  
    });  
  
    //文本框输入  
    $("#txt").keyup(function () {  
        $("#div_items").css('display', 'block');//只要输入就显示列表框  
  
        if ($("#txt").val().length <= 0) {  
            $(".div_item").css('display', 'block');//如果什么都没填，跳出，保持全部显示状态  
            return;  
        }  
  
        $(".div_item").css('display', 'none');//如果填了，先将所有的选项隐藏  
  
        for (var i = 0; i < $(".div_item").length; i++) {  
            //模糊匹配，将所有匹配项显示  
            if ($(".div_item").eq(i).text().substr(0, $("#txt").val().length) == $("#txt").val()) {  
                $(".div_item").eq(i).css('display', 'block');  
            }  
        }  
    });  
  
    //项点击  
    $(".div_item").click(function () {  
    	alert($(this).text())
        $("#txt").val($(this).text());  
    });  
    //弹出列表框  
    $("#txt").click(function () {  
        $("#div_items").css('display', 'block');  
        return false;  
    });  
  
    //隐藏列表框  
    $("body").click(function () {  
        $("#div_items").css('display', 'none');  
    });  
  
    //移入移出效果  
    $(".div_item").hover(function () {  
        $(this).css('background-color', '#1C86EE').css('color', 'white');  
    }, function () {  
        $(this).css('background-color', 'white').css('color', 'black');  
    });  
  
    //文本框输入  
    $("input[name='cezhan']").keyup(function () {  
        $("#div_items").css('display', 'block');//只要输入就显示列表框  
  
        if ($("#txt").val().length <= 0) {  
            $(".div_item").css('display', 'block');//如果什么都没填，跳出，保持全部显示状态  
            return;  
        }  
  
        $(".div_item").css('display', 'none');//如果填了，先将所有的选项隐藏  
  
        for (var i = 0; i < $(".div_item").length; i++) {  
            //模糊匹配，将所有匹配项显示  
            if ($(".div_item").eq(i).text().substr(0, $("#txt").val().length) == $("#txt").val()) {  
                $(".div_item").eq(i).css('display', 'block');  
            }  
        }  
    });  
  
    //项点击  
    $(".div_item").click(function () {  
        $("#txt").val($(this).text());  
    });  
