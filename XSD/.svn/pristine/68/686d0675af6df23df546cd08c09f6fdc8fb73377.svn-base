<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html style="padding-top:0px;">
<head>
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" 	href="<%=basePath%>common/layui/css/layui.css">
<script>
   //项目路径
   var basePath = '<%=basePath%>';
</script>

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



<link href="<%=basePath%>business/system/home_page/sty.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>common/assets/css_/iconfont.css">
<!-- eachars -->

<link href="<%=basePath%>common/zui/lib/uploader/zui.uploader.min.css" rel="stylesheet">
<link href="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/bootstraptable/bootstrap-table.min.css"/>
<link rel="stylesheet" href="<%=basePath%>common/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css"/>
<link rel="stylesheet" href="<%=basePath%>common/js/treegrid/css/jquery.treegrid.css" type="text/css">

<link rel="stylesheet" type="text/css" href="<%=basePath%>common/js/combox/combox.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/icon/iconfont.css">
<%--//修改了源代码bootstrap-table.js中分页的样式，不引用min.js--%>
<script src="<%=basePath%>common/js/bootstraptable/bootstrap-table.js"></script>
<script src="<%=basePath%>common/js/bootstraptable/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=basePath%>common/js/bootstraptable/extensions/key-events/bootstrap-table-key-events.min.js"></script>
<script src="<%=basePath%>common/js/bootstrap-datetime/moment-with-locales(1).js"></script>
<script src="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>common/js/bootstrap-datetime/bootstrap-datetimepicker.zh-CN.js"></script>


<script src="<%=basePath%>common/layui/layui.js"></script>
<script src="<%=basePath%>common/layui/layui.all.js"></script>
<script type="text/javascript" src='<%=basePath %>common/js/combox/combox.js'></script>

<style>
      .col-md-6{
       height:340px;
      }
      .col-md-6:nth-child(2), .col-md-6:nth-child(4){
       padding-left:0px;
      }
      #item-one{
       color:white;
       background: #568fc3;
      }
      .list-group-item{
        padding: 5px 13px;
      }
      .color-one{
	      background: #518cc2!important;
	      color:white;
      }
      .style-one{
      white-space : normal nowrap;
      text-align:left!important;
      }
      table tr td:nth-child(1), table tr td:nth-child(3){
        background: #edf0f6;
        text-align:right;
      }
</style>
</head>

<body style="padding:15px;"> 
	<table class="layui-table" style="margin:0px;">
	  <colgroup>
	    <col width="200">
	    <col width="200">
	    <col width="200">
	    <col width="200">
	    <col>
	  </colgroup>
	  <tbody>
	    <tr>
	      <td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-search"></i>基础信息</td>
	    </tr>
	    <tr>
	      <td>项目名称：</td>
	      <td></td>
	      <td>省级：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>市级：</td>
	      <td></td>
	      <td>县级：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>一级支流：</td>
	      <td></td>
	      <td>所在流域：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>所在具体支流：</td>
	      <td></td>
	      <td>建设运营状态：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>装机容量：</td>
	      <td></td>
	      <td>开发方式：</td>
	      <td></td>
	    </tr>
	    <tr>
	      <td>投产时间：</td>
	      <td></td>
	      <td></td>
	       <td></td>  
	    </tr>
	    
	     <tr>
	      <td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-table"></i>工程信息</td>
	    </tr>
	    <tr>
	      <td>设计年发电量：</td>
	      <td></td>
	      <td>2017年发电量：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>总投资：</td>
	      <td></td>
	      <td>投资性质（所有制）：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>投资来源：</td>
	      <td></td>
	      <td>并网情况：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>综合利用情况：</td>
	      <td></td>
	      <td>总库容：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>坝高：</td>
	      <td></td>
	      <td>上网电价：</td>
	      <td></td>
	    </tr>
	     
	     <tr>
	      <td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-paste"></i>审批及管理情况</td>
	    </tr>
	    <tr>
	      <td class="style-one">是否有项目核准（审批）：</td>
	      <td></td>
	      <td>是否符合规划：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>是否符合规划环评：</td>
	      <td></td>
	      <td class="style-one">是否通过竣工环保验收：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>是否通过项目环评：</td>
	      <td></td>
	      <td>环评时间早于投产：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>是否有项目竣工报告：</td>
	      <td></td>
	      <td></td>
	      <td></td>
	    </tr>
	      <tr>
	      <td colspan="4" class="color-one style-one"><i style="margin-right:10px;" class="icon icon-carousel"></i>环保监管</td>
	    </tr>
	    <tr>
	      <td>生态流量泄放措施：</td>
	      <td></td>
	      <td>生态流量监控措施：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>过鱼设施：</td>
	      <td></td>
	      <td>鱼类增殖放流措施：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td>是否涉及自然保护区：</td>
	      <td></td>
	      <td>涉及核心区：</td>
	      <td></td>
	    </tr>
	     <tr>
	      <td class="style-one">坝下是否存在脱水干涸：</td>
	       <td></td>
	      <td>涉及缓冲区：</td>
	      <td></td> 
	    </tr>
	     <tr>
	      <td>涉及实验区：</td>
	       <td></td>
	      <td>电站投产时间与保护区设立时间先后关系：</td>
	      <td></td> 
	    </tr>
	    <tr>
	      <td>保护区未分区：</td>
	       <td></td>
	      <td></td>
	      <td></td> 
	    </tr>
	  </tbody>
	</table>	
</body>
</html>