package com.lyht.business.modelManage.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.modelManage.bean.ModelParamenter;
import com.lyht.business.modelManage.dao.ModelInfoDao;
import com.lyht.business.modelManage.dao.ModelParamenterDao;
import com.lyht.business.modelManage.formbean.ModelInfoFormBean;
/**
 *作者： 刘魁
 *脚本日期:2018年5月10日 21:41:11
 *说明:  参数值Service
*/
@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class ModelParamenterService {
	@Resource
	private ModelParamenterDao mpDao;
	@Resource
	private ModelInfoDao modelInfoDao;
	@Resource
	private ModelInfoService mInfoService;
	/**
	 * 根据模型获取模型值
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getParaListData(ModelInfoFormBean mInfoBean){
		return mpDao.getParByInfo(mInfoBean);
	}
	
	/**
	 * 增加实体对象
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	public ModelParamenter savePara(ModelParamenter mP) {
		mpDao.save(mP);
		return mP;
	}
	
	/**
	 * 根据主键删除单个实体对象
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void delParaById(String ids) {
		String[] idary=ids.split(",");
		for(int i=0;i<idary.length;i++){
			mpDao.delModelParaById(idary[i]);
		}
	}
	
	/**
	 * 根据mode_code删除多个实体对象
	 */
	public void delParByCode(String code) {
		mpDao.delModelParaBymCode(code);
	}
	
	
	
}
