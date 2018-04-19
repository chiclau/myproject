<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file= "/common/include.jsp" %>
<!DOCTYPE html >
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cats</title>
<script src="<c:url value='/scripts/common/jquery.validatebox.rules.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/common/easyui.common.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/web/main/index.js'/>" type="text/javascript" ></script>
<script src="<c:url value='/scripts/common/easyui.pagination.js'/>" type="text/javascript"></script>
<script src="<c:url value='/scripts/common/drawer.js'/>" type="text/javascript" ></script>
<script src="<c:url value='/scripts/common/resource.js'/>" type="text/javascript" ></script>
<script src="<c:url value='/scripts/web/constant/constant.js'/>" type="text/javascript" ></script>
<style type="text/css">
/* .region_north {
	height: 70px;
	overflow: hidden;
	background-color: #8989ff;
	background: url("./images/web/adminTopBack.jpg") repeat-x #a3c1e7;
}

.region_south {
	height: 70px;
	overflow: hidden;
	background-color: #8989ff;
	background: url("./images/web/adminTopBack.jpg") repeat-x #a3c1e7;
}

.region_north .user-info {
	position: absolute;
	top: 55px;
	right: 10px;
}

.region_north .user-info a {
	text-decoration: none;
}

.region_north .user-info a:hover {
	color: #6D7591;
}

.region_nav {
	height: 10px;
	top: 170px;
} */
/* 
#treeDemo li a.level0 {width:193px;height: 27px; text-align: center; display:block; background-color: #e5efff; border:1px #95b8e7 solid;}
#treeDemo li a.level0.cur {background-color: #ffe48d;border-color:#d4b243; }
#treeDemo li a.level0 span {display: block; color: #0e2d5f; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;}
#treeDemo li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;}
#treeDemo li span.button.switch.level0 {display:none;}
#treeDemo {margin:0; padding:0px; color:#333}
#treeDemo li a.level0 span.ico_open{background-image:url('../images/web/accordion_expand.gif') !important;}
#treeDemo a.level0 span.button.ico_close {background-image: url('../images/web/accordion_collapse.gif');background-repeat: no-repeat;background-position: 0px 3px;background-size: 16px 16px		}
#treeDemo a.level0 span.button.ico_open {background-image: url('../images/web/accordion_expand.gif');background-repeat: no-repeat;background-position: 0px 3px;background-size: 16px 16px;}
*/
#treeDemo{overflow:hidden;}
#treeDemo li a.level0 {width:195px;height: 25px;line-height:25px; text-align: center; display:block; background-color: #e5efff; border-top:1px #95b8e7 solid;}
#treeDemo li a.last{ border-bottom:1px #95b8e7 solid;}
#treeDemo li a.first{ border-top:none;}
#treeDemo li a.level0.cur {background-color: #ffe48d;border-color:#d4b243;   border-top: 1px solid #d4b243;border-bottom:1px solid #d4b243;}
#treeDemo li a.level0 span {display: block; color: #0e2d5f; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;text-align:left;background:url("../images/web/file-icon.png");background-position:6px 5px;background-size:16px 16px;background-repeat:no-repeat;padding-left:25px;line-height:20px;}
#treeDemo li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;}
#treeDemo li span.button.switch.level0 {display:none;}

#treeDemo li a.level0 span.ico_open{background-image:url('../images/web/accordion_expand.gif') !important;} 


#treeDemo li{padding:0;list-style:none; line-height:25px; text-align:left; white-space:nowrap; outline:0}
#treeDemo li a {padding:1px 3px 0 0;cursor:pointer; height:25px; color:#333; background-color: transparent;text-decoration:none; vertical-align:top; display: inline-block}
#treeDemo li li a{margin-left:-37px;padding-left:15px;width:185px;height:25px;line-height:25px;border-top:1px solid white;border-bottom:1px solid white;}
#treeDemo li a.curSelectedNode {padding-top:0px;padding-left:14px;background-color:#e0ecff; color:black; height:25px;line-height:25px; border-top:1px #95b8e7 solid; border-bottom:1px #95b8e7 solid; opacity:0.8;}
#treeDemo li span.button.center_docu{background-position:112px -18px}
#treeDemo li span.button.bottom_docu{background-position:112px -36px}
#treeDemo li span.button.ico_docu{margin-right:2px; background-position:-110px -32px; vertical-align:center !important; vertical-align:middle}
#treeDemo a.level0 span.button.ico_close {background-image: url('../images/web/accordion_collapse.gif');background-repeat: no-repeat;background-position: 0px 3px;background-size: 16px 16px;margin-right:-24px;}
#treeDemo a.level0 span.button.ico_open {background-image: url('../images/web/accordion_expand.gif');background-repeat: no-repeat;background-position: 0px 3px;background-size: 16px 16px;margin-right:-24px;}
#treeDemo li ul.line{background:none;}
#treeDemo {padding:0 !important;}
#treeDemo li span{line-height:25px;vertical-align:middle}
#treeDemo li li a:hover{background-color: #e0ecff;color: black;border-top: 1px #95b8e7 dashed;border-bottom: 1px #95b8e7 dashed;opacity: 0.8;padding-left:15px;}
#treeDemo li a.curSelectedNode:hover{padding-left:14px;border:1px #95b8e7 solid;}
</style>
</head>
<body class="easyui-layout" style="margin-left: 1px;margin-right: 1px;" data-options="fit:true">
	<div id="index">
		<input type="hidden" id="organId" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.organId}">
		<input type="hidden" id="organCode" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.organCode}">
        <input type="hidden" id="organName" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.organName}">
        <input type="hidden" id="username"  value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
        <input type="hidden" id="userId"  value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userId}">
        <input type="hidden" id="departId" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.departId}">
		<input type="hidden" id="departName" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.departName}">
		<input type="hidden" id="pwdIsValid" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.pwdIsValid}">
    </div>
        <div data-options="region:'north',minHeight:127,maxHeight:127,height:127">
	        <div class="index-nav">
				<div class="index-logo"></div>
				<div class="head-bg"></div>
				<div class="index-title">
					<ul>
						<li>
							<a href="../logout" id="loginOut">
								<img src="<c:url value='/images/web/logout.png'/>">
								<p>退出</p>
							</a>
						</li>
						<li id="user" style="width:120px">
							<a href="#" >
								<img src="<c:url value='/images/web/user.png'/>" >
								<marquee direction="left" scrollamount="2" scrolldelay="1">
									<p style="width:150px;">
										<c:out value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}"></c:out>，欢迎您！
									</p>
								</marquee>
							</a>
						</li>
						<li>
							<a id="setUp" href="#">
								<img src="<c:url value='/images/web/shezhi.png'/>">
								<p>设置</p>
							</a>
						</li>
						<li>
							<a id="addPrepare" href="<c:url value='/jsp/message/receiveMessage.jsp'/>" target="content">
								<img src="<c:url value='/images/web/zhannei.png'/>">
								<p>站内信息</p>
							</a>
						</li>
					</ul>
				</div>
				<div class="index-navbar" id="index-navbar">
					<ul id="css3menu">
					</ul>
				</div>
			</div>
        </div>
         
        <div id="west" title="&nbsp;菜单栏" data-options="iconCls:'icon-menu-pic',region:'west'" class="caidan"	 style="width:200px;">
        	<!--  导航内容 -->
<!-- 			<div id='wnav' class="easyui-accordion" data-options="fit:true,border:false">
			</div> -->
				<div class="zTreeDemoBackground left">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
		</div>
		
        <div data-options="region:'center'" id="content"> 
        	<!-- <iframe id="content" name="content" src="jsp/main/main.jsp" width="100%" height="100%" frameborder="0" scrolling="auto"></iframe> -->
        </div>
        
        <div data-options="region:'south'" style="height:35px;line-height:30px;">
        	<div class="index-footer">&copy;版权信息</div>
        </div> 
    <div id="dialogDiv">
    	<div id="dialog"></div>
    	<div id="setUpDialog"></div> 
	</div> 
</body>
</html>
