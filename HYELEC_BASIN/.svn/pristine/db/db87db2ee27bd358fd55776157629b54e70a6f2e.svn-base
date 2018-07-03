<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form_floodTran" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<input type="hidden" id="upstcd_fsdr_" name="mFsdrFormBean.mFsdrInfoBean.upstcd"/>
	<input type="hidden" id="dwstcd_fsdr_" name="mFsdrFormBean.mFsdrInfoBean.dwstcd"/>

	<div class="modal fade" id="edit_dialog_floodTran">
		<div class="modal-dialog modal-lg" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增洪水传播时间</h4>
				</div>

				<div class="modal-body" style="padding-top: 8px;">
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 上游名称：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="upstcd_fsdr" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择上游名称">
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 下游名称：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="dwstcd_fsdr" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择下游名称">
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 河段长(km)：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="rchlen_fsdr" 
                                   name="mFsdrFormBean.mFsdrInfoBean.rchlen" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="3"
                                   data-bv-notempty-message="河段长不能为空"   
                                   data-bv-stringlength-max="3" data-bv-stringlength-message="不能超过3个字"
                                placeholder="请输入河段长"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 安全泄量(m<sup>3</sup>/s)：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="sftq_fsdr" 
                                   name="mFsdrFormBean.mFsdrInfoBean.sftq" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="9"
                                   data-bv-notempty-message="安全泄量不能为空"   
                                   data-bv-stringlength-max="9" data-bv-stringlength-message="不能超过9个字"
                                placeholder="请输入安全泄量"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 流量量级(m<sup>3</sup>/s)：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="qmgn_fsdr" 
                                   name="mFsdrFormBean.mFsdrInfoBean.qmgn" 
                                   data-bv-group=".rowGroup" 
                                   required 
                                   onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
                                   onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                                   maxlength="5"
                                   data-bv-notempty-message="安全泄量不能为空"   
                                   data-bv-stringlength-max="5" data-bv-stringlength-message="不能超过5个字"
                                placeholder="请输入安全泄量"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 最小传播时间：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="mntrtm_fsdr" 
                                   name="mFsdrFormBean.mFsdrInfoBean.mntrtm" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="5"
                                   data-bv-notempty-message="最小传播时间不能为空"   
                                   data-bv-stringlength-max="5" data-bv-stringlength-message="不能超过5个字"
                                placeholder="请输入最小传播时间"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 最大传播时间：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="mxtrtm_fsdr" 
                                   name="mFsdrFormBean.mFsdrInfoBean.mxtrtm" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="5"
                                   data-bv-notempty-message="最大传播时间不能为空"   
                                   data-bv-stringlength-max="5" data-bv-stringlength-message="不能超过5个字"
                                placeholder="请输入最大传播时间"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 平均传播时间：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="avtrtm_fsdr" 
                                   name="mFsdrFormBean.mFsdrInfoBean.avtrtm" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="5"
                                   data-bv-notempty-message="平均传播时间不能为空"   
                                   data-bv-stringlength-max="5" data-bv-stringlength-message="不能超过5个字"
                                placeholder="请输入平均传播时间"
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



