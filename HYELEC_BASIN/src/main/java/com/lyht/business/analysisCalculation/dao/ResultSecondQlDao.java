package com.lyht.business.analysisCalculation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.business.analysisCalculation.bean.ResultSecondQl;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
public class ResultSecondQlDao extends HibernateBaseDao<ResultSecondQl> {

	public List<Map> queryStep2InitData(String stcd,String pch){
		StringBuffer sql = new StringBuffer("SELECT CONVERT(CHAR(16),  TM, 120) as DATE,YML AS JYL,Q AS LL,STCD,PCH FROM C_RESULT_SECOND_QL WHERE STCD='"+stcd+"' and PCH='"+pch+"' order by TM ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	/**
	 * 查询某个测站某个时刻最近的流量数据
	 * @param stcd
	 * @param starttime
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map queryLastByStartTime(String stcd,String starttime){
		StringBuffer sql = new StringBuffer("select TOP 1 CONVERT(VARCHAR(16), TM,120) as TM,Q "
				+ " from ST_RIVER_R where STCD='"+stcd+"'"
						+ " and CONVERT(VARCHAR(16), TM,120)<'"+starttime+"' and TM IS NOT NULL AND Q IS NOT NULL order by TM DESC ");
		List<Map> relist = this.createSQLQuerybyMap(sql.toString());
		if(relist!=null && relist.size()>0){
			return relist.get(0);
		}
		return null;
	}
	public List<Map> queryStep2LlData(String stcd,String start,String end){
		StringBuffer sql = new StringBuffer("select CONVERT(VARCHAR(16), TM,120) as TM, Q "
				+ " from ST_RIVER_R where STCD='"+stcd+"'"
						+ " and CONVERT(VARCHAR(16), TM,120)>='"+start+"'"
								+ " and CONVERT(VARCHAR(16), TM,120)<='"+end+"' and Q is not null order by TM ASC ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public Map queryLastByEndTime(String stcd,String end){
		StringBuffer sql = new StringBuffer("select TOP 1 CONVERT(VARCHAR(16), TM,120) as TM,Q "
				+ " from ST_RIVER_R where STCD='"+stcd+"'"
						+ " and CONVERT(VARCHAR(16), TM,120)>'"+end+"' AND Q IS NOT NULL AND TM IS NOT NULL order by TM ASC ");
		List<Map> relist = this.createSQLQuerybyMap(sql.toString());
		if(relist!=null && relist.size()>0){
			return relist.get(0);
		}
		return null;
	}
	public List<Map> queryStep2InitData(String stcd,String pch,String start,String end,int interval){
		StringBuffer  sql=new StringBuffer ();
		sql.append("SELECT A.TM AS DATE,ISNULL(C.YML,0) AS JYL,A.STCD "
				+ " FROM (SELECT CONVERT(VARCHAR(16), "
				+ " DateAdd(mi,number*"+interval+",'"+start+"'),120) AS TM,'"+stcd+"' as STCD FROM master..spt_values "
						+ " WHERE type = 'p' AND number <= DateDiff(mi,'"+start+"','"+end+"')/"+interval+") A "
				+ " LEFT JOIN (select * from C_RESULT where pch='"+pch+"' AND STCD='"+stcd+"') C ON A.TM=C.YML_TIME AND A.STCD=C.STCD where 1=1 ");
		sql.append("  ORDER BY A.TM ASC  ");
		return this.createSQLQuerybyMap(sql.toString());
	}
	public int deleteStep2Data(String stcd,String pch){
		StringBuffer sql = new StringBuffer(" delete from C_RESULT_SECOND_QL where STCD=? AND PCH=? ");
		return this.excuteSql(sql.toString(), new Object[]{stcd,pch});
	}
}
