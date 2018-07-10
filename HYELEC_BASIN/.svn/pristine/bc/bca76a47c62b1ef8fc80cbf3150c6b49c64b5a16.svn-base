package com.lyht.business.analysisCalculation.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.Constants;
import com.lyht.RetMessage;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ModelProgValue;
import com.lyht.business.analysisCalculation.bean.ModelProgram;
import com.lyht.business.analysisCalculation.control.ModelProgramControl;
import com.lyht.business.analysisCalculation.formbean.ModelProgValFromBean;
import com.lyht.business.analysisCalculation.formbean.ModelProgramFromBean;
import com.lyht.business.modelManage.bean.ModelInfo;
import com.lyht.business.modelManage.control.ModelInfoControl;
import com.lyht.business.modelManage.control.ModelParamenterControl;
import com.lyht.business.modelManage.formbean.ModelInfoFormBean;
import com.lyht.business.system.bean.SysRole;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.BaseLyhtAction;
import com.lyht.util.CommonFunction;

import net.sf.json.JSONArray;

/**
 *作者： 刘魁
 *脚本日期:2018年5月12日 17:31:11
 *说明:  创建方案Action
*/
@Namespace("/cjfa")
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
@Action("cjfa")
public class CjfaAction extends BaseLyhtAction {

private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger("CjfaAction");
	private ModelProgramFromBean modelprogramFormBean=new ModelProgramFromBean();
	private ModelInfoFormBean modelInfoFormBean=new ModelInfoFormBean();
	private ModelProgValFromBean   modelProgValFromBean=new ModelProgValFromBean();
	private String modelCode;
	@Resource
	private ModelProgramControl modelProgramControl;
	@Resource
	private ModelInfoControl mInfoControl;
	@Resource
	private ModelParamenterControl modelParamenterControl;
	public String list(){
		log.info("CjfaAction=list:获取创建方案信息");
		HashMap<String, Object> mHashMap=new HashMap<String,Object>();
		RetMessage mRetMessage=new RetMessage();
		ModelProgram modelProgram=new ModelProgram();
		SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		mRetMessage=modelProgramControl.viewStaff(mSysStaff, modelProgram); //获取用户信息
		mHashMap.put("mSysStaff", mSysStaff);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 * 保存方案
	 */
	public String save() {
		log.info("CjfaAction=save:保存方案FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		ModelProgram modelProgram=new ModelProgram();
		ModelInfo mInfo=new ModelInfo();
		ModelProgValue mpv=new ModelProgValue();
		SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF);
		//保存方案
		mRetMessage=modelProgramControl.create(modelprogramFormBean.getModelprogramFormBean(), mInfo, modelprogramFormBean, mSysStaff);
		mRetMessage.setMessage("保存方案成功!");
		mRetMessage.setRetflag(SUCCESS);
		String progCode=	modelprogramFormBean.getModelprogramFormBean().getProgCode();
		mHashMap.put("progCode", progCode);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	
	}
	/**
	 * 初始化Ztree，加载数据并把用户信息赋值到前台
	 * @return
	 */
	public String model() {
		log.info("CjfaAction=model:保存方案FORM表单数据");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		RetMessage mRetMessage1=new RetMessage();
		RetMessage mRetMessage2=new RetMessage();
		PageResults mPageResults=new PageResults();
		PageResults mPageResults1=new PageResults();
		PageResults mPageResults2=new PageResults();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
		String date = df.format(new Date());
		mHashMap.put("date", date); //当前时间
		mRetMessage=mInfoControl.getModelNameByType(modelInfoFormBean, mPageResults); //查询模型名称
		mRetMessage1=mInfoControl.getModelHuiliu(modelInfoFormBean, mPageResults1); //汇流
		mRetMessage2=mInfoControl.getModelChanliu(modelInfoFormBean, mPageResults2);//产流
		if (mRetMessage.getRetflag().equals(RetMessage.RETFLAG_ERROR)){
			JSONArray mJSONArray = new JSONArray();
			mHashMap.put("total", 0);
			mHashMap.put("rows", mJSONArray);	
			mHashMap.put("huiliu", mPageResults1.getResults());	
			mHashMap.put("chanliu", mPageResults2.getResults());	
		}else {
			mHashMap.put("total", mPageResults.getTotalCount());
			mHashMap.put("rows", mPageResults.getResults());	
			mHashMap.put("huiliu", mPageResults1.getResults());	
			mHashMap.put("chanliu", mPageResults2.getResults());	
		}
		SysStaff  mSysStaff = (SysStaff) getSession().getAttribute(Constants.SESSION_STAFF); //当前用户
		mHashMap.put("mSysStaff", mSysStaff);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	}
	/**
	 *modelCode 获取参数列表
	 * @return
	 */
	public String getPara() {
		log.info("CjfaAction=getPara:参数列表");
		HashMap<String, Object> mHashMap = new HashMap<String, Object>();
		RetMessage mRetMessage=new RetMessage();
		ModelInfo modelInfo=new ModelInfo();
		List list =new ArrayList();
		modelInfoFormBean.getModelInfoFormBean().setModelCode(modelCode); //modelcode
		list=modelParamenterControl.getModelParaMess(modelInfoFormBean, modelInfo);
		mHashMap.put(RetMessage.AJAX_RETFLAG, mRetMessage.getRetflag());
		mHashMap.put(RetMessage.AJAX_MESSAGE, mRetMessage.getMessage());		
		mHashMap.put("list",list);
		CommonFunction.writeResponse(getResponse(), mHashMap);
		return null;
	
	}
	
	
	public ModelProgramFromBean getModelprogramFormBean() {
		return modelprogramFormBean;
	}
	public void setModelprogramFormBean(ModelProgramFromBean modelprogramFormBean) {
		this.modelprogramFormBean = modelprogramFormBean;
	}
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	public ModelProgValFromBean getModelProgValFromBean() {
		return modelProgValFromBean;
	}
	public void setModelProgValFromBean(ModelProgValFromBean modelProgValFromBean) {
		this.modelProgValFromBean = modelProgValFromBean;
	}

	
	
	

}