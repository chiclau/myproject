<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form_addvcd" name="info_form_addvcd" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
	
	<div class="modal fade" id="info_dialog_addvcd">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息维护</h4>
				</div>
				
				<div class="modal-body">
				    <div style="display: none;">
							paddvcd<input type="text" class="form-control" id="paddvcd_addvcd"
							name="mAddvcdFormBean.mAddvcdInfoBean.paddvcd">
					</div>
				    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              上级信息：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="pname_addvcd" readonly>
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 行政区域编码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="addvcd_addvcd" 
                                   name="mAddvcdFormBean.mAddvcdInfoBean.addvcd" 
                                   required   maxlength="10" 
                                   data-bv-notempty-message="行政区域编码不能为空"   
                                   data-bv-stringlength-max="10" 
                                   data-bv-stringlength-message="字数不能超过10"
                                   placeholder="请输入行政区域编码，字数限制10  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 行政区域名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name_addvcd" 
                                   name="mAddvcdFormBean.mAddvcdInfoBean.name" 
                                   required   maxlength="100" 
                                   data-bv-notempty-message="流域名称不能为空"   
                                   data-bv-stringlength-max="100" 
                                   data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入流域名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
				</div>
				
				<div class="modal-footer">
					<button type="submit" id="btn_save_addvcd" class="btn btn-primary" >
						<i class="icon icon-save"></i> 保存
					</button>
					
					<button type="button" class="btn" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>
</form>



