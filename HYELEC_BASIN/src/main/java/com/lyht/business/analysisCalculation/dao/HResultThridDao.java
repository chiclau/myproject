package com.lyht.business.analysisCalculation.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.HResultSecond;
import com.lyht.business.analysisCalculation.bean.HResultThrid;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class HResultThridDao extends HibernateBaseDao<HResultThrid> {

	/**
	 * 查询测站所有洪号汇流第三步计算结果数据
	 * @param stcd
	 * @return
	 */
	public List<HResultThrid> findByStcdOrderBy(String stcd){
		StringBuffer hql = new StringBuffer("from HResultThrid where stcd=? order by m asc");
		return this.getListByHQL(hql.toString(), new Object[]{stcd});
	}
	/**
	 * 查询测站所有洪号汇流第三步计算结果数据
	 * @param stcd
	 * @return
	 */
	public HResultThrid findByStcdAndPch(String stcd,String pch){
		StringBuffer hql = new StringBuffer("from HResultThrid where stcd=? and pch=? order by nm asc");
		List<HResultThrid> relist= this.getListByHQL(hql.toString(), new Object[]{stcd,pch});
		if(relist!=null && relist.size()>0){
			return relist.get(0);
		}
		return null;
	}
}
