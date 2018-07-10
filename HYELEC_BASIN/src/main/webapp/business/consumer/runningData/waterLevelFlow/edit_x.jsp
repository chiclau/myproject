<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form_waterLevelFlow_x" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="edit_dialog_waterLevelFlow_x">
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
					 <div style="display: none;">
							stcd<input type="text" class="form-control" id="stcd_zqrl_x"
							name="mZqrlFormBean.mZqrlInfoBean.stcd">
					</div> 
				<div class="form-group" style="margin-top: 10px;">
					<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
					</label>
					<div class="col-md-2 rowGroup">
						<input type="text" class="form-control" id="stnm_zqrl_x" 
                                  name="mZqrlFormBean.mZqrlInfoBean.stnm" 
                                  data-bv-group=".rowGroup" 
                                  required
                                  readonly="readonly">
					</div>
					<label class="col-md-2 control-label"> 
                             <span class="text-danger">*&nbsp;</span> 曲线名称：
					</label>
					<div class="col-md-2 rowGroup">
                            <input type="text" class="form-control" id="lnnm_zqrl_x" 
                                  name="mZqrlFormBean.mZqrlInfoBean.lnnm" 
                                  data-bv-group=".rowGroup" 
                                  required
                                  readonly="readonly">
					</div >
					<div class="col-md-2 rowGroup" style="margin-left: 164px">
						<button type="button" id="query_add_waterLevelFlow_x" class="btn btn-primary">
						   <div class="visible-md visible-lg"><i class="icon icon-plus-sign"></i>&nbsp;新增数据</div>
						   <div class="visible-xs visible-sm"><i class="icon icon-file-o"></i></div>
						</button>
					</div>
				</div > 
				
				<div style="min-height: 500px;width: 400px;">
					<table class="table-bordered" style="margin-bottom: 0px;margin-top: 10px;" id="query_table_waterLevelFlow_x" >
					</table>
				</div>
				<div id="myEcharts" style="position:absolute; top:50px; left:430px;width: 560px; height:500px;">
				</div>
				
				</div>
			</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="button" id="btn_save_x" class="btn btn-primary"
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



