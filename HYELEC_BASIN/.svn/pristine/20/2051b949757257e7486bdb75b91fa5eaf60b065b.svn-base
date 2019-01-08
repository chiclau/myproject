package com.lyht.business.analysisCalculation.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.ResultSixPpa;
@Repository
@Scope("prototype")
public class ResultSixPpaDao extends HibernateBaseDao<ResultSixPpa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<ResultSixPpa> queryResultSixPpaByStcdAndPch(String stcd,String pch){
		StringBuffer hql = new StringBuffer("from ResultSixPpa where stcd=? and pch=? order by r asc");
		return this.getListByHQL(hql.toString(), new Object[]{stcd,pch});
	}

}
