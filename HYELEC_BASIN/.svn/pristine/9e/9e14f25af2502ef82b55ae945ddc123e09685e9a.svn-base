package com.lyht.business.system.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.bean.SysUser;
import com.lyht.business.system.dao.SysGroupDao;
import com.lyht.business.system.dao.SysRoleDao;
import com.lyht.business.system.dao.SysStaffDao;
import com.lyht.business.system.formbean.SysStaffFormBean;


@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysStaffService {

	@Resource 
	private SysStaffDao mSysStaffDao;
	@Resource
	private SysRoleDao roleDao;
	@Resource
	private SysGroupDao mSysGroupDao;
	/**
	 * 获取人员信息列表
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSysStaffListData(SysStaffFormBean mSysStaffFormBean){
		return mSysStaffDao.getSysStaffListData(mSysStaffFormBean);
	}
	
	/**
	 * 新增人员信息
	 * */
	public boolean insertSysStaffInfo(SysStaff mSysStaff,String staffCode,SysUser mSysUser){
		return mSysStaffDao.insertSysStaffInfo(mSysStaff,staffCode,mSysUser);
	}
	
	/**
	 * 新增人员信息
	 * */
	public boolean insertSysStaffInfo(SysStaff mSysStaff,String staffCode){
		SysRole object=roleDao.getSysRoleInfoById(3);//获取普通用户的角色信息
		String roleStaffCode=object.getStaffCode();
		roleDao.updateSysRoleInfoById("3",roleStaffCode+","+staffCode);//设置人员
		return mSysStaffDao.insertSysStaffInfo(mSysStaff,staffCode);
	}
	
	/**
	 * 修改人员信息
	 * */
	public boolean updateSysStaffInfo(SysStaff mSysStaff){
		return mSysStaffDao.updateSysStaffInfo(mSysStaff);
	}
	
	/**
	 * 删除人员信息
	 * */
	public boolean deleteSysStaffInfo(String staffCode){
		boolean flag=false;
		String[] idary=staffCode.split(",");
		for(int i=0;i<idary.length;i++){
			flag=mSysStaffDao.deleteSysStaffInfo(idary[i]);
		}
		return flag;
	}
	
	/**
	 * 审核人员信息
	 * */
	public boolean auditSysStaffInfo(SysUser mSysUser){
		return mSysStaffDao.auditSysStaffInfo(mSysUser);
	}
	
	/**
	 * 根据人员编号获取人员信息
	 * */
	public SysStaff getStaffInfoByStaffCode(String staffCode){
		return mSysStaffDao.getStaffInfoByStaffCode(staffCode);
	}
	
	/**
	 * 根据用户名称查询是否存在账户信息
	 * */
	public PageResults getSysStaffInfoByUserName(SysStaff mSysStaff){
		return mSysStaffDao.getSysStaffInfoByUserName(mSysStaff);
	}
}
