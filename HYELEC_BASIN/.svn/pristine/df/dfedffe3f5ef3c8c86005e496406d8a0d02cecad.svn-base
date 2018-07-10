<%@page import="com.lyht.util.DateUtil"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn" class="screen-desktop-wide device-desktop">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=no">
<title>实时预报展示</title>
</head>
<body>

	<div class="radio">
		<label> <input type="radio" name="time_radio_group"
			checked="checked" value="1" onchange="loadData()"> 1小时
		</label>
	</div>

	<div class="radio">
		<label> <input type="radio" name="time_radio_group" value="3"
			onchange="loadData()"> 3小时
		</label>
	</div>

	<div class="radio">
		<label> <input type="radio" name="time_radio_group" value="6"
			onchange="loadData()"> 6小时
		</label>
	</div>

	<div class="radio">
		<label> <input type="radio" name="time_radio_group" value="12"
			onchange="loadData()"> 12小时
		</label>
	</div>
	<div class="row">
		<div class="col-md-12" id="watrrd">
			<table class="table table-condensed bordered">
				<tr>
					<th>地区</th>
					<th>测站名称</th>
					<th>雨量</th>
					<th>水位</th>
				</tr>
			</table>
		</div>
	</div>

	<div class="row" style="width: 298px; height: 275px; overflow: auto;">
		<div class="col-md-12" id="watersmy">
			<table class="table table-condensed bordered" id="waters">
			</table>
		</div>
	</div>
</body>
</html>