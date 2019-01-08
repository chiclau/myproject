<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
	.form-group{
		margin-bottom: -4px;
	}
	th{
	white-space: nowrap;
	}
	input[type=checkbox], input[type=radio]{
	margin: 2px 0 0;
	}
	.c2{
	width:110%;
	margin-right: 10px;
	}
	
	.tableA{
	margin-left: 20px;
	}
	
</style>
	<div class="modal fade" id="modellist_edit_dialog">
		<div class="modal-dialog modal-lg" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增测站信息</h4>
				</div>
				<div class="modal-body" style="padding-top: 8px;height:480px;overflow-y:auto;">
					<form id="modellist_form" name="info_form" class="form-horizontal"
						autocomplete="off" method="post"
						data-bv-message="This value is not valid"
						data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
						data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
						data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
					<div style="display: none;">
						id<input type="text" class="form-control"  id="PROG_CODE_plan"
							name="modelprogramFormBean.modelprogramFormBean.progCode">
						STCD<input type="text" class="form-control"  id="STCD_plan"
							name="modelprogramFormBean.modelprogramFormBean.stcd">
						MODEL_CODE
						<input type="text" class="form-control"  id="MODEL_CODE_plan"
							name="modelprogramFormBean.modelprogramFormBean.modelCode">
						create_staff
						<input type="text" class="form-control"  id="CREATE_STAFF_plan"
							name="modelprogramFormBean.modelprogramFormBean.createStaff">
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 方案名称：
						</label>
						<div class="col-md-4 rowGroup">
                            <input type="text" class="form-control" id="PROG_NAME_plan" 
                                   name="modelprogramFormBean.modelprogramFormBean.progName" 
                                   value="<s:property value='modelprogramFormBean.modelprogramFormBean.progName' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="25"
                                   data-bv-notempty-message="方案名称不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
                                placeholder="请输入方案名称"
                            >
						</div>
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 站点名称：
						</label>
						<div class="col-md-4 rowGroup">
						   <input type="text" class="form-control" id="STNM_plan" 
                                   name="modelprogramFormBean.modelprogramFormBean.stnm" 
                                   value="<s:property value='modelprogramFormBean.modelprogramFormBean.stnm' />" 
                                   data-bv-group=".rowGroup" 
                                   required readonly="readonly"
                                   maxlength="25"
                                   data-bv-notempty-message="站点名称不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
                                placeholder="请输入站点名称"
                            >
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
							<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 模型名称：
							</label>
							<div class="col-md-4 rowGroup">
						   		<input type="text" class="form-control" id="MODEL_NAME_plan" 
                                   name="modelprogramFormBean.modelprogramFormBean.modelName" 
                                   value="<s:property value='modelprogramFormBean.modelprogramFormBean.stnm' />" 
                                   data-bv-group=".rowGroup" 
                                   required  readonly="readonly"
                                   maxlength="25"
                                   data-bv-notempty-message="模型名称不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
                                placeholder="请输入模型名称"
                            	>
							</div>
							<label class="col-md-2 control-label"> 
                              <span class="text-danger">*&nbsp;</span> 创建时间：
							</label>
							<div class="col-md-4 rowGroup">
						   		<input type="text" class="form-control" id="CREATE_TIME_plan" 
                                   name="modelprogramFormBean.modelprogramFormBean.createTime" 
                                   data-bv-group=".rowGroup" 
                                   required readonly="readonly"
                                   maxlength="25"
                                   data-bv-notempty-message="方案日期不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
                                placeholder="请输入方案日期"
                            >
						</div>
						</div>
						
							<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-2 control-label"> 
                              <span class="text-danger">&nbsp;</span> 描述：
						</label>
						<div class="col-md-10 rowGroup">
                            <textarea type="text" class="form-control" id="REMARK_plan" 
                                   name="modelprogramFormBean.modelprogramFormBean.remark" 
                                   data-bv-group=".rowGroup" 
                                placeholder="请输入描述"></textarea>
						</div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						 <table id="query_cs" style="width:100%;"> 
					        <tbody>
					  
					        </tbody>
					    </table>
					</div>
					</form>
				</div>
				<div class="modal-footer"style="height: 40px;     margin-top: 20px;">
					<button type="button" id="btn_edit_plan_save" class="btn btn-primary"
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
<script>
//主要用来监听输入的站点名称，并查找输入的站点名称STCD，做关联用
var flag = true;
$("input[id='STNM_plan']").on('compositionstart',function(){// compositionstart 在输入一段需要确认的文本如拼音to汉字、语音时会触发
    flag = false;
})
$("input[id='STNM_plan']").on('compositionend',function(){//compositionend  在拼音选词完成、语音输入完毕时会触发
    flag = true;
})
$("input[id='STNM_plan']").each(function(i){
	
}).on('input',function(){
    var _this = this;
    setTimeout(function(){
        if(flag){
            console.log($(_this).val());
            var json = "mStbprpFormBean.mStbprpInfoBean.stnm="+$(_this).val();
        	var url = basePath + "cjfa/cjfa!getStbprpMoHu.action"
        	common_ajax(json, url, function(response) {
        		$("#STCD_plan").val(response.rows[0].STCD)
        		var div="";
        	/* 	for (var i = 0; i < response.rows.length; i++) {
    			 div+="<div class='div_item' id='"+response.rows[i].STCD+"' onclick='on(this)''>"+response.rows[i].STNM+"</div>";
    			}
        		$("#div_items_txt").html(div); */
        		
        	});
        }
    },1)
})


</script>