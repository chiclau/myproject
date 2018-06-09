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
import com.lyht.business.consumer.hydrologicalData.bean.Rsvr;
import com.lyht.business.consumer.hydrologicalData.formbean.RsvrFormBean;
import com.lyht.business.consumer.hydrologicalData.service.RsvrService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RsvrControl {

	@Resource
	private RsvrService mRsvrService;
	
	@Optlog(menuflag="RsvrList", opttype = "getRsvrMes") 
	public RetMessage getRsvrMes(RsvrFormBean mRsvrFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mRsvrService.getRsvrListData(mRsvrFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RsvrView", opttype = "view") 
	public RetMessage view(RsvrFormBean mRsvrFormBean,Rsvr mRsvr){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mRsvr,mRsvrService.getRsvrInfoById(mRsvrFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RsvrCreate", opttype = "create")  
	public RetMessage create(Rsvr mRsvr){
		RetMessage mRetMessage=new RetMessage();
		try {
			mRsvrService.create(mRsvr);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RsvrUpdate",opttype = "update")
	public RetMessage update(Rsvr mRsvr){
		RetMessage mRetMessage=new RetMessage();
		try {
			mRsvrService.update(mRsvr);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RsvrUpdate",opttype = "getRsvrInfoListByIds") 
	public PageResults getRsvrInfoListByIds(String ids){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mRsvrService.getRsvrInfoListByIds(ids);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="RsvrDelete",opttype = "deletRsvrInfoByIds") 
	public RetMessage deletRsvrInfoByIds(RsvrFormBean mRsvrFormBean){
		RetMessage mRetMessage=new RetMessage();
		try{
			mRsvrService.deletRsvrInfoByIds(mRsvrFormBean);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="PptnExport", opttype = "export") 
	public RetMessage export(RsvrFormBean mRsvrFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage mRetMessage=new RetMessage();
		try{
			mRsvrService.export(mRsvrFormBean,prs,request,response);
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
			mRsvrService.importPptn(file,fileFileName);
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
