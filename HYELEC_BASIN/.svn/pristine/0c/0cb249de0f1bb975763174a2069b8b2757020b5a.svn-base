//获取当前时间
function getdate(){
	var myDate = new Date();
	var returndate = myDate.getFullYear();
	var month = myDate.getMonth()+1;
	var day = myDate.getDate();
	if(month<10)
		returndate +="-0"+month;
	else 
		returndate +="-"+month;
	if(day<10)
		returndate +="-0"+day; 
	else 
		returndate +="-"+day;
	return returndate;
}

// 页面复选框选中事件 
	function Func_GetSelectedItemCountAndValues(itemName) {
		var items = document.getElementsByName(itemName);
		var cnt = 0;
		var values = "";
		if (items && items.length) {
			for (var i = 0; i < items.length; i++) {
				if (items[i].checked) {
					cnt++;
					if (values == "") {
						values += items[i].value;
					} else {
						values += "," + items[i].value;
					}
				}
			}
		}
		var result = new Array();
		result[0] = cnt;
		result[1] = values;
		return result;
	}
	// 页面复选框选中事件 
	function Func_GetSelectedItemCountAndValuesName(itemName) {
		var items = document.getElementsByName(itemName);
		var cnt = 0;
		var values = "";
		var names = "";
		if (items && items.length) {
			for (var i = 0; i < items.length; i++) {
				if (items[i].checked) {
					cnt++;
					var selval = items[i].value;
					var selvalarr = selval.split("-,-");
					if (values == "") {
						if(selvalarr!=null && selvalarr.length>0){
							values += selvalarr[0];
						}
						if(selvalarr!=null && selvalarr.length>1){
							names += selvalarr[1];
						}
					} else {
						if(selvalarr!=null && selvalarr.length>0){
							values += ","+selvalarr[0];
						}
						if(selvalarr!=null && selvalarr.length>1){
							names += "," + selvalarr[1];
						}
					}
				}
			}
		}
		var result = new Array();
		result[0] = cnt;
		result[1] = values;
		result[2] = names;
		return result;
	}
	// 全选事件 
	function checkedAll(){
	    var arr = document.getElementsByName("item");
	     if(arr && arr.length>0){
	        for(var i=0;i<arr.length;i++){
	          arr[i].checked=document.getElementById("checkAll").checked;
	        }
	     }
	 }	
	function checkAllByName(itemName,obj){
		 var arr = document.getElementsByName(itemName);
	     if(arr && arr.length>0){
	        for(var i=0;i<arr.length;i++){
	          arr[i].checked=obj.checked;
	        }
	     }
	}