package com.lyht.business.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.bean.SysOperRef;
import com.lyht.business.system.dao.SysOperRefDao;
import com.lyht.business.system.formbean.SysOperRefFormBean;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysOperRefService {

	@Resource
	private SysOperRefDao mSysOperRefDao;
	
	/**
	 * 根据角色编号查询关联表信息
	 * */
	public boolean getSysOperRefByRoleCode(SysOperRefFormBean mSysOperRefFormBean){
		boolean flag=false;
		PageResults mPageResults=mSysOperRefDao.getSysOperRefByRoleCode(mSysOperRefFormBean);
		if(mPageResults.getResults().size()>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据角色编号与菜单编码为菜单授权
	 * */
	public boolean sysMenuSqOperation(SysMenu mSysMenu,SysOperRefFormBean mSysOperRefFormBean){
		boolean flag=false;
		int i=mSysOperRefDao.sysMenuSqOperation(mSysMenu,mSysOperRefFormBean);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据角色编号与菜单编码查询权限表
	 * */
	@SuppressWarnings("unchecked")
	public List<Map> getSysRefByMenuCode(SysMenu mSysMenu,SysOperRefFormBean mSysOperRefFormBean){
		PageResults mPageResults=mSysOperRefDao.getSysRefByMenuCode(mSysMenu,mSysOperRefFormBean);
		return mPageResults.getResults();
	}
	
	/**
	 * 根据菜单表名与菜单编码查询权限表
	 * */
	public List<SysOperRef> getSysRefByMenuCode(String menuCode){
		return mSysOperRefDao.getSysRefByMenuCode(menuCode);
	}
	
	/**
	 * 根据角色编号与角色表名查询权限表
	 * */
	public List<SysOperRef> getSysRefByRoleCode(String roleCode){
		return mSysOperRefDao.getSysRefByRoleCode(roleCode);
	}
	
	/**
	 * 根据角色编号与菜单编号删除权限表
	 * */
	public boolean deleteSysRefByMenuRoleCode(SysMenu mSysMenu,SysOperRefFormBean mSysOperRefFormBean){
		boolean flag=false;
		int i= mSysOperRefDao.deleteSysRefByMenuRoleCode(mSysMenu,mSysOperRefFormBean);
		if(i>0){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 根据菜单编号删除权限表
	 * */
	public void deleteSysRefByMenuCode(SysOperRef mSysOperRef){
		mSysOperRefDao.deleteSysRefByMenuCode(mSysOperRef);
	}
	
	/**
	 * 根据角色编号删除权限表
	 * */
	public void deleteSysRefByRoleCode(SysOperRef mSysOperRef){
		mSysOperRefDao.deleteSysRefByRoleCode(mSysOperRef);
	}
	
}
