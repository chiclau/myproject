<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String fgbm = request.getParameter("fgbm");//用request得到
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
<script src="<%=basePath%>common/jquery/jquery.cookie.js" charset="utf-8"></script>
<!-- zui js -->
<script src="<%=basePath%>common/zui/js/zui.min.js" charset="utf-8"></script>

<!-- 提示组件 -->
<script src="<%=basePath%>common/js/bootbox.js" charset="utf-8"></script>
<!-- bootstrapValidator js-->
<script src="<%=basePath%>common/js/bootstrapValidator/js/bootstrapValidator.min.js" charset="utf-8"></script>
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/icon/iconfont.css">
<script src="<%=basePath%>common/layui/layui.js"></script>
<script src="<%=basePath%>common/layui/layui.all.js"></script>
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
                 <!--   <div class="col-md-6 col-sm-6 col-xs-6">
					    <div class="layui-form-item">
						    <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">法规编码：</label>
						    <div class="layui-input-block">
						        <input type="text" name="xmmc" lay-verify="title" autocomplete="off" placeholder="请输入法规编码" class="layui-input inputclass-one">
						  </div>
					</div>		
                   </div> -->
                   <input type="hidden" id="fgbm">
                   <div class="col-md-6 col-sm-6 col-xs-6">
		               <div class="layui-form-item">
						    <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">*文号：</label>
							<div class="layui-input-block">
							    <input id = 'wh' type="text" name="sheng" lay-verify="required" autocomplete="off" placeholder="请输入文号" class="layui-input inputclass-one">
							</div>
					    </div>
                   </div>
                     <div class="col-md-6 col-sm-6 col-xs-6">
		                <div class="layui-form-item">
                                    <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">颁布日期：</label>
                                    <div class="layui-input-inline" >
                                        <input type="text" style="width:320px" lay-verify="required" placeholder="请点击获取时间" class="layui-input" id="pbrq">
                                    </div>
                             
                                </div>
                   </div>
           
            </div>
            <!--  <div class="row">
                   <div class="col-md-6 col-sm-6 col-xs-6">
					    <div class="layui-form-item">
						    <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">来源：</label>
						    <div class="layui-input-block">
						        <input type="text" name="xmmc" lay-verify="title" autocomplete="off" placeholder="请输入文号来源" class="layui-input inputclass-one">
						  </div>
					</div>		
                   </div>
                 
           
            </div> -->
               <div class="row">
                   <div class="col-md-6 col-sm-6 col-xs-6">
					    <div class="layui-form-item">
						     <label class="layui-form-label"  style="white-space:nowrap;width:109px;padding-top:11px;color:#333333;background:white">法规来源：</label>
									<div class="layui-input-block"  style="margin-left:107px;">
									    <select name="sffhghhp" class="selectCom" lay-verify="required" style="padding-left:7px;width:320px;color:#757575;height:38px;border:1px solid #D2D2D2" id = "fgly_id_add">
									    </select>			
									</div>
					</div>		
                   </div>
                   <div class="col-md-6 col-sm-6 col-xs-6">
		                <div class="layui-form-item">
                              <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">*标题：</label>
							<div class="layui-input-block">
							    <input type="text" name="sheng" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input inputclass-one" id = "bt">
							</div>
                             
                                </div>
                   </div>
           
            </div>
             <div class="row">
                   <div class="col-md-6 col-sm-6 col-xs-6">
					    <div class="layui-form-item">
						    <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">法规分类：</label>
						    <div class="layui-input-block">
						       <select name="sffhghhp" class="selectCom" lay-verify="required" style="padding-left:7px;width:320px;color:#757575;height:38px;border:1px solid #D2D2D2" id = "ssbm_id_add">
								</select>	
						  </div>
					</div>		
                   </div>
                   <div class="col-md-6 col-sm-6 col-xs-6">
							<div class="layui-upload">
							  <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button><span id="file"></span>
							</div>
					</div>		
                   </div>
            </div>
            <div class="row">
                   <div class="col-md-6 col-sm-6 col-xs-6">
		                <div class="layui-form-item">
                              <label class="layui-form-label"  style="width:109px;padding-top:11px;color:#333333;background:white">制定机关：</label>
							<div class="layui-input-block">
							    <input type="text" name="zdjg" lay-verify="required" autocomplete="off" placeholder="请输入指定机关" class="layui-input inputclass-one" id = "zdjg">
							</div>
                             
                                </div>
                   </div>
            </div>
             <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
		                <label class="layui-form-label">备注：</label>
				    <div class="layui-input-block">
				      <textarea name="desc" placeholder="请输入备注内容" class="layui-textarea" id = "bz"></textarea>
				    </div>
                   </div>
            </div>
             <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12" style="margin-top:20px;">
                 <div class="layui-form-item">
				    <div class="layui-input-block" id = 'div_id'>
				        <button  class="layui-btn " onclick = "close_zcfg()">取消</button>
				         <button class="layui-btn" name = "add" id ='test10' onclick="add_zcfg()">确认</button>
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
fgbm = '<%=fgbm %>';
</script>
<script src="../zhengcefagui/add.js"></script>
