package com.lyht.business.analysisCalculation.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.StPptnDayR;
import com.lyht.business.analysisCalculation.formbean.StPptnDayRFormBean;
import com.lyht.business.analysisCalculation.service.StPptnDayRService;
import com.lyht.util.CommonFunction;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

@Namespace("/business")
@Controller
@Scope("prototype")
@Action("/stpptndayr")
public class StPptnDayrAction {

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("StPptnDayRAction");
	
	@Resource
	private StPptnDayRService stPptnDayRService;
	
	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	private StPptnDayRFormBean stPptnDayRFormBean = new StPptnDayRFormBean();
	
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	
	public StPptnDayRFormBean getStPptnDayRFormBean() {
		return stPptnDayRFormBean;
	}

	public void setStPptnDayRFormBean(StPptnDayRFormBean stPptnDayRFormBean) {
		this.stPptnDayRFormBean = stPptnDayRFormBean;
	}

	public String list(){
		log.info("StPptnDayRAction=list:获取日降雨量列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		try {
			PageResults mPageResults = stPptnDayRService.getStPptnDayRListData(stPptnDayRFormBean);
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_ERROR);
			mHashMap.put(RetMessage.AJAX_MESSAGE, RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	public String edit(){
		log.info("StPptnDayRAction=edit:初始化日降雨FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			StPptnDayR mPptn = stPptnDayRService.queryStPptnDayRByNm(stPptnDayRFormBean.getStPptnDayRInfoBean().getNm());
			mHashMap.put("stPptnDayRInfoBean", mPptn);
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
			mHashMap.put(RetMessage.AJAX_MESSAGE, "查询数据成功！");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_ERROR);
			mHashMap.put(RetMessage.AJAX_MESSAGE, RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 降水量保存FORM表单数据
	 * */
	public String save(){
		log.info("StPptnDayRAction=save:日降水量保存FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			stPptnDayRService.saveOrUpdate(stPptnDayRFormBean.getStPptnDayRInfoBean());
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
			mHashMap.put(RetMessage.AJAX_MESSAGE, "保存成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_ERROR);
			mHashMap.put(RetMessage.AJAX_MESSAGE, RetMessage.ERROR_SERVICE_MSG+"保存数据失败！");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 批量删除降水量列表数据
	 * */
	public String removeids(){
		log.info("批量删除日降水量列表数据==StPptnDayRAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		try {
			stPptnDayRService.deletByIds(stPptnDayRFormBean.getStPptnDayRInfoBean().getNm());
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_SUCCESS);
			mHashMap.put(RetMessage.AJAX_MESSAGE, "删除成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mHashMap.put(RetMessage.AJAX_RETFLAG, RetMessage.RETFLAG_ERROR);
			mHashMap.put(RetMessage.AJAX_MESSAGE, RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 导入日降水量
	 */
	public String importPptn(){
		log.info("StPptnDayRAction=importPptn: 导入日降水量");
		RetMessage ret=new RetMessage();
		try {
			Hashtable<String,String> table = stPptnDayRService.importPptnLast(file,fileFileName);
			if(table.get("reflag").toString().equals("1")){
				ret.setRetflag(RetMessage.RETFLAG_SUCCESS);
				ret.setMessage("导入成功！");
			}else{
				ret.setRetflag(RetMessage.RETFLAG_ERROR);
				ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入失败！"+table.get("message"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret.setRetflag(RetMessage.RETFLAG_ERROR);
			ret.setMessage(RetMessage.ERROR_SERVICE_MSG+"导入失败！");
		}
		CommonFunction.writeResponse(getResponse(), JSONObject.fromObject(ret));
		return null;
	}
	public HttpServletRequest getRequest(){
		return (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	}
	
	public HttpServletResponse getResponse(){
		return (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
	}
	
	public HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}	
}
