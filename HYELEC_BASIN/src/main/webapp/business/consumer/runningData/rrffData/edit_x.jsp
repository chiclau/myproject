<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
	<div class="modal fade" id="edit_dialog_rrff_x">
		<div class="modal-dialog modal-lg" style="width: 1000px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增洪水传播时间</h4>
				</div>

				<div class="modal-body" style="padding-top: 8px;">
				<form id="info_form_rrff_x" name="info_form" class="form-horizontal"
				autocomplete="off" method="post"
				data-bv-message="This value is not valid"
				data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
				data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
				data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
					 <div style="display: none;">
							stcd<input type="text" class="form-control" id="stcd_rrff_x"
							name="mRrffFormBean.mRrffInfoBean.stcd">
					</div> 
				<div class="form-group" style="margin-top: 10px;">
					<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
					</label>
					<div class="col-md-2 rowGroup">
						<input type="text" class="form-control" id="stnm_rrff_x" 
                                  name="mRvsectFormBean.mRvsectInfoBean.stnm" 
                                  data-bv-group=".rowGroup" 
                                  required
                                  readonly="readonly">
					</div>
					<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 用户名：
					</label>
					<div class="col-md-2 rowGroup">
							<input type="text" class="form-control" id="userName_rrff_x" 
                                  name="mRrffFormBean.mRrffInfoBean.userName" 
                                  data-bv-group=".rowGroup" 
                                  required
                                  readonly="readonly">
					</div>
					<div class="col-md-2 rowGroup">
						<button type="button" id="query_add_rrff_x" class="btn btn-primary">
						   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增数据</div>
						   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
						</button>
					</div>
				</div>
				<div style="min-height: 400px;max-height:500px;width: 400px;overflow-y:auto;">
				<table class="table-bordered" style="margin-bottom: 0px;margin-top: 30px;" id="query_table_rrff_x" >
				</table>
				</div>
				<div id="myEchart_rrff" style="position:absolute; top:70px; left:430px;width: 560px; height:400px;">
				</div>
				</form>
				</div>
			</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="button" id="btn_save_rrff_x" class="btn btn-primary"
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

</form>



