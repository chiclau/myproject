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
import com.lyht.business.analysisCalculation.bean.ResultFourth;
import com.lyht.business.analysisCalculation.bean.ResultThrid;
import com.lyht.business.analysisCalculation.dao.ResultDao;
import com.lyht.business.analysisCalculation.dao.ResultFourthDao;
import com.lyht.business.analysisCalculation.dao.ResultThridDao;
import com.lyht.business.analysisCalculation.formbean.ResultFormBean;
import com.lyht.business.analysisCalculation.formbean.ResultFourthFormBean;
import com.lyht.business.analysisCalculation.formbean.ResultThridFormBean;
import com.lyht.business.consumer.hydrologicalData.bean.River;
import com.lyht.business.consumer.hydrologicalData.dao.RiverDao;
import com.lyht.business.consumer.hydrologicalData.formbean.RiverFormBean;
import com.lyht.business.consumer.hydrologicalData.formbean.TsqxFormBean;
import com.lyht.util.Randomizer;
/**
 *作者： 刘魁
 *脚本日期:2018年6月24日 15:24:11
 *说明:  产流计算结果第四步service
*/
@Service
@Scope("prototype")
@Transactional
public class ResultFourthService {
	@Resource ResultFourthDao resultFourthDao;
	@Resource RiverDao riverDao;
		/**
		 * 保存第四步结果
		 */
	@Transactional(propagation=Propagation.REQUIRED)
	public ResultFourth saveResultFourth(ResultFourth resultFourth) {
		resultFourthDao.saveResutFourth(resultFourth);;
		return resultFourth;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public ResultFourth getResultFourthByTm(ResultFourth resultFourth) {
		return resultFourthDao.getResutFourthByTm(resultFourth);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getStep4(ResultFourthFormBean resultThrid) {
		return resultFourthDao.getStep4Table(resultThrid);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List  sumQdx(ResultFourthFormBean resultFourth) {
		return resultFourthDao.sumQdx(resultFourth);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List  sumQT(ResultFourthFormBean resultFourth) {
		return resultFourthDao.sumQT(resultFourth);
	}
	/**
	 * 汇流计算第一步查询
	 * @param rf
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public PageResults getHuiLiuStep1(ResultFourthFormBean rf) {
		return resultFourthDao.getHuiLiuStep1(rf);
	}
	
}
