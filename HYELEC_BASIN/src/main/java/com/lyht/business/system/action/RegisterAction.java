package com.lyht.business.system.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.business.system.bean.SysUser;
import com.lyht.business.system.formbean.SysStaffFormBean;
import com.lyht.business.system.formbean.SysUserFormBean;
import com.lyht.business.system.service.SysStaffService;
import com.lyht.business.system.service.SysUserService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.Randomizer;

@Namespace("/register")
@Controller
@Scope("prototype")
public class RegisterAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("RegisterAction");
	private SysUserFormBean mSysUserFormBean=new SysUserFormBean();
	private SysStaffFormBean mSysStaffFormBean=new SysStaffFormBean();
	
	@Resource 
	private SysUserService mSysUserService;
	@Resource 
	private SysStaffService mSysStaffService;
	
	/**
	 * 获取注册信息 
	 * */
	public String getRegisterInfo(){
		log.info("获取注册信息=========RegisterAction.getRegisterInfo");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		try {
			String userCode=Randomizer.nextNumber("user", 4); //账户编号
			String staffCode=Randomizer.nextNumber("staff", 3);//人员编号
			getSession().removeAttribute(Constants.SESSION_ACCT);
			mSysStaffService.insertSysStaffInfo(mSysUserFormBean.getmSysUserInfoBean(),userCode,mSysStaffFormBean.getmSysStaffInfoBean(),staffCode);
			mHashMap.put("flag", Constants.AJAX_RETFLAG_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHashMap.put("flag", Constants.AJAX_RETFLAG_ERROR);
		} 
		CommonFunction.writeResponse(getResponse(), mHashMap);
	    return null;
	}
	
	public SysUserFormBean getmSysUserFormBean() {
		return mSysUserFormBean;
	}

	public SysStaffFormBean getmSysStaffFormBean() {
		return mSysStaffFormBean;
	}
	
}
