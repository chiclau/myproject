﻿<%@ page language="java" pageEncoding="UTF-8"%>

<form id="query_reservoir_form" name="info_form" class="form-inline"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="query_info_reservoirWater">
		<div class="modal-dialog " style="width: 800px">
			<div class="modal-content">
				<div class="modal-header"
					style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<i class="icon icon-times"></i><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;">
						<i class="icon icon-home"></i>&nbsp;&nbsp;水库水情详细
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
								style="margin-left: 15px;" id="STCD_detail_s">BM20180100</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>时间：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="TM_detail_s">2018-11-12 12:12:23</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>库水位：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="RZ_detail_s">120m</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>入库流量：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="INQ_detail_s">20万m³</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>蓄水量：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="W_detail_s">3400万m³</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>出库流量：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="OTQ_detail_s">21万m³</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>库水特征码：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="RWCHRCD_detail_s">W</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>库水水势：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="RWPTN_detail_s">0.123Mpa</lable></td>
						</tr>
						<tr>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>入流时段长：</lable>
							</td>
							<td style="width: 300px; text-align: left"><lable
								style="margin-left: 15px;" id="INQDR_detail_s">12h</lable></td>
							<td
								style="width: 100px; text-align: right; font-weight: bold; background: #f1f1f1">
								<lable>测流方法：</lable>
							</td>
							<td style="text-align: left"><lable
								style="margin-left: 15px;" id="MSQMT_detail_s">测流方法</lable></td>
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



