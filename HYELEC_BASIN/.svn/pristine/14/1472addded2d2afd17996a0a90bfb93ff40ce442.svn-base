<%@ page language="java" pageEncoding="UTF-8"%>
<form id="_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="_dialog">
		<div class="modal-dialog modal-lg" style="width:550px">
			<div class="modal-content">

				<div class="modal-header"  style="height: 40px">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height:15px;"><i class='icon icon-edit'></i>&nbsp;修改用户密码</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 旧&nbsp;&nbsp;密&nbsp;码：
						</label>
						<div class="col-md-7">
                            <input type="password" class="form-control"
                                   name="formBean.infoBean.pwd"  id="pwd"
                                   value="<s:property value='formBean.infoBean.' />" 
                                   required  maxlength="100" 
                                   data-bv-notempty-message="旧密码不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入旧密码，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                    
                  <div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 新&nbsp;&nbsp;密&nbsp;码：
						</label>
						<div class="col-md-7">
                            <input type="password" class="form-control"
                                   name="formBean.newPwd"  id="newPwd"
                                   required  maxlength="100" 
                                   data-bv-notempty-message="新密码不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入新密码，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 确认密码：
						</label>
						<div class="col-md-7 ">
                            <input type="password" class="form-control"
                                   name="formBean.confirmPwd"  id="confirmPwd"
                                   required  maxlength="100" 
                                   data-bv-notempty-message="确认密码不能为空"   
                                   data-bv-stringlength-max="100" data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入确认密码，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
				</div>
				</div>
				<div class="modal-footer" style="height: 40px;">
					<button type="button" id="btn_updatepwd_save" class="btn btn-primary" style="margin-top: -14px">
						<i class="icon icon-save"></i> 保存
					</button>
					
					<button type="button" class="btn btn-large " data-dismiss="modal" style="margin-top: -14px;margin-right:-5px">
						<i class="icon icon-times"></i></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
</form>

<script src="<%=basePath%>common/inc/js.js"></script>
