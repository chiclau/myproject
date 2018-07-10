package com.lyht.business.system.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.system.bean.Addvcd;
import com.lyht.business.system.control.AddvcdControl;
import com.lyht.business.system.dao.AddvcdDao;
import com.lyht.business.system.formbean.AddvcdFormBean;
import com.lyht.business.system.service.AddvcdService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/system")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class AddvcdAction extends BaseLyhtAction {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("AddvcdAction");
	private AddvcdFormBean mAddvcdFormBean=new AddvcdFormBean();
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	@Resource
	private AddvcdControl mAddvcdControl;
	@Resource
	private AddvcdService mAddvcdService;
	@Resource
	private AddvcdDao mAddvcdDao;
	/**
	 * 获取根节点数据
	 * */
	public String listroot(){
		log.info("AddvcdAction=listroot:获取行政区域代码根节点数据");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mAddvcdControl.getListRootData(mAddvcdFormBean, mPageResults);
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
	 * 获取行政区域代码所有节点
	 * */
	public String list(){
		log.info("AddvcdAction=list:获取行政区域代码列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= mAddvcdControl.getAddvcdMes(mAddvcdFormBean, mPageResults);
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
	 * 初始化行政区域代码根节点FORM表单数据
	 * */
	public String initRootTreeFormData(){
		log.info("AddvcdAction=initRootTreeFormData:初始化行政区域代码根节点FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Addvcd mAddvcd = new Addvcd();
		Addvcd mPAddvcd= new Addvcd();
		mRetMessage=mAddvcdControl.view(mAddvcdFormBean.getmAddvcdInfoBean().getAddvcd(),
				mAddvcd,mPAddvcd);
		mHashMap.put("mAddvcdFormBean", mAddvcd);
		mHashMap.put("mPAddvcdFormBean", mPAddvcd);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化流域水系代码子节点FORM表单数据
	 * */
	public String initChildTreeFormData(){
		log.info("AddvcdAction=initChildTreeFormData:初始化行政区域代码子节点FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Addvcd mAddvcd = new Addvcd();
		Addvcd mPAddvcd = new Addvcd();
		mRetMessage=mAddvcdControl.add(mAddvcdFormBean.getmAddvcdInfoBean().getAddvcd(),mPAddvcd);
		mHashMap.put("mAddvcdInfoBean", mAddvcd);
		mHashMap.put("mPAddvcdInfoBean", mPAddvcd);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存节点FORM表单数据
	 * */
	public String saveTreeFormData(){
		log.info("AddvcdAction=saveTreeFormData:保存节点FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		Addvcd mAddvcd = new Addvcd();
		String addvcd=mAddvcdFormBean.getmAddvcdInfoBean().getAddvcd(); 
		if("".equals(addvcd)){
			mRetMessage=mAddvcdControl.create(mAddvcdFormBean.getmAddvcdInfoBean(),mAddvcd);
		}else{
			Addvcd mAddvcd_ = mAddvcdDao.getAddvcdInfoById(addvcd);
			if(!"".equals(mAddvcd_.getAddvcd()) && ("".equals(mAddvcd_.getPaddvcd()) 
						|| !"".equals(mAddvcd_.getPaddvcd()))){
				mRetMessage=mAddvcdControl.updateAddvcdInfo(mAddvcdFormBean.getmAddvcdInfoBean());
			}else{
				mRetMessage=mAddvcdControl.create_(mAddvcdFormBean.getmAddvcdInfoBean(),mAddvcd); //添加子节点
			}
		}
		mHashMap.put("mAddvcdFormBean", mAddvcdFormBean.getmAddvcdInfoBean());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除行政区域代码数据
	 * */
	public String removeIds(){
		log.info("AddvcdAction=removeIds:批量删除行政区域代码数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String addvcd=mAddvcdFormBean.getmAddvcdInfoBean().getAddvcd();
		boolean flag=mAddvcdService.removeAddvcdInfoByAddvcd(addvcd);
		if(flag){
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
		log.info("AddvcdAction=importPptn: 导入行政区域");
		RetMessage ret=new RetMessage();
		ret=mAddvcdControl.importPptn(getFile(),getFileFileName());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	/**
	 * 通过省份编码加载市区数据
	 * */
	public String loadCityData(){
		log.info("AddvcdAction=loadCityData: 通过省份编码加载市区数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String prov=this.getRequest().getParameter("prov");
		List<Map> list=mAddvcdService.loadCityData(prov);
		mHashMap.put("rows", list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	
	public AddvcdFormBean getmAddvcdFormBean() {
		return mAddvcdFormBean;
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
