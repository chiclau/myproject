package com.lyht.business.policy.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.policy.formbean.ZcfgInfoFormBean;
import com.lyht.business.policy.service.ZcfgService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes") 
public class ZcfgControl {

	@Resource
	private ZcfgService zcfgService;
	
	@Optlog(menuflag="zcfgList", opttype = "getZcfgMes") 
	public RetMessage getZcfgMes(ZcfgInfoFormBean mZcfgInfoFormBean, PageResults mPageResults) {
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,zcfgService.getZcfgListData(mZcfgInfoFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}

}
