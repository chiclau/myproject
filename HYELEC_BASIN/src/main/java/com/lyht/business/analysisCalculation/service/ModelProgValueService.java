package com.lyht.business.analysisCalculation.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyht.business.analysisCalculation.dao.ModelProgValueDao;
import com.lyht.business.analysisCalculation.dao.ModelProgramDao;

/**
 *作者： 刘魁
 *脚本日期:2018年5月12日 17:41:11
 *说明:  方案参数值Service
*/
@Service
@Scope("prototype")
@Transactional
@SuppressWarnings("rawtypes")
public class ModelProgValueService {
	@Resource
	private ModelProgramDao  modelProgramDao;
	@Resource
	private ModelProgValueDao  modelProgValueDao;
	
}
