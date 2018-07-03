$(function(){
	var url= basePath + "chanliu/chanliu!getSelect.action";
	common_ajax(null, url, function(response) {
		if (response.total == 0) {
		} else {
			$("#selectResult").append("<option > --请选择--</option>")
			for (var i = 0; i < response.rows.length; i++) {
				$("#selectResult").append("<option value='"+response.rows[i].PCH+"' > "+response.rows[i].PCH+"</option>")
			}
		}
	});
})
//下拉选择计算结果触发事件
$('#selectResult').on('change',function(){
//判断是否选取
    if($(this).val()){
        var selectText = $(this).find('option:selected').text();
        json="pch="+selectText;
    	var url= basePath + "chanliu/chanliu!getHistory.action";
    	common_ajax(json, url, function(response) {
    		if (response.total == 0) {
    		} else {//如果有数据
    			//$("#table1").empty();
    			var tr1="<tr id='tr1'>	<td rowspan='2' align='center'>序号</td> </tr>"+
    			"<td rowspan='2' align='center' style='width:190px'>日期</td>"+
    			"<td colspan='2' align='center' id='td1' class='addTd'>"+
    			"<input	type='text' name='czmc' id='txt'   style='width:100%'>"
    		}
    		
    	});
    }else{

    }
});

//查询输入的测站名称是否存在
function getStp() {
	var json = $("#info_form_cjfa").serialize();
	var stnm=$("#stnm").val();
	if(stnm==''){
		confirm("产流计算", "请输入测站名称")
		return false;
	}
	var url = basePath + "chanliu/chanliu!getStbprp.action"
	common_ajax(json, url, function(response) {
		// 如果total是0，则该测站名称不存在
		if (response.total == 0) {
			$("#m").show();// 提示
			$("#zhanming").empty();
			$("#stcd").val("");
		} else {
			for (var i = 0; i < response.rows.length; i++) {
				// alert(response.rows[i].STNM);
				$("#zhanming").html(response.rows[i].STNM);
				$("#stcd").val(response.rows[i].STCD);// 测站编码赋值
			}
			$("#zhanming").show();
			$("#m").hide();// 不提示
		}
	});
}

// 保存计算结果
function save() {
	var date1 = new Date($("#start").val());
	var pch=formatDateTime2(date1);
	var json = $("#info_form_cjfa").serialize();
	json+="&pch="+pch;
	var url = basePath + "chanliu/chanliu!save.action"
	confirm("产流计算", "您确定要保存计算结果吗？", "icon-save-sign", function(result) {
		if (result) {
			common_ajax(json, url, function(response) {
				var msg = new $.zui.Messager("消息提示：" + response.message, {
					placement : "center",
					type : "primary"
				});
				msg.show();
			});
		}
	});
}

// 计算
function jisuan() {
	var arr = []; // 声明一个数组用来存储雨面量
	arr = yumianliang();
	var res = document.getElementsByName("result");
	for (var i = 0; i <= res.length; i++) {
		$("#removeTr" + i + " td:last input:first").val(arr[i]);
	}

}
// 计算雨面量 返回结果数组
function yumianliang() {
	// **校验权重----START------
	var qz = document.getElementsByName("quanzhong");// 获取所有权重的Input，
	var yu = document.getElementsByName("yu");// 获取所有降雨量的Input，
	var y = 0;// 权重的和
	var yuliang = 0;// 降雨量的和，累加
	var arr1 = [];// 定义一个数组来接收雨量值,这个值可以是输入的也可以是查询的
	for (var i = 0; i < qz.length; i++) {
		if (isNaN(qz[i].value)) {
			confirm("产流计算", "权重:请输入数字")
			return false;
		}
		if (qz[i].value == '') {
			confirm("产流计算", "权重不能为空")
			return false;
		}
		y += Number(qz[i].value);
	} // yuliang+=Number(qz[i].value)*Number(yu[i].value); //乘积之后求和
	if (y > 1) { // 值超过1判断
		confirm("产流计算", "权重值不能超过1")
		return false;
	}
	if (y < 1) { // 值小于1判断
		confirm("产流计算", "权重值不能小于1")
		return false;
	}
	for (var j = 0; j < yu.length; j++) {
		var he = Number(yu[j].value);
		arr1.push(he);
	}
	var yuliang2 = [];
	var result = [];
	var num = Number($("#cols").val());
	if (arr1.length > 1) { // 加入判断，防止死循环崩溃
		for (var i = 0, len = arr1.length; i < len; i += num) {
			result.push(arr1.slice(i, i + num));
		}
	}
	var zonghe = [];
	// var re=0;//
	for (var i = 0; i < result.length; i++) {
		var re = 0;
		for (var j = 0; j < result[i].length; j++) {

			re = result[i][j] * Number(qz[j].value);// 量*权重 求和
			zonghe.push(re);
		}
	}
	// 将求和过后的数组在分割
	var q = [];
	for (var i = 0, len = zonghe.length; i < len; i += num) {
		q.push(zonghe.slice(i, i + num));
	}
	var p = [];
	for (var i = 0; i < q.length; i++) {
		p.push(sum(q[i]));
	}
	return p;
}

// 数组求和
function sum(arr) {
	var len = arr.length;
	if (len == 0) {
		return 0;
	} else if (len == 1) {
		return arr[0];
	} else {
		return arr[0] + sum(arr.slice(1));
	}
}
// 确定
function autocreate() {
	 stcd= $("#stcd").val(); // 测站编码
	 stnm=$("#stnm").val();//测站名称
	 beginDate  = $("#start").val(); // 开始时间
	 endDate  = $("#end").val(); // 结束时间
	 interval =$("#jiange").val(); //时间间隔

	var d1 = $("#start").val().toString();
	if(d1==""){
		alert("请选择时间段！")
		return false;
	}
	$(".add").remove();// 先把添加的移除，避免重复添加
	var num = $("#cols").val();// 列数
	// 动态添加列
	for (var i = 1; i < num; i++) {
		var td = "<td colspan='2' class='add' align='center'><input  id='txt"+i+"' style='width:100%' type='text' onclick='sel(this)' name='czmc'  placeholder='请输入测站名'>" +
				"<div id='div_txt"+i+"'><div id='div_items_txt"+i+"' class='div_items'> </div> </div>" +
				"</td>";
		var td2 = "<td  class='add'>权重</td><td  class='add'><input type='text' name='quanzhong' id='quanzhong"
				+ i + "' ></td>";
		var td3 = "<td colspan='2' class='add' align='center'><input  type='text' name='yu'  style='width:100%'></td>";
		// $(".addTd").after(td);
		$(".addTd").after(td);
		$(".addTd2").after(td2);
		$(".addTd4").after(td3);
	}
	// 先删除
	$(".removeTr").remove();
	var list = cha();
	var list1 = $("#tr3").children();
	if (list > 0) { // 移除第一个序号td
		$("#xuhao1").remove();
		for (var i = 0; i <=list; i++) {
			var tdXuhao = "<td>" + i + "</td>";
			var tra = "<tr class='removeTr' align='center' id='removeTr" + i
					+ "'>";
			var trb = "</tr>"
			var addTr = $("#tr3").html();
			$("#tr3").before(tra + tdXuhao + addTr + trb); //添加行的函数
		}
		$("#tr3").css('display', 'none');  ;
	}
	var shijian = document.getElementsByName("time");// 获取所有时间input，往里面添加时间
	var date1 = new Date($("#start").val()); // 开始时间
	var date2 = date1.getTime();// 将时间转换时间戳
	var curTime = formatDateTime(date1)// 转化时间格式
	var shu = Number($("#jiange").val()) * 60;// 分钟*秒
	var haomiao = shu * 1000;// 毫秒，增加时间的时候用毫秒
	var addTime = 0;
	for (var i = 0; i < shijian.length; i++) {// 下面的switch是生成时间都
		switch (i) {
		case 0:
			for(var j=0;j<num;j++){
				var x=2+j;
				var d1=date1.getTime();
				$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d1+j);
			}
			
			shijian[i].value = curTime;// 当第一个就是开始时间
			break;
		case 1:
			for(var j=0;j<num;j++){
				var x=2+j;
				var d2=date2 + haomiao;
				$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d2+j);
			}
			var t1 = formatDateTime(new Date(date2 + haomiao));
			shijian[i].value = t1;
			break;
		default:
			for(var j=0;j<num;j++){
				var x=2+j;
				var d3=date2 + addTime;
				$("#removeTr"+i+"").find("td:eq("+x+")").find("input").attr("id",d3+j);
			}
			var t2 = formatDateTime(new Date(date2 + addTime));
			shijian[i].value = t2;
			;
		}
		addTime += haomiao;// 每次叠加1个时间段
	}

}



// 计算时间差
function cha() {
	var date1 = new Date($("#start").val()).getTime(); // 开始时间
	var date2 = new Date($("#end").val()).getTime(); // 结束时间
	var date3 = date2 - date1; // 时间差的毫秒数
	// ------------------------------
	// 计算出相差天数
	var days = Math.floor(date3 / (24 * 3600 * 1000))
	// 计算出小时数
	var leave1 = date3 % (24 * 3600 * 1000) // 计算天数后剩余的毫秒数
	var hours = Math.floor(leave1 / (3600 * 1000))
	// 计算相差分钟数
	var leave2 = leave1 % (3600 * 1000) // 计算小时数后剩余的毫秒数
	var minutes = Math.floor(leave2 / (60 * 1000))
	// 计算相差秒数
	var leave3 = leave2 % (60 * 1000) // 计算分钟数后剩余的毫秒数
	var seconds = Math.round(leave3 / 1000)
	var fen = days * 24 * 60 + hours * 60 + minutes
	var shu = $("#jiange").val();
	var n = Number(shu)
	var tr = fen / n
	// alert(" 相差 "+days+"天 "+hours+"小时 "+minutes+" 分钟")
	// alert("总行数:"+tr)
	return tr;
}

// 时间格式化
var formatDateTime = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	var h = date.getHours();
	h = h < 10 ? ('0' + h) : h;
	var minute = date.getMinutes();
	minute = minute < 10 ? ('0' + minute) : minute;
	var second = date.getSeconds();
	second = second < 10 ? ('0' + second) : second;
	return y + '/' + m + '/' + d + ' ' + h + ':' + minute; // 返回时：分
};


//时间格式化
var formatDateTime2 = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	m = m < 10 ? ('0' + m) : m;
	var d = date.getDate();
	d = d < 10 ? ('0' + d) : d;
	var h = date.getHours();
	h = h < 10 ? ('0' + h) : h;
	var minute = date.getMinutes();
	minute = minute < 10 ? ('0' + minute) : minute;
	var second = date.getSeconds();
	second = second < 10 ? ('0' + second) : second;
	return y  + m + d  + h  + minute; // 返回时：分
};