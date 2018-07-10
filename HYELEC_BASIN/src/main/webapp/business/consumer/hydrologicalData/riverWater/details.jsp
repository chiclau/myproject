<%@ page language="java" pageEncoding="UTF-8"%>

<form id="query_form" name="info_form" class="form-inline"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="query_info_riverWater">
		<div class="modal-dialog " style="width: 900px">
			<div class="modal-content">
				<div class="modal-header"
					style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<i class="icon icon-times"></i><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">
						<i class="icon icon-home"></i>&nbsp;&nbsp;日蒸发量详细
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered" style="margin-bottom: 0px;">
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>测站编码：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="STCD_detail_h"></lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>时间：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="TM_detail_h">2018-11-12 12:12:23</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>水位：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="Z_detail_h">12m</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>流量：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="Q_detail_h">1200m³/s</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>断面过水面积：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="XSA_detail_h">2811m²</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>断面平均流速：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="XSAVV_detail_h">1200m³/s</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>断面最大流速：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="XSMXV_detail_h">1800m³/s</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>河水特征码：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="FLWCHRCD_detail_h">T</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>水势：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="WPTN_detail_h">0.12MPa</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>测流方法：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="MSQMT_detail_h">测流方法</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>测积方法：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="MSAMT_detail_h">断面法</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>测速方法：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="MSVMT_detail_h">毕托管测流</lable></td>
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



