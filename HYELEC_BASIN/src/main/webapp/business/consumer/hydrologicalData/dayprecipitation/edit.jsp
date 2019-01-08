<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form_dayprecipitation" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="edit_dialog_dayprecipitation">
		<div class="modal-dialog modal-lg" style="width: 500px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增日降水量</h4>
				</div>
				<input type="hidden" id="nm_daypptn_"  name="stPptnDayRFormBean.stPptnDayRInfoBean.nm" />
				<input type="hidden" id="stcd_daypptn_"  name="stPptnDayRFormBean.stPptnDayRInfoBean.stcd" />
				<input type="hidden" id="laydate_daypptn_tm_"  name="stPptnDayRFormBean.stPptnDayRInfoBean.tm" />

				<div class="modal-body" style="padding-top: 8px;">
					<div class="form-group" style="margin-top: 10px;height:38px;line-height:28px;">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
						</label>
						<div class="col-md-9 rowGroup">
							<select id="stcd_daypptn" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择测站名称">
							</select>
						</div>
					</div>
					<div class="form-group" style="line-height:30px;height:40px;">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 时间：
						</label>
						<div class="col-md-9 rowGroup">
                             <a class='input-group date' style="float: left;left:10px;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydatetime" autofocus="autofocus"
									style="width: 306px; height: 32px;background-color:#fff;" readonly="readonly"
									id="tm_daypptn" data-bv-group=".rowGroup"
									required
									data-bv-notempty-message="时间不能为空"
									placeholder="请选择时间"> <span  class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
									</span>
								</label>
							</a>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 日降水量：
						</label>
						<div class="col-md-9 rowGroup">
							<input type="text" class="form-control" id="dyp_daypptn" 
                                   name="stPptnDayRFormBean.stPptnDayRInfoBean.dyp" 
                                   value="" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="5"
                                   data-bv-notempty-message="日降水量不能为空"   
                                   data-bv-stringlength-max="5" data-bv-stringlength-message="不能超过5个字"
                                placeholder="请输入日降水量"
                            >
						</div>
					</div>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="submit" id="btn_dayprecipitation_save" class="btn btn-primary"
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



