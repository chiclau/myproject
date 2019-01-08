package com.lyht.business.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.dao.SysMenuDao;
import com.lyht.business.system.formbean.SysMenuFormBean;
import com.lyht.business.system.formbean.SysOperRefFormBean;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysMenuService {

	@Resource 
	private SysMenuDao mSysMenuDao;
	
	/**
	 * 获取根节点数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getListRootData(SysMenuFormBean mSysMenuFormBean){
		return mSysMenuDao.getListRootData(mSysMenuFormBean);
	}
	
	/**
	 * 获取节点数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED) 
	public PageResults getListData(SysMenuFormBean mSysMenuFormBean){
		return mSysMenuDao.getListData(mSysMenuFormBean);
	}
	
	/**
	 * 根据全编码获取菜单信息
	 * */
	public PageResults getSysMenuByFcode(SysOperRefFormBean mSysOperRefFormBean,SysMenuFormBean mSysMenuFormBean){
		return mSysMenuDao.getSysMenuByFcode(mSysOperRefFormBean,mSysMenuFormBean);
	}
	
	/**
	 * 根据菜单编号获取菜单信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED) 
	public SysMenu getSysMenuInfoById(String fcode){
		SysMenu mSysMenu= new SysMenu();
		mSysMenu=mSysMenuDao.getSysMenuInfoById(fcode);
		return mSysMenu;
	}
	
	/**
	 * 根据属性及属性值查找对象实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysMenu getSysMenuInfoByProp(String PropName,Object PropValue){
		return mSysMenuDao.getSysMenuInfoByProp(PropName,PropValue);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysMenu create(SysMenu mSysMenu){
		mSysMenu.setMenuCode(Randomizer.nextNumber("menu", 4));
		mSysMenuDao.save(mSysMenu);
		return mSysMenu;
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(SysMenu mSysMenu){
		mSysMenuDao.update(mSysMenu);
	}
	
	/**
	 * 根据菜单编号获取菜单信息及子菜单信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getRootOrChildSysMenuInfoByMenuCode(String fCode){
      return mSysMenuDao.getSysMenuInfoByFCode(fCode);
	}
	
	/**
	 * 根据menuCode(1,2,3,4,5)删除多个对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSysMenuInfoByFCode(String fCode){
		String[] menuCodeArr=fCode.split(",");
		for(int i=0;i<menuCodeArr.length;i++){
			mSysMenuDao.deleteSysMenuInfoByFCode(menuCodeArr[i]);
		}
	}
	
	/**
	 * 根据菜单编号查询全编码
	 * */
	public List<SysMenu> getFCodeByMenuCode(String menuCode){
		SysMenu mSysMenu=mSysMenuDao.getSysMenuInfoByMenuCode(menuCode);
		return mSysMenuDao.getFCodeByMenuCode(mSysMenu);
	}
	
	/**
	 * 获取菜单信息
	 * */
	public List<SysMenu> getSysMenuInfoList(){
		return mSysMenuDao.getSysMenuInfoList();
	}
	
	/**
	 * 根据角色编号获取菜单信息
	 * */
	public PageResults getSysMenuInfoByRoleCode(String RoleCode){
		return mSysMenuDao.getSysMenuInfoByRoleCode(RoleCode);
	}
	
}
