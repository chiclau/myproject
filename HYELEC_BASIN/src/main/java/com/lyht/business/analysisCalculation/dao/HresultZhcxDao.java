package com.lyht.business.analysisCalculation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.HresultZhcx;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class HresultZhcxDao extends HibernateBaseDao<HresultZhcx> {

	public List<HresultZhcx> queryByStcdOrPch(String stcd,String pch){
		StringBuffer hql = new StringBuffer("from HresultZhcx where stcd=?");
		if(CommonUtil.trim(pch).length()>0){
			hql.append(" and pch='"+pch+"'");
		}
		hql.append(" order by pch asc ");
		return this.getListByHQL(hql.toString(), new Object[]{stcd});
	}
	
	public void deleteByStcd(String stcd){
		StringBuffer sql = new StringBuffer("delete from H_RESULT_ZHCX WHERE STCD='"+stcd+"'");
		this.excuteSql(sql.toString(), new Object[]{});
	}
	public List<Map> queryStep5Table1Data(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT A.*,B.MC,B.MJMC FROM H_RESULT A LEFT JOIN H_RESULT_ZHCX B ON A.STCD=B.STCD AND A.PCH=B.PCH where A.STCD='"+stcd+"'");
		if(CommonUtil.trim(pch).length()>0){
			sql.append(" and A.PCH='"+pch+"'");
		}
		sql.append(" order by A.PCH ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
}
