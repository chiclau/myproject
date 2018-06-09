package com.lyht.business.analysisCalculation.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.business.analysisCalculation.bean.Result;
import com.lyht.business.analysisCalculation.service.ResultService;
/**
 *作者： 刘魁
 *脚本日期:2018年6月4日 15:24:11
 *说明:  产流计算结果Control 
*/
@Controller
@Scope("prototype")
public class ResultControl {
	@Resource
	private 	ResultService  resultService;
	/**
	 * 保存

	 */
	@Optlog(menuflag="modelProaramCreate", opttype = "create")  
	public  RetMessage create(Result result,Result result1) {
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(result,resultService.saveResult(result,result1));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	
	
	
}
