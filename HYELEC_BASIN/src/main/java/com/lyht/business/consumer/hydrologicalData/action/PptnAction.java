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
import com.lyht.business.consumer.hydrologicalData.bean.Pptn;
import com.lyht.business.consumer.hydrologicalData.control.PptnControl;
import com.lyht.business.consumer.hydrologicalData.formbean.PptnFormBean;
import com.lyht.business.consumer.hydrologicalData.service.PptnService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import com.lyht.util.Randomizer;

import net.sf.json.JSONArray;

/**
 * @author 张琦
 *
 */
@Namespace("/business")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("/pptn")
public class PptnAction extends BaseLyhtAction{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("PptnAction");
	private PptnFormBean mPptnFormBean=new PptnFormBean();
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	
	@Resource
	private PptnControl mPptnControl;
	@Resource
	private PptnService mPptnService;
	
	/**
	 * 获取降水量列表
	 * */
	public String list(){
		log.info("PptnAction=list: 获取降水量列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mPptnControl.getPptnMes(mPptnFormBean, mPageResults);
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
	 * 导出降水量
	 * */
	public String export(){
		log.info("PptnAction=export: 导出降水量");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		mPptnControl.export(mPptnFormBean,prs,req,res);
		return null;
	}
	
	/**
	 * 导入降水量
	 */
	public String importPptn(){
		log.info("PptnAction=importPptn: 导入降水量");
		RetMessage ret=new RetMessage();
		ret=mPptnControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	/**
	 * 初始化降水量FORM表单数据
	 * */
	public String edit(){
		log.info("PptnAction=edit:初始化降水量FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Pptn mPptn = new Pptn();
		mRetMessage=mPptnControl.view(mPptnFormBean,mPptn);
		mHashMap.put("mPptnFormBean", mPptn);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 降水量保存FORM表单数据
	 * */
	public String save(){
		log.info("PptnAction=save:降水量保存FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Pptn mPptn = new Pptn();
		mPptn=mPptnService.getPptnInfoById(mPptnFormBean);
		if("".equals(mPptn.getStcd()) && "".equals(mPptn.getTm())){
			mRetMessage=mPptnControl.create(mPptnFormBean.getmPptnInfoBean());
		}else{
			mRetMessage=mPptnControl.update(mPptnFormBean.getmPptnInfoBean());
		}
		mHashMap.put("mPptnFormBean", mPptnFormBean.getmPptnInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除降水量列表数据
	 * */
	public String removeids(){
		log.info("批量删除降水量列表数据==PptnAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		mRetMessage=mPptnControl.deletPptnInfoByIds(mPptnFormBean);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}

	public PptnFormBean getmPptnFormBean() {
		return mPptnFormBean;
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
