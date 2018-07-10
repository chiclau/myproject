package com.lyht.business.consumer.hydrologicalData.action;

import java.io.File;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Dayev;
import com.lyht.business.consumer.hydrologicalData.bean.Pptn;
import com.lyht.business.consumer.hydrologicalData.bean.Rrff;
import com.lyht.business.consumer.hydrologicalData.control.PptnControl;
import com.lyht.business.consumer.hydrologicalData.control.RrffControl;
import com.lyht.business.consumer.hydrologicalData.formbean.DayevFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.RrffFormBean;
import com.lyht.business.consumer.hydrologicalData.service.PptnService;
import com.lyht.business.consumer.hydrologicalData.service.RrffService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.Randomizer;

import net.sf.json.JSONArray;

/**
 * @author 张琦
 *
 */
@Namespace("/rrff")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("/rrff")
public class RrffAction extends BaseLyhtAction{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("RrffAction");
	private RrffFormBean mRrffFormBean=new RrffFormBean();
	
	public RrffFormBean getmRrffFormBean() {
		return mRrffFormBean;
	}
	
	@Resource private RrffControl mRrffControl;
	@Resource private RrffService mRrffService;
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	/**
	 * 获取降雨径流表数据
	 * */
	public String list(){
		log.info("RrffAction=list: 获取降雨径流列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mRrffControl.getRrffMes(mRrffFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);			
		} else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());			
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存降雨径流FORM表单数据
	 * */
	public String save(){
		log.info("RrffAction=save:保存降雨径流FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Rrff mRrff = new Rrff();
		mRrff=mRrffService.getRrffInfoById(mRrffFormBean);
		if("".equals(mRrff.getStcd()) && "".equals(mRrff.getUserName())){
			mRetMessage=mRrffControl.update(mRrffFormBean.getmRrffInfoBean());
		}else{
			mRetMessage=mRrffControl.create(mRrffFormBean.getmRrffInfoBean());
		}
		mHashMap.put("mRrffFormBean", mRrffFormBean.getmRrffInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除列表数据
	 * */
	public String removeids(){
		log.info("批量删除==RrffAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		mRetMessage=mRrffControl.deletRrffInfoByIds(mRrffFormBean);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 导入降雨径流
	 */
	public String importRrff(){
		log.info("RrffAction=importRrff: 导入降雨径流");
		RetMessage ret=new RetMessage();
		ret=mRrffControl.importRrff(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	
	/**
	 * 导出降雨径流
	 * */
	public String export(){
		log.info("RrffAction=export: ");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mRrffControl.export(mRrffFormBean,prs,req,res);
		return null;
	}
	
	/*
	 * 修改降雨径流并保存
	 */
	public String save_x(){
		log.info("RrffAction=saveX:保存修改降雨径流FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String []pa = getRequest().getParameterValues("pa");
		String []p= getRequest().getParameterValues("p");
		String []r= getRequest().getParameterValues("r");
		mRetMessage=mRrffControl.createX(mRrffFormBean,pa,p,r);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
}
