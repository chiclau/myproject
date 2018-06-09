package com.lyht.business.system.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.business.system.dao.SysGroupDao;
import com.lyht.business.system.dao.SysMenuDao;
import com.lyht.business.system.dao.SysRoleDao;
import com.lyht.business.system.dao.SysStaffDao;
import com.lyht.business.system.formbean.SysRoleFormBean;
import com.lyht.util.DesUtils;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class SysRoleService {

	@Resource
	private SysRoleDao mSysRoleDao;
	@Resource
	private SysMenuDao mSysMenuDao;
	@Resource
	private SysStaffDao mSysStaffDao;
	@Resource
	private SysGroupDao mSysGroupDao;
	
	/**
	 * 获取角色信息列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSysRoleListData(SysRoleFormBean mSysRoleFormBean){
		return mSysRoleDao.getSysRoleListData(mSysRoleFormBean);
	}
	
	/**
	 * 根据人员编号查询角色信息
	 * */
	public SysRole getSysRoleInfoByStaffCode(String staffCode){
		return mSysRoleDao.getSysRoleInfoByStaffCode(staffCode);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysRole create(SysRole mSysRole){
		mSysRoleDao.saveSysRoleInfo(mSysRole);
		return mSysRole;
	}
	
	/**
	 * 修改实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysRole update(SysRole mSysRole){
		mSysRoleDao.updateSysRoleInfo(mSysRole);	
		return mSysRole;
	}
	
	/**
	 * 根据主键ID获取角色信息实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getSysRoleInfoListByIds(String ids){
		return mSysRoleDao.getSysRoleInfoListByIds(ids);
	}
	
	/**
	 * 根据主键ID删除角色信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteSysRoleInfoByIds(String ids){
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			mSysRoleDao.deleteSysRoleInfoByIds(Integer.parseInt(idary[i]));
		}
	}
	
	/**
	 * 根据主键ID查询角色信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public SysRole getSysRoleInfoById(int id){
		return mSysRoleDao.getSysRoleInfoById(id);
	}
	
	/**
	 * 根据角色编号查询角色信息
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public Object getSysRoleInfoByRoleCode(String roleCode){
		return mSysRoleDao.getSysRoleInfoByRoleCode(roleCode);
	}
	
	/**
	 * 根据角色编号修改菜单编号
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateSysRoleByMenuCode(SysMenu mSysMenu,String roleCode){
		mSysRoleDao.updateSysRoleByMenuCode(mSysMenu,roleCode);
	}
	
	/**
	 * 根据角色编号修改菜单编号
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateSysRoleByMenuCode(String menuCode,String roleCode){
		mSysRoleDao.updateSysRoleByMenuCode(menuCode,roleCode);
	}
	
	/**
	 * 角色设置人员
	 * */
	public boolean setStaffNameBySysRoleId(String ids,String staffCode){
		return mSysRoleDao.updateSysRoleInfoById(ids,staffCode);
	}
	
	/**
	 * 移除角色信息人员编号
	 * */
	public void removeSysRoleStaffCode(String staffCode){
		List<Map> list=mSysRoleDao.getSysRoleByStaffCode(staffCode);
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				Object obj_=list.get(i).get("ROLE_CODE");
				Object obj=list.get(i).get("STAFF_CODE");
				if(null!=obj && null!=obj_){
					String key=obj.toString(); //数据库查询
					String staffCode_=DesUtils.removeListElement(key.split(","),staffCode);
					mSysRoleDao.updateSysRoleByStaffCode(obj_.toString(),staffCode_);
				}
			}
		}
	}
	
	/**
	 * 移除角色信息分组编号
	 * */
	public void removeSysRoleByGroupCode(List<Map> list){
		for(int i=0;i<list.size();i++){
			Object obj=list.get(i).get("GROUP_CODE");
			if(null!=obj){
				String key=obj.toString();
				List<Map> list_=mSysRoleDao.getSysRoleByGroupCode(key);
				if(list_.size()>0){
					for(int j=0;j<list_.size();j++){
						Object _obj_=list_.get(i).get("ROLE_CODE");
						Object obj_=list_.get(i).get("GROUP_CODE");
						if(null!=obj_ && null!=_obj_){
							String key_=obj_.toString();
							String groupCode_=DesUtils.removeListElement(key_.split(","),key);
							mSysRoleDao.updateSysRoleByGroupCode(_obj_.toString(),groupCode_);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 根据分组编号设置分组信息分组名称
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean setGroupNameBySysRoleId(String []ids,String []groupCode){
		String str="";
		boolean flag=false;
		for(int i=0;i<groupCode.length;i++){
			for(int j=0;j<ids.length;j++){
				Object obj=mSysRoleDao.getSysRoleInfoListById(ids[j]);
				if(null==obj || "".equals(obj)){
					flag=mSysRoleDao.updateSysRoleInfoByIds(ids[j], groupCode[i]);
				}else{
					String []groupCode_=obj.toString().split(","); //数据库查询
					for(int k=0;k<groupCode_.length;k++){
						if(groupCode_[k].equals(groupCode[i])){
							str=obj.toString();
							break;
						}else{
							str=obj.toString()+","+groupCode[i];
						}
					}
					str=DesUtils.removeDuplicate(str);
					flag=mSysRoleDao.updateSysRoleInfoByIds(ids[j],str);
				}
			}
		}
		return flag;
	}
	
	
	/**
	 * 根据编号获取菜单、分组名称
	 * */
	@SuppressWarnings({ "unchecked" })
	public List<Map> getMenuAndGroupByCode(PageResults mPageResults){
		String str="";
		List<Map> list=mPageResults.getResults();
		for(int i=0;i<list.size();i++){
			Object obj=list.get(i).get("ROLE_CODE"); //角色编号
			Object obj_=list.get(i).get("STAFF_CODE"); //人员编号
			Object _obj_=list.get(i).get("GROUP_CODE"); //分组编号
			if(null!=obj_){
				String key=obj_.toString();
				SysStaff mSysStaff=mSysStaffDao.getStaffInfoByStaffCode(key);
				list.get(i).put("STAFF_NAME", mSysStaff.getStaffName());
			}
			if(null!=_obj_ ){
				String key[]=_obj_.toString().split(",");
				for(int j=0;j<key.length;j++){
					String groupName=mSysGroupDao.getSysGroupInfoByCode(key[j]);
					str+=groupName+",";
				}
				if(",".equals(str.substring(str.length()-1))){
					str=str.substring(0,str.length()-1);
				}
				list.get(i).put("GROUP_NAME", str);
			}
			str="";
			if(null!=obj ){
				int menuCode=0;
				String key=obj.toString();
				List<Map> list_=mSysRoleDao.getMenuNumByRoleCode(key);
				if(list_.size()>0){
					menuCode=Integer.parseInt(list_.get(0).get("MENU_CODE").toString());
				}
				list.get(i).put("MENU_NAME", menuCode);
			}
		}
		return list;
	}
	
}
