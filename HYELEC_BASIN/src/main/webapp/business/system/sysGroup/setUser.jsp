<%@ page language="java" pageEncoding="UTF-8"%>
	<div class="modal fade" id="set_user_dialog" >
		<div class="modal-dialog modal-lg" style="width:900px;height:500px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">信息维护-用户管理</h4>
				</div>
				
				<input type="hidden" id="id_group" name="id_group"/>
				<input type="hidden" id="mark" name="mark"/>
				<div class="modal-body" style="height:400px;">
					<div id="maincontent" class="row-fluid">
						<div class="row-fluid col-md-12">
						  <div id="setid">
							<div class="btn-toolbar">
								<div class="btn-group pull-right col-lg-4 col-md-4 col-sm-4 col-xs-6">
									<div class="input-group ">
										<input type="text" id="searchName" class="form-control"
											placeholder="输入关键字进行模糊查询"><span class="input-group-btn">
											<button class="btn" id="btn_ref_staff" type="button">
												<div class="visible-md visible-lg">
													<i class="icon icon-search"></i>&nbsp;查询
												</div>
												<div class="visible-xs visible-sm">
													<i class="icon icon-search"></i>
												</div>
											</button>
										</span>
									</div>
								</div>
							</div>
						</div>
							<div id="sassid" style="margin-left: -15px;" ></div>
							<!-- 按钮工具条结束 -->
							<table id="tbinfo_staff" class="table-condensed table-hover">
								<thead>
									<tr>
										<th data-halign="center" data-align="center"
											data-sortable="false" data-field="state" data-checkbox="true"
											data-formatter="FMT_Check_staff"></th>
										<th data-halign="center" data-align="center"
											data-sortable="false" data-width="60" data-formatter="FMT_Num_staff">
											序号</th>
										<th data-halign="center" data-align="center" data-sortable="true"
											data-field="STAFF_NAME" data-width="200">姓名</th>
										<th data-halign="center" data-align="center" data-sortable="true"
											data-field="TREENM_DEPT" data-width="300">单位</th>
										<th data-halign="center" data-align="center" data-sortable="false"
											data-field="STAFF_DEPT" data-width="300">部门</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer" id="footid">
				<div id="gropl"> 
					<button type="button" id="btn_staff_save" class="btn btn-primary" >
						<i class="icon icon-save"></i> 保存
					</button>
					<button type="button" class="btn" id="areas" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>	
				</div>
			</div>
		</div>
	</div>
<script src="../business/system/sysStaff/def.js"></script>
<script src="../business/system/sysStaff/list.js"></script>
<style>
    #setid{
      float:left;
    }
   #sassid{
    
   width: 898px;
   
    margin-left: -22px;
    height: 45px;
    background: white;
   }
   #footid{
    background-color: white;
   
   }
   #footid{
     padding:0px;
   }
   #gropl{
    position: absolute;
    width: 100%;
    bottom: -56px;
    height: 57px;
    background:#e0f2f3;
    border-top: 1px solid #e5e5e5;
   }
   #btn_staff_save{
   margin-top:12px;   
   }
   #areas{
    margin-right:22px;
    margin-top:12px;
   }
   #set_user_dialog .fixed-table-container{
   	height:380px !important;
   }
</style>