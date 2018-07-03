<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form_rrffData" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="edit_dialog_rrffData">
		<div class="modal-dialog modal-lg" style="width: 600px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增降雨径流</h4>
				</div>

				<div class="modal-body" style="padding-top: 8px;">
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
						</label>
						<div class="col-md-4 rowGroup">
							<select id="stcd_rrff" name="mRrffFormBean.mRrffInfoBean.stcd" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择测站名称">
							</select>
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 用户名：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="userName_rrff" 
                                   name="mRrffFormBean.mRrffInfoBean.userName" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="8"
                                   data-bv-notempty-message="用户名不能为空"   
                                   data-bv-stringlength-max="8" data-bv-stringlength-message="不能超过8个字"
                                placeholder="请输入用户名"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 影响雨量：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="pa_rrff" 
                                   name="mRrffFormBean.mRrffInfoBean.pa" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="8"
                                   data-bv-notempty-message="影响雨量不能为空"   
                                   data-bv-stringlength-max="8" data-bv-stringlength-message="不能超过8个字"
                                placeholder="请输入影响雨量"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 降雨量：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="p_rrff" 
                                   name="mRrffFormBean.mRrffInfoBean.p" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="8"
                                   data-bv-notempty-message="降雨量不能为空"   
                                   data-bv-stringlength-max="8" data-bv-stringlength-message="不能超过8个字"
                                placeholder="请输入降雨量"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 径流：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="r_rrff" 
                                   name="mRrffFormBean.mRrffInfoBean.r" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="8"
                                   data-bv-notempty-message="径流不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过8个字"
                                placeholder="请输入径流"
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



