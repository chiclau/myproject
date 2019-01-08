<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
.clgl-13 {
	width: 100%;
	margin: 0 auto;
}

.clgl-14 {
	float: left;
	width: 20%;
	padding-top: 20px;
	line-height: 35px;
	text-align: center;
}

.clgl-16 {
	float: left;
	width: 80%;
	padding-top: 20px;
	line-height: 35px;
	text-align: center;
}
</style>
	<div class="container-fluid" style="position: relative;">
		<div id="body-step2-content" style="bottom: 0px;width: 100%;height: calc(100% - 40px);min-height:500px;">
			<div id="body-step2-content-top" style="width:100%;height:50px;">
				<div class="btn-group">
				  <button id="step_one" onClick="nextStep2(1)" class="btn">推理公式</button>
				  <button id="step_two" onClick="nextStep2(2)" class="btn">推理峰量法</button>
				  <button id="step_three" onClick="nextStep2(3)" class="btn">推理过程线法</button>
			 	  <button id="step_four" onClick="nextStep2(4)" class="btn">瞬时单位线法</button>
				  <button id="step_five" onClick="nextStep2(5)" class="btn">计算结果</button>
				</div>
			</div>
			<div id="step2-body-content-bottom"  style="width:100%;height:calc(100% - 50px);"></div>
		</div>
	</div>
</body>
<!-- 不要改变以下引用顺序 -->
<script type="text/javascript">
	var serverUrl="http://113.240.224.29:8099";
	var qjstcd= ""; // 测站编码
	var qjstnm= "";//测站名称
	var beginDate = ""; // 开始时间
	var endDate = ""; // 结束时间
	var interval = ""; //时间间隔
	var qjpch="";

	$(".form_datetime").datetimepicker({
		format : " yyyy-mm-dd hh:ii:ss"
	});
	nextStep2(1);
	
	function nextStep2(num){
							if (num == 1){
								$("#step_one").addClass("btn-success")
								$("#step_two").removeClass("btn-success")
									$("#step_three").removeClass("btn-success")
										$("#step_four").removeClass("btn-success")
											$("#step_five").removeClass("btn-success")
								tempUrl = basePath+"business/consumer/analysisjs/confluencejs/mainList.jsp";
							}else if (num == 2){
								$("#step_one").removeClass("btn-success")
								$("#step_two").addClass("btn-success")
									$("#step_three").removeClass("btn-success")
										$("#step_four").removeClass("btn-success")
											$("#step_five").removeClass("btn-success")
								tempUrl = basePath+"business/consumer/analysisjs/confluencejs/mainList2.jsp";
							}else if (num == 3){
								$("#step_one").removeClass("btn-success")
								$("#step_two").removeClass("btn-success")
									$("#step_three").addClass("btn-success")
										$("#step_four").removeClass("btn-success")
											$("#step_five").removeClass("btn-success")
								tempUrl = basePath+"business/consumer/analysisjs/confluencejs/mainList3.jsp";
							}else if (num == 4){
								$("#step_one").removeClass("btn-success")
								$("#step_two").removeClass("btn-success")
									$("#step_three").removeClass("btn-success")
										$("#step_four").addClass("btn-success")
											$("#step_five").removeClass("btn-success")
								tempUrl = basePath+"business/consumer/analysisjs/confluencejs/mainList4.jsp";
							}
							else if (num == 5){
								$("#step_one").removeClass("btn-success")
								$("#step_two").removeClass("btn-success")
									$("#step_three").removeClass("btn-success")
										$("#step_four").removeClass("btn-success")
											$("#step_five").addClass("btn-success")
								tempUrl = basePath+"business/consumer/analysisjs/confluencejs/mainList5.jsp";
							}
							$("#step2-body-content-bottom").load(tempUrl);
	}
	
	var i = 0;
	$("#hide_show").click(function(){
	if(i == 0){
	$("#step2-body-content-bottom").hide();
	$("#body-step2-content").css("height","32px");
	$("#body-step2-content").css("min-height","32px");
	i = 1;
	}else{
	$("#step2-body-content-bottom").show();
	$("#body-step2-content").css("height","50%");
	$("#body-step2-content").css("min-height","500px");
	i = 0;
	}
	});
</script>