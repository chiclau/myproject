package com.lyht.business.analysisCalculation.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.DicEmpt;

@Repository
@Scope("prototype")
public class DicEmptDao extends HibernateBaseDao<DicEmpt> {

	/**
	 * 查询所有的
	 * @return
	 */
	public List<DicEmpt> m_queryAllList(){
		StringBuffer hql = new StringBuffer("from DicEmpt where 1=1");
		return this.getListByHQL(hql.toString(), new Object[]{});
	}
	
}
