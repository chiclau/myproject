<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
	#edit_dialog_sectionTest .modal-dialog .modal-content .modal-body label{
		top:0px;
	}
</style>
<form id="info_form_sectionTest" name="info_form" class="form-horizontal"
	autocomplete="off" method="post"
	data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="edit_dialog_sectionTest">
		<div class="modal-dialog modal-lg" style="width: 700px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增洪水传播时间</h4>
				</div>
	 <div style="display: none;">
					
							ptno<input type="text" class="form-control" id="stcd_rvsect_"
							name="mRvsectFormBean.mRvsectInfoBean.stcd">
					</div> 
				<div class="modal-body" style="padding-top: 8px;">
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 测站名称：
						</label>
						<div class="col-md-4 rowGroup">
						<!-- 	<select id="stcd_rvsect" name="mRvsectFormBean.mRvsectInfoBean.stcd" class="form-control" 
                            	data-bv-group=".rowGroup" 
                            	required data-bv-notempty-message="请选择测站名称">
							</select> -->
				<input type="text" class="form-control"   id="stcd_rvsect"   required placeholder="请输入测站名称" >				
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 垂线号：
						</label>
						<div class="col-md-4 rowGroup">
                             <input type="text" class="form-control" id="vtno_rvsect" 
                                   name="mRvsectFormBean.mRvsectInfoBean.vtno" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="4"
                                   data-bv-notempty-message="垂线号不能为空"   
                                   data-bv-stringlength-max="4" data-bv-stringlength-message="不能超过4个字"
                                placeholder="请输入垂线号"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 起点距：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="di_rvsect" 
                                   name="mRvsectFormBean.mRvsectInfoBean.di" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="8"
                                   data-bv-notempty-message="起点距不能为空"   
                                   data-bv-stringlength-max="8" data-bv-stringlength-message="不能超过8个字"
                                placeholder="请输入起点距"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 河底高程：
						</label>
						<div class="col-md-4 rowGroup">
							<input type="text" class="form-control" id="zb_rvsect" 
                                   name="mRvsectFormBean.mRvsectInfoBean.zb" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="8"
                                   data-bv-notempty-message="河底高程不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过8个字"
                                placeholder="请输入河底高程"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;height:40px;line-height:30px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 施测时间：
						</label>
						<div class="col-md-4 rowGroup">
							<a class='input-group date' style="float: left;left:10px;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 170px; height: 30px;background-color:#fff;" readonly="readonly"
									id="mstm_rvsect" data-bv-group=".rowGroup"
									name="mRvsectFormBean.mRvsectInfoBean.mstm" required
									data-bv-notempty-message="施测时间不能为空"
									placeholder="请选择日期"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
									</span>
								</label>
							</a>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                        	<span class="text-danger">*&nbsp;</span> 备注：
						</label>
						<div class="col-md-10 rowGroup">
							<textarea type="text" class="form-control" id="comments_rvsect" 
                                name="mRvsectFormBean.mRvsectInfoBean.comments" 
                                data-bv-group=".rowGroup" 
                                required
                                maxlength="25"
                                data-bv-notempty-message="备注不能为空"   
                                data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
                                placeholder="请输入备注"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="submit" id="btn_section_save" class="btn btn-primary"
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
<script type="text/javascript">
	$(function(){
		$(".laydate").each(function(i,obj){
			laydate({
	            elem: obj,
	            format: 'YYYY-MM-DD'
	        });
		});
		
		stnmMohu();
	});
	
	function stnmMohu(){
		$("#stcd_rvsect").combox({
			url:	 basePath + "chanliu/chanliu!getStbprpMoHu.action",
			label:"STNM",
			value:"STCD",
			dataKey:"dataList",
			height:'250px',
			width:'150px',
			isPager:false,
			select:function(item){
				$("#stcd_rvsect_").val(item.STCD);//定位
			}
		});
		
	}
</script>


