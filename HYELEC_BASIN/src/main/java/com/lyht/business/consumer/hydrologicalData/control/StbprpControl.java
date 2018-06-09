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
import com.lyht.business.consumer.hydrologicalData.bean.Stbprp;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.business.consumer.hydrologicalData.service.StbprpService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class StbprpControl {

	@Resource private StbprpService mStbprpService;
	
	
	/** 根据条件查询所有测站信息
	 * @param formBean
	 * @return
	 */
	public List<Map> getStbprpList(StbprpFormBean formBean){
		return mStbprpService.getStbprpList(formBean);
	}
	
	/** 查询单个测站信息
	 * @param stcd
	 * @return
	 */
	public Stbprp getObjectByStcd(StbprpFormBean mStbprpFormBean){
		return mStbprpService.getStbprpInfoById(mStbprpFormBean);
	}
	
	/** 查询单个测站信息
	 * @param stcd
	 * @return
	 */
	public Stbprp getObjectByStcd_(String stcd){
		return mStbprpService.getStbprpInfoById_(stcd);
	}
	
	@Optlog(menuflag="stbprpList", opttype = "getStbprpMes") 
	public RetMessage getStbprpMes(StbprpFormBean mStbprpFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mStbprpService.getStbprpListData(mStbprpFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="StbprpView", opttype = "view") 
	public RetMessage view(StbprpFormBean mStbprpFormBean,Stbprp mStbprp){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mStbprp,mStbprpService.getStbprpInfoById(mStbprpFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="StbprpCreate", opttype = "create")  
	public RetMessage create(Stbprp mStbprp,Stbprp retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mStbprpService.create(mStbprp));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="StbprpUpdate",opttype = "update")
	public RetMessage update(Stbprp mStbprp,Stbprp retBean){
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(retBean,mStbprpService.update(mStbprp));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="StbprpUpdate",opttype = "getStbprpInfoListByIds") 
	public PageResults getStbprpInfoListByIds(String ids){
		PageResults mPageResults=new PageResults();
		try{
			mPageResults=mStbprpService.getStbprpInfoListByIds(ids);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	@Optlog(menuflag="StbprpDelete",opttype = "deletStbprpInfoByIds") 
	public RetMessage deletStbprpInfoByIds(String ids,List list){
		RetMessage mRetMessage=new RetMessage();
		try{
			mStbprpService.deletStbprpInfoByIds(ids);
			list.clear();
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="PptnExport", opttype = "export") 
	public RetMessage export(StbprpFormBean mStbprpFormBean,PageResults prs,HttpServletRequest request,HttpServletResponse response){
		RetMessage mRetMessage=new RetMessage();
		try{
			mStbprpService.export(mStbprpFormBean,prs,request,response);
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
			mStbprpService.importPptn(file,fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入失败！");
			e.printStackTrace();
		}
		return ret;
	}
	
	@Optlog(menuflag="stbprpList", opttype = "getupstcd_Stbprp") 
	public RetMessage getupstcd_Stbprp(StbprpFormBean mStbprpFormBean,PageResults mPageResults){
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mStbprpService.getUpstcdData(mStbprpFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
}
