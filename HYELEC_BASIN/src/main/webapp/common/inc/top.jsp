<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.lyht.Constants,java.util.ArrayList" %>
<nav class="navbar navbar-default navbar-fixed-top" style="margin-bottom:0px;" role="navigation">
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
		 	<div class="text-primary" style="margin-top:22px;">
		 		<font style="color:#fff">
		 			<h3>水资源分析模拟综合平台</h3>
		 		</font>
		 	</div>
		</a>
	</div>
	
	<!-- 导航菜单 -->
	<div class="collapse navbar-collapse navbar-collapse-example">
		<div style="float:left;margin-left:20px;">
			<ul class="nav navbar-nav navbar-right">
				<s:iterator value="#session.session_menu" id="menua">
					<s:if test="#menua.FCODE.length()<=4">
						<li class="dropdown">
							<a href="javascript:loadMenuUrl('<s:property value="#menua.MENU_URL"/>')"
								class="dropdown-toggle" data-toggle="dropdown">
								<div class="visible-lg visible-xs visible-sm visible-md">
									<i class="<s:property value="#menua.MENU_ICON"/>"></i>
									<span class="menu_font"><s:property value="#menua.MENU_NAME"/></span>
									<b class="caret menu_font"></b>
								</div>
							</a>
							
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
							</ul>
						</li>
					</s:if>
				</s:iterator>
			</ul>
		</div>
		
		<!-- 用户信息 -->
		<div class="btn-group navbar-right" style="margin-right: -2px;">
			<a href="#" class="btn dropdown-toggle btn_transparent btn_a_css" data-toggle="dropdown">
					<i class="icon icon-user"></i>
					${session_staff.staffName}
					<b class="caret menu_font"></b>
					<i class="icon icon-user"></i>
			</a>
			
			<ul class="dropdown-menu">
				<li class='dropdown-submenu'>
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
				</li>
				<%-- <li class="divider"></li>
				<li><a href="<%=basePath %>business/system/sysMenu/list.jsp">注册菜单</a></li> --%>
			    <li class="divider"></li>
			    <li>
			    	<a href="#" onclick="logout()">
				    	<i class="icon icon-off text-warning"></i>
				    	<span class="text-primary">注销系统</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
<%@include file="/common/inc/list.jsp"%>
<script>
	var golabelParam={menuFlag:"cezhanchaxun"};
	function loadMenuUrl(menuFlag){
		golabelParam.menuFlag=menuFlag;
		//business/consumer/modelmanage/modellist/list.jsp
		if(menuFlag=="cezhanchaxun"){
			//测站查询
			$("#nomapmaincontent").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#maincontent").show();
			$("#layer-change-tool").show();
			$("#mapsearchdiv").show();
			$("#cl-dashboard").hide();
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
		}else if(menuFlag=="shishiyubao"){
			//实时预报
			$("#nomapmaincontent").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#maincontent").show();
			$("#layer-change-tool").show();
			$("#mapsearchdiv").show();
			$("#cl-dashboard").hide();
			$("#left_tools_panel").show();
			$("#right_tools_panel").show();
			loadDivDataById(basePath+"main/ssybleft.jsp","#left_tools_panel");
			loadDivDataById(basePath+"main/ssybright.jsp","#right_tools_panel");
		}else if(menuFlag=="moxingliebiao"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/consumer/modelmanage/modellist/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="wodefangan"){
			$("#nomapmaincontent").hide();
			$("#maincontent").show();
			$("#layer-change-tool").show();
			$("#mapsearchdiv").show();
			$("#cl-dashboard").hide();
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").show();
			$("#maplayercontent").css("margin-top",$(window).height()-570);
			$("#maplayerbottom").show();
			loadDivDataById(basePath+"business/consumer/analysisjs/myplan/list.jsp","#maplayerbottom");
		}else if(menuFlag=="chuangjianfangan"){
			$("#nomapmaincontent").hide();
			$("#maincontent").show();
			$("#layer-change-tool").show();
			$("#mapsearchdiv").show();
			$("#cl-dashboard").hide();
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").show();
			$("#maplayercontent").css("margin-top",$(window).height()-570);
			$("#maplayerbottom").show();
			loadDivDataById(basePath+"business/consumer/analysisjs/cjfa/list.jsp","#maplayerbottom");
		}else if(menuFlag=="huiliujisuan"){
			$("#nomapmaincontent").hide();
			$("#maincontent").show();
			$("#layer-change-tool").show();
			$("#mapsearchdiv").show();
			$("#cl-dashboard").hide();
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").show();
			$("#maplayercontent").css("margin-top",$(window).height()-570);
			$("#maplayerbottom").show();
			loadDivDataById(basePath+"business/consumer/analysisjs/confluencejs/mainList9.jsp","#maplayerbottom");
		}else if(menuFlag=="chanliujisuan"){
			$("#nomapmaincontent").hide();
			$("#maincontent").show();
			$("#layer-change-tool").show();
			$("#mapsearchdiv").show();
			$("#cl-dashboard").hide();
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").show();
			$("#maplayercontent").css("margin-top",$(window).height()-570);
			$("#maplayerbottom").show();
			loadDivDataById(basePath+"business/consumer/analysisjs/chanliujs/mainList9.jsp","#maplayerbottom");
		}else if(menuFlag=="cezhanxinxi"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/consumer/basicData/stationInfo/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="yunxingshuju"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/consumer/runningData/mainList.jsp","#nomapmaincontent");
		}else if(menuFlag=="shuiwenshuju"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/consumer/hydrologicalData/mainList.jsp","#nomapmaincontent");
		}else if(menuFlag=="xingzhengquyudaimaguanli"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/system/addvcd/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="liuyushuixidaimaguanli"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/system/sysBasin/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="yonghuguanli"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/system/sysAccount/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="jueseguanli"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/system/sysRole/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="fenzuguanli"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/system/sysGroup/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="juesecaidanpeizhi"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/system/sysRole_sysMenu/list.jsp","#nomapmaincontent");
		}else if(menuFlag=="caidanguanli"){
			$("#left_tools_panel").hide();
			$("#right_tools_panel").hide();
			$("#maplayercontent").hide();
			$("#map").height($(window).height()-70);
			$("#mapsearchdiv").hide();
			$("#cl-dashboard").hide();
			$("#maincontent").hide();
			$("#layer-change-tool").hide();
			$("#nomapmaincontent").show();
			loadDivDataById(basePath+"business/system/sysMenu/list.jsp","#nomapmaincontent");
		}
	}
	function loadDivDataById(url,divId){
		$(divId).load(url);
	}
	$.ajaxSetup ({ 
      cache: false //关闭AJAX相应的缓存 
    });
    
</script>