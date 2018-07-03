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
import com.lyht.business.consumer.hydrologicalData.bean.Zvarl;
import com.lyht.business.consumer.hydrologicalData.bean.Zvarl;
import com.lyht.business.consumer.hydrologicalData.bean.Zvarl;
import com.lyht.business.consumer.hydrologicalData.formbean.ZvarlFormBean;
import com.lyht.business.consumer.hydrologicalData.service.ZvarlService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ZvarlControl {

	@Resource
	private ZvarlService mZvarlService;
	
	@Optlog(menuflag="ZvarlList", opttype = "getZvarlMes") 
	public RetMessage getZvarlMes(ZvarlFormBean mZvarlFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mZvarlService.getZvarlListData(mZvarlFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZvarlView", opttype = "view") 
	public RetMessage view(String stcd,List<Zvarl> mZvarlList){
		RetMessage mRetMessage=new RetMessage();
		List<Zvarl> ZvarlList = mZvarlService.getZvarlInfoById(stcd);
		try{
			for(Zvarl Zvarl : ZvarlList){
				Zvarl mZvarl = new Zvarl();
				BeanUtils.copyProperties(mZvarl,Zvarl);
				mZvarlList.add(mZvarl);
			}
			/*BeanUtils.copyProperties(mZvarl,mZvarlService.getZvarlInfoById(stcd));*/
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZvarlCreate", opttype = "create")  
	public RetMessage create(Zvarl mZvarl,Zvarl retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mZvarlService.create(mZvarl));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZvarlUpdate",opttype = "update")
	public RetMessage update(Zvarl mZvarl,Zvarl retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mZvarlService.update(mZvarl));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	public RetMessage createX(ZvarlFormBean mZvarlFormBean,String []ptno,String []rz,String []w,String []wsfa){
		RetMessage mRetMessage=new RetMessage();
		try {
			mZvarlService.createX(mZvarlFormBean,ptno,rz,w,wsfa);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="ZvarlUpdate",opttype = "getZvarlInfoListByIds") 
	public List<Zvarl> getZvarlInfoListByIds(String ids,List<Zvarl> mZvarlList){
		PageResults mPageResults=new PageResults();
		List<Zvarl> ZvarlList = mZvarlService.getZvarlInfoListByIds(ids);
		try{
			for(Zvarl Zvarl : ZvarlList){
				Zvarl mZvarl = new Zvarl();
				BeanUtils.copyProperties(mZvarl,Zvarl);
				mZvarlList.add(mZvarl);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mZvarlList;
	}
	
	@Optlog(menuflag="ZvarlDelete",opttype = "deletZvarlInfoByIds") 
	public RetMessage deletZvarlInfoByIds(String []stcd,String []mstm,String []ptno,String flag){
		RetMessage mRetMessage=new RetMessage();
		try{
			mZvarlService.deletZvarlInfoByIds(stcd,mstm,ptno,flag);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="PptnExport", opttype = "export") 
	public RetMessage export(ZvarlFormBean mZvarlFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage mRetMessage=new RetMessage();
		try{
			mZvarlService.export(mZvarlFormBean,prs,request,response);
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
			mZvarlService.importPptn(file,fileFileName);
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
