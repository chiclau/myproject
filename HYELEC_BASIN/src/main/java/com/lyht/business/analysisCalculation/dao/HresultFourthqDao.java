package com.lyht.business.analysisCalculation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.HresultFourthq;

@Repository
@Scope("prototype")
public class HresultFourthqDao extends HibernateBaseDao<HresultFourthq> {

	public void deleteHresultFourthqByStcdAndPch(String stcd,String pch){
		StringBuffer sql = new StringBuffer("DELETE FROM H_RESULT_FOURTH_Q WHERE STCD='"+stcd+"' and PCH='"+pch+"'" );
		this.excuteSql(sql.toString(), new Object[]{});
	}
	public List<Map> queryHresultForthqByStcdAndPch(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT A.*,CONVERT(CHAR(16),  A.TM, 120) AS DT FROM H_RESULT_FOURTH_Q A WHERE A.STCD='"+stcd+"' and A.PCH='"+pch+"' order by A.TM ASC" );
		return this.createSQLQuerybyMap(sql.toString());
	}
	public double maxJsQs(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT Max(QS) AS QS FROM H_RESULT_FOURTH_Q A WHERE A.STCD='"+stcd+"' and A.PCH='"+pch+"'" );
		List<Map> relist = this.createSQLQuerybyMap(sql.toString());
		if(relist!=null && relist.size()>0){
			return Double.valueOf(relist.get(0).get("QS").toString()).doubleValue();
		}
		return 0;
	}
	public List<Map> queryCresultStep3Result(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT AA.STCD,AA.PCH,CONVERT(CHAR(16),  AA.TM, 120) AS DT,AA.Q AS QS,BB.Q AS QJ,(AA.Q-BB.Q) AS QSQJ,NULL AS QPJ,NULL AS MI,NULL AS MI2,NULL AS MIQPJ,NULL AS MI2QI FROM (SELECT A.* FROM C_RESULT_THRID A"
				+ " WHERE A.STCD='"+stcd+"' and A.PCH='"+pch+"') AA LEFT JOIN ("
						+ "SELECT C.STCD,C.PCH,D.Q FROM (SELECT B.STCD,B.PCH,MAX(B.TM) AS TM FROM C_RESULT_THRID B"
				+ " WHERE B.STCD='"+stcd+"' and B.PCH='"+pch+"' GROUP BY B.STCD,B.PCH) C LEFT JOIN "
						+ "(SELECT A.* FROM C_RESULT_THRID A"
				+ " WHERE A.STCD='"+stcd+"' and A.PCH='"+pch+"') D ON C.STCD=D.STCD AND C.PCH=D.PCH AND C.TM=D.TM "
						+ ") BB ON AA.STCD=BB.STCD AND AA.PCH=BB.PCH ORDER BY AA.TM ASC " );
		return this.createSQLQuerybyMap(sql.toString());
	}
	public double maxYyQs(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT max(A.Q) AS QS FROM C_RESULT_THRID A WHERE A.STCD='"+stcd+"' and A.PCH='"+pch+"'" );
		List<Map> relist = this.createSQLQuerybyMap(sql.toString());
		if(relist!=null && relist.size()>0){
			return Double.valueOf(relist.get(0).get("QS").toString()).doubleValue();
		}
		return 0;
	}
}
