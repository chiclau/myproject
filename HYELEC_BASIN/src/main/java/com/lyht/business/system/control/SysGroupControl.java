package com.lyht.business.system.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.SysGroup;
import com.lyht.business.system.formbean.SysGroupFormBean;
import com.lyht.business.system.service.SysGroupService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SysGroupControl {

	@Resource
	private SysGroupService mSysGroupService;
	
	@Optlog(menuflag="SysGroupList", opttype = "getSysGroupMes") 
	public RetMessage getSysGroupMes(SysGroupFormBean mSysGroupFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mSysGroupService.getSysGroupListData(mSysGroupFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SysGroupCreate", opttype = "create")  
	public RetMessage create(SysGroup mSysGroup,SysGroup retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mSysGroupService.create(mSysGroup));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SysGroupUpdate",opttype = "update")
	public RetMessage update(SysGroup mSysGroup,SysGroup retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mSysGroupService.update(mSysGroup));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SysGroupUpdate",opttype = "getSysGroupInfoListByIds") 
	public PageResults getSysGroupInfoListByIds(String ids){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mSysGroupService.getSysGroupInfoListByIds(ids);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="SysGroupDelete",opttype = "deleteSysGroupInfoByIds") 
	public RetMessage deleteSysGroupInfoByIds(String ids,List list){
		RetMessage mRetMessage=new RetMessage();
		try{
			mSysGroupService.deleteSysGroupInfoByIds(ids);
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
