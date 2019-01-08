$(function (){
	//inintmm();
})
$("#ymm").blur(function(){
/*	var state;
	var psw=$("#ymm").val();
	$.ajax({
		url:basePath+"system/sysuser!checkUserPwd.action",
		data:{"userCode":userCode,"psw":psw},
		type: "POST",
		dataType:"json",
		success:function(response){
			debugger;
			if(response.state='error'){//错误说明用户名和密码不匹配
				//原密码不正确，请核对原始密码
			//	$("#er_font").text("系统提示：原密码不正确，请核对原始密码。")
				state="error"
			}else if(response.state='success'){
				$("#er_font").hide();
				state="success";
			}
			
		}
	})*/
})

function checkUserPwd(){
	var psw=$("#ymm").val();
	$.ajax({
		url:basePath+"system/sysuser!checkUserPwd.action",
		data:{"userCode":userCode,"psw":psw},
		type: "POST",
		dataType:"json",
		success:function(response){
			if(response.state=='error'){//错误说明用户名和密码不匹配
				//原密码不正确，请核对原始密码
				 layer.alert( '原密码不正确，请核对原始密码' );
			}else if(response.state='success'){
				add_zcfg();//提交
				
			}
			
		}
	})
}
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

//确认
function add_zcfg(){
	 if($("#ymm").val().length < 1){
		 layer.alert( '原密码不能为空' )
         return false;
     }
	 if($("#xmm1").val().length < 1){
		 layer.alert( '新密码不能为空' )
		 return false;
	 }
	 if($("#xmm2").val().length < 1){
		 layer.alert( '请确认新密码' )
		 return false;
	 }
	 if($("#xmm1").val()!=$("#xmm2").val()){
		 layer.alert( '两次输入的密码不相同' )
		 return false;
	 }
	 $.ajax({
			url:basePath+"system/sysuser!updatePsw.action",
			data:{"userCode":userCode,"psw":$("#xmm1").val()},
			type: "POST",
			dataType:"json",
			success:function(response){
				if(response.success == 'success'){
					layer.open({
				        type: 1
				        ,content: '<div style="padding: 20px 100px;">修改成功</div>'+
				        	'<div style="padding: 20px 100px;">新密码：'+$("#xmm1").val()+'</div>'
				        ,btn: '确定'
				        ,btnAlign: 'c' //按钮居中
				        ,shade: 0 //不显示遮罩
				        ,yes: function(){
				        	parent.layer.closeAll();
				        	parent.layui.table.reload('testone',{page:{curr:1}});
				        //	parent.initHeight();
				        }
				      });
				}else{
					layer.open({
				        type: 1
				        ,content: '<div style="padding: 20px 100px;">修改失败</div>'
				        ,btn: '确定'
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