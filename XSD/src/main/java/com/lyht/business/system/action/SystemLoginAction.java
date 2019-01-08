package com.lyht.business.system.action;

import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.TokenSession;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.bean.SysUser;
import com.lyht.business.system.service.SysMenuService;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.business.system.service.SysStaffService;
import com.lyht.business.system.service.SysUserService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.MD5;

@Namespace("/login")

@Results({ @Result(name = "role", location = "/business/system/home_page/list.jsp"),
		   @Result(name = "error", location = "/login.jsp")
})

@Controller
@Scope("prototype")
@Action("/system")
public class SystemLoginAction extends BaseLyhtAction{
	
	private static final long serialVersionUID = 1L;
	private Logger log= Logger.getLogger(SystemLoginAction.class);
	
	private SysUser mSysUser=new SysUser();
	
	@Resource 
	private SysUserService mSysUserService;
	@Resource 
	private SysStaffService mSysStaffService;
	@Resource 
	private SysRoleService mSysRoleService;
	@Resource 
	private SysMenuService mSysMenuService;
	
	/**
	 * 用户登录
	 * */
	@SuppressWarnings("rawtypes")
	public String login(){
		log.info("SystemLoginAction");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		String mark="error";
		try{
			SysUser sysUser = (SysUser) getSession().getAttribute(Constants.SESSION_ACCT);
			sysUser= checkAccount(mSysUser); //校验账号与密码是否存在
			String pwd=MD5.getInstance().getMD5ofStr(mSysUser.getUserPwd(), 16);
			String acctName=sysUser.getUserName();
			if(mSysUser.getUserName().equals(acctName)){
				if(sysUser.getUserPwd().equals(pwd)){
					if(sysUser.getStaffCode().length()>0){
						SysStaff mSysStaff=mSysStaffService.getStaffInfoByStaffCode(sysUser.getStaffCode());
						SysRole mSysRole=mSysRoleService.getSysRoleInfoByStaffCode(mSysStaff.getStaffCode());
						PageResults mPageResults=mSysMenuService.getSysMenuInfoByRoleCode(mSysRole.getRoleCode());
						setSessionData(mPageResults,mSysStaff,sysUser,mSysRole); //设置session数据
						mark="role";
					}
				}
			}
		}catch (Exception e) {
			log.info("SystemLoginAction.login==========登录出错！！！");
		}
		mHashMap.put("mark", mark);
	//	CommonFunction.writeResponse(getResponse(), mHashMap);
		return mark;
	}
	//验证用户名和密码是否正确
	public String check(){
		log.info("SystemLoginAction");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		String mark="error";
		try{
			SysUser sysUser = (SysUser) getSession().getAttribute(Constants.SESSION_ACCT);
			sysUser= checkAccount(mSysUser); //校验账号与密码是否存在
			String pwd=MD5.getInstance().getMD5ofStr(mSysUser.getUserPwd(), 16);
			String acctName=sysUser.getUserName();
			if(mSysUser.getUserName().equals(acctName)){
				if(sysUser.getUserPwd().equals(pwd)){
					if(sysUser.getStaffCode().length()>0){
						SysStaff mSysStaff=mSysStaffService.getStaffInfoByStaffCode(sysUser.getStaffCode());
						SysRole mSysRole=mSysRoleService.getSysRoleInfoByStaffCode(mSysStaff.getStaffCode());
						PageResults mPageResults=mSysMenuService.getSysMenuInfoByRoleCode(mSysRole.getRoleCode());
						setSessionData(mPageResults,mSysStaff,sysUser,mSysRole); //设置session数据
						mark="role";
					}
				}
			}
		}catch (Exception e) {
			log.info("SystemLoginAction.login==========登录出错！！！");
		}
		mHashMap.put("mark", mark);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 设置session数据
	 * */
	@SuppressWarnings("rawtypes")
	private void setSessionData(PageResults mPageResults,SysStaff mSysStaff,
			SysUser sysUser,SysRole mSysRole){
		TokenSession tkse=new TokenSession();
		tkse.setTokenid(UUID.randomUUID().toString());
		tkse.setFlag(1);
		tkse.setAcctnm(sysUser.getUserCode());
		getSession().setAttribute(Constants.SESSION_TOKENID, tkse);
		getSession().removeAttribute(Constants.SESSION_MENU);
		getSession().setAttribute(Constants.SESSION_MENU, mPageResults.getResults());
		getSession().setAttribute("mSysStaff", mSysStaff.getStaffCode());
		getSession().setAttribute("userCode", sysUser.getUserCode());
		getSession().setAttribute("userPwd", sysUser.getUserPwd());
		getSession().removeAttribute(Constants.SESSION_STAFF);
		getSession().setAttribute(Constants.SESSION_STAFF, mSysStaff);
		getSession().removeAttribute(Constants.SESSION_ACCT);
		getSession().setAttribute(Constants.SESSION_ACCT, sysUser);
		getSession().removeAttribute(Constants.SESSION_ROLE);
		getSession().setAttribute(Constants.SESSION_ROLE, mSysRole);
		getSession().setAttribute("mSysRole", mSysRole.getRoleName());
	}
	
	//注销用户信息
	public String logout(){
		getSession().removeAttribute(Constants.SESSION_TOKENID);
		getSession().removeAttribute(Constants.SESSION_STAFF);
		getSession().removeAttribute(Constants.SESSION_ACCT);
		getSession().removeAttribute(Constants.SESSION_MENU);
		getSession().removeAttribute(Constants.SESSION_ROLE);
		return "error";
	}
	
	/**
	 * 校验账号与密码是否存在
	 * */
	private SysUser checkAccount(SysUser sysUser){
		SysUser mSysUser=new SysUser();
		if(null!=sysUser){
			mSysUser=mSysUserService.checkAccount(sysUser);
		}
		return mSysUser;
	}
	
	public SysUser getmSysUser() {
		return mSysUser;
	}
}
