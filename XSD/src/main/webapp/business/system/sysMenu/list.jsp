﻿<%@ page language="java" pageEncoding="UTF-8"%>
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
		<title>菜单</title>
		 <style>
		 .fixed-table-pagination{
		  display:none!important;
		 }
		 #tbar{
		     margin-top: 3px;
		      margin-bottom: 5px;
		      padding-left:10px;
		      padding-right:10px;
		 }
		 #tbinfo_user_div{
		  padding-left:10px;
		  padding-right:10px;
		 }
		 
		 .input-group .form-control:first-child, .input-group-addon:first-child, .input-group-btn:first-child > .btn, .input-group-btn:first-child > .btn-group > .btn, .input-group-btn:first-child > .dropdown-toggle, .input-group-btn:last-child > .btn:not(:last-child):not(.dropdown-toggle), .input-group-btn:last-child > .btn-group:not(:last-child) > .btn 
		 {
		  color:black;
		 }
		 .form-control{
		  padding: 4px 10px;
		 }
		 input::-webkit-input-placeholder{
            color:black!important;
        }
       input::-moz-placeholder{  
           color:black!important;
       }
       input:-moz-placeholder{    
           color:black!important;
       }
       input:-ms-input-placeholder{  
           color:black!important;
       }
       a .icon, a [class^="icon-"], a [class*=" icon-"]:nth-child(4){
        color:black;
        position: static; 
       top: auto; 
        left: auto;
        font-size:16px;
       }
        a .icon, a [class^="icon-"], a [class*=" icon-"]:nth-child(4):hover{
        color:black!important;
       }
     .fixed-table-container tbody tr td:nth-child(9){
       text-align:center;
     }
    .icon icon-repeat{
       color:red!important;
     }
     .icon-refresh:before{
      color:black;
     }
		 </style>
		
	</head>
<body>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="container-fluid" style="height:100%;">
		<div class="row-fluid" style="height:30px;">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>功能列表</li>
					<li class="active">系统菜单管理</li>
				</ol>
			</h3>
		</div>
		<div class="row-fluid" style="height:calc(100% - 30px);">
			<!-- 按钮工具条开始 -->
			<div id="tbar" class="row-fluid btn-toolbar" style="height:35px;width:100%;">
				<div style="width:calc(100% - 150px);float:left;">
					<button class="btn btn-primary" id="btn_add" type="button">
						<div class="visible-md visible-lg">
							<i class="icon icon-file-o"></i>&nbsp;新增根节点
						</div>
						<div class="visible-xs visible-sm">
							<i class="icon icon-file-o"></i>
						</div>
					</button>
					<button class="btn" id="btn_basedata" type="button">
						<div class="visible-md visible-lg">
							<i class="icon icon-refresh"></i>&nbsp;刷新
						</div>
						<div class="visible-xs visible-sm">
							<i class="icon icon-refresh"></i>
						</div>
					</button>
				</div>
				<div style="width:150px;float:right;padding-left:10px;padding-right:10px;">
					<select id="select_pcode" class="form-control" style="width:150px;margin-left:10px;"
						onchange="sysMenu.refresh();">
						<option value="">显示全部数据</option>
					</select>
				</div>
				
			</div>
			<!-- 按钮工具条结束 -->
			<div class="row-fluid col-md-12" style="height:calc(100%);overflow:auto;">
				<table id="treeGrid" class="table-hover">
				</table>
			</div>
		</div>
	</div>
	</body>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.inc"%>
<script src="../business/system/sysMenu/def.js"></script>
<script src="../business/system/sysMenu/list.js"></script>
