package com.lyht.business.analysisCalculation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.HresultFourthZhcx;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class HresultFourthZhcxDao extends HibernateBaseDao<HresultFourthZhcx> {

	public List<HresultFourthZhcx> queryByStcdOrPch(String stcd,String pch){
		StringBuffer hql = new StringBuffer("from HresultFourthZhcx where stcd=?");
		if(CommonUtil.trim(pch).length()>0){
			hql.append(" and pch='"+pch+"'");
		}
		hql.append(" order by pch asc ");
		return this.getListByHQL(hql.toString(), new Object[]{stcd});
	}
	public void deleteByStcd(String stcd){
		StringBuffer sql = new StringBuffer("delete from H_RESULT_FOURTH_ZHCX WHERE STCD='"+stcd+"'");
		this.excuteSql(sql.toString(), new Object[]{});
	}
	public List<Map> queryStep5Table4Data(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT B.* FROM H_RESULT_FOURTH_ZHCX B where B.STCD='"+stcd+"'");
		if(CommonUtil.trim(pch).length()>0){
			sql.append(" and B.PCH='"+pch+"'");
		}
		sql.append(" order by B.PCH ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
}
