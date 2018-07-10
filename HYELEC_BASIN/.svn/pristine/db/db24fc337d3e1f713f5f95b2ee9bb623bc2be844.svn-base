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
import com.lyht.business.consumer.hydrologicalData.bean.Tsqx;
import com.lyht.business.consumer.hydrologicalData.control.TsqxControl;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.business.consumer.hydrologicalData.service.TsqxService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

/**
 * @author 颜世儒
 *
 */
@Namespace("/tsqx")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("/tsqx")
public class TsqxAction extends BaseLyhtAction{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("TsqxAction");
	private TsqxFormBean mTsqxFormBean=new TsqxFormBean();
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	
	@Resource
	private TsqxControl mTsqxControl;
	@Resource
	private TsqxService mTsqxService;
	
	/**
	 * 获取退水曲线列表
	 * */
	public String list(){
		log.info("TsqxAction=list: 获取退水曲线列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mTsqxControl.getTsqxMes(mTsqxFormBean, mPageResults);
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
	 * 导出退水曲线
	 * */
	public String export(){
		log.info("TsqxAction=export: 导出退水曲线");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mTsqxControl.export(mTsqxFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 导入退水曲线
	 */
	public String importTsqx(){
		log.info("TsqxAction=importTsqx: 导入退水曲线");
		RetMessage ret=new RetMessage();
		ret=mTsqxControl.importTsqx(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	/**
	 * 初始化退水曲线FORM表单数据
	 * */
	public String edit(){
		log.info("TsqxAction=edit:初始化退水曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Tsqx mTsqx = new Tsqx();
		mRetMessage=mTsqxControl.view(mTsqxFormBean.getmTsqxInfoBean().getStcd(),mTsqx);
		mHashMap.put("mTsqxFormBean", mTsqx);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	
	
	/**
	 * 退水曲线保存FORM表单数据
	 * */
	public String save(){
		log.info("TsqxAction=save:退水曲线保存FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Tsqx mTsqx = new Tsqx();
		mTsqx=mTsqxService.getTsqxInfoById(mTsqxFormBean.getIds());
		if("".equals(mTsqx.getStcd())){
			mRetMessage=mTsqxControl.create(mTsqxFormBean.getmTsqxInfoBean(),mTsqxFormBean.getmTsqxInfoBean());
		}else{
			mRetMessage=mTsqxControl.update(mTsqxFormBean);
		}
		
		mHashMap.put("mTsqxFormBean", mTsqxFormBean.getmTsqxInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 修改退水曲线保存FORM表单数据
	 * */
	public String saveX(){
		log.info("TsqxAction=saveX:保存退水曲线FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String []qm = getRequest().getParameterValues("qm");
		String []q= getRequest().getParameterValues("q");
		String []t= getRequest().getParameterValues("t");
		mRetMessage=mTsqxControl.createX(mTsqxFormBean,qm,q,t);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 批量删除退水曲线列表数据
	 * */
	public String removeids(){
		log.info("批量删除退水曲线列表数据==TsqxAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String ids = CommonUtil.trim(mTsqxFormBean.getIds());
		PageResults mPageResults=new PageResults(); 
		mPageResults=mTsqxControl.getTsqxInfoListByIds(ids);
		mRetMessage=mTsqxControl.deletTsqxInfoByIds(ids, mPageResults.getResults());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	

	public TsqxFormBean getmTsqxFormBean() {
		return mTsqxFormBean;
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
