package com.lyht.business.system.action;

import java.util.*;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.formbean.SysUserFormBean;
import com.lyht.business.system.service.SysUserService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.MD5;

@Namespace("/system")

@Controller
@Scope("prototype")
@Action("/sysuser")
@SuppressWarnings("rawtypes")
public class SysUserAction extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysUserAction");
	private SysUserFormBean mSysUserFormBean = new SysUserFormBean();
	
	@Resource 
	private SysUserService mSysUserService;
	
	/**
	 * 验证账号是否唯一
	 * */
	public String validateAccountInfo(){
		log.info("验证账号是否唯一=========SysUserAction.validateAccountInfo");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		//根据账号名称查询是否存在账户信息
		String usercode=this.getRequest().getParameter("");
		String pasw=this.getRequest().getParameter("");
		PageResults mPageResults=mSysUserService.getSysUserInfoByUserName(mSysUserFormBean.getmSysUserInfoBean());
		if(mPageResults.getResults().size()>0){
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}else{
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	public String checkUserPwd(){
		log.info("验证账号和密码");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		//根据账号名称查询是否存在账户信息
		String usercode=this.getRequest().getParameter("userCode");//userCode
		String psw=this.getRequest().getParameter("psw");//密码
		String pwd=MD5.getInstance().getMD5ofStr(psw, 16);//加密后的密码
		PageResults mPageResults=mSysUserService.getUser(usercode, pwd);
		if(mPageResults.getResults().size()>0){
			mHashMap.put("state","success");
			mHashMap.put("data",mPageResults.getResults());
		}else{
			mHashMap.put("state", "error");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	public String updatePsw(){
		log.info("修改密码");
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		//根据账号名称查询是否存在账户信息
		String usercode=this.getRequest().getParameter("userCode");//userCode
		String psw=this.getRequest().getParameter("psw");//密码
		String pwd=MD5.getInstance().getMD5ofStr(psw, 16);//加密后的密码
		boolean b=mSysUserService.updatePsw(usercode, pwd);//修改密码
		if(b==true) {//修改成功
			mHashMap.put("success", "success");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public SysUserFormBean getmSysUserFormBean() {
		return mSysUserFormBean;
	}

}
