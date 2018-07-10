<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.lyht.business.system.bean.SysUser"%>
<form id="form_user_flag" name="form_user_flag" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="dialog_user_flag">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">审核</h4>
				</div>
				
				<div class="modal-body">
					<div style="display: none;">
						StaffCode<input type="text" class="form-control" 
						        id="staffCode_userFlag" 
								name="mSysAccountFormBean.mSysUserInfoBean.staffCode"/>
					</div>
					
					<div class="form-group">
						<label  class="col-md-2 control-label">
							<span class="text-danger">*&nbsp;</span>用户状态：
						</label>
						<div class="col-md-4 rowGroup">
						   <select class="form-control" id="state_userFlag" required>
								<option value="" selected>请选择</option>
								<option value="0" <s:if test="mSysAccountFormBean.mSysUserInfoBean.state == 0">selected</s:if> >未审核</option>
								<option value="1" <s:if test="mSysAccountFormBean.mSysUserInfoBean.state == 1">selected</s:if> >已审核</option>
								<option value="2" <s:if test="mSysAccountFormBean.mSysUserInfoBean.state == 2">selected</s:if> >停用</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btn_save_userFlag" class="btn btn-primary" >
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