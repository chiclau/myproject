package com.lyht.business.search.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.lyht.base.hibernate.basedao.HibernateBaseDao;
import com.lyht.util.CommonUtil;

@Repository
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class SearchDao extends HibernateBaseDao {

	public List<Map> queryCezhan(String searchText){
		StringBuffer sb = new StringBuffer("select STCD,STNM,LGTD1,LTTD1,STTP from ST_STBPRP_B where 1=1 ");
		//查询条件 
		if(CommonUtil.trim(searchText).length()>0 && !CommonUtil.trim(searchText).equals("*")){
			sb.append(" and STNM LIKE '%"+CommonUtil.trim(searchText)+"%'");
		}
		return this.createSQLQuerybyMap(sb.toString());
	}
	public List<Map> queryCezhanByStnm(String searchText){
		StringBuffer sb = new StringBuffer("select STCD,STNM,LGTD1,LTTD1,STTP from ST_STBPRP_B where 1=1 and stnm='"+CommonUtil.trim(searchText)+"'");
		return this.createSQLQuerybyMap(sb.toString());
	}
}
