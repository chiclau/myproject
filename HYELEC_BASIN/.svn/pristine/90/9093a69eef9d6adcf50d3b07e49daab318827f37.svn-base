package com.lyht.business.analysisCalculation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.Hresult;
@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class HresultDao extends HibernateBaseDao<Hresult> {
	/**
	 * 查询测站所有洪号汇流第一步计算结果数据
	 * @param stcd
	 * @return
	 */
	public List<Hresult> findByStcdOrderBy(String stcd){
		StringBuffer hql = new StringBuffer("from Hresult where stcd=? order by m asc");
		return this.getListByHQL(hql.toString(), new Object[]{stcd});
	}
	public Map queryLastLJvalueBean(String stcd){
		StringBuffer sql = new StringBuffer("SELECT STCD,PCH,L,J FROM V_LAST_L_J where STCD='"+stcd+"' GROUP BY STCD,PCH,L,J ORDER BY PCH DESC");
		List<Map> relist = this.createSQLQuerybyMap(sql.toString());
		if(relist!=null && relist.size()>0){
			return relist.get(0);
		}
		return null;
	}
}
