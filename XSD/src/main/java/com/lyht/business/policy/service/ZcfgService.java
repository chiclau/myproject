package com.lyht.business.policy.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.policy.bean.ZcfgInfo;
import com.lyht.business.policy.dao.ZcfgDao;
import com.lyht.business.policy.formbean.ZcfgInfoFormBean;
import com.lyht.util.Randomizer;

@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("all")
public class ZcfgService {

	@Resource
	private ZcfgDao zcfgDao;
	
	/**
	 * 获取政策法规信息
	 * @param searchName 
	 * @param endDate 
	 * @param startDate 
	 * @param ssbm 
	 * @param fgly 
	 * @param mZcfgInfoFormBean
	 * @return
	 */
	public PageResults getDzListData(int page, int limit, String fgly, String ssbm, String startDate, String endDate, String searchName) {
		return zcfgDao.getZcfgListData(page,limit,fgly,ssbm,startDate,endDate,searchName);
	}
	/**
	 * 获取政策法规信息
	 * @param mZcfgInfoFormBean
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getZcfgListData(ZcfgInfoFormBean mZcfgInfoFormBean) {
		return zcfgDao.getZcfgListData(mZcfgInfoFormBean);
	}
	
	/**
	 * 查询政策法规文件来源
	 * @param condition 
	 * @return
	 */
	public List<Map> initWjly(String condition) {
		return zcfgDao.initWjly(condition);
	}
	
	/**
	 * 删除法规政策
	 * @return
	 */
	public boolean delete(String fgbm) {
		return zcfgDao.delete(fgbm);
	}
	
	public boolean auditZcfgInfo(String fgbm) {
		//ZcfgInfo zcfgInfo = zcfgDao.findOne(fgbm);
		try {
			/*if(zcfgInfo.getZt() == 0){
				zcfgDao.auditZcfgInfo(1,fgbm);
			}else{
				zcfgDao.auditZcfgInfo(0,fgbm);
			}*/
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 
	 * @param mZcfgInfoFormBean
	 * @param path
	 * @param name 
	 * @return
	 */
	public boolean saveZcfg(ZcfgInfoFormBean mZcfgInfoFormBean, String path, String name) {
		if(mZcfgInfoFormBean.getZcfgInfo().getFgbm() != null && !"".equals(mZcfgInfoFormBean.getZcfgInfo().getFgbm())){
			return zcfgDao.updateZcfg(mZcfgInfoFormBean,path,name);
		}else{
			mZcfgInfoFormBean.getZcfgInfo().setFgbm(Randomizer.generCode(10));
			return zcfgDao.saveZcfg(mZcfgInfoFormBean,path,name);
		}
	}
	
	/**
	 * 根据法规编码查询政策法规信息
	 * @param mZcfgInfoFormBean
	 * @return
	 */
	public PageResults findOne(ZcfgInfoFormBean mZcfgInfoFormBean) {
		return zcfgDao.findOne(mZcfgInfoFormBean);	
	}
}
