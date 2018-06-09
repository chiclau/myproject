package com.lyht.business.consumer.hydrologicalData.control;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Zqrl;
import com.lyht.business.consumer.hydrologicalData.formbean.ZqrlFormBean;
import com.lyht.business.consumer.hydrologicalData.service.ZqrlService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ZqrlControl {

	@Resource
	private ZqrlService mZqrlService;
	
	@Optlog(menuflag="ZqrlList", opttype = "getZqrlMes") 
	public RetMessage getZqrlMes(ZqrlFormBean mZqrlFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mZqrlService.getZqrlListData(mZqrlFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZqrlView", opttype = "view") 
	public RetMessage view(ZqrlFormBean mZqrlFormBean,List<Zqrl> mZqrlList){
		RetMessage mRetMessage=new RetMessage();
		List<Zqrl> zqrlList = mZqrlService.getZqrlInfoById(mZqrlFormBean);
		try{
			for(Zqrl zqrl : zqrlList){
				Zqrl mzqrl = new Zqrl();
				BeanUtils.copyProperties(mzqrl,zqrl);
				mZqrlList.add(mzqrl);
			}
			mZqrlList.clear();
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZqrlCreate", opttype = "create")  
	public RetMessage create(Zqrl mZqrl,Zqrl retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mZqrlService.create(mZqrl));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZqrlCreate", opttype = "create")  
	public RetMessage createX(ZqrlFormBean mZqrlFormBean,String []ptno,String []z,String []q){
		RetMessage mRetMessage=new RetMessage();
		try {
			mZqrlService.createX(mZqrlFormBean,ptno,z,q);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZqrlUpdate",opttype = "update")
	public RetMessage update(Zqrl mZqrl,Zqrl retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mZqrlService.update(mZqrl));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZqrlUpdate",opttype = "getZqrlInfoListByIds") 
	public List<Zqrl> getZqrlInfoListByIds(ZqrlFormBean mZqrlFormBean,List<Zqrl> mZqrlList){
		PageResults mPageResults=new PageResults();
		List<Zqrl> zqrlList = mZqrlService.getZqrlInfoListByIds(mZqrlFormBean);
		try{
			for(Zqrl zqrl : zqrlList){
				Zqrl mzqrl = new Zqrl();
				BeanUtils.copyProperties(mzqrl,zqrl);
				mZqrlList.add(mzqrl);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mZqrlList;
	}
	
	@Optlog(menuflag="ZqrlDelete",opttype = "deletZqrlInfoByIds") 
	public RetMessage deletZqrlInfoByIds(String []stcd,String []lnnm,String []ptno,String flag){
		RetMessage mRetMessage=new RetMessage();
		try{
			mZqrlService.deletZqrlInfoByIds(stcd,lnnm,ptno,flag);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="PptnExport", opttype = "export") 
	public RetMessage export(ZqrlFormBean mZqrlFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage mRetMessage=new RetMessage();
		try{
			mZqrlService.export(mZqrlFormBean,prs,request,response);
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
			mZqrlService.importPptn(file,fileFileName);
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
