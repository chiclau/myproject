<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form_user" name="info_form_user" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog_user">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息维护-用户管理</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						userCode<input type="text" class="form-control" 
						        id="userCode_user" 
								name="mSysAccountFormBean.mSysUserInfoBean.userCode">
						StaffCode<input type="text" class="form-control" 
						        id="StaffCode_user" 
								name="mSysAccountFormBean.mSysUserInfoBean.StaffCode">
						state<input type="text" class="form-control" 
						        id="state_user" 
								name="mSysAccountFormBean.mSysUserInfoBean.state">
						staffCode<input type="text" class="form-control" 
						        id="staffCode_staff" 
								name="mSysAccountFormBean.mSysStaffInfoBean.staffCode">
						jig<input type="text" class="form-control" 
						        id="jig_staff" 
								name="mSysAccountFormBean.mSysStaffInfoBean.jig">
						state<input type="text" class="form-control" 
						        id="state_staff" 
								name="mSysAccountFormBean.mSysStaffInfoBean.state">
					</div>
					
					<div class="form-group">
						<label  class="col-md-2 control-label">
							<span class="text-danger">*&nbsp;</span>账号：
						</label>
						<div class="col-md-4 rowGroup">
						   <input type="text" class="form-control" 
						   		   id="userName_user" 
                                   name="mSysAccountFormBean.mSysUserInfoBean.userName" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="账号不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                   placeholder="请输入账号，字数限制50  ，不能为空！">
						</div>
						<label class="col-md-2 control-label"> 
                             <span class="text-danger">*&nbsp;</span>真实姓名：
						</label>
						<div class="col-md-4 rowGroup">
						     <input type="text" class="form-control" 
						     	   id="staffName_staff" 
                                   name="mSysAccountFormBean.mSysStaffInfoBean.staffName" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="真实姓名不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                   placeholder="请输入真实姓名，不能为空！">
                        </div>
					</div>
					
					<div class="form-group hid">
						<label  class="col-md-2 control-label">
							<span class="text-danger">*&nbsp;</span>密码：
						</label>
						<div class="col-md-4 rowGroup">
						   <input type="text" class="form-control" 
						   		   id="userPwd_user" 
                                   name="mSysAccountFormBean.mSysUserInfoBean.userPwd" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="密码不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                   placeholder="请输入密码，字数限制50  ，不能为空！">
						</div>
						
						<label class="col-md-2 control-label"> 
                             <span class="text-danger">*&nbsp;</span>重复密码：
						</label>
						<div class="col-md-4 rowGroup">
						     <input type="text" class="form-control" 
						           id="retPwd_user" 
                                   name="mSysAccountFormBean.retPwd" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="重复密码不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                   placeholder="请输入重复密码，不能为空！">
                        </div>
					</div>
					
					<div class="form-group">
						<label  class="col-md-2 control-label">
							<span class="text-danger">*&nbsp;</span>联系电话：
						</label>
						<div class="col-md-4 rowGroup">
						   <input type="text" class="form-control" 
						           id="linkPhone_staff" 
                                   name="mSysAccountFormBean.mSysStaffInfoBean.linkPhone" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="联系电话不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                   placeholder="请输入联系电话，字数限制50  ，不能为空！">
						</div>
						
						<label  class="col-md-2 control-label">
							现居住地：
						</label>
						<div class="col-md-2 rowGroup">
							<select class="form-control" id="selectp_staff" name="selectp" onchange="selectCityArea();">
								
							</select>
						</div>
						<div class="col-md-2 rowGroup">
							<select class="form-control" id="selectc_staff" name="selectc">
								
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 
                             <span class="text-danger">*&nbsp;</span>所属单位：
						</label>
						<div class="col-md-4 rowGroup">
						     <input type="text" class="form-control" 
						           id="treeNmDept_staff" 
                                   name="mSysAccountFormBean.mSysStaffInfoBean.treeNmDept" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="所属单位不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                   placeholder="请输入所属单位，不能为空！">
                        </div>
                        <label class="col-md-2 control-label"> 
                             <span class="text-danger">*&nbsp;</span>部门：
						</label>
						<div class="col-md-4 rowGroup">
						     <input type="text" class="form-control" 
						           id="staffDept_staff" 
                                   name="mSysAccountFormBean.mSysStaffInfoBean.staffDept" 
                                   required  maxlength="50" 
                                   data-bv-notempty-message="部门不能为空"   
                                   data-bv-stringlength-max="50" 
                                   data-bv-stringlength-message="字数不能超过50"
                                   placeholder="请输入部门，不能为空！">
                        </div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btn_save_ennmcd" class="btn btn-primary" >
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
