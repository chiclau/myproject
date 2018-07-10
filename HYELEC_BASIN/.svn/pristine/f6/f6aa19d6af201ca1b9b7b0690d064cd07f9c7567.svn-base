package com.lyht.business.analysisCalculation.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.analysisCalculation.bean.Result;
import com.lyht.business.analysisCalculation.dao.ResultDao;
/**
 *作者： 刘魁
 *脚本日期:2018年6月4日 15:24:11
 *说明:  产流计算结果service
*/
@Service
@Scope("prototype")
@Transactional
public class ResultService {
	@Resource ResultDao resuldDao;
	
	
		/**
		 * 保存计算结果
		 */
		@Transactional(propagation=Propagation.REQUIRED)
		public Result saveResult(Result result,Result result1) {
			resuldDao.saveResult(result);
			return result1;
		}

		/**
		 * 根据id删除
		 */
		public void delResult(String ids) {
			resuldDao.delResult(ids);
		}





}
