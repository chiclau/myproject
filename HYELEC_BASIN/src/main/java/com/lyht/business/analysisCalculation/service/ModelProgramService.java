package com.lyht.business.analysisCalculation.service;

import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.ModelProgValue;
import com.lyht.business.analysisCalculation.bean.ModelProgram;
import com.lyht.business.analysisCalculation.dao.ModelProgValueDao;
import com.lyht.business.analysisCalculation.dao.ModelProgramDao;
import com.lyht.business.analysisCalculation.formbean.ModelProgramFromBean;
import com.lyht.business.modelManage.bean.ModelInfo;
import com.lyht.business.modelManage.formbean.ModelInfoFormBean;
import com.lyht.business.system.bean.SysStaff;
import com.lyht.util.Randomizer;

/**
 *作者： 刘魁
 *脚本日期:2018年5月8日 21:41:11
 *说明:  模型方案Service
*/
@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class ModelProgramService {
	@Resource
	private ModelProgramDao  modelProgramDao;
	@Resource
	private ModelProgValueDao  modelProgValueDao;
	
	/**
	 * 获取模型列表数据
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public  PageResults getMyPlanData(ModelProgramFromBean mpBean,ModelInfoFormBean modelInfoFormBean,SysStaff  mSysStaff) {
		return modelProgramDao.getModelProgram(mpBean,modelInfoFormBean,mSysStaff);
	}
	public Hashtable<String,Object> checkPlanName(String planCode,String planName,String stcd){
		Hashtable<String,Object> table = new Hashtable<String,Object>();
		List<ModelProgram> relist = modelProgramDao.queryModelProgramByCodeOrName(planCode, planName,stcd);
		if(relist!=null && relist.size()>0){
			table.put("reflag", "0");
			table.put("message", "方案名称【"+planName+"】已存在!");
		}else{
			table.put("reflag", "1");
		}
		return table;
	}
	/**
	 * 根据主键获取模型方案实体
	 * @return PageResults
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getById(String ids) {
		return modelProgramDao.getModelpgm(ids);
	}
	
	/**
	 * 根据主键获取实体
	 * @param code
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getModelProgram(ModelProgramFromBean mBean) {
		return 	modelProgramDao.getModelProgramById(mBean);
	}
	
	/**
	 * 根据主键删除模型方案实体
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delModelProgramByCodes(String ids) {
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			modelProgramDao.delModelPgm(idary[i]); //删除方案
			modelProgValueDao.deleteProValueByProg(idary[i]);//删除参数值
		}
	}
	
	/**
	 * 保存方案
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ModelProgram save(ModelProgram modelProgram,ModelInfo mInfo,ModelProgramFromBean mpv,SysStaff  mSysStaff) {
		modelProgram.setCreateStaff(mSysStaff.getStaffCode());
		String progCode=Randomizer.nextNumber("pc", 6);
		modelProgram.setProgCode(progCode);
		modelProgramDao.saveModelpgm(modelProgram);
		String[] paraName=mpv.getModelParaValueBean().getParaValue().split(",");//获取参数值数组
		String[] paraCode=mpv.getModelParaValueBean().getParaCode().split(",");//获取参数CODE数组
		if(paraName.length>0) {
			for(int i=0;i<paraName.length;i++) {
				ModelProgValue modelProgValue=new ModelProgValue();
				String code=Randomizer.nextNumber("code", 4);
				modelProgValue.setModelCode(modelProgram.getModelCode().trim());
				modelProgValue.setProgCode(modelProgram.getProgCode());
				modelProgValue.setParaValue(paraName[i].trim());
				modelProgValue.setParaCode(paraCode[i].trim());
				modelProgValue.setCode(code.trim());
				modelProgValueDao.saveModelpgm(modelProgValue); //保存参数值
			}
		}
		return  modelProgram;
	}
	
	/**
	 * 修改方案
	 * @param modelProgram
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ModelProgram update(ModelProgramFromBean modelProgramFormBean) {
		modelProgramDao.update(modelProgramFormBean.getModelprogramFormBean());//修改方案信息
		//修改参数值
		List<ModelProgValue> list=modelProgramFormBean.getValueList();
		for(ModelProgValue modelProgValue:list) {
			modelProgValueDao.updateValue(modelProgValue); //修改
		}
		return modelProgramFormBean.getModelprogramFormBean();
	}
}
