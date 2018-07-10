package com.lyht.business.consumer.hydrologicalData.control;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Soil;
import com.lyht.business.consumer.hydrologicalData.formbean.SoilFormBean;
import com.lyht.business.consumer.hydrologicalData.service.SoilService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SoilControl {

	@Resource
	private SoilService mSoilService;
	
	@Optlog(menuflag="SoilList", opttype = "getSoilMes") 
	public RetMessage getSoilMes(SoilFormBean mSoilFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mSoilService.getSoilListData(mSoilFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SoilView", opttype = "view") 
	public RetMessage view(SoilFormBean mSoilFormBean,Soil mSoil){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mSoil,mSoilService.getSoilInfoById(mSoilFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SoilCreate", opttype = "create")  
	public RetMessage create(Soil mSoil){
		RetMessage mRetMessage=new RetMessage();
		try {
			mSoilService.create(mSoil);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SoilUpdate",opttype = "update")
	public RetMessage update(Soil mSoil){
		RetMessage mRetMessage=new RetMessage();
		try {
			mSoilService.update(mSoil);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="SoilUpdate",opttype = "getSoilInfoListByIds") 
	public PageResults getSoilInfoListByIds(String ids){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mSoilService.getSoilInfoListByIds(ids);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="SoilDelete",opttype = "deletSoilInfoByIds") 
	public RetMessage deletSoilInfoByIds(SoilFormBean mSoilFormBean){
		RetMessage mRetMessage=new RetMessage();
		try{
			mSoilService.deletSoilInfoByIds(mSoilFormBean);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="PptnExport", opttype = "export") 
	public RetMessage export(SoilFormBean mSoilFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage mRetMessage=new RetMessage();
		try{
			mSoilService.export(mSoilFormBean,prs,request,response);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("导出数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出数据失败！");
			e.printStackTrace();
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="PptnImport", opttype = "import") 
	public RetMessage importPptn(File[] file,String[] fileFileName){
		RetMessage ret=new RetMessage();
		try {
			mSoilService.importPptn(file,fileFileName);
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
