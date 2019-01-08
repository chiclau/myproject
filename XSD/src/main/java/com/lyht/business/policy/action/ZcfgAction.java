package com.lyht.business.policy.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.policy.control.ZcfgControl;
import com.lyht.business.policy.formbean.ZcfgInfoFormBean;
import com.lyht.business.policy.service.ZcfgService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

@Namespace("/policy")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ZcfgAction extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("ZcfgAction");
	
	private ZcfgInfoFormBean mZcfgInfoFormBean = new ZcfgInfoFormBean();

	public ZcfgInfoFormBean getmZcfgInfoFormBean() {
		return mZcfgInfoFormBean;
	}
	private int page;
	private int limit;
	private String condition;
	private String fgly;
	private String ssbm;
	private String startDate;
	private String endDate;
	private String searchName;
	private String fgbm;
	private File file;//名称要与<input name="img" type="file"> 中的name一致
	private String fileContextType;//文件类型
	private String fileFileName;  //文件名称
	@Resource
	private ZcfgControl zcfgControl;
	@Resource
	private ZcfgService zcfgService;
	
	/**
	 * 获取行政区域代码所有节点
	 * */
	public String list(){
		log.info("ZcfgAction=list:获取政策法规信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		PageResults dzListData = zcfgService.getDzListData(page,limit,fgly,ssbm,startDate,endDate,searchName);
		if (dzListData == null){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);
		} else {
			mHashMap.put("code",0);
			mHashMap.put("msg", "");
			mHashMap.put("count", dzListData.getTotalCount());
			mHashMap.put("data", dzListData.getResults());		
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 根据法规编码查询政策法规
	 * */
	public String findOne(){
		log.info("ZcfgAction=findOne:根据法规编码查询政策法规");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		PageResults dzListData = zcfgService.findOne(mZcfgInfoFormBean);
		if (dzListData == null){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);
		} else {
			mHashMap.put("code",0);
			mHashMap.put("msg", "");
			mHashMap.put("count", dzListData.getTotalCount());
			mHashMap.put("data", dzListData.getResults());		
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 查询政策法规文件来源
	 * @return
	 */
	public String initWjly(){
		log.info("ZcfgAction=list:查询政策法规文件来源");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		List<Map> dzListData = zcfgService.initWjly(condition);
		if (dzListData == null){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("rows", mJSONArray);
		} else {
			mHashMap.put("rows", dzListData);		
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 启用禁用法规政策
	 * @return
	 */
	public String flag(){
		log.info("ZcfgAction=flag:启用禁用法规政策");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		boolean flag = zcfgService.auditZcfgInfo(mZcfgInfoFormBean.getZcfgInfo().getFgbm());
		if(flag){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("error", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 删除法规政策
	 * @return
	 */
	public String delete(){
		log.info("ZcfgAction=delete:删除法规政策");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		boolean flag = zcfgService.delete(fgbm);
		if(flag){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("success", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 保存法规政策
	 * @return
	 */
	public String saveZcfg(){
		log.info("ZcfgAction=saveZcfg:保存法规政策");
		try {
			//获得存储文件的路径
			HashMap<String, Object> mHashMap = new HashMap<String, Object>();
			String path = ServletActionContext.getServletContext().getRealPath("")+"../../../../../../upload";
			File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			//上传
			String path1=path+File.separator+this.getFileFileName();
			FileUtils.copyFile(this.file, new File(path1));
			this.getSession().setAttribute("path", "/upload/"+getFileFileName());
			this.getSession().setAttribute("name", getFileFileName());
			mHashMap.put("msg", "success");
			CommonFunction.writeResponse(getResponse(), mHashMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 保存法规政策
	 * @return
	 */
	public String save(){
		log.info("ZcfgAction=Zcfg:保存法规政策");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		String path = (String)this.getSession().getAttribute("path");
		this.getSession().removeAttribute("path");
		String name = (String)this.getSession().getAttribute("name");
		this.getSession().removeAttribute("name");
		boolean flag = zcfgService.saveZcfg(mZcfgInfoFormBean,path,name);
		if(flag){
			mHashMap.put("success", Constants.AJAX_RETFLAG_SUCCESS);
		}else{
			mHashMap.put("success", Constants.AJAX_RETFLAG_ERROR);
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}

	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContextType() {
		return fileContextType;
	}
	public void setFileContextType(String fileContextType) {
		this.fileContextType = fileContextType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFgbm() {
		return fgbm;
	}
	public void setFgbm(String fgbm) {
		this.fgbm = fgbm;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getFgly() {
		return fgly;
	}
	public void setFgly(String fgly) {
		this.fgly = fgly;
	}
	public String getSsbm() {
		return ssbm;
	}
	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}
