package com.lyht.business.analysisCalculation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.HresultFourthe;

@Repository
@Scope("prototype")
public class HresultFourtheDao extends HibernateBaseDao<HresultFourthe> {

	public void deleteHresultFortheByStcdAndPch(String stcd,String pch){
		StringBuffer sql = new StringBuffer("DELETE FROM H_RESULT_FOURTH_E WHERE STCD='"+stcd+"' and PCH='"+pch+"'" );
		this.excuteSql(sql.toString(), new Object[]{});
	}
	public List<Map> queryHresultFortheByStcdAndPch(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT A.*,CONVERT(CHAR(16),  A.TM, 120) AS DT FROM H_RESULT_FOURTH_E A WHERE A.STCD='"+stcd+"' and A.PCH='"+pch+"' order by A.TM ASC" );
		return this.createSQLQuerybyMap(sql.toString());
	}
	
	public List<Map> queryCresultStep6Result(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT A.STCD,A.PCH,isnull(A.R,0) AS II,NULL AS MI,NULL AS MI2,NULL AS IIMI,NULL AS IIMI2,CONVERT(CHAR(16),  A.TM, 120) AS DT FROM C_RESULT_SIXTH A WHERE A.STCD='"+stcd+"' and A.PCH='"+pch+"' order by A.TM ASC" );
		return this.createSQLQuerybyMap(sql.toString());
	}
}
