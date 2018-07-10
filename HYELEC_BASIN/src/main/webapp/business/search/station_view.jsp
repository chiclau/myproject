<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>测站基础信息</title>
<%@include file="/common/inc/inc.inc"%> 
</head>
<body> 
	 <div id="station_div_hidden" style="height: 100%;width:height: 100%;padding: -50px;margin-top:-30px;" align="right">
	 <table class="table table-bordered table-striped">
      <tbody>
					<tr>
                        <td class="text-nowrap" width="120"><label>测站编码：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.stcd'/></td>
                        <td class="text-nowrap" width="120"><label>测站名称：</label></td>
						<td width="200" colspan="3"><s:property value='searchFormBean.sbprp.stnm'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>水系名称：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.hnnm'/></td>
                        <td class="text-nowrap" width="120"><label>流域名称：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.rvnm'/></td>
                        <td class="text-nowrap" width="120"><label>河流名称：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.bsnm'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>经度：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.lgtd1'/></td>
                        <td class="text-nowrap" width="120"><label>纬度：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.lttd1'/></td>
                        <td class="text-nowrap" width="120"><label>行政区划码：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.addvcd'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>站址：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.stlc'/></td>
                        <td class="text-nowrap" width="120"><label>基面名称：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.dtmnm'/></td>
                        <td class="text-nowrap" width="120"><label>基面高程：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.dtmel'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>基面修正值：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.dtpr'/></td>
                        <td class="text-nowrap" width="120"><label>站类：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.sttp'/></td>
                        <td class="text-nowrap" width="120"><label>报汛等级：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.frgrd'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>建站年月：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.esstym'/></td>
                        <td class="text-nowrap" width="120"><label>始报年月：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.bgfrym'/></td>
                        <td class="text-nowrap" width="120"><label>隶属单位：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.atcunit'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>管理单位：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.admauth'/></td>
                        <td class="text-nowrap" width="120"><label>领导机关：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.locality'/></td>
                        <td class="text-nowrap" width="120"><label>测站岸别：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.stbk'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>测站方位：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.stazt'/></td>
                        <td class="text-nowrap" width="120"><label>至河口距离：</label></td>
						<td width="200"  ><s:property value='searchFormBean.sbprp.dstrvm'/></td>
						<td class="text-nowrap" width="120"><label>集水面积：</label></td>
						<td width="200"  ><s:property value='searchFormBean.sbprp.drna'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>测站方位：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.stazt'/></td>
                        <td class="text-nowrap" width="120"><label>至河口距离：</label></td>
						<td width="200"  ><s:property value='searchFormBean.sbprp.dstrvm'/></td>
						<td class="text-nowrap" width="120"><label>集水面积：</label></td>
						<td width="200"  ><s:property value='searchFormBean.sbprp.drna'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>拼音码：</label></td>
						<td width="200"><s:property value='searchFormBean.sbprp.phcd'/></td>
                        <td class="text-nowrap" width="120"><label>田间持水量：</label></td>
						<td width="200"  ><s:property value='searchFormBean.sbprp.fieldcap'/></td>
						<td class="text-nowrap" width="120"><label>启用标识：</label></td>
						<td width="200"  ><s:property value='searchFormBean.sbprp.usesymb'/></td>
					</tr>
					<tr>
                        <td class="text-nowrap" width="120"><label>备注：</label></td>
						<td width="600" colspan="5"><s:property value='searchFormBean.sbprp.comments'/></td>
					</tr>
</tbody>
    </table>
</body>
</html> 



