package com.lyht.business.analysisCalculation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.HResultSecond;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class HResultSecondDao extends HibernateBaseDao<HResultSecond> {

	/**
	 * 查询测站所有洪号汇流第二步计算结果数据
	 * @param stcd
	 * @return
	 */
	public List<HResultSecond> findByStcdOrderBy(String stcd){
		StringBuffer hql = new StringBuffer("from HResultSecond where stcd=? order by m asc");
		return this.getListByHQL(hql.toString(), new Object[]{stcd});
	}
	/**
	 * 根据站码，洪号查询第一个表格数据
	 * @param stcd
	 * @param pch
	 * @return
	 */
	public HResultSecond findByStcdAndPch(String stcd,String pch){
		StringBuffer hql = new StringBuffer("from HResultSecond where stcd=? and pch=?");
		List<HResultSecond> relist = this.getListByHQL(hql.toString(), new Object[]{stcd,pch});
		if(relist!=null && relist.size()>0){
			return relist.get(0);
		}
		return null;
	}
}
