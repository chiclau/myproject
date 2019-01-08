$(function (){
	initWjly("zcfg");
	initWjly("ssbm");
	if(fgbm != null && fgbm != ''){
		findOne(fgbm);
	}
})

function findOne(fgbm){
	$.ajax({
		url:basePath+"policy/zcfg!findOne.action",
		data:{"mZcfgInfoFormBean.zcfgInfo.fgbm":fgbm},
		type: "POST",
		dataType:"json",
		success:function(response){
			var data = response.data[0];
			$("#wh").val(data.wh)
			$("#fgly_id_add").val(data.ly)
			$("#ssbm_id_add").val(data.ssfl)
			$("#zdjg").val(data.zdjg)
			$("#bt").val(data.bt)
			$("#pbrq").val(data.pbrq)
			$("#bz").val(data.bz)
			$("#fgbm").val(data.fgbm)
			if(data.fjmc == null){
				$("#file").text('')
			}else{
				$("#file").text(data.fjmc)
			}
		}
	})
}
function close_zcfg(){
	parent.layer.closeAll();
}
function initWjly(condition){
	  $.ajax({
			url:basePath+"policy/zcfg!initWjly.action",
			data:{"condition":condition},
			type: "POST",
			dataType:"json",
			success:function(response){
				var data = response.rows;
				var html = '<option value = "0">请选择</option>';
				for (var i = 0; i < data.length; i++) {
					html += ' <option value = "'+data[i].nm+'">'+data[i].name+'</option>'
				}
				if(condition == "zcfg")$("#fgly_id_add").append(html);
				if(condition == "ssbm")$("#ssbm_id_add").append(html);
			}
		})
}

function add_zcfg(){
	 if($("#wh").val().length < 1){
		 layer.alert( '文号不能为空' )
         return false;
     }
	 if($("#bt").val().length < 1){
		 layer.alert( '标题不能为空' )
		 return false;
	 }
	 $.ajax({
			url:basePath+"policy/zcfg!save.action",
			data:{"mZcfgInfoFormBean.zcfgInfo.wh"  :$("#wh").val(),
				  "mZcfgInfoFormBean.zcfgInfo.ly"  :$("#fgly_id_add").val(),
				  "mZcfgInfoFormBean.zcfgInfo.ssfl":$("#ssbm_id_add").val(),
				  "mZcfgInfoFormBean.zcfgInfo.pbrq":$("#pbrq").val(),
				  "mZcfgInfoFormBean.zcfgInfo.bt"  :$("#bt").val(),
				  "mZcfgInfoFormBean.zcfgInfo.bz"  :$("#bz").val(),
				  "mZcfgInfoFormBean.zcfgInfo.fgbm"  :$("#fgbm").val(),
				  "mZcfgInfoFormBean.zcfgInfo.zdjg"  :$("#zdjg").val()
				 },
			type: "POST",
			dataType:"json",
			success:function(response){
				if(response.success == 'success'){
					layer.open({
				        type: 1
				        ,content: '<div style="padding: 20px 100px;">操作成功</div>'
				        ,btn: '关闭全部'
				        ,btnAlign: 'c' //按钮居中
				        ,shade: 0 //不显示遮罩
				        ,yes: function(){
				        	parent.layer.closeAll();
				        	parent.layui.table.reload('testone',{page:{curr:1}});
				        	parent.initHeight();
				        }
				      });
				}else{
					layer.open({
				        type: 1
				        ,content: '<div style="padding: 20px 100px;">操作失败</div>'
				        ,btn: '关闭全部'
				        ,btnAlign: 'c' //按钮居中
				        ,shade: 0 //不显示遮罩
				        ,yes: function(){
				        	parent.layer.closeAll();
				        }
				      });
				}
			}
		})
} 

layui.use('upload', function(){
	  var $ = layui.jquery
	  ,upload = layui.upload;
	//选完文件后不自动上传
	  upload.render({
	    elem: '#test8'
	    ,url: basePath+"policy/zcfg!saveZcfg.action"
	    ,auto: false
	    //,multiple: true
	     ,exts: 'pdf'
	    ,bindAction: '#test9'
    	,choose: function(obj){   
	    obj.preview(function(index, file, result){
		  $("#file").text(file.name)
		  $("#test10").css('display','none');//隐藏
		  $("#test9").css('display','block');//隐藏
	    })
    	}
    	,done: function(res){
	     if(res.msg == "success"){
	    	 add_zcfg();
	     }
	  }
  });
})


layui.use('laydate', function(){
  var laydate = layui.laydate;
  //执行一个laydate实例
  laydate.render({
    elem: '#pbrq' //指定元素
  });
});