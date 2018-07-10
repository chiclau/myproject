package com.lyht.business.modelManage.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.lyht.business.modelManage.bean.ModelInfo;
import com.lyht.business.modelManage.control.ModelInfoControl;
import com.lyht.business.modelManage.control.ModelParamenterControl;
import com.lyht.business.modelManage.formbean.ModelInfoFormBean;
import com.lyht.business.modelManage.service.ModelInfoService;
import com.lyht.business.modelManage.service.ModelParamenterService;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;
import com.lyht.util.CommonUtil;
import net.sf.json.JSONArray;
/**
 *作者： 刘魁
 *脚本日期:2018年5月10日 21:41:11
 *说明:  模型管理Action
*/

@Namespace("/modellist")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("modellist")
public class ModelListAction  extends BaseLyhtAction{

	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("ModelListAction");
	private ModelInfoFormBean modelInfoFormBean=new ModelInfoFormBean();
	/**
	 * 上传文件域对象
	 */
	private File[] file;
	/**
	 * 上传文件名
	 */	
	private String[] fileFileName;
	@Resource
	private ModelInfoControl modelInfoControl;
	@Resource
	private ModelInfoService modelInfoService;
	@Resource
	private ModelParamenterService modelParamenterService;
	@Resource
	private ModelParamenterControl modelParamenterControl;
	/**
	 * 获取模型列表
	 */
	public String list(){
		log.info("ModelListAction=list:获取模型列表");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= modelInfoControl.getModelMess(modelInfoFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);	
		}else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());	
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 初始化模型信息FORM表单数据
	 */
	public String edit(){
		log.info("ModelListAction=edit:初始化模型信息FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		ModelInfo modelInfo=new ModelInfo();
		List list=new ArrayList();
		mRetMessage=modelInfoControl.view(modelInfoFormBean.getModelInfoFormBean().getModelCode(), modelInfo);
		list=modelParamenterControl.getModelParaMess(modelInfoFormBean, modelInfo);
		mHashMap.put("ModelInfoFormBean", modelInfo);
		mHashMap.put("ModelPataList", list); //参数值list
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 保存模型信息form表单
	 */
	public String save(){
		log.info("ModelListAction=save:模型信息保存FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		ModelInfo modelInfo=new ModelInfo();
		if(modelInfoFormBean.getModelInfoFormBean().getModelCode()==null||modelInfoFormBean.getModelInfoFormBean().getModelCode().equals("")) {
			//新增模型和参数值
			mRetMessage=modelInfoControl.create(modelInfoFormBean.getModelInfoFormBean(),	modelInfoFormBean.getModelParaBean(),modelInfo);
		}else {
			//修改模型和参数值
			modelInfo=modelInfoService.getModeInfo(modelInfoFormBean.getModelInfoFormBean().getModelCode());
			mRetMessage=modelInfoControl.update(modelInfoFormBean.getModelInfoFormBean(), modelInfoFormBean.getModelInfoFormBean(),modelInfoFormBean.getModelParaBean());
		}
		mHashMap.put("modelInfoFormBean", modelInfo);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	/**
	 * 批量删除模型列表
	 */
	public String removeids(){
		log.info("批量删除==ModelListAction.removeids");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		String ids = CommonUtil.trim(modelInfoFormBean.getIds());
		PageResults mPageResults=new PageResults(); 
		mPageResults=modelInfoControl.getModelInfoListByIds(ids);
		mRetMessage=modelInfoControl.delModelInfoByCodes(ids, mPageResults.getResults());
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 导出
	 */
	public String export(){
		log.info("StbprpAction=export: 导出模型列表");
		PageResults prs = new PageResults();
		HttpServletRequest req = getRequest();
		HttpServletResponse res =getResponse();
		modelInfoControl.export(modelInfoFormBean, prs, req, res);
	return null;
	}
	/**
	 * 导入
	 */
	public String importModel(){
		log.info("ModelinfoAction=importModel: 导入模型列表");
		RetMessage ret=new RetMessage();
		ret=modelInfoControl.importModel(file, fileFileName);
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		return null;
	}
	
	/**
	 * 删除参数值
	 * @return
	 */
	public String delPara() {
		log.info("ModelinfoAction=delPara: 删除参数值");
		RetMessage ret=new RetMessage();
		ret=modelParamenterControl.depPara(modelInfoFormBean.getModelParaBean().getParaCode());
		if (ret.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_ERROR);
		}else{
			CommonFunction.writeResponse(getResponse(), RetMessage.RETFLAG_SUCCESS);
		}
		
		return null;
	}
	
	
	public String getModel() {
		log.info("ModelListAction=getModel:获取模型");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		PageResults mPageResults=new PageResults();
		mRetMessage= modelInfoControl.getModelNameByType(modelInfoFormBean, mPageResults);
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);	
		}else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());	
		}
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	
	public ModelInfoFormBean getModelInfoFormBean() {
		return modelInfoFormBean;
	}

	public void setModelInfoFormBean(ModelInfoFormBean modelInfoFormBean) {
		this.modelInfoFormBean = modelInfoFormBean;
	}

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

	
	
	
}
