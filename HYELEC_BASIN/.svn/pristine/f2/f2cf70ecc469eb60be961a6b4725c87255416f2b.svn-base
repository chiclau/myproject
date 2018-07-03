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
					<h4 class="modal-title">信息维护-系统日志</h4>
				</div>

				<div class="modal-body">
					<div style="display: none;">
						id<input type="text" class="form-control" id="id" name="formBean.infoBean.id">
                        nm<input type="text" class="form-control" id="nm" name="formBean.infoBean.nm">
                        flag<input type="text" class="form-control" id="flag" name="formBean.infoBean.flag">
                        sysflag<input type="text" class="form-control" id="sysflag" name="formBean.infoBean.sysflag">
                        createtime<input type="text" class="form-control" id="createtime" name="formBean.infoBean.createtime">
                        updatetime<input type="text" class="form-control" id="updatetime" name="formBean.infoBean.updatetime">
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              操作时间：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="logtime" 
                                   name="formBean.infoBean.logtime" 
                                   value="<s:property value='formBean.infoBean.logtime' />" 
                                placeholder="请输入操作时间，字数限制50  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              操作员：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="name" 
                                   name="formBean.infoBean.name" 
                                   value="<s:property value='formBean.infoBean.name' />" 
                                placeholder="请输入操作员，字数限制100  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              操作员内码：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="opernm" 
                                   name="formBean.infoBean.opernm" 
                                   value="<s:property value='formBean.infoBean.opernm' />" 
                                placeholder="请输入操作员内码，字数限制50  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              模块唯一标识：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="menuflag" 
                                   name="formBean.infoBean.menuflag" 
                                   value="<s:property value='formBean.infoBean.menuflag' />" 
                                placeholder="请输入模块唯一标识，字数限制50  "
                            >
						</div>
					</div>
                    
 					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              操作类型：
						</label>
						<div class="col-md-9">
							<select class="form-control" data-placeholder="选择操作类型..."
									id="dictnmOpttype" 
                                    name="formBean.infoBean.dictnmOpttype">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              旧数据：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="olddata" 
                                   name="formBean.infoBean.olddata" 
                                   value="<s:property value='formBean.infoBean.olddata' />" 
                                placeholder="请输入旧数据，字数限制-1  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              新数据：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="newdata" 
                                   name="formBean.infoBean.newdata" 
                                   value="<s:property value='formBean.infoBean.newdata' />" 
                                placeholder="请输入新数据，字数限制-1  "
                            >
						</div>
					</div>
                    
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              备注：
						</label>
						<div class="col-md-9">
                            <input type="text" class="form-control" id="memo" 
                                   name="formBean.infoBean.memo" 
                                   value="<s:property value='formBean.infoBean.memo' />" 
                                placeholder="请输入备注，字数限制-1  "
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



