<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.lyht.Constants,java.util.ArrayList" %>

<nav class="navbar navbar-default navbar-fixed-top"
 style="margin-bottom:0px;z-index:10000;" role="navigation">
	<!-- 导航头部 -->
	<div class="navbar-header">
		<!-- 移动设备上的导航切换按钮 -->
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse-example">
			<span class="sr-only">切换导航</span> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span> 
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		
		<!-- 品牌名称或logo -->
		<a href="../business/system/home_page/list.jsp">
		 	<div class="text-primary" style="margin-top:13px">
		 		<font style="color:#fff"> 
		 			<h3 style="display:inline-block;font-size:18px;margin-left:78px">长江经济带小水电基础信息管理系统</h3><br>
		 			<span style="position:relative;font-size:12px;margin-left:79px">SHUIDIANXINGMUPINGTAI
		 			     <img src="<%=basePath %>common/images/login/LOGO.png" style="position:absolute;left:-57px;top:-23px;display:inline-block;width:40px;height:40px;" alt="">
		 			</span>
		 		</font>
		 	</div>
		</a>
	</div>
	
	<!-- 导航菜单 -->
	<div class="collapse navbar-collapse navbar-collapse-example">
	  <%--  <!-- 用户信息 -->
		<div class="btn-group navbar-right" style="float:right">
			<a href="#" class="btn dropdown-toggle btn_transparent btn_a_css" data-toggle="dropdown">
				<div class="visible-lg">
					<i class="icon icon-user"></i>
					<c:choose>
						<c:when test="${sys_role.roleCode=='admins'}">
							<font class="no_weight">
								${sys_role.roleName}
							</font>
						</c:when> 
						<c:otherwise>
							<font class="no_weight">
								${session_staff.staffName}
							</font>
						</c:otherwise> 
					</c:choose>
					<b class="caret menu_font"></b>
				</div>
				<div class="visible-xs visible-sm visible-md">
					
				</div>
			</a>
			
		</div> --%>
		<div style="float:right;margin-left:20px;">
			<ul class="nav navbar-nav navbar-right">
				<s:iterator value="#session.session_menu" id="menua">
					<s:if test="#menua.FCODE.length()<=4">
						<li class="dropdown">
						 <s:if test="#menua.MENU_URL.length()>=2">
						   <a href="javascript:loadMenuUrl('<s:property value="#menua.MENU_URL"/>')"
								class="dropdown-toggle" >
								<div class="visible-lg visible-xs visible-sm visible-md">
									<%-- <i class="<s:property value="#menua.MENU_ICON"/>"></i> --%>
									 <s:if test="#menua.MENU_NAME == '平台首页'">
									 	<i class="iconfont icon-home"></i>
									 </s:if>
									 <s:if test="#menua.MENU_NAME == '基础信息'">
									 	<i class="iconfont icon-appstore"></i>
									 </s:if>
									 <s:if test="#menua.MENU_NAME == '环保统计'">
									 	<i class="iconfont icon-linechart"></i>
									 </s:if>
									 <s:if test="#menua.MENU_NAME == '数据检索'">
									 	<i class="iconfont icon-securityscan"></i>
									 </s:if>
									 <s:if test="#menua.MENU_NAME == '政策法规'">
									 	<i class="iconfont icon-read"></i>
									 </s:if>
									<span class="menu_font"><s:property value="#menua.MENU_NAME"/></span>
								</div>
							</a>
						 </s:if>
						 <s:if test="#menua.MENU_URL.length()<=1">
						   <a href="javascript:loadMenuUrl('<s:property value="#menua.MENU_URL"/>')"
								class="dropdown-toggle" data-toggle="dropdown">
								<div class="visible-lg visible-xs visible-sm visible-md">
									<%-- <i class="<s:property value="#menua.MENU_ICON"/>"></i> --%>
									<s:if test="#menua.MENU_NAME == '系统管理'">
									 	<i class="iconfont icon-setting"></i>
									 </s:if>
									<span class="menu_font"><s:property value="#menua.MENU_NAME"/></span>
									<b class="caret menu_font"></b>
								</div>
							</a>
						 </s:if>
							<ul class="dropdown-menu">
								<s:iterator value="#session.session_menu" id="menub">
									<s:if
										test="#menub.FCODE.length()<=7 && #menub.SUPER_CODE==#menua.FCODE">
										<li>
											<a href="javascript:loadMenuUrl('<s:property value="#menub.MENU_URL"/>');">
												<i class=" icon text-warning 
												    <s:property value="#menub.MENU_ICON"/>">
									    	    </i>
									    	    <span class="dropdown_menu_font_css">
									    	    	  <s:property value="#menub.MENU_NAME" />
									    	    </span>
											</a>
										</li>
									</s:if>
								</s:iterator>
				<li class="divider"></li>
				<!-- <li class='dropdown-submenu'>
					<a href='#'>主题</a>
					<ul class='dropdown-menu pull-left' id="themes">
						<li><a data-value="green" href="#">国网绿</a></li>
						<li><a data-value="default" href="#">蓝色</a></li>
						<li><a data-value="bluegrey" href="#">蓝灰色</a></li>
						<li><a data-value="brown" href="#">棕色</a></li>
						<li><a data-value="indigo" href="#">靛蓝</a></li>
						<li><a data-value="purple" href="#">紫色</a></li>
						<li><a data-value="yellow" href="#">黄色</a></li>
					</ul>
				</li> -->
			    <li>
			    	<a href="#" onclick="logout()">
				  <!--   	<i class="icon icon-off text-warning"></i> -->
				    	<span class="text-primary">注销系统</span>
					</a>
				</li>
				  <li>
			    	<a href="#" onclick="xiugai()">
				  <!--   	<i class="icon icon-off text-warning"></i> -->
				    	<span class="text-primary">修改密码</span>
					</a>
				</li>
			    <li>
			    	<a href="#" onclick="logout()">
				    	<!-- <i class="icon icon-off text-warning"></i> -->
				    	<span class="text-primary">
				    	<c:choose>
						<c:when test="${sys_role.roleCode=='admins'}">
							<font class="no_weight" style="line-height:22px;">
								${sys_role.roleName}
							</font>
						</c:when> 
						<c:otherwise>
							<font class="no_weight" style="line-height:22px;">
								${session_staff.staffName}
							</font>
						</c:otherwise> 
					</c:choose>
				    	</span>
					</a>
				</li>
			
				
				 <!-- 用户信息 -->
	<!-- 	<div class="btn-group navbar-right" style="float:right">
			<a href="#" class="btn dropdown-toggle btn_transparent btn_a_css" data-toggle="dropdown">
				<div class="visible-lg">
					<i class="icon icon-user"></i>
					
					<b class="caret menu_font"></b>
				</div>
				<div class="visible-xs visible-sm visible-md">
					
				</div>
			</a>
			
		</div> -->
							</ul>
						</li>
					</s:if>
				</s:iterator>
			</ul>
		</div>
			<input type="hidden" id="userCode" value="${userCode}">
			<input type="hidden" id="userPwd" value="${userPwd}">
	</div>
</nav>
<%@include file="/common/inc/list.jsp"%>
<script>
	function loadMenuUrl(menuFlag){
		if(menuFlag=="cezhanchaxun"){
			//测站查询
			menu_cezhanchaxun();
		}else if(menuFlag=="shishiyubao"){
			//实时预报
		}else if(menuFlag=="xingzhengquyudaimaguanli"){
			menuNoMapClickEvent(basePath+"business/system/addvcd/list.jsp");
		}else if(menuFlag=="liuyushuixidaimaguanli"){
			menuNoMapClickEvent(basePath+"business/system/sysBasin/list.jsp");
		}else if(menuFlag=="yonghuguanli"){
			menuNoMapClickEvent(basePath+"business/system/sysAccount/list.jsp");
		}else if(menuFlag=="jueseguanli"){
			menuNoMapClickEvent(basePath+"business/system/sysRole/list.jsp");
		}else if(menuFlag=="fenzuguanli"){
			menuNoMapClickEvent(basePath+"business/system/sysGroup/list.jsp");
		}else if(menuFlag=="juesecaidanpeizhi"){
			menuNoMapClickEvent(basePath+"business/system/sysRole_sysMenu/list.jsp");
		}else if(menuFlag=="caidanguanli"){
			menuNoMapClickEvent(basePath+"business/system/sysMenu/list.jsp");
		}else if(menuFlag=="pingtaishouye"){
			menuNoMapClickEvent(basePath+"business/system/home_page/list.jsp");
		}else if(menuFlag=="zhengcefagui"){
			menuNoMapClickEvent(basePath+"business/zhengcefagui/list.jsp");
		}else if(menuFlag=="findzhengcefagui"){
			menuNoMapClickEvent(basePath+"business/zhengcefagui/findList.jsp");
		}else if(menuFlag=="huanbaotongji"){
			menuNoMapClickEvent(basePath+"business/huanbaotongji/list.jsp");
		}else if(menuFlag=="jichuxinxi"){
			menuNoMapClickEvent(basePath+"business/jichuxinxi/list.jsp");
		}else if(menuFlag=="shujujiansuo"){
			menuNoMapClickEvent(basePath+"business/shujujiansuo/list.jsp");
		}
	}
	//无地图显示的菜单点击事件
	function menuNoMapClickEvent(url){
		$("#map-frame-content").hide();
		$("#nomap-frame-content").show();
		loadDiv("#nomap-frame-content",url);
	}
	
	function xiugai(){
		var userCode=$("#userCode").val();
		 layer.open({
	  		  type: 2, 
	  		  title: '修改',
	  		  area: ['900px', '480px'],
	  		  skin: 'layui-layer-molv' ,
	  		  icon: 6,
	  		  content: basePath+'business/zhengcefagui/xgpswd.jsp?userCode='+userCode,
	  	});
	}
</script>