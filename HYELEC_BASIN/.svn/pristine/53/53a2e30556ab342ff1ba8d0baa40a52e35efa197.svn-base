package com.lyht.business.system.action;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.bean.SysUser;
import com.lyht.business.system.formbean.SysAccountFormBean;
import com.lyht.business.system.service.SysGroupService;
import com.lyht.business.system.service.SysRoleService;
import com.lyht.business.system.service.SysStaffService;
import com.lyht.business.system.service.SysUserService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.Randomizer;

import net.sf.json.JSONArray;

@Namespace("/system")
@Controller
@Scope("prototype")
@Action("sysaccount")
@SuppressWarnings("rawtypes")
public class SysAccountAction extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("SysAccountAction");
	private SysAccountFormBean mSysAccountFormBean = new SysAccountFormBean();
	
	@Resource 
	private SysUserService mSysUserService;
	@Resource
	private SysStaffService mSysStaffService;
	@Resource
	private SysGroupService mSysGroupService;
	@Resource
	private SysRoleService mSysRoleService;
	
	/**
	 * 获取账户信息列表
	 * */
	public String list(){
		log.info("SysAccountAction=list:获取账户信息列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		PageResults mPageResults=new PageResults();
		mPageResults= mSysUserService.getSysAccountInfo(mSysAccountFormBean);
		if(mPageResults.getResults().size()>0){
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());
		}else{
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化账户信息FORM表单数据
	 * */
	public String edit(){
		log.info("SysAccountAction=edit:初始化账户信息FORM表单数据");
		String staffCode=mSysAccountFormBean.getmSysStaffInfoBean().getStaffCode();
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		SysUser mSysUser = new SysUser();
		SysStaff mSysStaff=new SysStaff();
		mSysUser=mSysUserService.getUserInfoByStaffCode(staffCode); //根据人员编号获取用户信息
		mSysStaff=mSysStaffService.getStaffInfoByStaffCode(staffCode); //根据人员编号获取人员信息
		if("".equals(mSysUser.getUserPwd())){
			mSysUser.setUserPwd("123456");
		}
		mHashMap.put("mSysUserInfoBean", mSysUser);
		mHashMap.put("mSysStaffInfoBean", mSysStaff);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存账户信息
	 * */
	public String save(){
		log.info("SysAccountAction=save:修改账户信息");
		String userCode_=mSysAccountFormBean.getmSysUserInfoBean().getUserCode();
		String staffCode_=mSysAccountFormBean.getmSysStaffInfoBean().getStaffCode();
		HashMap<String, Object> mHashMap=insertUpdateUserStaffInfo(userCode_,staffCode_);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 删除账户信息
	 * */
	public String delete(){
		log.info("SysAccountAction=delete:删除账户信息");
		String staffCode=mSysAccountFormBean.getmSysStaffInfoBean().getStaffCode();
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		boolean sysUserInfo=mSysUserService.deleteSysUserInfo(staffCode);
		boolean sysStaffInfo=mSysStaffService.deleteSysStaffInfo(staffCode);
		if(sysUserInfo && sysStaffInfo){
			mSysGroupService.removeSysGroupStaffCode(staffCode);
			mSysRoleService.removeSysRoleStaffCode(staffCode);
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化密码
	 * */
	public String initPwd(){
		log.info("SysAccountAction=initPwd:初始化密码");
		String staffCode=mSysAccountFormBean.getmSysStaffInfoBean().getStaffCode();
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		boolean sysUserInfo=mSysUserService.initPwd(staffCode);
		if(sysUserInfo){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化审核FORM表单数据
	 * */
	public String initFlag(){
		log.info("SysAccountAction=initFlag:审核账户信息");
		String staffCode=mSysAccountFormBean.getmSysStaffInfoBean().getStaffCode();
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		SysUser mSysUser=new SysUser();
		mSysUser=mSysUserService.getUserInfoByStaffCode(staffCode);
		mHashMap.put("mSysUserInfoBean", mSysUser);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 审核账户信息
	 * */
	public String flag(){
		log.info("SysAccountAction=flag:审核账户信息");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		boolean sysUserInfo=mSysUserService.auditSysUserInfo(mSysAccountFormBean.getmSysUserInfoBean());
		boolean sysStaffInfo=mSysStaffService.auditSysStaffInfo(mSysAccountFormBean.getmSysUserInfoBean());
		if(sysUserInfo && sysStaffInfo){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 新增与修改账户信息操作
	 * */
	private HashMap<String, Object> insertUpdateUserStaffInfo(String userCode_,String staffCode_){
		HashMap<String, Object> mHashMap=new HashMap<String, Object>();
		boolean sysUserInfo=false;
		boolean	sysStaffInfo=false;
		if("".equals(userCode_) && "".equals(staffCode_)){
			String userCode=Randomizer.nextNumber("user", 4); //账户编号
			String staffCode=Randomizer.nextNumber("staff", 3);//人员编号
			SysUser mSysUser = (SysUser) getSession().getAttribute(Constants.SESSION_ACCT);
			sysUserInfo=mSysUserService.insertSysUserInfo(mSysAccountFormBean.getmSysUserInfoBean(),userCode,staffCode,mSysUser);
			sysStaffInfo=mSysStaffService.insertSysStaffInfo(mSysAccountFormBean.getmSysStaffInfoBean(),staffCode,mSysUser);
		}else{
			sysUserInfo=mSysUserService.updateSysUserInfo(mSysAccountFormBean.getmSysUserInfoBean());
			sysStaffInfo=mSysStaffService.updateSysStaffInfo(mSysAccountFormBean.getmSysStaffInfoBean());
		}
		if(sysUserInfo && sysStaffInfo){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		return mHashMap;
	}

	
	public SysAccountFormBean getmSysAccountFormBean() {
		return mSysAccountFormBean;
	}

	public void setmSysAccountFormBean(SysAccountFormBean mSysAccountFormBean) {
		this.mSysAccountFormBean = mSysAccountFormBean;
	}
	
}
