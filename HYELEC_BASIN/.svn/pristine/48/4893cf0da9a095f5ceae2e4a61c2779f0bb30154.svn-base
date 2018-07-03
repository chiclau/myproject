package com.lyht.business.consumer.hydrologicalData.control;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Rrff;
import com.lyht.business.consumer.hydrologicalData.formbean.RrffFormBean;
import com.lyht.business.consumer.hydrologicalData.service.RrffService;

@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class RrffControl { 

	@Resource private RrffService mRrffService;
	
	@Optlog(menuflag="RrffList", opttype = "getRrffMes") 
	public RetMessage getRrffMes(RrffFormBean mRrffFormBean, PageResults mPageResults) {
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(mPageResults,mRrffService.getRrffListData(mRrffFormBean));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="RrffCreate", opttype = "create")  
	public RetMessage create(Rrff mRrff) {
		RetMessage mRetMessage=new RetMessage();
		try {
			mRrffService.create(mRrff);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="RrffUpdate", opttype = "update") 
	public RetMessage update(Rrff mRrff) {
		RetMessage mRetMessage=new RetMessage();
		try {
			mRrffService.update(mRrff);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}
	@Optlog(menuflag="RrffDelete",opttype = "deletRrffInfoByIds") 
	public RetMessage deletRrffInfoByIds(RrffFormBean mRrffFormBean) {
		RetMessage mRetMessage=new RetMessage();
		try{
			mRrffService.deletRrffInfoByIds(mRrffFormBean);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	
	@Optlog(menuflag="RrffImport", opttype = "import") 
	public RetMessage importRrff(File[] file, String[] fileFileName) {
		RetMessage ret=new RetMessage();
		try {
			mRrffService.importRrff(file,fileFileName);
			ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
			ret.setMessage("导入成功！");
		}catch (Exception e) {
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入失败！");
			e.printStackTrace();
		}
		return ret;
	}
	@Optlog(menuflag="RrffExport", opttype = "export") 
	public RetMessage export(RrffFormBean mRrffFormBean, PageResults prs, HttpServletRequest req, HttpServletResponse res) {
		RetMessage mRetMessage=new RetMessage();
		try{
			mRrffService.export(mRrffFormBean,prs,req,res);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("导出数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"导出数据失败！");
			e.printStackTrace();
		}
		return mRetMessage;
	}
	
	public RetMessage createX(RrffFormBean mRrffFormBean, String[] pa, String[] p, String[] r) {
		RetMessage mRetMessage=new RetMessage();
		try {
			mRrffService.createX(mRrffFormBean,pa,p,r);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("修改信息成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"修改信息失败！");
		}
		return mRetMessage;
	}

}
