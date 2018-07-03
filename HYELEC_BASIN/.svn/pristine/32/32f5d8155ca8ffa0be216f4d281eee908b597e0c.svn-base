<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form_riverWater" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<input type="hidden" id="stcd_river_"  name="mRiverFormBean.mRiverInfoBean.stcd" />
	<input type="hidden" id="laydatetime_"  name="mRiverFormBean.mRiverInfoBean.tm" />
	
	<div class="modal fade" id="edit_dialog_riverWater">
		<div class="modal-dialog modal-lg" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增河道水情信息</h4>
				</div>

				<div class="modal-body" style="padding-top: 8px;">
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="stcd_river"  class="form-control" 
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
									style="width: 256px; height: 30px;background-color:#fff;" readonly="readonly"
									id="tm_river" data-bv-group=".rowGroup"
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
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 水位(m)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="z_river" 
                                   name="mRiverFormBean.mRiverInfoBean.z" 
                                   value="<s:property value='mRiverFormBean.mRiverInfoBean.contractTitle' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="7"
                                   data-bv-notempty-message="水位不能为空"   
                                   data-bv-stringlength-max="7" data-bv-stringlength-message="不能超过7个字"
                                placeholder="请输入水位"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 断面最大流速(m/s)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="xsmxv_river" 
                                   name="mRiverFormBean.mRiverInfoBean.xsmxv" 
                                   value="<s:property value='mRiverFormBean.mRiverInfoBean.contractTitle' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="9"
                                   data-bv-notempty-message="断面最大流速不能为空"   
                                   data-bv-stringlength-max="9" data-bv-stringlength-message="不能超过9个字"
                                placeholder="请输入断面最大流速"
                            >
						</div>
						
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 水势：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="wptn_river" name="mRiverFormBean.mRiverInfoBean.wptn" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择水势">
                            	<option value="">请选择水势</option>
                            	<option value="4">落</option>
                            	<option value="5">涨</option>
                            	<option value="6">平</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测流方法：
						</label>
						<div class="col-md-4 rowGroup">
                             <select id="msqmt_river" name="mRiverFormBean.mRiverInfoBean.msqmt" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择水势">
                            	<option value="">请选择水势</option>
                            	<option value="1">水位流量关系曲线</option>
                            	<option value="2">浮标及溶液测流法</option>
                            	<option value="3">流速仪及量水建筑物</option>
                            	<option value="4">估算法</option>
                            	<option value="5">ADCP</option>
                            	<option value="6">电功率反推法</option>
                            	<option value="9">其它方法</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测积方法：
						</label>
						<div class="col-md-4 rowGroup">
                            <select id="msamt_river" name="mRiverFormBean.mRiverInfoBean.msamt" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择测积方法">
                            	<option value="">请选择测积方法</option>
                            	<option value="1">水位面积关系曲线</option>
                            	<option value="2">测深杆或测深锤、铅鱼</option>
                            	<option value="3">回声测深仪</option>
                            	<option value="9">其它方法</option>
							</select>
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 河水特征码：
						</label>
						<div class="col-md-4 rowGroup">
                             <select id="flwchrcd_river" name="mRiverFormBean.mRiverInfoBean.flwchrcd" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择河水特征码">
                            	<option value="">请选择河水特征码</option>
                            	<option value="1">干涸</option>
                            	<option value="2">断流</option>
                            	<option value="3">流向不定</option>
                            	<option value="4">逆流</option>
                            	<option value="5">起涨</option>
                            	<option value="6">洪峰</option>
                            	<option value="P">水电厂发电流量</option>
                            	<option value=" ">一般情况</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测速方法：
						</label>
						<div class="col-md-4 rowGroup">
                             <select id="msvmt_river" name="mRiverFormBean.mRiverInfoBean.msvmt" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择测速方法">
                            	<option value="">请选择测速方法</option>
                            	<option value="1">流速仪</option>
                            	<option value="2">浮标法</option>
                            	<option value="3">声学法</option>
                            	<option value="9">其它方法</option>
							</select>
						</div>
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 断面过水面积(m<sup>2</sup>)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="xsa_river" 
                                   name="mRiverFormBean.mRiverInfoBean.xsa" 
                                   value="<s:property value='mRiverFormBean.mRiverInfoBean.contractTitle' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="9"
                                   data-bv-notempty-message="断面过水面积不能为空"   
                                   data-bv-stringlength-max="9" data-bv-stringlength-message="不能超过9个字"
                                placeholder="请输入断面过水面积"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;margin-left: -70px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 流量(m<sup>3</sup>/s)：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="q_river" 
                                   name="mRiverFormBean.mRiverInfoBean.q" 
                                   value="<s:property value='mRiverFormBean.mRiverInfoBean.contractTitle' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="9"
                                   data-bv-notempty-message="流量不能为空"   
                                   data-bv-stringlength-max="9" data-bv-stringlength-message="不能超过9个字"
                                placeholder="请输入流量"
                            >
						</div>
						
						
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 断面平均流速(m/s)：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="xsavv_river" 
                                   name="mRiverFormBean.mRiverInfoBean.xsavv" 
                                   value="<s:property value='mRiverFormBean.mRiverInfoBean.contractTitle' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="5"
                                   data-bv-notempty-message="断面平均流速不能为空"   
                                   data-bv-stringlength-max="5" data-bv-stringlength-message="不能超过5个字"
                                placeholder="请输入断面平均流速"
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



