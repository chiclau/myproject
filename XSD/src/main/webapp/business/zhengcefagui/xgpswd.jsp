<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String userCode = request.getParameter("userCode");//用request得到
%>
<!DOCTYPE html>
<html style="padding-top:0px;">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/layui/css/layui.css">


<!-- zui css -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/zui/css/zui.css">
<!--bootstrap 样式 -->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/js/bootstrapValidator/css/bootstrapValidator.css">

<link rel="stylesheet" type="text/css" id="zui_theme_link_css"	href="<%=basePath%>common/zui/css/zui-theme-green.css">
<!--自定义样式-->
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/inc/css.css"> 

<!-- jquery js -->
<script src="<%=basePath%>common/jquery/jquery-1.11.3.min.js" charset="utf-8"></script>
<%-- <script src="<%=basePath%>common/jquery/jquery.cookie.js" charset="utf-8"></script> --%>
<!-- zui js -->
<script src="<%=basePath%>common/zui/js/zui.min.js" charset="utf-8"></script>

<!-- 提示组件 -->
<script src="<%=basePath%>common/js/bootbox.js" charset="utf-8"></script>
<!-- bootstrapValidator js-->
<%-- <script src="<%=basePath%>common/js/bootstrapValidator/js/bootstrapValidator.min.js" charset="utf-8"></script>
<script src="<%=basePath%>common/js/bootstrapValidator/js/language/zh_CN.js" charset="utf-8"></script>
<!--zui，下拉选择查找chosen-->
<link href="<%=basePath%>common/zui/lib/chosen/chosen.min.css" rel="stylesheet">
<script src="<%=basePath%>common/zui/lib/chosen/chosen.min.js" charset="utf-8"></script>
<!--日期选择-->
<script src="<%=basePath%>common/datejs/laydate.dev.js" charset="utf-8"></script>
<script src="<%=basePath%>common/zui/lib/datetimepicker/datetimepicker.min.js" type="text/javascript" charset="utf-8" ></script>
<!-- 自定义函数及变量 -->
<script src="<%=basePath%>common/inc/js.js" charset="utf-8"></script>
<link href="<%=basePath%>business/system/home_page/sty.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/assets/css_/iconfont.css">
<link href="<%=basePath%>common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<link href="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/bootstraptable/bootstrap-table.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/combox/combox.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/icon/iconfont.css"> --%>
<script src="<%=basePath%>common/layui/layui.js"></script>
<%-- <script src="<%=basePath%>common/layui/layui.all.js"></script> --%>
<script type="text/javascript" src='<%=basePath %>common/js/combox/combox.js'></script>
<style>
     #layui-laydate100001{
      left:572px!important;
     }
</style>
</head>

<body>
<div style="height:300px;width:100%;">
	 <div class="container-fluid">
            <div class="row" style="margin-top: 20px;">
                   <input type="hidden" id="fgbm">
                   <div class="col-md-6 col-sm-6 col-xs-6" >
		               <div class="layui-form-item">
						    <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">*原密码：</label>
							<div class="layui-input-block">
							    <input id = 'ymm' type="password" name="sheng" lay-verify="required" autocomplete="off" placeholder="请输入原密码" class="layui-input inputclass-one">
							</div>
								
					    </div>
                   </div>
              <div class="col-md-6 col-sm-6 col-xs-6" >
              	<font  color="red " id="er_font" style="display: none;">系统提示：原密码不正确，请核对原始密码。</font>
              </div>
            </div>
            <div class="row">
                   <div class="col-md-6 col-sm-6 col-xs-6">
		                <div class="layui-form-item">
                              <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">*新密码：</label>
							<div class="layui-input-block">
							    <input type="password" name="sheng" lay-verify="required" autocomplete="off" placeholder="请输入新密码" class="layui-input inputclass-one" id = "xmm1">
					
							</div>
                             
                                </div>
                   </div>
           
            </div>
             <div class="row">
                   <div class="col-md-6 col-sm-6 col-xs-6">
		                <div class="layui-form-item">
                              <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">*再次确认：</label>
							<div class="layui-input-block">
							    <input type="password" name="sheng" lay-verify="required" autocomplete="off" placeholder="请再次确认新密码" class="layui-input inputclass-one" id = "xmm2">
							</div>
                             
                                </div>
                   </div>
           
            </div>
             <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12" style="margin-top:20px;">
                 <div class="layui-form-item">
				    <div class="layui-input-block" id = 'div_id'>
				         <button class="layui-btn" name = "add" id ='test10' onclick="checkUserPwd()">确认</button>
					         <button  class="layui-btn " onclick = "close_zcfg()" style="margin-left: 50px;">取消</button>
					    <button class="layui-btn" id = "test9" style="display: none;margin-left: 80px;margin-top: -38px;">确认</button>
				    </div>
				  </div>
				  </div>
             </div>
            
        </div>
        
</div>
</body>
<!-- 不要改变以下引用顺序 -->
<script type="text/javascript">
basePath = '<%=basePath %>';
userCode = '<%=userCode %>';
</script>
<script src="../zhengcefagui/xgpswd.js"></script>
