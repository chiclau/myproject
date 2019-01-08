(function ($) {
	$.fn.combox = function (options, param) {
		if (typeof options == 'string') {
            return $.fn.combox.methods[options](this, param);
        }
		//2.将调用时候传过来的参数和default参数合并
        options = $.extend({}, $.fn.combox.defaults, options || {});
        var offset = $(this).offset(); 
        var left = offset.left;
        var top = offset.top+$(this).height();
        options.left=options.left?options.left:left+"px";
        options.width=options.width?options.width:$(this).width()+"px";
        var boxDiv=$("<div class='combox-box'></div>");
        var divBody=$("<div class='combox-item-list'></div>");
        $(boxDiv).append(divBody);
        $(this).after(boxDiv);
        $(divBody).css("width",options.width);
        $(divBody).css("height",options.height);
        $(boxDiv).css("left",options.left);
        if(options.top){
        	$(boxDiv).css("top",options.top);
        }
        $(boxDiv).css("margin-top","-2px");
        $(this).attr("options",JSON.stringify(options));
        options.input=this;
        options.boxContent=boxDiv;
        options.bodyContent=divBody;
        if(options.isPager){
        	var pageDiv=$("<div class='combox-page'></div>");
        	$(pageDiv).css("width",options.width);
        	$(boxDiv).append(pageDiv);
        	var pageLeftDiv=$("<div class='combox-page-left'><div class='combox-page-left-left'></div><div class='combox-page-left-right'></div></div>");
        	var pageCenterDiv=$("<div class='combox-page-center'></div>");
        	var pageRightDiv=$("<div class='combox-page-right'><div class='combox-page-right-left'></div><div class='combox-page-right-right'></div></div>");
        	$(pageDiv).append(pageLeftDiv);
        	$(pageDiv).append(pageCenterDiv);
        	$(pageDiv).append(pageRightDiv);
        	options.pageContent=pageDiv;
        	$(pageDiv).find(".combox-page-left-left").on("click",function(){
        		firstPage(options);
        	});
        	$(pageDiv).find(".combox-page-left-right").on("click",function(){
        		prePage(options);
        	});
        	$(pageDiv).find(".combox-page-right-left").on("click",function(){
        		nextPage(options);
        	});
        	$(pageDiv).find(".combox-page-right-right").on("click",function(){
        		lastPage(options);
        	});
        }
        $(this).on('keyup',function(e){
        	if(e.keyCode!=37 && e.keyCode!=38 && e.keyCode!=39 && e.keyCode!=40 && e.keyCode!=13){
        		getDataList(options); 
        	}
        });
        $("body").on('click',function(e){
        	$(options.boxContent).hide();
        });
        $(divBody).on('mouseleave',function(){
			var selD=$(options.input).attr("data");
			var selI=selD?JSON.parse(selD):{};
			var selv=selI[options.label]?selI[options.label]:"";
			$(options.input).val(selv);
			$(this).find("div.combox-item.active").removeClass("active");
			if(selv && selv.length>0){
				$(this).find("div.combox-item[value='"+selv+"']").addClass("active");
			}
		});
        $(this).on('keydown',function(e){
        	if(e.keyCode==38){
        		//向上
        		var childrens=$(divBody).children("div.combox-item");
        		var selectChildrens=$(divBody).find("div.combox-item.active");
        		if(childrens==null || childrens.length<1){
            		return ;
            	}
        		if(selectChildrens==null ||selectChildrens.length<1){
        			$(childrens[0]).addClass("active");
        			var item = JSON.parse($(childrens[0]).attr("data"));
        			$(options.input).val(item[options.label]);
					$(options.input).attr("data",$(childrens[0]).attr("data"));
        			//options.selectItem=item;
    				if(options.select){
    					options.select(item);
    				}
        		}else{
        			var index=childrens.index(selectChildrens[0]);
        			$(selectChildrens).removeClass("active");
        			if(index==0){
        				//选中的是最后一个
        				var lastChild=$(div).children("div.combox-item:last-child");
        				lastChild.addClass("active");
        				var item = JSON.parse($(lastChild).attr("data"));
        				
        				$(options.input).val(item[options.label]);
    					$(options.input).attr("data",$(lastChild).attr("data"));
        				//options.selectItem=item;
        				if(options.select){
        					options.select(item);
        				}
        			}else{
        				$(childrens[index-1]).addClass("active");
        				var item = JSON.parse($(childrens[index-1]).attr("data"));
        				$(options.input).attr("data",$(childrens[index-1]).attr("data"));
        				//options.selectItem=item;
        				$(options.input).val(item[options.label]);
        				if(options.select){
        					options.select(item);
        				}
        			}
        		}
        	}else if(e.keyCode==40){
        		var childrens=$(divBody).children("div.combox-item");
        		var selectChildrens=$(divBody).find("div.combox-item.active");
        		if(childrens==null || childrens.length<1){
            		return ;
            	}
        		//向下
        		if(selectChildrens==null ||selectChildrens.length<1){
        			$(childrens[0]).addClass("active");
        			var item = JSON.parse($(childrens[0]).attr("data"));
        			$(options.input).attr("data",$(childrens[0]).attr("data"));
        			//options.selectItem=item;
    				$(options.input).val(item[options.label]);
    				if(options.select){
    					options.select(item);
    				}
        		}else{
        			var index=childrens.index(selectChildrens[0]);
        			$(selectChildrens).removeClass("active");
        			if(index==childrens.length-1){
        				//选中的是最后一个
        				$(childrens[0]).addClass("active");
            			var item = JSON.parse($(childrens[0]).attr("data"));
            			$(options.input).attr("data",$(childrens[0]).attr("data"));
            			//options.selectItem=item;
        				$(options.input).val(item[options.label]);
        				if(options.select){
        					options.select(item);
        				}
        			}else{
        				$(childrens[index+1]).addClass("active");
        				var item = JSON.parse($(childrens[index+1]).attr("data"));
        				$(options.input).attr("data",$(childrens[index+1]).attr("data"));
        				//options.selectItem=item;
        				$(options.input).val(item[options.label]);
        				if(options.select){
        					options.select(item);
        				}
        			}
        		}
        	}else if(e.keyCode==13){
        		$(options.boxContent).hide();
        	}
        });
        function request(options){
        	var data = {};
        	if(options.isPager){
        		data.pageNo=options.pageNo;
        		data.pageSize=options.pageSize;
        	}
        	var searchText=$(options.input).val();
        	searchText=searchText.replace(/'/g,"");
        	data.searchText=searchText;
        	$.ajax({
        		url:options.url,
        		type:'post',
        		data:data,
        		dataType:'json',
        		success:function(response){
        			var dataList = response[options.dataKey];
        			options.data=dataList;
        			options.totalPage=response[options.totalPageKey];
        			loadDataList(options);
        		}
        	});
        }
        function getDataList(options){
        	var oldValue = options.oldValue;
        	var newValue = $(options.input).val();
        	console.log("-------oldValue----"+oldValue+"--------newValue----"+newValue+"--istrue="+(oldValue!=newValue));
        	if(newValue!=null && newValue.length>0 
        			&& oldValue!=newValue){
        		options.oldValue=newValue;
        		if(options.url){
        			request(options);
                }else{
                	loadDataList(options);
                }
        	}else{
        		options.oldValue=newValue;
        		loadDataList(options);
        	}
        }
        function prePage(options){
        	if(parseInt(options.pageNo)>1){
        		options.pageNo=parseInt(options.pageNo)-1;
        		request(options);
        	}
        }
        function firstPage(options){
        	if(parseInt(options.pageNo)>1){
        		options.pageNo=1;
        		request(options);
        	}
        }
        function nextPage(options){
        	if(options.totalPage && parseInt(options.totalPage)>parseInt(options.pageNo)){
        		options.pageNo=parseInt(options.pageNo)+1;
        		request(options);
        	}
        }
        function lastPage(options){
        	if(options.totalPage && parseInt(options.pageNo)<parseInt(options.totalPage)){
        		options.pageNo=options.totalPage;
        		request(options);
        	}
        }
        function loadDataList(options){
        	$(options.boxContent).show();
        	$(options.bodyContent).empty();
        	var dataList = options.data;
        	if(dataList && dataList.length>0){
        		for(var i=0;i<dataList.length;i++){
        			var item = dataList[i];
        			if(item){
        				var text = item[options.label];
        				var value=item[options.value];
        				if(options.formatter){
        					var fitem = options.formatter(item);
        					text = fitem[options.label];
        					value=fitem[options.value];
        				}
        				var itemDiv = $("<div class='combox-item' value='"+value+"' data='"+JSON.stringify(item)+"'>"+text+"</div>");
        				$(itemDiv).on('click',function(){
        					var selD=$(this).attr("data");
        					var selI=selD?JSON.parse(selD):{};
        					$(options.input).val(selI[options.label]);
        					$(options.input).attr("data",selD);
        					//options.selectItem=item;
        					if(options.select){
        						options.select(selI);
        					}
        					$(options.boxContent).hide();
        				});
        				$(itemDiv).on('mouseenter',function(){
        	        		$(options.bodyContent).find("div.combox-item.active").removeClass("active");
        	        		$(this).addClass("active");
        	        		var selD=$(this).attr("data");
        					var selI=selD?JSON.parse(selD):{};
        					var selv=selI[options.label]?selI[options.label]:"";
        	        		$(options.input).val(selv);
        				});
        				$(options.bodyContent).append(itemDiv)
        			}
        		}
        		if(parseInt(options.pageNo)<2){
        			$(options.pageContent).find(".combox-page-left-left").addClass("disabled");
        			$(options.pageContent).find(".combox-page-left-right").addClass("disabled");
        		}else{
        			$(options.pageContent).find(".combox-page-left-left").removeClass("disabled");
        			$(options.pageContent).find(".combox-page-left-right").removeClass("disabled");
        		}
        		if(parseInt(options.pageNo)<parseInt(options.totalPage)){
        			$(options.pageContent).find(".combox-page-right-left").removeClass("disabled");
        			$(options.pageContent).find(".combox-page-right-right").removeClass("disabled");
        		}else{
        			$(options.pageContent).find(".combox-page-right-left").addClass("disabled");
        			$(options.pageContent).find(".combox-page-right-right").addClass("disabled");
        		}
        	}else{
        		$(options.bodyContent).append("<span>查询结果无数据</span>")
        	}
        }
	};
	$.fn.combox.methods={
		getValue:function(input){
			var data=$(input).attr("data");
			var prop=$(input).attr("options");
			var options=prop?JSON.parse(prop):{};
			var item = data?JSON.parse(data):{};
			options.value=options.value?options.value:"value";
			options.label=options.label?options.label:"label";
			return item[options.value]?item[options.value]:null;
		},
		getLabel:function(input){
			var data=$(input).attr("data");
			var prop=$(input).attr("options");
			var options=prop?JSON.parse(prop):{};
			var item = data?JSON.parse(data):{};
			options.value=options.value?options.value:"value";
			options.label=options.label?options.label:"label";
			return item[options.label]?item[options.label]:null;
		},
		getItem:function(input){
			var data=$(input).attr("data");
			var prop=$(input).attr("options");
			var options=prop?JSON.parse(prop):{};
			var item = data?JSON.parse(data):{};
			return item;
		},
		setValue:function(input,obj){
			var prop=$(input).attr("options");
			var options=prop?JSON.parse(prop):{};
			options.value=options.value?options.value:"value";
			options.label=options.label?options.label:"label";
			var data={};
			data[options.label]=obj.label;
			data[options.value]=obj.value;
			$(input).val(obj.label);
			$(input).attr("data",JSON.stringify(data));
		}
	};
	//6.默认参数列表
    $.fn.combox.defaults = {
			height: "300px",
			data:null,
			url:null,
			label:"label",
			value:"value",
			dataKey:"rows",
			totalKey:"totalPage",
			pageNo:1,
			pageSize:30,
			totalPage:null,
			isPager:false,
			formatter:null,//格式化选择项显示
			select:null //选中事件
	 } ;
})(jQuery);