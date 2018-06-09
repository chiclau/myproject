package com.lyht.business.consumer.hydrologicalData.action;

import java.io.File;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Stbprp;
import com.lyht.business.consumer.hydrologicalData.control.StbprpControl;
import com.lyht.business.consumer.hydrologicalData.formbean.StbprpFormBean;
import com.lyht.business.consumer.hydrologicalData.service.StbprpService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/stbprp")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class StbprpAction extends BaseLyhtAction {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("StbprpAction");
	private StbprpFormBean mStbprpFormBean=new StbprpFormBean();
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	@Resource
	private StbprpControl mStbprpControl;
	@Resource
	private StbprpService mStbprpService;

	/**
	 * 获取测站信息列表
	 * */
	public String list(){
		log.info("StbprpAction=list:获取测站信息列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mStbprpControl.getStbprpMes(mStbprpFormBean, mPageResults);
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
	 * 初始化测站信息FORM表单数据
	 * */
	public String edit(){
		log.info("StbprpAction=edit:初始化测站信息FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Stbprp mStbprp = new Stbprp();
		mRetMessage=mStbprpControl.view(mStbprpFormBean,mStbprp);
		mHashMap.put("mStbprpFormBean", mStbprp);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 测站信息保存FORM表单数据
	 * */
	public String save(){
		log.info("StbprpAction=save:测站信息保存FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Stbprp mStbprp = new Stbprp();
		mStbprp=mStbprpService.getStbprpInfoById(mStbprpFormBean);
		if("".equals(mStbprp.getStcd())){
			mRetMessage=mStbprpControl.create(mStbprpFormBean.getmStbprpInfoBean(),mStbprp);
		}else{
			mRetMessage=mStbprpControl.update(mStbprpFormBean.getmStbprpInfoBean(),mStbprp);
		}
		mHashMap.put("mStbprpFormBean", mStbprpFormBean.getmStbprpInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除列表数据
	 * */
	public String removeids(){
		log.info("批量删除==StbprpAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String ids = CommonUtil.trim(mStbprpFormBean.getIds());
		PageResults mPageResults=new PageResults(); 
		mPageResults=mStbprpControl.getStbprpInfoListByIds(ids);
		mRetMessage=mStbprpControl.deletStbprpInfoByIds(ids, mPageResults.getResults());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导出降水量
	 * */
	public String export(){
		log.info("StbprpAction=export: 导出降水量");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mStbprpControl.export(mStbprpFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 导入测站信息
	 */
	public String importPptn(){
		log.info("StbprpAction=importPptn: 导入降水量");
		RetMessage ret=new RetMessage();
		ret=mStbprpControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	/**
	 * 获取下拉列表
	 * */
	public String upstcd_Stbprp(){
		log.info("StbprpAction=list:获取下拉列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mStbprpControl.getupstcd_Stbprp(mStbprpFormBean, mPageResults);
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
	
	public StbprpFormBean getmStbprpFormBean() {
		return mStbprpFormBean;
	}
	
	/**
	 * 上传文件域对象
	 */
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	/**
	 * 上传文件名
	 */	
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	
}
