<%@ page language="java" pageEncoding="UTF-8"%>

<form id="info_form" name="info_form" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="info_dialog">
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
						id<input type="text" class="form-control" id="id_menu_" name="mSysMenuFormBean.mSysMenuInfoBean.id">
						ids<input type="text" class="form-control" id="ids_menu_" name="mSysMenuFormBean.ids">
						menuCode<input type="text" class="form-control" id="menuCode_menu_" name="mSysMenuFormBean.mSysMenuInfoBean.menuCode">
                        pnm<input type="text" class="form-control" id="superCode_menu_" name="mSysMenuFormBean.mSysMenuInfoBean.superCode">
                        code<input type="text" class="form-control" id="fCode_menu_" name="mSysMenuFormBean.mSysMenuInfoBean.fCode">
                        flag<input type="text" class="form-control" id="state_menu_" name="mSysMenuFormBean.mSysMenuInfoBean.state">
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              上级信息：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="pname_menu_" readonly>
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 本级编码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="sCode_menu_" 
                                   name="mSysMenuFormBean.mSysMenuInfoBean.sCode" 
                                   required   maxlength="10" 
                                   onkeyup="this.value=this.value.replace(/\D/g,'')" 
                                   onafterpaste="this.value=this.value.replace(/\D/g,'')"
                                   data-bv-notempty-message="本级编码不能为空"   
                                   data-bv-stringlength-max="10" data-bv-stringlength-message="字数不能超过10"
                                placeholder="请输入本级编码，字数限制10  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">(*必填)</span> 名称：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="menuName_menu_" 
                                   name="mSysMenuFormBean.mSysMenuInfoBean.menuName" 
                                   required   maxlength="100" 
                                   data-bv-notempty-message="名称不能为空"   
                                   data-bv-stringlength-max="100" 
                                   data-bv-stringlength-message="字数不能超过100"
                                placeholder="请输入名称，字数限制100  ，不能为空！"
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              图标：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="menuIcon_menu_" 
                                   name="mSysMenuFormBean.mSysMenuInfoBean.menuIcon" 
                                placeholder="请输入图标，字数限制50  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              路径：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="menuUrl_menu_" 
                                   name="mSysMenuFormBean.mSysMenuInfoBean.menuUrl" 
                                placeholder="请输入url，字数限制300  "
                            >
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" id="btn_save" class="btn btn-primary" >
						<i class="icon icon-save"></i> 保存
					</button>

					<button type="button" class="btn" onclick="_reset()">
						<i class="icon icon-refresh"></i> 重置
					</button>
					
					<button type="button" class="btn" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>
</form>



