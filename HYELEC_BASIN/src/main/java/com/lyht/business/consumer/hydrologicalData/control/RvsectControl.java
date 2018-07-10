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
import com.lyht.business.consumer.hydrologicalData.bean.Rvsect;
import com.lyht.business.consumer.hydrologicalData.bean.Zvarl;
import com.lyht.business.consumer.hydrologicalData.bean.Rvsect;
import com.lyht.business.consumer.hydrologicalData.bean.Rvsect;
import com.lyht.business.consumer.hydrologicalData.formbean.RvsectFormBean;
import com.lyht.business.consumer.hydrologicalData.service.RvsectService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RvsectControl {

	@Resource
	private RvsectService mRvsectService;
	
	@Optlog(menuflag="RvsectList", opttype = "getRvsectMes") 
	public RetMessage getRvsectMes(RvsectFormBean mRvsectFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mRvsectService.getRvsectListData(mRvsectFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RvsectView", opttype = "view") 
	public RetMessage view(String stcd,List<Rvsect> mRvsectList){
		RetMessage mRetMessage=new RetMessage();
		List<Rvsect> RvsectList = mRvsectService.getRvsectInfoById(stcd);
		try{
			for(Rvsect Rvsect : RvsectList){
				Rvsect mRvsect = new Rvsect();
				BeanUtils.copyProperties(mRvsect,Rvsect);
				mRvsectList.add(mRvsect);
			}
			/*BeanUtils.copyProperties(mRvsect,mRvsectService.getRvsectInfoById(stcd));*/
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RvsectCreate", opttype = "create")  
	public RetMessage create(Rvsect mRvsect,Rvsect retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mRvsectService.create(mRvsect));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RvsectUpdate",opttype = "update")
	public RetMessage update(Rvsect mRvsect,Rvsect retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mRvsectService.update(mRvsect));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="createX",opttype = "update")
	public RetMessage createX(RvsectFormBean mRvsectFormBean,String []di,String []zb,String []vtno){
		RetMessage mRetMessage=new RetMessage();
		try {
			mRvsectService.createX(mRvsectFormBean,di,zb,vtno);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RvsectUpdate",opttype = "getRvsectInfoListByIds") 
	public List<Rvsect> getRvsectInfoListByIds(String ids,List<Rvsect> mRvsectList){
		PageResults mPageResults=new PageResults();
		List<Rvsect> RvsectList = mRvsectService.getRvsectInfoListByIds(ids);
		try{
			for(Rvsect Rvsect : RvsectList){
				Rvsect mRvsect = new Rvsect();
				BeanUtils.copyProperties(mRvsect,Rvsect);
				mRvsectList.add(mRvsect);
			}
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mRvsectList;
	}
	
	@Optlog(menuflag="RvsectDelete",opttype = "deletRvsectInfoByIds") 
	public RetMessage deletRvsectInfoByIds(String []stcd,String []mstm,String []vtno,String flag){
		RetMessage mRetMessage=new RetMessage();
		try{
			mRvsectService.deletRvsectInfoByIds(stcd,mstm,vtno,flag);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="PptnExport", opttype = "export") 
	public RetMessage export(RvsectFormBean mRvsectFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage mRetMessage=new RetMessage();
		try{
			mRvsectService.export(mRvsectFormBean,prs,request,response);
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
			mRvsectService.importPptn(file,fileFileName);
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
