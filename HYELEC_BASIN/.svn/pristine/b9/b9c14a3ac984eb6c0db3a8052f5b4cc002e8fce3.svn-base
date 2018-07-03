package com.lyht.business.system.action;

import java.io.File;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Ennmcd;
import com.lyht.business.system.control.EnnmcdControl;
import com.lyht.business.system.formbean.EnnmcdFormBean;
import com.lyht.business.system.service.EnnmcdService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/system")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class EnnmcdAction extends BaseLyhtAction{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("EnnmcdAction");
	private EnnmcdFormBean mEnnmcdFormBean=new EnnmcdFormBean();

	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	
	@Resource
	private EnnmcdControl mEnnmcdControl;
	@Resource
	private EnnmcdService mEnnmcdService;
	
	/**
	 * 获取根节点数据
	 * */
	public String listroot(){
		log.info("EnnmcdAction=listroot:查询流域水系代码根节点信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mEnnmcdControl.getListRootData(mEnnmcdFormBean, mPageResults);
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
	 * 获取流域水系代码所有节点
	 * */
	public String list(){
		log.info("EnnmcdAction=list: 获取流域水系代码所有节点");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mEnnmcdControl.getEnnmcdMes(mEnnmcdFormBean, mPageResults);
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
	 * 初始化流域水系代码根节点FORM表单数据
	 * */
	public String initRootTreeFormData(){
		log.info("EnnmcdAction=initRootTreeFormData:初始化流域水系代码根节点FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Ennmcd mEnnmcd = new Ennmcd();
		Ennmcd mPEnnmcd= new Ennmcd();
		mRetMessage=mEnnmcdControl.view(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvcd(),
				mEnnmcd,mPEnnmcd);
		mHashMap.put("mEnnmcdInfoBean", mEnnmcd);
		mHashMap.put("mPEnnmcdInfoBean", mPEnnmcd);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化流域水系代码子节点FORM表单数据
	 * */
	public String initChildTreeFormData(){
		log.info("EnnmcdAction=initChildTreeFormData:初始化流域水系代码子节点FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Ennmcd mEnnmcd = new Ennmcd();
		Ennmcd mPEnnmcd= new Ennmcd();
		mRetMessage=mEnnmcdControl.add(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvcd(),mPEnnmcd);
		mHashMap.put("mEnnmcdInfoBean", mEnnmcd);
		mHashMap.put("mPEnnmcdInfoBean", mPEnnmcd);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存节点FORM表单数据
	 * */
	public String saveTreeFormData(){
		log.info("EnnmcdAction=saveTreeFormData:保存节点FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Ennmcd mEnnmcd = new Ennmcd();
		if("".equals(mEnnmcdFormBean.getIds())){
			Ennmcd mEnnmcd_ =mEnnmcdService.getEnnmcdInfoById(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvcd());
			if(!"".equals(mEnnmcd_.getRvcd())){
				mRetMessage=mEnnmcdControl.updateEnnmcdInfo(mEnnmcdFormBean.getmEnnmcdInfoBean());
			}else{
				mRetMessage=mEnnmcdControl.create(mEnnmcdFormBean.getmEnnmcdInfoBean(),mEnnmcd);
			}
		}else{
			Ennmcd mPEnnmcd =mEnnmcdService.getEnnmcdInfoById_(mEnnmcdFormBean.getmEnnmcdInfoBean().getRvcd()); 
			Ennmcd mEnnmcd_ =mEnnmcdService.getEnnmcdInfoById(mEnnmcdFormBean.getIds());
			if(!"".equals(mPEnnmcd.getRvcd()) && ("".equals(mPEnnmcd.getPrvcd()) 
						|| !"".equals(mPEnnmcd.getPrvcd()))){
				mRetMessage=mEnnmcdControl.updateEnnmcdInfo(mEnnmcdFormBean.getmEnnmcdInfoBean());
			}else{
				mRetMessage=mEnnmcdControl.create(mEnnmcdFormBean.getmEnnmcdInfoBean(),mEnnmcd,mEnnmcd_);
			}
		}
		mHashMap.put("mEnnmcdFormBean", mEnnmcdFormBean.getmEnnmcdInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除流域水系代码列表数据
	 * */
	public String removeIds(){
		log.info("EnnmcdAction=removeIds:批量删除流域水系代码列表数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String rvcd=mEnnmcdFormBean.getmEnnmcdInfoBean().getRvcd();
		Ennmcd mEnnmcd=mEnnmcdService.getEnnmcdInfoById(rvcd);
		boolean flag =mEnnmcdService.deleteEnnmcdInfoByRvcd(mEnnmcd);
		if(flag){
			mEnnmcdService.removeGroupInfoByRvcd(mEnnmcd.getRvcd());
			mHashMap.put(RetMessage.AJAX_MESSAGE, "删除数据成功!");
		}else{
			mHashMap.put(RetMessage.AJAX_MESSAGE, "删除数据失败");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 导入库
	 */
	public String importPptn(){
		log.info("ZvarlAction=importPptn: 导入流域水系");
		RetMessage ret=new RetMessage();
		ret=mEnnmcdControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}

	
	public EnnmcdFormBean getmEnnmcdFormBean() {
		return mEnnmcdFormBean;
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
