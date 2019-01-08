package com.lyht.business.system.control;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.formbean.SysStaffFormBean;
import com.lyht.business.system.service.SysStaffService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysStaffControl {

	@Resource 
	private SysStaffService mSysStaffService;
	
	@Optlog(menuflag="sysStaffList", opttype = "getSysStaffMes") 
	public RetMessage getSysStaffMes(SysStaffFormBean mSysStaffFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mSysStaffService.getSysStaffListData(mSysStaffFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
}
