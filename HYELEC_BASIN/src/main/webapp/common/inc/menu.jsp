<%@ page language="java" pageEncoding="UTF-8"%>

<div id="mainmenu" class="col-md-2">
		<nav class="menu " data-toggle="menu" style="width: 100%">
		  <ul class="nav nav-primary">
			<li><a href="#"><i class="icon-th"></i> 主页</a></li>
			<li>
			    <a href="#"><i class="icon-user"></i> 系统管理</a>
			    <ul class="nav">
			    	<li><a href="#">组织机构</a></li>
			    	<li><a href="#">角色维护</a></li>
			    	<li><a href="#">用户维护</a></li>
			    	<li><a href="#">权限授权</a></li>
			    	
			    </ul>
			</li>
		    <li class="active show">
		      <a href="#"><i class="icon-time"></i> 编程实例</a>
		      <ul class="nav">
		        <li><a href="#" onclick='loadpage("demo/list.jsp")'>列表</a></li>
		        <li class="active"><a href="#" onclick='loadpage("demo/ist-ztree.jsp")'>ZTree+列表</a></li>
		        <li><a href="#" onclick='loadpage("demo/form.jsp")'>表单</a></li>


		      </ul>
		    </li>
		    <li><a href="#"><i class="icon-list-ul"></i> All</a></li>
		
		    <li>
		      <a href="#"><i class="icon-tasks"></i> Status</a>
		      <ul class="nav">
		        <li><a href="#"><i class="icon-circle-blank"></i> Ready</a></li>
		        <li><a href="#"><i class="icon-play-sign"></i> Ongoing</a></li>
		
		      </ul>
		    </li>
		  </ul>
		</nav>
</div>

