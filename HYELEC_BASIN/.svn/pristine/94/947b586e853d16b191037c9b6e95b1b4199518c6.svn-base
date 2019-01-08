package com.lyht.business.analysisCalculation.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.HResultThrid;
import com.lyht.business.analysisCalculation.bean.HresultFourth;

@Repository
@Scope("prototype")
public class HresultFourthDao extends HibernateBaseDao<HresultFourth>  {

	public void deleteHresultFourthqByStcdAndPch(String stcd,String pch){
		StringBuffer sql = new StringBuffer("DELETE FROM H_RESULT_FOURTH WHERE STCD='"+stcd+"' and PCH='"+pch+"'" );
		this.excuteSql(sql.toString(), new Object[]{});
	}
	/**
	 * 查询测站所有洪号汇流第三步计算结果数据
	 * @param stcd
	 * @return
	 */
	public List<HresultFourth> findByStcdOrderBy(String stcd){
		StringBuffer hql = new StringBuffer("from HresultFourth where stcd=? order by m1 asc");
		return this.getListByHQL(hql.toString(), new Object[]{stcd});
	}
	public HresultFourth queryHresultFourthByStcdAndPch(String stcd,String pch){
		StringBuffer hql = new StringBuffer("from HresultFourth where stcd=? and pch=? order by pch asc");
		List<HresultFourth> relist=this.getListByHQL(hql.toString(), new Object[]{stcd,pch});
		if(relist!=null && relist.size()>0){
			return relist.get(0);
		}
		return null;
	}
}
