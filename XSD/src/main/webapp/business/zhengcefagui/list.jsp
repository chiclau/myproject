﻿<%@ page import="com.lyht.util.DateUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>长江经济带小水电基础信息管理系统</title>
		 <link href="<%=basePath%>common/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
		<script src="<%=basePath%>common/zui/lib/datetimepicker/datetimepicker.min.js"></script>
		<style>
		.pager > li.active > a, .pager > li.active > span, .pager > li.active > a:hover, .pager > li.active > span:hover{
		      background-color: #518cc2;
              border-color: #518cc2;
		}
		     table tr td{
		     
		      color:black;
		     }
		    
		     .layui-table, .layui-table-view{
		       margin:0px;
		     } 
		     input::-webkit-input-placeholder {
      
		        color: #757575!important;
		      
		    }
		       input{
      
		        color: #757575!important;
		      
		    }
		    .form-control{
		     padding: 5px 10px;
		    }
		    .layui-table-tool{
		     display:none;
		    }
		   .layui-laypage .layui-laypage-curr .layui-laypage-em{
             background: #518cc2;
            }
            .input-group-addon:not(:first-child):not(:last-child), .input-group-btn:not(:first-child):not(:last-child), .input-group .form-control:not(:first-child):not(:last-child){
             background: white;
            }
		</style>
	</head>
<body>	
	<div style="height:100%;width:100%;padding-left: 10px;padding-right: 10px;padding-top: 10px">
	   <div	class="row">
			<div class="col-md-12" style="padding-right:0px;padding-left:5px;">
			    <div id="tbar" class="row" style="padding-left:0px;text-align:right;">
			      <div class="col-md-4" style="padding-left:0px;text-align:right;padding-right:5px;">
			      <button class="btn btn-primary" style="float:left;background: #3eaf6f" type="button" id="addfile">新增</button>
			             <label for="exampleInputAddress1" style="display:inline-block;font-weight:1;">法规来源</label>
						      <select class="form-control" id="fgly_id" style="display:inline-block;width:147px">
						      </select>
			      </div>
			      <div class="col-md-2" style="padding-left:0px;text-align:right;">
			            <label for="exampleInputAddress1" style="display:inline-block;font-weight:1;">法规分类</label>
						      <select class="form-control" id="ssbm_id" style="display:inline-block;width:147px">
						      </select>
   
			      </div>
			      <div class="col-md-2" style="padding-left:0px;padding-right:0px;padding-bottom:5px;">
					<div class="input-group date form-date" style="margin-top:0px;" data-date=""data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
					  <input id ="startDate" class="form-control" size="16" style="padding-left:10px;background:white" type="text" value="" readonly="" placeholder="开始时间">
					  <span class="input-group-addon" style="background:white"><span class="icon-remove"></span></span>
					  <span class="input-group-addon" style="background:white"><span class="icon-calendar"></span></span>
					</div>
				</div>
				<div class="col-md-2" style="padding-bottom:5px;padding-right:0px;">
				    <div style="display:inline-block;width:8px;height:1px;background: black;position:absolute;left:1px;top:15px"></div>
					<div class="input-group date form-date" style="margin-top:0px;" data-date=""  data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
					  <input id = "endDate" class="form-control" size="16" style="padding-left:10px;background:white" type="text" value="" readonly="" placeholder="结束时间">
					  <span class="input-group-addon"  style="background:white"><span class="icon-remove"></span></span>
					  <span class="input-group-addon"  style="background:white"><span class="icon-calendar"></span></span>
					</div>
				</div>
					<div class="col-md-2" style="padding-right:0px">
						<div class="input-group ">
							<input type="text" id="searchName_zcfg" class="form-control"
								placeholder="关键字模糊查询"> <span class="input-group-btn">
								<button onclick = "serch()" class="btn" id="btn_ref_zcfg" type="button" style="background: #338ccc;color:white">
									<div class="visible-md visible-lg">
										<i class="icon icon-search"></i>&nbsp;查询
									</div>
							<!-- 		<div class="visible-xs visible-sm">
										<i class="icon icon-search"></i>
									</div> -->
								</button>
							<!-- 		<input type="button" onclick="xiugai()" value="修改密码" class="btn primary"> -->
							</span>
						</div>
					
					</div>
					<!-- 	<div class="col-md-2" style="padding-right:0px">
						<div class="input-group ">
									<input type="button" onclick="xiugai()" value="修改密码" class="btn primary">
				
						</div>
					
					</div> -->
				</div>
			</div>
		</div>
		<div class="row-fluid" style="height:40px;line-height:40px;width:100%;background: #3399CC;">
			<div style="color: #FFFFFF;"><i style="margin-left:13px;margin-right:13px;" class="icon icon-tasks"></i>政策法规信息</div>
		</div>
				<div class="btn-group pull-left visible-lg visible-md visible-sm" style="float: left;left: 70px">
		</div>
		<!-- 按钮工具条开始 -->
				
		<div style="height:calc(100% - 140px);">
			<div id="tbinfo_user_div" style="width:100%;height:calc(100% - 2px);">
			 <table class="layui-hide" id="testone" lay-filter="test"></table>
			  <script type="text/html" id="id">
 				{{d.LAY_TABLE_INDEX+1}}
				</script>
			 <script type="text/html" id="barDemo">
  				   <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
 				   <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
			</script>
		</div>
	</div>
	</div>
	</body>
<!-- 不要改变以下引用顺序 -->
<script src="../business/zhengcefagui/list.js"></script>

