<%@ page language="java" pageEncoding="UTF-8"%>

<form id="query_form" name="info_form" class="form-inline"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="query_info_show">
		<div class="modal-dialog " style="width: 850px">
			<div class="modal-content">
				<div class="modal-header"
					style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<i class="icon icon-times"></i><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">
						<i class="icon icon-home"></i>&nbsp;&nbsp;模型管理详细
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" style="margin-bottom: 0px;">
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>模型分类：</lable>
							</td>
							<td style="width: 700px; text-align: left" colspan="3"><lable
								style="margin-left: 15px;" id="MODE_TYPE_detail">汇流模型</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>模型名称：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="MODEL_NAME_detail">新安江模型</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>参数分类：</lable>
							</td>
							<td style="width: 300px;text-align: left"><lable
								style="margin-left: 15px;" id="PARA_TYPE_detail">20</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>模型参数：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="PARA_NAME_detail">新安江模型计算模型</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>状态：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="RVNM_detail">启用</lable></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="button" class="btn btn-large btn-primary"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -5px">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>


</form>



