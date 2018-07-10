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
import com.lyht.business.consumer.hydrologicalData.bean.Tsqx;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.business.consumer.hydrologicalData.service.TsqxService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class TsqxControl {
	
	@Resource
	private TsqxService mTsqxService;
	
	@Optlog(menuflag="TsqxList", opttype = "getTsqxMes") 
	public RetMessage getTsqxMes(TsqxFormBean mTsqxFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mTsqxService.getTsqxListData(mTsqxFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="TsqxExport", opttype = "export") 
	public RetMessage export(TsqxFormBean mTsqxFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage mRetMessage=new RetMessage();
		try{
			mTsqxService.export(mTsqxFormBean,prs,request,response);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("导出数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出数据失败！");
			e.printStackTrace();
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="TsqxImport", opttype = "import") 
	public RetMessage importTsqx(File[] file,String[] fileFileName){
		RetMessage ret=new RetMessage();
		try {
			mTsqxService.importTsqx(file,fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入失败！");
			e.printStackTrace();
		}
		return ret;
	}
	
	@Optlog(menuflag="TsqxView", opttype = "view") 
	public RetMessage view(String stcd,Tsqx mTsqx){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mTsqx,mTsqxService.getTsqxInfoById(stcd));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="TsqxCreate", opttype = "create")  
	public RetMessage create(Tsqx mTsqx,Tsqx retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mTsqxService.create(mTsqx));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="TsqxUpdate",opttype = "update")
	public RetMessage update(TsqxFormBean mTsqxFormBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			mTsqxService.update(mTsqxFormBean);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="getTsqxInfoListByIds",opttype = "getTsqxInfoListByIds") 
	public PageResults getTsqxInfoListByIds(String ids){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mTsqxService.getTsqxInfoListByIds(ids);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="TsqxDelete",opttype = "deletTsqxInfoByIds") 
	public RetMessage deletTsqxInfoByIds(String ids,List list){
		RetMessage mRetMessage=new RetMessage();
		try{
			mTsqxService.deletTsqxInfoByIds(ids);
			list.clear();
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}

	public RetMessage createX(TsqxFormBean mTsqxFormBean, String[] qm, String[] q, String[] t) {
		RetMessage mRetMessage=new RetMessage();
		try {
			mTsqxService.createX(mTsqxFormBean,qm,q,t);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	

}
