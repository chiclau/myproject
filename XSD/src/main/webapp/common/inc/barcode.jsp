<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade" id="barcode">
	<div class="modal-dialog modal-lg" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-header" style="height: 40px">
				<button type="button" class="btn btn-link close"
					data-dismiss="modal">
					<span aria-hidden="true"><i class="icon icon-times"></i></span><span class="sr-only">关闭</span>
				</button>
				<h4 class="modal-title" style="line-height: 15px;"><i class="icon icon-resize"></i>&nbsp;生成二维码</h4>
			</div>
				<div style="display:none;">
				</div>
			<div class="form-group"style="margin-top: 5px;" >
				 <label class="col-md-3 " style="margin-top: 5px;">请输入地址:</label>
				 <div class="col-md-9">
				 <input type="text" id="url" class="form-control"  value=""/>
				 </div>
			</div>
			<div id="qrcodeTable" style="height: 250px;width: 250px;margin-top:50px;margin-left: 110px;" ></div> 
			<div class="modal-footer" style="height: 40px;">
				<button type="button" class="btn btn-primary" onclick="creat()" style="margin-top: -14px;">
					<i class="icon icon-download-alt"></i> 生成
				</button>
				<button type="button" class="btn btn-large" data-dismiss="modal" style="margin-top: -14px; margin-right: -5px">
					<i class="icon icon-times"></i> 取消
				</button>
			</div>
		</div>
	</div>
</div>

