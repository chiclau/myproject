package com.lyht.business.system.control;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.formbean.EnnmcdFormBean;
import com.lyht.business.system.service.EnnmcdService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class EnnmcdControl {
	
	@Resource
	private EnnmcdService mEnnmcdService;
	
	/** 查询流域树形 
	 * @param pid
	 * @return
	 */
	public List<Map> searcLyTree(String pid){
		return mEnnmcdService.searchLyTree(pid);
	}
	
	/**
	 * 获取根节点数据
	 * */
	@Optlog(menuflag="menu_system_sysmenu", opttype = "getListRootData") 
	public RetMessage getListRootData(EnnmcdFormBean mEnnmcdFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mPageResults,mEnnmcdService.getListRootData(mEnnmcdFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="EnnmcdList", opttype = "getEnnmcdMes") 
	public RetMessage getEnnmcdMes(EnnmcdFormBean mEnnmcdFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mEnnmcdService.getEnnmcdListData(mEnnmcdFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="EnnmcdView", opttype = "view") 
	public RetMessage view(String rvcd,Ennmcd mEnnmcd,Ennmcd mPEnnmcd){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mEnnmcd,mEnnmcdService.getEnnmcdInfoById(rvcd));
			if (mEnnmcd.getPrvcd().length()>0){
				BeanUtils.copyProperties(mPEnnmcd,mEnnmcdService.getEnnmcdInfoByProp("prvcd", mEnnmcd.getPrvcd()));
			}
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="EnnmcdAdd", opttype = "add")  
	public RetMessage add(String rvcd,Ennmcd mPEnnmcd){
		RetMessage mRetMessage=new RetMessage();
		try {
			if (!"".equals(rvcd)){
				BeanUtils.copyProperties(mPEnnmcd,mEnnmcdService.getEnnmcdInfoById(rvcd));
			}
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询信息失败！");
		}
		return mRetMessage;
	}
	
	/**
	 * （根节点）
	 * */
	@Optlog(menuflag="EnnmcdRootCreate", opttype = "create")  
	public RetMessage create(Ennmcd mEnnmcd,Ennmcd retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mEnnmcdService.create(mEnnmcd));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	/**
	 * （子节点）
	 * */
	@Optlog(menuflag="EnnmcdChildCreate", opttype = "create")  
	public RetMessage create(Ennmcd mEnnmcd,Ennmcd retBean,Ennmcd mPEnnmcd){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mEnnmcdService.create(mEnnmcd,mPEnnmcd));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="EnnmcdUpdate",opttype = "update")  //记录日志
	public RetMessage updateEnnmcdInfo(Ennmcd mEnnmcd){
		RetMessage mRetMessage=new RetMessage();
		try {
			mEnnmcdService.updateEnnmcdInfo(mEnnmcd);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	public PageResults getEnnmcdListByRvcd(EnnmcdFormBean mEnnmcdFormBean){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mEnnmcdService.getRootOrChildEnnmcdInfoByRvcd(mEnnmcdFormBean);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="PptnImport", opttype = "import") 
	public RetMessage importPptn(File[] file,String[] fileFileName){
		RetMessage ret=new RetMessage();
		try {
			mEnnmcdService.importPptn(file,fileFileName);
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
