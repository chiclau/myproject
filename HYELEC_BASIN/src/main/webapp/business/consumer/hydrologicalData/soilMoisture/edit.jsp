<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form_soilMoisture" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<input type="hidden" id="stcd_soil_" name="mSoilFormBean.mSoilInfoBean.stcd"/>
	<input type="hidden" id="tm_soil_" name="mSoilFormBean.mSoilInfoBean.tm"/>
	<input type="hidden" id="exkey_soil" name="mSoilFormBean.mSoilInfoBean.exkey"/>
	
	<div class="modal fade" id="edit_dialog_soilMoisture">
		<div class="modal-dialog modal-lg" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增土壤墒情</h4>
				</div>

				<div class="modal-body" style="padding-top: 8px;">
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="stcd_soil" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择测站名称">
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 时间：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydatetime" autofocus="autofocus"
									style="width: 236px; height: 30px;background-color:#fff;" readonly="readonly"
									id="tm_soil" data-bv-group=".rowGroup"
									required
									data-bv-notempty-message="时间不能为空"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
									</span>
								</label>
							</a>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 土壤含水率测法：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="slmmmt_soil" name="mSoilFormBean.mSoilInfoBean.slmmmt" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择土壤含水率测法">
                            	<option>请选择土壤含水率测法</option>
                            	<option value="1">烘干法</option>
                            	<option value="2">中子水分仪法</option>
                            	<option value="3">时域反射法</option>
                            	<option value="4">张力计法</option>
                            	<option value="5">频域法</option>
                            	<option value="9">其它方法</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 垂线平均含水率：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="vtavslm_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.vtavslm" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="垂线平均含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入垂线平均含水率"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 表层含水率：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="srlslm_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.srlslm" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="表层含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入表层含水率"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 10cm深度含水率：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="slm10_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.slm10" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="10cm深度含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入10cm深度含水率"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 20cm深度含水率：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="slm20_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.slm20" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="20cm深度含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入20cm深度含水率"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 30cm深度含水率：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="slm30_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.slm30" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="30cm深度含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入30cm深度含水率"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 40cm深度含水率：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="slm40_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.slm40" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="40cm深度含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入40cm深度含水率"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 50cm深度含水率：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="slm50_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.slm50" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="50cm深度含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入50cm深度含水率"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 60cm深度含水率：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="slm60_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.slm60" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="60cm深度含水率不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入60cm深度含水率"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 作物种类：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="crpty_soil" name="mSoilFormBean.mSoilInfoBean.crpty" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择作物种类">
                            	<option>请选择作物种类</option>
                            	<option value="0">白地</option>
                            	<option value="1">小麦</option>
                            	<option value="2">水稻</option>
                            	<option value="3">春播杂粮</option>
                            	<option value="4">夏播杂粮</option>
                            	<option value="5">薯类</option>
                            	<option value="6">棉花</option>
                            	<option value="7">油菜</option>
                            	<option value="8">甘蔗</option>
                            	<option value="9">其他作物</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 作物生长期：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="crpgrwprd_soil" name="mSoilFormBean.mSoilInfoBean.crpgrwprd" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择作物生长期">
                            	<option>请选择作物生长期</option>
                            	<option value="0">白地</option>
                            	<option value="1">播种期</option>
                            	<option value="2">幼苗期</option>
                            	<option value="3">成长期</option>
                            	<option value="4">开花结果期</option>
                            	<option value="5">黄熟收割期</option>
                            	<option value="6">其他</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 作物受灾原因：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="hitrsn_soil" name="mSoilFormBean.mSoilInfoBean.hitrsn" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择作物受灾原因">
                            	<option>请选择作物受灾原因</option>
                            	<option value="0">生长正常</option>
                            	<option value="1">干旱</option>
                            	<option value="2">洪涝</option>
                            	<option value="3">大风</option>
                            	<option value="4">霜冻</option>
                            	<option value="5">冰雹</option>
                            	<option value="6">其他</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 作物受灾程度：
						</label>
						<div class="col-md-4 rowGroup">
                           <select id="hitext_soil" name="mSoilFormBean.mSoilInfoBean.hitext" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择作物受灾程度">
                            	<option>请选择作物受灾程度</option>
                            	<option value="0">未受灾</option>
                            	<option value="1">轻度受灾</option>
                            	<option value="2">中度受灾</option>
                            	<option value="3">严重受灾</option>
                            	<option value="4">绝收</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 土壤类别：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="sltp_soil" name="mSoilFormBean.mSoilInfoBean.sltp" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择土壤类别">
                            	<option>请选择土壤类别</option>
                            	<option value="0">其他</option>
                            	<option value="1">沙土</option>
                            	<option value="2">壤土</option>
                            	<option value="3">粘土</option>
                            	<option value="4">壤砂土</option>
                            	<option value="5">砂壤土</option>
                            	<option value="6">粘壤土</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 土壤质地：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="slpq_soil" name="mSoilFormBean.mSoilInfoBean.slpq" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择土壤质地">
                            	<option>请选择土壤质地</option>
                            	<option value="11">粗砂土</option>
                            	<option value="12">细砂土</option>
                            	<option value="13">面砂土</option>
                            	<option value="21">砂粉土</option>
                            	<option value="22">粉土</option>
                            	<option value="23">粉壤土</option>
                            	<option value="24">粘壤土</option>
                            	<option value="25">砂黏土</option>
                            	<option value="31">粉黏土</option>
                            	<option value="32">壤黏土</option>
                            	<option value="33">黏土</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 干土层厚度(cm)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="drsld_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.drsld" 
                                   value="<s:property value='mSoilFormBean.mSoilInfoBean.contractTitle' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="干土层厚度不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入干土层厚度"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 灌溉相隔天数(d)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="irrintv_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.irrintv" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="3"
                                   data-bv-notempty-message="灌溉相隔天数不能为空"   
                                   data-bv-stringlength-max="3" data-bv-stringlength-message="不能超过3个字"
                                placeholder="请输入灌溉相隔天数"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 降雨相隔天数(d)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="pintv_soil" 
                                   name="mSoilFormBean.mSoilInfoBean.pintv" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="3"
                                   data-bv-notempty-message="降雨相隔天数不能为空"   
                                   data-bv-stringlength-max="3" data-bv-stringlength-message="不能超过3个字"
                                placeholder="请输入降雨相隔天数"
                            >
						</div>
					</div>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="submit" id="btn_save" class="btn btn-primary"
						style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 保存
					</button>

					<button type="button" class="btn btn-large"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -5px;">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>

</form>



