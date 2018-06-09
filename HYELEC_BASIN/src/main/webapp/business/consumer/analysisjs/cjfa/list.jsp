<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>资料管理</title>
<%-- <script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script> --%>
<%@include file="/common/inc/inc.inc"%>
<%@include file="/common/inc/bootstrapTable.inc"%>
<%@include file="../../../../common/inc/ztree.inc"%>
<style type="text/css">
.clgl-13{ width:100%; margin:0 auto;}
.clgl-14{ float:left; width:40%;   line-height:60px;  text-align:center; }
.clgl-16{ float:left; width:20%; line-height:35px; text-align:center; }
.clgl-15{ float:left; width:40%;  padding-top:20px; line-height:48px;  text-align:center; }
#progName_modelprogram,
#createStaff_modelprogram,
#createTime_modelprogram,
#remark_modelprogram
{
margin-top: 8px;} 
#id1{
    position: relative;
    top: -27px;
    left: 152px;
    font-size: 23px;
}
#btn_fj1{
    position: relative;
    top: -71px;
    left: 207px;
}
 .form-horizontal .form-group>label {
   position: relative;
    top: -14px;
    text-align: right;
    left: -94px;
} 
.form-control{
position: relative;
    left: -81px;
   width:143%;
}
</style>
</head>
<body style="background-color: #FCFCFC;">
	
	<div class="container-fluid">
		<%@include file="/common/inc/top.jsp"%>
		<div class="row-fluid">
			<h3 class="text-primary">
				<ol class="breadcrumb">
					<li>分析计算</li>
                    <li style="color:black;">创建方案</li>
				</ol>
			</h3>
		</div>
		 <form id="info_form_cjfa" name="info_form" class="form-horizontal"
					autocomplete="off" method="post"
					data-bv-message="This value is not valid"
					data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
					data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
					data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
        <div class="clgl-13" style="border:0.5px solid #EBEBEB">
	        <div class="clgl-14" style="border:0.5px solid #EBEBEB">
		        	<div>
		        		<img id="u484_img" src="<%=basePath%>common/images/u484.png">
		        	</div>
		        		<div style="display: none;"> <!-- 让table隐藏，通过query_table只请求后台不显示：没有匹配记录的xinxi -->
		        	  <table id="query_table"  class="">
	                        </table>
	                        </div>
		        	<div class="form-group" style="margin-top: 10px; font-size:20px;">
		        		<label class="col-md-4 control-label"  style="left:-72px;">
		        			<span>&nbsp;</span> 方案信息
		        		</label>
		        		<div class="col-md-4 rowGroup">
			        		<button type="button" id="btn_save" 
			        				class="btn btn-primary" onclick="btnSave()"
									style="margin-top: -12px;margin-left: 192px">
								<i class="icon icon-download-alt"></i> 保存方案
							</button>
						</div>
		        	</div>
					<div class="form-group" style="margin-top: 10px;">
						<label class="col-md-4 control-label" style="margin-left:82px" > 
                              <span class="text-danger">*&nbsp;</span> 方案名称：
						</label>
						<div class="col-md-6 rowGroup">
		                    <input type="text" class="form-control" id="progName_modelprogram"
		                               name="modelprogramFormBean.modelprogramFormBean.progName" 
	                                   value="<s:property value='modelprogramFormBean.modelprogramFormBean.progName' />" 
	                                   data-bv-group=".rowGroup" 
	                                   required
	                                   maxlength="25"
	                                   data-bv-notempty-message="方案名称不能为空"   
	                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
	                               	placeholder="请输入方案名称" style="width:287px"
		                            >
	                     </div>
					</div>
					<div class="form-group" style="margin-top: 10px;">
						 <label class="col-md-4 control-label"  style="margin-left:82px"> 
                              <span>&nbsp;</span> 创建人： <input type="hidden" id="modeCode" name="modelprogramFormBean.modelprogramFormBean.modelCode">
						</label>
						<div class="col-md-1 rowGroup">
							<input type="text" id="createStaff_modelprogram"
			                           class="form-control"  value="<s:property value='mSysStaff.staffName' />"
                                   name="modelprogramFormBean.modelprogramFormBean.createStaff" readonly="readonly" style="width:80px">
                         </div>
						<label class="col-md-3 control-label"  style="left:-11px"> 
                              <span>&nbsp;</span> 创建时间：
						</label>
						<div class="col-md-1 rowGroup">
						   <a class='input-group date' style="float: left;">
							<input type="text" id="createTime_modelprogram"
			                           class="form-control laydate"  value=""
                                   name="modelprogramFormBean.modelprogramFormBean.createTime" style="width:122px;margin-left:-23px">
									</a>
	                     </div>
	                    <%--  		<div class="col-md-4 rowGroup">
                             <a class='input-group date' style="float: left;"> <label
								class="input" style="display: inline"> <input
									type='text' class="form-control laydate" autofocus="autofocus"
									style="width: 170px; height: 30px;background-color:#fff;" readonly="readonly"
									id="tm_pptn" data-bv-group=".rowGroup"
									name="mPptnFormBean.mPptnInfoBean.tm" required
									data-bv-notempty-message="时间不能为空"
									value="<s:property value='contInfoFormBean.contInfoBean.signDate' />"
									placeholder="请选择时间"> <span class="input-group-addon"
									style="width: 39px; height: 30px;background-color:#f9f9f9;"> <span
										class="icon-calendar"></span>
									</span>
								</label>
							</a>
						</div> --%>
	                  </div>
	                  
					 <div class="form-group" style="margin-top: 10px;margin-bottom: 20px">
						<label class="col-md-4 control-label" style="margin-left:82px"> 
                              <span class="text-danger">&nbsp;</span> 描述：
						</label>
						 <div class="col-md-6 rowGroup">
							 <input type="text" class="form-control" id="remark_modelprogram" 
                                   name="modelprogramFormBean.modelprogramFormBean.remark" 
                                   value="<s:property value='modelprogramFormBean.modelprogramFormBean.remark' />" 
                                   data-bv-group=".rowGroup" 
                                   required
                                   maxlength="25"
                                   data-bv-notempty-message="备注不能为空"   
                                   data-bv-stringlength-max="50" data-bv-stringlength-message="不能超过25个字"
                                placeholder="请输入备注" style="height: 60px;width:287px"
                            >
						</div>
					</div>
			
	        </div>
		 <div class="clgl-16">
	        	<ul id="ztree" class="ztree" style="width: 260px; overflow: auto;">
					
				</ul>
	        </div> 
	        <div class="clgl-15" style="border:0.5px solid #EBEBEB" >
	        	
					
		        	<div class="form-group" style="margin-top: 10px;" >
		        		<label class="col-md-4 control-label" id="id1"> 
                              <span>&nbsp;</span> 输入参数
						</label>
						<div class="col-md-4 rowGroup">
							<%--  <a href="<%=basePath%>business/consumer/analysisjs/cjfa/edit.jsp">  --%>
								<button class="btn btn-primary" id="btn_fj1" type="button" onclick="btn_fj()">
									   <div class="visible-md visible-lg"><i class="icon icon-cloud-download"></i>&nbsp;分析计算</div>
									   <div class="visible-xs visible-sm"><i class="icon icon-cloud-download"></i></div>
								</button>
						</div>
		        	</div>
		       <div id="clg"></div>
	        </div>
	 	<div class="modal fade" id="cjfa_fxjs">
		<div class="modal-dialog modal-lg" style="width:600px">
			<div class="modal-content">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>新增测站信息</h4>
				</div>

				<div class="modal-body" style="padding-top: 8px;">
					<div class="flag" style="margin-top: 10px;margin-left: -70px;">
						<div id="cjfa_main" class="className" style="width:600px;height:300px;margin-left: 60px;">
							
						</div>
					</div>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="reset" class="btn btn-large"
						data-dismiss="modal"
						style="margin-top: -14px; margin-right: -5px;">
						<i class="icon icon-times"></i> 取消
					</button>
				</div>
			</div>
		</div>
	</div>
	    </div>
	   </form> 	
	  </div> <!-- maincontent -->
		      
</body>
</html>
<!-- 不要改变以下引用顺序 -->
<%@include file="edit.jsp"%>
<script src="query.js"></script>
<script src="tree.js"></script>
<script src="list.js"></script>
