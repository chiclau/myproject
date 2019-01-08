<%@ page language="java" pageEncoding="UTF-8"%>
<style>
	.file-list .file[data-status=uploading] .file-status>.text, .uploader-files .file[data-status=uploading] .file-status>.text{
		display:none;
	} 
	.file-list .file-status>.text, .uploader-files .file-status>.text{
		display:inline;
	}
	.file-list, .uploader-files{
		min-height:36px;
		max-height:200px;
		overflow:auto;
	} 
</style>

<form enctype="multipart/form-data" id="" name="" class="form-horizontal" autocomplete="off"
	method="post" data-bv-message="This value is not valid"
	data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
	data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
	data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">

	<div class="modal fade" id="import_dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
                    <div id='myUploader' class="uploader">
					  <div class="uploader-message text-center">
					    <div class="content"></div>
					  </div>
					  <div class="uploader-files file-list file-list-lg" data-drag-placeholder="请拖拽文件到此处"></div>
					  <div class="uploader-actions">
					    <div class="uploader-status pull-right text-muted"></div>
					    <button type="button" class="btn btn-link uploader-btn-browse"><i class="icon icon-plus"></i> 选择文件</button>
					    <button type="button" class="btn btn-link uploader-btn-start"><i class="icon icon-cloud-upload"></i>开始导�?/button>
					  </div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">
						<i class="icon icon-signout"></i> 关闭
					</button>
				</div>
			</div>
		</div>
	</div>
</form>


