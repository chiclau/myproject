package com.lyht.business.analysisCalculation.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.analysisCalculation.bean.ResultJg;
import com.lyht.business.analysisCalculation.dao.ResultJgDao;
@Service
@Scope("prototype")
@Transactional
public class ResultJgService {
	@Resource
	private ResultJgDao rjDao;
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveResultJg(ResultJg rj) {
		rjDao.delResultJg(rj);//先删除
		rjDao.saveResultJg(rj);//新增
	}
	
}
