package com.lyht.business.system.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysUser;
import com.lyht.business.system.dao.SysUserDao;
import com.lyht.business.system.formbean.SysAccountFormBean;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysUserService {

	@Resource 
	private SysUserDao mSysUserDao;
	
	/**
	 * 获取用户信息列表
	 * */
	public PageResults getSysAccountInfo(SysAccountFormBean mSysAccountFormBean){
		return mSysUserDao.getSysAccountInfo(mSysAccountFormBean);
	}
	
	/**
	 * 根据用户名称查询是否存在账户信息
	 * */
	public PageResults getSysUserInfoByUserName(SysUser mSysUser){
		return mSysUserDao.getSysUserInfoByUserName(mSysUser);
	}
	public PageResults getUser(String usercode,String psw){
		return mSysUserDao.getUser(usercode,psw);
	}
	
	public boolean updatePsw(String usercode,String psw) {
		return mSysUserDao.updatePsw(usercode, psw);
	}
	/**
	 * 新增用户信息
	 * */
	public boolean insertSysUserInfo(SysUser mSysUser,String userCode,String staffCode,SysUser sysUser){
		return mSysUserDao.insertSysUserInfo(mSysUser,userCode,staffCode,sysUser);
	}
	
	/**
	 * 新增用户信息
	 * */
	public boolean insertSysUserInfo(SysUser mSysUser,String userCode,String staffCode){
		return mSysUserDao.insertSysUserInfo(mSysUser,userCode,staffCode);
	}
	
	/**
	 * 修改用户信息
	 * */
	public boolean updateSysUserInfo(SysUser mSysUser){
		return mSysUserDao.updateSysUserInfo(mSysUser);
	}
	
	/**
	 * 删除用户信息
	 * */
	public boolean deleteSysUserInfo(String staffCode){
		boolean flag=false;
		String[] idary=staffCode.split(",");
		for(int i=0;i<idary.length;i++){
			flag=mSysUserDao.deleteSysUserInfo(idary[i]);
		}
		return flag;
	}
	
	/**
	 * 初始化密码
	 * */
	public boolean initPwd(String staffCode){
		return mSysUserDao.initPwd(staffCode);
	}
	
	/**
	 * 审核用户信息
	 * */
	public boolean auditSysUserInfo(SysUser mSysUser){
		return mSysUserDao.auditSysUserInfo(mSysUser);
	}
	
	/**
	 * 校验账号与密码是否存在
	 * */
	public SysUser checkAccount(SysUser sysUser){
		return mSysUserDao.checkAccount(sysUser);
	}
	
	/**
	 * 根据人员编号获取用户信息
	 * */
	public SysUser getUserInfoByStaffCode(String staffCode){
		return mSysUserDao.getUserInfoByStaffCode(staffCode);
	}
	
}
