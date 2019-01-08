	$(function(){
		//first("selectp","selectc","form1",0,0); //初始化省市区下拉数据
		initProv(); 
		$("#form1").bootstrapValidator({
            excluded: ':disabled',
            fields: {
                'regUserName': {
                    message: '账号没有校验',
                    validators: {
                        notEmpty: {
                            message: '账号不能为空'
                        }
                    }
                },'userPwd': {
                    message: '密码没有校验',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                },'newPwd': {
                    message: '重复密码没有校验',
                    validators: {
                        notEmpty: {
                            message: '重复密码不能为空'
                        }
                    }
                },'staffName': {
                    message: '真实姓名没有校验',
                    validators: {
                        notEmpty: {
                            message: '真实姓名不能为空'
                        }
                    }
                },'treeNmDept': {
                    message: '所属单位没有校验',
                    validators: {
                        notEmpty: {
                            message: '所属单位不能为空'
                        }
                    }
                },'linkPhone': {
                    message: '联系电话没有校验',
                    validators: {
                        notEmpty: {
                            message: '联系电话不能为空'
                        },regexp:{
                        	message:'请输入正确的联系电话',
                        	regexp:/^1[3|5|8]{1}[0-9]{9}$/
                        }
                    }
                }
            }
		}).on("success.form.bv", function(e) {
//			e.preventDefault(); // 去掉默认提交事件
			register();
		}).on("error.form.bv", function(e) {
//			e.preventDefault(); // 去掉默认提交事件
		});	
	});
	
	//加载省份
	function initProv(){
		$('#sel_1').empty();
		var url=basePath+"system/addvcd!listroot.action";
		$('#sel_1').append("<option value=''>请选择</option>");
		common_ajax(null, url, function(response){
			var rows=response.rows;
			for(var i=0;i<rows.length;i++){
				$('#sel_1').append("<option value='"+rows[i].ADDVCD+"'>"+rows[i].NAME+"</option>");
			}
		})
	}
	
	//省份与市区联动
	function selectCityArea(){
		$('#sel_2').empty();
		var prov=$('#sel_1').val();
		var url=basePath+"system/addvcd!loadCityData.action";
		$.post(url,{"prov":prov},function(data){
			var rows=JSON.parse(data).rows;
			for(var i=0;i<rows.length;i++){
				$('#sel_2').append("<option value='"+rows[i].ADDVCD+"'>"+rows[i].NAME+"</option>");
			}
		});
	}

	//验证账号是否唯一
	var userName;
	function verUserName(){
		var flag=false;
		var url=basePath+"system/sysuser!validateAccountInfo.action";
		var obj={
			"mSysUserFormBean.mSysUserInfoBean.userName":$("#regUserName").val()
		};
		$.ajax({
			data:obj,
			url:url,
			type: "POST",
			dataType:"json",
			success:function(response){
				if(response.success=="success"){
					flag=true;
					userName=$("#regUserName").val().trim();
				}else if(response.error=="error"){
					userName="";
					var msg = new $.zui.Messager("消息提示：账户名称不能重复!!!", {placement: "center",type:"primary"});
				    msg.show();	
				}
			}
		});
		return flag;
	}
	
	//验证密码是否重复
	var userPwd_;
	function retPwd(){
		var flag=false;
		var userPwd=$("#userPwd").val().trim();
		var newPwd=$("#newPwd").val().trim();
		if(userPwd==newPwd){
			flag=true;
			userPwd_=$("#userPwd").val().trim();
		}else{
			userPwd_="";
			var msg = new $.zui.Messager("消息提示：请输入正确密码!!!", {placement: "center",type:"primary"});
		    msg.show();	
		}
		return flag;
	}

	//注册用户信息
	function register(){
		var pro=$("#sel_1").find("option:selected").val();  //省份
		var city=$("#sel_2").find("option:selected").val(); //市区
		var obj={
			"mSysUserFormBean.mSysUserInfoBean.userName":userName //账户名称
			,"mSysUserFormBean.mSysUserInfoBean.userPwd":userPwd_ //用户密码
			,"mSysStaffFormBean.mSysStaffInfoBean.staffName":$("#staffName").val().trim() //真实姓名
			,"mSysStaffFormBean.mSysStaffInfoBean.treeNmDept": $("#treeNmDept").val().trim() //所属单位
			,"mSysStaffFormBean.mSysStaffInfoBean.staffDept":$("#staffDept").val().trim()  //部门
			,"mSysStaffFormBean.mSysStaffInfoBean.linkPhone":$("#linkPhone").val().trim() //联系电话
			,"mSysStaffFormBean.mSysStaffInfoBean.staffAddress":(pro+","+city).trim() //现居住地
		};
		var url=basePath+"register/register!getRegisterInfo.action";
		$.post(url,obj,function(result){ 
			var obj_=JSON.parse(result);
			if(obj_.flag=="success"){
				var msg = new $.zui.Messager("消息提示：注册成功！！！", {placement: "center",type:"primary"});
			    msg.show();
			    window.location.href=basePath+"login.jsp";
			}else if(obj_.flag=="error"){
				var msg = new $.zui.Messager("消息提示：注册！！！", {placement: "center",type:"primary"});
			    msg.show();
			}
		});
	}
	
	//返回登录
	function returnLogin(){
		window.location.href=basePath+"login.jsp";
	}