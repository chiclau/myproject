<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <!DOCTYPE html>
<html lang="zh-cn" class="screen-desktop-wide device-desktop"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
  <title>实时雨情展示</title>
  
    <style>
    
        .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row .col-md-6{
         width:0px;
        }
        .container-fluid .row:nth-child(3) .col-md-12 .col-md-8 .row{
         height:46px;
        }
        .container-fluid .row:nth-child(3) .col-md-12{
         height:211px;
        }
      #idone table tr td:nth-child(1){
        width:73px;
        height:40px;
      }
      #idone table tr td:nth-child(2){
        width:71px;
      }
      #idone table tr td:nth-child(3){
        width:71px;
      }
      #idone table tr td:nth-child(4){
        width:72px;
      }
       #idone table tr td{
       vertical-align:middle;
       word-wrap:break-word; 
       word-break:break-all;
       }  
    </style>
</head>
<body>

 	<div class="row" style="margin-top:15px">
 		<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6">
   			<select class="form-control" style="margin-left:10px;margin-top:12px;height:30px;padding:5px 8px;width: 100px;" onchange="show_sub(this.options[this.options.selectedIndex].value)">
					  <option value="0">-请选择-</option>
								<option value="1">行政区</option>
								<option value="2">流域</option>
            </select>
 	  </div>
 		<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6"  style="float: left;left: 20px">
   			<select class="form-control" style="margin-left:108px;margin-top:12px;height:30px;width: 100px;" id="mySelect">
			  <option value="0">-请选择-</option>
	        </select>
 		</div>
 	</div>
 	<div class="row" id="posi">
 		 <div class="row" style="height:20px;padding-left:6px;padding-top: 18px">
	            		   &nbsp;站类选择 :
	            		  <label class="checkbox-inline">
      						  <input type="checkbox" name='message' value="RR"> 水库
        						</label>
        						<label class="checkbox-inline">
        						  <input type="checkbox" name='message' value="ZS"> 河道
        						</label>
        						<label class="checkbox-inline">
        						  <input type="checkbox" name='message' value="DD"> 堰闸
        						</label>
        						<label class="checkbox-inline">
        						  <input type="checkbox" name='message' value="TT"> 潮汐
        						</label>
	            	</div>
 	            	<div class="row" style="height:20px;padding-left:8px;padding-top:2px">
             <!--  &nbsp;水库类型 :
                    <label class="checkbox-inline">
                    <input type="checkbox"> 大型
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 中型
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 小型
                    </label> -->
                </div>
                <div class="row" style="height:20px;padding-left:8px;">
                 <!-- &nbsp;报讯等级 :
                    <label class="checkbox-inline">
                    <input type="checkbox"> 中央
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 省重点
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 省一般
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox"> 山洪
                    </label> -->
                </div> 
 		
 	</div>

    <div style="height:50px">
      <div class="row" style="height:50px">
        <div class="col-md-12"  style="height:50px;margin-top:12px">
            <form class="form-inline">
            <div class="form-group">
              <label class="sr-only" for="exampleInputEmail3">测站名称</label>
              <input type="text" class="form-control" id="text" placeholder="测站名称">
            </div>
            <button type="button" class="btn btn-default btn btn-success" style="color:white" onclick="loadDataThree()">查询</button>
           </form>
        </div>
      </div>
    </div>
          <div class="row">
            <div class="col-md-12" id="idone">
                 <table class="table table-condensed bordered" >
                      <tr>
                          <th>地区</th>
                          <th>县市</th>
                          <th>站名</th>
                          <th>水位</th>
                      </tr>
                 </table>
              <div class="col-md-12" id="tablescontaion" style="overflow: auto; height: 178px; width: 298px;float: right;right: -10px">
                  <table class="table table-condensed bordered" id="waters">
                 </table>
                 <input type="hidden" value="0" id="mark">
                </div>
            </div>
        </div>
 </body>
 <script type="text/javascript">
	//下拉列表加载数据
 function show_sub(v){
 	$("#mySelect").empty();
 	//alert(v)
 	if(v==0){
 		$("#mySelect").append('<option value="0">-请选择-</option>')
 		return;
 	}
 	$.ajax({
 			url:basePath+'search/search!serchProvinceBasin.action?searchFormBean.administrativeRegionBasin='+v,
 			type:'post',
 			dataType:'json',
 			async:false,
 			success:function(datas){ 
 					//alert(datas)
 					$("#mySelect").append('<option value="0">-请选择-</option>')
 					for (var i = 0; i < datas.length; i++) {
 						$("#mySelect").append('<option value='+datas[i].id+'>'+datas[i].RVNM+'</option>')
 					}
 				}
 			})
 		}

 </script>
 </html>