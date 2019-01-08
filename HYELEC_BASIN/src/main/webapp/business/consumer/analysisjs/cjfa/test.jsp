				<%@ page language="java" pageEncoding="UTF-8"%>
				<div style="margin-top: 10px;width:100%;font-size:20px;">
					<div style="width:calc(100% - 120px);float:left;">
						<label>
							方案信息
						</label>
					</div>
					<div style="width:120px;float:right;">
						<button type="button" id="btn_save" class="btn btn-primary"
							onclick="btnSave()">
							<i class="icon icon-download-alt"></i> 保存方案
						</button>
					</div>
				</div>
				<div class="form-group" style="margin-top: 10px;">
					<label class="col-md-4 control-label" style="margin-left:82px">
						<span class="text-danger">*&nbsp;</span> 方案名称：
					</label>
					<div class="col-md-6 rowGroup">
						<input type="text" class="form-control" id="progName_modelprogram"
							name="modelprogramFormBean.modelprogramFormBean.progName"
							value=""
							data-bv-group=".rowGroup" required maxlength="25"
							data-bv-notempty-message="方案名称不能为空" data-bv-stringlength-max="50"
							data-bv-stringlength-message="不能超过25个字" placeholder="请输入方案名称"
							style="width:287px">
					</div>
					<label class="col-md-4 control-label" id="zdmc"> <span
						class="text-danger">*&nbsp;</span> 站点名称：
					</label>
					<div class="col-md-6 rowGroup">
						<input type="text" class="form-control" id="zd"
							name="modelprogramFormBean.modelprogramFormBean.progName"
							data-bv-group=".rowGroup" required maxlength="25"
							data-bv-notempty-message="站点名称不能为空" data-bv-stringlength-max="50"
							data-bv-stringlength-message="不能超过25个字" placeholder="请输入站点名称"
							style="width:287px">
					</div>
				</div>
				<div class="form-group" style="margin-top: 10px;">
					<label class="col-md-4 control-label" style="margin-left:82px">
						<span>&nbsp;</span> 创建人： <input type="hidden" id="modeCode"
						name="modelprogramFormBean.modelprogramFormBean.modelCode">
					</label>
					<div class="col-md-1 rowGroup">
						<input type="text" id="createStaff_modelprogram"
							class="form-control"
							value="<s:property value='mSysStaff.staffName' />"
							name="modelprogramFormBean.modelprogramFormBean.createStaff"
							readonly="readonly" style="width:80px">
					</div>
					<label class="col-md-3 control-label" style="left:-86px"> <span>&nbsp;</span>
						创建时间：
					</label>
					<div class="col-md-1 rowGroup">
						<a class='input-group date' style="float: left;"> <input
							type="text" id="createTime_modelprogram"
							class="form-control laydate" value=""
							name="modelprogramFormBean.modelprogramFormBean.createTime"
							style="width:122px;margin-left:-19px;   margin-top: 1px;">
						</a>
					</div>
				</div>

				<div class="form-group" style="margin-top: 10px;margin-bottom: 20px">
					<label class="col-md-4 control-label" style="margin-left:82px">
						<span class="text-danger">&nbsp;</span> 描述：
					</label>
					<div class="col-md-6 rowGroup">
						<input type="text" class="form-control" id="remark_modelprogram"
							name="modelprogramFormBean.modelprogramFormBean.remark"
							value=""
							data-bv-group=".rowGroup" required maxlength="25"
							data-bv-notempty-message="备注不能为空" data-bv-stringlength-max="50"
							data-bv-stringlength-message="不能超过25个字" placeholder="请输入备注"
							style="height: 60px;width:287px">
					</div>
				</div>