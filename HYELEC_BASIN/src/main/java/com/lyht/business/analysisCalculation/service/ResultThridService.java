package com.lyht.business.analysisCalculation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.base.hibernate.common.PageResults;
import com.lyht.business.analysisCalculation.bean.Result;
import com.lyht.business.analysisCalculation.bean.ResultThrid;
import com.lyht.business.analysisCalculation.dao.ResultDao;
import com.lyht.business.analysisCalculation.dao.ResultThridDao;
import com.lyht.business.analysisCalculation.formbean.ResultFormBean;
import com.lyht.business.analysisCalculation.formbean.ResultThridFormBean;
import com.lyht.business.consumer.hydrologicalData.bean.River;
import com.lyht.business.consumer.hydrologicalData.dao.RiverDao;
import com.lyht.business.consumer.hydrologicalData.formbean.RiverFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.util.Randomizer;
/**
 *作者： 刘魁
 *脚本日期:2018年6月4日 15:24:11
 *说明:  产流计算结果service
*/
@Service
@Scope("prototype")
@Transactional
public class ResultThridService {
	@Resource ResultThridDao resultThridDao;
	@Resource RiverDao riverDao;
		/**
		 * 保存计算结果
		 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ResultThrid saveResultThrid(ResultThrid resultThrid) {
		resultThridDao.saveResultThrid(resultThrid);
		return resultThrid;
	}
	
	//查询计算结果
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ResultThrid getResultThridByTm(ResultThrid resultThrid) {
		return resultThridDao.getResultThridByTm(resultThrid);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getStep3(ResultThridFormBean resultThrid) {
		return resultThridDao.getStep3(resultThrid);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List  sumQ(ResultThridFormBean resultThrid) {
		return resultThridDao.sumQ(resultThrid);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List  sumQT(ResultThridFormBean resultThrid) {
		return resultThridDao.sumQT(resultThrid);
	}
	
	/**
	 * 第四步
	 * @param resultThrid
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getStep4(ResultThridFormBean resultThrid ) {
		return resultThridDao.getStep4(resultThrid);
	}
	
	public List  maxQ(ResultThridFormBean resultThrid) {
		return resultThridDao.maxQ(resultThrid);
	}
}
