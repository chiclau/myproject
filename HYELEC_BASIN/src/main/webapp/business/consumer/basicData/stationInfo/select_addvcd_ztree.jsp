<%@ page language="java" pageEncoding="UTF-8"%>
<div class="modal fade" id="select_addvcd_ztree">
		<div class="modal-dialog modal-lg" style="width: 400px;height:calc(100% - 300px);">
			<div class="modal-content" style="height:100%;width:400px;">
				<div class="modal-header" style="height: 40px">
					<button type="button" class="btn btn-link close"
						data-dismiss="modal">
						<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" style="line-height: 15px;"><i class='icon icon-plus'></i>选择行政区</h4>
				</div>
				<div class="modal-body" style="padding-top: 8px;height:calc(100% - 40px);width:400px;overflow-y:auto;overflow-x:hidden;">
					<ul id="addvcd_ztree" class="ztree" style="width: 260px; overflow: auto;">
					</ul>
				</div>
				<div class="modal-footer"
					style="height: 40px;">
					<button type="button" id="btn_selectaddvcd_ok" onclick="saveSelectAddvcd()" class="btn btn-primary"
						style="margin-top: -14px">
						<i class="icon icon-download-alt"></i> 确定
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
		function initTreeData(){
			//ztree数加载用同步
			var setting={
				async: {
					enable: true,
					url:basePath+"stbprp/stbprp!queryAddvcZtree.action",
					autoParam:["ADDVCD=id"]
				},
				data:{
					simpleData:{
						enable: true,
						idKey: "ADDVCD",
						pIdKey: "PADDVCD",
						rootPId: "0"
					},
					key: {
						name:"NAME",
					}
				},
				check: {enable: true,chkStyle: "radio",chkboxType: { "Y":"s","N":"s"},radioType: "all"}
			};
			$.ajax({
				url:basePath+"stbprp/stbprp!queryAddvcZtree.action",
				type:"post",
				data:{"id":"0"},
				dataType:"json",
				success:function(response){
					$.fn.zTree.init($("#addvcd_ztree"), setting, response);
				}
			});
		}
		$(function(){
			initTreeData();
		})		
		function saveSelectAddvcd(){
			var treeObj = $.fn.zTree.getZTreeObj("addvcd_ztree");
			var nodes = treeObj.getCheckedNodes(true);
			if(nodes!=null && nodes.length>0){
				$("#addvcd_stbprp").val(nodes[0].ADDVCD);
				$("#addvcdname_stbprp").val(nodes[0].NAME);
			}
			$("#select_addvcd_ztree").modal("hide");
		}
	</script>