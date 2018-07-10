package com.lyht.business.system.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysMenu;
import com.lyht.business.system.formbean.SysMenuFormBean;
import com.lyht.business.system.service.SysMenuService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysMenuControl {

	@Resource
	private SysMenuService mSysMenuService;
	
	/**
	 * 获取根节点数据
	 * */
	@Optlog(menuflag="menu_system_sysmenu", opttype = "getListRootData") 
	public RetMessage getListRootData(SysMenuFormBean mSysMenuFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mPageResults,mSysMenuService.getListRootData(mSysMenuFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	/**
	 * 获取节点数据
	 * */
	@Optlog(menuflag="menu_system_sysmenu", opttype = "getListData")  
	public RetMessage getListData(SysMenuFormBean mSysMenuFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mPageResults,mSysMenuService.getListData(mSysMenuFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="menu_system_sysmenu", opttype = "view")  
	public RetMessage view(String fcode,SysMenu mSysMenu,SysMenu mPSysMenu){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mSysMenu,mSysMenuService.getSysMenuInfoById(fcode));
			if (mSysMenu.getSuperCode().length()>0){
				BeanUtils.copyProperties(mPSysMenu,mSysMenuService.getSysMenuInfoByProp("fCode", mSysMenu.getSuperCode()));
			}
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="menu_system_sysmenu", opttype = "add")  
	public RetMessage add(String fcode,SysMenu mPSysMenu){
		RetMessage mRetMessage=new RetMessage();
		try {
			if (!"".equals(fcode)){
				BeanUtils.copyProperties(mPSysMenu,mSysMenuService.getSysMenuInfoById(fcode));
			}
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="menu_system_sysmenu", opttype = "create")  
	public RetMessage create(SysMenu mSysMenu,SysMenu retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mSysMenuService.create(mSysMenu));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="menu_system_sysmenu",opttype = "update")  //记录日志
	public RetMessage update(SysMenu mSysMenu){
		RetMessage mRetMessage=new RetMessage();
		try {
			mSysMenuService.update(mSysMenu);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	public PageResults getSysMenuListById(String fCode){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mSysMenuService.getRootOrChildSysMenuInfoByMenuCode(fCode);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="menu_system_sysmenu",opttype = "deleteSysMenuInfoByMenuCode")
	public RetMessage deleteSysMenuInfoByFCode(String fCode,List list){
		RetMessage mRetMessage=new RetMessage();
		try {
			mSysMenuService.deleteSysMenuInfoByFCode(fCode);
			list.clear();
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
}
