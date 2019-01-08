<%@ page language="java" pageEncoding="UTF-8"%>
<table>
				<tr>
					<td>请输入站名 :</td>
					<td>
						<input type="text" id="stnm1" class="form-control" style="display:inline;"
						name="mStbprpFormBean.mStbprpInfoBean.stnm" onchange="clearStep1Data()">
					</td>
					<td>
						<input
						type="button" name="" style="width:50px;"  value="查询" onclick="getStp()" class="btn btn-primary"> <input
						type="button" name="" value="保存计算结果" onclick="save()" class="btn btn-save">
						<input type="button" onclick="cl_excel()" id="chanliu_daochu" style="margin-left: 20px;" value="导出到excel"  class="btn btn-primary" >
					</td>
					<td>
						<input	type="hidden" id="stcd" name="stcd"> &nbsp; 站名：
					</td>
					<td>
						<font color="green " style="display: none;" id="zhanming"></font>
						<font color="red " style="display: none;" id="m">提示 : 测站名称不存在</font>
					</td>
					<td>
						站码：
					</td>
					<td>
						<font color="green " style="display: none;" id="zhanma"></font>
					</td>
					<td>
						洪号：
					</td>
					<td>
						<input type="text" name="hh" id="honghao" style="margin-left: 10px;">
					</td>
					<td>
						<div  style="display:none" id="show_result"> 计算结果查询<select id="selectResult" onchange="pchChange()"></select></div>
					</td>
				</tr>
				<tr>
					<td>
						雨量站个数 :
					</td>
					<td>
						<input type="text" class="" style="width:120px" name="" value="" id="cols">
					</td>
					<td>
						<input type="button" style="margin-left:3px;" value="确定" onclick="queding()"  > 
						<input type="button" value="计算" onclick="jisuan()" class="btn btn-primary" >
					</td>
					<td>
						开始时间：
					</td>
					<td>
						<div class="input-append date form_datetime">
							<input size="16" id="start" onchange="startTimeChange(this)" type="text" readonly> <span
							 class="add-on"><i class="icon-th"></i></span>
						</div>
					</td>
					<td>
						截止时间：
					</td>
					<td>
						<div class="input-append date form_datetime">
							<input size="16" id="end" type="text" readonly> <span
								class="add-on"><i class="icon-th"></i></span>
						</div>
					</td>
					<td>
						时间间隔：
					</td>
					<td>
						<input type="text" id="jiange" name="" value="">
					</td>
					<td>
						(时间精确到分钟)
					</td>
				</tr>
			</table>