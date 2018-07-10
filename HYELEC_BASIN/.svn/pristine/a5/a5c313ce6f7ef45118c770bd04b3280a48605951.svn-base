package com.lyht.business.system.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.control.SysStaffControl;
import com.lyht.business.system.formbean.SysStaffFormBean;
import com.lyht.business.system.service.SysGroupService;
import com.lyht.business.system.service.SysStaffService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/system")

@Controller
@Scope("prototype")
@Action("sysstaff")
@SuppressWarnings("rawtypes")
public class SysStaffAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysStaffAction");
	private SysStaffFormBean mSysStaffFormBean = new SysStaffFormBean();
	
	@Resource 
	private SysStaffControl mSysStaffControl;
	@Resource 
	private SysGroupService mSysGroupService;
	@Resource 
	private SysStaffService mSysStaffService;
	
	/**
	 * 获取人员信息列表
	 * */
	public String list(){
		log.info("SysStaffAction=list:获取人员信息列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mSysStaffControl.getSysStaffMes(mSysStaffFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 验证账号是否唯一
	 * */
	public String validateAccountInfo(){
		log.info("验证真实姓名是否唯一=========SysStaffAction.validateAccountInfo");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		//根据账号名称查询是否存在账户信息
		PageResults mPageResults=mSysStaffService.getSysStaffInfoByUserName(mSysStaffFormBean.getmSysStaffInfoBean());
		if(mPageResults.getResults().size()>0){
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}else{
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	
	public SysStaffFormBean getmSysStaffFormBean() {
		return mSysStaffFormBean;
	}

}
