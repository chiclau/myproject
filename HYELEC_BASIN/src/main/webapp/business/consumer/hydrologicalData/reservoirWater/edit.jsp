<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
</style>
<form id="info_form_reservoirWater" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
 
    <input type="hidden" id="stcd_rsvr_" name="mRsvrFormBean.mRsvrInfoBean.stcd"/>
	<input type="hidden" id="tm_rsvr_" name="mRsvrFormBean.mRsvrInfoBean.tm"/>

	<div class="modal fade" id="edit_dialog_reservoirWater">
		<div class="modal-dialog modal-lg" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增水库水情信息</h4>
				</div>

				<div class="modal-body" style="padding-top: 8px;">
					<div class="form-group">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
						</label>
						<div class="col-md-3 rowGroup">
							<select id="stcd_rsvr" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择测站名称">
							</select>
						</div>
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 时间：
						</label>
						<div class="col-md-3 rowGroup">
							<a class='input-group date' style="float: left;left:10px;margin-top:0px;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydatetime" autofocus="autofocus"
									style="width: 166px; height: 30px;background-color:#fff;" readonly="readonly"
									id="tm_rsvr" data-bv-group=".rowGroup"
									required
									data-bv-notempty-message="时间不能为空"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
									</span>
								</label>
							</a>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 库水位(m)：
						</label>
						<div class="col-md-3 rowGroup">
                            <input type="text" class="form-control" id="rz_rsvr" 
                                   name="mRsvrFormBean.mRsvrInfoBean.rz" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="7"
                                   data-bv-notempty-message="库水位不能为空"   
                                   data-bv-stringlength-max="7" data-bv-stringlength-message="不能超过7个字"
                                placeholder="请输入库水位"
                            >
						</div>
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 入库流量(m<sup>3</sup>/s)：
						</label>
						<div class="col-md-3 rowGroup">
                             <input type="text" class="form-control" id="inq_rsvr" 
                                   name="mRsvrFormBean.mRsvrInfoBean.inq" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="9"
                                   data-bv-notempty-message="入库流量不能为空"   
                                   data-bv-stringlength-max="9" data-bv-stringlength-message="不能超过9个字"
                                placeholder="请输入入库流量"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 蓄水量(10<sup>6</sup>m<sup>3</sup>)：
						</label>
						<div class="col-md-3 rowGroup">
                            <input type="text" class="form-control" id="w_rsvr" 
                                   name="mRsvrFormBean.mRsvrInfoBean.w" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="9"
                                   data-bv-notempty-message="蓄水量不能为空"   
                                   data-bv-stringlength-max="9" data-bv-stringlength-message="不能超过9个字"
                                placeholder="请输入蓄水量"
                            >
						</div>
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 出库流量(m<sup>3</sup>/s)：
						</label>
						<div class="col-md-3 rowGroup">
                             <input type="text" class="form-control" id="otq_rsvr" 
                                   name="mRsvrFormBean.mRsvrInfoBean.otq" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="9"
                                   data-bv-notempty-message="出库流量不能为空"   
                                   data-bv-stringlength-max="9" data-bv-stringlength-message="不能超过9个字"
                                placeholder="请输入出库流量"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 库水特征码：
						</label>
						<div class="col-md-3 rowGroup">
                            <select id="rwchrcd_rsvr" name="mRsvrFormBean.mRsvrInfoBean.rwchrcd" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择库水特征码">
                            	<option value="">请选择库水特征码</option>
                            	<option value="1">干涸</option>
                            	<option value="2">断流</option>
                            	<option value="3">流向不定</option>
                            	<option value="4">逆流</option>
                            	<option value="5">起涨</option>
                            	<option value="6">洪峰</option>
                            	<option value="P">水电厂发电流量</option>
                            	<option value=" ">一般情况</option>
							</select>
						</div>
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 库水水势：
						</label>
						<div class="col-md-3 rowGroup">
                             <select id="rwptn_rsvr" name="mRsvrFormBean.mRsvrInfoBean.rwptn" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择库水势">
                            	<option value="">请选择库水势</option>
                            	<option value="4">落</option>
                            	<option value="5">涨</option>
                            	<option value="6">平</option>
							</select>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 入流时段长：
						</label>
						<div class="col-md-3 rowGroup">
                            <input type="text" class="form-control" id="inqdr_rsvr" 
                                   name="mRsvrFormBean.mRsvrInfoBean.inqdr" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="5"
                                   data-bv-notempty-message="入流时段长不能为空"   
                                   data-bv-stringlength-max="5" data-bv-stringlength-message="不能超过5个字"
                                placeholder="请输入入流时段长"
                            >
						</div>
						<label class="col-md-3 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测流方法：
						</label>
						<div class="col-md-3 rowGroup">
                             <select id="msqmt_rsvr" name="mRsvrFormBean.mRsvrInfoBean.msqmt" 
                        		class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择水势">
                            	<option value="">请选择水势</option>
                            	<option value="1">水位流量关系曲线</option>
                            	<option value="2">浮标及溶液测流法</option>
                            	<option value="3">流速仪及量水建筑物</option>
                            	<option value="4">估算法</option>
                            	<option value="5">ADCP</option>
                            	<option value="6">电功率反推法</option>
                            	<option value="9">其它方法</option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="submit" id="btn_reservoir_save" class="btn btn-primary"
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
	</div>
</form>
