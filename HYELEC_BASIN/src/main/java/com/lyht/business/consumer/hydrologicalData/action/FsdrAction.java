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
import org.springframework.util.StringUtils;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.consumer.hydrologicalData.bean.Fsdr;
import com.lyht.business.consumer.hydrologicalData.control.FsdrControl;
import com.lyht.business.consumer.hydrologicalData.formbean.FsdrFormBean;
import com.lyht.business.consumer.hydrologicalData.service.FsdrService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;

import net.sf.json.JSONArray;

@Namespace("/fsdr")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class FsdrAction extends BaseLyhtAction {
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("FsdrAction");
	private FsdrFormBean mFsdrFormBean=new FsdrFormBean();
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	@Resource
	private FsdrControl mFsdrControl;
	@Resource
	private FsdrService mFsdrService;

	/**
	 * 获取洪水传播时间表列表
	 * */
	public String list(){
		log.info("FsdrAction=list:获取洪水传播时间表列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mFsdrControl.getFsdrMes(mFsdrFormBean, mPageResults);
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
	 * 初始化洪水传播时间表FORM表单数据
	 * */
	public String edit(){
		log.info("FsdrAction=edit:初始化洪水传播时间表FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Fsdr mFsdr = new Fsdr();
		mRetMessage=mFsdrControl.view(mFsdrFormBean.getmFsdrInfoBean().getUpstcd(),mFsdr);
		mHashMap.put("mFsdrFormBean", mFsdr);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存洪水传播时间表FORM表单数据
	 * */
	public String save(){
		log.info("FsdrAction=save:保存洪水传播时间表FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Fsdr mFsdr = new Fsdr();
		mFsdr=mFsdrService.getFsdrInfoByud(mFsdrFormBean);
		if("".equals(mFsdr.getUpstcd()) && "".equals(mFsdr.getDwstcd())){
			mRetMessage=mFsdrControl.create(mFsdrFormBean.getmFsdrInfoBean());
		}else{
			mRetMessage=mFsdrControl.update(mFsdrFormBean.getmFsdrInfoBean());
		}
		mHashMap.put("mFsdrFormBean", mFsdrFormBean.getmFsdrInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除列表数据
	 * */
	public String removeids(){
		log.info("批量删除==FsdrAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		mRetMessage=mFsdrControl.deletFsdrInfoByIds(mFsdrFormBean);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导出洪水传播时间表
	 * */
	public String export(){
		log.info("FsdrAction=export: ");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mFsdrControl.export(mFsdrFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 导入洪水传播时间表
	 */
	public String importPptn(){
		log.info("FsdrAction=importPptn: 导入降水量");
		RetMessage ret=new RetMessage();
		ret=mFsdrControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	
	
	public FsdrFormBean getmFsdrFormBean() {
		return mFsdrFormBean;
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
