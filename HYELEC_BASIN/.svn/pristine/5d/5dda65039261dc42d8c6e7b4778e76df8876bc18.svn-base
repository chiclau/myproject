<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form_group" name="info_form_group" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog_group">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息维护-分组管理</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id_group" 
							name="mSysGroupFormBean.mSysGroupInfoBean.id">
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 分组编码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="groupCode_group" 
                                   name="mSysGroupFormBean.mSysGroupInfoBean.groupCode" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="分组编码不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                placeholder="请输入分组编码，字数限制50，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                           <span class="text-danger">(*必填)</span> 分组名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="groupName_group" 
                                   name="mSysGroupFormBean.mSysGroupInfoBean.groupName" 
                                   required  maxlength="100" 
                                   data-bv-notempty-message="分组名称不能为空"   
                                   data-bv-stringlength-max="100" 
                                   data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入分组名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                  <div class="form-group">
                	    <label class="col-md-3 control-label">备注：</label>
                	    <div class="col-md-9">
                	        <textarea id="remark_group" name="mSysGroupFormBean.mSysGroupInfoBean.remark" maxlength="500" rows='10' class='form-control' placeholder="请输入备注信息，字数限制500！">
                	           <s:property value='mSysGroupFormBean.mSysGroupInfoBean.remark' />
                	        </textarea>
                	    </div>
                  </div>  
				</div>
				<div class="modal-footer">
					<button type="submit" id="btn_save_group" class="btn btn-primary" >
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
