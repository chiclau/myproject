var flag = true;
        $("input[name='czmc']").on('compositionstart',function(){// compositionstart 在输入一段需要确认的文本如拼音to汉字、语音时会触发
            flag = false;
        })
        $("input[name='czmc']").on('compositionend',function(){//compositionend  在拼音选词完成、语音输入完毕时会触发
            flag = true;
        })
        $("input[name='czmc']").each(function(i){
        	
        }).on('input',function(){
            var _this = this;
            setTimeout(function(){
                if(flag){
                    console.log($(_this).val());
                    var json = "mStbprpFormBean.mStbprpInfoBean.stnm="+$(_this).val();
                	var url = basePath + "chanliu/chanliu!getStbprpMoHu.action"
                	common_ajax(json, url, function(response) {
                		var div="";
                		for (var i = 0; i < response.rows.length; i++) {
            			 div+="<div class='div_item' id='"+response.rows[i].STCD+"' onclick='on(this)''>"+response.rows[i].STNM+"</div>";
            			}
                		$("#div_items_txt").html(div);
                		
                	});
                }
            },1)
        })
 
     function sel(i){
    	 var flag = true;
         $("input[name='czmc']").on('compositionstart',function(){// compositionstart 在输入一段需要确认的文本如拼音to汉字、语音时会触发
             flag = false;
         })
         $("input[name='czmc']").on('compositionend',function(){//compositionend  在拼音选词完成、语音输入完毕时会触发
             flag = true;
         })
        	   var txtid=$(i).attr("id");
        	   inputId=txtid;
        	   var divid="div_"+txtid;
        	   var div_items="div_items_"+txtid;
        	   show(div_items);//显示
        	    $("input[name='czmc']").on('input',function(){
                    var _this = this;
                    setTimeout(function(){
                        if(flag){
                            console.log($(_this).val());
                            var json = "mStbprpFormBean.mStbprpInfoBean.stnm="+$(_this).val();
                        	var url = basePath + "chanliu/chanliu!getStbprpMoHu.action"
                        	common_ajax(json, url, function(response) {
                        		var div="";
                        		for (var i = 0; i < response.rows.length; i++) {
                    			 div+="<div class='div_item' id='"+response.rows[i].STCD+"' onclick='on(this)'' onmouseleave=onLeave(this)'>"+response.rows[i].STNM+"</div>";
                    			}
                        		$("#"+div_items+"").html(div);
                        		
                        	});
                        }
                    },1)
                })
        }

    //项点击  回显
    function on(i){
    	var par=$(i).parent().parent().attr("id");
    	var str=par.replace("div_","");
       $("#"+str+"").val($(i).text());  //选择的测站回显到输入框
        var stcd=$(i).attr("id");//获得测站编码
        var json = "mStbprpFormBean.mStbprpInfoBean.stcd="+stcd; //查询
    	var url = basePath + "chanliu/chanliu!list.action" //路径
    	common_ajax(json, url, function(response) {//ajax提交。回调函数
        	var shijian = document.getElementsByName("time");
    		cle(shijian)//清空input
    		for (var i = 0; i < response.rows.length; i++) {
				var date1=response.rows[i].TM.time;
				var d1=date1.toString();
				var newstr=d1.substring(0,d1.length-1);
				$("#"+date1+"").val(response.rows[i].DRP);//数据回显
			}
    		rem();//隐藏下拉DIV选项
    	});
    }   
    //鼠标移除div触发的方法    
    function onLeave(i){
    	 $(i).css('display', 'none');  
    }
    
     //弹出列表框  
    $("#txt").click(function () {  
        $("#div_items_txt").css('display', 'block');  
        return false;  
    });  
    function show(id){
    	   $("#"+id+"").css('display', 'block');  
    	   return false;  
    }

    function rem(){
    		  $(".div_items").css('display', 'none');  
    }
    //移入移出效果  
    $(".div_item").hover(function () {  
        $(this).css('background-color', '#1C86EE').css('color', 'white');  
    }, function () {  
        $(this).css('background-color', 'white').css('color', 'black');  
    });  
  
    //清空input
    function cle(shijian){
     	var date1 = new Date($("#start").val()); 
    	var date2 = date1.getTime();
    	var shu = Number($("#jiange").val()) * 60;// 分钟*秒
    	var haomiao = shu * 1000;
    	var num = $("#cols").val();
    	var addTime = 0;
    	for (var i = 0; i < shijian.length; i++) {// 下面的switch是生成时间都
    		switch (i) {
    		case 0:
    			for(var j=0;j<num;j++){
    				var x=2+j;
    				var d1=date1.getTime();
    				$("#"+d1+"").val("");
    				//$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d1+j);
    			}
    			break;
    		case 1:
    			for(var j=0;j<num;j++){
    				var x=2+j;
    				var d2=date2 + haomiao;
    				$("#"+d2+"").val("");
    			//	$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d2+j);
    			}
    			var t1 = formatDateTime(new Date(date2 + haomiao));
    			shijian[i].value = t1;
    			break;
    		default:
    			for(var j=0;j<num;j++){
    				var x=2+j;
    				var d3=date2 + addTime;
    				$("#"+d3+"").val("");
    				$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d3+j);
    			}
    			var t2 = formatDateTime(new Date(date2 + addTime));
    			shijian[i].value = t2;
    			;
    		}
    		addTime += haomiao;// 每次叠加1个时间段
    	}
    }
   
        
        