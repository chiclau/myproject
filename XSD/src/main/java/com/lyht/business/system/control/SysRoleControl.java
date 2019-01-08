package com.lyht.business.system.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.formbean.SysRoleFormBean;
import com.lyht.business.system.service.SysRoleService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysRoleControl {

	@Resource
	private SysRoleService mSysRoleService;
	
	@Optlog(menuflag="SysRoleList", opttype = "getSysRoleMes") 
	public RetMessage getSysRoleMes(SysRoleFormBean mSysRoleFormBean, PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mSysRoleService.getSysRoleListData(mSysRoleFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SysRoleView", opttype = "view") 
	public RetMessage view(int id,SysRole mSysRole){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mSysRole,mSysRoleService.getSysRoleInfoById(id));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SysRoleCreate", opttype = "create")  
	public RetMessage create(SysRole mSysRole,SysRole retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mSysRoleService.create(mSysRole));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SysRoleUpdate",opttype = "update")
	public RetMessage update(SysRole mSysRole,SysRole retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mSysRoleService.update(mSysRole));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="getSysRoleInfoListByIds",opttype = "getSysRoleInfoListByIds") 
	public PageResults getSysRoleInfoListByIds(String ids){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mSysRoleService.getSysRoleInfoListByIds(ids);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="SysRoleDelete",opttype = "deletSysRoleInfoByIds") 
	public RetMessage deleteSysRoleInfoByIds(String ids,List list){
		RetMessage mRetMessage=new RetMessage();
		try{
			mSysRoleService.deleteSysRoleInfoByIds(ids);
			list.clear();
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	
	
}
