package com.lyht.business.analysisCalculation.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.ResultThirdLess;

@Repository
@Scope("prototype")
public class ResultThirdLessDao extends HibernateBaseDao<ResultThirdLess> {

	public int deleteByStcdAndPch(String stcd,String pch){
		StringBuffer sql = new StringBuffer("delete from C_RESULT_THRID_LESS where STCD='"+stcd+"' and PCH='"+pch+"'");
		return this.excuteSql(sql.toString(), new Object[]{});
	}
}
