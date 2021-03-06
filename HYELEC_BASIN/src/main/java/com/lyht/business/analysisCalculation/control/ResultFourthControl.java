package com.lyht.business.analysisCalculation.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ResultFourth;
import com.lyht.business.analysisCalculation.formbean.DataTable;
import com.lyht.business.analysisCalculation.formbean.ResultFourthFormBean;
import com.lyht.business.analysisCalculation.service.ResultFourthService;
import com.lyht.business.analysisCalculation.service.ResultThridService;
/**
 *作者： 刘魁
 *脚本日期:2018年6月22日 15:24:11
 *说明:  产流计算结果第三步Control 
*/
@Controller
@Scope("prototype")
public class ResultFourthControl {
	@Resource
	private 	ResultThridService  resultThridService;
	@Resource
	private   ResultFourthService resultFourthService;
	/**
	 * 保存第四步的数据方法
	 */
	@Optlog(menuflag="resultFourthCreate", opttype = "create")  
	public  RetMessage create(ResultFourth resultFouth) {
		RetMessage mRetMessage=new RetMessage();
		try {
			ResultFourth resultThrid1=new  ResultFourth();
			BeanUtils.copyProperties(resultFouth,resultFourthService.saveResultFourth(resultFouth));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	/**
	 * 第四步保存之后table查询
	 */
	@Optlog(menuflag="resultFourthQuery", opttype = "query")  
	public  RetMessage getStep4(ResultFourthFormBean resultThrid,PageResults mPageResults) {
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mPageResults,resultFourthService.getStep4(resultThrid));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询信息成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return mRetMessage;
	}
}
