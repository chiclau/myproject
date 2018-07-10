package com.lyht.business.analysisCalculation.control;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lyht.RetMessage;
import com.lyht.annotations.Optlog;
import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ModelProgValue;
import com.lyht.business.analysisCalculation.bean.ModelProgram;
import com.lyht.business.analysisCalculation.formbean.ModelProgValFromBean;
import com.lyht.business.analysisCalculation.formbean.ModelProgramFromBean;
import com.lyht.business.analysisCalculation.service.ModelProgramService;
import com.lyht.business.modelManage.bean.ModelInfo;
import com.lyht.business.modelManage.bean.ModelParamenter;
import com.lyht.business.modelManage.formbean.ModelInfoFormBean;
import com.lyht.business.modelManage.formbean.ModelParaFormBean;
import com.lyht.business.modelManage.service.ModelInfoService;
import com.lyht.business.modelManage.service.ModelParamenterService;
import com.lyht.business.system.bean.SysStaff;

/**
 *作者： 刘魁
 *脚本日期:2018年5月12日 14:41:11
 *说明:  我的方案control
*/
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class ModelProgramControl {
	@Resource
	private ModelProgramService modelProgramService;
	
	/**查询我的方案列表
	 * 
	 * @param modelInfoFormBean
	 * @param mPageResults
	 * @return
	 */
	@Optlog(menuflag="myPlanList", opttype = "getMyPlanMes") 
	public RetMessage getModelMess(ModelProgramFromBean mpBean,ModelInfoFormBean modelInfoFormBean,PageResults mPageResults,SysStaff  mSysStaff) {
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(mPageResults,modelProgramService.getMyPlanData(mpBean,modelInfoFormBean,mSysStaff));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	
	/**
	 * 根据ID获取实体
	 */
	@Optlog(menuflag="myPlanUpdate",opttype = "getModelProgramByCode") 
	public PageResults getModelProgramByIds(String ids){
		PageResults mPageResults=new PageResults();
		try {
			mPageResults=modelProgramService.getById(ids);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return mPageResults;
	}
	
	/**
	 * 通过ID删除
	 * @param ids
	 * @param list
	 * @return
	 */
	@Optlog(menuflag="myPlanDelete",opttype = "delModelProgramByCodes") 
	public RetMessage delModelInfoByCodes(String ids,List list) {
		RetMessage mRetMessage=new RetMessage();
		try {
			modelProgramService.delModelProgramByCodes(ids);
			list.clear();
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("删除数据成功！");
		} catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"删除数据失败！");
		}
		return mRetMessage;
	}
	
	/**
	 * 通过id查找实体
	 * @param id
	 * @param modelProgram
	 * @return
	 */
	@Optlog(menuflag="modelProaramfoView", opttype = "view") 
	public RetMessage view(String id,ModelProgram modelProgram) {
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(modelProgram,modelProgramService.getModelProgram(id));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	/**
	 * 查看当前用户信息
	 */
	@Optlog(menuflag="modelProaramfoView", opttype = "view") 
	public RetMessage viewStaff(SysStaff  mSysStaff,ModelProgram modelProgram) {
		RetMessage mRetMessage=new RetMessage();
		try{
			BeanUtils.copyProperties(modelProgram,mSysStaff);
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("查询数据成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"查询数据失败！");
		}
		return mRetMessage;
	}
	/**
	 * 保存方案
	 * @param modelProgram
	 * @param mInfo
	 * @param mpBean
	 * @param mpv
	 * @param mSysStaff
	 * @return
	 */
	@Optlog(menuflag="modelProaramCreate", opttype = "create")  
	public  RetMessage create(ModelProgram modelProgram,ModelInfo mInfo,ModelProgramFromBean mpv,SysStaff  mSysStaff) {
		RetMessage mRetMessage=new RetMessage();
		try {
			BeanUtils.copyProperties(modelProgram,modelProgramService.save(modelProgram, mInfo, mpv, mSysStaff));
			mRetMessage.setRetflag(RetMessage.RETFLAG_SUCCESS);
			mRetMessage.setMessage("新增信息成功！");
		}catch (Exception e) {
			mRetMessage.setRetflag(RetMessage.RETFLAG_ERROR);
			mRetMessage.setMessage(RetMessage.ERROR_SERVICE_MSG+"新增信息失败！");
		}
		return mRetMessage;
	}
	
}
