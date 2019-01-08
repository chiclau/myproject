package com.lyht.business.system.control;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Addvcd;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.formbean.AddvcdFormBean;
import com.lyht.business.system.service.AddvcdService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class AddvcdControl {

	@Resource
	private AddvcdService mAddvcdService;
	
	@Optlog(menuflag="addvcdList", opttype = "getListRootData") 
	public RetMessage getListRootData(AddvcdFormBean mAddvcdFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mAddvcdService.getListRootData(mAddvcdFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="addvcdList", opttype = "getAddvcdMes") 
	public RetMessage getAddvcdMes(AddvcdFormBean mAddvcdFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mAddvcdService.getAddvcdListData(mAddvcdFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="addvcdView", opttype = "view") 
	public RetMessage view(String addvcd,Addvcd mAddvcd,Addvcd mPAddvcd){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mAddvcd,mAddvcdService.getAddvcdInfoByAddvcd(addvcd));
			if (mAddvcd.getPaddvcd().length()>0){
				BeanUtils.copyProperties(mPAddvcd,mAddvcdService.getAddvcdInfoByProp("paddvcd", mAddvcd.getPaddvcd()));
			}
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="addvcdView", opttype = "add")  
	public RetMessage add(String addvcd,Addvcd mPAddvcd){
		RetMessage mRetMessage=new RetMessage();
		try {
			if (addvcd != null && addvcd != ""){
				BeanUtils.copyProperties(mPAddvcd,mAddvcdService.getAddvcdInfoById(addvcd));
			}
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="AddvcdRootCreate", opttype = "create")  
	public RetMessage create(Addvcd mAddvcd,Addvcd retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mAddvcdService.create(mAddvcd));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	//增加子节点
	@Optlog(menuflag="AddvcdRootCreate", opttype = "create")  
	public RetMessage create_(Addvcd mAddvcd,Addvcd retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mAddvcdService.create_(mAddvcd));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="AddvcdUpdate",opttype = "update")  //记录日志
	public RetMessage updateAddvcdInfo(Addvcd mAddvcd){
		RetMessage mRetMessage=new RetMessage();
		try {
			mAddvcdService.updateAddvcdInfo(mAddvcd);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="PptnImport", opttype = "import") 
	public RetMessage importPptn(File[] file,String[] fileFileName){
		RetMessage ret=new RetMessage();
		try {
			mAddvcdService.importPptn(file,fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入失败！");
			e.printStackTrace();
		}
		return ret;
	}
	
	
}
